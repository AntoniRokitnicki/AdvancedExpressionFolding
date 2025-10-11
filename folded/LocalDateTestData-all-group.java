package data[0:";"]

import java.sql.Timestamp[1:";"]
import java.time.LocalDate[2:";"]
import java.util.Calendar[3:";"]

@SuppressWarnings("ALL")
public class LocalDateTestData {
    public void main(String[] args) {
        [4:"LocalDate"][107:"LocalDate"] d1 = [5:"LocalDate.of("][108:"LocalDate.of("]2018[5:", "][108:", "]12[5:", "][108:", "]10[5:")"][108:")"][6:";"][109:";"]
        [7:"LocalDate"][110:"LocalDate"] d2 = [8:"LocalDate.of("][111:"LocalDate.of("]2018[8:", "][111:", "]12[8:", "][111:", "]10[8:")"][111:")"][9:";"][112:";"]
        [10:"boolean"][113:"boolean"] isBefore = d1[11:".isBefore("][114:".isBefore("]d2[11:")"][114:")"][12:";"][115:";"]
        [13:"boolean"][116:"boolean"] isAfter = d1[14:".isAfter("][117:".isAfter("]d2[14:")"][117:")"][15:";"][118:";"]
        [16:"boolean"][119:"boolean"] d2SmallerOrEqualD1 = [17:"!"][120:"!"]d1[17:".isBefore("][120:".isBefore("]d2[17:")"][120:")"][18:";"][121:";"][19:";"][122:";"]
        [20:"boolean"][123:"boolean"] d1SmallerOrEqualD2 = [21:"!"][124:"!"]d1[21:".isAfter("][124:".isAfter("]d2[21:")"][124:")"][22:";"][125:";"]

        [23:"var"][126:"var"] date1 = java.time.LocalDate.now()[24:";"][127:";"]
        [25:"var"][128:"var"] date2 = java.time.LocalDate.now()[26:";"][129:";"]
        if [27:"("][130:"("]date1[131:".isBefore("]date2[131:")"] | date1[132:".isAfter("]date2[132:")"] | [133:"!"]date1[133:".isBefore("]date2[133:")"] | [134:"!"]date1[134:".isAfter("]date2[134:")"][27:")"][130:")"] [135:"{"]
            date1 = date2[136:";"]
[135:"        }\n"]
        [28:"var"][137:"var"] dateTime1 = java.time.LocalDateTime.now()[29:";"][138:";"]
        [30:"var"][139:"var"] dateTime2 = java.time.LocalDateTime.now()[31:";"][140:";"]
        if [32:"("][141:"("]dateTime1[142:".isBefore("]dateTime2[142:")"] | dateTime1[143:".isAfter("]dateTime2[143:")"] | [144:"!"]dateTime1[144:".isBefore("]dateTime2[144:")"] | [145:"!"]dateTime1[145:".isAfter("]dateTime2[145:")"][32:")"][141:")"] [146:"{"]
            dateTime1 = dateTime2[147:";"]
[146:"        }\n"]
        [33:"var"][148:"var"] time1 = java.time.LocalTime.now()[34:";"][149:";"]
        [35:"var"][150:"var"] time2 = java.time.LocalTime.now()[36:";"][151:";"]
        if [37:"("][152:"("]time1[153:".isBefore("]time2[153:")"] | time1[154:".isAfter("]time2[154:")"] | [155:"!"]time1[155:".isBefore("]time2[155:")"] | [156:"!"]time1[156:".isAfter("]time2[156:")"][37:")"][152:")"] [157:"{"]
            time1 = time2[158:";"]
[157:"        }\n"]
        [38:"var"][159:"var"] offsetTime1 = java.time.OffsetTime.now()[39:";"][160:";"]
        [40:"var"][161:"var"] offsetTime2 = java.time.OffsetTime.now()[41:";"][162:";"]
        if [42:"("][163:"("]offsetTime1[164:".isBefore("]offsetTime2[164:")"] | offsetTime1[165:".isAfter("]offsetTime2[165:")"] | [166:"!"]offsetTime1[166:".isBefore("]offsetTime2[166:")"] | [167:"!"]offsetTime1[167:".isAfter("]offsetTime2[167:")"][42:")"][163:")"] [168:"{"]
            offsetTime1 = offsetTime2[169:";"]
[168:"        }\n"]
        [43:"var"][170:"var"] offsetDateTime1 = java.time.OffsetDateTime.now()[44:";"][171:";"]
        [45:"var"][172:"var"] offsetDateTime2 = java.time.OffsetDateTime.now()[46:";"][173:";"]
        if [47:"("][174:"("]offsetDateTime1[175:".isBefore("]offsetDateTime2[175:")"] | offsetDateTime1[176:".isAfter("]offsetDateTime2[176:")"] | [177:"!"]offsetDateTime1[177:".isBefore("]offsetDateTime2[177:")"] | [178:"!"]offsetDateTime1[178:".isAfter("]offsetDateTime2[178:")"][47:")"][174:")"] [179:"{"]
            offsetDateTime1 = offsetDateTime2[180:";"]
[179:"        }\n"]
        [48:"var"][181:"var"] zonedDateTime1 = java.time.ZonedDateTime.now()[49:";"][182:";"]
        [50:"var"][183:"var"] zonedDateTime2 = java.time.ZonedDateTime.now()[51:";"][184:";"]
        if [52:"("][185:"("]zonedDateTime1[186:".isBefore("]zonedDateTime2[186:")"] | zonedDateTime1[187:".isAfter("]zonedDateTime2[187:")"] | [188:"!"]zonedDateTime1[188:".isBefore("]zonedDateTime2[188:")"] | [189:"!"]zonedDateTime1[189:".isAfter("]zonedDateTime2[189:")"][52:")"][185:")"] [190:"{"]
            zonedDateTime1 = zonedDateTime2[191:";"]
[190:"        }\n"]
        [53:"var"][192:"var"] hijrahDate1 = java.time.chrono.HijrahDate.now()[54:";"][193:";"]
        [55:"var"][194:"var"] hijrahDate2 = java.time.chrono.HijrahDate.now()[56:";"][195:";"]
        if [57:"("][196:"("]hijrahDate1[197:".isBefore("]hijrahDate2[197:")"] | hijrahDate1[198:".isAfter("]hijrahDate2[198:")"] | [199:"!"]hijrahDate1[199:".isBefore("]hijrahDate2[199:")"] | [200:"!"]hijrahDate1[200:".isAfter("]hijrahDate2[200:")"][57:")"][196:")"] [201:"{"]
            hijrahDate1 = hijrahDate2[202:";"]
[201:"        }\n"]
        [58:"var"][203:"var"] japaneseDate1 = java.time.chrono.JapaneseDate.now()[59:";"][204:";"]
        [60:"var"][205:"var"] japaneseDate2 = java.time.chrono.JapaneseDate.now()[61:";"][206:";"]
        if [62:"("][207:"("]japaneseDate1[208:".isBefore("]japaneseDate2[208:")"] | japaneseDate1[209:".isAfter("]japaneseDate2[209:")"] | [210:"!"]japaneseDate1[210:".isBefore("]japaneseDate2[210:")"] | [211:"!"]japaneseDate1[211:".isAfter("]japaneseDate2[211:")"][62:")"][207:")"] [212:"{"]
            japaneseDate1 = japaneseDate2[213:";"]
[212:"        }\n"]
        [63:"var"][214:"var"] minguoDate1 = java.time.chrono.MinguoDate.now()[64:";"][215:";"]
        [65:"var"][216:"var"] minguoDate2 = java.time.chrono.MinguoDate.now()[66:";"][217:";"]
        if [67:"("][218:"("]minguoDate1[219:".isBefore("]minguoDate2[219:")"] | minguoDate1[220:".isAfter("]minguoDate2[220:")"] | [221:"!"]minguoDate1[221:".isBefore("]minguoDate2[221:")"] | [222:"!"]minguoDate1[222:".isAfter("]minguoDate2[222:")"][67:")"][218:")"] [223:"{"]
            minguoDate1 = minguoDate2[224:";"]
[223:"        }\n"]
        [68:"var"][225:"var"] thaiBuddhistDate1 = java.time.chrono.ThaiBuddhistDate.now()[69:";"][226:";"]
        [70:"var"][227:"var"] thaiBuddhistDate2 = java.time.chrono.ThaiBuddhistDate.now()[71:";"][228:";"]
        if [72:"("][229:"("]thaiBuddhistDate1[230:".isBefore("]thaiBuddhistDate2[230:")"] | thaiBuddhistDate1[231:".isAfter("]thaiBuddhistDate2[231:")"] | [232:"!"]thaiBuddhistDate1[232:".isBefore("]thaiBuddhistDate2[232:")"] | [233:"!"]thaiBuddhistDate1[233:".isAfter("]thaiBuddhistDate2[233:")"][72:")"][229:")"] [234:"{"]
            thaiBuddhistDate1 = thaiBuddhistDate2[235:";"]
[234:"        }\n"]
        [73:"var"][236:"var"] utilDate1 = new java.util.Date()[74:";"][237:";"]
        [75:"var"][238:"var"] utilDate2 = new java.util.Date()[76:";"][239:";"]
        if [77:"("][240:"("]utilDate1[241:".before("]utilDate2[241:")"] | utilDate1[242:".after("]utilDate2[242:")"] | [243:"!"]utilDate1[243:".before("]utilDate2[243:")"] | [244:"!"]utilDate1[244:".after("]utilDate2[244:")"][77:")"][240:")"] [245:"{"]
            utilDate1 = utilDate2[246:";"]
[245:"        }\n"]
        [78:"long"][247:"long"] currentTime = System.currentTimeMillis()[79:";"][248:";"]
        [80:"var"][249:"var"] sqlDate1 = new java.sql.Date(currentTime)[81:";"][250:";"]
        [82:"var"][251:"var"] sqlDate2 = new java.sql.Date(currentTime)[83:";"][252:";"]
        if [84:"("][253:"("]sqlDate1[254:".before("]sqlDate2[254:")"] | sqlDate1[255:".after("]sqlDate2[255:")"] | [256:"!"]sqlDate1[256:".before("]sqlDate2[256:")"] | [257:"!"]sqlDate1[257:".after("]sqlDate2[257:")"][84:")"][253:")"] [258:"{"]
            sqlDate1 = sqlDate2[259:";"]
[258:"        }\n"]
        [85:"var"][260:"var"] timestamp1 = new Timestamp(System.currentTimeMillis())[86:";"][261:";"]
        [87:"var"][262:"var"] timestamp2 = new Timestamp(System.currentTimeMillis())[88:";"][263:";"]
        if [89:"("][264:"("]timestamp1[265:".before("]timestamp2[265:")"] | timestamp1[266:".after("]timestamp2[266:")"] | [267:"!"]timestamp1[267:".before("]timestamp2[267:")"] | [268:"!"]timestamp1[268:".after("]timestamp2[268:")"][89:")"][264:")"] [269:"{"]
            timestamp1 = timestamp2[270:";"]
[269:"        }\n"]
        [90:"var"][271:"var"] cal1 = Calendar.[91:"getInstance()"][272:"getInstance()"][92:";"][273:";"]
        [93:"var"][274:"var"] cal2 = Calendar.[94:"getInstance()"][275:"getInstance()"][95:";"][276:";"]
        if [96:"("][277:"("]cal1[278:".before("]cal2[278:")"] | cal1[279:".after("]cal2[279:")"] | [280:"!"]cal1[280:".before("]cal2[280:")"] | [281:"!"]cal1[281:".after("]cal2[281:")"][96:")"][277:")"] [282:"{"]
            cal1 = cal2[283:";"]
[282:"        }\n"]
        [97:"var"][284:"var"] customObj1 = new CustomClass()[98:";"][285:";"]
        [99:"var"][286:"var"] customObj2 = new CustomClass()[100:";"][287:";"]
        if [101:"("][288:"("]customObj1[289:".before("]customObj2[289:")"] | customObj1[290:".after("]customObj2[290:")"] | [291:"!"]customObj1[291:".before("]customObj2[291:")"] | [292:"!"]customObj1[292:".after("]customObj2[292:")"][101:")"][288:")"] [293:"{"]
            customObj1 = customObj2[294:";"]
[293:"        }\n"]
        [102:"var"][295:"var"] customObj2_1 = new CustomClass2()[103:";"][296:";"]
        [104:"var"][297:"var"] customObj2_2 = new CustomClass2()[105:";"][298:";"]
        if [106:"("][299:"("]customObj2_1[300:".isBefore("]customObj2_2[300:")"] | customObj2_1[301:".isAfter("]customObj2_2[301:")"] | [302:"!"]customObj2_1[302:".isBefore("]customObj2_2[302:")"] | [303:"!"]customObj2_1[303:".isAfter("]customObj2_2[303:")"][106:")"][299:")"] [304:"{"]
            customObj2_1 = customObj2_2[305:";"]
[304:"        }\n"]
    }

    public static class CustomClass {
        private final long timestamp[306:";"]

        public CustomClass() {[307:"\n            "]this.timestamp = System.currentTimeMillis()[307:";"][308:";"][309:";"][310:";"][307:"\n        "]}

        public boolean before(CustomClass other) {[311:"\n            "][311:"return"][311:" "]this.timestamp < other.timestamp[311:";"][312:";"][313:";"][311:"\n        "]}

        public boolean after(CustomClass other) {[314:"\n            "][314:"return"][314:" "]this.timestamp > other.timestamp[314:";"][315:";"][316:";"][314:"\n        "]}
    }

    public class CustomClass2  {
        private final java.time.chrono.MinguoDate minguoDate[317:";"]

        public CustomClass2() {[318:"\n            "]this.minguoDate = java.time.chrono.MinguoDate.now()[318:";"][319:";"][320:";"][321:";"][318:"\n        "]}

        public boolean isBefore(CustomClass2 other) {[322:"\n            "][322:"return"][322:" "]this.minguoDate[323:".isBefore("][325:".isBefore("]other.minguoDate[323:")"][325:")"][322:";"][324:";"][326:";"][322:"\n        "]}

        public boolean isAfter(CustomClass2 other) {[327:"\n            "][327:"return"][327:" "]this.minguoDate[328:".isAfter("][330:".isAfter("]other.minguoDate[328:")"][330:")"][327:";"][329:";"][331:";"][327:"\n        "]}
    }


}