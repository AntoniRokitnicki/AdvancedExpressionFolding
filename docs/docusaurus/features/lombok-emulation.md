---
title: Lombok Emulator
slug: /features/lombok-emulation
sidebar_label: Lombok Emulator
description: Preview how classic POJOs collapse into Lombok annotations without touching the source files.
---

Enabling **lombok** activates an emulator that recognises standard Java boilerplate and replaces it with the Lombok annotations you would normally hand-write. The folding is visual only—the underlying code stays untouched—so teams can enjoy concise views even when Lombok is not available on the classpath.

> Dirty getters and setters that deviate from the default implementation are marked with `@Getter(dirty)` / `@Setter(dirty)` so you can spot them instantly.

## Builders

```java title="Original"
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

```java title="Folded"
@Builder class ClassWithBuilder {
    private String name;
}
```

## Accessors

```java title="Original"
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

```java title="Folded"
@Getter public class LombokGetters {
    LombokTestData data;
    boolean ok;
}
```

```java title="Original"
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

```java title="Folded"
@Setter public class LombokSetters {
    LombokTestData data;
    boolean ok;
}
```

## Serialisation helpers

```java title="Original"
public class LombokTestData {
    private static final long serialVersionUID = 1234567L;
}
```

```java title="Folded"
@Serial public class LombokTestData {
}
```

## toString, equals, and hashCode

```java title="Original"
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

```java title="Folded"
@ToString public class ToStringFull {
    LombokTestData data;
    boolean ok;
}
```

```java title="Original"
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

```java title="Folded"
@EqualsAndHashCode public class EqualsAndHashCodeFull {
    LombokTestData data;
    boolean ok;
}
```

Variants that contain only `equals` or only `hashCode` collapse into `@Equals` / `@HashCode` respectively.

## Aggregated data classes

```java title="Original"
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

```java title="Folded"
@Data public class DataFull {
    LombokTestData data;
    boolean ok;
}
```

```java title="Original"
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

```java title="Folded"
@Value public static class ValueArgs {
    private final String field1;
    private final int field2;
    private final boolean field3;
}
```

`@LightValue` follows the same pattern but omits `equals`/`hashCode` when the original class does not supply them.

## Constructors and logging

```java title="Original"
public class NoArgsConstructor {
    public NoArgsConstructor() {
    }
}
```

```java title="Folded"
@NoArgsConstructor public class NoArgsConstructor {
}
```

```java title="Original"
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

```java title="Folded"
@AllArgsConstructor public static class AllArgs {
    private String field1;
    private int field2;
    private boolean field3;
}
```

```java title="Original"
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

```java title="Folded"
@RequiredArgsConstructor public static class RequiredArgs {
    private final String field1;
    private final int field2;
    private final boolean field3;
}
```

```java title="Original"
public class LogJava {
    Logger log = Logger.getLogger("LoggableAnnotation.class");
}
```

```java title="Folded"
@Loggable public class LogJava {
}
```

`@Constructor` markers highlight which fields participate in which constructor overloads, and the folding honours any explicit visibility modifiers.

## Field-level annotations

Some annotations can target individual fields. For example, only specific members may get getters or `equals`/`hashCode` participation:

```java title="Original"
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

```java title="Folded"
class FieldLevelNotFinalNotValue {
    @Getter @EqualsAndHashCode private String name;
    private String ignored;
}
```

All examples come from `examples/data/LombokTestData.java`—open the file alongside its folded counterpart to explore additional combinations and dirty marker behaviour.
