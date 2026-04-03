package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class PrependAssignForCollection(
    element: PsiElement,
    textRange: TextRange,
    private val receiver: Expression,
    private val sequence: Expression,
    private val receiverText: String,
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val receiverRange = receiver.textRange
        val sequenceRange = sequence.textRange
        if (receiverRange.endOffset >= sequenceRange.startOffset) {
            return false
        }
        if (sequenceRange.endOffset > textRange.endOffset) {
            return false
        }
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?,
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(PrependAssignForCollection::class.java.name)
        val descriptors = mutableListOf<FoldingDescriptor>()

        val receiverRange = receiver.textRange
        val sequenceRange = sequence.textRange

        val assignmentRange = TextRange.create(receiverRange.endOffset, sequenceRange.startOffset)
        if (!assignmentRange.isEmpty) {
            descriptors += FoldingDescriptor(element.node, assignmentRange, group, " = ")
        }

        val trailingRange = TextRange.create(sequenceRange.endOffset, textRange.endOffset)
        if (!trailingRange.isEmpty) {
            descriptors += FoldingDescriptor(element.node, trailingRange, group, " + $receiverText")
        }

        if (receiver.supportsFoldRegions(document, this)) {
            descriptors += receiver.buildFoldRegions(receiver.element, document, this).toList()
        }

        if (sequence.supportsFoldRegions(document, this)) {
            descriptors += sequence.buildFoldRegions(sequence.element, document, this).toList()
        }

        return descriptors.toTypedArray()
    }
}
