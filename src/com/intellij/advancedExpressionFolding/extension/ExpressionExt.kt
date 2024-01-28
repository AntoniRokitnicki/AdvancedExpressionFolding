package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings

interface ExpressionExt {

    fun isOptionalSetting(): Boolean {
        return settings().isOptional
    }

    fun isStreamSpreadSetting(): Boolean {
        return settings().isStreamSpread
    }

    fun isLombokSetting(): Boolean {
        return settings().isLombok
    }

    private fun settings() = AdvancedExpressionFoldingSettings.getInstance().state

}
