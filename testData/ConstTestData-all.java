package data;

import <fold text='...' expand='false'>com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiTypeElement;

import java.util.regex.Pattern;

import static data.ConstTestData.EOrder.SECOND;
import static java.util.regex.Pattern.compile;</fold>


<fold text='/** {@link com.intellij.advancedExpressionFolding.extension.NullableExt#fieldConstExpression(PsiField, PsiTypeElement, Document)} )} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.extension.NullableExt#fieldConstExpression(PsiField, PsiTypeElement, Document)} )}
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testConstTestData}
 */</fold>
public class ConstTestData {

    <fold text='econst' expand='false'>static final</fold> EOrder ENUM = EOrder.FIRST;
    <fold text='econst' expand='false'>static final</fold> EOrder ENUM_STATIC_IMPORT = SECOND;

    <fold text='const' expand='false'>static final</fold> Pattern PATTERN = Pattern.compile(".*");
    <fold text='const' expand='false'>static final</fold> Pattern PATTERN_STATIC_IMPORT = compile(".*");

    <fold text='const' expand='false'>public static final </fold><fold text='' expand='false'>String</fold> PUBLIC_STATIC_FINAL_VAR = "";
    <fold text='const' expand='false'>private static final </fold><fold text='' expand='false'>String</fold> PRIVATE_STATIC_FINAL_VAR = "";
    <fold text='const' expand='false'>protected static final </fold><fold text='' expand='false'>String</fold> PROTECTED_STATIC_FINAL_VAR = "";
    <fold text='const' expand='false'>static final </fold><fold text='' expand='false'>String</fold> DEFAULT_STATIC_FINAL_VAR = "";
    <fold text='const' expand='false'>static final</fold> String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    <fold text='const' expand='false'>static final </fold><fold text='' expand='false'>int</fold> VERSION = 1;
    <fold text='const' expand='false'>static final</fold> int VERSION_REF = VERSION;


    enum EOrder <fold text='{...}' expand='true'>{
        FIRST,
        SECOND;
    }</fold>

}

