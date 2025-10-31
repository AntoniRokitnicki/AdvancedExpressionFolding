package com.intellij.advancedExpressionFolding.integration.mesh

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

sealed interface FoldingMeshCommand : MeshCommand<Unit> {
    data class FoldEditor(val editor: Editor, val collapse: Boolean) : FoldingMeshCommand
    data class ClearEditor(val editor: Editor) : FoldingMeshCommand
    data class ClearProject(val project: Project) : FoldingMeshCommand
    data object ClearGlobal : FoldingMeshCommand
}

object FoldingServiceContract {
    const val NAME: String = "folding-service"

    fun create(handler: suspend MeshContext.(FoldingMeshCommand) -> Unit): ServiceContract<FoldingMeshCommand, Unit> =
        ServiceContract(
            name = NAME,
            workerCount = 2,
            handler = handler
        )
}
