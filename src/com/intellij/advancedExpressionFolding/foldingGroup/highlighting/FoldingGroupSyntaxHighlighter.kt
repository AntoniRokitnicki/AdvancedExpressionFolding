package com.intellij.advancedExpressionFolding.foldingGroup.highlighting

import com.intellij.advancedExpressionFolding.foldingGroup.lexer.FoldingGroupLexer
import com.intellij.advancedExpressionFolding.foldingGroup.lexer.FoldingGroupTokenTypes
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class FoldingGroupSyntaxHighlighter : SyntaxHighlighterBase() {
  override fun getHighlightingLexer() = FoldingGroupLexer()

  override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> = when (tokenType) {
    FoldingGroupTokenTypes.LBRACKET, FoldingGroupTokenTypes.RBRACKET -> pack(FoldingGroupColors.GROUP_BRACKET)
    FoldingGroupTokenTypes.COLON -> pack(FoldingGroupColors.GROUP_COLON)
    FoldingGroupTokenTypes.QUOTE -> pack(FoldingGroupColors.GROUP_QUOTE)
    else -> emptyArray()
  }
}
