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

---
### Folding catalogue

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

