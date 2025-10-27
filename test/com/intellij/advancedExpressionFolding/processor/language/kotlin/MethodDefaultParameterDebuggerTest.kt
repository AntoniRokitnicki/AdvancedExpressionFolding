package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.processor.language.kotlin.MethodDefaultParameterExt.enhanceMethodsWithDefaultParams
import com.intellij.debugger.DebuggerTestCase
import com.intellij.openapi.application.ex.PathManagerEx
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.search.GlobalSearchScope
import java.nio.file.Paths
import kotlin.test.assertNull

class MethodDefaultParameterDebuggerTest : DebuggerTestCase() {

    fun testMethodDefaultParametersAreNotFoldedWhileDebugging() {
        ensureDebugSessionStarted()
        val psiClass = resolveClass("DefaultParameterVarargs")
        val expression = enhanceMethodsWithDefaultParams(psiClass)
        assertNull(expression)
    }

    override fun getTestAppPath(): String = TEST_APP_PATH

    private fun ensureDebugSessionStarted() {
        if (debuggerSession == null) {
            createLocalProcess(MAIN_CLASS_NAME)
        }
        requireNotNull(debuggerSession?.xDebugSession) { "No active XDebugSession" }
    }

    private fun resolveClass(targetClassFqn: String): PsiClass {
        val psiClass = JavaPsiFacade.getInstance(project)
            .findClass(targetClassFqn, GlobalSearchScope.allScope(project))
        return psiClass ?: error("Class $targetClassFqn not found")
    }

    companion object {
        private const val MAIN_CLASS_NAME = "MethodDefaultParameterApp"
        private val TEST_APP_PATH: String = Paths.get(
            PathManagerEx.getCommunityHomePath(),
            "testData",
            "debug",
            "advancedExpressionFolding",
            "methodDefaultParameter",
            "app"
        ).toString()
    }
}
