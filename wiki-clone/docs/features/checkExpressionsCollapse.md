# Check Expressions Collapse (State field: checkExpressionsCollapse)

### Check Expressions Collapse
Folds null checks and Elvis patterns into safe-call expressions.

#### Example: ElvisTestData

examples/data/ElvisTestData.java:
```java
        System.out.println(e != null ? e : "");
        System.out.println(e != null ? e.sayHello() : "");
// ...
        if (e != null) {
                e.get().sayHello();
        }
        if (e.get() != null) {
                e.get().sayHello();
        }
```

folded/ElvisTestData-folded.java:
```java
        System.out.println(e ?: "");
        System.out.println(e?.sayHello() ?: "");
// ...
        e?.get().sayHello();
        e.get()?.sayHello();
```

Highlights ElvisTestData with check expressions collapse.
Removes boilerplate while preserving behavior.

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

Highlights IfNullSafeData with check expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `checkExpressionsCollapse`
Related features: (none)
---

#### Folding catalogue

##### ElvisTestData mappings
| Before | After |
| --- | --- |
| `System.out.println(e != null ? e : "");` | `System.out.println(e ?: "");` |
| `System.out.println(e != null ? e.sayHello() : "");` | `System.out.println(e?.sayHello() ?: "");` |
| `System.out.println(e == null ? "" : e);` | `System.out.println(e ?: "");` |
| `if (e != null) { e.get().sayHello(); }` | `e?.get().sayHello();` |
| `if (e.get() != null) { e.get().sayHello(); }` | `e.get()?.sayHello();` |

##### IfNullSafeData mappings
| Before | After |
| --- | --- |
| `var threeChains = data != null && data.getData1() != null && data != null && data.getData1() != null && data != null && data != null && data.getData1() != null && data.getData1().isActive();` | `var threeChains = data?.data1 != null && data?.data1 != null && data != null && data?.data1?.active == true;` |
| `var notChain = data != null && !data.getData1().isActive();` | `var notChain = data != null && !data.data1.active;` |
| `var chain = data != null && data.getData1() != null && data.getData1().getData4() != null;` | `var chain = data?.data1?.data4 != null;` |
| `if (data != null && data.getData1() != null && data.getData1().getData2() != null && data.getData1().getData2().getData3() != null) {` | `if (data?.data1?.data2?.data3 != null) {` |
| `if (data != null && data.getData1() != null) {` | `if (data?.data1 != null) {` |
| `if (data != null && data.isActive()) {` | `if (data?.active == true) {` |
| `if (data != null && data.getData1() != null && data.getData1().getData2() != null && data.getData1().getData2().getData3() != null && data.getData1().getData2().getData3().getData4() != null && data != null && data != null && data.getData1() != null && !data.getData1().isActive()) {` | `if (data?.data1?.data2?.data3?.data4 != null && data != null && data?.data1?.active == false) {` |
| `boolean has = data != null && data.getData1() != null && data.getData1().getData2() != null && data.getData1().getData2().getData3() != null && data.getData1().getData2().getData3().getData4() != null;` | `boolean has = data?.data1?.data2?.data3?.data4 != null;` |
| `var active = data != null && data.getData1() != null && data.getData1().isActive();` | `var active = data?.data1?.active == true;` |
| `var inactive = data != null && !data.isActive();` | `var inactive = data?.active == false;` |
| `while (data != null && data.getData2() != null && !data.getData2().isActive()) {` | `while (data?.data2?.active == false) {` |
| `active = !data.getData1().isActive();` | `active = !data.data1.active;` |
| `if ((data != null && data.getData6() != null && data.getData6().isActive())) {` | `if ((data?.data6?.active == true)) {` |
| `if ((data != null && data.getData6() != null && !data.getData6().isActive())) {` | `if ((data?.data6?.active == false)) {` |
| `if ((flag || data != null && data.getData1() != null && data.getData1().isActive()) && (data != null && data.getData2() != null && data.getData2().isActive() || data != null && data.getData3() != null && data.getData3().isActive() && data.getData3().getData4() != null && data.getData3().getData4().isActive()) || (data != null && data.getData5() != null && data.getData5().isActive()) && (flag && flag || flag && data != null && data.getData6() != null && data.getData6().isActive())) {` | `if ((flag || data?.data1?.active == true) && (data?.data2?.active == true || data?.data3?.active == true && data.data3.data4?.active == true) || (data?.data5?.active == true) && (flag && flag || flag && data?.data6?.active == true)) {` |
| `if (data.getData1().getData2().getData3() != null && data.getData1().getData2().getData3().isActive()) {` | `if (data.data1.data2.data3?.active == true) {` |
| `if (data2.getData1().getData2() != null && data2.getData1().getData2().isActive()) {` | `if (data2.data1.data2?.active == true) {` |
| `if (data2.getData1() != null && data2.getData1().isActive()) {` | `if (data2.data1?.active == true) {` |
