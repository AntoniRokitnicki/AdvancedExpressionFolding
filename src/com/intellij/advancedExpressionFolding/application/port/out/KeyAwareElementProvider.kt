package com.intellij.advancedExpressionFolding.application.port.out

import com.intellij.advancedExpressionFolding.domain.model.KeyAwareElement
import com.intellij.openapi.editor.Editor

interface KeyAwareElementProvider {
    fun provide(editor: Editor): KeyAwareElement?
}
