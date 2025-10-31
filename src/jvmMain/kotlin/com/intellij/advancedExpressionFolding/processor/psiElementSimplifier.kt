package com.intellij.advancedExpressionFolding.processor

import com.intellij.psi.*

fun PsiElement?.asReturn() = this?.asInstance<PsiReturnStatement>()
fun PsiElement?.asMethodCall() = this?.asInstance<PsiMethodCallExpression>()
fun PsiElement?.asBlock() = this?.asInstance<PsiBlockStatement>()
fun PsiElement?.asReference() = this?.asInstance<PsiReferenceExpression>()
fun PsiElement?.asNewInstance() = this?.asInstance<PsiNewExpression>()
fun PsiElement?.asIf() = this?.asInstance<PsiIfStatement>()
fun PsiElement?.asLiteral() = this?.asInstance<PsiLiteralExpression>()
fun PsiElement?.asSimpleIf() = asIf().takeIf {
    it?.elseBranch == null
}
fun PsiIfStatement?.asSimpleCondition() = this?.condition.asInstance<PsiBinaryExpression>()
fun PsiBinaryExpression?.asEqualsNull() = this?.takeIf {
    rOperand.asLiteral()?.value == null && operationTokenType == JavaTokenType.EQEQ
}?.lOperand.asReference()

fun PsiElement?.isReference(element: PsiElement): Boolean = this.asReference()?.takeIf {
    it.resolve() == element
} != null

val PsiCall.argumentCount: Int
    get() = argumentList?.expressionCount ?: 0


fun PsiStatement?.asSingleStatement(): PsiStatement? {
    return asBlock()?.codeBlock?.statements?.singleOrNull() ?: asInstance<PsiExpressionStatement>()
}
fun PsiStatement?.asAssignment(): Pair<PsiExpression, PsiExpression?>? {
    return asInstance<PsiExpressionStatement>()?.expression.asInstance<PsiAssignmentExpression>()?.takeIf {
        it.operationTokenType == JavaTokenType.EQ
    }?.let {
        it.lExpression to it.rExpression
    }
}

/**
 * for [PsiMethodCallExpression] and [PsiNewExpression]
 */
fun PsiCall.isArgumentReferencingElement(argumentIndex: Int, element: PsiElement) =
    this.argumentList?.expressions?.getOrNull<PsiExpression>(argumentIndex).isReference(element)

val PsiMethodCallExpression.className: PsiExpression?
    get() = methodExpression.qualifierExpression
val PsiMethodCallExpression.methodName: PsiIdentifier?
    get() = methodExpression.identifier

val PsiNewExpression.className: String?
    get() = classReference?.referenceName
