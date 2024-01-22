package com.intellij.advancedExpressionFolding;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class SpreadTestData {
    public static void main(Stream<Data> stream) {
        Stream.of("123", "2313").map(String::length).toList();

        var a = stream.map(Data::getData).filter(Objects::nonNull)
                .map(Data::getData).flatMap(Data::getDataStream)
                .map(Data::getDataList).flatMap(List::stream)
                .map(Data::getString).map(String::chars)
                .map(it -> it.count())
                .map(it -> {
                    var z = 2;
                    return new BigDecimal(it + z);
                })
                .map(it -> Stream.iterate(it, BigDecimal::plus))
                .map(it -> stream)
                .flatMap(Function.identity())
                .map(Data::isOk)
                .toList();
    }

    class Data {
        Data data;
        boolean ok;

        String string;

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
