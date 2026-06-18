package com.intellij.advancedExpressionFolding.foldingGroup.psi

import com.intellij.lang.ASTNode

class FoldingGroupText(node: ASTNode) : FoldingGroupPsiElement(node) {
  fun segments(): List<String> = children.map { it.text }
}
