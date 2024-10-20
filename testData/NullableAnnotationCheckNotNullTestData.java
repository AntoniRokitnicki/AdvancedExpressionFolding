package data;

import <fold text='...' expand='false'>org.jetbrains.annotations.Nullable;

import java.util.HashMap;</fold>

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    class PreconditionsCheck <fold text='{...}' expand='true'>{
        public void main(String<fold text='!!! ' expand='false'> </fold>args, Object<fold text='!!! ' expand='false'> </fold>o, Long<fold text='!!! ' expand='false'> </fold>l, Preconditions z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            Preconditions.checkNotNull(z.getData());<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;</fold>
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }</fold>

        public void mainMsgs(String<fold text='!!! ' expand='false'> </fold>args, Object<fold text='!!! ' expand='false'> </fold>o, Long<fold text='!!! ' expand='false'> </fold>l, Preconditions z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            Preconditions.checkNotNull(z.getData(), "o is null");<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;</fold>
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }</fold>

        public void mainConflictAnnotations(@Nullable String<fold text='!!! ' expand='false'> </fold>args, @Nullable Object<fold text='!!! ' expand='false'> </fold>o, @Nullable Long<fold text='!!! ' expand='false'> </fold>l, @Nullable Preconditions z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            Preconditions.checkNotNull(z.getData());<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;</fold>
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }</fold>

        public void mainConflictAnnotationsWithMsg(@Nullable String<fold text='!!! ' expand='false'> </fold>args, @Nullable Object<fold text='!!! ' expand='false'> </fold>o, @Nullable Long<fold text='!!! ' expand='false'> </fold>l, @Nullable Preconditions z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            Preconditions.checkNotNull(z.getData(), "o is null");<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;</fold>
            new HashMap<String, String>().put("a", "b");
            System.out.println();
        }</fold>
    }</fold>

    class PreconditionsCheckReturn <fold text='{...}' expand='true'>{
        private String args;
        private Object o;
        private Long l;
        private Preconditions data;

        public void main1(String args, Object o, Long l, Preconditions z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }</fold>

        public void mainMsgs(String args, Object o, Long l, Preconditions z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }</fold>

        public void mainNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }</fold>

        public void mainMsgsNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }</fold>

        private void printStatus()<fold text=' { ' expand='false'> {
            </fold>new HashMap<String, String>().put("a", "b");<fold text=' }' expand='false'>
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
