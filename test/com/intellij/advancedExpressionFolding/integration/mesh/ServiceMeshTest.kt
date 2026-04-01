package com.intellij.advancedExpressionFolding.integration.mesh

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.concurrent.CopyOnWriteArrayList

private data class IntCommand(val value: Int) : MeshCommand<Int>

class ServiceMeshTest {

    private val testScope = CoroutineScope(SupervisorJob())
    private val mesh = ServiceMesh.forScope(testScope)

    @AfterEach
    fun tearDown() {
        mesh.dispose()
        testScope.cancel()
    }

    @Test
    fun `dispatch executes handler and returns result`() = runBlocking {
        val endpoint = mesh.register(
            ServiceContract<IntCommand, Int>(
                name = "int-service",
                workerCount = 2
            ) { command ->
                ensureActive()
                command.value * 2
            }
        )

        val results = (1..5).map { index ->
            async { endpoint.dispatch(IntCommand(index)) }
        }.map { it.await() }

        assertEquals(listOf(2, 4, 6, 8, 10), results)
    }

    @Test
    fun `submit enqueues work respecting capacity`() = runBlocking {
        val seen = CopyOnWriteArrayList<Int>()
        val gate = CompletableDeferred<Unit>()
        val endpoint = mesh.register(
            ServiceContract<IntCommand, Int>(
                name = "bounded",
                workerCount = 1,
                capacity = 1
            ) { command ->
                seen += command.value
                gate.await()
                command.value
            }
        )

        val first = endpoint.submit(IntCommand(1))
        val second = endpoint.submit(IntCommand(2))
        val failure = assertThrows(IllegalStateException::class.java) {
            endpoint.submit(IntCommand(3))
        }
        assertEquals("Mesh 'bounded' queue is full", failure.message)
        gate.complete(Unit)
        assertEquals(1, first.await())
        assertEquals(2, second.await())
        assertEquals(listOf(1, 2), seen.sorted())
    }

    @Test
    fun `register rejects duplicate contracts`() {
        val endpoint = mesh.register(
            ServiceContract<IntCommand, Int>(
                name = "dup",
                workerCount = 1
            ) { command ->
                command.value
            }
        )

        assertThrows(IllegalStateException::class.java) {
            mesh.register(
                ServiceContract<IntCommand, Int>(
                    name = "dup",
                    workerCount = 1
                ) { command -> command.value }
            )
        }

        endpoint.close()
    }
}
