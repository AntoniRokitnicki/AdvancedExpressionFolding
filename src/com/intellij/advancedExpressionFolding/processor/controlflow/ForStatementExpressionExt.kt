package com.intellij.advancedExpressionFolding.processor.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ForEachIndexedStatement
import com.intellij.advancedExpressionFolding.expression.controlflow.ForEachStatement
import com.intellij.advancedExpressionFolding.expression.controlflow.ForStatement
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.Optional
import java.util.stream.Stream
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression

object ForStatementExpressionExt {
    @JvmStatic
    @Nullable
    fun getForStatementExpression(@NotNull element: PsiForStatement, @NotNull document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val lParenth = element.getLParenth()
        val rParenth = element.getRParenth()
        val initialization = element.getInitialization()
        val update = element.getUpdate()
        val condition = element.getCondition()
        if (settings.getState().getRangeExpressionsCollapse()
            && lParenth != null && rParenth != null
            && initialization is PsiDeclarationStatement
            && initialization.getDeclaredElements().size == 1
            && initialization.getDeclaredElements()[0] is PsiVariable
            && (initialization.getDeclaredElements()[0] as PsiVariable).getInitializer() != null
            && update != null && update.getChildren().size == 1
            && update.getChildren()[0] is PsiPostfixExpression
            && (update.getChildren()[0] as PsiPostfixExpression).getOperand() is PsiReferenceExpression
            && (update.getChildren()[0] as PsiPostfixExpression).getOperationSign().getText() == "++"
            && ((update.getChildren()[0] as PsiPostfixExpression).getOperand() as PsiReferenceExpression).getReference() != null
            && condition is PsiBinaryExpression
            && condition.getLOperand() is PsiReferenceExpression
            && condition.getLOperand().getReference() != null
            && condition.getROperand() != null
        ) {
            val updateVariable = ((update.getChildren()[0] as PsiPostfixExpression).getOperand() as PsiReferenceExpression).getReference()!!.resolve() as PsiVariable?
            val conditionROperand = condition.getROperand()
            val reference = (condition.getLOperand() as PsiReferenceExpression).getReference()
            if (reference != null) {
                val conditionVariable = reference.resolve() as PsiVariable
                if (updateVariable != null && conditionROperand != null
                    && updateVariable == initialization.getDeclaredElements()[0]
                    && updateVariable == conditionVariable
                    && ("int" == updateVariable.getType().getCanonicalText()
                    || "long" == updateVariable.getType().getCanonicalText())
                ) {
                    val identifier: Optional<PsiElement> = Stream.of(initialization.getDeclaredElements()[0].getChildren()).filter { c -> c is PsiIdentifier }.findAny()
                    if (identifier.isPresent) {
                        val variable = Variable(identifier.get(), identifier.get().getTextRange(), null, identifier.get().getText(), false)
                        val start = getAnyExpression((initialization.getDeclaredElements()[0] as PsiVariable).getInitializer(), document)
                        val end = getAnyExpression(conditionROperand, document)
                        val sign = condition.getOperationSign().getText()
                        if ("<" == sign || "<=" == sign) {
                            if (element.getBody() is PsiBlockStatement
                                && (element.getBody() as PsiBlockStatement).getCodeBlock().getStatements().size > 0
                                && (element.getBody() as PsiBlockStatement).getCodeBlock().getStatements()[0] is PsiDeclarationStatement
                                && ((element.getBody() as PsiBlockStatement).getCodeBlock().getStatements()[0] as PsiDeclarationStatement).getDeclaredElements().size == 1
                            ) {
                                if (start is NumberLiteral && start.getNumber() == 0) {
                                    val declaration = ((element.getBody() as PsiBlockStatement).getCodeBlock().getStatements()[0] as PsiDeclarationStatement).getDeclaredElements()[0] as PsiVariable
                                    val variableName = declaration.getNameIdentifier()
                                    val initializer = declaration.getInitializer()
                                    if (variableName != null
                                        && initializer is PsiArrayAccessExpression
                                        && initializer.getIndexExpression() is PsiReferenceExpression
                                        && isReferenceTo(initializer.getIndexExpression() as PsiReferenceExpression, conditionVariable)
                                        && conditionROperand is PsiReferenceExpression
                                        && conditionROperand.getQualifierExpression() is PsiReferenceExpression
                                        && initializer.getArrayExpression() is PsiReferenceExpression
                                        && isReferenceTo(conditionROperand.getQualifierExpression() as PsiReferenceExpression, (initializer.getArrayExpression() as PsiReferenceExpression).resolve())
                                    ) {
                                        val arrayExpression = initializer.getArrayExpression()
                                        val references = SyntaxTraverser.psiTraverser(element.getBody()).filter { e -> e is PsiReferenceExpression && (e as PsiReferenceExpression).isReferenceTo(conditionVariable) }.toList()
                                        if (references.size == 1) {
                                            return ForEachStatement(
                                                element,
                                                TextRange.create(
                                                    initialization.getTextRange().getStartOffset(),
                                                    declaration.getTextRange().getEndOffset()
                                                ),
                                                declaration.getTextRange(), variableName.getTextRange(),
                                                arrayExpression.getTextRange()
                                            )
                                        } else {
                                            val indexName = conditionVariable.getNameIdentifier()
                                            val isFinal = Helper.calculateIfFinal(declaration) && Helper.calculateIfFinal(updateVariable)
                                            if (indexName != null) {
                                                return ForEachIndexedStatement(
                                                    element,
                                                    TextRange.create(
                                                        initialization.getTextRange().getStartOffset() - 1,
                                                        declaration.getTextRange().getEndOffset()
                                                    ),
                                                    declaration.getTextRange(),
                                                    indexName.getTextRange(), variableName.getTextRange(),
                                                    arrayExpression.getTextRange(),
                                                    settings.getState().getVarExpressionsCollapse(),
                                                    isFinal
                                                )
                                            }
                                        }
                                    } else if (variableName != null
                                        && initializer is PsiMethodCallExpression
                                        && initializer.getArgumentList().getExpressions().size == 1
                                        && initializer.getArgumentList().getExpressions()[0] is PsiReferenceExpression
                                        && (initializer.getArgumentList().getExpressions()[0] as PsiReferenceExpression).isReferenceTo(conditionVariable)
                                        && conditionROperand is PsiMethodCallExpression
                                        && conditionROperand.getMethodExpression().getQualifierExpression() is PsiReferenceExpression
                                        && initializer.getMethodExpression().getQualifierExpression() is PsiReferenceExpression
                                        && Helper.isReferenceToReference(conditionROperand.getMethodExpression().getQualifierExpression() as PsiReferenceExpression, initializer.getMethodExpression().getQualifierExpression() as PsiReferenceExpression)
                                    ) {
                                        val arrayExpression = initializer.getMethodExpression().getQualifierExpression()
                                        if (arrayExpression != null) {
                                            val references = SyntaxTraverser.psiTraverser(element.getBody()).filter { e -> e is PsiReferenceExpression && (e as PsiReferenceExpression).isReferenceTo(conditionVariable) }.toList()
                                            if (references.size == 1) {
                                                return ForEachStatement(
                                                    element,
                                                    TextRange.create(
                                                        initialization.getTextRange().getStartOffset(),
                                                        declaration.getTextRange().getEndOffset()
                                                    ),
                                                    declaration.getTextRange(), variableName.getTextRange(),
                                                    arrayExpression.getTextRange()
                                                )
                                            } else {
                                                val indexName = conditionVariable.getNameIdentifier()
                                                if (indexName != null) {
                                                    val isFinal = Helper.calculateIfFinal(declaration) && Helper.calculateIfFinal(updateVariable)
                                                    return ForEachIndexedStatement(
                                                        element,
                                                        TextRange.create(
                                                            initialization.getTextRange().getStartOffset() - 1,
                                                            declaration.getTextRange().getEndOffset()
                                                        ),
                                                        declaration.getTextRange(),
                                                        indexName.getTextRange(), variableName.getTextRange(),
                                                        arrayExpression.getTextRange(),
                                                        settings.getState().getVarExpressionsCollapse(),
                                                        isFinal
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            val startOffset = lParenth.getTextRange().getStartOffset() + 1
                            val endOffset = rParenth.getTextRange().getEndOffset() - 1
                            return ForStatement(element, TextRange.create(startOffset, endOffset), variable, start, true, end, "<=" == sign)
                        }
                    }
                }
            }
        }
        if (element.getCondition() != null
            && element.getLParenth() != null && element.getRParenth() != null
            && settings.getState().getCompactControlFlowSyntaxCollapse()
        ) {
            return CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.getLParenth().getTextRange().getStartOffset(),
                    element.getRParenth().getTextRange().getEndOffset()
                )
            )
        }
        return null
    }

    @JvmStatic
    fun isReferenceTo(referenceExpression: PsiReferenceExpression?, element: PsiElement?): Boolean {
        return referenceExpression != null && element != null && referenceExpression.isReferenceTo(element)
    }
}

