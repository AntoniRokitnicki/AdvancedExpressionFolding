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
            o = opt[120:".get()"];
        }
        o = opt[0:".orElseThrow()"];

        o = [1:"Optional.ofNullable("]dataNull[1:")"];
        o = [2:"Optional.of("]data[2:")"];

        o = [4:"Optional.ofNullable("]dataNull[4:")"][3:".orElseGet("]this::orElseGetReturn[3:")"];
        o = [6:"Optional.ofNullable("]dataNull[6:")"][5:".orElseGet("]() -> data.getData().getData()[5:")"];

        o = [9:"Optional.of("]data[9:")"][8:".map("][10:"Data::getData"][8:")"][7:".orElse("]null[7:")"];
        o = [12:"Optional.ofNullable("]dataNull[12:")"].map(OptionalTestData::getOutsideData)[11:".get()"];

        o = opt.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);

        o = [18:"Optional.of("]data[18:")"][17:".map("][19:"Data::getData"][17:")"][16:"\n                .map("][20:"Data::getData"][16:")"]
                .filter(it -> it.ok)
                .map(Function.identity())[15:"\n                .map("][21:"Data::isOk"][15:")"]
                .map(it -> !it).map(it -> {
                var s = it.toString();
        return s.equals("false");
                })[14:".orElse("]null[14:")"];

        o = [24:"Optional.of("]data.getData()[24:")"].map(OptionalTestData::getOutsideData)[23:".map("][25:"Data::getString"][23:")"][22:".orElse("]data.getString()[22:")"];

        [27:"Optional.of("]data[27:")"].flatMap(this::ofNullable).map(data::getDataMethod)[26:".orElseGet("]() -> getOutsideData(data)[26:")"];

        o = Optional.<Data>empty()[32:".map("][33:"Data::getData"][32:")"].stream()[31:".map("][34:"Data::getData"][31:")"][30:".filter(Objects::nonNull"][30:")"].findAny()[29:".map("][42:"Data::getString"][29:")"][28:".get()"];

        o = opt[58:".map("][59:"Data::getData"][58:")"][57:".orElse("]null[57:")"];

        o = Optional.<Data>empty()[64:".map("][65:"Data::getData"][64:")"].stream()[63:".map("][66:"Data::getData"][63:")"][62:" .filter(Objects::nonNull"][62:")"].findAny()[61:".map("][74:"Data::getString"][61:")"][60:".get()"];

        Stream.of(data)[90:".map("][91:"Data::getData"][90:")"][89:".filter(Objects::nonNull"][89:")"];
        Stream.of(data)[98:".map("][99:"Data::getData"][98:")"][97:" .filter(Objects::nonNull"][97:")"][96:".map("][100:"Data::getData"][96:")"].findFirst()[95:".orElseThrow()"];
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
