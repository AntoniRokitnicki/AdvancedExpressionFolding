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
import java.util.*
import java.util.stream.Stream

object ForStatementExpressionExt {
    fun getForStatementExpression(element: PsiForStatement, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val lParenth = element.lParenth
        val rParenth = element.rParenth
        val initialization = element.initialization
        val update = element.update
        val condition = element.condition
        if (settings.state.rangeExpressionsCollapse &&
            lParenth != null && rParenth != null &&
            initialization is PsiDeclarationStatement && initialization.declaredElements.size == 1 &&
            initialization.declaredElements[0] is PsiVariable &&
            (initialization.declaredElements[0] as PsiVariable).initializer != null &&
            update != null && update.children.size == 1 &&
            update.children[0] is PsiPostfixExpression &&
            (update.children[0] as PsiPostfixExpression).operand is PsiReferenceExpression &&
            (update.children[0] as PsiPostfixExpression).operationSign.text == "++" &&
            (update.children[0] as PsiPostfixExpression).operand.reference != null &&
            condition is PsiBinaryExpression &&
            condition.lOperand is PsiReferenceExpression && condition.lOperand.reference != null &&
            condition.rOperand != null
        ) {
            val updateVariable = ((update.children[0] as PsiPostfixExpression).operand.reference!!.resolve()) as PsiVariable?
            val conditionROperand = condition.rOperand
            val reference = condition.lOperand.reference
            if (reference != null) {
                val conditionVariable = reference.resolve() as PsiVariable?
                if (updateVariable != null && conditionROperand != null &&
                    updateVariable === initialization.declaredElements[0] &&
                    updateVariable === conditionVariable &&
                    (updateVariable.type.canonicalText == "int" || updateVariable.type.canonicalText == "long")
                ) {
                    val identifier = Stream.of(*initialization.declaredElements[0].children)
                        .filter { c: PsiElement? -> c is PsiIdentifier }
                        .findAny()
                    if (identifier.isPresent) {
                        val variable = Variable(identifier.get(), identifier.get().textRange, null, identifier.get().text, false)
                        val start = BuildExpressionExt.getAnyExpression(
                            (initialization.declaredElements[0] as PsiVariable).initializer!!,
                            document
                        )
                        val end = BuildExpressionExt.getAnyExpression(conditionROperand, document)
                        val sign = condition.operationSign.text
                        if ("<" == sign || "<=" == sign) {
                            if (element.body is PsiBlockStatement &&
                                (element.body as PsiBlockStatement).codeBlock.statements.isNotEmpty() &&
                                (element.body as PsiBlockStatement).codeBlock.statements[0] is PsiDeclarationStatement &&
                                ((element.body as PsiBlockStatement).codeBlock.statements[0] as PsiDeclarationStatement).declaredElements.size == 1
                            ) {
                                if (start is NumberLiteral && start.number == 0) {
                                    val declaration = (((element.body as PsiBlockStatement).codeBlock.statements[0]) as PsiDeclarationStatement).declaredElements[0] as PsiVariable
                                    val variableName = declaration.nameIdentifier
                                    val initializer = declaration.initializer
                                    if (variableName != null && initializer is PsiArrayAccessExpression &&
                                        initializer.indexExpression is PsiReferenceExpression &&
                                        isReferenceTo(
                                            (initializer.indexExpression as PsiReferenceExpression),
                                            conditionVariable
                                        ) &&
                                        conditionROperand is PsiReferenceExpression &&
                                        conditionROperand.qualifierExpression is PsiReferenceExpression &&
                                        initializer.arrayExpression is PsiReferenceExpression &&
                                        isReferenceTo(
                                            conditionROperand.qualifierExpression as PsiReferenceExpression?,
                                            (initializer.arrayExpression as PsiReferenceExpression).resolve()
                                        )
                                    ) {
                                        val arrayExpression = initializer.arrayExpression
                                        val references = SyntaxTraverser.psiTraverser(element.body)
                                            .filter { e: PsiElement? ->
                                                e is PsiReferenceExpression && e.isReferenceTo(conditionVariable)
                                            }.toList()
                                        if (references.size == 1) {
                                            return ForEachStatement(
                                                element,
                                                TextRange.create(
                                                    initialization.textRange.startOffset,
                                                    declaration.textRange.endOffset
                                                ),
                                                declaration.textRange,
                                                variableName.textRange,
                                                arrayExpression.textRange
                                            )
                                        } else {
                                            val indexName = conditionVariable?.nameIdentifier
                                            val isFinal = Helper.calculateIfFinal(declaration) && Helper.calculateIfFinal(updateVariable)
                                            if (indexName != null) {
                                                return ForEachIndexedStatement(
                                                    element,
                                                    TextRange.create(
                                                        initialization.textRange.startOffset - 1,
                                                        declaration.textRange.endOffset
                                                    ),
                                                    declaration.textRange,
                                                    indexName.textRange,
                                                    variableName.textRange,
                                                    arrayExpression.textRange,
                                                    settings.state.varExpressionsCollapse,
                                                    isFinal
                                                )
                                            }
                                        }
                                    } else if (variableName != null && initializer is PsiMethodCallExpression &&
                                        initializer.argumentList.expressions.size == 1 &&
                                        initializer.argumentList.expressions[0] is PsiReferenceExpression &&
                                        (initializer.argumentList.expressions[0] as PsiReferenceExpression).isReferenceTo(conditionVariable) &&
                                        conditionROperand is PsiMethodCallExpression &&
                                        conditionROperand.methodExpression.qualifierExpression is PsiReferenceExpression &&
                                        initializer.methodExpression.qualifierExpression is PsiReferenceExpression &&
                                        Helper.isReferenceToReference(
                                            conditionROperand.methodExpression.qualifierExpression as PsiReferenceExpression?,
                                            initializer.methodExpression.qualifierExpression as PsiReferenceExpression?
                                        )
                                    ) {
                                        val arrayExpression = initializer.methodExpression.qualifierExpression
                                        if (arrayExpression != null) {
                                            val references = SyntaxTraverser.psiTraverser(element.body)
                                                .filter { e: PsiElement? ->
                                                    e is PsiReferenceExpression && e.isReferenceTo(conditionVariable)
                                                }.toList()
                                            if (references.size == 1) {
                                                return ForEachStatement(
                                                    element,
                                                    TextRange.create(
                                                        initialization.textRange.startOffset,
                                                        declaration.textRange.endOffset
                                                    ),
                                                    declaration.textRange,
                                                    variableName.textRange,
                                                    arrayExpression.textRange
                                                )
                                            } else {
                                                val indexName = conditionVariable?.nameIdentifier
                                                if (indexName != null) {
                                                    val isFinal = Helper.calculateIfFinal(declaration) && Helper.calculateIfFinal(updateVariable)
                                                    return ForEachIndexedStatement(
                                                        element,
                                                        TextRange.create(
                                                            initialization.textRange.startOffset - 1,
                                                            declaration.textRange.endOffset
                                                        ),
                                                        declaration.textRange,
                                                        indexName.textRange,
                                                        variableName.textRange,
                                                        arrayExpression.textRange,
                                                        settings.state.varExpressionsCollapse,
                                                        isFinal
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            val startOffset = lParenth.textRange.startOffset + 1
                            val endOffset = rParenth.textRange.endOffset - 1
                            return ForStatement(
                                element,
                                TextRange.create(startOffset, endOffset),
                                variable,
                                start,
                                true,
                                end,
                                "<=" == sign
                            )
                        }
                    }
                }
            }
        }
        if (element.condition != null && element.lParenth != null && element.rParenth != null &&
            settings.state.compactControlFlowSyntaxCollapse
        ) {
            return CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.lParenth!!.textRange.startOffset,
                    element.rParenth!!.textRange.endOffset
                )
            )
        }
        return null
    }

    fun isReferenceTo(referenceExpression: PsiReferenceExpression?, element: PsiElement?): Boolean {
        return referenceExpression != null && element != null && referenceExpression.isReferenceTo(element)
    }
}

