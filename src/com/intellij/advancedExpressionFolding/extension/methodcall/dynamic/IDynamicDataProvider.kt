package com.intellij.advancedExpressionFolding.extension.methodcall.dynamic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.toml.TomlFactory
import com.intellij.advancedExpressionFolding.extension.asInstance

interface IDynamicDataProvider {
    val objectMapper: ObjectMapper
        get() = ObjectMapper(TomlFactory())

    fun parse(): List<DynamicMethodCall>

    fun parseToml(text: String): List<DynamicMethodCall> {
        val listOfMaps =
            objectMapper.readValue(text, Map::class.java).values.asInstance<Collection<Map<String, String>>>()
        return listOfMaps?.map {
            DynamicMethodCall(DynamicMethodCallData(it))
        } ?: emptyList()
    }

}
