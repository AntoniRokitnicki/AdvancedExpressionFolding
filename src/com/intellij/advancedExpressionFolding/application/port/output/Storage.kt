package com.intellij.advancedExpressionFolding.application.port.output

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document

fun interface Storage {
    fun store(
        foldingDescriptors: Array<FoldingDescriptor>,
        document: Document
    ): Array<FoldingDescriptor>
}

