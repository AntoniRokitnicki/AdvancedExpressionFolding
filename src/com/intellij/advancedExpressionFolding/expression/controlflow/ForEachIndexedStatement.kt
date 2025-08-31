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

class ForEachIndexedStatement(
    private val element: PsiForStatement,
    textRange: TextRange,
    private val declarationTextRange: TextRange,
    private val indexTextRange: TextRange,
    private val variableTextRange: TextRange,
    private val arrayTextRange: TextRange,
    private val varSyntax: Boolean,
    private val isFinal: Boolean
) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?) = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ForEachIndexedStatement::class.java.name)
        val prefixRange = TextRange.create(textRange.startOffset, textRange.startOffset + 1)
        var prefix = document.getText(prefixRange)
        // TODO: Refactor this mess
        if (
            AdvancedExpressionFoldingSettings.getInstance().state.compactControlFlowSyntaxCollapse &&
            prefix == "("
        ) {
            prefix = ""
        }
        if (varSyntax) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(textRange.startOffset, indexTextRange.startOffset),
                    group,
                    prefix + if (isFinal) "val" else "var" + " (",
                ),
            )
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(indexTextRange.endOffset, variableTextRange.startOffset - 1),
                    group,
                    ",",
                ),
            )
        } else {
            descriptors.add(FoldingDescriptor(element.node, prefixRange, group, prefix + "("))
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(indexTextRange.endOffset, declarationTextRange.startOffset),
                    group,
                    ", ",
                ),
            )
        }
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(variableTextRange.endOffset, arrayTextRange.startOffset),
                group,
                if (AdvancedExpressionFoldingSettings.getInstance().state.compactControlFlowSyntaxCollapse) " : " else ") : ",
            ),
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(arrayTextRange.endOffset, declarationTextRange.endOffset),
                group,
                ") {\n",
            ),
        )
        return descriptors.toTypedArray()
    }
}

