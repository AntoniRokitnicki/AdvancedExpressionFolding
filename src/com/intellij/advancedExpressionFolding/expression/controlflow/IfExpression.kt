package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.language.java.PatternMatchingExt
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

class IfExpression : Expression {
    private var element: PsiIfStatement? = null

    constructor(element: PsiIfStatement, textRange: TextRange) : super(element, textRange) {
        this.element = element
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val state = AdvancedExpressionFoldingSettings.getInstance().getState()
        return isAssertExpression(state, element!!) || isCompactExpression(state, element!!)
    }

    override fun isNested(): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val state = AdvancedExpressionFoldingSettings.getInstance().getState()
        val group = FoldingGroup.newGroup(
            IfExpression::class.java.getName() + if (!isAssertExpression(state, this.element!!) && isCompactExpression(state, this.element!!)) HIGHLIGHTED_GROUP_POSTFIX else ""
        )
        val descriptors: java.util.ArrayList<FoldingDescriptor> = java.util.ArrayList()
        if (this.element!!.getLParenth() != null && this.element!!.getRParenth() != null) {
            if (isAssertExpression(state, this.element!!)) {
                foldAssertion(element, document, descriptors, group, state)
            } else if (isCompactExpression(state, this.element!!)) {
                foldCompactExpr(element, document, group, descriptors)
            } else if (state.getPatternMatchingInstanceof() && element is PsiIfStatement) {
                val instanceOfExpr = findInstanceOf(element)
                if (instanceOfExpr != null) {
                    PatternMatchingExt.foldInstanceOf(element, instanceOfExpr, document, descriptors)
                }
            }
        }
        return descriptors.toTypedArray()
    }

    private fun foldCompactExpr(
        element: PsiElement,
        document: Document,
        group: FoldingGroup,
        descriptors: java.util.ArrayList<FoldingDescriptor>
    ) {
        val textRange = TextRange.create(
            this.element!!.getLParenth()!!.getTextRange().getStartOffset(),
            this.element!!.getRParenth()!!.getTextRange().getEndOffset()
        )
        if (CompactControlFlowExpression.supportsFoldRegions(document, textRange)) {
            CompactControlFlowExpression.buildFoldRegions(element, group, descriptors, textRange)
        }
    }

    private fun foldAssertion(
        element: PsiElement,
        document: Document,
        descriptors: java.util.ArrayList<FoldingDescriptor>,
        group: FoldingGroup,
        state: AdvancedExpressionFoldingSettings.State
    ) {
        val throwStatement: PsiThrowStatement? =
            if (this.element!!.getThenBranch() is PsiBlockStatement && (this.element!!.getThenBranch() as PsiBlockStatement).getCodeBlock().getStatements().size == 1)
                (this.element!!.getThenBranch() as PsiBlockStatement).getCodeBlock().getStatements()[0] as? PsiThrowStatement
            else this.element!!.getThenBranch() as? PsiThrowStatement
        if (this.element!!.getCondition() != null && throwStatement != null) {
            val trailingSpace = document.getText(
                TextRange.create(
                    this.element!!.getLParenth()!!.getTextRange().getStartOffset() - 1,
                    this.element!!.getLParenth()!!.getTextRange().getStartOffset()
                )
            ).equals(" ")
            if (trailingSpace) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(
                            this.element!!.getTextRange().getStartOffset(),
                            this.element!!.getLParenth()!!.getTextRange().getStartOffset() - 1
                        ),
                        group,
                        "assert"
                    )
                )
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(
                            this.element!!.getLParenth()!!.getTextRange().getStartOffset(),
                            this.element!!.getCondition()!!.getTextRange().getStartOffset()
                        ),
                        group,
                        ""
                    )
                )
            } else {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(
                            this.element!!.getTextRange().getStartOffset(),
                            this.element!!.getCondition()!!.getTextRange().getStartOffset()
                        ),
                        group,
                        "assert "
                    )
                )
            }
            val binaryExpression = this.element!!.getCondition() as PsiBinaryExpression
            val p: String
            val text = binaryExpression.getOperationSign().getText()
            p = if ("==" == text) {
                "!="
            } else if ("!=" == text) {
                "=="
            } else if (">" == text) {
                "<="
            } else if ("<" == text) {
                ">="
            } else if (">=" == text) {
                "<"
            } else if ("<=" == text) {
                ">"
            } else {
                throw IllegalStateException("Unsupported operator: " + binaryExpression.getOperationSign().getText())
            }
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    binaryExpression.getOperationSign().getTextRange(),
                    group,
                    p
                )
            )
            val newException = if (throwStatement.getException() is PsiNewExpression) throwStatement.getException() as PsiNewExpression else null
            if (newException != null && newException.getArgumentList() != null && newException.getArgumentList()!!.getExpressions().size > 0 && newException.getArgumentList()!!.getExpressions()[0] is PsiLiteralExpression && newException.getArgumentList()!!.getExpressions()[0].getType() != null && "java.lang.String" == newException.getArgumentList()!!.getExpressions()[0].getType()!!.getCanonicalText()) {
                val r = TextRange.create(
                    throwStatement.getTextRange().getStartOffset(),
                    newException.getTextRange().getStartOffset()
                )
                val r2 = TextRange.create(
                    newException.getArgumentList()!!.getTextRange().getStartOffset(),
                    newException.getArgumentList()!!.getExpressions()[0].getTextRange().getStartOffset()
                )
                descriptors.add(FoldingDescriptor(element.getNode(), r, group, ""))
                descriptors.add(FoldingDescriptor(element.getNode(), r2, group, ""))
            }
        }
    }

    companion object {
        private val supportedOperatorSigns: Set<String> = object : java.util.HashSet<String>() {
            init {
                add("==")
                add("!=")
                add(">")
                add("<")
                add(">=")
                add("<=")
            }
        }

        @JvmStatic
        fun isCompactExpression(state: AdvancedExpressionFoldingSettings.State, element: PsiIfStatement): Boolean {
            return state.getCompactControlFlowSyntaxCollapse() && element.getRParenth() != null && element.getLParenth() != null && element.getCondition() != null
        }

        @JvmStatic
        fun isAssertExpression(state: AdvancedExpressionFoldingSettings.State, element: PsiIfStatement): Boolean {
            return state.getAssertsCollapse() && element.getCondition() is PsiBinaryExpression && supportedOperatorSigns.contains((element.getCondition() as PsiBinaryExpression).getOperationSign().getText()) && element.getElseBranch() == null && (element.getThenBranch() is PsiBlockStatement && (element.getThenBranch() as PsiBlockStatement).getCodeBlock().getStatements().size == 1 && ((element.getThenBranch() as PsiBlockStatement).getCodeBlock().getStatements()[0] is PsiThrowStatement) || element.getThenBranch() is PsiThrowStatement)
        }

        @JvmStatic
        fun findInstanceOf(element: PsiElement): PsiInstanceOfExpression? {
            if (element is PsiIfStatement) {
                val ifStatement = element
                val condition = ifStatement.getCondition()
                if (condition is PsiInstanceOfExpression) {
                    return condition
                }
            }
            return null
        }
    }
}

