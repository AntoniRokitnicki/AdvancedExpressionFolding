package data

import java.time.LocalDate

public class LocalDateTestData {
    public static void main(String[] args) {
        val d1 = 2018Y-12M-10D
        val d2 = 2018Y-12M-10D
        val isBefore = d1 < d2
        val isAfter = d1 > d2
        val d2SmallerOrEqualD1 = d1 ≥ d2
        val d1SmallerOrEqualD2 = d1 ≤ d2

        var date1 = java.time.LocalDate.now()
        val date2 = java.time.LocalDate.now()
        if date1 < date2 | date1 > date2 | date1 ≥ date2 | date1 ≤ date2 
            date1 = date2

        var dateTime1 = java.time.LocalDateTime.now()
        val dateTime2 = java.time.LocalDateTime.now()
        if dateTime1 < dateTime2 | dateTime1 > dateTime2 | dateTime1 ≥ dateTime2 | dateTime1 ≤ dateTime2 
            dateTime1 = dateTime2

        var time1 = java.time.LocalTime.now()
        val time2 = java.time.LocalTime.now()
        if time1 < time2 | time1 > time2 | time1 ≥ time2 | time1 ≤ time2 
            time1 = time2

        var offsetTime1 = java.time.OffsetTime.now()
        val offsetTime2 = java.time.OffsetTime.now()
        if offsetTime1 < offsetTime2 | offsetTime1 > offsetTime2 | offsetTime1 ≥ offsetTime2 | offsetTime1 ≤ offsetTime2 
            offsetTime1 = offsetTime2

        var offsetDateTime1 = java.time.OffsetDateTime.now()
        val offsetDateTime2 = java.time.OffsetDateTime.now()
        if offsetDateTime1 < offsetDateTime2 | offsetDateTime1 > offsetDateTime2 | offsetDateTime1 ≥ offsetDateTime2 | offsetDateTime1 ≤ offsetDateTime2 
            offsetDateTime1 = offsetDateTime2

        var zonedDateTime1 = java.time.ZonedDateTime.now()
        val zonedDateTime2 = java.time.ZonedDateTime.now()
        if zonedDateTime1 < zonedDateTime2 | zonedDateTime1 > zonedDateTime2 | zonedDateTime1 ≥ zonedDateTime2 | zonedDateTime1 ≤ zonedDateTime2 
            zonedDateTime1 = zonedDateTime2

        var hijrahDate1 = java.time.chrono.HijrahDate.now()
        val hijrahDate2 = java.time.chrono.HijrahDate.now()
        if hijrahDate1 < hijrahDate2 | hijrahDate1 > hijrahDate2 | hijrahDate1 ≥ hijrahDate2 | hijrahDate1 ≤ hijrahDate2 
            hijrahDate1 = hijrahDate2

        var japaneseDate1 = java.time.chrono.JapaneseDate.now()
        val japaneseDate2 = java.time.chrono.JapaneseDate.now()
        if japaneseDate1 < japaneseDate2 | japaneseDate1 > japaneseDate2 | japaneseDate1 ≥ japaneseDate2 | japaneseDate1 ≤ japaneseDate2 
            japaneseDate1 = japaneseDate2

        var minguoDate1 = java.time.chrono.MinguoDate.now()
        val minguoDate2 = java.time.chrono.MinguoDate.now()
        if minguoDate1 < minguoDate2 | minguoDate1 > minguoDate2 | minguoDate1 ≥ minguoDate2 | minguoDate1 ≤ minguoDate2 
            minguoDate1 = minguoDate2

        var thaiBuddhistDate1 = java.time.chrono.ThaiBuddhistDate.now()
        val thaiBuddhistDate2 = java.time.chrono.ThaiBuddhistDate.now()
        if thaiBuddhistDate1 < thaiBuddhistDate2 | thaiBuddhistDate1 > thaiBuddhistDate2 | thaiBuddhistDate1 ≥ thaiBuddhistDate2 | thaiBuddhistDate1 ≤ thaiBuddhistDate2 
            thaiBuddhistDate1 = thaiBuddhistDate2
    }
}