package com.intellij.advancedExpressionFolding.settings.tree

/**
 * Describes single configurable option.
 */
data class OptionDescriptor(
    val id: String,
    val label: String,
    val description: String,
    val defaultValue: Boolean,
    var currentValue: Boolean,
    val categoryPath: List<String>
)
