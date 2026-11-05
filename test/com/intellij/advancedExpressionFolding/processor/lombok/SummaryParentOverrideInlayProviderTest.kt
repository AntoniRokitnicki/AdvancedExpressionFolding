package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.folding.BaseTest
import com.intellij.codeInsight.hints.InlayDumpUtil
import com.intellij.codeInsight.hints.declarative.impl.DeclarativeInlayHintsPassFactory
import com.intellij.codeInsight.hints.declarative.impl.util.DeclarativeHintsDumpUtil
import com.intellij.codeInsight.hints.declarative.impl.views.TextInlayPresentationEntry
import com.intellij.codeInsight.multiverse.codeInsightContext
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.progress.EmptyProgressIndicator
import com.intellij.testFramework.runInEdtAndGet
import com.intellij.testFramework.runInEdtAndWait
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SummaryParentOverrideInlayProviderTest : BaseTest() {

    private var summaryParentOverrideBeforeTest = false

    @BeforeEach
    fun enableSummaryParentOverride() {
        summaryParentOverrideBeforeTest = state.summaryParentOverride
        state.summaryParentOverride = true
    }

    @AfterEach
    fun restoreSummaryParentOverride() {
        state.summaryParentOverride = summaryParentOverrideBeforeTest
    }

    @Test
    fun inlay_classWithParents_summaryFollowsEachParentName() {
        // GIVEN - a class with a parent class, an implemented interface and a marker interface
        @Language("JAVA")
        val withExpectedInlays = """
            interface Shape {
                double area();
                int corners();
            }

            interface Named {
            }

            class Base {
                void draw() {}
            }

            class Circle extends Base/*<# (1-draw) #>*/ implements Shape/*<# (2-area, corners) #>*/, Named/*<# (nothing overridden) #>*/ {
                void draw() {}
                public double area() { return 0; }
                public int corners() { return 0; }
            }
        """.trimIndent()

        // WHEN - inlay hints are collected for the file
        val rendered = renderInlays(withExpectedInlays)

        // THEN - every parent name keeps its text and the summary follows it
        assertEquals(withExpectedInlays, rendered)
    }

    @Test
    fun inlay_genericParent_summaryBeforeTypeArguments() {
        // GIVEN - a class implementing a generic interface
        @Language("JAVA")
        val withExpectedInlays = """
            interface Repository<T> {
                int size();
            }

            class NameRepository implements Repository/*<# (1-size) #>*/<String> {
                public int size() { return 0; }
            }
        """.trimIndent()

        // WHEN - inlay hints are collected for the file
        val rendered = renderInlays(withExpectedInlays)

        // THEN - the summary sits right after the parent name, before its type arguments
        assertEquals(withExpectedInlays, rendered)
    }

    @Test
    fun inlay_optionOff_noSummary() {
        // GIVEN - the summaryParentOverride option is off
        state.summaryParentOverride = false
        @Language("JAVA")
        val withoutInlays = """
            interface Named {
            }

            class Circle implements Named {
            }
        """.trimIndent()

        // WHEN - inlay hints are collected for the file
        val rendered = renderInlays(withoutInlays)

        // THEN - no summary is shown
        assertEquals(withoutInlays, rendered)
    }

    private fun renderInlays(sourceWithExpectedInlays: String): String {
        runInEdtAndWait {
            fixture.configureByText("Circle.java", InlayDumpUtil.removeInlays(sourceWithExpectedInlays))
        }
        // the platform builds and collects the pass off the EDT, then applies it on the EDT
        val pass = runReadAction {
            DeclarativeInlayHintsPassFactory.createPassForPreview(
                fixture.file,
                fixture.editor,
                SummaryParentOverrideInlayProvider(),
                "advancedExpressionFolding.summaryParentOverride",
                emptyMap(),
                false
            ).also { pass ->
                pass.setContext(fixture.file.codeInsightContext)
                pass.doCollectInformation(EmptyProgressIndicator())
            }
        }
        return runInEdtAndGet {
            pass.applyInformationToEditor()
            DeclarativeHintsDumpUtil.dumpHints(fixture.editor.document.text, fixture.editor) { hint ->
                hint.getEntries().joinToString("") { entry -> (entry as TextInlayPresentationEntry).text }
            }
        }
    }
}
