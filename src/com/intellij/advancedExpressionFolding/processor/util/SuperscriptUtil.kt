package com.intellij.advancedExpressionFolding.processor.util

object SuperscriptUtil {

    fun superscript(str: String): String? = map(str, Consts.SUPERSCRIPT_MAPPING)

    private fun map(str: String, mapping: Map<Char, Char>): String? {
        val builder = StringBuilder(str.length)
        for (ch in str) {
            val mapped = mapping[ch] ?: return null
            if (mapped != '❤') {
                builder.append(mapped)
            }
        }
        return builder.toString()
    }
}
