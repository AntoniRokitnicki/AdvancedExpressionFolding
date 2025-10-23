package com.intellij.advancedExpressionFolding.adapter.storage

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.advancedExpressionFolding.application.port.output.Storage

/**
 * Default no-op storage used in production.
 * Simply returns the provided folding descriptors without modification.
 */
object EmptyStorage : Storage {
    override fun store(
        foldingDescriptors: Array<FoldingDescriptor>,
        document: Document
    ): Array<FoldingDescriptor> = foldingDescriptors
}

