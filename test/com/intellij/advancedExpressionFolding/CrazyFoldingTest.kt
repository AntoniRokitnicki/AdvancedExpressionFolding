package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.allProperties
import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.State
import com.intellij.advancedExpressionFolding.extension.on
import org.junit.AssumptionViolatedException
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

@EnabledIfEnvironmentVariable(named = "dev-mode", matches = "1")
class CrazyFoldingTest : BaseTest() {

    class TooComplexException : AssumptionViolatedException("TOO COMPLEX FOLDING")

    private val state: State by lazy {
        getInstance().state
    }

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
        } catch (e: com.intellij.rt.execution.junit.FileComparisonFailure) {
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
            File("commit_message.txt").writeText("$testDataFileName -> $message - $duration")
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
            CONFIG_FILE.readCounterAndFilename()?.let { (count: Long, filename: String) ->
                counter = count
            }
            val props: List<KMutableProperty<*>> = allProperties()
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
            exists().on() ?: return null
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

