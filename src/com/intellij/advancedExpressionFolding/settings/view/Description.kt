package com.intellij.advancedExpressionFolding.settings.view

import org.jetbrains.annotations.Nls

@JvmInline
value class Description(@Nls val text: String) {
    init {
        require(text.isNotBlank()) { "Description must not be blank." }
    }

    override fun toString(): String = text
}
