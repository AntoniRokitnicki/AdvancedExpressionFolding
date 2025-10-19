## dynamic

### Dynamic names for methods based on IntelliJ config directory (PathManager.getConfigDir())/advanced-expression-folding/dynamic-ajf2.toml
Applies dynamic naming to methods based on a configuration file stored under the IntelliJ config directory.

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

Highlights DynamicTestData with dynamic names for methods based on the file located at PathManager.getConfigDir()/advanced-expression-folding/dynamic-ajf2.toml.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `dynamic`
Related features: (none)
