#!/usr/bin/env -S kotlinc -script --

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Comparator
import java.util.concurrent.CountDownLatch
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.concurrent.thread
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.pathString
import kotlin.io.use

private data class RunResult(
    val command: List<String>,
    val exitCode: Int,
    val stdout: String,
)

private data class FoldingFailure(
    val methodName: String,
    val stacktraceHeader: String,
)

private val projectDir: Path = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize()
private val gradleCommand = listOf("./gradlew", "--console=plain")
private val foldingTestClass = "com.intellij.advancedExpressionFolding.FoldingTest"
private val testResultsDir = projectDir.resolve("build").resolve("test-results").resolve("test")

private val candidateClasses = listOf(
    "AdvancedExpressionFoldingBuilderTest",
    "AdvancedExpressionFoldingSettingsTest",
    "ExamplesScopeTest",
    "FoldingActionsTest",
    "FoldingServiceTest",
)

private fun runGradle(vararg args: String): RunResult {
    val command = gradleCommand + args
    val process = ProcessBuilder(command)
        .directory(projectDir.toFile())
        .redirectErrorStream(true)
        .start()

    val outputCollector = StringBuilder()
    val latch = CountDownLatch(1)
    val readerThread = thread(start = true, isDaemon = true, name = "gradle-output-${args.joinToString("-")}") {
        process.inputStream.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                outputCollector.appendLine(line)
            }
        }
        latch.countDown()
    }

    val exitCode = process.waitFor()
    latch.await()
    readerThread.join()

    return RunResult(command, exitCode, outputCollector.toString())
}

private fun clearTestResultsDirectory() {
    if (!Files.exists(testResultsDir)) {
        return
    }

    Files.walk(testResultsDir).use { paths ->
        paths.sorted(Comparator.reverseOrder())
            .forEach(Files::delete)
    }
}

private fun extractFirstFoldingFailure(): FoldingFailure? {
    if (!Files.exists(testResultsDir) || !Files.isDirectory(testResultsDir)) {
        return null
    }

    val foldingResultFile = testResultsDir.resolve("TEST-$foldingTestClass.xml")
    if (!Files.exists(foldingResultFile)) {
        return null
    }

    val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    val document = Files.newInputStream(foldingResultFile).use { input ->
        documentBuilder.parse(input)
    }
    document.documentElement.normalize()

    val nodes = document.getElementsByTagName("testcase")
    for (index in 0 until nodes.length) {
        val testCase = nodes.item(index)
        if (testCase.nodeType != org.w3c.dom.Node.ELEMENT_NODE) {
            continue
        }
        val element = testCase as org.w3c.dom.Element
        val failures = element.getElementsByTagName("failure")
        if (failures.length == 0) {
            continue
        }
        val failure = failures.item(0) as org.w3c.dom.Element
        val methodName = element.getAttribute("name").ifBlank { "unknown" }
        val header = failure.textContent
            .lineSequence()
            .map { it.trim() }
            .firstOrNull { it.isNotEmpty() }
            ?: failure.getAttribute("message")
        return FoldingFailure(methodName, header ?: "")
    }

    return null
}

private fun formatCandidateKey(subset: List<String>): String = subset.joinToString(separator = "+")

private fun nonEmptySubsets(): Sequence<List<String>> = sequence {
    val subsetCount = 1 shl candidateClasses.size
    for (mask in 1 until subsetCount) {
        val subset = buildList {
            candidateClasses.forEachIndexed { index, candidate ->
                if (mask and (1 shl index) != 0) {
                    add(candidate)
                }
            }
        }
        yield(subset)
    }
}

private fun buildExcludePattern(subset: List<String>): String = subset.joinToString(separator = "|") { ".*${'$'}it" }

fun main() {
    println("Project directory: ${projectDir.pathString}")
    val subsets = nonEmptySubsets().toList()
    println("Total subsets to sweep: ${subsets.size}")

    subsets.forEachIndexed { index, subset ->
        println("\n=== Sweep ${index + 1} / ${subsets.size}: ${subset.joinToString()}")
        clearTestResultsDirectory()

        val excludePattern = buildExcludePattern(subset)
        val firstRun = runGradle(
            "test",
            "-Djunit.jupiter.excludeClassNamePatterns=$excludePattern",
        )
        println("Completed ${firstRun.command.joinToString(" ")} with exitCode=${firstRun.exitCode}")

        val secondRun = runGradle(
            "test",
            "--tests",
            foldingTestClass,
        )
        println("Completed ${secondRun.command.joinToString(" ")} with exitCode=${secondRun.exitCode}")

        val failure = extractFirstFoldingFailure()
        val failureName = failure?.methodName ?: "none"
        val header = failure?.stacktraceHeader?.replace("\"", "\\\"") ?: ""
        println(
            "candidate=${formatCandidateKey(subset)} " +
                "foldingTestFirstFailure=$failureName " +
                "result=first=${firstRun.exitCode},second=${secondRun.exitCode} " +
                "stacktraceHeader=\"$header\"",
        )
    }
}

main()
