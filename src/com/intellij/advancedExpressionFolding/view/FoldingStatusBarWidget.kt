package com.intellij.advancedExpressionFolding.view

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingBuilder
import com.intellij.advancedExpressionFolding.FoldingService
import com.intellij.advancedExpressionFolding.metrics.FoldingMetrics
import com.intellij.advancedExpressionFolding.metrics.FoldingMetricsCalculator
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.FoldingMetricsSettings
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.intellij.psi.PsiDocumentManager
import java.awt.Component

class FoldingStatusBarWidget(private val project: Project) :
    StatusBarWidget,
    StatusBarWidget.TextPresentation,
    Disposable {

    private var statusBar: StatusBar? = null
    private var text: String = NO_EDITOR_TEXT
    private var connection = project.messageBus.connect(this)
    private val metricsSettings = FoldingMetricsSettings.getInstance()

    private val foldListener = FoldingService.FoldUpdateListener { editor ->
        if (editor.project == project) {
            scheduleUpdate(editor)
        }
    }

    override fun ID(): String = WIDGET_ID

    override fun getPresentation(): StatusBarWidget.WidgetPresentation = this

    override fun install(statusBar: StatusBar) {
        this.statusBar = statusBar
        connection.subscribe(
            FileEditorManagerListener.FILE_EDITOR_MANAGER,
            object : FileEditorManagerListener {
                override fun selectionChanged(event: FileEditorManagerEvent) {
                    val newEditor = (event.newEditor as? TextEditor)?.editor
                    scheduleUpdate(newEditor)
                }
            }
        )
        FoldingService.get().addFoldUpdateListener(foldListener)
        scheduleUpdate(FileEditorManager.getInstance(project).selectedTextEditor)
    }

    override fun dispose() {
        FoldingService.get().removeFoldUpdateListener(foldListener)
        connection.dispose()
        statusBar = null
    }

    override fun getText(): String = text

    override fun getTooltipText(): String = TOOLTIP

    override fun getAlignment(): Float = Component.CENTER_ALIGNMENT

    private fun scheduleUpdate(editor: Editor?) {
        val application = ApplicationManager.getApplication()
        val runnable = Runnable {
            val targetEditor = editor ?: FileEditorManager.getInstance(project).selectedTextEditor
            updateForEditor(targetEditor)
        }
        if (application.isDispatchThread) {
            runnable.run()
        } else {
            application.invokeLater(runnable)
        }
    }

    private fun updateForEditor(editor: Editor?) {
        if (project.isDisposed) {
            return
        }
        val settings = AdvancedExpressionFoldingSettings.getInstance().state
        if (!settings.globalOn) {
            updateText(DISABLED_TEXT)
            return
        }
        if (editor == null || editor.isDisposed || editor.project != project) {
            updateText(NO_EDITOR_TEXT)
            return
        }

        val cachedMetrics = getCachedMetrics(editor)

        when (val computation = computeMetrics(editor)) {
            MetricsComputation.Unavailable -> {
                if (cachedMetrics != null) {
                    updateText(formatMetrics(cachedMetrics))
                } else {
                    updateText(NO_DATA_TEXT)
                }
            }
            MetricsComputation.Updating -> {
                if (cachedMetrics != null) {
                    updateText(formatMetrics(cachedMetrics))
                } else {
                    updateText(UPDATING_TEXT)
                }
            }
            is MetricsComputation.Available -> {
                storeMetrics(editor, computation.metrics)
                updateText(formatMetrics(computation.metrics))
            }
        }
    }

    private fun computeMetrics(editor: Editor): MetricsComputation {
        val project = editor.project ?: return MetricsComputation.Unavailable
        if (project.isDisposed || editor.isDisposed) {
            return MetricsComputation.Unavailable
        }

        return try {
            runReadAction {
                val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document)
                    ?: return@runReadAction MetricsComputation.Unavailable
                val descriptors = AdvancedExpressionFoldingBuilder().computeFoldDescriptors(psiFile, editor.document)
                MetricsComputation.Available(FoldingMetricsCalculator.calculate(editor.document, descriptors))
            }
        } catch (_: IndexNotReadyException) {
            MetricsComputation.Updating
        }
    }

    private fun updateText(value: String) {
        if (text != value) {
            text = value
            statusBar?.updateWidget(WIDGET_ID)
        }
    }

    private fun getCachedMetrics(editor: Editor): FoldingMetrics? {
        val fileUrl = resolveFile(editor)?.url ?: return null
        return metricsSettings.getMetrics(fileUrl)
    }

    private fun storeMetrics(editor: Editor, metrics: FoldingMetrics) {
        val fileUrl = resolveFile(editor)?.url ?: return
        metricsSettings.updateMetrics(fileUrl, metrics)
    }

    private fun resolveFile(editor: Editor): VirtualFile? =
        FileDocumentManager.getInstance().getFile(editor.document)

    private fun formatMetrics(metrics: FoldingMetrics): String {
        val foldText = formatUnit(metrics.foldCount, "fold")
        val lineText = formatApproxUnit(metrics.linesSaved, "line")
        val charText = formatApproxUnit(metrics.charactersSaved, "char")
        return "AEF: $foldText ($lineText, $charText)"
    }

    private fun formatUnit(value: Int, unit: String): String {
        val suffix = if (value == 1) unit else unit + "s"
        return "$value $suffix"
    }

    private fun formatApproxUnit(value: Int, unit: String): String {
        val suffix = if (value == 1) unit else unit + "s"
        return "~$value $suffix saved"
    }

    private sealed class MetricsComputation {
        object Unavailable : MetricsComputation()
        object Updating : MetricsComputation()
        class Available(val metrics: FoldingMetrics) : MetricsComputation()
    }

    companion object {
        const val WIDGET_ID = "advancedExpressionFoldingStatus"
        private const val TOOLTIP = "Advanced Expression Folding savings"
        private const val NO_EDITOR_TEXT = "AEF: no editor"
        private const val DISABLED_TEXT = "AEF: disabled"
        private const val NO_DATA_TEXT = "AEF: n/a"
        private const val UPDATING_TEXT = "AEF: updating..."
    }
}

class FoldingStatusBarWidgetFactory : StatusBarWidgetFactory, DumbAware {
    override fun getId(): String = FoldingStatusBarWidget.WIDGET_ID

    override fun getDisplayName(): String = "Advanced Expression Folding"

    override fun isAvailable(project: Project): Boolean = true

    override fun createWidget(project: Project): StatusBarWidget = FoldingStatusBarWidget(project)

    override fun disposeWidget(widget: StatusBarWidget) {
        widget.dispose()
    }

    override fun canBeEnabledOn(statusBar: StatusBar): Boolean = true
}
