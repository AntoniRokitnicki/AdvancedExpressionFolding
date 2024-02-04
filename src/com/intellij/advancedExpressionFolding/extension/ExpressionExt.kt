package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings

interface ExpressionExt {

    fun isOptionalSetting(): Boolean = settings().optional

    fun isStreamSpreadSetting(): Boolean = settings().streamSpread

    fun isLombokSetting(): Boolean = settings().lombok

    fun isFieldShiftSetting(): Boolean = settings().fieldShift

    private fun settings() = AdvancedExpressionFoldingSettings.getInstance().state

}
