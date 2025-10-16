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

    [9:"public static final"] String STRING_JOINER = "[10:"\" + \""]1";
    [11:"public static final"] String STRING_JOINER2 = "[12:"\" + "]DEFAULT_STATIC_FINAL_VAR[12:";"]
    [13:"public static final"] String STRING_JOINER3 =[14:" "]DEFAULT_STATIC_FINAL_VAR[14:" + \""]";
    [15:"public static final"] String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

    [16:"public final static "][16:"String"] PUBLIC_FINAL_STATIC_VAR = "";
    private [17:"final static "][17:"String"] PRIVATE_FINAL_STATIC_VAR = "";
    protected [18:"final static "][18:"String"] PROTECTED_FINAL_STATIC_VAR = "";

    [19:"static public final "][19:"String"] STATIC_PUBLIC_FINAL_VAR = "";
    static private final String STATIC_PRIVATE_FINAL_VAR = "";
    static protected final String STATIC_PROTECTED_FINAL_VAR = "";

    [20:"static final public "][20:"String"] STATIC_FINAL_PUBLIC_VAR = "";
    static final private String STATIC_FINAL_PRIVATE_VAR = "";
    static final protected String STATIC_FINAL_PROTECTED_VAR = "";

    [21:"final public static "][21:"String"] FINAL_PUBLIC_STATIC_VAR = "";
    final private static String FINAL_PRIVATE_STATIC_VAR = "";
    final protected static String FINAL_PROTECTED_STATIC_VAR = "";

    [22:"final static public "][22:"String"] FINAL_STATIC_PUBLIC_VAR = "";
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

    [23:"static final"] String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    [24:"static final "][24:"int"] VERSION = 1;
    [25:"static final "][25:"int"] VERSION_REF = VERSION;

    @SuppressWarnings("ALL")
    class ConstsWithAnnotation {
        @Deprecated
        [26:"static final "][26:"Pattern"] PATTERN = Pattern.compile(".*");

        @Deprecated
        [27:"static final"] Pattern PATTERN_STATIC_IMPORT = compile(".*");

        @Deprecated
        [28:"static final "][28:"EOrder"] ENUM = EOrder.FIRST;

        @Deprecated
        [29:"static final"] Orderable ENUM_BY_INTERFACE = EOrder.FIRST;

        @Deprecated
        [30:"static final"] EOrder ENUM_STATIC_IMPORT = SECOND;

        @Deprecated
        [31:"public static final "][31:"String"] PUBLIC_STATIC_FINAL_VAR = "";

        @Deprecated
        private [32:"static final "][32:"String"] PRIVATE_STATIC_FINAL_VAR = "";

        @Deprecated
        protected [33:"static final "][33:"String"] PROTECTED_STATIC_FINAL_VAR = "";

        @Deprecated
        [34:"static final "][34:"String"] DEFAULT_STATIC_FINAL_VAR = "";

        @Deprecated
        [35:"public static final"] String STRING_JOINER = "[36:"\" + \""]1";

        @Deprecated
        [37:"public static final"] String STRING_JOINER2 = "[38:"\" + "]DEFAULT_STATIC_FINAL_VAR[38:";"]

        @Deprecated
        [39:"public static final"] String STRING_JOINER3 =[40:" "]DEFAULT_STATIC_FINAL_VAR[40:" + \""]";

        @Deprecated
        [41:"public static final"] String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

        @Deprecated
        [42:"public final static "][42:"String"] PUBLIC_FINAL_STATIC_VAR = "";

        @Deprecated
        private [43:"final static "][43:"String"] PRIVATE_FINAL_STATIC_VAR = "";

        @Deprecated
        protected [44:"final static "][44:"String"] PROTECTED_FINAL_STATIC_VAR = "";

        @Deprecated
        [45:"static public final "][45:"String"] STATIC_PUBLIC_FINAL_VAR = "";

        @Deprecated
        static private final String STATIC_PRIVATE_FINAL_VAR = "";

        @Deprecated
        static protected final String STATIC_PROTECTED_FINAL_VAR = "";

        @Deprecated
        [46:"static final public "][46:"String"] STATIC_FINAL_PUBLIC_VAR = "";

        @Deprecated
        static final private String STATIC_FINAL_PRIVATE_VAR = "";

        @Deprecated
        static final protected String STATIC_FINAL_PROTECTED_VAR = "";

        @Deprecated
        [47:"final public static "][47:"String"] FINAL_PUBLIC_STATIC_VAR = "";

        @Deprecated
        final private static String FINAL_PRIVATE_STATIC_VAR = "";

        @Deprecated
        final protected static String FINAL_PROTECTED_STATIC_VAR = "";

        @Deprecated
        [48:"final static public "][48:"String"] FINAL_STATIC_PUBLIC_VAR = "";

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
        [49:"static final"] String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        [50:"static final "][50:"int"] VERSION = 1;

        @Deprecated
        [51:"static final "][51:"int"] VERSION_REF = VERSION;
    }


    public interface Orderable {}
    enum EOrder implements Orderabl[52:"e"] {
        FIRST,
        SECOND;
    }

}

