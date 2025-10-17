package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

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
}
