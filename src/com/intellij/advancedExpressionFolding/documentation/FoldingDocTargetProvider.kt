package com.intellij.advancedExpressionFolding.documentation

import com.intellij.openapi.project.DumbAware
import com.intellij.platform.backend.documentation.DocumentationTarget
import com.intellij.platform.backend.documentation.DocumentationTargetProvider
import com.intellij.psi.PsiFile

class FoldingDocTargetProvider : DocumentationTargetProvider, DumbAware {
  override fun documentationTargets(file: PsiFile, offset: Int): List<DocumentationTarget> {
    val element = file.findElementAt(offset) ?: return emptyList()
    return listOf(FoldingDocTarget(element))
  }
}
