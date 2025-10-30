package com.intellij.advancedExpressionFolding.view

import com.intellij.openapi.util.TextRange
import com.intellij.usageView.UsageInfo
import com.intellij.usages.impl.UsageViewImpl
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.CopyOnWriteArrayList

@TestOnly
object FindUsageCustomViewTestHook {
    private val usageInfos = CopyOnWriteArrayList<UsageInfo>()
    private val ranges = CopyOnWriteArrayList<TextRange>()
    @Volatile
    private var finished: Boolean = false

    fun onUsageViewCreated(@Suppress("UNUSED_PARAMETER") usageView: UsageViewImpl) {
        usageInfos.clear()
        ranges.clear()
        finished = false
    }

    fun onUsageAppended(usageInfo: UsageInfo, textRange: TextRange) {
        usageInfos.add(usageInfo)
        ranges.add(textRange)
    }

    fun onSearchFinished() {
        finished = true
    }

    fun dumpUsageData(): List<String> {
        return usageInfos.mapIndexed { index, usageInfo ->
            val range = usageInfo.rangeInElement ?: usageInfo.navigationRange ?: ranges[index]
            val fileName = usageInfo.file?.name ?: usageInfo.virtualFile?.name ?: "unknown"
            val start = range?.startOffset ?: ranges[index].startOffset
            val end = range?.endOffset ?: ranges[index].endOffset
            "$fileName:$start:$end"
        }
    }

    fun isSearchFinished(): Boolean = finished

    fun reset() {
        usageInfos.clear()
        ranges.clear()
        finished = false
    }
}
