package data;

import <fold text='...' expand='false'>java.util.regex.Pattern;

import static data.ConstTestData.EOrder.SECOND;
import static java.util.regex.Pattern.compile;</fold>


<fold text='/** {@link com.intellij.advancedExpressionFolding.expression.custom.FieldConstExpression )} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.expression.custom.FieldConstExpression )}
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testConstTestData}
 */</fold>
public class ConstTestData {

    <fold text='default const' expand='false'>static final </fold><fold text='' expand='false'>Pattern</fold> PATTERN = Pattern.compile(".*");
    <fold text='default const' expand='false'>static final</fold> Pattern PATTERN_STATIC_IMPORT = compile(".*");

    <fold text='default econst' expand='false'>static final </fold><fold text='' expand='false'>EOrder</fold> ENUM = EOrder.FIRST;
    <fold text='default const' expand='false'>static final</fold> Orderable ENUM_BY_INTERFACE = EOrder.FIRST;
    <fold text='default econst' expand='false'>static final</fold> EOrder ENUM_STATIC_IMPORT = SECOND;

    <fold text='default const' expand='false'>public static final </fold><fold text='' expand='false'>String</fold> PUBLIC_STATIC_FINAL_VAR = "";
    private <fold text='default const' expand='false'>static final </fold><fold text='' expand='false'>String</fold> PRIVATE_STATIC_FINAL_VAR = "";
    protected <fold text='default const' expand='false'>static final </fold><fold text='' expand='false'>String</fold> PROTECTED_STATIC_FINAL_VAR = "";
    <fold text='default const' expand='false'>static final </fold><fold text='' expand='false'>String</fold> DEFAULT_STATIC_FINAL_VAR = "";

    <fold text='default const' expand='false'>public static final</fold> String STRING_JOINER = "<fold text='' expand='false'>" + "</fold>1";
    <fold text='default const' expand='false'>public static final</fold> String STRING_JOINER2 = "<fold text='$' expand='false'>" + </fold>DEFAULT_STATIC_FINAL_VAR<fold text='";' expand='false'>;</fold>
    <fold text='default const' expand='false'>public static final</fold> String STRING_JOINER3 =<fold text=' "$' expand='false'> </fold>DEFAULT_STATIC_FINAL_VAR<fold text='' expand='false'> + "</fold>";
    <fold text='default const' expand='false'>public static final</fold> String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

    <fold text='default const' expand='false'>public final static </fold><fold text='' expand='false'>String</fold> PUBLIC_FINAL_STATIC_VAR = "";
    private <fold text='default const' expand='false'>final static </fold><fold text='' expand='false'>String</fold> PRIVATE_FINAL_STATIC_VAR = "";
    protected <fold text='default const' expand='false'>final static </fold><fold text='' expand='false'>String</fold> PROTECTED_FINAL_STATIC_VAR = "";

    <fold text='default const' expand='false'>static public final </fold><fold text='' expand='false'>String</fold> STATIC_PUBLIC_FINAL_VAR = "";
    static private final String STATIC_PRIVATE_FINAL_VAR = "";
    static protected final String STATIC_PROTECTED_FINAL_VAR = "";

    <fold text='default const' expand='false'>static final public </fold><fold text='' expand='false'>String</fold> STATIC_FINAL_PUBLIC_VAR = "";
    static final private String STATIC_FINAL_PRIVATE_VAR = "";
    static final protected String STATIC_FINAL_PROTECTED_VAR = "";

    <fold text='default const' expand='false'>final public static </fold><fold text='' expand='false'>String</fold> FINAL_PUBLIC_STATIC_VAR = "";
    final private static String FINAL_PRIVATE_STATIC_VAR = "";
    final protected static String FINAL_PROTECTED_STATIC_VAR = "";

    <fold text='default const' expand='false'>final static public </fold><fold text='' expand='false'>String</fold> FINAL_STATIC_PUBLIC_VAR = "";
    final static private String FINAL_STATIC_PRIVATE_VAR = "";
    final static protected String FINAL_STATIC_PROTECTED_VAR = "";

    public static String PUBLIC_STATIC_VAR = "";
    private static String PRIVATE_STATIC_VAR = "";
    protected static String PROTECTED_STATIC_VAR = "";

    public final String PUBLIC_FINAL_VAR = "";
    private final String PRIVATE_FINAL_VAR = "";
    protected final String PROTECTED_FINAL_VAR = "";

    static public String STATIC_PUBLIC_VAR = "";
    static private String STATIC_PRIVATE_VAR = "";
    static protected String STATIC_PROTECTED_VAR = "";

    final public String FINAL_PUBLIC_VAR = "";
    final private String FINAL_PRIVATE_VAR = "";
    final protected String FINAL_PROTECTED_VAR = "";

    <fold text='default const' expand='false'>static final</fold> String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    <fold text='default const' expand='false'>static final </fold><fold text='' expand='false'>int</fold> VERSION = 1;
    <fold text='default const' expand='false'>static final </fold><fold text='' expand='false'>int</fold> VERSION_REF = VERSION;

    public interface Orderable {}
    enum EOrder implements Orderable <fold text='{...}' expand='true'>{
        FIRST,
        SECOND;
    }</fold>

}

