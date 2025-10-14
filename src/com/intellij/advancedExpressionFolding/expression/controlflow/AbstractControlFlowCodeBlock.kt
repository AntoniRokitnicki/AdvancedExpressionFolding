package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiCatchSection
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiKeyword
import com.intellij.psi.PsiLoopStatement
import com.intellij.psi.PsiSwitchStatement
import com.intellij.psi.PsiTryStatement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.impl.source.codeStyle.IndentHelper
import com.intellij.psi.util.PsiTreeUtil

abstract class AbstractControlFlowCodeBlock(
    protected val codeBlock: PsiCodeBlock,
    textRange: TextRange
) : Expression(codeBlock, textRange) {

    private val indentHelper: IndentHelper = IndentHelper.getInstance()

    override fun isNested(): Boolean = true

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return codeBlock.lBrace != null && codeBlock.rBrace != null
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(AbstractControlFlowCodeBlock::class.java.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        codeBlock.lBrace?.let { lBrace ->
            descriptors += FoldingDescriptor(element.node, lBrace.textRange, group, "")
        }
        val rBrace = codeBlock.rBrace
        if (rBrace != null) {
            var smart = false
            if (element.parent != null) {
                val thisStatement = when {
                    element.parent?.parent is PsiIfStatement || element.parent?.parent is PsiLoopStatement ->
                        element.parent!!.parent
                    element.parent is PsiSwitchStatement ||
                        element.parent is PsiTryStatement ||
                        element.parent is PsiCatchSection ->
                        element.parent
                    else -> null
                }
                if (thisStatement != null) {
                    val thisStatementIndent = indentHelper.getIndent(thisStatement.containingFile, thisStatement.node)
                    val before = PsiTreeUtil.prevLeaf(rBrace, true)
                    val after = PsiTreeUtil.prevLeaf(rBrace, true)
                    if (before is PsiWhiteSpace && after is PsiWhiteSpace) {
                        smart = true
                        var startOffset = rBrace.textRange.startOffset
                        var newLine = false
                        var endOffset = rBrace.textRange.endOffset
                        while (endOffset < document.textLength) {
                            endOffset++
                            val c = document.getText(TextRange.create(endOffset - 1, endOffset))[0]
                            if (c != ' ' && c != '\t') {
                                if (c != '\n') {
                                    endOffset--
                                } else {
                                    newLine = true
                                }
                                break
                            }
                        }
                        if (newLine) {
                            var consumed = 0
                            while (consumed < thisStatementIndent) {
                                val c = document.getText(TextRange.create(startOffset - 1, startOffset))[0]
                                if (c != ' ' && c != '\t') {
                                    smart = false
                                    break
                                }
                                startOffset--
                                consumed++
                            }
                        }
                        if (smart) {
                            descriptors += FoldingDescriptor(
                                element.node,
                                TextRange.create(startOffset, endOffset),
                                group,
                                ""
                            )
                        }
                    }
                }
            }
            if (!smart) {
                var siblingKeyword: PsiElement? = PsiTreeUtil.nextLeaf(rBrace, true)
                if (siblingKeyword is PsiWhiteSpace) {
                    siblingKeyword = PsiTreeUtil.nextLeaf(siblingKeyword, true)
                }
                siblingKeyword = if (siblingKeyword is PsiKeyword) {
                    val keyword = siblingKeyword
                    if (keyword.tokenType == JavaTokenType.ELSE_KEYWORD ||
                        keyword.tokenType == JavaTokenType.WHILE_KEYWORD ||
                        keyword.tokenType == JavaTokenType.CATCH_KEYWORD ||
                        keyword.tokenType == JavaTokenType.FINALLY_KEYWORD
                    ) {
                        keyword
                    } else {
                        null
                    }
                } else {
                    null
                }
                val range = siblingKeyword?.let {
                    TextRange.create(rBrace.textRange.startOffset, it.textRange.startOffset)
                } ?: rBrace.textRange
                descriptors += FoldingDescriptor(element.node, range, group, "")
            }
        }
        return descriptors.toTypedArray()
    }
}
