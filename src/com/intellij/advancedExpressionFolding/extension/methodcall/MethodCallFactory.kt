package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.extension.BaseExtension
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.ConfigurationParser
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.extension.on
import com.intellij.advancedExpressionFolding.extension.util.Consts

typealias MethodName = String
typealias ClassName = String

object MethodCallFactory : BaseExtension(){

    @Volatile
    private var dynamicProvider: IDynamicDataProvider? = ConfigurationParser

    @Volatile
    private var methodCallMap: Map<MethodName?, List<AbstractMethodCall>> = emptyMap()
    @Volatile
    lateinit var supportedClasses: Collection<ClassName>
    @Volatile
    lateinit var supportedMethods: Collection<MethodName>
    @Volatile
    lateinit var classlessMethods: Collection<MethodName>

    fun refreshMethodCallMappings(dynamicProvider: IDynamicDataProvider? = null) {
        synchronized(this) {
            if (dynamicProvider != null) {
                // for tests
                this.dynamicProvider = dynamicProvider
            }

            methodCallMap = createMethodCalls()

            supportedClasses = createSupportedClasses()
            supportedMethods = createSupportedMethods()

            classlessMethods = createClasslessMethods()
        }
    }

    fun initialize(dynamicProvider: IDynamicDataProvider? = ConfigurationParser): MethodCallFactory {
        if (dynamicProvider != null) {
            this.dynamicProvider = dynamicProvider
        }
        refreshMethodCallMappings()
        return this
    }

    private fun createMethodCalls(): Map<MethodName?, List<AbstractMethodCall>> =
        getAllMethodCalls().filter {
            it.permission()
        }.flatMap { methodCall ->
            methodCall.methodNames.map { methodName ->
                methodName to methodCall
            }
        }.groupBy(
            { it.first },
            { it.second }
        ).mapValues { entry ->
            entry.value.sortedWith(compareBy {
                it.classNames.isEmpty()
            })
        }

    private fun getAllMethodCalls(): List<AbstractMethodCall> = MethodCallManager.methodCalls + loadDynamicMethods().orEmpty()

    private fun loadDynamicMethods(): List<DynamicMethodCall>? = dynamic.on()?.let { dynamicProvider?.parse() }

    private fun createSupportedClasses(): Collection<ClassName> =
        methodCallMap.values
            .asSequence()
            .flatten()
            .filter {
                it.classNames.isNotEmpty()
            }.map {
                it.classNames
            }.distinct().flatten()
            .toList()

    private fun createSupportedMethods(): List<MethodName> =
        methodCallMap.values
            .flatten()
            .map {
                it.methodNames
            }.flatten()

    private fun createClasslessMethods(): List<MethodName> =
        methodCallMap.values
            .flatten()
            .filter {
                it.classNames.isEmpty()
            }.map {
                it.methodNames
            }.flatten() + Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS

    fun findByMethodName(methodName: MethodName?): List<AbstractMethodCall>? =
        methodCallMap[methodName]
}
