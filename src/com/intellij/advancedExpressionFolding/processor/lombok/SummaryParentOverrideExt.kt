package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.isOverride
import com.intellij.advancedExpressionFolding.processor.one
import com.intellij.psi.*

private data class ParentWithOverrides(
    val nameElement: PsiIdentifier,
    val overriddenMethods: List<PsiMethod>
)

object SummaryParentOverrideExt {

    /**
     * Pairs each parent name in `extends` / `implements` with its override summary,
     * e.g. `(2-equals, hashCode)` or `(nothing overridden)`.
     */
    fun PsiClass.parentOverrideLabels(): List<Pair<PsiIdentifier, String>> =
        parentsWithOverrides().map { parent ->
            val methodNames = parent.overriddenMethods.map { method -> method.name }
            val label = when {
                methodNames.isNotEmpty() -> "(${methodNames.size}-${methodNames.joinToString(", ")})"
                else -> "(nothing overridden)"
            }
            parent.nameElement to label
        }

    private fun PsiClass.parentsWithOverrides(): List<ParentWithOverrides> =
        sequenceOf(extendsList, implementsList).filterNotNull().flatMap { parents ->
            parents.referenceElements.zip(parents.referencedTypes).mapNotNull { (reference, type) ->
                val nameElement = reference.referenceNameElement.asInstance<PsiIdentifier>() ?: return@mapNotNull null
                //TODO: support sharedMethod from GrandparentClass as well here
                val overriddenMethods = type.resolve()?.methods?.filter { method ->
                    findMethodBySignature(method, false) != null
                } ?: emptyList()
                ParentWithOverrides(nameElement, overriddenMethods)
            }
        }.toList()

    /**
     * Pairs the body brace of each `@Override` method with its note, e.g. `// overrides from Shape`: the closing
     * brace of a one-statement body, the opening brace otherwise. When several parents declare the method, the
     * last one in `extends` / `implements` order wins.
     */
    fun PsiClass.overriddenMethodNotes(): List<Pair<PsiJavaToken, String>> {
        val parentNameByOwnMethod = parentsWithOverrides().flatMap { parent ->
            parent.overriddenMethods.mapNotNull { parentMethod ->
                findMethodBySignature(parentMethod, false)?.let { ownMethod -> ownMethod to parent.nameElement.text }
            }
        }.toMap()
        return parentNameByOwnMethod.mapNotNull { (ownMethod, parentName) ->
            val body = ownMethod.body?.takeIf { ownMethod.annotations.any { it.isOverride() } } ?: return@mapNotNull null
            val brace = (if (body.statements.one) body.rBrace else body.lBrace) ?: return@mapNotNull null
            brace to "// overrides from $parentName"
        }
    }
}


