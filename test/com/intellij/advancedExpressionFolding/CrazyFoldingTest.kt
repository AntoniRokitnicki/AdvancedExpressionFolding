package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.takeIfTrue
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.allMainProperties
import junit.framework.ComparisonFailure
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*
import java.util.stream.Stream
import kotlin.math.pow
import kotlin.reflect.KMutableProperty
import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**
 * Exhaustive stress test that iterates through every combination of
 * [AdvancedExpressionFoldingSettings] flags.
 *
 * Each permutation toggles the plugin's main settings, runs the folding engine on the
 * `LombokTestData` file, and copies the resulting input and folded output into a directory
 * named after the active configuration. Progress is stored in `crazy.properties` so the
 * process can resume across JVM restarts, and each iteration writes a commit capturing the
 * produced artifacts.
 *
 * With 22 boolean options there are 2^22 = 4,194,304 permutations. Running the folding
 * engine on `LombokTestData` takes a median ~24 ms, so a full sweep requires about
 * 100,663,296 ms (~28 h) of folding time and invokes 22 property setters per run
 * (92,274,688 calls in total).
 *
 * After each permutation the generated artifacts are committed to Git. Even a modest
 * estimate of 1 s per commit would add ~4.2 million seconds (~48 days), so the overall
 * duration is dominated by Git operations and file I/O.
 *
 * The test is intentionally gated behind the environment variable `dev-mode=2` to avoid
 * running during normal test execution due to its extremely long runtime.
 */
@EnabledIfEnvironmentVariable(named = "dev-mode", matches = "2")
class CrazyFoldingTest : BaseTest() {

    private fun File.saveCounterAndFilename(counter: Long, filename: String) {
        val properties = Properties()
        properties.setProperty("counter", counter.toString())
        properties.setProperty("filename", filename)
        FileOutputStream(this).use {
            properties.store(it, null)
        }
    }


    fun doCrazyTest(message: String, dir: String) {
        val testName = "LombokTestData"
        counter += 1
        val startTime = System.nanoTime()
        try {
            super.doFoldingTest(testName)
        } catch (_: ComparisonFailure) {
            println("counter = $counter")
        } catch (e: IllegalArgumentException) {
            if (e.message == "Comparison method violates its general contract!") {
                throw TooComplexException()
            } else {
                throw e
            }
        } finally {
            val duration = (System.nanoTime() - startTime).toDuration(DurationUnit.NANOSECONDS)
            val testDataFileName = "$testName.java"
            File(dir).run {
                mkdirs()
                File("testData/$testDataFileName").copyTo(File(this, testDataFileName), overwrite = true)
                val folded = testDataFileName.replace(".java", "-folded.java")
                File("folded/$folded").copyTo(File(this, folded), overwrite = true)
            }
            CONFIG_FILE.saveCounterAndFilename(counter, testDataFileName)
            File("commit_message.txt").writeText("$testName -> $duration - $message")
            GitUtils.commitAllChanges(cleanupDirs = "testData folded")
        }
    }

    @ParameterizedTest
    @MethodSource("permutations")
    fun testLombokTestData(booleans: BooleanArray, props: List<KMutableProperty<*>>) {
        val message = props.zip(booleans.toList()).map { (prop, value) ->
            prop.setter.call(state, value)
            if (value) {
                prop.name
            } else {
                "!${prop.name}"
            }
        }.joinToString("-")
        doCrazyTest(message, message.replace("-", "/"))
    }

    companion object {

        private val CONFIG_FILE = File("crazy.properties")

        private var counter: Long = 0L

        @JvmStatic
        fun permutations(): Stream<Arguments> {
            CONFIG_FILE.readCounterAndFilename()?.let { (count: Long, _: String) ->
                counter = count
            }
            val props: List<KMutableProperty<*>> = allMainProperties()
            val numBooleans = props.size
            return Stream.iterate(BooleanArray(numBooleans)) { prev ->
                val next = prev.clone()
                for (i in 0 until numBooleans) {
                    next[i] = !next[i]
                    if (next[i]) {
                        break
                    }
                }
                next
            }
                .skip(counter)
                .limit(2.0.pow(numBooleans.toDouble()).toLong())
                .map {
                    Arguments.of(it, props)
                }
        }


        private fun File.readCounterAndFilename(): Pair<Long, String>? {
            exists().takeIfTrue() ?: return null
            val properties = Properties()
            FileInputStream(this).use {
                properties.load(it)
            }
            val counterValue = properties.getProperty("counter")?.toLong()
            val filename = properties.getProperty("filename")
            return if (counterValue != null && filename != null) {
                Pair(counterValue, filename)
            } else {
                null
            }
        }
    }

}

