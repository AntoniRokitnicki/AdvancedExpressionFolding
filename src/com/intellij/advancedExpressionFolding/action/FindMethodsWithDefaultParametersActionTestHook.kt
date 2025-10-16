package com.intellij.advancedExpressionFolding.action

import com.intellij.openapi.progress.ProgressIndicator
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicReference

@TestOnly
object FindMethodsWithDefaultParametersActionTestHook {
    private data class ProgressEvent(
        val fileName: String,
        val processedCount: Int,
        val text: String?,
        val fraction: Double,
        val canceled: Boolean
    ) {
        fun serialize(): String = listOf(
            fileName,
            processedCount.toString(),
            text.orEmpty(),
            fraction.toString(),
            canceled.toString()
        ).joinToString("|")
    }

    private data class FinishedEvent(
        val processedCount: Int,
        val text: String?,
        val fraction: Double,
        val canceled: Boolean
    ) {
        fun serialize(): String = listOf(
            "finished",
            processedCount.toString(),
            text.orEmpty(),
            fraction.toString(),
            canceled.toString()
        ).joinToString("|")
    }

    private data class Options(val cancelAfter: Int? = null, val delayPerFileMillis: Long = 0)

    private val processedEvents = CopyOnWriteArrayList<ProgressEvent>()
    private val finishedEvent = AtomicReference<FinishedEvent?>()
    private val options = AtomicReference(Options())

    fun reset() {
        processedEvents.clear()
        finishedEvent.set(null)
        options.set(Options())
    }

    fun onFileProcessed(fileName: String, processedCount: Int, indicator: ProgressIndicator) {
        processedEvents.add(
            ProgressEvent(
                fileName = fileName,
                processedCount = processedCount,
                text = indicator.text,
                fraction = indicator.fraction,
                canceled = indicator.isCanceled
            )
        )

        val currentOptions = options.get()
        if (currentOptions.delayPerFileMillis > 0) {
            Thread.sleep(currentOptions.delayPerFileMillis)
        }

        currentOptions.cancelAfter?.takeIf { processedCount >= it }?.let {
            indicator.cancel()
        }
    }

    fun onFinished(processedCount: Int, indicator: ProgressIndicator) {
        finishedEvent.set(
            FinishedEvent(
                processedCount = processedCount,
                text = indicator.text,
                fraction = indicator.fraction,
                canceled = indicator.isCanceled
            )
        )
    }

    fun setCancelAfter(processedCount: Int?) {
        options.updateAndGet { it.copy(cancelAfter = processedCount) }
    }

    fun setDelayPerFileMillis(delay: Long) {
        options.updateAndGet { it.copy(delayPerFileMillis = delay) }
    }

    fun getProcessedEvents(): List<String> = processedEvents.map { it.serialize() }

    fun getFinishedEvent(): String? = finishedEvent.get()?.serialize()
}
