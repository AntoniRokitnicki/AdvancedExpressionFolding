## dynamic

### Dynamic names for methods based on $user.home/dynamic-ajf2.toml
Applies dynamic naming to methods based on a configuration file.

#### Example: DynamicTestData

examples/data/DynamicTestData.java:
```java
    public static void staticMethod(Data data) {
// ...
                .normalMethod(
                        staticMethod(
```

folded/DynamicTestData-folded.java:
```java
    public static void changedStaticMethod(Data data) {
// ...
                .changedNormalMethod(
                        changedStaticMethod(
```

Highlights DynamicTestData with dynamic names for methods based on $user.home/dynamic-ajf2.toml.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `dynamic`
Related features: (none)

---
### Folding catalogue

#### DynamicTestData

##### Scenario 1

**Before**
```java
    public static void staticMethod(Data data) {
```

**After**
```java
    public static void changedStaticMethod(Data data) {
```


##### Scenario 2

**Before**
```java
                .normalMethod(
                        staticMethod(
```

**After**
```java
                .changedNormalMethod(
                        changedStaticMethod(
```


##### Scenario 3

**Before**
```java
                                        .normalMethod(
                                                new DynamicTestData().staticMethod(
                                                        data.getData()
```

**After**
```java
                                        .changedNormalMethod(
                                                new DynamicTestData().changedStaticMethod(
                                                        data.data
```


##### Scenario 4

**Before**
```java
        staticMethod(data.getData());
```

**After**
```java
        changedStaticMethod(data.data);
```


##### Scenario 5

**Before**
```java
    private String normalMethod(String args) {
        return normalMethod(args.substring(1));
```

**After**
```java
    private String changedNormalMethod(String args) {
        return changedNormalMethod(args.substring(1));
```


##### Scenario 6

**Before**
```java
    private static String staticMethod(String args) {
```

**After**
```java
    private static String changedStaticMethod(String args) {
```

