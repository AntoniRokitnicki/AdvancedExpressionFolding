package com.intellij.advancedExpressionFolding

import com.intellij.driver.client.Driver
import com.intellij.driver.sdk.ui.components.ideFrame
import com.intellij.driver.sdk.waitForIndicators
import com.intellij.ide.starter.ci.CIServer
import com.intellij.ide.starter.ci.NoCIServer
import com.intellij.ide.starter.di.di
import com.intellij.ide.starter.driver.engine.runIdeWithDriver
import com.intellij.ide.starter.driver.execute
import com.intellij.ide.starter.ide.IdeProductProvider
import com.intellij.ide.starter.models.TestCase
import com.intellij.ide.starter.plugins.PluginConfigurator
import com.intellij.ide.starter.project.LocalProjectInfo
import com.intellij.ide.starter.runner.Starter
import com.intellij.tools.ide.performanceTesting.commands.CommandChain
import com.intellij.tools.ide.performanceTesting.commands.openFile
import com.intellij.tools.ide.performanceTesting.commands.takeScreenshot
import com.intellij.tools.ide.performanceTesting.commands.waitForSmartMode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.junit.jupiter.api.fail
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import java.io.File
import kotlin.io.path.Path
import kotlin.time.Duration.Companion.minutes

@EnabledIfEnvironmentVariable(named = "integration", matches = "1")
class IntegrationTest {

    init {
        di = DI {
            extend(di)
            bindSingleton<CIServer>(overrides = true) {
                object : CIServer by NoCIServer {
                    override fun reportTestFailure(
                        testName: String,
                        message: String,
                        details: String,
                        linkToLogs: String?
                    ) {
                        fail { "$testName fails: $message. \n$details" }
                    }
                }
            }
        }
    }


    @Test
    fun openAllFiles() {
        Starter.newContext(
            "testExample",
            TestCase(
                IdeProductProvider.IC,
                LocalProjectInfo(Path("examples"))
            ).withVersion("2024.3")
        ).apply {
            val latestZipFile = File("build/distributions").listFiles()
                ?.filter { it.extension == "zip" }
                ?.maxByOrNull { it.lastModified() }
                ?.absolutePath ?: "No zip files found"
            val pathToPlugin = latestZipFile
            PluginConfigurator(this).installPluginFromPath(Path(pathToPlugin))
        }.runIdeWithDriver().useDriverAndCloseIde {
            waitForIndicators(5.minutes)
            //TODO: commands
            //CommandChain().gotoLine(1).searchEverywhere(textToType = "advanced java").takeScreenshot("")
             //.importGradleProject().setRegistry("a", "b").assertCompletionCommandContains(emptyList())

            runCatching {
                ideFrame {
                    //TODO: doesnt work, use find everywhere?
                    x { byVisibleText("IDE and Project Settings") }.click()
                }
            }

            openFiles()

            println()
            // TODO: examples
            // https://github.com/JetBrains/intellij-ide-starter/blob/master/intellij.tools.ide.starter.examples/testSrc/com/intellij/ide/starter/examples/driver/UiTestWithDriver.kt
        }
    }

    private fun Driver.openFiles() {
        runCatching {
            File("examples/data").listFiles()
                ?.filter { it.extension == "java" }?.take(3)?.forEach {
                    val commands =
                        CommandChain().waitForSmartMode().openFile("data/${it.name}").takeScreenshot("data/${it.name}")
                    //TODO:
                    //AdvancedExpressionFolding/out/ide-tests/tests/IC-243.21565.193/testExample/log/screenshots/data/SpreadTestData.java/frame0.png
                    execute(commands)
                }
        }.onFailure(Throwable::printStackTrace)
    }

}