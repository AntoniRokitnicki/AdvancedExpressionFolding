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
public class ExperimentalTestData {

    <fold text='⚡' expand='false'>static</fold> class Const <fold text='{...}' expand='true'>{
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold><fold text='' expand='true'>()</fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_ANN =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> <fold text='{}' expand='true'></fold>ConstClass<fold text='' expand='true'>()</fold> <fold text='{...}' expand='true'>{
        }</fold></fold>;
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_SUB = new SubConstClass();
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_SUB_ANN = new SubConstClass() <fold text='{...}' expand='true'>{
        }</fold>;

        private <fold text='const' expand='false'>static final</fold> HashMap<String, String> MAP =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> <fold text='' expand='true'></fold>HashMap<></fold><fold text='' expand='true'>()</fold>;
        private <fold text='const' expand='false'>static final</fold> HashMap<String, String> MAP2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<fold text='<~>' expand='false'><String, String></fold></fold><fold text='' expand='true'>()</fold>;
        private <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Map<String, String> MAP3 = new HashMap<>();
        private <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Map<String, String> MAP_TREE = new TreeMap<>();
        private <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> Map<String, String> MAP4 = Map.of();

        private <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> List<String> LIST = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
        private <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> List<String> LIST2 = List.of();
        private <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> List<String> LIST_SINGLE = List.of("1");
        private <fold text='⚡' expand='false'><fold text='const' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> List<String> LIST_LINKED = new LinkedList<>();


        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_PARAM_1 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(<fold text='✅' expand='false'>true</fold>);
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_PARAM_2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(<fold text='❌' expand='false'>false</fold>, LIST_SINGLE<fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>));

        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() <fold text='{...}' expand='true'>{
            int i = 1;
        }</fold>;
        <fold text='const' expand='false'>public <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold></fold> ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() <fold text='{...}' expand='true'>{
            public <fold text='💀' expand='false'>void</fold> setOk(boolean ok) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'>static</fold> ConstClass SELF_NULL = <fold text='🕳️' expand='false'>null</fold>;
        <fold text='🛡️' expand='false'>protected</fold> <fold text='⚡' expand='false'>static</fold> ConstClass EMPTY;
    }</fold>
    <fold text='⚡' expand='false'>static</fold> class Fields <fold text='{...}' expand='true'>{
        final ConstClass SELF =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold><fold text='' expand='true'>()</fold>;
        ConstClass SELF_ANN =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> <fold text='{}' expand='true'></fold>ConstClass<fold text='' expand='true'>()</fold> <fold text='{...}' expand='true'>{
        }</fold></fold>;
        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_SUB = new SubConstClass();
        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_SUB_ANN = new SubConstClass() <fold text='{...}' expand='true'>{
        }</fold>;

        private final HashMap<String, String> MAP =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<></fold><fold text='' expand='true'>()</fold>;
        private final HashMap<String, String> MAP2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<fold text='<~>' expand='false'><String, String></fold><fold text='' expand='true'></fold>()</fold>;
        private <fold text='🔒' expand='false'>final</fold> Map<String, String> MAP3 = new HashMap<>();

        private <fold text='🔒' expand='false'>final</fold> List<String> LIST = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
        private <fold text='🔒' expand='false'>final</fold> List<String> LIST2 = List.of("1");

        public final ConstClass SELF_PARAM_1 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(<fold text='✅' expand='false'>true</fold>);
        public final ConstClass SELF_PARAM_2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(<fold text='❌' expand='false'>false</fold>, LIST2<fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>));

        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() <fold text='{...}' expand='true'>{
            int i = 1;
        }</fold>;
        public <fold text='🔒' expand='false'>final</fold> ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() <fold text='{...}' expand='true'>{
            public <fold text='💀' expand='false'>void</fold> setOk(boolean ok) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        <fold text='🛡️' expand='false'>protected</fold> ConstClass SELF_NULL = <fold text='🕳️' expand='false'>null</fold>;
        <fold text='🛡️' expand='false'>protected</fold> ConstClass EMPTY;
    }</fold>



    <fold text='@NoArgsConstructor @AllArgsConstructor s' expand='false'><fold text='⚡' expand='false'>s</fold>tatic</fold> class ConstClass <fold text='{...}' expand='true'>{
        boolean ok;
        <fold text='@ToString S' expand='false'>S</fold>tring string;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public ConstClass() <fold text='{}' expand='true'>{
        }</fold></fold>

        public ConstClass(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold><fold text='📍' expand='false'></fold>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public ConstClass(boolean ok, String string) <fold text='{...}' expand='true'>{
            this.ok = <fold text='<<' expand='false'>ok</fold>;
            this.string = <fold text='<<' expand='false'>string</fold>;
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold>
        public String toString() <fold text='{...}' expand='true'>{
            return new StringJoiner(", ",<fold text=' "${' expand='false'> </fold>ConstClass.class.<fold text='simpleName' expand='false'>getSimpleName()</fold><fold text='}' expand='false'> + "</fold>[", "]")
                    .add("string='<fold text='$' expand='false'>" + </fold>string<fold text='' expand='false'> + "</fold>'")
                    .toString();
        }</fold></fold>
    }</fold>


    <fold text='⚡' expand='false'>static</fold> class SubConstClass extends ConstClass <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='⚡' expand='false'>static</fold> <fold text='🔒' expand='false'>final</fold> class SubConstClass2 extends ConstClass <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='@Getter @Setter c' expand='false'>c</fold>lass NamelessProperty <fold text='{...}' expand='true'>{
        private NamelessProperty prop;

        public NamelessProperty get()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>prop<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public data.ExperimentalTestData.NamelessProperty getProp()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return<fold text='' expand='true'></fold> </fold>prop<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public void setProp(NamelessProperty prop)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.prop = <fold text='<<' expand='false'>prop</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public void set(NamelessProperty s)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
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
            return namelessProperty.get();
        }</fold>
    }</fold>


}
