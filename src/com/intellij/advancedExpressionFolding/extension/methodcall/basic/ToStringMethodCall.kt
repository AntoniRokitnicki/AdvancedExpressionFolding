package com.intellij.advancedExpressionFolding.extension.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.*
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement

class ToStringMethodCall : AbstractMethodCall(), NeedsQualifier {
    override val methodNames by lazy { listOf("toString") }
    
    override val classNames by lazy { listOf(
        "java.lang.Object",
        "java.lang.String",
        "java.lang.StringBuilder",
        "java.math.BigDecimal",
        "java.math.BigInteger",
        "java.lang.Long",
        "java.lang.Integer",
        "java.lang.Float",
        "java.lang.Double",
        "java.lang.Character"
    ) }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? {
        val qualifier = context.qualifierExprNullable ?: return null
        
        return when (qualifier) {
            is Append -> Append(
                element, 
                element.textRange, 
                qualifier.operands, 
                element.parent is PsiStatement
            )
            is StringLiteral -> StringLiteral(
                element, 
                element.textRange, 
                qualifier.string
            )
            is NumberLiteral -> NumberLiteral(
                element, 
                element.textRange, 
                qualifier.numberTextRange, 
                qualifier.number, 
                true
            )
            is Variable -> Variable(
                element, 
                element.textRange, 
                qualifier.textRange, 
                qualifier.name, 
                true
            )
            else -> null
        }
    }
}
