package com.intellij.advancedExpressionFolding.settings.state

interface IEmojiVisibilityState {
    var finalEmoji: Boolean
    var finalRemoval: Boolean
    var emojify: Boolean
}

data class EmojiVisibilityState(
    override var finalEmoji: Boolean = false,
    override var finalRemoval: Boolean = false,
    override var emojify: Boolean = false,
) : IEmojiVisibilityState
