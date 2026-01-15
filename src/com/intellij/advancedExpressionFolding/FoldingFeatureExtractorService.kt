package com.intellij.advancedExpressionFolding

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiAnonymousClass
import com.intellij.psi.PsiBlockStatement
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiClassInitializer
import com.intellij.psi.PsiDoWhileStatement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiForStatement
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiLambdaExpression
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiModifier
import com.intellij.psi.PsiModifierListOwner
import com.intellij.psi.PsiSwitchStatement
import com.intellij.psi.PsiSynchronizedStatement
import com.intellij.psi.PsiTryStatement
import com.intellij.psi.PsiWhileStatement
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.util.PsiTreeUtil

@Service(Service.Level.PROJECT)
class FoldingFeatureExtractorService(private val project: Project) {

    fun extractFeatures(element: PsiElement): Map<String, Any> {
        return mapOf(
            "psiType" to resolvePsiType(element),
            "nonCommentLineCount" to countEffectiveLines(element),
            "nestingDepth" to calculateNestingDepth(element),
            "relevantAnnotationCount" to countRelevantAnnotations(element),
            "accessModifier" to resolveAccessModifier(element)
        )
    }

    private fun resolvePsiType(element: PsiElement): String = when (element) {
        is PsiMethod -> "method"
        is PsiClassInitializer -> "classInitializer"
        is PsiAnonymousClass -> "anonymousClass"
        is PsiClass -> when {
            element.isEnum -> "enum"
            element.isInterface -> "interface"
            element.isAnnotationType -> "annotationType"
            else -> "class"
        }
        is PsiTryStatement -> "tryStatement"
        is PsiWhileStatement -> "whileStatement"
        is PsiDoWhileStatement -> "doWhileStatement"
        is PsiForStatement -> "forStatement"
        is PsiForeachStatement -> "foreachStatement"
        is PsiSynchronizedStatement -> "synchronizedStatement"
        is PsiIfStatement -> "ifStatement"
        is PsiSwitchStatement -> "switchStatement"
        is PsiLambdaExpression -> "lambdaExpression"
        is PsiBlockStatement -> "blockStatement"
        else -> element.javaClass.simpleName ?: element::class.java.simpleName
    }

    private fun countEffectiveLines(element: PsiElement): Int {
        val file = element.containingFile ?: return 0
        val document = PsiDocumentManager.getInstance(project).getDocument(file) ?: return 0
        val elementRange = element.textRange
        val startLine = document.getLineNumber(elementRange.startOffset)
        val endLine = document.getLineNumber(elementRange.endOffset)
        var count = 0

        for (line in startLine..endLine) {
            val lineStartOffset = maxOf(document.getLineStartOffset(line), elementRange.startOffset)
            val lineEndOffset = minOf(document.getLineEndOffset(line), elementRange.endOffset)
            if (lineEndOffset <= lineStartOffset) continue
            val lineText = document.getText(TextRange(lineStartOffset, lineEndOffset)).trim()
            if (lineText.isEmpty() || isCommentLine(lineText)) continue
            count++
        }
        return count
    }

    private fun isCommentLine(text: String): Boolean {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return true
        if (trimmed.startsWith("//")) return true
        if (trimmed.startsWith("/*") && trimmed.endsWith("*/")) return true
        if (trimmed.startsWith("*")) return true
        return false
    }

    private fun calculateNestingDepth(element: PsiElement): Int {
        var depth = 0
        var parent = element.parent
        while (parent != null && parent !is PsiFile) {
            depth++
            parent = parent.parent
        }
        return depth
    }

    private fun countRelevantAnnotations(element: PsiElement): Int {
        val relevantPrefixes = listOf("javax.persistence", "org.springframework", "junit")
        return PsiTreeUtil.collectElementsOfType(element, PsiAnnotation::class.java)
            .count { annotation ->
                val qualifiedName = annotation.qualifiedName ?: return@count false
                relevantPrefixes.any { prefix -> qualifiedName.startsWith(prefix) }
            }
    }

    private fun resolveAccessModifier(element: PsiElement): String {
        val owner = element as? PsiModifierListOwner ?: return "package-private"
        return when {
            owner.hasModifierProperty(PsiModifier.PUBLIC) -> PsiModifier.PUBLIC
            owner.hasModifierProperty(PsiModifier.PROTECTED) -> PsiModifier.PROTECTED
            owner.hasModifierProperty(PsiModifier.PRIVATE) -> PsiModifier.PRIVATE
            else -> "package-private"
        }
    }
}
