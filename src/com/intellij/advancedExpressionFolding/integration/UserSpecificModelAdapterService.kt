package com.intellij.advancedExpressionFolding.integration

import com.intellij.openapi.application.PathManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.ArrayList
import java.util.LinkedHashSet
import java.util.concurrent.atomic.AtomicInteger
import kotlin.io.path.exists
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.io.path.writeText
import weka.classifiers.UpdateableClassifier
import weka.classifiers.trees.HoeffdingTree
import weka.core.Attribute
import weka.core.DenseInstance
import weka.core.Instances
import weka.core.SerializationHelper

@Service(Service.Level.APP)
class UserSpecificModelAdapterService {

    private val logger = Logger.getInstance(UserSpecificModelAdapterService::class.java)

    private val lock = Any()
    private val pendingInteractions = AtomicInteger()

    private val dataDirectory: Path = Path.of(
        PathManager.getConfigPath(),
        "advancedExpressionFolding",
        "adaptive"
    )
    private val dataFile: Path = dataDirectory.resolve("user-interactions.csv")
    private val processedMarkerFile: Path = dataDirectory.resolve("user-interactions.processed")
    private val modelFile: Path = dataDirectory.resolve("user-model.bin")

    fun recordInteraction(label: String, features: List<Double>) {
        if (label.isBlank() || features.isEmpty()) {
            return
        }

        synchronized(lock) {
            try {
                Files.createDirectories(dataDirectory)
                val csvLine = buildString {
                    append(label.trim())
                    features.forEach { value ->
                        append(';')
                        append(value)
                    }
                }
                Files.writeString(
                    dataFile,
                    "$csvLine\n",
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                )

                if (pendingInteractions.incrementAndGet() >= RETRAIN_THRESHOLD) {
                    retrainLocalModelLocked()
                    pendingInteractions.set(0)
                }
            } catch (ioException: IOException) {
                logger.warn("Failed to persist user interaction", ioException)
            }
        }
    }

    fun retrainLocalModel() {
        synchronized(lock) {
            retrainLocalModelLocked()
        }
    }

    private fun retrainLocalModelLocked() {
        val records = loadRecords()
        if (records.isEmpty()) {
            return
        }

        val featureSize = records.first().features.size
        if (featureSize == 0) {
            return
        }

        val labels = records.mapTo(LinkedHashSet()) { it.label }

        var processedCount = readProcessedCount()
        if (processedCount > records.size) {
            processedCount = 0
        }
        val modelState = loadModel()

        val (classifier, structure) = if (modelState == null || !modelState.isCompatible(featureSize, labels)) {
            processedCount = 0
            val structure = buildStructure(featureSize, labels)
            createClassifier(structure) to structure
        } else {
            modelState.classifier to modelState.structure
        }

        if (processedCount >= records.size) {
            return
        }

        val classAttribute = structure.classAttribute()
        val classIndex = structure.classIndex()
        val newRecords = records.subList(processedCount, records.size)

        for (record in newRecords) {
            if (record.features.size != featureSize) {
                logger.warn("Skipping record with invalid feature vector size: ${record.features.size}")
                continue
            }

            val labelIndex = classAttribute.indexOfValue(record.label)
            if (labelIndex < 0) {
                logger.warn("Skipping record with unknown label '${record.label}'")
                continue
            }

            val values = DoubleArray(structure.numAttributes())
            record.features.forEachIndexed { index, value ->
                values[index] = value
            }
            values[classIndex] = labelIndex.toDouble()

            val instance = DenseInstance(1.0, values).apply {
                setDataset(structure)
            }

            try {
                classifier.updateClassifier(instance)
            } catch (exception: Exception) {
                logger.warn("Failed to update classifier with a new instance", exception)
            }
        }

        saveModel(classifier, structure)
        saveProcessedCount(records.size)
        pendingInteractions.set(0)
    }

    private fun buildStructure(featureSize: Int, labels: Collection<String>): Instances {
        val attributes = ArrayList<Attribute>(featureSize + 1)
        repeat(featureSize) { index ->
            attributes.add(Attribute("feature_$index"))
        }

        val classAttribute = Attribute("class", ArrayList(labels))
        attributes.add(classAttribute)

        return Instances("userSpecificFolding", attributes, 0).apply {
            setClassIndex(attributes.lastIndex)
        }
    }

    private fun createClassifier(structure: Instances): UpdateableClassifier {
        return HoeffdingTree().apply {
            buildClassifier(structure)
        }
    }

    private fun loadRecords(): List<UserInteractionRecord> {
        if (!dataFile.exists()) {
            return emptyList()
        }

        return try {
            dataFile.readLines().mapNotNull(::parseRecord)
        } catch (ioException: IOException) {
            logger.warn("Unable to read user-specific data", ioException)
            emptyList()
        }
    }

    private fun parseRecord(line: String): UserInteractionRecord? {
        if (line.isBlank()) {
            return null
        }

        val parts = line.split(';')
        if (parts.size < 2) {
            return null
        }

        val label = parts.first().trim()
        val features = parts.drop(1).mapNotNull { value ->
            value.toDoubleOrNull()
        }

        if (label.isEmpty() || features.isEmpty()) {
            return null
        }

        return UserInteractionRecord(label, features)
    }

    private fun readProcessedCount(): Int {
        if (!processedMarkerFile.exists()) {
            return 0
        }

        return try {
            processedMarkerFile.readText().trim().toInt().coerceAtLeast(0)
        } catch (exception: Exception) {
            logger.warn("Failed to read processed counter", exception)
            0
        }
    }

    private fun saveProcessedCount(count: Int) {
        try {
            Files.createDirectories(dataDirectory)
            processedMarkerFile.writeText(count.toString())
        } catch (ioException: IOException) {
            logger.warn("Unable to persist processed counter", ioException)
        }
    }

    private fun loadModel(): ModelState? {
        if (!modelFile.exists()) {
            return null
        }

        return try {
            val objects = SerializationHelper.readAll(modelFile.toString())
            val classifier = objects.getOrNull(0) as? UpdateableClassifier ?: return null
            val structure = objects.getOrNull(1) as? Instances ?: return null
            ModelState(classifier, structure)
        } catch (exception: Exception) {
            logger.warn("Failed to load stored classifier", exception)
            null
        }
    }

    private fun saveModel(classifier: UpdateableClassifier, structure: Instances) {
        try {
            Files.createDirectories(dataDirectory)
            SerializationHelper.writeAll(modelFile.toString(), arrayOf(classifier, structure))
        } catch (exception: Exception) {
            logger.warn("Failed to persist classifier", exception)
        }
    }

    private fun ModelState.isCompatible(featureSize: Int, labels: Collection<String>): Boolean {
        val expectedAttributeCount = featureSize + 1
        if (structure.numAttributes() != expectedAttributeCount) {
            return false
        }

        val classAttribute = structure.classAttribute()
        val knownLabels = (0 until classAttribute.numValues()).mapTo(LinkedHashSet()) { index ->
            classAttribute.value(index)
        }

        val targetLabels = labels.toSet()
        if (knownLabels != targetLabels) {
            return false
        }

        return true
    }

    private data class UserInteractionRecord(
        val label: String,
        val features: List<Double>
    )

    private data class ModelState(
        val classifier: UpdateableClassifier,
        val structure: Instances
    )

    companion object {
        private const val RETRAIN_THRESHOLD = 100

        fun get(): UserSpecificModelAdapterService = service()
    }
}
