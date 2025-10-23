package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.psi.PsiAssignmentExpression
import com.intellij.psi.PsiDeclarationStatement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLoopStatement
import com.intellij.psi.PsiModifier
import com.intellij.psi.PsiModifierList
import com.intellij.psi.PsiPostfixExpression
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiVariable
import com.intellij.psi.SyntaxTraverser

object PsiVariableUtil {

    fun calculateIfFinal(element: PsiVariable): Boolean {
        val modifiers: PsiModifierList = element.modifierList ?: return false
        var isFinal = modifiers.hasExplicitModifier(PsiModifier.FINAL)
        if (!isFinal) {
            var body: PsiElement? = when (val parent = element.parent) {
                is PsiDeclarationStatement -> parent.parent
                is PsiLoopStatement -> parent.body
                else -> parent
            }
            if (body is PsiLoopStatement) {
                body = body.body
            }
            val references = SyntaxTraverser.psiTraverser(body)
                .filter { candidate ->
                    when (candidate) {
                        is PsiAssignmentExpression -> {
                            val lExpression = candidate.lExpression
                            lExpression is PsiReferenceExpression && lExpression.isReferenceTo(element)
                        }
                        is PsiPostfixExpression -> {
                            val sign = candidate.operationSign.text
                            (sign == "++" || sign == "--") &&
                                candidate.operand is PsiReferenceExpression &&
                                (candidate.operand as PsiReferenceExpression).isReferenceTo(element)
                        }
                        else -> false
                    }
                }
                .toList()
            if (references.isEmpty()) {
                isFinal = true
            }
        }
        return isFinal
    }
}
