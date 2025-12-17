package com.intellij.advancedExpressionFolding

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.util.ArrayDeque
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.abs
import kotlin.math.min
import kotlin.time.Duration.Companion.hours

@Service(Service.Level.APP)
class FoldingPredictor {

    private val storage = FoldingDataStorage()
    private val model = FoldingRNNModel(FEATURE_SIZE, hiddenUnits = HIDDEN_UNITS)
    private val scope = FoldingServiceCoroutineScope.get()

    private val suppressionCounter = AtomicInteger(0)
    private val trainingInProgress = AtomicBoolean(false)
    private val stateLock = Any()

    @Volatile
    private var lastEventTimestamp: Long? = null

    @Volatile
    private var lastPreference: Boolean? = null

    @Volatile
    private var trainedOnCount: Int = 0

    init {
        val interactions = storage.loadInteractions()
        lastEventTimestamp = interactions.lastOrNull()?.timestamp ?: storage.lastTimestamp()
        lastPreference = storage.readLastPreference() ?: interactions.lastOrNull()?.collapsed
        storage.readModel()?.let(model::load)
        if (model.isReady()) {
            trainedOnCount = interactions.size
        }
    }

    fun registerEditor(editor: Editor) {
        FoldingEventCollector.attach(editor)
    }

    fun unregisterEditor(editor: Editor) {
        FoldingEventCollector.detach(editor)
    }

    fun onUserAction(event: FoldingEvent) {
        val interaction = synchronized(stateLock) {
            val delta = lastEventTimestamp?.let { event.timestamp - it } ?: 0L
            lastEventTimestamp = event.timestamp
            FoldingInteraction(
                foldingId = event.foldingId,
                timestamp = event.timestamp,
                collapsed = event.collapsed,
                fileType = event.fileType,
                foldingType = event.foldingType,
                sessionDurationMillis = event.sessionDurationMillis,
                timeSinceLastMillis = delta
            ).also { interaction ->
                storage.append(interaction)
                lastPreference = interaction.collapsed
                storage.saveLastPreference(interaction.collapsed)
            }
        }
        maybeScheduleTraining()
    }

    fun applyPredictions(editor: Editor) {
        val project = editor.project ?: return
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) ?: return
        val regions = editor.foldingModel.allFoldRegions.filter(FoldRegion::isAdvancedExpressionFoldingGroup)
        if (regions.isEmpty()) {
            return
        }
        val now = System.currentTimeMillis()
        val sessionDuration = FoldingEventCollector.sessionDuration(editor, now)
        val timeSinceLast = synchronized(stateLock) { lastEventTimestamp?.let { now - it } ?: 0L }
        val previous = storage.loadRecentInteractions(SEQUENCE_WINDOW - 1)
        val fallback = synchronized(stateLock) { lastPreference }
        val canPredict = model.isReady()

        runWithoutCollection {
            editor.foldingModel.runBatchFoldingOperation {
                regions.forEach { region ->
                    val candidate = CandidateContext(
                        foldingId = computeFoldingId(psiFile, region),
                        timestamp = now,
                        fileType = psiFile.fileType.name,
                        foldingType = describeRegion(region),
                        sessionDurationMillis = sessionDuration,
                        timeSinceLastMillis = timeSinceLast
                    )
                    val probability = if (canPredict) predictCandidate(previous, candidate) else null
                    val desiredCollapsed = decideDesiredState(probability, fallback)
                    desiredCollapsed?.let { region.isExpanded = !it }
                }
            }
        }
    }

    fun shouldCollectEvents(): Boolean = suppressionCounter.get() == 0

    fun runWithoutCollection(block: () -> Unit) {
        suppressionCounter.incrementAndGet()
        try {
            block()
        } finally {
            suppressionCounter.decrementAndGet()
        }
    }

    private fun maybeScheduleTraining() {
        val count = storage.count()
        if (count < MIN_EVENTS_FOR_TRAINING) {
            return
        }
        val trainedCountSnapshot = trainedOnCount
        if (count - trainedCountSnapshot < RETRAIN_THRESHOLD) {
            return
        }
        if (!trainingInProgress.compareAndSet(false, true)) {
            return
        }
        scope.launch {
            try {
                trainModel()
            } finally {
                trainingInProgress.set(false)
            }
        }
    }

    private fun trainModel() {
        val interactions = storage.loadInteractions()
        if (interactions.size < MIN_EVENTS_FOR_TRAINING) {
            return
        }
        val dataset = buildTrainingDataset(interactions)
        if (dataset.isEmpty()) {
            return
        }
        model.train(dataset)
        storage.saveModel(model.snapshot())
        trainedOnCount = interactions.size
    }

    private fun buildTrainingDataset(events: List<FoldingInteraction>): List<FoldingTrainingExample> {
        if (events.isEmpty()) {
            return emptyList()
        }
        val examples = mutableListOf<FoldingTrainingExample>()
        events.forEachIndexed { index, _ ->
            val from = (index - SEQUENCE_WINDOW + 1).coerceAtLeast(0)
            val relevant = events.subList(from, index + 1)
            val history = ArrayDeque<Boolean>()
            val sequence = mutableListOf<DoubleArray>()
            relevant.forEach { event ->
                sequence.add(
                    buildFeatureVector(
                        timestamp = event.timestamp,
                        fileType = event.fileType,
                        foldingType = event.foldingType,
                        sessionDurationMillis = event.sessionDurationMillis,
                        timeSinceLastMillis = event.timeSinceLastMillis,
                        actionHistory = history.toList()
                    )
                )
                history.addLast(event.collapsed)
                if (history.size > WINDOW_FOR_SEQUENCE) {
                    history.removeFirst()
                }
            }
            val label = if (relevant.last().collapsed) 1.0 else 0.0
            examples.add(FoldingTrainingExample(sequence, label))
        }
        return examples
    }

    private fun predictCandidate(previous: List<FoldingInteraction>, candidate: CandidateContext): Double? {
        val sequence = buildSequence(previous, candidate)
        if (sequence.isEmpty()) {
            return null
        }
        return model.predict(sequence)
    }

    private fun buildSequence(previous: List<FoldingInteraction>, candidate: CandidateContext): List<DoubleArray> {
        val history = ArrayDeque<Boolean>()
        val features = mutableListOf<DoubleArray>()
        val trimmed = if (previous.size >= SEQUENCE_WINDOW - 1) {
            previous.takeLast(SEQUENCE_WINDOW - 1)
        } else {
            previous
        }
        trimmed.forEach { interaction ->
            features.add(
                buildFeatureVector(
                    timestamp = interaction.timestamp,
                    fileType = interaction.fileType,
                    foldingType = interaction.foldingType,
                    sessionDurationMillis = interaction.sessionDurationMillis,
                    timeSinceLastMillis = interaction.timeSinceLastMillis,
                    actionHistory = history.toList()
                )
            )
            history.addLast(interaction.collapsed)
            if (history.size > WINDOW_FOR_SEQUENCE) {
                history.removeFirst()
            }
        }
        features.add(
            buildFeatureVector(
                timestamp = candidate.timestamp,
                fileType = candidate.fileType,
                foldingType = candidate.foldingType,
                sessionDurationMillis = candidate.sessionDurationMillis,
                timeSinceLastMillis = candidate.timeSinceLastMillis,
                actionHistory = history.toList()
            )
        )
        return features
    }

    private fun buildFeatureVector(
        timestamp: Long,
        fileType: String,
        foldingType: String,
        sessionDurationMillis: Long,
        timeSinceLastMillis: Long,
        actionHistory: List<Boolean>
    ): DoubleArray {
        val zoned = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault())
        val hour = zoned.hour / 23.0
        val day = zoned.dayOfWeek.ordinal / 6.0
        val timeSince = normalize(timeSinceLastMillis, TIME_NORMALIZER_MILLIS)
        val session = normalize(sessionDurationMillis, SESSION_NORMALIZER_MILLIS)
        val fileFeature = hashFeature(fileType)
        val foldingFeature = hashFeature(foldingType)
        val collapsedRatio = if (actionHistory.isEmpty()) 0.5 else actionHistory.count { it }.toDouble() / actionHistory.size
        val lastAction = when {
            actionHistory.isEmpty() -> 0.5
            actionHistory.last() -> 1.0
            else -> 0.0
        }
        val momentumWindow = actionHistory.takeLast(min(3, actionHistory.size))
        val momentum = if (momentumWindow.isEmpty()) 0.5 else momentumWindow.count { it }.toDouble() / momentumWindow.size
        val historyFill = (actionHistory.size.toDouble() / WINDOW_FOR_SEQUENCE).coerceIn(0.0, 1.0)
        return doubleArrayOf(hour, day, timeSince, session, fileFeature, foldingFeature, collapsedRatio, lastAction, momentum, historyFill)
    }

    private fun decideDesiredState(probability: Double?, fallback: Boolean?): Boolean? {
        probability ?: return fallback
        return when {
            probability >= CONFIDENCE_THRESHOLD -> true
            (1.0 - probability) >= CONFIDENCE_THRESHOLD -> false
            else -> fallback
        }
    }

    companion object {
        private const val FEATURE_SIZE = 10
        private const val HIDDEN_UNITS = 64
        private const val MIN_EVENTS_FOR_TRAINING = 120
        private const val RETRAIN_THRESHOLD = 50
        private const val SEQUENCE_WINDOW = 12
        private const val WINDOW_FOR_SEQUENCE = 10
        private const val CONFIDENCE_THRESHOLD = 0.7
        private val TIME_NORMALIZER_MILLIS = (3.hours).inWholeMilliseconds.toDouble()
        private val SESSION_NORMALIZER_MILLIS = (4.hours).inWholeMilliseconds.toDouble()

        fun get(): FoldingPredictor = service()

        fun computeFoldingId(psiFile: PsiFile, region: FoldRegion): String {
            val fileId = psiFile.virtualFile?.path ?: psiFile.name
            return buildString {
                append(fileId)
                append(":")
                append(region.startOffset)
                append(":")
                append(region.endOffset)
            }
        }

        fun describeRegion(region: FoldRegion): String =
            region.group?.toString() ?: region.placeholderText.orEmpty()
    }

    private fun normalize(value: Long, maxValue: Double): Double {
        if (maxValue <= 0.0) {
            return 0.0
        }
        return (value.toDouble() / maxValue).coerceIn(0.0, 1.0)
    }

    private fun hashFeature(value: String): Double {
        val hashed = abs(value.hashCode()) % 1000
        return hashed / 1000.0
    }

    private data class CandidateContext(
        val foldingId: String,
        val timestamp: Long,
        val fileType: String,
        val foldingType: String,
        val sessionDurationMillis: Long,
        val timeSinceLastMillis: Long
    )
}
