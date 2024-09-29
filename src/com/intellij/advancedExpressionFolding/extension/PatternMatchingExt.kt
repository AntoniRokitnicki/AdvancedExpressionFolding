package com.intellij.advancedExpressionFolding.extension


import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.NlsSafe
import com.intellij.psi.*

object PatternMatchingExt : BaseExtension() {

    @JvmStatic
    fun foldInstanceOf(
        instanceOfExpr: PsiInstanceOfExpression,
        document: Document,
        descriptors: ArrayList<FoldingDescriptor>,
        element: PsiIfStatement
    ) {
        element.thenBranch.asInstance<PsiBlockStatement>()?.codeBlock?.statements?.firstOrNull()
            .asInstance<PsiDeclarationStatement>()?.run {
                val localVariable = declaredElements.firstOrNull().asInstance<PsiLocalVariable>() ?: return
                // TODO: check type and ==var from instanceof, simple var?, cast?, maybe instanceof alreayd have a var?

                appendDescriptors(instanceOfExpr, localVariable.name, element, document, descriptors)
            }
    }

    private fun PsiDeclarationStatement.appendDescriptors(
        instanceOfExpr: PsiInstanceOfExpression,
        varName: @NlsSafe String,
        element: PsiIfStatement,
        document: Document,
        descriptors: ArrayList<FoldingDescriptor>,
    ) {
        val list = exprList()
        list += instanceOfExpr.nextSibling?.expr(" $varName)")
        list += exprHide()
        list += nextWhiteSpace().exprHide()
        asBuildFoldRegions(list, element, document, descriptors)
    }

    private fun asBuildFoldRegions(
        list: MutableList<Expression?>,
        element: PsiIfStatement,
        document: Document,
        descriptors: ArrayList<FoldingDescriptor>
    ) {
        val expression = list.exprWrap(element)
        expression?.buildFoldRegions(element, document, expression)?.let(descriptors::addAll)
    }

}

