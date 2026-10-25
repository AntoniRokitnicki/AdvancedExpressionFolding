package com.intellij.advancedExpressionFolding.discovery

data class PsiVectorMetadata(
    val nodeTypes: List<String>,
    val methodNameTokens: List<String>,
    val chainLength: Int,
    val parameterCount: Int,
    val parentContext: String?,
    val textHash: Int,
    val sampleText: String
)
