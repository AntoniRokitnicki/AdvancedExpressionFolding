package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.view.SettingsConfigurable
import com.intellij.mock.MockApplication
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.Disposer
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.panel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.reflect.KMutableProperty0

class SettingsConfigurableTest {
    @Test
    fun checkboxTracksPendingChanges() {
        val disposable = Disposer.newDisposable()
        val app = MockApplication(disposable)
        val settings = AdvancedExpressionFoldingSettings()
        app.registerService(AdvancedExpressionFoldingSettings::class.java, settings)
        ApplicationManager.setApplication(app, disposable)

        class Holder(var option: Boolean)
        val holder = Holder(false)
        val configurable = SettingsConfigurable()
        with(configurable) {
            panel { registerCheckbox(holder::option, "option", null) }
        }

        val panelField = SettingsConfigurable::class.java.getDeclaredField("panel")
        panelField.isAccessible = true
        panelField.set(configurable, panel { })

        val field = SettingsConfigurable::class.java.getDeclaredField("propertyToCheckbox")
        field.isAccessible = true
        val map = field.get(configurable) as MutableMap<KMutableProperty0<Boolean>, JBCheckBox>
        val checkbox = map[holder::option]!!
        assertFalse(configurable.isModified())
        checkbox.doClick()
        assertTrue(configurable.isModified())
        configurable.apply()
        assertTrue(holder.option)

        Disposer.dispose(disposable)
    }
}
