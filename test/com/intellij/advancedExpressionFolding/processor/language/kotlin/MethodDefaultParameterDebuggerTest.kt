package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.processor.language.kotlin.MethodDefaultParameterExt.enhanceMethodsWithDefaultParams
import com.intellij.debugger.DebuggerTestCase
import com.intellij.debugger.impl.OutputChecker
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.search.GlobalSearchScope
import java.nio.file.Paths
import java.util.function.Supplier
import org.junit.Assert.assertNull

class MethodDefaultParameterDebuggerTest : DebuggerTestCase() {

    fun testMethodDefaultParametersAreNotFoldedWhileDebugging() {
        ensureDebugSessionStarted()
        val psiClass = resolveClass("DefaultParameterVarargs")
        try {
            val expression = runReadAction { enhanceMethodsWithDefaultParams(psiClass) }
            assertNull(expression)
        } finally {
            ApplicationManager.getApplication().invokeAndWait {
                debuggerSession?.xDebugSession?.stop()
            }
            debuggerSession?.process?.processHandler?.destroyProcess()
        }
    }

    override fun getTestAppPath(): String = TEST_APP_PATH

    override fun initOutputChecker(): OutputChecker = object : OutputChecker(Supplier { "" }, Supplier { "" }) {
        override fun checkValid(sdk: Sdk) {}

        override fun checkValid(sdk: Sdk, skipNotRunConfigurations: Boolean) {}
    }

    private fun ensureDebugSessionStarted() {
        if (debuggerSession == null) {
            createLocalProcess(MAIN_CLASS_NAME)
        }
        val session = requireNotNull(debuggerSession?.xDebugSession) { "No active XDebugSession" }
        ApplicationManager.getApplication().invokeAndWait { session.resume() }
    }

    private fun resolveClass(targetClassFqn: String): PsiClass = runReadAction {
        JavaPsiFacade.getInstance(project)
            .findClass(targetClassFqn, GlobalSearchScope.allScope(project))
            ?: error("Class $targetClassFqn not found")
    }

    companion object {
        private const val MAIN_CLASS_NAME = "MethodDefaultParameterApp"
        private val TEST_APP_PATH: String = Paths.get(
            System.getProperty("user.dir"),
            "testData",
            "debug",
            "advancedExpressionFolding",
            "methodDefaultParameter",
            "app"
        ).toString()
    }
}
