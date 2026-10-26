package com.intellij.advancedExpressionFolding.foldingGroup.parser

import com.intellij.advancedExpressionFolding.foldingGroup.lexer.FoldingGroupTokenTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType

class FoldingGroupParser : PsiParser {
  override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
    val rootMarker = builder.mark()
    while (!builder.eof()) {
      if (builder.tokenType == FoldingGroupTokenTypes.LBRACKET) {
        if (!parseMarker(builder)) {
          parsePlainText(builder)
        }
      } else {
        parsePlainText(builder)
      }
    }
    rootMarker.done(root)
    return builder.treeBuilt
  }

  private fun parsePlainText(builder: PsiBuilder) {
    val marker = builder.mark()
    var advanced = false
    while (!builder.eof() && builder.tokenType != FoldingGroupTokenTypes.LBRACKET) {
      builder.advanceLexer()
      advanced = true
    }
    if (advanced) {
      marker.done(FoldingGroupElementTypes.PLAIN_TEXT)
    } else {
      marker.drop()
      builder.advanceLexer()
    }
  }

  private fun parseMarker(builder: PsiBuilder): Boolean {
    val marker = builder.mark()
    if (builder.tokenType != FoldingGroupTokenTypes.LBRACKET) {
      marker.drop()
      return false
    }
    wrapToken(builder, FoldingGroupElementTypes.GROUP_OPEN)

    skipWhitespace(builder)
    if (builder.tokenType == FoldingGroupTokenTypes.GROUP_NUMBER) {
      wrapToken(builder, FoldingGroupElementTypes.GROUP_NUMBER_NODE)
      skipWhitespace(builder)
    }
    if (builder.tokenType == FoldingGroupTokenTypes.COLON) {
      wrapToken(builder, FoldingGroupElementTypes.GROUP_COLON)
    } else {
      builder.error("':' expected")
    }
    skipWhitespace(builder)
    if (builder.tokenType == FoldingGroupTokenTypes.QUOTE) {
      wrapToken(builder, FoldingGroupElementTypes.GROUP_QUOTE_OPEN)
    } else {
      builder.error("Opening quote expected")
      marker.done(FoldingGroupElementTypes.GROUP_MARKER)
      return true
    }

    val textMarker = builder.mark()
    var hasText = false
    while (!builder.eof()) {
      val tokenType = builder.tokenType
      if (tokenType == FoldingGroupTokenTypes.QUOTE) {
        break
      }
      if (tokenType == FoldingGroupTokenTypes.ESCAPED_SEQUENCE || tokenType == FoldingGroupTokenTypes.TEXT_CHUNK) {
        builder.advanceLexer()
        hasText = true
        continue
      }
      if (tokenType == FoldingGroupTokenTypes.RBRACKET) {
        break
      }
      if (tokenType == FoldingGroupTokenTypes.LBRACKET) {
        break
      }
      builder.advanceLexer()
      hasText = true
    }
    if (hasText) {
      textMarker.done(FoldingGroupElementTypes.GROUP_TEXT)
    } else {
      textMarker.done(FoldingGroupElementTypes.GROUP_TEXT)
    }

    if (builder.tokenType == FoldingGroupTokenTypes.QUOTE) {
      wrapToken(builder, FoldingGroupElementTypes.GROUP_QUOTE_CLOSE)
    } else {
      builder.error("Closing quote expected")
      marker.done(FoldingGroupElementTypes.GROUP_MARKER)
      return true
    }

    skipWhitespace(builder)
    if (builder.tokenType == FoldingGroupTokenTypes.RBRACKET) {
      wrapToken(builder, FoldingGroupElementTypes.GROUP_CLOSE)
    } else {
      builder.error("']' expected")
      marker.done(FoldingGroupElementTypes.GROUP_MARKER)
      return true
    }
    marker.done(FoldingGroupElementTypes.GROUP_MARKER)
    return true
  }

  private fun skipWhitespace(builder: PsiBuilder) {
    while (builder.tokenType == com.intellij.psi.TokenType.WHITE_SPACE) {
      builder.advanceLexer()
    }
  }

  private fun wrapToken(builder: PsiBuilder, type: IElementType) {
    val marker = builder.mark()
    builder.advanceLexer()
    marker.done(type)
  }
}
