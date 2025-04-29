<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>org.jetbrains.annotations.Nullable;

<fold text='🚢' expand='false'>import</fold> java.util.HashMap;</fold>

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> NullableAnnotationCheckNotNullTestData {

    <fold text='🏛️' expand='false'>class</fold> PreconditionsCheck <fold text='{...}' expand='true'>{
        public <fold text='💀' expand='false'>void</fold> main(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, Preconditions z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;<fold text='' expand='true'></fold>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            System.out.println();
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainMsgs(String<fold text='!!! ' expand='false'> </fold>args, Object o, Long<fold text='!!! ' expand='false'> </fold>l, Preconditions z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>, "o is null")</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            System.out.println();
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainConflictAnnotations(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='!!! ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='!!! ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>)</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>)</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>)</fold>;
            System.out.println();
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainConflictAnnotationsWithMsg(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='!!! ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='!!! ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{<fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;</fold><fold text='' expand='true'>
            </fold><fold text='' expand='false'><fold text='' expand='true'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;</fold>
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>, "o is null")</fold>;
            <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            System.out.println();
        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> PreconditionsCheckReturn <fold text='{...}' expand='true'>{
        <fold text='🚫' expand='false'>private</fold> String args;
        <fold text='🚫' expand='false'>private</fold> Object o;
        <fold text='🚫' expand='false'>private</fold> Long l;
        <fold text='🚫' expand='false'>private</fold> Preconditions data;

        public <fold text='💀' expand='false'>void</fold> main1(String args, Object o, Long l, Preconditions z) <fold text='{...}' expand='true'>{
            this.args = Preconditions.checkNotNull(<fold text='<<' expand='false'>args</fold>);
            this.l = Preconditions.checkNotNull(<fold text='<<' expand='false'>l</fold>);
            <fold text='📍' expand='false'>this</fold>.data = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='<<' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>;
            this.o = Preconditions.checkNotNull(<fold text='<<' expand='false'>o</fold>);
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainMsgs(String args, Object o, Long l, Preconditions z) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;
            <fold text='📍' expand='false'>this</fold>.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;
            <fold text='📍' expand='false'>this</fold>.data = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>, "saaa is null")</fold>;
            <fold text='📍' expand='false'>this</fold>.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            this.args = Preconditions.checkNotNull(<fold text='<<' expand='false'>args</fold>);
            this.l = Preconditions.checkNotNull(<fold text='<<' expand='false'>l</fold>);
            <fold text='📍' expand='false'>this</fold>.data = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='<<' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>;
            this.o = Preconditions.checkNotNull(<fold text='<<' expand='false'>o</fold>);
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        public <fold text='💀' expand='false'>void</fold> mainMsgsNullable(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>args, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Object<fold text='? ' expand='false'> </fold>o, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Long<fold text='? ' expand='false'> </fold>l, <fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>Preconditions<fold text='? ' expand='false'> </fold>z) <fold text='{...}' expand='true'>{
            <fold text='📍' expand='false'>this</fold>.args = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>args<fold text='!!' expand='false'>, "args are null")</fold>;
            <fold text='📍' expand='false'>this</fold>.l = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>l<fold text='!!' expand='false'>, "l is null")</fold>;
            <fold text='📍' expand='false'>this</fold>.data = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>z.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>, "saaa is null")</fold>;
            <fold text='📍' expand='false'>this</fold>.o = <fold text='' expand='false'>Preconditions.checkNotNull(</fold>o<fold text='!!' expand='false'>, "o is null")</fold>;
            new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;
            printStatus();
        }</fold>

        <fold text='🚫' expand='false'>private</fold> <fold text='💀' expand='false'>void</fold> printStatus()<fold text=' { ' expand='false'> {
            </fold>new HashMap<String, String>()<fold text='[' expand='false'>.put(</fold>"a"<fold text='] = ' expand='false'>, </fold>"b"<fold text='' expand='false'>)</fold>;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> Preconditions <fold text='{...}' expand='true'>{
        public <fold text='⚡' expand='false'>static</fold> <T> T checkNotNull(T o, String s)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> o;<fold text=' }' expand='false'>
        }</fold>
        public <fold text='⚡' expand='false'>static</fold> <T> T checkNotNull(T o)<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> o;<fold text=' }' expand='false'>
        }</fold>
        public Preconditions getData()<fold text=' { ' expand='false'> {
            </fold><fold text='🔙' expand='false'>return</fold> <fold text='📍' expand='false'>this</fold>;<fold text=' }' expand='false'>
        }</fold>
    }</fold>

}
