package com.intellij.advancedExpressionFolding.extension.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.openapi.editor.Document
import com.intellij.psi.*

object SingleExpressionFunctionExt : BaseExtension() {

    fun createSingleExpressionFunctions(method: PsiMethod, document: Document): Expression? {
        val single = method.body?.statements?.singleOrNull()
        val statement = single?.takeIf {
            method.body!!.text.length < 145
        } ?: return null

        val singleExpression = getAnyExpression(single.children.first(), document)

        return statement.asInstance<PsiReturnStatement>()?.let {
            onSingleExpressionReturn(it, method, singleExpression)
        } ?: onSingleExpressionNoReturn(statement, method, singleExpression)
    }

    private fun onSingleExpressionNoReturn(
        statement: PsiStatement,
        method: PsiMethod,
        singleExpression: Expression
    ): Expression? {
        val body = method.body!!
        val whitespaces = body.children.filter {
            it.isWhitespace()
        }.map {
            it.expr(" ")
        }
        val endingSemicolon = statement.children.lastOrNull().asInstance<PsiJavaToken>()//;
        val exprList = arrayOf<PsiElement?>(endingSemicolon).mapNotNull {
            it.exprHide()
        }.toMutableList<Expression?>()
        exprList.addAll(whitespaces)

        exprList.add(getAnyExpression(body))
        exprList.add(singleExpression)
        return exprList.exprWrap(method, group())
    }

    private fun onSingleExpressionReturn(
        statement: PsiReturnStatement,
        method: PsiMethod,
        singleExpression: Expression,
    ): Expression? {
        val keyword = statement.children.firstOrNull().asInstance<PsiKeyword>()
        val keywordNextWhitespace = keyword?.nextWhiteSpace()
        val returnPrevSpace = statement.prevWhiteSpace()

        val preBracketSpace = statement.nextSibling
        val endingSemicolon = statement.children.lastOrNull().asInstance<PsiJavaToken>()//;

        val exprList = sequenceOf(keyword, keywordNextWhitespace).mapNotNull {
            it.exprHide()
        }.toMutableList<Expression?>()
        exprList.add(preBracketSpace.expr(" "))

        // there are conflicts with default intellij single line method folding
        //val braceLeft = returnPrevSpace?.prevSibling
        //val braceRight = preBracketSpace.nextSibling
        //exprList.add(braceLeft?.expr("= "))

        exprList.add(returnPrevSpace?.expr(" "))
        exprList.add(endingSemicolon?.exprHide())
        exprList.add(singleExpression)

        statement.returnValue.asLiteral()?.let {
            method.foldForLiteral(exprList)
        }
        return exprList.exprWrap(method, group())
    }

    private fun PsiMethod.foldForLiteral(
        exprList: MutableList<Expression?>
    ) {
        if (modifier().isPublic() && !isStatic()) {
            foldKeywords(exprList)

            exprList.add(returnTypeElement.prevWhiteSpace().exprHide())
            exprList.add(returnTypeElement.exprHide())
            exprList.add(returnTypeElement.nextWhiteSpace().exprHide())
            if (parameterList.parameters.isEmpty()) {
                exprList.add(parameterList.exprHide())
                exprList.add(parameterList.nextWhiteSpace().exprHide())
            }
        }
    }

    private fun PsiMethod.foldKeywords(exprList: MutableList<Expression?>) {
        keywords.takeIfSizeNot(0)?.let {
            exprList.add(it.exprHide())
        }
    }

}
