package com.intellij.advancedExpressionFolding.discovery

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLambdaExpression
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiWhiteSpace
import java.util.ArrayDeque
import java.util.LinkedHashSet
import java.util.Locale

private const val MIN_SUBTREE_DEPTH = 2
private const val MAX_SUBTREE_DEPTH = 5
private const val MAX_NODES_PER_SUBTREE = 80

class PsiVectorExtractor(@Suppress("UNUSED_PARAMETER") private val project: Project) {

    private val encoder = FeatureEncoder(72)

    fun extractVectors(root: PsiElement): List<PsiFeatureVector> {
        val result = mutableListOf<PsiFeatureVector>()
        val contexts = collectContexts(root)
        contexts.forEach { context ->
            collectSubtrees(context, result)
        }
        return result
    }

    private fun collectContexts(root: PsiElement): Set<PsiElement> {
        val contexts = LinkedHashSet<PsiElement>()
        root.accept(object : com.intellij.psi.PsiRecursiveElementWalkingVisitor() {
            override fun visitElement(element: PsiElement) {
                when (element) {
                    is PsiMethod -> element.body?.let(contexts::add)
                    is PsiLambdaExpression -> element.body?.let(contexts::add)
                    is PsiCodeBlock -> contexts.add(element)
                }
                super.visitElement(element)
            }
        })
        return contexts
    }

    private fun collectSubtrees(context: PsiElement, sink: MutableList<PsiFeatureVector>) {
        val queue = ArrayDeque<Pair<PsiElement, Int>>()
        queue.add(context to 0)
        while (queue.isNotEmpty()) {
            val (element, depth) = queue.removeFirst()
            if (depth in MIN_SUBTREE_DEPTH..MAX_SUBTREE_DEPTH) {
                buildVector(element)?.let(sink::add)
            }
            if (depth < MAX_SUBTREE_DEPTH) {
                element.children
                    .filterNot(::isNoise)
                    .forEach { child -> queue.add(child to depth + 1) }
            }
        }
    }

    private fun buildVector(subtreeRoot: PsiElement): PsiFeatureVector? {
        val info = gatherStructuralInfo(subtreeRoot) ?: return null
        val categorical = mutableMapOf<String, Int>()
        info.nodeTypes.forEach { type -> categorical.merge("node:$type", 1, Int::plus) }
        info.methodTokens.forEach { token -> categorical.merge("method:$token", 1, Int::plus) }
        info.parentContext?.let { contextKey -> categorical["context:$contextKey"] = 1 }

        val numeric = mutableMapOf(
            "chainLength" to info.chainLength.toDouble(),
            "parameterCount" to info.parameterCount.toDouble()
        )

        val vector = encoder.encode(categorical, numeric)
        val sampleText = subtreeRoot.text
            .replace('\n', ' ')
            .replace('\r', ' ')
            .replace('\t', ' ')
            .split(' ')
            .filter(String::isNotBlank)
            .joinToString(separator = " ")
            .take(160)

        val metadata = PsiVectorMetadata(
            nodeTypes = info.nodeTypes,
            methodNameTokens = info.methodTokens,
            chainLength = info.chainLength,
            parameterCount = info.parameterCount,
            parentContext = info.parentContext,
            textHash = subtreeRoot.text.hashCode(),
            sampleText = sampleText
        )

        return PsiFeatureVector.fromElement(subtreeRoot, vector, metadata)
    }

    private fun gatherStructuralInfo(subtreeRoot: PsiElement): StructuralInfo? {
        val nodeTypes = mutableListOf<String>()
        val methodTokens = mutableListOf<String>()
        var chainLength = 0
        var parameterCount = 0
        val queue = ArrayDeque<Pair<PsiElement, Int>>()
        queue.add(subtreeRoot to 0)
        var visited = 0
        while (queue.isNotEmpty() && visited < MAX_NODES_PER_SUBTREE) {
            val (element, depth) = queue.removeFirst()
            if (isNoise(element)) {
                continue
            }
            visited++
            val type = element.node?.elementType?.toString() ?: element.javaClass.simpleName
            nodeTypes.add(type)
            if (element is PsiMethodCallExpression) {
                chainLength = maxOf(chainLength, computeChainLength(element))
                parameterCount = maxOf(parameterCount, element.argumentList.expressions.size)
                val referenceName = element.methodExpression.referenceName
                if (!referenceName.isNullOrBlank()) {
                    methodTokens.addAll(tokenise(referenceName))
                }
            }
            if (depth < MAX_SUBTREE_DEPTH) {
                element.children
                    .filterNot(::isNoise)
                    .forEach { child -> queue.add(child to depth + 1) }
            }
        }
        val parentContext = computeParentContext(subtreeRoot)
        return StructuralInfo(nodeTypes, methodTokens, chainLength, parameterCount, parentContext)
    }

    private fun computeParentContext(element: PsiElement): String? {
        var current: PsiElement? = element.parent
        while (current != null) {
            val context = when (current) {
                is com.intellij.psi.PsiIfStatement -> "if"
                is com.intellij.psi.PsiForStatement -> "for"
                is com.intellij.psi.PsiForeachStatement -> "foreach"
                is com.intellij.psi.PsiWhileStatement -> "while"
                is com.intellij.psi.PsiSwitchStatement -> "switch"
                is com.intellij.psi.PsiReturnStatement -> "return"
                is com.intellij.psi.PsiAssignmentExpression -> "assignment"
                else -> null
            }
            if (context != null) {
                return context
            }
            current = current.parent
        }
        return null
    }

    private fun computeChainLength(callExpression: PsiMethodCallExpression): Int {
        var count = 1
        var qualifier = callExpression.methodExpression.qualifierExpression
        while (qualifier is PsiMethodCallExpression) {
            count++
            qualifier = qualifier.methodExpression.qualifierExpression
        }
        return count
    }

    private fun tokenise(referenceName: String): List<String> {
        val normalized = referenceName.lowercase(Locale.US)
        val tokens = mutableListOf<String>()
        normalized.split('.', '_').forEach { part ->
            if (part.isBlank()) {
                return@forEach
            }
            tokens += splitCamelCase(part)
        }
        return tokens.filter { it.length > 1 }
    }

    private fun splitCamelCase(token: String): List<String> {
        if (token.isEmpty()) {
            return emptyList()
        }
        val result = mutableListOf<String>()
        var current = StringBuilder()
        token.forEach { char ->
            if (char.isUpperCase()) {
                if (current.isNotEmpty()) {
                    result.add(current.toString())
                    current = StringBuilder()
                }
                current.append(char.lowercaseChar())
            } else {
                current.append(char)
            }
        }
        if (current.isNotEmpty()) {
            result.add(current.toString())
        }
        return result
    }

    private fun isNoise(element: PsiElement): Boolean {
        return element is PsiWhiteSpace || element is PsiComment
    }

    private data class StructuralInfo(
        val nodeTypes: List<String>,
        val methodTokens: List<String>,
        val chainLength: Int,
        val parameterCount: Int,
        val parentContext: String?
    )
}
