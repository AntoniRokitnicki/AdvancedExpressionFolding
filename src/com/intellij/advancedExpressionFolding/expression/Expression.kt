package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

abstract class Expression {
    companion object {
        @JvmField
        val EMPTY_ARRAY: Array<FoldingDescriptor> = arrayOf()
        const val HIGHLIGHTED_GROUP_POSTFIX: String = ":highlighting"
    }

    protected lateinit var element: PsiElement
    protected lateinit var textRange: TextRange

    protected constructor() : super()

    constructor(element: PsiElement, textRange: TextRange) {
        this.element = element
        this.textRange = textRange
    }

    override fun toString(): String {
        return element.getText()
    }

    open fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return false
    }

    open fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        return EMPTY_ARRAY
    }

    open fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?,
        overflowGroup: FoldingGroup?,
        overflowLeftPlaceholder: String?,
        overflowRightPlaceholder: String?
    ): Array<FoldingDescriptor> {
        return buildFoldRegions(element, document, parent)
    }

    open fun isCollapsedByDefault(): Boolean {
        return true
    }

    fun getTextRange(): TextRange {
        return textRange
    }

    fun getElement(): PsiElement {
        return element
    }

    open fun isNested(): Boolean {
        return !textRange.equals(element.getTextRange())
    }

    open fun isOverflow(): Boolean {
        return isLeftOverflow() || isRightOverflow()
    }

    open fun isLeftOverflow(): Boolean {
        return false
    }

    open fun isRightOverflow(): Boolean {
        return false
    }

    open fun isHighlighted(): Boolean {
        return false
    }

    open fun getHighlightedTextRange(): TextRange {
        return getTextRange()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || this.javaClass != o.javaClass) return false

        val that = o as Expression

        if (!element.equals(that.element)) return false
        return textRange.equals(that.textRange)
    }

    override fun hashCode(): Int {
        var result = element.hashCode()
        result = 31 * result + textRange.hashCode()
        return result
    }
}
