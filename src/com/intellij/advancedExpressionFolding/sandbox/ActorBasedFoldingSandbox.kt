package com.intellij.advancedExpressionFolding.sandbox

import com.intellij.openapi.Disposable
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.Instant
import java.util.concurrent.CopyOnWriteArraySet
import java.util.LinkedHashMap
import kotlin.coroutines.CoroutineContext

/**
 * Prototype sandbox that uses an actor to serialize folding preview requests.
 *
 * The sandbox keeps an in-memory representation of a sample snippet and the selected folding
 * rules. Consumers enqueue commands (load a snippet, toggle rules, request a preview) which are
 * processed sequentially by the actor. This keeps the folding engine side-effect free while also
 * providing deterministic ordering that is easy to observe in tests.
 */
@OptIn(ObsoleteCoroutinesApi::class)
class ActorBasedFoldingSandbox(
    coroutineScope: CoroutineScope? = null,
    private val foldingEngine: SandboxFoldingEngine = EchoSandboxFoldingEngine(),
) : Disposable {

    private val ownedScope: OwnedSandboxScope? = if (coroutineScope == null) OwnedSandboxScope() else null
    private val scope: CoroutineScope = coroutineScope ?: ownedScope!!

    private val mutableState = MutableStateFlow(SandboxState())
    val state: StateFlow<SandboxState> = mutableState.asStateFlow()

    private val listeners = CopyOnWriteArraySet<SandboxListener>()

    private val mailbox: SendChannel<SandboxCommand> = scope.actor(
        context = Dispatchers.Default,
        capacity = Channel.BUFFERED,
        start = kotlinx.coroutines.CoroutineStart.DEFAULT,
    ) {
        var currentState = mutableState.value
        for (command in channel) {
            when (command) {
                is SandboxCommand.UpdateSample -> {
                    val nextState = currentState.next(
                        sample = command.sample,
                        lastPreview = null,
                        lastError = null,
                    )
                    currentState = publish(nextState)
                    command.reply.complete(Unit)
                }
                is SandboxCommand.ToggleRule -> {
                    val nextRules = LinkedHashMap(currentState.rules)
                    if (command.enabled) {
                        nextRules[command.ruleId] = true
                    } else {
                        nextRules.remove(command.ruleId)
                    }
                    val nextState = currentState.next(
                        rules = nextRules,
                        lastPreview = null,
                        lastError = null,
                    )
                    currentState = publish(nextState)
                    command.reply.complete(Unit)
                }
                is SandboxCommand.AttachListener -> {
                    listeners.add(command.listener)
                    runCatching { command.listener.onStateChanged(currentState) }
                    command.reply.complete(Unit)
                }
                is SandboxCommand.RemoveListener -> {
                    listeners.remove(command.listener)
                    command.reply.complete(Unit)
                }
                is SandboxCommand.RequestPreview -> {
                    val result = runCatching {
                        withContext(command.context ?: Dispatchers.Default) {
                            val start = Instant.now()
                            val preview = foldingEngine.buildPreview(currentState.sample, currentState.rules)
                            val duration = Duration.between(start, Instant.now())
                            if (preview.metadata.containsKey(SandboxPreview.METRIC_LATENCY_MS)) {
                                preview
                            } else {
                                preview.copy(
                                    metadata = preview.metadata +
                                        (SandboxPreview.METRIC_LATENCY_MS to duration.toMillis().toString()),
                                )
                            }
                        }
                    }
                    val nextState = result.fold(
                        onSuccess = { preview ->
                            currentState.next(lastPreview = preview, lastError = null)
                        },
                        onFailure = { error ->
                            currentState.next(lastError = error)
                        },
                    )
                    currentState = publish(nextState)
                    result.fold(
                        onSuccess = { command.reply.complete(it) },
                        onFailure = { command.reply.completeExceptionally(it) },
                    )
                }
                is SandboxCommand.Shutdown -> {
                    command.reply.complete(Unit)
                    break
                }
            }
        }
    }

    suspend fun loadSample(sample: String) {
        request { reply -> SandboxCommand.UpdateSample(sample, reply) }
    }

    suspend fun setRule(ruleId: String, enabled: Boolean) {
        request { reply -> SandboxCommand.ToggleRule(ruleId, enabled, reply) }
    }

    suspend fun requestPreview(context: CoroutineContext? = null): SandboxPreview {
        return request { reply -> SandboxCommand.RequestPreview(context, reply) }
    }

    suspend fun addListener(listener: SandboxListener) {
        request { reply -> SandboxCommand.AttachListener(listener, reply) }
    }

    suspend fun removeListener(listener: SandboxListener) {
        request { reply -> SandboxCommand.RemoveListener(listener, reply) }
    }

    suspend fun shutdown() {
        request { reply -> SandboxCommand.Shutdown(reply) }
    }

    override fun dispose() {
        mailbox.close()
        ownedScope?.cancel()
        listeners.clear()
    }

    private suspend fun <T> request(builder: (CompletableDeferred<T>) -> SandboxCommand): T {
        val deferred = CompletableDeferred<T>()
        mailbox.send(builder(deferred))
        return deferred.await()
    }

    private fun publish(state: SandboxState): SandboxState {
        mutableState.value = state
        listeners.removeIf { listener ->
            runCatching { listener.onStateChanged(state) }.isFailure
        }
        return state
    }

    private class OwnedSandboxScope : CoroutineScope {
        private val job = SupervisorJob()
        override val coroutineContext: CoroutineContext = Dispatchers.Default + job
        fun cancel() {
            job.cancel()
        }
    }

    private sealed interface SandboxCommand {
        class UpdateSample(val sample: String, val reply: CompletableDeferred<Unit>) : SandboxCommand
        class ToggleRule(val ruleId: String, val enabled: Boolean, val reply: CompletableDeferred<Unit>) : SandboxCommand
        class RequestPreview(val context: CoroutineContext?, val reply: CompletableDeferred<SandboxPreview>) : SandboxCommand
        class AttachListener(val listener: SandboxListener, val reply: CompletableDeferred<Unit>) : SandboxCommand
        class RemoveListener(val listener: SandboxListener, val reply: CompletableDeferred<Unit>) : SandboxCommand
        class Shutdown(val reply: CompletableDeferred<Unit>) : SandboxCommand
    }
}

fun interface SandboxFoldingEngine {
    suspend fun buildPreview(sample: String, rules: Map<String, Boolean>): SandboxPreview
}

fun interface SandboxListener {
    fun onStateChanged(state: SandboxState)
}

data class SandboxState(
    val sample: String = "",
    val rules: Map<String, Boolean> = emptyMap(),
    val lastPreview: SandboxPreview? = null,
    val lastError: Throwable? = null,
    val version: Long = 0,
) {
    fun next(
        sample: String = this.sample,
        rules: Map<String, Boolean> = this.rules,
        lastPreview: SandboxPreview? = this.lastPreview,
        lastError: Throwable? = this.lastError,
    ): SandboxState {
        return copy(
            sample = sample,
            rules = LinkedHashMap(rules),
            lastPreview = lastPreview,
            lastError = lastError,
            version = version + 1,
        )
    }
}

data class SandboxPreview(
    val rawText: String,
    val foldedText: String,
    val appliedRules: Map<String, Boolean>,
    val metadata: Map<String, String> = emptyMap(),
) {
    companion object {
        const val METRIC_LATENCY_MS: String = "latencyMs"
    }

    fun describe(): String {
        val ruleSummary = if (appliedRules.isEmpty()) "<none>" else appliedRules.keys.joinToString()
        return "rules=$ruleSummary -> ${foldedText.take(120)}"
    }
}

class EchoSandboxFoldingEngine : SandboxFoldingEngine {
    override suspend fun buildPreview(sample: String, rules: Map<String, Boolean>): SandboxPreview {
        val renderedRules = if (rules.isEmpty()) "(no rules)" else rules.keys.sorted().joinToString(prefix = "[", postfix = "]")
        val folded = buildString {
            append(sample.ifEmpty { "// no sample provided" })
            append("\n// preview via echo engine ")
            append(renderedRules)
        }
        return SandboxPreview(
            rawText = sample,
            foldedText = folded,
            appliedRules = LinkedHashMap(rules),
            metadata = mapOf("engine" to "echo"),
        )
    }
}
