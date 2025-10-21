package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties

class SettingsTest {

    private fun booleanProperties(): List<KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>> =
        AdvancedExpressionFoldingSettings.State::class.memberProperties
            .filterIsInstance<KMutableProperty1<AdvancedExpressionFoldingSettings.State, *>>()
            .filter { it.returnType.classifier == Boolean::class }
            .map { it as KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean> }
            .filter { it.name != "globalOn" && it.name != "memoryImprovement" }

    @Test
    fun disableAllTurnsOffEveryProperty() {
        val settings = AdvancedExpressionFoldingSettings()
        val state = settings.state
        val initialGlobalOn = state.globalOn
        val initialMemoryImprovement = state.memoryImprovement

        settings.enableAll()
        settings.disableAll()

        booleanProperties().forEach { property ->
            assertFalse(property.get(state), property.name)
        }

        assertEquals(initialGlobalOn, state.globalOn)
        assertEquals(initialMemoryImprovement, state.memoryImprovement)
    }

    @Test
    fun enableAllTurnsOnEveryProperty() {
        val settings = AdvancedExpressionFoldingSettings()
        val state = settings.state
        state.globalOn = false
        state.memoryImprovement = false
        settings.disableAll()

        settings.enableAll()

        booleanProperties().forEach { property ->
            assertTrue(property.get(state), property.name)
        }

        assertFalse(state.globalOn)
        assertFalse(state.memoryImprovement)
    }

    @Test
    fun enableAllWithExclusionsLeavesSpecifiedPropertiesUnchanged() {
        val settings = AdvancedExpressionFoldingSettings()
        val state = settings.state
        settings.disableAll()

        settings.enableAll(state::assertsCollapse)

        booleanProperties().forEach { property ->
            val value = property.get(state)
            if (property.name == "assertsCollapse") {
                assertFalse(value, "${property.name} should remain disabled")
            } else {
                assertTrue(value, "${property.name} should be enabled")
            }
        }
    }
}

