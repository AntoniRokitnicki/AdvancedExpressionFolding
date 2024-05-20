package data;


import java.util.*;

<fold text='/** {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()} ...*/' expand='true'>/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.extension.ExperimentalExt#singletonField(com.intellij.psi.PsiReferenceExpression)}
 * {@link com.intellij.advancedExpressionFolding.extension.NullableExt#foldFieldConstructor(com.intellij.psi.PsiField, com.intellij.openapi.editor.Document)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */</fold>
public class ExperimentalTestData {

    static class Const <fold text='{...}' expand='true'>{
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold><fold text='' expand='true'>()</fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_ANN =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='{}' expand='true'>ConstClass<fold text='' expand='true'>()</fold> <fold text='{...}' expand='true'>{
        }</fold></fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUB = new SubConstClass();
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUB_ANN = new SubConstClass() <fold text='{...}' expand='true'>{
        }</fold>;

        <fold text='const' expand='false'>private static final</fold> HashMap<String, String> MAP =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<></fold><fold text='' expand='true'>()</fold>;
        <fold text='const' expand='false'>private static final</fold> HashMap<String, String> MAP2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<fold text='<~>' expand='false'><String, String></fold><fold text='' expand='true'></fold>()</fold>;
        <fold text='const' expand='false'>private static final</fold> Map<String, String> MAP3 = new HashMap<>();
        <fold text='const' expand='false'>private static final</fold> Map<String, String> MAP_TREE = new TreeMap<>();
        <fold text='const' expand='false'>private static final</fold> Map<String, String> MAP4 = Map.of();

        <fold text='const' expand='false'>private static final</fold> List<String> LIST = new ArrayList<>();
        <fold text='const' expand='false'>private static final</fold> List<String> LIST2 = List.of();
        <fold text='const' expand='false'>private static final</fold> List<String> LIST_SINGLE = List.of("1");
        <fold text='const' expand='false'>private static final</fold> List<String> LIST_LINKED = new LinkedList<>();


        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_PARAM_1 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(true);
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_PARAM_2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(false, LIST_SINGLE<fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>));

        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() <fold text='{...}' expand='true'>{
            int i = 1;
        }</fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() <fold text='{...}' expand='true'>{
            public void setOk(boolean ok) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        protected static ConstClass SELF_NULL = null;
        protected static ConstClass EMPTY;
    }</fold>
    static class Fields <fold text='{...}' expand='true'>{
        final ConstClass SELF =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold><fold text='' expand='true'>()</fold>;
        ConstClass SELF_ANN =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='{}' expand='true'>ConstClass<fold text='' expand='true'>()</fold> <fold text='{...}' expand='true'>{
        }</fold></fold>;
        public final ConstClass SELF_SUB = new SubConstClass();
        public final ConstClass SELF_SUB_ANN = new SubConstClass() <fold text='{...}' expand='true'>{
        }</fold>;

        private final HashMap<String, String> MAP =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<></fold><fold text='' expand='true'>()</fold>;
        private final HashMap<String, String> MAP2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<fold text='<~>' expand='false'><String, String></fold></fold><fold text='' expand='true'>()</fold>;
        private final Map<String, String> MAP3 = new HashMap<>();

        private final List<String> LIST = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
        private final List<String> LIST2 = List.of("1");

        public final ConstClass SELF_PARAM_1 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(true);
        public final ConstClass SELF_PARAM_2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(false, LIST2<fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>));

        public final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() <fold text='{...}' expand='true'>{
            int i = 1;
        }</fold>;
        public final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() <fold text='{...}' expand='true'>{
            public void setOk(boolean ok) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

        protected ConstClass SELF_NULL = null;
        protected ConstClass EMPTY;
    }</fold>

    void main() <fold text='{...}' expand='true'>{
        var s = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>;
        System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.isOk());
        System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.getInstance())));

        var s2 = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.LOCAL;
        System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.LOCAL.isOk());
        System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.LOCAL.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.LOCAL.main(Singleton.getInstance())));
    }</fold>

    static class Singleton <fold text='{...}' expand='true'>{
        static Singleton INSTANCE =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>Singleton</fold><fold text='' expand='true'>()</fold>;
        Singleton LOCAL =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>Singleton</fold><fold text='' expand='true'>()</fold>;
        <fold text='@Getter b' expand='false'>b</fold>oolean ok;

        Singleton main(Singleton s)<fold text=' { ' expand='false'> {
            </fold>return this;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold>

        public static Singleton getInstance()<fold text=' { ' expand='false'> {
            </fold>return INSTANCE;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    <fold text='@ToStringˣ s' expand='false'>s</fold>tatic class ConstClass <fold text='{...}' expand='true'>{
        boolean ok;
        String string;

        public ConstClass() <fold text='{}' expand='true'>{
        }</fold>

        public ConstClass(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = ok;<fold text=' }' expand='false'>
        }</fold>

        public ConstClass(boolean ok, String string) <fold text='{...}' expand='true'>{
            this.ok = ok;
            this.string = string;
        }</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public String toString() <fold text='{...}' expand='true'>{
            return new StringJoiner(", ", ConstClass.class.getSimpleName() + "[", "]")
                    .add("string='" + string + "'")
                    .toString();
        }</fold></fold>
    }</fold>


    static class SubConstClass extends ConstClass <fold text='{...}' expand='true'>{
    }</fold>

    static final class SubConstClass2 extends ConstClass <fold text='{...}' expand='true'>{
    }</fold>

    <fold text='@Getter @Setter c' expand='false'>c</fold>lass NamelessProperty <fold text='{...}' expand='true'>{
        private NamelessProperty prop;

        public NamelessProperty get()<fold text=' { ' expand='false'> {
            </fold>return prop;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public data.ExperimentalTestData.NamelessProperty getProp()<fold text=' { ' expand='false'> {
            </fold>return prop;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setProp(NamelessProperty prop)<fold text=' { ' expand='false'> {
            </fold>this.prop = prop;<fold text=' }' expand='false'>
        }</fold></fold>

        public void set(NamelessProperty s)<fold text=' { ' expand='false'> {
            </fold>this.prop = s;<fold text=' }' expand='false'>
        }</fold>

        NamelessProperty main(NamelessProperty s, NamelessProperty namelessProperty) <fold text='{...}' expand='true'>{
            s.set(namelessProperty.getProp().get());
            s.set(namelessProperty.getProp());
            s.setProp(namelessProperty.getProp().get());
            s.setProp(namelessProperty.getProp());


            s.set(namelessProperty.get());
            s.set(namelessProperty.get().get()); //TODO:

            System.out.println(s.get());
            s.get();
            s.set(namelessProperty);
            s.set(s.get());
            s.set(namelessProperty.get().get().get());
            s.set(main(s.get(), namelessProperty.get()));
            return namelessProperty.get();
        }</fold>
    }</fold>


}
