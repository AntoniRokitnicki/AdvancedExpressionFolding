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
### Folding catalogue

#### NullableAnnotationTestData

##### Scenario 1

**Before**
```java
    @NotNull
    NullableAnnotationTestData data;
    boolean ok;
    @Nullable
    String string;
    public NullableAnnotationTestData getData() {
        return data;
    }
    public void setData(NullableAnnotationTestData data) {
        this.data = data;
    }
    public boolean isOk() {
        return ok;
    }
    public void setOk(boolean ok) {
        this.ok = ok;
    }
    public String getString() {
        return string;
    }
    public void setString(String string) {
        this.string = string;
    }
```

**After**
```java
    
    @Getter @Setter NullableAnnotationTestData!! data;
    @Getter @Setter boolean ok;
    
    @Getter @Setter String? string;
```


##### Scenario 2

**Before**
```java
    @Nonnull
    private NullableAnnotationTestData data2;
```

**After**
```java
    
    private NullableAnnotationTestData!! data2;
```


##### Scenario 3

**Before**
```java
    @Nullable
    private String string2;
```

**After**
```java
    
    private String? string2;
```


##### Scenario 4

**Before**
```java
    public void select(@Nullable String element,
```

**After**
```java
    public void select(String? element,
```


##### Scenario 5

**Before**
```java
                       @NotNull Object o,
                       @Nonnull LocalDate date
```

**After**
```java
                       Object!! o,
                       LocalDate!! date
```


##### Scenario 6

**Before**
```java
    @NotNull
    public String getStringNotNull() {
```

**After**
```java
    
    public String!! getStringNotNull() {
```


##### Scenario 7

**Before**
```java
    @Nonnull
    public String getStringNotNull2() {
```

**After**
```java
    
    public String!! getStringNotNull2() {
```


##### Scenario 8

**Before**
```java
    @Nullable
    public String getStringNull() {
```

**After**
```java
    
    public String? getStringNull() {
```


##### Scenario 9

**Before**
```java
        @Nullable
        public Integer select(@Nullable String element,
```

**After**
```java
        
        public Integer? select(String? element,
```


##### Scenario 10

**Before**
```java
                           @NotNull Object o,
                           @Nonnull LocalDate date
```

**After**
```java
                           Object!! o,
                           LocalDate!! date
```


##### Scenario 11

**Before**
```java
        @Nonnull
        public static int select(@Nullable String element,
```

**After**
```java
        
        public static int!! select(String? element,
```


##### Scenario 12

**Before**
```java
                                 @NotNull Object o,
                                 @Nonnull LocalDate date
```

**After**
```java
                                 Object!! o,
                                 LocalDate!! date
```


##### Scenario 13

**Before**
```java
    public record UserDataRecord(@Nonnull String username, boolean active, @Nullable String userIdentifier, @NotNull String username2) {
```

**After**
```java
    public record UserDataRecord(String!! username, boolean active, String? userIdentifier, String!! username2) {
```


##### Scenario 14

**Before**
```java
    class GetterNullable {
```

**After**
```java
    @Getter class GetterNullable {
```


##### Scenario 15

**Before**
```java
        @Nullable
        public NullableAnnotationTestData getGetterNullable() {
```

**After**
```java
        public NullableAnnotationTestData? getGetterNullable() {
```


##### Scenario 16

**Before**
```java
    class SetterNullable {
```

**After**
```java
    @Setter class SetterNullable {
```


##### Scenario 17

**Before**
```java
        public void setSetterNullable(@Nonnull NullableAnnotationTestData setterNullable) {
            this.setterNullable = setterNullable;
        }
```


##### Scenario 18

**Before**
```java
            @Nullable
            private String field;
```

**After**
```java
            
            @Getter private String? field;
```


##### Scenario 19

**Before**
```java
            public @Nullable String getField() {
                return field;
            }
```


##### Scenario 20

**Before**
```java
            @Nullable
            private String field;
```

**After**
```java
            
            @Setter private String? field;
```


##### Scenario 21

**Before**
```java
            public void setField(@Nullable String field) {
                this.field = field;
            }
```


##### Scenario 22

**Before**
```java
            @Nullable
            private String field;
```

**After**
```java
            
            @Getter(dirty) @Setter private String? field;
```


##### Scenario 23

**Before**
```java
            public @Nullable String getField() {
                new HashMap<String, String>().put("a", "b");
                return field;
            }

            public void setField(@Nullable String field) {
                this.field = field;
            }
```


##### Scenario 24

**Before**
```java
            @Nullable
            String field;
```

**After**
```java
            
            @Getter String? field;
```


##### Scenario 25

**Before**
```java
            @Nullable
            String field;
```

**After**
```java
            
            @Setter String? field;
```


##### Scenario 26

**Before**
```java
            @Nullable
            String field;
```

**After**
```java
            
            @Getter @Setter String? field;
```


##### Scenario 27

**Before**
```java
            public @Nullable String getField() {
                return field;
            }

            public void setField(@Nullable String field) {
                this.field = field;
            }
```


#### NullableAnnotationCheckNotNullTestData

##### Scenario 1

**Before**
```java
        public void main(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);
```

**After**
```java
        public void main(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;
```


##### Scenario 2

**Before**
```java
        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
        public void mainMsgs(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;
```


##### Scenario 3

**Before**
```java
        public void mainConflictAnnotations(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);
```

**After**
```java
        public void mainConflictAnnotations(String!!! args, Object? o, Long!!! l, Preconditions? z) {args!!;l!!;
            z.data!!;
            o!!;
```


##### Scenario 4

**Before**
```java
        public void mainConflictAnnotationsWithMsg(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
        public void mainConflictAnnotationsWithMsg(String!!! args, Object? o, Long!!! l, Preconditions? z) {args!!;l!!;
            z.data!!;
            o!!;
```


##### Scenario 5

**Before**
```java
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
```

**After**
```java
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;
```


##### Scenario 6

**Before**
```java
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;
```


##### Scenario 7

**Before**
```java
        public void mainNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
```

**After**
```java
        public void mainNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;
```


##### Scenario 8

**Before**
```java
        public void mainMsgsNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
        public void mainMsgsNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;
```


#### NullableAnnotationCheckNotNullFieldShiftTestData

##### Scenario 1

**Before**
```java
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
```

**After**
```java
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
```


##### Scenario 2

**Before**
```java
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
```


##### Scenario 3

**Before**
```java
        public void mainNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
```

**After**
```java
        public void mainNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
```


#### InterfaceExtensionPropertiesTestData

##### Scenario 1

**Before**
```java
        String getName();
        void setName(String name);

        int getAge();
        void setAge(int age);
```

**After**
```java
       @Getter String name;
       @Setter String name;

       @Getter int age;
       @Setter int age;
```


##### Scenario 2

**Before**
```java
        public String getName();
        public void setName(String name);

        public int getAge();
        public void setAge(int age);
```

**After**
```java
       @Getter public String name;
       @Setter public String name;

       @Getter public int age;
       @Setter public int age;
```


##### Scenario 3

**Before**
```java
            int getAge();
```

**After**
```java
           @Getter int age;
```


##### Scenario 4

**Before**
```java
        String getName();
        int getAge();
```

**After**
```java
       @Getter String name;
       @Getter int age;
```


##### Scenario 5

**Before**
```java
        void setName(String name);
        void setAge(int age);
```

**After**
```java
       @Setter String name;
       @Setter int age;
```


##### Scenario 6

**Before**
```java
        String getName();
        void setName(String name);

        int getAge();
```

**After**
```java
       @Getter String name;
       @Setter String name;

       @Getter int age;
```


##### Scenario 7

**Before**
```java
        String getName();
        void setName(String name);

        void setAge(int age);
```

**After**
```java
       @Getter String name;
       @Setter String name;

       @Setter int age;
```


##### Scenario 8

**Before**
```java
        String getName();
```

**After**
```java
       @Getter String name;
```


##### Scenario 9

**Before**
```java
        void setName(String name);
```

**After**
```java
       @Setter String name;
```


##### Scenario 10

**Before**
```java
        String getName();
        void setName(String name);
```

**After**
```java
       @Getter String name;
       @Setter String name;
```


##### Scenario 11

**Before**
```java
            String getName();

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);
```

**After**
```java
           @Getter String name;

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           @Setter String name;
```


##### Scenario 12

**Before**
```java
            void setAge(int age);
```

**After**
```java
           @Setter int age;
```


##### Scenario 13

**Before**
```java
            String getName();
```

**After**
```java
           @Getter String name;
```


##### Scenario 14

**Before**
```java
            void setName(String name);
```

**After**
```java
           @Setter String name;
```


##### Scenario 15

**Before**
```java
        //@FindBy String tag(String name);
        String findTagByName(String name);

        String findTagByAge(byte name);

        String findNameByName(String name);
```

**After**
```java
       @FindBy //@FindBy String tag(String name);
        String tag(String name);

       @FindBy String tag(byte age);

       @FindBy String name(String name);
```


##### Scenario 16

**Before**
```java
        @Nullable
        Integer getAge();
        void setAge(@Nullable int age);
        @Nullable
        String getName();
        void setName(@Nullable String name);
```

**After**
```java
       @Getter Integer? age;
       @Setter int? age;
       @Getter String? name;
       @Setter String? name;
```


##### Scenario 17

**Before**
```java
        @NotNull() String getName();
        void setName(@NotNull String name);
        int getAge();
```

**After**
```java
       @Getter String!! name;
       @Setter String!! name;
       @Getter int age;
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

