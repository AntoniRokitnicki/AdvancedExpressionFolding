package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.openapi.extensions.ExtensionPointName

class ExpressionBuilderManager {
  companion object {
    private val EP_NAME = ExtensionPointName.Companion.create<BuildExpression<*>>(
      "com.github.advanced-java-folding2.expressionBuilder"
    )

    private val BUILTIN_BUILDERS: List<BuildExpression<*>> =
      commonExpressionBuilders +
        controlFlowExpressionBuilders +
        declarationExpressionBuilders +
        lexicalExpressionBuilders

    val expressionBuilders: List<BuildExpression<*>>
      get() = BUILTIN_BUILDERS + EP_NAME.extensionList
  }
}
