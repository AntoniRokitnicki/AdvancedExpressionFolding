# Optional (State field: optional)

### Optional
Displays java.util.Optional flows as Kotlin-style null-safe chains.

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

Highlights ConcatenationTestData with optional.
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

Highlights OptionalTestData with optional.
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

Highlights SpreadTestData with optional.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `optional`
Related features: (none)
---

#### Folding catalogue

##### ConcatenationTestData optional-style operations
| Before | After |
| --- | --- |
| `streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());` | `streamToList = args*.toUpperCase().toList();` |
| `streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());` | `streamToList = list.stream()*.toUpperCase().toList();` |
| `long count = streamToList.stream().distinct().count();` | `long count = streamToList.distinct().count();` |

##### OptionalTestData mappings
| Before | After |
| --- | --- |
| `if (opt.isPresent()) { o = opt.get(); }` | `if (opt.isPresent()) { o = opt!!; }` |
| `o = opt.orElseThrow();` | `o = opt!!;` |
| `o = Optional.ofNullable(dataNull);` | `o = dataNull;` |
| `o = Optional.of(data);` | `o = data!!;` |
| `o = Optional.ofNullable(dataNull).orElseGet(this::orElseGetReturn);` | `o = dataNull ?: this::orElseGetReturn;` |
| `o = Optional.ofNullable(dataNull).orElseGet(() -> data.getData().getData());` | `o = dataNull ?: () -> data.getData().getData();` |
| `o = Optional.of(data).map(Data::getData).orElse(null);` | `o = data!!.data ?: null;` |
| `o = Optional.ofNullable(dataNull).map(OptionalTestData::getOutsideData).get();` | `o = dataNull.map(OptionalTestData::getOutsideData)!!;` |
| `o = Optional.of(data.getData()).map(OptionalTestData::getOutsideData).map(Data::getString).orElse(data.getString());` | `o = data.getData()!!.map(OptionalTestData::getOutsideData)?.string ?: data.getString();` |
| `Optional.of(data).flatMap(this::ofNullable).map(data::getDataMethod).orElseGet(() -> getOutsideData(data));` | `data!!.flatMap(this::ofNullable).map(data::getDataMethod) ?: () -> getOutsideData(data);` |
| `o = Optional.<Data>empty().map(Data::getData).stream().map(Data::getData).filter(Objects::nonNull).findAny().map(Data::getString).get();` | `o = Optional.<Data>empty()?.data.stream()*.data().filterNotNull().findAny()?.string!!;` |
| `o = opt.map(Data::getData).orElse(null);` | `o = opt?.data ?: null;` |
| `Stream.of(data).map(Data::getData).filter(Objects::nonNull);` | `Stream.of(data)*.data().filterNotNull();` |
| `Stream.of(data).map(Data::getData) .filter(Objects::nonNull).map(Data::getData).findFirst().orElseThrow();` | `Stream.of(data)*.data().filterNotNull()*.data().findFirst()!!;` |

##### SpreadTestData optional-style spread
| Before | After |
| --- | --- |
| `String empNames = list.stream().map(Data::getString).collect(Collectors.joining(", "));` | `String empNames = list*.string().collect(Collectors.joining(", "));` |
| `var a = stream.map(Data::getData).filter(Objects::nonNull).map(Data::getData).flatMap(Data::getDataStream).map(Data::getDataList).flatMap(List::stream).map(Data::getString).map(String::chars)` | `var a = stream*.data().filterNotNull()*.data()**.dataStream()*.dataList()**.stream()*.string()*.chars()` |
| `var p = methodStream(data).toList().stream().min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString)).map(Data::getString).orElse("string1");` | `var p = methodStream(data).toList().min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString))?.string ?: "string1";` |
