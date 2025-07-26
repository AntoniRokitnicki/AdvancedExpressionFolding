package com.intellij.advancedExpressionFolding.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.NotNull
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

@State(name = "AdvancedExpressionFoldingSettings", storages = [Storage("editor.codeinsight.xml")])
class AdvancedExpressionFoldingSettings : PersistentStateComponent<AdvancedExpressionFoldingSettings.State> {
    private var myState = State()
    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state.copy()
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
        override var kotlinQuickReturn: Boolean = true,
        override var ifNullSafe: Boolean = true,
        override var logFolding: Boolean = true,
        override var destructuring: Boolean = false,
        override var println: Boolean = true,
        override var const: Boolean = true,
        override var nullable: Boolean = false,
        override var finalRemoval: Boolean = false,
        @Deprecated("too specific")
        override var finalEmoji: Boolean = false,
        override var lombokDirtyOff: Boolean = false,
        override var expressionFunc: Boolean = true,
        override var dynamic: Boolean = true,
        @Deprecated("to be removed")
        override var arithmeticExpressions: Boolean = false,
        @Deprecated("it generates too many foldings")
        override var emojify: Boolean = false,
        override var interfaceExtensionProperties: Boolean = true,
        override var patternMatchingInstanceof: Boolean = true,
        override var summaryParentOverride: Boolean = false,
        override var constructorReferenceNotation: Boolean = true,
        override var methodDefaultParameters: Boolean = true,
        override var lombokPatternOff: String? = null,
        override var overrideHide: Boolean = true,
        override var suppressWarningsHide: Boolean = true,
        override var pseudoAnnotations: Boolean = true,
        // NEW OPTION VAR

        override var memoryImprovement: Boolean = true,
        override var experimental: Boolean = false,

        override var globalOn: Boolean = true,

        ) : IState, IConfig

    private fun updateAllState(value: Boolean, vararg excludeProperties: KMutableProperty<Boolean>) {
        val excluded = excludeProperties.map { it.toString() }
        with(myState) {
            allProperties()
                .filter {
                    !excluded.contains(it.toString())
                }.forEach {
                    if (it.setter.parameters.getOrNull(1)?.type?.javaType?.typeName == "boolean") {
                        it.setter.call(this, value)
                    }
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
