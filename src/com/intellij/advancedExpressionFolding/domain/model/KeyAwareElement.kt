package com.intellij.advancedExpressionFolding.domain.model

interface KeyAwareElement {
    fun clearKeys()
    fun children(): Sequence<KeyAwareElement>
}
