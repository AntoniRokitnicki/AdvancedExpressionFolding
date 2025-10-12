package com.intellij.advancedExpressionFolding.foldingGroup.psi

import com.intellij.advancedExpressionFolding.foldingGroup.parser.FoldingGroupElementTypes
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

class FoldingGroupMarker(node: ASTNode) : FoldingGroupPsiElement(node) {
  val groupId: Int
    get() = findChildByClass(FoldingGroupNumber::class.java)?.intValue() ?: -1

  val rawText: String
    get() {
      val textNode = findChildByClass(FoldingGroupText::class.java) ?: return ""
      return buildString {
        textNode.children.forEach { element ->
          append(unescapeSegment(element.text))
        }
      }
    }

  val quotedTextElement: FoldingGroupText?
    get() = findChildByClass(FoldingGroupText::class.java)

  val numberElement: FoldingGroupNumber?
    get() = findChildByClass(FoldingGroupNumber::class.java)

  val openBracket: PsiElement?
    get() = node.findChildByType(FoldingGroupElementTypes.GROUP_OPEN)?.psi

  val closingBracket: PsiElement?
    get() = node.findChildByType(FoldingGroupElementTypes.GROUP_CLOSE)?.psi

  private fun unescapeSegment(segment: String): String {
    val builder = StringBuilder()
    var index = 0
    while (index < segment.length) {
      val c = segment[index]
      if (c == '\\' && index + 1 < segment.length) {
        val next = segment[index + 1]
        when (next) {
          '\\' -> builder.append('\\')
          '"' -> builder.append('"')
          'n' -> builder.append('\n')
          't' -> builder.append('\t')
          else -> {
            builder.append('\\')
            builder.append(next)
          }
        }
        index += 2
      } else {
        builder.append(c)
        index++
      }
    }
    return builder.toString().replace("\r", "")
  }
}
