package com.intellij.advancedExpressionFolding.extension

inline fun <reified T> Any?.asInstance(): T? = if (T::class.isInstance(this)) {
    this as T
} else {
    null
}

inline fun <reified T> Any?.isInstance(): Boolean = this is T

fun String.equalsIgnoreSpaces(second: String): Boolean =
    filterNot(Char::isWhitespace) == second.filterNot(Char::isWhitespace)


fun Boolean?.on(): Any? = if (this == true) {
    on(true)
} else {
    null
}

fun <T> Boolean.on(element: T?): T? = if (this) {
    element
} else {
    null
}

fun Boolean.off(): Any? = if (this) {
    null
} else {
    true
}

fun <T> Boolean.off(element: T?): T? = if (this) {
    null
} else {
    element
}
