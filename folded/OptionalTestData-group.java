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
        o = opt[0:".orElseThrow()"][121:".orElseThrow()"];

        o = [1:"Optional.ofNullable("][122:"Optional.ofNullable("]dataNull[1:")"][122:")"];
        o = [2:"Optional.of("][123:"Optional.of("]data[2:")"][123:")"];

        o = [4:"Optional.ofNullable("][125:"Optional.ofNullable("]dataNull[4:")"][125:")"][3:".orElseGet("][124:".orElseGet("]this::orElseGetReturn[3:")"][124:")"];
        o = [6:"Optional.ofNullable("][127:"Optional.ofNullable("]dataNull[6:")"][127:")"][5:".orElseGet("][126:".orElseGet("]() -> data.getData().getData()[5:")"][126:")"];

        o = [9:"Optional.of("][130:"Optional.of("]data[9:")"][130:")"][8:".map("][129:".map("][10:"Data::getData"][131:"Data::getData"][8:")"][129:")"][7:".orElse("][128:".orElse("]null[7:")"][128:")"];
        o = [12:"Optional.ofNullable("][13:"Optional.ofNullable("][133:"Optional.ofNullable("][134:"Optional.ofNullable("]dataNull[12:")"][13:")"][133:")"][134:")"].map(OptionalTestData::getOutsideData)[11:".get()"][132:".get()"];

        o = opt.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);

        o = [18:"Optional.of("][139:"Optional.of("]data[18:")"][139:")"][17:".map("][138:".map("][19:"Data::getData"][140:"Data::getData"][17:")"][138:")"][16:"\n                .map("][137:"\n                .map("][20:"Data::getData"][141:"Data::getData"][16:")"][137:")"]
                .filter(it -> it.ok)
                .map(Function.identity())[15:"\n                .map("][136:"\n                .map("][21:"Data::isOk"][142:"Data::isOk"][15:")"][136:")"]
                .map(it -> !it).map(it -> {
                var s = it.toString();
        return s.equals("false");
                })[14:".orElse("][135:".orElse("]null[14:")"][135:")"];

        o = [24:"Optional.of("][145:"Optional.of("]data.getData()[24:")"][145:")"].map(OptionalTestData::getOutsideData)[23:".map("][144:".map("][25:"Data::getString"][146:"Data::getString"][23:")"][144:")"][22:".orElse("][143:".orElse("]data.getString()[22:")"][143:")"];

        [27:"Optional.of("][148:"Optional.of("]data[27:")"][148:")"].flatMap(this::ofNullable).map(data::getDataMethod)[26:".orElseGet("][147:".orElseGet("]() -> getOutsideData(data)[26:")"][147:")"];

        o = Optional.<Data>empty()[32:".map("][36:".map("][39:".map("][46:".map("][50:".map("][53:".map("][153:".map("][157:".map("][160:".map("][167:".map("][171:".map("][174:".map("][33:"Data::getData"][37:"Data::getData"][40:"Data::getData"][47:"Data::getData"][51:"Data::getData"][54:"Data::getData"][154:"Data::getData"][158:"Data::getData"][161:"Data::getData"][168:"Data::getData"][172:"Data::getData"][175:"Data::getData"][32:")"][36:")"][39:")"][46:")"][50:")"][53:")"][153:")"][157:")"][160:")"][167:")"][171:")"][174:")"].stream()[31:".map("][35:".map("][45:".map("][49:".map("][152:".map("][156:".map("][166:".map("][170:".map("][34:"Data::getData"][38:"Data::getData"][41:"Data::getData"][48:"Data::getData"][52:"Data::getData"][55:"Data::getData"][155:"Data::getData"][159:"Data::getData"][162:"Data::getData"][169:"Data::getData"][173:"Data::getData"][176:"Data::getData"][31:")"][35:")"][45:")"][49:")"][152:")"][156:")"][166:")"][170:")"][30:".filter(Objects::nonNull"][44:".filter(Objects::nonNull"][151:".filter(Objects::nonNull"][165:".filter(Objects::nonNull"][30:")"][44:")"][151:")"][165:")"].findAny()[29:".map("][43:".map("][150:".map("][164:".map("][42:"Data::getString"][56:"Data::getString"][163:"Data::getString"][177:"Data::getString"][29:")"][43:")"][150:")"][164:")"][28:".get()"][149:".get()"];

        o = opt[58:".map("][179:".map("][59:"Data::getData"][180:"Data::getData"][58:")"][179:")"][57:".orElse("][178:".orElse("]null[57:")"][178:")"];

        o = Optional.<Data>empty()[64:".map("][68:".map("][71:".map("][78:".map("][82:".map("][85:".map("][185:".map("][189:".map("][192:".map("][199:".map("][203:".map("][206:".map("][65:"Data::getData"][69:"Data::getData"][72:"Data::getData"][79:"Data::getData"][83:"Data::getData"][86:"Data::getData"][186:"Data::getData"][190:"Data::getData"][193:"Data::getData"][200:"Data::getData"][204:"Data::getData"][207:"Data::getData"][64:")"][68:")"][71:")"][78:")"][82:")"][85:")"][185:")"][189:")"][192:")"][199:")"][203:")"][206:")"].stream()[63:".map("][67:".map("][77:".map("][81:".map("][184:".map("][188:".map("][198:".map("][202:".map("][66:"Data::getData"][70:"Data::getData"][73:"Data::getData"][80:"Data::getData"][84:"Data::getData"][87:"Data::getData"][187:"Data::getData"][191:"Data::getData"][194:"Data::getData"][201:"Data::getData"][205:"Data::getData"][208:"Data::getData"][63:")"][67:")"][77:")"][81:")"][184:")"][188:")"][198:")"][202:")"][62:" .filter(Objects::nonNull"][76:" .filter(Objects::nonNull"][183:" .filter(Objects::nonNull"][197:" .filter(Objects::nonNull"][62:")"][76:")"][183:")"][197:")"].findAny()[61:".map("][75:".map("][182:".map("][196:".map("][74:"Data::getString"][88:"Data::getString"][195:"Data::getString"][209:"Data::getString"][61:")"][75:")"][182:")"][196:")"][60:".get()"][181:".get()"];

        Stream.of(data)[90:".map("][92:".map("][211:".map("][213:".map("][91:"Data::getData"][93:"Data::getData"][94:"Data::getData"][212:"Data::getData"][214:"Data::getData"][215:"Data::getData"][90:")"][92:")"][211:")"][213:")"][89:".filter(Objects::nonNull"][210:".filter(Objects::nonNull"][89:")"][210:")"];
        Stream.of(data)[98:".map("][102:".map("][104:".map("][110:".map("][114:".map("][116:".map("][219:".map("][223:".map("][225:".map("][231:".map("][235:".map("][237:".map("][99:"Data::getData"][103:"Data::getData"][105:"Data::getData"][106:"Data::getData"][111:"Data::getData"][115:"Data::getData"][117:"Data::getData"][118:"Data::getData"][220:"Data::getData"][224:"Data::getData"][226:"Data::getData"][227:"Data::getData"][232:"Data::getData"][236:"Data::getData"][238:"Data::getData"][239:"Data::getData"][98:")"][102:")"][104:")"][110:")"][114:")"][116:")"][219:")"][223:")"][225:")"][231:")"][235:")"][237:")"][97:" .filter(Objects::nonNull"][101:" .filter(Objects::nonNull"][109:" .filter(Objects::nonNull"][113:" .filter(Objects::nonNull"][218:" .filter(Objects::nonNull"][222:" .filter(Objects::nonNull"][230:" .filter(Objects::nonNull"][234:" .filter(Objects::nonNull"][97:")"][101:")"][109:")"][113:")"][218:")"][222:")"][230:")"][234:")"][96:".map("][108:".map("][217:".map("][229:".map("][100:"Data::getData"][107:"Data::getData"][112:"Data::getData"][119:"Data::getData"][221:"Data::getData"][228:"Data::getData"][233:"Data::getData"][240:"Data::getData"][96:")"][108:")"][217:")"][229:")"].findFirst()[95:".orElseThrow()"][216:".orElseThrow()"];
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
