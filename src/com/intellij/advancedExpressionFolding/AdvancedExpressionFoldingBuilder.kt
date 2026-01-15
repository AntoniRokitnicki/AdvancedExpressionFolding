package com.intellij.advancedExpressionFolding

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.getOrElse
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import arrow.core.raise.option
import arrow.core.raise.zipOrAccumulate
import arrow.optics.Lens
import com.google.common.collect.Lists
import com.google.common.collect.Sets
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.cache.CacheExt.invalidateExpired
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaFile

class AdvancedExpressionFoldingBuilder : FoldingBuilderEx(), IConfig by State()() {
    override fun buildFoldRegions(element: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val stateSnapshot = stateForInvocation(quick)
        val emptyResult = store.store(Expression.EMPTY_ARRAY, document)

        val context = prepareFoldingContext(element, document, stateSnapshot).getOrElse {
            return emptyResult
        }

        if (debugFolding) {
            preview(element, document)
        }

        val cachedDescriptors = when {
            stateSnapshot.memoryImprovement -> readCache(context, quick).fold({ null }, { it })
            else -> null
        }
        val foldingDescriptors = cachedDescriptors ?: collect(context)
        if (stateSnapshot.memoryImprovement && !quick && cachedDescriptors == null) {
            writeCache(context, foldingDescriptors)
        }
        return store.store(foldingDescriptors, document)
    }

    private fun isFoldingFile(file: PsiJavaFile) = file.name.endsWith("-folded.java")

    private fun readCache(
        context: FoldingContext,
        quick: Boolean
    ): Either<NonEmptyList<CacheIssue>, Array<FoldingDescriptor>> = either {
        zipOrAccumulate(
            { ensure(!quick) { CacheIssue.QuickPreview } },
            { ensure(!context.file.invalidateExpired(context.document, false)) { CacheIssue.Stale } },
            { ensureNotNull(context.file.getUserData(Keys.FULL_CACHE)) { CacheIssue.Miss } }
        ) { _, _, cached -> cached }
    }

    private fun writeCache(
        context: FoldingContext,
        foldingDescriptors: Array<FoldingDescriptor>
    ) {
        context.file.putUserData(Keys.FULL_CACHE, foldingDescriptors)
    }

    fun preview(element: PsiElement, document: Document): List<String> {
        val groupIds = Sets.newIdentityHashSet<FoldingGroup>()
        return collect(element, document).map { descriptor ->
            descriptor.group?.let(groupIds::add)
            buildString {
                append(descriptor.range.substring(document.text))
                append(" => ")
                append(descriptor.placeholderText)
                append('[')
                append(groupIds.size)
                append("-")
                append(descriptor.group?.run {
                    toString().substringAfterLast(".")
                } ?: "null")
                append(']')
            }
        }
    }

    private fun collect(context: FoldingContext): Array<FoldingDescriptor> =
        collect(context.file, context.document)

    private fun collect(
        element: PsiElement,
        document: Document
    ): Array<FoldingDescriptor> {
        //TODO: default list size based on file size
        val allDescriptors = Lists.newArrayListWithCapacity<FoldingDescriptor>(1_000)
        BuildExpressionExt.collectFoldRegionsRecursively(element, document, Sets.newIdentityHashSet(), allDescriptors)
        return allDescriptors.toTypedArray()
    }

    override fun getPlaceholderText(astNode: ASTNode) = null

    // TODO: Collapse everything by default but use these settings when actually building the folding descriptors
    override fun isCollapsedByDefault(astNode: ASTNode): Boolean {
        return try {
            option {
                val element = astNode.psi
                val document = ensureNotNull(
                    PsiDocumentManager.getInstance(element.project).getDocument(element.containingFile)
                )
                val expression = ensureNotNull(
                    BuildExpressionExt.getNonSyntheticExpression(element, document)
                )
                expression.isCollapsedByDefault()
            }.getOrElse { false }
        } catch (_: IndexNotReadyException) {
            false
        }
    }

    private val debugFolding = false
    private fun stateForInvocation(quick: Boolean): AdvancedExpressionFoldingSettings.State {
        val snapshot = AdvancedExpressionFoldingSettings.getInstance().state
        return memoryImprovementLens.modify(snapshot) { enabled -> enabled && !quick }
    }

    private fun prepareFoldingContext(
        element: PsiElement,
        document: Document,
        state: AdvancedExpressionFoldingSettings.State
    ): Either<NonEmptyList<FoldingEligibilityIssue>, FoldingContext> = either {
        val file = ensureNotNull(element.asInstance<PsiJavaFile>()) { FoldingEligibilityIssue.UnsupportedFile }
        zipOrAccumulate(
            { ensure(globalOnLens.get(state)) { FoldingEligibilityIssue.GloballyDisabled } },
            { ensure(!isFoldingFile(file)) { FoldingEligibilityIssue.FoldingPreview } },
            { ensure(file.isValid) { FoldingEligibilityIssue.InvalidFile } }
        ) { _, _, _ ->
            FoldingContext(file, document, state)
        }
    }

    private companion object {
        private val memoryImprovementLens = Lens(
            get = AdvancedExpressionFoldingSettings.State::memoryImprovement,
            set = { state, value -> state.copy(memoryImprovement = value) }
        )

        private val globalOnLens = Lens(
            get = AdvancedExpressionFoldingSettings.State::globalOn,
            set = { state, value -> state.copy(globalOn = value) }
        )
    }
}

private data class FoldingContext(
    val file: PsiJavaFile,
    val document: Document,
    val state: AdvancedExpressionFoldingSettings.State
)

private sealed interface FoldingEligibilityIssue {
    data object UnsupportedFile : FoldingEligibilityIssue
    data object GloballyDisabled : FoldingEligibilityIssue
    data object FoldingPreview : FoldingEligibilityIssue
    data object InvalidFile : FoldingEligibilityIssue
}

private sealed interface CacheIssue {
    data object QuickPreview : CacheIssue
    data object Stale : CacheIssue
    data object Miss : CacheIssue
}

var store: Storage = EmptyStorage
