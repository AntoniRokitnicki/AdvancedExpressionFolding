package com.intellij.advancedExpressionFolding.adapter.storage

import com.intellij.advancedExpressionFolding.application.port.output.Storage

object StorageRegistry {
    @Volatile
    private var delegate: Storage = EmptyStorage

    fun use(storage: Storage) {
        delegate = storage
    }

    fun reset() {
        delegate = EmptyStorage
    }

    fun current(): Storage = delegate
}
