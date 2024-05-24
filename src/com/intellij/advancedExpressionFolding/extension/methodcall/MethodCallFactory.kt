package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.extension.Consts
import com.intellij.advancedExpressionFolding.extension.methodcall.collection.CollectionGetMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.collection.MapPutMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.collection.OptionalGetMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.CreateDateFactoryMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsAfterDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsBeforeDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.nullable.CheckNotNullMethodCall

typealias MethodName = String
typealias ClassName = String

object MethodCallFactory {
    private lateinit var methodCallMap: Map<MethodName?, List<AbstractMethodCall>>

    lateinit var supportedClasses: Collection<ClassName>
    lateinit var supportedMethods: Collection<MethodName>

    lateinit var classlessMethods: Collection<MethodName>


    fun refreshMethodCallMappings() {
        methodCallMap = createMethodCalls()

        supportedClasses = createSupportedClasses()
        supportedMethods = createSupportedMethods()

        classlessMethods = createClasslessMethods()
    }

    init {
        refreshMethodCallMappings()
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

    //TODO: move to extension list when API is stable
    private fun getAllMethodCalls() = listOf(
        IsBeforeDateMethodCall(), IsAfterDateMethodCall(), CreateDateFactoryMethodCall(),
        CheckNotNullMethodCall(), MapPutMethodCall(), CollectionGetMethodCall(),
        OptionalGetMethodCall(),
    )

    private fun createSupportedClasses(): Collection<ClassName> =
        methodCallMap.values
            .asSequence()
            .flatten()
            .filter {
                it.classNames.isNotEmpty()
            }.map {
                it.classNames
            }.distinct().flatten()
            .toList() + Consts.SUPPORTED_CLASSES

    private fun createSupportedMethods(): List<MethodName> =
        methodCallMap.values
            .flatten()
            .mapNotNull {
                it.methodNames
            }.flatten() + Consts.SUPPORTED_METHODS

    private fun createClasslessMethods(): List<MethodName> =
        methodCallMap.values
            .flatten()
            .filter {
                it.classNames.isEmpty()
            }.mapNotNull {
                it.methodNames
            }.flatten() + Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS

    fun findByMethodName(methodName: MethodName?): List<AbstractMethodCall>? =
        methodCallMap[methodName]
}
