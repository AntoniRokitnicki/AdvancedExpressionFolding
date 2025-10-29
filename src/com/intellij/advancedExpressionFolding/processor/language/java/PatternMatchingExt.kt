package com.intellij.advancedExpressionFolding.processor.language.java


import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.*
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.TypeConversionUtil

object PatternMatchingExt : BaseExtension() {

    @JvmStatic
    fun foldInstanceOf(
        ifStatement: PsiIfStatement,
        instanceOfExpr: PsiInstanceOfExpression,
        document: Document,
        descriptors: ArrayList<FoldingDescriptor>,
    ) {
        ifStatement.run {
            val element: PsiIfStatement = this
            val blockStatement = thenBranch.asInstance<PsiBlockStatement>() ?: return
            val codeBlock = blockStatement.codeBlock
            val declarationStatement = codeBlock.statements.firstOrNull().asInstance<PsiDeclarationStatement>() ?: return
            val localVariable = declarationStatement.declaredElements.firstOrNull().asInstance<PsiLocalVariable>() ?: return
            validateInstanceOf(instanceOfExpr, localVariable, element) ?: return
            if (tryRecordPattern(instanceOfExpr, localVariable, codeBlock, element, declarationStatement, document, descriptors)) {
                return
            }
            val variableName = localVariable.name ?: return
            declarationStatement.appendDescriptors(instanceOfExpr, variableName, element, document, descriptors)
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

    private fun PsiIfStatement.tryRecordPattern(
        instanceOfExpr: PsiInstanceOfExpression,
        localVariable: PsiLocalVariable,
        codeBlock: PsiCodeBlock,
        element: PsiIfStatement,
        declarationStatement: PsiDeclarationStatement,
        document: Document,
        descriptors: ArrayList<FoldingDescriptor>,
    ): Boolean {
        val info = collectRecordPatternInfo(localVariable, codeBlock, declarationStatement) ?: return false
        appendRecordPatternDescriptors(instanceOfExpr, element, document, descriptors, info)
        return true
    }

    private data class RecordPatternInfo(
        val patternText: String,
        val recordDeclaration: PsiDeclarationStatement,
        val componentDeclarations: List<PsiDeclarationStatement>,
    )

    private fun collectRecordPatternInfo(
        localVariable: PsiLocalVariable,
        codeBlock: PsiCodeBlock,
        declarationStatement: PsiDeclarationStatement,
    ): RecordPatternInfo? {
        val recordType = localVariable.type as? PsiClassType ?: return null
        val recordClass = recordType.resolve()?.takeIf { it.isRecord } ?: return null
        val components = recordClass.recordComponents
        if (components.isEmpty()) {
            return null
        }
        val statements = codeBlock.statements
        if (statements.size <= 1) {
            return null
        }

        val componentDeclarations = mutableListOf<PsiDeclarationStatement>()
        val patternParts = mutableListOf<String>()

        var index = 0
        for (statement in statements.drop(1)) {
            val declaration = statement.asInstance<PsiDeclarationStatement>() ?: break
            val componentVariable = declaration.declaredElements.singleOrNull().asInstance<PsiLocalVariable>() ?: break
            val bindingText = componentVariable.buildPatternBindingText() ?: return null
            val methodCall = componentVariable.initializer.asInstance<PsiMethodCallExpression>() ?: break
            val component = components.getOrNull(index) ?: break
            if (!methodCall.isComponentAccessor(localVariable, recordClass, component, index, componentVariable)) {
                break
            }
            componentDeclarations += declaration
            patternParts += bindingText
            index++
            if (index == components.size) {
                break
            }
        }

        if (index != components.size) {
            return null
        }

        val allowedElements = (componentDeclarations + declarationStatement).toSet()
        val references = ReferencesSearch.search(localVariable, LocalSearchScope(codeBlock)).findAll()
        if (references.any { reference ->
                allowedElements.none { PsiTreeUtil.isAncestor(it, reference.element, false) }
            }) {
            return null
        }

        val patternText = patternParts.joinToString(", ")
        if (patternText.isBlank()) {
            return null
        }

        return RecordPatternInfo(
            patternText = patternText,
            recordDeclaration = declarationStatement,
            componentDeclarations = componentDeclarations,
        )
    }

    private fun PsiMethodCallExpression.isComponentAccessor(
        recordVariable: PsiLocalVariable,
        recordClass: PsiClass,
        component: PsiRecordComponent,
        componentIndex: Int,
        targetVariable: PsiLocalVariable,
    ): Boolean {
        if (argumentList.expressions.isNotEmpty()) {
            return false
        }
        val qualifier = methodExpression.qualifierExpression.asInstance<PsiReferenceExpression>() ?: return false
        if (qualifier.resolve() != recordVariable) {
            return false
        }
        val resolvedMethod = resolveMethod() ?: return false
        if (resolvedMethod.parameterList.parametersCount != 0) {
            return false
        }
        if (resolvedMethod.containingClass?.isEquivalentTo(recordClass) != true) {
            return false
        }
        val methodName = resolvedMethod.name
        val expectedName = component.name
        val matchesName = methodName == expectedName || methodName == "component${componentIndex + 1}"
        if (!matchesName) {
            return false
        }
        val returnType = resolvedMethod.returnType ?: return false
        val componentType = component.type
        if (!TypeConversionUtil.isAssignable(componentType, returnType)) {
            return false
        }
        if (!TypeConversionUtil.isAssignable(targetVariable.type, returnType)) {
            return false
        }
        return true
    }

    private fun PsiLocalVariable.buildPatternBindingText(): String? {
        val name = name ?: return null
        val modifier = modifierList?.text?.trim()?.takeIf { it.isNotEmpty() }?.let { "$it " } ?: ""
        val typeText = typeElement?.text ?: type.presentableText
        return "$modifier$typeText $name"
    }

    private fun appendRecordPatternDescriptors(
        instanceOfExpr: PsiInstanceOfExpression,
        element: PsiIfStatement,
        document: Document,
        descriptors: ArrayList<FoldingDescriptor>,
        info: RecordPatternInfo,
    ) {
        val list = exprList()
        val checkType = instanceOfExpr.checkType
        if (checkType != null) {
            list += checkType.expr("${checkType.text}(${info.patternText})")
        }
        list += instanceOfExpr.nextSibling?.expr("))")
        list += info.recordDeclaration.exprHide()
        list += info.recordDeclaration.nextWhiteSpace().exprHide()
        info.componentDeclarations.forEach { declaration ->
            list += declaration.exprHide()
            list += declaration.nextWhiteSpace().exprHide()
        }
        asBuildFoldRegions(list, element, document, descriptors)
    }

    private fun asBuildFoldRegions(
        list: MutableList<Expression?>,
        element: PsiIfStatement,
        document: Document,
        descriptors: ArrayList<FoldingDescriptor>
    ) {
        val expression = list.exprWrap(element, group())
        expression?.buildFoldRegions(element, document, expression)?.let(descriptors::addAll)
    }

}

