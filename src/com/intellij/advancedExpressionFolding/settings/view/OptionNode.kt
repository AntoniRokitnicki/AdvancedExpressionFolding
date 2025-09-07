package com.intellij.advancedExpressionFolding.settings.view

import kotlin.reflect.KMutableProperty0

/**
 * Represents a single option in the settings tree. The tree is built from a stable set of
 * [OptionNode] instances where each leaf node holds a reference to the corresponding settings
 * property. Parent nodes compute their selected state based on their children and propagate
 * selection to descendants.
 */
data class OptionNode(
    val id: String,
    val label: String,
    val description: String? = null,
    val property: KMutableProperty0<Boolean>? = null,
    val tags: List<String> = emptyList(),
    val enabled: Boolean = true,
    val children: List<OptionNode> = emptyList()
) {
    /**
     * Current selection state of this node. For leaf nodes it directly reflects the value of
     * [property]. For parent nodes it is derived from children.
     */
    fun isSelected(): Boolean = property?.get() ?: children.all { it.isSelected() }

    /**
     * Sets the selection state. For branch nodes the value is propagated to all descendants.
     */
    fun setSelected(value: Boolean) {
        property?.set(value)
        children.forEach { it.setSelected(value) }
    }

    /**
     * Determines whether this node or any of its metadata matches the provided [query].
     */
    fun matches(query: String): Boolean {
        if (query.isEmpty()) return true
        val lower = query.lowercase()
        if (label.lowercase().contains(lower)) return true
        if (description?.lowercase()?.contains(lower) == true) return true
        return tags.any { it.lowercase().contains(lower) }
    }
}

