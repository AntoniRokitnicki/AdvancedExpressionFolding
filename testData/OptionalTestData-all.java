package data;

import <fold text='...' expand='false'>java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;</fold>

@SuppressWarnings("ALL")
public class OptionalTestData {

    public void enter(Optional<Data> opt, Data data, Data dataNull) <fold text='{...}' expand='true'>{
        <fold text='var' expand='false'>Object</fold> o = null;
        if <fold text='' expand='false'>(</fold>opt.<fold text='present' expand='false'>isPresent()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            o = opt<fold text='!!' expand='false'>.get()</fold>;
        }</fold>
        o = opt<fold text='!!' expand='false'>.orElseThrow()</fold>;

        o = <fold text='' expand='false'>Optional.ofNullable(</fold>dataNull<fold text='' expand='false'>)</fold>;
        o = <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold>;

        o = <fold text='' expand='false'>Optional.ofNullable(</fold>dataNull<fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElseGet(</fold>this::orElseGetReturn<fold text='' expand='false'>)</fold>;
        o = <fold text='' expand='false'>Optional.ofNullable(</fold>dataNull<fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElseGet(</fold>() -> data.<fold text='data' expand='false'>getData()</fold>.<fold text='data' expand='false'>getData()</fold><fold text='' expand='false'>)</fold>;

        o = <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold><fold text='.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>null<fold text='' expand='false'>)</fold>;
        o = <fold text='' expand='false'>Optional.ofNullable(</fold>dataNull<fold text='' expand='false'>)</fold>.map(OptionalTestData::getOutsideData)<fold text='!!' expand='false'>.get()</fold>;

        o = opt.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);

        o = <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold><fold text='.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='?.' expand='false'>
                .map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>
                .filter(it -> it.ok)
                .map(Function.identity())<fold text='?.' expand='false'>
                .map(</fold><fold text='ok' expand='false'>Data::isOk</fold><fold text='' expand='false'>)</fold>
                .map(it -> !it).map(it -> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>var</fold> s = it.toString();
        return s<fold text=' ≡ ' expand='false'>.equals(</fold>"false"<fold text='' expand='false'>)</fold>;
                }</fold>)<fold text=' ?: ' expand='false'>.orElse(</fold>null<fold text='' expand='false'>)</fold>;

        o = <fold text='' expand='false'>Optional.of(</fold>data.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold>.map(OptionalTestData::getOutsideData)<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>data.<fold text='string' expand='false'>getString()</fold><fold text='' expand='false'>)</fold>;

        <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold>.flatMap(this::ofNullable).map(data::getDataMethod)<fold text=' ?: ' expand='false'>.orElseGet(</fold>() -> getOutsideData(data)<fold text='' expand='false'>)</fold>;

        o = Optional.<fold text='<~>' expand='false'><Data></fold>empty()<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.stream()<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'>.filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold>.findAny()<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text='!!' expand='false'>.get()</fold>;

        o = opt<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>null<fold text='' expand='false'>)</fold>;

        o = Optional.<fold text='<~>' expand='false'><Data></fold>empty()<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.stream()<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'> .filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold>.findAny()<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text='!!' expand='false'>.get()</fold>;

        Stream.of(data)<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'>.filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold>;
        Stream.of(data)<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'> .filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.findFirst()<fold text='!!' expand='false'>.orElseThrow()</fold>;
    }</fold>

    private Data orElseGetReturn()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold><fold text='' expand='true'></fold>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    private Optional<Data> ofNullable(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>Optional.empty()<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    private static Data getOutsideData(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
        </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>null<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
    </fold>}</fold>

    <fold text='@Getter @Setter s' expand='false'>s</fold>tatic class Data <fold text='{...}' expand='true'>{
        <fold text='@Constructor D' expand='false'>D</fold>ata data;
        boolean ok;

        String string;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>public Data(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public Data getData()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public boolean isOk()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>ok<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setData(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.data = <fold text='<<' expand='false'>data</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.ok = <fold text='<<' expand='false'>ok</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'></fold>

        </fold><fold text='' expand='false'>public String getString()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>string<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public void setString(String string)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold>this.string = <fold text='<<' expand='false'>string</fold><fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        public Data getDataMethod(Data data)<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>data<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>
    }</fold>
}
