package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.expression.Expression

interface NeedsQualifier {
    val Context.qualifierExpr: Expression
        get() = qualifierExprNullable ?: error("qualifierExpression is null is not supported")
}
