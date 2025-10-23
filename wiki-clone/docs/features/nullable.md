## example

### Nullable
Folds nullability annotations into ? and !! markers.

#### Example: NullableAnnotationTestData

examples/data/NullableAnnotationTestData.java:
```java
    @NotNull
    NullableAnnotationTestData data;
    boolean ok;
// ...
    public void setString(String string) {
        this.string = string;
    }
// ...
    @Nonnull
    private NullableAnnotationTestData data2;
```

folded/NullableAnnotationTestData-folded.java:
```java
    
    @Getter @Setter NullableAnnotationTestData!! data;
    @Getter @Setter boolean ok;
    
    @Getter @Setter String? string;
// ...
    
    private NullableAnnotationTestData!! data2;
```

Highlights NullableAnnotationTestData with nullable.
Removes boilerplate while preserving behavior.

#### Example: NullableAnnotationCheckNotNullTestData

examples/data/NullableAnnotationCheckNotNullTestData.java:
```java
        public void main(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);
// ...
        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
```

folded/NullableAnnotationCheckNotNullTestData-folded.java:
```java
        public void main(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;
// ...
        public void mainMsgs(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;
```

Highlights NullableAnnotationCheckNotNullTestData with nullable.
Removes boilerplate while preserving behavior.

#### Example: NullableAnnotationCheckNotNullFieldShiftTestData

examples/data/NullableAnnotationCheckNotNullFieldShiftTestData.java:
```java
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
// ...
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
```

folded/NullableAnnotationCheckNotNullFieldShiftTestData-folded.java:
```java
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
// ...
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
```

Highlights NullableAnnotationCheckNotNullFieldShiftTestData with nullable.
Removes boilerplate while preserving behavior.

#### Example: InterfaceExtensionPropertiesTestData

examples/data/InterfaceExtensionPropertiesTestData.java:
```java
        String getName();
        void setName(String name);

        int getAge();
        void setAge(int age);
// ...
        public String getName();
        public void setName(String name);

        public int getAge();
        public void setAge(int age);
```

folded/InterfaceExtensionPropertiesTestData-folded.java:
```java
       @Getter String name;
       @Setter String name;

       @Getter int age;
       @Setter int age;
// ...
       @Getter public String name;
       @Setter public String name;

       @Getter public int age;
       @Setter public int age;
```

Highlights InterfaceExtensionPropertiesTestData with nullable.
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

Highlights ExperimentalTestData with nullable.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `nullable`
Related features: (none)
---

#### Folding catalogue

##### NullableAnnotationTestData mappings
| Before | After |
| --- | --- |
| `@NotNull NullableAnnotationTestData data;` | `@Getter @Setter NullableAnnotationTestData!! data;` |
| `boolean ok;` | `@Getter @Setter boolean ok;` |
| `@Nullable String string;` | `@Getter @Setter String? string;` |
| `@Nonnull private NullableAnnotationTestData data2;` | `private NullableAnnotationTestData!! data2;` |
| `@Nullable private String string2;` | `private String? string2;` |
| `<pre>public void select(@Nullable String element,
                       int i,
                       @NotNull Object o,
                       @Nonnull LocalDate date
                       ) {</pre>` | `<pre>public void select(String? element,
                       int i,
                       Object!! o,
                       LocalDate!! date
                       ) {</pre>` |
| `@NotNull public String getStringNotNull() {` | `public String!! getStringNotNull() {` |
| `@Nonnull public String getStringNotNull2() {` | `public String!! getStringNotNull2() {` |
| `@Nullable public String getStringNull() {` | `public String? getStringNull() {` |
| `<pre>@Nullable
        public Integer select(@Nullable String element,
                           int i,
                           @NotNull Object o,
                           @Nonnull LocalDate date
        );</pre>` | `<pre>public Integer? select(String? element,
                           int i,
                           Object!! o,
                           LocalDate!! date
        );</pre>` |
| `<pre>@Nonnull
        public static int select(@Nullable String element,
                                 int i,
                                 @NotNull Object o,
                                 @Nonnull LocalDate date
        ) {</pre>` | `<pre>public static int!! select(String? element,
                                 int i,
                                 Object!! o,
                                 LocalDate!! date
        ) {</pre>` |
| `public record UserDataRecord(@Nonnull String username, boolean active, @Nullable String userIdentifier, @NotNull String username2) {` | `public record UserDataRecord(String!! username, boolean active, String? userIdentifier, String!! username2) {` |
| `<pre>public class HasGetter {
            @Nullable
            private String field;
            private String bla;

            public @Nullable String getField() {
                return field;
            }
        }</pre>` | `<pre>public class HasGetter {

            @Getter private String? field;
            private String bla;
        }</pre>` |
| `<pre>public class HasSetter {
            @Nullable
            private String field;
            private String bla;

            public void setField(@Nullable String field) {
                this.field = field;
            }
        }</pre>` | `<pre>public class HasSetter {

            @Setter private String? field;
            private String bla;
        }</pre>` |
| `<pre>public class HasGetterSetter {
            @Nullable
            private String field;
            private String bla;

            public @Nullable String getField() {
                new HashMap<String, String>().put("a", "b");
                return field;
            }

            public void setField(@Nullable String field) {
                this.field = field;
            }
        }</pre>` | `<pre>public class HasGetterSetter {

            @Getter(dirty) @Setter private String? field;
            private String bla;
        }</pre>` |
| `<pre>public class HasGetter {
            @Nullable
            String field;
            String bla;

            public @Nullable String getField() {
                return field;
            }
        }</pre>` | `<pre>public class HasGetter {

            @Getter String? field;
            String bla;
        }</pre>` |
| `<pre>public class HasSetter {
            @Nullable
            String field;
            String bla;

            public void setField(@Nullable String field) {
                this.field = field;
            }
        }</pre>` | `<pre>public class HasSetter {

            @Setter String? field;
            String bla;
        }</pre>` |
| `<pre>public class HasGetterSetter {
            @Nullable
            String field;
            String bla;

            public @Nullable String getField() {
                return field;
            }

            public void setField(@Nullable String field) {
                this.field = field;
            }
        }</pre>` | `<pre>public class HasGetterSetter {

            @Getter @Setter String? field;
            String bla;
        }</pre>` |

##### NullableAnnotationCheckNotNullTestData mappings
| Before | After |
| --- | --- |
| `<pre>public void main(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);</pre>` | `<pre>public void main(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;</pre>` |
| `<pre>public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");</pre>` | `<pre>public void mainMsgs(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;</pre>` |
| `<pre>public void mainConflictAnnotations(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);</pre>` | `<pre>public void mainConflictAnnotations(String!!! args, Object? o, Long!!! l, Preconditions? z) {args!!;l!!;
            z.data!!;
            o!!;</pre>` |
| `<pre>public void mainConflictAnnotationsWithMsg(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");</pre>` | `<pre>public void mainConflictAnnotationsWithMsg(String!!! args, Object? o, Long!!! l, Preconditions? z) {args!!;l!!;
            z.data!!;
            o!!;</pre>` |
| `<pre>this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);</pre>` | `<pre>this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;</pre>` |
| `<pre>this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");</pre>` | `<pre>this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;</pre>` |
| `<pre>public void mainNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);</pre>` | `<pre>public void mainNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;</pre>` |
| `<pre>public void mainMsgsNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");</pre>` | `<pre>public void mainMsgsNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;</pre>` |

##### NullableAnnotationCheckNotNullFieldShiftTestData mappings
| Before | After |
| --- | --- |
| `<pre>this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);</pre>` | `<pre>this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;</pre>` |
| `<pre>this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");</pre>` | `<pre>this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;</pre>` |
| `<pre>public void mainNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);</pre>` | `<pre>public void mainNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;</pre>` |

##### InterfaceExtensionPropertiesTestData mappings
| Before | After |
| --- | --- |
| `String getName();` | `@Getter String name;` |
| `void setName(String name);` | `@Setter String name;` |
| `int getAge();` | `@Getter int age;` |
| `void setAge(int age);` | `@Setter int age;` |
| `public String getName();` | `@Getter public String name;` |
| `public void setName(String name);` | `@Setter public String name;` |
| `public int getAge();` | `@Getter public int age;` |
| `public void setAge(int age);` | `@Setter public int age;` |

##### ExperimentalTestData nullable-adjacent mappings
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
