package com.intellij.advancedExpressionFolding.processor.util

object PropertyUtil {
    fun guessPropertyName(text: String): String {
        val startPos = when {
            text.startsWith("get") || text.startsWith("set") -> 3
            text.startsWith("with") -> 4
            text.startsWith("is") -> 2
            else -> 0
        }
        val builder = StringBuilder(text.substring(startPos))
        for (i in builder.indices) {
            val current = builder[i]
            if (current.isUpperCase()) {
                val next = if (i == builder.lastIndex) null else builder[i + 1]
                val shouldLowercase = i == 0 || next == null || next.isUpperCase() || next.isDigit()
                if (shouldLowercase) {
                    builder.setCharAt(i, current.lowercaseChar())
                    continue
                }
            }
            if (current.isLowerCase()) {
                break
            }
        }
        return builder.toString()
    }
}
