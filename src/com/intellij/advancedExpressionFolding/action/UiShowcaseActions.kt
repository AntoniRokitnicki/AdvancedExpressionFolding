package com.intellij.advancedExpressionFolding.action

import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.CollectionListModel
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.components.ActionLink
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.SearchTextField
import com.intellij.util.ui.JBUI
import java.awt.Dimension
import javax.swing.DefaultComboBoxModel
import javax.swing.JComponent
import javax.swing.JTable
import javax.swing.table.DefaultTableModel
import com.intellij.openapi.fileTypes.PlainTextFileType
import com.intellij.ui.EditorTextField
import com.intellij.ide.highlighter.JavaFileType
import javax.swing.event.DocumentEvent
import com.intellij.ui.components.JBTabbedPane

abstract class ShowcaseDialogAction(
    text: String,
    description: String,
    icon: javax.swing.Icon?
) : AnAction(text, description, icon), DumbAware {
    final override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        createDialog(project).show()
    }

    protected abstract fun createDialog(project: Project): DialogWrapper
}

class FoldingInsightsAction : ShowcaseDialogAction(
    "Folding Insights Dashboard",
    "Review a quick snapshot of folding metrics",
    AllIcons.General.Information
) {
    override fun createDialog(project: Project): DialogWrapper = FoldingInsightsDialog(project)
}

class FoldingRuleExplorerAction : ShowcaseDialogAction(
    "Folding Rule Explorer",
    "Browse available folding presets",
    AllIcons.Actions.ListFiles
) {
    override fun createDialog(project: Project): DialogWrapper = FoldingRuleExplorerDialog(project)
}

class FoldingPreviewGalleryAction : ShowcaseDialogAction(
    "Folding Preview Gallery",
    "Compare original and folded code side-by-side",
    AllIcons.Actions.PreviewDetails
) {
    override fun createDialog(project: Project): DialogWrapper = FoldingPreviewDialog(project)
}

class FoldingTroubleshootingAction : ShowcaseDialogAction(
    "Folding Troubleshooting Checklist",
    "Run through quick diagnostics for folding issues",
    AllIcons.General.Warning
) {
    override fun createDialog(project: Project): DialogWrapper = FoldingTroubleshootingDialog(project)
}

class FoldingQuickStartAction : ShowcaseDialogAction(
    "Folding Quick Start Guide",
    "Step through the essentials to get started",
    AllIcons.Actions.Help
) {
    override fun createDialog(project: Project): DialogWrapper = FoldingQuickStartDialog(project)
}

private class FoldingInsightsDialog(project: Project) : DialogWrapper(project) {
    init {
        title = "Folding Insights"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val narrativeArea = JBTextArea(
            "Keep an eye on your busiest folding groups and recent template tweaks."
        ).apply {
            isEditable = false
            lineWrap = true
            wrapStyleWord = true
            border = JBUI.Borders.empty(4)
        }

        return panel {
            row("Profile:") {
                comboBox(DefaultComboBoxModel(arrayOf("Project Defaults", "Team Preset", "Minimal")))
            }
            row("Auto-sync:") {
                checkBox("Enable background refresh").apply {
                    component.isSelected = true
                }
            }
            group("Highlights") {
                row { label("Active rules: 42") }
                row { label("Pending updates: 3") }
                row { label("Last sync: 5 minutes ago") }
            }
            row {
                cell(JBScrollPane(narrativeArea)).align(Align.FILL)
            }.resizableRow()
        }.apply {
            preferredSize = Dimension(480, 320)
        }
    }
}

private class FoldingRuleExplorerDialog(private val project: Project) : DialogWrapper(project) {
    init {
        title = "Folding Rule Explorer"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val rules = mutableListOf(
            "Getter/Setter Properties",
            "Optional to Kotlin",
            "Null-safe Checks",
            "Log Folding",
            "Stream Spread",
            "Lombok Emulator",
            "Compact Control Flow",
            "FieldShift"
        )
        val listModel = CollectionListModel(rules)
        val list = JBList(listModel).apply {
            visibleRowCount = 8
            selectedIndex = 0
        }

        val decorator = ToolbarDecorator.createDecorator(list)
            .setAddAction {
                listModel.add(listModel.size, "Custom Preset ${listModel.size + 1}")
            }
            .setEditAction {
                val value = list.selectedValue ?: return@setEditAction
                Messages.showInfoMessage(
                    project,
                    "Preview snippets and documentation for \"${'$'}value\" appear here.",
                    "Preset Preview"
                )
            }
            .disableUpDownActions()

        val detailsPanel: DialogPanel = panel {
            group("Selection Details") {
                row { label("Description:") }
                row {
                    val detailField = JBTextArea().apply {
                        isEditable = false
                        lineWrap = true
                        wrapStyleWord = true
                        text = "Select a preset to view its summary."
                        border = JBUI.Borders.empty(4)
                    }
                    list.addListSelectionListener {
                        val value = list.selectedValue
                        detailField.text = value?.let {
                            "${'$'}it keeps code compact and consistent."
                        } ?: "Select a preset to view its summary."
                    }
                    cell(JBScrollPane(detailField)).align(Align.FILL)
                }.resizableRow()
            }
        }

        return panel {
            row {
                cell(decorator.createPanel()).align(Align.FILL)
                cell(detailsPanel).align(Align.FILL)
            }.resizableRow()
        }.apply {
            preferredSize = Dimension(640, 360)
        }
    }
}

private class FoldingPreviewDialog(private val project: Project) : DialogWrapper(project) {
    init {
        title = "Folding Preview"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val before = EditorTextField(
            "public void log(User user) {\n    if (user != null) {\n        System.out.println(user.getName());\n    }\n}",
            project,
            JavaFileType.INSTANCE
        ).also {
            it.setOneLineMode(false)
            it.preferredSize = Dimension(520, 160)
        }
        val after = EditorTextField(
            "fun User.log() = println(name ?: \"<unknown>\")",
            project,
            PlainTextFileType.INSTANCE
        ).also {
            it.setOneLineMode(false)
            it.preferredSize = Dimension(520, 160)
        }

        val tabs = JBTabbedPane().apply {
            addTab("Before", JBScrollPane(before))
            addTab("After", JBScrollPane(after))
        }

        return panel {
            row {
                cell(tabs).align(Align.FILL)
            }.resizableRow()
        }.apply {
            preferredSize = Dimension(600, 300)
        }
    }
}

private class FoldingTroubleshootingDialog(project: Project) : DialogWrapper(project) {
    init {
        title = "Folding Troubleshooting"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val model = DefaultTableModel(arrayOf("Check", "Status", "Hint"), 0).apply {
            addRow(arrayOf("Plugin Enabled", "OK", "Advanced Folding is active"))
            addRow(arrayOf("Settings Profile", "OK", "Project defaults in use"))
            addRow(arrayOf("Latest Sync", "Warning", "Sync ran 2 hours ago"))
            addRow(arrayOf("Example Files", "Action Needed", "Examples not checked out"))
            addRow(arrayOf("Color Scheme", "Optional", "Update folded text colors"))
        }

        val table = JTable(model)
        val scrollPane = ScrollPaneFactory.createScrollPane(table).apply {
            preferredSize = Dimension(620, 180)
        }

        return panel {
            row {
                cell(scrollPane).align(Align.FILL)
            }.resizableRow()
            row {
                label("Double-click a row to open the related settings.")
            }
        }
    }
}

private class FoldingQuickStartDialog(project: Project) : DialogWrapper(project) {
    init {
        title = "Folding Quick Start"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val steps = listOf(
            "Install Advanced Expression Folding",
            "Review the Code Folding settings",
            "Checkout example files",
            "Run folding tests",
            "Share presets with the team"
        )
        val listModel = CollectionListModel(steps)
        val list = JBList(listModel).apply {
            visibleRowCount = 6
        }

        val searchField = SearchTextField().apply {
            textEditor.emptyText.text = "Filter steps"
        }

        searchField.addDocumentListener(object : com.intellij.ui.DocumentAdapter() {
            override fun textChanged(event: DocumentEvent) {
                val filter = searchField.text.trim().lowercase()
                val filtered = if (filter.isEmpty()) {
                    steps
                } else {
                    steps.filter { it.lowercase().contains(filter) }
                }
                listModel.replaceAll(filtered)
            }
        })

        val checklist = listOf(
            JBCheckBox("Enable favorite folding rules", true),
            JBCheckBox("Sync presets on project open", false),
            JBCheckBox("Show folding previews in gutter", true)
        )

        val resourcesLink = ActionLink("Open documentation wiki") {
            BrowserUtil.browse("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki")
        }

        return panel {
            row {
                cell(searchField).align(Align.FILL)
            }
            row {
                cell(JBScrollPane(list)).align(Align.FILL)
            }.resizableRow()
            group("Checklist") {
                checklist.forEach { checkBox ->
                    row { cell(checkBox) }
                }
            }
            row {
                cell(resourcesLink)
            }
        }.apply {
            preferredSize = Dimension(480, 360)
        }
    }
}
