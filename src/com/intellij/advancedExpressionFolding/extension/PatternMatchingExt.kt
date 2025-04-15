package com.intellij.advancedExpressionFolding.extension


import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.*

object PatternMatchingExt : BaseExtension() {

    @JvmStatic
    fun PsiIfStatement.foldInstanceOf(
        instanceOfExpr: PsiInstanceOfExpression,
        document: Document,
        descriptors: ArrayList<FoldingDescriptor>,
    ) {
        val element: PsiIfStatement = this
        thenBranch.asInstance<PsiBlockStatement>()?.codeBlock?.statements?.firstOrNull()
            .asInstance<PsiDeclarationStatement>()?.run {
                val localVariable = declaredElements.firstOrNull().asInstance<PsiLocalVariable>() ?: return
                validateInstanceOf(instanceOfExpr, localVariable, element) ?: return
                appendDescriptors(instanceOfExpr, localVariable.name, element, document, descriptors)
            }
    }

    private fun PsiIfStatement.validateInstanceOf(
        instanceOfExpr: PsiInstanceOfExpression,
        localVariable: PsiLocalVariable,
        element: PsiIfStatement
    ): Any? {
        // Check if the types match
        val instanceOfType = instanceOfExpr.checkType?.type
        val localVariableType = localVariable.type
        if (instanceOfType != localVariableType) return null

        // Check if the variable from instanceof is used
        val operand = instanceOfExpr.operand
        if (operand !is PsiReferenceExpression) return null
        val referencedVar = operand.resolve() as? PsiVariable ?: return null

        // Check if the local variable is a simple assignment or a cast
        val initializer = localVariable.initializer
        if (initializer !is PsiTypeCastExpression) {
            // Simple assignment
            if (initializer?.text != referencedVar.name) return null
        } else {
            // Cast assignment
            val castOperand = initializer.operand
            if (castOperand?.text != referencedVar.name) return null
        }

        // Check for poisoned state (compiler errors)
        if (element.hasErrorElement() || instanceOfExpr.hasErrorElement() || localVariable.hasErrorElement()) {
            return null
        }

        return this
    }

    // Helper extension function to check for error elements
    private fun PsiElement.hasErrorElement(): Boolean {
        return this.children.any { it is PsiErrorElement } ||
                (this as? PsiErrorElement) != null
    }

    private fun PsiDeclarationStatement.appendDescriptors(
        instanceOfExpr: PsiInstanceOfExpression,
        varName: String,
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

