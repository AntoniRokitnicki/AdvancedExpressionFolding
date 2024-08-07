package com.intellij.advancedExpressionFolding

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.NotNull
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

@State(name = "AdvancedExpressionFoldingSettings", storages = [Storage("editor.codeinsight.xml")])
class AdvancedExpressionFoldingSettings : PersistentStateComponent<AdvancedExpressionFoldingSettings.State> {
    private var myState = State()
    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state.copy()
    }

    interface IConfig {
        val testDataFoldingDiff: Boolean
        var globalOn: Boolean
        val memoryImprovement: Boolean
    }

    interface ILessImportantState {
        val localDateLiteralCollapse: Boolean
        val localDateLiteralPostfixCollapse: Boolean
        val controlFlowSingleStatementCodeBlockCollapse: Boolean
        val controlFlowMultiStatementCodeBlockCollapse: Boolean
        val semicolonsCollapse: Boolean
        val fieldShiftOld: Boolean
        val finalRemoval: Boolean
        val finalEmoji: Boolean
        val lombokDirtyOff: Boolean
        val destructuring: Boolean

        val expressionFunc: Boolean
        val dynamic: Boolean
        val arithmeticExpressions: Boolean
        val emojify: Boolean
        val interfaceExtensionProperties: Boolean
        // NEW OPTION VAL

        var experimental: Boolean
    }

    interface IState : ILessImportantState {
        val concatenationExpressionsCollapse: Boolean
        val slicingExpressionsCollapse: Boolean
        val comparingExpressionsCollapse: Boolean
        val comparingLocalDatesCollapse: Boolean
        val getExpressionsCollapse: Boolean
        val rangeExpressionsCollapse: Boolean
        val checkExpressionsCollapse: Boolean
        val castExpressionsCollapse: Boolean
        val varExpressionsCollapse: Boolean
        val getSetExpressionsCollapse: Boolean
        val compactControlFlowSyntaxCollapse: Boolean
        val assertsCollapse: Boolean

        val optional: Boolean
        val streamSpread: Boolean
        val lombok: Boolean
        val fieldShift: Boolean
        val kotlinQuickReturn: Boolean
        val ifNullSafe: Boolean
        val logFolding: Boolean
        val println: Boolean
        val const: Boolean
        val nullable: Boolean

    }

    data class State(
        override var concatenationExpressionsCollapse: Boolean = true,
        override var slicingExpressionsCollapse: Boolean = true,
        override var comparingExpressionsCollapse: Boolean = true,
        override var comparingLocalDatesCollapse: Boolean = true,
        override var localDateLiteralCollapse: Boolean = false,
        override var localDateLiteralPostfixCollapse: Boolean = false,
        override var getExpressionsCollapse: Boolean = true,
        override var rangeExpressionsCollapse: Boolean = true,
        override var checkExpressionsCollapse: Boolean = true,
        override var castExpressionsCollapse: Boolean = true,
        override var varExpressionsCollapse: Boolean = true,
        override var getSetExpressionsCollapse: Boolean = true,
        override var controlFlowSingleStatementCodeBlockCollapse: Boolean = false,
        override var compactControlFlowSyntaxCollapse: Boolean = false,
        override var controlFlowMultiStatementCodeBlockCollapse: Boolean = false,
        override var semicolonsCollapse: Boolean = false,
        override var assertsCollapse: Boolean = true,
        override var optional: Boolean = true,
        override var streamSpread: Boolean = true,
        override var lombok: Boolean = true,
        override var fieldShift: Boolean = true,
        override var fieldShiftOld: Boolean = false,
        override var kotlinQuickReturn: Boolean = true,
        override var ifNullSafe: Boolean = true,
        override var logFolding: Boolean = true,
        override var destructuring: Boolean = false,
        override var println: Boolean = true,
        override var const: Boolean = true,
        override var nullable: Boolean = false,
        override var finalRemoval: Boolean = false,
        override var finalEmoji: Boolean = false,
        override var lombokDirtyOff: Boolean = false,
        override var expressionFunc: Boolean = true,
        override var dynamic: Boolean = true,
        override var arithmeticExpressions: Boolean = true,
        override var emojify: Boolean = false,
        override var interfaceExtensionProperties: Boolean = true,
        // NEW OPTION VAR

        override var memoryImprovement: Boolean = true,
        override var experimental: Boolean = false,

        override var testDataFoldingDiff: Boolean = false,
        override var globalOn: Boolean = true,

        ) : IState, IConfig

    open class StateDelegate(private val state: State = getInstance().state) : IState by state
    open class ConfigDelegate(private val config: IConfig = getInstance().state) : IConfig by config

    private fun updateAllState(value: Boolean) {
        with(myState) {
            allProperties()
                .forEach {
                    it.setter.call(this, value)
                }
        }
    }

    fun disableAll() = updateAllState(false)
    fun enableAll() = updateAllState(true)

    companion object {
        @JvmStatic
        @NotNull
        fun getInstance(): AdvancedExpressionFoldingSettings =
            ApplicationManager.getApplication().getService(AdvancedExpressionFoldingSettings::class.java)

        fun allProperties() = State::class.memberProperties
            .filterIsInstance<KMutableProperty<*>>()
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