package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodName
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.diagnostic.Logger
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import kotlin.io.path.readText

object ConfigurationParser : IDynamicDataProvider {

    private val logger = Logger.getInstance(ConfigurationParser::class.java)

    private val filePath: Path
        get() {
            val configDir = PathManager.getConfigDir()
            val pluginConfigDir = configDir.resolve("advanced-expression-folding")
            Files.createDirectories(pluginConfigDir)
            return pluginConfigDir.resolve("dynamic-ajf2.toml")
        }

    override fun parse(): List<DynamicMethodCall> {
        val path = filePath
        if (!Files.exists(path)) {
            return emptyList()
        }
        if (!Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
            logger.warn("Dynamic configuration path is not a regular file: $path")
            return emptyList()
        }
        val text = path.readText()
        return parseToml(text)
    }

    fun addOrUpdateMethod(methodName: MethodName, newName: MethodName) {
        val tomlMap = objectMapper.readTomlFile(filePath)

        val methodDetails = mutableMapOf(
            "method" to methodName,
            "newName" to newName
        )

        tomlMap[methodName] = methodDetails

        objectMapper.writeTomlFile(filePath, tomlMap)
    }

    fun remove(methodName: MethodName) {
        if (!Files.exists(filePath)) {
            return
        }

        val tomlMap = objectMapper.readTomlFile(filePath)
        tomlMap.remove(methodName)

        objectMapper.writeTomlFile(filePath, tomlMap)
    }
}
