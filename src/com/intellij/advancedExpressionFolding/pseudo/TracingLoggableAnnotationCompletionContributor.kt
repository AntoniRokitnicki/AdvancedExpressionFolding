package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter
import com.intellij.xdebugger.XDebuggerManager
import com.intellij.xdebugger.breakpoints.SuspendPolicy
import com.intellij.xdebugger.breakpoints.XLineBreakpoint

class TracingLoggableAnnotationCompletionContributor : AbstractLoggingAnnotationCompletionContributor() {

    override val annotationName: String = "TracingLoggable"

    override fun shouldRemoveClassLogging(psiClass: PsiClass): Boolean {
        val className = psiClass.name ?: return false
        val virtualFile = psiClass.containingFile?.virtualFile ?: return false
        val breakpointManager = XDebuggerManager.getInstance(psiClass.project).breakpointManager
        return breakpointManager.allBreakpoints
            .filterIsInstance<XLineBreakpoint<*>>()
            .any { breakpoint ->
                val position = breakpoint.sourcePosition ?: return@any false
                if (position.file != virtualFile) return@any false
                if (!breakpoint.isLogMessage || breakpoint.suspendPolicy != SuspendPolicy.NONE) return@any false
                val expression = breakpoint.logExpressionObject?.expression ?: return@any false
                val enteringMarker = "\"$ENTERING $className"
                val exitingMarker = "\"$EXITING $className"
                expression.contains("$enteringMarker.") ||
                    expression.contains("$enteringMarker<") ||
                    expression.contains("$exitingMarker.") ||
                    expression.contains("$exitingMarker<")
            }
    }

    override fun removeClassLogging(psiClass: PsiClass) {
        val className = psiClass.name ?: return
        val virtualFile = psiClass.containingFile?.virtualFile ?: return
        BreakpointUtil.removeClassLogBreakpoints(
            psiClass.project,
            virtualFile,
            className,
            ENTERING,
            EXITING,
        )
    }

    override fun isAlreadyLogged(body: PsiCodeBlock): Boolean {
        val method = body.parent as? PsiMethod ?: return false
        val target = resolveTarget(method, body) ?: return false
        val entryExists = hasTracingBreakpoint(method.project, target.file, target.entryLine, buildEntryExpression(method))
        if (!entryExists) return false
        val exitLine = target.exitLine ?: return true
        return hasTracingBreakpoint(method.project, target.file, exitLine, buildExitExpression(method))
    }

    override fun addLogging(method: PsiMethod, body: PsiCodeBlock) {
        val target = resolveTarget(method, body) ?: return
        BreakpointUtil.toggleBreakpoint(method.project, target.file, target.entryLine, buildEntryExpression(method))
        target.exitLine?.let { exitLine ->
            BreakpointUtil.toggleBreakpoint(method.project, target.file, exitLine, buildExitExpression(method))
        }
    }

    override fun removeLogging(method: PsiMethod) {
        val body = method.body ?: return
        val target = resolveTarget(method, body) ?: return
        if (hasTracingBreakpoint(method.project, target.file, target.entryLine, buildEntryExpression(method))) {
            BreakpointUtil.toggleBreakpoint(method.project, target.file, target.entryLine, buildEntryExpression(method))
        }
        target.exitLine?.let { exitLine ->
            if (hasTracingBreakpoint(method.project, target.file, exitLine, buildExitExpression(method))) {
                BreakpointUtil.toggleBreakpoint(method.project, target.file, exitLine, buildExitExpression(method))
            }
        }
    }

    private fun resolveTarget(method: PsiMethod, body: PsiCodeBlock): LoggingTarget? {
        val psiFile = method.containingFile ?: return null
        val virtualFile = psiFile.virtualFile ?: return null
        val project = method.project
        val document = PsiDocumentManager.getInstance(project).getDocument(psiFile) ?: return null

        val entryElement = method.nameIdentifier ?: method
        val entryLine = document.getLineNumber(entryElement.textRange.startOffset) + 1

        val statements = body.statements
        val lastStatement = statements.lastOrNull()
        val exitCandidate = when {
            lastStatement != null -> document.getLineNumber(lastStatement.textRange.startOffset) + 1
            else -> body.rBrace?.let { document.getLineNumber(it.textRange.startOffset) + 1 }
        }

        val exitLine = exitCandidate?.takeIf { it != entryLine }

        return LoggingTarget(virtualFile, entryLine, exitLine)
    }

    private fun hasTracingBreakpoint(project: Project, file: VirtualFile, lineNumber: Int, logExpression: String): Boolean {
        val zeroBasedLine = lineNumber - 1
        val breakpointManager = XDebuggerManager.getInstance(project).breakpointManager
        return breakpointManager.allBreakpoints
            .filterIsInstance<XLineBreakpoint<*>>()
            .any { breakpoint ->
                val position = breakpoint.sourcePosition
                position?.file == file &&
                    position.line == zeroBasedLine &&
                    breakpoint.isLogMessage &&
                    breakpoint.suspendPolicy == SuspendPolicy.NONE &&
                    breakpoint.logExpressionObject?.expression == logExpression
            }
    }

    private fun buildEntryExpression(method: PsiMethod): String {
        val methodLabel = methodLabel(method)
        val parameterNames = method.parameterList.parameters.mapNotNull(PsiParameter::getName)
        val base = "\"$ENTERING $methodLabel\""
        if (parameterNames.isEmpty()) {
            return base
        }

        val parameterExpressions = parameterNames.map { "\"$it=\" + $it" }
        val joinedParameters = parameterExpressions.joinToString(separator = " + \", \" + ")
        return "$base + \" with args: \" + $joinedParameters"
    }

    private fun buildExitExpression(method: PsiMethod): String {
        val methodLabel = methodLabel(method)
        return "\"$EXITING $methodLabel\""
    }

    private fun methodLabel(method: PsiMethod): String {
        val className = method.containingClass?.name ?: "Anonymous"
        return if (method.isConstructor) {
            "$className.<init>"
        } else {
            "$className.${method.name}"
        }
    }

    private data class LoggingTarget(val file: VirtualFile, val entryLine: Int, val exitLine: Int?)

    private companion object {
        private const val ENTERING = "Entering"
        private const val EXITING = "Exiting"
    }
}
