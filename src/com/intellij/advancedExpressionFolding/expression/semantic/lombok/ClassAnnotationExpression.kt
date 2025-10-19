package com.intellij.advancedExpressionFolding.expression.semantic.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiKeyword
import com.intellij.psi.PsiModifierList
import com.intellij.psi.PsiTypeElement

@JvmInline
value class CustomClassAnnotation(val value: String) {
    init {
        require(value.startsWith("@")) { "CustomClassAnnotation must start with '@'" }
    }
}

open class ClassAnnotationExpression(
    element: PsiElement,
    internal val customClassAnnotations: List<CustomClassAnnotation>,
    internal val elementsToFold: List<PsiElement?>,
    private val additionalExpression: Expression? = null,
    private val group: FoldingGroup? = null,

    ) : Expression(element, element.textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = group ?: run {
            FoldingGroup.newGroup(this::class.java.name)
        }
        val foldingDescriptors = (elementsToFold
            .asSequence()
            .filterNotNull()
            .filter {
                !it.textRange.isEmpty
            }.filterNot {
                it.node == null
            }.map {
                fold(it, it.textRange, "", group)
            } + addAnnotation(element, document, group))
            .let { sequence ->
                additionalExpression?.let {
                    sequence.plus(it.buildFoldRegions(element, document, parent))
                } ?: sequence
            }
        return foldingDescriptors.toList().toTypedArray()
    }


    private fun addAnnotation(clazzOrField: PsiElement, document: Document, group: FoldingGroup): FoldingDescriptor {
        val foldOn = findAnnotationFoldOnElement(clazzOrField)
        val range = foldOn.textRange
        val newRange = TextRange.create(range.startOffset, range.startOffset + 1)
        val firstChar = newRange.substring(document.text)
        return fold(
            foldOn,
            newRange,
            "${customClassAnnotations.joinToString(" ") { it.value }} $firstChar",
            group
        )
    }

    private fun findAnnotationFoldOnElement(clazzOrField: PsiElement): PsiElement {
        var foldOn = clazzOrField
        for (child in clazzOrField.children) {
            if (child is PsiKeyword) {
                foldOn = child
                break
            } else {
                val keyword = child.asInstance<PsiModifierList>()?.children?.firstOrNull {
                    it is PsiKeyword
                }
                if (keyword != null) {
                    foldOn = keyword
                    break
                }
                val type = child.asInstance<PsiTypeElement>()
                if (type != null) {
                    foldOn = type
                    break
                }
            }
        }
        return foldOn
    }

    private fun fold(element: PsiElement, textRange: TextRange, placeholderText: String, group: FoldingGroup): FoldingDescriptor {
        return FoldingDescriptor(element.node, textRange, group, placeholderText, true, emptySet<Any>())
    }

    override fun isNested() = true
}
