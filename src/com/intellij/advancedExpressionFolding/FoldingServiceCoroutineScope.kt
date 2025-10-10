package com.intellij.advancedExpressionFolding

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@Service(Service.Level.PROJECT)
class FoldingServiceCoroutineScope(project: Project) : CoroutineScope, Disposable {
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext = job + Dispatchers.Default

    override fun dispose() {
        job.cancel()
    }
}
