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

        fun buildNullifiedText(
            document: Document,
            range: TextRange,
            elements: List<TextRange>,
            replaceSingle: Boolean
        ): String {
            if (elements.isEmpty()) {
                return document.getText(range)
            }
            val builder = StringBuilder(document.getText(range))
            elements
                .sortedByDescending { it.endOffset }
                .forEach { elementRange ->
                    val relativeStart = elementRange.startOffset - range.startOffset
                    val relativeEnd = elementRange.endOffset - range.startOffset
                    if (relativeStart < 0 || relativeEnd < 0 || relativeStart > builder.length) {
                        return@forEach
                    }
                    val postfixOffset = elementRange.endOffset
                    val postfixWithinRange = postfixOffset < range.endOffset && postfixOffset + 1 <= document.textLength
                    if (postfixWithinRange) {
                        val postfix = document.getText(TextRange(postfixOffset, postfixOffset + 1))
                        if (SUPPORTED_POSTFIXES.contains(postfix)) {
                            val localIndex = relativeEnd
                            if (localIndex in 0 until builder.length) {
                                builder.replace(localIndex, localIndex + 1, "?$postfix")
                                return@forEach
                            }
                        }
                    }
                    if (replaceSingle && relativeEnd in 0..builder.length) {
                        builder.insert(relativeEnd, "?")
                    }
                }
            return builder.toString()
        }
    }
}
