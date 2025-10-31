package com.intellij.advancedExpressionFolding.processor

inline fun <reified T> Any?.asInstance(): T? = if (T::class.isInstance(this)) {
    this as T
} else {
    null
}

inline fun <reified T> Any?.isInstance(): Boolean = this is T

fun String.equalsIgnoreSpaces(second: String): Boolean =
    filterNot(Char::isWhitespace) == second.filterNot(Char::isWhitespace)

fun Boolean?.takeIfTrue(): Any? = if (this == true) {
    takeIfTrue(true)
} else {
    null
}

fun <T> Boolean.takeIfTrue(element: T?): T? = if (this) {
    element
} else {
    null
}

fun Boolean.takeIfFalse(): Any? = if (this) {
    null
} else {
    true
}

fun <T> Boolean.takeIfFalse(element: T?): T? = if (this) {
    null
} else {
    element
}

fun allTrue(vararg booleans: Boolean?): Boolean = booleans.all {
    it == true
}
fun allNotNull(vararg any: Any?): Boolean = any.all {
    it != null
}
