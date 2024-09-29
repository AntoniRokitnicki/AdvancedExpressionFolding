package com.intellij.advancedExpressionFolding.extension


import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.*

object PatternMatchingExt : BaseExtension() {


    @JvmStatic
    fun foldInstanceOf(
        instanceOfExpr: PsiInstanceOfExpression,
        document: Document,
        group: FoldingGroup,
        descriptors: ArrayList<FoldingDescriptor>,
        element: PsiIfStatement
    ) {
        element.thenBranch.asInstance<PsiBlockStatement>()?.codeBlock?.statements?.firstOrNull()
            .asInstance<PsiDeclarationStatement>()?.let {
                val list = exprList()
                val varName = it.declaredElements.firstOrNull().asInstance<PsiLocalVariable>()?.name
                list += instanceOfExpr?.nextSibling?.expr(" $varName)")
                list += it.exprHide()
                list += it.nextWhiteSpace().exprHide()
                val expression = list.exprWrap(element)
                expression?.buildFoldRegions(element, document, expression)?.let {
                    descriptors.addAll(it)
                }
            }
    }

}

