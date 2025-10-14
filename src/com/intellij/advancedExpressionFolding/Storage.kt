package com.intellij.advancedExpressionFolding

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document

interface Storage {
    context(editorDocument: Document)
    fun store(
        foldingDescriptors: Array<FoldingDescriptor>
    ): Array<FoldingDescriptor>
}
