package com.intellij.advancedExpressionFolding.settings

import java.lang.reflect.Proxy

open class StateDelegate(
    private val defaultState: AdvancedExpressionFoldingSettings.State = AdvancedExpressionFoldingSettings.Companion.getInstance().state
) : IState by stateProxy(defaultState) {

    companion object {
        private val overrideState = ThreadLocal<AdvancedExpressionFoldingSettings.State?>()

        fun <T> withState(state: AdvancedExpressionFoldingSettings.State, block: () -> T): T {
            val previous = overrideState.get()
            overrideState.set(state)
            return try {
                block()
            } finally {
                if (previous == null) {
                    overrideState.remove()
                } else {
                    overrideState.set(previous)
                }
            }
        }

        private fun currentState(defaultState: AdvancedExpressionFoldingSettings.State): AdvancedExpressionFoldingSettings.State {
            return overrideState.get() ?: defaultState
        }

        private fun stateProxy(defaultState: AdvancedExpressionFoldingSettings.State): IState {
            val provider = { currentState(defaultState) }
            return Proxy.newProxyInstance(
                IState::class.java.classLoader,
                arrayOf(IState::class.java)
            ) { _, method, args ->
                method.invoke(provider(), *(args ?: emptyArray()))
            } as IState
        }
    }
}
