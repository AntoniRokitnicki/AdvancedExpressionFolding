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
        [0:"String"] empNames = list[2:".stream()"][1:"\n                .map("][3:"Data::getString"][1:")"]
                .collect(Collectors.joining(", "));

        [6:"var"] p1 = data.[11:"getDataList()"].stream()[7:".map("][9:"Data::getData"][7:")"].toList();
        [13:"var"] p2 = data.[22:"getDataList()"]
                .stream()[18:"\n                .map("][20:"Data::getData"][18:")"]
                .toList()
                .stream()[14:"\n                .map("][16:"Data::getData"][14:")"]
                .toList();

        [25:"var"] stream3 = Stream.of("123", "2313")[26:".map("][27:"String::length"][26:")"].toList();

        [29:"var"] a = stream[38:".map("][39:"Data::getData"][38:")"][37:".filter(Objects::nonNull"][37:")"][36:"\n                .map("][40:"Data::getData"][36:")"][35:".flatMap("][41:"Data::getDataStream"][35:")"][34:"\n                .map("][42:"Data::getDataList"][34:")"][33:".flatMap("][43:"List::stream"][33:")"][32:"\n                .map("][44:"Data::getString"][32:")"][31:".map("][45:"String::chars"][31:")"]
                .map(it -> it.count()[103:"+"]1)
                .map(it -> {
                    [104:"var"] z = 2;
                    [105:"var"] max = Stream.of(data)[110:".map("][111:"Data::getString"][110:")"].max(Comparator.comparing(String::length))[109:".map("][113:"String::length"][109:")"].stream()[108:".map("][114:"Object::getClass"][108:")"].findAny()[107:".map("][121:"Object::hashCode"][107:")"][106:".orElse("]1[106:")"];
                    return new BigDecimal(it + z + max);
                })
                .map(it -> Stream.iterate(it, BigDecimal::plus))
                .map(it -> stream)
                .flatMap(Function.identity())[30:"\n                .map("][122:"Data::isOk"][30:")"]
                .toList();

        [215:"var"] p = methodStream(data).toList()[218:".stream()."]min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString))[217:".map("][219:"Data::getString"][217:")"][216:".orElse("]"string1"[216:")"];


        [220:"var"] o1 = stream.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);
    }

    static Stream<Data> methodStream(Data data) {[442:"\n        "][442:"return"][442:" "]Stream.of(data)[442:";"][442:"\n    "]}

    [443:"s"]tatic class Data {
        [444:"D"]ata data;
        boolean ok;
        String string;[444:"\n\n        "][444:"public Data(Data data) {\n            this.data = data;\n        }"][443:"\n\n        "][443:"public Data getData() {\n                return data;\n        }"][443:"\n        "][443:"public boolean isOk() {\n                return ok;\n        }"][443:"\n        "][443:"public void setData(Data data) {\n                this.data = data;\n        }"][443:"\n        "][443:"public void setOk(boolean ok) {\n                this.ok = ok;\n        }"][443:"\n        "][443:"public String getString() {\n                return string;\n        }"][443:"\n        "][443:"public void setString(String string) {\n                this.string = string;\n        }"]
        public Data getDataMethod(Data data) {[468:"\n                "][468:"return"][468:" "]data[468:";"][468:"\n        "]}
        public Optional<Data> getDataOpt() {[469:"\n                "][469:"return"][469:" "][470:"Optional.ofNullable("]data[470:")"][469:";"][469:"\n        "]}
        public Stream<Data> getDataStream() {[472:"\n                "][472:"return"][472:" "][473:"Optional.ofNullable("]data[473:")"].stream()[472:";"][472:"\n        "]}
        public List<Data> getDataList() {[475:"\n                "][475:"return"][475:" "][476:"Optional.ofNullable("]data[476:")"].stream().toList()[475:";"][475:"\n        "]}
    }
}
