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
import com.intellij.xdebugger.breakpoints.XLineBreakpoint
import com.intellij.xdebugger.evaluation.EvaluationMode
import kotlin.math.max

object BreakpointUtil {
    internal const val ENTERING = "Entering"
    internal const val EXITING = "Exiting"

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
            val existingBreakpoints = bpMgr.allBreakpoints
                .filterIsInstance<XLineBreakpoint<*>>()
                .filter { breakpoint ->
                    val position = breakpoint.sourcePosition
                    position?.file == file && position.line == line
                }

            val existingTracing = existingBreakpoints.firstOrNull { breakpoint ->
                breakpoint.isTracingBreakpoint(logExpression)
            }

            if (existingTracing != null) {
                bpMgr.removeBreakpoint(existingTracing)
                return
            }

            bpMgr.addLineBreakpoint(bpType, file.url, line, bpType.createBreakpointProperties(file, line)).apply {
                suspendPolicy = SuspendPolicy.NONE
                isLogMessage = true
                logExpressionObject = util.createExpression(logExpression, JavaLanguage.INSTANCE, null, EvaluationMode.EXPRESSION)
            }
        }
    }

    fun removeTracingBreakpointsForClass(
        project: Project,
        file: VirtualFile,
        className: String,
        vararg logMarkers: String,
    ) {
        if (logMarkers.isEmpty()) return
        val breakpointManager = XDebuggerManager.getInstance(project).breakpointManager
        val matchingBreakpoints = breakpointManager.allBreakpoints
            .filterIsInstance<XLineBreakpoint<*>>()
            .filter { breakpoint -> breakpoint.sourcePosition?.file == file }
            .filter { breakpoint -> breakpoint.isLogMessage && breakpoint.suspendPolicy == SuspendPolicy.NONE }
            .filter { breakpoint ->
                val expression = breakpoint.logExpressionObject?.expression ?: return@filter false
                logMarkers.any { marker ->
                    val base = "\"$marker $className"
                    expression.contains("$base.") || expression.contains("$base<")
                }
            }

        matchingBreakpoints.forEach { breakpointManager.removeBreakpoint(it) }
    }

    private fun XLineBreakpoint<*>.isTracingBreakpoint(logExpression: String): Boolean {
        if (!isLogMessage || suspendPolicy != SuspendPolicy.NONE) return false
        val expression = logExpressionObject?.expression ?: return false
        return expression == logExpression
    }
}
