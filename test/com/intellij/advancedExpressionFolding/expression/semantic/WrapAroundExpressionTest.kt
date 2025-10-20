package com.intellij.advancedExpressionFolding.expression.semantic

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.processor.nextWhiteSpace
import com.intellij.advancedExpressionFolding.processor.prevWhiteSpace
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiNewExpression
import com.intellij.psi.util.PsiTreeUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WrapAroundExpressionTest : BaseTest() {

    private val wrapAfterFileName = "WrapAfter.java"
    private val wrapAfterFileText = """
        class WrapAfter {
            void test() {
                Object value = new Object()
                        ;
            }
        }
    """.trimIndent()

    @Test
    fun textBeforeWithoutFoldingKeepsTrailingWhitespaceCharacter() {
        val file = fixture.configureByText(wrapAfterFileName, wrapAfterFileText)
        val document = fixture.editor.document
        val (placeholder, expectedRange, actualRange) = runReadAction {
            val newExpression = PsiTreeUtil.getParentOfType(
                file.findElementAt(file.text.indexOf("new Object()")),
                PsiNewExpression::class.java,
                false,
            ) ?: error("Expected new expression")

            val expressions = WrapAroundExpression.modifyChildren(
                emptyList(),
                newExpression,
                textBefore = "::",
                foldPrevWhiteSpace = false,
                textAfter = null,
                foldNextWhiteSpace = false,
                group = null,
            )

            val simpleExpression = expressions.single() as SimpleExpression
            val descriptor = simpleExpression.buildFoldRegions(simpleExpression.element, document, null).single()
            val whiteSpace = newExpression.prevWhiteSpace() ?: error("Expected whitespace before expression")
            Triple(
                descriptor.placeholderText,
                TextRange(whiteSpace.textRange.endOffset - 1, whiteSpace.textRange.endOffset),
                descriptor.range,
            )
        }

        assertEquals(" ::", placeholder)
        assertEquals(expectedRange, actualRange)
    }

    @Test
    fun textAfterWithoutFoldingKeepsLeadingWhitespaceCharacter() {
        val file = fixture.configureByText(wrapAfterFileName, wrapAfterFileText)
        val document = fixture.editor.document
        val (placeholder, expectedRange, range) = runReadAction {
            val newExpression = PsiTreeUtil.getParentOfType(
                file.findElementAt(file.text.indexOf("new Object()")),
                PsiNewExpression::class.java,
                false,
            ) ?: error("Expected new expression")

            val expressions = WrapAroundExpression.modifyChildren(
                emptyList(),
                newExpression,
                textBefore = null,
                foldPrevWhiteSpace = false,
                textAfter = "::",
                foldNextWhiteSpace = false,
                group = null,
            )

            val simpleExpression = expressions.single() as SimpleExpression
            val descriptor = simpleExpression.buildFoldRegions(simpleExpression.element, document, null).single()
            val whiteSpace = newExpression.nextWhiteSpace() ?: error("Expected whitespace after expression")
            val startOffset = whiteSpace.textRange.startOffset
            Triple(descriptor.placeholderText, TextRange(startOffset, startOffset + 1), descriptor.range)
        }

        assertEquals("::\n", placeholder)
        assertEquals(expectedRange, range)
    }

    @Test
    fun textAfterWithFoldingReplacesEntireWhitespace() {
        val file = fixture.configureByText(wrapAfterFileName, wrapAfterFileText)
        val document = fixture.editor.document
        val (placeholder, actualRange, expectedRange) = runReadAction {
            val newExpression = PsiTreeUtil.getParentOfType(
                file.findElementAt(file.text.indexOf("new Object()")),
                PsiNewExpression::class.java,
                false,
            ) ?: error("Expected new expression")

            val expressions = WrapAroundExpression.modifyChildren(
                emptyList(),
                newExpression,
                textBefore = null,
                foldPrevWhiteSpace = false,
                textAfter = "::",
                foldNextWhiteSpace = true,
                group = null,
            )

            val simpleExpression = expressions.single() as SimpleExpression
            val descriptor = simpleExpression.buildFoldRegions(simpleExpression.element, document, null).single()
            val whiteSpace = newExpression.nextWhiteSpace() ?: error("Expected whitespace after expression")
            Triple(descriptor.placeholderText, descriptor.range, whiteSpace.textRange)
        }

        assertEquals("::", placeholder)
        assertEquals(expectedRange, actualRange)
    }

    @Test
    fun foldingWhitespaceWithoutTextAfterRemovesWhitespace() {
        val file = fixture.configureByText(wrapAfterFileName, wrapAfterFileText)
        val document = fixture.editor.document
        val (placeholder, actualRange, expectedRange) = runReadAction {
            val newExpression = PsiTreeUtil.getParentOfType(
                file.findElementAt(file.text.indexOf("new Object()")),
                PsiNewExpression::class.java,
                false,
            ) ?: error("Expected new expression")

            val expressions = WrapAroundExpression.modifyChildren(
                emptyList(),
                newExpression,
                textBefore = null,
                foldPrevWhiteSpace = false,
                textAfter = null,
                foldNextWhiteSpace = true,
                group = null,
            )

            val expression = expressions.single()
            assertTrue(expression is SimpleExpression)
            val simpleExpression = expression as SimpleExpression
            val descriptor = simpleExpression.buildFoldRegions(simpleExpression.element, document, null).single()
            val whiteSpace = newExpression.nextWhiteSpace() ?: error("Expected whitespace after expression")
            Triple(descriptor.placeholderText, descriptor.range, whiteSpace.textRange)
        }

        assertEquals("", placeholder)
        assertEquals(expectedRange, actualRange)
    }

    @Test
    fun textBeforeAndAfterWithFoldingReplacesBothWhitespaceSegments() {
        val file = fixture.configureByText(wrapAfterFileName, wrapAfterFileText)
        val document = fixture.editor.document
        val result = runReadAction {
            val newExpression = PsiTreeUtil.getParentOfType(
                file.findElementAt(file.text.indexOf("new Object()")),
                PsiNewExpression::class.java,
                false,
            ) ?: error("Expected new expression")

            val expressions = WrapAroundExpression.modifyChildren(
                emptyList(),
                newExpression,
                textBefore = "::",
                foldPrevWhiteSpace = true,
                textAfter = "##",
                foldNextWhiteSpace = true,
                group = null,
            )

            val beforeExpression = expressions[0] as SimpleExpression
            val afterExpression = expressions[1] as SimpleExpression
            val beforeDescriptor = beforeExpression.buildFoldRegions(beforeExpression.element, document, null).single()
            val afterDescriptor = afterExpression.buildFoldRegions(afterExpression.element, document, null).single()
            val prevWhiteSpace = newExpression.prevWhiteSpace() ?: error("Expected whitespace before expression")
            val nextWhiteSpace = newExpression.nextWhiteSpace() ?: error("Expected whitespace after expression")

            listOf(
                Triple(beforeDescriptor.placeholderText, beforeDescriptor.range, prevWhiteSpace.textRange),
                Triple(afterDescriptor.placeholderText, afterDescriptor.range, nextWhiteSpace.textRange),
            )
        }

        val (beforeResult, afterResult) = result
        assertEquals("::", beforeResult.first)
        assertEquals(beforeResult.third, beforeResult.second)
        assertEquals("##", afterResult.first)
        assertEquals(afterResult.third, afterResult.second)
    }
}

