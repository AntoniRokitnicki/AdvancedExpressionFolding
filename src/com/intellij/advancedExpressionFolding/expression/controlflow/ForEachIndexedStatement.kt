package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IControlFlowState
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiBlockStatement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiForStatement

class ForEachIndexedStatement(
    private val statement: PsiForStatement,
    textRange: TextRange,
    private val declarationTextRange: TextRange,
    private val indexTextRange: TextRange,
    private val variableTextRange: TextRange,
    private val arrayTextRange: TextRange,
    private val varSyntax: Boolean,
    private val isFinal: Boolean,
) : Expression(statement, textRange), IControlFlowState by AdvancedExpressionFoldingSettings.State()() {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ForEachIndexedStatement::class.java.name)
        val prefixRange = TextRange.create(textRange.startOffset, textRange.startOffset + 1)
        var prefix = document.getText(prefixRange)
        if (compactControlFlowSyntaxCollapse && prefix == "(") {
            prefix = ""
        }
        if (varSyntax) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, indexTextRange.startOffset),
                group,
                prefix + (if (isFinal) "val" else "var") + " ("
            )
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(indexTextRange.endOffset, variableTextRange.startOffset - 1),
                group,
                ","
            )
        } else {
            descriptors += FoldingDescriptor(element.node, prefixRange, group, prefix + "(")
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(indexTextRange.endOffset, declarationTextRange.startOffset),
                group,
                ", "
            )
        }
        val middlePlaceholder = if (compactControlFlowSyntaxCollapse) " : " else ") : "
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(variableTextRange.endOffset, arrayTextRange.startOffset),
            group,
            middlePlaceholder
        )
        val placeholder = closingPlaceholder(document)
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(arrayTextRange.endOffset, declarationTextRange.endOffset),
            group,
            placeholder
        )
        return descriptors.toTypedArray()
    }

    private fun closingPlaceholder(document: Document): String {
        val body = statement.body as? PsiBlockStatement ?: return ") {\n"
        val lBrace = body.codeBlock.lBrace ?: return ") {\n"
        val rParenth = statement.rParenth ?: return ") {\n"
        val start = rParenth.textRange.endOffset
        val end = lBrace.textRange.startOffset
        if (end <= start) {
            return ") {\n"
        }
        val between = document.getText(TextRange.create(start, end))
        return if (between.contains('\n')) {
            ")${between}{\n"
        } else {
            ") {\n"
        }
    }
}
