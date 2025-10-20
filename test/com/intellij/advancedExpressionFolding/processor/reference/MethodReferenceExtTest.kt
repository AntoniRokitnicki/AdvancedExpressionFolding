package com.intellij.advancedExpressionFolding.processor.reference

import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalMapSafeCallParam
import com.intellij.advancedExpressionFolding.expression.operation.stream.StreamMapCallParam
import com.intellij.advancedExpressionFolding.processor.reference.MethodReferenceExt.createExpression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.projectRoots.JavaSdk
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.psi.PsiMethodReferenceExpression
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase5
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MethodReferenceExtTest : LightJavaCodeInsightFixtureTestCase5(TEST_JDK) {

    override fun getTestDataPath(): String = ""

    @Test
    fun optionalMapProducesOptionalParam() {
        val file = fixture.configureByText(
            "OptionalMethodReference.java",
            """
            import java.util.Optional;

            class My {
                String getName() { return ""; }
            }

            class Test {
                void test() {
                    Optional.of(new My()).map(My::getName);
                }
            }
            """.trimIndent()
        )

        val methodReference = findMethodReference(file)
        val settings = AdvancedExpressionFoldingSettings.getInstance().state
        val originalOptional = settings.optional
        val originalStream = settings.streamSpread

        try {
            settings.optional = true
            settings.streamSpread = false

            val expression = runReadAction { createExpression(methodReference) }
            val optionalParam = assertInstanceOf(OptionalMapSafeCallParam::class.java, expression)
            assertEquals("name", optionalParam.getString())

            settings.optional = false
            settings.streamSpread = false

            assertNull(runReadAction { createExpression(methodReference)})
        } finally {
            settings.optional = originalOptional
            settings.streamSpread = originalStream
        }
    }

    @Test
    fun streamMapProducesStreamParam() {
        val file = fixture.configureByText(
            "StreamMethodReference.java",
            """
            import java.util.stream.Stream;

            class My {
                String getName() { return ""; }
            }

            class Test {
                void test() {
                    Stream.of(new My()).map(My::getName);
                }
            }
            """.trimIndent()
        )

        val methodReference = findMethodReference(file)
        val settings = AdvancedExpressionFoldingSettings.getInstance().state
        val originalOptional = settings.optional
        val originalStream = settings.streamSpread

        try {
            settings.optional = false
            settings.streamSpread = true

            val expression = runReadAction { createExpression(methodReference) }
            val streamParam = assertInstanceOf(StreamMapCallParam::class.java, expression)
            assertEquals("name()", streamParam.getString())

            settings.optional = false
            settings.streamSpread = false

            assertNull(runReadAction { createExpression(methodReference) })
        } finally {
            settings.optional = originalOptional
            settings.streamSpread = originalStream
        }
    }

    @Test
    fun methodReferenceWithParametersIsIgnored() {
        val file = fixture.configureByText(
            "MethodReferenceWithParameters.java",
            """
            import java.util.stream.Stream;

            class Test {
                void test(String value) {
                    Stream.of(value).map(value::concat);
                }
            }
            """.trimIndent()
        )

        val methodReference = findMethodReference(file)
        val settings = AdvancedExpressionFoldingSettings.getInstance().state
        val originalOptional = settings.optional
        val originalStream = settings.streamSpread

        try {
            settings.optional = false
            settings.streamSpread = true

            assertNull(runReadAction { createExpression(methodReference) })
        } finally {
            settings.optional = originalOptional
            settings.streamSpread = originalStream
        }
    }

    private fun findMethodReference(file: com.intellij.psi.PsiFile): PsiMethodReferenceExpression {
        val methodReference = runReadAction {
            PsiTreeUtil.findChildOfType(file, PsiMethodReferenceExpression::class.java)
        }
        return methodReference ?: fail("Expected to locate a PsiMethodReferenceExpression")
    }

    companion object {
        private val TEST_JDK: DefaultLightProjectDescriptor = object : DefaultLightProjectDescriptor() {
            override fun getSdk(): Sdk {
                return JavaSdk.getInstance().createJdk("Test JDK", System.getProperty("java.home"), true)
            }
        }
    }
}
