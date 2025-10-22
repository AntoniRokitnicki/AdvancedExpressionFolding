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
