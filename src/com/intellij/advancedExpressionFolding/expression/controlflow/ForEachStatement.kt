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

class ForEachStatement(
    element: PsiForStatement,
    textRange: TextRange,
    declarationTextRange: TextRange,
    variableTextRange: TextRange,
    arrayTextRange: TextRange
) : Expression(element, textRange) {
    private var element: PsiForStatement
    private var declarationTextRange: TextRange
    private var variableTextRange: TextRange
    private var arrayTextRange: TextRange

    init {
        this.element = element
        this.declarationTextRange = declarationTextRange
        this.variableTextRange = variableTextRange
        this.arrayTextRange = arrayTextRange
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ForEachStatement::class.java.getName())
        if (AdvancedExpressionFoldingSettings.getInstance().getState().getCompactControlFlowSyntaxCollapse()
            && this.element.getLParenth() != null
        ) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        this.element.getLParenth().getTextRange().getStartOffset(),
                        this.element.getLParenth().getTextRange().getStartOffset() + 1
                    ),
                    group,
                    ""
                )
            )
        }
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(textRange.getStartOffset(), declarationTextRange.getStartOffset()),
                group,
                ""
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(variableTextRange.getEndOffset(), arrayTextRange.getStartOffset()),
                group,
                " : "
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(arrayTextRange.getEndOffset(), declarationTextRange.getEndOffset()),
                group,
                if (AdvancedExpressionFoldingSettings.getInstance().getState().getCompactControlFlowSyntaxCollapse()) " {\\n" else ") {\\n"
            )
        )
        return descriptors.toArray(EMPTY_ARRAY)
    }
}

