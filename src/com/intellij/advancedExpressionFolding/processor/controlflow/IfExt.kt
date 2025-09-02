package com.intellij.advancedExpressionFolding.processor.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ElvisExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.IfExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ShortElvisExpression
import com.intellij.advancedExpressionFolding.expression.literal.InterpolatedString
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.expression.math.basic.Add
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.BinaryExpressionExt
import com.intellij.advancedExpressionFolding.processor.language.kotlin.IfNullSafeExt
import com.intellij.advancedExpressionFolding.processor.language.kotlin.LetReturnExt
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.Arrays
import java.util.Collections

import java.util.stream.Collectors

object IfExt {
    @JvmStatic
    fun getSwitchStatement(element: PsiSwitchStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        return if (element.getExpression() != null
            && element.getLParenth() != null && element.getRParenth() != null
            && settings.getState().getCompactControlFlowSyntaxCollapse()
        ) {
            CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.getLParenth().getTextRange().getStartOffset(),
                    element.getRParenth().getTextRange().getEndOffset()
                )
            )
        } else {
            null
        }
    }

    @JvmStatic
    @Nullable
    fun getIfExpression(element: PsiIfStatement, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val getIfExpression = LetReturnExt.getIfExpression(element)
        if (getIfExpression != null) {
            return getIfExpression
        }
        if (settings.getState().getCheckExpressionsCollapse()
            && element.getCondition() is PsiBinaryExpression
        ) {
            val condition = element.getCondition() as PsiBinaryExpression
            if (condition.getOperationSign().getText() == "!="
                && element.getElseBranch() == null
                && (BaseExtension.isNull(condition.getLOperand().getType())
                    && condition.getROperand() != null
                    || condition.getROperand() != null && BaseExtension.isNull(condition.getROperand().getType()))
                && element.getThenBranch() != null
            ) {
                var thenStatement: PsiStatement? = element.getThenBranch()
                if (thenStatement!!.getChildren().size == 1 && thenStatement.getChildren()[0] is PsiCodeBlock) {
                    val statements = (thenStatement.getChildren()[0] as PsiCodeBlock).getStatements()
                    if (statements.size == 1) {
                        thenStatement = statements[0]
                    } else {
                        return null
                    }
                }
                val qualifier: PsiElement = if (BaseExtension.isNull(condition.getLOperand().getType())) {
                    condition.getROperand()!!
                } else {
                    condition.getLOperand()
                }
                if (qualifier is PsiReferenceExpression
                    || qualifier is PsiMethodCallExpression && Helper.startsWith((qualifier as PsiMethodCallExpression).getMethodExpression().getReferenceName(), "get")
                    && (qualifier as PsiMethodCallExpression).getArgumentList().getExpressions().size == 0
                ) {
                    val r = Helper.findSameQualifier(thenStatement!!, qualifier)
                    if (r != null) {
                        return ShortElvisExpression(
                            element,
                            element.getTextRange(),
                            BuildExpressionExt.getAnyExpression(thenStatement, document),
                            Collections.singletonList(r.getTextRange())
                        )
                    }
                }
            }
        }
        return IfExpression(element, element.getTextRange())
    }

    @JvmStatic
    @Nullable
    fun getConditionalExpression(@NotNull element: PsiConditionalExpression, @NotNull document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (settings.getState().getCheckExpressionsCollapse()
            && element.getCondition() is PsiBinaryExpression
        ) {
            val condition = element.getCondition() as PsiBinaryExpression
            if (condition.getOperationSign().getText() == "!="
                && condition.getROperand() != null
                && (BaseExtension.isNull(condition.getLOperand().getType())
                    || BaseExtension.isNull(condition.getROperand().getType()))
                && element.getThenExpression() != null
                && element.getElseExpression() != null
            ) {
                val qualifier: PsiElement = if (BaseExtension.isNull(condition.getLOperand().getType())) {
                    condition.getROperand()!!
                } else {
                    condition.getLOperand()
                }
                if (qualifier is PsiReferenceExpression
                    || qualifier is PsiMethodCallExpression && Helper.startsWith((qualifier as PsiMethodCallExpression).getMethodExpression().getReferenceName(), "get")
                    && (qualifier as PsiMethodCallExpression).getArgumentList().getExpressions().size == 0
                ) {
                    val r: PsiReferenceExpression = if (qualifier is PsiReferenceExpression) {
                        qualifier
                    } else {
                        (qualifier as PsiMethodCallExpression).getMethodExpression()
                    }
                    val references = SyntaxTraverser.psiTraverser(element.getThenExpression())
                        .filter { e ->
                            e is PsiReferenceExpression && e.getParent() !is PsiMethodCallExpression && Helper.isReferenceToReference(e, r)
                                    || e is PsiMethodCallExpression && Helper.isReferenceToReference((e as PsiMethodCallExpression).getMethodExpression(), r)
                        }
                        .toList()
                    if (!references.isEmpty()) {
                        return ElvisExpression(
                            element,
                            element.getTextRange(),
                            BuildExpressionExt.getAnyExpression(element.getThenExpression(), document),
                            BuildExpressionExt.getAnyExpression(element.getElseExpression(), document),
                            references.stream().map { e: PsiElement -> e.getTextRange() }.collect(Collectors.toList())
                        )
                    }
                }
            }
        }
        return null
    }

    @JvmStatic
    @Nullable
    fun getPolyadicExpression(@NotNull element: PsiPolyadicExpression, @NotNull document: Document): Expression? {
        var add = true
        var string = false
        var operands: Array<Expression?>? = null
        for (i in 0 until element.getOperands().size - 1) {
            val a = element.getOperands()[i]
            val b = element.getOperands()[i + 1]
            val token = element.getTokenBeforeOperand(b)
            if (token != null) {
                if (token.getText() == "&&" && a is PsiBinaryExpression && b is PsiBinaryExpression) {
                    val twoBinaryExpression = BinaryExpressionExt.getAndTwoBinaryExpressions(element, a, b, document)
                    if (twoBinaryExpression != null) {
                        return twoBinaryExpression
                    }
                }
                if (add && token.getText() == "+") {
                    if (operands == null) {
                        operands = arrayOfNulls(element.getOperands().size)
                    }
                    operands[i] = BuildExpressionExt.getAnyExpression(element.getOperands()[i], document)
                    if (operands[i] is StringLiteral) {
                        string = true
                    }
                    if (operands[i] is InterpolatedString) {
                        add = false
                    }
                } else {
                    add = false
                }
            }
        }
        if (add && element.getOperands().size > 1 && operands != null && string) {
            operands[element.getOperands().size - 1] = BuildExpressionExt.getAnyExpression(element.getOperands()[element.getOperands().size - 1], document)
            return Add(element, element.getTextRange(), Arrays.asList(*operands))
        }
        return null
    }
}

