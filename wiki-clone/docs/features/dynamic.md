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

#### Folding catalogue

##### DynamicTestData mappings
| Before | After |
| --- | --- |
| `public static void staticMethod(Data data) {` | `public static void changedStaticMethod(Data data) {` |
| `.normalMethod(` | `.changedNormalMethod(` |
| `staticMethod(` | `changedStaticMethod(` |
| `new DynamicTestData().staticMethod(` | `new DynamicTestData().changedStaticMethod(` |
| `data.getData()` | `data.data` |
| `staticMethod(data.getData());` | `changedStaticMethod(data.data);` |
| `private String normalMethod(String args) {` | `private String changedNormalMethod(String args) {` |
| `return normalMethod(args.substring(1));` | `return changedNormalMethod(args.substring(1));` |
| `private static String staticMethod(String args) {` | `private static String changedStaticMethod(String args) {` |
