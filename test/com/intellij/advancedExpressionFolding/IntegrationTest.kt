package com.intellij.advancedExpressionFolding

import com.intellij.driver.client.Driver
import com.intellij.driver.sdk.ui.components.common.IdeaFrameUI
import com.intellij.driver.sdk.ui.components.common.ideFrame
import com.intellij.driver.sdk.ui.components.elements.JCheckBoxUi
import com.intellij.driver.sdk.ui.components.elements.checkBox
import com.intellij.driver.sdk.ui.components.elements.waitSelected
import com.intellij.driver.sdk.waitForIndicators
import com.intellij.ide.starter.ci.CIServer
import com.intellij.ide.starter.ci.NoCIServer
import com.intellij.ide.starter.di.di
import com.intellij.ide.starter.driver.engine.runIdeWithDriver
import com.intellij.ide.starter.driver.execute
import com.intellij.ide.starter.ide.IDETestContext
import com.intellij.ide.starter.ide.IdeProductProvider
import com.intellij.ide.starter.models.TestCase
import com.intellij.ide.starter.plugins.PluginConfigurator
import com.intellij.ide.starter.project.LocalProjectInfo
import com.intellij.ide.starter.runner.IDECommandLine
import com.intellij.ide.starter.runner.IDERunContext
import com.intellij.ide.starter.runner.Starter
import com.intellij.ide.starter.screenRecorder.IDEScreenRecorder
import com.intellij.tools.ide.performanceTesting.commands.*
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
    fun `make sure setting changes are persisted`() {
        init("settings").runIdeWithDriver().useDriverAndCloseIde {
            wait()
            ideFrame {

                toggleCheckbox(driver, expectInitiallyChecked = true, thenCheck = false)
                clickOk()

                toggleCheckbox(driver, expectInitiallyChecked = false, thenCheck = true)
                clickOk()
            }
        }
    }

    @Test
    fun `open all files`() {
        lateinit var recorder: IDEScreenRecorder
        val init = init("openAllFiles")
        init.runIdeWithDriver(commandLine = {
            recorder = IDEScreenRecorder(it)
            IDECommandLine.OpenTestCaseProject(init)
        }).useDriverAndCloseIde {
            execute {
                it.importGradleProject()
                it.awaitCompleteProjectConfiguration()
                it.waitForSmartMode()
            }
            recorder.record {
                openFiles()
            }
        }
    }

    private fun Driver.wait() = waitForIndicators(5.minutes)

    fun IdeaFrameUI.toggleCheckbox(
        driver: Driver,
        expectInitiallyChecked: Boolean,
        thenCheck: Boolean
    ) {
        driver.gotoSettings()

        with(findCheckbox()) {
            check(isSelected() == expectInitiallyChecked) { "Unexpected initial checkbox state" }

            if (thenCheck) {
                check()
            } else {
                uncheck()
            }
            waitSelected(thenCheck)

            check(isSelected() == thenCheck) { "Checkbox ${if (thenCheck) "check" else "uncheck"} did not work" }
        }
    }

    private fun Driver.gotoSettings() {
        execute {
            it.searchEverywhere(textToType = "Single-Expression", selectFirst = true)
        }
        wait()
    }

    private fun IdeaFrameUI.findCheckbox(): JCheckBoxUi =
        checkBox("//div[@class='DialogPanel']//div[contains(@class,'JBCheckBox') and contains(., 'Single-Expression')]")

    private fun IdeaFrameUI.clickOk() {
        x {
            byVisibleText("OK")
        }.click()
    }


    private fun Driver.openFiles() {
        File("examples/data").listFiles()
            ?.filter { it.extension == "java" }
            ?.take(2)
            ?.forEach { file ->
                execute {
                    it.openFile("data/${file.name}")
                        //out/ide-tests/tests/IC-251.26094.121/openAllFiles/log/screenshots/my/FinalEmojiTestData.java/frame0.png
                        //.takeScreenshot("my/${file.name}")
                }
                //TODO:
                //gotoLine(1)
            }
    }
}

inline fun <T> IDEScreenRecorder.record(block: () -> T): T {
    start()
    return try {
        block()
    } finally {
        Thread.sleep(500)
        stop()
    }
}

private fun init(testName: String): IDETestContext = Starter.newContext(
    testName,
    TestCase(
        IdeProductProvider.IC,
        LocalProjectInfo(Path("examples"))
    ).withVersion("2025.1.2")
).apply {
    val latestZipFile = File("build/distributions").listFiles()
        ?.filter { it.extension == "zip" }
        ?.maxByOrNull { it.lastModified() }
        ?.absolutePath ?: "No zip files found"
    val pathToPlugin = latestZipFile
    PluginConfigurator(this).installPluginFromPath(Path(pathToPlugin))
    IDERunContext(this)

}.applyVMOptionsPatch {
    //addSystemProperty("sun.java2d.dpiaware", "false")
    //addSystemProperty("ide.window.system.frame.decorated", "false")
    addSystemProperty("Idea.Is.In.FullScreen.Mode.Now", "true")
    addSystemProperty("ide.window.size", "1600x900")
}

