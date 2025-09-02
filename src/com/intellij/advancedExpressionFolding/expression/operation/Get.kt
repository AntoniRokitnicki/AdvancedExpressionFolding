package com.intellij.advancedExpressionFolding.expression.operation

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

class Get(
    element: PsiElement,
    textRange: TextRange,
    obj: Expression,
    key: Expression,
    style: Style
) : Expression(element, textRange) {
    private var objectExpression: Expression = obj
    private var key: Expression = key
    private var style: Style = style

    enum class Style {
        NORMAL,
        FIRST,
        LAST
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val objectEnd = objectExpression.getTextRange().getEndOffset()
        val keyStart = key.getTextRange().getStartOffset()
        val keyEnd = key.getTextRange().getEndOffset()
        val end = getTextRange().getEndOffset()
        return if (style == Style.NORMAL) {
            objectEnd < keyStart && keyEnd < end
        } else {
            objectEnd < keyStart - 1 && keyStart < keyEnd
        }
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Get::class.java.getName())
        val descriptors = ArrayList<FoldingDescriptor>()
        if (style == Style.NORMAL) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(objectExpression.getTextRange().getEndOffset(),
                        key.getTextRange().getStartOffset()),
                    group,
                    "["
                )
            )
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(key.getTextRange().getEndOffset(),
                        getTextRange().getEndOffset()),
                    group,
                    "]"
                )
            )
        } else {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(objectExpression.getTextRange().getEndOffset(),
                        key.getTextRange().getStartOffset() - 1),
                    group,
                    "." + if (style == Style.FIRST) "getFirst" else "getLast"
                )
            )
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(key.getTextRange().getStartOffset(),
                        key.getTextRange().getEndOffset()),
                    group,
                    ""
                )
            )
        }
        if (objectExpression.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *objectExpression.buildFoldRegions(objectExpression.getElement(), document, this))
        }
        if (style == Style.NORMAL) {
            if (key.supportsFoldRegions(document, this)) {
                Collections.addAll(descriptors, *key.buildFoldRegions(key.getElement(), document, this))
            }
        }
        return descriptors.toTypedArray()
    }
}
