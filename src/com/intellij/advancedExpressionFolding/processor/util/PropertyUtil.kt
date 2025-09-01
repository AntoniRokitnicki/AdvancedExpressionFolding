package com.intellij.advancedExpressionFolding.processor.util

import org.jetbrains.annotations.NotNull
import java.lang.Character

class PropertyUtil private constructor() {
    companion object {
        @NotNull
        fun guessPropertyName(@NotNull text: String): String {
            val sb = StringBuilder(text.length)
            val startPos: Int
            if (text.startsWith("get") || text.startsWith("set")) {
                startPos = 3
            } else if (text.startsWith("is")) {
                startPos = 2
            } else {
                startPos = 0
            }
            sb.append(text, startPos, text.length)
            var i = 0
            while (i < sb.length()) {
                if (Character.isUpperCase(sb.get(i)) && (i == sb.length() - 1 || Character.isUpperCase(sb.get(i + 1)) || i == 0)) {
                    sb.setCharAt(i, Character.toLowerCase(sb.get(i)))
                } else if (Character.isLowerCase(sb.get(i))) {
                    break
                }
                i++
            }
            return sb.toString()
        }
    }
}
