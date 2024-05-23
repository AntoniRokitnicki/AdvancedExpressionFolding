package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.NullAnnotationExpression
import com.intellij.advancedExpressionFolding.expression.custom.WrapperExpression
import com.intellij.psi.*

object ExperimentalExt : BaseExtension() {

    fun createSingleExpressionFunctions(method: PsiMethod, other: NullAnnotationExpression?): Expression? {
        val statement = method.body?.statements?.singleOrNull()?.takeIf {
            method.text.length < 145
        } ?: return other

        return statement.asInstance<PsiReturnStatement>()?.let {
            sefReturn(it, other, method)
        } ?: sefNoReturn(statement, other, method) ?: other
    }

    private fun sefNoReturn(
        statement: PsiStatement,
        other: NullAnnotationExpression?,
        method: PsiMethod
    ): WrapperExpression? {
        val body = method.body!!
        val whitespaces = body.children.filter {
            it.isWhitespace()
        }.map {
            it.expr(" ")
        }
        val endingSemicolon = statement.children.lastOrNull().asInstance<PsiJavaToken>()//;
        val exprList = elemList(endingSemicolon)
        exprList.addAll(whitespaces)

        exprList.add(other)
        return exprList.exprWrap(method)
    }

    private fun sefReturn(
        statement: PsiReturnStatement,
        other: NullAnnotationExpression?,
        method: PsiMethod
    ): WrapperExpression? {
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

        exprList.add(other)
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
