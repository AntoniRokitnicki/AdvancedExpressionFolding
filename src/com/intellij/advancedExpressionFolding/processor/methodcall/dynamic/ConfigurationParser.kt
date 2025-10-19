package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodName
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.diagnostic.Logger
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.readText

object ConfigurationParser : IDynamicDataProvider {

    private val logger = Logger.getInstance(ConfigurationParser::class.java)

    private val configurationDirectory: Path
        get() {
            val directory = PathManager.getConfigDir().resolve("advanced-expression-folding")
            DynamicFileSecurity.ensureDirectorySecure(directory)
            return directory
        }

    private val filePath: Path
        get() = configurationDirectory.resolve("dynamic-ajf2.toml")

    override fun parse(): List<DynamicMethodCall> {
        val path = filePath
        if (!Files.exists(path)) {
            return emptyList()
        }
        if (!DynamicFileSecurity.isOwnedByCurrentUser(path)) {
            logger.warn("Ignoring dynamic configuration at $path because it is not owned by the current user")
            return emptyList()
        }
        if (DynamicFileSecurity.hasWorldAccessiblePermissions(path)) {
            logger.warn("Ignoring dynamic configuration at $path because it has world-accessible permissions")
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
