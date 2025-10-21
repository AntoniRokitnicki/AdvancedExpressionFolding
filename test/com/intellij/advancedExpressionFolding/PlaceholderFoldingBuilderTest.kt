package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.openapi.application.ReadAction
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PlaceholderFoldingBuilderTest : BaseTest() {

    @Test
    fun previewShowsOptionalElvisFold() {
        val settings = getInstance()
        val originalState = settings.state.copy()
        val originalStore = store
        try {
            settings.disableAll()
            settings.state.apply {
                globalOn = true
                optional = true
            }
            store = FoldingDataStorage()

            val javaCode = """
                import java.util.Optional;
                
                class Demo {
                    Object test(Object input, Object fallback) {
                        return Optional.ofNullable(input).orElse(fallback);
                    }
                }
            """.trimIndent()
            fixture.configureByText("Demo.java", javaCode)
            val file = fixture.file
            val document = fixture.getDocument(file)

            val builder = AdvancedExpressionFoldingBuilder(settings.state)
            val preview = ReadAction.compute<List<String>, RuntimeException> {
                builder.preview(file, document)
            }

            val details = preview.map { summary ->
                val (original, rest) = summary.split(" => ", limit = 2)
                val placeholder = rest.substringBefore("[")
                val groupRaw = rest.substringAfter("[").removeSuffix("]")
                val (countText, groupName) = groupRaw.split("-", limit = 2)
                PlaceholderDetails(original, placeholder, countText.toInt(), groupName)
            }

            assertTrue(
                details.any { detail ->
                    detail.original == ".orElse(" &&
                        detail.placeholder == " ?: " &&
                        detail.groupCount == 1 &&
                        detail.groupName == "OptionalOrElseElvis"
                },
            )

            assertTrue(
                details.any { detail ->
                    detail.original == "Optional.ofNullable(" &&
                        detail.placeholder.isEmpty() &&
                        detail.groupCount == 2 &&
                        detail.groupName == "OptionalOfNullable"
                },
            )
        } finally {
            store = originalStore
            settings.loadState(originalState)
        }
    }

    private data class PlaceholderDetails(
        val original: String,
        val placeholder: String,
        val groupCount: Int,
        val groupName: String,
    )
}
