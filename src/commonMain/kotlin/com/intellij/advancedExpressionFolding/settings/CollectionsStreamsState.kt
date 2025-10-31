package com.intellij.advancedExpressionFolding.settings

interface ICollectionsStreamsState {
    val optional: Boolean
    val streamSpread: Boolean
}

data class CollectionsStreamsState(
    override val optional: Boolean = true,
    override val streamSpread: Boolean = true,
) : ICollectionsStreamsState
