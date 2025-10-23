## comparingLocalDatesCollapse

### Comparing Local Dates Collapse
Folds java.time isBefore/isAfter checks into readable date comparisons.

#### Example: LocalDateTestData

examples/data/LocalDateTestData.java:
```java
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);;
        boolean d1SmallerOrEqualD2 = !d1.isAfter(d2);
// ...
        if (date1.isBefore(date2) | date1.isAfter(date2) | !date1.isBefore(date2) | !date1.isAfter(date2)) {
```

folded/LocalDateTestData-folded.java:
```java
        boolean isBefore = d1 < d2;
        boolean isAfter = d1 > d2;
        boolean d2SmallerOrEqualD1 = d1 ≥ d2;;
        boolean d1SmallerOrEqualD2 = d1 ≤ d2;
// ...
        if (date1 < date2 | date1 > date2 | date1 ≥ date2 | date1 ≤ date2) {
```

Highlights LocalDateTestData with comparing local dates collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `comparingLocalDatesCollapse`
Related features: (none)
---

#### Folding catalogue

##### LocalDateTestData comparisons
| Before | After |
| --- | --- |
| `boolean isBefore = d1.isBefore(d2);` | `boolean isBefore = d1 < d2;` |
| `boolean isAfter = d1.isAfter(d2);` | `boolean isAfter = d1 > d2;` |
| `boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);;` | `boolean d2SmallerOrEqualD1 = d1 ≥ d2;;` |
| `boolean d1SmallerOrEqualD2 = !d1.isAfter(d2);` | `boolean d1SmallerOrEqualD2 = d1 ≤ d2;` |
| `if (date1.isBefore(date2) | date1.isAfter(date2) | !date1.isBefore(date2) | !date1.isAfter(date2)) {` | `if (date1 < date2 | date1 > date2 | date1 ≥ date2 | date1 ≤ date2) {` |
| `if (dateTime1.isBefore(dateTime2) | dateTime1.isAfter(dateTime2) | !dateTime1.isBefore(dateTime2) | !dateTime1.isAfter(dateTime2)) {` | `if (dateTime1 < dateTime2 | dateTime1 > dateTime2 | dateTime1 ≥ dateTime2 | dateTime1 ≤ dateTime2) {` |
| `if (time1.isBefore(time2) | time1.isAfter(time2) | !time1.isBefore(time2) | !time1.isAfter(time2)) {` | `if (time1 < time2 | time1 > time2 | time1 ≥ time2 | time1 ≤ time2) {` |
| `if (offsetTime1.isBefore(offsetTime2) | offsetTime1.isAfter(offsetTime2) | !offsetTime1.isBefore(offsetTime2) | !offsetTime1.isAfter(offsetTime2)) {` | `if (offsetTime1 < offsetTime2 | offsetTime1 > offsetTime2 | offsetTime1 ≥ offsetTime2 | offsetTime1 ≤ offsetTime2) {` |
| `if (offsetDateTime1.isBefore(offsetDateTime2) | offsetDateTime1.isAfter(offsetDateTime2) | !offsetDateTime1.isBefore(offsetDateTime2) | !offsetDateTime1.isAfter(offsetDateTime2)) {` | `if (offsetDateTime1 < offsetDateTime2 | offsetDateTime1 > offsetDateTime2 | offsetDateTime1 ≥ offsetDateTime2 | offsetDateTime1 ≤ offsetDateTime2) {` |
| `if (zonedDateTime1.isBefore(zonedDateTime2) | zonedDateTime1.isAfter(zonedDateTime2) | !zonedDateTime1.isBefore(zonedDateTime2) | !zonedDateTime1.isAfter(zonedDateTime2)) {` | `if (zonedDateTime1 < zonedDateTime2 | zonedDateTime1 > zonedDateTime2 | zonedDateTime1 ≥ zonedDateTime2 | zonedDateTime1 ≤ zonedDateTime2) {` |
| `if (hijrahDate1.isBefore(hijrahDate2) | hijrahDate1.isAfter(hijrahDate2) | !hijrahDate1.isBefore(hijrahDate2) | !hijrahDate1.isAfter(hijrahDate2)) {` | `if (hijrahDate1 < hijrahDate2 | hijrahDate1 > hijrahDate2 | hijrahDate1 ≥ hijrahDate2 | hijrahDate1 ≤ hijrahDate2) {` |
| `if (japaneseDate1.isBefore(japaneseDate2) | japaneseDate1.isAfter(japaneseDate2) | !japaneseDate1.isBefore(japaneseDate2) | !japaneseDate1.isAfter(japaneseDate2)) {` | `if (japaneseDate1 < japaneseDate2 | japaneseDate1 > japaneseDate2 | japaneseDate1 ≥ japaneseDate2 | japaneseDate1 ≤ japaneseDate2) {` |
| `if (minguoDate1.isBefore(minguoDate2) | minguoDate1.isAfter(minguoDate2) | !minguoDate1.isBefore(minguoDate2) | !minguoDate1.isAfter(minguoDate2)) {` | `if (minguoDate1 < minguoDate2 | minguoDate1 > minguoDate2 | minguoDate1 ≥ minguoDate2 | minguoDate1 ≤ minguoDate2) {` |
| `if (thaiBuddhistDate1.isBefore(thaiBuddhistDate2) | thaiBuddhistDate1.isAfter(thaiBuddhistDate2) | !thaiBuddhistDate1.isBefore(thaiBuddhistDate2) | !thaiBuddhistDate1.isAfter(thaiBuddhistDate2)) {` | `if (thaiBuddhistDate1 < thaiBuddhistDate2 | thaiBuddhistDate1 > thaiBuddhistDate2 | thaiBuddhistDate1 ≥ thaiBuddhistDate2 | thaiBuddhistDate1 ≤ thaiBuddhistDate2) {` |
| `if (utilDate1.before(utilDate2) | utilDate1.after(utilDate2) | !utilDate1.before(utilDate2) | !utilDate1.after(utilDate2)) {` | `if (utilDate1 < utilDate2 | utilDate1 > utilDate2 | utilDate1 ≥ utilDate2 | utilDate1 ≤ utilDate2) {` |
| `if (sqlDate1.before(sqlDate2) | sqlDate1.after(sqlDate2) | !sqlDate1.before(sqlDate2) | !sqlDate1.after(sqlDate2)) {` | `if (sqlDate1 < sqlDate2 | sqlDate1 > sqlDate2 | sqlDate1 ≥ sqlDate2 | sqlDate1 ≤ sqlDate2) {` |
| `if (timestamp1.before(timestamp2) | timestamp1.after(timestamp2) | !timestamp1.before(timestamp2) | !timestamp1.after(timestamp2)) {` | `if (timestamp1 < timestamp2 | timestamp1 > timestamp2 | timestamp1 ≥ timestamp2 | timestamp1 ≤ timestamp2) {` |
| `if (cal1.before(cal2) | cal1.after(cal2) | !cal1.before(cal2) | !cal1.after(cal2)) {` | `if (cal1 < cal2 | cal1 > cal2 | cal1 ≥ cal2 | cal1 ≤ cal2) {` |
| `if (customObj1.before(customObj2) | customObj1.after(customObj2) | !customObj1.before(customObj2) | !customObj1.after(customObj2)) {` | `if (customObj1 < customObj2 | customObj1 > customObj2 | customObj1 ≥ customObj2 | customObj1 ≤ customObj2) {` |
| `if (customObj2_1.isBefore(customObj2_2) | customObj2_1.isAfter(customObj2_2) | !customObj2_1.isBefore(customObj2_2) | !customObj2_1.isAfter(customObj2_2)) {` | `if (customObj2_1 < customObj2_2 | customObj2_1 > customObj2_2 | customObj2_1 ≥ customObj2_2 | customObj2_1 ≤ customObj2_2) {` |
| `return this.minguoDate.isBefore(other.minguoDate);` | `return this.minguoDate < other.minguoDate;` |
| `return this.minguoDate.isAfter(other.minguoDate);` | `return this.minguoDate > other.minguoDate;` |
