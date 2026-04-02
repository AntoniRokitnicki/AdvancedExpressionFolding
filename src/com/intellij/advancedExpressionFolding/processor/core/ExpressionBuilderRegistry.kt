package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.psi.PsiElement

object ExpressionBuilderRegistry {
    private val definitions:
        Map<Class<out BuildExpression<*>>, FunctionalExpressionBuilderDefinition<out PsiElement>> by lazy {
            expressionBuilderDefinitions +
                controlFlowExpressionBuilderDefinitions +
                lexicalExpressionBuilderDefinitions +
                declarationExpressionBuilderDefinitions
        }

    @Suppress("UNCHECKED_CAST")
    fun <T : PsiElement> definition(
        builderClass: Class<out BuildExpression<T>>,
    ): FunctionalExpressionBuilderDefinition<T> {
        return definitions[builderClass] as? FunctionalExpressionBuilderDefinition<T>
            ?: error("No expression builder registered for ${'$'}{builderClass.name}")
    }
}
