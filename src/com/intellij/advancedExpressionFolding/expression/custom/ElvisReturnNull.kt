package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

/**
<fold text=''>Expression expression = </fold>ForStatementExpressionExt.getForStatementExpression((PsiForStatement) element, document)<fold text=' ?: return null'>;
if (expression == null) {
   return null;
}</fold>
 */
class ElvisReturnNull(
    element: PsiElement, textRange: TextRange,
    private val declaration: PsiElement, private val declarationRange: TextRange,
    private val letElement: PsiElement, private val letRange: TextRange, val foldVariable: Boolean,
        ) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document,
                                     parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        if (foldVariable) {
            descriptors.add(
                FoldingDescriptor(declaration.node, declarationRange,
                    FoldingGroup.newGroup(ElvisReturnNull::class.java.name), ""))
        }
        descriptors.add(
                FoldingDescriptor(letElement.node, letRange,
                        FoldingGroup.newGroup(ElvisReturnNull::class.java.name), " ?: return null"))
        return descriptors.toTypedArray()
    }

}
