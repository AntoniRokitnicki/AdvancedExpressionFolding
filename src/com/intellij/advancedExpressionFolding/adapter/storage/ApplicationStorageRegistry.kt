package com.intellij.advancedExpressionFolding.adapter.storage

import com.intellij.advancedExpressionFolding.application.port.out.Storage
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import java.util.concurrent.atomic.AtomicReference

@Service(Service.Level.APP)
class ApplicationStorageRegistry {
    private val storageRef = AtomicReference<Storage>(EmptyStorage)

    fun current(): Storage = storageRef.get()

    fun set(storage: Storage) {
        storageRef.set(storage)
    }

    fun reset() {
        storageRef.set(EmptyStorage)
    }

    fun override(storage: Storage): AutoCloseable {
        val previous = storageRef.getAndSet(storage)
        return AutoCloseable { storageRef.set(previous) }
    }

    companion object {
        fun instance(): ApplicationStorageRegistry = service()
    }
}
