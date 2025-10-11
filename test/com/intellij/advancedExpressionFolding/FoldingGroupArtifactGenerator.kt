package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorEx
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper

object FoldingGroupArtifactGenerator {
    private const val GROUP_PREFIX = "ADF2"

    fun render(documentText: String, wrapper: FoldingDescriptorExWrapper): String {
        val nodes = buildNodes(wrapper.list)
        return renderSection(documentText, nodes, 0, documentText.length)
    }

    private fun buildNodes(descriptors: List<FoldingDescriptorEx>): List<Node> {
        val sorted = descriptors.sortedWith(compareBy({ it.range.start }, { -it.range.end }))
        val stack = ArrayDeque<Node>()
        val roots = mutableListOf<Node>()
        for (descriptor in sorted) {
            val node = Node(descriptor)
            while (stack.isNotEmpty() && descriptor.range.start >= stack.last().descriptor.range.end) {
                stack.removeLast()
            }
            if (stack.isEmpty()) {
                roots.add(node)
            } else {
                stack.last().children.add(node)
            }
            stack.addLast(node)
        }
        return roots
    }

    private fun renderSection(text: String, nodes: List<Node>, start: Int, end: Int): String {
        val builder = StringBuilder()
        var current = start
        for (node in nodes) {
            val nodeStart = node.descriptor.range.start
            val nodeEnd = node.descriptor.range.end
            builder.append(text.substring(current, nodeStart))
            builder.append(renderNode(text, node))
            current = nodeEnd
        }
        builder.append(text.substring(current, end))
        return builder.toString()
    }

    private fun renderNode(text: String, node: Node): String {
        val descriptor = node.descriptor
        val number = if (descriptor.group?.startsWith(GROUP_PREFIX) == true) {
            descriptor.groupReference
        } else {
            0
        }
        val original = text.substring(descriptor.range.start, descriptor.range.end)
        val children = node.children
        val childrenText = if (children.isEmpty()) {
            ""
        } else {
            children.joinToString(separator = " ", prefix = "{", postfix = "}") {
                renderNode(text, it)
            }
        }
        return buildString {
            append('[')
            append(number)
            append(":\"")
            append(original)
            append("\"]")
            append(childrenText)
        }
    }

    private data class Node(
        val descriptor: FoldingDescriptorEx,
        val children: MutableList<Node> = mutableListOf(),
    )
}
