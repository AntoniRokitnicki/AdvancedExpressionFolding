new video:
[Lombok](https://www.youtube.com/watch?v=u938oDp7Vuk)
![Animated overview of Lombok folding support](https://github.com/user-attachments/assets/7d2bdcf7-15ad-45a2-9aad-1f596443c4d7)



old video:
[video](https://www.youtube.com/watch?v=dKMWL3YJacU)

#### dirty
Since v1.0.52
Field level `@Getter` & `@Setter`. If method is doing something more that it should it's marked as dirty with `@Getter(dirty)` & `@Setter(dirty)`.


### @HasBuilder
Folds a builder pattern implementation into a single annotation.

LombokTestData.java:
```java
class ClassWithBuilder {
    private String name;
    class ClassWithBuilderBuilder {
        private String name;
        public ClassWithBuilderBuilder name(String name) {
            this.name = name;
            return this;
        }
        public ClassWithBuilder build() {
            return new ClassWithBuilder();
        }
    }
}
```

LombokTestData-folded.java:
```java
@HasBuilder class ClassWithBuilder {
    private String name;
}
```

### @Getter
Folds getter methods into a single annotation.

LombokTestData.java:
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
}
```

LombokTestData-folded.java:
```java
@Getter public class LombokGetters {
    LombokTestData data;
    boolean ok;
}
```

### @Setter
Folds setter methods into a single annotation.

LombokTestData.java:
```java
public class LombokSetters {
    LombokTestData data;
    boolean ok;

    public void setData(LombokTestData data) {
        this.data = data;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
```

LombokTestData-folded.java:
```java
@Setter public class LombokSetters {
    LombokTestData data;
    boolean ok;
}
```

### @Serial
Folds serialVersionUID declaration into an annotation.

LombokTestData.java:
```java
public class LombokTestData {
    private static final long serialVersionUID = 1234567L;
    // ...
}
```

LombokTestData-folded.java:
```java
@Serial public class LombokTestData {
    // ...
}
```

### @ToString
Folds toString() method implementation into an annotation.

LombokTestData.java:
```java
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
}
```

LombokTestData-folded.java:
```java
@ToString public class ToStringFull {
    LombokTestData data;
    boolean ok;
}
```

### @EqualsAndHashCode
Folds both equals() and hashCode() method implementations into a single annotation.

LombokTestData.java:
```java
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
}
```

LombokTestData-folded.java:
```java
@EqualsAndHashCode public class EqualsAndHashCodeFull {
    LombokTestData data;
    boolean ok;
}
```

### @Equals
Folds only the equals() method implementation into an annotation.

LombokTestData.java:
```java
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
}
```

LombokTestData-folded.java:
```java
@Equals public class EqualsFull {
    LombokTestData data;
    boolean ok;
}
```

### @HashCode
Folds only the hashCode() method implementation into an annotation.

LombokTestData.java:
```java
public class HashCodeFull {
    LombokTestData data;
    boolean ok;

    @Override
    public int hashCode() {
        int result = (data != null ? data.hashCode() : 0);
        result = 31 * result + (ok ? 1 : 0);
        return result;
    }
}
```

LombokTestData-folded.java:
```java
@HashCode public class HashCodeFull {
    LombokTestData data;
    boolean ok;
}
```

### @Data
Folds getter, setter, equals(), hashCode(), and toString() methods into a single annotation.

LombokTestData.java:
```java
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
}
```

LombokTestData-folded.java:
```java
@Data public class DataFull {
    LombokTestData data;
    boolean ok;
}
```

### @Value
Folds immutable class implementation (final fields, private constructor, getters, equals, hashCode, and toString) into a single annotation.

LombokTestData.java:
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
```

LombokTestData-folded.java:
```java
@Value public static class ValueArgs {
    private final String field1;
    private final int field2;
    private final boolean field3;
}
```

### @NoArgsConstructor
Folds no-argument constructor implementation into an annotation.

LombokTestData.java:
```java
public class NoArgsConstructor {
    public NoArgsConstructor() {
    }
}
```

LombokTestData-folded.java:
```java
@NoArgsConstructor public class NoArgsConstructor {
}
```

### @AllArgsConstructor
Folds all-arguments constructor implementation into an annotation.

LombokTestData.java:
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
}
```

LombokTestData-folded.java:
```java
@AllArgsConstructor public static class AllArgs {
    private String field1;
    private int field2;
    private boolean field3;
}
```

### @RequiredArgsConstructor
Folds constructor implementation for final fields into an annotation.

LombokTestData.java:
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
}
```

LombokTestData-folded.java:
```java
@RequiredArgsConstructor public static class RequiredArgs {
    private final String field1;
    private final int field2;
    private final boolean field3;
}
```

### @Log
Folds logger field declaration into an annotation.

LombokTestData.java:
```java
public class LogJava {
    Logger log = Logger.getLogger("LogAnnotation.class");
}
```

LombokTestData-folded.java:
```java
@Log public class LogJava {
}
```

### @Constructor
Specifies custom constructors to be generated. This is used in combination with other annotations.

LombokTestData.java:
```java
public class FiveConstructors {
    private int field1;
    private String field2;
    private double field3;
    private boolean field4;
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
```

LombokTestData-folded.java:
```java
@NoArgsConstructor @AllArgsConstructor public class FiveConstructors {
    @Constructor(2-1) @Constructor(3-1) @Constructor(4-1) private int field1;
    @Constructor(3-2) @Constructor(4-2) private String field2;
    @Constructor(4-3) private double field3;
    private boolean field4;
}
```

modifier support:
```java
@NoArgsConstructor @AllArgsConstructor public class FiveConstructorsMod {
    @Constructor(2-1,private) @Constructor(3-1,default) @Constructor(4-1,protected) private int field1;
    @Constructor(3-2,default) @Constructor(4-2,protected) private String field2;
    @Constructor(4-3,protected) private double field3;
    private boolean field4;
}
```

### @LightValue
This seems to be a custom annotation not present in standard Lombok. It appears to create an immutable class without equals and hashCode methods.

LombokTestData.java:
```java
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
}
```

LombokTestData-folded.java:
```java
@LightValue public static class ValueWihhoutEqualsAndHashcode {
    private final String field1;
    private final int field2;
    private final boolean field3;
}
```

### Field-level annotations
Some annotations can be applied at the field level to generate methods for specific fields only.

LombokTestData.java:
```java
class FieldLevelNotFinalNotValue {
    private String name;
    private String ignored;

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
```

LombokTestData-folded.java:
```java
class FieldLevelNotFinalNotValue {
    @Getter @EqualsAndHashCode private String name;
    private String ignored;
}
```

These examples demonstrate how the Lombok Emulator & Validator folds standard Java code into Lombok-style annotations, providing a more concise representation of the same functionality.