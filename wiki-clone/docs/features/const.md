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

---
### Folding catalogue

#### ConstTestData

##### Scenario 1

**Before**
```java
    static final Pattern PATTERN = Pattern.compile(".*");
    static final Pattern PATTERN_STATIC_IMPORT = compile(".*");

    static final EOrder ENUM = EOrder.FIRST;
    static final Orderable ENUM_BY_INTERFACE = EOrder.FIRST;
    static final EOrder ENUM_STATIC_IMPORT = SECOND;

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    private static final String PRIVATE_STATIC_FINAL_VAR = "";
    protected static final String PROTECTED_STATIC_FINAL_VAR = "";
    static final String DEFAULT_STATIC_FINAL_VAR = "";

    public static final String STRING_JOINER = "" + "1";
    public static final String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;
    public static final String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";
    public static final String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

    public final static String PUBLIC_FINAL_STATIC_VAR = "";
    private final static String PRIVATE_FINAL_STATIC_VAR = "";
    protected final static String PROTECTED_FINAL_STATIC_VAR = "";

    static public final String STATIC_PUBLIC_FINAL_VAR = "";
```

**After**
```java
    default const PATTERN = Pattern.compile(".*");
    default const Pattern PATTERN_STATIC_IMPORT = compile(".*");

    default econst ENUM = EOrder.FIRST;
    default const Orderable ENUM_BY_INTERFACE = EOrder.FIRST;
    default econst EOrder ENUM_STATIC_IMPORT = SECOND;

    const PUBLIC_STATIC_FINAL_VAR = "";
    private const PRIVATE_STATIC_FINAL_VAR = "";
    protected const PROTECTED_STATIC_FINAL_VAR = "";
    default const DEFAULT_STATIC_FINAL_VAR = "";

    const String STRING_JOINER = "" + "1";
    const String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;
    const String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";
    const String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

    const PUBLIC_FINAL_STATIC_VAR = "";
    private const PRIVATE_FINAL_STATIC_VAR = "";
    protected const PROTECTED_FINAL_STATIC_VAR = "";

    const STATIC_PUBLIC_FINAL_VAR = "";
```


##### Scenario 2

**Before**
```java
    static final public String STATIC_FINAL_PUBLIC_VAR = "";
```

**After**
```java
    const STATIC_FINAL_PUBLIC_VAR = "";
```


##### Scenario 3

**Before**
```java
    final public static String FINAL_PUBLIC_STATIC_VAR = "";
```

**After**
```java
    const FINAL_PUBLIC_STATIC_VAR = "";
```


##### Scenario 4

**Before**
```java
    final static public String FINAL_STATIC_PUBLIC_VAR = "";
```

**After**
```java
    const FINAL_STATIC_PUBLIC_VAR = "";
```


##### Scenario 5

**Before**
```java
    static final String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    static final int VERSION = 1;
    static final int VERSION_REF = VERSION;
```

**After**
```java
    default const String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    default const VERSION = 1;
    default const VERSION_REF = VERSION;
```


##### Scenario 6

**Before**
```java
        static final Pattern PATTERN = Pattern.compile(".*");

        @Deprecated
        static final Pattern PATTERN_STATIC_IMPORT = compile(".*");

        @Deprecated
        static final EOrder ENUM = EOrder.FIRST;

        @Deprecated
        static final Orderable ENUM_BY_INTERFACE = EOrder.FIRST;

        @Deprecated
        static final EOrder ENUM_STATIC_IMPORT = SECOND;

        @Deprecated
        public static final String PUBLIC_STATIC_FINAL_VAR = "";

        @Deprecated
        private static final String PRIVATE_STATIC_FINAL_VAR = "";

        @Deprecated
        protected static final String PROTECTED_STATIC_FINAL_VAR = "";

        @Deprecated
        static final String DEFAULT_STATIC_FINAL_VAR = "";

        @Deprecated
        public static final String STRING_JOINER = "" + "1";

        @Deprecated
        public static final String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        public static final String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";

        @Deprecated
        public static final String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

        @Deprecated
        public final static String PUBLIC_FINAL_STATIC_VAR = "";

        @Deprecated
        private final static String PRIVATE_FINAL_STATIC_VAR = "";

        @Deprecated
        protected final static String PROTECTED_FINAL_STATIC_VAR = "";

        @Deprecated
        static public final String STATIC_PUBLIC_FINAL_VAR = "";
```

**After**
```java
        default const PATTERN = Pattern.compile(".*");

        @Deprecated
        default const Pattern PATTERN_STATIC_IMPORT = compile(".*");

        @Deprecated
        default econst ENUM = EOrder.FIRST;

        @Deprecated
        default const Orderable ENUM_BY_INTERFACE = EOrder.FIRST;

        @Deprecated
        default econst EOrder ENUM_STATIC_IMPORT = SECOND;

        @Deprecated
        const PUBLIC_STATIC_FINAL_VAR = "";

        @Deprecated
        private const PRIVATE_STATIC_FINAL_VAR = "";

        @Deprecated
        protected const PROTECTED_STATIC_FINAL_VAR = "";

        @Deprecated
        default const DEFAULT_STATIC_FINAL_VAR = "";

        @Deprecated
        const String STRING_JOINER = "" + "1";

        @Deprecated
        const String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        const String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";

        @Deprecated
        const String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;

        @Deprecated
        const PUBLIC_FINAL_STATIC_VAR = "";

        @Deprecated
        private const PRIVATE_FINAL_STATIC_VAR = "";

        @Deprecated
        protected const PROTECTED_FINAL_STATIC_VAR = "";

        @Deprecated
        const STATIC_PUBLIC_FINAL_VAR = "";
```


##### Scenario 7

**Before**
```java
        static final public String STATIC_FINAL_PUBLIC_VAR = "";
```

**After**
```java
        const STATIC_FINAL_PUBLIC_VAR = "";
```


##### Scenario 8

**Before**
```java
        final public static String FINAL_PUBLIC_STATIC_VAR = "";
```

**After**
```java
        const FINAL_PUBLIC_STATIC_VAR = "";
```


##### Scenario 9

**Before**
```java
        final static public String FINAL_STATIC_PUBLIC_VAR = "";
```

**After**
```java
        const FINAL_STATIC_PUBLIC_VAR = "";
```


##### Scenario 10

**Before**
```java
        static final String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        static final int VERSION = 1;

        @Deprecated
        static final int VERSION_REF = VERSION;
```

**After**
```java
        default const String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

        @Deprecated
        default const VERSION = 1;

        @Deprecated
        default const VERSION_REF = VERSION;
```


#### ConstructorReferenceNotationWithConstTestData

##### Scenario 1

**Before**
```java
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
        public static final ConstClass SELF_SUB = new SubConstClass();
        public static final ConstClass SELF_SUB_ANN = new SubConstClass() {
```

**After**
```java
        const ConstClass SELF = ::new;
        const ConstClass SELF_ANN = ::new{};
        const ConstClass SELF_SUB = new SubConstClass();
        const ConstClass SELF_SUB_ANN = new SubConstClass() {
```


##### Scenario 2

**Before**
```java
        private static final HashMap<String, String> MAP = new HashMap<>();
        private static final HashMap<String, String> MAP2 = new HashMap<String, String>();
        private static final Map<String, String> MAP3 = new HashMap<>();
        private static final Map<String, String> MAP_TREE = new TreeMap<>();
        private static final Map<String, String> MAP4 = Map.of();
```

**After**
```java
        private const HashMap<String, String> MAP = ::new;
        private const HashMap<String, String> MAP2 = ::new;
        private const Map<String, String> MAP3 = new HashMap<>();
        private const Map<String, String> MAP_TREE = new TreeMap<>();
        private const Map<String, String> MAP4 = Map.of();
```


##### Scenario 3

**Before**
```java
        private static final List<String> LIST = new ArrayList<>();
        private static final List<String> LIST2 = List.of();
        private static final List<String> LIST_SINGLE = List.of("1");
        private static final List<String> LIST_LINKED = new LinkedList<>();
```

**After**
```java
        private const List<String> LIST = new ArrayList<>();
        private const List<String> LIST2 = List.of();
        private const List<String> LIST_SINGLE = List.of("1");
        private const List<String> LIST_LINKED = new LinkedList<>();
```


##### Scenario 4

**Before**
```java
        public static final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public static final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST_SINGLE.get(0));
```

**After**
```java
        const ConstClass SELF_PARAM_1 = ::new(true);
        const ConstClass SELF_PARAM_2 = ::new(false, LIST_SINGLE.get(0));
```


##### Scenario 5

**Before**
```java
        public static final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
```

**After**
```java
        const ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {
```


##### Scenario 6

**Before**
```java
        public static final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
```

**After**
```java
        const ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {
```


##### Scenario 7

**Before**
```java
        final ConstClass SELF = new ConstClass();
        ConstClass SELF_ANN = new ConstClass() {
        };
```

**After**
```java
        final ConstClass SELF = ::new;
        ConstClass SELF_ANN = ::new{};
```


##### Scenario 8

**Before**
```java
        private final HashMap<String, String> MAP = new HashMap<>();
        private final HashMap<String, String> MAP2 = new HashMap<String, String>();
```

**After**
```java
        private final HashMap<String, String> MAP = ::new;
        private final HashMap<String, String> MAP2 = ::new;
```


##### Scenario 9

**Before**
```java
        public final ConstClass SELF_PARAM_1 = new ConstClass(true);
        public final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST2.get(0));
```

**After**
```java
        public final ConstClass SELF_PARAM_1 = ::new(true);
        public final ConstClass SELF_PARAM_2 = ::new(false, LIST2.get(0));
```


#### ExperimentalTestData

##### Scenario 1

**Before**
```java
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
```

**After**
```java
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].bytes;
```


##### Scenario 2

**Before**
```java
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
```


##### Scenario 3

**Before**
```java
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
```

**After**
```java
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
```


##### Scenario 4

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows(IllegalArgumentException) {
```


##### Scenario 5

**Before**
```java
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
```


##### Scenario 6

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows {
```


##### Scenario 7

**Before**
```java
            } catch (Throwable t) {
                throw new IllegalStateException(t);
```


##### Scenario 8

**Before**
```java
            try {
                return new String(System.getProperty("sort-desc").getBytes(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
```

**After**
```java
            @SneakyThrows
            return new String(System["sort-desc"].bytes, "UTF-8");
```


##### Scenario 9

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows
```


##### Scenario 10

**Before**
```java
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }
```


##### Scenario 11

**Before**
```java
            example.set("ok");
            String current = example.get();
            System.out.println(example.get().isEmpty());
            example.set(example.get());
            String duplicate = example.get() + example.get();
```

**After**
```java
            example.!! = "ok";
            String current = example.!!;
            System.out.println(example.!!.empty);
            example.!! = example.!!;
            String duplicate = example.!! + example.!!;
```

