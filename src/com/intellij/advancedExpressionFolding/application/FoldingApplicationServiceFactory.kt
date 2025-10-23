package com.intellij.advancedExpressionFolding.application

import com.intellij.advancedExpressionFolding.adapter.cache.PsiFileFoldingCache
import com.intellij.advancedExpressionFolding.adapter.storage.ApplicationStorageRegistry
import com.intellij.advancedExpressionFolding.domain.DefaultFoldRegionsCollector
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IConfig

object FoldingApplicationServiceFactory {
    private val collector = DefaultFoldRegionsCollector()
    private val cache = PsiFileFoldingCache()

    fun create(config: IConfig = getInstance().state): FoldingApplicationService {
        val storage = ApplicationStorageRegistry.instance().current()
        return FoldingApplicationService(config, collector, cache, storage)
    }
}
