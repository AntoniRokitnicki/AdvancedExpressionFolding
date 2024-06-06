<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> org.jetbrains.annotations.Nullable;

@SuppressWarnings("ALL")
<fold text='@Getter p' expand='false'>p</fold>ublic class NullableAnnotationCheckNotNullTestData {

    private String saaa;<fold text='' expand='false'>

    </fold><fold text='' expand='false'>public String getSaaa()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>saaa<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold></fold>

    class PreconditionsCheck <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> main(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            System.out.println();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainMsgs(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>, "o is null")</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            System.out.println();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainConflictAnnotations(@Nullable String<fold text='!!! ' expand='false'> </fold>args, @Nullable Object o, @Nullable Long<fold text='!!! ' expand='false'> </fold>l, @Nullable NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            System.out.println();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainConflictAnnotationsWithMsg(@Nullable String<fold text='!!! ' expand='false'> </fold>args, @Nullable Object o, @Nullable Long<fold text='!!! ' expand='false'> </fold>l, @Nullable NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;<fold text='' expand='true'></fold>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>, "o is null")</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            System.out.println();
        }</fold>
    }</fold>

    class PreconditionsCheckReturn <fold text='{...}' expand='true'>{
        private String args;
        private Object o;
        private Long l;
        private String saaa;

        public <fold text='💀' expand='false'>void</fold> main(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;
            <fold text='📍' expand='false'>this</fold>.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;
            <fold text='📍' expand='false'>this</fold>.saaa = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='📍' expand='false'>this</fold>.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainMsgs(String args, Object o, Long l, NullableAnnotationCheckNotNullTestData z) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;
            <fold text='📍' expand='false'>this</fold>.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;
            <fold text='📍' expand='false'>this</fold>.saaa = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>, "saaa is null")</fold>;
            <fold text='📍' expand='false'>this</fold>.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            printStatus();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>NullableAnnotationCheckNotNullTestData<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;
            <fold text='📍' expand='false'>this</fold>.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;
            <fold text='📍' expand='false'>this</fold>.saaa = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='📍' expand='false'>this</fold>.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainMsgsNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>NullableAnnotationCheckNotNullTestData<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;
            <fold text='📍' expand='false'>this</fold>.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;
            <fold text='📍' expand='false'>this</fold>.saaa = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='saaa' expand='false'>getSaaa()</fold><fold text='!!' expand='false'>, "saaa is null")</fold>;
            <fold text='📍' expand='false'>this</fold>.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            printStatus();
        }</fold>

        private <fold text='💀' expand='false'>void</fold> printStatus() <fold text='{}' expand='true'>{
        }</fold>
    }</fold>


    class Preconditions <fold text='{...}' expand='true'>{
        public static <T> T checkNotNull(T o, String s)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>(T) </fold>o<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public static <T> T checkNotNull(T o)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>(T) </fold>o<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
