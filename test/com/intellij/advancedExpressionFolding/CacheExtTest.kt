package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.cache.CacheExt
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.SmartPointerManager
import com.intellij.psi.SmartPsiElementPointer
import com.intellij.psi.util.PsiTreeUtil
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class CacheExtTest : BaseTest() {

    @Test
    fun `document modification stamps preserve cache and invalidate on edit`() {
        val file = fixture.configureByText(
            "CacheSample.java",
            """
            class CacheSample {
                int calc(int a, int b) {
                    return a + b;
                }
            }
            """.trimIndent()
        )
        val project = fixture.project
        val document = fixture.editor.document

        val expressionPointer = runReadAction<SmartPsiElementPointer<PsiBinaryExpression>> {
            val binaryExpression = PsiTreeUtil.findChildOfType(file, PsiBinaryExpression::class.java)
                ?: error("Expected binary expression")
            SmartPointerManager.getInstance(project).createSmartPsiElementPointer(binaryExpression)
        }

        val first = runReadAction {
            CacheExt.getExpression(expressionPointer.element!!, document, false)
        }
        assertNotNull(first)

        val second = runReadAction {
            CacheExt.getExpression(expressionPointer.element!!, document, false)
        }
        assertSame(first, second)

        runReadAction {
            CacheExt.getExpression(expressionPointer.element!!, document, true)
        }

        val third = runReadAction {
            CacheExt.getExpression(expressionPointer.element!!, document, false)
        }
        assertSame(first, third)

        WriteCommandAction.runWriteCommandAction(project) {
            val offset = document.text.indexOf("a + b")
            check(offset >= 0) { "binary expression not found" }
            document.replaceString(offset, offset + "a + b".length, "a - b")
            PsiDocumentManager.getInstance(project).commitDocument(document)
        }

        val afterEdit = runReadAction {
            CacheExt.getExpression(expressionPointer.element!!, document, false)
        }
        assertNotNull(afterEdit)
        assertNotSame(first, afterEdit)
    }
}

