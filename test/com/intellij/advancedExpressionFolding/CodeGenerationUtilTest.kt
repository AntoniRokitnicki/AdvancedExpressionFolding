package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.util.CodeGenerationUtil
import com.intellij.ide.highlighter.JavaFileType
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class CodeGenerationUtilTest {
    @Test
    fun codeStringToPsiElementUsesPsiFileFactory() {
        val project = mockk<com.intellij.openapi.project.Project>()
        val psiClass = mockk<PsiClass> { every { getProject() } returns project }
        val psiElement = mockk<PsiElement>()
        val psiFile = mockk<PsiFile> { every { firstChild } returns psiElement }
        val factory = mockk<PsiFileFactory> {
            every { createFileFromText("temp.java", JavaFileType.INSTANCE, any<String>()) } returns psiFile
        }
        mockkStatic(PsiFileFactory::class)
        every { PsiFileFactory.getInstance(project) } returns factory
        val method = CodeGenerationUtil::class.java.getDeclaredMethod(
            "codeStringToPsiElement", PsiClass::class.java, String::class.java
        )
        method.isAccessible = true
        val result = method.invoke(CodeGenerationUtil, psiClass, "class B {}") as PsiElement
        assertSame(psiElement, result)
    }
}
