package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections
import java.util.HashSet
import java.util.List
import java.util.Set

class ShortElvisExpression(
    element: PsiElement,
    textRange: TextRange,
    thenExpression: Expression,
    elements: List<TextRange>
) : Expression(element, textRange) {
    private var thenExpression: Expression
    private var elements: List<TextRange>

    init {
        this.thenExpression = thenExpression
        this.elements = elements
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ShortElvisExpression::class.java.getName())
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(textRange.getStartOffset(), thenExpression.getTextRange().getStartOffset()),
                group,
                ""
            )
        )
        if (thenExpression.getTextRange().getEndOffset() < textRange.getEndOffset()) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        thenExpression.getTextRange().getEndOffset(),
                        getTextRange().getEndOffset()
                    ),
                    group,
                    ""
                )
            )
        }
        nullify(element, document, descriptors, group, elements, true)
        if (thenExpression.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                thenExpression.buildFoldRegions(thenExpression.getElement(), document, this)
            )
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    companion object {
        protected val SUPPORTED_POSTFIXES: Set<String> = object : HashSet<String>() {
            init {
                add(".")
                add(";")
                add(",")
                add(")")
            }
        }

        @JvmStatic
        protected fun nullify(
            element: PsiElement,
            document: Document,
            descriptors: ArrayList<FoldingDescriptor>,
            group: FoldingGroup,
            elements: List<TextRange>,
            replaceSingle: Boolean
        ) {
            for (range in elements) {
                val postfix = document.getText(TextRange.create(range.getEndOffset(), range.getEndOffset() + 1))
                if (SUPPORTED_POSTFIXES.contains(postfix)) {
                    descriptors.add(
                        FoldingDescriptor(
                            element.getNode(),
                            TextRange.create(range.getEndOffset(), range.getEndOffset() + 1),
                            group,
                            "?" + postfix
                        )
                    )
                } else if (replaceSingle) {
                    val r = TextRange.create(range.getStartOffset(), range.getEndOffset())
                    descriptors.add(
                        FoldingDescriptor(
                            element.getNode(),
                            r,
                            group,
                            document.getText(r) + "?"
                        )
                    )
                }
            }
        }
    }
}

