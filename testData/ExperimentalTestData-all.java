package data;


import <fold text='...' expand='false'>com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;

import java.util.*;</fold>

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
        <fold text='const' expand='false'>private static final</fold> HashMap<String, String> MAP2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<fold text='<~>' expand='false'><String, String></fold></fold><fold text='' expand='true'>()</fold>;
        <fold text='const' expand='false'>private static final</fold> Map<String, String> MAP3 = new HashMap<>();
        <fold text='const' expand='false'>private static final</fold> Map<String, String> MAP_TREE = new TreeMap<>();
        <fold text='const' expand='false'>private static final</fold> Map<String, String> MAP4 = Maps.newHashMap();

        <fold text='const' expand='false'>private static final</fold> List<String> LIST = new ArrayList<>();
        <fold text='const' expand='false'>private static final</fold> List<String> LIST2 = Lists.newArrayList();
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
        private final HashMap<String, String> MAP2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<fold text='<~>' expand='false'><String, String></fold><fold text='' expand='true'></fold>()</fold>;
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
        <fold text='val' expand='false'>var</fold> s = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>;
        <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.<fold text='ok' expand='false'>isOk()</fold>);
        <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='instance' expand='false'>getInstance()</fold>)));

        <fold text='val' expand='false'>var</fold> s2 = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.LOCAL;
        <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.LOCAL.<fold text='ok' expand='false'>isOk()</fold>);
        <fold text='' expand='false'>System.out.</fold>println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.LOCAL.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.LOCAL.main(Singleton.<fold text='instance' expand='false'>getInstance()</fold>)));
    }</fold>

    <fold text='@Getterˣ s' expand='false'>s</fold>tatic class Singleton <fold text='{...}' expand='true'>{
        static Singleton INSTANCE =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>Singleton</fold><fold text='' expand='true'>()</fold>;
        Singleton LOCAL =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>Singleton</fold><fold text='' expand='true'>()</fold>;
        boolean ok;

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
            </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
        }</fold>

        public ConstClass(boolean ok, String string) <fold text='{...}' expand='true'>{
            this.ok = <fold text='<<' expand='false'>ok</fold>;
            this.string = <fold text='<<' expand='false'>string</fold>;
        }</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>@Override
        public String toString() <fold text='{...}' expand='true'>{
            return new StringJoiner(", ",<fold text=' "${' expand='false'> </fold>ConstClass.class.<fold text='simpleName' expand='false'>getSimpleName()</fold><fold text='}' expand='false'> + "</fold>[", "]")
                    .add("string='<fold text='$' expand='false'>" + </fold>string<fold text='' expand='false'> + "</fold>'")
                    .toString();
        }</fold></fold>
    }</fold>


    static class SubConstClass extends ConstClass <fold text='{...}' expand='true'>{
    }</fold>

    static <fold text='' expand='false'>final</fold> class SubConstClass2 extends ConstClass <fold text='{...}' expand='true'>{
    }</fold>

    class SimpleGetSet<fold text='{...}' expand='true'>{
        private String s;

        public String get()<fold text=' { ' expand='false'> {
            </fold>return s;<fold text=' }' expand='false'>
        }</fold>

        public void set(String s)<fold text=' { ' expand='false'> {
            </fold>this.s = <fold text='<<' expand='false'>s</fold>;<fold text=' }' expand='false'>
        }</fold>

        void main(SimpleGetSet s) <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>System.out.</fold>println(s.get());
            s.get();
            s.set("1");
            s.set(s.get());
        }</fold>
    }</fold>

}
