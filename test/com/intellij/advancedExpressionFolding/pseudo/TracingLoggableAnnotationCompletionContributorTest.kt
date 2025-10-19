package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.xdebugger.XDebuggerManager
import com.intellij.xdebugger.breakpoints.XLineBreakpoint
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TracingLoggableAnnotationCompletionContributorTest : BaseTest() {

    private val annotationName = AnnotationName("TracingLoggable")

    private lateinit var settings: AdvancedExpressionFoldingSettings
    private var originalPseudoAnnotationsValue: Boolean = false

    @BeforeEach
    fun setUpTest() {
        settings = AdvancedExpressionFoldingSettings.getInstance()
        originalPseudoAnnotationsValue = settings.state.pseudoAnnotations
        settings.state.pseudoAnnotations = true
        clearBreakpoints()
    }

    @AfterEach
    fun tearDownTest() {
        settings.state.pseudoAnnotations = originalPseudoAnnotationsValue
        clearBreakpoints()
    }

    @Test
    fun `should offer @TracingLoggable in completion for annotation above method`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                public class Test {
                    @<caret>
                    public void doWork(int value) {
                        System.out.println(value);
                    }
                }
            """.trimIndent(),
        )

        val completions = fixture.complete(CompletionType.BASIC) ?: error("Expected completion results")
        assertTrue(completions.any { it.lookupString == annotationName.value })
    }

    @Test
    fun `should create tracing breakpoints when selecting @TracingLoggable from completion`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                public class Test {
                    @<caret>
                    public void doWork(int value, String name) {
                        System.out.println(value + name);
                    }
                }
            """.trimIndent(),
        )

        selectTracingLoggableCompletion()

        val breakpoints = fileBreakpoints()
        assertEquals(2, breakpoints.size, "Expected entry and exit tracing breakpoints")

        val expressions = breakpoints.mapNotNull { it.logExpressionObject?.expression }.toSet()
        assertTrue(expressions.contains("\"Entering Test.doWork\" + \" with args: \" + \"value=\" + value + \", \" + \"name=\" + name"))
        assertTrue(expressions.contains("\"Exiting Test.doWork\""))
    }

    @Test
    fun `should remove tracing breakpoints when selecting @TracingLoggable on already traced method`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                public class Test {
                    @<caret>
                    public void doWork(int value) {
                        System.out.println(value);
                    }
                }
            """.trimIndent(),
        )

        selectTracingLoggableCompletion()
        assertTrue(fileBreakpoints().isNotEmpty(), "Expected tracing breakpoints to be created")

        addAnnotationPlaceholderBefore("doWork")
        selectTracingLoggableCompletion()

        assertTrue(fileBreakpoints().isEmpty(), "Expected tracing breakpoints to be removed")
    }

    @Test
    fun `should create tracing breakpoints for each method when applied at class level`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
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
        )

        selectTracingLoggableCompletion()

        val breakpoints = fileBreakpoints()
        val expressions = breakpoints.mapNotNull { it.logExpressionObject?.expression }.filter { it.startsWith("\"Entering") }

        assertEquals(setOf("\"Entering Test.first\" + \" with args: \" + \"value=\" + value", "\"Entering Test.second\""), expressions.toSet())
    }

    @Test
    fun `should remove tracing breakpoints when selecting @TracingLoggable on already traced class`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
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
        )

        selectTracingLoggableCompletion()
        assertTrue(fileBreakpoints().isNotEmpty(), "Expected tracing breakpoints to be created")

        addAnnotationPlaceholderBeforeClass()
        selectTracingLoggableCompletion()

        assertTrue(fileBreakpoints().isEmpty(), "Expected tracing breakpoints to be removed")
    }

    private fun selectTracingLoggableCompletion() {
        val completions = fixture.complete(CompletionType.BASIC) ?: error("Expected completion results")

        val tracingCompletion = completions.find { it.lookupString == annotationName.value }
            ?: error("TracingLoggable completion not found")

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = tracingCompletion
            fixture.finishLookup('\n')
            PsiDocumentManager.getInstance(fixture.project).commitAllDocuments()
        }
    }

    private fun addAnnotationPlaceholderBefore(methodName: String) {
        val offset = runReadAction {
            PsiTreeUtil.findChildrenOfType(fixture.file, PsiMethod::class.java).first { it.name == methodName }
                .textRange.startOffset
        }

        ApplicationManager.getApplication().invokeAndWait {
            WriteCommandAction.runWriteCommandAction(fixture.project) {
                fixture.editor.document.insertString(offset, "@\n")
            }
            fixture.editor.caretModel.moveToOffset(offset + 1)
            PsiDocumentManager.getInstance(fixture.project).commitAllDocuments()
        }
    }

    private fun addAnnotationPlaceholderBeforeClass() {
        val offset = runReadAction {
            PsiTreeUtil.findChildOfType(fixture.file, PsiClass::class.java)?.textRange?.startOffset
                ?: error("Expected class in file")
        }

        ApplicationManager.getApplication().invokeAndWait {
            WriteCommandAction.runWriteCommandAction(fixture.project) {
                fixture.editor.document.insertString(offset, "@\n")
            }
            fixture.editor.caretModel.moveToOffset(offset + 1)
            PsiDocumentManager.getInstance(fixture.project).commitAllDocuments()
        }
    }

    private fun clearBreakpoints() {
        val breakpointManager = XDebuggerManager.getInstance(fixture.project).breakpointManager
        breakpointManager.allBreakpoints.forEach { breakpointManager.removeBreakpoint(it) }
    }

    private fun fileBreakpoints(): List<XLineBreakpoint<*>> {
        val file = fixture.file.virtualFile
        val breakpointManager = XDebuggerManager.getInstance(fixture.project).breakpointManager
        return breakpointManager.allBreakpoints
            .filterIsInstance<XLineBreakpoint<*>>()
            .filter { it.sourcePosition?.file == file }
    }
}
