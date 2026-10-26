package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.processor.controlflow.ForStatementExpressionExt
import com.intellij.advancedExpressionFolding.processor.controlflow.IfExt
import com.intellij.advancedExpressionFolding.processor.controlflow.LoopExt
import com.intellij.advancedExpressionFolding.processor.controlflow.PsiTryStatementExt
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiCatchSection
import com.intellij.psi.PsiDoWhileStatement
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiForStatement
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiSwitchStatement
import com.intellij.psi.PsiTryStatement
import com.intellij.psi.PsiWhileStatement

internal val controlFlowExpressionBuilders = listOf(
    registerBuilder<PsiForStatement> { element, document, _ ->
        ForStatementExpressionExt.getForStatementExpression(element, document)
    },
    registerBuilder<PsiForeachStatement> { element, _, _ ->
        LoopExt.getForEachStatementExpression(element)
    },
    registerBuilder<PsiIfStatement> { element, document, _ ->
        IfExt.getIfExpression(element, document)
    },
    registerBuilder<PsiWhileStatement> { element, _, _ ->
        LoopExt.getWhileStatement(element)
    },
    registerBuilder<PsiDoWhileStatement> { element, _, _ ->
        LoopExt.getDoWhileStatement(element)
    },
    registerBuilder<PsiSwitchStatement> { element, _, _ ->
        IfExt.getSwitchStatement(element)
    },
    registerBuilder<PsiTryStatement> { element, _, _ ->
        PsiTryStatementExt.createExpression(element)
    },
    registerBuilder<PsiCatchSection>(predicate = {
        compactControlFlowSyntaxCollapse &&
            it.parameter != null &&
            it.lParenth != null &&
            it.rParenth != null
    }) { element, _, _ ->
        val l = element.lParenth ?: return@registerBuilder null
        val r = element.rParenth ?: return@registerBuilder null
        CompactControlFlowExpression(
            element,
            TextRange.create(l.textRange.startOffset, r.textRange.endOffset)
        )
    }
)
