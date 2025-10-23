package com.intellij.advancedExpressionFolding.processor.language.java

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.getAnyExpression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiField
import com.intellij.psi.PsiNewExpression

object ConstructorReferenceExt : BaseExtension() {

    fun foldFieldConstructor(
        field: PsiField,
        document: Document
    ): Expression? {
        val initializer = field.initializer.asInstance<PsiNewExpression>()
        val noParams = initializer?.argumentList?.isEmpty == true
        val anonymousClass = initializer?.anonymousClass

        var noBody = initializer?.classReference != null
        if (anonymousClass != null) {
            noBody = anonymousClass.methods.size + anonymousClass.fields.size == 0
        }

        val sameType = field.sameTypeOfFieldAndInitializer(initializer)
        if (noBody && sameType && initializer != null) {
            val group = group()
            val list = exprList()
            initializer.classReference?.let {
                list += it.prevWhiteSpace()?.exprHide()
                list += it.exprHide()
            }
            initializer.anonymousClass?.let {
                list += it.prevWhiteSpace()?.exprHide()
                list += it.expr("{}")
            }
            list += initializer.exprWrapAround(textBefore = "::", group = group)

            if (noParams) {
                list += initializer.argumentList?.exprHide()
            } else {
                initializer.argumentList?.expressions?.map {
                    getAnyExpression(it, document)
                }?.let {
                    list.plusAssign(it)
                }
            }
            return list.exprWrap(field, group)
        }
        return null
    }

    //TODO: extract generic extension method
    private fun PsiField.sameTypeOfFieldAndInitializer(initializer: PsiNewExpression?) =
        initializer?.classOrAnonymousClassReference?.resolve() == typeResolved

}
