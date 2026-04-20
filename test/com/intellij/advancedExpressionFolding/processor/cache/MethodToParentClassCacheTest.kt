package com.intellij.advancedExpressionFolding.processor.cache

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.cache.Keys.METHOD_TO_PARENT_CLASS_KEY
import com.intellij.advancedExpressionFolding.processor.getSignature
import com.intellij.advancedExpressionFolding.processor.lombok.SummaryParentOverrideExt
import com.intellij.advancedExpressionFolding.processor.lombok.SummaryParentOverrideExt.addParentSummary
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.Computable
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiJavaFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MethodToParentClassCacheTest : BaseTest() {

    @Test
    fun `clearers drop override metadata`() {
        val childClass = createChildClass()

        runReadAction {
            childClass.putUserData(METHOD_TO_PARENT_CLASS_KEY, mutableMapOf())
            Keys.clearAll(childClass)
        }
        assertNull(readAction { childClass.getUserData(METHOD_TO_PARENT_CLASS_KEY) })

        runReadAction {
            childClass.putUserData(METHOD_TO_PARENT_CLASS_KEY, mutableMapOf())
            Keys.clearAllOnExpire(childClass)
        }
        assertNull(readAction { childClass.getUserData(METHOD_TO_PARENT_CLASS_KEY) })
    }

    @Test
    fun `summary parent override ext keeps metadata available`() {
        val childClass = createChildClass()
        val overrideMethod = readAction {
            with(SummaryParentOverrideExt) {
                childClass.addParentSummary()
            }
            childClass.findMethodsByName("overrideMe", false).single()
        }

        val metadata = readAction { childClass.getUserData(METHOD_TO_PARENT_CLASS_KEY) }
        assertNotNull(metadata)
        val signature = readAction { overrideMethod.getSignature() }
        val parentName = readAction { metadata!![signature] }
        assertEquals("Parent", parentName)

        val expressions = mutableListOf<Expression?>()
        readAction { SummaryParentOverrideExt.summaryParent(overrideMethod, expressions) }
        assertTrue(expressions.filterNotNull().isNotEmpty())
    }

    private fun createChildClass(): PsiClass {
        val file = fixture.configureByText(
            "SummaryParentOverride.java",
            """
            class Parent {
                void overrideMe() {}
            }

            class Child extends Parent {
                @Override
                void overrideMe() {
                    System.out.println("child");
                }
            }
            """.trimIndent()
        ) as PsiJavaFile
        return readAction { file.classes.single { it.name == "Child" } }
    }

    private fun <T> readAction(action: () -> T): T =
        runReadAction(action)

    private fun <T> runReadAction(action: () -> T): T =
        ApplicationManager.getApplication().runReadAction(Computable(action))
}
