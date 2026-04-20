package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.Action
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.testRenameDialogResult
import com.intellij.codeInsight.folding.CodeFoldingManager
import com.intellij.util.ui.UIUtil
import com.intellij.openapi.application.runReadAction
import com.intellij.testFramework.runInEdtAndWait
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.readText
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.testNewMethodName

class DynamicMethodFoldingConfigurationTest : BaseTest() {

    @Test
    fun `adding dynamic method fold updates configuration and refolds`(@TempDir tmpDir: Path) {
        val oldHome = System.getProperty("user.home")
        System.setProperty("user.home", tmpDir.toString())
        try {
            MethodCallFactory.refreshMethodCallMappings(object : IDynamicDataProvider {
                override fun parse(): List<DynamicMethodCall> = emptyList()
            })
            java.nio.file.Files.createFile(tmpDir.resolve("dynamic-ajf2.toml"))
            val text = "class A { void t() { normalMethod(); } }"
            fixture.configureByText("Test.java", text)
            runInEdtAndWait {
                fixture.editor.caretModel.moveToOffset(text.indexOf("normalMethod"))
                CodeFoldingManager.getInstance(fixture.project).updateFoldRegions(fixture.editor)
            }
            assertFalse(runReadAction { fixture.editor.foldingModel.allFoldRegions.any { it.placeholderText == "changedNormalMethod" } })

            testNewMethodName = "changedNormalMethod"
            fixture.launchAction(fixture.findSingleIntention("AJF2: Dynamic method folding"))
            runInEdtAndWait { UIUtil.dispatchAllInvocationEvents(); CodeFoldingManager.getInstance(fixture.project).updateFoldRegions(fixture.editor) }

            var regions = runReadAction { fixture.editor.foldingModel.allFoldRegions }
            assertTrue(regions.any { it.placeholderText == "changedNormalMethod" })

            val configFile = tmpDir.resolve("dynamic-ajf2.toml")
            assertTrue(configFile.exists())
            val config = configFile.readText()
            assertTrue(config.contains("normalMethod"))
            assertTrue(config.contains("changedNormalMethod"))

            fixture.configureByText("Test.java", text)
            runInEdtAndWait {
                fixture.editor.caretModel.moveToOffset(text.indexOf("normalMethod"))
                CodeFoldingManager.getInstance(fixture.project).updateFoldRegions(fixture.editor)
            }
            regions = runReadAction { fixture.editor.foldingModel.allFoldRegions }
            assertTrue(regions.any { it.placeholderText == "changedNormalMethod" })
        } finally {
            System.setProperty("user.home", oldHome)
            MethodCallFactory.refreshMethodCallMappings(object : IDynamicDataProvider {
                override fun parse(): List<DynamicMethodCall> = emptyList()
            })
        }
    }

    @Test
    fun `renaming dynamic method fold updates configuration and refolds`(@TempDir tmpDir: Path) {
        val oldHome = System.getProperty("user.home")
        System.setProperty("user.home", tmpDir.toString())
        try {
            MethodCallFactory.refreshMethodCallMappings(object : IDynamicDataProvider {
                override fun parse(): List<DynamicMethodCall> = emptyList()
            })
            val text = "class A { void t() { normalMethod(); } }"
            fixture.configureByText("Test.java", text)
            runInEdtAndWait {
                fixture.editor.caretModel.moveToOffset(text.indexOf("normalMethod"))
                CodeFoldingManager.getInstance(fixture.project).updateFoldRegions(fixture.editor)
            }
            assertFalse(runReadAction { fixture.editor.foldingModel.allFoldRegions.any { it.placeholderText == "firstName" || it.placeholderText == "secondName" } })

            testNewMethodName = "firstName"
            fixture.launchAction(fixture.findSingleIntention("AJF2: Dynamic method folding"))
            runInEdtAndWait { UIUtil.dispatchAllInvocationEvents(); CodeFoldingManager.getInstance(fixture.project).updateFoldRegions(fixture.editor) }
            var regions = runReadAction { fixture.editor.foldingModel.allFoldRegions }
            assertTrue(regions.any { it.placeholderText == "firstName" })

            testRenameDialogResult = Action.RENAME to "secondName"
            fixture.launchAction(fixture.findSingleIntention("AJF2: Dynamic method folding"))
            runInEdtAndWait { UIUtil.dispatchAllInvocationEvents(); CodeFoldingManager.getInstance(fixture.project).updateFoldRegions(fixture.editor) }
            regions = runReadAction { fixture.editor.foldingModel.allFoldRegions }
            assertTrue(regions.any { it.placeholderText == "secondName" })

            val config = tmpDir.resolve("dynamic-ajf2.toml").readText()
            assertTrue(config.contains("secondName"))
            assertFalse(config.contains("firstName"))

            fixture.configureByText("Test.java", text)
            runInEdtAndWait {
                fixture.editor.caretModel.moveToOffset(text.indexOf("normalMethod"))
                CodeFoldingManager.getInstance(fixture.project).updateFoldRegions(fixture.editor)
            }
            regions = runReadAction { fixture.editor.foldingModel.allFoldRegions }
            assertTrue(regions.any { it.placeholderText == "secondName" })
        } finally {
            testRenameDialogResult = null
            System.setProperty("user.home", oldHome)
            MethodCallFactory.refreshMethodCallMappings(object : IDynamicDataProvider {
                override fun parse(): List<DynamicMethodCall> = emptyList()
            })
        }
    }
}

