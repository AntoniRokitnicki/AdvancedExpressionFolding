## constructorReferenceNotation

### Constructor reference notation `::new` and compact field initialization
Simplifies constructor references and inline field initialization.

#### Example: ConstructorReferenceNotationTestData

examples/data/ConstructorReferenceNotationTestData.java:
```java
        public static final ConstClass SELF = new ConstClass();
        public static final ConstClass SELF_ANN = new ConstClass() {
        };
// ...
        private static final HashMap<String, String> MAP = new HashMap<>();
        private static final HashMap<String, String> MAP2 = new HashMap<String, String>();
```

folded/ConstructorReferenceNotationTestData-folded.java:
```java
        public static final ConstClass SELF = ::new;
        public static final ConstClass SELF_ANN = ::new{};
// ...
        private static final HashMap<String, String> MAP = ::new;
        private static final HashMap<String, String> MAP2 = ::new;
```

Highlights ConstructorReferenceNotationTestData with constructor reference notation `::new` and compact field initialization.
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

Highlights ConstructorReferenceNotationWithConstTestData with constructor reference notation `::new` and compact field initialization.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `constructorReferenceNotation`
Related features: (none)
---

#### Folding catalogue

##### ConstructorReferenceNotationTestData mappings
| Before | After |
| --- | --- |
| `public static final ConstClass SELF = new ConstClass();` | `public static final ConstClass SELF = ::new;` |
| `public static final ConstClass SELF_ANN = new ConstClass() {` | `public static final ConstClass SELF_ANN = ::new{};` |
| `private static final HashMap<String, String> MAP = new HashMap<>();` | `private static final HashMap<String, String> MAP = ::new;` |
| `private static final HashMap<String, String> MAP2 = new HashMap<String, String>();` | `private static final HashMap<String, String> MAP2 = ::new;` |
| `public static final ConstClass SELF_PARAM_1 = new ConstClass(true);` | `public static final ConstClass SELF_PARAM_1 = ::new(true);` |
| `public static final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST_SINGLE.get(0));` | `public static final ConstClass SELF_PARAM_2 = ::new(false, LIST_SINGLE.get(0));` |
| `final ConstClass SELF = new ConstClass();` | `final ConstClass SELF = ::new;` |
| `ConstClass SELF_ANN = new ConstClass() {` | `ConstClass SELF_ANN = ::new{};` |
| `private final HashMap<String, String> MAP = new HashMap<>();` | `private final HashMap<String, String> MAP = ::new;` |
| `private final HashMap<String, String> MAP2 = new HashMap<String, String>();` | `private final HashMap<String, String> MAP2 = ::new;` |
| `public final ConstClass SELF_PARAM_1 = new ConstClass(true);` | `public final ConstClass SELF_PARAM_1 = ::new(true);` |
| `public final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST2.get(0));` | `public final ConstClass SELF_PARAM_2 = ::new(false, LIST2.get(0));` |

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
| `private static final HashMap<String, String> MAP = new HashMap<>();` (fields section) | `private const HashMap<String, String> MAP = ::new;` |
| `private static final HashMap<String, String> MAP2 = new HashMap<String, String>();` (fields section) | `private const HashMap<String, String> MAP2 = ::new;` |
| `private static final Map<String, String> MAP3 = new HashMap<>();` (fields section) | `private const Map<String, String> MAP3 = new HashMap<>();` |
| `private static final List<String> LIST = new ArrayList<>();` (fields section) | `private const List<String> LIST = new ArrayList<>();` |
| `private static final List<String> LIST2 = List.of("1");` (fields section) | `private const List<String> LIST2 = List.of("1");` |
| `public static final ConstClass SELF_PARAM_1 = new ConstClass(true);` (fields section) | `public final ConstClass SELF_PARAM_1 = ::new(true);` |
| `public static final ConstClass SELF_PARAM_2 = new ConstClass(false, LIST2.get(0));` (fields section) | `public final ConstClass SELF_PARAM_2 = ::new(false, LIST2.get(0));` |
