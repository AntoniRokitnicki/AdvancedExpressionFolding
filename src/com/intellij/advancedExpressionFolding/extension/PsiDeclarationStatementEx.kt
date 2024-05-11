package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.DestructuringExpression
import com.intellij.advancedExpressionFolding.expression.custom.WrapperExpression
import com.intellij.psi.*

object PsiDeclarationStatementEx : BaseExtension() {

    @JvmStatic
    fun createExpression(
        element: PsiDeclarationStatement
    ): Expression? {
        if (destructuring) {
            return createCollectionDestructuringExpression(element)
        }
        return null
    }


    private fun createCollectionDestructuringExpression(element: PsiDeclarationStatement): WrapperExpression? {
        val variable = element.asVariable()
        val initialCollection = variable?.let {
            it.asArray() ?: it.asList()
        }?.takeIf {
            it.index() == 0
        } ?: return null

        val list = generateSequence(Pair(element, initialCollection)) { (statement, collection) ->
            val nextStatement = statement.nextStatement()
            nextStatement?.findNextCollection(collection)?.let {
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
                else -> transformMiddleElement(statement, identifier, index)
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

    without val:
    Data a1 = data.getArray()[0];
    into
    Data (a1,
    @formatter:on
     */
    private fun transformFirstElement(
        statement: PsiDeclarationStatement,
        identifier: PsiIdentifier,
        list: List<Pair<PsiDeclarationStatement, PsiExpression>>
    ): DestructuringExpression {
        val methodCallExclusion =
            DestructuringExpression(
                statement,
                (identifier.end()..statement.end()),
                ", ",
                null,
                "1-methodCallExclusion"
            )
        return if (varExpressionsCollapse) {
            val notFinal = list.mapNotNull {
                it.first.asVariable()
            }.any {
                !Helper.calculateIfFinal(it)
            }
            val varType = when (notFinal) {
                true -> "var"
                false -> "val"
            }
            DestructuringExpression(
                statement,
                (statement.start()..identifier.start()),
                "$varType (",
                methodCallExclusion,
                "1-prefix-$varType"
            )
        } else {
            val variable = statement.asVariable()!!
            val whiteSpaceAfterType = variable.typeElement?.nextSibling
                ?: variable.children[0] // should never happen
            DestructuringExpression(
                whiteSpaceAfterType,
                (whiteSpaceAfterType.start()..whiteSpaceAfterType.end()),
                " (",
                methodCallExclusion,
                "1-prefix"
            )
        }
    }

    /**
    @formatter:off
    <ENTER>Data a3 = data.getArray()[1];
    into
                a3,
    @formatter:on
     */
    private fun transformMiddleElement(
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
        collection: PsiExpression,
        identifier: PsiIdentifier,
        index: Int
    ): DestructuringExpression {
        val typeSpecificExclusion = if (collection is PsiMethodCallExpression) {
            val start = collection.methodExpression.qualifierExpression!!.end()
            DestructuringExpression(
                statement,
                (start..collection.end()),
                "",
                null,
                "$index-get-call"
            )
        } else {
            DestructuringExpression(
                statement,
                (collection.children[1].start()..collection.children[3].end()),
                "",
                null,
                "$index-indexExclusion"
            )
        }
        val closingBracket = DestructuringExpression(
            statement,
            (identifier.end()..identifier.end() + 1),
            ") ",
            typeSpecificExclusion,
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

    private fun PsiDeclarationStatement.findNextCollection(
        previousCollection: PsiExpression
    ): PsiExpression? {
        fun hasSequentialIndexWith(vararg indexes: Int?): Boolean =
            indexes.filterNotNull().zipWithNext().all { (a, b) -> a + 1 == b }

        val variable = asVariable()
        return variable?.let { it.asArray() ?: it.asList() }?.takeIf {
            it.hasSameParent(previousCollection) &&
                    hasSequentialIndexWith(
                        previousCollection.index(),
                        it.index()
                    )
        }
    }

    private fun PsiVariable.asList(): PsiMethodCallExpression? {
        return initializer.asInstance<PsiMethodCallExpression>().takeIf {
            it?.methodExpression?.referenceName == "get"
        }
    }

    private fun PsiVariable.asArray(): PsiArrayAccessExpression? =
        this.initializer.asInstance<PsiArrayAccessExpression>()


    private fun PsiExpression.index(): Int? = when (this) {
        is PsiArrayAccessExpression -> indexExpression.asInstance<PsiLiteralExpression>()?.value.asInstance<Int>()

        is PsiMethodCallExpression -> argumentList.expressions.singleOrNull()
            .asInstance<PsiLiteralExpression>()?.value.asInstance<Int>()

        else -> null
    }

    private fun PsiExpression.hasSameParent(other: PsiExpression): Boolean = when {
        this is PsiMethodCallExpression && other is PsiMethodCallExpression ->
            methodExpression.text == other.methodExpression.text

        this is PsiArrayAccessExpression && other is PsiArrayAccessExpression ->
            arrayExpression.text == other.arrayExpression.text

        else -> false
    }

    private fun PsiDeclarationStatement.nextStatement(): PsiDeclarationStatement? =
        this.realNextSibling().asInstance<PsiDeclarationStatement>()

    private fun PsiDeclarationStatement.asVariable(): PsiVariable? =
        this.declaredElements.singleOrNull().asInstance<PsiVariable>()

}




