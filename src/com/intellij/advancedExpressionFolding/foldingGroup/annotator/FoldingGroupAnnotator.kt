package com.intellij.advancedExpressionFolding.foldingGroup.annotator

import com.intellij.advancedExpressionFolding.foldingGroup.highlighting.FoldingGroupColors
import com.intellij.advancedExpressionFolding.foldingGroup.lexer.FoldingGroupTokenTypes
import com.intellij.advancedExpressionFolding.foldingGroup.psi.FoldingGroupMarker
import com.intellij.advancedExpressionFolding.foldingGroup.psi.FoldingGroupText
import com.intellij.advancedExpressionFolding.foldingGroup.parser.FoldingGroupElementTypes
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class FoldingGroupAnnotator : Annotator {
  override fun annotate(element: PsiElement, holder: AnnotationHolder) {
    if (element is FoldingGroupMarker) {
      annotateMarker(element, holder)
    }
  }

  private fun annotateMarker(marker: FoldingGroupMarker, holder: AnnotationHolder) {
    val numberElement = marker.numberElement
    if (numberElement != null) {
      val numberText = numberElement.text
      if (numberText.length > 1 && numberText.startsWith('0')) {
        holder.newAnnotation(HighlightSeverity.ERROR, "Leading zeros are not allowed")
          .range(numberElement.textRange)
          .create()
      }
      holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
        .range(numberElement.textRange)
        .textAttributes(FoldingGroupColors.numberKey(marker.groupId))
        .create()
    } else {
      holder.newAnnotation(HighlightSeverity.ERROR, "Group number expected")
        .range(marker.textRange)
        .create()
    }

    val openQuote = marker.node.findChildByType(FoldingGroupElementTypes.GROUP_QUOTE_OPEN)
    val closeQuote = marker.node.findChildByType(FoldingGroupElementTypes.GROUP_QUOTE_CLOSE)
    if (openQuote != null && closeQuote == null) {
      holder.newAnnotation(HighlightSeverity.ERROR, "Closing quote is missing")
        .range(marker.textRange)
        .create()
    }

    val closeBracket = marker.closingBracket
    if (closeBracket == null) {
      holder.newAnnotation(HighlightSeverity.ERROR, "']' expected")
        .range(marker.textRange)
        .create()
    }

    val textElement = marker.quotedTextElement
    if (textElement != null) {
      annotateText(marker.groupId, textElement, holder)
    }

    val message = buildTooltip(marker)
    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
      .range(marker.textRange)
      .tooltip(message)
      .create()
  }

  private fun annotateText(groupId: Int, textElement: FoldingGroupText, holder: AnnotationHolder) {
    if (textElement.textLength == 0) {
      holder.newAnnotation(HighlightSeverity.WARNING, "Empty folding text")
        .range(textElement.parent.textRange)
        .create()
      return
    }

    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
      .range(textElement.textRange)
      .textAttributes(FoldingGroupColors.textKey(groupId))
      .create()

    val children = textElement.node.getChildren(null)
    for (child in children) {
      val elementType = child.elementType
      if (elementType == FoldingGroupTokenTypes.ESCAPED_SEQUENCE) {
        continue
      }
      if (elementType == FoldingGroupTokenTypes.TEXT_CHUNK) {
        val chunk = child.text
        var index = 0
        while (index < chunk.length) {
          if (chunk[index] == '\\') {
            val nextIndex = index + 1
            if (nextIndex >= chunk.length) {
              registerUnknownEscape(child.psi, index, index + 1, holder)
              break
            }
            val next = chunk[nextIndex]
            if (next != '\\' && next != '"' && next != 'n' && next != 't') {
              registerUnknownEscape(child.psi, index, nextIndex + 1, holder)
            }
            index = nextIndex + 1
          } else {
            index++
          }
        }
      }
    }
  }

  private fun registerUnknownEscape(element: PsiElement, start: Int, end: Int, holder: AnnotationHolder) {
    val base = element.textRange.startOffset
    val range = TextRange(base + start, base + end)
    holder.newAnnotation(HighlightSeverity.WARNING, "Unknown escape sequence")
      .range(range)
      .create()
  }

  private fun buildTooltip(marker: FoldingGroupMarker): String {
    val id = if (marker.groupId >= 0) marker.groupId.toString() else "?"
    val raw = marker.rawText
    val escaped = buildString {
      for (ch in raw) {
        when (ch) {
          '\n' -> append("\\n")
          '\t' -> append("\\t")
          '\r' -> { /* skip */ }
          '"' -> append("\\\"")
          else -> append(ch)
        }
      }
    }
    val preview = if (escaped.length > 40) escaped.substring(0, 37) + "…" else escaped
    return "Group $id • length: ${raw.length} • preview: \"$preview\""
  }
}
