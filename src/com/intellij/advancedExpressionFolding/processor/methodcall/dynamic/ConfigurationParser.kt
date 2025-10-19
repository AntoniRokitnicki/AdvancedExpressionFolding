package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodName
import com.intellij.openapi.diagnostic.Logger
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.readText

object ConfigurationParser : IDynamicDataProvider {

    internal const val MAX_SUPPORTED_FILE_SIZE_BYTES: Long = IDynamicDataProvider.MAX_SUPPORTED_FILE_SIZE_BYTES

    private val logger: Logger
        get() = Logger.getInstance(ConfigurationParser::class.java)

    private val filePath: Path
        get() = Paths.get(System.getProperty("user.home"), IDynamicDataProvider.CONFIGURATION_FILE_NAME)

    override fun parse(): List<DynamicMethodCall> {
        if (!Files.exists(filePath)) {
            return emptyList()
        }

        val size = try {
            Files.size(filePath)
        } catch (ioException: IOException) {
            logger.warn("Failed to determine size of dynamic configuration file at $filePath", ioException)
            return emptyList()
        }

        if (size > MAX_SUPPORTED_FILE_SIZE_BYTES) {
            logger.warn(
                "Dynamic configuration file $filePath exceeds supported size of $MAX_SUPPORTED_FILE_SIZE_BYTES bytes (actual: $size bytes); skipping."
            )
            return emptyList()
        }

        val text = filePath.readText()
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
