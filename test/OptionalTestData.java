import java.util.Objects;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import static java.util.Optional.of;
import java.util.function.Function;

public class OptionalTestData {

    public void main(Data data, Optional<Data> opt) {
        opt.get();
        opt.orElseThrow();

        Optional.ofNullable(data);
        Optional.of(data);

        Optional.ofNullable(data).orElseGet(this::orElseGetReturn);
        Optional.ofNullable(data).orElseGet(() -> data.getData());

        var p = Optional.of(data).map(Data::getData).orElse(null);
        var p2 = Optional.ofNullable(data).map(OptionalTestData::getOutsideData).get();

        var ok = Optional.ofNullable(data).map(Data::getData)
                .map(Data::getData)
                .filter(it -> it.ok)
                .map(Function.identity())
                .map(Data::isOk)
                .map(it -> !it).map(it -> {
                var s = it.toString();
        return s.equals("false");
                }).orElse(null);

        var string = Optional.of(data.getData()).map(OptionalTestData::getOutsideData).map(Data::getString).orElse(data.getString());

        Optional.of(data).flatMap(this::ofNullable).map(data::getDataMethod).orElseGet(() -> getOutsideData(data));

        Optional.<Data>empty().map(Data::getData).stream().map(Data::getData).filter(Objects::nonNull).findAny().map(Data::getString).get();

        opt.map(Data::getData).orElse(null);
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
