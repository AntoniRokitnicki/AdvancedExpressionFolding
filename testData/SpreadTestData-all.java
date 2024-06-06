<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>java.math.BigDecimal;
<fold text='🚢' expand='false'>import</fold> java.util.Comparator;
<fold text='🚢' expand='false'>import</fold> java.util.List;
<fold text='🚢' expand='false'>import</fold> java.util.Objects;
<fold text='🚢' expand='false'>import</fold> java.util.Optional;
<fold text='🚢' expand='false'>import</fold> java.util.function.Function;
<fold text='🚢' expand='false'>import</fold> java.util.stream.Collectors;
<fold text='🚢' expand='false'>import</fold> java.util.stream.Stream;</fold>

@SuppressWarnings("ALL")
public class SpreadTestData {
    public <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> main(Stream<Data> stream, Data data, List<Data> list) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>String</fold> empNames = list<fold text='' expand='false'>.stream()</fold><fold text='*.' expand='false'>
                .map(</fold><fold text='string()' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold>
                .collect(Collectors.joining(", "));

        <fold text='val' expand='false'>var</fold> p1 = data.<fold text='dataList' expand='false'>getDataList()</fold>.stream()<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.toList();
        <fold text='val' expand='false'>var</fold> p2 = data.<fold text='dataList' expand='false'>getDataList()</fold>
                .stream()<fold text='*.' expand='false'>
                .map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>
                .toList()
                .stream()<fold text='*.' expand='false'>
                .map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>
                .toList();

        <fold text='val' expand='false'>var</fold> stream3 = Stream.of("123", "2313")<fold text='*.' expand='false'>.map(</fold><fold text='length()' expand='false'>String::length</fold><fold text='' expand='false'>)</fold>.toList();

        <fold text='val' expand='false'>var</fold> a = stream<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'>.filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>
                .map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='**.' expand='false'>.flatMap(</fold><fold text='dataStream()' expand='false'>Data::getDataStream</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>
                .map(</fold><fold text='dataList()' expand='false'>Data::getDataList</fold><fold text='' expand='false'>)</fold><fold text='**.' expand='false'>.flatMap(</fold><fold text='stream()' expand='false'>List::stream</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>
                .map(</fold><fold text='string()' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='chars()' expand='false'>String::chars</fold><fold text='' expand='false'>)</fold>
                .map(it -> it.count()<fold text=' + ' expand='false'>+</fold>1)
                .map(it -> <fold text='{...}' expand='true'>{
                    <fold text='val' expand='false'>var</fold> z = 2;
                    <fold text='val' expand='false'>var</fold> max = Stream.of(data)<fold text='*.' expand='false'>.map(</fold><fold text='string()' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold>.max(Comparator.comparing(String::length))<fold text='?.' expand='false'>.map(</fold><fold text='length' expand='false'>String::length</fold><fold text='' expand='false'>)</fold>.stream()<fold text='*.' expand='false'>.map(</fold><fold text='class()' expand='false'>Object::getClass</fold><fold text='' expand='false'>)</fold>.findAny()<fold text='?.' expand='false'>.map(</fold><fold text='hashCode' expand='false'>Object::hashCode</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>1<fold text='' expand='false'>)</fold>;
                    return new BigDecimal(it + z + max);
                }</fold>)
                .map(it -> Stream.iterate(it, BigDecimal::plus))
                .map(it -> stream)
                .flatMap(Function.identity())<fold text='*.' expand='false'>
                .map(</fold><fold text='ok()' expand='false'>Data::isOk</fold><fold text='' expand='false'>)</fold>
                .toList();

        <fold text='val' expand='false'>var</fold> p = methodStream(data).toList()<fold text='.' expand='false'>.stream().</fold>min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString))<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>"string1"<fold text='' expand='false'>)</fold>;


        <fold text='val' expand='false'>var</fold> o1 = stream.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);
    }</fold>

    static Stream<Data> methodStream(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>Stream.of(data)<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='@Getter @Setter s' expand='false'><fold text='⚡' expand='false'>s</fold>tatic</fold> class Data <fold text='{...}' expand='true'>{
        Data data;
        boolean ok;
        String string;

        public Data(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public Data getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>
        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>
        </fold><fold text='' expand='false'>public void setData(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>
        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;<fold text=' ' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold><fold text='' expand='false'></fold>
        </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>string<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>
        </fold><fold text='' expand='false'>public void setString(String string)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='📍' expand='false'>this</fold>.string = <fold text='<<' expand='false'>string</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>
        public Data getDataMethod(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text=' ' expand='true'>;<fold text='' expand='true'><fold text=' }' expand='false'></fold>
        </fold>}</fold>
        public Optional<Data> getDataOpt()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>Optional.ofNullable(</fold>data<fold text='' expand='false'>)</fold><fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public Stream<Data> getDataStream()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>Optional.ofNullable(</fold>data<fold text='' expand='false'>)</fold>.stream()<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
        public List<Data> getDataList()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold><fold text='' expand='false'>Optional.ofNullable(</fold>data<fold text='' expand='false'>)</fold>.stream().toList()<fold text=' ' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
