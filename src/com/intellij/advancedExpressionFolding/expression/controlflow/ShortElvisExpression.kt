package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class ShortElvisExpression : Expression {
    private var thenExpression: Expression? = null
    private var elements: List<TextRange>? = null

    constructor(
        element: PsiElement,
        textRange: TextRange,
        thenExpression: Expression,
        elements: List<TextRange>
    ) : super(element, textRange) {
        this.thenExpression = thenExpression
        this.elements = elements
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors: MutableList<FoldingDescriptor> = java.util.ArrayList()
        val group = FoldingGroup.newGroup(ShortElvisExpression::class.java.getName())
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(getTextRange().getStartOffset(), thenExpression!!.getTextRange().getStartOffset()),
                group,
                ""
            )
        )
        if (thenExpression!!.getTextRange().getEndOffset() < getTextRange().getEndOffset()) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(thenExpression!!.getTextRange().getEndOffset(), getTextRange().getEndOffset()),
                    group,
                    ""
                )
            )
        }
        nullify(element, document, descriptors, group, elements, true)
        if (thenExpression!!.supportsFoldRegions(document, this)) {
            java.util.Collections.addAll(
                descriptors,
                *thenExpression!!.buildFoldRegions(thenExpression!!.getElement(), document, this)
            )
        }
        return descriptors.toTypedArray()
    }

    companion object {
        protected val SUPPORTED_POSTFIXES: Set<String> = object : java.util.HashSet<String>() {
            init {
                add(".")
                add(";")
                add(",")
                add(")")
            }
        }

        protected fun nullify(
            element: PsiElement,
            document: Document,
            descriptors: java.util.ArrayList<FoldingDescriptor>,
            group: FoldingGroup,
            elements: List<TextRange>?,
            replaceSingle: Boolean
        ) {
            if (elements != null) {
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

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }
}

