package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiClass
import com.intellij.xdebugger.XDebuggerManager
import com.intellij.xdebugger.breakpoints.XLineBreakpoint

class BreakpointableAnnotationCompletionContributor : TracingLoggableAnnotationCompletionContributor() {

    override val annotationName: String = "Breakpointable"

    override fun toggleBreakpoint(
        project: Project,
        file: VirtualFile,
        lineNumber: Int,
        logExpression: String,
    ) {
        BreakpointUtil.toggleBreakpoint(
            project,
            file,
            lineNumber,
            groupName = "$annotationName-${file.name}",
        )
    }

    override fun shouldRemoveClassLogging(psiClass: PsiClass): Boolean {
        val className = psiClass.name ?: return false
        val virtualFile = psiClass.containingFile?.virtualFile ?: return false
        val breakpointManager = XDebuggerManager.getInstance(psiClass.project).breakpointManager
        return breakpointManager.allBreakpoints
            .filterIsInstance<XLineBreakpoint<*>>()
            .any { breakpoint ->
                val position = breakpoint.sourcePosition ?: return@any false
                if (position.file != virtualFile) return@any false
                return@any breakpoint.logExpressionObject?.expression == null
            }
    }

    override fun removeClassLogging(psiClass: PsiClass) {
        val className = psiClass.name ?: return
        val virtualFile = psiClass.containingFile?.virtualFile ?: return
        BreakpointUtil.removeClassLogBreakpoints(
            psiClass.project,
            virtualFile,
            className
        )
    }
}
