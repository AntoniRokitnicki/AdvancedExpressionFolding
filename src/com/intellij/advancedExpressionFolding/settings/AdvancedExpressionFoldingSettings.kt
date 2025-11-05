package com.intellij.advancedExpressionFolding.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.NotNull
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

@com.intellij.openapi.components.State(name = "AdvancedExpressionFoldingSettings", storages = [Storage("editor.codeinsight.xml")])
class AdvancedExpressionFoldingSettings : PersistentStateComponent<State> {
    private var myState = State()
    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state.copy()
    }

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
    fun restoreDefaults() {
        myState = State()
    }

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

        private fun exclude(
            kClass: KClass<*>,
            property: KMutableProperty<*>
        ) = !kClass.memberProperties.map { it.name }.contains(property.name)
    }
}
