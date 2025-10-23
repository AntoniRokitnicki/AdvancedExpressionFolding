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

class VisitorAnnotationCompletionContributorTest : BaseTest() {

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
    fun `should offer @Visitor in completion for annotation above class`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                interface Visitor {
                }

                @<caret>
                class ConcreteElement {
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        assertTrue(completions.any { it.lookupString == "Visitor" })
    }

    @Test
    fun `should generate visitor boilerplate when selecting @Visitor from completion`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                interface Visitor {
                }

                @<caret>
                class ConcreteElement {
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val visitorCompletion = completions.find { it.lookupString == "Visitor" }
        assertNotNull(visitorCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = visitorCompletion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(
            @Language("JAVA") """
                interface Visitor {
                    void visit(ConcreteElement concreteElement);
                }

                class ConcreteElement {
                    public void accept(Visitor visitor) {
                        visitor.visit(this);
                    }
                }
            """.trimIndent()
        )
    }

    @Test
    fun `should not duplicate accept or visit methods when already present`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                interface Visitor {
                    void visit(ConcreteElement concreteElement);
                }

                @<caret>
                class ConcreteElement {
                    public void accept(Visitor visitor) {
                        visitor.visit(this);
                    }
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val visitorCompletion = completions.find { it.lookupString == "Visitor" }
        assertNotNull(visitorCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = visitorCompletion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(
            @Language("JAVA") """
                interface Visitor {
                    void visit(ConcreteElement concreteElement);
                }

                class ConcreteElement {
                    public void accept(Visitor visitor) {
                        visitor.visit(this);
                    }
                }
            """.trimIndent()
        )
    }

    @Test
    fun `should update entire hierarchy when base class is annotated`() {
        fixture.configureByText(
            "Hierarchy.java",
            @Language("JAVA") """
                interface Visitor {
                }

                @<caret>
                abstract class Shape {
                }

                class Circle extends Shape {
                }

                class Rectangle extends Shape {
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)

        val visitorCompletion = completions.find { it.lookupString == "Visitor" }
        assertNotNull(visitorCompletion)

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = visitorCompletion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(
            @Language("JAVA") """
                interface Visitor {
                    void visit(Shape shape);

                    void visit(Circle circle);

                    void visit(Rectangle rectangle);
                }

                abstract class Shape {
                    public abstract void accept(Visitor visitor);
                }

                class Circle extends Shape {
                    @Override
                    public void accept(Visitor visitor) {
                        visitor.visit(this);
                    }
                }

                class Rectangle extends Shape {
                    @Override
                    public void accept(Visitor visitor) {
                        visitor.visit(this);
                    }
                }
            """.trimIndent()
        )
    }
}
