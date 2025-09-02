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
    getterTextRange: TextRange,
    obj: Expression?,
    name: String
) : Expression(element, textRange), IGetter {
    private var name: String = name
    private var getterTextRange: TextRange = getterTextRange
    private var obj: Expression? = obj

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = java.util.ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                getGetterTextRange(),
                FoldingGroup.newGroup(Getter::class.java.getName()),
                getName()
            )
        )
        if (obj != null && obj!!.supportsFoldRegions(document, this)) {
            java.util.Collections.addAll(descriptors, *obj!!.buildFoldRegions(obj!!.getElement(), document, this))
        }
        return descriptors.toTypedArray()
    }

    override fun getName(): String {
        return name
    }

    override fun getGetterTextRange(): TextRange {
        return getterTextRange
    }

    override fun getObject(): Expression? {
        return obj
    }
}

