package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.extension.Consts
import com.intellij.advancedExpressionFolding.extension.methodcall.date.FactoryDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsAfterDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsBeforeDateMethodCall

//TODO: move to extension-point
object MethodCallFactory {
    var methodCalls: MutableList<AbstractMethodCall> = createMethodCalls()
    var supportedMethods: Collection<String> = createSupportedMethods()
    var supportedClasses: Collection<String> = createSupportedClasses()

    fun clear() {
        methodCalls.clear()
        init()
    }

    fun init() {
        if (methodCalls.isEmpty()) {
            methodCalls = createMethodCalls()
            supportedMethods = createSupportedMethods()
            supportedClasses = createSupportedClasses()
        }
    }

    private fun createMethodCalls(): MutableList<AbstractMethodCall> =
        mutableListOf(IsBeforeDateMethodCall(), IsAfterDateMethodCall(), FactoryDateMethodCall()).filter {
            it.permission()
        }.toMutableList()

    private fun createSupportedMethods(): List<String> =
        methodCalls.mapNotNull { it.methodName() } + Consts.SUPPORTED_METHODS

    private fun createSupportedClasses(): Collection<String> =
        methodCalls.map { it.classNames }.flatten() + Consts.SUPPORTED_CLASSES
}
