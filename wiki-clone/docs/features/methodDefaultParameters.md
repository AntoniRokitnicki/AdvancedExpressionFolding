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
