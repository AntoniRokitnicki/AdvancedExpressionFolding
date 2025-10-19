package com.intellij.advancedExpressionFolding.documentation

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingBuilder
import com.intellij.advancedExpressionFolding.isAdvancedExpressionFoldingGroup
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.openapi.util.TextRange
import com.intellij.xml.util.XmlStringUtil
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement

class FoldingDocumentationProvider : AbstractDocumentationProvider() {
    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
        val target = element ?: originalElement ?: return null
        val project = target.project
        val file = target.containingFile ?: return null
        val document = PsiDocumentManager.getInstance(project).getDocument(file) ?: return null

        return try {
            val builder = AdvancedExpressionFoldingBuilder()
            val descriptors = builder.buildFoldRegions(file, document, false)
                .filter(FoldingDescriptor::isAdvancedExpressionFoldingGroup)
            if (descriptors.isEmpty()) {
                return null
            }

            val expression = BuildExpressionExt.getNonSyntheticExpression(target, document) ?: return null
            if (!expression.textRange.contains(target.textRange)) {
                return null
            }
            if (!expression.supportsFoldRegions(document, null)) {
                return null
            }

            val expressionDescriptors = descriptors.filter { expression.textRange.contains(it.range) }
            if (expressionDescriptors.isEmpty()) {
                return null
            }

            val before = document.getText(expression.textRange)
            val after = applyPlaceholders(before, expression.textRange, expressionDescriptors)
            if (after == before) {
                return null
            }

            val settingKeys = ExpressionSettingLocator.settingKeysFor(expression)
            if (settingKeys.isEmpty()) {
                return null
            }

            renderDocumentation(settingKeys, before, after)
        } catch (_: IndexNotReadyException) {
            null
        }
    }

    private fun applyPlaceholders(
        originalText: String,
        range: TextRange,
        descriptors: List<FoldingDescriptor>
    ): String {
        if (descriptors.isEmpty()) {
            return originalText
        }
        val builder = StringBuilder(originalText)
        val relativeDescriptors = descriptors.sortedByDescending { it.range.startOffset }
        for (descriptor in relativeDescriptors) {
            val placeholder = descriptor.placeholderText ?: continue
            val start = descriptor.range.startOffset - range.startOffset
            val end = descriptor.range.endOffset - range.startOffset
            if (start < 0 || end < start || end > builder.length) {
                continue
            }
            builder.replace(start, end, placeholder)
        }
        return builder.toString()
    }

    private fun renderDocumentation(keys: Set<String>, before: String, after: String): String {
        val label = if (keys.size > 1) "Settings" else "Setting"
        val settingsHtml = keys.joinToString(separator = " ") { key ->
            val link = DocumentationLinks.urlFor(key)
            if (link != null) {
                "<code>$key</code>&nbsp;<a href=\"$link\">docs</a>"
            } else {
                "<code>$key</code>"
            }
        }
        val escapedBefore = XmlStringUtil.escapeString(before)
        val escapedAfter = XmlStringUtil.escapeString(after)

        val body = buildString {
            append("<h3>Advanced Expression Folding</h3>")
            append("<p>$label: $settingsHtml</p>")
            append("<p>Before</p>")
            append("<pre><code>$escapedBefore</code></pre>")
            append("<p>After</p>")
            append("<pre><code>$escapedAfter</code></pre>")
        }
        return XmlStringUtil.wrapInHtml(body)
    }
}
