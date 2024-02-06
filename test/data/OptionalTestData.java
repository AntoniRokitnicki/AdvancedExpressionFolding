package data;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class OptionalTestData {

    public void main(Optional<Data> opt, @NotNull Data data, @Nullable Data dataNull) {
        Object o = null;
        if (opt.isPresent()) {
            o = opt.get();
        }
        o = opt.orElseThrow();

        o = Optional.ofNullable(dataNull);
        o = Optional.of(data);

        o = Optional.ofNullable(dataNull).orElseGet(this::orElseGetReturn);
        o = Optional.ofNullable(dataNull).orElseGet(() -> data.getData().getData());

        o = Optional.of(data).map(Data::getData).orElse(null);
        o = Optional.ofNullable(dataNull).map(OptionalTestData::getOutsideData).get();

        o = Optional.of(data).map(Data::getData)
                .map(Data::getData)
                .filter(it -> it.ok)
                .map(Function.identity())
                .map(Data::isOk)
                .map(it -> !it).map(it -> {
                var s = it.toString();
        return s.equals("false");
                }).orElse(null);

        o = Optional.of(data.getData()).map(OptionalTestData::getOutsideData).map(Data::getString).orElse(data.getString());

        Optional.of(data).flatMap(this::ofNullable).map(data::getDataMethod).orElseGet(() -> getOutsideData(data));

        o = Optional.<Data>empty().map(Data::getData).stream().map(Data::getData).filter(Objects::nonNull).findAny().map(Data::getString).get();

        o = opt.map(Data::getData).orElse(null);
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
    }
}
