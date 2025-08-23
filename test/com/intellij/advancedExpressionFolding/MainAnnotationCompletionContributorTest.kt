package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.application.runInEdt
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

class MainAnnotationCompletionContributorTest : BaseTest() {

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
                        public static void main(String[] args) {
                            int x = 0;
                            staticMethod(x);
                        }
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
                        public static void main(String[] args) {
                            String s = null;
                            new Test().instanceMethod(s);
                        }
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
                        public static void main(String[] args) {
                            System.out.println(new Test().getValue());
                        }
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
                        public static void main(String[] args) {
                            int param = 0;
                    
                            String s = null;
                            new Test(param).testMethod(s);
                        }
                        public Test(int param) {
                        }
                        
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
                        public static void main(String[] args) {
                            String[] args = new String[]{};
                            new Test().stringVarargs(args);
                        }
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
                            new Test().newMethod();
                        }
                    
                        public void newMethod() {
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
                        public static void main(String[] args) {
                            boolean b = false;
                            char c = '\0';
                            byte by = 0;
                            short s = 0;
                            int i = 0;
                            long l = 0;
                            float f = 0.0f;
                            double d = 0.0;
                            new Test().primitiveParams(b, c, by, s, i, l, f, d);
                        }
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
                        public static void main(String[] args) {
                            Date date = new java.util.Date();
                            LocalDate ld = java.time.LocalDate.now();
                            LocalDateTime ldt = java.time.LocalDateTime.now();
                            ZonedDateTime zdt = java.time.ZonedDateTime.now();
                            new Test().dateParams(date, ld, ldt, zdt);
                        }
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
    fun `should generate main method when selecting @Main from completion`(testCase: TestCase) {
        fixture.configureByText("Test.java", testCase.input)
        
        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        
        val mainCompletion = completions.find { it.lookupString == "Main" }
        assertNotNull(mainCompletion)

        runInEdt {
            fixture.lookup.currentItem = mainCompletion
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
