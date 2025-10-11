package com.intellij.advancedExpressionFolding.view

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.usageView.UsageInfo
import com.intellij.usages.*
import com.intellij.usages.impl.UsageViewImpl

class FindUsageCustomView(project: Project, title: String) {
    private var usageView: UsageViewImpl

    private val usageViewPresentation = UsageViewPresentation().apply {
        tabText = title
        isOpenInNewTab = false
        isShowReadOnlyStatusAsRed = false
        isShowCancelButton = false
        isCodeUsages = false
        isUsageTypeFilteringAvailable = false
        isDetachedMode = false
        isMergeDupLinesAvailable = false
    }

    init {
        usageView = UsageViewManager.getInstance(project).showUsages(
            emptyArray(),
            emptyArray(),
            usageViewPresentation,
            null
        ) as UsageViewImpl
        FindUsageCustomViewTestHook.onUsageViewCreated(usageView)
    }

    fun addUsage(element: PsiElement, textRange: TextRange) {
        val usageInfo = UsageInfo(
            element.containingFile,
            textRange,
            false
        )
        ApplicationManager.getApplication().invokeLater {
            usageView.appendUsage(UsageInfo2UsageAdapter(usageInfo))
            FindUsageCustomViewTestHook.onUsageAppended(usageInfo, textRange)
        }
    }

    fun finish() {
        ApplicationManager.getApplication().invokeLater {
            usageView.searchFinished()
            FindUsageCustomViewTestHook.onSearchFinished()
        }
    }
}
