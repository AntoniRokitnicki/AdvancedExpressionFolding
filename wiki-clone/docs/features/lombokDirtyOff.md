# Lombok Dirty Off (State field: lombokDirtyOff)

### Lombok Dirty Off
Skips folding Lombok accessors marked as dirty.

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

Highlights LombokDirtyOffTestData with lombok dirty off.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `lombokDirtyOff`
Related features: (none)
