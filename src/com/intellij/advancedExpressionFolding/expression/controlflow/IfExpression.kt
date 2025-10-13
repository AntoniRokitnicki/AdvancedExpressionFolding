package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.language.java.PatternMatchingExt
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiBlockStatement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiInstanceOfExpression
import com.intellij.psi.PsiNewExpression
import com.intellij.psi.PsiThrowStatement
import java.util.ArrayList

class IfExpression(
    private val ifStatement: PsiIfStatement,
    textRange: TextRange
) : Expression(ifStatement, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val state = AdvancedExpressionFoldingSettings.getInstance().state
        return isAssertExpression(state, ifStatement) || isCompactExpression(state, ifStatement)
    }

    override fun isNested(): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val state = AdvancedExpressionFoldingSettings.getInstance().state
        val highlightPostfix = if (!isAssertExpression(state, this.ifStatement) && isCompactExpression(state, this.ifStatement)) {
            HIGHLIGHTED_GROUP_POSTFIX
        } else {
            ""
        }
        val group = FoldingGroup.newGroup(IfExpression::class.java.name + highlightPostfix)
        val descriptors = ArrayList<FoldingDescriptor>()
        val lParenth = this.ifStatement.lParenth
        val rParenth = this.ifStatement.rParenth
        if (lParenth != null && rParenth != null) {
            when {
                isAssertExpression(state, this.ifStatement) ->
                    foldAssertion(element, document, descriptors, group, state)

                isCompactExpression(state, this.ifStatement) ->
                    foldCompactExpr(element, document, group, descriptors)

                state.patternMatchingInstanceof && element is PsiIfStatement -> {
                    val instanceOfExpr = findInstanceOf(element)
                    if (instanceOfExpr != null) {
                        PatternMatchingExt.foldInstanceOf(element, instanceOfExpr, document, descriptors)
                    }
                }
            }
        }
        return descriptors.toTypedArray()
    }

    override fun isHighlighted(): Boolean {
        val state = AdvancedExpressionFoldingSettings.getInstance().state
        return !isAssertExpression(state, ifStatement) && isCompactExpression(state, ifStatement)
    }

    override fun getHighlightedTextRange(): TextRange {
        val lParenth = ifStatement.lParenth
        val rParenth = ifStatement.rParenth
        return if (lParenth != null && rParenth != null) {
            TextRange.create(lParenth.textRange.startOffset, rParenth.textRange.endOffset)
        } else {
            super.getHighlightedTextRange()
        }
    }

    private fun foldCompactExpr(
        psiElement: PsiElement,
        document: Document,
        group: FoldingGroup,
        descriptors: MutableList<FoldingDescriptor>
    ) {
        val lParenth = ifStatement.lParenth ?: return
        val rParenth = ifStatement.rParenth ?: return
        val range = TextRange.create(lParenth.textRange.startOffset, rParenth.textRange.endOffset)
        if (CompactControlFlowExpression.supportsFoldRegions(document, range)) {
            CompactControlFlowExpression.buildFoldRegions(psiElement, group, descriptors, range)
        }
    }

    private fun foldAssertion(
        psiElement: PsiElement,
        document: Document,
        descriptors: MutableList<FoldingDescriptor>,
        group: FoldingGroup,
        state: AdvancedExpressionFoldingSettings.State
    ) {
        val throwStatement = when (val thenBranch = ifStatement.thenBranch) {
            is PsiBlockStatement -> {
                val statements = thenBranch.codeBlock.statements
                if (statements.size == 1 && statements[0] is PsiThrowStatement) {
                    statements[0] as PsiThrowStatement
                } else {
                    null
                }
            }
            is PsiThrowStatement -> thenBranch
            else -> null
        } ?: return

        val condition = ifStatement.condition ?: return
        val lParenth = ifStatement.lParenth ?: return
        val rParenth = ifStatement.rParenth ?: return
        val trailingSpace = document.getText(
            TextRange.create(lParenth.textRange.startOffset - 1, lParenth.textRange.startOffset)
        ) == " "
        if (trailingSpace) {
            descriptors += FoldingDescriptor(
                psiElement.node,
                TextRange.create(ifStatement.textRange.startOffset, lParenth.textRange.startOffset - 1),
                group,
                "assert"
            )
            descriptors += FoldingDescriptor(
                psiElement.node,
                TextRange.create(lParenth.textRange.startOffset, condition.textRange.startOffset),
                group,
                ""
            )
        } else {
            descriptors += FoldingDescriptor(
                psiElement.node,
                TextRange.create(ifStatement.textRange.startOffset, condition.textRange.startOffset),
                group,
                "assert "
            )
        }

        val binaryExpression = condition as PsiBinaryExpression
        val replacement = when (binaryExpression.operationSign.text) {
            "==" -> "!="
            "!=" -> "=="
            ">" -> "<="
            "<" -> ">="
            ">=" -> "<"
            "<=" -> ">"
            else -> throw IllegalStateException("Unsupported operator: ${binaryExpression.operationSign.text}")
        }
        descriptors += FoldingDescriptor(
            psiElement.node,
            binaryExpression.operationSign.textRange,
            group,
            replacement
        )

        val newException = throwStatement.exception as? PsiNewExpression
        val argumentList = newException?.argumentList
        val messageExpression = argumentList?.expressions?.firstOrNull()
        if (messageExpression != null && messageExpression.type?.canonicalText == "java.lang.String"
        ) {
            val spacesAroundColon = document.getText(
                TextRange.create(throwStatement.textRange.startOffset - 3, throwStatement.textRange.startOffset)
            ) == "   "
            if (spacesAroundColon) {
                descriptors += FoldingDescriptor(
                    psiElement.node,
                    TextRange.create(rParenth.textRange.endOffset - 1, throwStatement.textRange.startOffset - 3),
                    group,
                    ""
                )
                descriptors += FoldingDescriptor(
                    psiElement.node,
                    TextRange.create(throwStatement.textRange.startOffset - 2, throwStatement.textRange.startOffset - 1),
                    group,
                    ":"
                )
                descriptors += FoldingDescriptor(
                    psiElement.node,
                    TextRange.create(throwStatement.textRange.startOffset, messageExpression.textRange.startOffset),
                    group,
                    ""
                )
            } else {
                descriptors += FoldingDescriptor(
                    psiElement.node,
                    TextRange.create(condition.textRange.endOffset, messageExpression.textRange.startOffset),
                    group,
                    " : "
                )
            }
            if (!state.semicolonsCollapse && throwStatement.text.endsWith(";")) {
                descriptors += FoldingDescriptor(
                    psiElement.node,
                    TextRange.create(messageExpression.textRange.endOffset, throwStatement.textRange.endOffset - 1),
                    group,
                    ""
                )
                if (element.textRange.endOffset > throwStatement.textRange.endOffset) {
                    descriptors += FoldingDescriptor(
                        psiElement.node,
                        TextRange.create(throwStatement.textRange.endOffset, element.textRange.endOffset),
                        group,
                        ""
                    )
                }
            } else {
                descriptors += FoldingDescriptor(
                    psiElement.node,
                    TextRange.create(messageExpression.textRange.endOffset, element.textRange.endOffset),
                    group,
                    if (state.semicolonsCollapse) "" else ";"
                )
            }
        } else {
            if (!state.semicolonsCollapse && throwStatement.text.endsWith(";")) {
                descriptors += FoldingDescriptor(
                    psiElement.node,
                    TextRange.create(condition.textRange.endOffset, throwStatement.textRange.endOffset - 1),
                    group,
                    ""
                )
                if (element.textRange.endOffset > throwStatement.textRange.endOffset) {
                    descriptors += FoldingDescriptor(
                        psiElement.node,
                        TextRange.create(throwStatement.textRange.endOffset, element.textRange.endOffset),
                        group,
                        ""
                    )
                }
            } else {
                descriptors += FoldingDescriptor(
                    psiElement.node,
                    TextRange.create(condition.textRange.endOffset, element.textRange.endOffset),
                    group,
                    if (state.semicolonsCollapse) "" else ";"
                )
            }
        }
    }

    companion object {
        private val supportedOperatorSigns = setOf("==", "!=", ">", "<", ">=", "<=")

        fun isCompactExpression(state: AdvancedExpressionFoldingSettings.State, element: PsiIfStatement): Boolean {
            return state.compactControlFlowSyntaxCollapse &&
                element.rParenth != null &&
                element.lParenth != null &&
                element.condition != null
        }

        fun isAssertExpression(state: AdvancedExpressionFoldingSettings.State, element: PsiIfStatement): Boolean {
            val condition = element.condition
            if (!state.assertsCollapse || condition !is PsiBinaryExpression) {
                return false
            }
            if (!supportedOperatorSigns.contains(condition.operationSign.text)) {
                return false
            }
            if (element.elseBranch != null) {
                return false
            }
            return when (val thenBranch = element.thenBranch) {
                is PsiBlockStatement -> {
                    val statements = thenBranch.codeBlock.statements
                    statements.size == 1 && statements[0] is PsiThrowStatement
                }
                is PsiThrowStatement -> true
                else -> false
            }
        }

        fun findInstanceOf(element: PsiElement): PsiInstanceOfExpression? {
            if (element is PsiIfStatement) {
                val condition: PsiExpression? = element.condition
                if (condition is PsiInstanceOfExpression) {
                    return condition
                }
            }
            return null
        }
    }
}
