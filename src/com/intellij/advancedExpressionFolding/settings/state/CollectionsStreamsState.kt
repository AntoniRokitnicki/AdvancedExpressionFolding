package com.intellij.advancedExpressionFolding.settings.state

interface ICollectionsStreamsState {
    var optional: Boolean
    var streamSpread: Boolean
}

data class CollectionsStreamsState(
    override var optional: Boolean = true,
    override var streamSpread: Boolean = true,
) : ICollectionsStreamsState
