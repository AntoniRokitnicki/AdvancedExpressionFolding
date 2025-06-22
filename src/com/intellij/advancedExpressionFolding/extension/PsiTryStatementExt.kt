package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.HideExpression
import com.intellij.advancedExpressionFolding.extension.MethodBodyInspector.getRethrownException
import com.intellij.psi.PsiStatement
import com.intellij.psi.PsiTryStatement


object PsiTryStatementExt : BaseExtension() {

    fun createExpression(element: PsiTryStatement) = element.createSneakyThrows()

    fun PsiTryStatement.createSneakyThrows(): Expression? {
        if (!experimental || !isSimpleTryCatch()) return null

        val catchBlock = catchSections.singleOrNull()?.takeIf {
            it.catchBlock?.statements?.singleOrNull() != null
        } ?: return null

        val rethrownException = catchBlock.getRethrownException() ?: return null

        val tryKeyword = firstChild
        val sneakyThrows = when {
            rethrownException.selectivelyAddException() -> tryKeyword.expr("@SneakyThrows($rethrownException)")
            else -> tryKeyword.expr("@SneakyThrows")
        }
        val codeBlock = tryBlock?.statements?.singleOrNull()
        val list = if (codeBlock != null) {
            val firstBrace = tryBlock?.firstChild
            val lastBrace = tryBlock?.lastChild
            exprList(
                sneakyThrows,
                tryKeyword.nextWhiteSpace().exprHide(),
                firstBrace.exprHide(),
                foldSpacesBeforeSingleStatement(codeBlock),
                codeBlock.nextWhiteSpace().exprHide(),
                catchBlock.prevWhiteSpace().exprHide(),
                lastBrace.prevWhiteSpace().exprHide(),
                lastBrace.exprHide(),
                catchBlock.exprHide(),
            )
        } else {
            exprList(
                sneakyThrows,
                catchBlock.exprHide(),
                catchBlock.prevWhiteSpace().exprHide(),
            )
        }
        return list.exprWrap(this, group())
    }

    private fun String.selectivelyAddException() = this != "RuntimeException" && this != "IllegalStateException"

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

    fun PsiTryStatement.isSimpleTryCatch() = allTrue(
        tryBlock?.statements?.isNotEmpty(),
        catchBlocks.one,
        catchBlocks.one,
        catchBlockParameters.one,
        catchSections.one,
        finallyBlock == null,
        resourceList == null
    )

}



