package com.intellij.advancedExpressionFolding.action

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.project.DumbAware

class PersonaSwitchActionGroup : ActionGroup(), DumbAware {
    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val descriptors = settings.personaDescriptors()
        if (descriptors.isEmpty()) {
            return AnAction.EMPTY_ARRAY
        }
        return descriptors.map { PersonaQuickSwitchAction(it.id) }.toTypedArray()
    }
}

private class PersonaQuickSwitchAction(private val personaId: String) : ToggleAction(), DumbAware {
    override fun isSelected(e: AnActionEvent): Boolean {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        return settings.state.activePersonaId == personaId
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        if (!state) return
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        settings.setActivePersona(personaId, user = "toolbar:${e.place}")
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val persona = settings.personaDescriptors().firstOrNull { it.id == personaId }
        e.presentation.isEnabledAndVisible = persona != null
        if (persona != null) {
            e.presentation.text = persona.displayName
            e.presentation.description = "Activate persona ${persona.displayName}"
        }
    }
}

abstract class BaseActivatePersonaAction : ToggleAction(), DumbAware {
    protected abstract val personaId: String
    protected abstract val fallbackText: String

    override fun isSelected(e: AnActionEvent): Boolean {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        return settings.state.activePersonaId == personaId
    }

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        if (!state) return
        AdvancedExpressionFoldingSettings.getInstance().setActivePersona(personaId, user = "shortcut:${e.place}")
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val persona = settings.personaDescriptors().firstOrNull { it.id == personaId }
        e.presentation.text = persona?.displayName ?: fallbackText
        e.presentation.description = "Activate persona ${persona?.displayName ?: fallbackText}"
        e.presentation.isEnabled = persona != null
    }
}

class ActivateCorePersonaAction : BaseActivatePersonaAction() {
    override val personaId: String = AdvancedExpressionFoldingSettings.PersonaProfileState.DEFAULT_ID
    override val fallbackText: String = "Activate Core Persona"
}

class ActivateAnalystPersonaAction : BaseActivatePersonaAction() {
    override val personaId: String = "analyst"
    override val fallbackText: String = "Activate Analyst Persona"
}

class ActivateLoggerPersonaAction : BaseActivatePersonaAction() {
    override val personaId: String = "logger"
    override val fallbackText: String = "Activate Logger Persona"
}

class ActivateReviewerPersonaAction : BaseActivatePersonaAction() {
    override val personaId: String = "reviewer"
    override val fallbackText: String = "Activate Reviewer Persona"
}
