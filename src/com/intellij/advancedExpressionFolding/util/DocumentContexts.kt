package com.intellij.advancedExpressionFolding.util

import com.intellij.openapi.editor.Document

inline fun <R> withDocument(
    document: Document,
    block: context(Document) () -> R,
): R = block(document)
