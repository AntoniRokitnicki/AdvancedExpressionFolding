![Animated overview of Lombok folding support](https://github.com/user-attachments/assets/7d2bdcf7-15ad-45a2-9aad-1f596443c4d7)

old video:
[video](https://www.youtube.com/watch?v=dKMWL3YJacU)

# Lombok (State field: lombok)

### Lombok
Folds Java bean patterns into Lombok-style annotations.

#### Example: LombokTestData

examples/data/LombokTestData.java:
```java
public class LombokTestData {

    private static final long serialVersionUID = 1234567L;
// ...
    public LombokTestData getData() {
        return data;
    }
// ...
        this.string = string;
    }
```

folded/LombokTestData-folded.java:
```java
@Builder(ClassWithBuilder) @Getter @Setter @Serial public class LombokTestData {
// ...
    @Getter public class LombokGetters {
        LombokTestData data;
        boolean ok;
```

Highlights LombokTestData with lombok.
Removes boilerplate while preserving behavior.

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

Highlights NullableAnnotationTestData with lombok.
Removes boilerplate while preserving behavior.

#### Example: LombokDirtyOffTestData

examples/data/LombokDirtyOffTestData.java:
```java
        public class DirtyData {
// ...
            private boolean ok;
```

folded/LombokDirtyOffTestData-folded.java:
```java
        @EqualsAndHashCode public class DirtyData {
// ...
            @Getter private boolean ok;
```

Highlights LombokDirtyOffTestData with lombok.
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

Highlights InterfaceExtensionPropertiesTestData with lombok.
Removes boilerplate while preserving behavior.

#### Example: LombokPatternOffTestData

examples/data/LombokPatternOffTestData.java:
```java
            return "ToStringFull{" +
                    "data=" + data +
                    ", ok=" + ok +
                    '}';
// ...
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
```

folded/LombokPatternOffTestData-folded.java:
```java
            return "ToStringFull{" + "data=" + data + ", ok=" + ok + '}';
// ...
                return "ToStringPartial{" + "data=" + data + '}';
```

Highlights LombokPatternOffTestData with lombok.
Removes boilerplate while preserving behavior.

### @PostConstructor
LombokPostConstructorTestData.java:
```java
public class LombokPostConstructorTestData {

    public static class DataProcessor {
        private final int value1;
        private final int value2;
        private int computedResult;

        public DataProcessor(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
            initialize();
        }

        public DataProcessor() {
            this(0, 0);
            initialize();
        }

        private void initialize() {
            computedResult = value1 + value2;
        }
    }

    public static class TaskPipeline {
        private final int seed;
        private boolean prepared;
        private boolean executed;

        public TaskPipeline() {
            this.seed = -1;
            prepare();
            execute();
        }

        public TaskPipeline(int seed) {
            this.seed = seed;
            prepare();
            execute();
        }

        private void prepare() {
            prepared = true;
        }

        private void execute() {
            executed = prepared;
        }
    }
}
```

LombokPostConstructorTestData-folded.java:
```java
public class LombokPostConstructorTestData {

    public static class DataProcessor {
        private final int value1;
        private final int value2;
        private int computedResult;

        public DataProcessor(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
            initialize();
        }

        public DataProcessor() {
            this(0, 0);
            initialize();
        }

        @PostConstructor private void initialize() {
            computedResult = value1 + value2;
        }
    }

    public static class TaskPipeline {
        private final int seed;
        private boolean prepared;
        private boolean executed;

        public TaskPipeline() {
            this.seed = -1;
            prepare();
            execute();
        }

        public TaskPipeline(int seed) {
            this.seed = seed;
            prepare();
            execute();
        }

        @PostConstructor(1) private void prepare() {
            prepared = true;
        }

        @PostConstructor(2) private void execute() {
            executed = prepared;
        }
    }
}
```

Methods invoked at the end of every constructor now receive an informational `@PostConstructor` annotation while their explicit calls remain visible. When more than one method participates, numbering communicates the execution order.

### @LightValue
This seems to be a custom annotation not present in standard Lombok. It appears to create an immutable class without equals and hashCode methods.
#### Example: LombokPatternOffNegativeTestData

examples/data/LombokPatternOffNegativeTestData.java:
```java
public class LombokPatternOffNegativeTestData {

    private static final long serialVersionUID = 1234567L;
// ...
    public LombokPatternOffNegativeTestData getData() {
        return data;
    }
// ...
        this.string = string;
    }
```

folded/LombokPatternOffNegativeTestData-folded.java:
```java
@Builder(ClassWithBuilder) @Getter @Setter @Serial public class LombokPatternOffNegativeTestData {
// ...
    @Getter public class LombokGetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;
```

Highlights LombokPatternOffNegativeTestData with lombok.
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

Highlights ExperimentalTestData with lombok.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `lombok`
Related features: (none)
