package com.intellij.advancedExpressionFolding.integration.mesh

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.Dispatchers

/**
 * Base marker for commands that can be routed through the [ServiceMesh].
 */
interface MeshCommand<R>

/**
 * Describes how a service should be exposed inside the mesh.
 */
data class ServiceContract<C : MeshCommand<R>, R>(
    val name: String,
    val workerCount: Int = 1,
    val capacity: Int = Channel.BUFFERED,
    val handler: suspend MeshContext.(C) -> R
) {
    init {
        require(name.isNotBlank()) { "Contract name must not be blank" }
        require(workerCount > 0) { "Contract '$name' must declare at least one worker" }
        require(capacity != 0) { "Contract '$name' may not use rendezvous channels" }
    }
}

/**
 * Runtime context supplied to handlers for observability helpers.
 */
class MeshContext internal constructor(
    val contractName: String,
    val workerIndex: Int
) {
    private val logger: Logger = Logger.getInstance("AdvancedExpressionFolding.Mesh.$contractName")

    fun trace(message: () -> String) {
        if (logger.isTraceEnabled) {
            logger.trace("[worker-$workerIndex] ${message()}")
        }
    }

    suspend fun ensureActive() {
        coroutineContext.ensureActive()
    }
}

private data class MeshEnvelope<C : MeshCommand<R>, R>(
    val command: C,
    val reply: CompletableDeferred<R>
)

class MeshEndpoint<C : MeshCommand<R>, R>(
    private val scope: CoroutineScope,
    private val contract: ServiceContract<C, R>
) : AutoCloseable {

    private val channel: Channel<MeshEnvelope<C, R>>
    private val workers: List<Job>

    init {
        val workChannel = Channel<MeshEnvelope<C, R>>(contract.capacity)
        channel = workChannel
        workers = (0 until contract.workerCount).map { workerIndex ->
            scope.launch {
                val context = MeshContext(contract.name, workerIndex)
                for (envelope in workChannel) {
                    processEnvelope(context, envelope)
                }
            }
        }
    }

    private suspend fun processEnvelope(
        context: MeshContext,
        envelope: MeshEnvelope<C, R>
    ) {
        try {
            context.trace { "Handling ${envelope.command}" }
            val result = contract.handler.invoke(context, envelope.command)
            envelope.reply.complete(result)
        } catch (t: Throwable) {
            envelope.reply.completeExceptionally(t)
        }
    }

    suspend fun dispatch(command: C): R {
        val deferred = CompletableDeferred<R>()
        channel.send(MeshEnvelope(command, deferred))
        return deferred.await()
    }

    fun launch(command: C): Job = scope.launch {
        dispatch(command)
    }

    fun submit(command: C): Deferred<R> {
        val deferred = CompletableDeferred<R>()
        val result = channel.trySend(MeshEnvelope(command, deferred))
        if (result.isSuccess) {
            return deferred
        }

        val failure = result.exceptionOrNull()
        if (failure != null) {
            deferred.cancel(CancellationException("Mesh '${contract.name}' rejected command", failure))
            throw failure
        }

        deferred.cancel(CancellationException("Mesh '${contract.name}' queue is full"))
        throw IllegalStateException("Mesh '${contract.name}' queue is full")
    }

    override fun close() {
        channel.close()
        workers.forEach { it.cancel() }
    }
}

@Service(Service.Level.APP)
class ServiceMesh internal constructor(
    private val scope: CoroutineScope,
    private val ownsScope: Boolean
) : Disposable {

    private val endpoints = ConcurrentHashMap<String, MeshEndpoint<*, *>>()

    constructor() : this(
        CoroutineScope(SupervisorJob() + Dispatchers.Default),
        true
    )

    fun <C : MeshCommand<R>, R> register(contract: ServiceContract<C, R>): MeshEndpoint<C, R> {
        val endpoint = MeshEndpoint(scope, contract)
        val previous = endpoints.putIfAbsent(contract.name, endpoint)
        if (previous != null) {
            endpoint.close()
            throw IllegalStateException("Mesh contract '${contract.name}' already registered")
        }
        return endpoint
    }

    fun unregister(contractName: String) {
        val endpoint = endpoints.remove(contractName)
        endpoint?.close()
    }

    override fun dispose() {
        endpoints.values.forEach { it.close() }
        endpoints.clear()
        if (ownsScope) {
            scope.cancel()
        }
    }

    companion object {
        fun get(): ServiceMesh = service()

        internal fun forScope(scope: CoroutineScope): ServiceMesh = ServiceMesh(scope, false)
    }
}
