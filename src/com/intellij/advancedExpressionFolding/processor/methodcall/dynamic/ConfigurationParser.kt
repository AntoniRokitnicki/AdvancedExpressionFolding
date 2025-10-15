package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodName
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.diagnostic.Logger
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.readText

object ConfigurationParser : IDynamicDataProvider {

    private val filePath: Path
        get() = PathManager.getConfigDir().resolve(CONFIG_FILE_NAME)

    private val logger: Logger
        get() = Logger.getInstance(ConfigurationParser::class.java)

    override fun parse(): List<DynamicMethodCall> {
        if (!Files.exists(filePath)) {
            return emptyList()
        }
        val text = try {
            filePath.readText()
        } catch (exception: Exception) {
            notifyFailure("Failed to read dynamic folding configuration.", exception)
            return emptyList()
        }
        return parseToml(text)
    }

    fun addOrUpdateMethod(methodName: MethodName, newName: MethodName) {
        val tomlMap = objectMapper.readTomlFile(filePath)

        val methodDetails = mutableMapOf(
            "method" to methodName,
            "newName" to newName
        )

        tomlMap[methodName] = methodDetails

        try {
            filePath.parent?.let(Files::createDirectories)
            objectMapper.writeTomlFile(filePath, tomlMap)
        } catch (exception: Exception) {
            notifyFailure("Failed to persist dynamic folding configuration.", exception)
        }
    }

    fun remove(methodName: MethodName) {
        if (!Files.exists(filePath)) {
            return
        }

        val tomlMap = objectMapper.readTomlFile(filePath)
        tomlMap.remove(methodName)

        try {
            filePath.parent?.let(Files::createDirectories)
            objectMapper.writeTomlFile(filePath, tomlMap)
        } catch (exception: Exception) {
            notifyFailure("Failed to persist dynamic folding configuration.", exception)
        }
    }

    private fun notifyFailure(message: String, exception: Exception) {
        logger.warn(message, exception)
        NotificationGroupManager.getInstance()
            .getNotificationGroup(NOTIFICATION_GROUP_ID)
            .createNotification("$message ${exception.message ?: ""}".trim(), NotificationType.ERROR)
            .notify(null)
    }

    private const val CONFIG_FILE_NAME = "dynamic-ajf2.toml"
    private const val NOTIFICATION_GROUP_ID = "AdvancedExpressionFolding"
}
