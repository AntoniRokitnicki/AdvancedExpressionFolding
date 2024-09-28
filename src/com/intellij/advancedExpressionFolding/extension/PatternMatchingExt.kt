package com.intellij.advancedExpressionFolding.extension


import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.BuilderShiftExpression
import com.intellij.advancedExpressionFolding.extension.Keys.BUILDER
import com.intellij.advancedExpressionFolding.extension.Keys.isOn
import com.intellij.advancedExpressionFolding.extension.Keys.turnOn
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.NullUtils.hasNull
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.descendantsOfType
import java.util.ArrayList

object PatternMatchingExt : BaseExtension() {


    @JvmStatic
    fun foldInstanceOf(
        instanceOfExpr: PsiInstanceOfExpression,
        document: Document,
        group: FoldingGroup,
        descriptors: ArrayList<FoldingDescriptor>,
        element: PsiElement
    ) {
        val operand = instanceOfExpr.operand ?: return
        val checkType = instanceOfExpr.checkType ?: return

        val parenthesisElement = element.lastChild as? PsiJavaToken
            ?: return

        if (parenthesisElement.tokenType != JavaTokenType.RPARENTH) return

        val startOffset = parenthesisElement.textRange.startOffset
        val endOffset = parenthesisElement.textRange.endOffset

        val placeholder = " a)"
        val foldingDescriptor = FoldingDescriptor(
            parenthesisElement.node,
            TextRange(startOffset, endOffset),
            group,
            placeholder
        )

        descriptors.add(foldingDescriptor)
    }

}

