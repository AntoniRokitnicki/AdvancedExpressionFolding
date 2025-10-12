package com.intellij.advancedExpressionFolding.foldingGroup.highlighting

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Color

object FoldingGroupColors {
  val GROUP_BRACKET: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
    "FOLDING_GROUP_BRACKET",
    TextAttributes(Color(0x44475A), null, null, null, 1)
  )

  val GROUP_COLON: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
    "FOLDING_GROUP_COLON",
    TextAttributes(Color(0x6272A4), null, null, null, 0)
  )

  val GROUP_QUOTE: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
    "FOLDING_GROUP_QUOTE",
    TextAttributes(Color(0x708090), null, null, null, 0)
  )

  val DEFAULT_TEXT: TextAttributesKey = TextAttributesKey.createTextAttributesKey(
    "FOLDING_GROUP_DEFAULT_TEXT",
    DefaultLanguageHighlighterColors.STRING
  )

  private val PALETTE = intArrayOf(
    0x6272A4,
    0xBD93F9,
    0x50FA7B,
    0xFF79C6,
    0x8BE9FD,
    0xF1FA8C,
    0xFFB86C,
    0xFF5555,
    0xA4FFFF,
    0xC792EA
  )

  private val numberKeys: Array<TextAttributesKey> = Array(10) { index ->
    TextAttributesKey.createTextAttributesKey(
      "FOLDING_GROUP_NUMBER_$index",
      TextAttributes(Color(PALETTE[index]), null, null, null, 0)
    )
  }

  private val textKeys: Array<TextAttributesKey> = Array(10) { index ->
    val baseColor = Color(PALETTE[index])
    val background = Color(baseColor.red, baseColor.green, baseColor.blue, if (index == 0) (0.18f * 255).toInt() else 38)
    TextAttributesKey.createTextAttributesKey(
      "FOLDING_GROUP_TEXT_$index",
      TextAttributes(Color(0xF8F8F2), background, null, null, 0)
    )
  }

  fun numberKey(groupId: Int): TextAttributesKey = numberKeys[modIndex(groupId)]

  fun textKey(groupId: Int): TextAttributesKey = textKeys[modIndex(groupId)]

  private fun modIndex(groupId: Int): Int {
    if (groupId < 0) return 0
    return groupId % 10
  }
}
