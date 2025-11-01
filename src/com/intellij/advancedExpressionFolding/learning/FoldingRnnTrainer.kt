package com.intellij.advancedExpressionFolding.learning

import com.intellij.advancedExpressionFolding.learning.FoldingRnnPredictor.FoldKey
import com.intellij.advancedExpressionFolding.learning.FoldingRnnPredictor.FileKey
import com.intellij.advancedExpressionFolding.learning.FoldingRnnPredictor.LearnedModel
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.ProjectManager
import com.intellij.util.concurrency.AppExecutorUtil
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.time.Clock
import java.util.HashMap
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.io.path.createDirectories
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.notExists
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.min

@Service(Service.Level.APP)
class FoldingRnnTrainer(
    private val telemetry: FoldingTelemetryService = FoldingTelemetryService.get(),
    private val state: FoldingRnnState = FoldingRnnState.get(),
) {

    private val logger = Logger.getInstance(FoldingRnnTrainer::class.java)
    private val executor = AppExecutorUtil.createBoundedApplicationPoolExecutor("AEF.FoldingTrainer", 1)
    private val running = AtomicBoolean(false)
    @Volatile
    private var currentFuture: Future<*>? = null

    fun maybeTrain(force: Boolean = false) {
        if (!AdvancedExpressionFoldingSettings.getInstance().state.learnFoldingPreference) {
            state.deleteArtifacts()
            return
        }
        if (!state.ensureEnabled()) {
            return
        }
        val now = System.currentTimeMillis()
        val lastTrain = state.state.lastTrainTs
        if (!force && lastTrain > 0 && now - lastTrain < ONE_DAY_MS) {
            return
        }
        if (!running.compareAndSet(false, true)) {
            return
        }
        currentFuture = executor.submit {
            try {
                runTraining()
            } finally {
                running.set(false)
            }
        }
    }

    fun cancel() {
        currentFuture?.cancel(true)
    }

    private fun runTraining() {
        val events = telemetry.readEvents().toList()
        if (events.size < state.state.minEventsForTraining) {
            return
        }
        val dataset = buildDataset(events)
        if (dataset.train.isEmpty()) {
            return
        }
        val model = train(dataset) ?: return
        val path = writeModel(model)
        val projects = ProjectManager.getInstance().openProjects
        projects.forEach { project ->
            FoldingRnnPredictor.get(project).updateModel(model, path)
        }
        state.markTrained(System.currentTimeMillis())
    }

    private fun writeModel(model: LearnedModel): Path? {
        val modelsDir = state.modelsDir()
        if (modelsDir.notExists()) {
            modelsDir.createDirectories()
        }
        val fileName = "model-${ApplicationInfo.getInstance().build.baselineVersion}-${Clock.systemUTC().millis()}.txt"
        val path = modelsDir.resolve(fileName)
        return try {
            Files.write(path, model.toLines())
            pruneOldCheckpoints(modelsDir)
            path
        } catch (exception: IOException) {
            logger.warn("Unable to write folding model", exception)
            null
        }
    }

    private fun pruneOldCheckpoints(dir: Path) {
        try {
            val files = dir.listDirectoryEntries().sortedByDescending { it.fileName.toString() }
            if (files.size > 2) {
                files.drop(2).forEach { Files.deleteIfExists(it) }
            }
        } catch (_: IOException) {
            // ignore cleanup failures
        }
    }

    internal data class DerivedEvent(
        val event: FoldingTelemetryService.TelemetryEvent,
        val features: DoubleArray,
    )

    internal data class Sample(
        val features: DoubleArray,
        val label: Double,
        val fileExt: String,
        val foldType: String,
        val ts: Long,
    )

    internal data class Dataset(
        val train: List<Sample>,
        val eval: List<Sample>,
    )

    companion object {
        private const val STREAK_CAP = 5
        private const val EPOCHS = 5
        private const val EARLY_STOP_PATIENCE = 2
        private const val LEARNING_RATE = 0.05
        private const val ONE_DAY_MS = 86_400_000L
        private const val TWO_MINUTES_MS = 120_000L
        private const val SEVEN_DAYS_MS = 7L * 24 * 60 * 60 * 1000
        private const val DAY_MS = 86_400_000.0
        private const val DELTA_SCALE = 600_000.0
        private const val LENGTH_SCALE = 5_000.0
        private const val DEPTH_SCALE = 8.0
        private const val SESSION_SCALE = 3_600_000.0

        internal fun buildDatasetForTesting(events: List<FoldingTelemetryService.TelemetryEvent>): Dataset = buildDataset(events)

        private fun buildDataset(events: List<FoldingTelemetryService.TelemetryEvent>): Dataset {
            val sorted = events.sortedBy { it.ts }
            val lastOpenByFile = mutableMapOf<FileKey, Long>()
            val lastTsByKey = mutableMapOf<FoldKey, Long>()
            val collapseStreak = mutableMapOf<FoldKey, Int>()
            val expandStreak = mutableMapOf<FoldKey, Int>()
            val sequences = mutableMapOf<FoldKey, MutableList<DerivedEvent>>()
            sorted.forEach { event ->
                if (event.action == "open") {
                    lastOpenByFile[FileKey(event.projectIdHash, event.filePathHash)] = event.ts
                    return@forEach
                }
                if (event.action != "collapse" && event.action != "expand") {
                    return@forEach
                }
                val key = FoldKey(event.projectIdHash, event.filePathHash, event.foldType)
                val delta = lastTsByKey[key]?.let { event.ts - it } ?: 0L
                lastTsByKey[key] = event.ts
                val collapse = if (event.action == "collapse") (collapseStreak[key] ?: 0) + 1 else 0
                val expand = if (event.action == "expand") (expandStreak[key] ?: 0) + 1 else 0
                collapseStreak[key] = min(collapse, STREAK_CAP)
                expandStreak[key] = min(expand, STREAK_CAP)
                if (event.action == "collapse") {
                    expandStreak[key] = 0
                } else {
                    collapseStreak[key] = 0
                }
                val sessionAge = event.ts - (lastOpenByFile[FileKey(event.projectIdHash, event.filePathHash)] ?: event.ts)
                val features = doubleArrayOf(
                    (event.ts % DAY_MS).toDouble() / DAY_MS,
                    normalize(delta.toDouble(), DELTA_SCALE),
                    normalize(event.regionLength.toDouble(), LENGTH_SCALE),
                    normalize(event.nestingDepth.toDouble(), DEPTH_SCALE),
                    if (event.action == "collapse") 1.0 else 0.0,
                    if (event.action == "expand") 1.0 else 0.0,
                    (collapseStreak[key] ?: 0).toDouble() / STREAK_CAP,
                    normalize(sessionAge.toDouble(), SESSION_SCALE),
                )
                sequences.computeIfAbsent(key) { mutableListOf() }.add(DerivedEvent(event, features))
            }
            val samples = mutableListOf<Sample>()
            sequences.forEach { (_, list) ->
                val ordered = list.sortedBy { it.event.ts }
                for (index in ordered.indices) {
                    val label = if (wantsOff(ordered, index)) 1.0 else 0.0
                    val derived = ordered[index]
                    samples.add(
                        Sample(
                            features = derived.features,
                            label = label,
                            fileExt = derived.event.fileExt,
                            foldType = derived.event.foldType,
                            ts = derived.event.ts,
                        )
                    )
                }
            }
            val sortedSamples = samples.sortedBy { it.ts }
            if (sortedSamples.isEmpty()) {
                return Dataset(emptyList(), emptyList())
            }
            val split = max(1, (sortedSamples.size * 0.8).toInt())
            val train = sortedSamples.take(split)
            val eval = sortedSamples.drop(split).ifEmpty { train }
            return Dataset(train, eval)
        }

        private fun wantsOff(sequence: List<DerivedEvent>, index: Int): Boolean {
            val current = sequence[index].event
            val windowEnd = current.ts + SEVEN_DAYS_MS
            var count = 0
            var lastTs = current.ts
            for (i in index + 1 until sequence.size) {
                val next = sequence[i].event
                if (next.ts > windowEnd) {
                    break
                }
                if (next.action == "expand") {
                    val delta = next.ts - lastTs
                    if (delta <= TWO_MINUTES_MS) {
                        count += 1
                        lastTs = next.ts
                        if (count >= 3) {
                            return true
                        }
                    } else {
                        break
                    }
                } else if (next.action == "collapse") {
                    break
                }
            }
            return false
        }

        internal fun trainForTesting(dataset: Dataset): LearnedModel? = train(dataset)

        private fun train(dataset: Dataset): LearnedModel? {
            if (dataset.train.isEmpty()) {
                return null
            }
            val featureCount = dataset.train.first().features.size
            val weights = DoubleArray(featureCount)
            var bias = 0.0
            val fileExtWeights = mutableMapOf<String, Double>()
            val foldTypeWeights = mutableMapOf<String, Double>()
            var bestLoss = Double.POSITIVE_INFINITY
            var bestModel: LearnedModel? = null
            var patience = 0
            repeat(EPOCHS) {
                dataset.train.forEach { sample ->
                    val prediction = sigmoid(score(sample, weights, bias, fileExtWeights, foldTypeWeights))
                    val error = prediction - sample.label
                    for (index in weights.indices) {
                        weights[index] -= LEARNING_RATE * error * sample.features[index]
                    }
                    bias -= LEARNING_RATE * error
                    fileExtWeights[sample.fileExt] = (fileExtWeights[sample.fileExt] ?: 0.0) - LEARNING_RATE * error
                    foldTypeWeights[sample.foldType] = (foldTypeWeights[sample.foldType] ?: 0.0) - LEARNING_RATE * error
                }
                val loss = logisticLoss(dataset.eval, weights, bias, fileExtWeights, foldTypeWeights)
                if (loss < bestLoss - 1e-6) {
                    bestLoss = loss
                    bestModel = LearnedModel(bias, weights.copyOf(), HashMap(fileExtWeights), HashMap(foldTypeWeights), System.currentTimeMillis())
                    patience = 0
                } else {
                    patience += 1
                    if (patience >= EARLY_STOP_PATIENCE) {
                        return bestModel
                    }
                }
            }
            return bestModel
        }

        private fun logisticLoss(
            samples: List<Sample>,
            weights: DoubleArray,
            bias: Double,
            fileExtWeights: Map<String, Double>,
            foldTypeWeights: Map<String, Double>,
        ): Double {
            if (samples.isEmpty()) {
                return 0.0
            }
            var sum = 0.0
            samples.forEach { sample ->
                val prediction = sigmoid(score(sample, weights, bias, fileExtWeights, foldTypeWeights))
                val clipped = prediction.coerceIn(1e-6, 1.0 - 1e-6)
                sum += -sample.label * ln(clipped) - (1.0 - sample.label) * ln(1.0 - clipped)
            }
            return sum / samples.size
        }

        private fun score(
            sample: Sample,
            weights: DoubleArray,
            bias: Double,
            fileExtWeights: Map<String, Double>,
            foldTypeWeights: Map<String, Double>,
        ): Double {
            var total = bias
            for (index in weights.indices) {
                total += weights[index] * sample.features[index]
            }
            total += fileExtWeights[sample.fileExt] ?: 0.0
            total += foldTypeWeights[sample.foldType] ?: 0.0
            return total
        }

        private fun sigmoid(value: Double): Double = 1.0 / (1.0 + kotlin.math.exp(-value))

        private fun normalize(value: Double, scale: Double): Double {
            if (value <= 0.0) {
                return 0.0
            }
            return (value / scale).coerceIn(0.0, 1.0)
        }

        fun get(): FoldingRnnTrainer = service()
    }
}
