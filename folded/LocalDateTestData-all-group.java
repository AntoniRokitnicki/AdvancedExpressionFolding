package data[0:";"]

import java.sql.Timestamp[1:";"]
import java.time.LocalDate[2:";"]
import java.util.Calendar[3:";"]

@SuppressWarnings("ALL")
public class LocalDateTestData {
    public void main(String[] args) {
        [4:"LocalDate"] d1 = [5:"LocalDate.of("]2018[5:", "]12[5:", "]10[5:")"][6:";"]
        [7:"LocalDate"] d2 = [8:"LocalDate.of("]2018[8:", "]12[8:", "]10[8:")"][9:";"]
        [10:"boolean"] isBefore = d1[11:".isBefore("]d2[11:")"][12:";"]
        [13:"boolean"] isAfter = d1[14:".isAfter("]d2[14:")"][15:";"]
        [16:"boolean"] d2SmallerOrEqualD1 = [17:"!"]d1[17:".isBefore("]d2[17:")"][18:";"][19:";"]
        [20:"boolean"] d1SmallerOrEqualD2 = [21:"!"]d1[21:".isAfter("]d2[21:")"][22:";"]

        [23:"var"] date1 = java.time.LocalDate.now()[24:";"]
        [25:"var"] date2 = java.time.LocalDate.now()[26:";"]
        if [27:"("]date1[131:".isBefore("]date2[131:")"] | date1[132:".isAfter("]date2[132:")"] | [133:"!"]date1[133:".isBefore("]date2[133:")"] | [134:"!"]date1[134:".isAfter("]date2[134:")"][27:")"] [135:"{"]
            date1 = date2[136:";"]
[135:"        }\n"]
        [28:"var"] dateTime1 = java.time.LocalDateTime.now()[29:";"]
        [30:"var"] dateTime2 = java.time.LocalDateTime.now()[31:";"]
        if [32:"("]dateTime1[142:".isBefore("]dateTime2[142:")"] | dateTime1[143:".isAfter("]dateTime2[143:")"] | [144:"!"]dateTime1[144:".isBefore("]dateTime2[144:")"] | [145:"!"]dateTime1[145:".isAfter("]dateTime2[145:")"][32:")"] [146:"{"]
            dateTime1 = dateTime2[147:";"]
[146:"        }\n"]
        [33:"var"] time1 = java.time.LocalTime.now()[34:";"]
        [35:"var"] time2 = java.time.LocalTime.now()[36:";"]
        if [37:"("]time1[153:".isBefore("]time2[153:")"] | time1[154:".isAfter("]time2[154:")"] | [155:"!"]time1[155:".isBefore("]time2[155:")"] | [156:"!"]time1[156:".isAfter("]time2[156:")"][37:")"] [157:"{"]
            time1 = time2[158:";"]
[157:"        }\n"]
        [38:"var"] offsetTime1 = java.time.OffsetTime.now()[39:";"]
        [40:"var"] offsetTime2 = java.time.OffsetTime.now()[41:";"]
        if [42:"("]offsetTime1[164:".isBefore("]offsetTime2[164:")"] | offsetTime1[165:".isAfter("]offsetTime2[165:")"] | [166:"!"]offsetTime1[166:".isBefore("]offsetTime2[166:")"] | [167:"!"]offsetTime1[167:".isAfter("]offsetTime2[167:")"][42:")"] [168:"{"]
            offsetTime1 = offsetTime2[169:";"]
[168:"        }\n"]
        [43:"var"] offsetDateTime1 = java.time.OffsetDateTime.now()[44:";"]
        [45:"var"] offsetDateTime2 = java.time.OffsetDateTime.now()[46:";"]
        if [47:"("]offsetDateTime1[175:".isBefore("]offsetDateTime2[175:")"] | offsetDateTime1[176:".isAfter("]offsetDateTime2[176:")"] | [177:"!"]offsetDateTime1[177:".isBefore("]offsetDateTime2[177:")"] | [178:"!"]offsetDateTime1[178:".isAfter("]offsetDateTime2[178:")"][47:")"] [179:"{"]
            offsetDateTime1 = offsetDateTime2[180:";"]
[179:"        }\n"]
        [48:"var"] zonedDateTime1 = java.time.ZonedDateTime.now()[49:";"]
        [50:"var"] zonedDateTime2 = java.time.ZonedDateTime.now()[51:";"]
        if [52:"("]zonedDateTime1[186:".isBefore("]zonedDateTime2[186:")"] | zonedDateTime1[187:".isAfter("]zonedDateTime2[187:")"] | [188:"!"]zonedDateTime1[188:".isBefore("]zonedDateTime2[188:")"] | [189:"!"]zonedDateTime1[189:".isAfter("]zonedDateTime2[189:")"][52:")"] [190:"{"]
            zonedDateTime1 = zonedDateTime2[191:";"]
[190:"        }\n"]
        [53:"var"] hijrahDate1 = java.time.chrono.HijrahDate.now()[54:";"]
        [55:"var"] hijrahDate2 = java.time.chrono.HijrahDate.now()[56:";"]
        if [57:"("]hijrahDate1[197:".isBefore("]hijrahDate2[197:")"] | hijrahDate1[198:".isAfter("]hijrahDate2[198:")"] | [199:"!"]hijrahDate1[199:".isBefore("]hijrahDate2[199:")"] | [200:"!"]hijrahDate1[200:".isAfter("]hijrahDate2[200:")"][57:")"] [201:"{"]
            hijrahDate1 = hijrahDate2[202:";"]
[201:"        }\n"]
        [58:"var"] japaneseDate1 = java.time.chrono.JapaneseDate.now()[59:";"]
        [60:"var"] japaneseDate2 = java.time.chrono.JapaneseDate.now()[61:";"]
        if [62:"("]japaneseDate1[208:".isBefore("]japaneseDate2[208:")"] | japaneseDate1[209:".isAfter("]japaneseDate2[209:")"] | [210:"!"]japaneseDate1[210:".isBefore("]japaneseDate2[210:")"] | [211:"!"]japaneseDate1[211:".isAfter("]japaneseDate2[211:")"][62:")"] [212:"{"]
            japaneseDate1 = japaneseDate2[213:";"]
[212:"        }\n"]
        [63:"var"] minguoDate1 = java.time.chrono.MinguoDate.now()[64:";"]
        [65:"var"] minguoDate2 = java.time.chrono.MinguoDate.now()[66:";"]
        if [67:"("]minguoDate1[219:".isBefore("]minguoDate2[219:")"] | minguoDate1[220:".isAfter("]minguoDate2[220:")"] | [221:"!"]minguoDate1[221:".isBefore("]minguoDate2[221:")"] | [222:"!"]minguoDate1[222:".isAfter("]minguoDate2[222:")"][67:")"] [223:"{"]
            minguoDate1 = minguoDate2[224:";"]
[223:"        }\n"]
        [68:"var"] thaiBuddhistDate1 = java.time.chrono.ThaiBuddhistDate.now()[69:";"]
        [70:"var"] thaiBuddhistDate2 = java.time.chrono.ThaiBuddhistDate.now()[71:";"]
        if [72:"("]thaiBuddhistDate1[230:".isBefore("]thaiBuddhistDate2[230:")"] | thaiBuddhistDate1[231:".isAfter("]thaiBuddhistDate2[231:")"] | [232:"!"]thaiBuddhistDate1[232:".isBefore("]thaiBuddhistDate2[232:")"] | [233:"!"]thaiBuddhistDate1[233:".isAfter("]thaiBuddhistDate2[233:")"][72:")"] [234:"{"]
            thaiBuddhistDate1 = thaiBuddhistDate2[235:";"]
[234:"        }\n"]
        [73:"var"] utilDate1 = new java.util.Date()[74:";"]
        [75:"var"] utilDate2 = new java.util.Date()[76:";"]
        if [77:"("]utilDate1[241:".before("]utilDate2[241:")"] | utilDate1[242:".after("]utilDate2[242:")"] | [243:"!"]utilDate1[243:".before("]utilDate2[243:")"] | [244:"!"]utilDate1[244:".after("]utilDate2[244:")"][77:")"] [245:"{"]
            utilDate1 = utilDate2[246:";"]
[245:"        }\n"]
        [78:"long"] currentTime = System.currentTimeMillis()[79:";"]
        [80:"var"] sqlDate1 = new java.sql.Date(currentTime)[81:";"]
        [82:"var"] sqlDate2 = new java.sql.Date(currentTime)[83:";"]
        if [84:"("]sqlDate1[254:".before("]sqlDate2[254:")"] | sqlDate1[255:".after("]sqlDate2[255:")"] | [256:"!"]sqlDate1[256:".before("]sqlDate2[256:")"] | [257:"!"]sqlDate1[257:".after("]sqlDate2[257:")"][84:")"] [258:"{"]
            sqlDate1 = sqlDate2[259:";"]
[258:"        }\n"]
        [85:"var"] timestamp1 = new Timestamp(System.currentTimeMillis())[86:";"]
        [87:"var"] timestamp2 = new Timestamp(System.currentTimeMillis())[88:";"]
        if [89:"("]timestamp1[265:".before("]timestamp2[265:")"] | timestamp1[266:".after("]timestamp2[266:")"] | [267:"!"]timestamp1[267:".before("]timestamp2[267:")"] | [268:"!"]timestamp1[268:".after("]timestamp2[268:")"][89:")"] [269:"{"]
            timestamp1 = timestamp2[270:";"]
[269:"        }\n"]
        [90:"var"] cal1 = Calendar.[91:"getInstance()"][92:";"]
        [93:"var"] cal2 = Calendar.[94:"getInstance()"][95:";"]
        if [96:"("]cal1[278:".before("]cal2[278:")"] | cal1[279:".after("]cal2[279:")"] | [280:"!"]cal1[280:".before("]cal2[280:")"] | [281:"!"]cal1[281:".after("]cal2[281:")"][96:")"] [282:"{"]
            cal1 = cal2[283:";"]
[282:"        }\n"]
        [97:"var"] customObj1 = new CustomClass()[98:";"]
        [99:"var"] customObj2 = new CustomClass()[100:";"]
        if [101:"("]customObj1[289:".before("]customObj2[289:")"] | customObj1[290:".after("]customObj2[290:")"] | [291:"!"]customObj1[291:".before("]customObj2[291:")"] | [292:"!"]customObj1[292:".after("]customObj2[292:")"][101:")"] [293:"{"]
            customObj1 = customObj2[294:";"]
[293:"        }\n"]
        [102:"var"] customObj2_1 = new CustomClass2()[103:";"]
        [104:"var"] customObj2_2 = new CustomClass2()[105:";"]
        if [106:"("]customObj2_1[300:".isBefore("]customObj2_2[300:")"] | customObj2_1[301:".isAfter("]customObj2_2[301:")"] | [302:"!"]customObj2_1[302:".isBefore("]customObj2_2[302:")"] | [303:"!"]customObj2_1[303:".isAfter("]customObj2_2[303:")"][106:")"] [304:"{"]
            customObj2_1 = customObj2_2[305:";"]
[304:"        }\n"]
    }

    public static class CustomClass {
        private final long timestamp[306:";"]

        public CustomClass() {[307:"\n            "]this.timestamp = System.currentTimeMillis()[307:";"][307:"\n        "]}

        public boolean before(CustomClass other) {[311:"\n            "][311:"return"][311:" "]this.timestamp < other.timestamp[311:";"][311:"\n        "]}

        public boolean after(CustomClass other) {[314:"\n            "][314:"return"][314:" "]this.timestamp > other.timestamp[314:";"][314:"\n        "]}
    }

    public class CustomClass2  {
        private final java.time.chrono.MinguoDate minguoDate[317:";"]

        public CustomClass2() {[318:"\n            "]this.minguoDate = java.time.chrono.MinguoDate.now()[318:";"][318:"\n        "]}

        public boolean isBefore(CustomClass2 other) {[322:"\n            "][322:"return"][322:" "]this.minguoDate[323:".isBefore("]other.minguoDate[323:")"][322:";"][322:"\n        "]}

        public boolean isAfter(CustomClass2 other) {[327:"\n            "][327:"return"][327:" "]this.minguoDate[328:".isAfter("]other.minguoDate[328:")"][327:";"][327:"\n        "]}
    }


}