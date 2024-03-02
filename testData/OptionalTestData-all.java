package data;

import <fold text='...' expand='false'>org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;</fold>

@SuppressWarnings("ALL")
public class OptionalTestData {

    public void enter(Optional<Data> opt, @NotNull Data data, @Nullable Data dataNull) <fold text='{...}' expand='true'>{
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
        o = <fold text='' expand='false'>Optional.ofNullable(</fold>dataNull<fold text='' expand='false'>)</fold><fold text='?.' expand='false'>.map(</fold>OptionalTestData::getOutsideData<fold text='' expand='false'>)</fold><fold text='!!' expand='false'>.get()</fold>;

        o = <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold><fold text='.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='?.' expand='false'>
                .map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>
                .filter(it -> it.ok)
                .map(Function.identity())<fold text='?.' expand='false'>
                .map(</fold><fold text='ok' expand='false'>Data::isOk</fold><fold text='' expand='false'>)</fold>
                .map(it -> !it).map(it -> <fold text='{...}' expand='true'>{
                    <fold text='val' expand='false'>var</fold> s = it.toString();
                    return s<fold text=' ≡ ' expand='false'>.equals(</fold>"false"<fold text='' expand='false'>)</fold>;
                }</fold>)<fold text=' ?: ' expand='false'>.orElse(</fold>null<fold text='' expand='false'>)</fold>;

        o = <fold text='' expand='false'>Optional.of(</fold>data.<fold text='data' expand='false'>getData()</fold><fold text='!!' expand='false'>)</fold><fold text='.' expand='false'>.map(</fold>OptionalTestData::getOutsideData<fold text='' expand='false'>)</fold><fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>data.<fold text='string' expand='false'>getString()</fold><fold text='' expand='false'>)</fold>;

        <fold text='' expand='false'>Optional.of(</fold>data<fold text='!!' expand='false'>)</fold><fold text='.*' expand='false'>.flatMap(</fold>this::ofNullable<fold text='' expand='false'>)</fold><fold text='?.' expand='false'>.map(</fold>data::getDataMethod<fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElseGet(</fold>() -> getOutsideData(data)<fold text='' expand='false'>)</fold>;

        o = Optional.<fold text='<~>' expand='false'><Data></fold>empty()<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.stream()<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'>.filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold>.findAny()<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text='!!' expand='false'>.get()</fold>;

        o = opt<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>null<fold text='' expand='false'>)</fold>;

        o = Optional.<fold text='<~>' expand='false'><Data></fold>empty()<fold text='?.' expand='false'>.map(</fold><fold text='data' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.stream()<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'> .filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold>.findAny()<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text='!!' expand='false'>.get()</fold>;

        Stream.of(data)<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'>.filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold>;
        Stream.of(data)<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='.filterNotNull()' expand='false'> .filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold>.findFirst()<fold text='!!' expand='false'>.orElseThrow()</fold>;
    }</fold>

    private Data orElseGetReturn()<fold text=' { ' expand='false'> {
        </fold>return null;<fold text=' }' expand='false'>
    }</fold>

    private Optional<Data> ofNullable(Data data)<fold text=' { ' expand='false'> {
        </fold>return Optional.empty();<fold text=' }' expand='false'>
    }</fold>

    private static Data getOutsideData(Data data)<fold text=' { ' expand='false'> {
        </fold>return null;<fold text=' }' expand='false'>
    }</fold>

    <fold text='@Getter @Setter s' expand='false'>s</fold>tatic class Data <fold text='{...}' expand='true'>{
        Data data;
        boolean ok;

        String string;<fold text='' expand='true'>

        </fold><fold text='' expand='true'>public Data getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='true'>

        </fold><fold text='' expand='true'>public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='true'>

        </fold><fold text='' expand='true'>public void setData(Data data)<fold text=' { ' expand='false'> {
            </fold>this.data = <fold text='<<' expand='false'>data</fold>;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='true'></fold>

        </fold><fold text='' expand='true'>public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = <fold text='<<' expand='false'>ok</fold>;<fold text=' }' expand='false'>
        }</fold></fold><fold text='' expand='true'>

        </fold><fold text='' expand='true'>public String getString()<fold text=' { ' expand='false'> {
            </fold>return string;<fold text=' }' expand='false'>
        }</fold><fold text='' expand='true'></fold>

        </fold><fold text='' expand='true'>public void setString(String string)<fold text=' { ' expand='false'> {
            </fold>this.string = <fold text='<<' expand='false'>string</fold>;<fold text=' }' expand='false'>
        }</fold></fold>

        public Data getDataMethod(Data data)<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>
    }</fold>
}
