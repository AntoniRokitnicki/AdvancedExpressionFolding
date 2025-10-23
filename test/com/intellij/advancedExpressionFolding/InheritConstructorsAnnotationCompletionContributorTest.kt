package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.application.ApplicationManager
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class InheritConstructorsAnnotationCompletionContributorTest : BaseTest() {
    private var originalPseudoAnnotationsValue: Boolean = false

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
    fun `should offer @InheritConstructors in completion for annotation above class`() {
        fixture.configureByText("Test.java", @Language("JAVA") """
            import java.io.IOException;
            
            class Base {
                public Base() {}
                public Base(String message, Throwable cause) throws IOException {}
            }
            
            @<caret>
            class Derived extends Base {
            }
        """.trimIndent())

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        assertTrue(completions.any { it.lookupString == "InheritConstructors" })
    }

    @ParameterizedTest
    @MethodSource("testCases")
    fun `should generate constructors when selecting @InheritConstructors from completion`(testCase: TestCase) {
        fixture.configureByText("Test.java", testCase.input)

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val inheritConstructors = completions.find { it.lookupString == "InheritConstructors" }
        assertNotNull(inheritConstructors)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = inheritConstructors
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

    companion object {
        @JvmStatic
        fun testCases(): Stream<TestCase> = Stream.of(
            TestCase(
                name = "Generates constructors from parent",
                input = """
                    import java.io.IOException;
                    
                    class Base {
                        public Base() {}
                        public Base(String message, Throwable cause) throws IOException {}
                        protected Base(int code, String name) {}
                        private Base(boolean hidden) {}
                    }
                    
                    @<caret>
                    class Derived extends Base {
                    }
                """.trimIndent(),
                expected = """
                    import java.io.IOException;
                    
                    class Base {
                        public Base() {}
                        public Base(String message, Throwable cause) throws IOException {}
                        protected Base(int code, String name) {}
                        private Base(boolean hidden) {}
                    }
                    
                    class Derived extends Base {
                        public Derived() {
                            super();
                        }
                    
                        public Derived(String message, Throwable cause) throws IOException {
                            super(message, cause);
                        }
                    
                        protected Derived(int code, String name) {
                            super(code, name);
                        }
                    }
                """.trimIndent()
            ),
            TestCase(
                name = "Skips existing constructors",
                input = """
                    class Base {
                        public Base() {}
                        public Base(int value) {}
                    }
                    
                    @<caret>
                    class Derived extends Base {
                        public Derived() {}
                    }
                """.trimIndent(),
                expected = """
                    class Base {
                        public Base() {}
                        public Base(int value) {}
                    }
                    
                    class Derived extends Base {
                        public Derived() {}
                    
                        public Derived(int value) {
                            super(value);
                        }
                    }
                """.trimIndent()
            )
        )
    }
}
