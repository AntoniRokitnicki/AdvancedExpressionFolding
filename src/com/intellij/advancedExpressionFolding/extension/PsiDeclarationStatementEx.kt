package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.DestructuringExpression
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.ClassType.DESTRUCTURING
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiArrayAccessExpression
import com.intellij.psi.PsiDeclarationStatement
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiVariable

object PsiDeclarationStatementEx : BaseExtension() {

    @JvmStatic
    fun createExpression(
        element: PsiDeclarationStatement,
        document: Document
    ): Expression? {
        destructuring.takeIf {
            it
        } ?: return null
        val variable = asVariable(element) ?: return null
        val array = asArray(variable) ?: return null
        val index = index(array) ?: return null
        val id = variable.nameIdentifier ?: return null
//        Data a1 = data.getArray()[0];
//        Data a2 = data.getArray()[1];
//into
//        def (a1, a2) = data.getArray();
        if (element.getType() != DESTRUCTURING) {
            val next = nextDeclaration(element) ?: return null
            val variable2 = asVariable(next) ?: return null
            val array2 = asArray(variable2) ?: return null
            val index2 = index(array2) ?: return null
            val isNext = index == 0 && index2 == 1 && hasSameParentMethodCall(array, array2)
            if (!isNext) {
                return null
            }
            next.setType(DESTRUCTURING)
// first
//        Data a1 = data.getArray()[0];
// into
//        def (a1,
            val hideMethodCall = DestructuringExpression(element, (id.end()..element.end()), ", ", null, "1-methodCall")
            return DestructuringExpression(element, (element.start()..id.start()), "def (", hideMethodCall, "1-prefix")
        } else {
// second
//        <ENTER>Data a2 = data.getArray()[1];
//into
//                    a2) = data.getArray();
            val hideIndex = DestructuringExpression(
                array,
                (array.children[1].start()..array.children[3].end()),
                "",
                null,
                "2-hideIndex"
            )
            val secondBracket = DestructuringExpression(
                element,
                (id.end()..id.end() + 1),
                ") ",
                hideIndex,
                "2-secondBracket"
            )
            return DestructuringExpression(
                element,
                (element.prevSibling.start()..id.start()),
                "",
                secondBracket,
                "2-prefix"
            )
        }
    }

    private fun hasSameParentMethodCall(array: PsiArrayAccessExpression, array2: PsiArrayAccessExpression) =
        array.arrayExpression.text == array2.arrayExpression.text

    private fun nextDeclaration(element: PsiDeclarationStatement) =
        element.realNextSibling().asInstance<PsiDeclarationStatement>()

    private fun index(array: PsiArrayAccessExpression) =
        array.indexExpression.asInstance<PsiLiteralExpression>()?.value.asInstance<Int>()

    private fun asArray(variable: PsiVariable) =
        variable.initializer.asInstance<PsiArrayAccessExpression>()

    private fun asVariable(element: PsiDeclarationStatement) =
        element.declaredElements.singleOrNull().asInstance<PsiVariable>()


}

