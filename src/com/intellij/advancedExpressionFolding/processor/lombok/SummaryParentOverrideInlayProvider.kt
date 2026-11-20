package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.processor.lombok.SummaryParentOverrideExt.overriddenMethodNotes
import com.intellij.advancedExpressionFolding.processor.lombok.SummaryParentOverrideExt.parentOverrideLabels
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.codeInsight.hints.declarative.InlayHintsCollector
import com.intellij.codeInsight.hints.declarative.InlayHintsProvider
import com.intellij.codeInsight.hints.declarative.InlayTreeSink
import com.intellij.codeInsight.hints.declarative.InlineInlayPosition
import com.intellij.codeInsight.hints.declarative.SharedBypassCollector
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiJavaFile

/**
 * Shows the override summary after each parent name in `extends` / `implements`, and the
 * `// overrides from X` note at the body brace of each `@Override` method.
 *
 * Inlays rather than folds: a fold region cannot be empty, so it would have to swallow a character (the name's
 * last letter, the brace) and repaint it as placeholder text, which clashes with other folds hiding that character.
 */
class SummaryParentOverrideInlayProvider : InlayHintsProvider {

    override fun createCollector(file: PsiFile, editor: Editor): InlayHintsCollector? {
        val state = AdvancedExpressionFoldingSettings.getInstance().state
        return if (file is PsiJavaFile && state.globalOn && state.summaryParentOverride) Collector else null
    }

    private object Collector : SharedBypassCollector {
        override fun collectFromElement(element: PsiElement, sink: InlayTreeSink) {
            val clazz = element as? PsiClass ?: return
            clazz.parentOverrideLabels().forEach { (nameElement, label) ->
                val afterName = InlineInlayPosition(nameElement.textRange.endOffset, relatedToPrevious = true)
                // the platform cuts long inlay text with an ellipsis, so the tooltip carries the whole list
                sink.addPresentation(afterName, tooltip = label, hasBackground = true) {
                    text(label)
                }
            }
            clazz.overriddenMethodNotes().forEach { (brace, note) ->
                val afterBrace = InlineInlayPosition(brace.textRange.endOffset, relatedToPrevious = true)
                sink.addPresentation(afterBrace, hasBackground = true) {
                    text(note)
                }
            }
        }
    }
}
