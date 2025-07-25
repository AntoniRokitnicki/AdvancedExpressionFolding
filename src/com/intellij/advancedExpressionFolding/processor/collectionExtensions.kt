package com.intellij.advancedExpressionFolding.processor

import com.intellij.psi.PsiElement
import java.util.*

fun <E, T : Collection<E>?> T.takeIfSizeNot(size: Int): T? = this.takeIf {
    it?.size != size
}
fun <T> Array<T>?.takeIfSize(size: Int): Array<T>? = this.takeIf {
    it?.size == size
}

val Array<*>?.one: Boolean
    get() = this?.singleOrNull() != null
val List<*>?.one: Boolean
    get() = this?.singleOrNull() != null

fun <T> Array<T>.firstOrNullIfNotEmpty(): T? {
    return if (isEmpty() || size > 1) {
        null
    } else {
        first()
    }
}

fun Array<out PsiElement>.asInstance(vararg elements: Class<out PsiElement>): Array<out PsiElement>? {
    if (elements.size != this.size) {
        return null
    }
    val classQueue = LinkedList(elements.asList())
    forEach {
        val next = classQueue.poll()
        if (!next.isInstance(it)) {
            return null
        }
    }
    return this
}


fun <T> Iterable<T>.distinctNot(): List<T> {
    return groupBy {
        it
    }.filter {
        it.value.size > 1
    }.flatMap {
        it.value
    }
}

fun <K, V> Map<K, V>.isUnique(): Boolean =
    keys.sameSize(keys.distinct()) && values.sameSize(values.distinct())

fun <E, E2> Collection<E>?.sameSize(otherCollection: Collection<E2>?) = this?.size == otherCollection?.size
