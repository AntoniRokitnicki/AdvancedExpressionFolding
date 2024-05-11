package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.extension.Consts
import com.intellij.advancedExpressionFolding.extension.methodcall.date.CreateDateFactoryMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsAfterDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsBeforeDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.nullable.CheckNotNullMethodCall

//TODO: move to extension-point
object MethodCallFactory {
    private lateinit var methodCallMap: Map<String?, AbstractMethodCall>

    lateinit var supportedClasses: Collection<String>
    lateinit var supportedMethods: Collection<String>

    lateinit var classlessMethods: Collection<String>


    fun clear() {
        methodCallMap = createMethodCalls()

        supportedClasses = createSupportedClasses()
        supportedMethods = createSupportedMethods()

        classlessMethods = createClasslessMethods()
    }

    init {
        clear()
    }

    private fun createMethodCalls(): Map<String?, AbstractMethodCall> =
        //TODO: move to extension list when stable
        mutableListOf(
            IsBeforeDateMethodCall(), IsAfterDateMethodCall(), CreateDateFactoryMethodCall(),
            CheckNotNullMethodCall()
        ).filter {
            it.permission()
        }.flatMap { methodCall ->
            methodCall.methodNames.map { methodName ->
                methodName to methodCall
            }
        }.toMap()

    private fun createSupportedClasses(): Collection<String> =
        methodCallMap.values
            .filter {
                it.classNames.isNotEmpty()
            }.map {
                it.classNames
            }.distinct().flatten() + Consts.SUPPORTED_CLASSES

    private fun createSupportedMethods(): List<String> =
        methodCallMap.values
            .mapNotNull {
                it.methodNames
            }.flatten() + Consts.SUPPORTED_METHODS

    private fun createClasslessMethods(): List<String> =
        methodCallMap.values
            .filter {
                it.classNames.isEmpty()
            }.mapNotNull {
                it.methodNames
            }.flatten() + Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS

    fun findByMethodName(methodName: String?): AbstractMethodCall? = methodCallMap[methodName]
}
