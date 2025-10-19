package data;

import java.util.*;

public class ConstructorReferenceNotationWithConstTestData {

    static class Const <fold text='{...}' expand='true'>{
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold><fold text='' expand='true'>()</fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_ANN =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='{}' expand='true'>ConstClass<fold text='' expand='true'>()</fold> <fold text='{...}' expand='true'>{
        }</fold></fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUB = new SubConstClass();
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUB_ANN = new SubConstClass() <fold text='{...}' expand='true'>{
        }</fold>;

        private <fold text='const' expand='false'>static final</fold> HashMap<String, String> MAP =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<></fold><fold text='' expand='true'>()</fold>;
        private <fold text='const' expand='false'>static final</fold> HashMap<String, String> MAP2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>HashMap<fold text='<~>' expand='false'><String, String></fold></fold><fold text='' expand='true'>()</fold>;
        private <fold text='const' expand='false'>static final</fold> Map<String, String> MAP3 = new HashMap<>();
        private <fold text='const' expand='false'>static final</fold> Map<String, String> MAP_TREE = new TreeMap<>();
        private <fold text='const' expand='false'>static final</fold> Map<String, String> MAP4 = Map.of();

        private <fold text='const' expand='false'>static final</fold> List<String> LIST = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
        private <fold text='const' expand='false'>static final</fold> List<String> LIST2 = List.of();
        private <fold text='const' expand='false'>static final</fold> List<String> LIST_SINGLE = List.of("1");
        private <fold text='const' expand='false'>static final</fold> List<String> LIST_LINKED = new LinkedList<>();


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

    <fold text='@NoArgsConstructor @AllArgsConstructor s' expand='false'>s</fold>tatic class ConstClass <fold text='{...}' expand='true'>{
        <fold text='@Constructor b' expand='false'>b</fold>oolean ok;
        <fold text='@ToString S' expand='false'>S</fold>tring string;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public ConstClass() <fold text='{}' expand='true'>{
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public ConstClass(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.ok = <fold text='<<' expand='true'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public ConstClass(boolean ok, String string) <fold text='{...}' expand='true'>{
            this.ok = <fold text='<<' expand='true'>ok</fold>;
            this.string = <fold text='<<' expand='true'>string</fold>;
        }</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='true'><fold text='' expand='false'>@Override</fold><fold text='' expand='true'>
        </fold>public String toString() <fold text='{...}' expand='true'>{
            return new StringJoiner(", ",<fold text=' "${' expand='false'> </fold>ConstClass.class.<fold text='simpleName' expand='false'>getSimpleName()</fold><fold text='}' expand='false'> + "</fold>[", "]")
                    .add("string='<fold text='$' expand='false'>" + </fold>string<fold text='' expand='false'> + "</fold>'")
                    .toString();
        }</fold></fold>
    }</fold>

    static class SubConstClass extends ConstClas<fold text='s(nothing overridden)' expand='true'>s</fold> <fold text='{...}' expand='true'>{
    }</fold>

    static <fold text='' expand='false'>final</fold> class SubConstClass2 extends ConstClas<fold text='s(nothing overridden)' expand='true'>s</fold> <fold text='{...}' expand='true'>{
    }</fold>
}
