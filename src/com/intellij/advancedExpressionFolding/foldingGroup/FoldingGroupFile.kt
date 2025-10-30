package com.intellij.advancedExpressionFolding.foldingGroup

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class FoldingGroupFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FoldingGroupLanguage) {
  override fun getFileType() = FoldingGroupFileType

  override fun toString(): String = "FoldingGroupFile"
}
