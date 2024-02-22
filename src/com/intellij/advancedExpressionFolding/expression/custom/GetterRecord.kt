package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.IGetter
import com.intellij.advancedExpressionFolding.extension.plus
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.*

class GetterRecord(element: PsiElement, textRange: TextRange, private var getterTextRange: TextRange, private val `object`: Expression?, private var name: String) : Expression(element, textRange), IGetter {
    override fun supportsFoldRegions(document: Document,
                                     parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
                FoldingDescriptor(element.node, getGetterTextRange(),
                        FoldingGroup.newGroup(GetterRecord::class.java.name), getName()))
        if (`object` != null && `object`.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *`object`.buildFoldRegions(`object`.element, document, this))
        }
        return descriptors.toArray(FoldingDescriptor.EMPTY)
    }

    override fun getName(): String {
        return name
    }

    override fun getGetterTextRange(): TextRange {
        return getterTextRange
    }

    override fun makeFieldShift() {
        name = "<<"
        getterTextRange += (-1..0)
    }

    override fun getObject(): Expression? = `object`
}
