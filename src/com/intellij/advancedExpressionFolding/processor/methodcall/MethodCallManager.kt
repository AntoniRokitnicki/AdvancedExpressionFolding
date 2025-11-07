package com.intellij.advancedExpressionFolding.processor.methodcall

import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.advancedExpressionFolding.processor.methodcall.math.MathMethodCallRegistrar

class MethodCallManager {
  companion object {
    private val EP_NAME = ExtensionPointName.create<AbstractMethodCall>(
      "com.github.advanced-java-folding2.methodCallFolding"
    )

    private val mathMethodCalls: List<AbstractMethodCall> by lazy { MathMethodCallRegistrar.methodCalls }

    val methodCalls: List<AbstractMethodCall>
      get() = EP_NAME.extensionList + mathMethodCalls
  }
}
