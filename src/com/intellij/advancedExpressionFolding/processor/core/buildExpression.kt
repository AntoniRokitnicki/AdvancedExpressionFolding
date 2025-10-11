package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.SemicolonExpression
import com.intellij.advancedExpressionFolding.expression.operation.basic.TypeCast
import com.intellij.advancedExpressionFolding.processor.cache.CacheExt
import com.intellij.advancedExpressionFolding.processor.controlflow.ForStatementExpressionExt
import com.intellij.advancedExpressionFolding.processor.controlflow.IfExt
import com.intellij.advancedExpressionFolding.processor.controlflow.LoopExt
import com.intellij.advancedExpressionFolding.processor.controlflow.PsiTryStatementExt
import com.intellij.advancedExpressionFolding.processor.declaration.*
import com.intellij.advancedExpressionFolding.processor.expression.*
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallExpressionExt
import com.intellij.advancedExpressionFolding.processor.reference.NewExpressionExt
import com.intellij.advancedExpressionFolding.processor.reference.ReferenceExpressionExt
import com.intellij.advancedExpressionFolding.processor.token.PsiJavaTokenExt
import com.intellij.advancedExpressionFolding.processor.token.PsiKeywordExt
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

abstract class BuildExpression<T : PsiElement>(
    private val elementType: Class<T>
) : BaseExtension() {

    context(doc: Document, isSynthetic: Boolean)
    protected abstract fun buildExpression(element: T): Expression?

    protected open fun checkConditions(element: T): Boolean = true

    @Suppress("UNCHECKED_CAST")
    context(doc: Document, isSynthetic: Boolean)
    private fun canProcess(element: PsiElement): Boolean =
        elementType.isInstance(element) && checkConditions(element as T)

    context(doc: Document, isSynthetic: Boolean)
    protected fun build(element: T): Expression? = buildExpression(element)

    fun buildExpression(element: T, document: Document, synthetic: Boolean): Expression? =
        with(document) {
            with(synthetic) {
                build(element)
            }
        }

    @Suppress("UNCHECKED_CAST")
    context(doc: Document, isSynthetic: Boolean)
    fun tryBuildExpression(element: PsiElement): Expression? =
        if (canProcess(element)) build(element as T) else null

    fun tryBuildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? =
        with(document) {
            with(synthetic) {
                tryBuildExpression(element)
            }
        }
}

class ForStatementBuilder : BuildExpression<PsiForStatement>(PsiForStatement::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiForStatement) =
        ForStatementExpressionExt.getForStatementExpression(element, doc)
}

class ForEachStatementBuilder : BuildExpression<PsiForeachStatement>(PsiForeachStatement::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiForeachStatement): Expression? =
        LoopExt.getForEachStatementExpression(element)
}

class IfStatementBuilder : BuildExpression<PsiIfStatement>(PsiIfStatement::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiIfStatement) =
        IfExt.getIfExpression(element, doc)
}

class WhileStatementBuilder : BuildExpression<PsiWhileStatement>(PsiWhileStatement::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiWhileStatement): Expression? =
        LoopExt.getWhileStatement(element)
}

class SemicolonBuilder : BuildExpression<PsiJavaToken>(PsiJavaToken::class.java) {
    override fun checkConditions(element: PsiJavaToken) =
        element.tokenType == JavaTokenType.SEMICOLON &&
                semicolonsCollapse &&
                !element.isWritable

    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiJavaToken) =
        SemicolonExpression(
            element,
            element.textRange
        )
}

class TokenBuilder : BuildExpression<PsiJavaToken>(PsiJavaToken::class.java) {
    override fun checkConditions(element: PsiJavaToken) =
        element.tokenType != JavaTokenType.SEMICOLON || element.isWritable

    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiJavaToken) =
        PsiJavaTokenExt.createExpression(element)
}

class CatchSectionBuilder : BuildExpression<PsiCatchSection>(PsiCatchSection::class.java) {
    override fun checkConditions(element: PsiCatchSection) =
        compactControlFlowSyntaxCollapse &&
                element.parameter != null &&
                element.lParenth != null &&
                element.rParenth != null

    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiCatchSection): Expression? {
        val l = element.lParenth ?: return null
        val r = element.rParenth ?: return null
        return CompactControlFlowExpression(
            element,
            TextRange.create(l.textRange.startOffset, r.textRange.endOffset)
        )
    }
}

class DoWhileStatementBuilder : BuildExpression<PsiDoWhileStatement>(PsiDoWhileStatement::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiDoWhileStatement): Expression? =
        LoopExt.getDoWhileStatement(element)
}

class SwitchStatementBuilder : BuildExpression<PsiSwitchStatement>(PsiSwitchStatement::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiSwitchStatement): Expression? =
        IfExt.getSwitchStatement(element)
}

class ArrayAccessExpressionBuilder : BuildExpression<PsiArrayAccessExpression>(PsiArrayAccessExpression::class.java) {
    override fun checkConditions(element: PsiArrayAccessExpression) = getExpressionsCollapse

    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiArrayAccessExpression) =
        PsiArrayAccessExpressionExt.getArrayAccessExpression(element, doc)
}

class MethodCallExpressionBuilder : BuildExpression<PsiMethodCallExpression>(PsiMethodCallExpression::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiMethodCallExpression) =
        MethodCallExpressionExt.getMethodCallExpression(element, doc)
}

class ReferenceExpressionBuilder : BuildExpression<PsiReferenceExpression>(PsiReferenceExpression::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiReferenceExpression) =
        ReferenceExpressionExt.getReferenceExpression(element)
}

class NewExpressionBuilder : BuildExpression<PsiNewExpression>(PsiNewExpression::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiNewExpression) =
        NewExpressionExt.getNewExpression(element, doc)
}

class LiteralExpressionBuilder : BuildExpression<PsiLiteralExpression>(PsiLiteralExpression::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiLiteralExpression) =
        LiteralExpressionExt.getLiteralExpression(element)
}

class AssignmentExpressionBuilder : BuildExpression<PsiAssignmentExpression>(PsiAssignmentExpression::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiAssignmentExpression) =
        AssignmentExpressionExt.getAssignmentExpression(element, doc)
}

class PolyadicExpressionBuilder : BuildExpression<PsiPolyadicExpression>(PsiPolyadicExpression::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiPolyadicExpression) =
        IfExt.getPolyadicExpression(element, doc)
}

class BinaryExpressionBuilder : BuildExpression<PsiBinaryExpression>(PsiBinaryExpression::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiBinaryExpression) =
        BinaryExpressionExt.getBinaryExpression(element, doc)
}

class ConditionalExpressionBuilder : BuildExpression<PsiConditionalExpression>(PsiConditionalExpression::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiConditionalExpression) =
        IfExt.getConditionalExpression(element, doc)
}

class PrefixExpressionBuilder : BuildExpression<PsiPrefixExpression>(PsiPrefixExpression::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiPrefixExpression) =
        PrefixExpressionExt.getPrefixExpression(element, doc)
}

class ParenthesizedExpressionBuilder :
    BuildExpression<PsiParenthesizedExpression>(PsiParenthesizedExpression::class.java) {
    override fun checkConditions(element: PsiParenthesizedExpression) = castExpressionsCollapse

    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(
        element: PsiParenthesizedExpression,
    ): Expression? {
        val expression = element.expression
        if (expression is PsiTypeCastExpression) {
            val typeCast = PsiTypeCastExpressionExt.getTypeCastExpression(expression, doc)
            if (typeCast != null) {
                return TypeCast(
                    element,
                    element.textRange,
                    typeCast.getObject()
                )
            }
        }
        if (expression != null) {
            return CacheExt.getExpression(expression)
        }
        return null
    }
}

class TypeCastExpressionBuilder : BuildExpression<PsiTypeCastExpression>(PsiTypeCastExpression::class.java) {
    override fun checkConditions(element: PsiTypeCastExpression) = castExpressionsCollapse

    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiTypeCastExpression) =
        PsiTypeCastExpressionExt.getTypeCastExpression(element, doc)
}

class DeclarationStatementBuilder : BuildExpression<PsiDeclarationStatement>(PsiDeclarationStatement::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiDeclarationStatement) =
        PsiDeclarationStatementExt.createExpression(element)
}

class VariableBuilder : BuildExpression<PsiVariable>(PsiVariable::class.java) {
    override fun checkConditions(element: PsiVariable) = varExpressionsCollapse &&
            (element.parent is PsiDeclarationStatement || element.parent is PsiForeachStatement)

    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiVariable) =
        PsiVariableExt.getVariableDeclaration(element)
}

class CodeBlockBuilder : BuildExpression<PsiCodeBlock>(PsiCodeBlock::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiCodeBlock) =
        PsiCodeBlockExt.getCodeBlockExpression(element)
}

class ClassBuilder : BuildExpression<PsiClass>(PsiClass::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiClass) =
        PsiClassExt.createExpression(element)
}

class FieldBuilder : BuildExpression<PsiField>(PsiField::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiField) =
        PsiFieldExt.createExpression(element)
}

class ParameterBuilder : BuildExpression<PsiParameter>(PsiParameter::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiParameter) =
        PsiMethodExt.createExpression(element)
}

class RecordComponentBuilder : BuildExpression<PsiRecordComponent>(PsiRecordComponent::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiRecordComponent) =
        PsiFieldExt.createExpression(element)
}

class MethodBuilder : BuildExpression<PsiMethod>(PsiMethod::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiMethod) =
        PsiMethodExt.createExpression(element)
}

class KeywordBuilder : BuildExpression<PsiKeyword>(PsiKeyword::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiKeyword) =
        PsiKeywordExt.createExpression(element)
}

class TryStatementBuilder : BuildExpression<PsiTryStatement>(PsiTryStatement::class.java) {
    context(doc: Document, isSynthetic: Boolean)
    override fun buildExpression(element: PsiTryStatement) =
        PsiTryStatementExt.createExpression(element)

}

context(doc: Document, isSynthetic: Boolean)
fun tryBuildExpression(element: PsiElement): Expression? {
    return ExpressionBuilderManager.Companion.expressionBuilders
        .firstNotNullOfOrNull {
            it.tryBuildExpression(element)
        }
}

fun tryBuildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? =
    with(document) {
        with(synthetic) {
            tryBuildExpression(element)
        }
    }
