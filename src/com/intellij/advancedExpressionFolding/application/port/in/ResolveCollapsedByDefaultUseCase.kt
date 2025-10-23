package com.intellij.advancedExpressionFolding.application.port.`in`

import com.intellij.lang.ASTNode

/**
 * Driving port that decides whether a fold should be collapsed by default.
 */
fun interface ResolveCollapsedByDefaultUseCase {
    fun isCollapsedByDefault(astNode: ASTNode): Boolean
}
