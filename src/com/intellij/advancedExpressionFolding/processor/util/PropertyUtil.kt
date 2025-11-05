package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.psi.util.PropertyUtilBase

object PropertyUtil {
    fun guessPropertyName(text: String): String {
        val withoutPrefix = text
            .removePrefix("get")
            .removePrefix("set")
            .removePrefix("is")
            .removePrefix("with")

        return PropertyUtilBase.getPropertyName(withoutPrefix)
            ?: PropertyUtilBase.getPropertyName(text)?.takeUnless { it == withoutPrefix }
            ?: PropertyUtilBase.getPropertyName("get$withoutPrefix")?.takeUnless { it == withoutPrefix }
            ?: decapitalizeLeadingUppercase(withoutPrefix)
    }

    private fun decapitalizeLeadingUppercase(value: String): String {
        if (value.isEmpty()) return value
        val builder = StringBuilder(value)
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
