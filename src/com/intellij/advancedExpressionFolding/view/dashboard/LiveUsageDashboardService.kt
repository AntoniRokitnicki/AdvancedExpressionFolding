package com.intellij.advancedExpressionFolding.view.dashboard

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import org.jetbrains.annotations.TestOnly
import java.util.ArrayDeque
import java.util.concurrent.CopyOnWriteArrayList

@Service(Service.Level.PROJECT)
class LiveUsageDashboardService(private val project: Project) {

    private val lock = Any()
    private var totalUsages: Int = 0
    private val usageByFile = LinkedHashMap<String, Int>()
    private val history = ArrayDeque<String>()
    private val listeners = CopyOnWriteArrayList<(LiveUsageDashboardState) -> Unit>()

    fun recordUsage(file: PsiFile, textRange: TextRange) {
        if (file.project != project) {
            return
        }
        val descriptor = buildDescriptor(file, textRange)
        val snapshot = synchronized(lock) {
            totalUsages += 1
            usageByFile[descriptor.file] = (usageByFile[descriptor.file] ?: 0) + 1
            history.addLast(descriptor.presentation)
            trimHistoryIfNeeded()
            buildState()
        }
        notifyListeners(snapshot)
    }

    fun snapshot(): LiveUsageDashboardState = synchronized(lock) { buildState() }

    fun addListener(listener: (LiveUsageDashboardState) -> Unit) {
        listeners.add(listener)
        dispatch(listener, snapshot())
    }

    fun removeListener(listener: (LiveUsageDashboardState) -> Unit) {
        listeners.remove(listener)
    }

    @TestOnly
    fun reset() {
        synchronized(lock) {
            totalUsages = 0
            usageByFile.clear()
            history.clear()
        }
        notifyListeners(snapshot())
    }

    private fun trimHistoryIfNeeded() {
        while (history.size > MAX_HISTORY_ENTRIES) {
            history.removeFirst()
        }
    }

    private fun notifyListeners(state: LiveUsageDashboardState) {
        listeners.forEach { listener ->
            dispatch(listener, state)
        }
    }

    private fun dispatch(listener: (LiveUsageDashboardState) -> Unit, state: LiveUsageDashboardState) {
        val application = ApplicationManager.getApplication()
        if (application.isDispatchThread) {
            listener(state)
        } else {
            application.invokeLater { listener(state) }
        }
    }

    private fun buildState(): LiveUsageDashboardState = LiveUsageDashboardState(
        totalUsages = totalUsages,
        distinctFiles = usageByFile.size,
        history = history.toList()
    )

    private fun buildDescriptor(file: PsiFile, textRange: TextRange): UsageDescriptor {
        val virtualFile = file.virtualFile
        val fileLabel = virtualFile?.presentableUrl ?: file.name
        val presentation = "$fileLabel:${textRange.startOffset}-${textRange.endOffset}"
        return UsageDescriptor(fileLabel, presentation)
    }

    private data class UsageDescriptor(val file: String, val presentation: String)

    companion object {
        private const val MAX_HISTORY_ENTRIES = 50

        fun getInstance(project: Project): LiveUsageDashboardService = project.service()
    }
}


data class LiveUsageDashboardState(
    val totalUsages: Int,
    val distinctFiles: Int,
    val history: List<String>,
)
