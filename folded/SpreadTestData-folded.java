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
        String empNames = list*.string()
                .collect(Collectors.joining(", "));

        var p1 = data.getDataList().stream()*.data().toList();
        var p2 = data.getDataList()
                .stream()*.data()
                .toList()
                .stream()*.data()
                .toList();

        var stream3 = Stream.of("123", "2313")*.length().toList();

        var a = stream*.data().filterNotNull()*.data()**.dataStream()*.dataList()**.stream()*.string()*.chars()
                .map(it -> it.count() + 1)
                .map(it -> {
                    var z = 2;
                    var max = Stream.of(data)*.string().max(Comparator.comparing(String::length))?.length.stream()*.class().findAny()?.hashCode ?: 1;
                    return new BigDecimal(it + z + max);
                })
                .map(it -> Stream.iterate(it, BigDecimal::plus))
                .map(it -> stream)
                .flatMap(Function.identity())*.ok()
                .toList();

        var p = methodStream(data).toList().min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString))?.string ?: "string1";


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
