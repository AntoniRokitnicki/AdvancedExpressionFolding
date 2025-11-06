package com.intellij.advancedExpressionFolding

import com.intellij.codeInsight.folding.CodeFoldingManager
import com.intellij.advancedExpressionFolding.action.FoldingOffAction
import com.intellij.advancedExpressionFolding.action.FoldingOnAction
import com.intellij.advancedExpressionFolding.action.GlobalToggleFoldingAction
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.impl.SimpleDataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.application.runReadAction
import com.intellij.testFramework.runInEdtAndWait
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GlobalFoldingToggleTest : BaseTest() {
    private fun pluginRegions(editor: Editor): List<FoldRegion> = runReadAction {
        editor.foldingModel.allFoldRegions.filter {
            it.group?.toString()?.startsWith("com.intellij.advancedExpressionFolding") == true
        }
    }

    private fun createEvent(editor: Editor): AnActionEvent {
        val dataContext = SimpleDataContext.builder()
            .add(CommonDataKeys.EDITOR, editor)
            .build()
        return AnActionEvent.createFromDataContext(ActionPlaces.UNKNOWN, null, dataContext)
    }

    @Test
    fun globalToggleActionsFoldAndPersist() {
        val settings = AdvancedExpressionFoldingSettings.getInstance().state
        settings.globalOn = true

        fixture.configureByFile("ConcatenationTestData.java")
        val editor = fixture.editor
        val project = fixture.project
        runInEdtAndWait {
            CodeFoldingManager.getInstance(project).updateFoldRegions(editor)
        }
        var regions = pluginRegions(editor)
        assertTrue(regions.isNotEmpty(), "Expected plugin fold regions when global folding is enabled")

        runInEdtAndWait {
            FoldingOnAction().actionPerformed(createEvent(editor))
        }
        regions = pluginRegions(editor)
        assertTrue(regions.all { !it.isExpanded }, "Regions should collapse after FoldingOnAction")

        runInEdtAndWait {
            FoldingOffAction().actionPerformed(createEvent(editor))
        }
        regions = pluginRegions(editor)
        assertTrue(regions.all { it.isExpanded }, "Regions should expand after FoldingOffAction")

        val toggle = GlobalToggleFoldingAction()
        runInEdtAndWait {
            toggle.setSelected(createEvent(editor), false)
        }
        assertFalse(settings.globalOn)
        assertFalse(GlobalToggleFoldingAction().isSelected(createEvent(editor)), "State should persist after toggling off")

        fixture.configureByFile("AssertTestData.java")
        val editor2 = fixture.editor
        runInEdtAndWait {
            CodeFoldingManager.getInstance(project).updateFoldRegions(editor2)
        }
        regions = pluginRegions(editor2)
        assertTrue(regions.isEmpty(), "No plugin fold regions expected when global folding is disabled")

        runInEdtAndWait {
            toggle.setSelected(createEvent(editor2), true)
        }
        assertTrue(settings.globalOn)
        assertTrue(GlobalToggleFoldingAction().isSelected(createEvent(editor2)), "State should persist after toggling on")

        fixture.configureByFile("ConcatenationTestData.java")
        val editor3 = fixture.editor
        runInEdtAndWait {
            CodeFoldingManager.getInstance(project).updateFoldRegions(editor3)
        }
        regions = pluginRegions(editor3)
        assertTrue(regions.isNotEmpty(), "Plugin fold regions expected after re-enabling global folding")
    }
}

