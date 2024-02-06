package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings

interface IExtension {

    fun isOptional(): Boolean = settings().optional

    fun isStreamSpread(): Boolean = settings().streamSpread

    fun isLombok(): Boolean = settings().lombok

    fun isFieldShift(): Boolean = settings().fieldShift

    private fun settings() = AdvancedExpressionFoldingSettings.getInstance().state

}
