package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.CustomClassAnnotation
import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiKeyword
import com.intellij.psi.PsiModifierList

class ClassAnnotationExpression(
        element: PsiClass,
        private val customClassAnnotations: List<CustomClassAnnotation>,
        private val elementsToFold: List<PsiElement>
) : Expression(element, element.textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ClassAnnotationExpression::class.java.name)
        return (elementsToFold
            .filter {
                !it.textRange.isEmpty
            }.filterNot {
                it.node == null
            }.map {
                fold(it, it.textRange, "", group)
            } + mapClass(element, document, group))
            .toTypedArray()
    }


    private fun mapClass(clazz: PsiElement, document: Document, group: FoldingGroup): FoldingDescriptor {
        var foldOn = clazz
        for (child in clazz.children) {
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
            }
        }

        val range = foldOn.textRange
        val newRange = TextRange.create(range.startOffset, range.startOffset + 1)
        val firstChar = newRange.substring(document.text)
        return fold(foldOn, newRange, "${customClassAnnotations.joinToString(" ")} $firstChar", group)
    }

    private fun fold(element: PsiElement, textRange: TextRange, placeholderText: String, group: FoldingGroup): FoldingDescriptor {
        return FoldingDescriptor(element.node, textRange, group, placeholderText, true, emptySet<Any>())
    }

    override fun isNested(): Boolean = true
}
