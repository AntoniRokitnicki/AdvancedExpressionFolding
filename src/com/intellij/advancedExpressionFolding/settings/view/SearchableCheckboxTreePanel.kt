package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.ui.*
import com.intellij.ui.CheckboxTree.CheckboxTreeCellRenderer
import com.intellij.util.Alarm
import com.intellij.util.ui.tree.TreeUtil
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.event.DocumentEvent
import javax.swing.tree.DefaultTreeModel

/**
 * Panel displaying a [CheckboxTree] with a [SearchTextField] that filters nodes on the fly.
 * The underlying [OptionNode] structure is never copied; filtering only affects the view.
 */
class SearchableCheckboxTreePanel(private var root: OptionNode) : JPanel(BorderLayout()) {

    private val searchField = SearchTextField()
    private val statusLabel = JLabel()
    private val alarm = Alarm()
    private var matchedNodes: MutableSet<OptionNode> = mutableSetOf()

    private val tree: CheckboxTree

    init {
        val renderer = object : CheckboxTreeCellRenderer(true) {
            override fun customizeRenderer(
                tree: javax.swing.JTree,
                value: Any,
                selected: Boolean,
                expanded: Boolean,
                leaf: Boolean,
                row: Int,
                hasFocus: Boolean
            ) {
                val node = value as CheckedTreeNode
                val option = node.userObject as OptionNode
                val attributes = if (matchedNodes.isEmpty() || matchedNodes.contains(option))
                    SimpleTextAttributes.REGULAR_ATTRIBUTES else SimpleTextAttributes.GRAYED_ATTRIBUTES
                textRenderer.toolTipText = option.description
                SearchUtil.appendFragments(
                    searchField.text,
                    option.label,
                    attributes,
                    SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES,
                    textRenderer
                )
            }
        }

        val rootNode = buildTree(root)
        tree = object : CheckboxTree(renderer, rootNode) {}
        tree.isRootVisible = false
        tree.addCheckboxTreeListener(object : CheckboxTreeListener {
            override fun nodeStateChanged(node: CheckedTreeNode) {
                val option = node.userObject as OptionNode
                option.setSelected(node.isChecked)
            }
        })

        searchField.addDocumentListener(object : DocumentAdapter() {
            override fun textChanged(e: DocumentEvent) {
                alarm.cancelAllRequests()
                alarm.addRequest({ applyFilter() }, 150)
            }
        })
        searchField.textEditor.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                if (e.keyCode == KeyEvent.VK_ESCAPE) {
                    searchField.text = ""
                }
            }
        })

        layout = BorderLayout()
        add(searchField, BorderLayout.NORTH)
        add(ScrollPaneFactory.createScrollPane(tree), BorderLayout.CENTER)
        add(statusLabel, BorderLayout.SOUTH)
        TreeUtil.expandAll(tree)
        updateStatus()
    }

    private fun buildTree(option: OptionNode): CheckedTreeNode {
        val node = CheckedTreeNode(option)
        node.isChecked = option.isSelected()
        node.isEnabled = option.enabled
        option.children.forEach { child ->
            node.add(buildTree(child))
        }
        return node
    }

    private fun applyFilter() {
        matchedNodes = mutableSetOf()
        val filtered = filter(root, searchField.text.lowercase())
        val newRoot = filtered ?: buildTree(root)
        tree.model = DefaultTreeModel(newRoot)
        tree.isRootVisible = false
        TreeUtil.expandAll(tree)
        updateStatus()
    }

    private fun filter(option: OptionNode, query: String): CheckedTreeNode? {
        val nodeMatches = option.matches(query)
        val children = option.children.mapNotNull { filter(it, query) }
        val hasMatchingChild = children.isNotEmpty()
        if (nodeMatches) matchedNodes.add(option)
        if (query.isEmpty() || nodeMatches || hasMatchingChild) {
            val node = CheckedTreeNode(option)
            node.isChecked = option.isSelected()
            node.isEnabled = option.enabled
            children.forEach { node.add(it) }
            return node
        }
        return null
    }

    private fun updateStatus() {
        statusLabel.text = if (searchField.text.isEmpty()) "" else "${matchedNodes.size} option(s) found"
    }

    fun setRoot(option: OptionNode) {
        root = option
        tree.model = DefaultTreeModel(buildTree(root))
        TreeUtil.expandAll(tree)
        updateStatus()
    }
}

