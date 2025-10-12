package com.intellij.advancedExpressionFolding.expression.property

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.PsiElement

private val IGetter.expression: Expression
    get() = this as Expression

private val IGetter.foldingGroup: FoldingGroup
    get() = FoldingGroup.newGroup(javaClass.name)

internal fun IGetter.buildGetterFoldRegions(
    element: PsiElement,
    document: Document
): Array<FoldingDescriptor> {
    val descriptors = mutableListOf<FoldingDescriptor>()
    descriptors += FoldingDescriptor(
        element.node,
        getterTextRange,
        foldingGroup,
        name
    )
    `object`?.takeIf { it.supportsFoldRegions(document, expression) }?.let { obj ->
        descriptors += obj.buildFoldRegions(obj.element, document, expression).toList()
    }
    return descriptors.toTypedArray()
}
