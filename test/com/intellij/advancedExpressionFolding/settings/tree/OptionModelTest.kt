package com.intellij.advancedExpressionFolding.settings.tree

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OptionModelTest {

    private fun descriptor(id: String, value: Boolean, category: List<String>) =
        OptionDescriptor(id, id, defaultValue = value, currentValue = value, categoryPath = category)

    @Test
    fun `tri state updates with child changes`() {
        val descriptors = listOf(
            descriptor("a", true, listOf("Cat")),
            descriptor("b", false, listOf("Cat"))
        )
        val model = OptionModel(descriptors)
        val cat = model.findParent(listOf("Cat")) ?: error("category not found")

        assertEquals(TriState.PARTIAL, cat.triState)

        model.toggleLeaf("b")
        assertEquals(TriState.CHECKED, cat.triState)

        model.toggleParent(cat)
        assertEquals(TriState.UNCHECKED, cat.triState)
    }

    @Test
    fun `model syncs with state`() {
        val state = AdvancedExpressionFoldingSettings.State(
            concatenationExpressionsCollapse = true,
            slicingExpressionsCollapse = false,
        )
        val model = OptionModel.fromSettingsState(state)

        model.toggleLeaf("slicingExpressionsCollapse")
        model.applyToState(state)
        assertEquals(true, state.slicingExpressionsCollapse)

        state.slicingExpressionsCollapse = false
        model.loadFromState(state)
        assertEquals(false, model.getLeaf("slicingExpressionsCollapse")?.descriptor?.currentValue)
    }

    @Test
    fun `factory detects modifications`() {
        val state = AdvancedExpressionFoldingSettings.State(
            concatenationExpressionsCollapse = true,
        )
        val model = OptionModel.fromSettingsState(state)
        assertEquals(false, model.isModified(state))

        model.toggleLeaf("concatenationExpressionsCollapse")
        assertEquals(true, model.isModified(state))
    }
}

