package data;

import <fold text='...' expand='false'>java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;</fold>

public class ExperimentalTestData {

    static class Const <fold text='{...}' expand='true'>{
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold><fold text='' expand='true'>()</fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_ANN =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='{}' expand='true'>ConstClass<fold text='' expand='true'>()</fold> <fold text='{...}' expand='true'>{
        }</fold></fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUB = new SubConstClass();
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUB_ANN = new SubConstClass() <fold text='{...}' expand='true'>{
        }</fold>;

        <fold text='const' expand='false'>private static final</fold> List<String> LIST = new ArrayList<>();

        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_PARAM_1 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(true);
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_PARAM_2 =<fold text=' ::' expand='true'> </fold>new<fold text='' expand='true'> </fold><fold text='' expand='true'>ConstClass</fold>(false, LIST<fold text='.getFirst' expand='false'>.get</fold>(<fold text='' expand='false'>0</fold>));

        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() <fold text='{...}' expand='true'>{
            int i = 1;
        }</fold>;
        <fold text='const' expand='false'>public static final</fold> ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() <fold text='{...}' expand='true'>{
            public void setOk(boolean ok) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>;

    }</fold>

    void main()<fold text='{...}' expand='true'>{
        var s = Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>;
        System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.isOk());
        System.out.println(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.<fold text='🧍' expand='true'>INSTANCE</fold>.main(Singleton.getInstance())));
    }</fold>
    <fold text='@Getter s' expand='false'>s</fold>tatic class Singleton <fold text='{...}' expand='true'>{
        static Singleton INSTANCE = new Singleton();
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
}