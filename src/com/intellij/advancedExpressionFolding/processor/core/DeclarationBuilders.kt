package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.processor.declaration.PsiClassExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiCodeBlockExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiDeclarationStatementExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiFieldExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiMethodExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiVariableExt
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiDeclarationStatement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter
import com.intellij.psi.PsiRecordComponent
import com.intellij.psi.PsiVariable

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
