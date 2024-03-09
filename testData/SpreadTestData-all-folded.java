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
        val empNames = list*.string()
                .collect(Collectors.joining(", "));

        val p1 = data.dataList.stream()*.data().toList();
        val p2 = data.dataList
                .stream()*.data()
                .toList()
                .stream()*.data()
                .toList();

        val stream3 = Stream.of("123", "2313")*.length().toList();

        val a = stream*.data().filterNotNull()*.data()**.dataStream()*.dataList()**.stream()*.string()*.chars()
                .map(it -> it.count() + 1)
                .map(it -> {
                    val z = 2;
                    val max = Stream.of(data)*.string().max(Comparator.comparing(String::length))?.length.stream()*.class().findAny()?.hashCode ?: 1;
                    return new BigDecimal(it + z + max);
                })
                .map(it -> Stream.iterate(it, BigDecimal::plus))
                .map(it -> stream)
                .flatMap(Function.identity())*.ok()
                .toList();

        val p = methodStream(data).toList().min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString))?.string ?: "string1";


        val o1 = stream.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);
    }

    static Stream<Data> methodStream(Data data) {
        return Stream.of(data);
    }

    @Getter @Setter static class Data {
        Data data;
        boolean ok;
        String string;

        public Data(Data data) {
            this.data = <<;
        }
        public Data getDataMethod(Data data) {
                return data;
        }
        public Optional<Data> getDataOpt() {
                return data;
        }
        public Stream<Data> getDataStream() {
                return data.stream();
        }
        public List<Data> getDataList() {
                return data.stream().toList();
        }
    }
}
