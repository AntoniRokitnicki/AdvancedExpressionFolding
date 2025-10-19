package com.intellij.advancedExpressionFolding.integration

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction

@JvmInline
value class ActionId(val value: String) {
    override fun toString(): String = value
}

fun ActionManager.getAction(actionId: ActionId): AnAction? = getAction(actionId.value)
