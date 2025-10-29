# Expression Func (State field: expressionFunc)

### Expression Func
Folds single-expression methods into expression-bodied functions.

#### Example: ExpressionFuncTestData

examples/data/ExpressionFuncTestData.java:
```java
    public long findNinjaId() {
        return 1L;
    }
// ...
    private void printStatus() {
        new HashMap<String, String>().put("a", "b");
    }
```

folded/ExpressionFuncTestData-folded.java:
```java
    findNinjaId { 1L }
// ...
    private void printStatus() { new HashMap<String, String>().put("a", "b") }
```

Highlights ExpressionFuncTestData with expression func.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `expressionFunc`
Related features: (none)

---
### Folding catalogue

#### ExpressionFuncTestData

##### Scenario 1

**Before**
```java
    public long findNinjaId() {
        return 1L;
    }
```

**After**
```java
    findNinjaId { 1L }
```


##### Scenario 2

**Before**
```java
    private void printStatus() {
        new HashMap<String, String>().put("a", "b");
    }
```

**After**
```java
    private void printStatus() { new HashMap<String, String>().put("a", "b") }
```


##### Scenario 3

**Before**
```java
    private String printStatusReturn() {
        return new HashMap<String, String>().put("a", "b");
    }
```

**After**
```java
    private String printStatusReturn() { new HashMap<String, String>().put("a", "b") }
```


##### Scenario 4

**Before**
```java
    public boolean isUser() {
        return false;
    }
```

**After**
```java
    isUser { false }
```


##### Scenario 5

**Before**
```java
    public String tableName() {
        return "table1";
    }
```

**After**
```java
    tableName { "table1" }
```


##### Scenario 6

**Before**
```java
    public String columnName(String column) {
        return "column1";
    }
```

**After**
```java
    columnName(String column) { "column1" }
```


##### Scenario 7

**Before**
```java
    public void assignField(String field) {
        this.field = field;
    }
```

**After**
```java
    public void assignField(String field) { this.field = field }
```


##### Scenario 8

**Before**
```java
    public String assignFieldAndReturn(String field) {
        return this.field = field;
    }
```

**After**
```java
    public String assignFieldAndReturn(String field) { this.field = field }
```


##### Scenario 9

**Before**
```java
    public String methodCall(String field) {
        return assignFieldAndReturn(field);
    }
```

**After**
```java
    public String methodCall(String field) { assignFieldAndReturn(field) }
```


##### Scenario 10

**Before**
```java
    public void methodCall2(String field) {
        assignFieldAndReturn(field);
    }
```

**After**
```java
    public void methodCall2(String field) { assignFieldAndReturn(field) }
```


##### Scenario 11

**Before**
```java
    public void streamShort(List<String> list) {
        list.stream().map(Function.identity()).map(Function.identity());
    }
```

**After**
```java
    public void streamShort(List<String> list) { list.stream().map(Function.identity()).map(Function.identity()) }
```


##### Scenario 12

**Before**
```java
        public long getId() {
            return 0L;
        }
```

**After**
```java
        getId { 0L }
```


##### Scenario 13

**Before**
```java
        public long getId() {
            return 2L;
        }
```

**After**
```java
        getId { 2L }
```


##### Scenario 14

**Before**
```java
        public long getId() {
            return 3L;
        }
```

**After**
```java
        getId { 3L }
```


##### Scenario 15

**Before**
```java
        public long getId() {
            return 1L;
        }
```

**After**
```java
        getId { 1L }
```

