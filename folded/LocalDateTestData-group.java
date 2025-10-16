package data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

@SuppressWarnings("ALL")
public class LocalDateTestData {
    public void main(String[] args) {
        LocalDate d1 = LocalDate.of(2018, 12, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        boolean isBefore = d1[0:".isBefore("]d2[0:")"];
        boolean isAfter = d1[1:".isAfter("]d2[1:")"];
        boolean d2SmallerOrEqualD1 = [2:"!"]d1[2:".isBefore("]d2[2:")"];;
        boolean d1SmallerOrEqualD2 = [3:"!"]d1[3:".isAfter("]d2[3:")"];

        var date1 = java.time.LocalDate.now();
        var date2 = java.time.LocalDate.now();
        if (date1[8:".isBefore("]date2[8:")"] | date1[9:".isAfter("]date2[9:")"] | [10:"!"]date1[10:".isBefore("]date2[10:")"] | [11:"!"]date1[11:".isAfter("]date2[11:")"]) {
            date1 = date2;
        }

        var dateTime1 = java.time.LocalDateTime.now();
        var dateTime2 = java.time.LocalDateTime.now();
        if (dateTime1[12:".isBefore("]dateTime2[12:")"] | dateTime1[13:".isAfter("]dateTime2[13:")"] | [14:"!"]dateTime1[14:".isBefore("]dateTime2[14:")"] | [15:"!"]dateTime1[15:".isAfter("]dateTime2[15:")"]) {
            dateTime1 = dateTime2;
        }

        var time1 = java.time.LocalTime.now();
        var time2 = java.time.LocalTime.now();
        if (time1[16:".isBefore("]time2[16:")"] | time1[17:".isAfter("]time2[17:")"] | [18:"!"]time1[18:".isBefore("]time2[18:")"] | [19:"!"]time1[19:".isAfter("]time2[19:")"]) {
            time1 = time2;
        }

        var offsetTime1 = java.time.OffsetTime.now();
        var offsetTime2 = java.time.OffsetTime.now();
        if (offsetTime1[20:".isBefore("]offsetTime2[20:")"] | offsetTime1[21:".isAfter("]offsetTime2[21:")"] | [22:"!"]offsetTime1[22:".isBefore("]offsetTime2[22:")"] | [23:"!"]offsetTime1[23:".isAfter("]offsetTime2[23:")"]) {
            offsetTime1 = offsetTime2;
        }

        var offsetDateTime1 = java.time.OffsetDateTime.now();
        var offsetDateTime2 = java.time.OffsetDateTime.now();
        if (offsetDateTime1[24:".isBefore("]offsetDateTime2[24:")"] | offsetDateTime1[25:".isAfter("]offsetDateTime2[25:")"] | [26:"!"]offsetDateTime1[26:".isBefore("]offsetDateTime2[26:")"] | [27:"!"]offsetDateTime1[27:".isAfter("]offsetDateTime2[27:")"]) {
            offsetDateTime1 = offsetDateTime2;
        }

        var zonedDateTime1 = java.time.ZonedDateTime.now();
        var zonedDateTime2 = java.time.ZonedDateTime.now();
        if (zonedDateTime1[28:".isBefore("]zonedDateTime2[28:")"] | zonedDateTime1[29:".isAfter("]zonedDateTime2[29:")"] | [30:"!"]zonedDateTime1[30:".isBefore("]zonedDateTime2[30:")"] | [31:"!"]zonedDateTime1[31:".isAfter("]zonedDateTime2[31:")"]) {
            zonedDateTime1 = zonedDateTime2;
        }

        var hijrahDate1 = java.time.chrono.HijrahDate.now();
        var hijrahDate2 = java.time.chrono.HijrahDate.now();
        if (hijrahDate1[32:".isBefore("]hijrahDate2[32:")"] | hijrahDate1[33:".isAfter("]hijrahDate2[33:")"] | [34:"!"]hijrahDate1[34:".isBefore("]hijrahDate2[34:")"] | [35:"!"]hijrahDate1[35:".isAfter("]hijrahDate2[35:")"]) {
            hijrahDate1 = hijrahDate2;
        }

        var japaneseDate1 = java.time.chrono.JapaneseDate.now();
        var japaneseDate2 = java.time.chrono.JapaneseDate.now();
        if (japaneseDate1[36:".isBefore("]japaneseDate2[36:")"] | japaneseDate1[37:".isAfter("]japaneseDate2[37:")"] | [38:"!"]japaneseDate1[38:".isBefore("]japaneseDate2[38:")"] | [39:"!"]japaneseDate1[39:".isAfter("]japaneseDate2[39:")"]) {
            japaneseDate1 = japaneseDate2;
        }

        var minguoDate1 = java.time.chrono.MinguoDate.now();
        var minguoDate2 = java.time.chrono.MinguoDate.now();
        if (minguoDate1[40:".isBefore("]minguoDate2[40:")"] | minguoDate1[41:".isAfter("]minguoDate2[41:")"] | [42:"!"]minguoDate1[42:".isBefore("]minguoDate2[42:")"] | [43:"!"]minguoDate1[43:".isAfter("]minguoDate2[43:")"]) {
            minguoDate1 = minguoDate2;
        }

        var thaiBuddhistDate1 = java.time.chrono.ThaiBuddhistDate.now();
        var thaiBuddhistDate2 = java.time.chrono.ThaiBuddhistDate.now();
        if (thaiBuddhistDate1[44:".isBefore("]thaiBuddhistDate2[44:")"] | thaiBuddhistDate1[45:".isAfter("]thaiBuddhistDate2[45:")"] | [46:"!"]thaiBuddhistDate1[46:".isBefore("]thaiBuddhistDate2[46:")"] | [47:"!"]thaiBuddhistDate1[47:".isAfter("]thaiBuddhistDate2[47:")"]) {
            thaiBuddhistDate1 = thaiBuddhistDate2;
        }

        var utilDate1 = new java.util.Date();
        var utilDate2 = new java.util.Date();
        if (utilDate1[48:".before("]utilDate2[48:")"] | utilDate1[49:".after("]utilDate2[49:")"] | [50:"!"]utilDate1[50:".before("]utilDate2[50:")"] | [51:"!"]utilDate1[51:".after("]utilDate2[51:")"]) {
            utilDate1 = utilDate2;
        }

        long currentTime = System.currentTimeMillis();
        var sqlDate1 = new java.sql.Date(currentTime);
        var sqlDate2 = new java.sql.Date(currentTime);
        if (sqlDate1[52:".before("]sqlDate2[52:")"] | sqlDate1[53:".after("]sqlDate2[53:")"] | [54:"!"]sqlDate1[54:".before("]sqlDate2[54:")"] | [55:"!"]sqlDate1[55:".after("]sqlDate2[55:")"]) {
            sqlDate1 = sqlDate2;
        }

        var timestamp1 = new Timestamp(System.currentTimeMillis());
        var timestamp2 = new Timestamp(System.currentTimeMillis());
        if (timestamp1[56:".before("]timestamp2[56:")"] | timestamp1[57:".after("]timestamp2[57:")"] | [58:"!"]timestamp1[58:".before("]timestamp2[58:")"] | [59:"!"]timestamp1[59:".after("]timestamp2[59:")"]) {
            timestamp1 = timestamp2;
        }

        var cal1 = Calendar.getInstance();
        var cal2 = Calendar.getInstance();
        if (cal1[60:".before("]cal2[60:")"] | cal1[61:".after("]cal2[61:")"] | [62:"!"]cal1[62:".before("]cal2[62:")"] | [63:"!"]cal1[63:".after("]cal2[63:")"]) {
            cal1 = cal2;
        }

        var customObj1 = new CustomClass();
        var customObj2 = new CustomClass();
        if (customObj1[64:".before("]customObj2[64:")"] | customObj1[65:".after("]customObj2[65:")"] | [66:"!"]customObj1[66:".before("]customObj2[66:")"] | [67:"!"]customObj1[67:".after("]customObj2[67:")"]) {
            customObj1 = customObj2;
        }

        var customObj2_1 = new CustomClass2();
        var customObj2_2 = new CustomClass2();
        if (customObj2_1[68:".isBefore("]customObj2_2[68:")"] | customObj2_1[69:".isAfter("]customObj2_2[69:")"] | [70:"!"]customObj2_1[70:".isBefore("]customObj2_2[70:")"] | [71:"!"]customObj2_1[71:".isAfter("]customObj2_2[71:")"]) {
            customObj2_1 = customObj2_2;
        }

    }

    public static class CustomClass {
        private final long timestamp;

        public CustomClass() {
            this.timestamp = System.currentTimeMillis();
        }

        public boolean before(CustomClass other) {
            return this.timestamp < other.timestamp;
        }

        public boolean after(CustomClass other) {
            return this.timestamp > other.timestamp;
        }
    }

    public class CustomClass2  {
        private final java.time.chrono.MinguoDate minguoDate;

        public CustomClass2() {
            this.minguoDate = java.time.chrono.MinguoDate.now();
        }

        public boolean isBefore(CustomClass2 other) {
            return this.minguoDate[72:".isBefore("]other.minguoDate[72:")"];
        }

        public boolean isAfter(CustomClass2 other) {
            return this.minguoDate[74:".isAfter("]other.minguoDate[74:")"];
        }
    }


}