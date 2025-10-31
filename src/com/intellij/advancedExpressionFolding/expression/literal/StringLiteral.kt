package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression

class StringLiteral(
    element: PsiElement,
    textRange: TextRange,
    val string: String
) : Expression(element, textRange), CharSequenceLiteral {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val settings = AdvancedExpressionFoldingSettings.getInstance().state
        if (!settings.stringEscapesVisualizeNewlines && !isTextBlockLiteral() && string.any { it == '\n' || it == '\r' }) {
            return false
        }
        return document.getText(textRange) != placeholder(settings)
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(StringLiteral::class.java.name)
        val descriptors = arrayOf(
            FoldingDescriptor(element.node, element.textRange, group, placeholder())
        )
        return descriptors
    }

    private fun placeholder(settings: AdvancedExpressionFoldingSettings.State = AdvancedExpressionFoldingSettings.getInstance().state): String {
        return if (shouldVisualize(settings)) {
            "\"${StringEscapeVisualizer.render(string)}\""
        } else {
            "\"$string\""
        }
    }
    private fun shouldVisualize(settings: AdvancedExpressionFoldingSettings.State): Boolean {
        if (!settings.stringEscapesVisualizeNewlines) {
            return false
        }
        return !isTextBlockLiteral()
    }

    private fun isTextBlockLiteral(): Boolean {
        val literal = element
        return literal is PsiLiteralExpression && literal.isTextBlock
    }

}
