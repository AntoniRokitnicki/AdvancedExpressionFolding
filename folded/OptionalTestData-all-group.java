package data;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
public class OptionalTestData {

    public void enter(Optional<Data> opt, Data data, Data dataNull) {
        [0:"Object"][128:"Object"] o = null;
        if [1:"("][129:"("]opt.[130:"isPresent()"][1:")"][129:")"] {
            o = opt[131:".get()"];
        }
        o = opt[2:".orElseThrow()"][132:".orElseThrow()"];

        o = [3:"Optional.ofNullable("][133:"Optional.ofNullable("]dataNull[3:")"][133:")"];
        o = [4:"Optional.of("][134:"Optional.of("]data[4:")"][134:")"];

        o = [6:"Optional.ofNullable("][136:"Optional.ofNullable("]dataNull[6:")"][136:")"][5:".orElseGet("][135:".orElseGet("]this::orElseGetReturn[5:")"][135:")"];
        o = [8:"Optional.ofNullable("][138:"Optional.ofNullable("]dataNull[8:")"][138:")"][7:".orElseGet("][137:".orElseGet("]() -> data.[10:"getData()"][140:"getData()"].[9:"getData()"][139:"getData()"][7:")"][137:")"];

        o = [13:"Optional.of("][143:"Optional.of("]data[13:")"][143:")"][12:".map("][142:".map("][14:"Data::getData"][144:"Data::getData"][12:")"][142:")"][11:".orElse("][141:".orElse("]null[11:")"][141:")"];
        o = [16:"Optional.ofNullable("][17:"Optional.ofNullable("][146:"Optional.ofNullable("][147:"Optional.ofNullable("]dataNull[16:")"][17:")"][146:")"][147:")"].map(OptionalTestData::getOutsideData)[15:".get()"][145:".get()"];

        o = opt.map(Data::new).filter(Data.class::isInstance).map(Data.class::cast);

        o = [22:"Optional.of("][152:"Optional.of("]data[22:")"][152:")"][21:".map("][151:".map("][23:"Data::getData"][153:"Data::getData"][21:")"][151:")"][20:"\n                .map("][150:"\n                .map("][24:"Data::getData"][154:"Data::getData"][20:")"][150:")"]
                .filter(it -> it.ok)
                .map(Function.identity())[19:"\n                .map("][149:"\n                .map("][25:"Data::isOk"][155:"Data::isOk"][19:")"][149:")"]
                .map(it -> !it).map(it -> {
                [26:"var"][156:"var"] s = it.toString();
        return s[27:".equals("][157:".equals("]"false"[27:")"][157:")"];
                })[18:".orElse("][148:".orElse("]null[18:")"][148:")"];

        o = [30:"Optional.of("][160:"Optional.of("]data.[31:"getData()"][161:"getData()"][30:")"][160:")"].map(OptionalTestData::getOutsideData)[29:".map("][159:".map("][32:"Data::getString"][162:"Data::getString"][29:")"][159:")"][28:".orElse("][158:".orElse("]data.[33:"getString()"][163:"getString()"][28:")"][158:")"];

        [35:"Optional.of("][165:"Optional.of("]data[35:")"][165:")"].flatMap(this::ofNullable).map(data::getDataMethod)[34:".orElseGet("][164:".orElseGet("]() -> getOutsideData(data)[34:")"][164:")"];

        o = Optional.<Data>empty()[40:".map("][44:".map("][47:".map("][54:".map("][58:".map("][61:".map("][170:".map("][174:".map("][177:".map("][184:".map("][188:".map("][191:".map("][41:"Data::getData"][45:"Data::getData"][48:"Data::getData"][55:"Data::getData"][59:"Data::getData"][62:"Data::getData"][171:"Data::getData"][175:"Data::getData"][178:"Data::getData"][185:"Data::getData"][189:"Data::getData"][192:"Data::getData"][40:")"][44:")"][47:")"][54:")"][58:")"][61:")"][170:")"][174:")"][177:")"][184:")"][188:")"][191:")"].stream()[39:".map("][43:".map("][53:".map("][57:".map("][169:".map("][173:".map("][183:".map("][187:".map("][42:"Data::getData"][46:"Data::getData"][49:"Data::getData"][56:"Data::getData"][60:"Data::getData"][63:"Data::getData"][172:"Data::getData"][176:"Data::getData"][179:"Data::getData"][186:"Data::getData"][190:"Data::getData"][193:"Data::getData"][39:")"][43:")"][53:")"][57:")"][169:")"][173:")"][183:")"][187:")"][38:".filter(Objects::nonNull"][52:".filter(Objects::nonNull"][168:".filter(Objects::nonNull"][182:".filter(Objects::nonNull"][38:")"][52:")"][168:")"][182:")"].findAny()[37:".map("][51:".map("][167:".map("][181:".map("][50:"Data::getString"][64:"Data::getString"][180:"Data::getString"][194:"Data::getString"][37:")"][51:")"][167:")"][181:")"][36:".get()"][166:".get()"];

        o = opt[66:".map("][196:".map("][67:"Data::getData"][197:"Data::getData"][66:")"][196:")"][65:".orElse("][195:".orElse("]null[65:")"][195:")"];

        o = Optional.<Data>empty()[72:".map("][76:".map("][79:".map("][86:".map("][90:".map("][93:".map("][202:".map("][206:".map("][209:".map("][216:".map("][220:".map("][223:".map("][73:"Data::getData"][77:"Data::getData"][80:"Data::getData"][87:"Data::getData"][91:"Data::getData"][94:"Data::getData"][203:"Data::getData"][207:"Data::getData"][210:"Data::getData"][217:"Data::getData"][221:"Data::getData"][224:"Data::getData"][72:")"][76:")"][79:")"][86:")"][90:")"][93:")"][202:")"][206:")"][209:")"][216:")"][220:")"][223:")"].stream()[71:".map("][75:".map("][85:".map("][89:".map("][201:".map("][205:".map("][215:".map("][219:".map("][74:"Data::getData"][78:"Data::getData"][81:"Data::getData"][88:"Data::getData"][92:"Data::getData"][95:"Data::getData"][204:"Data::getData"][208:"Data::getData"][211:"Data::getData"][218:"Data::getData"][222:"Data::getData"][225:"Data::getData"][71:")"][75:")"][85:")"][89:")"][201:")"][205:")"][215:")"][219:")"][70:" .filter(Objects::nonNull"][84:" .filter(Objects::nonNull"][200:" .filter(Objects::nonNull"][214:" .filter(Objects::nonNull"][70:")"][84:")"][200:")"][214:")"].findAny()[69:".map("][83:".map("][199:".map("][213:".map("][82:"Data::getString"][96:"Data::getString"][212:"Data::getString"][226:"Data::getString"][69:")"][83:")"][199:")"][213:")"][68:".get()"][198:".get()"];

        Stream.of(data)[98:".map("][100:".map("][228:".map("][230:".map("][99:"Data::getData"][101:"Data::getData"][102:"Data::getData"][229:"Data::getData"][231:"Data::getData"][232:"Data::getData"][98:")"][100:")"][228:")"][230:")"][97:".filter(Objects::nonNull"][227:".filter(Objects::nonNull"][97:")"][227:")"];
        Stream.of(data)[106:".map("][110:".map("][112:".map("][118:".map("][122:".map("][124:".map("][236:".map("][240:".map("][242:".map("][248:".map("][252:".map("][254:".map("][107:"Data::getData"][111:"Data::getData"][113:"Data::getData"][114:"Data::getData"][119:"Data::getData"][123:"Data::getData"][125:"Data::getData"][126:"Data::getData"][237:"Data::getData"][241:"Data::getData"][243:"Data::getData"][244:"Data::getData"][249:"Data::getData"][253:"Data::getData"][255:"Data::getData"][256:"Data::getData"][106:")"][110:")"][112:")"][118:")"][122:")"][124:")"][236:")"][240:")"][242:")"][248:")"][252:")"][254:")"][105:" .filter(Objects::nonNull"][109:" .filter(Objects::nonNull"][117:" .filter(Objects::nonNull"][121:" .filter(Objects::nonNull"][235:" .filter(Objects::nonNull"][239:" .filter(Objects::nonNull"][247:" .filter(Objects::nonNull"][251:" .filter(Objects::nonNull"][105:")"][109:")"][117:")"][121:")"][235:")"][239:")"][247:")"][251:")"][104:".map("][116:".map("][234:".map("][246:".map("][108:"Data::getData"][115:"Data::getData"][120:"Data::getData"][127:"Data::getData"][238:"Data::getData"][245:"Data::getData"][250:"Data::getData"][257:"Data::getData"][104:")"][116:")"][234:")"][246:")"].findFirst()[103:".orElseThrow()"][233:".orElseThrow()"];
    }

    private Data orElseGetReturn() {[258:"\n        "][258:"return"][258:" "]null[258:";"][258:"\n    "]}

    private Optional<Data> ofNullable(Data data) {[259:"\n        "][259:"return"][259:" "]Optional.empty()[259:";"][259:"\n    "]}

    private static Data getOutsideData(Data data) {[260:"\n        "][260:"return"][260:" "]null[260:";"][260:"\n    "]}

    [261:"s"]tatic class Data {
        [262:"D"]ata data;
        boolean ok;

        String string;[262:"\n\n        "][262:"public Data(Data data) {\n            this.data = data;\n        }"]data"][265:"data"][266:"data"][267:"data"][263:";"][263:"\n        "]}[261:"\n\n        "][261:"public Data getData() {\n                return data;\n        }"]][268:" "]data[268:";"][268:"\n        "]}[261:"\n\n        "][261:"public boolean isOk() {\n                return ok;\n        }"]n"][269:" "]ok[269:";"][269:"\n        "]}[261:"\n\n        "][261:"public void setData(Data data) {\n                this.data = data;\n        }"]data"][272:"data"][273:"data"][274:"data"][270:";"][270:"\n        "]}[261:"\n\n        "][261:"public void setOk(boolean ok) {\n                this.ok = ok;\n        }"]:"ok"][277:"ok"][278:"ok"][279:"ok"][275:";"][275:"\n        "]}[261:"\n\n        "][261:"public String getString() {\n                return string;\n        }"]280:" "]string[280:";"][280:"\n        "]}[261:"\n\n        "][261:"public void setString(String string) {\n                this.string = string;\n        }"]ring"][283:"string"][284:"string"][285:"string"][281:";"][281:"\n        "]}

        public Data getDataMethod(Data data) {[286:"\n                "][286:"return"][286:" "]data[286:";"][286:"\n        "]}
    }
}
