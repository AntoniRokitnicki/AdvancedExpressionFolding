package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.view.FindUsageCustomView
import com.intellij.openapi.project.Project
import com.intellij.usages.UsageViewManager
import com.intellij.usages.Usage
import com.intellij.usages.UsageTarget
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Test

class FindUsageCustomViewTest {
    @Test
    fun createsUsageViewOnInit() {
        val project = mockk<Project>()
        val manager = mockk<UsageViewManager>()
        val usageView = mockk<com.intellij.usages.impl.UsageViewImpl>(relaxed = true)
        every { manager.showUsages(any<Array<UsageTarget>>(), any<Array<Usage>>(), any(), any()) } returns usageView
        mockkStatic(UsageViewManager::class)
        every { UsageViewManager.getInstance(project) } returns manager
        FindUsageCustomView(project, "title")
        verify { UsageViewManager.getInstance(project) }
    }
}
