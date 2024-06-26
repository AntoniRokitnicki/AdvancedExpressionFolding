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
        String empNames = list.stream()
                .map(Data::getString)
                .collect(Collectors.joining(", "));

        var p1 = data.getDataList().stream().map(Data::getData).toList();
        var p2 = data.getDataList()
                .stream()
                .map(Data::getData)
                .toList()
                .stream()
                .map(Data::getData)
                .toList();

        var stream3 = Stream.of("123", "2313").map(String::length).toList();

        var a = stream.map(Data::getData).filter(Objects::nonNull)
                .map(Data::getData).flatMap(Data::getDataStream)
                .map(Data::getDataList).flatMap(List::stream)
                .map(Data::getString).map(String::chars)
                .map(it -> it.count()+1)
                .map(it -> {
                    var z = 2;
                    var max = Stream.of(data).map(Data::getString).max(Comparator.comparing(String::length)).map(String::length).stream().map(Object::getClass).findAny().map(Object::hashCode).orElse(1);
                    return new BigDecimal(it + z + max);
                })
                .map(it -> Stream.iterate(it, BigDecimal::plus))
                .map(it -> stream)
                .flatMap(Function.identity())
                .map(Data::isOk)
                .toList();

        var p = methodStream(data).toList().stream().min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString)).map(Data::getString).orElse("string1");


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
                return Optional.ofNullable(data);
        }
        public Stream<Data> getDataStream() {
                return Optional.ofNullable(data).stream();
        }
        public List<Data> getDataList() {
                return Optional.ofNullable(data).stream().toList();
        }
    }
}
