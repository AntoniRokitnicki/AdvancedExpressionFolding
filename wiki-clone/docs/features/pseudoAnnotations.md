https://github.com/user-attachments/assets/53ad15ef-2c32-4fe4-a857-d36114d020aa

# Pseudo Annotations (State field: pseudoAnnotations)

### Pseudo Annotations
Folds entry points into an @Main pseudo-annotation.

#### Example: PseudoAnnotationsMainTestData

examples/data/PseudoAnnotationsMainTestData.java:
```java
package data;


// ...
    }

}
```

folded/PseudoAnnotationsMainTestData-folded.java:
```java
// No folded sample provided.
```

Highlights PseudoAnnotationsMainTestData with pseudo annotations.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `pseudoAnnotations`
Related features: (none)
---

#### Folding catalogue

| Before | After |
| --- | --- |
| `<pre>public static void main(String[] args) {
        int x = 0;
        staticMethod(x);
    }</pre>` | `@Main staticMethod(int x = 0)` |
| `<pre>public static void main(String[] args) {
        int param = 0;

        String s = "";
        new PseudoAnnotationsMainTestData(param).instanceMethod(s);
    }</pre>` | `@Main new PseudoAnnotationsMainTestData(0).instanceMethod("")` |
| `<pre>public static void main(String[] args) {
        int param = 0;
        System.out.println(new PseudoAnnotationsMainTestData(param).getValue());
    }</pre>` | `@Main System.out.println(new PseudoAnnotationsMainTestData(0).getValue())` |
| `<pre>public static void main(String[] args) {
        int param = 0;

        boolean b = false;
        char c = '\\0';
        byte by = 0;
        short s = 0;
        int i = 0;
        long l = 0;
        float f = 0.0f;
        double d = 0.0;
        new PseudoAnnotationsMainTestData(param).primitiveParams(b, c, by, s, i, l, f, d);
    }</pre>` | `@Main new PseudoAnnotationsMainTestData(0).primitiveParams(false, '\\0', 0, 0, 0, 0, 0.0f, 0.0)` |
| `<pre>public static void main(String[] args) {
        int param = 0;

        java.util.Date date = new java.util.Date();
        LocalDate ld = java.time.LocalDate.now();
        java.time.LocalDateTime ldt = java.time.LocalDateTime.now();
        ZonedDateTime zdt = java.time.ZonedDateTime.now();
        new PseudoAnnotationsMainTestData(param).dateParams(date, ld, ldt, zdt);
    }</pre>` | `@Main new PseudoAnnotationsMainTestData(0).dateParams(new java.util.Date(), java.time.LocalDate.now(), java.time.LocalDateTime.now(), java.time.ZonedDateTime.now())` |
| `<pre>public static void main(String[] args) {
        int param = 0;

        int[] arr = null;
        String[] varargs = new String[]{""};
        new PseudoAnnotationsMainTestData(param).arrayParams(arr, varargs);

        boolean[] array = null;
        Object[] objs = new Object[]{};
        new PseudoAnnotationsMainTestData(param).mixedParams(0, "", array, objs);
    }</pre>` | `@Main new PseudoAnnotationsMainTestData(0).arrayParams(null, new String[]{""});`<br>`@Main new PseudoAnnotationsMainTestData(0).mixedParams(0, "", null, new Object[]{})` |
| `<pre>public static void main(String[] args) {
        new PseudoAnnotationsMainTestData();
        new PseudoAnnotationsMainTestData(0);
        new PseudoAnnotationsMainTestData("", 0);
    }</pre>` | `@Main new PseudoAnnotationsMainTestData();`<br>`@Main new PseudoAnnotationsMainTestData(0);`<br>`@Main new PseudoAnnotationsMainTestData("", 0)` |
