package com.intellij.advancedExpressionFolding.settings

interface ILombokState {
    var lombok: Boolean
    var lombokDirtyOff: Boolean
    var lombokPatternOff: String?
    var interfaceExtensionProperties: Boolean
    val pseudoAnnotations: Boolean
}

data class LombokState(
    override var lombok: Boolean = true,
    override var lombokDirtyOff: Boolean = false,
    override var lombokPatternOff: String? = null,
    override var interfaceExtensionProperties: Boolean = true,
    override val pseudoAnnotations: Boolean = true,
) : ILombokState
