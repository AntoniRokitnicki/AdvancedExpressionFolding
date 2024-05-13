package data;

import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiTypeElement;

import java.util.regex.Pattern;

import static data.ConstTestData.EOrder.SECOND;
import static java.util.regex.Pattern.compile;


/**
 * {@link com.intellij.advancedExpressionFolding.extension.NullableExt#fieldConstExpression(PsiField, PsiTypeElement, Document)} )}
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testConstTestData}
 */
public class ConstTestData {

    static final EOrder ENUM = EOrder.FIRST;
    static final EOrder ENUM_STATIC_IMPORT = SECOND;

    static final Pattern PATTERN = Pattern.compile(".*");
    static final Pattern PATTERN_STATIC_IMPORT = compile(".*");

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    private static final String PRIVATE_STATIC_FINAL_VAR = "";
    protected static final String PROTECTED_STATIC_FINAL_VAR = "";
    static final String DEFAULT_STATIC_FINAL_VAR = "";
    static final String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    static final int VERSION = 1;
    static final int VERSION_REF = VERSION;


    enum EOrder {
        FIRST,
        SECOND;
    }

}

