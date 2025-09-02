package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections
import java.util.List

class Slice(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "slice", operands) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Slice::class.java.getName())
        val descriptors = ArrayList<FoldingDescriptor>()
        val ops = getOperands()
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(
                    ops.get(0).getTextRange().getEndOffset(),
                    if (ops.get(1) is NumberLiteral && (ops.get(1) as NumberLiteral).getNumber().intValue() == 0)
                        ops.get(1).getTextRange().getEndOffset()
                    else
                        ops.get(1).getTextRange().getStartOffset()
                ),
                group,
                "["
            )
        )
        if (ops.get(1) is NumberLiteral &&
            (ops.get(1) as NumberLiteral).getNumber().intValue() < 0 &&
            document.getText(
                TextRange.create(
                    ops.get(1).getTextRange().getStartOffset() + 1,
                    ops.get(1).getTextRange().getStartOffset() + 2
                )
            ).equals(" ")
        ) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        ops.get(1).getTextRange().getStartOffset() + 1,
                        ops.get(1).getTextRange().getStartOffset() + 2
                    ),
                    group,
                    ""
                )
            )
        }
        if (ops.size > 2) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        ops.get(1).getTextRange().getEndOffset(),
                        ops.get(2).getTextRange().getStartOffset()
                    ),
                    group,
                    ":"
                )
            )
            if (ops.get(2) is NumberLiteral &&
                (ops.get(2) as NumberLiteral).getNumber().intValue() < 0 &&
                document.getText(
                    TextRange.create(
                        ops.get(2).getTextRange().getStartOffset() + 1,
                        ops.get(2).getTextRange().getStartOffset() + 2
                    )
                ).equals(" ")
            ) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(
                            ops.get(2).getTextRange().getStartOffset() + 1,
                            ops.get(2).getTextRange().getStartOffset() + 2
                        ),
                        group,
                        ""
                    )
                )
            }
        }
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(
                    if (ops.size > 2) getTextRange().getEndOffset() - 1 else ops.get(1).getTextRange().getEndOffset(),
                    getTextRange().getEndOffset()
                ),
                group,
                if (ops.size == 2) ":]" else "]"
            )
        )
        for (operand in ops) {
            if (operand.supportsFoldRegions(document, this)) {
                Collections.addAll(
                    descriptors,
                    *operand.buildFoldRegions(operand.getElement(), document, this)
                )
            }
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }
}
