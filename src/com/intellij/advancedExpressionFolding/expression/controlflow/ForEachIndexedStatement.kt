package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiForStatement
import java.util.ArrayList

class ForEachIndexedStatement(
    element: PsiForStatement,
    textRange: TextRange,
    declarationTextRange: TextRange,
    indexTextRange: TextRange,
    variableTextRange: TextRange,
    arrayTextRange: TextRange,
    varSyntax: Boolean,
    isFinal: Boolean
) : Expression(element, textRange) {
    private var element: PsiForStatement
    private var declarationTextRange: TextRange
    private var indexTextRange: TextRange
    private var variableTextRange: TextRange
    private var arrayTextRange: TextRange
    private var varSyntax: Boolean
    private var isFinal: Boolean

    init {
        this.element = element
        this.declarationTextRange = declarationTextRange
        this.indexTextRange = indexTextRange
        this.variableTextRange = variableTextRange
        this.arrayTextRange = arrayTextRange
        this.varSyntax = varSyntax
        this.isFinal = isFinal
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ForEachIndexedStatement::class.java.getName())
        val prefixRange = TextRange.create(textRange.getStartOffset(), textRange.getStartOffset() + 1)
        var prefix = document.getText(prefixRange)
        if (AdvancedExpressionFoldingSettings.getInstance().getState().getCompactControlFlowSyntaxCollapse()
            && prefix.equals("(")
        ) {
            prefix = ""
        }
        if (varSyntax) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(textRange.getStartOffset(), indexTextRange.getStartOffset()),
                    group,
                    prefix + (if (isFinal) "val" else "var") + " ("
                )
            )
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(indexTextRange.getEndOffset(), variableTextRange.getStartOffset() - 1),
                    group,
                    ","
                )
            )
        } else {
            descriptors.add(FoldingDescriptor(element.getNode(), prefixRange, group, prefix + "("))
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(indexTextRange.getEndOffset(), declarationTextRange.getStartOffset()),
                    group,
                    ", "
                )
            )
        }
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(variableTextRange.getEndOffset(), arrayTextRange.getStartOffset()),
                group,
                if (AdvancedExpressionFoldingSettings.getInstance().getState().getCompactControlFlowSyntaxCollapse()) " : " else ") : "
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(arrayTextRange.getEndOffset(), declarationTextRange.getEndOffset()),
                group,
                ") {\\n"
            )
        )
        return descriptors.toArray(EMPTY_ARRAY)
    }
}

