package com.intellij.advancedExpressionFolding.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.MessageType
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class ReportBrokenFoldingAction : AnAction(), DumbAware {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = event.getData(CommonDataKeys.PSI_FILE) ?: return

        PsiDocumentManager.getInstance(project).commitDocument(editor.document)

        val note = Messages.showInputDialog(
            project,
            "Describe what is wrong with the folding for the selected code:",
            "Report Broken Folding",
            null
        )?.trim()?.takeIf { it.isNotEmpty() } ?: return

        val selectionModel = editor.selectionModel
        val selectionRange = if (selectionModel.hasSelection() &&
            selectionModel.selectionStart != selectionModel.selectionEnd
        ) {
            TextRange(selectionModel.selectionStart, selectionModel.selectionEnd)
        } else {
            null
        }
        val caretOffset = if (selectionRange == null) editor.caretModel.offset else null

        val startElement = selectionRange?.let { psiFile.findElementAt(it.startOffset) }
        val endElement = selectionRange?.let {
            val endOffset = (it.endOffset - 1).coerceAtLeast(it.startOffset)
            psiFile.findElementAt(endOffset)
        }
        val caretElement = if (selectionRange == null) {
            psiFile.findElementAt(editor.caretModel.offset)
                ?: psiFile.findElementAt((editor.caretModel.offset - 1).coerceAtLeast(0))
        } else {
            null
        }

        val anchor = when {
            startElement != null && endElement != null ->
                PsiTreeUtil.findCommonParent(startElement, endElement) ?: startElement ?: endElement
            startElement != null -> startElement
            endElement != null -> endElement
            caretElement != null -> caretElement
            else -> psiFile
        }

        val psiTreeText = buildPsiTree(anchor, selectionRange, caretOffset).takeIf { it.isNotBlank() }

        val htmlContent = buildString {
            append("<html>")
            append("<b>Notes:</b><br/>")
            append(StringUtil.escapeXmlEntities(note).replace("\n", "<br/>"))
            if (psiTreeText != null) {
                append("<br/><br/><b>Selected PSI:</b><br/><pre>")
                append(StringUtil.escapeXmlEntities(psiTreeText))
                append("</pre>")
            }
            append("</html>")
        }

        val balloon = JBPopupFactory.getInstance()
            .createHtmlTextBalloonBuilder(htmlContent, MessageType.INFO, null)
            .setHideOnClickOutside(true)
            .setHideOnKeyOutside(true)
            .setHideOnAction(true)
            .setCloseButtonEnabled(true)
            .createBalloon()

        val popupLocation = JBPopupFactory.getInstance().guessBestPopupLocation(editor)
        balloon.show(popupLocation, Balloon.Position.below)
    }

    private fun buildPsiTree(root: PsiElement, selectionRange: TextRange?, caretOffset: Int?): String {
        val builder = StringBuilder()
        var nodeCount = 0
        var truncated = false

        fun appendElement(element: PsiElement, indent: String) {
            val range = element.textRange
            if (!shouldInclude(range, selectionRange, caretOffset)) {
                return
            }
            if (nodeCount >= MAX_TREE_NODES) {
                if (!truncated) {
                    builder.append(indent).append("...truncated...").append('\n')
                    truncated = true
                }
                return
            }
            nodeCount++

            val className = element.javaClass.simpleName.ifEmpty {
                element.javaClass.name.substringAfterLast('.')
            }
            builder.append(indent).append(className)
            element.node?.elementType?.let { builder.append(" (").append(it.toString()).append(')') }
            range?.let { builder.append(" [").append(it.startOffset).append(", ").append(it.endOffset).append(")") }

            element.text?.trim()?.takeIf { it.isNotEmpty() }?.let {
                val preview = StringUtil.shortenTextWithEllipsis(it.replace("\n", "\\n"), 80, 0)
                if (preview.isNotEmpty()) {
                    builder.append(" \"").append(preview).append('\"')
                }
            }
            builder.append('\n')

            val childIndent = indent + "  "
            for (child in element.children) {
                appendElement(child, childIndent)
                if (truncated) {
                    break
                }
            }
        }

        appendElement(root, "")
        return builder.toString().trimEnd()
    }

    private fun shouldInclude(range: TextRange?, selectionRange: TextRange?, caretOffset: Int?): Boolean {
        if (range == null) {
            return false
        }
        return when {
            selectionRange != null ->
                selectionRange.startOffset < range.endOffset && range.startOffset < selectionRange.endOffset
            caretOffset != null -> range.contains(caretOffset)
            else -> true
        }
    }

    companion object {
        private const val MAX_TREE_NODES = 200
    }
}
