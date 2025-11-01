package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.toml.TomlFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.intellij.openapi.diagnostic.Logger
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

interface IDynamicDataProvider {
    private val logger: Logger
        get() = Logger.getInstance(IDynamicDataProvider::class.java)
    val objectMapper: ObjectMapper
        get() = sharedObjectMapper

    companion object {
        private val sharedObjectMapper: ObjectMapper by lazy {
            ObjectMapper(TomlFactory()).apply {
                registerModule(KotlinModule.Builder().build())
            }
        }
    }

    fun parse(): List<DynamicMethodCall>

    fun parseToml(text: String): List<DynamicMethodCall> {
        val parsedEntries = runCatching {
            objectMapper.readValue<Map<String, DynamicMethodCallData>>(text)
        }.onFailure { throwable ->
            when (throwable) {
                is IOException -> logger.error("parseToml failed: unable to read TOML content", throwable)
                else -> logger.error("parseToml failed: unexpected data shape", throwable)
            }
        }.getOrDefault(emptyMap())

        return parsedEntries.mapNotNull { (entryName, data) ->
            val validated = data.validatedOrNull()
            if (validated == null) {
                logger.warn("Skipping dynamic method entry missing required keys: [$entryName] -> $data")
                null
            } else {
                DynamicMethodCall(validated)
            }
        }
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
