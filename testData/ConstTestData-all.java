<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>java.util.regex.Pattern;

<fold text='🚢' expand='false'>import</fold> <fold text='⚡' expand='false'>static</fold> data.ConstTestData.EOrder.SECOND;
<fold text='🚢' expand='false'>import</fold> <fold text='⚡' expand='false'>static</fold> java.util.regex.Pattern.compile;</fold>


<fold text='/** {@link com.intellij.advancedExpressionFolding.expression.custom.FieldConstExpression )} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.expression.custom.FieldConstExpression )}
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testConstTestData}
 */</fold>
public <fold text='🏛️' expand='false'>class</fold> ConstTestData {

    <fold text='default const' expand='false'><fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>Pattern</fold> PATTERN = Pattern.compile(".*");
    <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Pattern PATTERN_STATIC_IMPORT = compile(".*");

    <fold text='⚡' expand='false'><fold text='default econst' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='' expand='false'></fold>EOrder</fold> ENUM = EOrder.FIRST;
    <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Orderable ENUM_BY_INTERFACE = EOrder.FIRST;
    <fold text='⚡' expand='false'><fold text='default econst' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> EOrder ENUM_STATIC_IMPORT = SECOND;

    <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='' expand='false'></fold>String</fold> PUBLIC_STATIC_FINAL_VAR = "";
    <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>String</fold> PRIVATE_STATIC_FINAL_VAR = "";
    <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='' expand='false'></fold>String</fold> PROTECTED_STATIC_FINAL_VAR = "";
    <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>String</fold> DEFAULT_STATIC_FINAL_VAR = "";

    <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String STRING_JOINER = "<fold text='' expand='false'>" + "</fold>1";
    <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String STRING_JOINER2 = "<fold text='$' expand='false'>" + </fold>DEFAULT_STATIC_FINAL_VAR<fold text='";' expand='false'>;</fold>
    <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String STRING_JOINER3 =<fold text=' "$' expand='false'> </fold>DEFAULT_STATIC_FINAL_VAR<fold text='' expand='false'> + "</fold>";
    <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

    <fold text='const' expand='false'>public <fold text='🔒' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> </fold><fold text='' expand='false'>String</fold> PUBLIC_FINAL_STATIC_VAR = "";
    <fold text='🚫' expand='false'>private</fold> <fold text='const' expand='false'><fold text='🔒' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> </fold><fold text='' expand='false'>String</fold> PRIVATE_FINAL_STATIC_VAR = "";
    <fold text='🛡️' expand='false'>protected</fold> <fold text='🔒' expand='false'><fold text='const' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> </fold><fold text='' expand='false'>String</fold> PROTECTED_FINAL_STATIC_VAR = "";

    <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> public <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>String</fold> STATIC_PUBLIC_FINAL_VAR = "";
    <fold text='⚡' expand='false'>static</fold> <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String STATIC_PRIVATE_FINAL_VAR = "";
    <fold text='⚡' expand='false'>static</fold> <fold text='🛡️' expand='false'>protected</fold> <fold text='🔒' expand='false'>final</fold> String STATIC_PROTECTED_FINAL_VAR = "";

    <fold text='const' expand='false'><fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> public </fold><fold text='' expand='false'>String</fold> STATIC_FINAL_PUBLIC_VAR = "";
    <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🚫' expand='false'>private</fold> String STATIC_FINAL_PRIVATE_VAR = "";
    <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🛡️' expand='false'>protected</fold> String STATIC_FINAL_PROTECTED_VAR = "";

    <fold text='🔒' expand='false'><fold text='const' expand='false'>final</fold> public <fold text='⚡' expand='false'>static</fold> </fold><fold text='' expand='false'>String</fold> FINAL_PUBLIC_STATIC_VAR = "";
    <fold text='🔒' expand='false'>final</fold> <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> String FINAL_PRIVATE_STATIC_VAR = "";
    <fold text='🔒' expand='false'>final</fold> <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'>static</fold> String FINAL_PROTECTED_STATIC_VAR = "";

    <fold text='🔒' expand='false'><fold text='const' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> public </fold><fold text='' expand='false'>String</fold> FINAL_STATIC_PUBLIC_VAR = "";
    <fold text='🔒' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> <fold text='🚫' expand='false'>private</fold> String FINAL_STATIC_PRIVATE_VAR = "";
    <fold text='🔒' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> <fold text='🛡️' expand='false'>protected</fold> String FINAL_STATIC_PROTECTED_VAR = "";

    public <fold text='⚡' expand='false'>static</fold> String PUBLIC_STATIC_VAR = "";
    <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> String PRIVATE_STATIC_VAR = "";
    <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'>static</fold> String PROTECTED_STATIC_VAR = "";

    public <fold text='🔒' expand='false'>final</fold> String PUBLIC_FINAL_VAR = "";
    <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String PRIVATE_FINAL_VAR = "";
    <fold text='🛡️' expand='false'>protected</fold> <fold text='🔒' expand='false'>final</fold> String PROTECTED_FINAL_VAR = "";

    <fold text='⚡' expand='false'>static</fold> public String STATIC_PUBLIC_VAR = "";
    <fold text='⚡' expand='false'>static</fold> <fold text='🚫' expand='false'>private</fold> String STATIC_PRIVATE_VAR = "";
    <fold text='⚡' expand='false'>static</fold> <fold text='🛡️' expand='false'>protected</fold> String STATIC_PROTECTED_VAR = "";

    <fold text='🔒' expand='false'>final</fold> public String FINAL_PUBLIC_VAR = "";
    <fold text='🔒' expand='false'>final</fold> <fold text='🚫' expand='false'>private</fold> String FINAL_PRIVATE_VAR = "";
    <fold text='🔒' expand='false'>final</fold> <fold text='🛡️' expand='false'>protected</fold> String FINAL_PROTECTED_VAR = "";

    <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>int</fold> VERSION = 1;
    <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>int</fold> VERSION_REF = VERSION;

    @SuppressWarnings("ALL")
    <fold text='🏛️' expand='false'>class</fold> ConstsWithAnnotaiton <fold text='{...}' expand='true'>{
        @Deprecated
        <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>Pattern</fold> PATTERN = Pattern.compile(".*");

        @Deprecated
        <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Pattern PATTERN_STATIC_IMPORT = compile(".*");

        @Deprecated
        <fold text='⚡' expand='false'><fold text='default econst' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>EOrder</fold> ENUM = EOrder.FIRST;

        @Deprecated
        <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Orderable ENUM_BY_INTERFACE = EOrder.FIRST;

        @Deprecated
        <fold text='⚡' expand='false'><fold text='default econst' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> EOrder ENUM_STATIC_IMPORT = SECOND;

        @Deprecated
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>String</fold> PUBLIC_STATIC_FINAL_VAR = "";

        @Deprecated
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>String</fold> PRIVATE_STATIC_FINAL_VAR = "";

        @Deprecated
        <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>String</fold> PROTECTED_STATIC_FINAL_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>String</fold> DEFAULT_STATIC_FINAL_VAR = "";

        @Deprecated
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String STRING_JOINER = "<fold text='' expand='false'>" + "</fold>1";

        @Deprecated
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String STRING_JOINER2 = "<fold text='$' expand='false'>" + </fold>DEFAULT_STATIC_FINAL_VAR<fold text='";' expand='false'>;</fold>

        @Deprecated
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String STRING_JOINER3 =<fold text=' "$' expand='false'> </fold>DEFAULT_STATIC_FINAL_VAR<fold text='' expand='false'> + "</fold>";

        @Deprecated
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

        @Deprecated
        <fold text='const' expand='false'>public <fold text='🔒' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> <fold text='' expand='false'></fold>String</fold> PUBLIC_FINAL_STATIC_VAR = "";

        @Deprecated
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'><fold text='const' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> </fold><fold text='' expand='false'>String</fold> PRIVATE_FINAL_STATIC_VAR = "";

        @Deprecated
        <fold text='🛡️' expand='false'>protected</fold> <fold text='🔒' expand='false'><fold text='const' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> <fold text='' expand='false'></fold>String</fold> PROTECTED_FINAL_STATIC_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> public <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>String</fold> STATIC_PUBLIC_FINAL_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'>static</fold> <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String STATIC_PRIVATE_FINAL_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'>static</fold> <fold text='🛡️' expand='false'>protected</fold> <fold text='🔒' expand='false'>final</fold> String STATIC_PROTECTED_FINAL_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> public </fold><fold text='' expand='false'>String</fold> STATIC_FINAL_PUBLIC_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🚫' expand='false'>private</fold> String STATIC_FINAL_PRIVATE_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🛡️' expand='false'>protected</fold> String STATIC_FINAL_PROTECTED_VAR = "";

        @Deprecated
        <fold text='🔒' expand='false'><fold text='const' expand='false'>final</fold> public <fold text='⚡' expand='false'>static</fold> </fold><fold text='' expand='false'>String</fold> FINAL_PUBLIC_STATIC_VAR = "";

        @Deprecated
        <fold text='🔒' expand='false'>final</fold> <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> String FINAL_PRIVATE_STATIC_VAR = "";

        @Deprecated
        <fold text='🔒' expand='false'>final</fold> <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'>static</fold> String FINAL_PROTECTED_STATIC_VAR = "";

        @Deprecated
        <fold text='🔒' expand='false'><fold text='const' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> public </fold><fold text='' expand='false'>String</fold> FINAL_STATIC_PUBLIC_VAR = "";

        @Deprecated
        <fold text='🔒' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> <fold text='🚫' expand='false'>private</fold> String FINAL_STATIC_PRIVATE_VAR = "";

        @Deprecated
        <fold text='🔒' expand='false'>final</fold> <fold text='⚡' expand='false'>static</fold> <fold text='🛡️' expand='false'>protected</fold> String FINAL_STATIC_PROTECTED_VAR = "";

        @Deprecated
        public <fold text='⚡' expand='false'>static</fold> String PUBLIC_STATIC_VAR = "";

        @Deprecated
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> String PRIVATE_STATIC_VAR = "";

        @Deprecated
        <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'>static</fold> String PROTECTED_STATIC_VAR = "";

        @Deprecated
        public <fold text='🔒' expand='false'>final</fold> String PUBLIC_FINAL_VAR = "";

        @Deprecated
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> String PRIVATE_FINAL_VAR = "";

        @Deprecated
        <fold text='🛡️' expand='false'>protected</fold> <fold text='🔒' expand='false'>final</fold> String PROTECTED_FINAL_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'>static</fold> public String STATIC_PUBLIC_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'>static</fold> <fold text='🚫' expand='false'>private</fold> String STATIC_PRIVATE_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'>static</fold> <fold text='🛡️' expand='false'>protected</fold> String STATIC_PROTECTED_VAR = "";

        @Deprecated
        <fold text='🔒' expand='false'>final</fold> public String FINAL_PUBLIC_VAR = "";

        @Deprecated
        <fold text='🔒' expand='false'>final</fold> <fold text='🚫' expand='false'>private</fold> String FINAL_PRIVATE_VAR = "";

        @Deprecated
        <fold text='🔒' expand='false'>final</fold> <fold text='🛡️' expand='false'>protected</fold> String FINAL_PROTECTED_VAR = "";

        @Deprecated
        <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>int</fold> VERSION = 1;

        @Deprecated
        <fold text='⚡' expand='false'><fold text='default const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> </fold><fold text='' expand='false'>int</fold> VERSION_REF = VERSION;
    }</fold>


    public <fold text='🖥️' expand='false'>interface</fold> Orderable {}
    <fold text='📊' expand='false'>enum</fold> EOrder implements Orderabl<fold text='e(0-)' expand='true'>e</fold> <fold text='{...}' expand='true'>{
        FIRST,
        SECOND;
    }</fold>

}

