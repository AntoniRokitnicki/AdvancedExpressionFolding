package data<fold text='' expand='false'>;</fold>

import <fold text='...' expand='false'>java.sql.Timestamp<fold text='' expand='false'>;</fold>
import java.time.LocalDate<fold text='' expand='false'>;</fold>
import java.util.Calendar<fold text='' expand='false'>;</fold></fold>

@SuppressWarnings("ALL")
public class LocalDateTestData {
    public void main(String[] args) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>LocalDate</fold> d1 = <fold text='' expand='false'>LocalDate.of(</fold>2018<fold text='Y-' expand='false'>, </fold>12<fold text='M-' expand='false'>, </fold>10<fold text='D' expand='false'>)</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>LocalDate</fold> d2 = <fold text='' expand='false'>LocalDate.of(</fold>2018<fold text='Y-' expand='false'>, </fold>12<fold text='M-' expand='false'>, </fold>10<fold text='D' expand='false'>)</fold><fold text='' expand='false'>;</fold>
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

        <fold text='var' expand='false'>var</fold> utilDate1 = new java.util.Date()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> utilDate2 = new java.util.Date()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>utilDate1<fold text=' < ' expand='false'>.before(</fold>utilDate2<fold text='' expand='false'>)</fold> | utilDate1<fold text=' > ' expand='false'>.after(</fold>utilDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>utilDate1<fold text=' ≥ ' expand='false'>.before(</fold>utilDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>utilDate1<fold text=' ≤ ' expand='false'>.after(</fold>utilDate2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            utilDate1 = utilDate2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='val' expand='false'>long</fold> currentTime = System.currentTimeMillis()<fold text='' expand='false'>;</fold>
        <fold text='var' expand='false'>var</fold> sqlDate1 = new java.sql.Date(currentTime)<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> sqlDate2 = new java.sql.Date(currentTime)<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>sqlDate1<fold text=' < ' expand='false'>.before(</fold>sqlDate2<fold text='' expand='false'>)</fold> | sqlDate1<fold text=' > ' expand='false'>.after(</fold>sqlDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>sqlDate1<fold text=' ≥ ' expand='false'>.before(</fold>sqlDate2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>sqlDate1<fold text=' ≤ ' expand='false'>.after(</fold>sqlDate2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            sqlDate1 = sqlDate2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> timestamp1 = new Timestamp(System.currentTimeMillis())<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> timestamp2 = new Timestamp(System.currentTimeMillis())<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>timestamp1<fold text=' < ' expand='false'>.before(</fold>timestamp2<fold text='' expand='false'>)</fold> | timestamp1<fold text=' > ' expand='false'>.after(</fold>timestamp2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>timestamp1<fold text=' ≥ ' expand='false'>.before(</fold>timestamp2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>timestamp1<fold text=' ≤ ' expand='false'>.after(</fold>timestamp2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            timestamp1 = timestamp2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> cal1 = Calendar.<fold text='instance' expand='false'>getInstance()</fold><fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> cal2 = Calendar.<fold text='instance' expand='false'>getInstance()</fold><fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>cal1<fold text=' < ' expand='false'>.before(</fold>cal2<fold text='' expand='false'>)</fold> | cal1<fold text=' > ' expand='false'>.after(</fold>cal2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>cal1<fold text=' ≥ ' expand='false'>.before(</fold>cal2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>cal1<fold text=' ≤ ' expand='false'>.after(</fold>cal2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            cal1 = cal2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> customObj1 = new CustomClass()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> customObj2 = new CustomClass()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>customObj1<fold text=' < ' expand='false'>.before(</fold>customObj2<fold text='' expand='false'>)</fold> | customObj1<fold text=' > ' expand='false'>.after(</fold>customObj2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>customObj1<fold text=' ≥ ' expand='false'>.before(</fold>customObj2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>customObj1<fold text=' ≤ ' expand='false'>.after(</fold>customObj2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            customObj1 = customObj2<fold text='' expand='false'>;</fold>
        }</fold>

        <fold text='var' expand='false'>var</fold> customObj2_1 = new CustomClass2()<fold text='' expand='false'>;</fold>
        <fold text='val' expand='false'>var</fold> customObj2_2 = new CustomClass2()<fold text='' expand='false'>;</fold>
        if <fold text='' expand='false'>(</fold>customObj2_1<fold text=' < ' expand='false'>.isBefore(</fold>customObj2_2<fold text='' expand='false'>)</fold> | customObj2_1<fold text=' > ' expand='false'>.isAfter(</fold>customObj2_2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>customObj2_1<fold text=' ≥ ' expand='false'>.isBefore(</fold>customObj2_2<fold text='' expand='false'>)</fold> | <fold text='' expand='false'>!</fold>customObj2_1<fold text=' ≤ ' expand='false'>.isAfter(</fold>customObj2_2<fold text='' expand='false'>)</fold><fold text='' expand='false'>)</fold> <fold text='' expand='false'><fold text='{...}' expand='true'>{</fold>
            customObj2_1 = customObj2_2<fold text='' expand='false'>;</fold>
        }</fold>

    }</fold>

    public static class CustomClass <fold text='{...}' expand='true'>{
        private final long timestamp;

        public CustomClass()<fold text=' { ' expand='false'> {
            </fold>this.timestamp = System.currentTimeMillis()<fold text='' expand='false'>;</fold><fold text=' }' expand='false'>
        }</fold>

        public boolean before(CustomClass other)<fold text=' { ' expand='false'> {
            </fold>return this.timestamp < other.timestamp<fold text='' expand='false'>;</fold><fold text=' }' expand='false'>
        }</fold>

        public boolean after(CustomClass other)<fold text=' { ' expand='false'> {
            </fold>return this.timestamp > other.timestamp<fold text='' expand='false'>;</fold><fold text=' }' expand='false'>
        }</fold>
    }</fold>

    public class CustomClass2  <fold text='{...}' expand='true'>{
        private final java.time.chrono.MinguoDate minguoDate;

        public CustomClass2()<fold text=' { ' expand='false'> {
            </fold>this.minguoDate = java.time.chrono.MinguoDate.now()<fold text='' expand='false'>;</fold><fold text=' }' expand='false'>
        }</fold>

        public boolean isBefore(CustomClass2 other)<fold text=' { ' expand='false'> {
            </fold>return this.minguoDate<fold text=' < ' expand='false'>.isBefore(</fold>other.minguoDate<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold><fold text=' }' expand='false'>
        }</fold>

        public boolean isAfter(CustomClass2 other)<fold text=' { ' expand='false'> {
            </fold>return this.minguoDate<fold text=' > ' expand='false'>.isAfter(</fold>other.minguoDate<fold text='' expand='false'>)</fold><fold text='' expand='false'>;</fold><fold text=' }' expand='false'>
        }</fold>
    }</fold>


}