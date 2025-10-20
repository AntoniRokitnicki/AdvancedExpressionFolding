package com.intellij.advancedExpressionFolding.processor.methodcall

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.LinkedHashSet

class MethodCallBuilder {
    private var canExecute: () -> Boolean = { true }
    private val methodNames = LinkedHashSet<MethodName>()
    private val classNames = LinkedHashSet<ClassName>()

    private var onNoArguments: ((PsiMethodCallExpression, Context) -> Expression?)? = null
    private var onSingleArgument: ((PsiMethodCallExpression, Context, PsiExpression, Expression) -> Expression?)? = null
    private var onTwoArguments: ((PsiMethodCallExpression, Context, PsiExpression, PsiExpression, Expression, Expression) -> Expression?)? = null
    private var onManyArguments: ((PsiMethodCallExpression, Context, Array<PsiExpression>) -> Expression?)? = null
    private var onAnyArguments: ((PsiMethodCallExpression, Context, Array<PsiExpression>) -> Expression?)? = null

    fun enableWhen(predicate: () -> Boolean) {
        canExecute = predicate
    }

    fun methods(vararg names: MethodName) {
        methodNames.addAll(names)
    }

    fun methods(names: Iterable<MethodName>) {
        methodNames.addAll(names)
    }

    fun classes(vararg names: ClassName) {
        classNames.addAll(names)
    }

    fun classes(names: Iterable<ClassName>) {
        classNames.addAll(names)
    }

    fun onNoArguments(handler: (PsiMethodCallExpression, Context) -> Expression?) {
        onNoArguments = handler
    }

    fun onSingleArgument(
        handler: (PsiMethodCallExpression, Context, PsiExpression, Expression) -> Expression?
    ) {
        onSingleArgument = handler
    }

    fun onTwoArguments(
        handler: (PsiMethodCallExpression, Context, PsiExpression, PsiExpression, Expression, Expression) -> Expression?
    ) {
        onTwoArguments = handler
    }

    fun onManyArguments(
        handler: (PsiMethodCallExpression, Context, Array<PsiExpression>) -> Expression?
    ) {
        onManyArguments = handler
    }

    fun onAnyArguments(
        handler: (PsiMethodCallExpression, Context, Array<PsiExpression>) -> Expression?
    ) {
        onAnyArguments = handler
    }

    fun build(): MethodCallConfiguration = MethodCallConfiguration(
        canExecute = canExecute,
        methodNames = methodNames.toList(),
        classNames = classNames.toList(),
        onNoArguments = onNoArguments,
        onSingleArgument = onSingleArgument,
        onTwoArguments = onTwoArguments,
        onManyArguments = onManyArguments,
        onAnyArguments = onAnyArguments
    )
}

data class MethodCallConfiguration(
    val canExecute: () -> Boolean,
    val methodNames: List<MethodName>,
    val classNames: List<ClassName>,
    val onNoArguments: ((PsiMethodCallExpression, Context) -> Expression?)?,
    val onSingleArgument: ((PsiMethodCallExpression, Context, PsiExpression, Expression) -> Expression?)?,
    val onTwoArguments: ((PsiMethodCallExpression, Context, PsiExpression, PsiExpression, Expression, Expression) -> Expression?)?,
    val onManyArguments: ((PsiMethodCallExpression, Context, Array<PsiExpression>) -> Expression?)?,
    val onAnyArguments: ((PsiMethodCallExpression, Context, Array<PsiExpression>) -> Expression?)?
)

abstract class DslMethodCall : AbstractMethodCall() {
    private val configuration: MethodCallConfiguration by lazy(LazyThreadSafetyMode.NONE) {
        MethodCallBuilder().also { builder ->
            configure(builder)
        }.build()
    }

    protected open fun configure(builder: MethodCallBuilder) {
        // subclasses will populate the builder
    }

    override fun canExecute(): Boolean = configuration.canExecute()

    override val methodNames: List<MethodName> by lazy(LazyThreadSafetyMode.NONE) {
        configuration.methodNames
    }

    override val classNames: List<ClassName> by lazy(LazyThreadSafetyMode.NONE) {
        configuration.classNames
    }

    override fun onAnyArguments(
        element: PsiMethodCallExpression,
        context: Context,
        expressions: Array<PsiExpression>
    ): Expression? {
        return configuration.onAnyArguments?.invoke(element, context, expressions)
            ?: super.onAnyArguments(element, context, expressions)
    }

    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? {
        return configuration.onNoArguments?.invoke(element, context)
            ?: super.onNoArguments(element, context)
    }

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        return configuration.onSingleArgument?.invoke(element, context, argument, argumentExpression)
            ?: super.onSingleArgument(element, context, argument, argumentExpression)
    }

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? {
        return configuration.onTwoArguments?.invoke(element, context, a1, a2, a1Expression, a2Expression)
            ?: super.onTwoArguments(element, context, a1, a2, a1Expression, a2Expression)
    }

    override fun onManyArguments(
        element: PsiMethodCallExpression,
        context: Context,
        expressions: Array<PsiExpression>
    ): Expression? {
        return configuration.onManyArguments?.invoke(element, context, expressions)
            ?: super.onManyArguments(element, context, expressions)
    }
}
