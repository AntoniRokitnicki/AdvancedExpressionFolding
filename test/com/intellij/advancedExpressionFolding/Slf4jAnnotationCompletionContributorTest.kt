package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.application.ApplicationManager
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull

class Slf4jAnnotationCompletionContributorTest : BaseTest() {

    companion object {
        private var originalPseudoAnnotationsValue: Boolean = false
    }

    @BeforeEach
    fun setUp() {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        originalPseudoAnnotationsValue = settings.state.pseudoAnnotations
        settings.state.pseudoAnnotations = true

        fixture.addClass(
            """
            package org.slf4j;

            public interface Logger {
            }
            """.trimIndent()
        )

        fixture.addClass(
            """
            package org.slf4j;

            public class LoggerFactory {
                public static Logger getLogger(Class<?> clazz) {
                    return null;
                }
            }
            """.trimIndent()
        )
    }

    @AfterEach
    fun tearDown() {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        settings.state.pseudoAnnotations = originalPseudoAnnotationsValue
    }

    @Test
    fun `should offer @Slf4j in completion for annotation above class`() {
        fixture.configureByText("Test.java", @Language("JAVA") """
            @<caret>
            public class Test {
            }
        """.trimIndent())

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        assertTrue(completions.any { it.lookupString == "Slf4j" })
    }

    @Test
    fun `should generate logger field for top level class`() {
        fixture.configureByText("Test.java", @Language("JAVA") """
            @<caret>
            public class Test {
            }
        """.trimIndent())

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val slf4jCompletion = completions.find { it.lookupString == "Slf4j" }
        assertNotNull(slf4jCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = slf4jCompletion
            fixture.finishLookup('\n')
        }

        val expected = """
            import org.slf4j.Logger;
            import org.slf4j.LoggerFactory;

            public class Test {
                private static final Logger log = LoggerFactory.getLogger(Test.class);
            }
        """.trimIndent()

        assertEquals(expected, fixture.file.text.trim())
    }

    @Test
    fun `should generate logger field for nested class`() {
        fixture.configureByText("Test.java", @Language("JAVA") """
            public class Outer {
                @<caret>
                static class Inner {
                }
            }
        """.trimIndent())

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val slf4jCompletion = completions.find { it.lookupString == "Slf4j" }
        assertNotNull(slf4jCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = slf4jCompletion
            fixture.finishLookup('\n')
        }

        val expected = """
            import org.slf4j.Logger;
            import org.slf4j.LoggerFactory;

            public class Outer {
                static class Inner {
                    private static final Logger log = LoggerFactory.getLogger(Inner.class);
                }
            }
        """.trimIndent()

        assertEquals(expected, fixture.file.text.trim())
    }
}
