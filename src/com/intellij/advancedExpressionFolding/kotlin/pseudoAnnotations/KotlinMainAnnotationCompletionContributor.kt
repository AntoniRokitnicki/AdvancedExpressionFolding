package com.intellij.advancedExpressionFolding.kotlin.pseudoAnnotations

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IState
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.AutoCompletionPolicy
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.util.ProcessingContext
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtModifierList
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtNamedFunction

class KotlinMainAnnotationCompletionContributor(
    private val state: IState = getInstance().state,
    private val handler: KotlinMainAnnotationHandler = KotlinMainAnnotationHandler()
) : CompletionContributor(), IState by state {

    init {
        extend(
            CompletionType.BASIC,
            psiElement()
                .withParent(KtNameReferenceExpression::class.java)
                .withSuperParent(2, KtAnnotationEntry::class.java)
                .withSuperParent(3, KtModifierList::class.java)
                .withSuperParent(4, KtNamedFunction::class.java),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    if (!pseudoAnnotations) return

                    val lookup = LookupElementBuilder.create("Main")
                        .withLookupString("@Main")
                        .withPresentableText("@Main")
                        .withInsertHandler { insertionContext: InsertionContext, _ ->
                            handler.handle(insertionContext)
                        }
                        .withAutoCompletionPolicy(AutoCompletionPolicy.NEVER_AUTOCOMPLETE)

                    result.addElement(lookup)
                }
            }
        )
    }
}
