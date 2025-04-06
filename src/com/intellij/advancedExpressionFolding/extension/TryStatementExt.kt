package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.HideExpression
import com.intellij.advancedExpressionFolding.extension.MethodBodyInspector.isRethrowingException
import com.intellij.psi.PsiStatement
import com.intellij.psi.PsiTryStatement

object TryStatementExt : BaseExtension() {

    fun PsiTryStatement.createSneakyThrows(): Expression? {
        if (!experimental || finallyBlock?.statements != null || resourceList != null) return null

        val codeBlock = tryBlock?.statements?.singleOrNull() ?: return null

        val catchBlock = catchSections.singleOrNull()
        val catchBlockCode = catchBlock?.catchBlock ?: return null

        catchBlockCode.statements.singleOrNull() ?: return null

        if (!catchBlock.isRethrowingException()) return null

        val tryKeyword = firstChild
        val firstBrace = tryBlock?.firstChild
        val lastBrace = tryBlock?.lastChild
        val list = exprList(
            tryKeyword.expr("@SneakyThrows"),
            tryKeyword.nextWhiteSpace().exprHide(),
            firstBrace.exprHide(),
            foldSpacesBeforeSingleStatement(codeBlock),
            lastBrace.prevWhiteSpace().exprHide(),
            lastBrace.exprHide(),
            catchBlock.exprHide(),
        )
        return list.exprWrap(this)
    }

    /**
     * Folds extra indentation spaces before a single statement in a try block.
     *
     * <code>
     * try {
     * ____return new String(bytes, "UTF-8");
     * }
     *  </code>
     *
     * The underscores (_) represent spaces that will be folded.
     */
    private fun PsiTryStatement.foldSpacesBeforeSingleStatement(
        codeBlock: PsiStatement
    ): HideExpression? {
        val tryLength = prevWhiteSpace()?.text?.substringAfterLast("\n")?.length // 12
        val codePrefixWhiteSpace = codeBlock.prevWhiteSpace()
        val codeBlockLength = codePrefixWhiteSpace?.text?.substringAfterLast("\n")?.length // 16
        return tryLength?.let { tryIndentLength ->
            codeBlockLength?.let { codeIndentLength ->
                (codeIndentLength - tryIndentLength).takeIf {
                    it > 0
                }?.let { diffCharCount ->
                    codePrefixWhiteSpace.exprHide(
                        textRange = codePrefixWhiteSpace.trailingCharsRange(diffCharCount)
                    )
                }
            }
        }
    }

}



