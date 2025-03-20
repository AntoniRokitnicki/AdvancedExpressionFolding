package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.openapi.extensions.ExtensionPointName

class MethodCallManager {
  companion object {
    private val EP_NAME = ExtensionPointName.create<AbstractMethodCall>(
      "com.github.advanced-java-folding2.advancedExpressionFolding2.methodCall"
    )

    val methodCalls: List<AbstractMethodCall>
      get() = EP_NAME.extensionList
  }
}