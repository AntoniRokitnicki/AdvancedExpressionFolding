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
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class AdapterAnnotationCompletionContributorTest : BaseTest() {

    companion object {
        private var originalPseudoAnnotationsValue: Boolean = false

        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            Arguments.of(
                TestCase(
                    name = "Interface Basic",
                    input = """
                        @<caret>
                        public interface ClickListener {
                            void onClick();
                            void onValue(Integer value, boolean flag, long id);
                        }
                    """.trimIndent(),
                    expected = """
                    public interface ClickListener {
                        void onClick();
                        void onValue(Integer value, boolean flag, long id);
                    }

                    public class ClickListenerAdapter implements ClickListener {
                        @Override
                        public void onClick() {
                        }

                        @Override
                        public void onValue(Integer value, boolean flag, long id) {
                        }
                    }
                    """.trimIndent()
                )
            ),
            Arguments.of(
                TestCase(
                    name = "Throwing Implementation",
                    input = """
                        @Adapter(throw = true)
                        @<caret>
                        public interface Worker {
                            int compute();
                            String describe() throws Exception;
                        }
                    """.trimIndent(),
                    expected = """
                    public interface Worker {
                        int compute();
                        String describe() throws Exception;
                    }

                    public class WorkerAdapter implements Worker {
                        @Override
                        public int compute() {
                            throw new UnsupportedOperationException();
                        }

                        @Override
                        public String describe() throws Exception {
                            throw new UnsupportedOperationException();
                        }
                    }
                    """.trimIndent()
                )
            ),
            Arguments.of(
                TestCase(
                    name = "Primitive Wrapper Defaults",
                    input = """
                        @Adapter(primitiveWrapperReturns = DEFAULT)
                        @<caret>
                        public interface ValueProvider {
                            Boolean flag();
                            Long count();
                            Double rate();
                            Character marker();
                        }
                    """.trimIndent(),
                    expected = """
                    public interface ValueProvider {
                        Boolean flag();
                        Long count();
                        Double rate();
                        Character marker();
                    }

                    public class ValueProviderAdapter implements ValueProvider {
                        @Override
                        public Boolean flag() {
                            return false;
                        }

                        @Override
                        public Long count() {
                            return 0L;
                        }

                        @Override
                        public Double rate() {
                            return 0.0d;
                        }

                        @Override
                        public Character marker() {
                            return '\0';
                        }
                    }
                    """.trimIndent()
                )
            ),
            Arguments.of(
                TestCase(
                    name = "Abstract Class",
                    input = """
                        @<caret>
                        public abstract class Processor<T> {
                            public abstract T transform(String value);
                            protected abstract int cost();
                        }
                    """.trimIndent(),
                    expected = """
                    public abstract class Processor<T> {
                        public abstract T transform(String value);
                        protected abstract int cost();
                    }

                    public class ProcessorAdapter<T> extends Processor<T> {
                        @Override
                        public T transform(String value) {
                            return null;
                        }

                        @Override
                        protected int cost() {
                            return 0;
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
    fun `should offer @Adapter in completion for annotation above type`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                @<caret>
                public interface Demo {
                    void run();
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        assertTrue(completions.any { it.lookupString == "Adapter" })
    }

    @ParameterizedTest
    @MethodSource("testCases")
    fun `should generate adapter when selecting @Adapter from completion`(testCase: TestCase) {
        fixture.configureByText("Test.java", testCase.input)

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val adapterCompletion = completions.find { it.lookupString == "Adapter" }
        assertNotNull(adapterCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = adapterCompletion
            fixture.finishLookup('\n')
        }

        val actual = fixture.file.text
        assertEquals(testCase.expected, actual)
    }

    data class TestCase(
        val name: String,
        @param:Language("JAVA") val input: String,
        @param:Language("JAVA") val expected: String
    ) {
        override fun toString(): String = name
    }
}
