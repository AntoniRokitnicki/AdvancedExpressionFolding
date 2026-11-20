package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.NamedArgumentsCall
import com.intellij.advancedExpressionFolding.expression.semantic.NamedArgumentsCall.NamedArgument
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IExpressionCollapseState
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiClassType
import com.intellij.psi.PsiJavaToken
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiType

/**
 * Folds `person.toBuilder().name("Alice").age(31).build()` into `person.copy(name = "Alice", age = 31)`.
 *
 * Only a clean chain folds: `toBuilder()`, then one-argument calls of builder methods that set a builder field
 * of the same name and return the builder, then `build()` returning the receiver's class.
 */
object ToBuilderCopyExt : IExpressionCollapseState by State()() {

    fun createExpression(build: PsiMethodCallExpression, document: Document): Expression? {
        if (!toBuilderCopy || build.methodExpression.referenceName != "build" || !build.argumentList.isEmpty) {
            return null
        }
        val setters = generateSequence(build.qualifierCall()) { it.qualifierCall() }
            .takeWhile { it.methodExpression.referenceName != "toBuilder" }
            .toList()
            .asReversed()
        val toBuilder = (setters.firstOrNull() ?: return null).qualifierCall() ?: return null
        if (toBuilder.methodExpression.referenceName != "toBuilder" || !toBuilder.argumentList.isEmpty) {
            return null
        }
        val builderClass = toBuilder.resolveMethod()?.returnType.resolveClass() ?: return null
        val targetClass = toBuilder.resolveMethod()?.containingClass ?: return null
        val buildMethod = build.resolveMethod() ?: return null
        if (!builderClass.isSame(buildMethod.containingClass) || !targetClass.isSame(buildMethod.returnType.resolveClass())) {
            return null
        }
        if (!setters.all { it.isFieldSetterOf(builderClass, document) }) {
            return null
        }
        return NamedArgumentsCall(
            element = build,
            openRange = TextRange(toBuilder.dotOffset() ?: return null, toBuilder.textRange.endOffset),
            openText = ".copy(",
            arguments = setters.map { setter ->
                val value = setter.argumentList.expressions.single().textRange
                NamedArgument(
                    name = setter.methodExpression.referenceName!!,
                    prefixRange = TextRange(setter.dotOffset() ?: return null, value.startOffset),
                    suffixRange = TextRange(value.endOffset, setter.textRange.endOffset),
                )
            },
            closeRange = TextRange(build.dotOffset() ?: return null, build.textRange.endOffset),
        )
    }

    private fun PsiMethodCallExpression.isFieldSetterOf(builderClass: PsiClass, document: Document): Boolean {
        val method = resolveMethod() ?: return false
        val value = argumentList.expressions.singleOrNull() ?: return false
        return method.parameterList.parametersCount == 1 &&
            builderClass.isSame(method.containingClass) &&
            builderClass.isSame(method.returnType.resolveClass()) &&
            builderClass.findFieldByName(method.name, false) != null &&
            // shortcut: a concatenation folded into "…$x" also folds the call's parentheses, so it is left unfolded
            !BuildExpressionExt.getAnyExpression(value, document).isOverflow()
    }

    private fun PsiMethodCallExpression.qualifierCall() = methodExpression.qualifierExpression as? PsiMethodCallExpression

    private fun PsiMethodCallExpression.dotOffset() = methodExpression.children
        .firstOrNull { it is PsiJavaToken && it.tokenType == JavaTokenType.DOT }
        ?.textRange?.startOffset

    private fun PsiType?.resolveClass() = (this as? PsiClassType)?.resolve()

    private fun PsiClass.isSame(other: PsiClass?) = manager.areElementsEquivalent(this, other)
}
