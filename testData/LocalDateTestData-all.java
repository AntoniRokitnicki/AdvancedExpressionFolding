package data<fold text='' expand='false'>;</fold>

import java.time.LocalDate<fold text='' expand='false'>;</fold>

public class LocalDateTestData {
    public static void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>LocalDate</fold> d1 = <fold text='' expand='false'>LocalDate.of(</fold>2018<fold text='Y-' expand='false'>, </fold>12<fold text='M-' expand='false'>, </fold>10<fold text='D' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>LocalDate</fold> d2 = <fold text='' expand='false'>LocalDate.of(</fold>2018<fold text='Y-' expand='false'>, </fold>12<fold text='M-' expand='false'>, </fold>10<fold text='D' expand='false'>)<fold text='' expand='false'></fold>;</fold>
        <fold text='val' expand='false'>boolean</fold> isBefore = d1<fold text=' < ' expand='false'>.isBefore(</fold>d2<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>boolean</fold> isAfter = d1<fold text=' > ' expand='false'>.isAfter(</fold>d2<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>boolean</fold> d2SmallerOrEqualD1 = <fold text='' expand='false'>!</fold>d1<fold text=' ≥ ' expand='false'>.isBefore(</fold>d2<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>boolean</fold> d1SmallerOrEqualD2 = <fold text='' expand='false'>!</fold>d1<fold text=' ≤ ' expand='false'>.isAfter(</fold>d2<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold>

        <fold text='var' expand='false'>var</fold> date1 = java.time.LocalDate.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> date2 = java.time.LocalDate.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>date1<fold text=' < ' expand='false'>.isBefore(</fold>date2<fold text='' expand='false'>)</fold> | date1<fold text=' > ' expand='false'>.isAfter(</fold>date2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>date1<fold text=' ≥ ' expand='false'>.isBefore(</fold>date2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>date1<fold text=' ≤ ' expand='false'>.isAfter(</fold>date2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            date1 = date2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> dateTime1 = java.time.LocalDateTime.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> dateTime2 = java.time.LocalDateTime.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>dateTime1<fold text=' < ' expand='false'>.isBefore(</fold>dateTime2<fold text='' expand='false'>)</fold> | dateTime1<fold text=' > ' expand='false'>.isAfter(</fold>dateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>dateTime1<fold text=' ≥ ' expand='false'>.isBefore(</fold>dateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>dateTime1<fold text=' ≤ ' expand='false'>.isAfter(</fold>dateTime2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            dateTime1 = dateTime2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> time1 = java.time.LocalTime.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> time2 = java.time.LocalTime.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>time1<fold text=' < ' expand='false'>.isBefore(</fold>time2<fold text='' expand='false'>)</fold> | time1<fold text=' > ' expand='false'>.isAfter(</fold>time2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>time1<fold text=' ≥ ' expand='false'>.isBefore(</fold>time2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>time1<fold text=' ≤ ' expand='false'>.isAfter(</fold>time2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            time1 = time2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> offsetTime1 = java.time.OffsetTime.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> offsetTime2 = java.time.OffsetTime.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>offsetTime1<fold text=' < ' expand='false'>.isBefore(</fold>offsetTime2<fold text='' expand='false'>)</fold> | offsetTime1<fold text=' > ' expand='false'>.isAfter(</fold>offsetTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>offsetTime1<fold text=' ≥ ' expand='false'>.isBefore(</fold>offsetTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>offsetTime1<fold text=' ≤ ' expand='false'>.isAfter(</fold>offsetTime2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            offsetTime1 = offsetTime2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> offsetDateTime1 = java.time.OffsetDateTime.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> offsetDateTime2 = java.time.OffsetDateTime.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>offsetDateTime1<fold text=' < ' expand='false'>.isBefore(</fold>offsetDateTime2<fold text='' expand='false'>)</fold> | offsetDateTime1<fold text=' > ' expand='false'>.isAfter(</fold>offsetDateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>offsetDateTime1<fold text=' ≥ ' expand='false'>.isBefore(</fold>offsetDateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>offsetDateTime1<fold text=' ≤ ' expand='false'>.isAfter(</fold>offsetDateTime2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            offsetDateTime1 = offsetDateTime2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> zonedDateTime1 = java.time.ZonedDateTime.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> zonedDateTime2 = java.time.ZonedDateTime.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>zonedDateTime1<fold text=' < ' expand='false'>.isBefore(</fold>zonedDateTime2<fold text='' expand='false'>)</fold> | zonedDateTime1<fold text=' > ' expand='false'>.isAfter(</fold>zonedDateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>zonedDateTime1<fold text=' ≥ ' expand='false'>.isBefore(</fold>zonedDateTime2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>zonedDateTime1<fold text=' ≤ ' expand='false'>.isAfter(</fold>zonedDateTime2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            zonedDateTime1 = zonedDateTime2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> hijrahDate1 = java.time.chrono.HijrahDate.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> hijrahDate2 = java.time.chrono.HijrahDate.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>hijrahDate1<fold text=' < ' expand='false'>.isBefore(</fold>hijrahDate2<fold text='' expand='false'>)</fold> | hijrahDate1<fold text=' > ' expand='false'>.isAfter(</fold>hijrahDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>hijrahDate1<fold text=' ≥ ' expand='false'>.isBefore(</fold>hijrahDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>hijrahDate1<fold text=' ≤ ' expand='false'>.isAfter(</fold>hijrahDate2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            hijrahDate1 = hijrahDate2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> japaneseDate1 = java.time.chrono.JapaneseDate.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> japaneseDate2 = java.time.chrono.JapaneseDate.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>japaneseDate1<fold text=' < ' expand='false'>.isBefore(</fold>japaneseDate2<fold text='' expand='false'>)</fold> | japaneseDate1<fold text=' > ' expand='false'>.isAfter(</fold>japaneseDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>japaneseDate1<fold text=' ≥ ' expand='false'>.isBefore(</fold>japaneseDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>japaneseDate1<fold text=' ≤ ' expand='false'>.isAfter(</fold>japaneseDate2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            japaneseDate1 = japaneseDate2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> minguoDate1 = java.time.chrono.MinguoDate.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> minguoDate2 = java.time.chrono.MinguoDate.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>minguoDate1<fold text=' < ' expand='false'>.isBefore(</fold>minguoDate2<fold text='' expand='false'>)</fold> | minguoDate1<fold text=' > ' expand='false'>.isAfter(</fold>minguoDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>minguoDate1<fold text=' ≥ ' expand='false'>.isBefore(</fold>minguoDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>minguoDate1<fold text=' ≤ ' expand='false'>.isAfter(</fold>minguoDate2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            minguoDate1 = minguoDate2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> thaiBuddhistDate1 = java.time.chrono.ThaiBuddhistDate.now()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> thaiBuddhistDate2 = java.time.chrono.ThaiBuddhistDate.now()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>thaiBuddhistDate1<fold text=' < ' expand='false'>.isBefore(</fold>thaiBuddhistDate2<fold text='' expand='false'>)</fold> | thaiBuddhistDate1<fold text=' > ' expand='false'>.isAfter(</fold>thaiBuddhistDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>thaiBuddhistDate1<fold text=' ≥ ' expand='false'>.isBefore(</fold>thaiBuddhistDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>thaiBuddhistDate1<fold text=' ≤ ' expand='false'>.isAfter(</fold>thaiBuddhistDate2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            thaiBuddhistDate1 = thaiBuddhistDate2<fold text='' expand='false'>;</fold>
        }</fold>
    }</fold>
}