#### example:

### Method Default Parameters
Folds overloads into default-parameter annotations.

#### Example: MethodDefaultParametersTestData

examples/data/MethodDefaultParametersTestData.java:
```java
        public String applySort1(String criterionName, boolean descending) {
// ...
        public String applySort1() {
            return applySort1("DESC", false);
        }
```

folded/MethodDefaultParametersTestData-folded.java:
```java
        public String applySort1(String criterionName = "DESC", boolean descending = false) {
// ...
        
```

Highlights MethodDefaultParametersTestData with method default parameters.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `methodDefaultParameters`
Related features: (none)
---

#### Folding catalogue

##### MethodDefaultParametersTestData mappings
| Before | After |
| --- | --- |
| `<pre>public String applySort1(String criterionName, boolean descending) {
            return criterionName;
        }
        public String applySort1() {
            return applySort1("DESC", false);
        }</pre>` | `<pre>public String applySort1(String criterionName = "DESC", boolean descending = false) {
            return criterionName;
        }</pre>` |
| `<pre>public String applySort2(String criterionName, boolean descending) {
            return criterionName;
        }
        public String applySort2(String criterionName) {
            return applySort2(criterionName, System.getProperty("sort-desc") != null);
        }</pre>` | `<pre>public String applySort2(String criterionName, boolean descending = System.getProperty("sort-desc") != null) {
            return criterionName;
        }</pre>` |
| `<pre>public String multipleDefaults(String name, int count, boolean flag) {
            return name + count + flag;
        }
        public String multipleDefaults(String name, int count) {
            return multipleDefaults(name, count, false);
        }
        public String multipleDefaults(String name) {
            return multipleDefaults(name, 10, false);
        }</pre>` | `<pre>public String multipleDefaults(String name, int count = 10, boolean flag = false) {
            return name + count + flag;
        }</pre>` |
| `<pre>public String chainedDefaults(String name, int count, boolean flag) {
            return name + count + flag;
        }
        public String chainedDefaults(String name, int count) {
            return chainedDefaults(name, count, true);
        }</pre>` | `<pre>public String chainedDefaults(String name, int count, boolean flag = true) {
            return name + count + flag;
        }</pre>` |
| `<pre>public String expressionDefaults(String name, int count) {
            return name + count;
        }
        public String expressionDefaults(String name) {
            return expressionDefaults(name, "test".length() + 2);
        }</pre>` | `<pre>public String expressionDefaults(String name, int count = "test".length() + 2) {
            return name + count;
        }</pre>` |
| `<pre>public static String staticWithDefaults(String name, boolean flag) {
            return name + flag;
        }
        public static String staticWithDefaults(String name) {
            return staticWithDefaults(name, true);
        }</pre>` | `<pre>public static String staticWithDefaults(String name, boolean flag = true) {
            return name + flag;
        }</pre>` |
| `<pre>public String mixedTypes(String text, int number, boolean flag, double value) {
            return text + number + flag + value;
        }
        public String mixedTypes(String text, int number, boolean flag) {
            return mixedTypes(text, number, flag, 1.0);
        }</pre>` | `<pre>public String mixedTypes(String text, int number, boolean flag, double value = 1.0) {
            return text + number + flag + value;
        }</pre>` |
| `<pre>public String varargMethod(String prefix, String... items) {
            return prefix + String.join(",", items);
        }
        public String varargMethod(String prefix) {
            return varargMethod(prefix, "default");
        }</pre>` | `<pre>public String varargMethod(String prefix, String... items = "default") {
            return prefix + String.join(",", items);
        }</pre>` |
| `<pre>public String differentParamNames(String name, boolean enabled) {
            return name + enabled;
        }
        public String differentParamNames(String title) {
            return differentParamNames(title, true);
        }</pre>` | `<pre>public String differentParamNames(String name, boolean enabled = true) {
            return name + enabled;
        }</pre>` |
| `<pre>public <T> String genericMethod(T item, boolean flag) {
            return item.toString() + flag;
        }
        public <T> String genericMethod(T item) {
            return genericMethod(item, false);
        }</pre>` | `<pre>public <T> String genericMethod(T item, boolean flag = false) {
            return item + flag;
        }</pre>` |
