package com.intellij.advancedExpressionFolding.processor.methodcall.dsl

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

private typealias NoArgsHandler =
    DslMethodCall.(PsiMethodCallExpression, Context) -> Expression?

private typealias SingleArgHandler =
    DslMethodCall.(PsiMethodCallExpression, Context, PsiExpression, Expression) -> Expression?

private typealias TwoArgHandler =
    DslMethodCall.(
        PsiMethodCallExpression,
        Context,
        PsiExpression,
        PsiExpression,
        Expression,
        Expression,
    ) -> Expression?

private typealias ManyArgsHandler =
    DslMethodCall.(PsiMethodCallExpression, Context, Array<PsiExpression>) -> Expression?

internal data class MethodCallDefinition(
    val methodNames: List<String>,
    val classNames: List<String>,
    val canExecute: (DslMethodCall.() -> Boolean)?,
    val onNoArguments: NoArgsHandler?,
    val onSingleArgument: SingleArgHandler?,
    val onTwoArguments: TwoArgHandler?,
    val onManyArguments: ManyArgsHandler?,
)

open class MethodCallBuilder {
    private val methodNames = linkedSetOf<String>()
    private val classNames = linkedSetOf<String>()

    private var canExecute: (DslMethodCall.() -> Boolean)? = null
    private var onNoArguments: NoArgsHandler? = null
    private var onSingleArgument: SingleArgHandler? = null
    private var onTwoArguments: TwoArgHandler? = null
    private var onManyArguments: ManyArgsHandler? = null

    fun method(name: String) {
        methodNames += name
    }

    fun methods(vararg names: String) {
        methodNames += names
    }

    fun className(name: String) {
        classNames += name
    }

    fun classNames(vararg names: String) {
        classNames += names
    }

    protected fun setCanExecute(block: DslMethodCall.() -> Boolean) {
        canExecute = block
    }

    protected fun setOnNoArguments(block: NoArgsHandler) {
        onNoArguments = block
    }

    protected fun setOnSingleArgument(block: SingleArgHandler) {
        onSingleArgument = block
    }

    protected fun setOnTwoArguments(block: TwoArgHandler) {
        onTwoArguments = block
    }

    protected fun setOnManyArguments(block: ManyArgsHandler) {
        onManyArguments = block
    }

    internal fun buildDefinition(): MethodCallDefinition = MethodCallDefinition(
        methodNames.toList(),
        classNames.toList(),
        canExecute,
        onNoArguments,
        onSingleArgument,
        onTwoArguments,
        onManyArguments,
    )
}

class PlainMethodCallBuilder : MethodCallBuilder() {
    fun canExecute(block: DslMethodCall.() -> Boolean) {
        setCanExecute(block)
    }

    fun onNoArguments(block: DslMethodCall.(PsiMethodCallExpression, Context) -> Expression?) {
        setOnNoArguments(block)
    }

    fun onSingleArgument(
        block: DslMethodCall.(PsiMethodCallExpression, Context, PsiExpression, Expression) -> Expression?,
    ) {
        setOnSingleArgument(block)
    }

    fun onTwoArguments(
        block: DslMethodCall.(
            PsiMethodCallExpression,
            Context,
            PsiExpression,
            PsiExpression,
            Expression,
            Expression,
        ) -> Expression?,
    ) {
        setOnTwoArguments(block)
    }

    fun onManyArguments(
        block: DslMethodCall.(PsiMethodCallExpression, Context, Array<PsiExpression>) -> Expression?,
    ) {
        setOnManyArguments(block)
    }
}

class QualifiedMethodCallBuilder : MethodCallBuilder() {
    fun canExecute(block: QualifiedDslMethodCall.() -> Boolean) {
        setCanExecute { (this as QualifiedDslMethodCall).block() }
    }

    fun onNoArguments(block: QualifiedDslMethodCall.(PsiMethodCallExpression, Context) -> Expression?) {
        setOnNoArguments { element, context ->
            block.invoke(this as QualifiedDslMethodCall, element, context)
        }
    }

    fun onSingleArgument(
        block: QualifiedDslMethodCall.(PsiMethodCallExpression, Context, PsiExpression, Expression) -> Expression?,
    ) {
        setOnSingleArgument { element, context, argument, argumentExpression ->
            block.invoke(this as QualifiedDslMethodCall, element, context, argument, argumentExpression)
        }
    }

    fun onTwoArguments(
        block: QualifiedDslMethodCall.(
            PsiMethodCallExpression,
            Context,
            PsiExpression,
            PsiExpression,
            Expression,
            Expression,
        ) -> Expression?,
    ) {
        setOnTwoArguments { element, context, a1, a2, a1Expression, a2Expression ->
            block.invoke(
                this as QualifiedDslMethodCall,
                element,
                context,
                a1,
                a2,
                a1Expression,
                a2Expression,
            )
        }
    }

    fun onManyArguments(
        block: QualifiedDslMethodCall.(PsiMethodCallExpression, Context, Array<PsiExpression>) -> Expression?,
    ) {
        setOnManyArguments { element, context, expressions ->
            block.invoke(this as QualifiedDslMethodCall, element, context, expressions)
        }
    }
}

abstract class DslMethodCall internal constructor(builder: MethodCallBuilder) : AbstractMethodCall() {
    private val definition = builder.buildDefinition()

    override fun canExecute(): Boolean = definition.canExecute?.invoke(this) ?: true

    override val methodNames: List<String> by lazy(LazyThreadSafetyMode.NONE) {
        definition.methodNames
    }

    override val classNames: List<String> by lazy(LazyThreadSafetyMode.NONE) {
        definition.classNames
    }

    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context,
    ): Expression? = definition.onNoArguments?.invoke(this, element, context)

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression,
    ): Expression? = definition.onSingleArgument?.invoke(this, element, context, argument, argumentExpression)

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression,
    ): Expression? = definition.onTwoArguments?.invoke(this, element, context, a1, a2, a1Expression, a2Expression)

    override fun onManyArguments(
        element: PsiMethodCallExpression,
        context: Context,
        expressions: Array<PsiExpression>,
    ): Expression? = definition.onManyArguments?.invoke(this, element, context, expressions)
}

abstract class PlainDslMethodCall protected constructor(
    init: PlainMethodCallBuilder.() -> Unit,
) : DslMethodCall(PlainMethodCallBuilder().apply(init))

abstract class QualifiedDslMethodCall protected constructor(
    init: QualifiedMethodCallBuilder.() -> Unit,
) : DslMethodCall(QualifiedMethodCallBuilder().apply(init)), NeedsQualifier
