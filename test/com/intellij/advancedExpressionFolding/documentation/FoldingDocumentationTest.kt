package com.intellij.advancedExpressionFolding.documentation

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingBuilder
import com.intellij.advancedExpressionFolding.folding.BaseTest
import com.intellij.advancedExpressionFolding.isAdvancedExpressionFoldingGroup
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.application.runReadAction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FoldingDocumentationTest : BaseTest() {
  @Test
  fun documentationIncludesBeforeAfterAndSettings() {
    assignState(state::globalOn, state::localDateLiteralCollapse, state::localDateLiteralPostfixCollapse)
    val file = fixture.configureByText(
      "Doc.java",
      """
      import java.time.LocalDate;
      class Doc {
        void test() {
          LocalDate date = LocalDate.of(2024, 10, 26);
        }
      }
      """.trimIndent()
    )

    val provider = FoldingDocTargetProvider()
    val document = fixture.editor.document
    val offset = runReadAction {
      val descriptors = AdvancedExpressionFoldingBuilder()
        .buildFoldRegions(file, document, false)
        .filter(FoldingDescriptor::isAdvancedExpressionFoldingGroup)
      check(descriptors.isNotEmpty()) { "No advanced folding descriptors available" }
      val range = descriptors.first().range
      (range.startOffset + range.endOffset) / 2
    }

    val targets = runReadAction { provider.documentationTargets(file, offset) }
    assertEquals(1, targets.size) { "Expected a single documentation target at offset $offset" }

    val documentation = runReadAction { targets.single().computeDocumentation() }
    val html = extractHtml(requireNotNull(documentation))

    assertTrue(html.contains("Before"))
    assertTrue(html.contains("After"))
    assertTrue(html.contains("localDateLiteralCollapse"))
  }

  private fun extractHtml(result: com.intellij.platform.backend.documentation.DocumentationResult): String {
    val method = result::class.java.methods.firstOrNull { it.name == "getHtml" }
    if (method != null) {
      return method.invoke(result) as? String ?: ""
    }
    val field = result::class.java.fields.firstOrNull { it.name == "html" }
    if (field != null) {
      return field.get(result) as? String ?: ""
    }
    return result.toString()
  }
}
