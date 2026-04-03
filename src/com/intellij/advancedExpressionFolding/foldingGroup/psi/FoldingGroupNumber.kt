package com.intellij.advancedExpressionFolding.foldingGroup.psi

import com.intellij.lang.ASTNode

class FoldingGroupNumber(node: ASTNode) : FoldingGroupPsiElement(node) {
  fun intValue(): Int? {
    val text = text
    return text.toIntOrNull()
  }
}
