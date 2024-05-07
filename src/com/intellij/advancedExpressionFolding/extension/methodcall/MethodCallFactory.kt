package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.extension.Consts
import com.intellij.advancedExpressionFolding.extension.methodcall.date.*
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
        mutableListOf(
            IsBeforeDateMethodCall(), IsAfterDateMethodCall(), CreateDateFactoryMethodCall(),
            AfterDateMethodCall(), BeforeDateMethodCall(), CheckNotNullMethodCall()
        ).filter {
            it.permission()
        }.associateBy {
            it.methodName()
        }

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
                it.methodName()
            } + Consts.SUPPORTED_METHODS

    private fun createClasslessMethods(): List<String> =
        methodCallMap.values
            .filter {
                it.classNames.isEmpty()
            }.mapNotNull {
                it.methodName()
            } + Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS

    fun findByMethodName(methodName: String?): AbstractMethodCall? = methodCallMap[methodName]
}
