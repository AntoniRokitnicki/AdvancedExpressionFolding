package com.intellij.advancedExpressionFolding.adapter.cache

import com.intellij.advancedExpressionFolding.application.port.out.FoldingCache
import com.intellij.advancedExpressionFolding.processor.cache.CacheExt.invalidateExpired
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaFile

class PsiFileFoldingCache : FoldingCache {
    override fun read(element: PsiElement, quick: Boolean, document: Document): Array<FoldingDescriptor>? {
        if (quick) {
            return null
        }
        val psiFile = element as? PsiJavaFile ?: return null
        if (!psiFile.invalidateExpired(document, false)) {
            return psiFile.getUserData(Keys.FULL_CACHE)
        }
        return null
    }

    override fun write(element: PsiElement, foldingDescriptors: Array<FoldingDescriptor>) {
        (element as? PsiJavaFile)?.putUserData(Keys.FULL_CACHE, foldingDescriptors)
    }
}
