package com.intellij.advancedExpressionFolding.settings.tree

/**
 * Model storing tree of options and providing operations for toggling nodes.
 */
class OptionModel(descriptors: List<OptionDescriptor>) {
    val root: OptionTreeNode.ParentNode = TreeBuilder().build(descriptors)

    private val leafById: Map<String, OptionTreeNode.LeafNode> = buildLeafIndex()

    init {
        root.computeTriState()
    }

    private fun buildLeafIndex(): Map<String, OptionTreeNode.LeafNode> {
        val map = mutableMapOf<String, OptionTreeNode.LeafNode>()
        fun collect(node: OptionTreeNode) {
            when (node) {
                is OptionTreeNode.LeafNode -> map[node.descriptor.id] = node
                is OptionTreeNode.ParentNode -> node.children.forEach { collect(it) }
            }
        }
        collect(root)
        return map
    }

    fun toggleLeaf(id: String) {
        leafById[id]?.toggle()
    }

    fun toggleParent(path: List<String>) {
        findParent(path)?.toggle()
    }

    fun findParent(path: List<String>): OptionTreeNode.ParentNode? {
        var current: OptionTreeNode.ParentNode = root
        for (segment in path) {
            val child = current.children.find { it is OptionTreeNode.ParentNode && it.name == segment } as OptionTreeNode.ParentNode?
                ?: return null
            current = child
        }
        return current
    }
}
