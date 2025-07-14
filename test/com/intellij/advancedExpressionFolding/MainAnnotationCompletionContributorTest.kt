package com.intellij.advancedExpressionFolding

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase5
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MainAnnotationCompletionContributorTest : LightJavaCodeInsightFixtureTestCase5() {

    override fun getTestDataPath(): String = "testData"

    companion object {
        private var originalPseudoAnnotationsValue: Boolean = false
        
        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            Arguments.of(TestCase(
                name = "Static Method",
                input = """
                    public class Test {
                        @<caret>
                        public static void staticMethod(int x) {
                        }
                    }
                """.trimIndent(),
                expected = """
                    public class Test {
                        @Main
                        public static void staticMethod(int x) {
                        }
                    }
                """.trimIndent()
            )),
            
            Arguments.of(TestCase(
                name = "Instance Method",
                input = """
                    public class Test {
                        @<caret>
                        public void instanceMethod(String s) {
                        }
                    }
                """.trimIndent(),
                expected = """
                    public class Test {
                        @Main
                        public void instanceMethod(String s) {
                        }
                    }
                """.trimIndent()
            )),
            
            Arguments.of(TestCase(
                name = "Non-void Return Type",
                input = """
                    public class Test {
                        @<caret>
                        public String getValue() {
                            return "";
                        }
                    }
                """.trimIndent(),
                expected = """
                    public class Test {
                        @Main
                        public String getValue() {
                            return "";
                        }
                    }
                """.trimIndent()
            )),
            
            Arguments.of(TestCase(
                name = "Constructor With Parameters",
                input = """
                    public class Test {
                        public Test(int param) {
                        }
                        
                        @<caret>
                        public void testMethod(String s) {
                        }
                    }
                """.trimIndent(),
                expected = """
                    public class Test {
                        public Test(int param) {
                        }
                        
                        @Main
                        public void testMethod(String s) {
                        }
                    }
                """.trimIndent()
            )),
            
            Arguments.of(TestCase(
                name = "Varargs Parameters",
                input = """
                    public class Test {
                        @<caret>
                        public void stringVarargs(String... args) {
                        }
                    }
                """.trimIndent(),
                expected = """
                    public class Test {
                        @Main
                        public void stringVarargs(String... args) {
                        }
                    }
                """.trimIndent()
            )),
            
            Arguments.of(TestCase(
                name = "Existing Main Method",
                input = """
                    public class Test {
                        public static void main(String[] args) {
                            // existing main
                        }
                        
                        @<caret>
                        public void newMethod() {
                        }
                    }
                """.trimIndent(),
                expected = """
                    public class Test {
                        public static void main(String[] args) {
                            // existing main
                        }
                        
                        @Main
                        public void newMethod() {
                        }
                    }
                """.trimIndent()
            )),
            
            Arguments.of(TestCase(
                name = "Nested Static Class",
                input = """
                    public class Test {
                        public static class NestedStatic {
                            @<caret>
                            public void nestedMethod(int x) {
                            }
                        }
                    }
                """.trimIndent(),
                expected = """
                    public class Test {
                        public static class NestedStatic {
                            @Main
                            public void nestedMethod(int x) {
                            }
                        }
                    }
                """.trimIndent()
            )),
            
            Arguments.of(TestCase(
                name = "Primitive Parameters",
                input = """
                    public class Test {
                        @<caret>
                        public void primitiveParams(boolean b, char c, byte by, short s, int i, long l, float f, double d) {
                        }
                    }
                """.trimIndent(),
                expected = """
                    public class Test {
                        @Main
                        public void primitiveParams(boolean b, char c, byte by, short s, int i, long l, float f, double d) {
                        }
                    }
                """.trimIndent()
            )),
            
            Arguments.of(TestCase(
                name = "Date Time Parameters",
                input = """
                    import java.time.LocalDate;
                    import java.time.ZonedDateTime;
                    
                    public class Test {
                        @<caret>
                        public void dateParams(java.util.Date date, LocalDate ld, java.time.LocalDateTime ldt, ZonedDateTime zdt) {
                        }
                    }
                """.trimIndent(),
                expected = """
                    import java.time.LocalDate;
                    import java.time.ZonedDateTime;
                    
                    public class Test {
                        @Main
                        public void dateParams(java.util.Date date, LocalDate ld, java.time.LocalDateTime ldt, ZonedDateTime zdt) {
                        }
                    }
                """.trimIndent()
            ))
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
    fun `should offer @Main in completion for annotation above method`() {
        fixture.configureByText("Test.java", @Language("JAVA") """
            public class Test {
                @<caret>
                public static void staticMethod(int x) {
                }
            }
        """.trimIndent())

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        assertTrue(completions.any { it.lookupString == "Main" })
    }

    @ParameterizedTest
    @MethodSource("testCases")
    fun `should add @Main annotation when typing Main after @`(testCase: TestCase) {
        fixture.configureByText("Test.java", testCase.input)
        fixture.type("Main")
        
        println("=== ${testCase.name} ===")
        println("Actual result:")
        println(fixture.file.text)
        println("=== End ${testCase.name} ===")
        
        fixture.checkResult(testCase.expected)
    }

    data class TestCase(
        val name: String,
        @param:Language("JAVA") val input: String,
        @param:Language("JAVA") val expected: String
    )
}
