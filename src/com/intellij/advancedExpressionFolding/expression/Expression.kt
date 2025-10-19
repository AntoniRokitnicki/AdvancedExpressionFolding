package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class Expression protected constructor() {

    protected lateinit var elementBacking: PsiElement
        private set
    protected lateinit var textRangeBacking: TextRange
        private set

    protected constructor(element: PsiElement, textRange: TextRange) : this() {
        this.elementBacking = element
        this.textRangeBacking = textRange
    }

    open val element: PsiElement
        get() = elementBacking

    open val textRange: TextRange
        get() = textRangeBacking

    open fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val range = textRange
        if (range.isEmpty) {
            return false
        }
        if (range.startOffset < 0 || range.endOffset > document.textLength) {
            return false
        }
        val parentRange = parent?.textRange ?: return true
        if (range.startOffset < parentRange.startOffset || range.endOffset > parentRange.endOffset) {
            return false
        }
        if (parentRange == range && !isHighlighted() && !isOverflow()) {
            return false
        }
        return true
    }

    open fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
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

    open fun isCollapsedByDefault(): Boolean = true

    open fun isNested(): Boolean = textRange != element.textRange

    open fun isOverflow(): Boolean = isLeftOverflow() || isRightOverflow()

    open fun isLeftOverflow(): Boolean = false

    open fun isRightOverflow(): Boolean = false

    open fun isHighlighted(): Boolean = false

    open fun getHighlightedTextRange(): TextRange = textRange

    override fun toString(): String = element.text

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

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

    companion object {
        val EMPTY_ARRAY: Array<FoldingDescriptor> = emptyArray()
        const val HIGHLIGHTED_GROUP_POSTFIX: String = ":highlighting"
    }
}
