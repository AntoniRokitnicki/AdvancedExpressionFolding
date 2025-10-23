package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement

object DocumentUtil {

    fun getDocument(element: PsiElement): Document? {
        val project = element.project
        val psiFile = element.containingFile
        val psiDocumentManager = PsiDocumentManager.getInstance(project)
        return psiDocumentManager.getDocument(psiFile)
    }

    fun findDot(document: Document, positionStart: Int, direction: Int, includeNewLines: Boolean): Int {
        val chars = document.charsSequence
        val length = chars.length
        var position = positionStart
        var offset = 0
        while (kotlin.math.abs(offset) < 100 && position > 0 && position < length) {
            position += direction
            offset += direction
            if (charAt(chars, position) == '.') {
                break
            }
            if (!charAt(chars, position).isWhitespace()) {
                return Int.MAX_VALUE
            }
        }
        if (includeNewLines) {
            var offsetWithNewLine = offset
            do {
                position += direction
                offsetWithNewLine += direction
                if (direction < 0 && charAt(chars, position) == '\n') {
                    offset = offsetWithNewLine
                } else if (direction > 0 && charAt(chars, position).isWhitespace()) {
                    offset = offsetWithNewLine
                }
            } while (kotlin.math.abs(offsetWithNewLine) < 100 && position > 0 && position < length &&
                charAt(chars, position).isWhitespace())
        }
        if (kotlin.math.abs(offset) >= 100) {
            return Int.MAX_VALUE
        }
        return offset
    }

    fun charAt(document: Document, position: Int): Char = charAt(document.charsSequence, position)

    private fun charAt(chars: CharSequence, position: Int): Char {
        if (position < 0 || position >= chars.length) {
            throw IndexOutOfBoundsException("Position $position is out of bounds for document length ${chars.length}")
        }
        return chars[position]
    }
}
