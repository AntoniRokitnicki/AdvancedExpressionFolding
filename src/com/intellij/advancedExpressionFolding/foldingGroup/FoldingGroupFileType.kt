package com.intellij.advancedExpressionFolding.foldingGroup

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

object FoldingGroupFileType : LanguageFileType(FoldingGroupLanguage) {
  override fun getName(): String = "FoldingGroup"

  override fun getDescription(): String = "Folding group marker file"

  override fun getDefaultExtension(): String = "group.java"

  override fun getIcon(): Icon? = null

  override fun getCharset(file: VirtualFile, content: ByteArray): String = "UTF-8"
}
