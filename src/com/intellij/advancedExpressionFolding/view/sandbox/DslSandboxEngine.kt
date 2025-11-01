package com.intellij.advancedExpressionFolding.view.sandbox

import java.util.LinkedHashMap

internal class DslSandboxEngine {
    data class StateFrame(
        val index: Int,
        val command: String,
        val snapshot: Map<String, String>,
        val message: String,
        val isError: Boolean = false
    )

    fun execute(script: String): List<StateFrame> {
        val frames = mutableListOf<StateFrame>()
        val state = LinkedHashMap<String, String>()
        frames += StateFrame(
            index = 0,
            command = "(initial)",
            snapshot = emptyMap(),
            message = "Fresh workspace ready"
        )

        var nextIndex = 1
        val lines = script.lines()
        for (rawLine in lines) {
            val line = rawLine.trim()
            if (line.isEmpty() || line.startsWith("#")) {
                continue
            }

            val result = processCommand(line, state)
            frames += StateFrame(
                index = nextIndex++,
                command = line,
                snapshot = LinkedHashMap(state),
                message = result.message,
                isError = result.isError
            )

            if (result.isTerminal) {
                break
            }
        }

        return frames
    }

    private fun processCommand(
        command: String,
        state: LinkedHashMap<String, String>
    ): CommandResult {
        val tokens = command.split(" ")
            .filter { it.isNotBlank() }
        if (tokens.isEmpty()) {
            return CommandResult("Skipped empty command")
        }

        val keyword = tokens.first().uppercase()
        val tail = tokens.drop(1)

        return when (keyword) {
            "SET" -> {
                val key = tail.getOrNull(0)
                    ?: return CommandResult.error("SET requires a key")
                val value = extractValue(command, key)
                state[key] = value
                CommandResult("Set $key = \"$value\"")
            }

            "APPEND" -> {
                val key = tail.getOrNull(0)
                    ?: return CommandResult.error("APPEND requires a key")
                val addition = extractValue(command, key)
                val current = state[key]
                state[key] = when (current) {
                    null -> addition
                    else -> current + addition
                }
                CommandResult("Appended \"$addition\" to $key")
            }

            "INCR" -> {
                val key = tail.getOrNull(0)
                    ?: return CommandResult.error("INCR requires a key")
                val amountText = tail.getOrNull(1) ?: "1"
                val amount = amountText.toIntOrNull()
                    ?: return CommandResult.error("INCR amount must be an integer")
                val current = state[key]?.toIntOrNull() ?: 0
                val updated = current + amount
                state[key] = updated.toString()
                CommandResult("Incremented $key by $amount (now $updated)")
            }

            "DELETE" -> {
                val key = tail.getOrNull(0)
                    ?: return CommandResult.error("DELETE requires a key")
                val existed = state.remove(key) != null
                CommandResult(
                    if (existed) "Removed $key" else "Nothing to remove for $key"
                )
            }

            "CLEAR" -> {
                state.clear()
                CommandResult("Cleared all entries")
            }

            "EMIT" -> {
                val message = command.substringAfter(' ', "").ifBlank { "(empty signal)" }
                CommandResult("Note: $message")
            }

            "SNAPSHOT" -> {
                val name = tail.joinToString(" ")
                val label = if (name.isBlank()) "untitled" else name
                CommandResult("Snapshot saved: $label")
            }

            "HALT" -> {
                CommandResult("Simulation halted", isTerminal = true)
            }

            else -> CommandResult.error("Unknown command: $keyword")
        }
    }

    private fun extractValue(command: String, key: String): String {
        val marker = "$key "
        val position = command.indexOf(marker)
        if (position < 0) {
            return command.substringAfter(' ', "").trim()
        }
        return command.substring(position + marker.length).trim().ifBlank { "" }
    }

    private data class CommandResult(
        val message: String,
        val isTerminal: Boolean = false,
        val isError: Boolean = false
    ) {
        companion object {
            fun error(message: String): CommandResult = CommandResult(
                message = "Error: $message",
                isTerminal = true,
                isError = true
            )
        }
    }
}
