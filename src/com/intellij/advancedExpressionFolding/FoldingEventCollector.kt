package com.intellij.advancedExpressionFolding

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiDocumentManager
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * Collects user initiated folding actions and forwards them to the [FoldingPredictor].
 */
object FoldingEventCollector {

    private val sessionStartKey: Key<Long> = Key.create("advancedExpressionFolding.sessionStart")
    private val installedKey: Key<Boolean> = Key.create("advancedExpressionFolding.collectorInstalled")

    fun attach(editor: Editor) {
        if (editor.getUserData(installedKey) == true) {
            return
        }
        editor.putUserData(installedKey, true)
        editor.putUserData(sessionStartKey, editor.getUserData(sessionStartKey) ?: System.currentTimeMillis())
        registerListener(editor)
    }

    fun detach(editor: Editor) {
        editor.putUserData(installedKey, null)
    }

    fun sessionDuration(editor: Editor, now: Long): Long = now - editor.sessionStart()

    private fun registerListener(editor: Editor) {
        val listenerClass = runCatching {
            Class.forName("com.intellij.openapi.editor.event.FoldingListener")
        }.getOrNull() ?: return
        val handler = InvocationHandler { _, method, args ->
            if (method.isFoldingStateChange && !args.isNullOrEmpty()) {
                val region = extractRegion(args[0])
                if (region != null) {
                    handleRegionStateChange(editor, region)
                }
            }
            null
        }
        val listener = Proxy.newProxyInstance(listenerClass.classLoader, arrayOf(listenerClass), handler)
        val multicaster = EditorFactory.getInstance().eventMulticaster
        val addMethod = multicaster::class.java.methods.find { it.name == "addFoldingListener" && it.parameterCount >= 2 } ?: return
        addMethod.invoke(multicaster, listener, editor)
    }

    private fun extractRegion(event: Any): FoldRegion? {
        val method = event::class.java.methods.find { it.name == "getFoldRegion" && it.parameterCount == 0 }
        return runCatching { method?.invoke(event) as? FoldRegion }.getOrNull()
    }

    private fun handleRegionStateChange(editor: Editor, region: FoldRegion) {
        if (!region.isAdvancedExpressionFoldingGroup) {
            return
        }
        val project = editor.project ?: return
        val predictor = FoldingPredictor.get()
        if (!predictor.shouldCollectEvents()) {
            return
        }
        val psiFile = project.psiFile(editor) ?: return
        val event = FoldingEvent(
            foldingId = FoldingPredictor.computeFoldingId(psiFile, region),
            timestamp = System.currentTimeMillis(),
            collapsed = !region.isExpanded,
            fileType = psiFile.fileType.name,
            foldingType = FoldingPredictor.describeRegion(region),
            sessionDurationMillis = System.currentTimeMillis() - editor.sessionStart()
        )
        predictor.onUserAction(event)
    }

    private fun Project.psiFile(editor: Editor) = PsiDocumentManager.getInstance(this).getPsiFile(editor.document)

    private fun Editor.sessionStart(): Long =
        getUserData(sessionStartKey) ?: System.currentTimeMillis().also { putUserData(sessionStartKey, it) }
}

private val Method.isFoldingStateChange: Boolean
    get() = name == "foldingStateChanged" && parameterCount == 1

data class FoldingEvent(
    val foldingId: String,
    val timestamp: Long,
    val collapsed: Boolean,
    val fileType: String,
    val foldingType: String,
    val sessionDurationMillis: Long
)
