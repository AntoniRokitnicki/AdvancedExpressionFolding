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

    fun getDocument(element: PsiElement): Document? {
        val project: Project = element.project
        val psiFile = element.containingFile
        val psiDocumentManager = PsiDocumentManager.getInstance(project)
        return psiDocumentManager.getDocument(psiFile)
    }

    fun isReferenceToReference(
        referenceExpression: PsiReferenceExpression?,
        reference: PsiReference?
    ): Boolean {
        return if (reference != null) {
            val element = reference.resolve()
            referenceExpression != null && element != null && referenceExpression.isReferenceTo(element)
        } else {
            false
        }
    }

    fun findSameQualifier(element: PsiElement, qualifier: PsiElement): PsiElement? {
        if (element is PsiStatement && element.firstChild != null) {
            return findSameQualifier(element.firstChild, qualifier)
        }
        if (equal(qualifier, element)) {
            return element
        }
        if (element is PsiMethodCallExpression && element.methodExpression.qualifierExpression != null) {
            val q = element.methodExpression.qualifierExpression
            if (q != null) {
                return findSameQualifier(q, qualifier)
            }
        }
        if (element is PsiReferenceExpression && element.qualifierExpression != null) {
            val q = element.qualifierExpression
            if (q != null) {
                return findSameQualifier(q, qualifier)
            }
        }
        return null
    }

    fun startsWith(string: String?, prefix: String): Boolean {
        return string != null && string.startsWith(prefix)
    }

    fun equal(e1: PsiElement?, e2: PsiElement?): Boolean {
        return if (e2 is PsiReferenceExpression && e1 is PsiReferenceExpression) {
            Objects.equals(e2.referenceName, e1.referenceName) &&
                isReferenceToReference(e2, e1)
        } else if (e2 is PsiMethodCallExpression && e1 is PsiMethodCallExpression) {
            equal(e2.methodExpression, e1.methodExpression) &&
                equal(e2.methodExpression.qualifierExpression, e1.methodExpression.qualifierExpression)
        } else {
            false
        }
    }

    fun calculateIfFinal(element: PsiVariable): Boolean {
        val modifiers = element.modifierList
        if (modifiers != null) {
            var isFinal = modifiers.hasExplicitModifier(PsiModifier.FINAL)
            if (!isFinal) {
                var body: PsiElement = if (element.parent is PsiDeclarationStatement)
                    element.parent.parent
                else if (element.parent is PsiLoopStatement)
                    (element.parent as PsiLoopStatement).body
                else element.parent
                if (body is PsiLoopStatement) {
                    body = body.body
                }
                val references = SyntaxTraverser.psiTraverser(body)
                    .filter { e: PsiElement? ->
                        e is PsiAssignmentExpression && e.lExpression is PsiReferenceExpression &&
                            (e.lExpression as PsiReferenceExpression).isReferenceTo(element) ||
                            e is PsiPostfixExpression &&
                            (e.operationSign.text == "++" || e.operationSign.text == "--") &&
                            e.operand is PsiReferenceExpression &&
                            (e.operand as PsiReferenceExpression).isReferenceTo(element)
                    }.toList()
                if (references.isEmpty()) {
                    isFinal = true
                }
            }
            return isFinal
        }
        return false
    }

    fun findChildExpressions(element: PsiElement, expressions: MutableList<Expression>, document: Document) {
        for (child in element.children) {
            val expression = BuildExpressionExt.getNonSyntheticExpression(child, document)
            if (expression != null) {
                expressions.add(expression)
            }
            if (expression == null || expression.textRange != child.textRange) {
                findChildExpressions(child, expressions, document)
            }
        }
    }

    fun eraseGenerics(signature: String): String {
        var s = signature
        var m: Matcher = Consts.GENERICS_PATTERN.matcher(s)
        while (m.find()) {
            s = m.replaceAll("")
            m = Consts.GENERICS_PATTERN.matcher(s)
        }
        return s
    }

    fun isSupportedClass(element: PsiElement): Boolean {
        val reference = element.reference
        if (reference != null) {
            val e = reference.resolve()
            if (e is PsiField) {
                val psiClass = e.containingClass
                if (psiClass != null && psiClass.qualifiedName != null) {
                    return Consts.SUPPORTED_CLASSES.contains(eraseGenerics(psiClass.qualifiedName!!))
                }
            }
        }
        return false
    }

    fun findDot(document: Document, position: Int, i: Int, includeNewLines: Boolean): Int {
        var pos = position
        var offset = 0
        while (Math.abs(offset) < 100 && pos > 0 && pos < document.text.length) {
            pos += i
            offset += i
            if (charAt(document, pos) == '.') {
                break
            }
            if (!Character.isWhitespace(charAt(document, pos))) {
                return Int.MAX_VALUE
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
            } while (Math.abs(offsetWithNewLine) < 100 && pos > 0 && pos < document.text.length &&
                Character.isWhitespace(charAt(document, pos)))
        }
        return if (Math.abs(offset) >= 100) Int.MAX_VALUE else offset
    }

    fun charAt(document: Document, position: Int): Char {
        return document.getText(TextRange.create(position, position + 1))[0]
    }

    fun getSlicePosition(
        parent: PsiElement,
        qualifierExpression: Expression,
        a2b: PsiBinaryExpression,
        document: Document
    ): NumberLiteral? {
        val rOperand = a2b.rOperand
        val lOperand = a2b.lOperand
        if (a2b.operationSign.text == "-" && rOperand != null &&
            (lOperand is PsiMethodCallExpression || lOperand is PsiReferenceExpression)
        ) {
            val s = BuildExpressionExt.getAnyExpression(rOperand, document)
            if (s is NumberLiteral) {
                val a2me = if (lOperand is PsiMethodCallExpression) {
                    lOperand.methodExpression
                } else {
                    lOperand as PsiReferenceExpression
                }
                val a2i = Stream.of(*a2me.children)
                    .filter { c: PsiElement? -> c is PsiIdentifier }
                    .findAny()
                if (a2i.isPresent &&
                    (a2i.get().text == "length" || a2i.get().text == "size") &&
                    a2me.qualifierExpression != null
                ) {
                    val a2qe = BuildExpressionExt.getAnyExpression(a2me.qualifierExpression!!, document)
                    if (a2qe == qualifierExpression) {
                        return NumberLiteral(
                            parent,
                            TextRange.create(
                                a2b.operationSign.textRange.startOffset,
                                a2b.textRange.endOffset
                            ),
                            null,
                            -s.number!!.toInt(),
                            false
                        )
                    }
                }
            }
        }
        return null
    }

    fun isSetter(text: String): Boolean {
        return text.startsWith("set") && text.length > 3 && Character.isUpperCase(text[3])
    }

    fun isPureMethodReference(element: PsiMethodCallExpression): Boolean {
        return findChildByTypeHierarchy(
            element,
            PsiMethodReferenceExpression::class.java,
            PsiExpressionList::class.java,
            PsiMethodReferenceExpression::class.java
        ).isPresent
    }

    fun hasOptionalChainOperations(element: PsiMethodCallExpression): Boolean {
        return findAncestorsUntilClass(element, PsiExpressionStatement::class.java).findAny().isPresent
    }

    fun isGetter(element: PsiElement, expression: PsiMethodCallExpression): Boolean {
        return expression.argumentList.expressions.isEmpty() && isGetter(element.text)
    }

    fun isGetter(name: String): Boolean {
        return isGetterAux(name, "get") || isGetterAux(name, "is")
    }

    fun isGetterAux(name: String?, prefix: String): Boolean {
        return startsWith(name, prefix) &&
            name!!.length > prefix.length &&
            Character.isUpperCase(name[prefix.length])
    }

    fun <T : PsiElement> findAncestorsUntilClass(
        element: PsiElement,
        ancestorClass: Class<T>
    ): Stream<PsiElement> {
        return findAncestorsUntil(element) { parent: PsiElement -> !ancestorClass.isInstance(parent) }
    }

    fun <T : PsiElement> findAncestorsUntil(
        element: PsiElement,
        untilPredicate: Predicate<PsiElement>
    ): Stream<PsiElement> {
        return Stream.iterate(element.parent, Objects::nonNull, PsiElement::getParent).takeWhile(untilPredicate)
    }

    @SafeVarargs
    fun <T : PsiElement> findChildByTypeHierarchy(
        element: PsiElement,
        childClass: Class<T>,
        vararg children: Class<out PsiElement>
    ): Optional<T> {
        val classQueue: LinkedList<Class<out PsiElement>> = LinkedList(listOf(*children))
        val next = classQueue.poll()
        for (child in element.children) {
            if (next != null && next.isInstance(child)) {
                return if (classQueue.isEmpty()) {
                    Optional.of(childClass.cast(child))
                } else {
                    findChildByTypeHierarchy(child, childClass, *classQueue.toTypedArray())
                }
            }
        }
        return Optional.empty()
    }

    fun superscript(str: String): String? {
        return map(str, Consts.SUPERSCRIPT_MAPPING)
    }

    private fun map(str: String, subscriptMapping: Map<Char, Char>): String? {
        val sb = StringBuilder(str.length)
        for (i in str.indices) {
            val c = subscriptMapping[str[i]]
            if (c == null) {
                return null
            } else if (c != '❤') {
                sb.append(c.toChar())
            }
        }
        return sb.toString()
    }
}

