package com.intellij.advancedExpressionFolding.view

import com.intellij.advancedExpressionFolding.FoldingEditorCreatedListener
import com.intellij.advancedExpressionFolding.FoldingService
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.ReadAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ScrollType
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.ColoredTreeCellRenderer
import com.intellij.ui.SimpleTextAttributes
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.treeStructure.Tree
import com.intellij.util.concurrency.AppExecutorUtil
import com.intellij.util.messages.MessageBusConnection
import com.intellij.util.ui.tree.TreeUtil
import java.awt.BorderLayout
import java.awt.event.KeyEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.util.concurrent.atomic.AtomicLong
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.KeyStroke
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import javax.swing.tree.TreePath
import javax.swing.tree.TreeSelectionModel

class FoldingDescriptorsToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = FoldingDescriptorsPanel(project)
        val content = ContentFactory.getInstance().createContent(panel.component, "", false)
        toolWindow.contentManager.addContent(content)
        Disposer.register(content, panel)
    }
}

private class FoldingDescriptorsPanel(private val project: Project) : Disposable {

    private val tree = Tree(DefaultMutableTreeNode("root"))
    private val updateRequestId = AtomicLong()

    private val applicationConnection: MessageBusConnection
    private val projectConnection: MessageBusConnection

    private var currentEditor: Editor? = null

    val component: JComponent = JPanel(BorderLayout()).apply {
        add(JBScrollPane(tree), BorderLayout.CENTER)
    }

    init {
        configureTree()

        applicationConnection = ApplicationManager.getApplication().messageBus.connect(this)
        applicationConnection.subscribe(
            FoldingEditorCreatedListener.TOPIC,
            object : FoldingEditorCreatedListener.Listener {
                override fun editorCreated(editor: Editor) {
                    if (editor.project == project && editor == FileEditorManager.getInstance(project).selectedTextEditor) {
                        scheduleUpdate(editor, force = true)
                    }
                }

                override fun editorReleased(editor: Editor) {
                    if (editor == currentEditor) {
                        clear()
                    }
                }
            }
        )

        projectConnection = project.messageBus.connect(this)
        projectConnection.subscribe(
            FileEditorManagerListener.FILE_EDITOR_MANAGER,
            object : FileEditorManagerListener {
                override fun selectionChanged(event: FileEditorManagerEvent) {
                    val editor = (event.newEditor as? TextEditor)?.editor
                    if (editor != null) {
                        scheduleUpdate(editor)
                    } else {
                        clear()
                    }
                }
            }
        )

        FileEditorManager.getInstance(project).selectedTextEditor?.let(::scheduleUpdate)
    }

    private fun configureTree() {
        tree.isRootVisible = false
        tree.showsRootHandles = true
        tree.selectionModel.selectionMode = TreeSelectionModel.SINGLE_TREE_SELECTION
        tree.emptyText.text = "Select an editor to inspect folding"
        tree.cellRenderer = object : ColoredTreeCellRenderer() {
            override fun customizeCellRenderer(
                tree: javax.swing.JTree,
                value: Any?,
                selected: Boolean,
                expanded: Boolean,
                leaf: Boolean,
                row: Int,
                hasFocus: Boolean
            ) {
                val node = value as? DefaultMutableTreeNode ?: return
                when (val data = node.userObject) {
                    is FoldingPreviewGroup -> {
                        append(data.name, SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)
                    }
                    is FoldingPreviewEntry -> {
                        val placeholder = data.placeholderText ?: "<no placeholder>"
                        append(placeholder, SimpleTextAttributes.REGULAR_ATTRIBUTES)
                        append("  ", SimpleTextAttributes.GRAYED_SMALL_ATTRIBUTES)
                        append(data.codeSnippet, SimpleTextAttributes.GRAYED_ATTRIBUTES)
                    }
                }
            }
        }

        tree.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                if (e.clickCount == 2 && !e.isConsumed) {
                    navigateToSelected()
                }
            }
        })
        tree.registerKeyboardAction(
            { navigateToSelected() },
            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
            JComponent.WHEN_FOCUSED
        )
    }

    private fun scheduleUpdate(editor: Editor, force: Boolean = false) {
        if (editor.isDisposed) {
            return
        }
        if (!force && currentEditor == editor) {
            return
        }
        currentEditor = editor
        showLoading()
        val requestId = updateRequestId.incrementAndGet()
        ReadAction.nonBlocking<List<FoldingDescriptor>> {
            FoldingService.get().descriptorsFor(editor)
        }
            .finishOnUiThread(ModalityState.NON_MODAL) { descriptors ->
                if (updateRequestId.get() != requestId) {
                    return@finishOnUiThread
                }
                if (editor.isDisposed || currentEditor != editor) {
                    return@finishOnUiThread
                }
                val groups = FoldingPreviewViewModelBuilder.fromDescriptors(editor.document, descriptors)
                applyGroups(groups)
            }
            .submit(AppExecutorUtil.getAppExecutorService())
    }

    private fun showLoading() {
        tree.emptyText.text = "Loading folding descriptors..."
        applyGroups(emptyList(), emptyMessage = null)
    }

    private fun applyGroups(groups: List<FoldingPreviewGroup>, emptyMessage: String? = "No folding descriptors") {
        val newRoot = DefaultMutableTreeNode("root")
        groups.forEach { group ->
            val groupNode = GroupNode(group)
            group.entries.forEach { entry ->
                groupNode.add(DescriptorNode(entry))
            }
            newRoot.add(groupNode)
        }
        tree.model = DefaultTreeModel(newRoot)
        TreeUtil.expandAll(tree)
        if (groups.isEmpty()) {
            emptyMessage?.let { tree.emptyText.text = it }
        }
    }

    private fun navigateToSelected() {
        val entry = selectedEntry() ?: return
        val editor = currentEditor?.takeUnless(Editor::isDisposed) ?: return
        val range = entry.range
        editor.caretModel.moveToOffset(range.startOffset)
        editor.selectionModel.setSelection(range.startOffset, range.endOffset)
        editor.scrollingModel.scrollToCaret(ScrollType.CENTER)
    }

    private fun selectedEntry(): FoldingPreviewEntry? {
        val path: TreePath = tree.selectionPath ?: return null
        val node = path.lastPathComponent as? DefaultMutableTreeNode ?: return null
        return node.userObject as? FoldingPreviewEntry
    }

    private fun clear() {
        currentEditor = null
        applyGroups(emptyList(), emptyMessage = "Select an editor to inspect folding")
    }

    override fun dispose() {
        // message bus connections are disposed automatically
    }

    private class GroupNode(val group: FoldingPreviewGroup) : DefaultMutableTreeNode(group) {
        init {
            allowsChildren = true
        }

        override fun toString(): String = group.name
    }

    private class DescriptorNode(val entry: FoldingPreviewEntry) : DefaultMutableTreeNode(entry) {
        init {
            allowsChildren = false
        }

        override fun toString(): String = entry.placeholderText ?: entry.codeSnippet
    }
}
