package data;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
public class OptionalTestData {

    public void enter(Optional<Data> opt, Data data, Data dataNull) {
        Object o = null;
        if (opt.isPresent()) {
            o = opt[0:".get()"];
        }
        o = opt[0:".orElseThrow()"]{[0:".orElseThrow()"]};

        o = [0:"Optional.ofNullable("]{[0:"Optional.ofNullable("]}dataNull[0:")"]{[0:")"]};
        o = [0:"Optional.of("]{[0:"Optional.of("]}data[0:")"]{[0:")"]};

        o = [0:"Optional.ofNullable("]{[0:"Optional.ofNullable("]}dataNull[0:")"]{[0:")"]}[0:".orElseGet("]{[0:".orElseGet("]}this::orElseGetReturn[0:")"]{[0:")"]};
        o = [0:"Optional.ofNullable("]{[0:"Optional.ofNullable("]}dataNull[0:")"]{[0:")"]}[0:".orElseGet("]{[0:".orElseGet("]}() -> data.getData().getData()[0:")"]{[0:")"]};

        o = [0:"Optional.of("]{[0:"Optional.of("]}data[0:")"]{[0:")"]}[0:".map("]{[0:".map("]}[0:"Data::getData"]{[0:"Data::getData"]}[0:")"]{[0:")"]}[0:".orElse("]{[0:".orElse("]}null[0:")"]{[0:")"]};
        o = [0:"Optional.ofNullable("]{[0:"Optional.ofNullable("]{[0:"Optional.ofNullable("]{[0:"Optional.ofNullable("]}}}dataNull[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}.map(OptionalTestData::getOutsideData)[0:".get()"]{[0:".get()"]};

        o = opt.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);

        o = [0:"Optional.of("]{[0:"Optional.of("]}data[0:")"]{[0:")"]}[0:".map("]{[0:".map("]}[0:"Data::getData"]{[0:"Data::getData"]}[0:")"]{[0:")"]}[0:"
                .map("]{[0:"
                .map("]}[0:"Data::getData"]{[0:"Data::getData"]}[0:")"]{[0:")"]}
                .filter(it -> it.ok)
                .map(Function.identity())[0:"
                .map("]{[0:"
                .map("]}[0:"Data::isOk"]{[0:"Data::isOk"]}[0:")"]{[0:")"]}
                .map(it -> !it).map(it -> {
                var s = it.toString();
        return s.equals("false");
                })[0:".orElse("]{[0:".orElse("]}null[0:")"]{[0:")"]};

        o = [0:"Optional.of("]{[0:"Optional.of("]}data.getData()[0:")"]{[0:")"]}.map(OptionalTestData::getOutsideData)[0:".map("]{[0:".map("]}[0:"Data::getString"]{[0:"Data::getString"]}[0:")"]{[0:")"]}[0:".orElse("]{[0:".orElse("]}data.getString()[0:")"]{[0:")"]};

        [0:"Optional.of("]{[0:"Optional.of("]}data[0:")"]{[0:")"]}.flatMap(this::ofNullable).map(data::getDataMethod)[0:".orElseGet("]{[0:".orElseGet("]}() -> getOutsideData(data)[0:")"]{[0:")"]};

        o = Optional.<Data>empty()[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]}}}}}}}}}}}[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]}}}}}}}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}}}}}.stream()[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]}}}}}}}[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]}}}}}}}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}[0:".filter(Objects::nonNull"]{[0:".filter(Objects::nonNull"]{[0:".filter(Objects::nonNull"]{[0:".filter(Objects::nonNull"]}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}.findAny()[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]}}}[0:"Data::getString"]{[0:"Data::getString"]{[0:"Data::getString"]{[0:"Data::getString"]}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}[0:".get()"]{[0:".get()"]};

        o = opt[0:".map("]{[0:".map("]}[0:"Data::getData"]{[0:"Data::getData"]}[0:")"]{[0:")"]}[0:".orElse("]{[0:".orElse("]}null[0:")"]{[0:")"]};

        o = Optional.<Data>empty()[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]}}}}}}}}}}}[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]}}}}}}}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}}}}}.stream()[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]}}}}}}}[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]}}}}}}}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}.findAny()[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]}}}[0:"Data::getString"]{[0:"Data::getString"]{[0:"Data::getString"]{[0:"Data::getString"]}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}[0:".get()"]{[0:".get()"]};

        Stream.of(data)[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]}}}[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}[0:".filter(Objects::nonNull"]{[0:".filter(Objects::nonNull"]}[0:")"]{[0:")"]};
        Stream.of(data)[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]}}}}}}}}}}}[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]}}}}}}}}}}}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}}}}}[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]{[0:" .filter(Objects::nonNull"]}}}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}[0:".map("]{[0:".map("]{[0:".map("]{[0:".map("]}}}[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]{[0:"Data::getData"]}}}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}.findFirst()[0:".orElseThrow()"]{[0:".orElseThrow()"]};
    }

    private Data orElseGetReturn() {
        return null;
    }

    private Optional<Data> ofNullable(Data data) {
        return Optional.empty();
    }

    private static Data getOutsideData(Data data) {
        return null;
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
    }
}
