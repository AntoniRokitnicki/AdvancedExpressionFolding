package org.jetbrains.litmuskt

import java.util.concurrent.CyclicBarrier
import java.util.concurrent.BrokenBarrierException
import kotlin.concurrent.thread

open class LitmusIIOutcome {
    var r1: Int = 0
    var r2: Int = 0
}

fun interface Barrier {
    fun await()
}

typealias BarrierProducer = (Int) -> Barrier

data class LitmusRunParams(
    val batchSize: Int = 1,
    val syncPeriod: Int = 1,
    val affinityMap: Map<Int, Int>? = null,
    val barrierProducer: BarrierProducer = { NoOpBarrier },
)

object NoOpBarrier : Barrier {
    override fun await() {}
}

class JvmSpinBarrier(threadCount: Int) : Barrier {
    private val barrier = CyclicBarrier(threadCount)

    override fun await() {
        try {
            barrier.await()
        } catch (exception: BrokenBarrierException) {
            throw IllegalStateException("Barrier broken", exception)
        } catch (exception: InterruptedException) {
            Thread.currentThread().interrupt()
            throw IllegalStateException("Barrier interrupted", exception)
        }
    }
}

class LitmusTest<O : LitmusIIOutcome> internal constructor(
    internal val outcomeFactory: () -> O,
    internal val threads: List<(O) -> Unit>,
    internal val resetBlock: (O) -> Unit,
    internal val accepted: Set<Pair<Int, Int>>,
    internal val interesting: Set<Pair<Int, Int>>,
)

class LitmusResult(
    val outcomeCounts: Map<Pair<Int, Int>, Int>,
    val interestingOutcomes: Map<Pair<Int, Int>, Int>,
)

interface LitmusRunner {
    fun <O : LitmusIIOutcome> run(
        test: LitmusTest<O>,
        params: LitmusRunParams,
        instances: Int,
    ): LitmusResult
}

class JvmThreadRunner : LitmusRunner {
    override fun <O : LitmusIIOutcome> run(
        test: LitmusTest<O>,
        params: LitmusRunParams,
        instances: Int,
    ): LitmusResult {
        val outcomeCounts = mutableMapOf<Pair<Int, Int>, Int>()
        val interestingCounts = mutableMapOf<Pair<Int, Int>, Int>()

        repeat(instances) {
            val outcome = test.outcomeFactory()
            val barrier = params.barrierProducer(test.threads.size)
            val jobs = test.threads.map { action ->
                thread(start = true) {
                    barrier.await()
                    action(outcome)
                }
            }
            jobs.forEach { it.join() }
            val observed = outcome.r1 to outcome.r2
            outcomeCounts[observed] = outcomeCounts.getOrDefault(observed, 0) + 1
            if (observed in test.interesting) {
                interestingCounts[observed] = interestingCounts.getOrDefault(observed, 0) + 1
            }
            test.resetBlock(outcome)
        }

        return LitmusResult(outcomeCounts, interestingCounts)
    }
}

fun <O : LitmusIIOutcome> litmusTest(
    outcomeFactory: () -> O,
    block: LitmusBuilder<O>.() -> Unit,
): LitmusTest<O> {
    val builder = LitmusBuilder(outcomeFactory)
    builder.block()
    return builder.build()
}

class LitmusBuilder<O : LitmusIIOutcome>(
    private val outcomeFactory: () -> O,
) {
    private val threads = mutableListOf<(O) -> Unit>()
    private val accepted = linkedSetOf<Pair<Int, Int>>()
    private val interesting = linkedSetOf<Pair<Int, Int>>()
    private var resetBlock: (O) -> Unit = {}

    fun thread(block: O.() -> Unit) {
        threads += { outcome -> outcome.block() }
    }

    fun spec(block: SpecBuilder.() -> Unit) {
        SpecBuilder(accepted, interesting).block()
    }

    fun reset(block: O.() -> Unit) {
        resetBlock = { outcome -> outcome.block() }
    }

    fun build(): LitmusTest<O> = LitmusTest(
        outcomeFactory = outcomeFactory,
        threads = threads.toList(),
        resetBlock = resetBlock,
        accepted = accepted.toSet(),
        interesting = interesting.toSet(),
    )

    inner class SpecBuilder(
        private val accepted: MutableSet<Pair<Int, Int>>,
        private val interesting: MutableSet<Pair<Int, Int>>,
    ) {
        fun accept(r1: Int, r2: Int) {
            accepted += r1 to r2
        }

        fun interesting(r1: Int, r2: Int) {
            val pair = r1 to r2
            accepted += pair
            interesting += pair
        }
    }
}

fun <O : LitmusIIOutcome> LitmusRunner.runSingleTestParallel(
    test: LitmusTest<O>,
    params: LitmusRunParams,
    instances: Int,
): LitmusResult = run(test, params, instances)
