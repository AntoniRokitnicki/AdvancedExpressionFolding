package data;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ALL")
<fold text='@Getter p' expand='false'>p</fold>ublic class NullableAnnotationCheckNotNullTestData {

    private String saaa;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public String getSaaa()<fold text=' { ' expand='false'> {
        </fold>return saaa;<fold text=' }' expand='false'>
    }</fold></fold>

    class PreconditionsCheck <fold text='{...}' expand='true'>{
        public void main(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='true'>Preconditions.checkNotNull(args);</fold><fold text='' expand='true'>
            <fold text='' expand='true'></fold>Preconditions.checkNotNull(l);</fold>
            Preconditions.checkNotNull(z.getSaaa());
            Preconditions.checkNotNull(o);
            System.out.println();
        }</fold>

        public void mainMsgs(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='true'>Preconditions.checkNotNull(args, "args are null");</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>Preconditions.checkNotNull(l, "l is null");</fold>
            Preconditions.checkNotNull(z.getSaaa(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
            System.out.println();
        }</fold>

        public void mainConflictAnnotations(@Nullable String<fold text='!!! ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, @Nullable Long<fold text='!!! ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>NullableAnnotationCheckNotNullTestData<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='true'>Preconditions.checkNotNull(args);</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>Preconditions.checkNotNull(l);</fold>
            Preconditions.checkNotNull(z.getSaaa());
            Preconditions.checkNotNull(o);
            System.out.println();
        }</fold>

        public void mainConflictAnnotationsWithMsg(@Nullable String<fold text='!!! ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, @Nullable Long<fold text='!!! ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>NullableAnnotationCheckNotNullTestData<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='true'>Preconditions.checkNotNull(args, "args are null");</fold><fold text='' expand='true'>
            </fold><fold text='' expand='true'>Preconditions.checkNotNull(l, "l is null");</fold>
            Preconditions.checkNotNull(z.getSaaa(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
            System.out.println();
        }</fold>
    }</fold>

    class PreconditionsCheckReturn <fold text='{...}' expand='true'>{
        private String args;
        private Object o;
        private Long l;
        private String saaa;

        public void main(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;
            this.saaa = Preconditions.checkNotNull(z.getSaaa());
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public void mainMsgs(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{
            this.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;
            this.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;
            this.saaa = Preconditions.checkNotNull(z.getSaaa(), "saaa is null");
            this.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            printStatus();
        }</fold>

        public void mainNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>NullableAnnotationCheckNotNullTestData<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.saaa = Preconditions.checkNotNull(z.getSaaa());
            this.o = Preconditions.checkNotNull(o);
            printStatus();
        }</fold>

        public void mainMsgsNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>NullableAnnotationCheckNotNullTestData<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.saaa = Preconditions.checkNotNull(z.getSaaa(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
            printStatus();
        }</fold>

        private void printStatus() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>


    class Preconditions <fold text='{...}' expand='true'>{
        public static <T> T checkNotNull(T o, String s)<fold text=' { ' expand='false'> {
            </fold>return (T) o;<fold text=' }' expand='false'>
        }</fold>
        public static <T> T checkNotNull(T o)<fold text=' { ' expand='false'> {
            </fold>return (T) o;<fold text=' }' expand='false'>
        }</fold>
    }</fold>
}
