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

---
### Folding catalogue

#### LombokTestData

##### Scenario 1

**Before**
```java
public class LombokTestData {

    private static final long serialVersionUID = 1234567L;
```

**After**
```java
@HasBuilder(ClassWithBuilder) @Getter @Setter @Serial public class LombokTestData {
```


##### Scenario 2

**Before**
```java
    public LombokTestData getData() {
        return data;
    }

    public void setData(LombokTestData data) {
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


##### Scenario 3

**Before**
```java
    public class LombokGetters {
        LombokTestData data;
        boolean ok;

        public LombokTestData getData() {
            return data;
        }

        public boolean isOk() {
            return ok;
        }
```

**After**
```java
    @Getter public class LombokGetters {
        LombokTestData data;
        boolean ok;
```


##### Scenario 4

**Before**
```java
            boolean ok;

            public boolean isOk() {
                return ok;
            }
        }
    }

    public class LombokSetters {
        LombokTestData data;
        boolean ok;

        public LombokTestData getData() {
            return data;
        }

        public void setData(LombokTestData data) {
            this.data = data;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }
```

**After**
```java
            @Getter boolean ok;
        }
    }

    @Getter @Setter public class LombokSetters {
        LombokTestData data;
        boolean ok;
```


##### Scenario 5

**After**
```java
            @Setter LombokTestData data;
            boolean ok;
        }

        public class LombokSettersFinalField {
            @Setter LombokTestData data;
            final boolean ok = true;
        }
    }

    @ToString public class ToStringFull {
        LombokTestData data;
        boolean ok;

        public class ToStringPartial {
            @ToString LombokTestData data;
            boolean ok;
        }

        public class ToStringPartial2 {
            @ToString LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @EqualsAndHashCode public class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;

        public class EqualsAndHashCodePartial {
            @EqualsAndHashCode LombokTestData data;
            boolean ok;
        }

        public class EqualsAndHashCodePartialTwo {
            @EqualsAndHashCode LombokTestData data;
            @EqualsAndHashCode boolean ok;
            String string;
        }
    }

    @Equals public class EqualsFull {
        LombokTestData data;
        boolean ok;

        public class EqualsPartial {
            @Equals LombokTestData data;
            boolean ok;
        }

        public class EqualsPartialTwo {
            @Equals LombokTestData data;
            @Equals boolean ok;
            String string;
        }
    }

    @HashCode public class HashCodeFull {
        LombokTestData data;
        boolean ok;

        public class HashCodePartial {
            @HashCode LombokTestData data;
            boolean ok;
        }

        public class HashCodePartialTwo {
            @HashCode LombokTestData data;
            @HashCode boolean ok;
            String string;
        }
    }

    @Data public class DataFull {
        LombokTestData data;
        boolean ok;

        @Data public class DataWithoutToString {
```


##### Scenario 6

**Before**
```java
            public void setData(LombokTestData data) {
                this.data = data;
            }
        }

        public class LombokSettersFinalField {
            LombokTestData data;
            final boolean ok = true;

            public void setData(LombokTestData data) {
                this.data = data;
            }
        }
    }

    public class ToStringFull {
        LombokTestData data;
        boolean ok;

        @Override
        public String toString() {
            return "ToStringFull{" +
                    "data=" + data +
                    ", ok=" + ok +
                    '}';
        }

        public class ToStringPartial {
            LombokTestData data;
            boolean ok;

            @Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }
        }

        public class ToStringPartial2 {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }
        }
    }

    public class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }

        @Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }

        public class EqualsAndHashCodePartial {
            LombokTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }

            @Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }
        }

        public class EqualsAndHashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }

            @Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }
    }

    public class EqualsFull {
        LombokTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsFull that = (EqualsFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }

        public class EqualsPartial {
            LombokTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartial that = (EqualsPartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }
        }

        public class EqualsPartialTwo {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartialTwo that = (EqualsPartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }
        }
    }

    public class HashCodeFull {
        LombokTestData data;
        boolean ok;

        @Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }

        public class HashCodePartial {
            LombokTestData data;
            boolean ok;

            @Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }
        }

        public class HashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;

            @Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }
    }

    public class DataFull {
        LombokTestData data;
        boolean ok;

        public LombokTestData getData() {
            return data;
        }

        public void setData(LombokTestData data) {
            this.data = data;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DataFull)) return false;
            DataFull dataFull = (DataFull) o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }

        public class DataWithoutToString {
            LombokTestData data;
            boolean ok;

            public LombokTestData getData() {
                return data;
            }

            public void setData(LombokTestData data) {
                this.data = data;
            }

            public boolean isOk() {
                return ok;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

        }

        public class DataWithPartialGetters {
            LombokTestData data;
            boolean ok;

            public LombokTestData getData() {
                return data;
            }

            public void setData(LombokTestData data) {
                this.data = data;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

            @Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }

        public class DataWithPartialSetters {
            LombokTestData data;
            boolean ok;

            public LombokTestData getData() {
                return data;
            }

            public void setData(LombokTestData data) {
                this.data = data;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokTestData.DataFull)) return false;
                LombokTestData.DataFull dataFull = (LombokTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

            @Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
```

**After**
```java
        }

        @Setter @ToString @EqualsAndHashCode public class DataWithPartialGetters {
            @Getter LombokTestData data;
            boolean ok;
        }

        @Getter @ToString @EqualsAndHashCode public class DataWithPartialSetters {
            @Setter LombokTestData data;
            boolean ok;
```


##### Scenario 7

**Before**
```java
        public class FoldOnPublic {
            boolean ok;

            public boolean isOk() {
                return ok;
            }
        }

        class FoldOnClass {
            boolean ok;

            public boolean isOk() {
                return ok;
            }
```

**After**
```java
        @Getter public class FoldOnPublic {
            boolean ok;
        }

        @Getter class FoldOnClass {
            boolean ok;
```


##### Scenario 8

**Before**
```java
        class FoldOnWithAnnotation {
            boolean ok;

            public boolean isOk() {
                return ok;
            }
```

**After**
```java
        @Getter class FoldOnWithAnnotation {
            boolean ok;
```


##### Scenario 9

**Before**
```java
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty() {
            return dirty2;
        }

        public boolean isDirty2() {
            return dirty;
        }

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public boolean isDirty() {
                return !dirty;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
```

**After**
```java
        @Getter(dirtyNoReference) boolean dirty;
        @Getter(dirtyNoReference) private boolean dirty2;

        @EqualsAndHashCode public class DirtyData {
            @Getter(dirty) boolean dirty;
            @Getter private boolean ok;
```


##### Scenario 10

**Before**
```java
            boolean dirty;
            boolean ok;

            public boolean isOk() {
                return ok;
            }

            public boolean isDirty() {
                return dirty2;
            }
```

**After**
```java
            @Getter(dirtyNoReference) boolean dirty;
            @Getter boolean ok;
```


##### Scenario 11

**Before**
```java
        private List<String> wrapper;
        private List<String> wrapperWrongRef;
        private List<String> wrapperDeeplyHiddenRef;

        private List<String> localMethodWrappedList;
        private List<String> thisLocalMethodWrappedList;
        private List<String> lazyLoadedList;
        private List<String> oneLineLazyLoadedList;
        private List<String> defensiveCopyList;

        public List<String> getWrapper() {
            return Collections.unmodifiableList(wrapper);
        }

        public List<String> getWrapperDeeplyHiddenRef() {
            if (wrapper != null) {
                try {
                    return wrapperDeeplyHiddenRef;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        }

        public List<String> getWrapperWrongRef() {
            return Collections.unmodifiableList(wrapper);
        }

        public List<String> getLocalMethodWrappedList() {
            return localWrap(localMethodWrappedList);
        }

        public List<String> getThisLocalMethodWrappedList() {
            return this.localWrap(thisLocalMethodWrappedList);
        }

        public List<String> getLazyLoadedList() {
            if (lazyLoadedList == null) {
                lazyLoadedList = new ArrayList<>();
            }
            return lazyLoadedList;
        }

        public List<String> getDefensiveCopyList() {
            return new ArrayList<>(defensiveCopyList);
        }

        public List<String> getOneLineLazyLoadedList() {
            if (oneLineLazyLoadedList == null) oneLineLazyLoadedList = new ArrayList<>();
            return oneLineLazyLoadedList;
        }
```

**After**
```java
        @Getter(wrapper = Collections::unmodifiableList) private List<String> wrapper;
        @Getter(dirtyNoReference) private List<String> wrapperWrongRef;
        @Getter(dirty) private List<String> wrapperDeeplyHiddenRef;

        @Getter(wrapper = this::localWrap) private List<String> localMethodWrappedList;
        @Getter(wrapper = this::localWrap) private List<String> thisLocalMethodWrappedList;
        @Getter(lazy = ArrayList::new) private List<String> lazyLoadedList;
        @Getter(lazy = ArrayList::new) private List<String> oneLineLazyLoadedList;
        @Getter(wrapper = ArrayList::new) private List<String> defensiveCopyList;
```


##### Scenario 12

**Before**
```java
        boolean dirty;
        private boolean dirty2;
        boolean withoutThis;

        public void setDirty(boolean dirty) {
            this.dirty2 = dirty;
        }

        public void setDirty2(boolean dirty2) {
            this.dirty = dirty2;
        }

        public void setWithoutThis(boolean withoutThiss) {
            withoutThis = withoutThiss;
        }

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public void setDirty(boolean dirty) {
                this.dirty = !dirty;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            public boolean isDirty() {
                return dirty;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
```

**After**
```java
        @Setter(dirtyNoReference) boolean dirty;
        @Setter(dirtyNoReference) private boolean dirty2;
        @Setter boolean withoutThis;

        @Getter @EqualsAndHashCode public class DirtyData {
            @Setter(dirty) boolean dirty;
            @Setter private boolean ok;
```


##### Scenario 13

**Before**
```java
            boolean dirty;
            boolean ok;

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            public void setDirty(boolean dirty) {
                this.ok = dirty;
            }
```

**After**
```java
            @Setter(dirtyNoReference) boolean dirty;
            @Setter boolean ok;
```


##### Scenario 14

**Before**
```java
        public class LogJava {
            Logger log = Logger.getLogger("LogAnnotation.class");
        }

        public class LogJava2 {
            Logger log = Logger.getLogger("LogAnnotation.class");
        }

        public class LogDiffrentFieldName {
            public static final Logger logger = Logger.getLogger("LogAnnotation.class");
        }

        public class LogCustomNameDeprecated {
            @Deprecated
            static final Logger xlogger = Logger.getLogger("LogAnnotation.class");
        }

        public class TripleLogJava {
            Logger log = Logger.getLogger("LogAnnotation.class");
            public static final Logger log2 = Logger.getLogger("LogAnnotation.class");
            static Logger log3 = Logger.getLogger("LogAnnotation.class");
```

**After**
```java
        @Log public class LogJava {
        }

        @Log public class LogJava2 {
        }

        @Log(logger) public class LogDiffrentFieldName {
        }

        @Log(xlogger) public class LogCustomNameDeprecated {
        }

        @Log @Log(log2) @Log(log3) public class TripleLogJava {
```


##### Scenario 15

**Before**
```java
        public class NoArgsConstructor {
            public NoArgsConstructor() {
            }
        }
        class NoArgsConstructorPrivate {
            private NoArgsConstructorPrivate() {
            }
        }
        public class NoArgsConstructorCommentBeforeSuper {
            public NoArgsConstructorCommentBeforeSuper() {
                // comment
                super();
            }
        }
        public class NoArgsConstructorCommentAfterSuper {
            public NoArgsConstructorCommentAfterSuper() {
                super();
                // comment
            }
        }
        public class ProtectedNoArgsConstructorComment {
            protected ProtectedNoArgsConstructorComment() {
                // comment
            }
```

**After**
```java
        @NoArgsConstructor public class NoArgsConstructor {
        }
        @NoArgsConstructor(private) class NoArgsConstructorPrivate {
        }
        @NoArgsConstructor(// comment) public class NoArgsConstructorCommentBeforeSuper {
        }
        @NoArgsConstructor(// comment) public class NoArgsConstructorCommentAfterSuper {
        }
        @NoArgsConstructor(protected,// comment) public class ProtectedNoArgsConstructorComment {
```


##### Scenario 16

**Before**
```java
        public class PrivateNoArgsConstructorComment {
            private PrivateNoArgsConstructorComment() {
                // comment
            }
        }
        public class NoArgsConstructorSuper {
            private String field1;
            public NoArgsConstructorSuper() {
                super();
            }
```

**After**
```java
        @NoArgsConstructor(private) public class PrivateNoArgsConstructorComment {
        }
        @NoArgsConstructor public class NoArgsConstructorSuper {
            private String field1;
```


##### Scenario 17

**Before**
```java
        public static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @AllArgsConstructor public static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;
```


##### Scenario 18

**Before**
```java
        public static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }

        public static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;
            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @AllArgsConstructor public static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;
        }

        @AllArgsConstructor(private) public static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;
```


##### Scenario 19

**Before**
```java
        public static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;
            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @AllArgsConstructor(protected) public static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;
```


##### Scenario 20

**Before**
```java
        public static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @RequiredArgsConstructor public static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
```


##### Scenario 21

**Before**
```java
        public static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }

        public static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @RequiredArgsConstructor public static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }

        @RequiredArgsConstructor(private) public static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
```


##### Scenario 22

**Before**
```java
        public static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @RequiredArgsConstructor(protected) public static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
```


##### Scenario 23

**Before**
```java
        public static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public String getField1() {
                return field1;
            }
            public int getField2() {
                return field2;
            }
            public boolean isField3() {
                return field3;
            }
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ValueArgs valueArgs = (ValueArgs) o;
                if (field2 != valueArgs.field2) return false;
                if (field3 != valueArgs.field3) return false;
                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;
            }
            @Override
            public int hashCode() {
                int result = field1 != null ? field1.hashCode() : 0;
                result = 31 * result + field2;
                result = 31 * result + (field3 ? 1 : 0);
                return result;
            }

            @Override
            public String toString() {
                return "ValueArgs{" +
                        "field1='" + field1 + '\'' +
                        ", field2=" + field2 +
                        ", field3=" + field3 +
                        '}';
            }
        }
        public static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public String getField1() {
                return field1;
            }
            public int getField2() {
                return field2;
            }
            public boolean isField3() {
                return field3;
            }
            @Override
            public final boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof ValueArgsSuper)) return false;

                ValueArgsSuper that = (ValueArgsSuper) o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }

            @Override
            public int hashCode() {
                int result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                return result;
            }
        }
        public static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public String getField1() {
                return field1;
            }
            public int getField2() {
                return field2;
            }
            public boolean isField3() {
                return field3;
            }
```

**After**
```java
        @Value public static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }
        @Value public static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }
        @LightValue public static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;
```


##### Scenario 24

**Before**
```java
        public static class AllArgs {
            private String field1;
            public AllArgs(String field1) {
                this.field1 = field1;
            }
        }
        public static class ReqArgs {
            private final String field1;
            public ReqArgs(String field1) {
                this.field1 = field1;
            }
        }
        public static class Value {
            private final String field1;
            public Value(String field1) {
                this.field1 = field1;
            }
            public String getField1() {
                return field1;
            }
            @Override
            public final boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Value)) return false;

                Value value = (Value) o;
                return Objects.equals(field1, value.field1);
            }
            @Override
            public int hashCode() {
                return Objects.hashCode(field1);
            }
        }
        public static class ValueWithoutEqualsAndHashCode {
            private final String field1;
            public ValueWithoutEqualsAndHashCode(String field1) {
                this.field1 = field1;
            }
            public String getField1() {
                return field1;
            }
```

**After**
```java
        @AllArgsConstructor public static class AllArgs {
            private String field1;
        }
        @RequiredArgsConstructor public static class ReqArgs {
            private final String field1;
        }
        @Value public static class Value {
            private final String field1;
        }
        @LightValue public static class ValueWithoutEqualsAndHashCode {
            private final String field1;
```


##### Scenario 25

**Before**
```java
            static class AllArgsDefault {
```

**After**
```java
            @AllArgsConstructor(default) static class AllArgsDefault {
```


##### Scenario 26

**Before**
```java
                AllArgsDefault(String field1) {
                    this.field1 = field1;
                }
            }
            static class AllArgsPrivate {
```

**After**
```java
            }
            @AllArgsConstructor(private) static class AllArgsPrivate {
```


##### Scenario 27

**Before**
```java
                private AllArgsPrivate(String field1) {
                    this.field1 = field1;
                }
            }
            static class AllArgsProteced {
```

**After**
```java
            }
            @AllArgsConstructor(protected) static class AllArgsProteced {
```


##### Scenario 28

**Before**
```java
                protected AllArgsProteced(String field1) {
                    this.field1 = field1;
                }
```


##### Scenario 29

**Before**
```java
        private String name;
```

**After**
```java
        @Data private String name;
```


##### Scenario 30

**Before**
```java
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
```


##### Scenario 31

**Before**
```java
        private final String name = "1";
```

**After**
```java
        @Value private final String name = "1";
```


##### Scenario 32

**Before**
```java
        public String getName() {
            return name;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
```


##### Scenario 33

**Before**
```java
        private String name;
```

**After**
```java
        @Getter @EqualsAndHashCode private String name;
```


##### Scenario 34

**Before**
```java
        public String getName() {
            return name;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
    }

    class ClassWithBuilder {
```

**After**
```java
    }

    @HasBuilder class ClassWithBuilder {
```


##### Scenario 35

**Before**
```java
        public class FiveConstructors {
            private int field1;
            private String field2;
            private double field3;
```

**After**
```java
        @NoArgsConstructor @AllArgsConstructor public class FiveConstructors {
            @Constructor(2-1) @Constructor(3-1) @Constructor(4-1) private int field1;
            @Constructor(3-2) @Constructor(4-2) private String field2;
            @Constructor(4-3) private double field3;
```


##### Scenario 36

**Before**
```java
            public FiveConstructors() {
            }
            public FiveConstructors(int field1) {
                this.field1 = field1;
            }
            public FiveConstructors(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
            public FiveConstructors(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public FiveConstructors(int field1, String field2, double field3, boolean field4) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                this.field4 = field4;
            }
        }

        public class FiveConstructorsMod {
            private int field1;
            private String field2;
            private double field3;
```

**After**
```java
        }

        @NoArgsConstructor @AllArgsConstructor public class FiveConstructorsMod {
            @Constructor(2-1,private) @Constructor(3-1,default) @Constructor(4-1,protected) private int field1;
            @Constructor(3-2,default) @Constructor(4-2,protected) private String field2;
            @Constructor(4-3,protected) private double field3;
```


##### Scenario 37

**Before**
```java
            public FiveConstructorsMod() {
            }

            private FiveConstructorsMod(int field1) {
                this.field1 = field1;
            }

            FiveConstructorsMod(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }

            protected FiveConstructorsMod(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }

            public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                this.field4 = field4;
            }
```


##### Scenario 38

**Before**
```java
            private final int field1;
            private int field2;
            SingleConstructor(int field1) {
                this.field1 = field1;
            }
```

**After**
```java
            @Constructor(default) private final int field1;
            private int field2;
```


##### Scenario 39

**Before**
```java
            private final int field1;
            private final int field2;
```

**After**
```java
            @Constructor(1,private) private final int field1;
            @Constructor(2,private) private final int field2;
```


##### Scenario 40

**Before**
```java
            private SingleDoubleConstructor(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
```


##### Scenario 41

**Before**
```java
            private final int field1;
            private int field2;
            public SingleConstructorNoMod(int field1) {
                this.field1 = field1;
            }
```

**After**
```java
            @Constructor private final int field1;
            private int field2;
```


##### Scenario 42

**Before**
```java
            private final int field1;
            private final int field2;
```

**After**
```java
            @Constructor(1) private final int field1;
            @Constructor(2) private final int field2;
```


##### Scenario 43

**Before**
```java
            public SingleDoubleConstructorNoMod(int field1, int field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
        }

    }

    class Builders {
```

**After**
```java
        }

    }

    @HasBuilder(FirstBuilder) @HasBuilder(SecondBuilder) @HasBuilder class Builders {
```


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


#### LombokDirtyOffTestData

##### Scenario 1

**Before**
```java
        public class DirtyData {
```

**After**
```java
        @EqualsAndHashCode public class DirtyData {
```


##### Scenario 2

**Before**
```java
            private boolean ok;
```

**After**
```java
            @Getter private boolean ok;
```


##### Scenario 3

**Before**
```java
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
```


##### Scenario 4

**Before**
```java
            boolean ok;

            public boolean isOk() {
                return ok;
            }
```

**After**
```java
            @Getter boolean ok;
```


##### Scenario 5

**Before**
```java
        public class DirtyData {
```

**After**
```java
        @Getter @EqualsAndHashCode public class DirtyData {
```


##### Scenario 6

**Before**
```java
            private boolean ok;
```

**After**
```java
            @Setter private boolean ok;
```


##### Scenario 7

**Before**
```java
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            public boolean isDirty() {
                return dirty;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
```


##### Scenario 8

**Before**
```java
            boolean ok;

            public void setOk(boolean ok) {
                this.ok = ok;
            }
```

**After**
```java
            @Setter boolean ok;
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


#### LombokPatternOffTestData

##### Scenario 1

**Before**
```java
            return "ToStringFull{" +
                    "data=" + data +
                    ", ok=" + ok +
                    '}';
```

**After**
```java
            return "ToStringFull{" + "data=" + data + ", ok=" + ok + '}';
```


##### Scenario 2

**Before**
```java
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
```

**After**
```java
                return "ToStringPartial{" + "data=" + data + '}';
```


##### Scenario 3

**Before**
```java
                return "ValueArgs{" +
                        "field1='" + field1 + '\'' +
                        ", field2=" + field2 +
                        ", field3=" + field3 +
                        '}';
```

**After**
```java
                return "ValueArgs{" + "field1='" + field1 + '\'' + ", field2=" + field2 + ", field3=" + field3 + '}';
```


#### LombokPatternOffNegativeTestData

##### Scenario 1

**Before**
```java
public class LombokPatternOffNegativeTestData {

    private static final long serialVersionUID = 1234567L;
```

**After**
```java
@HasBuilder(ClassWithBuilder) @Getter @Setter @Serial public class LombokPatternOffNegativeTestData {
```


##### Scenario 2

**Before**
```java
    public LombokPatternOffNegativeTestData getData() {
        return data;
    }

    public void setData(LombokPatternOffNegativeTestData data) {
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


##### Scenario 3

**Before**
```java
    public class LombokGetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        public LombokPatternOffNegativeTestData getData() {
            return data;
        }

        public boolean isOk() {
            return ok;
        }
```

**After**
```java
    @Getter public class LombokGetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;
```


##### Scenario 4

**Before**
```java
            boolean ok;

            public boolean isOk() {
                return ok;
            }
        }
    }

    public class LombokSetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        public LombokPatternOffNegativeTestData getData() {
            return data;
        }

        public void setData(LombokPatternOffNegativeTestData data) {
            this.data = data;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }
```

**After**
```java
            @Getter boolean ok;
        }
    }

    @Getter @Setter public class LombokSetters {
        LombokPatternOffNegativeTestData data;
        boolean ok;
```


##### Scenario 5

**After**
```java
            @Setter LombokPatternOffNegativeTestData data;
            boolean ok;
        }

        public class LombokSettersFinalField {
            @Setter LombokPatternOffNegativeTestData data;
            final boolean ok = true;
        }
    }

    @ToString public class ToStringFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        public class ToStringPartial {
            @ToString LombokPatternOffNegativeTestData data;
            boolean ok;
        }

        public class ToStringPartial2 {
            @ToString LombokPatternOffNegativeTestData data;
            boolean ok;
            String string;
        }
    }

    @EqualsAndHashCode public class EqualsAndHashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        public class EqualsAndHashCodePartial {
            @EqualsAndHashCode LombokPatternOffNegativeTestData data;
            boolean ok;
        }

        public class EqualsAndHashCodePartialTwo {
            @EqualsAndHashCode LombokPatternOffNegativeTestData data;
            @EqualsAndHashCode boolean ok;
            String string;
        }
    }

    @Equals public class EqualsFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        public class EqualsPartial {
            @Equals LombokPatternOffNegativeTestData data;
            boolean ok;
        }

        public class EqualsPartialTwo {
            @Equals LombokPatternOffNegativeTestData data;
            @Equals boolean ok;
            String string;
        }
    }

    @HashCode public class HashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        public class HashCodePartial {
            @HashCode LombokPatternOffNegativeTestData data;
            boolean ok;
        }

        public class HashCodePartialTwo {
            @HashCode LombokPatternOffNegativeTestData data;
            @HashCode boolean ok;
            String string;
        }
    }

    @Data public class DataFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        @Data public class DataWithoutToString {
```


##### Scenario 6

**Before**
```java
            public void setData(LombokPatternOffNegativeTestData data) {
                this.data = data;
            }
        }

        public class LombokSettersFinalField {
            LombokPatternOffNegativeTestData data;
            final boolean ok = true;

            public void setData(LombokPatternOffNegativeTestData data) {
                this.data = data;
            }
        }
    }

    public class ToStringFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        @Override
        public String toString() {
            return "ToStringFull{" +
                    "data=" + data +
                    ", ok=" + ok +
                    '}';
        }

        public class ToStringPartial {
            LombokPatternOffNegativeTestData data;
            boolean ok;

            @Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }
        }

        public class ToStringPartial2 {
            LombokPatternOffNegativeTestData data;
            boolean ok;
            String string;

            @Override
            public String toString() {
                return "ToStringPartial{" +
                        "data=" + data +
                        '}';
            }
        }
    }

    public class EqualsAndHashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsAndHashCodeFull that = (EqualsAndHashCodeFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }

        @Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }

        public class EqualsAndHashCodePartial {
            LombokPatternOffNegativeTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartial that = (EqualsAndHashCodePartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }

            @Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }
        }

        public class EqualsAndHashCodePartialTwo {
            LombokPatternOffNegativeTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsAndHashCodePartialTwo that = (EqualsAndHashCodePartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }

            @Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }
    }

    public class EqualsFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EqualsFull that = (EqualsFull) o;
            return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
        }

        public class EqualsPartial {
            LombokPatternOffNegativeTestData data;
            boolean ok;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartial that = (EqualsPartial) o;
                return data != null ? data.equals(that.data) : that.data == null;
            }
        }

        public class EqualsPartialTwo {
            LombokPatternOffNegativeTestData data;
            boolean ok;
            String string;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                EqualsPartialTwo that = (EqualsPartialTwo) o;
                return ok == that.ok && (data != null ? data.equals(that.data) : that.data == null);
            }
        }
    }

    public class HashCodeFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        @Override
        public int hashCode() {
            int result = (data != null ? data.hashCode() : 0);
            result = 31 * result + (ok ? 1 : 0);
            return result;
        }

        public class HashCodePartial {
            LombokPatternOffNegativeTestData data;
            boolean ok;

            @Override
            public int hashCode() {
                return data != null ? data.hashCode() : 0;
            }
        }

        public class HashCodePartialTwo {
            LombokPatternOffNegativeTestData data;
            boolean ok;
            String string;

            @Override
            public int hashCode() {
                int result = (data != null ? data.hashCode() : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
        }
    }

    public class DataFull {
        LombokPatternOffNegativeTestData data;
        boolean ok;

        public LombokPatternOffNegativeTestData getData() {
            return data;
        }

        public void setData(LombokPatternOffNegativeTestData data) {
            this.data = data;
        }

        public boolean isOk() {
            return ok;
        }

        public void setOk(boolean ok) {
            this.ok = ok;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DataFull)) return false;
            DataFull dataFull = (DataFull) o;
            return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("ok", ok)
                    .toString();
        }

        public class DataWithoutToString {
            LombokPatternOffNegativeTestData data;
            boolean ok;

            public LombokPatternOffNegativeTestData getData() {
                return data;
            }

            public void setData(LombokPatternOffNegativeTestData data) {
                this.data = data;
            }

            public boolean isOk() {
                return ok;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokPatternOffNegativeTestData.DataFull)) return false;
                LombokPatternOffNegativeTestData.DataFull dataFull = (LombokPatternOffNegativeTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

        }

        public class DataWithPartialGetters {
            LombokPatternOffNegativeTestData data;
            boolean ok;

            public LombokPatternOffNegativeTestData getData() {
                return data;
            }

            public void setData(LombokPatternOffNegativeTestData data) {
                this.data = data;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokPatternOffNegativeTestData.DataFull)) return false;
                LombokPatternOffNegativeTestData.DataFull dataFull = (LombokPatternOffNegativeTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

            @Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
        }

        public class DataWithPartialSetters {
            LombokPatternOffNegativeTestData data;
            boolean ok;

            public LombokPatternOffNegativeTestData getData() {
                return data;
            }

            public void setData(LombokPatternOffNegativeTestData data) {
                this.data = data;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof LombokPatternOffNegativeTestData.DataFull)) return false;
                LombokPatternOffNegativeTestData.DataFull dataFull = (LombokPatternOffNegativeTestData.DataFull) o;
                return new EqualsBuilder().append(ok, dataFull.ok).append(data, dataFull.data).isEquals();
            }

            @Override
            public int hashCode() {
                return new HashCodeBuilder(17, 37).append(data).append(ok).toHashCode();
            }

            @Override
            public String toString() {
                return new ToStringBuilder(this)
                        .append("data", data)
                        .append("ok", ok)
                        .toString();
            }
```

**After**
```java
        }

        @Setter @ToString @EqualsAndHashCode public class DataWithPartialGetters {
            @Getter LombokPatternOffNegativeTestData data;
            boolean ok;
        }

        @Getter @ToString @EqualsAndHashCode public class DataWithPartialSetters {
            @Setter LombokPatternOffNegativeTestData data;
            boolean ok;
```


##### Scenario 7

**Before**
```java
        public class FoldOnPublic {
            boolean ok;

            public boolean isOk() {
                return ok;
            }
        }

        class FoldOnClass {
            boolean ok;

            public boolean isOk() {
                return ok;
            }
```

**After**
```java
        @Getter public class FoldOnPublic {
            boolean ok;
        }

        @Getter class FoldOnClass {
            boolean ok;
```


##### Scenario 8

**Before**
```java
        class FoldOnWithAnnotation {
            boolean ok;

            public boolean isOk() {
                return ok;
            }
```

**After**
```java
        @Getter class FoldOnWithAnnotation {
            boolean ok;
```


##### Scenario 9

**Before**
```java
        boolean dirty;
        private boolean dirty2;

        public boolean isDirty() {
            return dirty2;
        }

        public boolean isDirty2() {
            return dirty;
        }

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public boolean isDirty() {
                return !dirty;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
```

**After**
```java
        @Getter(dirtyNoReference) boolean dirty;
        @Getter(dirtyNoReference) private boolean dirty2;

        @EqualsAndHashCode public class DirtyData {
            @Getter(dirty) boolean dirty;
            @Getter private boolean ok;
```


##### Scenario 10

**Before**
```java
            boolean dirty;
            boolean ok;

            public boolean isOk() {
                return ok;
            }

            public boolean isDirty() {
                return dirty2;
            }
```

**After**
```java
            @Getter(dirtyNoReference) boolean dirty;
            @Getter boolean ok;
```


##### Scenario 11

**Before**
```java
        boolean dirty;
        private boolean dirty2;

        public void setDirty(boolean dirty) {
            this.dirty2 = dirty;
        }

        public void setDirty2(boolean dirty2) {
            this.dirty = dirty2;
        }

        public class DirtyData {
            boolean dirty;
            private boolean ok;

            public void setDirty(boolean dirty) {
                this.dirty = !dirty;
            }

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            public boolean isDirty() {
                return dirty;
            }

            public boolean isOk() {
                return ok;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof DirtyData)) return false;

                DirtyData dirtyData = (DirtyData) o;

                if (dirty != dirtyData.dirty) return false;
                if (ok != dirtyData.ok) return false;

                return true;
            }

            @Override
            public int hashCode() {
                int result = (dirty ? 1 : 0);
                result = 31 * result + (ok ? 1 : 0);
                return result;
            }
```

**After**
```java
        @Setter(dirtyNoReference) boolean dirty;
        @Setter(dirtyNoReference) private boolean dirty2;

        @Getter @EqualsAndHashCode public class DirtyData {
            @Setter(dirty) boolean dirty;
            @Setter private boolean ok;
```


##### Scenario 12

**Before**
```java
            boolean dirty;
            boolean ok;

            public void setOk(boolean ok) {
                this.ok = ok;
            }

            public void setDirty(boolean dirty) {
                this.ok = dirty;
            }
```

**After**
```java
            @Setter(dirtyNoReference) boolean dirty;
            @Setter boolean ok;
```


##### Scenario 13

**Before**
```java
        public class LogJava {
            Logger log = Logger.getLogger("LogAnnotation.class");
        }

        public class LogJava2 {
            Logger log = Logger.getLogger("LogAnnotation.class");
        }

        public class LogDiffrentFieldName {
            public static final Logger logger = Logger.getLogger("LogAnnotation.class");
        }

        public class LogCustomNameDeprecated {
            @Deprecated
            static final Logger xlogger = Logger.getLogger("LogAnnotation.class");
        }

        public class TripleLogJava {
            Logger log = Logger.getLogger("LogAnnotation.class");
            public static final Logger log2 = Logger.getLogger("LogAnnotation.class");
            static Logger log3 = Logger.getLogger("LogAnnotation.class");
```

**After**
```java
        @Log public class LogJava {
        }

        @Log public class LogJava2 {
        }

        @Log(logger) public class LogDiffrentFieldName {
        }

        @Log(xlogger) public class LogCustomNameDeprecated {
        }

        @Log @Log(log2) @Log(log3) public class TripleLogJava {
```


##### Scenario 14

**Before**
```java
        public class NoArgsConstructor {
            public NoArgsConstructor() {
            }
        }
        class NoArgsConstructorPrivate {
            private NoArgsConstructorPrivate() {
            }
        }
        public class NoArgsConstructorSuperBefore {
            public NoArgsConstructorSuperBefore() {
                // comment
                super();
            }
        }
        public class NoArgsConstructorSuperAfter {
            public NoArgsConstructorSuperAfter() {
                super();
                // comment
            }
        }

        public class ProtectedNoArgsConstructorSuperAfter {
            protected ProtectedNoArgsConstructorSuperAfter() {
                // comment
            }
        }
        public class NoArgsConstructorSuper {
            private String field1;
            public NoArgsConstructorSuper() {
                super();
            }
```

**After**
```java
        @NoArgsConstructor public class NoArgsConstructor {
        }
        @NoArgsConstructor(private) class NoArgsConstructorPrivate {
        }
        @NoArgsConstructor(// comment) public class NoArgsConstructorSuperBefore {
        }
        @NoArgsConstructor(// comment) public class NoArgsConstructorSuperAfter {
        }

        @NoArgsConstructor(protected,// comment) public class ProtectedNoArgsConstructorSuperAfter {
        }
        @NoArgsConstructor public class NoArgsConstructorSuper {
            private String field1;
```


##### Scenario 15

**Before**
```java
        public static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @AllArgsConstructor public static class AllArgs {
            private String field1;
            private int field2;
            private boolean field3;
```


##### Scenario 16

**Before**
```java
        public static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;
            public AllArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }

        public static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;
            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @AllArgsConstructor public static class AllArgsSuper {
            private String field1;
            private int field2;
            private boolean field3;
        }

        @AllArgsConstructor(private) public static class StaticNameArgs {
            private String field1;
            private int field2;
            private boolean field3;
```


##### Scenario 17

**Before**
```java
        public static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;
            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @AllArgsConstructor(protected) public static class ProtectedArgs {
            private String field1;
            private int field2;
            private boolean field3;
```


##### Scenario 18

**Before**
```java
        public static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @RequiredArgsConstructor public static class RequiredArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
```


##### Scenario 19

**Before**
```java
        public static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;

            public RequiredArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
        }

        public static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            private StaticNameArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @RequiredArgsConstructor public static class RequiredArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }

        @RequiredArgsConstructor(private) public static class StaticNameArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
```


##### Scenario 20

**Before**
```java
        public static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;

            protected ProtectedArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
```

**After**
```java
        @RequiredArgsConstructor(protected) public static class ProtectedArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
```


##### Scenario 21

**Before**
```java
        public static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgs(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public String getField1() {
                return field1;
            }
            public int getField2() {
                return field2;
            }
            public boolean isField3() {
                return field3;
            }
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ValueArgs valueArgs = (ValueArgs) o;
                if (field2 != valueArgs.field2) return false;
                if (field3 != valueArgs.field3) return false;
                return field1 != null ? field1.equals(valueArgs.field1) : valueArgs.field1 == null;
            }
            @Override
            public int hashCode() {
                int result = field1 != null ? field1.hashCode() : 0;
                result = 31 * result + field2;
                result = 31 * result + (field3 ? 1 : 0);
                return result;
            }

            @Override
            public String toString() {
                return "ValueArgs{" +
                        "field1='" + field1 + '\'' +
                        ", field2=" + field2 +
                        ", field3=" + field3 +
                        '}';
            }
        }
        public static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueArgsSuper(String field1, int field2, boolean field3) {
                super();
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public String getField1() {
                return field1;
            }
            public int getField2() {
                return field2;
            }
            public boolean isField3() {
                return field3;
            }
            @Override
            public final boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof ValueArgsSuper)) return false;

                ValueArgsSuper that = (ValueArgsSuper) o;
                return field2 == that.field2 && field3 == that.field3 && Objects.equals(field1, that.field1);
            }

            @Override
            public int hashCode() {
                int result = Objects.hashCode(field1);
                result = 31 * result + field2;
                result = 31 * result + Boolean.hashCode(field3);
                return result;
            }
        }
        public static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;
            public ValueWihhoutEqualsAndHashcode(String field1, int field2, boolean field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public String getField1() {
                return field1;
            }
            public int getField2() {
                return field2;
            }
            public boolean isField3() {
                return field3;
            }
```

**After**
```java
        @Value public static class ValueArgs {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }
        @Value public static class ValueArgsSuper {
            private final String field1;
            private final int field2;
            private final boolean field3;
        }
        @LightValue public static class ValueWihhoutEqualsAndHashcode {
            private final String field1;
            private final int field2;
            private final boolean field3;
```


##### Scenario 22

**Before**
```java
        public static class AllArgs {
            private String field1;
            public AllArgs(String field1) {
                this.field1 = field1;
            }
        }
        public static class ReqArgs {
            private final String field1;
            public ReqArgs(String field1) {
                this.field1 = field1;
            }
        }
        public static class Value {
            private final String field1;
            public Value(String field1) {
                this.field1 = field1;
            }
            public String getField1() {
                return field1;
            }
            @Override
            public final boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Value)) return false;

                Value value = (Value) o;
                return Objects.equals(field1, value.field1);
            }
            @Override
            public int hashCode() {
                return Objects.hashCode(field1);
            }
        }
        public static class ValueWithoutEqualsAndHashCode {
            private final String field1;
            public ValueWithoutEqualsAndHashCode(String field1) {
                this.field1 = field1;
            }
            public String getField1() {
                return field1;
            }
```

**After**
```java
        @AllArgsConstructor public static class AllArgs {
            private String field1;
        }
        @RequiredArgsConstructor public static class ReqArgs {
            private final String field1;
        }
        @Value public static class Value {
            private final String field1;
        }
        @LightValue public static class ValueWithoutEqualsAndHashCode {
            private final String field1;
```


##### Scenario 23

**Before**
```java
            static class AllArgsDefault {
```

**After**
```java
            @AllArgsConstructor(default) static class AllArgsDefault {
```


##### Scenario 24

**Before**
```java
                AllArgsDefault(String field1) {
                    this.field1 = field1;
                }
            }
            static class AllArgsPrivate {
```

**After**
```java
            }
            @AllArgsConstructor(private) static class AllArgsPrivate {
```


##### Scenario 25

**Before**
```java
                private AllArgsPrivate(String field1) {
                    this.field1 = field1;
                }
            }
            static class AllArgsProteced {
```

**After**
```java
            }
            @AllArgsConstructor(protected) static class AllArgsProteced {
```


##### Scenario 26

**Before**
```java
                protected AllArgsProteced(String field1) {
                    this.field1 = field1;
                }
```


##### Scenario 27

**Before**
```java
        private String name;
```

**After**
```java
        @Data private String name;
```


##### Scenario 28

**Before**
```java
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
```


##### Scenario 29

**Before**
```java
        private final String name = "1";
```

**After**
```java
        @Value private final String name = "1";
```


##### Scenario 30

**Before**
```java
        public String getName() {
            return name;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
```


##### Scenario 31

**Before**
```java
        private String name;
```

**After**
```java
        @Getter @EqualsAndHashCode private String name;
```


##### Scenario 32

**Before**
```java
        public String getName() {
            return name;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldLevelData)) return false;
            FieldLevelData that = (FieldLevelData) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
    }

    class ClassWithBuilder {
```

**After**
```java
    }

    @HasBuilder class ClassWithBuilder {
```


##### Scenario 33

**Before**
```java
        public class FiveConstructors {
            private int field1;
            private String field2;
            private double field3;
```

**After**
```java
        @NoArgsConstructor @AllArgsConstructor public class FiveConstructors {
            @Constructor(2-1) @Constructor(3-1) @Constructor(4-1) private int field1;
            @Constructor(3-2) @Constructor(4-2) private String field2;
            @Constructor(4-3) private double field3;
```


##### Scenario 34

**Before**
```java
            public FiveConstructors() {
            }
            public FiveConstructors(int field1) {
                this.field1 = field1;
            }
            public FiveConstructors(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
            public FiveConstructors(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public FiveConstructors(int field1, String field2, double field3, boolean field4) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                this.field4 = field4;
            }
        }

        public class FiveConstructorsMod {
            private int field1;
            private String field2;
            private double field3;
```

**After**
```java
        }

        @NoArgsConstructor @AllArgsConstructor public class FiveConstructorsMod {
            @Constructor(2-1,private) @Constructor(3-1,default) @Constructor(4-1,protected) private int field1;
            @Constructor(3-2,default) @Constructor(4-2,protected) private String field2;
            @Constructor(4-3,protected) private double field3;
```


##### Scenario 35

**Before**
```java
            public FiveConstructorsMod() {
            }
            private FiveConstructorsMod(int field1) {
                this.field1 = field1;
            }
            FiveConstructorsMod(int field1, String field2) {
                this.field1 = field1;
                this.field2 = field2;
            }
            protected FiveConstructorsMod(int field1, String field2, double field3) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
            }
            public FiveConstructorsMod(int field1, String field2, double field3, boolean field4) {
                this.field1 = field1;
                this.field2 = field2;
                this.field3 = field3;
                this.field4 = field4;
            }
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

