package com.intellij.advancedExpressionFolding.view

import com.intellij.openapi.util.TextRange
import com.intellij.usageView.UsageInfo
import com.intellij.usages.impl.UsageViewImpl
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.CopyOnWriteArrayList

@TestOnly
object FindUsageCustomViewTestHook {
    @Volatile
    private var usageViewRef: UsageViewImpl? = null
    private val usageInfos = CopyOnWriteArrayList<UsageInfo>()
    private val ranges = CopyOnWriteArrayList<TextRange>()
    @Volatile
    private var searchFinishedCount: Int = 0

    fun onUsageViewCreated(usageView: UsageViewImpl) {
        usageViewRef = usageView
        usageInfos.clear()
        ranges.clear()
        searchFinishedCount = 0
    }

    fun onUsageAppended(usageInfo: UsageInfo, textRange: TextRange) {
        usageInfos.add(usageInfo)
        ranges.add(textRange)
    }

    fun onSearchFinished() {
        searchFinishedCount++
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

    fun searchFinishedCount(): Int = searchFinishedCount

    fun reset() {
        usageViewRef = null
        usageInfos.clear()
        ranges.clear()
        searchFinishedCount = 0
    }
}
