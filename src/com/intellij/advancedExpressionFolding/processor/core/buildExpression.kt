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
    abstract fun buildExpression(element: T, document: Document, synthetic: Boolean): Expression?

    @Suppress("UNCHECKED_CAST")
    private fun canProcess(element: PsiElement): Boolean =
        elementType.isInstance(element) && checkConditions(element as T)

    protected open fun checkConditions(element: T): Boolean = true

    @Suppress("UNCHECKED_CAST")
    fun tryBuildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? =
        if (canProcess(element)) buildExpression(element as T, document, synthetic) else null
}

class ForStatementBuilder : BuildExpression<PsiForStatement>(PsiForStatement::class.java) {
    override fun buildExpression(element: PsiForStatement, document: Document, synthetic: Boolean) =
        ForStatementExpressionExt.getForStatementExpression(element, document)
}

class ForEachStatementBuilder : BuildExpression<PsiForeachStatement>(PsiForeachStatement::class.java) {
    override fun buildExpression(element: PsiForeachStatement, document: Document, synthetic: Boolean): Expression? =
        LoopExt.getForEachStatementExpression(element)
}

class IfStatementBuilder : BuildExpression<PsiIfStatement>(PsiIfStatement::class.java) {
    override fun buildExpression(element: PsiIfStatement, document: Document, synthetic: Boolean) =
        IfExt.getIfExpression(element, document)
}

class WhileStatementBuilder : BuildExpression<PsiWhileStatement>(PsiWhileStatement::class.java) {
    override fun buildExpression(element: PsiWhileStatement, document: Document, synthetic: Boolean): Expression? =
        LoopExt.getWhileStatement(element)
}

class SemicolonBuilder : BuildExpression<PsiJavaToken>(PsiJavaToken::class.java) {
    override fun checkConditions(element: PsiJavaToken) =
        element.tokenType == JavaTokenType.SEMICOLON &&
                semicolonsCollapse &&
                !element.isWritable

    override fun buildExpression(element: PsiJavaToken, document: Document, synthetic: Boolean) =
        SemicolonExpression(
            element,
            element.textRange
        )
}

class TokenBuilder : BuildExpression<PsiJavaToken>(PsiJavaToken::class.java) {
    override fun checkConditions(element: PsiJavaToken) =
        element.tokenType != JavaTokenType.SEMICOLON || element.isWritable

    override fun buildExpression(element: PsiJavaToken, document: Document, synthetic: Boolean) =
        PsiJavaTokenExt.createExpression(element)
}

class CatchSectionBuilder : BuildExpression<PsiCatchSection>(PsiCatchSection::class.java) {
    override fun checkConditions(element: PsiCatchSection) =
        compactControlFlowSyntaxCollapse &&
                element.parameter != null &&
                element.lParenth != null &&
                element.rParenth != null

    override fun buildExpression(element: PsiCatchSection, document: Document, synthetic: Boolean): Expression? {
        val l = element.lParenth ?: return null
        val r = element.rParenth ?: return null
        return CompactControlFlowExpression(
            element,
            TextRange.create(l.textRange.startOffset, r.textRange.endOffset)
        )
    }
}

class DoWhileStatementBuilder : BuildExpression<PsiDoWhileStatement>(PsiDoWhileStatement::class.java) {
    override fun buildExpression(element: PsiDoWhileStatement, document: Document, synthetic: Boolean): Expression? =
        LoopExt.getDoWhileStatement(element)
}

class SwitchStatementBuilder : BuildExpression<PsiSwitchStatement>(PsiSwitchStatement::class.java) {
    override fun buildExpression(element: PsiSwitchStatement, document: Document, synthetic: Boolean): Expression? =
        IfExt.getSwitchStatement(element)
}

class SwitchExpressionBuilder : BuildExpression<PsiSwitchExpression>(PsiSwitchExpression::class.java) {
    override fun buildExpression(element: PsiSwitchExpression, document: Document, synthetic: Boolean): Expression? =
        IfExt.getSwitchStatement(element)
}

class ArrayAccessExpressionBuilder : BuildExpression<PsiArrayAccessExpression>(PsiArrayAccessExpression::class.java) {
    override fun checkConditions(element: PsiArrayAccessExpression) = getExpressionsCollapse

    override fun buildExpression(element: PsiArrayAccessExpression, document: Document, synthetic: Boolean) =
        PsiArrayAccessExpressionExt.getArrayAccessExpression(element, document)
}

class MethodCallExpressionBuilder : BuildExpression<PsiMethodCallExpression>(PsiMethodCallExpression::class.java) {
    override fun buildExpression(element: PsiMethodCallExpression, document: Document, synthetic: Boolean) =
        MethodCallExpressionExt.getMethodCallExpression(element, document)
}

class ReferenceExpressionBuilder : BuildExpression<PsiReferenceExpression>(PsiReferenceExpression::class.java) {
    override fun buildExpression(element: PsiReferenceExpression, document: Document, synthetic: Boolean) =
        ReferenceExpressionExt.getReferenceExpression(element)
}

class NewExpressionBuilder : BuildExpression<PsiNewExpression>(PsiNewExpression::class.java) {
    override fun buildExpression(element: PsiNewExpression, document: Document, synthetic: Boolean) =
        NewExpressionExt.getNewExpression(element, document)
}

class LiteralExpressionBuilder : BuildExpression<PsiLiteralExpression>(PsiLiteralExpression::class.java) {
    override fun buildExpression(element: PsiLiteralExpression, document: Document, synthetic: Boolean) =
        LiteralExpressionExt.getLiteralExpression(element)
}

class AssignmentExpressionBuilder : BuildExpression<PsiAssignmentExpression>(PsiAssignmentExpression::class.java) {
    override fun buildExpression(element: PsiAssignmentExpression, document: Document, synthetic: Boolean) =
        AssignmentExpressionExt.getAssignmentExpression(element, document)
}

class PolyadicExpressionBuilder : BuildExpression<PsiPolyadicExpression>(PsiPolyadicExpression::class.java) {
    override fun buildExpression(element: PsiPolyadicExpression, document: Document, synthetic: Boolean) =
        IfExt.getPolyadicExpression(element, document)
}

class BinaryExpressionBuilder : BuildExpression<PsiBinaryExpression>(PsiBinaryExpression::class.java) {
    override fun buildExpression(element: PsiBinaryExpression, document: Document, synthetic: Boolean) =
        BinaryExpressionExt.getBinaryExpression(element, document)
}

class ConditionalExpressionBuilder : BuildExpression<PsiConditionalExpression>(PsiConditionalExpression::class.java) {
    override fun buildExpression(element: PsiConditionalExpression, document: Document, synthetic: Boolean) =
        IfExt.getConditionalExpression(element, document)
}

class PrefixExpressionBuilder : BuildExpression<PsiPrefixExpression>(PsiPrefixExpression::class.java) {
    override fun buildExpression(element: PsiPrefixExpression, document: Document, synthetic: Boolean) =
        PrefixExpressionExt.getPrefixExpression(element, document)
}

class ParenthesizedExpressionBuilder :
    BuildExpression<PsiParenthesizedExpression>(PsiParenthesizedExpression::class.java) {
    override fun checkConditions(element: PsiParenthesizedExpression) = castExpressionsCollapse

    override fun buildExpression(
        element: PsiParenthesizedExpression,
        document: Document,
        synthetic: Boolean
    ): Expression? {
        val expression = element.expression
        if (expression is PsiTypeCastExpression) {
            val typeCast = PsiTypeCastExpressionExt.getTypeCastExpression(expression, document)
            if (typeCast != null) {
                return TypeCast(
                    element,
                    element.textRange,
                    typeCast.getObject()
                )
            }
        }
        if (expression != null) {
            return CacheExt.getExpression(expression, document, synthetic)
        }
        return null
    }
}

class TypeCastExpressionBuilder : BuildExpression<PsiTypeCastExpression>(PsiTypeCastExpression::class.java) {
    override fun checkConditions(element: PsiTypeCastExpression) = castExpressionsCollapse

    override fun buildExpression(element: PsiTypeCastExpression, document: Document, synthetic: Boolean) =
        PsiTypeCastExpressionExt.getTypeCastExpression(element, document)
}

class DeclarationStatementBuilder : BuildExpression<PsiDeclarationStatement>(PsiDeclarationStatement::class.java) {
    override fun buildExpression(element: PsiDeclarationStatement, document: Document, synthetic: Boolean) =
        PsiDeclarationStatementExt.createExpression(element)
}

class VariableBuilder : BuildExpression<PsiVariable>(PsiVariable::class.java) {
    override fun checkConditions(element: PsiVariable) = varExpressionsCollapse &&
            (element.parent is PsiDeclarationStatement || element.parent is PsiForeachStatement)

    override fun buildExpression(element: PsiVariable, document: Document, synthetic: Boolean) =
        PsiVariableExt.getVariableDeclaration(element)
}

class CodeBlockBuilder : BuildExpression<PsiCodeBlock>(PsiCodeBlock::class.java) {
    override fun buildExpression(element: PsiCodeBlock, document: Document, synthetic: Boolean) =
        PsiCodeBlockExt.getCodeBlockExpression(element)
}

class ClassBuilder : BuildExpression<PsiClass>(PsiClass::class.java) {
    override fun buildExpression(element: PsiClass, document: Document, synthetic: Boolean) =
        PsiClassExt.createExpression(element)
}

class FieldBuilder : BuildExpression<PsiField>(PsiField::class.java) {
    override fun buildExpression(element: PsiField, document: Document, synthetic: Boolean) =
        PsiFieldExt.createExpression(element, document)
}

class ParameterBuilder : BuildExpression<PsiParameter>(PsiParameter::class.java) {
    override fun buildExpression(element: PsiParameter, document: Document, synthetic: Boolean) =
        PsiMethodExt.createExpression(element, document)
}

class RecordComponentBuilder : BuildExpression<PsiRecordComponent>(PsiRecordComponent::class.java) {
    override fun buildExpression(element: PsiRecordComponent, document: Document, synthetic: Boolean) =
        PsiFieldExt.createExpression(element)
}

class MethodBuilder : BuildExpression<PsiMethod>(PsiMethod::class.java) {
    override fun buildExpression(element: PsiMethod, document: Document, synthetic: Boolean) =
        PsiMethodExt.createExpression(element, document)
}

class KeywordBuilder : BuildExpression<PsiKeyword>(PsiKeyword::class.java) {
    override fun buildExpression(element: PsiKeyword, document: Document, synthetic: Boolean) =
        PsiKeywordExt.createExpression(element)
}

class TryStatementBuilder : BuildExpression<PsiTryStatement>(PsiTryStatement::class.java) {
    override fun buildExpression(element: PsiTryStatement, document: Document, synthetic: Boolean) =
        PsiTryStatementExt.createExpression(element)

}

fun tryBuildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? {
    return ExpressionBuilderManager.Companion.expressionBuilders
        .firstNotNullOfOrNull {
            it.tryBuildExpression(element, document, synthetic)
        }
}
