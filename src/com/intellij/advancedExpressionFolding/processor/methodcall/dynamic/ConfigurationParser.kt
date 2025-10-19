package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodName
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.readText

object ConfigurationParser : IDynamicDataProvider {

    private val filePath: Path
        get() = Paths.get(System.getProperty("user.home"), "dynamic-ajf2.toml")

    override fun parse(): List<DynamicMethodCall> {
        if (!Files.exists(filePath)) {
            return emptyList()
        }
        val text = filePath.readText()
        return parseToml(text)
    }

    fun addOrUpdateMethod(methodName: MethodName, newName: MethodName) {
        val tomlMap = objectMapper.readTomlFile(filePath)

        val methodDetails = mutableMapOf(
            "method" to methodName.value,
            "newName" to newName.value
        )

        tomlMap[methodName.value] = methodDetails

        objectMapper.writeTomlFile(filePath, tomlMap)
    }

    fun remove(methodName: MethodName) {
        if (!Files.exists(filePath)) {
            return
        }

        val tomlMap = objectMapper.readTomlFile(filePath)
        tomlMap.remove(methodName.value)

        objectMapper.writeTomlFile(filePath, tomlMap)
    }
}
