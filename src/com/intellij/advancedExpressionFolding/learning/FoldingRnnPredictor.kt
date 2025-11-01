package com.intellij.advancedExpressionFolding.learning

import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.editor.impl.EditorImpl
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.time.Clock
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference
import kotlin.io.path.createDirectories
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.notExists
import kotlin.math.exp

@Service(Service.Level.PROJECT)
class FoldingRnnPredictor(private val project: Project) {

    data class FoldKey(val projectHash: Int, val fileHash: Int, val foldType: String)

    data class FileKey(val projectHash: Int, val fileHash: Int)

    data class LearnedModel(
        val bias: Double,
        val weights: DoubleArray,
        val fileExtWeights: Map<String, Double>,
        val foldTypeWeights: Map<String, Double>,
        val trainedAt: Long,
    ) {
        fun predict(features: DoubleArray, fileExt: String, foldType: String): Double {
            var score = bias
            for (index in features.indices) {
                score += weights[index] * features[index]
            }
            score += fileExtWeights[fileExt] ?: 0.0
            score += foldTypeWeights[foldType] ?: 0.0
            return sigmoid(score)
        }

        fun toLines(): List<String> {
            val featureLine = weights.joinToString(separator = ",")
            val fileExtLine = fileExtWeights.entries.joinToString(separator = ";") { "${it.key}:${it.value}" }
            val foldTypeLine = foldTypeWeights.entries.joinToString(separator = ";") { "${it.key}:${it.value}" }
            return listOf(
                trainedAt.toString(),
                bias.toString(),
                featureLine,
                fileExtLine,
                foldTypeLine,
            )
        }

        companion object {
            fun fromLines(lines: List<String>): LearnedModel? {
                if (lines.size < 5) return null
                val trainedAt = lines[0].toLongOrNull() ?: return null
                val bias = lines[1].toDoubleOrNull() ?: return null
                val weights = lines[2].split(',').mapNotNull { it.toDoubleOrNull() }.toDoubleArray()
                val fileExtWeights = parseMap(lines[3])
                val foldTypeWeights = parseMap(lines[4])
                return LearnedModel(bias, weights, fileExtWeights, foldTypeWeights, trainedAt)
            }

            private fun parseMap(value: String): Map<String, Double> {
                if (value.isBlank()) return emptyMap()
                return value.split(';').mapNotNull { token ->
                    val parts = token.split(':')
                    if (parts.size != 2) return@mapNotNull null
                    val key = parts[0]
                    val weight = parts[1].toDoubleOrNull() ?: return@mapNotNull null
                    key to weight
                }.toMap()
            }

            private fun sigmoid(value: Double): Double = 1.0 / (1.0 + exp(-value))
        }
    }

    private val logger = Logger.getInstance(FoldingRnnPredictor::class.java)
    private val state = FoldingRnnState.get()
    private val telemetry = FoldingTelemetryService.get()
    private val modelRef = AtomicReference<LearnedModel?>(loadLatestModel())
    private val foldTypesByFile = ConcurrentHashMap<FileKey, MutableMap<RangeKey, String>>()
    private val expandByFile = ConcurrentHashMap<FileKey, MutableSet<RangeKey>>()
    private val cachedEvents = AtomicReference<Map<FoldKey, List<FoldingTelemetryService.TelemetryEvent>>>(emptyMap())
    @Volatile
    private var cachedEventsVersion: Long = -1

    data class RangeKey(val start: Int, val end: Int)

    fun prepareDescriptors(psiFile: PsiFile, descriptors: Array<com.intellij.lang.folding.FoldingDescriptor>): Array<com.intellij.lang.folding.FoldingDescriptor> {
        val virtualFile = psiFile.virtualFile
        val projectHash = project.locationHash.hashCode()
        val fileHash = (virtualFile?.path ?: psiFile.name).hashCode()
        val fileKey = FileKey(projectHash, fileHash)
        val rangeToType = foldTypesByFile.computeIfAbsent(fileKey) { ConcurrentHashMap() }
        val expandRanges = mutableSetOf<RangeKey>()
        val filtered = ArrayList<com.intellij.lang.folding.FoldingDescriptor>(descriptors.size)
        ensureRecentEvents()
        descriptors.forEach { descriptor ->
            val range = descriptor.range
            val foldType = descriptor.getUserData(FoldingLearningKeys.FOLD_TYPE) ?: foldTypeFromDescriptor(descriptor)
            val rangeKey = RangeKey(range.startOffset, range.endOffset)
            rangeToType[rangeKey] = foldType
            val features = buildFeatures(projectHash, fileHash, foldType)
            val probability = probabilityFor(features, foldType, virtualFile?.extension ?: "")
            when {
                probability > 0.8 -> {
                    // skip descriptor entirely
                }
                probability >= 0.5 -> {
                    descriptor.putUserData(FoldingLearningKeys.DEFAULT_EXPANDED, true)
                    filtered.add(descriptor)
                    expandRanges.add(rangeKey)
                }
                else -> {
                    filtered.add(descriptor)
                }
            }
        }
        if (expandRanges.isNotEmpty()) {
            expandByFile[fileKey] = expandRanges
        } else {
            expandByFile.remove(fileKey)
        }
        return filtered.toTypedArray()
    }

    fun applyExpansionPolicy(editor: Editor) {
        val impl = editor as? EditorImpl ?: return
        val projectHash = project.locationHash.hashCode()
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) ?: return
        val fileHash = (psiFile.virtualFile?.path ?: psiFile.name).hashCode()
        val ranges = expandByFile[FileKey(projectHash, fileHash)] ?: return
        impl.foldingModel.runBatchFoldingOperation {
            impl.foldingModel.allFoldRegions.forEach { region ->
                val key = RangeKey(region.startOffset, region.endOffset)
                if (ranges.contains(key)) {
                    region.isExpanded = true
                    region.putUserData(FoldingLearningKeys.DEFAULT_EXPANDED, true)
                }
            }
        }
    }

    fun resolveFoldType(psiFile: PsiFile, region: FoldRegion): String {
        val virtualFile = psiFile.virtualFile
        val projectHash = project.locationHash.hashCode()
        val fileHash = (virtualFile?.path ?: psiFile.name).hashCode()
        val fileKey = FileKey(projectHash, fileHash)
        val rangeKey = RangeKey(region.startOffset, region.endOffset)
        return foldTypesByFile[fileKey]?.get(rangeKey) ?: region.getUserData(FoldingLearningKeys.FOLD_TYPE) ?: "unknown"
    }

    fun updateModel(model: LearnedModel?, checkpoint: Path?) {
        modelRef.set(model)
        checkpoint?.let { keepLatestCheckpoints(it.parent) }
    }

    private fun keepLatestCheckpoints(modelsDir: Path?) {
        if (modelsDir == null || modelsDir.notExists()) return
        try {
            val files = modelsDir.listDirectoryEntries().sortedByDescending { it.fileName.toString() }
            if (files.size > 2) {
                files.drop(2).forEach { Files.deleteIfExists(it) }
            }
        } catch (exception: IOException) {
            logger.warn("Failed pruning checkpoints", exception)
        }
    }

    private fun buildFeatures(projectHash: Int, fileHash: Int, foldType: String): DoubleArray {
        val key = FoldKey(projectHash, fileHash, foldType)
        val events = cachedEvents.get()[key]
        if (events.isNullOrEmpty()) {
            return DoubleArray(FEATURE_COUNT)
        }
        val lastEvents = events.takeLast(MAX_HISTORY)
        var deltaSum = 0.0
        var lengthSum = 0.0
        var depthSum = 0.0
        var collapse = 0
        var expand = 0
        var sessionAgeSum = 0.0
        var tsSum = 0.0
        var count = 0
        lastEvents.forEach { event ->
            tsSum += (event.ts % DAY_MS).toDouble() / DAY_MS
            deltaSum += normalize(event.timeSincePrevMs.toDouble(), DELTA_SCALE)
            lengthSum += normalize(event.regionLength.toDouble(), LENGTH_SCALE)
            depthSum += normalize(event.nestingDepth.toDouble(), DEPTH_SCALE)
            sessionAgeSum += normalize(event.timeSincePrevMs.toDouble(), SESSION_SCALE)
            when (event.action) {
                "collapse" -> collapse += 1
                "expand" -> expand += 1
            }
            count += 1
        }
        if (count == 0) {
            return DoubleArray(FEATURE_COUNT)
        }
        return doubleArrayOf(
            tsSum / count,
            deltaSum / count,
            lengthSum / count,
            depthSum / count,
            collapse.toDouble() / count,
            expand.toDouble() / count,
            sessionAgeSum / count,
            count.toDouble() / MAX_HISTORY
        )
    }

    private fun normalize(value: Double, scale: Double): Double {
        if (value <= 0.0) return 0.0
        return (value / scale).coerceIn(0.0, 1.0)
    }

    private fun probabilityFor(features: DoubleArray, foldType: String, fileExt: String): Double {
        val model = modelRef.get()
        if (model != null) {
            return model.predict(features, fileExt, foldType)
        }
        // heuristic fallback: prefer folding by default
        val expandRatio = features.getOrNull(5) ?: 0.0
        return (expandRatio * 0.8).coerceIn(0.0, 1.0)
    }

    private fun foldTypeFromDescriptor(descriptor: com.intellij.lang.folding.FoldingDescriptor): String {
        val text = descriptor.placeholderText
        if (!text.isNullOrBlank()) {
            return text.trim().take(32)
        }
        return descriptor.group?.toString()?.substringAfterLast('.') ?: "unknown"
    }

    private fun ensureRecentEvents() {
        val events = telemetry.readEvents().toList()
        val latest = events.maxOfOrNull { it.ts } ?: -1
        if (latest == cachedEventsVersion) {
            return
        }
        val grouped = events.filter { it.action != "open" }.groupBy { FoldKey(it.projectIdHash, it.filePathHash, it.foldType) }
        cachedEvents.set(grouped.mapValues { (_, list) -> list.sortedBy { it.ts } })
        cachedEventsVersion = latest
    }

    private fun loadLatestModel(): LearnedModel? {
        val modelsDir = state.modelsDir()
        if (modelsDir.notExists()) {
            return null
        }
        val prefix = "model-${ApplicationInfo.getInstance().build.baselineVersion}"
        val candidates = modelsDir.listDirectoryEntries().filter { it.fileName.toString().startsWith(prefix) }.sortedByDescending { it.fileName.toString() }
        val latest = candidates.firstOrNull() ?: return null
        return try {
            Files.readAllLines(latest).let(LearnedModel::fromLines)
        } catch (exception: IOException) {
            logger.warn("Unable to load folding model", exception)
            null
        }
    }

    fun saveModel(model: LearnedModel): Path? {
        val modelsDir = state.modelsDir()
        if (modelsDir.notExists()) {
            modelsDir.createDirectories()
        }
        val fileName = "model-${ApplicationInfo.getInstance().build.baselineVersion}-${Clock.systemUTC().millis()}.txt"
        val path = modelsDir.resolve(fileName)
        return try {
            Files.write(path, model.toLines())
            modelRef.set(model)
            path
        } catch (exception: IOException) {
            logger.warn("Failed to store folding model", exception)
            null
        }
    }

    companion object {
        private const val DAY_MS = 86_400_000.0
        private const val DELTA_SCALE = 600_000.0
        private const val LENGTH_SCALE = 5_000.0
        private const val DEPTH_SCALE = 8.0
        private const val SESSION_SCALE = 3_600_000.0
        private const val FEATURE_COUNT = 8
        private const val MAX_HISTORY = 20

        fun get(project: Project): FoldingRnnPredictor = project.getService(FoldingRnnPredictor::class.java)
    }
}
