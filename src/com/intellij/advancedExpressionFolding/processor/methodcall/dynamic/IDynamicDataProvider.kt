package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.toml.TomlFactory
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.openapi.diagnostic.Logger
import java.nio.file.Files
import java.nio.file.Path
import kotlin.text.Charsets

interface IDynamicDataProvider {
    private val logger: Logger
        get() = Logger.getInstance(IDynamicDataProvider::class.java)
    val objectMapper: ObjectMapper
        get() = sharedObjectMapper

    companion object {
        private val sharedObjectMapper: ObjectMapper by lazy {
            ObjectMapper(TomlFactory())
        }
        internal const val MAX_SUPPORTED_FILE_SIZE_BYTES: Long = 1024L * 1024L
        internal const val CONFIGURATION_FILE_NAME: String = "dynamic-ajf2.toml"
    }

    fun parse(): List<DynamicMethodCall>

    fun parseToml(text: String): List<DynamicMethodCall> {
        if (isOversized(text)) {
            return emptyList()
        }
        val listOfMaps = objectMapper.parseTomlValues(text)
        return listOfMaps?.mapNotNull { entry ->
            val method = entry["method"]
            val newName = entry["newName"]

            if (method.isNullOrBlank() || newName.isNullOrBlank()) {
                logger.warn("Skipping dynamic method entry missing required keys: $entry")
                null
            } else {
                DynamicMethodCall(DynamicMethodCallData(entry))
            }
        } ?: emptyList()
    }

    /**
     * Extension method to parse TOML text into a Collection of Maps.
     */
    fun ObjectMapper.parseTomlValues(text: String): Collection<Map<String, String>>? {
        if (isOversized(text)) {
            return null
        }
        return try {
            this.readValue(text, Map::class.java).values.asInstance<Collection<Map<String, String>>>()
        } catch (e: Exception) {
            logger.error("parseToml failed", e)
            null
        }
    }

    private fun isOversized(text: String): Boolean {
        val size = text.toByteArray(Charsets.UTF_8).size.toLong()
        if (size > MAX_SUPPORTED_FILE_SIZE_BYTES) {
            logger.warn(
                "Dynamic configuration input exceeds supported size of $MAX_SUPPORTED_FILE_SIZE_BYTES bytes (actual: $size bytes); skipping."
            )
            return true
        }
        return false
    }

    /**
     * Extension method to read a TOML file into a MutableMap.
     * Returns an empty map if the file doesn't exist.
     */
    fun ObjectMapper.readTomlFile(path: Path): MutableMap<String, Any> {
        return when {
            !Files.exists(path) -> mutableMapOf()
            else -> runCatching {
                @Suppress("UNCHECKED_CAST")
                this.readValue(path.toFile(), MutableMap::class.java) as MutableMap<String, Any>
            }.getOrDefault(mutableMapOf())
        }
    }

    /**
     * Extension method to write a Map to a TOML file or delete file if empty.
     */
    fun ObjectMapper.writeTomlFile(path: Path, data: Map<String, Any>) {
        val file = path.toFile()
        if (data.isEmpty()) {
            if (Files.exists(path)) {
                Files.delete(path)
            }
        } else {
            this.writeValue(file, data)
        }
    }
}
