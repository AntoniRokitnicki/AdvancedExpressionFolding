package data;

import java.util.*;
import java.util.stream.Collectors;

public class ConcatenationTestData {
    public static void main(String[] args) {
        [0:"List<String>"][220:"List<String>"] list = Arrays.asList(args);
        list[1:".add("][221:".add("]"one"[1:")"][221:")"];
        list[2:".remove("][222:".remove("]"one"[2:")"][222:")"];
        [3:"System.out."][223:"System.out."]println(list.add("two"));
        [4:"System.out."][224:"System.out."]println(list.remove("two"));
        [5:"List<String>"][225:"List<String>"] singleton = Collections.emptyList();
        list[6:".addAll("][226:".addAll("]singleton[6:")"][226:")"];
        list[7:".removeAll("][227:".removeAll("]singleton[7:")"][227:")"];
        [8:"Collections.addAll("][228:"Collections.addAll("]list[8:", "][228:", "]args[8:")"][228:")"];
        [9:"Set<String>"][229:"Set<String>"] set = new HashSet<>();
        set[10:".add("][230:".add("]"three"[10:")"][230:")"];
        set[11:".remove("][231:".remove("]"three"[11:")"][231:")"];
        [12:"System.out."][232:"System.out."]println(set);
        [13:"Set<String>"][233:"Set<String>"] copyOfSet = new HashSet<>();
        set[14:".addAll("][234:".addAll("]copyOfSet[14:")"][234:")"];
        [15:"System.out."][235:"System.out."]println(copyOfSet);
        [16:"List<String>"][236:"List<String>"] streamToList = [19:"Arrays.stream("][22:"Arrays.stream("][24:"Arrays.stream("][239:"Arrays.stream("][242:"Arrays.stream("][244:"Arrays.stream("]args[19:")"][22:")"][24:")"][239:")"][242:")"][244:")"][18:".map("][21:".map("][238:".map("][241:".map("][20:"String::toUpperCase"][23:"String::toUpperCase"][25:"String::toUpperCase"][240:"String::toUpperCase"][243:"String::toUpperCase"][245:"String::toUpperCase"][18:")"][21:")"][238:")"][241:")"][17:".collect(Collectors."][237:".collect(Collectors."]toList()[17:")"][237:")"];
        [26:"System.out."][246:"System.out."]println(streamToList);
        streamToList = [29:"Arrays.stream("][32:"Arrays.stream("][34:"Arrays.stream("][249:"Arrays.stream("][252:"Arrays.stream("][254:"Arrays.stream("]args[29:")"][32:")"][34:")"][249:")"][252:")"][254:")"][28:".map("][31:".map("][248:".map("][251:".map("][30:"String::toUpperCase"][33:"String::toUpperCase"][35:"String::toUpperCase"][250:"String::toUpperCase"][253:"String::toUpperCase"][255:"String::toUpperCase"][28:")"][31:")"][248:")"][251:")"][27:".collect(Collectors."][247:".collect(Collectors."]toList()[27:")"][247:")"];
        [36:"System.out."][256:"System.out."]println(streamToList);
        streamToList = [39:"Arrays.stream("][42:"Arrays.stream("][44:"Arrays.stream("][259:"Arrays.stream("][262:"Arrays.stream("][264:"Arrays.stream("]args[39:")"][42:")"][44:")"][259:")"][262:")"][264:")"][38:".map("][41:".map("][258:".map("][261:".map("][40:"String::toUpperCase"][43:"String::toUpperCase"][45:"String::toUpperCase"][260:"String::toUpperCase"][263:"String::toUpperCase"][265:"String::toUpperCase"][38:")"][41:")"][258:")"][261:")"][37:".collect(Collectors."][257:".collect(Collectors."]toList()[37:")"][257:")"];
        [46:"System.out."][266:"System.out."]println(streamToList);
        streamToList = [49:"Arrays.stream("][52:"Arrays.stream("][54:"Arrays.stream("][269:"Arrays.stream("][272:"Arrays.stream("][274:"Arrays.stream("]args[49:")"][52:")"][54:")"][269:")"][272:")"][274:")"][48:".map("][51:".map("][268:".map("][271:".map("][50:"String::toUpperCase"][53:"String::toUpperCase"][55:"String::toUpperCase"][270:"String::toUpperCase"][273:"String::toUpperCase"][275:"String::toUpperCase"][48:")"][51:")"][268:")"][271:")"][47:".collect(Collectors."][267:".collect(Collectors."]toList()[47:")"][267:")"];
        [56:"System.out."][276:"System.out."]println(streamToList);
        streamToList = [59:"Arrays.stream("][62:"Arrays.stream("][64:"Arrays.stream("][279:"Arrays.stream("][282:"Arrays.stream("][284:"Arrays.stream("]args[59:")"][62:")"][64:")"][279:")"][282:")"][284:")"][58:".map("][61:".map("][278:".map("][281:".map("][60:"String::toUpperCase"][63:"String::toUpperCase"][65:"String::toUpperCase"][280:"String::toUpperCase"][283:"String::toUpperCase"][285:"String::toUpperCase"][58:")"][61:")"][278:")"][281:")"][57:".collect(Collectors."][277:".collect(Collectors."]toList()[57:")"][277:")"];
        [66:"System.out."][286:"System.out."]println(streamToList);
        streamToList = [69:"Arrays.stream("][72:"Arrays.stream("][74:"Arrays.stream("][289:"Arrays.stream("][292:"Arrays.stream("][294:"Arrays.stream("]args[69:")"][72:")"][74:")"][289:")"][292:")"][294:")"][68:".map("][71:".map("][288:".map("][291:".map("][70:"String::toUpperCase"][73:"String::toUpperCase"][75:"String::toUpperCase"][290:"String::toUpperCase"][293:"String::toUpperCase"][295:"String::toUpperCase"][68:")"][71:")"][288:")"][291:")"][67:".collect(Collectors."][287:".collect(Collectors."]toList()[67:")"][287:")"];
        [76:"System.out."][296:"System.out."]println(streamToList);
        streamToList = [79:"Arrays.stream("][82:"Arrays.stream("][84:"Arrays.stream("][299:"Arrays.stream("][302:"Arrays.stream("][304:"Arrays.stream("]args[79:")"][82:")"][84:")"][299:")"][302:")"][304:")"][78:".map("][81:".map("][298:".map("][301:".map("][80:"String::toUpperCase"][83:"String::toUpperCase"][85:"String::toUpperCase"][300:"String::toUpperCase"][303:"String::toUpperCase"][305:"String::toUpperCase"][78:")"][81:")"][298:")"][301:")"][77:".collect(Collectors."][297:".collect(Collectors."]toList()[77:")"][297:")"];
        [86:"System.out."][306:"System.out."]println(streamToList);
        streamToList = [89:"Arrays.stream("][92:"Arrays.stream("][94:"Arrays.stream("][309:"Arrays.stream("][312:"Arrays.stream("][314:"Arrays.stream("]args[89:")"][92:")"][94:")"][309:")"][312:")"][314:")"][88:".map("][91:".map("][308:".map("][311:".map("][90:"String::toUpperCase"][93:"String::toUpperCase"][95:"String::toUpperCase"][310:"String::toUpperCase"][313:"String::toUpperCase"][315:"String::toUpperCase"][88:")"][91:")"][308:")"][311:")"][87:".collect(Collectors."][307:".collect(Collectors."]toList()[87:")"][307:")"];
        [96:"System.out."][316:"System.out."]println(streamToList);
        streamToList = [99:"Arrays.stream("][102:"Arrays.stream("][104:"Arrays.stream("][319:"Arrays.stream("][322:"Arrays.stream("][324:"Arrays.stream("]args[99:")"][102:")"][104:")"][319:")"][322:")"][324:")"][98:".map("][101:".map("][318:".map("][321:".map("][100:"String::toUpperCase"][103:"String::toUpperCase"][105:"String::toUpperCase"][320:"String::toUpperCase"][323:"String::toUpperCase"][325:"String::toUpperCase"][98:")"][101:")"][318:")"][321:")"][97:".collect(Collectors."][317:".collect(Collectors."]toList()[97:")"][317:")"];
        [106:"System.out."][326:"System.out."]println(streamToList);
        streamToList = [109:"Arrays.stream("][112:"Arrays.stream("][114:"Arrays.stream("][329:"Arrays.stream("][332:"Arrays.stream("][334:"Arrays.stream("]args[109:")"][112:")"][114:")"][329:")"][332:")"][334:")"][108:".map("][111:".map("][328:".map("][331:".map("][110:"String::toUpperCase"][113:"String::toUpperCase"][115:"String::toUpperCase"][330:"String::toUpperCase"][333:"String::toUpperCase"][335:"String::toUpperCase"][108:")"][111:")"][328:")"][331:")"][107:".collect(Collectors."][327:".collect(Collectors."]toList()[107:")"][327:")"];
        [116:"System.out."][336:"System.out."]println(streamToList);

        streamToList = list[119:".stream()."][122:".stream()."][124:".stream()."][339:".stream()."][342:".stream()."][344:".stream()."]118:".map("][121:".map("][338:".map("][341:".map("][120:"String::toUpperCase"][123:"String::toUpperCase"][125:"String::toUpperCase"][340:"String::toUpperCase"][343:"String::toUpperCase"][345:"String::toUpperCase"][118:")"][121:")"][338:")"][341:")"][117:".collect(Collectors."][337:".collect(Collectors."]toList()[117:")"][337:")"];
        [126:"System.out."][346:"System.out."]println(streamToList);
        streamToList = list[129:".stream()."][132:".stream()."][134:".stream()."][349:".stream()."][352:".stream()."][354:".stream()."]128:".map("][131:".map("][348:".map("][351:".map("][130:"String::toUpperCase"][133:"String::toUpperCase"][135:"String::toUpperCase"][350:"String::toUpperCase"][353:"String::toUpperCase"][355:"String::toUpperCase"][128:")"][131:")"][348:")"][351:")"][127:".collect(Collectors."][347:".collect(Collectors."]toList()[127:")"][347:")"];
        [136:"System.out."][356:"System.out."]println(streamToList);
        streamToList = list[139:".stream()."][142:".stream()."][144:".stream()."][359:".stream()."][362:".stream()."][364:".stream()."]138:".map("][141:".map("][358:".map("][361:".map("][140:"String::toUpperCase"][143:"String::toUpperCase"][145:"String::toUpperCase"][360:"String::toUpperCase"][363:"String::toUpperCase"][365:"String::toUpperCase"][138:")"][141:")"][358:")"][361:")"][137:".collect(Collectors."][357:".collect(Collectors."]toList()[137:")"][357:")"];
        [146:"System.out."][366:"System.out."]println(streamToList);
        streamToList = list[149:".stream()."][152:".stream()."][154:".stream()."][369:".stream()."][372:".stream()."][374:".stream()."]148:".map("][151:".map("][368:".map("][371:".map("][150:"String::toUpperCase"][153:"String::toUpperCase"][155:"String::toUpperCase"][370:"String::toUpperCase"][373:"String::toUpperCase"][375:"String::toUpperCase"][148:")"][151:")"][368:")"][371:")"][147:".collect(Collectors."][367:".collect(Collectors."]toList()[147:")"][367:")"];
        [156:"System.out."][376:"System.out."]println(streamToList);
        streamToList = list[159:".stream()."][162:".stream()."][164:".stream()."][379:".stream()."][382:".stream()."][384:".stream()."]158:".map("][161:".map("][378:".map("][381:".map("][160:"String::toUpperCase"][163:"String::toUpperCase"][165:"String::toUpperCase"][380:"String::toUpperCase"][383:"String::toUpperCase"][385:"String::toUpperCase"][158:")"][161:")"][378:")"][381:")"][157:".collect(Collectors."][377:".collect(Collectors."]toList()[157:")"][377:")"];
        [166:"System.out."][386:"System.out."]println(streamToList);
        streamToList = list[169:".stream()."][172:".stream()."][174:".stream()."][389:".stream()."][392:".stream()."][394:".stream()."]168:".map("][171:".map("][388:".map("][391:".map("][170:"String::toUpperCase"][173:"String::toUpperCase"][175:"String::toUpperCase"][390:"String::toUpperCase"][393:"String::toUpperCase"][395:"String::toUpperCase"][168:")"][171:")"][388:")"][391:")"][167:".collect(Collectors."][387:".collect(Collectors."]toList()[167:")"][387:")"];
        [176:"System.out."][396:"System.out."]println(streamToList);
        streamToList = list[179:".stream()."][182:".stream()."][184:".stream()."][399:".stream()."][402:".stream()."][404:".stream()."]178:".map("][181:".map("][398:".map("][401:".map("][180:"String::toUpperCase"][183:"String::toUpperCase"][185:"String::toUpperCase"][400:"String::toUpperCase"][403:"String::toUpperCase"][405:"String::toUpperCase"][178:")"][181:")"][398:")"][401:")"][177:".collect(Collectors."][397:".collect(Collectors."]toList()[177:")"][397:")"];
        [186:"System.out."][406:"System.out."]println(streamToList);
        streamToList = list[189:".stream()."][192:".stream()."][194:".stream()."][409:".stream()."][412:".stream()."][414:".stream()."]188:".map("][191:".map("][408:".map("][411:".map("][190:"String::toUpperCase"][193:"String::toUpperCase"][195:"String::toUpperCase"][410:"String::toUpperCase"][413:"String::toUpperCase"][415:"String::toUpperCase"][188:")"][191:")"][408:")"][411:")"][187:".collect(Collectors."][407:".collect(Collectors."]toList()[187:")"][407:")"];
        [196:"System.out."][416:"System.out."]println(streamToList);
        streamToList = list[199:".stream()."][202:".stream()."][204:".stream()."][419:".stream()."][422:".stream()."][424:".stream()."]198:".map("][201:".map("][418:".map("][421:".map("][200:"String::toUpperCase"][203:"String::toUpperCase"][205:"String::toUpperCase"][420:"String::toUpperCase"][423:"String::toUpperCase"][425:"String::toUpperCase"][198:")"][201:")"][418:")"][421:")"][197:".collect(Collectors."][417:".collect(Collectors."]toList()[197:")"][417:")"];
        [206:"System.out."][426:"System.out."]println(streamToList);
        streamToList = list[209:".stream()."][212:".stream()."][214:".stream()."][429:".stream()."][432:".stream()."][434:".stream()."]208:".map("][211:".map("][428:".map("][431:".map("][210:"String::toUpperCase"][213:"String::toUpperCase"][215:"String::toUpperCase"][430:"String::toUpperCase"][433:"String::toUpperCase"][435:"String::toUpperCase"][208:")"][211:")"][428:")"][431:")"][207:".collect(Collectors."][427:".collect(Collectors."]toList()[207:")"][427:")"];
        [216:"System.out."][436:"System.out."]println(streamToList);

        [217:"long"][437:"long"] count = streamToList[218:".stream()."][438:".stream()."]distinct().count();
        [219:"System.out."][439:"System.out."]println(count);
    }
}
