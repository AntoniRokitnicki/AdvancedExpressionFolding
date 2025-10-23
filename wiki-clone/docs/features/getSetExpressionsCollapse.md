# Get Set Expressions Collapse (State field: getSetExpressionsCollapse)

### Get Set Expressions Collapse
Folds Java getter and setter calls into property-style access.

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

Highlights AppendSetterInterpolatedStringTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: GetterSetterTestData

examples/data/GetterSetterTestData.java:
```java
        d.setParent(d);
        d.setName("Hello");
        d.getParent().setName("Pum!");
        System.out.println(d.getParent().getName());
```

folded/GetterSetterTestData-folded.java:
```java
        d.parent = d;
        d.name = "Hello";
        d.parent.name = "Pum!";
        System.out.println(d.parent.name);
```

Highlights GetterSetterTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: FieldShiftBuilder

examples/data/FieldShiftBuilder.java:
```java
        this.username = username;
        this.active = active;
        this.userIdentifier = userIdentifier;
        this.child = child;
// ...
                .username(record.username());
```

folded/FieldShiftBuilder-folded.java:
```java
        this.username = <<;
        this.active = <<;
        this.userIdentifier = <<;
        this.child = <<;
// ...
                .username(record<<);
```

Highlights FieldShiftBuilder with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: FieldShiftSetters

examples/data/FieldShiftSetters.java:
```java
        this.username = username;
// ...
        this.active = active;
```

folded/FieldShiftSetters-folded.java:
```java
        this.username = <<;
// ...
        this.active = <<;
```

Highlights FieldShiftSetters with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: IfNullSafeData

examples/data/IfNullSafeData.java:
```java
        var threeChains = data != null
                && data.getData1() != null
// ...
                && data.getData1() != null
                && data != null
                && data != null
                && data.getData1() != null
                && data.getData1().isActive();
```

folded/IfNullSafeData-folded.java:
```java
        var threeChains = data?.data1 != null
                && data?.data1 != null
// ...
                && data?.data1?.active == true;
```

Highlights IfNullSafeData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: LogBrackets

examples/data/LogBrackets.java:
```java
        log.debug("Debug message with 1 parameter - Name: %s", name);
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

folded/LogBrackets-folded.java:
```java
        log.debug("Debug message with 1 parameter - Name: $name");
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");
```

Highlights LogBrackets with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: LogFoldingTextBlocksTestData

examples/data/LogFoldingTextBlocksTestData.java:
```java
        log.debug("Debug message with 1 parameter - Name: %s", name);
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

folded/LogFoldingTextBlocksTestData-folded.java:
```java
        log.debug("Debug message with 1 parameter - Name: $name");
// ...
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");
```

Highlights LogFoldingTextBlocksTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: FieldShiftFields

examples/data/FieldShiftFields.java:
```java
        this.username = username;
        this.active = active;
        this.userIdentifier = userIdentifier;
        this.child = child;
        this.userIdentifier = child.userIdentifier;
        this.userIdentifier = child.getUserIdentifier();
// ...
        result.username = source.child.username;
        result.userIdentifier = source.child.child.child.userIdentifier;
        result.active = source.child.active;
        result.list = List.copyOf(source.list);
```

folded/FieldShiftFields-folded.java:
```java
        this.username = <<;
        this.active = <<;
        this.userIdentifier = <<;
        this.child = <<;
        this.userIdentifier = child.<<;
        this.userIdentifier = child.<<;
// ...
        result.username = source.child.<<;
        result.userIdentifier = source.child.child.child.<<;
        result.active = source.child.<<;
        result.list = List.copyOf(source.<<);
```

Highlights FieldShiftFields with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: DestructuringAssignmentArrayTestData

examples/data/DestructuringAssignmentArrayTestData.java:
```java
        Data ignored1 = array[0];
// ...
        Data first = array[0];
```

folded/DestructuringAssignmentArrayTestData-folded.java:
```java
        val ignored1 = array[0];
// ...
        val first, second, third, fourth) = array;
```

Highlights DestructuringAssignmentArrayTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: DestructuringAssignmentArrayWithoutValTestData

examples/data/DestructuringAssignmentArrayWithoutValTestData.java:
```java
        Data first = array[0];
// ...
        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];
```

folded/DestructuringAssignmentArrayWithoutValTestData-folded.java:
```java
        Data (first, second, third, fourth) = array;
// ...
        Data ignored21 = data.array[4];
        Data ignored22 = data.array[5];
```

Highlights DestructuringAssignmentArrayWithoutValTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: DestructuringAssignmentListTestData

examples/data/DestructuringAssignmentListTestData.java:
```java
        Data ignored1 = list.get(0);
// ...
        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);
```

folded/DestructuringAssignmentListTestData-folded.java:
```java
        val ignored1 = list.get(0);
// ...
        val first, second, third, fourth) = list;
```

Highlights DestructuringAssignmentListTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: DestructuringAssignmentListWithoutValTestData

examples/data/DestructuringAssignmentListWithoutValTestData.java:
```java
        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);
// ...
        Data ignored21 = data.getList().get(4);
        Data ignored22 = data.getList().get(5);
```

folded/DestructuringAssignmentListWithoutValTestData-folded.java:
```java
        Data (first, second, third, fourth) = list;
// ...
        Data ignored21 = data.list.get(4);
        Data ignored22 = data.list.get(5);
```

Highlights DestructuringAssignmentListWithoutValTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: NullableAnnotationCheckNotNullTestData

examples/data/NullableAnnotationCheckNotNullTestData.java:
```java
        public void main(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);
// ...
        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
```

folded/NullableAnnotationCheckNotNullTestData-folded.java:
```java
        public void main(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;
// ...
        public void mainMsgs(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;
```

Highlights NullableAnnotationCheckNotNullTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: NullableAnnotationCheckNotNullFieldShiftTestData

examples/data/NullableAnnotationCheckNotNullFieldShiftTestData.java:
```java
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
// ...
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
```

folded/NullableAnnotationCheckNotNullFieldShiftTestData-folded.java:
```java
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
// ...
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
```

Highlights NullableAnnotationCheckNotNullFieldShiftTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

#### Example: DynamicTestData

examples/data/DynamicTestData.java:
```java
    public static void staticMethod(Data data) {
// ...
                .normalMethod(
                        staticMethod(
```

folded/DynamicTestData-folded.java:
```java
    public static void changedStaticMethod(Data data) {
// ...
                .changedNormalMethod(
                        changedStaticMethod(
```

Highlights DynamicTestData with get set expressions collapse.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `getSetExpressionsCollapse`
Related features: (none)
---

#### Folding catalogue

##### AppendSetterInterpolatedStringTestData property mappings
| Before | After |
| --- | --- |
| `new AppendSetterInterpolatedStringTestData().setName("Hello, " + args[0]);` | `new AppendSetterInterpolatedStringTestData().name = "Hello, ${args[0]}";` |
| `new AppendSetterInterpolatedStringTestData().setName(args[0] + ", hello!");` | `new AppendSetterInterpolatedStringTestData().name = "${args[0]}, hello!";` |
##### GetterSetterTestData direct field access
| Before | After |
| --- | --- |
| `d.setParent(d);` | `d.parent = d;` |
| `d.setName("Hello");` | `d.name = "Hello";` |
| `d.getParent().setName("Pum!");` | `d.parent.name = "Pum!";` |
| `System.out.println(d.getParent().getName());` | `System.out.println(d.parent.name);` |
##### IfNullSafeData getter to property conversions
| Before | After |
| --- | --- |
| `data.getData1() != null` | `data?.data1 != null` |
| `data.getData1().getData2() != null` | `data?.data1?.data2 != null` |
| `data.getData1().getData2().getData3() != null` | `data?.data1?.data2?.data3 != null` |
| `data.getData1().getData2().getData3().getData4() != null` | `data?.data1?.data2?.data3?.data4 != null` |
| `data.getData1().isActive()` | `data?.data1?.active == true` |
| `!data.getData1().isActive()` | `data?.data1?.active == false` |
| `data.isActive()` | `data?.active == true` |
| `!data.isActive()` | `data?.active == false` |
| `data.getData2()` | `data?.data2` |
| `data.getData3().getData4()` | `data.data3.data4` |
| `data2.getData1().getData2()` | `data2.data1.data2` |
| `data2.getData1()` | `data2.data1` |
##### LogBrackets nested access mappings
| Before | After |
| --- | --- |
| `log.trace("Trace message - Name: %s, log:%s    $", data.getName(), logPrintfStyle(data));` | `log.trace("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $");` |
| `log.warn("Warning message with three parameters - Name: %s, Age: %s, City: %s", name, data.getData().getName(), city);` | `log.warn("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city");` |
| `log.error("error1 %s", e, e.getMessage(), e);` | `log.error("error1 $e", e, e.message, e);` |
| `log.error("error2 %s", data.getData().getName(), data.getData().getName(), data.getData().getName());` | `log.error("error2 ${data.data.name}", data.data.name, data.data.name);` |
| `Formatter formatter = new Formatter(); formatter.format("User details - Name: %s, Age: %d, City: %s", name, age, city); log.info("Formatter example: {}", formatter.toString());` | `Formatter formatter = new Formatter(); formatter.format("User details - Name: $name, Age: $age, City: $city"); log.info("Formatter example: ${formatter.toString()}");` |
| `System.out.println("Warning message with three parameters - Name: %s, Age: %s, City: %s".formatted(name, data.getData().getName(), city));` | `System.out.println("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city".formatted());` |
| `log.error("Failed to write to log file: %s", e.getMessage());` | `log.error("Failed to write to log file: ${e.message}");` |
##### LogFoldingTextBlocksTestData property mappings
| Before | After |
| --- | --- |
| `log.warn("Warning message with three parameters - Name: %s, Age: %s, City: %s", name, data.getData().getName(), city);` | `log.warn("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city");` |
| `log.error("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s", name, age, city);` | `log.error("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s");` |
| `log.error("error1 %s", e, e.getMessage(), e);` | `log.error("error1 $e", e, e.message, e);` |
| `log.error("error2 %s", data.getData().getName(), data.getData().getName(), data.getData().getName());` | `log.error("error2 ${data.data.name}", data.data.name, data.data.name);` |
| `System.out.println("Trace message - Name: %s, log:%s    $".formatted(data.getName(), logPrintfStyle(data)));` | `System.out.println("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $".formatted());` |
| `log.info("""\n                Data summary:\n                Root: {}\n                Child: {}\n                """, data.getName(), data.getData().getName());` | `log.info("""\n                Data summary:\n                Root: ${data.name}\n                Child: ${data.data.name}\n                """);` |
| `log.trace("""\n                Formatter contents:\n                {}\n                """, formatter);` | `log.trace("""\n                Formatter contents:\n                $formatter\n                """);` |
##### FieldShiftBuilder shorthand mappings
| Before | After |
| --- | --- |
| `this.username = username;` | `this.username = <<;` |
| `this.active = active;` | `this.active = <<;` |
| `this.userIdentifier = userIdentifier;` | `this.userIdentifier = <<;` |
| `this.child = child;` | `this.child = <<;` |
| `.username(record.username())` | `.username(record<<)` |
| `.userIdentifier(source.getUserIdentifier())` | `.userIdentifier(source<<)` |
| `.username(builder.username("a").build().getUsername())` | `.username(builder.username("a").build()<<)` |
| `.child(builder1 .userIdentifier(source.getUsername()) .username(source.getUsername()) .build().getChild())` | `.child(builder1 .userIdentifier(source.username) .username(source<<) .build()<<)` |
| `.active(builder.build().isActive())` | `.active(builder.build()<<)` |
| `.username(source.getUsername() + source.getUserIdentifier())` | `.username(source.username + source.userIdentifier)` |
##### FieldShiftSetters shorthand mappings
| Before | After |
| --- | --- |
| `this.username = username;` | `this.username = <<;` |
| `this.active = active;` | `this.active = <<;` |
| `this.userIdentifier = userIdentifier;` | `this.userIdentifier = <<;` |
| `this.child = child;` | `this.child = <<;` |
| `result.setUsername(source.getUsername());` | `result.username = source<<;` |
| `result.setUserIdentifier(source.getUserIdentifier());` | `result.userIdentifier = source<<;` |
| `result.setActive(source.isActive());` | `result.active = source<<;` |
| `setters2.setActive(setters.isActive());` | `setters2.active = setters<<;` |
| `childBuilder2.setUsername(source.getUsername());` | `childBuilder2.username = source<<;` |
##### FieldShiftFields shorthand mappings
| Before | After |
| --- | --- |
| `this.username = username;` | `this.username = <<;` |
| `this.active = active;` | `this.active = <<;` |
| `this.userIdentifier = userIdentifier;` | `this.userIdentifier = <<;` |
| `this.userIdentifier = child.userIdentifier;` | `this.userIdentifier = child.<<;` |
| `result.username = source.child.username;` | `result.username = source.child.<<;` |
| `result.userIdentifier = source.userIdentifier;` | `result.userIdentifier = source.<<;` |
| `result.username = changer(record.username);` | `result.username = changer(record.<<);` |
| `setters2.userIdentifier = record.userIdentifier();` | `setters2.userIdentifier = record.<<;` |
| `result.child = childBuilder2.child.child.child.child;` | `result.child = childBuilder2.child.child.child.<<;` |
##### DestructuringAssignmentArrayTestData property access
| Before | After |
| --- | --- |
| `data.getArray()[4];` | `data.array[4];` |
| `data.getArray()[5];` | `data.array[5];` |
| `data.getArray()[0];` | `data.array[0];` |
| `data.getData().getArray()[0];` | `data.data.array[0];` |
| `data.getData().getArray()[1];` | `data.data.array[1];` |
##### DestructuringAssignmentListTestData property access
| Before | After |
| --- | --- |
| `data.getList().get(4);` | `data.list.get(4);` |
| `data.getList().get(5);` | `data.list.get(5);` |
| `data.getList().get(0);` | `data.list.get(0);` |
| `data.getData().getList().get(0);` | `data.data.list.get(0);` |
| `data.getData().getList().get(1);` | `data.data.list.get(1);` |
##### NullableAnnotationCheckNotNullTestData property and null assertions
| Before | After |
| --- | --- |
| `Preconditions.checkNotNull(args);` | `args!!;` |
| `Preconditions.checkNotNull(l);` | `l!!;` |
| `Preconditions.checkNotNull(z.getData());` | `z.data!!;` |
| `Preconditions.checkNotNull(o);` | `o!!;` |
| `this.args = Preconditions.checkNotNull(args);` | `this.args = args!!;` |
| `this.l = Preconditions.checkNotNull(l);` | `this.l = l!!;` |
| `this.data = Preconditions.checkNotNull(z.getData());` | `this.data = z.data!!;` |
| `this.o = Preconditions.checkNotNull(o);` | `this.o = o!!;` |
| `this.args = Preconditions.checkNotNull(args, "args are null");` | `this.args = args!!;` |
| `this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");` | `this.data = z.data!!;` |
##### NullableAnnotationCheckNotNullFieldShiftTestData property shorthand
| Before | After |
| --- | --- |
| `this.args = Preconditions.checkNotNull(args);` | `this.args = <<!!;` |
| `this.l = Preconditions.checkNotNull(l);` | `this.l = <<!!;` |
| `this.data = Preconditions.checkNotNull(z.getData());` | `this.data = z.<<!!;` |
| `this.o = Preconditions.checkNotNull(o);` | `this.o = <<!!;` |
| `this.args = Preconditions.checkNotNull(args, "args are null");` | `this.args = <<!!;` |
| `this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");` | `this.data = z.<<!!;` |
##### DynamicTestData property mappings
| Before | After |
| --- | --- |
| `staticMethod(data.getData());` | `changedStaticMethod(data.data);` |
| `new DynamicTestData().normalMethod(new DynamicTestData().staticMethod(data.getData()))` | `new DynamicTestData().changedNormalMethod(new DynamicTestData().changedStaticMethod(data.data))` |
| `return new DynamicTestData().staticMethod(data.getData());` | `return new DynamicTestData().changedStaticMethod(data.data);` |
