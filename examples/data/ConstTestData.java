package data;

import java.util.regex.Pattern;

import static data.ConstTestData.EOrder.SECOND;
import static java.util.regex.Pattern.compile;


/**
 * {@link com.intellij.advancedExpressionFolding.expression.custom.FieldConstExpression )}
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testConstTestData}
 */
public class ConstTestData {

    static final Pattern PATTERN = Pattern.compile(".*");
    static final Pattern PATTERN_STATIC_IMPORT = compile(".*");

    static final EOrder ENUM = EOrder.FIRST;
    static final Orderable ENUM_BY_INTERFACE = EOrder.FIRST;
    static final EOrder ENUM_STATIC_IMPORT = SECOND;

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    private static final String PRIVATE_STATIC_FINAL_VAR = "";
    protected static final String PROTECTED_STATIC_FINAL_VAR = "";
    static final String DEFAULT_STATIC_FINAL_VAR = "";

    public static final String STRING_JOINER = "" + "1";
    public static final String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;
    public static final String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";
    public static final String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

    public final static String PUBLIC_FINAL_STATIC_VAR = "";
    private final static String PRIVATE_FINAL_STATIC_VAR = "";
    protected final static String PROTECTED_FINAL_STATIC_VAR = "";

    static public final String STATIC_PUBLIC_FINAL_VAR = "";
    static private final String STATIC_PRIVATE_FINAL_VAR = "";
    static protected final String STATIC_PROTECTED_FINAL_VAR = "";

    static final public String STATIC_FINAL_PUBLIC_VAR = "";
    static final private String STATIC_FINAL_PRIVATE_VAR = "";
    static final protected String STATIC_FINAL_PROTECTED_VAR = "";

    final public static String FINAL_PUBLIC_STATIC_VAR = "";
    final private static String FINAL_PRIVATE_STATIC_VAR = "";
    final protected static String FINAL_PROTECTED_STATIC_VAR = "";

    final static public String FINAL_STATIC_PUBLIC_VAR = "";
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

    static final String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    static final int VERSION = 1;
    static final int VERSION_REF = VERSION;

    public interface Orderable {}
    enum EOrder implements Orderable {
        FIRST,
        SECOND;
    }

}

