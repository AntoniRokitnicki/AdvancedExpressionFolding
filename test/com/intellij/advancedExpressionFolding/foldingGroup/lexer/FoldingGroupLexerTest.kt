package com.intellij.advancedExpressionFolding.foldingGroup.lexer

import com.intellij.psi.TokenType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FoldingGroupLexerTest {
  @Test
  fun markers() {
    val text = "pre [2:\"say(\\\"hi\\\")\\nnext\"] post"
    val lexer = FoldingGroupLexer()
    lexer.start(text)
    val tokens = mutableListOf<Pair<Any, String>>()
    while (lexer.tokenType != null) {
      tokens += lexer.tokenType!! to text.substring(lexer.tokenStart, lexer.tokenEnd)
      lexer.advance()
    }
    val expected = listOf(
      FoldingGroupTokenTypes.PLAINTEXT to "pre",
      TokenType.WHITE_SPACE to " ",
      FoldingGroupTokenTypes.LBRACKET to "[",
      FoldingGroupTokenTypes.GROUP_NUMBER to "2",
      FoldingGroupTokenTypes.COLON to ":",
      FoldingGroupTokenTypes.QUOTE to "\"",
      FoldingGroupTokenTypes.TEXT_CHUNK to "say(",
      FoldingGroupTokenTypes.ESCAPED_SEQUENCE to "\\\"",
      FoldingGroupTokenTypes.TEXT_CHUNK to "hi",
      FoldingGroupTokenTypes.ESCAPED_SEQUENCE to "\\\"",
      FoldingGroupTokenTypes.TEXT_CHUNK to ")",
      FoldingGroupTokenTypes.ESCAPED_SEQUENCE to "\\n",
      FoldingGroupTokenTypes.TEXT_CHUNK to "next",
      FoldingGroupTokenTypes.QUOTE to "\"",
      FoldingGroupTokenTypes.RBRACKET to "]",
      TokenType.WHITE_SPACE to " ",
      FoldingGroupTokenTypes.PLAINTEXT to "post"
    )
    assertEquals(expected, tokens)
  }
}
