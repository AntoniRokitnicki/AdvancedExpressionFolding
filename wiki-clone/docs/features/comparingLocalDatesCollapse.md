# Local date comparisons

## Overview

Folds java.time isBefore/isAfter checks into readable date comparisons.

## Configuration

- **Toggle ID:** `comparingLocalDatesCollapse`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

### Comparing Local Dates Collapse

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


---
### Folding catalogue

#### LocalDateTestData

##### Scenario 1

**Before**
```java
        boolean isBefore = d1.isBefore(d2);
        boolean isAfter = d1.isAfter(d2);
        boolean d2SmallerOrEqualD1 = !d1.isBefore(d2);;
        boolean d1SmallerOrEqualD2 = !d1.isAfter(d2);
```

**After**
```java
        boolean isBefore = d1 < d2;
        boolean isAfter = d1 > d2;
        boolean d2SmallerOrEqualD1 = d1 ≥ d2;;
        boolean d1SmallerOrEqualD2 = d1 ≤ d2;
```


##### Scenario 2

**Before**
```java
        if (date1.isBefore(date2) | date1.isAfter(date2) | !date1.isBefore(date2) | !date1.isAfter(date2)) {
```

**After**
```java
        if (date1 < date2 | date1 > date2 | date1 ≥ date2 | date1 ≤ date2) {
```


##### Scenario 3

**Before**
```java
        if (dateTime1.isBefore(dateTime2) | dateTime1.isAfter(dateTime2) | !dateTime1.isBefore(dateTime2) | !dateTime1.isAfter(dateTime2)) {
```

**After**
```java
        if (dateTime1 < dateTime2 | dateTime1 > dateTime2 | dateTime1 ≥ dateTime2 | dateTime1 ≤ dateTime2) {
```


##### Scenario 4

**Before**
```java
        if (time1.isBefore(time2) | time1.isAfter(time2) | !time1.isBefore(time2) | !time1.isAfter(time2)) {
```

**After**
```java
        if (time1 < time2 | time1 > time2 | time1 ≥ time2 | time1 ≤ time2) {
```


##### Scenario 5

**Before**
```java
        if (offsetTime1.isBefore(offsetTime2) | offsetTime1.isAfter(offsetTime2) | !offsetTime1.isBefore(offsetTime2) | !offsetTime1.isAfter(offsetTime2)) {
```

**After**
```java
        if (offsetTime1 < offsetTime2 | offsetTime1 > offsetTime2 | offsetTime1 ≥ offsetTime2 | offsetTime1 ≤ offsetTime2) {
```


##### Scenario 6

**Before**
```java
        if (offsetDateTime1.isBefore(offsetDateTime2) | offsetDateTime1.isAfter(offsetDateTime2) | !offsetDateTime1.isBefore(offsetDateTime2) | !offsetDateTime1.isAfter(offsetDateTime2)) {
```

**After**
```java
        if (offsetDateTime1 < offsetDateTime2 | offsetDateTime1 > offsetDateTime2 | offsetDateTime1 ≥ offsetDateTime2 | offsetDateTime1 ≤ offsetDateTime2) {
```


##### Scenario 7

**Before**
```java
        if (zonedDateTime1.isBefore(zonedDateTime2) | zonedDateTime1.isAfter(zonedDateTime2) | !zonedDateTime1.isBefore(zonedDateTime2) | !zonedDateTime1.isAfter(zonedDateTime2)) {
```

**After**
```java
        if (zonedDateTime1 < zonedDateTime2 | zonedDateTime1 > zonedDateTime2 | zonedDateTime1 ≥ zonedDateTime2 | zonedDateTime1 ≤ zonedDateTime2) {
```


##### Scenario 8

**Before**
```java
        if (hijrahDate1.isBefore(hijrahDate2) | hijrahDate1.isAfter(hijrahDate2) | !hijrahDate1.isBefore(hijrahDate2) | !hijrahDate1.isAfter(hijrahDate2)) {
```

**After**
```java
        if (hijrahDate1 < hijrahDate2 | hijrahDate1 > hijrahDate2 | hijrahDate1 ≥ hijrahDate2 | hijrahDate1 ≤ hijrahDate2) {
```


##### Scenario 9

**Before**
```java
        if (japaneseDate1.isBefore(japaneseDate2) | japaneseDate1.isAfter(japaneseDate2) | !japaneseDate1.isBefore(japaneseDate2) | !japaneseDate1.isAfter(japaneseDate2)) {
```

**After**
```java
        if (japaneseDate1 < japaneseDate2 | japaneseDate1 > japaneseDate2 | japaneseDate1 ≥ japaneseDate2 | japaneseDate1 ≤ japaneseDate2) {
```


##### Scenario 10

**Before**
```java
        if (minguoDate1.isBefore(minguoDate2) | minguoDate1.isAfter(minguoDate2) | !minguoDate1.isBefore(minguoDate2) | !minguoDate1.isAfter(minguoDate2)) {
```

**After**
```java
        if (minguoDate1 < minguoDate2 | minguoDate1 > minguoDate2 | minguoDate1 ≥ minguoDate2 | minguoDate1 ≤ minguoDate2) {
```


##### Scenario 11

**Before**
```java
        if (thaiBuddhistDate1.isBefore(thaiBuddhistDate2) | thaiBuddhistDate1.isAfter(thaiBuddhistDate2) | !thaiBuddhistDate1.isBefore(thaiBuddhistDate2) | !thaiBuddhistDate1.isAfter(thaiBuddhistDate2)) {
```

**After**
```java
        if (thaiBuddhistDate1 < thaiBuddhistDate2 | thaiBuddhistDate1 > thaiBuddhistDate2 | thaiBuddhistDate1 ≥ thaiBuddhistDate2 | thaiBuddhistDate1 ≤ thaiBuddhistDate2) {
```


##### Scenario 12

**Before**
```java
        if (utilDate1.before(utilDate2) | utilDate1.after(utilDate2) | !utilDate1.before(utilDate2) | !utilDate1.after(utilDate2)) {
```

**After**
```java
        if (utilDate1 < utilDate2 | utilDate1 > utilDate2 | utilDate1 ≥ utilDate2 | utilDate1 ≤ utilDate2) {
```


##### Scenario 13

**Before**
```java
        if (sqlDate1.before(sqlDate2) | sqlDate1.after(sqlDate2) | !sqlDate1.before(sqlDate2) | !sqlDate1.after(sqlDate2)) {
```

**After**
```java
        if (sqlDate1 < sqlDate2 | sqlDate1 > sqlDate2 | sqlDate1 ≥ sqlDate2 | sqlDate1 ≤ sqlDate2) {
```


##### Scenario 14

**Before**
```java
        if (timestamp1.before(timestamp2) | timestamp1.after(timestamp2) | !timestamp1.before(timestamp2) | !timestamp1.after(timestamp2)) {
```

**After**
```java
        if (timestamp1 < timestamp2 | timestamp1 > timestamp2 | timestamp1 ≥ timestamp2 | timestamp1 ≤ timestamp2) {
```


##### Scenario 15

**Before**
```java
        if (cal1.before(cal2) | cal1.after(cal2) | !cal1.before(cal2) | !cal1.after(cal2)) {
```

**After**
```java
        if (cal1 < cal2 | cal1 > cal2 | cal1 ≥ cal2 | cal1 ≤ cal2) {
```


##### Scenario 16

**Before**
```java
        if (customObj1.before(customObj2) | customObj1.after(customObj2) | !customObj1.before(customObj2) | !customObj1.after(customObj2)) {
```

**After**
```java
        if (customObj1 < customObj2 | customObj1 > customObj2 | customObj1 ≥ customObj2 | customObj1 ≤ customObj2) {
```


##### Scenario 17

**Before**
```java
        if (customObj2_1.isBefore(customObj2_2) | customObj2_1.isAfter(customObj2_2) | !customObj2_1.isBefore(customObj2_2) | !customObj2_1.isAfter(customObj2_2)) {
```

**After**
```java
        if (customObj2_1 < customObj2_2 | customObj2_1 > customObj2_2 | customObj2_1 ≥ customObj2_2 | customObj2_1 ≤ customObj2_2) {
```


##### Scenario 18

**Before**
```java
            return this.minguoDate.isBefore(other.minguoDate);
```

**After**
```java
            return this.minguoDate < other.minguoDate;
```


##### Scenario 19

**Before**
```java
            return this.minguoDate.isAfter(other.minguoDate);
```

**After**
```java
            return this.minguoDate > other.minguoDate;
```
