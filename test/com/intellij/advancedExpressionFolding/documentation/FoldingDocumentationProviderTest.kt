package com.intellij.advancedExpressionFolding.documentation

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.openapi.application.ReadAction
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.util.PsiTreeUtil
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FoldingDocumentationProviderTest : BaseTest() {
    @Test
    fun getterDocumentationRendersBeforeAfter() {
        fixture.configureByText(
            "GetterDoc.java",
            """
            class GetterDoc {
                String name;
                String getName() { return name; }
                void test(GetterDoc doc) {
                    doc.getName();
                }
            }
            """.trimIndent()
        )

        val offset = fixture.editor.document.text.indexOf("getName();")
        assertTrue(offset >= 0)
        val call = ReadAction.compute<PsiMethodCallExpression, RuntimeException> {
            val element = fixture.file.findElementAt(offset)
            PsiTreeUtil.getParentOfType(element, PsiMethodCallExpression::class.java)
        }!!

        val provider = FoldingDocumentationProvider()
        val documentation = ReadAction.compute<String?, RuntimeException> {
            provider.generateDoc(call, call)
        }

        assertNotNull(documentation)
        val html = documentation!!
        assertTrue(html.contains("doc.getName()"))
        assertTrue(html.contains("doc.name"))
        assertTrue(html.contains("getSetExpressionsCollapse"))
    }
}
