package com.intellij.advancedExpressionFolding

import com.intellij.driver.client.Driver
import com.intellij.driver.client.service
import com.intellij.driver.client.utility
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
import com.intellij.ide.starter.report.Error
import com.intellij.ide.starter.report.ErrorReporterToCI
import com.intellij.ide.starter.runner.IDECommandLine
import com.intellij.ide.starter.runner.Starter
import com.intellij.ide.starter.screenRecorder.IDEScreenRecorder
import com.intellij.tools.ide.performanceTesting.commands.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.junit.jupiter.api.fail
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import java.io.File
import java.lang.Thread.sleep
import kotlin.io.path.Path
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * One test == one IDE run
 */
@EnabledIfEnvironmentVariable(named = "integration", matches = "1")
class IntegrationTest {
    val record = false
    val showWholeFile = false

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

    //@Disabled
    @Test
    fun `make sure setting changes are persisted`() {
        init("settings").runIdeWithDriver().useDriverAndCloseIde {
            wait()
            ideFrame {
                val property = service<SettingsStub>().getState()::expressionFunc
                check(property.get()) {
                    "expressionFunc should be initially enabled (true), but was ${property.get()}"
                }

                toggleCheckbox(driver, expectInitiallyChecked = true, thenCheck = false)
                clickOk()
                check(!property.get()) {
                    "expressionFunc should be disabled (false) after unchecking, but was ${property.get()}"
                }

                toggleCheckbox(driver, expectInitiallyChecked = false, thenCheck = true)
                clickOk()
                check(property.get()) {
                    "expressionFunc should be enabled (true) after checking, but was ${property.get()}"
                }
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

            utility<ColorActionStub>().changeFoldingColors()
            service<SettingsStub>().enableEverything()
            startZenMode()

            val next = { openFiles() }
            val errorList = if (record) {
                recorder.record {
                    next()
                }
            } else {
                next()
            }
            errorList.forEach { (filename, errors) ->
                println("File: $filename")
                errors.forEach { error ->
                    println(error.stackTraceContent)
                }
            }
        }
    }

    private fun Driver.startZenMode() {
        execute {
            it.searchEverywhere(textToType = "Zen Mode", selectFirst = true)
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

    private fun Driver.openFiles(): List<Pair<ErrorFileName, List<Error>>> {
        val seenErrors = HashSet<Error>()

        val excludedFiles = setOf(
            // Deprecated features
            "FinalEmojiTestData.java",
            "EmojifyTestData.java",
            //"ArithmeticExpressionsTestData.java",

            // Multiple Lombok tests - keep only basic one
            "LombokUsageTestData.java",
            "LombokDirtyOffTestData.java",
            "LombokPatternOffTestData.java",
            "LombokPatternOffNegativeTestData.java",

            // Complex/edge case combinations
            "DestructuringAssignmentArrayWithoutValTestData.java",
            "DestructuringAssignmentListWithoutValTestData.java",
            "NullableAnnotationCheckNotNullFieldShiftTestData.java",
            "ConstructorReferenceNotationWithConstTestData.java",
        )

        return File("examples/data")
            .listFiles()
            .filter { it.extension == "java" }
            .filterNot { it.name in excludedFiles }
            .sorted()
            //.take(3)
            .mapNotNull { file ->
                val filename = "data/${file.name}"
                execute {
                    it.openFile(filename)
                }
                if (showWholeFile) {
                    showWholeFile(file)
                }

                val errors = allErrors().filter { error ->
                    seenErrors.add(error)
                }.toList()
                if (errors.isNotEmpty()) {
                    file.name to errors
                } else {
                    null
                }

            }
    }

    private fun allErrors(): Sequence<Error> {
        val file = File("out/ide-tests/tests/IC-251.26094.121/openAllFiles/log/errors")
        if (!file.exists()) {
            return emptySequence()
        }
        return ErrorReporterToCI.collectErrors(file.toPath())
            .asSequence()
            .filter {
                it.stackTraceContent.contains("advancedExpressionFolding")
            }
    }

    private fun Driver.showWholeFile(file: File) {
        val millis = 2.seconds.inWholeMilliseconds
        sleep(millis)
        val linesCount = file.readText().lineSequence().count()
        (50 until linesCount step 50).forEach { index ->
            execute {
                it.gotoLine(index)
            }
            sleep(millis)
        }
    }
}

//./out/ide-tests/tests/IC-251.26094.121/openAllFiles/log/screenRecording/ScreenRecording
inline fun <T> IDEScreenRecorder.record(block: () -> T): T {
    start()
    return try {
        block()
    } finally {
        sleep(500)
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
    PluginConfigurator(this).installPluginFromPath(Path(latestZipFile))
}

typealias ErrorFileName = String
