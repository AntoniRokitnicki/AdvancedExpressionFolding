package com.intellij.advancedExpressionFolding.application.port.out

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document

interface Storage {
    fun store(
        foldingDescriptors: Array<FoldingDescriptor>,
        document: Document
    ): Array<FoldingDescriptor>
}
