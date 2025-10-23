package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.AutoCompletionPolicy
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElementFactory
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiModifier
import com.intellij.psi.PsiModifierList
import com.intellij.psi.PsiModifierListOwner
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext

class ObservatorAnnotationCompletionContributor(
    private val state: IState = getInstance().state
) : CompletionContributor(), IState by state {

    init {
        val annotationContext = PlatformPatterns.psiElement(PsiIdentifier::class.java)
            .withParent(PsiJavaCodeReferenceElement::class.java)
            .withSuperParent(2, PsiAnnotation::class.java)
            .withSuperParent(3, PsiModifierList::class.java)

        extend(CompletionType.BASIC, annotationContext, ObservatorCompletionProvider())
    }

    private inner class ObservatorCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
            parameters: CompletionParameters,
            context: ProcessingContext,
            result: CompletionResultSet
        ) {
            if (!pseudoAnnotations) return

            val annotation = PsiTreeUtil.getParentOfType(parameters.position, PsiAnnotation::class.java, false) ?: return
            val modifierList = PsiTreeUtil.getParentOfType(annotation, PsiModifierList::class.java, false) ?: return
            val owner = modifierList.parent
            if (owner !is PsiField && owner !is PsiClass) return

            val lookup = LookupElementBuilder.create("Observator")
                .withLookupString("@Observator")
                .withPresentableText("@Observator")
                .withInsertHandler { ctx, _ ->
                    handleInsert(ctx)
                }
                .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

            result.addElement(lookup)
        }
    }

    private fun handleInsert(ctx: com.intellij.codeInsight.completion.InsertionContext) {
        val project = ctx.project
        val element = ctx.file.findElementAt(ctx.startOffset) ?: return
        val annotation = PsiTreeUtil.getParentOfType(element, PsiAnnotation::class.java, false) ?: return
        val modifierList = PsiTreeUtil.getParentOfType(annotation, PsiModifierList::class.java, false) ?: return
        val owner = modifierList.parent as? PsiModifierListOwner ?: return

        val targetClass = when (owner) {
            is PsiField -> owner.containingClass
            is PsiClass -> owner
            else -> null
        } ?: return

        val targetFields = when (owner) {
            is PsiField -> listOf(owner)
            is PsiClass -> owner.fields.toList()
            else -> emptyList()
        }.filterNot { field ->
            field.hasModifierProperty(PsiModifier.STATIC) || field.hasModifierProperty(PsiModifier.FINAL)
        }

        if (targetFields.isEmpty()) {
            WriteCommandAction.runWriteCommandAction(project) {
                removeAnnotation(annotation)
            }
            return
        }

        WriteCommandAction.runWriteCommandAction(project) {
            removeAnnotation(annotation)
            val elementFactory = JavaPsiFacade.getElementFactory(project)

            val addedElements = mutableListOf<com.intellij.psi.PsiElement>()
            ensureSupportField(targetClass, elementFactory)?.let(addedElements::add)

            var caretTarget: com.intellij.psi.PsiElement? = null
            targetFields.forEach { field ->
                ensureObservationMethods(targetClass, field, elementFactory).let { generated ->
                    addedElements += generated
                    if (caretTarget == null) {
                        caretTarget = generated.firstOrNull { it is PsiMethod && it.name.startsWith("set") }
                    }
                }
            }

            val codeStyleManager = CodeStyleManager.getInstance(project)
            addedElements.forEach { element ->
                codeStyleManager.reformat(element)
            }
            JavaCodeStyleManager.getInstance(project).shortenClassReferences(targetClass)

            val caretElement = caretTarget ?: addedElements.firstOrNull()
            caretElement?.textOffset?.let { offset ->
                PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(ctx.document)
                ctx.editor.caretModel.moveToOffset(offset)
            }
        }
    }

    private fun ensureSupportField(psiClass: PsiClass, elementFactory: PsiElementFactory): PsiField? {
        val existing = psiClass.fields.firstOrNull { it.name == OBSERVATOR_SUPPORT_FIELD }
        if (existing != null) return null

        val fieldText = "private final java.beans.PropertyChangeSupport $OBSERVATOR_SUPPORT_FIELD = new java.beans.PropertyChangeSupport(this);"
        val field = elementFactory.createFieldFromText(fieldText, psiClass)
        return psiClass.add(field) as? PsiField
    }

    private fun ensureObservationMethods(
        psiClass: PsiClass,
        field: PsiField,
        elementFactory: PsiElementFactory
    ): List<com.intellij.psi.PsiElement> {
        val generatedElements = mutableListOf<com.intellij.psi.PsiElement>()
        val fieldName = field.name ?: return generatedElements
        val capitalized = fieldName.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val typeText = field.type.presentableText

        val setterName = "set$capitalized"
        if (!hasSetter(psiClass, setterName)) {
            val setterText = """
                public void $setterName($typeText $fieldName) {
                    $typeText oldValue = this.$fieldName;
                    this.$fieldName = $fieldName;
                    $OBSERVATOR_SUPPORT_FIELD.firePropertyChange("$fieldName", oldValue, $fieldName);
                }
            """.trimIndent()
            val setter = elementFactory.createMethodFromText(setterText, psiClass)
            generatedElements += psiClass.add(setter)
        }

        val addListenerName = "add${capitalized}Listener"
        if (!hasMethod(psiClass, addListenerName)) {
            val addListenerText = """
                public void $addListenerName(java.beans.PropertyChangeListener listener) {
                    $OBSERVATOR_SUPPORT_FIELD.addPropertyChangeListener("$fieldName", listener);
                }
            """.trimIndent()
            generatedElements += psiClass.add(elementFactory.createMethodFromText(addListenerText, psiClass))
        }

        val removeListenerName = "remove${capitalized}Listener"
        if (!hasMethod(psiClass, removeListenerName)) {
            val removeListenerText = """
                public void $removeListenerName(java.beans.PropertyChangeListener listener) {
                    $OBSERVATOR_SUPPORT_FIELD.removePropertyChangeListener("$fieldName", listener);
                }
            """.trimIndent()
            generatedElements += psiClass.add(elementFactory.createMethodFromText(removeListenerText, psiClass))
        }

        return generatedElements
    }

    private fun hasSetter(psiClass: PsiClass, setterName: String): Boolean {
        return psiClass.findMethodsByName(setterName, false).any { method ->
            method.parameterList.parametersCount == 1
        }
    }

    private fun hasMethod(psiClass: PsiClass, methodName: String): Boolean {
        return psiClass.findMethodsByName(methodName, false).isNotEmpty()
    }

    private fun removeAnnotation(annotation: PsiAnnotation) {
        annotation.delete()
    }

    companion object {
        private const val OBSERVATOR_SUPPORT_FIELD = "observatorSupport"
    }
}
