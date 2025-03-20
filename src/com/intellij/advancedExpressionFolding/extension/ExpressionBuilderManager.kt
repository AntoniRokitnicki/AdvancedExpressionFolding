package com.intellij.advancedExpressionFolding.extension

import com.intellij.openapi.extensions.ExtensionPointName

class ExpressionBuilderManager {
  companion object {
    private val EP_NAME = ExtensionPointName.create<BuildExpression<*>>(
      "com.github.advanced-java-folding2.expressionBuilder"
    )

    val expressionBuilders: List<BuildExpression<*>>
      get() = EP_NAME.extensionList
  }
}