package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.extension.Consts
import com.intellij.advancedExpressionFolding.extension.methodcall.date.AbstractDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.CreateDateFactoryMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsAfterDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsBeforeDateMethodCall

//TODO: move to extension-point
object MethodCallFactory {
    private lateinit var methodCallMap: Map<String?, AbstractMethodCall>
    lateinit var supportedMethods: Collection<String>
    lateinit var supportedClasses: Collection<String>

    fun clear() {
        methodCallMap = createMethodCalls()
        supportedMethods = createSupportedMethods()
        supportedClasses = createSupportedClasses()
    }

    init {
        clear()
    }

    private fun createMethodCalls(): Map<String?, AbstractDateMethodCall> =
        mutableListOf(IsBeforeDateMethodCall(), IsAfterDateMethodCall(), CreateDateFactoryMethodCall()).filter {
            it.permission()
        }.associateBy {
            it.methodName()
        }

    private fun createSupportedMethods(): List<String> =
        methodCallMap.values.mapNotNull { it.methodName() } + Consts.SUPPORTED_METHODS

    private fun createSupportedClasses(): Collection<String> =
        methodCallMap.values.map { it.classNames }.distinct().flatten() + Consts.SUPPORTED_CLASSES

    fun findByMethodName(methodName: String?): AbstractMethodCall? = methodCallMap[methodName]
}
