package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression.Companion.EMPTY_ARRAY
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.impl.source.codeStyle.IndentHelper
import com.intellij.psi.util.PsiTreeUtil
import java.util.ArrayList

abstract class AbstractControlFlowCodeBlock : Expression {
    private var element: PsiCodeBlock
    private var indentHelper: IndentHelper

    constructor(element: PsiCodeBlock, textRange: TextRange) : super(element, textRange) {
        this.element = element
        this.indentHelper = IndentHelper.getInstance()
    }

    override fun isNested(): Boolean {
        return true
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return element.getLBrace() != null && element.getRBrace() != null
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(AbstractControlFlowCodeBlock::class.java.getName())
        val descriptors = ArrayList<FoldingDescriptor>()
        if (this.element.getLBrace() != null) {
            descriptors.add(FoldingDescriptor(element.getNode(), this.element.getLBrace().getTextRange(), group, ""))
        }
        if (this.element.getRBrace() != null) {
            var smart = false
            if (element.getParent() != null) {
                var thisStatement: PsiElement?
                if (element.getParent().getParent() is PsiIfStatement || element.getParent().getParent() is PsiLoopStatement) {
                    thisStatement = element.getParent().getParent()
                } else if (element.getParent() is PsiSwitchStatement || element.getParent() is PsiTryStatement || element.getParent() is PsiCatchSection) {
                    thisStatement = element.getParent()
                } else {
                    thisStatement = null
                }
                if (thisStatement != null) {
                    val thisStatementIndent = indentHelper.getIndent(thisStatement.getContainingFile(), thisStatement.getNode())
                    var before = PsiTreeUtil.prevLeaf(this.element.getRBrace(), true)
                    var after = PsiTreeUtil.prevLeaf(this.element.getRBrace(), true)
                    if (before is PsiWhiteSpace && after is PsiWhiteSpace) {
                        smart = true
                        var startOffset = this.element.getRBrace().getTextRange().getStartOffset()
                        var newLine = false
                        var endOffset = this.element.getRBrace().getTextRange().getEndOffset()
                        while (endOffset < document.getTextLength()) {
                            endOffset++
                            val c = document.getText(TextRange.create(endOffset - 1, endOffset)).charAt(0)
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
                            var i = 0
                            while (i < thisStatementIndent) {
                                val c = document.getText(TextRange.create(startOffset - 1, startOffset)).charAt(0)
                                if (c != ' ' && c != '\t') {
                                    smart = false
                                    break
                                }
                                startOffset--
                                i++
                            }
                        }
                        if (smart) {
                            descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(startOffset, endOffset), group, ""))
                        }
                    }
                }
            }
            if (!smart) {
                var siblingKeyword: PsiElement? = PsiTreeUtil.nextLeaf(this.element.getRBrace(), true)
                if (siblingKeyword is PsiWhiteSpace) {
                    siblingKeyword = PsiTreeUtil.nextLeaf(siblingKeyword, true)
                }
                if (siblingKeyword !is PsiKeyword) {
                    siblingKeyword = null
                } else {
                    val keyword = siblingKeyword as PsiKeyword
                    if (keyword.getTokenType() !== JavaTokenType.ELSE_KEYWORD &&
                        keyword.getTokenType() !== JavaTokenType.WHILE_KEYWORD &&
                        keyword.getTokenType() !== JavaTokenType.CATCH_KEYWORD &&
                        keyword.getTokenType() !== JavaTokenType.FINALLY_KEYWORD) {
                        siblingKeyword = null
                    }
                }
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        if (siblingKeyword != null)
                            TextRange.create(this.element.getRBrace().getTextRange().getStartOffset(), siblingKeyword.getTextRange().getStartOffset())
                        else
                            this.element.getRBrace().getTextRange(),
                        group,
                        ""
                    )
                )
            }
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }
}
