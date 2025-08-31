package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiForStatement
import java.util.ArrayList

class ForEachStatement(
    private val element: PsiForStatement,
    textRange: TextRange,
    private val declarationTextRange: TextRange,
    private val variableTextRange: TextRange,
    private val arrayTextRange: TextRange
) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?) = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ForEachStatement::class.java.name)
        if (
            AdvancedExpressionFoldingSettings.getInstance().state.compactControlFlowSyntaxCollapse &&
            this.element.lParenth != null
        ) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(
                        this.element.lParenth!!.textRange.startOffset,
                        this.element.lParenth!!.textRange.startOffset + 1
                    ),
                    group,
                    ""
                )
            )
        }
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, declarationTextRange.startOffset),
                group,
                ""
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(variableTextRange.endOffset, arrayTextRange.startOffset),
                group,
                " : "
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(arrayTextRange.endOffset, declarationTextRange.endOffset),
                group,
                if (AdvancedExpressionFoldingSettings.getInstance().state.compactControlFlowSyntaxCollapse) " {\n" else ") {\n"
            )
        )
        return descriptors.toTypedArray()
    }
}

