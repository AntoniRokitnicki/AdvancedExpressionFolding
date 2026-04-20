package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.processor.controlflow.ForStatementExpressionExt
import com.intellij.advancedExpressionFolding.processor.controlflow.IfExt
import com.intellij.advancedExpressionFolding.processor.controlflow.LoopExt
import com.intellij.advancedExpressionFolding.processor.controlflow.PsiTryStatementExt
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiCatchSection
import com.intellij.psi.PsiDoWhileStatement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiForStatement
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiSwitchStatement
import com.intellij.psi.PsiTryStatement
import com.intellij.psi.PsiWhileStatement

internal val controlFlowExpressionBuilderDefinitions:
    Map<Class<out BuildExpression<*>>, FunctionalExpressionBuilderDefinition<out PsiElement>> = mapOf(
    ForStatementBuilder::class.java to builderDefinition<PsiForStatement> { element, document, _ ->
        ForStatementExpressionExt.getForStatementExpression(element, document)
    },
    ForEachStatementBuilder::class.java to builderDefinition<PsiForeachStatement> { element, _, _ ->
        LoopExt.getForEachStatementExpression(element)
    },
    IfStatementBuilder::class.java to builderDefinition<PsiIfStatement> { element, document, _ ->
        IfExt.getIfExpression(element, document)
    },
    WhileStatementBuilder::class.java to builderDefinition<PsiWhileStatement> { element, _, _ ->
        LoopExt.getWhileStatement(element)
    },
    DoWhileStatementBuilder::class.java to builderDefinition<PsiDoWhileStatement> { element, _, _ ->
        LoopExt.getDoWhileStatement(element)
    },
    SwitchStatementBuilder::class.java to builderDefinition<PsiSwitchStatement> { element, _, _ ->
        IfExt.getSwitchStatement(element)
    },
    TryStatementBuilder::class.java to builderDefinition<PsiTryStatement> { element, _, _ ->
        PsiTryStatementExt.createExpression(element)
    },
    CatchSectionBuilder::class.java to builderDefinition<PsiCatchSection>(checkConditions = { element ->
        compactControlFlowSyntaxCollapse &&
            element.parameter != null &&
            element.lParenth != null &&
            element.rParenth != null
    }) { element, _, _ ->
        val l = element.lParenth ?: return@builderDefinition null
        val r = element.rParenth ?: return@builderDefinition null
        CompactControlFlowExpression(
            element,
            TextRange.create(l.textRange.startOffset, r.textRange.endOffset)
        )
    },
)

class ForStatementBuilder :
    FunctionalExpressionBuilder<PsiForStatement>(
        ExpressionBuilderRegistry.definition(ForStatementBuilder::class.java)
    )

class ForEachStatementBuilder :
    FunctionalExpressionBuilder<PsiForeachStatement>(
        ExpressionBuilderRegistry.definition(ForEachStatementBuilder::class.java)
    )

class IfStatementBuilder :
    FunctionalExpressionBuilder<PsiIfStatement>(
        ExpressionBuilderRegistry.definition(IfStatementBuilder::class.java)
    )

class WhileStatementBuilder :
    FunctionalExpressionBuilder<PsiWhileStatement>(
        ExpressionBuilderRegistry.definition(WhileStatementBuilder::class.java)
    )

class DoWhileStatementBuilder :
    FunctionalExpressionBuilder<PsiDoWhileStatement>(
        ExpressionBuilderRegistry.definition(DoWhileStatementBuilder::class.java)
    )

class SwitchStatementBuilder :
    FunctionalExpressionBuilder<PsiSwitchStatement>(
        ExpressionBuilderRegistry.definition(SwitchStatementBuilder::class.java)
    )

class TryStatementBuilder :
    FunctionalExpressionBuilder<PsiTryStatement>(
        ExpressionBuilderRegistry.definition(TryStatementBuilder::class.java)
    )

class CatchSectionBuilder :
    FunctionalExpressionBuilder<PsiCatchSection>(
        ExpressionBuilderRegistry.definition(CatchSectionBuilder::class.java)
    )

private inline fun <reified T : PsiElement> builderDefinition(
    noinline checkConditions: (FunctionalExpressionBuilder<T>.(T) -> Boolean)? = null,
    noinline build: FunctionalExpressionBuilder<T>.(T, Document, Boolean) -> Expression?,
): FunctionalExpressionBuilderDefinition<T> =
    FunctionalExpressionBuilderDefinition(T::class.java, checkConditions, build)
