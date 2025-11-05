package com.intellij.advancedExpressionFolding

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Schedules asynchronous clearing work for a batch of items using the provided [CoroutineScope].
 * The implementation is intentionally tiny so it can be stress-tested with litmus tests.
 */
class EditorClearScheduler<T>(
    private val scopeProvider: () -> CoroutineScope,
) {
    fun schedule(
        items: List<T>,
        action: (T) -> Unit,
    ): Job? {
        if (items.isEmpty()) {
            return null
        }
        val scope = scopeProvider()
        return scope.launch {
            items.forEach(action)
        }
    }
}
