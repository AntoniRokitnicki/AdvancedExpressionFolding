package com.intellij.advancedExpressionFolding.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.NotNull
import kotlin.properties.Delegates
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty
import kotlin.reflect.full.memberProperties

@State(name = "AdvancedExpressionFoldingSettings", storages = [Storage("editor.codeinsight.xml")])
class AdvancedExpressionFoldingSettings : PersistentStateComponent<AdvancedExpressionFoldingSettings.State> {
    private var myState = State()
    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state.copy()
    }

    class State : IState, IConfig {
        internal val delegatedProperties = mutableMapOf<String, BooleanDelegate>()
        internal val changedProperties = mutableSetOf<String>()

        private fun boolean(default: Boolean): PropertyDelegateProvider<State, BooleanDelegate> =
            PropertyDelegateProvider { thisRef, property ->
                BooleanDelegate(property.name, default, thisRef.changedProperties)
                    .also { thisRef.delegatedProperties[property.name] = it }
            }

        override var concatenationExpressionsCollapse: Boolean by boolean(true)
        override var slicingExpressionsCollapse: Boolean by boolean(true)
        override var comparingExpressionsCollapse: Boolean by boolean(true)
        override var comparingLocalDatesCollapse: Boolean by boolean(true)
        override var localDateLiteralCollapse: Boolean by boolean(false)
        override var localDateLiteralPostfixCollapse: Boolean by boolean(false)
        override var getExpressionsCollapse: Boolean by boolean(true)
        override var rangeExpressionsCollapse: Boolean by boolean(true)
        override var checkExpressionsCollapse: Boolean by boolean(true)
        override var castExpressionsCollapse: Boolean by boolean(true)
        override var varExpressionsCollapse: Boolean by boolean(true)
        override var getSetExpressionsCollapse: Boolean by boolean(true)
        override var controlFlowSingleStatementCodeBlockCollapse: Boolean by boolean(false)
        override var compactControlFlowSyntaxCollapse: Boolean by boolean(false)
        override var controlFlowMultiStatementCodeBlockCollapse: Boolean by boolean(false)
        override var semicolonsCollapse: Boolean by boolean(false)
        override var assertsCollapse: Boolean by boolean(true)
        override var optional: Boolean by boolean(true)
        override var streamSpread: Boolean by boolean(true)
        override var lombok: Boolean by boolean(true)
        override var fieldShift: Boolean by boolean(true)
        override var kotlinQuickReturn: Boolean by boolean(true)
        override var ifNullSafe: Boolean by boolean(true)
        override var logFolding: Boolean by boolean(true)
        override var destructuring: Boolean by boolean(false)
        override var println: Boolean by boolean(true)
        override var const: Boolean by boolean(true)
        override var nullable: Boolean by boolean(false)
        override var finalRemoval: Boolean by boolean(false)
        @Deprecated("too specific")
        override var finalEmoji: Boolean by boolean(false)
        override var lombokDirtyOff: Boolean by boolean(false)
        override var expressionFunc: Boolean by boolean(true)
        override var dynamic: Boolean by boolean(true)
        @Deprecated("to be removed")
        override var arithmeticExpressions: Boolean by boolean(false)
        @Deprecated("it generates too many foldings")
        override var emojify: Boolean by boolean(false)
        override var interfaceExtensionProperties: Boolean by boolean(true)
        override var patternMatchingInstanceof: Boolean by boolean(true)
        override var summaryParentOverride: Boolean by boolean(false)
        override var constructorReferenceNotation: Boolean by boolean(true)
        override var methodDefaultParameters: Boolean by boolean(true)
        override var lombokPatternOff: String? = null
        override var overrideHide: Boolean by boolean(true)
        override var suppressWarningsHide: Boolean by boolean(true)
        override var pseudoAnnotations: Boolean by boolean(true)
        // NEW OPTION VAR

        override var memoryImprovement: Boolean by boolean(true)
        override var experimental: Boolean by boolean(false)

        override var globalOn: Boolean by boolean(true)

        fun copy(): State {
            val copy = State()
            copy.lombokPatternOff = this.lombokPatternOff
            delegatedProperties.forEach { (name, delegate) ->
                copy.delegatedProperties[name]?.value = delegate.value
            }
            return copy
        }

        internal class BooleanDelegate(
            private val name: String,
            initial: Boolean,
            private val changed: MutableSet<String>
        ) : ReadWriteProperty<State, Boolean> {
            var value: Boolean by Delegates.observable(initial) { _, old, new ->
                if (old != new) changed += name
            }

            override fun getValue(thisRef: State, property: KProperty<*>) = value
            override fun setValue(thisRef: State, property: KProperty<*>, value: Boolean) {
                this.value = value
            }
        }
    }

    private fun updateAllState(value: Boolean, vararg excludeProperties: KMutableProperty0<Boolean>) {
        val excluded = (excludeProperties.map { it.name } + IConfig::class.memberProperties.map { it.name }).toSet()
        myState.delegatedProperties.forEach { (name, delegate) ->
            if (name !in excluded) {
                delegate.value = value
            }
        }
    }

    fun disableAll() = updateAllState(false)
    fun enableAll(vararg excludeProperties: KMutableProperty0<Boolean>) = updateAllState(true, *excludeProperties)

    // used in integrationStubs
    fun enableEverything() = updateAllState(true, state::emojify, state::finalEmoji)

    companion object {
        @JvmStatic
        @NotNull
        fun getInstance(): AdvancedExpressionFoldingSettings =
            ApplicationManager.getApplication().getService(AdvancedExpressionFoldingSettings::class.java)

        fun allProperties() = State::class.memberProperties
            .filterIsInstance<KMutableProperty<Boolean>>()
            .filter { property ->
                exclude(IConfig::class, property)
            }

        fun allMainProperties() = allProperties().filter { property ->
            exclude(ILessImportantState::class, property)
        }

        private fun exclude(
            kClass: KClass<*>,
            property: KMutableProperty<*>
        ) = !kClass.memberProperties.map { it.name }.contains(property.name)
    }
}

