package com.intellij.advancedExpressionFolding.kotlin.pseudoAnnotations

import org.jetbrains.kotlin.psi.KtParameter

class KotlinParameterValueProvider {
    fun from(parameter: KtParameter, index: Int, usedNames: MutableSet<String>): ParameterValue {
        val baseName = parameter.name?.takeIf { it.isNotBlank() } ?: "param$index"
        val name = reserveName(baseName, usedNames)
        val typeText = parameter.typeReference?.text?.trim()

        return if (parameter.isVarArg) {
            buildVarArgValue(name, typeText)
        } else {
            buildRegularValue(name, typeText)
        }
    }

    private fun buildRegularValue(name: String, typeText: String?): ParameterValue {
        val trimmedType = typeText?.trim()
        val declarationValue = defaultValueExpression(name, trimmedType)
        val declaration = if (trimmedType != null) {
            "val $name: $trimmedType = $declarationValue"
        } else {
            "val $name = $declarationValue"
        }
        return ParameterValue(name, declaration, name)
    }

    private fun buildVarArgValue(name: String, typeText: String?): ParameterValue {
        val elementType = typeText?.removeSuffix("?")?.takeIf { it.isNotBlank() } ?: "Any?"
        val declaration = "val $name = ${defaultVarargValue(elementType)}"
        return ParameterValue(name, declaration, "*$name")
    }

    private fun defaultValueExpression(name: String, typeText: String?): String {
        if (typeText == null || typeText.isBlank()) return "TODO()"
        val trimmed = typeText.trim()
        if (trimmed.endsWith("?")) return "null"

        val bare = trimmed.removeSuffix("?")
        val base = bare.substringBefore('<')
        val generics = if ('<' in bare) bare.substringAfter('<').substringBeforeLast('>') else ""
        val simpleName = base.substringAfterLast('.')

        return when (simpleName) {
            "Boolean" -> "false"
            "Char" -> "'\\u0000'"
            "Byte" -> "0"
            "Short" -> "0"
            "Int" -> "0"
            "Long" -> "0L"
            "Float" -> "0f"
            "Double" -> "0.0"
            "String" -> "\"\""
            "UInt" -> "0u"
            "ULong" -> "0uL"
            "UByte" -> "0u"
            "UShort" -> "0u"
            "LocalDate" -> "java.time.LocalDate.now()"
            "LocalDateTime" -> "java.time.LocalDateTime.now()"
            "ZonedDateTime" -> "java.time.ZonedDateTime.now()"
            "Instant" -> "java.time.Instant.now()"
            "Date" -> "java.util.Date()"
            "BigDecimal" -> "java.math.BigDecimal.ZERO"
            else -> when {
                simpleName.endsWith("Array") -> arrayDefault(simpleName)
                simpleName == "Array" -> "emptyArray<${genericOrDefault(generics)}>()"
                simpleName == "List" -> "emptyList<${genericOrDefault(generics)}>()"
                simpleName == "MutableList" -> "mutableListOf<${genericOrDefault(generics)}>()"
                simpleName == "Set" -> "emptySet<${genericOrDefault(generics)}>()"
                simpleName == "MutableSet" -> "mutableSetOf<${genericOrDefault(generics)}>()"
                simpleName == "Map" -> "emptyMap<${genericOrDefault(generics, "Any?, Any?")}>()"
                simpleName == "MutableMap" -> "mutableMapOf<${genericOrDefault(generics, "Any?, Any?")}>()"
                simpleName == "Sequence" -> "emptySequence<${genericOrDefault(generics)}>()"
                simpleName == "ArrayList" -> "arrayListOf<${genericOrDefault(generics)}>()"
                else -> "TODO()"
            }
        }
    }

    private fun defaultVarargValue(elementType: String): String {
        val simple = elementType.substringAfterLast('.')
        return when (simple) {
            "Int" -> "intArrayOf()"
            "Long" -> "longArrayOf()"
            "Short" -> "shortArrayOf()"
            "Byte" -> "byteArrayOf()"
            "Double" -> "doubleArrayOf()"
            "Float" -> "floatArrayOf()"
            "Boolean" -> "booleanArrayOf()"
            "Char" -> "charArrayOf()"
            else -> "emptyArray<$elementType>()"
        }
    }

    private fun arrayDefault(simpleName: String): String = when (simpleName) {
        "IntArray" -> "intArrayOf()"
        "LongArray" -> "longArrayOf()"
        "ShortArray" -> "shortArrayOf()"
        "ByteArray" -> "byteArrayOf()"
        "DoubleArray" -> "doubleArrayOf()"
        "FloatArray" -> "floatArrayOf()"
        "BooleanArray" -> "booleanArrayOf()"
        "CharArray" -> "charArrayOf()"
        else -> "$simpleName()"
    }

    private fun genericOrDefault(generics: String, fallback: String = "Any?"): String =
        generics.takeIf { it.isNotBlank() } ?: fallback

    private fun reserveName(baseName: String, usedNames: MutableSet<String>): String {
        var candidate = baseName
        var counter = 1
        while (!usedNames.add(candidate)) {
            candidate = baseName + counter
            counter++
        }
        return candidate
    }
}
