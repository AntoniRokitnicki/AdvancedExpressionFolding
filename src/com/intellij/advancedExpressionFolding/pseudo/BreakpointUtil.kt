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

    fun toggleBreakpoint(project: Project, file: VirtualFile, lineNumber: Int, logExpression: String? = null, groupName: String? = null) {
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
                    addGroup(groupName)
                    logExpression ?: return@apply
                    suspendPolicy = SuspendPolicy.NONE
                    isLogMessage = true
                    logExpressionObject = util.createExpression(logExpression, JavaLanguage.INSTANCE, null, EvaluationMode.EXPRESSION)
                }
        }
    }

    fun XLineBreakpoint<*>.addGroup(groupName: String?) {
        try {
            val method = javaClass.getMethod("setGroup", String::class.java)
            method.isAccessible = true
            method.invoke(this, groupName)
        } catch (_: Exception) {
            // ignore
        }
    }

    fun removeClassLogBreakpoints(
        project: Project,
        file: VirtualFile,
        className: String?,
        vararg markers: String,
    ) {
        val breakpointManager = XDebuggerManager.getInstance(project).breakpointManager
        val matchingBreakpoints = breakpointManager.allBreakpoints
            .filterIsInstance<XLineBreakpoint<*>>()
            .filter { breakpoint -> breakpoint.sourcePosition?.file == file }
            .filter { breakpoint -> breakpoint.isLogMessage && breakpoint.suspendPolicy == SuspendPolicy.NONE }
            .filter { breakpoint ->
                if (className == null || markers.isEmpty()) {
                    return@filter true
                }

                val expression = breakpoint.logExpressionObject?.expression ?: return@filter false
                markers.any { marker ->
                    val base = "\"$marker $className"
                    expression.contains("$base.") || expression.contains("$base<")
                }
            }

        matchingBreakpoints.forEach { breakpointManager.removeBreakpoint(it) }
    }
}
