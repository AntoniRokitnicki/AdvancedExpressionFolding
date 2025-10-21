package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.application.ApplicationManager
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class OptionalAnnotationCompletionContributorTest : BaseTest() {

    companion object {
        private var originalPseudoAnnotationsValue: Boolean = false

        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            Arguments.of(TestCase(
                name = "Generates Optional wrapper for reference return type",
                input = @Language("JAVA") """
                    @<caret>
                    public class Test {
                        public String value() {
                            return "";
                        }

                        public void doIt() {
                        }
                    }
                """.trimIndent(),
                expected = @Language("JAVA") """
                    import java.util.Optional;

                    public class Test {
                        public String value() {
                            return "";
                        }

                        public Optional<String> optionalValue() {
                            return Optional.ofNullable(value());
                        }

                        public void doIt() {
                        }
                    }
                """.trimIndent()
            )),
            Arguments.of(TestCase(
                name = "Preserves static modifier and boxes primitives",
                input = @Language("JAVA") """
                    @<caret>
                    public class Test {
                        public static int number() {
                            return 42;
                        }
                    }
                """.trimIndent(),
                expected = @Language("JAVA") """
                    import java.util.Optional;

                    public class Test {
                        public static int number() {
                            return 42;
                        }

                        public static Optional<Integer> optionalNumber() {
                            return Optional.ofNullable(number());
                        }
                    }
                """.trimIndent()
            )),
            Arguments.of(TestCase(
                name = "Copies type parameters and throws clauses",
                input = @Language("JAVA") """
                    @<caret>
                    public class Test {
                        public <T> T find(Class<T> type) throws java.io.IOException {
                            return null;
                        }
                    }
                """.trimIndent(),
                expected = @Language("JAVA") """
                    import java.io.IOException;
                    import java.util.Optional;

                    public class Test {
                        public <T> T find(Class<T> type) throws java.io.IOException {
                            return null;
                        }

                        public <T> Optional<T> optionalFind(Class<T> type) throws IOException {
                            return Optional.ofNullable(find(type));
                        }
                    }
                """.trimIndent()
            )),
            Arguments.of(TestCase(
                name = "Skips generation when wrapper already exists",
                input = @Language("JAVA") """
                    @<caret>
                    public class Test {
                        public String value() {
                            return "";
                        }

                        public java.util.Optional<String> optionalValue() {
                            return java.util.Optional.ofNullable(value());
                        }
                    }
                """.trimIndent(),
                // The wrapper is already present and uses fully-qualified Optional references,
                // so the contributor must leave the code unchanged (no added import).
                expected = @Language("JAVA") """
                    public class Test {
                        public String value() {
                            return "";
                        }

                        public java.util.Optional<String> optionalValue() {
                            return java.util.Optional.ofNullable(value());
                        }
                    }
                """.trimIndent()
            ))
        )
    }

    @BeforeEach
    fun setUp() {
        val settings = AdvancedExpressionFoldingSettings.Companion.getInstance()
        originalPseudoAnnotationsValue = settings.state.pseudoAnnotations
        settings.state.pseudoAnnotations = true
    }

    @AfterEach
    fun tearDown() {
        val settings = AdvancedExpressionFoldingSettings.Companion.getInstance()
        settings.state.pseudoAnnotations = originalPseudoAnnotationsValue
    }

    @Test
    fun `should offer @Optional in completion for annotation above class`() {
        fixture.configureByText("Test.java", @Language("JAVA") """
            @<caret>
            public class Test {
                public String value() {
                    return "";
                }
            }
        """.trimIndent())

        val completions = fixture.complete(CompletionType.BASIC)
        Assertions.assertNotNull(completions)
        Assertions.assertTrue(completions.any { it.lookupString == "Optional" })
        Assertions.assertFalse(completions.any { it.lookupString == "Main" })
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testCases")
    fun `should generate optional wrappers when selecting @Optional from completion`(testCase: TestCase) {
        fixture.configureByText("Test.java", testCase.input)

        val completions = fixture.complete(CompletionType.BASIC)
        Assertions.assertNotNull(completions)

        val optionalCompletion = completions.find { it.lookupString == "Optional" }
        Assertions.assertNotNull(optionalCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = optionalCompletion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(testCase.expected)
    }

    data class TestCase(
        val name: String,
        @param:Language("JAVA") val input: String,
        @param:Language("JAVA") val expected: String
    ) {
        override fun toString() = name
    }
}
