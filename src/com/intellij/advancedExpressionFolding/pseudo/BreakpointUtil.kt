package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.debugger.ui.breakpoints.JavaLineBreakpointType
import com.intellij.lang.java.JavaLanguage
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiManager
import com.intellij.xdebugger.XDebuggerManager
import com.intellij.xdebugger.XDebuggerUtil
import com.intellij.xdebugger.breakpoints.SuspendPolicy
import com.intellij.xdebugger.evaluation.EvaluationMode
import kotlin.math.max

object BreakpointUtil {
    fun toggleTracingBreakpoint(project: Project, file: VirtualFile, lineNumber: Int, logExpression: String) {
        runReadAction {
            PsiManager.getInstance(project).findFile(file)?.let { psiFile ->
                PsiDocumentManager.getInstance(project).getDocument(psiFile)
            }
        }?.let { doc ->
            if (doc.lineCount == 0) return
            val line = max(0, lineNumber - 1).coerceAtMost(doc.lineCount - 1)
            val util = XDebuggerUtil.getInstance()
            val bpType = util.findBreakpointType(JavaLineBreakpointType::class.java) ?: return
            if (!util.canPutBreakpointAt(project, file, line)) return

            val bpMgr = XDebuggerManager.getInstance(project).breakpointManager
            bpMgr.allBreakpoints.find { it.sourcePosition?.file == file && it.sourcePosition?.line == line }
                ?.let { bpMgr.removeBreakpoint(it) }
                ?: bpMgr.addLineBreakpoint(bpType, file.url, line, bpType.createBreakpointProperties(file, line)).apply {
                    suspendPolicy = SuspendPolicy.NONE
                    isLogMessage = true
                    logExpressionObject = util.createExpression(logExpression, JavaLanguage.INSTANCE, null, EvaluationMode.EXPRESSION)
                }
        }
    }
}
