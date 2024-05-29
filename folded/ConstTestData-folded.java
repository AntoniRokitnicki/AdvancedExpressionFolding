package data;

import java.util.regex.Pattern;

import static data.ConstTestData.EOrder.SECOND;
import static java.util.regex.Pattern.compile;


/**
 * {@link com.intellij.advancedExpressionFolding.expression.custom.FieldConstExpression )}
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testConstTestData}
 */
public class ConstTestData {

    default const PATTERN = Pattern.compile(".*");
    default const Pattern PATTERN_STATIC_IMPORT = compile(".*");

    default econst ENUM = EOrder.FIRST;
    default const Orderable ENUM_BY_INTERFACE = EOrder.FIRST;
    default econst EOrder ENUM_STATIC_IMPORT = SECOND;

    default const PUBLIC_STATIC_FINAL_VAR = "";
    private default const PRIVATE_STATIC_FINAL_VAR = "";
    protected default const PROTECTED_STATIC_FINAL_VAR = "";
    default const DEFAULT_STATIC_FINAL_VAR = "";

    default const String STRING_JOINER = "" + "1";
    default const String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;
    default const String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";
    default const String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

    default const PUBLIC_FINAL_STATIC_VAR = "";
    private default const PRIVATE_FINAL_STATIC_VAR = "";
    protected default const PROTECTED_FINAL_STATIC_VAR = "";

    default const STATIC_PUBLIC_FINAL_VAR = "";
    static private final String STATIC_PRIVATE_FINAL_VAR = "";
    static protected final String STATIC_PROTECTED_FINAL_VAR = "";

    default const STATIC_FINAL_PUBLIC_VAR = "";
    static final private String STATIC_FINAL_PRIVATE_VAR = "";
    static final protected String STATIC_FINAL_PROTECTED_VAR = "";

    default const FINAL_PUBLIC_STATIC_VAR = "";
    final private static String FINAL_PRIVATE_STATIC_VAR = "";
    final protected static String FINAL_PROTECTED_STATIC_VAR = "";

    default const FINAL_STATIC_PUBLIC_VAR = "";
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

    default const String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    default const VERSION = 1;
    default const VERSION_REF = VERSION;

    public interface Orderable {}
    enum EOrder implements Orderable {
        FIRST,
        SECOND;
    }

}

