<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>java.util.Objects;
<fold text='🚢' expand='false'>import</fold> java.util.Optional;
<fold text='🚢' expand='false'>import</fold> java.util.function.Function;
<fold text='🚢' expand='false'>import</fold> java.util.stream.Stream;</fold>

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> OptionalTestData {

    public <fold text='💀' expand='false'>void</fold> enter(Optional<Data> opt, Data data, Data dataNull) <fold text='{...}' expand='true'>{
        <fold text='var' expand='false'>Object</fold> o = <fold text='🕳️' expand='false'>null</fold>;
        if <fold text='' expand='false'>(</fold>opt.<fold text='present' expand='false'>isPresent()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            o = opt<fold text='!!' expand='false'>.get()</fold>;
        }</fold>
        o = opt<fold text='!!' expand='false'>.orElseThrow()</fold>;

        o = <fold text='' expand='false'>Optional.ofNullable(</fold>dataNull<fold text='' expand='false'>)</fold>;
        o = <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold>;

        o = <fold text='' expand='false'>Optional.ofNullable(</fold>dataNull<fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElseGet(</fold><fold text='📍' expand='false'>this</fold>::orElseGetReturn<fold text='' expand='false'>)</fold>;
        o = <fold text='' expand='false'>Optional.ofNullable(</fold>dataNull<fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElseGet(</fold>() -> data.<fold text='data' expand='false'>getData()</fold>.<fold text='data' expand='false'>getData()</fold><fold text='' expand='false'>)</fold>;

        o = <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold><fold text='.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold>;
        o = <fold text='' expand='false'>Optional.ofNullable(</fold>dataNull<fold text='' expand='false'>)</fold>.map(OptionalTestData::getOutsideData)<fold text='!!' expand='false'>.get()</fold>;

        o = opt.map(Data::new).filter(Data.<fold text='🏛️' expand='false'>class</fold>::isInstance).map(Data.<fold text='🏛️' expand='false'>class</fold>::cast);

        o = <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold><fold text='.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='?.' expand='false'>
                .map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>
                .filter(it -> it.ok)
                .map(Function.identity())<fold text='?.' expand='false'>
                .map(</fold><fold text='ok' expand='false'>Data::isOk</fold><fold text='' expand='false'>)</fold>
                .map(it -> !it).map(it -> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>var</fold> s = it.toString();
        <fold text='🔙' expand='false'>return</fold> s<fold text=' ≡ ' expand='false'>.equals(</fold>"false"<fold text='' expand='false'>)</fold>;
                }</fold>)<fold text=' ?: ' expand='false'>.orElse(</fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold>;

        o = <fold text='' expand='false'>Optional.of(</fold>data.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>.map(OptionalTestData::getOutsideData)<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>data.<fold text='string' expand='false'>getString()</fold><fold text='' expand='false'>)</fold>;

        <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold>.flatMap(<fold text='📍' expand='false'>this</fold>::ofNullable).map(data::getDataMethod)<fold text=' ?: ' expand='false'>.orElseGet(</fold>() -> getOutsideData(data)<fold text='' expand='false'>)</fold>;

        o = Optional.<fold text='<~>' expand='false'><Data></fold>empty()<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.stream()<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'>.filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold>.findAny()<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text='!!' expand='false'>.get()</fold>;

        o = opt<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='false'>)</fold>;

        o = Optional.<fold text='<~>' expand='false'><Data></fold>empty()<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.stream()<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'> .filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold>.findAny()<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text='!!' expand='false'>.get()</fold>;

        Stream.of(data)<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'>.filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold>;
        Stream.of(data)<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'> .filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.findFirst()<fold text='!!' expand='false'>.orElseThrow()</fold>;
    }</fold>

    <fold text='🚫' expand='false'>private</fold> Data orElseGetReturn()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='false'></fold>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='🚫' expand='false'>private</fold> Optional<Data> ofNullable(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>Optional.empty()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='🚫' expand='false'>private</fold> <fold text='⚡' expand='false'>static</fold> Data getOutsideData(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold><fold text='🕳️' expand='false'>null</fold><fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='@Getter @Setter s' expand='false'><fold text='⚡' expand='false'>s</fold>tatic</fold> <fold text='🏛️' expand='false'>class</fold> Data <fold text='{...}' expand='true'>{
        <fold text='@Constructor D' expand='false'>D</fold>ata data;
        <fold text='🔘' expand='false'>boolean</fold> ok;

        String string;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public Data(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public Data getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold><fold text='' expand='false'></fold>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='🔘' expand='false'>boolean</fold> isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setData(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setOk(<fold text='🔘' expand='false'>boolean</fold> ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>string<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public <fold text='💀' expand='false'>void</fold> setString(String string)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.string = <fold text='<<' expand='false'>string</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public Data getDataMethod(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='false'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
