package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class ShortElvisExpression(
    element: PsiElement,
    textRange: TextRange,
    private val thenExpression: Expression,
    private val elements: List<TextRange>
) : Expression(element, textRange) {

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ShortElvisExpression::class.java.name)
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(textRange.startOffset, thenExpression.textRange.startOffset),
            group,
            ""
        )
        if (thenExpression.textRange.endOffset < textRange.endOffset) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(thenExpression.textRange.endOffset, textRange.endOffset),
                group,
                ""
            )
        }
        nullify(element, document, descriptors, group, elements, true)
        if (thenExpression.supportsFoldRegions(document, this)) {
            descriptors += thenExpression.buildFoldRegions(thenExpression.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    companion object {
        private val SUPPORTED_POSTFIXES: Set<String> = setOf(".", ";", ",", ")")

        @JvmStatic
        fun nullify(
            element: PsiElement,
            document: Document,
            descriptors: MutableList<FoldingDescriptor>,
            group: FoldingGroup,
            elements: List<TextRange>,
            replaceSingle: Boolean
        ) {
            for (range in elements) {
                val postfix = document.getText(TextRange.create(range.endOffset, range.endOffset + 1))
                if (SUPPORTED_POSTFIXES.contains(postfix)) {
                    descriptors += FoldingDescriptor(
                        element.node,
                        TextRange.create(range.endOffset, range.endOffset + 1),
                        group,
                        "?" + postfix
                    )
                } else if (replaceSingle) {
                    val r = TextRange.create(range.startOffset, range.endOffset)
                    descriptors += FoldingDescriptor(
                        element.node,
                        r,
                        group,
                        document.getText(r) + "?"
                    )
                }
            }
        }
    }
}
