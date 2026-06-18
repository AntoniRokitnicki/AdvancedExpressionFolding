package litmuskt.sample

import org.jetbrains.litmuskt.BarrierProducer
import org.jetbrains.litmuskt.LitmusResult
import org.jetbrains.litmuskt.LitmusRunParams
import org.jetbrains.litmuskt.LitmusRunner
import org.jetbrains.litmuskt.autooutcomes.LitmusIIOutcome
import org.jetbrains.litmuskt.autooutcomes.accept
import org.jetbrains.litmuskt.autooutcomes.interesting
import org.jetbrains.litmuskt.litmusTest
import org.jetbrains.litmuskt.runSingleTestParallel

object MessagePassingLitmus {
    val plain = litmusTest({
        object : LitmusIIOutcome() {
            var x = 0
            var y = 0
        }
    }) {
        thread {
            x = 1
            y = 1
        }
        thread {
            r1 = y
            r2 = x
        }
        spec {
            accept(0, 0)
            accept(0, 1)
            accept(1, 1)
            interesting(1, 0)
        }
        reset {
            x = 0
            y = 0
        }
    }
}

fun runMessagePassing(
    runner: LitmusRunner,
    barrierProducer: BarrierProducer,
    batchSize: Int = 1024,
    syncPeriod: Int = 64,
): LitmusResult {
    val params = LitmusRunParams(
        batchSize = batchSize,
        syncPeriod = syncPeriod,
        affinityMap = null,
        barrierProducer = barrierProducer,
    )
    return runner.runSingleTestParallel(
        test = MessagePassingLitmus.plain,
        params = params,
        instances = 1,
    )
}
