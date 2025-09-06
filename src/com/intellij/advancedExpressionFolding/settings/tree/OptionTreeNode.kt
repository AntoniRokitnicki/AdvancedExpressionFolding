package com.intellij.advancedExpressionFolding.settings.tree

/**
 * Node of option tree representing either a parent category or a leaf option.
 */
sealed class OptionTreeNode(val name: String) {
    var parent: ParentNode? = null

    open fun computeTriState(): TriState = TriState.UNCHECKED

    class ParentNode(name: String) : OptionTreeNode(name) {
        val children: MutableList<OptionTreeNode> = mutableListOf()
        var triState: TriState = TriState.UNCHECKED

        override fun computeTriState(): TriState {
            val childStates = children.map { it.computeTriState() }
            triState = when {
                childStates.isEmpty() -> TriState.UNCHECKED
                childStates.all { it == TriState.CHECKED } -> TriState.CHECKED
                childStates.all { it == TriState.UNCHECKED } -> TriState.UNCHECKED
                else -> TriState.PARTIAL
            }
            return triState
        }

        fun toggle() {
            val check = triState != TriState.CHECKED
            children.forEach { setCheckedRecursively(it, check) }
            computeTriStateUpwards()
        }

        private fun setCheckedRecursively(node: OptionTreeNode, checked: Boolean) {
            when (node) {
                is LeafNode -> node.selected = checked
                is ParentNode -> {
                    node.children.forEach { setCheckedRecursively(it, checked) }
                    node.triState = if (checked) TriState.CHECKED else TriState.UNCHECKED
                }
            }
        }

        fun computeTriStateUpwards() {
            computeTriState()
            parent?.computeTriStateUpwards()
        }
    }

    class LeafNode(val descriptor: OptionDescriptor) : OptionTreeNode(descriptor.id) {
        var selected: Boolean = descriptor.currentValue

        override fun computeTriState(): TriState = if (selected) TriState.CHECKED else TriState.UNCHECKED

        fun toggle() {
            selected = !selected
            descriptor.currentValue = selected
            parent?.computeTriStateUpwards()
        }
    }
}
