package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.advancedExpressionFolding.folding.BaseTest
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.application.ApplicationManager
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class OptionalAnnotationCompletionContributorTest : BaseTest() {

    companion object {
        private var originalPseudoAnnotationsValue: Boolean = false

        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            Arguments.of(
                TestCase(
                    name = "Wraps reference return type",
                    input = @Language("JAVA") """
                        public class Test {
                            @<caret>
                            public String value() {
                                return "value";
                            }
                        }
                    """.trimIndent(),
                    expected = @Language("JAVA") """
                        import java.util.Optional;

                        public class Test {
                            public Optional<String> value() {
                                return Optional.ofNullable("value");
                            }
                        }
                    """.trimIndent()
                )
            ),
            Arguments.of(
                TestCase(
                    name = "Wraps primitive return type",
                    input = @Language("JAVA") """
                        public class Test {
                            @<caret>
                            public int value() {
                                return 42;
                            }
                        }
                    """.trimIndent(),
                    expected = @Language("JAVA") """
                        import java.util.Optional;

                        public class Test {
                            public Optional<Integer> value() {
                                return Optional.of(42);
                            }
                        }
                    """.trimIndent()
                )
            ),
            Arguments.of(
                TestCase(
                    name = "Replaces null return with Optional.empty",
                    input = @Language("JAVA") """
                        public class Test {
                            @<caret>
                            public String maybe() {
                                return null;
                            }
                        }
                    """.trimIndent(),
                    expected = @Language("JAVA") """
                        import java.util.Optional;

                        public class Test {
                            public Optional<String> maybe() {
                                return Optional.empty();
                            }
                        }
                    """.trimIndent()
                )
            ),
            Arguments.of(
                TestCase(
                    name = "Handles multiple return statements",
                    input = @Language("JAVA") """
                        public class Test {
                            @<caret>
                            public String find(String input) {
                                if (input == null) {
                                    return null;
                                }
                                if (input.isEmpty()) {
                                    return "empty";
                                }
                                return input;
                            }
                        }
                    """.trimIndent(),
                    expected = @Language("JAVA") """
                        import java.util.Optional;

                        public class Test {
                            public Optional<String> find(String input) {
                                if (input == null) {
                                    return Optional.empty();
                                }
                                if (input.isEmpty()) {
                                    return Optional.ofNullable("empty");
                                }
                                return Optional.ofNullable(input);
                            }
                        }
                    """.trimIndent()
                )
            ),
            Arguments.of(
                TestCase(
                    name = "Applies to class and skips existing Optional",
                    input = @Language("JAVA") """
                        @<caret>
                        public class Test {
                            public String value(String input) {
                                return input;
                            }

                            public void doNothing() {
                            }

                            public int number() {
                                return 7;
                            }

                            public java.util.Optional<String> already() {
                                return java.util.Optional.empty();
                            }
                        }
                    """.trimIndent(),
                    expected = @Language("JAVA") """
                        import java.util.Optional;

                        public class Test {
                            public Optional<String> value(String input) {
                                return Optional.ofNullable(input);
                            }

                            public void doNothing() {
                            }

                            public Optional<Integer> number() {
                                return Optional.of(7);
                            }

                            public Optional<String> already() {
                                return Optional.empty();
                            }
                        }
                    """.trimIndent()
                )
            )
        )
    }

    @BeforeEach
    fun setUp() {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        originalPseudoAnnotationsValue = settings.state.pseudoAnnotations
        settings.state.pseudoAnnotations = true
    }

    @AfterEach
    fun tearDown() {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        settings.state.pseudoAnnotations = originalPseudoAnnotationsValue
    }

    @Test
    fun `should offer @Optional in completion for annotation above method`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                public class Test {
                    @<caret>
                    public String value() {
                        return "";
                    }
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        assertTrue(completions.any { it.lookupString == "Optional" })
    }

    @ParameterizedTest
    @MethodSource("testCases")
    fun `should wrap return values when selecting @Optional from completion`(testCase: TestCase) {
        fixture.configureByText("Test.java", testCase.input)

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val optionalCompletion = completions.find { it.lookupString == "Optional" }
        assertNotNull(optionalCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = optionalCompletion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(testCase.expected)
    }

    data class TestCase(
        val name: String,
        @param:Language("JAVA") val input: String,
        @param:Language("JAVA") val expected: String,
    ) {
        override fun toString(): String = name
    }
}
