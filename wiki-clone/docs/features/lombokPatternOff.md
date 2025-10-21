# Lombok Pattern Off (State field: lombokPatternOff)

### Lombok Pattern Off
Uses a regex to disable Lombok folding for matching classes.

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

Highlights LombokPatternOffTestData with lombok pattern off.
Removes boilerplate while preserving behavior.

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

Highlights LombokPatternOffNegativeTestData with lombok pattern off.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `lombokPatternOff`
Related features: (none)
