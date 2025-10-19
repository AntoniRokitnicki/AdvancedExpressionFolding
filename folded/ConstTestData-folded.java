package data;

import java.util.regex.Pattern;

import static data.ConstTestData.EOrder.SECOND;
import static java.util.regex.Pattern.compile;


/**
 * {@link com.intellij.advancedExpressionFolding.expression.semantic.FieldConstExpression )}
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testConstTestData}
 */
public class ConstTestData {

    default const PATTERN = Pattern.compile(".*");
    default const Pattern PATTERN_STATIC_IMPORT = compile(".*");

    default econst ENUM = EOrder.FIRST;
    default const Orderable ENUM_BY_INTERFACE = EOrder.FIRST;
    default econst EOrder ENUM_STATIC_IMPORT = SECOND;

    const PUBLIC_STATIC_FINAL_VAR = "";
    private const PRIVATE_STATIC_FINAL_VAR = "";
    protected const PROTECTED_STATIC_FINAL_VAR = "";
    default const DEFAULT_STATIC_FINAL_VAR = "";

    const String STRING_JOINER = "" + "1";
    const String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;
    const String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";
    const String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

    const PUBLIC_FINAL_STATIC_VAR = "";
    private const PRIVATE_FINAL_STATIC_VAR = "";
    protected const PROTECTED_FINAL_STATIC_VAR = "";

    const STATIC_PUBLIC_FINAL_VAR = "";
    private const STATIC_PRIVATE_FINAL_VAR = "";
    protected const STATIC_PROTECTED_FINAL_VAR = "";

    const STATIC_FINAL_PUBLIC_VAR = "";
    private const STATIC_FINAL_PRIVATE_VAR = "";
    protected const STATIC_FINAL_PROTECTED_VAR = "";

    const FINAL_PUBLIC_STATIC_VAR = "";
    private const FINAL_PRIVATE_STATIC_VAR = "";
    protected const FINAL_PROTECTED_STATIC_VAR = "";

    const FINAL_STATIC_PUBLIC_VAR = "";
    private const FINAL_STATIC_PRIVATE_VAR = "";
    protected const FINAL_STATIC_PROTECTED_VAR = "";

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

    @SuppressWarnings("ALL")
    class ConstsWithAnnotation {
        @Deprecated
        default const PATTERN = Pattern.compile(".*");

        @Deprecated
        default const Pattern PATTERN_STATIC_IMPORT = compile(".*");

        @Deprecated
        default econst ENUM = EOrder.FIRST;

        @Deprecated
        default const Orderable ENUM_BY_INTERFACE = EOrder.FIRST;

        @Deprecated
        default econst EOrder ENUM_STATIC_IMPORT = SECOND;

        @Deprecated
        const PUBLIC_STATIC_FINAL_VAR = "";

        @Deprecated
        private const PRIVATE_STATIC_FINAL_VAR = "";

        @Deprecated
        protected const PROTECTED_STATIC_FINAL_VAR = "";

        @Deprecated
        default const DEFAULT_STATIC_FINAL_VAR = "";

        @Deprecated
        const String STRING_JOINER = "" + "1";

        @Deprecated
        const String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        const String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";

        @Deprecated
        const String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

        @Deprecated
        const PUBLIC_FINAL_STATIC_VAR = "";

        @Deprecated
        private const PRIVATE_FINAL_STATIC_VAR = "";

        @Deprecated
        protected const PROTECTED_FINAL_STATIC_VAR = "";

        @Deprecated
        const STATIC_PUBLIC_FINAL_VAR = "";

        @Deprecated
        private const STATIC_PRIVATE_FINAL_VAR = "";

        @Deprecated
        protected const STATIC_PROTECTED_FINAL_VAR = "";

        @Deprecated
        const STATIC_FINAL_PUBLIC_VAR = "";

        @Deprecated
        private const STATIC_FINAL_PRIVATE_VAR = "";

        @Deprecated
        protected const STATIC_FINAL_PROTECTED_VAR = "";

        @Deprecated
        const FINAL_PUBLIC_STATIC_VAR = "";

        @Deprecated
        private const FINAL_PRIVATE_STATIC_VAR = "";

        @Deprecated
        protected const FINAL_PROTECTED_STATIC_VAR = "";

        @Deprecated
        const FINAL_STATIC_PUBLIC_VAR = "";

        @Deprecated
        private const FINAL_STATIC_PRIVATE_VAR = "";

        @Deprecated
        protected const FINAL_STATIC_PROTECTED_VAR = "";

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
        default const String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        default const VERSION = 1;

        @Deprecated
        default const VERSION_REF = VERSION;
    }


    public interface Orderable {}
    enum EOrder implements Orderable {
        FIRST,
        SECOND;
    }

}

