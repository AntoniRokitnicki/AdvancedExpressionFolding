package com.intellij.advancedExpressionFolding.settings.tree

/**
 * Builds [OptionTreeNode] hierarchy from flat descriptors.
 */
class TreeBuilder {
    fun build(descriptors: List<OptionDescriptor>): OptionTreeNode.ParentNode {
        val root = OptionTreeNode.ParentNode("root")
        for (descriptor in descriptors) {
            var current = root
            for (category in descriptor.categoryPath) {
                val child = current.children.find { it is OptionTreeNode.ParentNode && it.name == category }
                val parent = if (child is OptionTreeNode.ParentNode) child else OptionTreeNode.ParentNode(category).also {
                    it.parent = current
                    current.children.add(it)
                }
                current = parent
            }
            val leaf = OptionTreeNode.LeafNode(descriptor)
            leaf.parent = current
            current.children.add(leaf)
        }
        return root
    }
}
