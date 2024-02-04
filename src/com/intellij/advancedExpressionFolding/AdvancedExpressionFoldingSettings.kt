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

    data class State(
        var arithmeticExpressionsCollapse: Boolean = false,
        var concatenationExpressionsCollapse: Boolean = true,
        var slicingExpressionsCollapse: Boolean = true,
        var comparingExpressionsCollapse: Boolean = true,
        var comparingLocalDatesCollapse: Boolean = true,
        var localDateLiteralCollapse: Boolean = true,
        var localDateLiteralPostfixCollapse: Boolean = false,
        var getExpressionsCollapse: Boolean = true,
        var rangeExpressionsCollapse: Boolean = true,
        var checkExpressionsCollapse: Boolean = true,
        var castExpressionsCollapse: Boolean = true,
        var varExpressionsCollapse: Boolean = true,
        var getSetExpressionsCollapse: Boolean = true,
        var controlFlowSingleStatementCodeBlockCollapse: Boolean = false,
        var compactControlFlowSyntaxCollapse: Boolean = true,
        var controlFlowMultiStatementCodeBlockCollapse: Boolean = false,
        var semicolonsCollapse: Boolean = true,
        var assertsCollapse: Boolean = true,
        var optional: Boolean = true,
        var streamSpread: Boolean = true,
        var lombok: Boolean = true,
        var fieldShift: Boolean = true,

    )

    fun disableAll() {
        with(myState) {
            State::class.memberProperties
                .filterIsInstance<KMutableProperty<*>>()
                .forEach {
                    it.setter.call(this, false)
                }
        }
    }

    companion object {
        @JvmStatic
        @NotNull
        fun getInstance(): AdvancedExpressionFoldingSettings =
            ApplicationManager.getApplication().getService(AdvancedExpressionFoldingSettings::class.java)

    }
}
