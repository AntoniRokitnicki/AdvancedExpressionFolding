package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.psi.*

object ExperimentalExt : BaseExtension() {

    fun createSingleExpressionFunctions(method: PsiMethod, document: Document): Expression? {
        val single = method.body?.statements?.singleOrNull()
        val statement = single?.takeIf {
            method.body!!.text.length < 145
        } ?: return null

        val singleExpression = getAnyExpression(single.children.first(), document)

        return statement.asInstance<PsiReturnStatement>()?.let {
            sefReturn(it, method, singleExpression)
        } ?: sefNoReturn(statement, method, singleExpression)
    }

    private fun sefNoReturn(
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
        val exprList = elemList(endingSemicolon)
        exprList.addAll(whitespaces)

        exprList.add(getAnyExpression(body))
        exprList.add(singleExpression)
        return exprList.exprWrap(method)
    }

    private fun sefReturn(
        statement: PsiReturnStatement,
        method: PsiMethod,
        singleExpression: Expression,
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
        exprList.add(singleExpression)
        return exprList.exprWrap(method)
    }

    @JvmStatic
    fun createExpression(element: PsiReferenceExpression): Expression? {
        return emojify.on(element)?.singletonField()
    }

    private fun PsiReferenceExpression.singletonField(): Expression? {
        return resolve().asInstance<PsiField>()?.takeIf {
            it.isStatic() && it.singletonField
        }?.let {
            identifier?.takeIf {
                it.textMatches("INSTANCE")
            }?.let {
                val exprList = exprList(it.expr(Consts.Emoji.SINGLETON_MAN_STANDING.toString()))
                exprList.exprWrap(this)
            }
        }
    }



}
