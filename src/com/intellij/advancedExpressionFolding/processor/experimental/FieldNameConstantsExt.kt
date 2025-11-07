package com.intellij.advancedExpressionFolding.processor.experimental

import com.intellij.advancedExpressionFolding.expression.semantic.lombok.ClassAnnotationExpression
import com.intellij.advancedExpressionFolding.processor.isFinal
import com.intellij.advancedExpressionFolding.processor.isPrivate
import com.intellij.advancedExpressionFolding.processor.isProtected
import com.intellij.advancedExpressionFolding.processor.isPublic
import com.intellij.advancedExpressionFolding.processor.isStatic
import com.intellij.advancedExpressionFolding.processor.markIgnored
import com.intellij.advancedExpressionFolding.processor.prevWhiteSpace
import com.intellij.psi.CommonClassNames
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiEnumConstant
import com.intellij.psi.PsiField
import com.intellij.psi.PsiLiteralExpression
import java.util.Locale

private const val DEFAULT_INNER_TYPE_NAME = "Fields"

object FieldNameConstantsExt {

    fun fold(clazz: PsiClass): ClassAnnotationExpression? {
        val data = clazz.fieldNameConstantsData() ?: return null
        data.innerClass.markIgnored()

        val elementsToFold = listOfNotNull(
            data.innerClass.prevWhiteSpace(),
            data.innerClass as PsiElement,
        )

        return ClassAnnotationExpression(
            clazz,
            listOf(data.annotationText()),
            elementsToFold,
        )
    }

    private data class FieldNameConstantsData(
        val innerClass: PsiClass,
        val asEnum: Boolean,
    ) {
        fun annotationText(): String {
            val parameters = mutableListOf<String>()
            if (asEnum) {
                parameters += "asEnum = true"
            }

            innerClass.name?.takeIf { it != DEFAULT_INNER_TYPE_NAME }?.let {
                parameters += "innerTypeName = \"$it\""
            }

            buildLevelParameter()?.let { parameters += it }

            val suffix = if (parameters.isEmpty()) {
                ""
            } else {
                parameters.joinToString(prefix = "(", postfix = ")")
            }

            return "@FieldNameConstants$suffix"
        }

        private fun buildLevelParameter(): String? {
            return when {
                innerClass.isPublic() -> null
                innerClass.isProtected() -> "level = AccessLevel.PROTECTED"
                innerClass.isPrivate() -> "level = AccessLevel.PRIVATE"
                else -> "level = AccessLevel.PACKAGE"
            }
        }
    }

    private fun PsiClass.fieldNameConstantsData(): FieldNameConstantsData? {
        val outerFields = fields.filter { it.outerFieldCandidate() }
        if (outerFields.isEmpty()) {
            return null
        }

        val candidate = innerClasses.firstOrNull { it.isFieldNameConstantsInnerClass(outerFields) } ?: return null
        return FieldNameConstantsData(candidate, candidate.isEnum)
    }

    private fun PsiClass.isFieldNameConstantsInnerClass(outerFields: List<PsiField>): Boolean {
        if (name.isNullOrEmpty()) {
            return false
        }

        if (isInterface || isAnnotationType) {
            return false
        }

        if (isEnum) {
            val enumFields = fields.filterIsInstance<PsiEnumConstant>()
            if (enumFields.isEmpty()) {
                return false
            }

            val extraFields = fields.filterNot { it is PsiEnumConstant }
            if (extraFields.any { !it.name.isNullOrEmpty() && !it.name!!.startsWith("$") }) {
                return false
            }

            if (initializers.isNotEmpty() || innerClasses.isNotEmpty()) {
                return false
            }

            if (!enumFields.all { constant ->
                    val constantName = constant.name ?: return false
                    outerFields.any { matchesFieldName(it.name, constantName) }
                }) {
                return false
            }

            return true
        }

        if (!isStatic() || !isFinal()) {
            return false
        }

        val classFields = fields
        if (classFields.isEmpty()) {
            return false
        }

        if (classFields.any { !it.isFieldNameConstantField(outerFields) }) {
            return false
        }

        if (methods.isNotEmpty() || initializers.isNotEmpty() || innerClasses.isNotEmpty()) {
            return false
        }

        return true
    }

    private fun PsiField.isFieldNameConstantField(outerFields: List<PsiField>): Boolean {
        if (!isPublic() || !isStatic() || !isFinal()) {
            return false
        }

        if (type.canonicalText != CommonClassNames.JAVA_LANG_STRING) {
            return false
        }

        val literal = initializer as? PsiLiteralExpression ?: return false
        val value = literal.value as? String ?: return false

        return outerFields.any { matchesFieldName(value, it.name) }
    }

    private fun PsiField.outerFieldCandidate(): Boolean {
        if (name.isNullOrEmpty()) {
            return false
        }
        return !isStatic()
    }

    private fun matchesFieldName(value: String, fieldName: String?): Boolean {
        fieldName ?: return false

        if (value == fieldName) {
            return true
        }

        val uppercase = value.uppercase(Locale.ROOT)
        if (uppercase == fieldName) {
            return true
        }

        val snakeCase = value.toUpperSnakeCase()
        if (snakeCase == fieldName) {
            return true
        }

        return false
    }

    private fun String.toUpperSnakeCase(): String {
        if (isEmpty()) {
            return this
        }

        val result = StringBuilder(length * 2)
        forEachIndexed { index, char ->
            when {
                char == '_' -> result.append('_')
                char.isDigit() -> {
                    if (index > 0 && !this[index - 1].isDigit()) {
                        result.append('_')
                    }
                    result.append(char)
                }
                char.isUpperCase() -> {
                    if (index > 0) {
                        val previous = this[index - 1]
                        if (previous.isLowerCase() || previous.isDigit()) {
                            result.append('_')
                        } else if (previous.isUpperCase()) {
                            val next = getOrNull(index + 1)
                            if (next != null && next.isLowerCase()) {
                                result.append('_')
                            }
                        }
                    }
                    result.append(char)
                }
                else -> {
                    if (index > 0 && this[index - 1].isDigit()) {
                        result.append('_')
                    }
                    result.append(char.uppercaseChar())
                }
            }
        }
        return result.toString()
    }
}
