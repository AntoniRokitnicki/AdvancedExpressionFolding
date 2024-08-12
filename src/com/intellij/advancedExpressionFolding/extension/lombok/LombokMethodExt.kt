package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.SimpleExpression
import com.intellij.advancedExpressionFolding.extension.*
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.ClassLevelAnnotation
import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt.findMethodType
import com.intellij.advancedExpressionFolding.extension.lombok.LombokInterfaceFoldingAnnotation.*
import com.intellij.advancedExpressionFolding.extension.lombok.MethodType.*
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethod

enum class LombokInterfaceFoldingAnnotation(val annotation: String) {
    LOMBOK_INTERFACE_GETTER("@Getter"),
    LOMBOK_INTERFACE_SETTER("@Setter"),
    LOMBOK_INTERFACE_FINDER("@Finder"),
}

data class MethodLevelAnnotation(
    val methodAnnotation: LombokInterfaceFoldingAnnotation,
)

object LombokMethodExt : GenericCallback<PsiMethod, List<MethodLevelAnnotation>> {
    override val callbackKey: Key<() -> List<MethodLevelAnnotation>> by lazy {
        Key.create("lombok-method-callback")
    }

    fun PsiClass.interfaceSupport(): List<ClassLevelAnnotation>? {
        val k = methods
        val methodsNotStatic = methodsNotStatic
        val filterNot = methodsNotStatic.filterNot {
            isInterfaceDefault()
        }
        val r = filterNot.mapNotNull { method ->
            method.findMethodType().takeIf {
                it == GETTER || it == SETTER || it == FINDER
            }?.let { type ->
                val e = when (type) {
                    GETTER -> LOMBOK_INTERFACE_GETTER
                    SETTER -> LOMBOK_INTERFACE_SETTER
                    FINDER -> LOMBOK_INTERFACE_FINDER
                    else -> null
                }
                //TODO: better enum conversion, maybe a custom type?
                initCallback(method, listOf(MethodLevelAnnotation(e!!)))
            }
        }
        //TODO: dont join getter and setter, since method references are needed

        return null
    }

    fun PsiMethod.isFinder() = name.startsWith("find") && name.length > "find".length
            && parameterList.parameters.size == 1 && name.contains("By")


    fun PsiMethod.addInterfaceAnnotations(
        methodLevelAnnotations: MethodLevelAnnotation,
        id: PsiIdentifier,
        list: MutableList<Expression?>
    ) {
        val type = methodLevelAnnotations.methodAnnotation
        if (type == LOMBOK_INTERFACE_FINDER) {

            list += addAnnotationByLastCharOfPrevWhitespace(type)
            id.run {
                fun extractTagAndName(input: String): Pair<String, String>? {
                    val regex = "find(\\w+)By(\\w*)".toRegex(RegexOption.IGNORE_CASE)
                    val matchResult = regex.find(input)

                    return matchResult?.let {
                        val (tag, name) = it.destructured
                        Pair(tag.decapitalize(), name.decapitalize())
                    }
                }
                extractTagAndName(name)?.run {
                    val (tag, by) = this
                    println("tag = $tag")
                    println("by = $by")
                    //TODO: fold on first char
                    list += expr(tag)

                    list += parameterList.parameters.first()?.takeIf {
                        by != it.name
                    }?.identifier
                        ?.run {
                            expr(by)
                        }
                }
            }

            return
        }

        //TODO: support @Nullable?
        val name = this.guessPropertyName()
        val getName = id.text

        list += id.run {
            fun countCharDifferencesForGetterAndField(getName: String, name: String) =
                getName.length - name.reversed().zip(getName.reversed())
                    .indexOfFirst { (c1, c2) -> c1 != c2 }
            val diffCount = countCharDifferencesForGetterAndField(getName, name)
            compressMethodNameByFirstChar(name, diffCount)
        }

        list += addAnnotationByLastCharOfPrevWhitespace(type)

        if (type == LOMBOK_INTERFACE_GETTER) list += this.parameterList.exprHide()
        else if (type == LOMBOK_INTERFACE_SETTER) {
            val param = this.parameterList.parameters.firstOrNull()?.type?.presentableText
            list += param?.let {
                this.returnTypeElement?.expr(it)
            }
            list += this.parameterList.exprHide()
        }
    }

    /**
     *  Add @Getter annotation before the method's start, at the last character of the preceding whitespace
     */
    private fun PsiMethod.addAnnotationByLastCharOfPrevWhitespace(methodAnnotation: LombokInterfaceFoldingAnnotation): SimpleExpression? {
        val element = docComment?.nextWhiteSpace() ?: prevWhiteSpace() ?: return null
        return expr("${methodAnnotation.annotation} ", textRange = element.textRangeChar(PsiElement::end, -1, 0))
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