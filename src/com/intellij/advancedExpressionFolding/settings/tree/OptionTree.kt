package com.intellij.advancedExpressionFolding.settings.tree

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties

/**
 * Descriptor of a single configurable option.
 */
data class OptionDescriptor(
    val id: String,
    var label: String,
    var description: String? = null,
    val defaultValue: Boolean,
    var currentValue: Boolean,
    val categoryPath: List<String> = emptyList(),
)

/** Tri-state used for parent nodes in the option tree. */
enum class TriState {
    CHECKED,
    UNCHECKED,
    PARTIAL
}

/**
 * Node of options tree. Parent nodes group children, leaf nodes correspond to
 * actual options stored in [OptionDescriptor].
 */
sealed class OptionTreeNode {
    abstract val triState: TriState

    data class Leaf(val descriptor: OptionDescriptor) : OptionTreeNode() {
        override val triState: TriState
            get() = if (descriptor.currentValue) TriState.CHECKED else TriState.UNCHECKED
    }

    data class Parent(
        val name: String,
        val children: MutableList<OptionTreeNode> = mutableListOf(),
    ) : OptionTreeNode() {
        override val triState: TriState
            get() {
                if (children.isEmpty()) return TriState.UNCHECKED
                val states = children.map { it.triState }
                return when {
                    states.all { it == TriState.CHECKED } -> TriState.CHECKED
                    states.all { it == TriState.UNCHECKED } -> TriState.UNCHECKED
                    else -> TriState.PARTIAL
                }
            }
    }
}

/**
 * Holds tree of options and provides operations for mutating and reading the
 * state of nodes.
 */
class OptionModel(descriptors: List<OptionDescriptor>) {
    val root: OptionTreeNode.Parent = OptionTreeNode.Parent("root")
    private val leavesById = mutableMapOf<String, OptionTreeNode.Leaf>()

    init {
        descriptors.forEach { addDescriptor(it) }
    }

    private fun addDescriptor(descriptor: OptionDescriptor) {
        var parent = root
        descriptor.categoryPath.forEach { category ->
            val existing = parent.children.filterIsInstance<OptionTreeNode.Parent>()
                .firstOrNull { it.name == category }
            parent = existing ?: OptionTreeNode.Parent(category).also { parent.children.add(it) }
        }
        val leaf = OptionTreeNode.Leaf(descriptor)
        parent.children.add(leaf)
        leavesById[descriptor.id] = leaf
    }

    fun getLeaf(id: String): OptionTreeNode.Leaf? = leavesById[id]

    val leaves: Collection<OptionTreeNode.Leaf>
        get() = leavesById.values

    fun findParent(path: List<String>): OptionTreeNode.Parent? {
        var current = root
        for (name in path) {
            val next = current.children.filterIsInstance<OptionTreeNode.Parent>()
                .firstOrNull { it.name == name } ?: return null
            current = next
        }
        return current
    }

    fun toggleLeaf(id: String) {
        val leaf = leavesById[id] ?: return
        leaf.descriptor.currentValue = !leaf.descriptor.currentValue
    }

    fun toggleParent(parent: OptionTreeNode.Parent) {
        val newValue = parent.triState == TriState.UNCHECKED
        setSubtree(parent, newValue)
    }

    private fun setSubtree(node: OptionTreeNode, value: Boolean) {
        when (node) {
            is OptionTreeNode.Leaf -> node.descriptor.currentValue = value
            is OptionTreeNode.Parent -> node.children.forEach { setSubtree(it, value) }
        }
    }

    fun applyToState(state: AdvancedExpressionFoldingSettings.State) {
        val properties = AdvancedExpressionFoldingSettings.State::class.memberProperties
            .filterIsInstance<KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>>()
            .associateBy { it.name }
        for ((id, leaf) in leavesById) {
            properties[id]?.set(state, leaf.descriptor.currentValue)
        }
    }

    fun loadFromState(state: AdvancedExpressionFoldingSettings.State) {
        val properties = AdvancedExpressionFoldingSettings.State::class.memberProperties
            .filterIsInstance<KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>>()
            .associateBy { it.name }
        for ((id, leaf) in leavesById) {
            properties[id]?.get(state)?.let { leaf.descriptor.currentValue = it }
        }
    }

    fun isModified(state: AdvancedExpressionFoldingSettings.State): Boolean {
        val properties = AdvancedExpressionFoldingSettings.State::class.memberProperties
            .filterIsInstance<KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>>()
            .associateBy { it.name }
        return leavesById.any { (id, leaf) ->
            properties[id]?.get(state) != leaf.descriptor.currentValue
        }
    }

    companion object {
        fun fromSettingsState(state: AdvancedExpressionFoldingSettings.State): OptionModel {
            val defaultState = AdvancedExpressionFoldingSettings.State()
            val descriptors = AdvancedExpressionFoldingSettings.State::class.memberProperties
                .filterIsInstance<KMutableProperty1<AdvancedExpressionFoldingSettings.State, *>>()
                .filter { it.returnType.classifier == Boolean::class }
                .map { property ->
                    @Suppress("UNCHECKED_CAST")
                    val booleanProperty = property as KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>
                    OptionDescriptor(
                        id = booleanProperty.name,
                        label = booleanProperty.name,
                        defaultValue = booleanProperty.get(defaultState),
                        currentValue = booleanProperty.get(state),
                    )
                }
            return OptionModel(descriptors)
        }
    }
}

