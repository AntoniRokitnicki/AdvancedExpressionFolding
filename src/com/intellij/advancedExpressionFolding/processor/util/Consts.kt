package com.intellij.advancedExpressionFolding.processor.util

import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import java.util.regex.Pattern

object Consts {
    @JvmField
    val SUPPORTED_CLASSES: MutableSet<String> = object : HashSet<String>() {
        init {
            add("java.math.BigDecimal")
            add("java.math.BigInteger")
            add("java.lang.Math")
            add("java.lang.Long")
            add("java.lang.Integer")
            add("java.lang.Float")
            add("java.lang.Double")
            add("java.lang.Character")
            add("java.lang.String")
            add("java.lang.StringBuilder")
            add("java.lang.AbstractStringBuilder")
            add("java.util.List")
            add("java.util.ArrayList")
            add("java.util.Map")
            add("java.util.HashMap")
            add("java.util.Set")
            add("java.util.HashSet")
            add("java.lang.Object")
            add("java.util.Arrays")
            add("java.util.Optional")
            add("java.util.Collection")
            add("java.util.Collections")
            add("java.util.Objects")
            add("java.util.stream.Stream")
            add("java.io.PrintStream")
        }
    }

    @JvmField
    val UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS: MutableSet<String> = object : HashSet<String>() {
        init {
            add("equals")
            add("compareTo")
        }
    }

    @JvmField
    val SUPPORTED_PRIMITIVE_TYPES: MutableSet<String> = object : HashSet<String>() {
        init {
            add("int")
            add("long")
            add("float")
            add("double")
            add("char")
            add("java.lang.String")
        }
    }

    @JvmField
    val SUPPORTED_BINARY_OPERATORS: MutableSet<String> = object : HashSet<String>() {
        init {
            add("+")
            add("-")
            add("*")
            add("/")
        }
    }

    @JvmField
    val SUPPORTED_CONSTANTS: MutableMap<String, Any> = object : HashMap<String, Any>() {
        init {
            put("ZERO", 0)
            put("ONE", 1)
            put("TEN", 10)
            put("PI", "π")
            put("E", "\uD835\uDC52")
        }
    }

    @JvmField
    val GENERICS_PATTERN: Pattern = Pattern.compile("<[^<>]*>")

    @JvmField
    val SUPERSCRIPT_MAPPING: MutableMap<Char, Char> = object : HashMap<Char, Char>() {
        init {
            put('0', '⁰')
            put('1', '¹')
            put('2', '²')
            put('3', '³')
            put('4', '⁴')
            put('5', '⁵')
            put('6', '⁶')
            put('7', '⁷')
            put('8', '⁸')
            put('9', '⁹')
            put('(', '⁽')
            put(')', '⁾')
            put('+', '⁺')
            put('⁻', '⁻')
            put('n', 'ⁿ')
            put('i', 'ⁱ')
            put('a', 'ᵃ')
            put('b', 'ᵇ')
            put('c', 'ᶜ')
            put('d', 'ᵈ')
            put('e', 'ᵉ')
            put('f', 'ᶠ')
            put('g', 'ᵍ')
            put('h', 'ʰ')
            put('j', 'ʲ')
            put('k', 'ᵏ')
            put('l', 'ˡ')
            put('m', 'ᵐ')
            put('o', 'ᵒ')
            put('p', 'ᵖ')
            put('r', 'ʳ')
            put('s', 'ˢ')
            put('t', 'ᵗ')
            put('u', 'ᵘ')
            put('w', 'ʷ')
            put('*', 'ˣ')
            put('x', 'ˣ')
            put('y', 'ʸ')
            put('z', 'ᶻ')
            put('A', 'ᴬ')
            put('B', 'ᴮ')
            put('D', 'ᴰ')
            put('E', 'ᴱ')
            put('G', 'ᴳ')
            put('H', 'ᴴ')
            put('I', 'ᴵ')
            put('J', 'ᴶ')
            put('K', 'ᴷ')
            put('L', 'ᴸ')
            put('M', 'ᴹ')
            put('N', 'ᴺ')
            put('O', 'ᴼ')
            put('P', 'ᴾ')
            put('R', 'ᴿ')
            put('T', 'ᵀ')
            put('U', 'ᵁ')
            put('V', 'ⱽ')
            put('W', 'ᵂ')
            put(' ', '❤')
        }
    }

    @JvmField
    val SUBSCRIPT_MAPPING: MutableMap<Char, Char> = object : HashMap<Char, Char>() {
        init {
            put('0', '₀')
            put('1', '₁')
            put('2', '₂')
            put('3', '₃')
            put('4', '₄')
            put('5', '₅')
            put('6', '₆')
            put('7', '₇')
            put('8', '₈')
            put('9', '₉')
            put('+', '₊')
            put('-', '₋')
            put('(', '₍')
            put(')', '₎')
            put('a', 'ₐ')
            put('e', 'ₑ')
            put('x', 'ₓ')
            put('i', 'ᵢ')
            put('j', 'ⱼ')
            put('o', 'ₒ')
            put('r', 'ᵣ')
            put('u', 'ᵤ')
            put('v', 'ᵥ')
            put(' ', '❤')
        }
    }

    enum class Emoji(private val unicode: String) {
        FINAL_LOCK("\uD83D\uDD12"),
        SINGLETON_MAN_STANDING("\uD83E\uDDCD");

        override fun toString(): String {
            return unicode
        }
    }
}
