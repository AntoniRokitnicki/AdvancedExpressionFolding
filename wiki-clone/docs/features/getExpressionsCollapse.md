# Get Expressions Collapse (State field: getExpressionsCollapse)

### Get Expressions Collapse
Folds collection access and literal builders into indexed or map-style expressions.

#### Example: GetSetPutTestData

examples/data/GetSetPutTestData.java:
```java
        List<String> list = Arrays.asList("one", "two");
        list.set(1,"three" );
        System.out.println(list.get(list.size() - 1));
        System.out.println(args[args.length - 1]);
// ...
        map.put("one", 1);
        System.out.println(map.get("two"));
        List<String> singleton = java.util.Collections.singletonList("one");
```

folded/GetSetPutTestData-folded.java:
```java
        List<String> list = ["one", "two"];
        list[1] = "three";
        System.out.println(list.getLast());
        System.out.println(args.last());
// ...
        map["one"] = 1;
        System.out.println(map["two"]);
        List<String> singleton = ["one"];
```

Highlights GetSetPutTestData with get expressions collapse.
Removes boilerplate while preserving behavior.

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

Highlights ExperimentalTestData with get expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `getExpressionsCollapse`
Related features: (none)
---

#### Folding catalogue

##### GetSetPutTestData mappings
| Before | After |
| --- | --- |
| `List<String> list = Arrays.asList("one", "two");` | `List<String> list = ["one", "two"];` |
| `list.set(1,"three" );` | `list[1] = "three";` |
| `System.out.println(list.get(list.size() - 1));` | `System.out.println(list.getLast());` |
| `System.out.println(args[args.length - 1]);` | `System.out.println(args.last());` |
| `map.put("one", 1);` | `map["one"] = 1;` |
| `System.out.println(map.get("two"));` | `System.out.println(map["two"]);` |
| `List<String> singleton = java.util.Collections.singletonList("one");` | `List<String> singleton = ["one"];` |
| `List<String> asList = Arrays.asList("one", "two");` | `List<String> asList = ["one", "two"];` |
| `List<String> copy = new ArrayList<>(Arrays.asList("one", "two"));` | `List<String> copy = ["one", "two"];` |
| `List<String> unmodifiable = Collections.unmodifiableList(Arrays.asList("one", "two"));` | `List<String> unmodifiable = ["one", "two"];` |
| `Set<String> set = new HashSet<String>() { { add("one"); add("two"); } };` | `Set<String> set = ["one", "two"];` |
| `Set<String> copyOfSet = Collections.unmodifiableSet(new HashSet<String>() { { add("one"); add("two"); } });` | `Set<String> copyOfSet = ["one", "two"];` |
| `String[] strings = new String[] {"one", "two"};` | `String[] strings = ["one", "two"];` |
| `System.out.println(System.getProperty("user.dir"));` | `System.out.println(System["user.dir"]);` |
| `System.out.println(System.getenv().get("user.dir"));` | `System.out.println(System.getenv()["user.dir"]);` |

##### ExperimentalTestData mappings for get expressions
| Before | After |
| --- | --- |
| `byte[] bytez = System.getProperty("sort-desc").getBytes();` | `byte[] bytez = System["sort-desc"].bytes;` |
| `return new String(System.getProperty("sort-desc").getBytes(), "UTF-8");` | `return new String(System["sort-desc"].bytes, "UTF-8");` |
| `example.set("ok");` | `example.!! = "ok";` |
| `String current = example.get();` | `String current = example.!!;` |
| `System.out.println(example.get().isEmpty());` | `System.out.println(example.!!.empty);` |
| `example.set(example.get());` | `example.!! = example.!!;` |
| `String duplicate = example.get() + example.get();` | `String duplicate = example.!! + example.!!;` |
