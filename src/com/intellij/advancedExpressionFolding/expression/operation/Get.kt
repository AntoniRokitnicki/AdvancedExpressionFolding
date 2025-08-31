package com.intellij.advancedExpressionFolding.expression.operation

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Get(
    element: PsiElement,
    textRange: TextRange,
    private val obj: Expression,
    private val key: Expression,
    private val style: Style
) : Expression(element, textRange) {

    enum class Style {
        NORMAL,
        FIRST,
        LAST
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val objectEnd = obj.textRange.endOffset
        val keyStart = key.textRange.startOffset
        val keyEnd = key.textRange.endOffset
        val end = textRange.endOffset
        return if (style == Style.NORMAL) {
            objectEnd < keyStart && keyEnd < end
        } else {
            objectEnd < keyStart - 1 && keyStart < keyEnd
        }
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Get::class.java.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        if (style == Style.NORMAL) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(obj.textRange.endOffset, key.textRange.startOffset),
                    group,
                    "["
                )
            )
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(key.textRange.endOffset, textRange.endOffset),
                    group,
                    "]"
                )
            )
        } else {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(obj.textRange.endOffset, key.textRange.startOffset - 1),
                    group,
                    "." + if (style == Style.FIRST) "getFirst" else "getLast"
                )
            )
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(key.textRange.startOffset, key.textRange.endOffset),
                    group,
                    ""
                )
            )
        }
        if (obj.supportsFoldRegions(document, this)) {
            descriptors.addAll(obj.buildFoldRegions(obj.element, document, this))
        }
        if (style == Style.NORMAL && key.supportsFoldRegions(document, this)) {
            descriptors.addAll(key.buildFoldRegions(key.element, document, this))
        }
        return descriptors.toTypedArray()
    }
}

