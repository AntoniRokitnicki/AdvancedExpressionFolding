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

#### Folding catalogue

##### ExpressionFuncTestData mappings
| Before | After |
| --- | --- |
| `<pre>public long findNinjaId() {
        return 1L;
    }</pre>` | `findNinjaId { 1L }` |
| `<pre>private void printStatus() {
        new HashMap<String, String>().put("a", "b");
    }</pre>` | `private void printStatus() { new HashMap<String, String>().put("a", "b") }` |
| `<pre>private String printStatusReturn() {
        return new HashMap<String, String>().put("a", "b");
    }</pre>` | `private String printStatusReturn() { new HashMap<String, String>().put("a", "b") }` |
| `<pre>public boolean isUser() {
        return false;
    }</pre>` | `isUser { false }` |
| `<pre>public String tableName() {
        return "table1";
    }</pre>` | `tableName { "table1" }` |
| `<pre>public String columnName(String column) {
        return "column1";
    }</pre>` | `columnName(String column) { "column1" }` |
| `<pre>public void assignField(String field) {
        this.field = field;
    }</pre>` | `public void assignField(String field) { this.field = field }` |
| `<pre>public String assignFieldAndReturn(String field) {
        return this.field = field;
    }</pre>` | `public String assignFieldAndReturn(String field) { this.field = field }` |
| `<pre>public String methodCall(String field) {
        return assignFieldAndReturn(field);
    }</pre>` | `public String methodCall(String field) { assignFieldAndReturn(field) }` |
| `<pre>public void methodCall2(String field) {
        assignFieldAndReturn(field);
    }</pre>` | `public void methodCall2(String field) { assignFieldAndReturn(field) }` |
| `<pre>public void streamShort(List<String> list) {
        list.stream().map(Function.identity()).map(Function.identity());
    }</pre>` | `public void streamShort(List<String> list) { list.stream().map(Function.identity()).map(Function.identity()) }` |
| `<pre>public long getId() {
            return 0L;
        }</pre>` | `getId { 0L }` |
| `<pre>public long getId() {
            return 2L;
        }</pre>` | `getId { 2L }` |
| `<pre>public long getId() {
            return 3L;
        }</pre>` | `getId { 3L }` |
| `<pre>public long getId() {
            return 1L;
        }</pre>` | `getId { 1L }` |
