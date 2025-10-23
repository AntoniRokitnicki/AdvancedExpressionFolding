package com.intellij.advancedExpressionFolding.application.port.output

import com.intellij.advancedExpressionFolding.domain.model.FoldingGroup
import com.intellij.openapi.editor.Editor

interface FoldingGroupRepository {
    fun load(editor: Editor): List<FoldingGroup>
}
