# Get Expressions Collapse

## Overview

Folds collection access and literal builders into indexed or map-style expressions.


## Configuration

- **Toggle ID:** `getExpressionsCollapse`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

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


---
### Folding catalogue

#### GetSetPutTestData

##### Scenario 1

**Before**
```java
        List<String> list = Arrays.asList("one", "two");
        list.set(1,"three" );
        System.out.println(list.get(list.size() - 1));
        System.out.println(args[args.length - 1]);
```

**After**
```java
        List<String> list = ["one", "two"];
        list[1] = "three";
        System.out.println(list.getLast());
        System.out.println(args.last());
```


##### Scenario 2

**Before**
```java
        map.put("one", 1);
        System.out.println(map.get("two"));
        List<String> singleton = java.util.Collections.singletonList("one");
```

**After**
```java
        map["one"] = 1;
        System.out.println(map["two"]);
        List<String> singleton = ["one"];
```


##### Scenario 3

**Before**
```java
        List<String> asList = Arrays.asList("one", "two");
```

**After**
```java
        List<String> asList = ["one", "two"];
```


##### Scenario 4

**Before**
```java
        List<String> copy = new ArrayList<>(Arrays.asList("one", "two"));
```

**After**
```java
        List<String> copy = ["one", "two"];
```


##### Scenario 5

**Before**
```java
        List<String> unmodifiable = Collections.unmodifiableList(Arrays.asList("one", "two"));
```

**After**
```java
        List<String> unmodifiable = ["one", "two"];
```


##### Scenario 6

**Before**
```java
        Set<String> set = new HashSet<String>() {
            {
                add("one");
                add("two");
            }
        };
```

**After**
```java
        Set<String> set = ["one", "two"];
```


##### Scenario 7

**Before**
```java
        Set<String> copyOfSet = Collections.unmodifiableSet(new HashSet<String>() {
            {
                add("one");
                add("two");
            }
        });
```

**After**
```java
        Set<String> copyOfSet = ["one", "two"];
```


##### Scenario 8

**Before**
```java
        String[] strings = new String[] {"one", "two"};
```

**After**
```java
        String[] strings = ["one", "two"];
```


##### Scenario 9

**Before**
```java
        System.out.println(System.getProperty("user.dir"));
```

**After**
```java
        System.out.println(System["user.dir"]);
```


##### Scenario 10

**Before**
```java
        System.out.println(System.getenv().get("user.dir"));
```

**After**
```java
        System.out.println(System.getenv()["user.dir"]);
```


#### ExperimentalTestData

##### Scenario 1

**Before**
```java
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
```

**After**
```java
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].bytes;
```


##### Scenario 2

**Before**
```java
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
```


##### Scenario 3

**Before**
```java
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
```

**After**
```java
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
```


##### Scenario 4

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows(IllegalArgumentException) {
```


##### Scenario 5

**Before**
```java
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
```


##### Scenario 6

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows {
```


##### Scenario 7

**Before**
```java
            } catch (Throwable t) {
                throw new IllegalStateException(t);
```


##### Scenario 8

**Before**
```java
            try {
                return new String(System.getProperty("sort-desc").getBytes(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
```

**After**
```java
            @SneakyThrows
            return new String(System["sort-desc"].bytes, "UTF-8");
```


##### Scenario 9

**Before**
```java
            try {
```

**After**
```java
            @SneakyThrows
```


##### Scenario 10

**Before**
```java
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }
```


##### Scenario 11

**Before**
```java
            example.set("ok");
            String current = example.get();
            System.out.println(example.get().isEmpty());
            example.set(example.get());
            String duplicate = example.get() + example.get();
```

**After**
```java
            example.!! = "ok";
            String current = example.!!;
            System.out.println(example.!!.empty);
            example.!! = example.!!;
            String duplicate = example.!! + example.!!;
```
