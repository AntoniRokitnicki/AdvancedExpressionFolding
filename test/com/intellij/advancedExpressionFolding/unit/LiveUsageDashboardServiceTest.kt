package com.intellij.advancedExpressionFolding.unit

import com.intellij.advancedExpressionFolding.folding.BaseTest
import com.intellij.advancedExpressionFolding.view.dashboard.LiveUsageDashboardService
import com.intellij.psi.PsiJavaFile
import com.intellij.openapi.application.runReadAction
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.PsiTreeUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LiveUsageDashboardServiceTest : BaseTest() {

    @Test
    fun `records usages and tracks distinct files`() {
        val project = fixture.project
        val service = LiveUsageDashboardService.getInstance(project)
        service.reset()

        val firstFile: PsiJavaFile = fixture.configureByText(
            "Foo.java",
            """
            class Foo {
                void first() {}
                void second() {}
            }
            """.trimIndent()
        ) as PsiJavaFile
        val secondFile: PsiJavaFile = fixture.configureByText(
            "Bar.java",
            """
            class Bar {
                void sample() {}
            }
            """.trimIndent()
        ) as PsiJavaFile

        runReadAction {
            val firstMethods = PsiTreeUtil.collectElementsOfType(firstFile, PsiMethod::class.java).toList()
            val secondMethods = PsiTreeUtil.collectElementsOfType(secondFile, PsiMethod::class.java).toList()
            val firstMethod = firstMethods[0]
            val secondMethod = firstMethods[1]
            val thirdMethod = secondMethods[0]

            service.recordUsage(firstFile, firstMethod.textRange)
            service.recordUsage(firstFile, secondMethod.textRange)
            service.recordUsage(secondFile, thirdMethod.textRange)
        }

        val snapshot = service.snapshot()
        assertEquals(3, snapshot.totalUsages)
        assertEquals(2, snapshot.distinctFiles)
        assertEquals(3, snapshot.history.size)
        assertTrue(snapshot.history.first().contains("Foo.java"))
    }
}
