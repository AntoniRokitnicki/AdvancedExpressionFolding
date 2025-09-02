package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiCodeBlock

@Deprecated
class ControlFlowMultiStatementCodeBlockExpression(element: PsiCodeBlock, textRange: TextRange) :
    AbstractControlFlowCodeBlock(element, textRange)
