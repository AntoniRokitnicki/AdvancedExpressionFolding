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

    econst EOrder ENUM = EOrder.FIRST;
    econst EOrder ENUM_STATIC_IMPORT = SECOND;

    const Pattern PATTERN = Pattern.compile(".*");
    const Pattern PATTERN_STATIC_IMPORT = compile(".*");

    const PUBLIC_STATIC_FINAL_VAR = "";
    const PRIVATE_STATIC_FINAL_VAR = "";
    const PROTECTED_STATIC_FINAL_VAR = "";
    const DEFAULT_STATIC_FINAL_VAR = "";
    const String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    const VERSION = 1;
    const int VERSION_REF = VERSION;


    enum EOrder {
        FIRST,
        SECOND;
    }

}

