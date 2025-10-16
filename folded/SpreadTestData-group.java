package data;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
public class SpreadTestData {
    public static void main(Stream<Data> stream, Data data, List<Data> list) {
        String empNames = list[1:".stream()"][0:"\n                .map("][2:"Data::getString"][0:")"]
                .collect(Collectors.joining(", "));

        var p1 = data.getDataList().stream()[5:".map("][7:"Data::getData"][5:")"].toList();
        var p2 = data.getDataList()
                .stream()[14:"\n                .map("][16:"Data::getData"][14:")"]
                .toList()
                .stream()[10:"\n                .map("][12:"Data::getData"][10:")"]
                .toList();

        var stream3 = Stream.of("123", "2313")[20:".map("][21:"String::length"][20:")"].toList();

        var a = stream[31:".map("][32:"Data::getData"][31:")"][30:".filter(Objects::nonNull"][30:")"][29:"\n                .map("][33:"Data::getData"][29:")"][28:".flatMap("][34:"Data::getDataStream"][28:")"][27:"\n                .map("][35:"Data::getDataList"][27:")"][26:".flatMap("][36:"List::stream"][26:")"][25:"\n                .map("][37:"Data::getString"][25:")"][24:".map("][38:"String::chars"][24:")"]
                .map(it -> it.count()[96:"+"]1)
                .map(it -> {
                    var z = 2;
                    var max = Stream.of(data)[101:".map("][102:"Data::getString"][101:")"].max(Comparator.comparing(String::length))[100:".map("][104:"String::length"][100:")"].stream()[99:".map("][105:"Object::getClass"][99:")"].findAny()[98:".map("][112:"Object::hashCode"][98:")"][97:".orElse("]1[97:")"];
                    return new BigDecimal(it + z + max);
                })
                .map(it -> Stream.iterate(it, BigDecimal::plus))
                .map(it -> stream)
                .flatMap(Function.identity())[23:"\n                .map("][113:"Data::isOk"][23:")"]
                .toList();

        var p = methodStream(data).toList()[206:".stream()."]min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString))[205:".map("][207:"Data::getString"][205:")"][204:".orElse("]"string1"[204:")"];


        var o1 = stream.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);
    }

    static Stream<Data> methodStream(Data data) {
        return Stream.of(data);
    }

    static class Data {
        Data data;
        boolean ok;
        String string;

        public Data(Data data) {
            this.data = data;
        }

        public Data getData() {
                return data;
        }
        public boolean isOk() {
                return ok;
        }
        public void setData(Data data) {
                this.data = data;
        }
        public void setOk(boolean ok) {
                this.ok = ok;
        }
        public String getString() {
                return string;
        }
        public void setString(String string) {
                this.string = string;
        }
        public Data getDataMethod(Data data) {
                return data;
        }
        public Optional<Data> getDataOpt() {
                return [416:"Optional.ofNullable("]data[416:")"];
        }
        public Stream<Data> getDataStream() {
                return [418:"Optional.ofNullable("]data[418:")"].stream();
        }
        public List<Data> getDataList() {
                return [420:"Optional.ofNullable("]data[420:")"].stream().toList();
        }
    }
}
