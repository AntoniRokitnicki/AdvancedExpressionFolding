package com.intellij.advancedExpressionFolding.foldingGroup.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.extapi.psi.ASTWrapperPsiElement

open class FoldingGroupPsiElement(node: ASTNode) : ASTWrapperPsiElement(node)

class FoldingGroupLeaf(node: ASTNode) : FoldingGroupPsiElement(node)

class FoldingGroupPlainText(node: ASTNode) : FoldingGroupPsiElement(node)
