package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.impl.source.codeStyle.IndentHelper
import com.intellij.psi.util.PsiTreeUtil
import java.util.ArrayList

abstract class AbstractControlFlowCodeBlock(
    private val element: PsiCodeBlock,
    textRange: TextRange
) : Expression(element, textRange) {
    private val indentHelper = IndentHelper.getInstance()

    override fun isNested(): Boolean = true

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return element.lBrace != null && element.rBrace != null
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(AbstractControlFlowCodeBlock::class.java.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        if (this.element.lBrace != null) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    this.element.lBrace!!.textRange,
                    group,
                    ""
                )
            )
        }
        if (this.element.rBrace != null) {
            var smart = false
            if (element.parent != null) {
                val thisStatement: PsiElement? =
                    if (element.parent.parent is PsiIfStatement || element.parent.parent is PsiLoopStatement) {
                        element.parent.parent
                    } else if (
                        element.parent is PsiSwitchStatement ||
                        element.parent is PsiTryStatement ||
                        element.parent is PsiCatchSection
                    ) {
                        element.parent
                    } else {
                        null
                    }
                if (thisStatement != null) {
                    val thisStatementIndent =
                        indentHelper.getIndent(thisStatement.containingFile, thisStatement.node)
                    val before = PsiTreeUtil.prevLeaf(this.element.rBrace, true)
                    val after = PsiTreeUtil.prevLeaf(this.element.rBrace, true)
                    if (before is PsiWhiteSpace && after is PsiWhiteSpace) {
                        smart = true
                        var startOffset = this.element.rBrace!!.textRange.startOffset
                        var newLine = false
                        var endOffset = this.element.rBrace!!.textRange.endOffset
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
                            for (i in 0 until thisStatementIndent /* - parentStatementIndent*/) {
                                val c = document.getText(TextRange.create(startOffset - 1, startOffset))[0]
                                if (c != ' ' && c != '\t') {
                                    smart = false
                                    break
                                }
                                startOffset--
                            }
                        }
                        if (smart) {
                            descriptors.add(
                                FoldingDescriptor(
                                    element.node,
                                    TextRange.create(startOffset, endOffset),
                                    group,
                                    ""
                                )
                            )
                        }
                    }
                }
            }
            if (!smart) {
                var siblingKeyword: PsiElement? = PsiTreeUtil.nextLeaf(this.element.rBrace, true)
                if (siblingKeyword is PsiWhiteSpace) {
                    siblingKeyword = PsiTreeUtil.nextLeaf(siblingKeyword, true)
                }
                if (siblingKeyword !is PsiKeyword) {
                    siblingKeyword = null
                } else {
                    val keyword = siblingKeyword
                    if (keyword.tokenType != JavaTokenType.ELSE_KEYWORD &&
                        keyword.tokenType != JavaTokenType.WHILE_KEYWORD &&
                        keyword.tokenType != JavaTokenType.CATCH_KEYWORD &&
                        keyword.tokenType != JavaTokenType.FINALLY_KEYWORD
                    ) {
                        siblingKeyword = null
                    }
                }
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        if (siblingKeyword != null)
                            TextRange.create(
                                this.element.rBrace!!.textRange.startOffset,
                                siblingKeyword.textRange.startOffset
                            )
                        else
                            this.element.rBrace!!.textRange,
                        group,
                        ""
                    )
                )
            }
        }
        return descriptors.toTypedArray()
    }
}

