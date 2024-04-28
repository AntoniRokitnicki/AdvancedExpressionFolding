package data;

import java.sql.Timestamp;
import java.time.LocalDate;

@SuppressWarnings("ALL")
public class LocalDateTestData {
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.of(2018, 12, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);;
        boolean d1SmallerOrEqualD2 = !d1.isAfter(d2);

        var date1 = java.time.LocalDate.now();
        var date2 = java.time.LocalDate.now();
        if (date1.isBefore(date2) | date1.isAfter(date2) | !date1.isBefore(date2) | !date1.isAfter(date2)) {
            date1 = date2;
        }

        var dateTime1 = java.time.LocalDateTime.now();
        var dateTime2 = java.time.LocalDateTime.now();
        if (dateTime1.isBefore(dateTime2) | dateTime1.isAfter(dateTime2) | !dateTime1.isBefore(dateTime2) | !dateTime1.isAfter(dateTime2)) {
            dateTime1 = dateTime2;
        }

        var time1 = java.time.LocalTime.now();
        var time2 = java.time.LocalTime.now();
        if (time1.isBefore(time2) | time1.isAfter(time2) | !time1.isBefore(time2) | !time1.isAfter(time2)) {
            time1 = time2;
        }

        var offsetTime1 = java.time.OffsetTime.now();
        var offsetTime2 = java.time.OffsetTime.now();
        if (offsetTime1.isBefore(offsetTime2) | offsetTime1.isAfter(offsetTime2) | !offsetTime1.isBefore(offsetTime2) | !offsetTime1.isAfter(offsetTime2)) {
            offsetTime1 = offsetTime2;
        }

        var offsetDateTime1 = java.time.OffsetDateTime.now();
        var offsetDateTime2 = java.time.OffsetDateTime.now();
        if (offsetDateTime1.isBefore(offsetDateTime2) | offsetDateTime1.isAfter(offsetDateTime2) | !offsetDateTime1.isBefore(offsetDateTime2) | !offsetDateTime1.isAfter(offsetDateTime2)) {
            offsetDateTime1 = offsetDateTime2;
        }

        var zonedDateTime1 = java.time.ZonedDateTime.now();
        var zonedDateTime2 = java.time.ZonedDateTime.now();
        if (zonedDateTime1.isBefore(zonedDateTime2) | zonedDateTime1.isAfter(zonedDateTime2) | !zonedDateTime1.isBefore(zonedDateTime2) | !zonedDateTime1.isAfter(zonedDateTime2)) {
            zonedDateTime1 = zonedDateTime2;
        }

        var hijrahDate1 = java.time.chrono.HijrahDate.now();
        var hijrahDate2 = java.time.chrono.HijrahDate.now();
        if (hijrahDate1.isBefore(hijrahDate2) | hijrahDate1.isAfter(hijrahDate2) | !hijrahDate1.isBefore(hijrahDate2) | !hijrahDate1.isAfter(hijrahDate2)) {
            hijrahDate1 = hijrahDate2;
        }

        var japaneseDate1 = java.time.chrono.JapaneseDate.now();
        var japaneseDate2 = java.time.chrono.JapaneseDate.now();
        if (japaneseDate1.isBefore(japaneseDate2) | japaneseDate1.isAfter(japaneseDate2) | !japaneseDate1.isBefore(japaneseDate2) | !japaneseDate1.isAfter(japaneseDate2)) {
            japaneseDate1 = japaneseDate2;
        }

        var minguoDate1 = java.time.chrono.MinguoDate.now();
        var minguoDate2 = java.time.chrono.MinguoDate.now();
        if (minguoDate1.isBefore(minguoDate2) | minguoDate1.isAfter(minguoDate2) | !minguoDate1.isBefore(minguoDate2) | !minguoDate1.isAfter(minguoDate2)) {
            minguoDate1 = minguoDate2;
        }

        var thaiBuddhistDate1 = java.time.chrono.ThaiBuddhistDate.now();
        var thaiBuddhistDate2 = java.time.chrono.ThaiBuddhistDate.now();
        if (thaiBuddhistDate1.isBefore(thaiBuddhistDate2) | thaiBuddhistDate1.isAfter(thaiBuddhistDate2) | !thaiBuddhistDate1.isBefore(thaiBuddhistDate2) | !thaiBuddhistDate1.isAfter(thaiBuddhistDate2)) {
            thaiBuddhistDate1 = thaiBuddhistDate2;
        }

        var utilDate1 = new java.util.Date();
        var utilDate2 = new java.util.Date();
        if (utilDate1.before(utilDate2) | utilDate1.after(utilDate2) | !utilDate1.before(utilDate2) | !utilDate1.after(utilDate2)) {
            utilDate1 = utilDate2;
        }

        long currentTime = System.currentTimeMillis();
        var sqlDate1 = new java.sql.Date(currentTime);
        var sqlDate2 = new java.sql.Date(currentTime);
        if (sqlDate1.before(sqlDate2) | sqlDate1.after(sqlDate2) | !sqlDate1.before(sqlDate2) | !sqlDate1.after(sqlDate2)) {
            sqlDate1 = sqlDate2;
        }

        var timestamp1 = new Timestamp(System.currentTimeMillis());
        var timestamp2 = new Timestamp(System.currentTimeMillis());
        if (timestamp1.before(timestamp2) | timestamp1.after(timestamp2) | !timestamp1.before(timestamp2) | !timestamp1.after(timestamp2)) {
            timestamp1 = timestamp2;
        }
    }
}