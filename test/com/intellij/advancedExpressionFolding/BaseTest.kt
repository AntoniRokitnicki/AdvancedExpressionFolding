package com.intellij.advancedExpressionFolding

import ai.grazie.utils.capitalize
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorEx
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper
import com.intellij.advancedExpressionFolding.processor.off
import com.intellij.openapi.application.WriteAction
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
import java.io.File
import java.io.IOException
import java.nio.file.Files

abstract class BaseTest : LightJavaCodeInsightFixtureTestCase5(TEST_JDK) {
    override fun getTestDataPath() = "testData"
    private val saveFoldingsAsJson = false

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
            saveFoldingsAsJson.off() ?: run {
                val folderName = "wrappers"
                val jsonFileName = fileName.replace("testData/", "$folderName/")
                File(folderName).mkdirs()
                store.saveToJsonFile(createOutputFile(jsonFileName, ".json"), wrapper)
            }
            val all = fileName.contains("-all")
            if (!all) {
                replaceAllTestData(fileName, actual)
                createFoldedFile(fileName, actual, wrapper)
                createGroupFile(fileName, actual, wrapper)
            }
            throw e
        }

        val wrapper = store.createOrderedFoldingWrapper()
        assertGroupFile(fileName, wrapper)
    }

    private fun createFoldedFile(fileName: String, actual: String, wrapper: FoldingDescriptorExWrapper) {
        val foldingFile = fileName.replace("testData/", "folded/")
        Files.writeString(
            createOutputFile(foldingFile, "-folded.java").toPath(),
            FoldingTemporaryTestEditor.getFoldedText(actual, wrapper)
        )
    }

    private fun createGroupFile(fileName: String, actual: String, wrapper: FoldingDescriptorExWrapper) {
        val foldingFile = fileName.replace("testData/", "folded/")
        Files.writeString(
            createOutputFile(foldingFile, "-group.java").toPath(),
            getGroupFoldedText(actual, wrapper)
        )
    }

    private fun assertGroupFile(fileName: String, wrapper: FoldingDescriptorExWrapper) {
        val testDataFile = File(fileName)
        val fileContent = FileUtil.loadFile(testDataFile).replace("\r", "")
        val actual = getGroupFoldedText(fileContent, wrapper)
        val foldingFile = fileName.replace("testData/", "folded/")
        val groupFile = createOutputFile(foldingFile, "-group.java")
        val expected = groupFile.takeIf(File::exists)?.let { FileUtil.loadFile(it).replace("\r", "") }
        if (expected != actual) {
            Files.writeString(groupFile.toPath(), actual)
            throw FileComparisonFailedError(groupFile.name, expected ?: "", actual, groupFile.path)
        }
    }

    private fun getGroupFoldedText(actual: String, wrapper: FoldingDescriptorExWrapper): String {
        val groupWrapper = wrapper.withGroupPlaceholdersUsingReference()
        val folded = FoldingTemporaryTestEditor.getFoldedText(actual, groupWrapper)
        return folded.replace("\r\n", "\n").replace('\r', '\n')
    }

    private fun FoldingDescriptorExWrapper.withGroupPlaceholdersUsingReference(): FoldingDescriptorExWrapper {
        val grouped = linkedMapOf<Pair<Int, Int>, MutableList<FoldingDescriptorEx>>()
        for (descriptor in list) {
            val key = descriptor.range.start to descriptor.range.end
            grouped.getOrPut(key) { mutableListOf() }.add(descriptor)
        }
        val newList = grouped.values.map { descriptors ->
            val combinedPlaceholder = buildString {
                descriptors.forEach { descriptor ->
                    val groupId = descriptor.groupReference.takeIf { it >= 0 } ?: 0
                    append(descriptor.text.asGroupMarker(groupId))
                }
            }
            val first = descriptors.first()
            first.copy(placeholder = combinedPlaceholder)
        }
        return FoldingDescriptorExWrapper(newList.size, newList)
    }

    private fun String.asGroupMarker(groupId: Int): String {
        val escaped = StringBuilder(length)
        for (ch in this) {
            when (ch) {
                '\\' -> escaped.append("\\\\")
                '"' -> escaped.append("\\\"")
                '\n' -> escaped.append("\\n")
                '\t' -> escaped.append("\\t")
                '\r' -> Unit
                else -> escaped.append(ch)
            }
        }
        return "[${groupId}:\"$escaped\"]"
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
            assertNotNull(file)
            fixture.configureFromExistingVirtualFile(file!!)
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
