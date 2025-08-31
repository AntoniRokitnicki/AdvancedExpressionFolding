package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.language.java.PatternMatchingExt.foldInstanceOf
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import java.util.ArrayList
import java.util.HashSet

class IfExpression(
    private val element: PsiIfStatement,
    textRange: TextRange
) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val state = AdvancedExpressionFoldingSettings.getInstance().state
        return isAssertExpression(state, element) || isCompactExpression(state, element)
    }

    override fun isNested() = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val state = AdvancedExpressionFoldingSettings.getInstance().state
        val group = FoldingGroup.newGroup(
            IfExpression::class.java.name +
                if (!isAssertExpression(state, this.element) && isCompactExpression(state, this.element))
                    HIGHLIGHTED_GROUP_POSTFIX else ""
        )
        val descriptors = ArrayList<FoldingDescriptor>()
        if (this.element.lParenth != null && this.element.rParenth != null) {
            if (isAssertExpression(state, this.element)) {
                foldAssertion(element, document, descriptors, group, state)
            } else if (isCompactExpression(state, this.element)) {
                foldCompactExpr(element, document, group, descriptors)
            } else if (state.patternMatchingInstanceof && element is PsiIfStatement) {
                val instanceOfExpr = findInstanceOf(element)
                if (instanceOfExpr != null) {
                    element.foldInstanceOf(instanceOfExpr, document, descriptors)
                }
            }
        }
        return descriptors.toTypedArray()
    }

    private fun foldCompactExpr(
        element: PsiElement,
        document: Document,
        group: FoldingGroup,
        descriptors: ArrayList<FoldingDescriptor>
    ) {
        val textRange = TextRange.create(
            this.element.lParenth!!.textRange.startOffset,
            this.element.rParenth!!.textRange.endOffset
        )
        if (CompactControlFlowExpression.supportsFoldRegions(document, textRange)) {
            CompactControlFlowExpression.buildFoldRegions(element, group, descriptors, textRange)
        }
    }

    private fun foldAssertion(
        element: PsiElement,
        document: Document,
        descriptors: ArrayList<FoldingDescriptor>,
        group: FoldingGroup,
        state: AdvancedExpressionFoldingSettings.State
    ) {
        val throwStatement: PsiThrowStatement? =
            if (this.element.thenBranch is PsiBlockStatement &&
                (this.element.thenBranch as PsiBlockStatement).codeBlock.statements.size == 1
            ) {
                ((this.element.thenBranch as PsiBlockStatement).codeBlock.statements[0] as PsiThrowStatement)
            } else {
                this.element.thenBranch as? PsiThrowStatement
            }
        if (this.element.condition != null && throwStatement != null) {
            val trailingSpace = document.getText(
                TextRange.create(
                    this.element.lParenth!!.textRange.startOffset - 1,
                    this.element.lParenth!!.textRange.startOffset
                )
            ) == " "
            if (trailingSpace) {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(
                            this.element.textRange.startOffset,
                            this.element.lParenth!!.textRange.startOffset - 1
                        ),
                        group,
                        "assert"
                    )
                )
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(
                            this.element.lParenth!!.textRange.startOffset,
                            this.element.condition!!.textRange.startOffset
                        ),
                        group,
                        ""
                    )
                )
            } else {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(
                            this.element.textRange.startOffset,
                            this.element.condition!!.textRange.startOffset
                        ),
                        group,
                        "assert "
                    )
                )
            }
            val binaryExpression = this.element.condition as PsiBinaryExpression
            val p = when (binaryExpression.operationSign.text) {
                "==" -> "!="
                "!=" -> "=="
                ">" -> "<="
                "<" -> ">="
                ">=" -> "<"
                "<=" -> ">"
                else -> throw IllegalStateException(
                    "Unsupported operator: " + binaryExpression.operationSign.text
                )
            }
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    binaryExpression.operationSign.textRange,
                    group,
                    p
                )
            )
            val newException = throwStatement.exception as? PsiNewExpression
            if (
                newException != null &&
                newException.argumentList != null &&
                newException.argumentList!!.expressions.isNotEmpty() &&
                newException.argumentList!!.expressions[0] is PsiLiteralExpression &&
                newException.argumentList!!.expressions[0].type != null &&
                newException.argumentList!!.expressions[0].type!!.canonicalText == "java.lang.String"
            ) {
                val spacesAroundColon = document.getText(
                    TextRange.create(
                        throwStatement.textRange.startOffset - 3,
                        throwStatement.textRange.startOffset
                    )
                ) == "   "
                if (spacesAroundColon) {
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(
                                this.element.rParenth!!.textRange.endOffset - 1,
                                throwStatement.textRange.startOffset - 3
                            ),
                            group,
                            ""
                        )
                    )
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(
                                throwStatement.textRange.startOffset - 2,
                                throwStatement.textRange.startOffset - 1
                            ),
                            group,
                            ":"
                        )
                    )
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(
                                throwStatement.textRange.startOffset,
                                newException.argumentList!!.expressions[0].textRange.startOffset
                            ),
                            group,
                            ""
                        )
                    )
                } else {
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(
                                this.element.condition!!.textRange.endOffset,
                                newException.argumentList!!.expressions[0].textRange.startOffset
                            ),
                            group,
                            " : "
                        )
                    )
                }
                if (
                    !state.semicolonsCollapse && throwStatement.text.endsWith(";")
                ) {
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(
                                newException.argumentList!!.expressions[0].textRange.endOffset,
                                throwStatement.textRange.endOffset - 1
                            ),
                            group,
                            ""
                        )
                    )
                    if (this.element.textRange.endOffset > throwStatement.textRange.endOffset) {
                        descriptors.add(
                            FoldingDescriptor(
                                element.node,
                                TextRange.create(
                                    throwStatement.textRange.endOffset,
                                    this.element.textRange.endOffset
                                ),
                                group,
                                ""
                            )
                        )
                    }
                } else {
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(
                                newException.argumentList!!.expressions[0].textRange.endOffset,
                                this.element.textRange.endOffset
                            ),
                            group,
                            if (state.semicolonsCollapse) "" else ";"
                        )
                    )
                }
            } else {
                if (!state.semicolonsCollapse && throwStatement.text.endsWith(";")) {
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(
                                this.element.condition!!.textRange.endOffset,
                                throwStatement.textRange.endOffset - 1
                            ),
                            group,
                            ""
                        )
                    )
                    if (this.element.textRange.endOffset > throwStatement.textRange.endOffset) {
                        descriptors.add(
                            FoldingDescriptor(
                                element.node,
                                TextRange.create(
                                    throwStatement.textRange.endOffset,
                                    this.element.textRange.endOffset
                                ),
                                group,
                                ""
                            )
                        )
                    }
                } else {
                    descriptors.add(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(
                                this.element.condition!!.textRange.endOffset,
                                this.element.textRange.endOffset
                            ),
                            group,
                            if (state.semicolonsCollapse) "" else ";"
                        )
                    )
                }
            }
        }
    }

    override fun isHighlighted(): Boolean {
        val state = AdvancedExpressionFoldingSettings.getInstance().state
        return !isAssertExpression(state, this.element) && isCompactExpression(state, this.element)
    }

    override fun getHighlightedTextRange(): TextRange {
        return if (this.element.lParenth != null && this.element.rParenth != null) {
            TextRange.create(
                this.element.lParenth!!.textRange.startOffset,
                this.element.rParenth!!.textRange.endOffset
            )
        } else {
            super.getHighlightedTextRange()
        }
    }

    companion object {
        private val supportedOperatorSigns = HashSet<String>().apply {
            add("==")
            add("!=")
            add(">")
            add("<")
            add(">=")
            add("<=")
        }

        fun isCompactExpression(
            state: AdvancedExpressionFoldingSettings.State,
            element: PsiIfStatement
        ) = state.compactControlFlowSyntaxCollapse &&
                element.rParenth != null &&
                element.lParenth != null &&
                element.condition != null

        fun isAssertExpression(
            state: AdvancedExpressionFoldingSettings.State,
            element: PsiIfStatement
        ): Boolean {
            return state.assertsCollapse &&
                    element.condition is PsiBinaryExpression &&
                    supportedOperatorSigns.contains(
                        (element.condition as PsiBinaryExpression).operationSign.text
                    ) &&
                    element.elseBranch == null &&
                    (
                            element.thenBranch is PsiBlockStatement &&
                                    (element.thenBranch as PsiBlockStatement).codeBlock.statements.size == 1 &&
                                    (element.thenBranch as PsiBlockStatement).codeBlock.statements[0] is PsiThrowStatement ||
                                    element.thenBranch is PsiThrowStatement
                            )
        }

        fun findInstanceOf(element: PsiElement): PsiInstanceOfExpression? {
            if (element is PsiIfStatement) {
                val condition = element.condition
                if (condition is PsiInstanceOfExpression) {
                    return condition
                }
            }
            return null
        }
    }
}

