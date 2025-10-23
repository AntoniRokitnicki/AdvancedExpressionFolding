[video](https://www.youtube.com/watch?v=zvpvhn7ISAw)


![Null-safe if folding showcased on chained access](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/56aa2dbb-0aa1-4143-a296-801ffb0668cd)


## example Structural Searches

### If Null Safe
Extends null-safe folding to if statements and guard clauses.

#### Example: IfNullSafeData

examples/data/IfNullSafeData.java:
```java
        var threeChains = data != null
                && data.getData1() != null
// ...
                && data.getData1() != null
                && data != null
                && data != null
                && data.getData1() != null
                && data.getData1().isActive();
```

folded/IfNullSafeData-folded.java:
```java
        var threeChains = data?.data1 != null
                && data?.data1 != null
// ...
                && data?.data1?.active == true;
```

Highlights IfNullSafeData with if null safe.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `ifNullSafe`
Related features: (none)
---

#### Folding catalogue

##### IfNullSafeData null-safe chains
| Before | After |
| --- | --- |
| `data != null && data.getData1() != null && data.getData1().getData2() != null && data.getData1().getData2().getData3() != null` | `data?.data1?.data2?.data3 != null` |
| `data != null && data.getData1() != null` | `data?.data1 != null` |
| `data != null && data.isActive()` | `data?.active == true` |
| `data != null && data.getData1() != null && data.getData1().getData2() != null && data.getData1().getData2().getData3().getData4() != null` | `data?.data1?.data2?.data3?.data4 != null` |
| `data != null && !data.getData1().isActive()` | `data?.data1?.active == false` |
| `data != null && !data.isActive()` | `data?.active == false` |
| `while (data != null && data.getData2() != null && !data.getData2().isActive()) {` | `while (data?.data2?.active == false) {` |
| `if ((data != null && data.getData6() != null && data.getData6().isActive()))` | `if ((data?.data6?.active == true))` |
| `if ((data != null && data.getData6() != null && !data.getData6().isActive()))` | `if ((data?.data6?.active == false))` |
| `if ((flag || data != null && data.getData1() != null && data.getData1().isActive()) && (data != null && data.getData2() != null && data.getData2().isActive() || data != null && data.getData3() != null && data.getData3().isActive() && data.getData3().getData4() != null && data.getData3().getData4().isActive()) || (data != null && data.getData5() != null && data.getData5().isActive()) && (flag && flag || flag && data != null && data.getData6() != null && data.getData6().isActive()))` | `if ((flag || data?.data1?.active == true) && (data?.data2?.active == true || data?.data3?.active == true && data.data3.data4?.active == true) || (data?.data5?.active == true) && (flag && flag || flag && data?.data6?.active == true))` |
