package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class Expression {
    companion object {
        @JvmField val EMPTY_ARRAY: Array<FoldingDescriptor> = arrayOf()
        const val HIGHLIGHTED_GROUP_POSTFIX: String = ":highlighting"
    }

    open lateinit var element: PsiElement
    open lateinit var textRange: TextRange

    protected constructor()

    protected constructor(element: PsiElement, textRange: TextRange) {
        this.element = element
        this.textRange = textRange
    }

    override fun toString(): String = element.text // TODO: Use document.getText(textRange)

    open fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return false // TODO no-format: This should be impossible
    }

    open fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> = EMPTY_ARRAY

    open fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?,
        overflowGroup: FoldingGroup?,
        overflowLeftPlaceholder: String?,
        overflowRightPlaceholder: String?
    ): Array<FoldingDescriptor> = buildFoldRegions(element, document, parent)

    open fun isCollapsedByDefault(): Boolean = true

    fun getTextRange(): TextRange = textRange

    fun getElement(): PsiElement = element

    open fun isNested(): Boolean = textRange != element.textRange

    fun isOverflow(): Boolean = isLeftOverflow() || isRightOverflow()

    open fun isLeftOverflow(): Boolean = false

    open fun isRightOverflow(): Boolean = false

    open fun isHighlighted(): Boolean = false

    open fun getHighlightedTextRange(): TextRange = textRange

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Expression
        if (element != other.element) return false
        if (textRange != other.textRange) return false
        return true
    }

    override fun hashCode(): Int {
        var result = element.hashCode()
        result = 31 * result + textRange.hashCode()
        return result
    }
}

