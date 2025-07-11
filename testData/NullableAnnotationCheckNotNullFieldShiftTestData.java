package data;

import <fold text='...' expand='false'>org.jetbrains.annotations.Nullable;

import java.util.HashMap;</fold>

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullFieldShiftTestData {

    class PreconditionsCheckReturn <fold text='{...}' expand='true'>{
        private String args;
        private Object o;
        private Long l;
        private Preconditions data;

        public void main1(String args, Object o, Long l, Preconditions z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>args</fold><fold text='!!' expand='false'>)</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>l</fold><fold text='!!' expand='false'>)</fold>;
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>o</fold><fold text='!!' expand='false'>)</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public void mainMsgs(String args, Object o, Long l, Preconditions z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(<fold text='<<' expand='false'></fold>args</fold><fold text='!!' expand='false'>, "args are null")</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>l</fold><fold text='!!' expand='false'>, "l is null")</fold>;
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>o</fold><fold text='!!' expand='false'>, "o is null")</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public void mainNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>args</fold><fold text='!!' expand='false'>)</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>l</fold><fold text='!!' expand='false'>)</fold>;
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>o</fold><fold text='!!' expand='false'>)</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        private void printStatus()<fold text=' { ' expand='false'> {
            </fold>new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    class Preconditions <fold text='{...}' expand='true'>{
        public static <T> T checkNotNull(T o, String s)<fold text=' { ' expand='false'> {
            </fold>return o;<fold text=' }' expand='false'>
        }</fold>
        public static <T> T checkNotNull(T o)<fold text=' { ' expand='false'> {
            </fold>return o;<fold text=' }' expand='false'>
        }</fold>
        public Preconditions getData()<fold text=' { ' expand='false'> {
            </fold>return this;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

}
