# Stream Spread (State field: streamSpread)

### Stream Spread
Displays stream pipelines using Groovy-style spread notation.

#### Example: ConcatenationTestData

examples/data/ConcatenationTestData.java:
```java
        list.add("one");
        list.remove("one");
// ...
        list.addAll(singleton);
        list.removeAll(singleton);
        Collections.addAll(list, args);
```

folded/ConcatenationTestData-folded.java:
```java
        list += "one";
        list -= "one";
// ...
        list += singleton;
        list -= singleton;
        list += args;
```

Highlights ConcatenationTestData with stream spread.
Removes boilerplate while preserving behavior.

#### Example: OptionalTestData

examples/data/OptionalTestData.java:
```java
            o = opt.get();
// ...
        o = opt.orElseThrow();
```

folded/OptionalTestData-folded.java:
```java
            o = opt!!;
// ...
        o = opt!!;
```

Highlights OptionalTestData with stream spread.
Removes boilerplate while preserving behavior.

#### Example: SpreadTestData

examples/data/SpreadTestData.java:
```java
        String empNames = list.stream()
                .map(Data::getString)
// ...
        var p1 = data.getDataList().stream().map(Data::getData).toList();
```

folded/SpreadTestData-folded.java:
```java
        String empNames = list*.string()
// ...
        var p1 = data.getDataList().stream()*.data().toList();
```

Highlights SpreadTestData with stream spread.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `streamSpread`
Related features: (none)
---

#### Folding catalogue

##### ConcatenationTestData spread mappings
| Before | After |
| --- | --- |
| `streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());` | `streamToList = args*.toUpperCase().toList();` |
| `streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());` | `streamToList = list.stream()*.toUpperCase().toList();` |

##### OptionalTestData spread mappings
| Before | After |
| --- | --- |
| `Optional.<Data>empty().map(Data::getData).stream().map(Data::getData)` | `Optional.<Data>empty()?.data.stream()*.data()` |
| `Stream.of(data).map(Data::getData).filter(Objects::nonNull)` | `Stream.of(data)*.data().filterNotNull()` |
| `Stream.of(data).map(Data::getData) .filter(Objects::nonNull).map(Data::getData)` | `Stream.of(data)*.data().filterNotNull()*.data()` |

##### SpreadTestData mappings
| Before | After |
| --- | --- |
| `list.stream().map(Data::getString).collect(Collectors.joining(", "));` | `list*.string().collect(Collectors.joining(", "));` |
| `data.getDataList().stream().map(Data::getData).toList();` | `data.getDataList().stream()*.data().toList();` |
| `Stream.of("123", "2313").map(String::length).toList();` | `Stream.of("123", "2313")*.length().toList();` |
| `stream.map(Data::getData).filter(Objects::nonNull).map(Data::getData).flatMap(Data::getDataStream).map(Data::getDataList).flatMap(List::stream).map(Data::getString).map(String::chars)` | `stream*.data().filterNotNull()*.data()**.dataStream()*.dataList()**.stream()*.string()*.chars()` |
