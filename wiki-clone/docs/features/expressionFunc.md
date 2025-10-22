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
