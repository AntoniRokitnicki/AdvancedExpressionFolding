package com.intellij.advancedExpressionFolding.foldingGroup.parser

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.foldingGroup.FoldingGroupLanguage
import com.intellij.advancedExpressionFolding.foldingGroup.psi.FoldingGroupMarker
import com.intellij.openapi.application.runReadAction
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FoldingGroupPsiTest : BaseTest() {
  @Test
  fun groupProperties() {
    val text = "[2:\"say(\\\"hi\\\")\\nnext\"]"
    val file = runReadAction {
      PsiFileFactory.getInstance(fixture.project).createFileFromText("sample.group", FoldingGroupLanguage, text)
    }
    val marker = runReadAction {
      PsiTreeUtil.findChildOfType(file, FoldingGroupMarker::class.java)
    } ?: error("Marker not found")
    assertEquals(2, marker.groupId)
    assertEquals("say(\"hi\")\nnext", marker.rawText)
  }
}
