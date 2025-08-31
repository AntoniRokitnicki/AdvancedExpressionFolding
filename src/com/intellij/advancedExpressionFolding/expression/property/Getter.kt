package com.intellij.advancedExpressionFolding.expression.property

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Getter(
    element: PsiElement,
    textRange: TextRange,
    private val getterTextRange: TextRange,
    private val obj: Expression?,
    private val name: String
) : Expression(element, textRange), IGetter {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.node,
                getterTextRange,
                FoldingGroup.newGroup(Getter::class.java.name),
                name
            )
        )
        if (obj != null && obj.supportsFoldRegions(document, this)) {
            descriptors.addAll(obj.buildFoldRegions(obj.element, document, this))
        }
        return descriptors.toTypedArray()
    }

    override fun getName(): String = name

    override fun getGetterTextRange(): TextRange = getterTextRange

    override fun getObject(): Expression? = obj
}

