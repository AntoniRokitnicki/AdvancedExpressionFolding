package com.intellij.advancedExpressionFolding.litmus

import com.intellij.advancedExpressionFolding.EditorClearScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.jetbrains.litmuskt.JvmSpinBarrier
import org.jetbrains.litmuskt.JvmThreadRunner
import org.jetbrains.litmuskt.LitmusIIOutcome
import org.jetbrains.litmuskt.LitmusRunParams
import org.jetbrains.litmuskt.litmusTest
import org.jetbrains.litmuskt.runSingleTestParallel
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class EditorClearSchedulerLitmusTest {

    private val dispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    @AfterEach
    fun tearDown() {
        scope.cancel()
        dispatcher.close()
    }

    @Test
    fun `scheduler eventually clears every editor`() {
        val test = litmusTest({
            object : LitmusIIOutcome() {
                val scheduler = EditorClearScheduler<TrackedEditor> { scope }
                val editors = listOf(
                    TrackedEditor("A"),
                    TrackedEditor("B")
                )
                val counts = editors.associateWith { AtomicInteger() }
                val jobs = CopyOnWriteArrayList<Job?>()
            }
        }) {
            thread {
                jobs += scheduler.schedule(editors) { editor ->
                    counts.getValue(editor).incrementAndGet()
                }
                r1 = counts.getValue(editors[0]).get()
            }
            thread {
                jobs += scheduler.schedule(listOf(editors[1])) { editor ->
                    counts.getValue(editor).incrementAndGet()
                }
                r2 = counts.getValue(editors[1]).get()
            }
            spec {
                accept(0, 0)
                accept(0, 1)
                accept(0, 2)
                accept(1, 1)
                accept(1, 2)
                interesting(0, 0)
            }
            reset {
                runBlocking {
                    jobs.filterNotNull().forEach { it.join() }
                }
                assertEquals(1, counts.getValue(editors[0]).get(), "editor A should be cleared once")
                assertEquals(2, counts.getValue(editors[1]).get(), "editor B should be cleared twice")
                counts.values.forEach { it.set(0) }
                jobs.clear()
            }
        }

        val runner = JvmThreadRunner()
        val result = runner.runSingleTestParallel(
            test = test,
            params = LitmusRunParams(barrierProducer = { threads -> JvmSpinBarrier(threads) }),
            instances = 128,
        )
        assertTrue(result.interestingOutcomes.isNotEmpty(), "expected an interesting interleaving to be observed")
    }

    private data class TrackedEditor(val id: String)
}
