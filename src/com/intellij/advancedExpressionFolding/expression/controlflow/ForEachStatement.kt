package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IControlFlowState
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiForStatement

class ForEachStatement(
    private val forStatement: PsiForStatement,
    textRange: TextRange,
    private val declarationTextRange: TextRange,
    private val variableTextRange: TextRange,
    private val arrayTextRange: TextRange,
) : Expression(forStatement, textRange), IControlFlowState by State()() {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ForEachStatement::class.java.name)
        if (compactControlFlowSyntaxCollapse &&
            this.forStatement.lParenth != null
        ) {
            val startOffset = this.forStatement.lParenth!!.textRange.startOffset
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(startOffset, startOffset + 1),
                group,
                ""
            )
        }
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(textRange.startOffset, declarationTextRange.startOffset),
            group,
            ""
        )
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(variableTextRange.endOffset, arrayTextRange.startOffset),
            group,
            " : "
        )
        val placeholder = if (compactControlFlowSyntaxCollapse) {
            " {\n"
        } else {
            ") {\n"
        }
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(arrayTextRange.endOffset, declarationTextRange.endOffset),
            group,
            placeholder
        )
        return descriptors.toTypedArray()
    }
}
