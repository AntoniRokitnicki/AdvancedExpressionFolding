package io.stryker_mutator.stryker4k

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

private const val DEFAULT_COMMAND = "./gradlew test --rerun-tasks"

class Stryker4kPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("stryker4k", Stryker4kTask::class.java) { task ->
            task.group = "verification"
            task.description = "Runs mutation tests via the configured Stryker4k command."
        }
    }
}

abstract class Stryker4kTask : DefaultTask() {
    @TaskAction
    fun run() {
        val command = resolveCommand()
        logger.lifecycle("Running Stryker4k command: {}", command.joinToString(" "))
        project.exec { execSpec ->
            execSpec.commandLine(command)
        }.rethrowFailure()
    }

    private fun resolveCommand(): List<String> {
        val configFile = project.layout.projectDirectory.file("stryker-conf.json").asFile
        if (configFile.isFile) {
            val text = configFile.readText()
            val match = Regex("\\\"command\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"").find(text)
            if (match != null) {
                return match.groupValues[1]
                    .split(" ")
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
            }
        }
        return DEFAULT_COMMAND.split(" ")
    }
}
