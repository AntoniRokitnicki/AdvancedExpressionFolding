package data;

import java.time.LocalDate;

public class LocalDateTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        LocalDate d1 = LocalDate.of(2018, 12, 10);
        LocalDate d2 = LocalDate.of(2018, 12, 10);
        boolean isBefore = d1<fold text=' < ' expand='false'>.isBefore(</fold>d2<fold text='' expand='false'>)</fold>;
        boolean isAfter = d1<fold text=' > ' expand='false'>.isAfter(</fold>d2<fold text='' expand='false'>)</fold>;
        boolean d2SmallerOrEqualD1 = <fold text='' expand='false'>!</fold>d1<fold text=' ≥ ' expand='false'>.isBefore(</fold>d2<fold text='' expand='false'>)</fold>;;
        boolean d1SmallerOrEqualD2 = <fold text='' expand='false'>!</fold>d1<fold text=' ≤ ' expand='false'>.isAfter(</fold>d2<fold text='' expand='false'>)</fold>;

        var date1 = java.time.LocalDate.now();
        var date2 = java.time.LocalDate.now();
        if (date1<fold text=' < ' expand='false'>.isBefore(</fold>date2<fold text='' expand='false'>)</fold> | date1<fold text=' > ' expand='false'>.isAfter(</fold>date2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>date1<fold text=' ≥ ' expand='false'>.isBefore(</fold>date2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>date1<fold text=' ≤ ' expand='false'>.isAfter(</fold>date2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            date1 = date2;
        }</fold>

        var dateTime1 = java.time.LocalDateTime.now();
        var dateTime2 = java.time.LocalDateTime.now();
        if (dateTime1<fold text=' < ' expand='false'>.isBefore(</fold>dateTime2<fold text='' expand='false'>)</fold> | dateTime1<fold text=' > ' expand='false'>.isAfter(</fold>dateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>dateTime1<fold text=' ≥ ' expand='false'>.isBefore(</fold>dateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>dateTime1<fold text=' ≤ ' expand='false'>.isAfter(</fold>dateTime2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            dateTime1 = dateTime2;
        }</fold>

        var time1 = java.time.LocalTime.now();
        var time2 = java.time.LocalTime.now();
        if (time1<fold text=' < ' expand='false'>.isBefore(</fold>time2<fold text='' expand='false'>)</fold> | time1<fold text=' > ' expand='false'>.isAfter(</fold>time2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>time1<fold text=' ≥ ' expand='false'>.isBefore(</fold>time2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>time1<fold text=' ≤ ' expand='false'>.isAfter(</fold>time2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            time1 = time2;
        }</fold>

        var offsetTime1 = java.time.OffsetTime.now();
        var offsetTime2 = java.time.OffsetTime.now();
        if (offsetTime1<fold text=' < ' expand='false'>.isBefore(</fold>offsetTime2<fold text='' expand='false'>)</fold> | offsetTime1<fold text=' > ' expand='false'>.isAfter(</fold>offsetTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>offsetTime1<fold text=' ≥ ' expand='false'>.isBefore(</fold>offsetTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>offsetTime1<fold text=' ≤ ' expand='false'>.isAfter(</fold>offsetTime2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            offsetTime1 = offsetTime2;
        }</fold>

        var offsetDateTime1 = java.time.OffsetDateTime.now();
        var offsetDateTime2 = java.time.OffsetDateTime.now();
        if (offsetDateTime1<fold text=' < ' expand='false'>.isBefore(</fold>offsetDateTime2<fold text='' expand='false'>)</fold> | offsetDateTime1<fold text=' > ' expand='false'>.isAfter(</fold>offsetDateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>offsetDateTime1<fold text=' ≥ ' expand='false'>.isBefore(</fold>offsetDateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>offsetDateTime1<fold text=' ≤ ' expand='false'>.isAfter(</fold>offsetDateTime2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            offsetDateTime1 = offsetDateTime2;
        }</fold>

        var zonedDateTime1 = java.time.ZonedDateTime.now();
        var zonedDateTime2 = java.time.ZonedDateTime.now();
        if (zonedDateTime1<fold text=' < ' expand='false'>.isBefore(</fold>zonedDateTime2<fold text='' expand='false'>)</fold> | zonedDateTime1<fold text=' > ' expand='false'>.isAfter(</fold>zonedDateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>zonedDateTime1<fold text=' ≥ ' expand='false'>.isBefore(</fold>zonedDateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>zonedDateTime1<fold text=' ≤ ' expand='false'>.isAfter(</fold>zonedDateTime2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            zonedDateTime1 = zonedDateTime2;
        }</fold>

        var hijrahDate1 = java.time.chrono.HijrahDate.now();
        var hijrahDate2 = java.time.chrono.HijrahDate.now();
        if (hijrahDate1<fold text=' < ' expand='false'>.isBefore(</fold>hijrahDate2<fold text='' expand='false'>)</fold> | hijrahDate1<fold text=' > ' expand='false'>.isAfter(</fold>hijrahDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>hijrahDate1<fold text=' ≥ ' expand='false'>.isBefore(</fold>hijrahDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>hijrahDate1<fold text=' ≤ ' expand='false'>.isAfter(</fold>hijrahDate2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            hijrahDate1 = hijrahDate2;
        }</fold>

        var japaneseDate1 = java.time.chrono.JapaneseDate.now();
        var japaneseDate2 = java.time.chrono.JapaneseDate.now();
        if (japaneseDate1<fold text=' < ' expand='false'>.isBefore(</fold>japaneseDate2<fold text='' expand='false'>)</fold> | japaneseDate1<fold text=' > ' expand='false'>.isAfter(</fold>japaneseDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>japaneseDate1<fold text=' ≥ ' expand='false'>.isBefore(</fold>japaneseDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>japaneseDate1<fold text=' ≤ ' expand='false'>.isAfter(</fold>japaneseDate2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            japaneseDate1 = japaneseDate2;
        }</fold>

        var minguoDate1 = java.time.chrono.MinguoDate.now();
        var minguoDate2 = java.time.chrono.MinguoDate.now();
        if (minguoDate1<fold text=' < ' expand='false'>.isBefore(</fold>minguoDate2<fold text='' expand='false'>)</fold> | minguoDate1<fold text=' > ' expand='false'>.isAfter(</fold>minguoDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>minguoDate1<fold text=' ≥ ' expand='false'>.isBefore(</fold>minguoDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>minguoDate1<fold text=' ≤ ' expand='false'>.isAfter(</fold>minguoDate2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            minguoDate1 = minguoDate2;
        }</fold>

        var thaiBuddhistDate1 = java.time.chrono.ThaiBuddhistDate.now();
        var thaiBuddhistDate2 = java.time.chrono.ThaiBuddhistDate.now();
        if (thaiBuddhistDate1<fold text=' < ' expand='false'>.isBefore(</fold>thaiBuddhistDate2<fold text='' expand='false'>)</fold> | thaiBuddhistDate1<fold text=' > ' expand='false'>.isAfter(</fold>thaiBuddhistDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>thaiBuddhistDate1<fold text=' ≥ ' expand='false'>.isBefore(</fold>thaiBuddhistDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>thaiBuddhistDate1<fold text=' ≤ ' expand='false'>.isAfter(</fold>thaiBuddhistDate2<fold text='' expand='false'>)</fold>) <fold text='{...}' expand='true'>{
            thaiBuddhistDate1 = thaiBuddhistDate2;
        }</fold>
    }</fold>
}