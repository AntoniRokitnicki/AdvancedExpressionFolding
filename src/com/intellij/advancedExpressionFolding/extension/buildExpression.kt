package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.SemicolonExpression
import com.intellij.advancedExpressionFolding.expression.TypeCast
import com.intellij.advancedExpressionFolding.extension.methodcall.MethodCallExpressionExt
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

abstract class BuildExpression<T : PsiElement> : BaseExtension() {
    abstract val elementType: Class<T>

    abstract fun buildExpression(element: T, document: Document, synthetic: Boolean): Expression?

    @Suppress("UNCHECKED_CAST")
    private fun canProcess(element: PsiElement): Boolean =
        elementType.isInstance(element) && checkConditions(element as T)

    protected open fun checkConditions(element: T): Boolean = true

    @Suppress("UNCHECKED_CAST")
    fun tryBuildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? =
        if (canProcess(element)) buildExpression(element as T, document, synthetic) else null

}

class ForStatementBuilder : BuildExpression<PsiForStatement>() {
    override val elementType = PsiForStatement::class.java

    override fun buildExpression(element: PsiForStatement, document: Document, synthetic: Boolean) =
        ForStatementExpressionExt.getForStatementExpression(element, document)
}

class ForeachStatementBuilder : BuildExpression<PsiForeachStatement>() {
    override val elementType = PsiForeachStatement::class.java

    override fun buildExpression(element: PsiForeachStatement, document: Document, synthetic: Boolean): Expression? =
        LoopExt.getForeachStatementExpression(element)
}

class IfStatementBuilder : BuildExpression<PsiIfStatement>() {
    override val elementType = PsiIfStatement::class.java

    override fun buildExpression(element: PsiIfStatement, document: Document, synthetic: Boolean) =
        IfExt.getIfExpression(element, document)
}

class WhileStatementBuilder : BuildExpression<PsiWhileStatement>() {
    override val elementType = PsiWhileStatement::class.java

    override fun buildExpression(element: PsiWhileStatement, document: Document, synthetic: Boolean): Expression? =
        LoopExt.getWhileStatement(element)
}

class SemicolonBuilder : BuildExpression<PsiJavaToken>() {
    override val elementType = PsiJavaToken::class.java

    override fun checkConditions(element: PsiJavaToken) =
        element.tokenType == JavaTokenType.SEMICOLON &&
                semicolonsCollapse &&
                !element.isWritable

    override fun buildExpression(element: PsiJavaToken, document: Document, synthetic: Boolean) =
        SemicolonExpression(element, element.textRange)
}

class TokenBuilder : BuildExpression<PsiJavaToken>() {
    override val elementType = PsiJavaToken::class.java

    override fun checkConditions(element: PsiJavaToken) =
        element.tokenType != JavaTokenType.SEMICOLON || element.isWritable

    override fun buildExpression(element: PsiJavaToken, document: Document, synthetic: Boolean) =
        TokenExt.createExpression(element)
}

class CatchSectionBuilder : BuildExpression<PsiCatchSection>() {
    override val elementType = PsiCatchSection::class.java

    override fun checkConditions(element: PsiCatchSection) =
        compactControlFlowSyntaxCollapse &&
                element.parameter != null &&
                element.lParenth != null &&
                element.rParenth != null

    override fun buildExpression(element: PsiCatchSection, document: Document, synthetic: Boolean) =
        CompactControlFlowExpression(
            element,
            TextRange.create(element.lParenth!!.textRange.startOffset, element.rParenth!!.textRange.endOffset)
        )
}

class DoWhileStatementBuilder : BuildExpression<PsiDoWhileStatement>() {
    override val elementType = PsiDoWhileStatement::class.java

    override fun buildExpression(element: PsiDoWhileStatement, document: Document, synthetic: Boolean): Expression? =
        LoopExt.getDoWhileStatement(element)
}

class SwitchStatementBuilder : BuildExpression<PsiSwitchStatement>() {
    override val elementType = PsiSwitchStatement::class.java

    override fun buildExpression(element: PsiSwitchStatement, document: Document, synthetic: Boolean): Expression? =
        IfExt.getSwitchStatement(element)
}

class ArrayAccessExpressionBuilder : BuildExpression<PsiArrayAccessExpression>() {
    override val elementType = PsiArrayAccessExpression::class.java

    override fun checkConditions(element: PsiArrayAccessExpression) =
        getExpressionsCollapse

    override fun buildExpression(element: PsiArrayAccessExpression, document: Document, synthetic: Boolean) =
        PsiArrayAccessExpressionExt.getArrayAccessExpression(element, document)
}

class MethodCallExpressionBuilder : BuildExpression<PsiMethodCallExpression>() {
    override val elementType = PsiMethodCallExpression::class.java

    override fun buildExpression(element: PsiMethodCallExpression, document: Document, synthetic: Boolean) =
        MethodCallExpressionExt.getMethodCallExpression(element, document)
}

class ReferenceExpressionBuilder : BuildExpression<PsiReferenceExpression>() {
    override val elementType = PsiReferenceExpression::class.java

    override fun buildExpression(element: PsiReferenceExpression, document: Document, synthetic: Boolean) =
        ReferenceExpressionExt.getReferenceExpression(element)
}

class NewExpressionBuilder : BuildExpression<PsiNewExpression>() {
    override val elementType = PsiNewExpression::class.java

    override fun buildExpression(element: PsiNewExpression, document: Document, synthetic: Boolean) =
        NewExpressionExt.getNewExpression(element, document)
}

class LiteralExpressionBuilder : BuildExpression<PsiLiteralExpression>() {
    override val elementType = PsiLiteralExpression::class.java

    override fun buildExpression(element: PsiLiteralExpression, document: Document, synthetic: Boolean) =
        LiteralExpressionExt.getLiteralExpression(element)
}

class AssignmentExpressionBuilder : BuildExpression<PsiAssignmentExpression>() {
    override val elementType = PsiAssignmentExpression::class.java

    override fun buildExpression(element: PsiAssignmentExpression, document: Document, synthetic: Boolean) =
        AssignmentExpressionExt.getAssignmentExpression(element, document)
}

class PolyadicExpressionBuilder : BuildExpression<PsiPolyadicExpression>() {
    override val elementType = PsiPolyadicExpression::class.java

    override fun buildExpression(element: PsiPolyadicExpression, document: Document, synthetic: Boolean) =
        IfExt.getPolyadicExpression(element, document)
}

class BinaryExpressionBuilder : BuildExpression<PsiBinaryExpression>() {
    override val elementType = PsiBinaryExpression::class.java

    override fun buildExpression(element: PsiBinaryExpression, document: Document, synthetic: Boolean) =
        BinaryExpressionExt.getBinaryExpression(element, document)
}

class ConditionalExpressionBuilder : BuildExpression<PsiConditionalExpression>() {
    override val elementType = PsiConditionalExpression::class.java

    override fun buildExpression(element: PsiConditionalExpression, document: Document, synthetic: Boolean) =
        IfExt.getConditionalExpression(element, document)
}

class PrefixExpressionBuilder : BuildExpression<PsiPrefixExpression>() {
    override val elementType = PsiPrefixExpression::class.java

    override fun buildExpression(element: PsiPrefixExpression, document: Document, synthetic: Boolean) =
        PrefixExpressionExt.getPrefixExpression(element, document)
}

class ParenthesizedExpressionBuilder : BuildExpression<PsiParenthesizedExpression>() {
    override val elementType = PsiParenthesizedExpression::class.java

    override fun checkConditions(element: PsiParenthesizedExpression) =
        castExpressionsCollapse

    override fun buildExpression(
        element: PsiParenthesizedExpression,
        document: Document,
        synthetic: Boolean
    ): Expression? {
        val expression = element.expression
        if (expression is PsiTypeCastExpression) {
            val typeCast = PsiTypeCastExpressionExt.getTypeCastExpression(expression, document)
            if (typeCast != null) {
                return TypeCast(element, element.textRange, typeCast.getObject())
            }
        }
        if (expression != null) {
            return CacheExt.getExpression(expression, document, synthetic)
        }
        return null
    }
}

class TypeCastExpressionBuilder : BuildExpression<PsiTypeCastExpression>() {
    override val elementType = PsiTypeCastExpression::class.java

    override fun checkConditions(element: PsiTypeCastExpression) =
        castExpressionsCollapse

    override fun buildExpression(element: PsiTypeCastExpression, document: Document, synthetic: Boolean) =
        PsiTypeCastExpressionExt.getTypeCastExpression(element, document)
}

class DeclarationStatementBuilder : BuildExpression<PsiDeclarationStatement>() {
    override val elementType = PsiDeclarationStatement::class.java

    override fun buildExpression(element: PsiDeclarationStatement, document: Document, synthetic: Boolean) =
        PsiDeclarationStatementEx.createExpression(element)
}

class VariableBuilder : BuildExpression<PsiVariable>() {
    override val elementType = PsiVariable::class.java

    override fun checkConditions(element: PsiVariable) =
        varExpressionsCollapse &&
                (element.parent is PsiDeclarationStatement || element.parent is PsiForeachStatement)

    override fun buildExpression(element: PsiVariable, document: Document, synthetic: Boolean) =
        PsiVariableExt.getVariableDeclaration(element)
}

class CodeBlockBuilder : BuildExpression<PsiCodeBlock>() {
    override val elementType = PsiCodeBlock::class.java

    override fun buildExpression(element: PsiCodeBlock, document: Document, synthetic: Boolean) =
        PsiCodeBlockExt.getCodeBlockExpression(element)
}

class ClassBuilder : BuildExpression<PsiClass>() {
    override val elementType = PsiClass::class.java

    override fun buildExpression(element: PsiClass, document: Document, synthetic: Boolean) =
        PsiClassExt.createExpression(element)
}

class FieldBuilder : BuildExpression<PsiField>() {
    override val elementType = PsiField::class.java

    override fun buildExpression(element: PsiField, document: Document, synthetic: Boolean) =
        NullableExt.createExpression(element, document)
}

class ParameterBuilder : BuildExpression<PsiParameter>() {
    override val elementType = PsiParameter::class.java

    override fun buildExpression(element: PsiParameter, document: Document, synthetic: Boolean) =
        NullableExt.createExpression(element, document)
}

class RecordComponentBuilder : BuildExpression<PsiRecordComponent>() {
    override val elementType = PsiRecordComponent::class.java

    override fun buildExpression(element: PsiRecordComponent, document: Document, synthetic: Boolean) =
        NullableExt.createExpression(element)
}

class MethodBuilder : BuildExpression<PsiMethod>() {
    override val elementType = PsiMethod::class.java

    override fun buildExpression(element: PsiMethod, document: Document, synthetic: Boolean) =
        NullableExt.createExpression(element, document)
}

class KeywordBuilder : BuildExpression<PsiKeyword>() {
    override val elementType = PsiKeyword::class.java

    override fun buildExpression(element: PsiKeyword, document: Document, synthetic: Boolean) =
        KeywordExt.createExpression(element)
}

fun tryBuildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? {
    return ExpressionBuilderManager.expressionBuilders
        .firstNotNullOfOrNull {
            it.tryBuildExpression(element, document, synthetic)
        }
}