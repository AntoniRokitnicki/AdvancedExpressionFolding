package com.intellij.advancedExpressionFolding.documentation

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingBuilder
import com.intellij.advancedExpressionFolding.isAdvancedExpressionFoldingGroup
import com.intellij.advancedExpressionFolding.documentation.links.DocumentationLinks
import com.intellij.advancedExpressionFolding.documentation.settings.ExpressionSettingLocator
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.openapi.application.WriteIntentReadAction
import com.intellij.openapi.project.DumbAware
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.platform.backend.documentation.DocumentationResult
import com.intellij.platform.backend.documentation.DocumentationTarget
import com.intellij.platform.backend.presentation.TargetPresentation
import com.intellij.model.Pointer
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.SmartPointerManager
import com.intellij.xml.util.XmlStringUtil

class FoldingDocTarget(private val seed: PsiElement) : DocumentationTarget, DumbAware {
  override fun computePresentation(): TargetPresentation =
    TargetPresentation.builder("Advanced Expression Folding").presentation()

  override fun createPointer(): Pointer<out DocumentationTarget> {
    val pointer = SmartPointerManager.getInstance(seed.project).createSmartPsiElementPointer(seed)
    return Pointer {
      pointer.element?.let(::FoldingDocTarget)
    }
  }

  override fun computeDocumentation(): DocumentationResult? {
    val file = seed.containingFile ?: return null
    val project = seed.project
    val docManager = PsiDocumentManager.getInstance(project)
    val document = docManager.getDocument(file) ?: return null
    if (!docManager.isCommitted(document)) {
      WriteIntentReadAction.run<RuntimeException> {
        docManager.commitDocument(document)
      }
    }

    val builder = AdvancedExpressionFoldingBuilder()
    val all = builder.buildFoldRegions(file, document, false)
      .filter(FoldingDescriptor::isAdvancedExpressionFoldingGroup)
    if (all.isEmpty()) return null

    var current: PsiElement? = seed
    var expression = current?.let { BuildExpressionExt.getNonSyntheticExpression(it, document) }
    while (expression == null && current != null) {
      current = current.parent
      expression = current?.let { BuildExpressionExt.getNonSyntheticExpression(it, document) }
    }
    expression ?: return null
    if (!expression.supportsFoldRegions(document, null)) return null

    val inside = all.filter { expression.textRange.intersectsStrict(it.range) }
    if (inside.isEmpty()) return null

    val before = document.getText(expression.textRange)

    val items = inside.mapNotNull { descriptor ->
      val node = descriptor.element ?: return@mapNotNull null
      val placeholder = builder.getPlaceholderText(node) ?: return@mapNotNull null
      descriptor.range to placeholder
    }.sortedByDescending { it.first.startOffset }
    if (items.isEmpty()) return null

    val after = StringBuilder(before).also { buffer ->
      val base = expression.textRange.startOffset
      for ((range, placeholder) in items) {
        val start = range.startOffset - base
        val end = range.endOffset - base
        if (start >= 0 && end <= buffer.length && end >= start) {
          buffer.replace(start, end, placeholder)
        }
      }
    }.toString()
    if (after == before) return null

    val keys = ExpressionSettingLocator.settingKeysFor(expression)
    if (keys.isEmpty()) return null

    val settingsHtml = keys.joinToString(" ") { key ->
      DocumentationLinks.urlFor(key)?.let { url ->
        "<code>$key</code>&nbsp;<a href=\"$url\">docs</a>"
      } ?: "<code>$key</code>"
    }

    val body = buildString {
      append("<h3>Advanced Expression Folding</h3>")
      append("<p>Settings: $settingsHtml</p>")
      append("<p>Before</p>")
      append("<pre><code>${XmlStringUtil.escapeString(before)}</code></pre>")
      append("<p>After</p>")
      append("<pre><code>${XmlStringUtil.escapeString(after)}</code></pre>")
    }

    return DocumentationResult.documentation(XmlStringUtil.wrapInHtml(body))
  }
}
