package com.intellij.advancedExpressionFolding.processor.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ForEachIndexedStatement
import com.intellij.advancedExpressionFolding.expression.controlflow.ForEachStatement
import com.intellij.advancedExpressionFolding.expression.controlflow.ForStatement
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.processor.argumentExpressions
import com.intellij.advancedExpressionFolding.processor.argumentCount
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IControlFlowState
import com.intellij.advancedExpressionFolding.settings.state.IExpressionCollapseState
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiArrayAccessExpression
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiBlockStatement
import com.intellij.psi.PsiDeclarationStatement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiForStatement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiPostfixExpression
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiStatement
import com.intellij.psi.PsiVariable
import com.intellij.psi.SyntaxTraverser

object ForStatementExpressionExt :
    IExpressionCollapseState by State()(),
    IControlFlowState by State()() {

    fun getForStatementExpression(element: PsiForStatement, document: Document): Expression? {
        val lParenth = element.lParenth
        val rParenth = element.rParenth
        val initialization = element.initialization
        val update = element.update
        val condition = element.condition
        if (rangeExpressionsCollapse &&
            lParenth != null &&
            rParenth != null &&
            initialization is PsiDeclarationStatement &&
            initialization.declaredElements.size == 1 &&
            initialization.declaredElements[0] is PsiVariable &&
            (initialization.declaredElements[0] as PsiVariable).initializer != null &&
            update != null &&
            update.children.size == 1 &&
            update.children[0] is PsiPostfixExpression &&
            (update.children[0] as PsiPostfixExpression).operand is PsiReferenceExpression &&
            (update.children[0] as PsiPostfixExpression).operationSign.text == "++" &&
            ((update.children[0] as PsiPostfixExpression).operand as PsiReferenceExpression).reference != null &&
            condition is PsiBinaryExpression &&
            condition.lOperand is PsiReferenceExpression &&
            condition.lOperand.reference != null &&
            condition.rOperand != null
        ) {
            val updateVariable = ((update.children[0] as PsiPostfixExpression).operand as PsiReferenceExpression).reference?.resolve() as? PsiVariable
            val conditionROperand = condition.rOperand
            val reference: PsiReference? = (condition.lOperand as PsiReferenceExpression).reference
            if (reference != null) {
                val conditionVariable = reference.resolve() as? PsiVariable
                val declaredVariable = initialization.declaredElements[0] as PsiVariable
                if (updateVariable != null &&
                    conditionROperand != null &&
                    updateVariable == declaredVariable &&
                    updateVariable == conditionVariable &&
                    (updateVariable.type.canonicalText == "int" || updateVariable.type.canonicalText == "long")
                ) {
                    val identifier = declaredVariable.children.firstOrNull { it is PsiIdentifier } as? PsiIdentifier
                    if (identifier != null) {
                        val variable = Variable(identifier, identifier.textRange, null, identifier.text, false)
                        val start = BuildExpressionExt.getAnyExpression(declaredVariable.initializer!!, document)
                        val end = BuildExpressionExt.getAnyExpression(conditionROperand, document)
                        val sign = condition.operationSign.text
                        if (sign == "<" || sign == "<=") {
                            val body = element.body
                            if (body is PsiBlockStatement && body.codeBlock.statements.isNotEmpty()) {
                                val firstStatement = body.codeBlock.statements[0]
                                if (firstStatement is PsiDeclarationStatement && firstStatement.declaredElements.size == 1) {
                                    val declaration = firstStatement.declaredElements[0] as? PsiVariable
                                    if (declaration != null) {
                                        val variableName = declaration.nameIdentifier
                                        val initializerExpr = declaration.initializer
                                        if (start is NumberLiteral && start.number == 0) {
                                            val foreach = buildIndexedOrSimpleForEach(
                                                element,
                                                initialization,
                                                declaration,
                                                variableName,
                                                initializerExpr,
                                                conditionROperand,
                                                conditionVariable,
                                                updateVariable
                                            )
                                            if (foreach != null) {
                                                return foreach
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
                                sign == "<="
                            )
                        }
                    }
                }
            }
        }
        if (element.condition != null && element.lParenth != null && element.rParenth != null &&
            compactControlFlowSyntaxCollapse
        ) {
            return CompactControlFlowExpression(
                element,
                TextRange.create(element.lParenth!!.textRange.startOffset, element.rParenth!!.textRange.endOffset)
            )
        }
        return null
    }

    private fun buildIndexedOrSimpleForEach(
        element: PsiForStatement,
        initialization: PsiStatement,
        declaration: PsiVariable,
        variableName: PsiIdentifier?,
        initializer: PsiExpression?,
        conditionROperand: PsiExpression,
        conditionVariable: PsiVariable?,
        updateVariable: PsiVariable
    ): Expression? {
        if (variableName == null || conditionVariable == null) {
            return null
        }
        if (initializer is PsiArrayAccessExpression &&
            initializer.indexExpression is PsiReferenceExpression &&
            isReferenceTo(initializer.indexExpression as PsiReferenceExpression?, conditionVariable) &&
            conditionROperand is PsiReferenceExpression &&
            conditionROperand.qualifierExpression is PsiReferenceExpression &&
            initializer.arrayExpression is PsiReferenceExpression &&
            isReferenceTo(
                conditionROperand.qualifierExpression as PsiReferenceExpression?,
                (initializer.arrayExpression as PsiReferenceExpression).resolve()
            )
        ) {
            val arrayExpression = initializer.arrayExpression
            val body = element.body ?: return null
            val references = SyntaxTraverser.psiTraverser(body)
                .filter { it is PsiReferenceExpression && (it as PsiReferenceExpression).isReferenceTo(conditionVariable) }
                .toList()
            return if (references.size == 1) {
                ForEachStatement(
                    element,
                    TextRange.create(initialization.textRange.startOffset, declaration.textRange.endOffset),
                    declaration.textRange,
                    variableName.textRange,
                    arrayExpression.textRange
                )
            } else {
                val indexName = conditionVariable.nameIdentifier
                if (indexName != null) {
                    val isFinal = Helper.calculateIfFinal(declaration) && Helper.calculateIfFinal(updateVariable)
                    ForEachIndexedStatement(
                        element,
                        TextRange.create(initialization.textRange.startOffset - 1, declaration.textRange.endOffset),
                        declaration.textRange,
                        indexName.textRange,
                        variableName.textRange,
                        arrayExpression.textRange,
                        varExpressionsCollapse,
                        isFinal
                    )
                } else {
                    null
                }
            }
        } else if (initializer is PsiMethodCallExpression &&
            initializer.argumentCount == 1 &&
            initializer.argumentExpressions[0] is PsiReferenceExpression &&
            (initializer.argumentExpressions[0] as PsiReferenceExpression).isReferenceTo(conditionVariable) &&
            conditionROperand is PsiMethodCallExpression &&
            conditionROperand.methodExpression.qualifierExpression is PsiReferenceExpression &&
            initializer.methodExpression.qualifierExpression is PsiReferenceExpression &&
            Helper.isReferenceToReference(
                conditionROperand.methodExpression.qualifierExpression as PsiReferenceExpression,
                initializer.methodExpression.qualifierExpression as PsiReferenceExpression
            )
        ) {
            val arrayExpression = initializer.methodExpression.qualifierExpression
            if (arrayExpression != null) {
                val body = element.body ?: return null
                val references = SyntaxTraverser.psiTraverser(body)
                    .filter { it is PsiReferenceExpression && (it as PsiReferenceExpression).isReferenceTo(conditionVariable) }
                    .toList()
                return if (references.size == 1) {
                    ForEachStatement(
                        element,
                        TextRange.create(initialization.textRange.startOffset, declaration.textRange.endOffset),
                        declaration.textRange,
                        variableName.textRange,
                        arrayExpression.textRange
                    )
                } else {
                    val indexName = conditionVariable.nameIdentifier
                    if (indexName != null) {
                        val isFinal = Helper.calculateIfFinal(declaration) && Helper.calculateIfFinal(updateVariable)
                        ForEachIndexedStatement(
                            element,
                            TextRange.create(initialization.textRange.startOffset - 1, declaration.textRange.endOffset),
                            declaration.textRange,
                            indexName.textRange,
                            variableName.textRange,
                            arrayExpression.textRange,
                            varExpressionsCollapse,
                            isFinal
                        )
                    } else {
                        null
                    }
                }
            }
        }
        return null
    }

    fun isReferenceTo(referenceExpression: PsiReferenceExpression?, element: PsiElement?): Boolean {
        return referenceExpression != null && element != null && referenceExpression.isReferenceTo(element)
    }
}
