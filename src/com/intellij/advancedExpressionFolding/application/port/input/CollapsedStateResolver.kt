package com.intellij.advancedExpressionFolding.application.port.input

import com.intellij.lang.ASTNode

fun interface CollapsedStateResolver {
    fun isCollapsedByDefault(astNode: ASTNode): Boolean
}
