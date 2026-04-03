package com.intellij.advancedExpressionFolding.processor.methodcall.math

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.advanced.Cbrt
import com.intellij.advancedExpressionFolding.expression.math.advanced.Ceil
import com.intellij.advancedExpressionFolding.expression.math.advanced.Exp
import com.intellij.advancedExpressionFolding.expression.math.advanced.Floor
import com.intellij.advancedExpressionFolding.expression.math.advanced.Log
import com.intellij.advancedExpressionFolding.expression.math.advanced.Log10
import com.intellij.advancedExpressionFolding.expression.math.advanced.Rint
import com.intellij.advancedExpressionFolding.expression.math.advanced.Round
import com.intellij.advancedExpressionFolding.expression.math.advanced.Sqrt
import com.intellij.advancedExpressionFolding.expression.math.advanced.Ulp
import com.intellij.advancedExpressionFolding.expression.math.basic.Abs
import com.intellij.advancedExpressionFolding.expression.math.trig.Acos
import com.intellij.advancedExpressionFolding.expression.math.trig.Asin
import com.intellij.advancedExpressionFolding.expression.math.trig.Atan
import com.intellij.advancedExpressionFolding.expression.math.trig.Cos
import com.intellij.advancedExpressionFolding.expression.math.trig.Cosh
import com.intellij.advancedExpressionFolding.expression.math.trig.Sin
import com.intellij.advancedExpressionFolding.expression.math.trig.Sinh
import com.intellij.advancedExpressionFolding.expression.math.trig.Tan
import com.intellij.advancedExpressionFolding.expression.math.trig.Tanh
import com.intellij.advancedExpressionFolding.expression.math.trig.ToDegrees
import com.intellij.advancedExpressionFolding.expression.math.trig.ToRadians
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

private typealias SingleArgMathBuilder = (
    PsiMethodCallExpression,
    Context,
    PsiExpression,
    Expression,
) -> Expression?

internal object MathMethodCallRegistrar {
    val methodCalls: List<AbstractMethodCall> by lazy {
        singleArgumentCalls.map { (methodNames, builder) ->
            SingleArgMathMethodCall(methodNames, builder)
        }
    }

    private val singleArgumentCalls: Map<List<String>, SingleArgMathBuilder> = mapOf(
        listOf("abs") to { element, _, _, argumentExpression ->
            Abs(element, element.textRange, listOf(argumentExpression))
        },
        listOf("acos") to { element, _, _, argumentExpression ->
            Acos(element, element.textRange, listOf(argumentExpression))
        },
        listOf("asin") to { element, _, _, argumentExpression ->
            Asin(element, element.textRange, listOf(argumentExpression))
        },
        listOf("atan") to { element, _, _, argumentExpression ->
            Atan(element, element.textRange, listOf(argumentExpression))
        },
        listOf("cbrt") to { element, _, _, argumentExpression ->
            Cbrt(element, element.textRange, listOf(argumentExpression))
        },
        listOf("ceil") to { element, _, _, argumentExpression ->
            Ceil(element, element.textRange, listOf(argumentExpression))
        },
        listOf("cos") to { element, _, _, argumentExpression ->
            Cos(element, element.textRange, listOf(argumentExpression))
        },
        listOf("cosh") to { element, _, _, argumentExpression ->
            Cosh(element, element.textRange, listOf(argumentExpression))
        },
        listOf("exp") to { element, _, _, argumentExpression ->
            Exp(element, element.textRange, listOf(argumentExpression))
        },
        listOf("floor") to { element, _, _, argumentExpression ->
            Floor(element, element.textRange, listOf(argumentExpression))
        },
        listOf("log") to { element, _, _, argumentExpression ->
            Log(element, element.textRange, listOf(argumentExpression))
        },
        listOf("log10") to { element, _, _, argumentExpression ->
            Log10(element, element.textRange, listOf(argumentExpression))
        },
        listOf("rint") to { element, _, _, argumentExpression ->
            Rint(element, element.textRange, listOf(argumentExpression))
        },
        listOf("round") to { element, _, _, argumentExpression ->
            Round(element, element.textRange, listOf(argumentExpression))
        },
        listOf("sin") to { element, _, _, argumentExpression ->
            Sin(element, element.textRange, listOf(argumentExpression))
        },
        listOf("sinh") to { element, _, _, argumentExpression ->
            Sinh(element, element.textRange, listOf(argumentExpression))
        },
        listOf("sqrt") to { element, _, _, argumentExpression ->
            Sqrt(element, element.textRange, listOf(argumentExpression))
        },
        listOf("tan") to { element, _, _, argumentExpression ->
            Tan(element, element.textRange, listOf(argumentExpression))
        },
        listOf("tanh") to { element, _, _, argumentExpression ->
            Tanh(element, element.textRange, listOf(argumentExpression))
        },
        listOf("toDegrees") to { element, _, _, argumentExpression ->
            ToDegrees(element, element.textRange, listOf(argumentExpression))
        },
        listOf("toRadians") to { element, _, _, argumentExpression ->
            ToRadians(element, element.textRange, listOf(argumentExpression))
        },
        listOf("ulp") to { element, _, _, argumentExpression ->
            Ulp(element, element.textRange, listOf(argumentExpression))
        },
    )
}
