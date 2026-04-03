package com.intellij.advancedExpressionFolding.foldingGroup

import com.intellij.lang.Language

object FoldingGroupLanguage : Language("FoldingGroupLang") {
  override fun isCaseSensitive(): Boolean = true
}
