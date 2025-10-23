# Const (State field: const)

### Const
Folds static final fields into Kotlin-style const declarations.

#### Example: ConstTestData

examples/data/ConstTestData.java:
```java
    static final Pattern PATTERN = Pattern.compile(".*");
    static final Pattern PATTERN_STATIC_IMPORT = compile(".*");

// ...
    protected final static String PROTECTED_FINAL_STATIC_VAR = "";

    static public final String STATIC_PUBLIC_FINAL_VAR = "";
// ...
    static final public String STATIC_FINAL_PUBLIC_VAR = "";
```

folded/ConstTestData-folded.java:
```java
    default const PATTERN = Pattern.compile(".*");
    default const Pattern PATTERN_STATIC_IMPORT = compile(".*");

// ...
    protected const PROTECTED_FINAL_STATIC_VAR = "";

    const STATIC_PUBLIC_FINAL_VAR = "";
// ...
    const STATIC_FINAL_PUBLIC_VAR = "";
```

Highlights ConstTestData with const.
Removes boilerplate while preserving behavior.

#### Example: ConstructorReferenceNotationWithConstTestData

examples/data/ConstructorReferenceNotationWithConstTestData.java:
```java
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
        public static final ConstClass SELF_SUB = new SubConstClass();
        public static final ConstClass SELF_SUB_ANN = new SubConstClass() {
// ...
        private static final HashMap<String, String> MAP = new HashMap<>();
        private static final HashMap<String, String> MAP2 = new HashMap<String, String>();
        private static final Map<String, String> MAP3 = new HashMap<>();
        private static final Map<String, String> MAP_TREE = new TreeMap<>();
        private static final Map<String, String> MAP4 = Map.of();
```

folded/ConstructorReferenceNotationWithConstTestData-folded.java:
```java
        const ConstClass SELF = ::new;
        const ConstClass SELF_ANN = ::new{};
        const ConstClass SELF_SUB = new SubConstClass();
        const ConstClass SELF_SUB_ANN = new SubConstClass() {
// ...
        private const HashMap<String, String> MAP = ::new;
        private const HashMap<String, String> MAP2 = ::new;
        private const Map<String, String> MAP3 = new HashMap<>();
        private const Map<String, String> MAP_TREE = new TreeMap<>();
        private const Map<String, String> MAP4 = Map.of();
```

Highlights ConstructorReferenceNotationWithConstTestData with const.
Removes boilerplate while preserving behavior.

#### Example: ExperimentalTestData

examples/data/ExperimentalTestData.java:
```java
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
// ...
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
```

folded/ExperimentalTestData-folded.java:
```java
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].getBytes();
// ...
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
```

Highlights ExperimentalTestData with const.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `const`
Related features: (none)
