package com.intellij.advancedExpressionFolding.processor.util

import org.jetbrains.annotations.NotNull
import java.lang.Character.*

object PropertyUtil {
    @JvmStatic
    @NotNull
    fun guessPropertyName(@NotNull text: String): String {
        val sb = StringBuilder(text.length)
        var startPos: Int
        if (text.startsWith("get") || text.startsWith("set")) {
            startPos = 3
        } else if (text.startsWith("is")) {
            startPos = 2
        } else {
            startPos = 0
        }
        sb.append(text, startPos, text.length)
        var i = 0
        while (i < sb.length) {
            if (isUpperCase(sb[i]) && (i == sb.length - 1 || isUpperCase(sb[i + 1]) || i == 0)) {
                sb.setCharAt(i, toLowerCase(sb[i]))
            } else if (isLowerCase(sb[i])) {
                break
            }
            i++
        }
        return sb.toString()
    }
}
