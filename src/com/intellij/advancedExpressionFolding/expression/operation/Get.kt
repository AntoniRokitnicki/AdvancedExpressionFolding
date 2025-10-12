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
    val `object`: Expression,
    val key: Expression,
    val style: Style
) : Expression(element, textRange) {

    enum class Style { NORMAL, FIRST, LAST }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val objectEnd = `object`.textRange.endOffset
        val keyStart = key.textRange.startOffset
        val keyEnd = key.textRange.endOffset
        val end = textRange.endOffset
        return if (style == Style.NORMAL) {
            objectEnd < keyStart && keyEnd < end
        } else {
            objectEnd < keyStart - 1 && keyStart < keyEnd
        }
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Get::class.java.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        if (style == Style.NORMAL) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(`object`.textRange.endOffset, key.textRange.startOffset),
                group,
                "["
            )
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(key.textRange.endOffset, textRange.endOffset),
                group,
                "]"
            )
        } else {
            val placeholder = "." + if (style == Style.FIRST) "getFirst" else "getLast"
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(`object`.textRange.endOffset, key.textRange.startOffset - 1),
                group,
                placeholder
            )
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(key.textRange.startOffset, key.textRange.endOffset),
                group,
                ""
            )
        }
        if (`object`.supportsFoldRegions(document, this)) {
            descriptors += `object`.buildFoldRegions(`object`.element, document, this).toList()
        }
        if (style == Style.NORMAL && key.supportsFoldRegions(document, this)) {
            descriptors += key.buildFoldRegions(key.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }
}
