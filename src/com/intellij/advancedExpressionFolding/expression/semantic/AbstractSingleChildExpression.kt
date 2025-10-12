package com.intellij.advancedExpressionFolding.expression.semantic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.group
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class AbstractSingleChildExpression(
    element: PsiElement,
    textRange: TextRange,
    private val text: String,
    private val child: Expression?,
) : Expression(element, textRange) {

    /**
     * some Expressions like
     * [com.intellij.advancedExpressionFolding.expression.ArrayLiteral.supportsFoldRegions]
     * [com.intellij.advancedExpressionFolding.expression.ArrayLiteral.buildFoldRegions]
     * are using this offset
     */
    override val textRange: TextRange
        get() = element.textRange

    protected var group: FoldingGroup? = null

    //TODO: support that in "-folded.java"
    var ignored: Boolean = false

    override fun supportsFoldRegions(
        document: Document,
        parent: Expression?
    ): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        if (ignored) {
            return emptyArray()
        }
        group = parent.asInstance<AbstractSingleChildExpression>()?.group
            ?: group()
        val folding = FoldingDescriptor(
            element.node,
            super.textRange,
            group,
            text,
        )
        val array = arrayOf(folding)
        return child?.takeIf { it.supportsFoldRegions(document, this) }
            ?.let { array + it.buildFoldRegions(it.element, document, this) }
            ?: array
    }

    fun group(): FoldingGroup = this::class.group()

}
