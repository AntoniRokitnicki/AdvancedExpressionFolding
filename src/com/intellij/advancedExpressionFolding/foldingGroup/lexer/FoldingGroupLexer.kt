package com.intellij.advancedExpressionFolding.foldingGroup.lexer

import com.intellij.lexer.LexerBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

private const val STATE_DEFAULT = 0
private const val STATE_AFTER_LBRACKET = 1
private const val STATE_AFTER_NUMBER = 2
private const val STATE_AFTER_COLON = 3
private const val STATE_IN_TEXT = 4
private const val STATE_AFTER_QUOTE = 5

class FoldingGroupLexer : LexerBase() {
  private var buffer: CharSequence = ""
  private var endOffset: Int = 0
  private var tokenStart: Int = 0
  private var tokenEnd: Int = 0
  private var tokenType: IElementType? = null
  private var state: Int = STATE_DEFAULT

  override fun getState(): Int = state

  override fun getTokenType(): IElementType? = tokenType

  override fun getTokenStart(): Int = tokenStart

  override fun getTokenEnd(): Int = tokenEnd

  override fun getBufferSequence(): CharSequence = buffer

  override fun getBufferEnd(): Int = endOffset

  override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
    this.buffer = buffer
    this.endOffset = endOffset
    this.tokenStart = startOffset
    this.tokenEnd = startOffset
    this.state = initialState
    this.tokenType = null
    advance()
  }

  override fun advance() {
    if (tokenEnd >= endOffset) {
      tokenType = null
      tokenStart = endOffset
      return
    }
    tokenStart = if (tokenType == null) tokenStart else tokenEnd
    when (state) {
      STATE_DEFAULT -> lexDefault()
      STATE_AFTER_LBRACKET -> lexAfterLBracket()
      STATE_AFTER_NUMBER -> lexAfterNumber()
      STATE_AFTER_COLON -> lexAfterColon()
      STATE_IN_TEXT -> lexInText()
      STATE_AFTER_QUOTE -> lexAfterQuote()
      else -> lexDefault()
    }
  }

  private fun lexDefault() {
    if (tokenStart >= endOffset) {
      tokenType = null
      return
    }
    val c = buffer[tokenStart]
    when {
      c == '[' -> {
        tokenEnd = tokenStart + 1
        tokenType = FoldingGroupTokenTypes.LBRACKET
        state = STATE_AFTER_LBRACKET
      }
      c.isWhitespace() -> {
        tokenEnd = scanWhile(tokenStart) { it.isWhitespace() }
        tokenType = TokenType.WHITE_SPACE
      }
      else -> {
        tokenEnd = scanUntil(tokenStart) { it == '[' || it.isWhitespace() }
        tokenType = FoldingGroupTokenTypes.PLAINTEXT
      }
    }
  }

  private fun lexAfterLBracket() {
    if (tokenStart >= endOffset) {
      tokenType = null
      return
    }
    val c = buffer[tokenStart]
    when {
      c.isWhitespace() -> {
        tokenEnd = scanWhile(tokenStart) { it.isWhitespace() }
        tokenType = TokenType.WHITE_SPACE
      }
      c.isDigit() -> {
        tokenEnd = scanWhile(tokenStart) { it.isDigit() }
        tokenType = FoldingGroupTokenTypes.GROUP_NUMBER
        state = STATE_AFTER_NUMBER
      }
      c == ':' -> {
        tokenEnd = tokenStart + 1
        tokenType = FoldingGroupTokenTypes.COLON
        state = STATE_AFTER_COLON
      }
      c == '"' -> {
        tokenEnd = tokenStart + 1
        tokenType = FoldingGroupTokenTypes.QUOTE
        state = STATE_IN_TEXT
      }
      else -> {
        tokenEnd = scanUntil(tokenStart) { it == '[' || it.isWhitespace() }
        tokenType = FoldingGroupTokenTypes.PLAINTEXT
        state = STATE_DEFAULT
      }
    }
  }

  private fun lexAfterNumber() {
    if (tokenStart >= endOffset) {
      tokenType = null
      return
    }
    val c = buffer[tokenStart]
    when {
      c.isWhitespace() -> {
        tokenEnd = scanWhile(tokenStart) { it.isWhitespace() }
        tokenType = TokenType.WHITE_SPACE
      }
      c == ':' -> {
        tokenEnd = tokenStart + 1
        tokenType = FoldingGroupTokenTypes.COLON
        state = STATE_AFTER_COLON
      }
      else -> {
        tokenEnd = scanUntil(tokenStart) { it == '[' || it.isWhitespace() }
        tokenType = FoldingGroupTokenTypes.PLAINTEXT
        state = STATE_DEFAULT
      }
    }
  }

  private fun lexAfterColon() {
    if (tokenStart >= endOffset) {
      tokenType = null
      return
    }
    val c = buffer[tokenStart]
    when {
      c.isWhitespace() -> {
        tokenEnd = scanWhile(tokenStart) { it.isWhitespace() }
        tokenType = TokenType.WHITE_SPACE
      }
      c == '"' -> {
        tokenEnd = tokenStart + 1
        tokenType = FoldingGroupTokenTypes.QUOTE
        state = STATE_IN_TEXT
      }
      else -> {
        tokenEnd = scanUntil(tokenStart) { it == '[' || it.isWhitespace() }
        tokenType = FoldingGroupTokenTypes.PLAINTEXT
        state = STATE_DEFAULT
      }
    }
  }

  private fun lexInText() {
    if (tokenStart >= endOffset) {
      tokenType = null
      return
    }
    val c = buffer[tokenStart]
    when {
      c == '"' -> {
        tokenEnd = tokenStart + 1
        tokenType = FoldingGroupTokenTypes.QUOTE
        state = STATE_AFTER_QUOTE
      }
      c == '\\' -> {
        if (tokenStart + 1 < endOffset) {
          val next = buffer[tokenStart + 1]
          if (next == '\\' || next == '"' || next == 'n' || next == 't') {
            tokenEnd = tokenStart + 2
            tokenType = FoldingGroupTokenTypes.ESCAPED_SEQUENCE
            return
          }
        }
        tokenEnd = (tokenStart + 2).coerceAtMost(endOffset)
        tokenType = FoldingGroupTokenTypes.TEXT_CHUNK
      }
      else -> {
        tokenEnd = scanUntil(tokenStart) { it == '"' || it == '\\' }
        tokenType = FoldingGroupTokenTypes.TEXT_CHUNK
      }
    }
  }

  private fun lexAfterQuote() {
    if (tokenStart >= endOffset) {
      tokenType = null
      return
    }
    val c = buffer[tokenStart]
    when {
      c.isWhitespace() -> {
        tokenEnd = scanWhile(tokenStart) { it.isWhitespace() }
        tokenType = TokenType.WHITE_SPACE
      }
      c == ']' -> {
        tokenEnd = tokenStart + 1
        tokenType = FoldingGroupTokenTypes.RBRACKET
        state = STATE_DEFAULT
      }
      else -> {
        tokenEnd = scanUntil(tokenStart) { it == '[' || it.isWhitespace() }
        tokenType = FoldingGroupTokenTypes.PLAINTEXT
        state = STATE_DEFAULT
      }
    }
  }

  private inline fun scanWhile(start: Int, predicate: (Char) -> Boolean): Int {
    var index = start
    while (index < endOffset && predicate(buffer[index])) {
      index++
    }
    return index
  }

  private inline fun scanUntil(start: Int, stopPredicate: (Char) -> Boolean): Int {
    var index = start
    while (index < endOffset && !stopPredicate(buffer[index])) {
      index++
    }
    return index
  }

  private fun Char.isWhitespace(): Boolean = this == ' ' || this == '\t' || this == '\n' || this == '\r'

  private fun Char.isDigit(): Boolean = this in '0'..'9'
}
