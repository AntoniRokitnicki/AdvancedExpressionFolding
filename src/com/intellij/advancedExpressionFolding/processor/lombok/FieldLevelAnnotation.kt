package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod

data class FieldLevelAnnotation(
    val classAnnotation: LombokFoldingAnnotation,
    val field: PsiField,
    val method: List<PsiMethod>,
    val arguments: List<String> = emptyList(),
    val group: FoldingGroup? = null,
)
