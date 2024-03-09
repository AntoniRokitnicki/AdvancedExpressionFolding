package data;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
public class OptionalTestData {

    public void enter(Optional<Data> opt, @NotNull Data data, @Nullable Data dataNull) {
        var o = null;
        if opt.present {
            o = opt!!;
        }
        o = opt!!;

        o = dataNull;
        o = data!!;

        o = dataNull ?: this::orElseGetReturn;
        o = dataNull ?: () -> data.data.data;

        o = data!!.data ?: null;
        o = dataNull.map(OptionalTestData::getOutsideData)!!;

        o = opt.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);

        o = data!!.data?.data
                .filter(it -> it.ok)
                .map(Function.identity())?.ok
                .map(it -> !it).map(it -> {
                val s = it.toString();
        return s ≡ "false";
                }) ?: null;

        o = data.data!!.map(OptionalTestData::getOutsideData)?.string ?: data.string;

        data!!.flatMap(this::ofNullable).map(data::getDataMethod) ?: () -> getOutsideData(data);

        o = Optional.<Data>empty()?.data.stream()*.data().filterNotNull().findAny()?.string!!;

        o = opt?.data ?: null;

        o = Optional.<Data>empty()?.data.stream()*.data().filterNotNull().findAny()?.string!!;

        Stream.of(data)*.data().filterNotNull();
        Stream.of(data)*.data().filterNotNull()*.data().findFirst()!!;
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
    }
}
