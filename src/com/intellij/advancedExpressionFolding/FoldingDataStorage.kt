package com.intellij.advancedExpressionFolding

import com.intellij.openapi.application.PathManager
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

class FoldingDataStorage {

    private val baseDir: Path = PathManager.getConfigDir().resolve("advancedExpressionFolding")
    private val eventsFile: Path = baseDir.resolve("folding-events.tsv")
    private val modelFile: Path = baseDir.resolve("folding-model.bin")
    private val preferenceFile: Path = baseDir.resolve("folding-preference.txt")

    private val lock = ReentrantReadWriteLock()
    private var cachedEvents: MutableList<FoldingInteraction>? = null

    init {
        Files.createDirectories(baseDir)
    }

    fun append(interaction: FoldingInteraction) {
        lock.write {
            val events = cachedEvents ?: loadEventsInternal().toMutableList().also { cachedEvents = it }
            events.add(interaction)
            Files.writeString(
                eventsFile,
                encode(interaction) + "\n",
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            )
        }
    }

    fun loadInteractions(): List<FoldingInteraction> = lock.read {
        cachedEvents?.toList() ?: loadEventsInternal().also { cachedEvents = it.toMutableList() }
    }

    fun loadRecentInteractions(limit: Int): List<FoldingInteraction> = lock.read {
        val interactions = cachedEvents ?: loadEventsInternal().also { cachedEvents = it.toMutableList() }
        if (limit <= 0 || interactions.isEmpty()) emptyList() else interactions.takeLast(limit)
    }

    fun lastTimestamp(): Long? = lock.read {
        val events = cachedEvents ?: loadEventsInternal().also { cachedEvents = it.toMutableList() }
        events.lastOrNull()?.timestamp
    }

    fun saveModel(snapshot: FoldingRNNModel.Snapshot) {
        lock.write {
            ObjectOutputStream(
                Files.newOutputStream(modelFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
            ).use { output ->
                output.writeObject(snapshot)
            }
        }
    }

    fun readModel(): FoldingRNNModel.Snapshot? = lock.read {
        if (!Files.exists(modelFile)) return null
        ObjectInputStream(Files.newInputStream(modelFile)).use { input ->
            input.readObject() as? FoldingRNNModel.Snapshot
        }
    }

    fun saveLastPreference(collapsed: Boolean) {
        lock.write {
            Files.writeString(
                preferenceFile,
                collapsed.toString(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            )
        }
    }

    fun readLastPreference(): Boolean? = lock.read {
        if (!Files.exists(preferenceFile)) return null
        Files.readString(preferenceFile).trim().takeIf(String::isNotEmpty)?.toBooleanStrictOrNull()
    }

    fun count(): Int = lock.read {
        cachedEvents?.size ?: loadEventsInternal().size
    }

    private fun encode(interaction: FoldingInteraction): String = buildString {
        append(interaction.foldingId.replace('\t', ' '))
        append('\t')
        append(interaction.timestamp)
        append('\t')
        append(interaction.collapsed)
        append('\t')
        append(interaction.fileType.replace('\t', ' '))
        append('\t')
        append(interaction.foldingType.replace('\t', ' '))
        append('\t')
        append(interaction.sessionDurationMillis)
        append('\t')
        append(interaction.timeSinceLastMillis)
    }

    private fun decode(line: String): FoldingInteraction? {
        val parts = line.split('\t')
        if (parts.size < 7) return null
        return FoldingInteraction(
            foldingId = parts[0],
            timestamp = parts[1].toLongOrNull() ?: return null,
            collapsed = parts[2].toBooleanStrictOrNull() ?: return null,
            fileType = parts[3],
            foldingType = parts[4],
            sessionDurationMillis = parts[5].toLongOrNull() ?: return null,
            timeSinceLastMillis = parts[6].toLongOrNull() ?: return null
        )
    }

    private fun loadEventsInternal(): List<FoldingInteraction> {
        if (!Files.exists(eventsFile)) return emptyList()
        return Files.readAllLines(eventsFile).mapNotNull(::decode)
    }
}

data class FoldingInteraction(
    val foldingId: String,
    val timestamp: Long,
    val collapsed: Boolean,
    val fileType: String,
    val foldingType: String,
    val sessionDurationMillis: Long,
    val timeSinceLastMillis: Long
)
