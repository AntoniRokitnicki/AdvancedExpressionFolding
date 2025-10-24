# Range Expressions Collapse

## Overview

Folds indexed loops into Kotlin-style range expressions.


## Configuration

- **Toggle ID:** `rangeExpressionsCollapse`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

#### Example: ForRangeTestData

examples/data/ForRangeTestData.java:
```java
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
// ...
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
```

folded/ForRangeTestData-folded.java:
```java
                for ((int i, String arg) : args) {
// ...
                for (String arg : args) {
```

Highlights ForRangeTestData with range expressions collapse.
Removes boilerplate while preserving behavior.


---
### Folding catalogue

#### ForRangeTestData

##### Scenario 1

**Before**
```java
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
```

**After**
```java
                for ((int i, String arg) : args) {
```


##### Scenario 2

**Before**
```java
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
```

**After**
```java
                for (String arg : args) {
```


##### Scenario 3

**Before**
```java
                for (int i = 0; i < args.length; i++) {
```

**After**
```java
                for (int i : [0, args.length)) {
```


##### Scenario 4

**Before**
```java
                for (int i = 0; i <= args.length - 1; i++) {
```

**After**
```java
                for (int i : [0, args.length - 1]) {
```


##### Scenario 5

**Before**
```java
                for (int i = 0; i < list.size(); i++) {
                        String a = list
                                .get(i);
```

**After**
```java
                for (String a : list) {
```


##### Scenario 6

**Before**
```java
                for (int i = 0; i < list.size(); i++) {
                        String a = list
                                .get(i);
```

**After**
```java
                for ((int i, String a) : list) {
```


##### Scenario 7

**Before**
```java
                if (args.length > 0 && args.length < 2) {
```

**After**
```java
                if (args.length in (0, 2)) {
```
