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
