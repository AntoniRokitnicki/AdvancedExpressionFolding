package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.rt.execution.junit.FileComparisonFailure;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.DefaultLightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.intellij.testFramework.fixtures.impl.CodeInsightTestFixtureImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class BaseTest extends LightJavaCodeInsightFixtureTestCase {

    private static final String FOLD = "fold";

    private static final DefaultLightProjectDescriptor TEST_JDK = new DefaultLightProjectDescriptor() {
        public Sdk getSdk() {
            return JavaSdk.getInstance()
                    .createJdk("Test JDK", System.getProperty("java.home"), true);
        }
    };


    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    @NotNull
    protected LightProjectDescriptor getProjectDescriptor() {
        return TEST_JDK;
    }

    private static void rewriteFileOnFailure(String fileName, String testName, Runnable action) {
        try {
            action.run();
        } catch (FileComparisonFailure e) {
            try {
                String actual = e.getActual();
                Files.writeString(new File(fileName).toPath(), actual);
                if (!fileName.contains("-all")) {
                    Files.writeString(new File(getAllTestFileName(fileName)).toPath(), actual);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw e;
        }
    }

    protected void doFoldingTest() {
        String testName = getTestName(false);
        String fileName = getTestFileName(testName);
        rewriteFileOnFailure(fileName, testName, () -> myFixture.testFoldingWithCollapseStatus(fileName));
    }

    protected void doReadOnlyFoldingTest() {
        String testName = getTestName(false);
        String fileName = getTestFileName(testName);
        rewriteFileOnFailure(fileName, testName, () -> testReadOnlyFoldingRegions(fileName,
                null, true));
    }

    protected @NotNull String getTestFileName(String testName) {
        return getTestDataPath() + "/" + testName + ".java";
    }

    protected static @NotNull String getAllTestFileName(String testName) {
        return testName.replace(".java", "-all.java");
    }


    // TODO: Refactor this mess
    private void testReadOnlyFoldingRegions(@NotNull String verificationFileName,
                                            @Nullable String destinationFileName,
                                            boolean doCheckCollapseStatus) {
        String expectedContent;
        final File verificationFile;
        try {
            verificationFile = new File(verificationFileName);
            expectedContent = FileUtil.loadFile(verificationFile);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(expectedContent);

        expectedContent = StringUtil.replace(expectedContent, "\r", "");
        final String cleanContent = expectedContent.replaceAll("<" + FOLD + "\\stext=\'[^\']*\'(\\sexpand=\'[^\']*\')*>", "")
                .replace("</" + FOLD + ">", "");
        if (destinationFileName == null) {
            myFixture.configureByText(FileTypeManager.getInstance().getFileTypeByFileName(verificationFileName), cleanContent);
        }
        else {
            try {
                FileUtil.writeToFile(new File(destinationFileName), cleanContent);
                VirtualFile file = LocalFileSystem.getInstance().refreshAndFindFileByPath(destinationFileName);
                assertNotNull(file);
                myFixture.configureFromExistingVirtualFile(file);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            WriteAction.run(() -> myFixture.getFile().getVirtualFile().setWritable(false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final String actual = ((CodeInsightTestFixtureImpl)myFixture).getFoldingDescription(doCheckCollapseStatus);
        if (!expectedContent.equals(actual)) {
            throw new FileComparisonFailure(verificationFile.getName(), expectedContent, actual, verificationFile.getPath());
        }
    }


    @Override
    public void setUp() throws Exception {
        super.setUp();
        AdvancedExpressionFoldingSettings.getInstance().disableAll();
    }
}

