package com.intellij.advancedExpressionFolding.learning

import com.intellij.openapi.application.PathManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.notExists

@State(name = "FoldingRnnState", storages = [Storage("advancedExpressionFolding-learning.xml")])
@Service(Service.Level.APP)
class FoldingRnnState : PersistentStateComponent<FoldingRnnState.State> {

    data class State(
        var enabled: Boolean = true,
        var dataFileRelPath: String = "learning/folding_events.csv",
        var modelFileRelPath: String = "learning/models",
        var minEventsForTraining: Int = 500,
        var lastTrainTs: Long = 0L,
    )

    private var myState = State()

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state.copy()
    }

    fun configDir(): Path {
        val base = PathManager.getConfigDir().resolve("advancedExpressionFolding")
        if (base.notExists()) {
            base.createDirectories()
        }
        return base
    }

    fun dataFile(): Path {
        val path = configDir().resolve(myState.dataFileRelPath)
        ensureParent(path)
        return path
    }

    fun modelsDir(): Path {
        val path = configDir().resolve(myState.modelFileRelPath)
        ensureParent(path)
        if (path.notExists()) {
            path.createDirectories()
        }
        return path
    }

    fun ensureEnabled(): Boolean {
        if (!myState.enabled) {
            deleteArtifacts()
            return false
        }
        return true
    }

    fun markTrained(timestamp: Long) {
        myState = myState.copy(lastTrainTs = timestamp)
    }

    fun deleteArtifacts() {
        deleteFileIfExists(configDir().resolve(myState.dataFileRelPath))
        val models = configDir().resolve(myState.modelFileRelPath)
        if (models.exists() && models.isDirectory()) {
            try {
                models.listDirectoryEntries().forEach { deleteFileIfExists(it) }
            } catch (_: IOException) {
                // ignore cleanup issues
            }
        }
    }

    private fun ensureParent(path: Path) {
        val parent = path.parent ?: return
        if (parent.notExists()) {
            parent.createDirectories()
        }
    }

    private fun deleteFileIfExists(path: Path) {
        try {
            Files.deleteIfExists(path)
        } catch (_: IOException) {
            // ignored
        }
    }

    companion object {
        fun get(): FoldingRnnState = service()
    }
}
