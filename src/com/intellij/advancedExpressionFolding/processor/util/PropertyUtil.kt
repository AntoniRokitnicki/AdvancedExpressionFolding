package com.intellij.advancedExpressionFolding.processor.util

import kotlin.text.StringBuilder

object PropertyUtil {
    fun guessPropertyName(text: String): String {
        val sb = StringBuilder(text.length)
        val startPos = when {
            text.startsWith("get") || text.startsWith("set") -> 3
            text.startsWith("is") -> 2
            else -> 0
        }
        sb.append(text, startPos, text.length)
        for (i in 0 until sb.length) {
            val c = sb[i]
            if (c.isUpperCase() && (i == sb.length - 1 || sb[i + 1].isUpperCase() || i == 0)) {
                sb.setCharAt(i, c.lowercaseChar())
            } else if (c.isLowerCase()) {
                break
            }
        }
        return sb.toString()
    }
}

