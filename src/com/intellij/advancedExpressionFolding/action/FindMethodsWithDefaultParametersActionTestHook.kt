package com.intellij.advancedExpressionFolding.action

import com.intellij.openapi.progress.ProgressIndicator
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.CopyOnWriteArrayList

@TestOnly
object FindMethodsWithDefaultParametersActionTestHook {
    private val processedEvents = CopyOnWriteArrayList<String>()
    @Volatile
    private var finishedEvent: String? = null
    @Volatile
    private var cancelAfter: Int? = null
    @Volatile
    private var delayPerFileMillis: Long = 0

    fun reset() {
        processedEvents.clear()
        finishedEvent = null
        cancelAfter = null
        delayPerFileMillis = 0
    }

    fun onFileProcessed(fileName: String, processedCount: Int, indicator: ProgressIndicator) {
        processedEvents.add(serializeEvent(fileName, processedCount, indicator))
        val delay = delayPerFileMillis
        if (delay > 0) {
            Thread.sleep(delay)
        }
        cancelAfter?.takeIf { processedCount >= it }?.let {
            indicator.cancel()
        }
    }

    fun onFinished(processedCount: Int, indicator: ProgressIndicator) {
        finishedEvent = serializeEvent("finished", processedCount, indicator)
    }

    fun setCancelAfter(processedCount: Int?) {
        cancelAfter = processedCount
    }

    fun setDelayPerFileMillis(delay: Long) {
        delayPerFileMillis = delay
    }

    fun getProcessedEvents(): List<String> = processedEvents.toList()

    fun getFinishedEvent(): String? = finishedEvent

    private fun serializeEvent(fileName: String, processedCount: Int, indicator: ProgressIndicator): String {
        val text = indicator.text ?: ""
        return listOf(
            fileName,
            processedCount.toString(),
            text,
            indicator.fraction.toString(),
            indicator.isCanceled.toString()
        ).joinToString("|")
    }
}
