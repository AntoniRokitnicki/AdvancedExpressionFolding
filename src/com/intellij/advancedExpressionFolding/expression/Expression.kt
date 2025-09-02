package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class Expression {
    companion object {
        @JvmField
        val EMPTY_ARRAY: Array<FoldingDescriptor> = arrayOf()
        const val HIGHLIGHTED_GROUP_POSTFIX: String = ":highlighting"
    }

    protected var _element: PsiElement? = null
    protected var _textRange: TextRange? = null

    protected constructor() : super()

    constructor(element: PsiElement, textRange: TextRange) {
        this._element = element
        this._textRange = textRange
    }

    override fun toString(): String {
        return _element!!.getText() // TODO: Use document.getText(textRange)
    }

    open fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return false // TODO no-format: This should be impossible
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
        return _textRange!!
    }

    fun getElement(): PsiElement {
        return _element!!
    }

    open fun isNested(): Boolean {
        return !_textRange!!.equals(_element!!.getTextRange())
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

        if (!_element!!.equals(that.getElement())) return false
        return _textRange!!.equals(that.getTextRange())
    }

    override fun hashCode(): Int {
        var result = _element!!.hashCode()
        result = 31 * result + _textRange!!.hashCode()
        return result
    }
}

