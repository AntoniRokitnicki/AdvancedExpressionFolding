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
---

#### Folding catalogue

##### LombokPatternOffTestData mappings
| Before | After |
| --- | --- |
| `<pre>return "ToStringFull{" +
                    "data=" + data +
                    ", ok=" + ok +
                    '}';</pre>` | `return "ToStringFull{" + "data=" + data + ", ok=" + ok + '}';` |
| `<pre>return "ToStringPartial{" +
                        "data=" + data +
                        '}';</pre>` | `return "ToStringPartial{" + "data=" + data + '}';` |
| `<pre>return "ToStringPartial{" +
                        "data=" + data +
                        '}';</pre>` (partial2) | `return "ToStringPartial{" + "data=" + data + '}';` |

##### LombokPatternOffNegativeTestData mappings
| Before | After |
| --- | --- |
| `public class LombokPatternOffNegativeTestData {` | `@HasBuilder(ClassWithBuilder) @Getter @Setter @Serial public class LombokPatternOffNegativeTestData {` |
| `public class LombokGetters {` | `@Getter public class LombokGetters {` |
| `public class LombokSetters {` | `@Getter @Setter public class LombokSetters {` |
| `public class ToStringFull {` | `@ToString public class ToStringFull {` |
| `public class EqualsAndHashCodeFull {` | `@EqualsAndHashCode public class EqualsAndHashCodeFull {` |
| `public class EqualsFull {` | `@Equals public class EqualsFull {` |
| `public class HashCodeFull {` | `@HashCode public class HashCodeFull {` |
| `public class DataFull {` | `@Data public class DataFull {` |
| `public class FoldOn {` | `public class FoldOn {` (unchanged container with annotated members) |
