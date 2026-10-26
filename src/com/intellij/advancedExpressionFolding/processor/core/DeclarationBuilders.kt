package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.processor.declaration.PsiClassExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiCodeBlockExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiDeclarationStatementExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiFieldExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiMethodExt
import com.intellij.advancedExpressionFolding.processor.declaration.PsiVariableExt
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiDeclarationStatement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter
import com.intellij.psi.PsiRecordComponent
import com.intellij.psi.PsiVariable

internal val declarationExpressionBuilders = listOf(
    registerBuilder<PsiDeclarationStatement> { element, _, _ ->
        PsiDeclarationStatementExt.createExpression(element)
    },
    registerBuilder<PsiVariable>(predicate = {
        varExpressionsCollapse &&
            (it.parent is PsiDeclarationStatement || it.parent is PsiForeachStatement)
    }) { element, _, _ ->
        PsiVariableExt.getVariableDeclaration(element)
    },
    registerBuilder<PsiCodeBlock> { element, _, _ ->
        PsiCodeBlockExt.getCodeBlockExpression(element)
    },
    registerBuilder<PsiClass> { element, _, _ ->
        PsiClassExt.createExpression(element)
    },
    registerBuilder<PsiField> { element, document, _ ->
        PsiFieldExt.createExpression(element, document)
    },
    registerBuilder<PsiParameter> { element, document, _ ->
        PsiMethodExt.createExpression(element, document)
    },
    registerBuilder<PsiRecordComponent> { element, _, _ ->
        PsiFieldExt.createExpression(element)
    },
    registerBuilder<PsiMethod> { element, document, _ ->
        PsiMethodExt.createExpression(element, document)
    }
)
