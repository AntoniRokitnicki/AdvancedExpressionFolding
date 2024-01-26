package com.intellij.advancedExpressionFolding.extension

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class UnexpectedException(
    private val clazz: Class<out PsiElement>,
    private val text: String,
    private val textRange: TextRange,
    private val fileName: String?,
    exception: Throwable
) : IllegalStateException(exception) {

    override val message: String
        get() = super.message+" for file:$fileName, class:${clazz.simpleName}, range:$textRange, text:$text"
}
