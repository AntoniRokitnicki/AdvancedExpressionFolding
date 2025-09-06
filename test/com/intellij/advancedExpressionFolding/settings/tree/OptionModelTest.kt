package com.intellij.advancedExpressionFolding.settings.tree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OptionModelTest {

    private fun createModel(): OptionModel {
        val descriptors = listOf(
            OptionDescriptor("a", "A", "", false, false, listOf("Cat")),
            OptionDescriptor("b", "B", "", false, false, listOf("Cat"))
        )
        return OptionModel(descriptors)
    }

    @Test
    fun `toggling parent updates children`() {
        val model = createModel()
        val path = listOf("Cat")

        model.toggleParent(path)
        val parent = model.findParent(path)!!
        val statesAfterCheck = parent.children.map { (it as OptionTreeNode.LeafNode).selected }
        assertEquals(listOf(true, true), statesAfterCheck)
        assertEquals(TriState.CHECKED, parent.triState)

        model.toggleParent(path)
        val statesAfterUncheck = parent.children.map { (it as OptionTreeNode.LeafNode).selected }
        assertEquals(listOf(false, false), statesAfterUncheck)
        assertEquals(TriState.UNCHECKED, parent.triState)
    }

    @Test
    fun `toggling leaf propagates to parent`() {
        val model = createModel()
        val path = listOf("Cat")
        val parent = model.findParent(path)!!
        val leafA = parent.children[0] as OptionTreeNode.LeafNode
        val leafB = parent.children[1] as OptionTreeNode.LeafNode

        leafA.toggle()
        assertEquals(TriState.PARTIAL, parent.triState)

        leafB.toggle()
        assertEquals(TriState.CHECKED, parent.triState)

        leafA.toggle()
        assertEquals(TriState.PARTIAL, parent.triState)
    }
}
