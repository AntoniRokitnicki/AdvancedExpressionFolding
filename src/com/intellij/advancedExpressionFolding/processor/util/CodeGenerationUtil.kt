package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.advancedExpressionFolding.processor.isWhitespace
import com.intellij.ide.highlighter.JavaFileType
import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.*

@Suppress("unused")
object CodeGenerationUtil {
    private val logger: Logger = Logger.getInstance(CodeGenerationUtil::class.java)

    private fun PsiClass.codeStringToPsiElement(code: String): PsiElement? {
        val psiFile = PsiFileFactory.getInstance(project)
            .createFileFromText("temp.java", JavaFileType.INSTANCE, code)
        return psiFile.firstChild
    }

    private fun generatePsiElementFromCode(any: PsiClass) {
        val code = any.codeStringToPsiElement("code")!!.parent // top level element is whitespace

        class KeyCleanerPsiElementVisitor : JavaRecursiveElementVisitor() {
            val elements = mutableListOf<PsiElement>()
            var on = false
            override fun visitCodeBlock(block: PsiCodeBlock) {
                on = true
                super.visitCodeBlock(block)
                on = false
            }

            override fun visitElement(element: PsiElement) {
                if (on && !element.isWhitespace()) {
                    elements.add(element)
                }
                super.visitElement(element)
            }

        }

        val v = KeyCleanerPsiElementVisitor()
        code.accept(v)
        if (logger.isDebugEnabled) {
            logger.debug("Collected code block elements: ${v.elements}")
        }
    }
}
