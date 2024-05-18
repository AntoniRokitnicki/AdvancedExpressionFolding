package com.intellij.advancedExpressionFolding.extension

import com.intellij.ide.highlighter.JavaFileType
import com.intellij.psi.*

@Suppress("unused")
object CodeGenerationUtil {
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
        println(v.elements)
    }
}
