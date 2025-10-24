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
### Folding catalogue

#### ConcatenationTestData

##### Scenario 1

**Before**
```java
        list.add("one");
        list.remove("one");
```

**After**
```java
        list += "one";
        list -= "one";
```


##### Scenario 2

**Before**
```java
        list.addAll(singleton);
        list.removeAll(singleton);
        Collections.addAll(list, args);
```

**After**
```java
        list += singleton;
        list -= singleton;
        list += args;
```


##### Scenario 3

**Before**
```java
        set.add("three");
        set.remove("three");
```

**After**
```java
        set += "three";
        set -= "three";
```


##### Scenario 4

**Before**
```java
        set.addAll(copyOfSet);
```

**After**
```java
        set += copyOfSet;
```


##### Scenario 5

**Before**
```java
        List<String> streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
```

**After**
```java
        List<String> streamToList = args*.toUpperCase().toList();
```


##### Scenario 6

**Before**
```java
        streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());
```

**After**
```java
        streamToList = args*.toUpperCase().toList();
```


##### Scenario 7

**Before**
```java
        streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
```

**After**
```java
        streamToList = list.stream()*.toUpperCase().toList();
```


##### Scenario 8

**Before**
```java
        long count = streamToList.stream().distinct().count();
```

**After**
```java
        long count = streamToList.distinct().count();
```


#### OptionalTestData

##### Scenario 1

**Before**
```java
            o = opt.get();
```

**After**
```java
            o = opt!!;
```


##### Scenario 2

**Before**
```java
        o = opt.orElseThrow();
```

**After**
```java
        o = opt!!;
```


##### Scenario 3

**Before**
```java
        o = Optional.ofNullable(dataNull);
        o = Optional.of(data);
```

**After**
```java
        o = dataNull;
        o = data!!;
```


##### Scenario 4

**Before**
```java
        o = Optional.ofNullable(dataNull).orElseGet(this::orElseGetReturn);
        o = Optional.ofNullable(dataNull).orElseGet(() -> data.getData().getData());
```

**After**
```java
        o = dataNull ?: this::orElseGetReturn;
        o = dataNull ?: () -> data.getData().getData();
```


##### Scenario 5

**Before**
```java
        o = Optional.of(data).map(Data::getData).orElse(null);
        o = Optional.ofNullable(dataNull).map(OptionalTestData::getOutsideData).get();
```

**After**
```java
        o = data!!.data ?: null;
        o = dataNull.map(OptionalTestData::getOutsideData)!!;
```


##### Scenario 6

**Before**
```java
        o = Optional.of(data).map(Data::getData)
                .map(Data::getData)
```

**After**
```java
        o = data!!.data?.data
```


##### Scenario 7

**Before**
```java
                .map(Function.identity())
                .map(Data::isOk)
```

**After**
```java
                .map(Function.identity())?.ok
```


##### Scenario 8

**Before**
```java
                }).orElse(null);
```

**After**
```java
                }) ?: null;
```


##### Scenario 9

**Before**
```java
        o = Optional.of(data.getData()).map(OptionalTestData::getOutsideData).map(Data::getString).orElse(data.getString());
```

**After**
```java
        o = data.getData()!!.map(OptionalTestData::getOutsideData)?.string ?: data.getString();
```


##### Scenario 10

**Before**
```java
        Optional.of(data).flatMap(this::ofNullable).map(data::getDataMethod).orElseGet(() -> getOutsideData(data));
```

**After**
```java
        data!!.flatMap(this::ofNullable).map(data::getDataMethod) ?: () -> getOutsideData(data);
```


##### Scenario 11

**Before**
```java
        o = Optional.<Data>empty().map(Data::getData).stream().map(Data::getData).filter(Objects::nonNull).findAny().map(Data::getString).get();
```

**After**
```java
        o = Optional.<Data>empty()?.data.stream()*.data().filterNotNull().findAny()?.string!!;
```


##### Scenario 12

**Before**
```java
        o = opt.map(Data::getData).orElse(null);
```

**After**
```java
        o = opt?.data ?: null;
```


##### Scenario 13

**Before**
```java
        o = Optional.<Data>empty().map(Data::getData).stream().map(Data::getData) .filter(Objects::nonNull).findAny().map(Data::getString).get();
```

**After**
```java
        o = Optional.<Data>empty()?.data.stream()*.data().filterNotNull().findAny()?.string!!;
```


##### Scenario 14

**Before**
```java
        Stream.of(data).map(Data::getData).filter(Objects::nonNull);
        Stream.of(data).map(Data::getData) .filter(Objects::nonNull).map(Data::getData).findFirst().orElseThrow();
```

**After**
```java
        Stream.of(data)*.data().filterNotNull();
        Stream.of(data)*.data().filterNotNull()*.data().findFirst()!!;
```


#### SpreadTestData

##### Scenario 1

**Before**
```java
        String empNames = list.stream()
                .map(Data::getString)
```

**After**
```java
        String empNames = list*.string()
```


##### Scenario 2

**Before**
```java
        var p1 = data.getDataList().stream().map(Data::getData).toList();
```

**After**
```java
        var p1 = data.getDataList().stream()*.data().toList();
```


##### Scenario 3

**Before**
```java
                .stream()
                .map(Data::getData)
```

**After**
```java
                .stream()*.data()
```


##### Scenario 4

**Before**
```java
        var stream3 = Stream.of("123", "2313").map(String::length).toList();
```

**After**
```java
        var stream3 = Stream.of("123", "2313")*.length().toList();
```


##### Scenario 5

**Before**
```java
        var a = stream.map(Data::getData).filter(Objects::nonNull)
                .map(Data::getData).flatMap(Data::getDataStream)
                .map(Data::getDataList).flatMap(List::stream)
                .map(Data::getString).map(String::chars)
                .map(it -> it.count()+1)
```

**After**
```java
        var a = stream*.data().filterNotNull()*.data()**.dataStream()*.dataList()**.stream()*.string()*.chars()
                .map(it -> it.count() + 1)
```


##### Scenario 6

**Before**
```java
                    var max = Stream.of(data).map(Data::getString).max(Comparator.comparing(String::length)).map(String::length).stream().map(Object::getClass).findAny().map(Object::hashCode).orElse(1);
```

**After**
```java
                    var max = Stream.of(data)*.string().max(Comparator.comparing(String::length))?.length.stream()*.class().findAny()?.hashCode ?: 1;
```


##### Scenario 7

**Before**
```java
                .flatMap(Function.identity())
                .map(Data::isOk)
```

**After**
```java
                .flatMap(Function.identity())*.ok()
```


##### Scenario 8

**Before**
```java
        var p = methodStream(data).toList().stream().min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString)).map(Data::getString).orElse("string1");
```

**After**
```java
        var p = methodStream(data).toList().min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString))?.string ?: "string1";
```


##### Scenario 9

**Before**
```java
                return Optional.ofNullable(data);
```

**After**
```java
                return data;
```


##### Scenario 10

**Before**
```java
                return Optional.ofNullable(data).stream();
```

**After**
```java
                return data.stream();
```


##### Scenario 11

**Before**
```java
                return Optional.ofNullable(data).stream().toList();
```

**After**
```java
                return data.stream().toList();
```

