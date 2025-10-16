package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.advancedExpressionFolding.BaseTest
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

class LoggableAnnotationCompletionContributorTest : BaseTest() {

    companion object {
        private var originalPseudoAnnotationsValue: Boolean = false

        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            Arguments.of(
                TestCase(
                    name = "Method-level logging with parameters",
                    input = @Language("JAVA") """
                        public class Test {
                            @<caret>
                            public void doWork(int value, String name) {
                                System.out.println(value + name);
                            }
                        }
                    """.trimIndent(),
                    expected = @Language("JAVA") """
                        public class Test {
                            public void doWork(int value, String name) {
                                System.out.println("Entering Test.doWork" + " with args: " + "value=" + value + ", " + "name=" + name);
                                System.out.println(value + name);
                                System.out.println("Exiting Test.doWork");
                            }
                        }
                    """.trimIndent()
                )
            ),
            Arguments.of(
                TestCase(
                    name = "Class-level logging applied to nested classes",
                    input = @Language("JAVA") """
                        @<caret>
                        public class Outer {
                            public void outerMethod() {
                                System.out.println("work");
                            }

                            class Inner {
                                public void innerMethod() {
                                    System.out.println("inner");
                                }
                            }
                        }
                    """.trimIndent(),
                    expected = @Language("JAVA") """
                        public class Outer {
                            public void outerMethod() {
                                System.out.println("Entering Outer.outerMethod");
                                System.out.println("work");
                                System.out.println("Exiting Outer.outerMethod");
                            }

                            class Inner {
                                public void innerMethod() {
                                    System.out.println("Entering Inner.innerMethod");
                                    System.out.println("inner");
                                    System.out.println("Exiting Inner.innerMethod");
                                }
                            }
                        }
                    """.trimIndent()
                )
            ),
            Arguments.of(
                TestCase(
                    name = "Class-level logging applied to all methods",
                    input = @Language("JAVA") """
                        @<caret>
                        public class Test {
                            public void first(int value) {
                                System.out.println(value);
                            }

                            public String second() {
                                return "ok";
                            }
                        }
                    """.trimIndent(),
                    expected = @Language("JAVA") """
                        public class Test {
                            public void first(int value) {
                                System.out.println("Entering Test.first" + " with args: " + "value=" + value);
                                System.out.println(value);
                                System.out.println("Exiting Test.first");
                            }

                            public String second() {
                                System.out.println("Entering Test.second");
                                System.out.println("Exiting Test.second");
                                return "ok";
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
    fun `should offer @Loggable in completion for annotation above method`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                public class Test {
                    @<caret>
                    public void doWork(int value) {
                        System.out.println(value);
                    }
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        assertTrue(completions.any { it.lookupString == "Loggable" })
    }

    @ParameterizedTest
    @MethodSource("testCases")
    fun `should insert logging when selecting @Loggable from completion`(testCase: TestCase) {
        fixture.configureByText("Test.java", testCase.input)

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val logCompletion = completions.find { it.lookupString == "Loggable" }
        assertNotNull(logCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = logCompletion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(testCase.expected)
    }

    @Test
    fun `should remove logging when selecting @Loggable on already logged method`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                public class Test {
                    @<caret>
                    public void doWork(int value) {
                        System.out.println("Entering Test.doWork" + " with args: " + "value=" + value);
                        System.out.println(value);
                        System.out.println("Exiting Test.doWork");
                    }
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val logCompletion = completions.find { it.lookupString == "Loggable" }
        assertNotNull(logCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = logCompletion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(
            @Language("JAVA") """
                public class Test {
                    public void doWork(int value) {
                        System.out.println(value);
                    }
                }
            """.trimIndent()
        )
    }

    data class TestCase(
        val name: String,
        @param:Language("JAVA") val input: String,
        @param:Language("JAVA") val expected: String
    ) {
        override fun toString() = name
    }
}
