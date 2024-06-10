package com.intellij.advancedExpressionFolding.extension.methodcall.dynamic

import com.intellij.util.io.readText
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object ConfigurationParser : IDynamicDataProvider {

    private val filePath: Path = Paths.get(System.getProperty("user.home"), "dynamic-ajf2.toml")

    override fun parse(): List<DynamicMethodCall> {
        if (!Files.exists(filePath)) {
            return emptyList()
        }
        val text = filePath.readText()
        return parseToml(text)
    }

    fun addOrUpdateMethod(methodName: String, newName: String) {
        val tomlFile = File(filePath.toUri())
        val tomlMap = if (tomlFile.exists()) {
            objectMapper.readValue(tomlFile, MutableMap::class.java) as MutableMap<String, Any>
        } else {
            mutableMapOf()
        }

        val methodDetails = mutableMapOf(
            "method" to methodName,
            "newName" to newName
        )

        tomlMap[methodName] = methodDetails

        objectMapper.writeValue(tomlFile, tomlMap)
    }

    fun remove(methodName: String) {
        val tomlFile = File(filePath.toUri())
        if (!tomlFile.exists()) {
            return
        }

        val tomlMap = objectMapper.readValue(tomlFile, MutableMap::class.java) as MutableMap<String, Any>
        tomlMap.remove(methodName)

        objectMapper.writeValue(tomlFile, tomlMap)
    }

}