package com.intellij.advancedExpressionFolding.diff

@JvmRecord
data class Range(
    val start: Int,
    val end: Int,
    val size: Int,
)

@JvmRecord
data class FoldingDescriptorEx(
    val id: Int,
    val text: String,
    val placeholder: String?,
    val range: Range,
    val group: String,
)

@JvmRecord
data class FoldingDescriptorExWrapper(
    val size: Int,
    val list: List<FoldingDescriptorEx>,
)
