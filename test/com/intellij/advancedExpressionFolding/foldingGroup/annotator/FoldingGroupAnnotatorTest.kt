package com.intellij.advancedExpressionFolding.foldingGroup.annotator

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.foldingGroup.highlighting.FoldingGroupColors
import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.lang.annotation.HighlightSeverity
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FoldingGroupAnnotatorTest : BaseTest() {
  @Test
  fun validMarkersProduceHighlights() {
    fixture.configureByText("valid.group", """pre [10:"foo"] mid [0:"x + "y""] post""")
    val infos = fixture.doHighlighting().filterIsInstance<HighlightInfo>()
    assertTrue(infos.any { it.severity == HighlightSeverity.INFORMATION && it.forcedTextAttributesKey == FoldingGroupColors.numberKey(10) })
    assertTrue(infos.any { it.toolTip?.contains("Group 10") == true })
  }

  @Test
  fun leadingZeroError() {
    fixture.configureByText("zero.group", """[01:"foo"]""")
    val infos = fixture.doHighlighting().filterIsInstance<HighlightInfo>()
    assertTrue(infos.any { it.description?.contains("Leading zeros") == true })
  }

  @Test
  fun unknownEscapeWarning() {
    fixture.configureByText("escape.group", """[1:"foo\xbar"]""")
    val infos = fixture.doHighlighting().filterIsInstance<HighlightInfo>()
    assertTrue(infos.any { it.description?.contains("Unknown escape") == true })
  }

  @Test
  fun missingQuoteAndBracketErrors() {
    fixture.configureByText("broken.group", """[2:"foo"""")
    val infos = fixture.doHighlighting().filterIsInstance<HighlightInfo>()
    assertTrue(infos.any { it.severity == HighlightSeverity.ERROR })
  }
}
