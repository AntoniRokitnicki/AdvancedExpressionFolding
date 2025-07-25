package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.SimpleExpression
import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.advancedExpressionFolding.extension.lombok.AnnotationExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt.findMethodType
import com.intellij.advancedExpressionFolding.extension.lombok.LombokInterfaceFoldingAnnotation.*
import com.intellij.advancedExpressionFolding.extension.lombok.LombokInterfaceFoldingAnnotation.Companion.fromMethodType
import com.intellij.advancedExpressionFolding.extension.lombok.MethodType.*
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethod

enum class LombokInterfaceFoldingAnnotation(
    val annotation: String,
    private val methodType: MethodType,
) {
    LOMBOK_INTERFACE_GETTER("@Getter", GETTER),
    LOMBOK_INTERFACE_SETTER("@Setter", SETTER),
    LOMBOK_INTERFACE_FIND_BY("@FindBy", FIND_BY);

    companion object {
        fun fromMethodType(methodType: MethodType) = entries.asSequence().firstOrNull {
            it.methodType == methodType
        }
    }
}


data class MethodLevelAnnotation(
    val methodAnnotation: LombokInterfaceFoldingAnnotation,
)

object LombokMethodExt : GenericCallback<PsiMethod, List<MethodLevelAnnotation>> {
    override val callbackKey: Key<() -> List<MethodLevelAnnotation>> by lazy {
        Key.create("lombok-method-callback")
    }

    fun PsiClass.interfaceSupport(): List<ClassLevelAnnotation>? {
        methodsNotStatic.toList().filter {
            it.body == null
        }.mapNotNull { method ->
            fromMethodType(method.findMethodType())
                ?.let { type ->
                    initCallback(method, listOf(MethodLevelAnnotation(type)))
                }
        }
        // IMPORTANT: Do not combine @Getter and @Setter at the class level,
        // as user might need to use method references individually.
        return null
    }

    fun PsiMethod.isFinder() = name.startsWith("find") && name.length > "find".length
            && parameterList.parameters.size == 1 && name.contains("By")


    fun PsiMethod.addInterfaceAnnotations(
        methodLevelAnnotations: MethodLevelAnnotation,
        id: PsiIdentifier,
    ): List<Expression?> {
        val list = exprList()
        val type = methodLevelAnnotations.methodAnnotation
        if (type == LOMBOK_INTERFACE_FIND_BY) {
            addFindBy(id, list, type)
        } else {
            addGetterAndSetter(id, list, type)
        }
        return list
    }

    private fun PsiMethod.addGetterAndSetter(
        id: PsiIdentifier,
        list: MutableList<Expression?>,
        type: LombokInterfaceFoldingAnnotation
    ) {
        //TODO: support @Nullable?
        val name = this.guessPropertyName()
        val getName = id.text

        list += id.run {
             val diffCount = countCharDifferencesForGetterAndField(getName, name)
            compressMethodNameByFirstChar(name, diffCount)
        }
        list += addAnnotationByLastCharOfPrevWhitespace(type)

        if (type == LOMBOK_INTERFACE_GETTER) {
            list += this.parameterList.exprHide()
        } else if (type == LOMBOK_INTERFACE_SETTER) {
            val param = this.parameterList.parameters.firstOrNull()?.type?.presentableText
            list += param?.let {
                this.returnTypeElement?.expr(it)
            }
            list += this.parameterList.exprHide()
        }
    }

    private fun PsiMethod.addFindBy(
        id: PsiIdentifier,
        list: MutableList<Expression?>,
        type: LombokInterfaceFoldingAnnotation
    ) {
        list += addAnnotationByLastCharOfPrevWhitespace(type)
        id.run {
            extractTagAndName(name)?.let { (tag, by) ->
                list += expr(tag.first().toString(), textRange = textRangeChar(PsiElement::start, 0, 5))
                val goBackBy = by.length + "by".length
                list += exprHide(textRange = textRangeChar(PsiElement::end, goBackBy * -1, 0))

                list += parameterList.parameters.first()?.takeIf {
                    by != it.name
                }?.identifier
                    ?.run {
                        expr(by)
                    }
            }
        }
    }

    fun countCharDifferencesForGetterAndField(getName: String, name: String) =
        getName.length - name.reversed().zip(getName.reversed())
            .indexOfFirst { (c1, c2) -> c1 != c2 }

    fun extractTagAndName(input: String): Pair<String, String>? {
        val regex = "find(\\w+)By(\\w+)".toRegex(RegexOption.IGNORE_CASE)
        val matchResult = regex.find(input)

        return matchResult?.takeIf {
            it.groupValues.size == 3
        }?.let {
            val (tag, name) = it.destructured
            Pair(tag.replaceFirstChar(Char::lowercase), name.replaceFirstChar(Char::lowercase))
        }
    }

    /**
     *  Add @Getter annotation before the method's start, at the last character of the preceding whitespace
     */
    private fun PsiMethod.addAnnotationByLastCharOfPrevWhitespace(methodAnnotation: LombokInterfaceFoldingAnnotation): SimpleExpression? {
        return foldOnLastElement(methodAnnotation.annotation, docComment)
    }

    private fun PsiElement.foldOnLastElement(text: String, spaceable: PsiElement?): SimpleExpression? {
        val whiteSpace = spaceable?.nextWhiteSpace() ?: prevWhiteSpace() ?: return null
        return expr("$text ", textRange = whiteSpace.textRangeChar(PsiElement::end, -1, 0))
    }

    /**
     * Optimize method name folding to include only the necessary characters
     * For example, in "getName", fold "getName" to "n" by removing "get" to simplify the representation
     * This ensures that the method name remains clickable
     */
    private fun PsiIdentifier.compressMethodNameByFirstChar(
        name: String,
        diffCount: Int
    ) = expr(name.first().toString(), textRange = textRangeChar(PsiElement::start, 0, diffCount))

}

