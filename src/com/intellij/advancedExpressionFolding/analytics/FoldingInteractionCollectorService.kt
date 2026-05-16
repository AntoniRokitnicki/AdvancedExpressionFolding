package com.intellij.advancedExpressionFolding.analytics

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.ProjectLocator
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import java.lang.reflect.Proxy

@Service(Service.Level.APP)
class FoldingInteractionCollectorService : Disposable {

    private val foldingListener: Any?

    init {
        foldingListener = createListener()?.also { listener ->
            addListener(listener)
        }
    }

    private fun handleFoldRegionStateChange(region: FoldRegion) {
        if (!region.isValid || ApplicationManager.getApplication().isUnitTestMode) {
            return
        }

        val document = region.document
        val virtualFile = FileDocumentManager.getInstance().getFile(document) ?: return
        val project = ProjectLocator.getInstance().guessProjectForFile(virtualFile) ?: return
        val psiDocumentManager = PsiDocumentManager.getInstance(project)
        psiDocumentManager.commitDocument(document)
        val psiFile = PsiManager.getInstance(project).findFile(virtualFile) ?: return

        val element = findElement(psiFile, region) ?: return
        val features = FoldingFeatureExtractorService.getInstance().extract(element, document)

        val storage = FoldingInteractionStorageService.getInstance()
        storage.addRecord(
            FoldingInteractionStorageService.Record(
                elementType = features.elementType,
                textLength = features.textLength,
                lineCount = features.lineCount,
                label = if (region.isExpanded) 0 else 1,
            ),
        )
    }

    override fun dispose() {
        // nothing to dispose
    }

    private fun createListener(): Any? {
        val classLoader = EditorFactory::class.java.classLoader
        val listenerClass = sequenceOf(
            "com.intellij.openapi.editor.event.EditorFoldingListener",
            "com.intellij.openapi.editor.event.FoldingListener",
        ).mapNotNull { className ->
            runCatching { Class.forName(className, false, classLoader) }.getOrNull()
        }.firstOrNull() ?: return null

        return Proxy.newProxyInstance(classLoader, arrayOf(listenerClass)) { _, method, args ->
            when (method.name) {
                "onFoldRegionStateChange" -> {
                    val region = args?.getOrNull(0) as? FoldRegion ?: return@newProxyInstance null
                    handleFoldRegionStateChange(region)
                }
                "onFoldProcessingEnd" -> {
                    val editor = args?.getOrNull(0) as? Editor ?: return@newProxyInstance null
                    handleFoldProcessingEnd(editor)
                }
            }
            null
        }
    }

    private fun addListener(listener: Any) {
        val multicaster = EditorFactory.getInstance().eventMulticaster
        val listenerClass = listener.javaClass.interfaces.firstOrNull() ?: return
        val method = sequenceOf(
            "addEditorFoldingListener",
            "addFoldingListener",
        ).mapNotNull { name ->
            runCatching { multicaster.javaClass.getMethod(name, listenerClass, Disposable::class.java) }.getOrNull()
        }.firstOrNull() ?: return

        method.invoke(multicaster, listener, this)
    }

    private fun handleFoldProcessingEnd(@Suppress("UNUSED_PARAMETER") editor: Editor) {
        // no-op
    }

    private fun findElement(file: PsiFile, region: FoldRegion): PsiElement? {
        val start = region.startOffset
        val end = (region.endOffset - 1).coerceAtLeast(start)
        var current = file.findElementAt(start)

        while (current != null) {
            val range = current.textRange ?: break
            if (range.containsRange(start, end)) {
                return current
            }
            current = current.parent
        }
        return null
    }

    companion object {
        fun getInstance(): FoldingInteractionCollectorService = service()
    }
}

private fun com.intellij.openapi.util.TextRange.containsRange(start: Int, end: Int): Boolean {
    if (start < 0 || end < 0) return false
    return start >= this.startOffset && end <= this.endOffset
}
