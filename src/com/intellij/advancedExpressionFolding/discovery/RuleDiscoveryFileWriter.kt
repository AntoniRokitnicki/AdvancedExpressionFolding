package com.intellij.advancedExpressionFolding.discovery

import com.intellij.openapi.application.PathManager
import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Path
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

internal object RuleDiscoveryFileWriter {

    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    fun write(report: RuleDiscoveryReport) {
        val configDir = PathManager.getConfigDir()
        val targetDir = configDir.resolve("advanced-expression-folding")
        Files.createDirectories(targetDir)
        val target = targetDir.resolve("rules.yaml")
        Files.newBufferedWriter(target).use { writer ->
            writeHeader(writer, report, target)
            report.rules.forEach { rule ->
                writeRule(writer, rule)
            }
        }
    }

    private fun writeHeader(writer: BufferedWriter, report: RuleDiscoveryReport, target: Path) {
        writer.appendLine("# Auto-generated rule discovery report")
        writer.appendLine("# Generated: ${formatter.format(report.generatedAt.atOffset(ZoneOffset.UTC))}")
        writer.appendLine("# Target: ${target.toAbsolutePath()}")
        writer.appendLine("# Total rules: ${report.rules.size}")
        writer.appendLine("# Stats: occurrences=${report.stats.totalOccurrences}, languages=${report.stats.languagesProcessed}, families=${report.stats.familyCount}, noiseRate=${"%.3f".format(Locale.US, report.stats.overallNoiseRate)}")
    }

    private fun writeRule(writer: BufferedWriter, rule: RuleCandidate) {
        writer.appendLine("- id: ${escape(rule.id)}")
        writer.appendLine("  language: ${escape(rule.language)}")
        writer.appendLine("  foldType: ${escape(rule.foldType)}")
        writer.appendLine("  predicateDescription: ${escape(rule.predicateDescription)}")
        writer.appendLine("  psiChecks:")
        rule.psiChecks.forEach { check ->
            writer.appendLine("    - ${escape(check)}")
        }
        writer.appendLine("  predicateSnippet: |")
        rule.predicateSnippet.lines().forEach { line ->
            writer.appendLine("    ${line.trimEnd()}")
        }
        writer.appendLine("  metrics:")
        writer.appendLine("    support: ${rule.metrics.support}")
        writer.appendLine("    precision: ${"%.3f".format(Locale.US, rule.metrics.precision)}")
        writer.appendLine("    recall: ${"%.3f".format(Locale.US, rule.metrics.recall)}")
        writer.appendLine("    noiseRate: ${"%.3f".format(Locale.US, rule.metrics.noiseRate)}")
        writer.appendLine("  examples:")
        rule.examples.take(MAX_EXAMPLES).forEach { example ->
            writer.appendLine("    - file: ${escape(example.fileHash)}")
            writer.appendLine("      range: ${escape(example.range)}")
            writer.appendLine("      code: |")
            example.code.lines().forEach { line ->
                writer.appendLine("        ${line.trimEnd()}")
            }
        }
    }

    private fun escape(value: String): String {
        if (value.isEmpty()) {
            return "''"
        }
        val escaped = value.replace("'", "''")
        return "'${escaped}'"
    }

    private const val MAX_EXAMPLES = 5
}
