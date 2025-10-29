package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.language.java.PatternMatchingExt
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IControlFlowState
import com.intellij.advancedExpressionFolding.settings.IKotlinLanguageState
import com.intellij.advancedExpressionFolding.settings.IUnclassifiedFeatureState
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
import com.intellij.psi.PsiThrowStatement
import java.util.ArrayList

class IfExpression(
    private val ifStatement: PsiIfStatement,
    textRange: TextRange,
) : Expression(ifStatement, textRange),
    IControlFlowState by AdvancedExpressionFoldingSettings.State()(),
    IKotlinLanguageState by AdvancedExpressionFoldingSettings.State()(),
    IUnclassifiedFeatureState by AdvancedExpressionFoldingSettings.State()() {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return isAssertExpression(ifStatement) || isCompactExpression(ifStatement)
    }

    override fun isNested(): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val highlightPostfix = if (!isAssertExpression(this.ifStatement) && isCompactExpression(this.ifStatement)) {
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
                isAssertExpression(this.ifStatement) ->
                    foldAssertion(element, document, descriptors, group)

                isCompactExpression(this.ifStatement) ->
                    foldCompactExpr(element, document, group, descriptors)

                patternMatchingInstanceof && element is PsiIfStatement -> {
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
        return !isAssertExpression(ifStatement) && isCompactExpression(ifStatement)
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
        group: FoldingGroup
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
        fun MutableList<FoldingDescriptor>.addIfNotEmpty(
            startOffset: Int,
            endOffset: Int,
            placeholder: String,
        ) {
            if (startOffset < endOffset) {
                this += FoldingDescriptor(
                    psiElement.node,
                    TextRange.create(startOffset, endOffset),
                    group,
                    placeholder,
                )
            }
        }

        if (trailingSpace) {
            descriptors.addIfNotEmpty(
                ifStatement.textRange.startOffset,
                lParenth.textRange.startOffset - 1,
                "assert",
            )
            descriptors.addIfNotEmpty(
                lParenth.textRange.startOffset,
                condition.textRange.startOffset,
                "",
            )
        } else {
            descriptors.addIfNotEmpty(
                ifStatement.textRange.startOffset,
                condition.textRange.startOffset,
                "assert ",
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
        descriptors.addIfNotEmpty(
            binaryExpression.operationSign.textRange.startOffset,
            binaryExpression.operationSign.textRange.endOffset,
            replacement,
        )

        val exceptionExpression = throwStatement.exception ?: return
        val colonStart = condition.textRange.endOffset
        val colonEnd = exceptionExpression.textRange.startOffset
        descriptors.addIfNotEmpty(
            colonStart,
            colonEnd,
            " : ",
        )
        if (!semicolonsCollapse && throwStatement.text.endsWith(";")) {
            descriptors.addIfNotEmpty(
                exceptionExpression.textRange.endOffset,
                throwStatement.textRange.endOffset - 1,
                "",
            )
            if (element.textRange.endOffset > throwStatement.textRange.endOffset) {
                descriptors.addIfNotEmpty(
                    throwStatement.textRange.endOffset,
                    element.textRange.endOffset,
                    "",
                )
            }
        } else {
            descriptors.addIfNotEmpty(
                exceptionExpression.textRange.endOffset,
                element.textRange.endOffset,
                if (semicolonsCollapse) "" else ";",
            )
        }
    }

    companion object : IControlFlowState by AdvancedExpressionFoldingSettings.State()() {
        private val supportedOperatorSigns = setOf("==", "!=", ">", "<", ">=", "<=")

        fun isCompactExpression(element: PsiIfStatement): Boolean {
            return compactControlFlowSyntaxCollapse &&
                element.rParenth != null &&
                element.lParenth != null &&
                element.condition != null
        }

        fun isAssertExpression(element: PsiIfStatement): Boolean {
            val condition = element.condition
            if (!assertsCollapse || condition !is PsiBinaryExpression) {
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
