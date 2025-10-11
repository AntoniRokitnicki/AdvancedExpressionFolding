package com.intellij.advancedExpressionFolding

import com.intellij.driver.client.Driver
import com.intellij.driver.client.utility
import com.intellij.driver.sdk.ui.components.common.IdeaFrameUI
import com.intellij.driver.sdk.ui.components.common.ideFrame
import com.intellij.driver.sdk.waitForIndicators
import com.intellij.ide.starter.ci.CIServer
import com.intellij.ide.starter.ci.NoCIServer
import com.intellij.ide.starter.di.di
import com.intellij.ide.starter.driver.engine.runIdeWithDriver
import com.intellij.ide.starter.driver.execute
import com.intellij.ide.starter.ide.IDETestContext
import com.intellij.ide.starter.ide.IdeProductProvider
import com.intellij.ide.starter.models.TestCase
import com.intellij.ide.starter.project.LocalProjectInfo
import com.intellij.ide.starter.runner.Starter
import com.intellij.tools.ide.performanceTesting.commands.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.junit.jupiter.api.fail
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@EnabledIfEnvironmentVariable(named = "integration", matches = "1")
class FindMethodsWithDefaultParametersActionIntegrationTest {

    init {
        di = DI {
            extend(di)
            bindSingleton<CIServer>(overrides = true) {
                object : CIServer by NoCIServer {
                    override fun reportTestFailure(testName: String, message: String, details: String, linkToLogs: String?) {
                        fail { "$testName fails: $message. \n$details" }
                    }
                }
            }
        }
    }

    @Test
    fun `find methods action populates usage view`() {
        val context = initContext("findMethodsDefaultParameters")
        context.runIdeWithDriver().useDriverAndCloseIde {
            waitForProjectSetup()

            val usageBridge = utility<FindUsageCustomViewTestBridge>()
            val actionBridge = utility<FindMethodsWithDefaultParametersActionTestBridge>()
            usageBridge.reset()
            actionBridge.reset()
            actionBridge.setDelayPerFileMillis(200)

            utility<FindMethodsWithDefaultParametersActionExecutorBridge>().runOnCurrentProject()
            waitForIndicators(2.minutes)

            ensureUsageTreeContains("DefaultParameterOverloads.java", "SecondaryDefaults.java")

            val usageEntries = parseUsageEntries(usageBridge.dumpUsageData())
            val grouped = usageEntries.groupBy { it.fileName }
            val defaultContent = readProjectFile("DefaultParameterOverloads.java")
            val secondaryContent = readProjectFile("SecondaryDefaults.java")

            assertUsageLines(
                grouped.getValue("DefaultParameterOverloads.java"),
                defaultContent,
                listOf(9, 13, 17)
            )
            assertUsageLines(
                grouped.getValue("SecondaryDefaults.java"),
                secondaryContent,
                listOf(10, 14)
            )

            check(usageBridge.searchFinishedCount() == 1) {
                "Usage view should finish once after processing all files"
            }

            val events = parseProgressEvents(actionBridge.getProcessedEvents())
            check(events.size == 2) { "Expected two progress updates but got ${events.size}" }
            check(events.map { it.text }.containsAll(listOf("Processing file 1", "Processing file 2"))) {
                "Progress text did not contain expected updates: ${events.map { it.text }}"
            }

            val finished = parseFinishedEvent(actionBridge.getFinishedEvent())
            check(finished != null) { "Finished event was not reported" }
            check(finished!!.processedCount == 2) { "Expected finished event with two files but was ${finished.processedCount}" }
            check(!finished.canceled) { "Finished event should not be canceled for full run" }
        }
    }

    @Test
    fun `find methods action can be canceled and finalizes view`() {
        val context = initContext("findMethodsDefaultParametersCanceled")
        context.runIdeWithDriver().useDriverAndCloseIde {
            waitForProjectSetup()

            val usageBridge = utility<FindUsageCustomViewTestBridge>()
            val actionBridge = utility<FindMethodsWithDefaultParametersActionTestBridge>()
            usageBridge.reset()
            actionBridge.reset()
            actionBridge.setCancelAfter(1)
            actionBridge.setDelayPerFileMillis(150)

            utility<FindMethodsWithDefaultParametersActionExecutorBridge>().runOnCurrentProject()
            waitForIndicators(1.minutes)

            ensureUsageTreeContains("DefaultParameterOverloads.java")

            val usageEntries = parseUsageEntries(usageBridge.dumpUsageData())
            val grouped = usageEntries.groupBy { it.fileName }
            val defaultContent = readProjectFile("DefaultParameterOverloads.java")
            check(grouped.keys == setOf("DefaultParameterOverloads.java")) {
                "Usage entries should only include the first file after cancellation: ${grouped.keys}"
            }
            assertUsageLines(
                grouped.getValue("DefaultParameterOverloads.java"),
                defaultContent,
                listOf(9, 13, 17)
            )

            check(usageBridge.searchFinishedCount() == 1) {
                "Usage view should finish once even when the task is canceled"
            }

            val events = parseProgressEvents(actionBridge.getProcessedEvents())
            check(events.size == 1) { "Expected a single progress update before cancellation but got ${events.size}" }
            check(events.single().fileName.endsWith("DefaultParameterOverloads.java")) {
                "Unexpected file processed before cancellation: ${events.single().fileName}"
            }

            val finished = parseFinishedEvent(actionBridge.getFinishedEvent())
            check(finished != null) { "Finished event was not reported after cancellation" }
            check(finished!!.processedCount == 1) {
                "Expected finished event to report one processed file but was ${finished.processedCount}"
            }
            check(finished.canceled) { "Finished event should indicate cancellation" }
        }
    }

    private fun Driver.waitForProjectSetup() {
        execute {
            it.importGradleProject()
            it.awaitCompleteProjectConfiguration()
            it.waitForSmartMode()
        }
        waitForIndicators(1.minutes)
    }

    private fun Driver.ensureUsageTreeContains(vararg labels: String) {
        ideFrame {
            labels.forEach { label ->
                waitForUsageNode(label, 30.seconds)
            }
        }
    }

    private fun IdeaFrameUI.waitForUsageNode(text: String, timeout: Duration) {
        val node = x { byVisibleText(text) }
        val deadline = System.currentTimeMillis() + timeout.inWholeMilliseconds
        while (true) {
            if (node.present() && node.isVisible()) {
                return
            }
            if (System.currentTimeMillis() > deadline) {
                error("Timed out waiting for usage node '$text'")
            }
            Thread.sleep(200)
        }
    }

    private fun readProjectFile(name: String): String {
        val path = Path("integrationProjects/findDefaultParametersProject/src/main/java/com/example/$name")
        return Files.readString(path)
    }

    private fun parseUsageEntries(entries: List<String>): List<UsageEntry> = entries.map { entry ->
        val parts = entry.split(":")
        UsageEntry(parts[0], parts[1].toInt(), parts[2].toInt())
    }

    private fun assertUsageLines(entries: List<UsageEntry>, fileContent: String, expectedLines: List<Int>) {
        val actualLines = entries.map { it.startLine(fileContent) }.sorted()
        check(actualLines == expectedLines.sorted()) {
            "Unexpected usage line numbers. Expected $expectedLines but was $actualLines"
        }
    }

    private fun UsageEntry.startLine(content: String): Int {
        val prefix = content.substring(0, startOffset.coerceAtMost(content.length))
        return prefix.count { it == '\n' } + 1
    }

    private fun parseProgressEvents(events: List<String>): List<ProgressEvent> = events.map { raw ->
        val parts = raw.split("|")
        ProgressEvent(
            fileName = parts.getOrElse(0) { "" },
            processedCount = parts.getOrElse(1) { "0" }.toInt(),
            text = parts.getOrElse(2) { "" },
            fraction = parts.getOrElse(3) { "0.0" }.toDoubleOrNull() ?: 0.0,
            canceled = parts.getOrElse(4) { "false" }.toBooleanStrictOrNull() ?: false
        )
    }

    private fun parseFinishedEvent(raw: String?): FinishedEvent? {
        raw ?: return null
        val parts = raw.split("|")
        return FinishedEvent(
            processedCount = parts.getOrElse(1) { "0" }.toInt(),
            text = parts.getOrElse(2) { "" },
            fraction = parts.getOrElse(3) { "0.0" }.toDoubleOrNull() ?: 0.0,
            canceled = parts.getOrElse(4) { "false" }.toBooleanStrictOrNull() ?: false
        )
    }

    private fun initContext(testName: String): IDETestContext = Starter.newContext(
        testName,
        TestCase(
            IdeProductProvider.IC,
            LocalProjectInfo(Path("integrationProjects/findDefaultParametersProject"))
        ).withVersion("2025.1.2")
    ).apply {
        val latestZipFile = java.io.File("build/distributions").listFiles()
            ?.filter { it.extension == "zip" }
            ?.maxByOrNull { it.lastModified() }
            ?.absolutePath ?: "No zip files found"
        com.intellij.ide.starter.plugins.PluginConfigurator(this).installPluginFromPath(Path(latestZipFile))
    }

    private data class UsageEntry(val fileName: String, val startOffset: Int, val endOffset: Int)

    private data class ProgressEvent(
        val fileName: String,
        val processedCount: Int,
        val text: String,
        val fraction: Double,
        val canceled: Boolean
    )

    private data class FinishedEvent(
        val processedCount: Int,
        val text: String,
        val fraction: Double,
        val canceled: Boolean
    )
}
