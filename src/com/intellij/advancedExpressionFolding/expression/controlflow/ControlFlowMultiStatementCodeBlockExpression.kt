package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiCodeBlock

@Deprecated("Legacy multi-statement control-flow folding")
class ControlFlowMultiStatementCodeBlockExpression(
    element: PsiCodeBlock,
    textRange: TextRange
) : AbstractControlFlowCodeBlock(element, textRange)
