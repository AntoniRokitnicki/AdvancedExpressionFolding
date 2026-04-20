package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
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
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter
import com.intellij.psi.PsiRecordComponent
import com.intellij.psi.PsiVariable

internal val declarationExpressionBuilderDefinitions:
    Map<Class<out BuildExpression<*>>, FunctionalExpressionBuilderDefinition<out PsiElement>> = mapOf(
    DeclarationStatementBuilder::class.java to builderDefinition<PsiDeclarationStatement> { element, _, _ ->
        PsiDeclarationStatementExt.createExpression(element)
    },
    VariableBuilder::class.java to builderDefinition<PsiVariable>(checkConditions = { element ->
        varExpressionsCollapse &&
            (element.parent is PsiDeclarationStatement || element.parent is PsiForeachStatement)
    }) { element, _, _ ->
        PsiVariableExt.getVariableDeclaration(element)
    },
    CodeBlockBuilder::class.java to builderDefinition<PsiCodeBlock> { element, _, _ ->
        PsiCodeBlockExt.getCodeBlockExpression(element)
    },
    ClassBuilder::class.java to builderDefinition<PsiClass> { element, _, _ ->
        PsiClassExt.createExpression(element)
    },
    FieldBuilder::class.java to builderDefinition<PsiField> { element, document, _ ->
        PsiFieldExt.createExpression(element, document)
    },
    ParameterBuilder::class.java to builderDefinition<PsiParameter> { element, document, _ ->
        PsiMethodExt.createExpression(element, document)
    },
    RecordComponentBuilder::class.java to builderDefinition<PsiRecordComponent> { element, _, _ ->
        PsiFieldExt.createExpression(element)
    },
    MethodBuilder::class.java to builderDefinition<PsiMethod> { element, document, _ ->
        PsiMethodExt.createExpression(element, document)
    },
)

class DeclarationStatementBuilder :
    FunctionalExpressionBuilder<PsiDeclarationStatement>(
        ExpressionBuilderRegistry.definition(DeclarationStatementBuilder::class.java)
    )

class VariableBuilder :
    FunctionalExpressionBuilder<PsiVariable>(
        ExpressionBuilderRegistry.definition(VariableBuilder::class.java)
    )

class CodeBlockBuilder :
    FunctionalExpressionBuilder<PsiCodeBlock>(
        ExpressionBuilderRegistry.definition(CodeBlockBuilder::class.java)
    )

class ClassBuilder :
    FunctionalExpressionBuilder<PsiClass>(
        ExpressionBuilderRegistry.definition(ClassBuilder::class.java)
    )

class FieldBuilder :
    FunctionalExpressionBuilder<PsiField>(
        ExpressionBuilderRegistry.definition(FieldBuilder::class.java)
    )

class ParameterBuilder :
    FunctionalExpressionBuilder<PsiParameter>(
        ExpressionBuilderRegistry.definition(ParameterBuilder::class.java)
    )

class RecordComponentBuilder :
    FunctionalExpressionBuilder<PsiRecordComponent>(
        ExpressionBuilderRegistry.definition(RecordComponentBuilder::class.java)
    )

class MethodBuilder :
    FunctionalExpressionBuilder<PsiMethod>(
        ExpressionBuilderRegistry.definition(MethodBuilder::class.java)
    )

private inline fun <reified T : PsiElement> builderDefinition(
    noinline checkConditions: (FunctionalExpressionBuilder<T>.(T) -> Boolean)? = null,
    noinline build: FunctionalExpressionBuilder<T>.(T, Document, Boolean) -> Expression?,
): FunctionalExpressionBuilderDefinition<T> =
    FunctionalExpressionBuilderDefinition(T::class.java, checkConditions, build)
