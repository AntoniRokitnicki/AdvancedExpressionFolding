package com.intellij.advancedExpressionFolding.domain.service

import com.intellij.advancedExpressionFolding.domain.model.KeyAwareElement

class KeyClearanceDomainService {
    fun clear(element: KeyAwareElement) {
        element.clearKeys()
        element.children().forEach { child ->
            clear(child)
        }
    }
}
