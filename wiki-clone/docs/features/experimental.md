# Experimental (State field: experimental)

### Experimental
Enables experimental folding prototypes.

#### Example: ExperimentalTestData

examples/data/ExperimentalTestData.java:
```java
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
// ...
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
```

folded/ExperimentalTestData-folded.java:
```java
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].getBytes();
// ...
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
```

Highlights ExperimentalTestData with experimental.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `experimental`
Related features: (none)
---

#### Folding catalogue

##### SneakyThrows scaffolding
| Before | After |
| --- | --- |
| `<pre>try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
                return new String(bytez, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }</pre>` | `<pre>@SneakyThrows {
                byte[] bytez = System["sort-desc"].bytes;
                return new String(bytez, "UTF-8");
            }</pre>` |
| `<pre>try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }</pre>` | `@SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);` |
| `<pre>try {
                Function<String, Long> longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }</pre>` | `<pre>@SneakyThrows(IllegalArgumentException) {
                Function<String, Long> longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            }</pre>` |
| `<pre>try {
                var throwable = new Throwable();
                throw throwable;
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }</pre>` | `<pre>@SneakyThrows {
                var throwable = new Throwable();
                throw throwable;
            }</pre>` |
| `<pre>try {
            throw new Throwable();
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }</pre>` | `@SneakyThrows
            throw new Throwable();` |

##### Property shortcuts
| Before | After |
| --- | --- |
| `System.getProperty("sort-desc").getBytes();` | `System["sort-desc"].bytes;` |
| `new String(System.getProperty("sort-desc").getBytes(), "UTF-8");` | `new String(System["sort-desc"].bytes, "UTF-8");` |

##### Nameless accessor placeholders
| Before | After |
| --- | --- |
| `example.set("ok");` | `example.!! = "ok";` |
| `String current = example.get();` | `String current = example.!!;` |
| `System.out.println(example.get().isEmpty());` | `System.out.println(example.!!.empty);` |
| `example.set(example.get());` | `example.!! = example.!!;` |
| `String duplicate = example.get() + example.get();` | `String duplicate = example.!! + example.!!;` |
