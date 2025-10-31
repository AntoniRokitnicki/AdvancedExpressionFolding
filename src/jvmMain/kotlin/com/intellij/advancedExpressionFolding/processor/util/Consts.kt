package com.intellij.advancedExpressionFolding.processor.util

import java.util.regex.Pattern

object Consts {
    val SUPPORTED_CLASSES: Set<String> = setOf(
        "java.math.BigDecimal",
        "java.math.BigInteger",
        "java.lang.Math",
        "java.lang.Long",
        "java.lang.Integer",
        "java.lang.Float",
        "java.lang.Double",
        "java.lang.Character",
        "java.lang.String",
        "java.lang.StringBuilder",
        "java.lang.AbstractStringBuilder",
        "java.util.List",
        "java.util.ArrayList",
        "java.util.Map",
        "java.util.HashMap",
        "java.util.Set",
        "java.util.HashSet",
        "java.lang.Object",
        "java.util.Arrays",
        "java.util.Optional",
        "java.util.Collection",
        "java.util.Collections",
        "java.util.Objects",
        "java.util.stream.Stream",
        "java.io.PrintStream"
    )

    val UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS: Set<String> = setOf("equals", "compareTo")

    val SUPPORTED_PRIMITIVE_TYPES: Set<String> = setOf("int", "long", "float", "double", "char", "java.lang.String")

    val SUPPORTED_BINARY_OPERATORS: Set<String> = setOf("+", "-", "*", "/")

    val SUPPORTED_CONSTANTS: Map<String, Any> = mapOf(
        "ZERO" to 0,
        "ONE" to 1,
        "TEN" to 10,
        "PI" to "π",
        "E" to "\uD835\uDC52"
    )

    val GENERICS_PATTERN: Pattern = Pattern.compile("<[^<>]*>")

    val SUPERSCRIPT_MAPPING: Map<Char, Char> = mapOf(
        '0' to '⁰',
        '1' to '¹',
        '2' to '²',
        '3' to '³',
        '4' to '⁴',
        '5' to '⁵',
        '6' to '⁶',
        '7' to '⁷',
        '8' to '⁸',
        '9' to '⁹',
        '(' to '⁽',
        ')' to '⁾',
        '+' to '⁺',
        '⁻' to '⁻',
        'n' to 'ⁿ',
        'i' to 'ⁱ',
        'a' to 'ᵃ',
        'b' to 'ᵇ',
        'c' to 'ᶜ',
        'd' to 'ᵈ',
        'e' to 'ᵉ',
        'f' to 'ᶠ',
        'g' to 'ᵍ',
        'h' to 'ʰ',
        'j' to 'ʲ',
        'k' to 'ᵏ',
        'l' to 'ˡ',
        'm' to 'ᵐ',
        'o' to 'ᵒ',
        'p' to 'ᵖ',
        'r' to 'ʳ',
        's' to 'ˢ',
        't' to 'ᵗ',
        'u' to 'ᵘ',
        'w' to 'ʷ',
        '*' to 'ˣ',
        'x' to 'ˣ',
        'y' to 'ʸ',
        'z' to 'ᶻ',
        'A' to 'ᴬ',
        'B' to 'ᴮ',
        'D' to 'ᴰ',
        'E' to 'ᴱ',
        'G' to 'ᴳ',
        'H' to 'ᴴ',
        'I' to 'ᴵ',
        'J' to 'ᴶ',
        'K' to 'ᴷ',
        'L' to 'ᴸ',
        'M' to 'ᴹ',
        'N' to 'ᴺ',
        'O' to 'ᴼ',
        'P' to 'ᴾ',
        'R' to 'ᴿ',
        'T' to 'ᵀ',
        'U' to 'ᵁ',
        'V' to 'ⱽ',
        'W' to 'ᵂ',
        ' ' to '❤'
    )

    val SUBSCRIPT_MAPPING: Map<Char, Char> = mapOf(
        '0' to '₀',
        '1' to '₁',
        '2' to '₂',
        '3' to '₃',
        '4' to '₄',
        '5' to '₅',
        '6' to '₆',
        '7' to '₇',
        '8' to '₈',
        '9' to '₉',
        '+' to '₊',
        '-' to '₋',
        '(' to '₍',
        ')' to '₎',
        'a' to 'ₐ',
        'e' to 'ₑ',
        'x' to 'ₓ',
        'i' to 'ᵢ',
        'j' to 'ⱼ',
        'o' to 'ₒ',
        'r' to 'ᵣ',
        'u' to 'ᵤ',
        'v' to 'ᵥ',
        ' ' to '❤'
    )

    enum class Emoji(private val unicode: String) {
        FINAL_LOCK("\uD83D\uDD12"),
        SINGLETON_MAN_STANDING("\uD83E\uDDCD");

        override fun toString(): String = unicode
    }
}
