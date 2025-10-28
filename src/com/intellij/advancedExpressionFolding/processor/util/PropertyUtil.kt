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
            if (current.isUpperCase() && (i == builder.lastIndex || builder[i + 1].isUpperCase() || i == 0)) {
                builder.setCharAt(i, current.lowercaseChar())
            } else if (current.isLowerCase()) {
                break
            }
        }
        return builder.toString()
    }
}
