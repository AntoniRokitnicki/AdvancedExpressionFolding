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

class Slice(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "slice", operands) {
    override fun supportsFoldRegions(document: Document, parent: Expression?) = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Slice::class.java.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(
                    operands[0].textRange.endOffset,
                    if (operands[1] is NumberLiteral &&
                        (operands[1] as NumberLiteral).number.toInt() == 0
                    ) {
                        operands[1].textRange.endOffset
                    } else {
                        operands[1].textRange.startOffset
                    }
                ),
                group,
                "["
            )
        )
        if (operands[1] is NumberLiteral &&
            (operands[1] as NumberLiteral).number.toInt() < 0 &&
            document.getText(
                TextRange.create(
                    operands[1].textRange.startOffset + 1,
                    operands[1].textRange.startOffset + 2
                )
            ) == " "
        ) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(
                        operands[1].textRange.startOffset + 1,
                        operands[1].textRange.startOffset + 2
                    ),
                    group,
                    ""
                )
            )
        }
        if (operands.size > 2) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(
                        operands[1].textRange.endOffset,
                        operands[2].textRange.startOffset
                    ),
                    group,
                    ":"
                )
            )
            if (operands[2] is NumberLiteral &&
                (operands[2] as NumberLiteral).number.toInt() < 0 &&
                document.getText(
                    TextRange.create(
                        operands[2].textRange.startOffset + 1,
                        operands[2].textRange.startOffset + 2
                    )
                ) == " "
            ) {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(
                            operands[2].textRange.startOffset + 1,
                            operands[2].textRange.startOffset + 2
                        ),
                        group,
                        ""
                    )
                )
            }
        }
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(
                    if (operands.size > 2) {
                        textRange.endOffset - 1
                    } else {
                        operands[1].textRange.endOffset
                    },
                    textRange.endOffset
                ),
                group,
                if (operands.size == 2) ":]" else "]"
            )
        )
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                Collections.addAll(
                    descriptors,
                    *operand.buildFoldRegions(operand.element, document, this)
                )
            }
        }
        return descriptors.toTypedArray()
    }
}
