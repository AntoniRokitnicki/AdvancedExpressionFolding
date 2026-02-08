package com.intellij.advancedExpressionFolding.foldingGroup.lexer

import com.intellij.advancedExpressionFolding.foldingGroup.FoldingGroupLanguage
import com.intellij.psi.tree.IElementType
import com.intellij.psi.TokenType

class FoldingGroupTokenType(debugName: String) : IElementType(debugName, FoldingGroupLanguage) {
  override fun toString(): String = "FoldingGroupTokenType." + super.toString()
}

object FoldingGroupTokenTypes {
  val LBRACKET = FoldingGroupTokenType("LBRACKET")
  val RBRACKET = FoldingGroupTokenType("RBRACKET")
  val COLON = FoldingGroupTokenType("COLON")
  val QUOTE = FoldingGroupTokenType("QUOTE")
  val GROUP_NUMBER = FoldingGroupTokenType("GROUP_NUMBER")
  val ESCAPED_SEQUENCE = FoldingGroupTokenType("ESCAPED_SEQUENCE")
  val TEXT_CHUNK = FoldingGroupTokenType("TEXT_CHUNK")
  val PLAINTEXT = FoldingGroupTokenType("PLAINTEXT")
  val BAD_CHARACTER: IElementType = TokenType.BAD_CHARACTER
}
