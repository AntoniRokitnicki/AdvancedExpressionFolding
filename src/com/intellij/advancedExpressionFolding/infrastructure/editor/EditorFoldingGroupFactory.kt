package com.intellij.advancedExpressionFolding.infrastructure.editor

import com.intellij.advancedExpressionFolding.application.port.out.FoldingGroupFactory
import com.intellij.advancedExpressionFolding.domain.model.FoldingGroup
import com.intellij.advancedExpressionFolding.domain.model.FoldingRegionPort
import com.intellij.advancedExpressionFolding.isAdvancedExpressionFoldingGroup
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion

class EditorFoldingGroupFactory : FoldingGroupFactory {
    override fun create(editor: Editor): FoldingGroup {
        val ports = editor.foldingModel.allFoldRegions.map(::EditorFoldingRegionPort)
        return FoldingGroup(ports)
    }

    private class EditorFoldingRegionPort(
        private val delegate: FoldRegion
    ) : FoldingRegionPort {
        override val isManagedByPlugin: Boolean
            get() = delegate.isAdvancedExpressionFoldingGroup

        override fun collapse() {
            delegate.isExpanded = false
        }

        override fun expand() {
            delegate.isExpanded = true
        }
    }
}
