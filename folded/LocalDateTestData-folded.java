package data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

@SuppressWarnings("ALL")
public class LocalDateTestData {
    public void main(String[] args) {
        LocalDate d1 = LocalDate.of(2018, 12, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        boolean isBefore = d1 < d2;
        boolean isAfter = d1 > d2;
        boolean d2SmallerOrEqualD1 = d1 ≥ d2;;
        boolean d1SmallerOrEqualD2 = d1 ≤ d2;

        var date1 = java.time.LocalDate.now();
        var date2 = java.time.LocalDate.now();
        if (date1 < date2 | date1 > date2 | date1 ≥ date2 | date1 ≤ date2) {
            date1 = date2;
        }

        var dateTime1 = java.time.LocalDateTime.now();
        var dateTime2 = java.time.LocalDateTime.now();
        if (dateTime1 < dateTime2 | dateTime1 > dateTime2 | dateTime1 ≥ dateTime2 | dateTime1 ≤ dateTime2) {
            dateTime1 = dateTime2;
        }

        var time1 = java.time.LocalTime.now();
        var time2 = java.time.LocalTime.now();
        if (time1 < time2 | time1 > time2 | time1 ≥ time2 | time1 ≤ time2) {
            time1 = time2;
        }

        var offsetTime1 = java.time.OffsetTime.now();
        var offsetTime2 = java.time.OffsetTime.now();
        if (offsetTime1 < offsetTime2 | offsetTime1 > offsetTime2 | offsetTime1 ≥ offsetTime2 | offsetTime1 ≤ offsetTime2) {
            offsetTime1 = offsetTime2;
        }

        var offsetDateTime1 = java.time.OffsetDateTime.now();
        var offsetDateTime2 = java.time.OffsetDateTime.now();
        if (offsetDateTime1 < offsetDateTime2 | offsetDateTime1 > offsetDateTime2 | offsetDateTime1 ≥ offsetDateTime2 | offsetDateTime1 ≤ offsetDateTime2) {
            offsetDateTime1 = offsetDateTime2;
        }

        var zonedDateTime1 = java.time.ZonedDateTime.now();
        var zonedDateTime2 = java.time.ZonedDateTime.now();
        if (zonedDateTime1 < zonedDateTime2 | zonedDateTime1 > zonedDateTime2 | zonedDateTime1 ≥ zonedDateTime2 | zonedDateTime1 ≤ zonedDateTime2) {
            zonedDateTime1 = zonedDateTime2;
        }

        var hijrahDate1 = java.time.chrono.HijrahDate.now();
        var hijrahDate2 = java.time.chrono.HijrahDate.now();
        if (hijrahDate1 < hijrahDate2 | hijrahDate1 > hijrahDate2 | hijrahDate1 ≥ hijrahDate2 | hijrahDate1 ≤ hijrahDate2) {
            hijrahDate1 = hijrahDate2;
        }

        var japaneseDate1 = java.time.chrono.JapaneseDate.now();
        var japaneseDate2 = java.time.chrono.JapaneseDate.now();
        if (japaneseDate1 < japaneseDate2 | japaneseDate1 > japaneseDate2 | japaneseDate1 ≥ japaneseDate2 | japaneseDate1 ≤ japaneseDate2) {
            japaneseDate1 = japaneseDate2;
        }

        var minguoDate1 = java.time.chrono.MinguoDate.now();
        var minguoDate2 = java.time.chrono.MinguoDate.now();
        if (minguoDate1 < minguoDate2 | minguoDate1 > minguoDate2 | minguoDate1 ≥ minguoDate2 | minguoDate1 ≤ minguoDate2) {
            minguoDate1 = minguoDate2;
        }

        var thaiBuddhistDate1 = java.time.chrono.ThaiBuddhistDate.now();
        var thaiBuddhistDate2 = java.time.chrono.ThaiBuddhistDate.now();
        if (thaiBuddhistDate1 < thaiBuddhistDate2 | thaiBuddhistDate1 > thaiBuddhistDate2 | thaiBuddhistDate1 ≥ thaiBuddhistDate2 | thaiBuddhistDate1 ≤ thaiBuddhistDate2) {
            thaiBuddhistDate1 = thaiBuddhistDate2;
        }

        var utilDate1 = new java.util.Date();
        var utilDate2 = new java.util.Date();
        if (utilDate1 < utilDate2 | utilDate1 > utilDate2 | utilDate1 ≥ utilDate2 | utilDate1 ≤ utilDate2) {
            utilDate1 = utilDate2;
        }

        long currentTime = System.currentTimeMillis();
        var sqlDate1 = new java.sql.Date(currentTime);
        var sqlDate2 = new java.sql.Date(currentTime);
        if (sqlDate1 < sqlDate2 | sqlDate1 > sqlDate2 | sqlDate1 ≥ sqlDate2 | sqlDate1 ≤ sqlDate2) {
            sqlDate1 = sqlDate2;
        }

        var timestamp1 = new Timestamp(System.currentTimeMillis());
        var timestamp2 = new Timestamp(System.currentTimeMillis());
        if (timestamp1 < timestamp2 | timestamp1 > timestamp2 | timestamp1 ≥ timestamp2 | timestamp1 ≤ timestamp2) {
            timestamp1 = timestamp2;
        }

        var cal1 = Calendar.getInstance();
        var cal2 = Calendar.getInstance();
        if (cal1 < cal2 | cal1 > cal2 | cal1 ≥ cal2 | cal1 ≤ cal2) {
            cal1 = cal2;
        }

        var customObj1 = new CustomClass();
        var customObj2 = new CustomClass();
        if (customObj1 < customObj2 | customObj1 > customObj2 | customObj1 ≥ customObj2 | customObj1 ≤ customObj2) {
            customObj1 = customObj2;
        }

        var customObj2_1 = new CustomClass2();
        var customObj2_2 = new CustomClass2();
        if (customObj2_1 < customObj2_2 | customObj2_1 > customObj2_2 | customObj2_1 ≥ customObj2_2 | customObj2_1 ≤ customObj2_2) {
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
            return this.minguoDate < other.minguoDate;
        }

        public boolean isAfter(CustomClass2 other) {
            return this.minguoDate > other.minguoDate;
        }
    }


}