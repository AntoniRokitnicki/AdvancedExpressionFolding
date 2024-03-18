package com.intellij.advancedExpressionFolding

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.NotNull
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
    }

    interface IState {
        val arithmeticExpressionsCollapse: Boolean
        val concatenationExpressionsCollapse: Boolean
        val slicingExpressionsCollapse: Boolean
        val comparingExpressionsCollapse: Boolean
        val comparingLocalDatesCollapse: Boolean
        val localDateLiteralCollapse: Boolean
        val localDateLiteralPostfixCollapse: Boolean
        val getExpressionsCollapse: Boolean
        val rangeExpressionsCollapse: Boolean
        val checkExpressionsCollapse: Boolean
        val castExpressionsCollapse: Boolean
        val varExpressionsCollapse: Boolean
        val getSetExpressionsCollapse: Boolean
        val controlFlowSingleStatementCodeBlockCollapse: Boolean
        val compactControlFlowSyntaxCollapse: Boolean
        val controlFlowMultiStatementCodeBlockCollapse: Boolean
        val semicolonsCollapse: Boolean
        val assertsCollapse: Boolean
        val optional: Boolean
        val streamSpread: Boolean
        val lombok: Boolean
        val fieldShift: Boolean
        val fieldShiftOld: Boolean
        val kotlinQuickReturn: Boolean
        val ifNullSafe: Boolean
        val logFolding: Boolean
        val destructuring: Boolean
    }

    data class State(
        override var arithmeticExpressionsCollapse: Boolean = false,
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
        override var compactControlFlowSyntaxCollapse: Boolean = true,
        override var controlFlowMultiStatementCodeBlockCollapse: Boolean = false,
        override var semicolonsCollapse: Boolean = true,
        override var assertsCollapse: Boolean = true,
        override var optional: Boolean = true,
        override var streamSpread: Boolean = true,
        override var lombok: Boolean = true,
        override var fieldShift: Boolean = true,
        override var fieldShiftOld: Boolean = false,
        override var kotlinQuickReturn: Boolean = true,
        override var ifNullSafe: Boolean = true,
        override var logFolding: Boolean = true,
        override var destructuring: Boolean = true,

        override var testDataFoldingDiff: Boolean = false,
        override var globalOn: Boolean = true,

        ) : IState, IConfig

    open class StateDelegate(private val state: State = getInstance().state) : IState by state
    open class ConfigDelegate(private val config: IConfig = getInstance().state) : IConfig by config

    private fun updateAllState(value: Boolean) {
        with(myState) {
            state::class.memberProperties
                .filterIsInstance<KMutableProperty<*>>()
                .filter { property -> !IConfig::class.memberProperties.map { it.name }.contains(property.name) }
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

    }
}
