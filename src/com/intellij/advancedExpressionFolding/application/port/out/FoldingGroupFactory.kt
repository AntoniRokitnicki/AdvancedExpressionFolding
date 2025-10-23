package com.intellij.advancedExpressionFolding.application.port.out

import com.intellij.advancedExpressionFolding.domain.model.FoldingGroup
import com.intellij.openapi.editor.Editor

interface FoldingGroupFactory {
    fun create(editor: Editor): FoldingGroup
}
