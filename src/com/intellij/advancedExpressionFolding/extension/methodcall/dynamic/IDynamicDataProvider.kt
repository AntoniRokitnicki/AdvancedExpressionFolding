package com.intellij.advancedExpressionFolding.extension.methodcall.dynamic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.toml.TomlFactory
import com.intellij.advancedExpressionFolding.extension.asInstance

interface IDynamicDataProvider {
    private val objectMapper: ObjectMapper
        get() = ObjectMapper(TomlFactory())

    fun parse(): List<DynamicMethodCall>

    fun parseToml(text: String): List<DynamicMethodCall> {
        val mapOfMaps = objectMapper.readValue(text, Map::class.java)
        val asInstance = mapOfMaps.values.asInstance<Collection<Map<String, String>>>()
        return asInstance?.map {
            DynamicMethodCall(DynamicMethodCallData(it))
        } ?: emptyList()
    }
}
