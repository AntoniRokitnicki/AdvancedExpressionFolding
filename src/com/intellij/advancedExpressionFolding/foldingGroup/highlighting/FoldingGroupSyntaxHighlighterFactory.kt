package com.intellij.advancedExpressionFolding.foldingGroup.highlighting

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory
import com.intellij.openapi.fileTypes.SyntaxHighlighter

class FoldingGroupSyntaxHighlighterFactory : SingleLazyInstanceSyntaxHighlighterFactory() {
    override fun createHighlighter(): SyntaxHighlighter = FoldingGroupSyntaxHighlighter()
}
