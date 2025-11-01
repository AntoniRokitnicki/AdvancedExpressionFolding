package com.intellij.advancedExpressionFolding.learning

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener
import com.intellij.openapi.editor.event.FoldingListener
import com.intellij.openapi.editor.impl.EditorImpl
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiDocumentManager
import com.intellij.util.concurrency.AppExecutorUtil
import java.io.IOException
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.time.Clock
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.max
import kotlin.io.path.notExists

@Service(Service.Level.APP)
class FoldingTelemetryService : Disposable {

    data class TelemetryEvent(
        val ts: Long,
        val projectIdHash: Int,
        val fileExt: String,
        val languageId: String,
        val filePathHash: Int,
        val foldType: String,
        val regionStart: Int,
        val regionEnd: Int,
        val regionLength: Int,
        val nestingDepth: Int,
        val action: String,
        val timeSincePrevMs: Long,
        val caretOffset: Int,
        val visibleLineCount: Int,
    ) {
        fun toCsv(): String {
            return listOf(
                ts,
                projectIdHash,
                fileExt.sanitize(),
                languageId.sanitize(),
                filePathHash,
                foldType.sanitize(),
                regionStart,
                regionEnd,
                regionLength,
                nestingDepth,
                action.sanitize(),
                timeSincePrevMs,
                caretOffset,
                visibleLineCount,
            ).joinToString(",")
        }

        private fun String.sanitize(): String = this.replace(',', '_')

        companion object {
            private const val EXPECTED_FIELDS = 14

            fun fromCsv(line: String): TelemetryEvent? {
                val parts = StringUtil.split(line, ",")
                if (parts.size != EXPECTED_FIELDS) {
                    return null
                }
                return TelemetryEvent(
                    ts = parts[0].toLongOrNull() ?: return null,
                    projectIdHash = parts[1].toIntOrNull() ?: return null,
                    fileExt = parts[2],
                    languageId = parts[3],
                    filePathHash = parts[4].toIntOrNull() ?: return null,
                    foldType = parts[5],
                    regionStart = parts[6].toIntOrNull() ?: return null,
                    regionEnd = parts[7].toIntOrNull() ?: return null,
                    regionLength = parts[8].toIntOrNull() ?: return null,
                    nestingDepth = parts[9].toIntOrNull() ?: return null,
                    action = parts[10],
                    timeSincePrevMs = parts[11].toLongOrNull() ?: return null,
                    caretOffset = parts[12].toIntOrNull() ?: return null,
                    visibleLineCount = parts[13].toIntOrNull() ?: return null,
                )
            }
        }
    }

    private val state = FoldingRnnState.get()
    private val clock: Clock = Clock.systemUTC()
    private val executor = AppExecutorUtil.createBoundedApplicationPoolExecutor("AEF.FoldingTelemetry", 1)
    private val buffer = CopyOnWriteArrayList<TelemetryEvent>()
    private val flushing = AtomicBoolean(false)
    private val lastEventPerRegion = ConcurrentHashMap<String, Long>()
    private val application = ApplicationManager.getApplication()
    private val logger = Logger.getInstance(FoldingTelemetryService::class.java)

    private val foldingListener = object : FoldingListener {
        override fun onFoldRegionStateChange(region: FoldRegion) {
            handleFoldEvent(region)
        }
    }

    private val editorListener = object : EditorFactoryListener {
        override fun editorCreated(event: EditorFactoryEvent) {
            recordOpenEvent(event.editor)
        }
    }

    init {
        Disposer.register(application, this)
        val editorFactory = com.intellij.openapi.editor.EditorFactory.getInstance()
        editorFactory.eventMulticaster.addFoldingListener(foldingListener, this)
        editorFactory.addEditorFactoryListener(editorListener, this)
    }

    private fun shouldCapture(): Boolean {
        if (!state.ensureEnabled()) {
            return false
        }
        return AdvancedExpressionFoldingSettings.getInstance().state.learnFoldingPreference.also { enabled ->
            if (!enabled) {
                state.deleteArtifacts()
            }
        }
    }

    private fun recordOpenEvent(editor: Editor) {
        if (!shouldCapture()) {
            return
        }
        val project = editor.project ?: return
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) ?: return
        val virtualFile = psiFile.virtualFile
        val ts = clock.millis()
        val event = TelemetryEvent(
            ts = ts,
            projectIdHash = project.locationHash.hashCode(),
            fileExt = virtualFile?.extension ?: "",
            languageId = psiFile.language.id,
            filePathHash = (virtualFile?.path ?: psiFile.name).hashCode(),
            foldType = "open",
            regionStart = 0,
            regionEnd = 0,
            regionLength = 0,
            nestingDepth = 0,
            action = "open",
            timeSincePrevMs = -1,
            caretOffset = editor.caretModel.offset,
            visibleLineCount = visibleLineCount(editor),
        )
        append(event)
    }

    private fun handleFoldEvent(region: FoldRegion) {
        if (!shouldCapture()) {
            return
        }
        val editor = region.editor as? EditorImpl ?: return
        val project = editor.project ?: return
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) ?: return
        val predictor = project.getService(FoldingRnnPredictor::class.java)
        val foldType = predictor?.resolveFoldType(psiFile, region) ?: "unknown"
        val ts = clock.millis()
        val virtualFile = psiFile.virtualFile
        val projectHash = project.locationHash.hashCode()
        val fileHash = (virtualFile?.path ?: psiFile.name).hashCode()
        val key = "$projectHash|$fileHash|$foldType|${region.startOffset}|${region.endOffset}"
        val lastTs = lastEventPerRegion.put(key, ts)
        val delta = if (lastTs != null) ts - lastTs else -1
        val event = TelemetryEvent(
            ts = ts,
            projectIdHash = projectHash,
            fileExt = virtualFile?.extension ?: "",
            languageId = psiFile.language.id,
            filePathHash = fileHash,
            foldType = foldType,
            regionStart = region.startOffset,
            regionEnd = region.endOffset,
            regionLength = region.endOffset - region.startOffset,
            nestingDepth = nestingDepth(editor, region),
            action = if (region.isExpanded) "expand" else "collapse",
            timeSincePrevMs = delta,
            caretOffset = editor.caretModel.offset,
            visibleLineCount = visibleLineCount(editor),
        )
        append(event)
    }

    private fun visibleLineCount(editor: Editor): Int {
        val lineHeight = max(1, editor.lineHeight)
        val visibleArea = editor.scrollingModel.visibleArea
        val lines = visibleArea.height / lineHeight
        return max(1, lines)
    }

    private fun nestingDepth(editor: Editor, region: FoldRegion): Int {
        var depth = 0
        editor.foldingModel.allFoldRegions.forEach { other ->
            if (other !== region && other.startOffset <= region.startOffset && other.endOffset >= region.endOffset) {
                depth += 1
            }
        }
        return depth
    }

    private fun append(event: TelemetryEvent) {
        buffer.add(event)
        if (buffer.size >= BATCH_SIZE) {
            scheduleFlush()
        }
    }

    private fun scheduleFlush() {
        if (!flushing.compareAndSet(false, true)) {
            return
        }
        executor.execute {
            try {
                flushBuffer()
            } finally {
                flushing.set(false)
            }
        }
    }

    private fun flushBuffer() {
        if (buffer.isEmpty()) {
            return
        }
        val events = mutableListOf<TelemetryEvent>()
        events.addAll(buffer)
        buffer.removeAll(events)
        if (events.isEmpty()) {
            return
        }
        val path = state.dataFile()
        val headerNeeded = path.notExists()
        try {
            Files.newBufferedWriter(path, Charsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND).use { writer ->
                if (headerNeeded) {
                    writer.write(CSV_HEADER)
                    writer.newLine()
                }
                for (event in events.sortedBy { it.ts }) {
                    writer.write(event.toCsv())
                    writer.newLine()
                }
            }
        } catch (exception: IOException) {
            logger.warn("Failed to write folding telemetry", exception)
        }
    }

    fun readEvents(): Sequence<TelemetryEvent> {
        val path = state.dataFile()
        if (path.notExists()) {
            return emptySequence()
        }
        return try {
            Files.newBufferedReader(path).use { reader ->
                reader.lineSequence()
                    .drop(1)
                    .mapNotNull(TelemetryEvent.Companion::fromCsv)
                    .toList()
                    .asSequence()
            }
        } catch (_: IOException) {
            emptySequence()
        }
    }

    override fun dispose() {
        if (buffer.isNotEmpty()) {
            flushBuffer()
        }
    }

    companion object {
        private const val BATCH_SIZE = 32
        private const val CSV_HEADER = "ts,projectIdHash,fileExt,languageId,filePathHash,foldType,regionStart,regionEnd,regionLength,nestingDepth,action,timeSincePrevMs,caretOffset,visibleLineCount"

        fun get(): FoldingTelemetryService = service()
    }
}
