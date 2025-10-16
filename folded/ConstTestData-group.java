package data;

import java.util.regex.Pattern;

import static data.ConstTestData.EOrder.SECOND;
import static java.util.regex.Pattern.compile;


/**
 * {@link com.intellij.advancedExpressionFolding.expression.semantic.FieldConstExpression )}
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testConstTestData}
 */
public class ConstTestData {

    [0:"static final "][0:"Pattern"] PATTERN = Pattern.compile(".*");
    [1:"static final"] Pattern PATTERN_STATIC_IMPORT = compile(".*");

    [2:"static final "][2:"EOrder"] ENUM = EOrder.FIRST;
    [3:"static final"] Orderable ENUM_BY_INTERFACE = EOrder.FIRST;
    [4:"static final"] EOrder ENUM_STATIC_IMPORT = SECOND;

    [5:"public static final "][5:"String"] PUBLIC_STATIC_FINAL_VAR = "";
    private [6:"static final "][6:"String"] PRIVATE_STATIC_FINAL_VAR = "";
    protected [7:"static final "][7:"String"] PROTECTED_STATIC_FINAL_VAR = "";
    [8:"static final "][8:"String"] DEFAULT_STATIC_FINAL_VAR = "";

    [9:"public static final"] String STRING_JOINER = "" + "1";
    [10:"public static final"] String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;
    [11:"public static final"] String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";
    [12:"public static final"] String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

    [13:"public final static "][13:"String"] PUBLIC_FINAL_STATIC_VAR = "";
    private [14:"final static "][14:"String"] PRIVATE_FINAL_STATIC_VAR = "";
    protected [15:"final static "][15:"String"] PROTECTED_FINAL_STATIC_VAR = "";

    [16:"static public final "][16:"String"] STATIC_PUBLIC_FINAL_VAR = "";
    static private final String STATIC_PRIVATE_FINAL_VAR = "";
    static protected final String STATIC_PROTECTED_FINAL_VAR = "";

    [17:"static final public "][17:"String"] STATIC_FINAL_PUBLIC_VAR = "";
    static final private String STATIC_FINAL_PRIVATE_VAR = "";
    static final protected String STATIC_FINAL_PROTECTED_VAR = "";

    [18:"final public static "][18:"String"] FINAL_PUBLIC_STATIC_VAR = "";
    final private static String FINAL_PRIVATE_STATIC_VAR = "";
    final protected static String FINAL_PROTECTED_STATIC_VAR = "";

    [19:"final static public "][19:"String"] FINAL_STATIC_PUBLIC_VAR = "";
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

    [20:"static final"] String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    [21:"static final "][21:"int"] VERSION = 1;
    [22:"static final "][22:"int"] VERSION_REF = VERSION;

    @SuppressWarnings("ALL")
    class ConstsWithAnnotation {
        @Deprecated
        [23:"static final "][23:"Pattern"] PATTERN = Pattern.compile(".*");

        @Deprecated
        [24:"static final"] Pattern PATTERN_STATIC_IMPORT = compile(".*");

        @Deprecated
        [25:"static final "][25:"EOrder"] ENUM = EOrder.FIRST;

        @Deprecated
        [26:"static final"] Orderable ENUM_BY_INTERFACE = EOrder.FIRST;

        @Deprecated
        [27:"static final"] EOrder ENUM_STATIC_IMPORT = SECOND;

        @Deprecated
        [28:"public static final "][28:"String"] PUBLIC_STATIC_FINAL_VAR = "";

        @Deprecated
        private [29:"static final "][29:"String"] PRIVATE_STATIC_FINAL_VAR = "";

        @Deprecated
        protected [30:"static final "][30:"String"] PROTECTED_STATIC_FINAL_VAR = "";

        @Deprecated
        [31:"static final "][31:"String"] DEFAULT_STATIC_FINAL_VAR = "";

        @Deprecated
        [32:"public static final"] String STRING_JOINER = "" + "1";

        @Deprecated
        [33:"public static final"] String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        [34:"public static final"] String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";

        @Deprecated
        [35:"public static final"] String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

        @Deprecated
        [36:"public final static "][36:"String"] PUBLIC_FINAL_STATIC_VAR = "";

        @Deprecated
        private [37:"final static "][37:"String"] PRIVATE_FINAL_STATIC_VAR = "";

        @Deprecated
        protected [38:"final static "][38:"String"] PROTECTED_FINAL_STATIC_VAR = "";

        @Deprecated
        [39:"static public final "][39:"String"] STATIC_PUBLIC_FINAL_VAR = "";

        @Deprecated
        static private final String STATIC_PRIVATE_FINAL_VAR = "";

        @Deprecated
        static protected final String STATIC_PROTECTED_FINAL_VAR = "";

        @Deprecated
        [40:"static final public "][40:"String"] STATIC_FINAL_PUBLIC_VAR = "";

        @Deprecated
        static final private String STATIC_FINAL_PRIVATE_VAR = "";

        @Deprecated
        static final protected String STATIC_FINAL_PROTECTED_VAR = "";

        @Deprecated
        [41:"final public static "][41:"String"] FINAL_PUBLIC_STATIC_VAR = "";

        @Deprecated
        final private static String FINAL_PRIVATE_STATIC_VAR = "";

        @Deprecated
        final protected static String FINAL_PROTECTED_STATIC_VAR = "";

        @Deprecated
        [42:"final static public "][42:"String"] FINAL_STATIC_PUBLIC_VAR = "";

        @Deprecated
        final static private String FINAL_STATIC_PRIVATE_VAR = "";

        @Deprecated
        final static protected String FINAL_STATIC_PROTECTED_VAR = "";

        @Deprecated
        public static String PUBLIC_STATIC_VAR = "";

        @Deprecated
        private static String PRIVATE_STATIC_VAR = "";

        @Deprecated
        protected static String PROTECTED_STATIC_VAR = "";

        @Deprecated
        public final String PUBLIC_FINAL_VAR = "";

        @Deprecated
        private final String PRIVATE_FINAL_VAR = "";

        @Deprecated
        protected final String PROTECTED_FINAL_VAR = "";

        @Deprecated
        static public String STATIC_PUBLIC_VAR = "";

        @Deprecated
        static private String STATIC_PRIVATE_VAR = "";

        @Deprecated
        static protected String STATIC_PROTECTED_VAR = "";

        @Deprecated
        final public String FINAL_PUBLIC_VAR = "";

        @Deprecated
        final private String FINAL_PRIVATE_VAR = "";

        @Deprecated
        final protected String FINAL_PROTECTED_VAR = "";

        @Deprecated
        [43:"static final"] String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        [44:"static final "][44:"int"] VERSION = 1;

        @Deprecated
        [45:"static final "][45:"int"] VERSION_REF = VERSION;
    }


    public interface Orderable {}
    enum EOrder implements Orderable {
        FIRST,
        SECOND;
    }

}

