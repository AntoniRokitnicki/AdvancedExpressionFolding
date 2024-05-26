package com.intellij.advancedExpressionFolding.extension.methodcall.dynamic

import com.intellij.util.io.readText
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
}