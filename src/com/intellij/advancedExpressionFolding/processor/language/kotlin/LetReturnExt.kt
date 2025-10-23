package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.ElvisReturnNull
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.LetReturnIt
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IKotlinLanguageState
import com.intellij.psi.*


object LetReturnExt : IKotlinLanguageState by AdvancedExpressionFoldingSettings.State() {

    @JvmStatic
    fun getIfExpression(element: PsiIfStatement): Expression? {
        if (!kotlinQuickReturn) {
            return null
        }
        if (isDebugSessionActive(element)) {
            return null
        }
        /*
        Expression expression = ForStatementExpressionExt.getForStatementExpression((PsiForStatement) element, document);
        if (expression != null) {
            return expression;
        }
        */
        val declaration = element.prevRealSibling as? PsiDeclarationStatement ?: return null
        val psiBinaryExpression = element.condition as? PsiBinaryExpression ?: return null

        // 1) if (expression != null) {

        // 1a) expression
        val variable = (psiBinaryExpression.lOperand as? PsiReferenceExpression)?.resolve() as? PsiLocalVariable
            ?: return null

        val token = psiBinaryExpression.operationTokenType
        // 1b)  !=
        val notEquals = token == JavaTokenType.NE
        // 1b)  ==
        val equals = token == JavaTokenType.EQEQ

        if (!notEquals && !equals) {
            return null
        }

        // 1c) null
        (psiBinaryExpression.rOperand as? PsiLiteralExpression)
            ?.let {
                if (it.value == null) {
                    it
                } else {
                    null
                }
            } ?: return null

        // 2) return expression;
        // 2a) no else
        element.elseBranch?.let { return null }
        // 2b) then
        // return local var
        val returnValue =
            element.thenBranch.asInstance<PsiBlockStatement>()?.codeBlock?.statements?.asInstance(
                PsiReturnStatement::class.java
            )
                ?.firstOrNullIfNotEmpty()
                ?.let {
                    it as? PsiReturnStatement
                }?.returnValue

        if (notEquals) {
            returnValue
                ?.let {
                    it as? PsiReferenceExpression
                }
                ?.resolve()
                ?.let {
                    if (it == variable) {
                        it
                    } else {
                        null
                    }
                } ?: return null
        } else {
            returnValue.asInstance<PsiLiteralExpression>()?.let {
                if (it.value == null) {
                    it
                } else {
                    null
                }
            } ?: return null
        }


        val methodCall = declaration.declaredElements.firstOrNullIfNotEmpty()
            .asInstance<PsiLocalVariable>()?.initializer.asInstance<PsiMethodCallExpression>()
            ?: return null

        val block = element.parent.asInstance<PsiCodeBlock>() ?: return null
        val index = block.statements.indexOf(element)

        val foldVariable = !isLocalVariableUsed(block, variable, index)

        val ifParent = element.parent
        val methodCallComma = methodCall.nextSibling

        val declarationRange = (declaration.start()..methodCall.start()).toTextRange()
        val letRange = (methodCallComma.start()..element.end()).toTextRange()
        return if (notEquals) {
            LetReturnIt(
                element, element.textRange,
                declaration, declarationRange,
                ifParent, letRange, foldVariable
            )
        } else {
            ElvisReturnNull(
                element, element.textRange,
                declaration, declarationRange,
                ifParent, letRange, foldVariable
            )
        }
    }

    private fun isLocalVariableUsed(block: PsiCodeBlock, variable: PsiLocalVariable, afterIndex: Int): Boolean {
        var used = false
        var startChecking = false
        block.accept(object : JavaRecursiveElementVisitor() {
            override fun visitStatement(statement: PsiStatement) {
                val indexOf = block.statements.indexOf(statement)
                if (afterIndex < indexOf) {
                    startChecking = true
                }
                if (!used) {
                    super.visitStatement(statement)
                }
            }

            override fun visitReferenceExpression(expression: PsiReferenceExpression) {
                if (!startChecking) {
                    return
                }
                if (expression.resolve() == variable) {
                    used = true
                }
                if (!used) {
                    super.visitReferenceExpression(expression)
                }
            }
        })
        return used
    }

}
