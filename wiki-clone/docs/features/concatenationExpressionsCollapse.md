# Concatenation Expressions Collapse (State field: concatenationExpressionsCollapse)

### Concatenation Expressions Collapse
Folds StringBuilder append chains, collection add/remove calls, interpolated strings, and stream collectors into compact expressions.

#### Example: StringBuilderTestData

examples/data/StringBuilderTestData.java:
```java
        StringBuilder sb1 = new StringBuilder("[");
// ...
        sb1.append(arg);
```

folded/StringBuilderTestData-folded.java:
```java
        StringBuilder sb1 = "[";
// ...
        sb1 += arg;
```

Highlights StringBuilderTestData with concatenation expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: InterpolatedStringTestData

examples/data/InterpolatedStringTestData.java:
```java
        System.out.println("Hello, " + args[0]);
        System.out.println("Hello, " + args[0] + "!");
        System.out.println(args[0] + ", hello!");
        System.out.println(args[0] + ", " + args[0]);
// ...
        System.out.println("Hello, " + name);
        System.out.println("Hello, " + name + "!");
        System.out.println(name + ", hello!");
// ...
        System.out.println("Length: " + args.length);
        System.out.println("Sum: " + (2 + 3));
        System.out.println("Upper: " + name.toUpperCase());
```

folded/InterpolatedStringTestData-folded.java:
```java
        System.out.println("Hello, ${args[0]}");
        System.out.println("Hello, ${args[0]}!");
        System.out.println("${args[0]}, hello!");
        System.out.println("${args[0]}, ${args[0]}");
// ...
        System.out.println("Hello, $name");
        System.out.println("Hello, $name!");
        System.out.println("$name, hello!");
// ...
        System.out.println("Length: ${args.length}");
        System.out.println("Sum: ${(2 + 3)}");
        System.out.println("Upper: ${name.toUpperCase()}");
```

Highlights InterpolatedStringTestData with concatenation expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: AppendSetterInterpolatedStringTestData

examples/data/AppendSetterInterpolatedStringTestData.java:
```java
        StringBuilder sb1 = new StringBuilder().append(args[0]);
        sb1.append("Hello, " + args[0]);
        System.out.println(sb1.toString());
// ...
        sb2.append(args[0] + ", hello!");
        System.out.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder("Hello, ").append(args[0]); // Should be StringBuilder sb3 = "Hello, $(args[0)":
// ...
        new AppendSetterInterpolatedStringTestData().setName("Hello, " + args[0]);
        new AppendSetterInterpolatedStringTestData().setName(args[0] + ", hello!");
```

folded/AppendSetterInterpolatedStringTestData-folded.java:
```java
        StringBuilder sb1 = args[0];
        sb1 += "Hello, ${args[0]}";
        System.out.println(sb1);
// ...
        sb2 += "${args[0]}, hello!";
        System.out.println(sb2);
        StringBuilder sb3 = "Hello, " + args[0]; // Should be StringBuilder sb3 = "Hello, $(args[0)":
// ...
        new AppendSetterInterpolatedStringTestData().name = "Hello, ${args[0]}";
        new AppendSetterInterpolatedStringTestData().name = "${args[0]}, hello!";
```

Highlights AppendSetterInterpolatedStringTestData with concatenation expressions collapse.
Removes boilerplate while preserving behavior.

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

Highlights ConcatenationTestData with concatenation expressions collapse.
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

Highlights OptionalTestData with concatenation expressions collapse.
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

Highlights SpreadTestData with concatenation expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `concatenationExpressionsCollapse`
Related features: (none)
---

#### Folding catalogue

##### StringBuilderTestData mappings
| Before | After |
| --- | --- |
| `StringBuilder sb1 = new StringBuilder("[");` | `StringBuilder sb1 = "[";` |
| `sb1.append(arg);` | `sb1 += arg;` |
| `sb1.append(",");` | `sb1 += ",";` |
| `System.out.println(sb1.append("]").toString());` | `System.out.println(sb1 + "]");` |
| `StringBuilder sb2 = new StringBuilder().append("[");` | `StringBuilder sb2 = "[";` |
| `sb2.append(arg);` | `sb2 += arg;` |
| `sb2.append(",");` | `sb2 += ",";` |
| `System.out.println(sb2.append("]").toString());` | `System.out.println(sb2 + "]");` |
| `StringBuilder sb3 = new StringBuilder();` | `StringBuilder sb3 = "";` |
| `sb3.append(",").append(" ");` | `sb3 += "," + " ";` |
| `System.out.println(sb3.toString());` | `System.out.println(sb3);` |

##### InterpolatedStringTestData mappings
| Before | After |
| --- | --- |
| `System.out.println("Hello, " + args[0]);` | `System.out.println("Hello, ${args[0]}");` |
| `System.out.println("Hello, " + args[0] + "!");` | `System.out.println("Hello, ${args[0]}!");` |
| `System.out.println(args[0] + ", hello!");` | `System.out.println("${args[0]}, hello!");` |
| `System.out.println(args[0] + ", " + args[0]);` | `System.out.println("${args[0]}, ${args[0]}");` |
| `System.out.println("Hello, " + name);` | `System.out.println("Hello, $name");` |
| `System.out.println("Hello, " + name + "!");` | `System.out.println("Hello, $name!");` |
| `System.out.println(name + ", hello!");` | `System.out.println("$name, hello!");` |
| `System.out.println(name + ", " + name);` | `System.out.println("$name, $name");` |
| `System.out.println('"' + name + " says hi");` | `System.out.println("\"$name says hi");` |
| `System.out.println("Hi " + name + '"');` | `System.out.println("Hi $name\"");` |
| `System.out.println("Unicode: " + '\\u0041');` | `System.out.println("Unicode: ${'\\u0041'}");` |
| `System.out.println("Next: " + (char)('A' + 1));` | `System.out.println("Next: ${(char)('A' + 1)}");` |
| `System.out.println("Length: " + args.length);` | `System.out.println("Length: ${args.length}");` |
| `System.out.println("Sum: " + (2 + 3));` | `System.out.println("Sum: ${(2 + 3)}");` |
| `System.out.println("Upper: " + name.toUpperCase());` | `System.out.println("Upper: ${name.toUpperCase()}");` |

##### AppendSetterInterpolatedStringTestData mappings
| Before | After |
| --- | --- |
| `StringBuilder sb1 = new StringBuilder().append(args[0]);` | `StringBuilder sb1 = args[0];` |
| `sb1.append("Hello, " + args[0]);` | `sb1 += "Hello, ${args[0]}";` |
| `System.out.println(sb1.toString());` | `System.out.println(sb1);` |
| `StringBuilder sb2 = new StringBuilder("");` | `StringBuilder sb2 = "";` |
| `sb2.append(args[0] + ", hello!");` | `sb2 += "${args[0]}, hello!";` |
| `System.out.println(sb2.toString());` | `System.out.println(sb2);` |
| `StringBuilder sb3 = new StringBuilder("Hello, ").append(args[0]);` | `StringBuilder sb3 = "Hello, " + args[0];` |
| `new AppendSetterInterpolatedStringTestData().setName("Hello, " + args[0]);` | `new AppendSetterInterpolatedStringTestData().name = "Hello, ${args[0]}";` |
| `new AppendSetterInterpolatedStringTestData().setName(args[0] + ", hello!");` | `new AppendSetterInterpolatedStringTestData().name = "${args[0]}, hello!";` |

##### ConcatenationTestData mappings
| Before | After |
| --- | --- |
| `list.add("one");` | `list += "one";` |
| `list.remove("one");` | `list -= "one";` |
| `list.addAll(singleton);` | `list += singleton;` |
| `list.removeAll(singleton);` | `list -= singleton;` |
| `Collections.addAll(list, args);` | `list += args;` |
| `set.add("three");` | `set += "three";` |
| `set.remove("three");` | `set -= "three";` |
| `set.addAll(copyOfSet);` | `set += copyOfSet;` |
| `List<String> streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());` | `List<String> streamToList = args*.toUpperCase().toList();` |
| `streamToList = Arrays.stream(args).map(String::toUpperCase).collect(Collectors.toList());` | `streamToList = args*.toUpperCase().toList();` |
| `streamToList = list.stream().map(String::toUpperCase).collect(Collectors.toList());` | `streamToList = list.stream()*.toUpperCase().toList();` |
| `long count = streamToList.stream().distinct().count();` | `long count = streamToList.distinct().count();` |

##### OptionalTestData concatenation-adjacent mappings
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

##### SpreadTestData stream concatenation mappings
| Before | After |
| --- | --- |
| `String empNames = list.stream().map(Data::getString).collect(Collectors.joining(", "));` | `String empNames = list*.string().collect(Collectors.joining(", "));` |
| `var p1 = data.getDataList().stream().map(Data::getData).toList();` | `var p1 = data.getDataList().stream()*.data().toList();` |
| `var p2 = data.getDataList().stream().map(Data::getData).toList().stream().map(Data::getData).toList();` | `var p2 = data.getDataList().stream()*.data().toList().stream()*.data().toList();` |
| `var stream3 = Stream.of("123", "2313").map(String::length).toList();` | `var stream3 = Stream.of("123", "2313")*.length().toList();` |
| `var a = stream.map(Data::getData).filter(Objects::nonNull).map(Data::getData).flatMap(Data::getDataStream).map(Data::getDataList).flatMap(List::stream).map(Data::getString).map(String::chars)` | `var a = stream*.data().filterNotNull()*.data()**.dataStream()*.dataList()**.stream()*.string()*.chars()` |
| `var p = methodStream(data).toList().stream().min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString)).map(Data::getString).orElse("string1");` | `var p = methodStream(data).toList().min(Comparator.comparing(Data::isOk)).stream().min(Comparator.comparing(Data::getString))?.string ?: "string1";` |
