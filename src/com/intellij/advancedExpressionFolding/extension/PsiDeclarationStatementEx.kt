package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.DestructuringExpression
import com.intellij.advancedExpressionFolding.expression.custom.WrapperExpression
import com.intellij.openapi.editor.Document
import com.intellij.psi.*

object PsiDeclarationStatementEx : BaseExtension() {

    @JvmStatic
    fun createExpression(
        element: PsiDeclarationStatement,
        document: Document
    ): Expression? {
        if (destructuring) {
            return createDestructuringExpression(element)
        }
        return null
    }

    private fun createDestructuringExpression(element: PsiDeclarationStatement): WrapperExpression? {
        val initialArray = element.asVariable()?.asArray().takeIf {
            it?.index() == 0
        } ?: return null

        val list = generateSequence(Pair(element, initialArray)) { (statement, array) ->
            val nextStatement = statement.nextStatement()
            nextStatement?.getNextArray(array)?.let {
                Pair(nextStatement, it)
            }
        }.toList()
        return list.takeIf {
            it.size >= 2
        }?.mapIndexed { index, (statement, array) ->
            val identifier = statement.asVariable()?.nameIdentifier ?: return null
            when (index) {
                0 -> transformFirstElement(statement, identifier, list)
                list.lastIndex -> transformLastElement(statement, array, identifier, index)
                else -> transformMiddleElements(statement, identifier, index)
            }
        }?.let {
            WrapperExpression(element.parent, element.parent.textRange, it)
        }
    }

    /**
    @formatter:off
    Data a1 = data.getArray()[0];
    into
    val (a1,
    @formatter:on
     */
    private fun transformFirstElement(
        statement: PsiDeclarationStatement,
        identifier: PsiIdentifier,
        list: List<Pair<PsiDeclarationStatement, PsiArrayAccessExpression>>
    ): DestructuringExpression {
        val methodCallExclusion =
            DestructuringExpression(
                statement,
                (identifier.end()..statement.end()),
                ", ",
                null,
                "1-methodCallExclusion"
            )
        if (varExpressionsCollapse) {
            val notFinal = list.mapNotNull {
                it.first.asVariable()
            }.any {
                !Helper.calculateIfFinal(it)
            }
            val varType = when (notFinal) {
                true -> "var"
                false -> "val"
            }
            return DestructuringExpression(
                statement,
                (statement.start()..identifier.start()),
                "$varType (",
                methodCallExclusion,
                "1-prefix-$varType"
            )
        }
        val variable = statement.asVariable()!!
        val whiteSpaceAfterType = variable.typeElement?.nextSibling
            ?: variable.children[2] // should never happen
        return DestructuringExpression(
            whiteSpaceAfterType,
            (whiteSpaceAfterType.start()..whiteSpaceAfterType.end()),
            " (",
            methodCallExclusion,
            "1-prefix"
        )
    }

    /**
    @formatter:off
    <ENTER>Data a3 = data.getArray()[1];
    into
                a3,
    @formatter:on
     */
    private fun transformMiddleElements(
        statement: PsiDeclarationStatement,
        identifier: PsiIdentifier,
        index: Int
    ): DestructuringExpression {
        val methodCallExclusion =
            DestructuringExpression(
                statement,
                (identifier.end()..statement.end()),
                ", ",
                null,
                "$index-methodCallExclusion"
            )
        return DestructuringExpression(
            statement,
            (statement.prevSibling.start()..identifier.start()),
            "",
            methodCallExclusion,
            "$index-prefix"
        )
    }

    /**
    @formatter:off
    <ENTER>Data a2 = data.getArray()[2];
    into
    a2) = data.getArray();
    @formatter:on
     */
    private fun transformLastElement(
        statement: PsiDeclarationStatement,
        array: PsiArrayAccessExpression,
        identifier: PsiIdentifier,
        index: Int
    ): DestructuringExpression {
        val indexExclusion = DestructuringExpression(
            statement,
            (array.children[1].start()..array.children[3].end()),
            "",
            null,
            "$index-indexExclusion"
        )
        val closingBracket = DestructuringExpression(
            statement,
            (identifier.end()..identifier.end() + 1),
            ") ",
            indexExclusion,
            "$index-closingBracket"
        )
        return DestructuringExpression(
            statement,
            (statement.prevSibling.start()..identifier.start()),
            "",
            closingBracket,
            "$index-prefix"
        )
    }

    private fun PsiDeclarationStatement.getNextArray(
        previousArray: PsiArrayAccessExpression
    ): PsiArrayAccessExpression? {
        fun PsiArrayAccessExpression.hasSequentialIndexWith(nextArray: PsiArrayAccessExpression): Boolean =
            sequenceOf(this.index(), nextArray.index()).filterNotNull().zipWithNext { a, b -> a + 1 == b }
                .singleOrNull() == true

        return this.asVariable()?.asArray()?.takeIf {
            it.hasSameParentMethodCall(previousArray) && previousArray.hasSequentialIndexWith(it)
        }
    }

    private fun PsiArrayAccessExpression.hasSameParentMethodCall(second: PsiArrayAccessExpression) =
        this.arrayExpression.text == second.arrayExpression.text

    private fun PsiDeclarationStatement.nextStatement(): PsiDeclarationStatement? =
        this.realNextSibling().asInstance<PsiDeclarationStatement>()

    private fun PsiArrayAccessExpression.index(): Int? =
        this.indexExpression.asInstance<PsiLiteralExpression>()?.value.asInstance<Int>()

    private fun PsiVariable.asArray(): PsiArrayAccessExpression? =
        this.initializer.asInstance<PsiArrayAccessExpression>()

    private fun PsiDeclarationStatement.asVariable(): PsiVariable? =
        this.declaredElements.singleOrNull().asInstance<PsiVariable>()

}


