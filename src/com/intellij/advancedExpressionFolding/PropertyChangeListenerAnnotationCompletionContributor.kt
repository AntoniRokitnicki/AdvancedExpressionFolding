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
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.util.text.StringUtil
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiField
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiModifier
import com.intellij.psi.PsiModifierList
import com.intellij.psi.PsiJavaCodeReferenceElement
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.PsiModifierListOwner
import com.intellij.psi.PsiDocumentManager
import com.intellij.util.ProcessingContext

private const val PROPERTY_CHANGE_SUPPORT_FIELD = "propertyChangeSupport"

class PropertyChangeListenerAnnotationCompletionContributor(
    private val state: IState = getInstance().state
) : CompletionContributor(), IState by state {

    init {
        val provider = object : CompletionProvider<CompletionParameters>() {
            override fun addCompletions(
                parameters: CompletionParameters,
                context: ProcessingContext,
                result: CompletionResultSet
            ) {
                if (!pseudoAnnotations) return

                result.addElement(createLookupElement())
            }
        }

        val basePattern = PlatformPatterns.psiElement(PsiIdentifier::class.java)
            .withParent(PsiJavaCodeReferenceElement::class.java)
            .withSuperParent(2, PsiAnnotation::class.java)
            .withSuperParent(3, PsiModifierList::class.java)

        extend(CompletionType.BASIC, basePattern.withSuperParent(4, PsiClass::class.java), provider)
        extend(CompletionType.BASIC, basePattern.withSuperParent(4, PsiField::class.java), provider)
    }

    private fun createLookupElement(): LookupElement {
        return LookupElementBuilder.create("PropertyChangeListener")
            .withLookupString("@PropertyChangeListener")
            .withPresentableText("@PropertyChangeListener")
            .withInsertHandler { ctx, _ ->
                handleInsert(ctx)
            }
            .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)
    }

    private fun handleInsert(ctx: InsertionContext) {
        val element = ctx.file.findElementAt(ctx.startOffset) ?: return
        val annotation = PsiTreeUtil.getParentOfType(element, PsiAnnotation::class.java, false) ?: return
        val owner = PsiTreeUtil.getParentOfType(annotation, PsiModifierListOwner::class.java, false) ?: return

        when (owner) {
            is PsiClass -> handleClassInsert(owner, annotation, ctx)
            is PsiField -> handleFieldInsert(owner, annotation, ctx)
        }
    }

    private fun handleClassInsert(psiClass: PsiClass, annotation: PsiAnnotation, ctx: InsertionContext) {
        val project = psiClass.project
        val documentManager = PsiDocumentManager.getInstance(project)

        WriteCommandAction.runWriteCommandAction(project) {
            annotation.delete()

            val insertedElements = ensurePropertyChangeInfrastructure(psiClass)

            documentManager.doPostponedOperationsAndUnblockDocument(ctx.document)
            documentManager.commitDocument(ctx.document)

            val javaStyle = JavaCodeStyleManager.getInstance(project)
            val codeStyle = CodeStyleManager.getInstance(project)

            insertedElements.forEach { element ->
                javaStyle.shortenClassReferences(element)
                codeStyle.reformat(element)
            }

            insertedElements.lastOrNull()?.let {
                ctx.editor.caretModel.moveToOffset(it.textRange.startOffset)
            }
        }
    }

    private fun handleFieldInsert(field: PsiField, annotation: PsiAnnotation, ctx: InsertionContext) {
        val psiClass = field.containingClass ?: return
        val project = psiClass.project
        val documentManager = PsiDocumentManager.getInstance(project)

        WriteCommandAction.runWriteCommandAction(project) {
            annotation.delete()

            val insertedElements = ensurePropertyChangeInfrastructure(psiClass).toMutableList()
            val setter = upsertSetter(field, psiClass)
            setter?.let(insertedElements::add)

            documentManager.doPostponedOperationsAndUnblockDocument(ctx.document)
            documentManager.commitDocument(ctx.document)

            val javaStyle = JavaCodeStyleManager.getInstance(project)
            val codeStyle = CodeStyleManager.getInstance(project)

            insertedElements.forEach { element ->
                javaStyle.shortenClassReferences(element)
                codeStyle.reformat(element)
            }

            moveCaretToPropertyLiteral(setter, ctx)
        }
    }

    private fun ensurePropertyChangeInfrastructure(psiClass: PsiClass): List<PsiElement> {
        val project = psiClass.project
        val factory = JavaPsiFacade.getElementFactory(project)
        val inserted = mutableListOf<PsiElement>()

        if (psiClass.fields.none { it.name == PROPERTY_CHANGE_SUPPORT_FIELD }) {
            val fieldText = "private final java.beans.PropertyChangeSupport $PROPERTY_CHANGE_SUPPORT_FIELD = new java.beans.PropertyChangeSupport(this);"
            val field = factory.createFieldFromText(fieldText, psiClass)
            inserted.add(psiClass.add(field))
        }

        fun ensureMethod(name: String, parameterCount: Int, text: String) {
            val exists = psiClass.methods.any { it.name == name && it.parameterList.parametersCount == parameterCount }
            if (!exists) {
                val method = factory.createMethodFromText(text, psiClass)
                inserted.add(psiClass.add(method))
            }
        }

        ensureMethod(
            name = "addPropertyChangeListener",
            parameterCount = 1,
            text = "public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) { $PROPERTY_CHANGE_SUPPORT_FIELD.addPropertyChangeListener(listener); }"
        )
        ensureMethod(
            name = "removePropertyChangeListener",
            parameterCount = 1,
            text = "public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) { $PROPERTY_CHANGE_SUPPORT_FIELD.removePropertyChangeListener(listener); }"
        )
        ensureMethod(
            name = "firePropertyChange",
            parameterCount = 3,
            text = "protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) { $PROPERTY_CHANGE_SUPPORT_FIELD.firePropertyChange(propertyName, oldValue, newValue); }"
        )

        return inserted
    }

    private fun upsertSetter(field: PsiField, psiClass: PsiClass): PsiMethod? {
        if (field.hasModifierProperty(PsiModifier.STATIC)) return null
        if (field.hasModifierProperty(PsiModifier.FINAL)) return null

        val project = psiClass.project
        val factory = JavaPsiFacade.getElementFactory(project)

        val fieldName = field.name ?: return null
        val capitalized = StringUtil.capitalize(fieldName)
        val setterName = "set$capitalized"
        val typeText = field.type.presentableText
        val parameterName = fieldName
        val oldValueName = "old$capitalized"

        val methodText = """
            public void $setterName($typeText $parameterName) {
                $typeText $oldValueName = this.$fieldName;
                this.$fieldName = $parameterName;
                firePropertyChange("$fieldName", $oldValueName, $parameterName);
            }
        """.trimIndent()

        val newMethod = factory.createMethodFromText(methodText, psiClass)

        val existing = psiClass.findMethodsByName(setterName, false)
            .firstOrNull { it.parameterList.parametersCount == 1 }

        return if (existing != null) {
            existing.replace(newMethod) as? PsiMethod
        } else {
            psiClass.add(newMethod) as? PsiMethod
        }
    }

    private fun moveCaretToPropertyLiteral(method: PsiMethod?, ctx: InsertionContext) {
        val literal = PsiTreeUtil.findChildOfType(method ?: return, PsiLiteralExpression::class.java) ?: return
        val offset = literal.textRange.startOffset + 1
        if (offset <= ctx.document.textLength) {
            ctx.editor.caretModel.moveToOffset(offset)
        }
    }
}
