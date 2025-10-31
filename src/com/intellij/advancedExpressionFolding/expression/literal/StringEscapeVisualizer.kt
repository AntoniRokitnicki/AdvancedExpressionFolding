package com.intellij.advancedExpressionFolding.expression.literal

object StringEscapeVisualizer {
    fun render(value: String): String {
        if (value.isEmpty()) {
            return value
        }
        val asciiOnly = isAsciiOnly()
        val builder = StringBuilder(value.length)
        var index = 0
        while (index < value.length) {
            when (val character = value[index]) {
                '\r' -> {
                    if (index + 1 < value.length && value[index + 1] == '\n') {
                        builder.append(if (asciiOnly) ASCII_NEWLINE else NEWLINE_TOKEN)
                        index += 2
                    } else {
                        builder.append(if (asciiOnly) ASCII_CARRIAGE_RETURN else CARRIAGE_RETURN_TOKEN)
                        index += 1
                    }
                }
                '\n' -> {
                    builder.append(if (asciiOnly) ASCII_NEWLINE else NEWLINE_TOKEN)
                    index += 1
                }
                else -> {
                    builder.append(character)
                    index += 1
                }
            }
        }
        return builder.toString()
    }

    private fun isAsciiOnly(): Boolean = java.lang.Boolean.getBoolean(ASCII_ONLY_PROPERTY)

    private const val ASCII_ONLY_PROPERTY = "advanced.expression.folding.stringEscapes.visualizeNewlines.asciiOnly"
    private const val NEWLINE_TOKEN = "⏎"
    private const val CARRIAGE_RETURN_TOKEN = "␍"
    private const val ASCII_NEWLINE = "\\n"
    private const val ASCII_CARRIAGE_RETURN = "\\r"
}
