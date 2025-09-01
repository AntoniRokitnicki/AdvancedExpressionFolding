package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

class VariableDeclarationImpl(element: PsiElement, textRange: TextRange, isFinal: Boolean) : Expression(element, textRange) {
    private var isFinal: Boolean

    init {
        this.isFinal = isFinal
    }

    fun isFinal(): Boolean {
        return isFinal
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(VariableDeclarationImpl::class.java.getName())
        return arrayOf(FoldingDescriptor(element.getNode(), getTextRange(), group, if (isFinal) "val" else "var"))
    }
}
