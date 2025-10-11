package data;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
public class OptionalTestData {

    public void enter(Optional<Data> opt, Data data, Data dataNull) {
        [0:"Object"] o = null;
        if [1:"("]opt.[130:"isPresent()"][1:")"] {
            o = opt[131:".get()"];
        }
        o = opt[2:".orElseThrow()"];

        o = [3:"Optional.ofNullable("]dataNull[3:")"];
        o = [4:"Optional.of("]data[4:")"];

        o = [6:"Optional.ofNullable("]dataNull[6:")"][5:".orElseGet("]this::orElseGetReturn[5:")"];
        o = [8:"Optional.ofNullable("]dataNull[8:")"][7:".orElseGet("]() -> data.[10:"getData()"].[9:"getData()"][7:")"];

        o = [13:"Optional.of("]data[13:")"][12:".map("][14:"Data::getData"][12:")"][11:".orElse("]null[11:")"];
        o = [16:"Optional.ofNullable("]dataNull[16:")"].map(OptionalTestData::getOutsideData)[15:".get()"];

        o = opt.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);

        o = [22:"Optional.of("]data[22:")"][21:".map("][23:"Data::getData"][21:")"][20:"\n                .map("][24:"Data::getData"][20:")"]
                .filter(it -> it.ok)
                .map(Function.identity())[19:"\n                .map("][25:"Data::isOk"][19:")"]
                .map(it -> !it).map(it -> {
                [26:"var"] s = it.toString();
        return s[27:".equals("]"false"[27:")"];
                })[18:".orElse("]null[18:")"];

        o = [30:"Optional.of("]data.[31:"getData()"][30:")"].map(OptionalTestData::getOutsideData)[29:".map("][32:"Data::getString"][29:")"][28:".orElse("]data.[33:"getString()"][28:")"];

        [35:"Optional.of("]data[35:")"].flatMap(this::ofNullable).map(data::getDataMethod)[34:".orElseGet("]() -> getOutsideData(data)[34:")"];

        o = Optional.<Data>empty()[40:".map("][41:"Data::getData"][40:")"].stream()[39:".map("][42:"Data::getData"][39:")"][38:".filter(Objects::nonNull"][38:")"].findAny()[37:".map("][50:"Data::getString"][37:")"][36:".get()"];

        o = opt[66:".map("][67:"Data::getData"][66:")"][65:".orElse("]null[65:")"];

        o = Optional.<Data>empty()[72:".map("][73:"Data::getData"][72:")"].stream()[71:".map("][74:"Data::getData"][71:")"][70:" .filter(Objects::nonNull"][70:")"].findAny()[69:".map("][82:"Data::getString"][69:")"][68:".get()"];

        Stream.of(data)[98:".map("][99:"Data::getData"][98:")"][97:".filter(Objects::nonNull"][97:")"];
        Stream.of(data)[106:".map("][107:"Data::getData"][106:")"][105:" .filter(Objects::nonNull"][105:")"][104:".map("][108:"Data::getData"][104:")"].findFirst()[103:".orElseThrow()"];
    }

    private Data orElseGetReturn() {[258:"\n        "][258:"return"][258:" "]null[258:";"][258:"\n    "]}

    private Optional<Data> ofNullable(Data data) {[259:"\n        "][259:"return"][259:" "]Optional.empty()[259:";"][259:"\n    "]}

    private static Data getOutsideData(Data data) {[260:"\n        "][260:"return"][260:" "]null[260:";"][260:"\n    "]}

    [261:"s"]tatic class Data {
        [262:"D"]ata data;
        boolean ok;

        String string;[262:"\n\n        "][262:"public Data(Data data) {\n            this.data = data;\n        }"][261:"\n\n        "][261:"public Data getData() {\n                return data;\n        }"][261:"\n\n        "][261:"public boolean isOk() {\n                return ok;\n        }"][261:"\n\n        "][261:"public void setData(Data data) {\n                this.data = data;\n        }"][261:"\n\n        "][261:"public void setOk(boolean ok) {\n                this.ok = ok;\n        }"][261:"\n\n        "][261:"public String getString() {\n                return string;\n        }"][261:"\n\n        "][261:"public void setString(String string) {\n                this.string = string;\n        }"]

        public Data getDataMethod(Data data) {[286:"\n                "][286:"return"][286:" "]data[286:";"][286:"\n        "]}
    }
}
