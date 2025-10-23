package com.intellij.advancedExpressionFolding

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

class ObservatorAnnotationCompletionContributorTest : BaseTest() {

    companion object {
        private var originalPseudoAnnotationsValue: Boolean = false

        @JvmStatic
        fun testCases(): Stream<Arguments> = Stream.of(
            Arguments.of(TestCase(
                name = "Single Field",
                input = """
                    public class Test {
                        @<caret>
                        private String name;
                    }
                """.trimIndent(),
                expected = """
                public class Test {
                    private final java.beans.PropertyChangeSupport observatorSupport = new java.beans.PropertyChangeSupport(this);

                    private String name;

                    public void setName(String name) {
                        String oldValue = this.name;
                        this.name = name;
                        observatorSupport.firePropertyChange("name", oldValue, name);
                    }

                    public void addNameListener(java.beans.PropertyChangeListener listener) {
                        observatorSupport.addPropertyChangeListener("name", listener);
                    }

                    public void removeNameListener(java.beans.PropertyChangeListener listener) {
                        observatorSupport.removePropertyChangeListener("name", listener);
                    }
                }
                """.trimIndent()
            )),
            Arguments.of(TestCase(
                name = "Class Level",
                input = """
                    @<caret>
                    public class Test {
                        private int count;
                        private String description;
                    }
                """.trimIndent(),
                expected = """
                public class Test {
                    private final java.beans.PropertyChangeSupport observatorSupport = new java.beans.PropertyChangeSupport(this);

                    private int count;
                    private String description;

                    public void setCount(int count) {
                        int oldValue = this.count;
                        this.count = count;
                        observatorSupport.firePropertyChange("count", oldValue, count);
                    }

                    public void addCountListener(java.beans.PropertyChangeListener listener) {
                        observatorSupport.addPropertyChangeListener("count", listener);
                    }

                    public void removeCountListener(java.beans.PropertyChangeListener listener) {
                        observatorSupport.removePropertyChangeListener("count", listener);
                    }

                    public void setDescription(String description) {
                        String oldValue = this.description;
                        this.description = description;
                        observatorSupport.firePropertyChange("description", oldValue, description);
                    }

                    public void addDescriptionListener(java.beans.PropertyChangeListener listener) {
                        observatorSupport.addPropertyChangeListener("description", listener);
                    }

                    public void removeDescriptionListener(java.beans.PropertyChangeListener listener) {
                        observatorSupport.removePropertyChangeListener("description", listener);
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
    fun `should offer @Observator in completion for field`() {
        fixture.configureByText("Test.java", @Language("JAVA") """
            public class Test {
                @<caret>
                private int count;
            }
        """.trimIndent())

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        assertTrue(completions.any { it.lookupString == "Observator" })
    }

    @ParameterizedTest
    @MethodSource("testCases")
    fun `should generate observation helpers when selecting @Observator`(testCase: TestCase) {
        fixture.configureByText("Test.java", testCase.input)

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val completion = completions.find { it.lookupString == "Observator" }
        assertNotNull(completion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = completion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(testCase.expected)
    }

    data class TestCase(
        val name: String,
        @param:Language("JAVA") val input: String,
        @param:Language("JAVA") val expected: String
    ) {
        override fun toString(): String = name
    }
}
