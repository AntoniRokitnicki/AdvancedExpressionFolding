package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.psi.*

object ExperimentalExt : BaseExtension() {

    fun createSingleExpressionFunctions(method: PsiMethod): Expression? {
        val statement = method.body?.statements?.singleOrNull()?.takeIf {
            method.body!!.text.length < 145
        } ?: return null

        return statement.asInstance<PsiReturnStatement>()?.let {
            sefReturn(it, method)
        } ?: sefNoReturn(statement, method)
    }

    private fun sefNoReturn(
        statement: PsiStatement,
        method: PsiMethod
    ): Expression? {
        val body = method.body!!
        val whitespaces = body.children.filter {
            it.isWhitespace()
        }.map {
            it.expr(" ")
        }
        val endingSemicolon = statement.children.lastOrNull().asInstance<PsiJavaToken>()//;
        val exprList = elemList(endingSemicolon)
        exprList.addAll(whitespaces)

        exprList.add(getAnyExpression(body))
        return exprList.exprWrap(method)
    }

    private fun sefReturn(
        statement: PsiReturnStatement,
        method: PsiMethod,
    ): Expression? {
        val keyword = statement.children.firstOrNull().asInstance<PsiKeyword>()
        val keywordNextWhitespace = keyword?.nextWhiteSpace()
        val returnPrevSpace = statement.prevWhiteSpace()


        val preBracketSpace = statement.nextSibling
        val endingSemicolon = statement.children.lastOrNull().asInstance<PsiJavaToken>()//;

        val exprList = elemList(keyword, keywordNextWhitespace, preBracketSpace)

        //TODO: fix issue with Intellij method folding to fold like kotlin
        //val braceLeft = returnPrevSpace?.prevSibling
        //val braceRight = preBracketSpace.nextSibling
        //exprList.add(braceLeft?.expr("= "))
        // hide them then:
        exprList.add(returnPrevSpace?.expr(" "))
        exprList.add(endingSemicolon?.expr(" "))

        return exprList.exprWrap(method)
    }

    @JvmStatic
    fun createExpression(element: PsiReferenceExpression): Expression? {
        return experimental.on(element)?.singletonField()
    }

    private fun PsiReferenceExpression.singletonField(): Expression? {
        return resolve().asInstance<PsiField>()?.takeIf {
            it.isStatic() && it.singletonField
        }?.let {
            identifier?.let {
                val exprList = exprList(it.expr(Consts.Emoji.MAN_STANDING.toString()))
                exprList.exprWrap(this)
            }
        }
    }



}
