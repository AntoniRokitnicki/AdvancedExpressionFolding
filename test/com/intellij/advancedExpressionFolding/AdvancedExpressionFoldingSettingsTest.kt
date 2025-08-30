package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class AdvancedExpressionFoldingSettingsTest {

    @Test
    fun `stateFlow emits on loadState`() = runBlocking {
        val settings = AdvancedExpressionFoldingSettings()
        val emitted = async { settings.stateFlow.drop(1).first() }
        val newState = settings.state.copy(optional = false)
        settings.loadState(newState)
        assertFalse(emitted.await().optional)
    }

    @Test
    fun `stateFlow emits on mutation`() = runBlocking {
        val settings = AdvancedExpressionFoldingSettings()
        val emitted = async { settings.stateFlow.drop(1).first() }
        settings.disableAll()
        assertFalse(emitted.await().concatenationExpressionsCollapse)
    }
}

