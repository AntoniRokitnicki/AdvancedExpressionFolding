package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.extension.methodcall.date.FactoryDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsAfterDateMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.date.IsBeforeDateMethodCall

//TODO: move to extension-point
object MethodCallFactory {
    val methods: List<AbstractMethodCall> by lazy {
        mutableListOf(IsBeforeDateMethodCall(), IsAfterDateMethodCall(), FactoryDateMethodCall()).filter {
            it.permission()
        }
    }

    //FIXME: dont reuse sets

    fun appendSupportedMethods(supportedMethods: MutableSet<String>): MutableSet<String> {
        methods.mapNotNull { it.methodName() }.forEach(supportedMethods::add)
        return supportedMethods
    }

    fun appendSupportedClasses(supportedClasses: MutableSet<String>): MutableSet<String> {
        methods.map { it.classNames }.flatten().forEach(supportedClasses::add)
        return supportedClasses
    }
}
