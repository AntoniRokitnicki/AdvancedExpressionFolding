package data;

import java.util.*;
import java.util.stream.Collectors;

public class ConcatenationTestData {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        list[0:".add("][189:".add("]"one"[0:")"][189:")"];
        list[1:".remove("][190:".remove("]"one"[1:")"][190:")"];
        System.out.println(list.add("two"));
        System.out.println(list.remove("two"));
        List<String> singleton = Collections.emptyList();
        list[2:".addAll("][191:".addAll("]singleton[2:")"][191:")"];
        list[3:".removeAll("][192:".removeAll("]singleton[3:")"][192:")"];
        [4:"Collections.addAll("][193:"Collections.addAll("]list[4:", "][193:", "]args[4:")"][193:")"];
        Set<String> set = new HashSet<>();
        set[5:".add("][194:".add("]"three"[5:")"][194:")"];
        set[6:".remove("][195:".remove("]"three"[6:")"][195:")"];
        System.out.println(set);
        Set<String> copyOfSet = new HashSet<>();
        set[7:".addAll("][196:".addAll("]copyOfSet[7:")"][196:")"];
        System.out.println(copyOfSet);
        List<String> streamToList = [10:"Arrays.stream("][13:"Arrays.stream("][15:"Arrays.stream("][199:"Arrays.stream("][202:"Arrays.stream("][204:"Arrays.stream("]args[10:")"][13:")"][15:")"][199:")"][202:")"][204:")"][9:".map("][12:".map("][198:".map("][201:".map("][11:"String::toUpperCase"][14:"String::toUpperCase"][16:"String::toUpperCase"][200:"String::toUpperCase"][203:"String::toUpperCase"][205:"String::toUpperCase"][9:")"][12:")"][198:")"][201:")"][8:".collect(Collectors."][197:".collect(Collectors."]toList()[8:")"][197:")"];
        System.out.println(streamToList);
        streamToList = [19:"Arrays.stream("][22:"Arrays.stream("][24:"Arrays.stream("][208:"Arrays.stream("][211:"Arrays.stream("][213:"Arrays.stream("]args[19:")"][22:")"][24:")"][208:")"][211:")"][213:")"][18:".map("][21:".map("][207:".map("][210:".map("][20:"String::toUpperCase"][23:"String::toUpperCase"][25:"String::toUpperCase"][209:"String::toUpperCase"][212:"String::toUpperCase"][214:"String::toUpperCase"][18:")"][21:")"][207:")"][210:")"][17:".collect(Collectors."][206:".collect(Collectors."]toList()[17:")"][206:")"];
        System.out.println(streamToList);
        streamToList = [28:"Arrays.stream("][31:"Arrays.stream("][33:"Arrays.stream("][217:"Arrays.stream("][220:"Arrays.stream("][222:"Arrays.stream("]args[28:")"][31:")"][33:")"][217:")"][220:")"][222:")"][27:".map("][30:".map("][216:".map("][219:".map("][29:"String::toUpperCase"][32:"String::toUpperCase"][34:"String::toUpperCase"][218:"String::toUpperCase"][221:"String::toUpperCase"][223:"String::toUpperCase"][27:")"][30:")"][216:")"][219:")"][26:".collect(Collectors."][215:".collect(Collectors."]toList()[26:")"][215:")"];
        System.out.println(streamToList);
        streamToList = [37:"Arrays.stream("][40:"Arrays.stream("][42:"Arrays.stream("][226:"Arrays.stream("][229:"Arrays.stream("][231:"Arrays.stream("]args[37:")"][40:")"][42:")"][226:")"][229:")"][231:")"][36:".map("][39:".map("][225:".map("][228:".map("][38:"String::toUpperCase"][41:"String::toUpperCase"][43:"String::toUpperCase"][227:"String::toUpperCase"][230:"String::toUpperCase"][232:"String::toUpperCase"][36:")"][39:")"][225:")"][228:")"][35:".collect(Collectors."][224:".collect(Collectors."]toList()[35:")"][224:")"];
        System.out.println(streamToList);
        streamToList = [46:"Arrays.stream("][49:"Arrays.stream("][51:"Arrays.stream("][235:"Arrays.stream("][238:"Arrays.stream("][240:"Arrays.stream("]args[46:")"][49:")"][51:")"][235:")"][238:")"][240:")"][45:".map("][48:".map("][234:".map("][237:".map("][47:"String::toUpperCase"][50:"String::toUpperCase"][52:"String::toUpperCase"][236:"String::toUpperCase"][239:"String::toUpperCase"][241:"String::toUpperCase"][45:")"][48:")"][234:")"][237:")"][44:".collect(Collectors."][233:".collect(Collectors."]toList()[44:")"][233:")"];
        System.out.println(streamToList);
        streamToList = [55:"Arrays.stream("][58:"Arrays.stream("][60:"Arrays.stream("][244:"Arrays.stream("][247:"Arrays.stream("][249:"Arrays.stream("]args[55:")"][58:")"][60:")"][244:")"][247:")"][249:")"][54:".map("][57:".map("][243:".map("][246:".map("][56:"String::toUpperCase"][59:"String::toUpperCase"][61:"String::toUpperCase"][245:"String::toUpperCase"][248:"String::toUpperCase"][250:"String::toUpperCase"][54:")"][57:")"][243:")"][246:")"][53:".collect(Collectors."][242:".collect(Collectors."]toList()[53:")"][242:")"];
        System.out.println(streamToList);
        streamToList = [64:"Arrays.stream("][67:"Arrays.stream("][69:"Arrays.stream("][253:"Arrays.stream("][256:"Arrays.stream("][258:"Arrays.stream("]args[64:")"][67:")"][69:")"][253:")"][256:")"][258:")"][63:".map("][66:".map("][252:".map("][255:".map("][65:"String::toUpperCase"][68:"String::toUpperCase"][70:"String::toUpperCase"][254:"String::toUpperCase"][257:"String::toUpperCase"][259:"String::toUpperCase"][63:")"][66:")"][252:")"][255:")"][62:".collect(Collectors."][251:".collect(Collectors."]toList()[62:")"][251:")"];
        System.out.println(streamToList);
        streamToList = [73:"Arrays.stream("][76:"Arrays.stream("][78:"Arrays.stream("][262:"Arrays.stream("][265:"Arrays.stream("][267:"Arrays.stream("]args[73:")"][76:")"][78:")"][262:")"][265:")"][267:")"][72:".map("][75:".map("][261:".map("][264:".map("][74:"String::toUpperCase"][77:"String::toUpperCase"][79:"String::toUpperCase"][263:"String::toUpperCase"][266:"String::toUpperCase"][268:"String::toUpperCase"][72:")"][75:")"][261:")"][264:")"][71:".collect(Collectors."][260:".collect(Collectors."]toList()[71:")"][260:")"];
        System.out.println(streamToList);
        streamToList = [82:"Arrays.stream("][85:"Arrays.stream("][87:"Arrays.stream("][271:"Arrays.stream("][274:"Arrays.stream("][276:"Arrays.stream("]args[82:")"][85:")"][87:")"][271:")"][274:")"][276:")"][81:".map("][84:".map("][270:".map("][273:".map("][83:"String::toUpperCase"][86:"String::toUpperCase"][88:"String::toUpperCase"][272:"String::toUpperCase"][275:"String::toUpperCase"][277:"String::toUpperCase"][81:")"][84:")"][270:")"][273:")"][80:".collect(Collectors."][269:".collect(Collectors."]toList()[80:")"][269:")"];
        System.out.println(streamToList);
        streamToList = [91:"Arrays.stream("][94:"Arrays.stream("][96:"Arrays.stream("][280:"Arrays.stream("][283:"Arrays.stream("][285:"Arrays.stream("]args[91:")"][94:")"][96:")"][280:")"][283:")"][285:")"][90:".map("][93:".map("][279:".map("][282:".map("][92:"String::toUpperCase"][95:"String::toUpperCase"][97:"String::toUpperCase"][281:"String::toUpperCase"][284:"String::toUpperCase"][286:"String::toUpperCase"][90:")"][93:")"][279:")"][282:")"][89:".collect(Collectors."][278:".collect(Collectors."]toList()[89:")"][278:")"];
        System.out.println(streamToList);

        streamToList = list[100:".stream()."][103:".stream()."][105:".stream()."][289:".stream()."][292:".stream()."][294:".stream()."]99:".map("][102:".map("][288:".map("][291:".map("][101:"String::toUpperCase"][104:"String::toUpperCase"][106:"String::toUpperCase"][290:"String::toUpperCase"][293:"String::toUpperCase"][295:"String::toUpperCase"][99:")"][102:")"][288:")"][291:")"][98:".collect(Collectors."][287:".collect(Collectors."]toList()[98:")"][287:")"];
        System.out.println(streamToList);
        streamToList = list[109:".stream()."][112:".stream()."][114:".stream()."][298:".stream()."][301:".stream()."][303:".stream()."]108:".map("][111:".map("][297:".map("][300:".map("][110:"String::toUpperCase"][113:"String::toUpperCase"][115:"String::toUpperCase"][299:"String::toUpperCase"][302:"String::toUpperCase"][304:"String::toUpperCase"][108:")"][111:")"][297:")"][300:")"][107:".collect(Collectors."][296:".collect(Collectors."]toList()[107:")"][296:")"];
        System.out.println(streamToList);
        streamToList = list[118:".stream()."][121:".stream()."][123:".stream()."][307:".stream()."][310:".stream()."][312:".stream()."]117:".map("][120:".map("][306:".map("][309:".map("][119:"String::toUpperCase"][122:"String::toUpperCase"][124:"String::toUpperCase"][308:"String::toUpperCase"][311:"String::toUpperCase"][313:"String::toUpperCase"][117:")"][120:")"][306:")"][309:")"][116:".collect(Collectors."][305:".collect(Collectors."]toList()[116:")"][305:")"];
        System.out.println(streamToList);
        streamToList = list[127:".stream()."][130:".stream()."][132:".stream()."][316:".stream()."][319:".stream()."][321:".stream()."]126:".map("][129:".map("][315:".map("][318:".map("][128:"String::toUpperCase"][131:"String::toUpperCase"][133:"String::toUpperCase"][317:"String::toUpperCase"][320:"String::toUpperCase"][322:"String::toUpperCase"][126:")"][129:")"][315:")"][318:")"][125:".collect(Collectors."][314:".collect(Collectors."]toList()[125:")"][314:")"];
        System.out.println(streamToList);
        streamToList = list[136:".stream()."][139:".stream()."][141:".stream()."][325:".stream()."][328:".stream()."][330:".stream()."]135:".map("][138:".map("][324:".map("][327:".map("][137:"String::toUpperCase"][140:"String::toUpperCase"][142:"String::toUpperCase"][326:"String::toUpperCase"][329:"String::toUpperCase"][331:"String::toUpperCase"][135:")"][138:")"][324:")"][327:")"][134:".collect(Collectors."][323:".collect(Collectors."]toList()[134:")"][323:")"];
        System.out.println(streamToList);
        streamToList = list[145:".stream()."][148:".stream()."][150:".stream()."][334:".stream()."][337:".stream()."][339:".stream()."]144:".map("][147:".map("][333:".map("][336:".map("][146:"String::toUpperCase"][149:"String::toUpperCase"][151:"String::toUpperCase"][335:"String::toUpperCase"][338:"String::toUpperCase"][340:"String::toUpperCase"][144:")"][147:")"][333:")"][336:")"][143:".collect(Collectors."][332:".collect(Collectors."]toList()[143:")"][332:")"];
        System.out.println(streamToList);
        streamToList = list[154:".stream()."][157:".stream()."][159:".stream()."][343:".stream()."][346:".stream()."][348:".stream()."]153:".map("][156:".map("][342:".map("][345:".map("][155:"String::toUpperCase"][158:"String::toUpperCase"][160:"String::toUpperCase"][344:"String::toUpperCase"][347:"String::toUpperCase"][349:"String::toUpperCase"][153:")"][156:")"][342:")"][345:")"][152:".collect(Collectors."][341:".collect(Collectors."]toList()[152:")"][341:")"];
        System.out.println(streamToList);
        streamToList = list[163:".stream()."][166:".stream()."][168:".stream()."][352:".stream()."][355:".stream()."][357:".stream()."]162:".map("][165:".map("][351:".map("][354:".map("][164:"String::toUpperCase"][167:"String::toUpperCase"][169:"String::toUpperCase"][353:"String::toUpperCase"][356:"String::toUpperCase"][358:"String::toUpperCase"][162:")"][165:")"][351:")"][354:")"][161:".collect(Collectors."][350:".collect(Collectors."]toList()[161:")"][350:")"];
        System.out.println(streamToList);
        streamToList = list[172:".stream()."][175:".stream()."][177:".stream()."][361:".stream()."][364:".stream()."][366:".stream()."]171:".map("][174:".map("][360:".map("][363:".map("][173:"String::toUpperCase"][176:"String::toUpperCase"][178:"String::toUpperCase"][362:"String::toUpperCase"][365:"String::toUpperCase"][367:"String::toUpperCase"][171:")"][174:")"][360:")"][363:")"][170:".collect(Collectors."][359:".collect(Collectors."]toList()[170:")"][359:")"];
        System.out.println(streamToList);
        streamToList = list[181:".stream()."][184:".stream()."][186:".stream()."][370:".stream()."][373:".stream()."][375:".stream()."]180:".map("][183:".map("][369:".map("][372:".map("][182:"String::toUpperCase"][185:"String::toUpperCase"][187:"String::toUpperCase"][371:"String::toUpperCase"][374:"String::toUpperCase"][376:"String::toUpperCase"][180:")"][183:")"][369:")"][372:")"][179:".collect(Collectors."][368:".collect(Collectors."]toList()[179:")"][368:")"];
        System.out.println(streamToList);

        long count = streamToList[188:".stream()."][377:".stream()."]distinct().count();
        System.out.println(count);
    }
}
