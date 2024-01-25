import <fold text='...' expand='false'>java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;</fold>

public class SpreadTestData {
    public static void main(Stream<Data> stream, Data data) <fold text='{...}' expand='true'>{

        var stream3 = Stream.of("123", "2313")<fold text='*.' expand='false'>.map(</fold><fold text='length()' expand='false'>String::length</fold><fold text='' expand='false'>)</fold>.toList();

        var a = stream<fold text='*.' expand='false'>.map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='!!' expand='false'>.filter(Objects::nonNull</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>
                .map(</fold><fold text='data()' expand='false'>Data::getData</fold><fold text='' expand='false'>)</fold><fold text='**.' expand='false'>.flatMap(</fold><fold text='dataStream()' expand='false'>Data::getDataStream</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>
                .map(</fold><fold text='dataList()' expand='false'>Data::getDataList</fold><fold text='' expand='false'>)</fold><fold text='**.' expand='false'>.flatMap(</fold><fold text='stream()' expand='false'>List::stream</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>
                .map(</fold><fold text='string()' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text='*.' expand='false'>.map(</fold><fold text='chars()' expand='false'>String::chars</fold><fold text='' expand='false'>)</fold>
                .map(it -> it.count())
                .map(it -> <fold text='{...}' expand='true'>{
                    var z = 2;
                    var max = Stream.of(data)<fold text='*.' expand='false'>.map(</fold><fold text='string()' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold>.max(Comparator.comparing(String::length))<fold text='?.' expand='false'>.map(</fold><fold text='length' expand='false'>String::length</fold><fold text='' expand='false'>)</fold>.stream()<fold text='*.' expand='false'>.map(</fold><fold text='class()' expand='false'>Object::getClass</fold><fold text='' expand='false'>)</fold>.findAny()<fold text='?.' expand='false'>.map(</fold><fold text='hashCode' expand='false'>Object::hashCode</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>1<fold text='' expand='false'>)</fold>;
                    return new BigDecimal(it + z + max);
                }</fold>)
                .map(it -> Stream.iterate(it, BigDecimal::plus))
                .map(it -> stream)
                .flatMap(Function.identity())<fold text='*.' expand='false'>
                .map(</fold><fold text='ok()' expand='false'>Data::isOk</fold><fold text='' expand='false'>)</fold>
                .toList();

        var p = methodStream(data).toList()<fold text='.' expand='false'>.stream().</fold>min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString))<fold text='?.' expand='false'>.map(</fold><fold text='string' expand='false'>Data::getString</fold><fold text='' expand='false'>)</fold><fold text=' ?: ' expand='false'>.orElse(</fold>"string1"<fold text='' expand='false'>)</fold>;
    }</fold>

    static Stream<Data> methodStream(Data data)<fold text=' { ' expand='false'> {
        </fold>return Stream.of(data);<fold text=' }' expand='false'>
    }</fold>

    class Data <fold text='{...}' expand='true'>{
        Data data;
        boolean ok;
        String string;
        public Data getData()<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>
        public boolean isOk()<fold text=' { ' expand='false'> {
            </fold>return ok;<fold text=' }' expand='false'>
        }</fold>
        public void setData(Data data)<fold text=' { ' expand='false'> {
            </fold>this.data = data;<fold text=' }' expand='false'>
        }</fold>
        public void setOk(boolean ok)<fold text=' { ' expand='false'> {
            </fold>this.ok = ok;<fold text=' }' expand='false'>
        }</fold>
        public String getString()<fold text=' { ' expand='false'> {
            </fold>return string;<fold text=' }' expand='false'>
        }</fold>
        public void setString(String string)<fold text=' { ' expand='false'> {
            </fold>this.string = string;<fold text=' }' expand='false'>
        }</fold>
        public Data getDataMethod(Data data)<fold text=' { ' expand='false'> {
            </fold>return data;<fold text=' }' expand='false'>
        }</fold>
        public Optional<Data> getDataOpt()<fold text=' { ' expand='false'> {
            </fold>return <fold text='' expand='false'>Optional.ofNullable(</fold>data<fold text='' expand='false'>)</fold>;<fold text=' }' expand='false'>
        }</fold>
        public Stream<Data> getDataStream()<fold text=' { ' expand='false'> {
            </fold>return <fold text='' expand='false'>Optional.ofNullable(</fold>data<fold text='' expand='false'>)</fold>.stream();<fold text=' }' expand='false'>
        }</fold>
        public List<Data> getDataList()<fold text=' { ' expand='false'> {
            </fold>return <fold text='' expand='false'>Optional.ofNullable(</fold>data<fold text='' expand='false'>)</fold>.stream().toList();<fold text=' }' expand='false'>
        }</fold>
    }</fold>
}
