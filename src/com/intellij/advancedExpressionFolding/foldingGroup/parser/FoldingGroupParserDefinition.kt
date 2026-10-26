package com.intellij.advancedExpressionFolding.foldingGroup.parser

import com.intellij.advancedExpressionFolding.foldingGroup.FoldingGroupFile
import com.intellij.advancedExpressionFolding.foldingGroup.lexer.FoldingGroupLexer
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.TokenSet

class FoldingGroupParserDefinition : ParserDefinition {
  override fun createLexer(project: Project) = FoldingGroupLexer()

  override fun createParser(project: Project): PsiParser = FoldingGroupParser()

  override fun getFileNodeType() = FoldingGroupElementTypes.FILE

  override fun getWhitespaceTokens(): TokenSet = FoldingGroupElementTypes.WHITE_SPACES

  override fun getCommentTokens(): TokenSet = FoldingGroupElementTypes.COMMENTS

  override fun getStringLiteralElements(): TokenSet = FoldingGroupElementTypes.STRINGS

  override fun createFile(viewProvider: FileViewProvider): PsiFile = FoldingGroupFile(viewProvider)

  override fun createElement(node: ASTNode): PsiElement = FoldingGroupElementTypes.createElement(node)

  override fun spaceExistenceTypeBetweenTokens(left: ASTNode, right: ASTNode): ParserDefinition.SpaceRequirements = ParserDefinition.SpaceRequirements.MAY
}
