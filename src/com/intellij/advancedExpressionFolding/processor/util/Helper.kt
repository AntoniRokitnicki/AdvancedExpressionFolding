package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.processor.argumentExpressions
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.codeInsight.PsiEquivalenceUtil
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

object Helper {

    fun isReferenceToReference(referenceExpression: PsiReferenceExpression?, reference: PsiReference?): Boolean {
        val element = reference?.resolve()
        return referenceExpression != null && element != null && referenceExpression.isReferenceTo(element)
    }

    fun findSameQualifier(element: PsiElement, qualifier: PsiElement): PsiElement? {
        if (element is PsiStatement && element.firstChild != null) {
            return findSameQualifier(element.firstChild, qualifier)
        }
        if (equal(qualifier, element)) {
            return element
        }
        if (element is PsiMethodCallExpression) {
            val qualifierExpression = element.methodExpression.qualifierExpression
            if (qualifierExpression != null) {
                return findSameQualifier(qualifierExpression, qualifier)
            }
        }
        if (element is PsiReferenceExpression) {
            val qualifierExpression = element.qualifierExpression
            if (qualifierExpression != null) {
                return findSameQualifier(qualifierExpression, qualifier)
            }
        }
        return null
    }

    fun startsWith(string: String?, prefix: String): Boolean = string?.startsWith(prefix) == true

    fun equal(e1: PsiElement?, e2: PsiElement?): Boolean {
        if (e1 === e2) {
            return true
        }
        if (e1 == null || e2 == null) {
            return false
        }
        return PsiEquivalenceUtil.areElementsEquivalent(e1, e2)
    }

    fun calculateIfFinal(element: PsiVariable): Boolean {
        val modifiers: PsiModifierList = element.modifierList ?: return false
        var isFinal = modifiers.hasExplicitModifier(PsiModifier.FINAL)
        if (!isFinal) {
            var body: PsiElement? = when (val parent = element.parent) {
                is PsiDeclarationStatement -> parent.parent
                is PsiLoopStatement -> parent.body
                else -> parent
            }
            if (body is PsiLoopStatement) {
                body = body.body
            }
            val references = SyntaxTraverser.psiTraverser(body)
                .filter { candidate ->
                    when (candidate) {
                        is PsiAssignmentExpression -> {
                            val lExpression = candidate.lExpression
                            lExpression is PsiReferenceExpression && lExpression.isReferenceTo(element)
                        }
                        is PsiPostfixExpression -> {
                            val sign = candidate.operationSign.text
                            (sign == "++" || sign == "--") &&
                                candidate.operand is PsiReferenceExpression &&
                                (candidate.operand as PsiReferenceExpression).isReferenceTo(element)
                        }
                        else -> false
                    }
                }
                .toList()
            if (references.isEmpty()) {
                isFinal = true
            }
        }
        return isFinal
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
        var result = signature
        var matcher = Consts.GENERICS_PATTERN.matcher(result)
        while (matcher.find()) {
            result = matcher.replaceAll("")
            matcher = Consts.GENERICS_PATTERN.matcher(result)
        }
        return result
    }

    fun isSupportedClass(element: PsiElement): Boolean {
        val resolved = element.reference?.resolve()
        if (resolved is PsiField) {
            val psiClass = resolved.containingClass
            if (psiClass?.qualifiedName != null) {
                return Consts.SUPPORTED_CLASSES.contains(eraseGenerics(psiClass.qualifiedName!!))
            }
        }
        return false
    }

    @JvmStatic
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

    private fun charAt(chars: CharSequence, position: Int): Char {
        if (position < 0 || position >= chars.length) {
            throw IndexOutOfBoundsException("Position $position is out of bounds for document length ${chars.length}")
        }
        return chars[position]
    }

    fun getSlicePosition(
        parent: PsiElement,
        qualifierExpression: Expression,
        a2b: PsiBinaryExpression,
        document: Document
    ): NumberLiteral? {
        val rOperand = a2b.rOperand ?: return null
        val lOperand = a2b.lOperand
        if (a2b.operationSign.text == "-" &&
            (lOperand is PsiMethodCallExpression || lOperand is PsiReferenceExpression)
        ) {
            val s = BuildExpressionExt.getAnyExpression(rOperand, document)
            if (s is NumberLiteral) {
                val methodExpression = when (lOperand) {
                    is PsiMethodCallExpression -> lOperand.methodExpression
                    is PsiReferenceExpression -> lOperand
                    else -> return null
                }
                val identifier = methodExpression.children.firstOrNull { it is PsiIdentifier } as? PsiIdentifier
                if (identifier != null && (identifier.text == "length" || identifier.text == "size") &&
                    methodExpression.qualifierExpression != null
                ) {
                    val qualifier = BuildExpressionExt.getAnyExpression(methodExpression.qualifierExpression!!, document)
                    if (qualifier == qualifierExpression) {
                        return NumberLiteral(
                            parent,
                            TextRange.create(a2b.operationSign.textRange.startOffset, a2b.textRange.endOffset),
                            null,
                            -s.number.toInt(),
                            false
                        )
                    }
                }
            }
        }
        return null
    }

    fun isSetter(text: String): Boolean {
        return text.startsWith("set") && text.length > 3 && text[3].isUpperCase()
    }

    fun hasOptionalChainOperations(element: PsiMethodCallExpression): Boolean {
        return findAncestorsUntilClass(element, PsiExpressionStatement::class.java).firstOrNull() != null
    }

    fun isGetter(element: PsiElement, expression: PsiMethodCallExpression): Boolean {
        return expression.argumentExpressions.isEmpty() && isGetter(element.text)
    }

    fun isGetter(name: String): Boolean = isGetterAux(name, "get") || isGetterAux(name, "is")

    fun isGetterAux(name: String?, prefix: String): Boolean {
        return startsWith(name, prefix) && name!!.length > prefix.length && name[prefix.length].isUpperCase()
    }

    fun isNamelessGetter(name: String, expression: PsiMethodCallExpression, method: PsiMethod?): Boolean {
        if (name != "get") {
            return false
        }
        if (expression.argumentExpressions.isNotEmpty()) {
            return false
        }
        return hasNonVoidReturn(expression, method)
    }

    fun isNamelessSetter(name: String, expression: PsiMethodCallExpression, method: PsiMethod?): Boolean {
        if (name != "set") {
            return false
        }
        if (expression.argumentExpressions.size != 1) {
            return false
        }
        return hasVoidReturn(expression, method)
    }

    fun hasVoidReturn(expression: PsiMethodCallExpression, method: PsiMethod?): Boolean {
        val returnType = method?.returnType ?: expression.type ?: return false
        return returnType == PsiTypes.voidType()
    }

    fun hasNonVoidReturn(expression: PsiMethodCallExpression, method: PsiMethod?): Boolean {
        val returnType = method?.returnType ?: expression.type ?: return false
        return returnType != PsiTypes.voidType()
    }

    fun guessNamelessPropertyName(): String = "!!"

    fun findAncestorsUntilClass(element: PsiElement, ancestorClass: Class<out PsiElement>): Sequence<PsiElement> {
        return findAncestorsUntil(element) { parent -> !ancestorClass.isInstance(parent) }
    }

    inline fun findAncestorsUntil(
        element: PsiElement,
        crossinline untilPredicate: (PsiElement) -> Boolean
    ): Sequence<PsiElement> {
        return generateSequence(element.parent) { it.parent }
            .takeWhile { untilPredicate(it) }
    }

    fun <T : PsiElement> findChildByTypeHierarchy(
        element: PsiElement,
        childClass: Class<T>,
        vararg children: Class<out PsiElement>
    ): T? {
        val classQueue = children.toList()
        val next = classQueue.firstOrNull() ?: return null
        for (child in element.children) {
            if (next.isInstance(child)) {
                return if (classQueue.size == 1) {
                    childClass.cast(child)
                } else {
                    val remaining = classQueue.drop(1).toTypedArray()
                    findChildByTypeHierarchy(child, childClass, *remaining)
                }
            }
        }
        return null
    }

    fun superscript(str: String): String? = map(str, Consts.SUPERSCRIPT_MAPPING)

    private fun map(str: String, mapping: Map<Char, Char>): String? {
        val builder = StringBuilder(str.length)
        for (ch in str) {
            val mapped = mapping[ch] ?: return null
            if (mapped != '❤') {
                builder.append(mapped)
            }
        }
        return builder.toString()
    }
}
