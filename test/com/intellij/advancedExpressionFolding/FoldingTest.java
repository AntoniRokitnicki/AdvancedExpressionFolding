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

public class FoldingTest extends LightJavaCodeInsightFixtureTestCase {

    public static final DefaultLightProjectDescriptor TEST_JDK = new DefaultLightProjectDescriptor() {
        public Sdk getSdk() {
            return JavaSdk.getInstance()
                    .createJdk("Test JDK", System.getProperty("java.home"), true);
        }
    };

    private static final String FOLD = "fold";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        disableAllFoldings();
    }

    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    @NotNull
    protected LightProjectDescriptor getProjectDescriptor() {
        return TEST_JDK;
    }



    private static void rewriteFileOnFailure(String fileName, Runnable action) {
        try {
            action.run();
        } catch (FileComparisonFailure e) {
            try {
                Files.writeString(new File(fileName).toPath(), e.getActual());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw e;
        }
    }

    public void doFoldingTest() {
        String fileName = getTestDataPath() + "/" + getTestName(false) + ".java";
        rewriteFileOnFailure(fileName, () -> myFixture.testFoldingWithCollapseStatus(fileName));
    }

    public void doReadOnlyFoldingTest() {
        String fileName = getTestDataPath() + "/" + getTestName(false) + ".java";
        rewriteFileOnFailure(fileName, () -> testReadOnlyFoldingRegions(fileName,
                null, true));
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

    public void testElvisTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setCheckExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testForRangeTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setRangeExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testStringBuilderTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setConcatenationExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testInterpolatedStringTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setConcatenationExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testGetSetPutTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setGetExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testSliceTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setSlicingExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testAppendSetterInterpolatedStringTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setConcatenationExpressionsCollapse(true);
        AdvancedExpressionFoldingSettings.getInstance().getState().setGetSetExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testEqualsCompareTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setComparingExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testTypeCastTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setCastExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testVarTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setVarExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testGetterSetterTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setGetSetExpressionsCollapse(true);
        doFoldingTest();
    }

    public void testControlFlowSingleStatementTestData() {
        // TODO: Test with different indentation settings
        AdvancedExpressionFoldingSettings.getInstance().getState().setControlFlowSingleStatementCodeBlockCollapse(true);
        doReadOnlyFoldingTest();
    }

    public void testControlFlowMultiStatementTestData() {
        // TODO: Test with different indentation settings
        AdvancedExpressionFoldingSettings.getInstance().getState().setControlFlowMultiStatementCodeBlockCollapse(true);
        doReadOnlyFoldingTest();
    }

    public void testLocalDateTestData() {
        AdvancedExpressionFoldingSettings.State state = AdvancedExpressionFoldingSettings.getInstance().getState();
        state.setComparingLocalDatesCollapse(true);
        doReadOnlyFoldingTest();
    }

    public void testLocalDateLiteralTestData() {
        AdvancedExpressionFoldingSettings.State state = AdvancedExpressionFoldingSettings.getInstance().getState();
        state.setLocalDateLiteralCollapse(true);
        doReadOnlyFoldingTest();
    }

    public void testLocalDateLiteralPostfixTestData() {
        AdvancedExpressionFoldingSettings.State state = AdvancedExpressionFoldingSettings.getInstance().getState();
        state.setLocalDateLiteralCollapse(true);
        state.setLocalDateLiteralPostfix(true);
        doReadOnlyFoldingTest();
    }

    public void testCompactControlFlowTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setCompactControlFlowSyntaxCollapse(true);
        doFoldingTest();
    }

    public void testSemicolonTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setSemicolonsCollapse(true);
        doReadOnlyFoldingTest();
    }

    //TODO: make tests below independent of others flags
    public void testConcatenationTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setConcatenationExpressionsCollapse(true);
        AdvancedExpressionFoldingSettings.getInstance().getState().setOptional(true);
        AdvancedExpressionFoldingSettings.getInstance().getState().setStreamSpread(true);
        doFoldingTest();
    }

    public void testOptionalTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setConcatenationExpressionsCollapse(true);
        AdvancedExpressionFoldingSettings.getInstance().getState().setOptional(true);
        AdvancedExpressionFoldingSettings.getInstance().getState().setStreamSpread(true);
        doFoldingTest();
    }

    public void testSpreadTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setConcatenationExpressionsCollapse(true);
        AdvancedExpressionFoldingSettings.getInstance().getState().setOptional(true);
        AdvancedExpressionFoldingSettings.getInstance().getState().setStreamSpread(true);
        doFoldingTest();
    }

    private void disableAllFoldings() {
        // TODO: Find a way to test all folding both together and separately
        AdvancedExpressionFoldingSettings.getInstance().getState().disableAll();
    }

    public void testLombokTestData() {
        AdvancedExpressionFoldingSettings.getInstance().getState().setLombok(true);
        doFoldingTest();
    }
}
