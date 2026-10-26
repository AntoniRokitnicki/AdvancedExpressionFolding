package com.intellij.advancedExpressionFolding.foldingGroup.editor

import com.intellij.advancedExpressionFolding.foldingGroup.lexer.FoldingGroupTokenTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class FoldingGroupBraceMatcher : PairedBraceMatcher {
  private val pairs = arrayOf(BracePair(FoldingGroupTokenTypes.LBRACKET, FoldingGroupTokenTypes.RBRACKET, false))

  override fun getPairs(): Array<BracePair> = pairs

  override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, tokenType: IElementType?): Boolean = true

  override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int = openingBraceOffset
}
