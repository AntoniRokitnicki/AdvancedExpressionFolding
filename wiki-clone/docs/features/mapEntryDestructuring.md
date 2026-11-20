# Map Entry Destructuring (State field: mapEntryDestructuring)

### Destructuring for-each over Map.entrySet() as (key, value)
Shows a for-each over `map.entrySet()` the way Kotlin writes it: the loop header becomes `(key, value) in map`
and every `e.getKey()` / `e.getValue()` in the body becomes `key` / `value`.

#### Example: MapEntryDestructuringTestData

examples/data/MapEntryDestructuringTestData.java:
```java
        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
```

folded/MapEntryDestructuringTestData-folded.java:
```java
        for ((key, value) in stock) {
            System.out.println("$key: $value");
        }
```

The loop stays as it is when the shown names would hide something:
- the entry is used in any other way, e.g. `e.setValue(...)` or passed as an argument;
- the body already declares or reads a plain `key` or `value`;
- the loop does not iterate `entrySet()` of a `java.util.Map`.

Default: On
Controlled by: `mapEntryDestructuring`
Related features: `concatenationExpressionsCollapse` (string interpolation), `println`, `compactControlFlowSyntaxCollapse`
