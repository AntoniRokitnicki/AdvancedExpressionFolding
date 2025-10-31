package com.intellij.advancedExpressionFolding

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document

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

