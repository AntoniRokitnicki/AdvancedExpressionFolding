package com.intellij.advancedExpressionFolding.settings

interface IEmojiVisibilityState {
    val finalEmoji: Boolean
    val finalRemoval: Boolean
    val emojify: Boolean
}

data class EmojiVisibilityState(
    override val finalEmoji: Boolean = false,
    override val finalRemoval: Boolean = false,
    override val emojify: Boolean = false,
) : IEmojiVisibilityState
