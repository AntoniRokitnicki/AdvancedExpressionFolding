package com.intellij.advancedExpressionFolding.folding

import ai.grazie.utils.capitalize
import com.intellij.advancedExpressionFolding.folding.util.FoldingDataStorage
import com.intellij.advancedExpressionFolding.folding.util.FoldingTemporaryTestEditor
import com.intellij.advancedExpressionFolding.folding.util.TestDynamicDataProvider
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.processor.takeIfFalse
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.application.runInEdt
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.projectRoots.JavaSdk
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.platform.testFramework.core.FileComparisonFailedError
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase5
import com.intellij.testFramework.fixtures.impl.CodeInsightTestFixtureImpl
import org.junit.jupiter.api.Assertions.assertNotNull
import org.opentest4j.TestAbortedException
import java.io.File
import java.io.IOException
import java.nio.file.Files
import kotlin.reflect.KMutableProperty0

abstract class BaseTest : LightJavaCodeInsightFixtureTestCase5(TEST_JDK) {
    override fun getTestDataPath() = "testData"
    private val saveFoldingsAsJson = false

    class TooComplexException : TestAbortedException("TOO COMPLEX FOLDING")
    class FoldingChangedException : AssertionError()

    protected val state: State by lazy {
        getInstance().state
    }

    open fun assignState(vararg turnOnProperties: KMutableProperty0<Boolean>,) {
        getInstance().disableAll()
        turnOnProperties.forEach {
            it.set(true)
        }
    }

    open fun doFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider(),
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        try {
            doFoldingTest(null)
        } catch (_: FileComparisonFailedError) {
            throw FoldingChangedException()
        } catch (e: IllegalArgumentException) {
            if (e.message == "Comparison method violates its general contract!") {
                throw TooComplexException()
            }
        }
    }

    protected fun doReadOnlyFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider()
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        runInEdt {
            doReadOnlyFoldingTest()
        }
    }

    protected open fun doFoldingTest(testNameArg: String? = null) {
        val testName = testNameArg ?: getTestName(false)
        val fileName = getTestFileName(testName)
        testWrapper(fileName, testName) {
            fixture.testFoldingWithCollapseStatus(fileName)
        }
    }

    protected fun doReadOnlyFoldingTest() {
        val testName = getTestName(false)
        val fileName = getTestFileName(testName)
        testWrapper(fileName, testName) {
            testReadOnlyFoldingRegions(
                fileName,
                null, true
            )
        }
    }

    protected open fun testWrapper(fileName: String, testName: String, action: () -> Unit) {
        rewriteFileOnFailure(fileName, testName, action)
    }

    private inline fun rewriteFileOnFailure(fileName: String, testName: String, action: () -> Unit) {
        val testDataFile = File(fileName)
        if (devMode()) {
            replaceTestDataWithExample(testName, testDataFile)
        }

        val store = FoldingDataStorage()
        com.intellij.advancedExpressionFolding.store = store
        try {
            action.invoke()
        } catch (e: FileComparisonFailedError) {
            val actual = e.actual.stringRepresentation
            Files.writeString(testDataFile.toPath(), actual)
            val wrapper = store.createOrderedFoldingWrapper()
            saveFoldingsAsJson.takeIfFalse() ?: run {
                val folderName = "wrappers"
                val jsonFileName = fileName.replace("testData/", "$folderName/")
                File(folderName).mkdirs()
                store.saveToJsonFile(createOutputFile(jsonFileName, ".json"), wrapper)
            }
            val all = fileName.contains("-all")
            if (!all) {
                replaceAllTestData(fileName, actual)
                createFoldedFile(fileName, actual, wrapper)
            }
            throw e
        }
    }

    private fun createFoldedFile(fileName: String, actual: String, wrapper: FoldingDescriptorExWrapper) {
        val foldingFile = fileName.replace("testData/", "folded/")
        Files.writeString(createOutputFile(foldingFile, "-folded.java").toPath(), FoldingTemporaryTestEditor.getFoldedText(actual, wrapper))
    }

    protected open fun getTestFileName(testName: String) = "testData/${testName.capitalize()}.java"

    // TODO: Refactor this mess
    private fun testReadOnlyFoldingRegions(
        verificationFileName: String,
        destinationFileName: String?,
        doCheckCollapseStatus: Boolean
    ) {
        val verificationFile = File(verificationFileName)
        var expectedContent = FileUtil.loadFile(verificationFile)
        assertNotNull(expectedContent)

        expectedContent = StringUtil.replace(expectedContent, "\r", "")
        val cleanContent =
            expectedContent.replace(("<$FOLD\\stext=\'[^\']*\'(\\sexpand=\'[^\']*\')*>").toRegex(), "")
                .replace("</$FOLD>", "")
        if (destinationFileName == null) {
            fixture.configureByText(
                FileTypeManager.getInstance().getFileTypeByFileName(verificationFileName),
                cleanContent
            )
        } else {
            FileUtil.writeToFile(File(destinationFileName), cleanContent)
            val file = LocalFileSystem.getInstance().refreshAndFindFileByPath(destinationFileName)
            val virtualFile = requireNotNull(file)
            fixture.configureFromExistingVirtualFile(virtualFile)
        }
        WriteAction.run<IOException> {
            fixture.file.virtualFile.isWritable = false
        }
        val actual = (fixture as CodeInsightTestFixtureImpl).getFoldingDescription(doCheckCollapseStatus)
        if (expectedContent != actual) {
            throw FileComparisonFailedError(verificationFile.name, expectedContent, actual, verificationFile.path)
        }
    }

    companion object {
        private const val FOLD = "fold"

        private val TEST_JDK: DefaultLightProjectDescriptor = object : DefaultLightProjectDescriptor() {
            override fun getSdk(): Sdk {
                return JavaSdk.getInstance()
                    .createJdk("Test JDK", System.getProperty("java.home"), true)
            }
        }

        private fun replaceAllTestData(fileName: String, actual: String) {
            Files.writeString(getAllTestFileName(fileName).toPath(), actual)
        }

        private fun devMode(): Boolean = System.getenv("dev-mode") != null

        private fun replaceTestDataWithExample(testName: String, testDataFile: File) {
            val exampleFile = File(".", "/examples/data/$testName.java")
            com.google.common.io.Files.copy(exampleFile, testDataFile)
        }

        private fun createOutputFile(fileName: String, extension: String) = File(fileName.replace(".java", extension))

        fun getAllTestFileName(testName: String): File = createOutputFile(testName, "-all.java")

    }

}
