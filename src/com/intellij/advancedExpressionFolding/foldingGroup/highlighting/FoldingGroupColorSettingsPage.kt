package com.intellij.advancedExpressionFolding.foldingGroup.highlighting

import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.editor.colors.TextAttributesKey
import javax.swing.Icon

class FoldingGroupColorSettingsPage : ColorSettingsPage {
  private val descriptors = arrayOf(
    AttributesDescriptor("Brackets", FoldingGroupColors.GROUP_BRACKET),
    AttributesDescriptor(":", FoldingGroupColors.GROUP_COLON),
    AttributesDescriptor("Quotes", FoldingGroupColors.GROUP_QUOTE)
  )

  override fun getDisplayName(): String = "Folding Group Markers"

  override fun getIcon(): Icon? = null

  override fun getHighlighter(): SyntaxHighlighter = FoldingGroupSyntaxHighlighter()

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = descriptors

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDemoText(): String = "pre [2:\"demo\"] post"

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null
}
