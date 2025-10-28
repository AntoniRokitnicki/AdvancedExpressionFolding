package com.intellij.advancedExpressionFolding.integration

import com.intellij.driver.client.Driver
import com.intellij.driver.client.service
import com.intellij.driver.client.utility
import com.intellij.driver.sdk.singleProject
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
import java.lang.Thread.sleep
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * One test == one IDE run
 */
@EnabledIfEnvironmentVariable(named = "integration", matches = "1|all-versions")
open class IntegrationTest {
    val record = false
    val showWholeFile = false

    protected open fun ideVersions(): List<String> = when (integrationMode()) {
        IntegrationMode.SINGLE -> listOf(DEFAULT_IDE_VERSION)
        IntegrationMode.ALL -> SUPPORTED_IDE_VERSIONS
    }

    protected open fun createContexts(testName: String): List<IDETestContext> {
        val mode = integrationMode()
        val versions = ideVersions()
        val contextName: (String, String) -> String = { name, version ->
            if (versions.size == 1) name else "$name-$version"
        }
        val pluginZip = findLatestPluginZip()
        println("Running integration tests in ${mode.displayName} mode")
        return versions.map { version ->
            Starter.newContext(
                contextName(testName, version),
                TestCase(
                    IdeProductProvider.IC,
                    LocalProjectInfo(Path("examples"))
                ).withVersion(version)
            ).apply {
                PluginConfigurator(this).installPluginFromPath(pluginZip.toPath())
            }
        }
    }

    protected fun runForEachContext(testName: String, action: (IDETestContext) -> Unit) {
        createContexts(testName).forEach(action)
    }

    private fun findLatestPluginZip(): File = File("build/distributions").listFiles()
        ?.filter { it.extension == "zip" }
        ?.maxByOrNull { it.lastModified() }
        ?: error("No plugin zip found")

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
        runForEachContext("settings") { context ->
            context.runIdeWithDriver().useDriverAndCloseIde {
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
    }

    @Test
    fun `open all files`() {
        runForEachContext("openAllFiles") { context ->
            lateinit var recorder: IDEScreenRecorder
            context.runIdeWithDriver(commandLine = {
                recorder = IDEScreenRecorder(it)
                IDECommandLine.OpenTestCaseProject(context)
            }).useDriverAndCloseIde {
                setupProjectWithGradle()

                println("changeFoldingColors=" + runCatching {
                    changeFoldingColors()
                }.exceptionOrNull())
                service<SettingsStub>().enableEverything()
                startZenMode()

                val next = { openFiles(context) }
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
    }

    @Test
    fun `global toggle folding action switches setting`() {
        runForEachContext("globalToggleFolding") { context ->
            context.runIdeWithDriver().useDriverAndCloseIde {
                setupProjectWithGradle()

                check(service<SettingsStub>().getState().globalOn) { "globalOn should start enabled" }

                execute { it.searchEverywhere(textToInsert = "Advanced Folding: Global", selectFirst = true, startThoughAction = true) }
                wait()
                check(!service<SettingsStub>().getState().globalOn) { "globalOn should be disabled after toggle" }

                execute { it.searchEverywhere(textToInsert = "Advanced Folding: Global", selectFirst = true, startThoughAction = true) }
                wait()
                check(service<SettingsStub>().getState().globalOn) { "globalOn should be re-enabled after second toggle" }
            }
        }
    }

    @Test
    fun `find methods with default parameters action shows usage results`() {
        runForEachContext("findMethodsWithDefaultParameters") { context ->
            context.runIdeWithDriver().useDriverAndCloseIde {
                wait()
                setupProjectWithGradle()

                execute { it.searchEverywhere(textToInsert = "Find Methods with Default Parameters", selectFirst = true) }
                wait()
                wait()
                val usageCount = service<UsageViewManagerStub>(singleProject()).getSelectedUsageView()?.getUsagesCount() ?: 0
                check(usageCount > 0) { "Expected to find usages but found $usageCount" }
            }
        }
    }

    private fun Driver.changeFoldingColors() = utility<ColorActionStub>().changeFoldingColors()

    @Test
    fun `global toggle disables and restores folding`() {
        runForEachContext("globalToggle") { context ->
            context.runIdeWithDriver().useDriverAndCloseIde {
                execute {
                    it.importGradleProject()
                    it.awaitCompleteProjectConfiguration()
                    it.waitForSmartMode()
                }

                service<SettingsStub>().enableEverything()
                check(service<SettingsStub>().getState().optional) {
                    "Optional folding should stay enabled when testing the global toggle"
                }

                utility<FoldingIntegrationStub>().toggleGlobalFolding(false)
                check(!service<SettingsStub>().getState().globalOn) {
                    "Global folding should be disabled after toggling off"
                }

                openOptionalTestData()

                val foldsWhenDisabled = utility<FoldingIntegrationStub>().countAdvancedFoldRegions()
                check(foldsWhenDisabled == 0) {
                    "Expected no advanced folds when global toggle is disabled, but found $foldsWhenDisabled"
                }

                utility<FoldingIntegrationStub>().toggleGlobalFolding(true)
                check(service<SettingsStub>().getState().globalOn) {
                    "Global folding should be enabled after toggling on"
                }

                openOptionalTestData()
                val foldsWhenEnabled = utility<FoldingIntegrationStub>().countAdvancedFoldRegions()
                check(foldsWhenEnabled > 0) {
                    "Expected advanced folds to return after re-enabling the global toggle, but found $foldsWhenEnabled"
                }
            }
        }
    }

    private fun Driver.openOptionalTestData() {
        execute {
            it.closeAllTabs()
        }
        execute {
            it.openFile("data/OptionalTestData.java")
        }
        wait()
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

    private fun Driver.openFiles(context: IDETestContext): List<Pair<ErrorFileName, List<Error>>> {
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

                val errors = allErrors(context).filter { error ->
                    seenErrors.add(error)
                }.toList()
                if (errors.isNotEmpty()) {
                    file.name to errors
                } else {
                    null
                }

            }
    }

    private fun allErrors(context: IDETestContext): Sequence<Error> {
        val logsDir = IDERunContext(context, launchName = context.testName).logsDir
        val errorsPath = logsDir.resolve("errors")
        if (!Files.exists(errorsPath)) {
            return emptySequence()
        }
        return ErrorReporterToCI.collectErrors(errorsPath)
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

    private companion object {
        private const val DEFAULT_IDE_VERSION = "2025.1.2"
        private const val INTEGRATION_ENV = "integration"
        private const val MODE_PROPERTY = "integration.mode"
        private val SUPPORTED_IDE_VERSIONS = listOf(
            "2024.2.6",      // last pre-243 build: shows breaking API boundary
            "2024.3.7",      // first stable after API changes: full test coverage
            "2025.1.6",      // main stable release used by most users
            "253.27864.23"   // current EAP: detects early regressions
        )
    }

    private fun integrationMode(): IntegrationMode {
        val propertyMode = System.getProperty(MODE_PROPERTY)?.lowercase()?.let(IntegrationMode::fromToken)
        if (propertyMode != null) {
            return propertyMode
        }
        val envMode = System.getenv(INTEGRATION_ENV)?.lowercase()?.let(IntegrationMode::fromToken)
        return envMode ?: IntegrationMode.ALL
    }

    private enum class IntegrationMode(val displayName: String, vararg val tokens: String) {
        SINGLE("single version", "1", "single"),
        ALL("all versions", "all", "all-versions");

        companion object {
            fun fromToken(token: String): IntegrationMode? = entries.firstOrNull { mode ->
                mode.tokens.any { it == token }
            }
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

typealias ErrorFileName = String


private fun Driver.setupProjectWithGradle() {
    execute {
        it.importGradleProject()
        it.awaitCompleteProjectConfiguration()
        it.waitForSmartMode()
    }
}
