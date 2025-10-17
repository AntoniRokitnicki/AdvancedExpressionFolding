package com.intellij.advancedExpressionFolding

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.Project

private const val ADVANCED_FOLDING_GROUP_MARKER = "com.intellij.advancedExpressionFolding"

val FoldingGroup?.isAdvancedExpressionFoldingGroup: Boolean
    get() = this?.toString()?.contains(ADVANCED_FOLDING_GROUP_MARKER) == true

val FoldRegion.isAdvancedExpressionFoldingGroup: Boolean
    get() = group.isAdvancedExpressionFoldingGroup

val FoldingDescriptor.isAdvancedExpressionFoldingGroup: Boolean
    get() = group.isAdvancedExpressionFoldingGroup

val Project.openTextEditors: List<Editor>
    get() = FileEditorManager.getInstance(this).allEditors
        .mapNotNull { (it as? TextEditor)?.editor }
        .filterNot(Editor::isDisposed)
