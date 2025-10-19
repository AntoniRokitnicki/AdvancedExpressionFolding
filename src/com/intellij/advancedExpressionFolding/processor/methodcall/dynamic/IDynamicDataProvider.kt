package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.toml.TomlFactory
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.openapi.diagnostic.Logger
import java.nio.channels.Channels
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.nio.file.StandardOpenOption.CREATE
import java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
import java.nio.file.StandardOpenOption.WRITE

interface IDynamicDataProvider {
    private val logger: Logger
        get() = Logger.getInstance(IDynamicDataProvider::class.java)
    val objectMapper: ObjectMapper
        get() = sharedObjectMapper

    companion object {
        private val sharedObjectMapper: ObjectMapper by lazy {
            ObjectMapper(TomlFactory())
        }
    }

    fun parse(): List<DynamicMethodCall>

    fun parseToml(text: String): List<DynamicMethodCall> {
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
        return try {
            this.readValue(text, Map::class.java).values.asInstance<Collection<Map<String, String>>>()
        } catch (e: Exception) {
            logger.error("parseToml failed", e)
            null
        }
    }

    /**
     * Extension method to read a TOML file into a MutableMap.
     * Returns an empty map if the file doesn't exist.
     */
    fun ObjectMapper.readTomlFile(path: Path): MutableMap<String, Any> {
        if (!Files.exists(path)) {
            return mutableMapOf()
        }
        if (!Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
            logger.warn("Skipping dynamic configuration because path is not a regular file: $path")
            return mutableMapOf()
        }
        return runCatching {
            @Suppress("UNCHECKED_CAST")
            this.readValue(path.toFile(), MutableMap::class.java) as MutableMap<String, Any>
        }.getOrDefault(mutableMapOf())
    }

    /**
     * Extension method to write a Map to a TOML file or delete file if empty.
     */
    fun ObjectMapper.writeTomlFile(path: Path, data: Map<String, Any>) {
        if (data.isEmpty()) {
            if (Files.exists(path)) {
                if (Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
                    Files.delete(path)
                } else {
                    logger.warn("Refusing to delete dynamic configuration because path is not a regular file: $path")
                }
            }
        } else {
            if (Files.exists(path) && !Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
                logger.warn("Refusing to write dynamic configuration because path is not a regular file: $path")
                return
            }
            Channels.newOutputStream(
                Files.newByteChannel(path, WRITE, CREATE, TRUNCATE_EXISTING, LinkOption.NOFOLLOW_LINKS)
            ).use { outputStream ->
                this.writeValue(outputStream, data)
            }
        }
    }
}
