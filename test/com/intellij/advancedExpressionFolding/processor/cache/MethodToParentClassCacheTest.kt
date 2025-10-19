package com.intellij.advancedExpressionFolding.processor.cache

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.processor.lombok.SummaryParentOverrideExt.addParentSummary
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiJavaFile
import com.intellij.openapi.application.runReadAction
import org.jetbrains.annotations.TestOnly
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@TestOnly
class MethodToParentClassCacheTest : BaseTest() {

    @Test
    fun validateAllKeysAreReflected() {
        val keys = runReadAction { Keys.allKeys }
        assertTrue(Keys.METHOD_TO_PARENT_CLASS_KEY in keys)
    }

    @Test
    fun clearsMethodToParentClassCache() {
        val psiFile = fixture.configureByText(
            "Child.java",
            """
            interface Parent {
                void method();
            }

            class Child implements Parent {
                @Override
                public void method() {}
            }
            """.trimIndent()
        ) as PsiJavaFile

        runReadAction {
            psiFile.requiredChildClass("Child").addParentSummary()
        }

        val cached = runReadAction {
            psiFile.requiredChildClass("Child")
                .getUserData(Keys.METHOD_TO_PARENT_CLASS_KEY)
        }
        assertNotNull(cached)
        assertTrue(cached!!.isNotEmpty())

        runReadAction {
            Keys.clearAll(psiFile.requiredChildClass("Child"))
        }

        val cleared = runReadAction {
            psiFile.requiredChildClass("Child")
                .getUserData(Keys.METHOD_TO_PARENT_CLASS_KEY)
        }
        assertNull(cleared)
    }

    private fun PsiJavaFile.childClass(name: String): PsiClass? =
        classes.firstOrNull { it.name == name }

    private fun PsiJavaFile.requiredChildClass(name: String): PsiClass =
        childClass(name) ?: error("Child class '$name' not found")
}
