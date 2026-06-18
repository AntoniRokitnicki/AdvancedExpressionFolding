package com.intellij.advancedExpressionFolding.foldingGroup.parser

import com.intellij.advancedExpressionFolding.foldingGroup.FoldingGroupLanguage
import com.intellij.advancedExpressionFolding.foldingGroup.lexer.FoldingGroupTokenTypes
import com.intellij.advancedExpressionFolding.foldingGroup.psi.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

object FoldingGroupElementTypes {
  val FILE: IFileElementType = IFileElementType(FoldingGroupLanguage)

  val GROUP_MARKER = FoldingGroupElementType("GROUP_MARKER")
  val GROUP_OPEN = FoldingGroupElementType("GROUP_OPEN")
  val GROUP_NUMBER_NODE = FoldingGroupElementType("GROUP_NUMBER_NODE")
  val GROUP_COLON = FoldingGroupElementType("GROUP_COLON")
  val GROUP_QUOTE_OPEN = FoldingGroupElementType("GROUP_QUOTE_OPEN")
  val GROUP_TEXT = FoldingGroupElementType("GROUP_TEXT")
  val GROUP_QUOTE_CLOSE = FoldingGroupElementType("GROUP_QUOTE_CLOSE")
  val GROUP_CLOSE = FoldingGroupElementType("GROUP_CLOSE")
  val PLAIN_TEXT = FoldingGroupElementType("PLAIN_TEXT")

  val WHITE_SPACES: TokenSet = TokenSet.create(TokenType.WHITE_SPACE)
  val STRINGS: TokenSet = TokenSet.create(FoldingGroupTokenTypes.TEXT_CHUNK, FoldingGroupTokenTypes.ESCAPED_SEQUENCE)
  val COMMENTS: TokenSet = TokenSet.EMPTY

  fun createElement(node: ASTNode) = when (node.elementType) {
    GROUP_MARKER -> FoldingGroupMarker(node)
    GROUP_OPEN -> FoldingGroupLeaf(node)
    GROUP_NUMBER_NODE -> FoldingGroupNumber(node)
    GROUP_COLON -> FoldingGroupLeaf(node)
    GROUP_QUOTE_OPEN -> FoldingGroupLeaf(node)
    GROUP_TEXT -> FoldingGroupText(node)
    GROUP_QUOTE_CLOSE -> FoldingGroupLeaf(node)
    GROUP_CLOSE -> FoldingGroupLeaf(node)
    PLAIN_TEXT -> FoldingGroupPlainText(node)
    else -> FoldingGroupPsiElement(node)
  }
}
