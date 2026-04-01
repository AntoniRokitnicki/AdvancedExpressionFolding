package com.intellij.advancedExpressionFolding.kotlin.pseudoAnnotations

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.Computable
import com.intellij.openapi.projectRoots.JavaSdk
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase5
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.psiUtil.getStrictParentOfType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class KotlinMainAnnotationCompletionContributorTest : LightJavaCodeInsightFixtureTestCase5(TEST_JDK) {

    private var originalPseudoAnnotationsValue: Boolean = false

    override fun getTestDataPath(): String = ""

    @BeforeEach
    fun setUpSetting() {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        originalPseudoAnnotationsValue = settings.state.pseudoAnnotations
        settings.state.pseudoAnnotations = true
    }

    @AfterEach
    fun restoreSetting() {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        settings.state.pseudoAnnotations = originalPseudoAnnotationsValue
    }

    @Test
    fun `should generate main for top level function`() {
        val generator = KotlinMainGenerator()
        fixture.configureByText(
            "Test.kt",
            """
            fun helper(value: Int) {
            }

            @<caret>
            fun target(name: String) {
            }
            """.trimIndent()
        )

        val actual = buildMainFunction(generator)
        val expected = """
            fun main() {
                val name: String = ""
                target(name)
            }
        """.trimIndent()

        assertEquals(expected, actual)
    }

    @Test
    fun `should generate main for class member`() {
        val generator = KotlinMainGenerator()
        fixture.configureByText(
            "Test.kt",
            """
            class Example(val required: Int) {
                @<caret>
                fun run(vararg flag: Boolean) {
                }
            }
            """.trimIndent()
        )

        val actual = buildMainFunction(generator)
        val expected = """
            fun main() {
                val required: Int = 0
                val flag = booleanArrayOf()
                Example(required).run(*flag)
            }
        """.trimIndent()

        assertEquals(expected, actual)
    }

    private fun buildMainFunction(generator: KotlinMainGenerator): String {
        return ApplicationManager.getApplication().runReadAction(Computable {
            val offset = fixture.editor.caretModel.offset
            val function = fixture.file.findElementAt(offset)?.getStrictParentOfType<KtNamedFunction>()
                ?: error("Function not found at caret")
            generator.buildMainFunction(function) ?: error("Failed to build main function")
        })
    }

    companion object {
        private val TEST_JDK: DefaultLightProjectDescriptor = object : DefaultLightProjectDescriptor() {
            override fun getSdk(): Sdk = JavaSdk.getInstance().createJdk("Test JDK", System.getProperty("java.home"), true)
        }
    }
}
