package com.intellij.advancedExpressionFolding.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.NotNull
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

@State(name = "AdvancedExpressionFoldingSettings", storages = [Storage("editor.codeinsight.xml")])
class AdvancedExpressionFoldingSettings : PersistentStateComponent<AdvancedExpressionFoldingSettings.State> {
    private var myState = State()
    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state.copy()
    }

    data class State(
        @FoldingFlag(main = true)
        override var concatenationExpressionsCollapse: Boolean = true,
        @FoldingFlag(main = true)
        override var slicingExpressionsCollapse: Boolean = true,
        @FoldingFlag(main = true)
        override var comparingExpressionsCollapse: Boolean = true,
        @FoldingFlag(main = true)
        override var comparingLocalDatesCollapse: Boolean = true,
        @FoldingFlag
        override var localDateLiteralCollapse: Boolean = false,
        @FoldingFlag
        override var localDateLiteralPostfixCollapse: Boolean = false,
        @FoldingFlag(main = true)
        override var getExpressionsCollapse: Boolean = true,
        @FoldingFlag(main = true)
        override var rangeExpressionsCollapse: Boolean = true,
        @FoldingFlag(main = true)
        override var checkExpressionsCollapse: Boolean = true,
        @FoldingFlag(main = true)
        override var castExpressionsCollapse: Boolean = true,
        @FoldingFlag(main = true)
        override var varExpressionsCollapse: Boolean = true,
        @FoldingFlag(main = true)
        override var getSetExpressionsCollapse: Boolean = true,
        @FoldingFlag
        override var controlFlowSingleStatementCodeBlockCollapse: Boolean = false,
        @FoldingFlag(main = true)
        override var compactControlFlowSyntaxCollapse: Boolean = false,
        @FoldingFlag
        override var controlFlowMultiStatementCodeBlockCollapse: Boolean = false,
        @FoldingFlag
        override var semicolonsCollapse: Boolean = false,
        @FoldingFlag(main = true)
        override var assertsCollapse: Boolean = true,
        @FoldingFlag(main = true)
        override var optional: Boolean = true,
        @FoldingFlag(main = true)
        override var streamSpread: Boolean = true,
        @FoldingFlag(main = true)
        override var lombok: Boolean = true,
        @FoldingFlag(main = true)
        override var fieldShift: Boolean = true,
        @FoldingFlag(main = true)
        override var kotlinQuickReturn: Boolean = true,
        @FoldingFlag(main = true)
        override var ifNullSafe: Boolean = true,
        @FoldingFlag(main = true)
        override var logFolding: Boolean = true,
        @FoldingFlag
        override var destructuring: Boolean = false,
        @FoldingFlag(main = true)
        override var println: Boolean = true,
        @FoldingFlag(main = true)
        override var const: Boolean = true,
        @FoldingFlag(main = true)
        override var nullable: Boolean = false,
        @FoldingFlag
        override var finalRemoval: Boolean = false,
        @FoldingFlag
        @Deprecated("too specific")
        override var finalEmoji: Boolean = false,
        @FoldingFlag
        override var lombokDirtyOff: Boolean = false,
        @FoldingFlag
        override var expressionFunc: Boolean = true,
        @FoldingFlag
        override var dynamic: Boolean = true,
        @FoldingFlag
        @Deprecated("to be removed")
        override var arithmeticExpressions: Boolean = false,
        @FoldingFlag
        @Deprecated("it generates too many foldings")
        override var emojify: Boolean = false,
        @FoldingFlag
        override var interfaceExtensionProperties: Boolean = true,
        @FoldingFlag
        override var patternMatchingInstanceof: Boolean = true,
        @FoldingFlag
        override var summaryParentOverride: Boolean = false,
        @FoldingFlag
        override var constructorReferenceNotation: Boolean = true,
        @FoldingFlag
        override var methodDefaultParameters: Boolean = true,
        override var lombokPatternOff: String? = null,
        @FoldingFlag
        override var overrideHide: Boolean = true,
        @FoldingFlag
        override var suppressWarningsHide: Boolean = true,
        @FoldingFlag
        override var pseudoAnnotations: Boolean = true,
        // NEW OPTION VAR

        override var memoryImprovement: Boolean = true,
        override var experimental: Boolean = false,

        override var globalOn: Boolean = true,

        ) : IState, IConfig

    private fun updateAllState(value: Boolean, vararg excludeProperties: KMutableProperty1<State, Boolean>) {
        val excluded = excludeProperties.toSet()
        with(myState) {
            configurableFlags()
                .filterNot { excluded.contains(it) }
                .forEach { it.set(this, value) }
        }
    }

    fun disableAll() = updateAllState(false)
    fun enableAll(vararg excludeProperties: KMutableProperty1<State, Boolean>) = updateAllState(true, *excludeProperties)

    // used in integrationStubs
    fun enableEverything() = updateAllState(true, State::emojify, State::finalEmoji)

    companion object {
        @JvmStatic
        @NotNull
        fun getInstance(): AdvancedExpressionFoldingSettings =
            ApplicationManager.getApplication().getService(AdvancedExpressionFoldingSettings::class.java)

        fun allMainProperties(): List<KMutableProperty1<State, Boolean>> =
            getInstance().state.mainFlags()
    }
}

private fun AdvancedExpressionFoldingSettings.State.configurableFlags(): List<KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>> =
    this::class.memberProperties
        .mapNotNull { prop ->
            prop.findAnnotation<FoldingFlag>()?.let {
                prop as? KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>
            }
        }

private fun AdvancedExpressionFoldingSettings.State.mainFlags(): List<KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>> =
    this::class.memberProperties
        .mapNotNull { prop ->
            prop.findAnnotation<FoldingFlag>()?.takeIf { it.main }?.let {
                prop as? KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>
            }
        }

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
private annotation class FoldingFlag(val main: Boolean = false)
