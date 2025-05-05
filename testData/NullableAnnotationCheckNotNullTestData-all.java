package data;

import <fold text='...' expand='false'>org.jetbrains.annotations.Nullable;

import java.util.HashMap;</fold>

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    class PreconditionsCheck <fold text='{...}' expand='true'>{
        public void main(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, Preconditions z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            System.out.println();
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
        }</fold>

        public void mainMsgs(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, Preconditions z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;<fold text='' expand='true'></fold>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>, "o is null")</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            System.out.println();
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
        }</fold>

        public void mainConflictAnnotations(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='!!! ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='!!! ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            <fold text='' expand='false'><fold text='' expand='true'></fold>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            System.out.println();
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
        }</fold>

        public void mainConflictAnnotationsWithMsg(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='!!! ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='!!! ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>, "o is null")</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            System.out.println();
        }</fold>
    }</fold>

    class PreconditionsCheckReturn <fold text='{...}' expand='true'>{
        private String args;
        private Object o;
        private Long l;
        private Preconditions data;

        public void main1(String args, Object o, Long l, Preconditions z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>args</fold><fold text='!!' expand='false'>)</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>l</fold><fold text='!!' expand='false'>)</fold>;
            this.data = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='<<' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>;
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>o</fold><fold text='!!' expand='false'>)</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public void mainMsgs(String args, Object o, Long l, Preconditions z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;
            this.data = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>, "saaa is null")</fold>;
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public void mainNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>args</fold><fold text='!!' expand='false'>)</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>l</fold><fold text='!!' expand='false'>)</fold>;
            this.data = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='<<' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>;
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold><fold text='<<' expand='false'>o</fold><fold text='!!' expand='false'>)</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public void mainMsgsNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;
            this.data = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>, "saaa is null")</fold>;
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        private void printStatus()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

    class Preconditions <fold text='{...}' expand='true'>{
        public static <T> T checkNotNull(T o, String s)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>o<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public static <T> T checkNotNull(T o)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>o<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public Preconditions getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>

}
