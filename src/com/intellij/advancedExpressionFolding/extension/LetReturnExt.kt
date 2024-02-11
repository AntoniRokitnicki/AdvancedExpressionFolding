package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.ElvisReturnNull
import com.intellij.advancedExpressionFolding.expression.custom.LetReturnIt
import com.intellij.advancedExpressionFolding.extension.BuilderShiftExt.isKotlinQuickReturn
import com.intellij.psi.*
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset

class LetReturnExt : IExtension {
    companion object {
        @JvmStatic
        fun getIfExpression(element: PsiIfStatement): Expression? {
            if (!isKotlinQuickReturn()) {
                return null
            }

            val containingBlock = element.parent as? PsiCodeBlock ?: return null
            val array =
                containingBlock.statements.asInstance(PsiDeclarationStatement::class.java, PsiIfStatement::class.java)
                    ?: return null
            /*
            Expression expression = ForStatementExpressionExt.getForStatementExpression((PsiForStatement) element, document);
            if (expression != null) {
                return expression;
            }
            */
            val declaration = array[0] as PsiDeclarationStatement
            val ifNotNullStatement = array[1] as PsiIfStatement
            val psiBinaryExpression = ifNotNullStatement.condition as? PsiBinaryExpression

            // 1) if (expression != null) {

            // 1a) expression
            val variable = (psiBinaryExpression?.lOperand as? PsiReferenceExpression)?.resolve() as? PsiLocalVariable
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
            ifNotNullStatement.elseBranch?.let { return null }
            // 2b) then
            // return local var
            val returnValue =
                ifNotNullStatement.thenBranch.asInstance<PsiBlockStatement>()?.codeBlock?.statements?.asInstance(
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

            val ifParent = ifNotNullStatement.parent
            val methodCallComma = methodCall.nextSibling

            val declarationRange = (declaration.startOffset..methodCall.startOffset).toTextRange()
            val letRange = (methodCallComma.startOffset..ifNotNullStatement.endOffset).toTextRange()
            if (notEquals) {
                return LetReturnIt(
                    ifNotNullStatement, ifNotNullStatement.textRange,
                    declaration, declarationRange,
                    ifParent, letRange
                )
            } else {
                return ElvisReturnNull(
                    ifNotNullStatement, ifNotNullStatement.textRange,
                    declaration, declarationRange,
                    ifParent, letRange
                )
            }
        }
    }

}
