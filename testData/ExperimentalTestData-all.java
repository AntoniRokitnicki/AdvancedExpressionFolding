<fold text='📦' expand='false'>package</fold> data;


<fold text='🚢' expand='false'>import</fold> java.util.*;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.extension.ExperimentalExt#singletonField(com.intellij.psi.PsiReferenceExpression)}
 * {@link com.intellij.advancedExpressionFolding.extension.NullableExt#foldFieldConstructor(com.intellij.psi.PsiField, com.intellij.openapi.editor.Document)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */</fold>
public <fold text='🏛️' expand='false'>class</fold> ExperimentalTestData {

    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Const <fold text='{...}' expand='true'>{
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold><fold text='' expand='true'>()</fold>;
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_ANN =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='{}' expand='true'>ConstClass<fold text='' expand='true'>()</fold> <fold text='{...}' expand='true'>{
        }</fold></fold>;
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_SUB = new SubConstClass();
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_SUB_ANN = new SubConstClass() <fold text='{...}' expand='true'>{
        }</fold>;

        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> HashMap<String, String> MAP =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<></fold><fold text='' expand='true'>()</fold>;
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> HashMap<String, String> MAP2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<fold text='<~>' expand='false'><String, String></fold></fold><fold text='' expand='true'>()</fold>;
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Map<String, String> MAP3 = new HashMap<>();
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Map<String, String> MAP_TREE = new TreeMap<>();
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Map<String, String> MAP4 = Map.of();

        <fold text='🚫' expand='false'>private</fold> <fold text='const' expand='false'><fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> List<String> LIST = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> List<String> LIST2 = List.of();
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> List<String> LIST_SINGLE = List.of("1");
        <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> List<String> LIST_LINKED = new LinkedList<>();


        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_PARAM_1 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(<fold text='✅' expand='false'>true</fold>);
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_PARAM_2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(<fold text='❌' expand='false'>false</fold>, LIST_SINGLE<fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>));

        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() <fold text='{...}' expand='true'>{
            <fold text='🔢' expand='false'>int</fold> i = 1;
        }</fold>;
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() <fold text='{...}' expand='true'>{
            public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'>static</fold> ConstClass SELF_NULL = <fold text='🕳️' expand='false'>null</fold>;
        <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'>static</fold> ConstClass EMPTY;
    }</fold>
    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> Fields <fold text='{...}' expand='true'>{
        <fold text='🔒' expand='false'>final</fold> ConstClass SELF =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold><fold text='' expand='true'>()</fold>;
        ConstClass SELF_ANN =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='{}' expand='true'>ConstClass<fold text='' expand='true'>()</fold> <fold text='{...}' expand='true'>{
        }</fold></fold>;
        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_SUB = new SubConstClass();
        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_SUB_ANN = new SubConstClass() <fold text='{...}' expand='true'>{
        }</fold>;

        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> HashMap<String, String> MAP =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<></fold><fold text='' expand='true'>()</fold>;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> HashMap<String, String> MAP2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<fold text='<~>' expand='false'><String, String></fold><fold text='' expand='true'></fold>()</fold>;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> Map<String, String> MAP3 = new HashMap<>();

        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> List<String> LIST = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
        <fold text='🚫' expand='false'>private</fold> <fold text='🔒' expand='false'>final</fold> List<String> LIST2 = List.of("1");

        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_PARAM_1 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(<fold text='✅' expand='false'>true</fold>);
        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_PARAM_2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(<fold text='❌' expand='false'>false</fold>, LIST2<fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>));

        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() <fold text='{...}' expand='true'>{
            <fold text='🔢' expand='false'>int</fold> i = 1;
        }</fold>;
        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() <fold text='{...}' expand='true'>{
            public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        <fold text='🛡️' expand='false'>protected</fold> ConstClass SELF_NULL = <fold text='🕳️' expand='false'>null</fold>;
        <fold text='🛡️' expand='false'>protected</fold> ConstClass EMPTY;
    }</fold>



    <fold text='@NoArgsConstructor @AllArgsConstructor s' expand='false'><fold text='⚡' expand='false'>s</fold>tatic</fold> <fold text='🏛️' expand='false'>class</fold> ConstClass <fold text='{...}' expand='true'>{
        <fold text='🔘' expand='false'>boolean</fold> ok;
        <fold text='@ToString S' expand='false'>S</fold>tring string;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public ConstClass() <fold text='{}' expand='true'>{
        }</fold></fold>

        public ConstClass(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public ConstClass(<fold text='🔘' expand='false'>boolean</fold> ok, String string) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold>;
            <fold text='📍' expand='false'>this</fold>.string = <fold text='<<' expand='false'>string</fold>;
        }</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public String toString() <fold text='{...}' expand='true'>{
            <fold text='🔙' expand='false'>return</fold> new StringJoiner(", ",<fold text=' "${' expand='false'> </fold>ConstClass.<fold text='🏛️' expand='false'>class</fold>.<fold text='simpleName' expand='false'>getSimpleName()</fold><fold text='}' expand='false'> + "</fold>[", "]")
                    .add("string='<fold text='$' expand='false'>" + </fold>string<fold text='' expand='false'> + "</fold>'")
                    .toString();
        }</fold></fold>
    }</fold>


    <fold text='⚡' expand='false'>static</fold> <fold text='🏛️' expand='false'>class</fold> SubConstClass extends ConstClass <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> <fold text='🏛️' expand='false'>class</fold> SubConstClass2 extends ConstClass <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='@Getter @Setter c' expand='false'><fold text='🏛️' expand='false'>c</fold>lass</fold> NamelessProperty <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> NamelessProperty prop;

        public NamelessProperty get()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>prop<fold text='' expand='true'>;</fold><fold text=' }' expand='false'><fold text='' expand='true'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public data.ExperimentalTestData.NamelessProperty getProp()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>prop<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setProp(NamelessProperty prop)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.prop = <fold text='<<' expand='false'>prop</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public <fold text='💀' expand='false'>void</fold> set(NamelessProperty s)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.prop = s<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        NamelessProperty main(NamelessProperty s, NamelessProperty namelessProperty) <fold text='{...}' expand='true'>{
            s.set(namelessProperty.<fold text='prop' expand='false'>getProp()</fold>.get());
            s.set(namelessProperty.<fold text='prop' expand='false'>getProp()</fold>);
            s.<fold text='prop = ' expand='false'>setProp(</fold>namelessProperty.<fold text='prop' expand='false'>getProp()</fold>.get()<fold text='' expand='false'>)</fold>;
            s.<fold text='prop = ' expand='false'>setProp(</fold>namelessProperty<fold text='<<' expand='false'>.getProp()</fold><fold text='' expand='false'>)</fold>;


            s.set(namelessProperty.get());
            s.set(namelessProperty.get().get()); //TODO:

            <fold text='' expand='false'>System.out.</fold>println(s.get());
            s.get();
            s.set(namelessProperty);
            s.set(s.get());
            s.set(namelessProperty.get().get().get());
            s.set(main(s.get(), namelessProperty.get()));
            <fold text='🔙' expand='false'>return</fold> namelessProperty.get();
        }</fold>
    }</fold>


}
