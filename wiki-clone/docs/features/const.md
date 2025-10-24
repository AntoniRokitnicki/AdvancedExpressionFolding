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

#### Folding catalogue

##### ConstTestData mappings
| Before | After |
| --- | --- |
| `static final Pattern PATTERN = Pattern.compile(".*");` | `default const PATTERN = Pattern.compile(".*");` |
| `static final Pattern PATTERN_STATIC_IMPORT = compile(".*");` | `default const Pattern PATTERN_STATIC_IMPORT = compile(".*");` |
| `static final EOrder ENUM = EOrder.FIRST;` | `default econst ENUM = EOrder.FIRST;` |
| `static final Orderable ENUM_BY_INTERFACE = EOrder.FIRST;` | `default const Orderable ENUM_BY_INTERFACE = EOrder.FIRST;` |
| `static final EOrder ENUM_STATIC_IMPORT = SECOND;` | `default econst EOrder ENUM_STATIC_IMPORT = SECOND;` |
| `public static final String PUBLIC_STATIC_FINAL_VAR = "";` | `const PUBLIC_STATIC_FINAL_VAR = "";` |
| `private static final String PRIVATE_STATIC_FINAL_VAR = "";` | `private const PRIVATE_STATIC_FINAL_VAR = "";` |
| `protected static final String PROTECTED_STATIC_FINAL_VAR = "";` | `protected const PROTECTED_STATIC_FINAL_VAR = "";` |
| `static final String DEFAULT_STATIC_FINAL_VAR = "";` | `default const DEFAULT_STATIC_FINAL_VAR = "";` |
| `public static final String STRING_JOINER = "" + "1";` | `const String STRING_JOINER = "" + "1";` |
| `public static final String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;` | `const String STRING_JOINER2 = "" + DEFAULT_STATIC_FINAL_VAR;` |
| `public static final String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";` | `const String STRING_JOINER3 = DEFAULT_STATIC_FINAL_VAR + "";` |
| `public static final String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;` | `const String STRING_JOINER4 = DEFAULT_STATIC_FINAL_VAR + PROTECTED_STATIC_FINAL_VAR + PUBLIC_STATIC_FINAL_VAR;` |
| `public final static String PUBLIC_FINAL_STATIC_VAR = "";` | `const PUBLIC_FINAL_STATIC_VAR = "";` |
| `private final static String PRIVATE_FINAL_STATIC_VAR = "";` | `private const PRIVATE_FINAL_STATIC_VAR = "";` |
| `protected final static String PROTECTED_FINAL_STATIC_VAR = "";` | `protected const PROTECTED_FINAL_STATIC_VAR = "";` |
| `static public final String STATIC_PUBLIC_FINAL_VAR = "";` | `const STATIC_PUBLIC_FINAL_VAR = "";` |
| `static final public String STATIC_FINAL_PUBLIC_VAR = "";` | `const STATIC_FINAL_PUBLIC_VAR = "";` |
| `final public static String FINAL_PUBLIC_STATIC_VAR = "";` | `const FINAL_PUBLIC_STATIC_VAR = "";` |
| `final static public String FINAL_STATIC_PUBLIC_VAR = "";` | `const FINAL_STATIC_PUBLIC_VAR = "";` |
| `static final String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;` | `default const String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;` |
| `static final int VERSION = 1;` | `default const VERSION = 1;` |
| `static final int VERSION_REF = VERSION;` | `default const VERSION_REF = VERSION;` |
| `@Deprecated static final Pattern PATTERN = Pattern.compile(".*");` | `@Deprecated default const PATTERN = Pattern.compile(".*");` |

##### ConstructorReferenceNotationWithConstTestData mappings
| Before | After |
| --- | --- |
| `public static final ConstClass SELF = new ConstClass();` | `const ConstClass SELF = ::new;` |
| `public static final ConstClass SELF_ANN = new ConstClass() {` | `const ConstClass SELF_ANN = ::new{};` |
| `public static final ConstClass SELF_SUB = new SubConstClass();` | `const ConstClass SELF_SUB = new SubConstClass();` |
| `public static final ConstClass SELF_SUB_ANN = new SubConstClass() {` | `const ConstClass SELF_SUB_ANN = new SubConstClass() {` |
| `private static final HashMap<String, String> MAP = new HashMap<>();` | `private const HashMap<String, String> MAP = ::new;` |
| `private static final HashMap<String, String> MAP2 = new HashMap<String, String>();` | `private const HashMap<String, String> MAP2 = ::new;` |
| `private static final Map<String, String> MAP3 = new HashMap<>();` | `private const Map<String, String> MAP3 = new HashMap<>();` |
| `private static final Map<String, String> MAP_TREE = new TreeMap<>();` | `private const Map<String, String> MAP_TREE = new TreeMap<>();` |
| `private static final Map<String, String> MAP4 = Map.of();` | `private const Map<String, String> MAP4 = Map.of();` |
| `private static final List<String> LIST = new ArrayList<>();` | `private const List<String> LIST = new ArrayList<>();` |
| `private static final List<String> LIST2 = List.of();` | `private const List<String> LIST2 = List.of();` |
| `private static final List<String> LIST_SINGLE = List.of("1");` | `private const List<String> LIST_SINGLE = List.of("1");` |
| `private static final List<String> LIST_LINKED = new LinkedList<>();` | `private const List<String> LIST_LINKED = new LinkedList<>();` |
| `public static final ConstClass SELF_PARAM_1 = new ConstClass(true);` | `const ConstClass SELF_PARAM_1 = ::new(true);` |
| `public static final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST_SINGLE.get(0));` | `const ConstClass SELF_PARAM_2 = ::new(false, LIST_SINGLE.get(0));` |
| `public static final ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {` | `const ConstClass SELF_SUBCLASS_MORE_FIELD = new ConstClass() {` |
| `public static final ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {` | `const ConstClass SELF_SUBCLASS_MORE_FUNC = new ConstClass() {` |
| `final ConstClass SELF = new ConstClass();` | `final ConstClass SELF = ::new;` |
| `ConstClass SELF_ANN = new ConstClass() {` | `ConstClass SELF_ANN = ::new{};` |
| `public final ConstClass SELF_SUB = new SubConstClass();` | `public final ConstClass SELF_SUB = new SubConstClass();` |
| `private final HashMap<String, String> MAP = new HashMap<>();` | `private final HashMap<String, String> MAP = ::new;` |
| `private final HashMap<String, String> MAP2 = new HashMap<String, String>();` | `private final HashMap<String, String> MAP2 = ::new;` |
| `private final Map<String, String> MAP3 = new HashMap<>();` | `private final Map<String, String> MAP3 = new HashMap<>();` |
| `private final List<String> LIST = new ArrayList<>();` | `private final List<String> LIST = new ArrayList<>();` |
| `private final List<String> LIST2 = List.of("1");` | `private final List<String> LIST2 = List.of("1");` |
| `public final ConstClass SELF_PARAM_1 = new ConstClass(true);` | `public final ConstClass SELF_PARAM_1 = ::new(true);` |
| `public final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST2.get(0));` | `public final ConstClass SELF_PARAM_2 = ::new(false, LIST2.get(0));` |

##### ExperimentalTestData const-adjacent mappings
| Before | After |
| --- | --- |
| `<pre>try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
                return new String(bytez, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }</pre>` | `<pre>@SneakyThrows {
                byte[] bytez = System["sort-desc"].bytes;
                return new String(bytez, "UTF-8");
            }</pre>` |
| `<pre>try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }</pre>` | `@SneakyThrows(IllegalArgumentException) return Integer.parseInt(value);` |
| `<pre>try {
                Function<String, Long> longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }</pre>` | `<pre>@SneakyThrows(IllegalArgumentException) {
                Function<String, Long> longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            }</pre>` |
| `<pre>try {
                var throwable = new Throwable();
                throw throwable;
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }</pre>` | `<pre>@SneakyThrows {
                var throwable = new Throwable();
                throw throwable;
            }</pre>` |
| `<pre>try {
                return new String(System.getProperty("sort-desc").getBytes(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }</pre>` | `@SneakyThrows return new String(System["sort-desc"].bytes, "UTF-8");` |
| `<pre>try {
            throw new Throwable();
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }</pre>` | `@SneakyThrows throw new Throwable();` |
| `<pre>example.set("ok");
            String current = example.get();
            System.out.println(example.get().isEmpty());
            example.set(example.get());
            String duplicate = example.get() + example.get();</pre>` | `<pre>example.!! = "ok";
            String current = example.!!;
            System.out.println(example.!!.empty);
            example.!! = example.!!;
            String duplicate = example.!! + example.!!;</pre>` |
