package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.FoldingTemporaryTestEditor.getFoldedText
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper
import com.intellij.openapi.application.WriteAction
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.projectRoots.JavaSdk
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.rt.execution.junit.FileComparisonFailure
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase5
import com.intellij.testFramework.fixtures.impl.CodeInsightTestFixtureImpl
import org.junit.jupiter.api.Assertions.assertNotNull
import java.io.File
import java.io.IOException
import java.nio.file.Files

abstract class BaseTest : LightJavaCodeInsightFixtureTestCase5(TEST_JDK) {
    override fun getTestDataPath() = "testData"

    protected open fun doFoldingTest(testNameArg: String? = null) {
        val testName = testNameArg ?: getTestName(false)
        val fileName = getTestFileName(testName)
        rewriteFileOnFailure(fileName, testName) {
            fixture.testFoldingWithCollapseStatus(fileName)
            println(1)
        }
    }

    protected fun doReadOnlyFoldingTest() {
        val testName = getTestName(false)
        val fileName = getTestFileName(testName)
        rewriteFileOnFailure(fileName, testName) {
            testReadOnlyFoldingRegions(
                fileName,
                null, true
            )
        }
    }


    private fun rewriteFileOnFailure(fileName: String, testName: String, action: Runnable) {
        val testDataFile = File(fileName)
        if (devMode()) {
            replaceTestDataWithExample(testName, testDataFile)
        }

        val store = FoldingDataStorage()
        com.intellij.advancedExpressionFolding.store = store
        try {
            action.run()
        } catch (e: FileComparisonFailure) {
            try {
                //TODO: fixture.editor

                val actual = e.actual
                Files.writeString(testDataFile.toPath(), actual)

                val wrapper = store.saveFolding(createOutputFile(fileName, ".json"))

                val all = fileName.contains("-all")
                if (!all) {
                    replaceAllTestData(fileName, actual)
                    val foldingFile = fileName.replace("testData/", "folded/")
                    createFoldedFile(foldingFile, actual, wrapper)
                }
            } catch (ex: IOException) {
                throw RuntimeException(ex)
            }
            throw e
        }
    }

    protected open fun getTestFileName(testName: String) = "testData/$testName.java"

    // TODO: Refactor this mess
    private fun testReadOnlyFoldingRegions(
        verificationFileName: String,
        destinationFileName: String?,
        doCheckCollapseStatus: Boolean
    ) {
        var expectedContent: String
        val verificationFile: File
        try {
            verificationFile = File(verificationFileName)
            expectedContent = FileUtil.loadFile(verificationFile)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
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
            try {
                FileUtil.writeToFile(File(destinationFileName), cleanContent)
                val file = LocalFileSystem.getInstance().refreshAndFindFileByPath(destinationFileName)
                assertNotNull(file)
                fixture.configureFromExistingVirtualFile(file!!)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
        try {
            WriteAction.run<IOException> { fixture.file.virtualFile.isWritable = false }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        val actual = (fixture as CodeInsightTestFixtureImpl).getFoldingDescription(doCheckCollapseStatus)
        if (expectedContent != actual) {
            throw FileComparisonFailure(verificationFile.name, expectedContent, actual, verificationFile.path)
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

        private fun createFoldedFile(foldingFile: String, actual: String, wrapper: FoldingDescriptorExWrapper) {
            Files.writeString(createOutputFile(foldingFile, "-folded.java").toPath(), getFoldedText(actual, wrapper))
        }

        private fun replaceAllTestData(fileName: String, actual: String) {
            Files.writeString(getAllTestFileName(fileName).toPath(), actual)
        }

        private fun devMode(): Boolean = System.getenv("dev-mode") != null

        private fun replaceTestDataWithExample(testName: String, testDataFile: File) {
            val exampleFile = File(".", "/examples/data/$testName.java")
            try {
                com.google.common.io.Files.copy(exampleFile, testDataFile)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }

        private fun createOutputFile(fileName: String, extension: String) = File(fileName.replace(".java", extension))

        fun getAllTestFileName(testName: String): File = createOutputFile(testName, "-all.java")

    }

}
