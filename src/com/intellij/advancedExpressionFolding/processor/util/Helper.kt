package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.*
import java.util.function.Predicate
import java.util.regex.Matcher
import java.util.stream.Stream

object Helper {
    @JvmStatic
    @Nullable
    fun getDocument(element: PsiElement): Document? {
        val project: Project = element.getProject()
        val psiFile: PsiFile = element.getContainingFile()
        val psiDocumentManager: PsiDocumentManager = PsiDocumentManager.getInstance(project)
        return psiDocumentManager.getDocument(psiFile)
    }

    @JvmStatic
    fun isReferenceToReference(referenceExpression: PsiReferenceExpression?, reference: PsiReference?): Boolean {
        return if (reference != null) {
            val element = reference.resolve()
            referenceExpression != null && element != null && referenceExpression.isReferenceTo(element)
        } else {
            false
        }
    }

    @JvmStatic
    @Nullable
    fun findSameQualifier(@NotNull element: PsiElement, @NotNull qualifier: PsiElement): PsiElement? {
        if (element is PsiStatement && element.getFirstChild() != null) {
            return findSameQualifier(element.getFirstChild(), qualifier)
        }
        if (equal(qualifier, element)) {
            return element
        }
        if (element is PsiMethodCallExpression && element.getMethodExpression().getQualifierExpression() != null) {
            val q = element.getMethodExpression().getQualifierExpression()
            if (q != null) {
                return findSameQualifier(q, qualifier)
            }
        }
        if (element is PsiReferenceExpression && element.getQualifierExpression() != null) {
            val q = element.getQualifierExpression()
            if (q != null) {
                return findSameQualifier(q, qualifier)
            }
        }
        return null
    }

    @JvmStatic
    fun startsWith(string: String?, prefix: String): Boolean {
        return string != null && string.startsWith(prefix)
    }

    @JvmStatic
    fun equal(e1: PsiElement?, e2: PsiElement?): Boolean {
        return if (e2 is PsiReferenceExpression && e1 is PsiReferenceExpression) {
            Objects.equals(e2.getReferenceName(), e1.getReferenceName()) && isReferenceToReference(e2, e1)
        } else if (e2 is PsiMethodCallExpression && e1 is PsiMethodCallExpression) {
            equal(e2.getMethodExpression(), e1.getMethodExpression()) && equal(e2.getMethodExpression().getQualifierExpression(), e1.getMethodExpression().getQualifierExpression())
        } else {
            false
        }
    }

    @JvmStatic
    fun calculateIfFinal(element: PsiVariable): Boolean {
        val modifiers = element.getModifierList()
        if (modifiers != null) {
            var isFinal = modifiers.hasExplicitModifier(PsiModifier.FINAL)
            if (!isFinal) {
                var body: PsiElement = if (element.getParent() is PsiDeclarationStatement)
                    element.getParent().getParent()
                else if (element.getParent() is PsiLoopStatement)
                    (element.getParent() as PsiLoopStatement).getBody()!!
                else
                    element.getParent()
                if (body is PsiLoopStatement) {
                    body = body.getBody()!!
                }
                val references = SyntaxTraverser.psiTraverser(body)
                    .filter { e ->
                        e is PsiAssignmentExpression && e.getLExpression() is PsiReferenceExpression && (e.getLExpression() as PsiReferenceExpression).isReferenceTo(element)
                                || e is PsiPostfixExpression && (e.getOperationSign().getText() == "++" || e.getOperationSign().getText() == "--") && e.getOperand() is PsiReferenceExpression && (e.getOperand() as PsiReferenceExpression).isReferenceTo(element)
                    }
                    .toList()
                if (references.isEmpty()) {
                    isFinal = true
                }
            }
            return isFinal
        }
        return false
    }

    @JvmStatic
    fun findChildExpressions(element: PsiElement, expressions: MutableList<Expression>, document: Document) {
        for (child in element.getChildren()) {
            val expression = BuildExpressionExt.getNonSyntheticExpression(child, document)
            if (expression != null) {
                expressions.add(expression)
            }
            if (expression == null || !expression.getTextRange().equals(child.getTextRange())) {
                findChildExpressions(child, expressions, document)
            }
        }
    }

    @JvmStatic
    @NotNull
    fun eraseGenerics(signature: String): String {
        var result = signature
        var m: Matcher = Consts.GENERICS_PATTERN.matcher(result)
        while (m.find()) {
            result = m.replaceAll("")
            m = Consts.GENERICS_PATTERN.matcher(result)
        }
        return result
    }

    @JvmStatic
    fun isSupportedClass(element: PsiElement): Boolean {
        val reference = element.getReference()
        if (reference != null) {
            val e = reference.resolve()
            if (e is PsiField) {
                val psiClass = e.getContainingClass()
                if (psiClass != null && psiClass.getQualifiedName() != null) {
                    return Consts.SUPPORTED_CLASSES.contains(eraseGenerics(psiClass.getQualifiedName()))
                }
            }
        }
        return false
    }

    @JvmStatic
    fun findDot(document: Document, position: Int, i: Int, includeNewLines: Boolean): Int {
        var pos = position
        var offset = 0
        while (Math.abs(offset) < 100 && pos > 0 && pos < document.getText().length) {
            pos += i
            offset += i
            if (charAt(document, pos) == '.') {
                break
            }
            if (!Character.isWhitespace(charAt(document, pos))) {
                return Integer.MAX_VALUE
            }
        }
        if (includeNewLines) {
            var offsetWithNewLine = offset
            do {
                pos += i
                offsetWithNewLine += i
                if (i < 0 && charAt(document, pos) == '\n') {
                    offset = offsetWithNewLine
                } else if (i > 0 && Character.isWhitespace(charAt(document, pos))) {
                    offset = offsetWithNewLine
                }
            } while (Math.abs(offsetWithNewLine) < 100 && pos > 0 && pos < document.getText().length && Character.isWhitespace(charAt(document, pos)))
        }
        return if (Math.abs(offset) >= 100) Integer.MAX_VALUE else offset
    }

    @JvmStatic
    fun charAt(document: Document, position: Int): Char {
        return document.getText(TextRange.create(position, position + 1)).charAt(0)
    }

    @JvmStatic
    @Nullable
    fun getSlicePosition(parent: PsiElement, qualifierExpression: Expression, a2b: PsiBinaryExpression, document: Document): NumberLiteral? {
        val rOperand = a2b.getROperand()
        val lOperand = a2b.getLOperand()
        if (a2b.getOperationSign().getText() == "-" && rOperand != null && (lOperand is PsiMethodCallExpression || lOperand is PsiReferenceExpression)) {
            val s = BuildExpressionExt.getAnyExpression(rOperand, document)
            if (s is NumberLiteral) {
                val a2me: PsiReferenceExpression = if (lOperand is PsiMethodCallExpression) lOperand.getMethodExpression() else lOperand as PsiReferenceExpression
                val a2i = Stream.of(a2me.getChildren()).filter { c -> c is PsiIdentifier }.findAny()
                if (a2i.isPresent && (a2i.get().getText() == "length" || a2i.get().getText() == "size")) {
                    val slicePosition = s.getNumber().toInt()
                    val offset = findDot(document, a2b.getTextRange().getStartOffset(), -1, false)
                    if (offset < Integer.MAX_VALUE) {
                        return NumberLiteral(parent, TextRange.create(a2b.getTextRange().getStartOffset() + offset, a2b.getTextRange().getEndOffset()), null, slicePosition, false)
                    }
                }
            }
        }
        return null
    }
}
