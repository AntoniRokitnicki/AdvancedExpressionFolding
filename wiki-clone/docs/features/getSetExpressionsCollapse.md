# Get Set Expressions Collapse

## Overview

Folds Java getter and setter calls into property-style access.


## Configuration

- **Toggle ID:** `getSetExpressionsCollapse`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

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


---
### Folding catalogue

#### AppendSetterInterpolatedStringTestData

##### Scenario 1

**Before**
```java
        StringBuilder sb1 = new StringBuilder().append(args[0]);
        sb1.append("Hello, " + args[0]);
        System.out.println(sb1.toString());
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(args[0] + ", hello!");
        System.out.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder("Hello, ").append(args[0]); // Should be StringBuilder sb3 = "Hello, $(args[0)":
```

**After**
```java
        StringBuilder sb1 = args[0];
        sb1 += "Hello, ${args[0]}";
        System.out.println(sb1);
        StringBuilder sb2 = "";
        sb2 += "${args[0]}, hello!";
        System.out.println(sb2);
        StringBuilder sb3 = "Hello, " + args[0]; // Should be StringBuilder sb3 = "Hello, $(args[0)":
```


##### Scenario 2

**Before**
```java
        new AppendSetterInterpolatedStringTestData().setName("Hello, " + args[0]);
        new AppendSetterInterpolatedStringTestData().setName(args[0] + ", hello!");
```

**After**
```java
        new AppendSetterInterpolatedStringTestData().name = "Hello, ${args[0]}";
        new AppendSetterInterpolatedStringTestData().name = "${args[0]}, hello!";
```


#### GetterSetterTestData

##### Scenario 1

**Before**
```java
        d.setParent(d);
        d.setName("Hello");
        d.getParent().setName("Pum!");
        System.out.println(d.getParent().getName());
```

**After**
```java
        d.parent = d;
        d.name = "Hello";
        d.parent.name = "Pum!";
        System.out.println(d.parent.name);
```


#### FieldShiftBuilder

##### Scenario 1

**Before**
```java
        this.username = username;
        this.active = active;
        this.userIdentifier = userIdentifier;
        this.child = child;
```

**After**
```java
        this.username = <<;
        this.active = <<;
        this.userIdentifier = <<;
        this.child = <<;
```


##### Scenario 2

**Before**
```java
                .username(record.username())
                .withUserIdentifier(record.userIdentifier());
```

**After**
```java
                .username(record<<)
                .withUserIdentifier(record<<);
```


##### Scenario 3

**Before**
```java
                .active(source.isActive());
        return FieldShiftBuilder.builder().username(record.userIdentifier()).username(changer(record.username()))
                .username(source.getUsername()).username(builder.username("a").build().getUsername())
                .username(source.getUsername() + "1")
                .active(source.isActive()).userIdentifier(source.getUserIdentifier())
```

**After**
```java
                .active(source<<);
        return FieldShiftBuilder.builder().username(record.userIdentifier).username(changer(record<<))
                .username(source<<).username(builder.username("a").build()<<)
                .username(source.username + "1")
                .active(source<<).userIdentifier(source<<)
```


##### Scenario 4

**Before**
```java
                                .userIdentifier(source.getUsername())
                                .username(source.getUsername())
                                .build().getChild())
                        .active(builder.build().isActive())
                        .username(builder.build().getUsername())
                        .userIdentifier(record.userIdentifier())
```

**After**
```java
                                .userIdentifier(source.username)
                                .username(source<<)
                                .build()<<)
                        .active(builder.build()<<)
                        .username(builder.build()<<)
                        .userIdentifier(record<<)
```


##### Scenario 5

**Before**
```java
                        .username(source.getUsername())
```

**After**
```java
                        .username(source<<)
```


##### Scenario 6

**Before**
```java
                .username(source.getUsername())
                .userIdentifier(source.getUserIdentifier())
                .withUserIdentifier(source.getUserIdentifier())
```

**After**
```java
                .username(source<<)
                .userIdentifier(source<<)
                .withUserIdentifier(source<<)
```


##### Scenario 7

**Before**
```java
                .username(source.username())
                .active(source.active())
                .userIdentifier(source.userIdentifier())
                .withUserIdentifier(source.userIdentifier())
```

**After**
```java
                .username(source<<)
                .active(source<<)
                .userIdentifier(source<<)
                .withUserIdentifier(source<<)
```


##### Scenario 8

**Before**
```java
            this.username = username;
            this.active = active;
            this.userIdentifier = userIdentifier;
```

**After**
```java
            this.username = <<;
            this.active = <<;
            this.userIdentifier = <<;
```


##### Scenario 9

**Before**
```java
                this.username = username;
```

**After**
```java
                this.username = <<;
```


##### Scenario 10

**Before**
```java
                this.active = active;
```

**After**
```java
                this.active = <<;
```


##### Scenario 11

**Before**
```java
                this.userIdentifier = userIdentifier;
```

**After**
```java
                this.userIdentifier = <<;
```


##### Scenario 12

**Before**
```java
            this.username = username;
```

**After**
```java
            this.username = <<;
```


##### Scenario 13

**Before**
```java
            this.active = active;
```

**After**
```java
            this.active = <<;
```


##### Scenario 14

**Before**
```java
            this.userIdentifier = userIdentifier;
```

**After**
```java
            this.userIdentifier = <<;
```


##### Scenario 15

**Before**
```java
            this.child = child;
```

**After**
```java
            this.child = <<;
```


#### FieldShiftSetters

##### Scenario 1

**Before**
```java
        this.username = username;
```

**After**
```java
        this.username = <<;
```


##### Scenario 2

**Before**
```java
        this.active = active;
```

**After**
```java
        this.active = <<;
```


##### Scenario 3

**Before**
```java
        this.userIdentifier = userIdentifier;
```

**After**
```java
        this.userIdentifier = <<;
```


##### Scenario 4

**Before**
```java
        this.child = child;
```

**After**
```java
        this.child = <<;
```


##### Scenario 5

**Before**
```java
        this.list = list;
```

**After**
```java
        this.list = <<;
```


##### Scenario 6

**Before**
```java
        result.setUsername(source.getChild().getUsername());
        result.setUserIdentifier(source.getChild().getChild().getChild().getUserIdentifier());
        result.setActive(source.getChild().isActive());
```

**After**
```java
        result.username = source.child<<;
        result.userIdentifier = source.child.child.child<<;
        result.active = source.child<<;
```


##### Scenario 7

**Before**
```java
        result.setUsername(source.getUsername());
        result.setUserIdentifier(source.getUserIdentifier());
        result.setActive(source.isActive());
        result.setList(List.copyOf(source.getList()));
        result.withUsername(source.getUsername());
        result.withActive(source.isActive());
        result.withUserIdentifier(source.getUserIdentifier());
```

**After**
```java
        result.username = source<<;
        result.userIdentifier = source<<;
        result.active = source<<;
        result.list = List.copyOf(source<<);
        result.withUsername(source<<);
        result.withActive(source<<);
        result.withUserIdentifier(source<<);
```


##### Scenario 8

**Before**
```java
        result.setUsername(source.username());
        result.setActive(source.active());
        result.setUserIdentifier(source.userIdentifier());
        result.withUsername(source.username());
        result.withActive(source.active());
        result.withUserIdentifier(source.userIdentifier());
```

**After**
```java
        result.username = source<<;
        result.active = source<<;
        result.userIdentifier = source<<;
        result.withUsername(source<<);
        result.withActive(source<<);
        result.withUserIdentifier(source<<);
```


##### Scenario 9

**Before**
```java
        var1.setUsername(record.username());
```

**After**
```java
        var1.username = record<<;
```


##### Scenario 10

**Before**
```java
        var2.setActive(source.isActive());
```

**After**
```java
        var2.active = source<<;
```


##### Scenario 11

**Before**
```java
        result.setUsername(record.userIdentifier());
        result.setUsername(changer(record.username()));
        result.setUsername(source.getUsername());
        result.setUsername(var1.getChild().getUsername());
        result.setUsername(source.getUsername() + "1");
        result.setUsername(source.getUsername() + source.getUserIdentifier());
        result.setActive(source.isActive());
        result.setUserIdentifier(source.getUserIdentifier());
```

**After**
```java
        result.username = record.userIdentifier;
        result.username = changer(record<<);
        result.username = source<<;
        result.username = var1.child<<;
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source<<;
        result.userIdentifier = source<<;
```


##### Scenario 12

**Before**
```java
        setters2.setChild(var1.getChild());
        var1.setUserIdentifier(source.getUsername());
        var1.setUsername(source.getUsername());
        setters2.getChild();
        setters2.setActive(setters.isActive());
        setters2.setUsername(setters.getUsername());
        setters2.setUserIdentifier(record.userIdentifier());
        result.setChild(setters2);
```

**After**
```java
        setters2.child = var1<<;
        var1.userIdentifier = source.username;
        var1.username = source<<;
        setters2.child;
        setters2.active = setters<<;
        setters2.username = setters<<;
        setters2.userIdentifier = record<<;
        result.child = setters2;
```


##### Scenario 13

**Before**
```java
        childBuilder2.setUsername(source.getUsername());
        result.setChild(childBuilder2.getChild().getChild().getChild().getChild());
```

**After**
```java
        childBuilder2.username = source<<;
        result.child = childBuilder2.child.child.child<<;
```


##### Scenario 14

**Before**
```java
            this.username = username;
```

**After**
```java
            this.username = <<;
```


##### Scenario 15

**Before**
```java
            this.active = active;
```

**After**
```java
            this.active = <<;
```


##### Scenario 16

**Before**
```java
            this.userIdentifier = userIdentifier;
```

**After**
```java
            this.userIdentifier = <<;
```


#### IfNullSafeData

##### Scenario 1

**Before**
```java
        var threeChains = data != null
                && data.getData1() != null
```

**After**
```java
        var threeChains = data?.data1 != null
                && data?.data1 != null
```


##### Scenario 2

**Before**
```java
                && data.getData1() != null
                && data != null
                && data != null
                && data.getData1() != null
                && data.getData1().isActive();
```

**After**
```java
                && data?.data1?.active == true;
```


##### Scenario 3

**Before**
```java
        var notChain = data != null && !data.getData1().isActive();
        var chain = data != null && data.getData1() != null && data.getData1().getData4() != null;
```

**After**
```java
        var notChain = data != null && !data.data1.active;
        var chain = data?.data1?.data4 != null;
```


##### Scenario 4

**Before**
```java
        if (data != null && data.getData1() != null &&
                data.getData1().getData2() != null && data.getData1().
                getData2()
                .getData3() != null) {
```

**After**
```java
        if (data?.data1?.data2?.data3 != null) {
```


##### Scenario 5

**Before**
```java
        if (data != null && data.getData1() != null) {
```

**After**
```java
        if (data?.data1 != null) {
```


##### Scenario 6

**Before**
```java
        if (data != null && data.isActive()) {
```

**After**
```java
        if (data?.active == true) {
```


##### Scenario 7

**Before**
```java
        if (data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null
```

**After**
```java
        if (data?.data1?.data2?.data3?.data4 != null
```


##### Scenario 8

**Before**
```java
                && data != null
                && data.getData1() != null
                && !data.getData1().isActive()
```

**After**
```java
                && data?.data1?.active == false
```


##### Scenario 9

**Before**
```java
        boolean has = data != null
                && data.getData1() != null
                && data.getData1().getData2() != null
                && data.getData1().getData2().getData3() != null
                && data.getData1().getData2().getData3().getData4() != null;
        var active = data != null
                && data.getData1() != null
                && data.getData1().isActive();
        var inactive = data != null && !data.isActive();
        while (data != null && data.getData2() != null && !data.getData2().isActive()) {
            active = !data.getData1().isActive();
```

**After**
```java
        boolean has = data?.data1?.data2?.data3?.data4 != null;
        var active = data?.data1?.active == true;
        var inactive = data?.active == false;
        while (data?.data2?.active == false) {
            active = !data.data1.active;
```


##### Scenario 10

**Before**
```java
        if ((data != null && data.getData6() != null &&
                data.getData6().isActive())) {
```

**After**
```java
        if ((data?.data6?.active == true)) {
```


##### Scenario 11

**Before**
```java
        if ((data != null && data.getData6() != null &&
                !data.getData6().isActive())) {
```

**After**
```java
        if ((data?.data6?.active == false)) {
```


##### Scenario 12

**Before**
```java
                || data != null
                && data.getData1() != null
                && data.getData1().isActive())
```

**After**
```java
                || data?.data1?.active == true)
```


##### Scenario 13

**Before**
```java
                && (data != null
                && data.getData2() != null
                && data.getData2().isActive() ||
```

**After**
```java
                && (data?.data2?.active == true ||
```


##### Scenario 14

**Before**
```java
                data != null
                        && data.getData3() != null
                        && data.getData3().isActive()
                        && data.getData3().getData4() != null
                        && data.getData3().getData4().isActive()) ||
```

**After**
```java
                data?.data3?.active == true
                        && data.data3.data4?.active == true) ||
```


##### Scenario 15

**Before**
```java
                (data != null
                        && data.getData5() != null
                        && data.getData5().isActive()) &&
```

**After**
```java
                (data?.data5?.active == true) &&
```


##### Scenario 16

**Before**
```java
                                data != null &&
                                data.getData6() != null &&
                                data.getData6().isActive())) {
```

**After**
```java
                                data?.data6?.active == true)) {
```


##### Scenario 17

**Before**
```java
        if (data.getData1().getData2().getData3() != null &&
                data.getData1().getData2().getData3().isActive()) {
```

**After**
```java
        if (data.data1.data2.data3?.active == true) {
```


##### Scenario 18

**Before**
```java
        if (data2.getData1().getData2() != null &&
                data2.getData1().getData2().isActive()) {
```

**After**
```java
        if (data2.data1.data2?.active == true) {
```


##### Scenario 19

**Before**
```java
        if (data2.getData1() != null &&
                data2.getData1().isActive()) {
```

**After**
```java
        if (data2.data1?.active == true) {
```


#### LogBrackets

##### Scenario 1

**Before**
```java
        log.debug("Debug message with 1 parameter - Name: %s", name);
```

**After**
```java
        log.debug("Debug message with 1 parameter - Name: $name");
```


##### Scenario 2

**Before**
```java
        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

**After**
```java
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");
        log.info(MY_MARKER, "Marker missing parameters {} {}");
```


##### Scenario 3

**Before**
```java
        log.info("Info message with 2 parameters - Name: %s, Age: %d    ", name, age);
        log.info("Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

**After**
```java
        log.info("Info message with 2 parameters - Name: $name, Age: $age    ");
        log.info("Info message with 2 parameters - Name: $name, Age: $age");
```


##### Scenario 4

**Before**
```java
        log.trace("Trace message - Name: %s, log:%s    $", data.getName(), logPrintfStyle(data));
        log.warn("Warning message with three parameters - Name: %s, Age: %s, City: %s", name, data.getData().getName(), city);
```

**After**
```java
        log.trace("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $");
        log.warn("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city");
```


##### Scenario 5

**Before**
```java
        log.error("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s", name, age, city);
        log.error("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s", name, age);
        log.error("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s", name);
```

**After**
```java
        log.error("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s");
        log.error("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s");
        log.error("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s");
```


##### Scenario 6

**Before**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    name,
```

**After**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: $name, 2: ${data.data.name}, 3: $city");
```


##### Scenario 7

**Before**
```java
                    data.getData().getName(),

                    city
            );

            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    data.getData().getName(),
                    name,
                    data.getData().getName()
            );
```

**After**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: ${data.data.name}, 2: $name, 3: ${data.data.name}");
```


##### Scenario 8

**Before**
```java
            log.error("error1 %s", e, e.getMessage(), e);
            log.error("error2 %s", data.getData().getName(), data.getData().getName(), data.getData().getName());
```

**After**
```java
            log.error("error1 $e", e.message, e);
            log.error("error2 ${data.data.name}", data.data.name, data.data.name);
```


##### Scenario 9

**Before**
```java
        String formatted = String.format("Hello, %s! Your age is %d", name, age);
        log.info("String.format example: {}", formatted);
```

**After**
```java
        String formatted = String.format("Hello, $name! Your age is $age");
        log.info("String.format example: $formatted");
```


##### Scenario 10

**Before**
```java
        System.out.printf("User: %s, Age: %d, City: %s%n", name, age, city);
```

**After**
```java
        System.out.printf("User: $name, Age: $age, City: $city%n");
```


##### Scenario 11

**Before**
```java
        System.err.printf("Error scenario: User %s not found in %s and ignore new-line break%n", name, city, "ignored");
```

**After**
```java
        System.err.printf("Error scenario: User $name not found in $city and ignore new-line break%n", "ignored");
```


##### Scenario 12

**Before**
```java
        formatter.format("User details - Name: %s, Age: %d, City: %s", name, age, city);
        log.info("Formatter example: {}", formatter.toString());
```

**After**
```java
        formatter.format("User details - Name: $name, Age: $age, City: $city");
        log.info("Formatter example: ${formatter.toString()}");
```


##### Scenario 13

**Before**
```java
            writer.printf("Log entry: User %s, Age %d, accessed from %s", name, age, city);
```

**After**
```java
            writer.printf("Log entry: User $name, Age $age, accessed from $city");
```


##### Scenario 14

**Before**
```java
            log.error("Failed to write to log file: %s", e.getMessage());
```

**After**
```java
            log.error("Failed to write to log file: ${e.message}");
```


##### Scenario 15

**Before**
```java
        System.out.println("Debug message with 1 parameter - Name: %s".formatted(name));
        System.out.println("Trace message - Name: %s, log:%s    $".formatted(data.getName(), logPrintfStyle(data)));
        System.out.println("Warning message with three parameters - Name: %s, Age: %s, City: %s".formatted(name, data.getData().getName(), city));
```

**After**
```java
        System.out.println("Debug message with 1 parameter - Name: $name".formatted());
        System.out.println("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $".formatted());
        System.out.println("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city".formatted());
```


##### Scenario 16

**Before**
```java
        System.out.println("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s".formatted(name, age, city));
        System.out.println("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s".formatted(name, age));
        System.out.println("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s".formatted(name));
```

**After**
```java
        System.out.println("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s".formatted());
        System.out.println("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s".formatted());
        System.out.println("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s".formatted());
```


##### Scenario 17

**Before**
```java
        System.out.println("Additional 1 parameter - Name: %s".formatted(name, data));
        System.out.println("Additional 2 parameters - Name: %s".formatted(name, data, logPrintfStyle(data)));
```

**After**
```java
        System.out.println("Additional 1 parameter - Name: $name".formatted( data));
        System.out.println("Additional 2 parameters - Name: $name".formatted( data, logPrintfStyle(data)));
```


#### LogFoldingTextBlocksTestData

##### Scenario 1

**Before**
```java
        log.debug("Debug message with 1 parameter - Name: %s", name);
```

**After**
```java
        log.debug("Debug message with 1 parameter - Name: $name");
```


##### Scenario 2

**Before**
```java
        log.info(MY_MARKER, "Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

**After**
```java
        log.info(MY_MARKER, "Info message with 2 parameters - Name: $name, Age: $age");
```


##### Scenario 3

**Before**
```java
        log.info("Info message with 2 parameters - Name: %s, Age: %d    ", name, age);
        log.info("Info message with 2 parameters - Name: %s, Age: %d", name, age);
```

**After**
```java
        log.info("Info message with 2 parameters - Name: $name, Age: $age    ");
        log.info("Info message with 2 parameters - Name: $name, Age: $age");
```


##### Scenario 4

**Before**
```java
        log.trace("Trace message - Name: %s, log:%s    $", data.getName(), logPrintfStyle(data));
        log.warn("Warning message with three parameters - Name: %s, Age: %s, City: %s", name, data.getData().getName(), city);
```

**After**
```java
        log.trace("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $");
        log.warn("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city");
```


##### Scenario 5

**Before**
```java
        log.error("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s", name, age, city);
        log.error("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s", name, age);
        log.error("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s", name);
```

**After**
```java
        log.error("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s");
        log.error("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s");
        log.error("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s");
```


##### Scenario 6

**Before**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    name,
```

**After**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: $name, 2: ${data.data.name}, 3: $city");
```


##### Scenario 7

**Before**
```java
                    data.getData().getName(),

                    city
            );

            log.warn("Warning message with 3 parameters and formatting - 1: %s, 2: %s, 3: %s",
                    data.getData().getName(),
                    name,
                    data.getData().getName()
            );
```

**After**
```java
            log.warn("Warning message with 3 parameters and formatting - 1: ${data.data.name}, 2: $name, 3: ${data.data.name}");
```


##### Scenario 8

**Before**
```java
            log.error("error1 %s", e, e.getMessage(), e);
            log.error("error2 %s", data.getData().getName(), data.getData().getName(), data.getData().getName());
```

**After**
```java
            log.error("error1 $e", e.message, e);
            log.error("error2 ${data.data.name}", data.data.name, data.data.name);
```


##### Scenario 9

**Before**
```java
        String formatted = String.format("Hello, %s! Your age is %d", name, age);
        log.info("String.format example: {}", formatted);
```

**After**
```java
        String formatted = String.format("Hello, $name! Your age is $age");
        log.info("String.format example: $formatted");
```


##### Scenario 10

**Before**
```java
        System.out.printf("User: %s, Age: %d, City: %s%n", name, age, city);
```

**After**
```java
        System.out.printf("User: $name, Age: $age, City: $city%n");
```


##### Scenario 11

**Before**
```java
        System.err.printf("Error scenario: User %s not found in %s and ignore new-line break%n", name, city, "ignored");
```

**After**
```java
        System.err.printf("Error scenario: User $name not found in $city and ignore new-line break%n", "ignored");
```


##### Scenario 12

**Before**
```java
        formatter.format("User details - Name: %s, Age: %d, City: %s", name, age, city);
        log.info("Formatter example: {}", formatter.toString());
```

**After**
```java
        formatter.format("User details - Name: $name, Age: $age, City: $city");
        log.info("Formatter example: ${formatter.toString()}");
```


##### Scenario 13

**Before**
```java
            writer.printf("Log entry: User %s, Age %d, accessed from %s", name, age, city);
```

**After**
```java
            writer.printf("Log entry: User $name, Age $age, accessed from $city");
```


##### Scenario 14

**Before**
```java
            log.error("Failed to write to log file: %s", e.getMessage());
```

**After**
```java
            log.error("Failed to write to log file: ${e.message}");
```


##### Scenario 15

**Before**
```java
        System.out.println("Debug message with 1 parameter - Name: %s".formatted(name));
        System.out.println("Trace message - Name: %s, log:%s    $".formatted(data.getName(), logPrintfStyle(data)));
        System.out.println("Warning message with three parameters - Name: %s, Age: %s, City: %s".formatted(name, data.getData().getName(), city));
```

**After**
```java
        System.out.println("Debug message with 1 parameter - Name: $name".formatted());
        System.out.println("Trace message - Name: ${data.name}, log:${logPrintfStyle(data)}    $".formatted());
        System.out.println("Warning message with three parameters - Name: $name, Age: ${data.data.name}, City: $city".formatted());
```


##### Scenario 16

**Before**
```java
        System.out.println("Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s".formatted(name, age, city));
        System.out.println("Missing 2 parameters - 1: %s, 2: %d, empty: %s, empty: %s".formatted(name, age));
        System.out.println("Missing 3 parameters - 1: %s, empty: %s, empty: %s, empty: %s".formatted(name));
```

**After**
```java
        System.out.println("Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s".formatted());
        System.out.println("Missing 2 parameters - 1: $name, 2: $age, empty: %s, empty: %s".formatted());
        System.out.println("Missing 3 parameters - 1: $name, empty: %s, empty: %s, empty: %s".formatted());
```


##### Scenario 17

**Before**
```java
        System.out.println("Additional 1 parameter - Name: %s".formatted(name, data));
        System.out.println("Additional 2 parameters - Name: %s".formatted(name, data, logPrintfStyle(data)));
```

**After**
```java
        System.out.println("Additional 1 parameter - Name: $name".formatted( data));
        System.out.println("Additional 2 parameters - Name: $name".formatted( data, logPrintfStyle(data)));
```


##### Scenario 18

**Before**
```java
                Missing 1 parameter - 1: %s, 2: %d, 3: %s, empty: %s
                """, name, age, city);
```

**After**
```java
                Missing 1 parameter - 1: $name, 2: $age, 3: $city, empty: %s
                """);
```


##### Scenario 19

**Before**
```java
                Root: {}
                Child: {}
                """, data.getName(), data.getData().getName());
```

**After**
```java
                Root: ${data.name}
                Child: ${data.data.name}
                """);
```


##### Scenario 20

**Before**
```java
                Name: {}
                Age: {}
                City: {}
                """, name, age, city);
```

**After**
```java
                Name: $name
                Age: $age
                City: $city
                """);
```


##### Scenario 21

**Before**
```java
                Parent: {}
                Child: {}
                """, data.getName(), data.getData().getName());
```

**After**
```java
                Parent: ${data.name}
                Child: ${data.data.name}
                """);
```


##### Scenario 22

**Before**
```java
                {}
                """, formatter);
```

**After**
```java
                $formatter
                """);
```


#### FieldShiftFields

##### Scenario 1

**Before**
```java
        this.username = username;
        this.active = active;
        this.userIdentifier = userIdentifier;
        this.child = child;
        this.userIdentifier = child.userIdentifier;
        this.userIdentifier = child.getUserIdentifier();
```

**After**
```java
        this.username = <<;
        this.active = <<;
        this.userIdentifier = <<;
        this.child = <<;
        this.userIdentifier = child.<<;
        this.userIdentifier = child.<<;
```


##### Scenario 2

**Before**
```java
        result.username = source.child.username;
        result.userIdentifier = source.child.child.child.userIdentifier;
        result.active = source.child.active;
        result.list = List.copyOf(source.list);
```

**After**
```java
        result.username = source.child.<<;
        result.userIdentifier = source.child.child.child.<<;
        result.active = source.child.<<;
        result.list = List.copyOf(source.<<);
```


##### Scenario 3

**Before**
```java
        result.username = source.username;
        result.userIdentifier = source.userIdentifier;
        result.active = source.active;
```

**After**
```java
        result.username = source.<<;
        result.userIdentifier = source.<<;
        result.active = source.<<;
```


##### Scenario 4

**Before**
```java
        result.username = source.username;
        result.active = source.active;
        result.userIdentifier = source.userIdentifier;
```

**After**
```java
        result.username = source.<<;
        result.active = source.<<;
        result.userIdentifier = source.<<;
```


##### Scenario 5

**Before**
```java
        result.username = source.username();
        result.active = source.active();
        result.userIdentifier = source.userIdentifier();
```

**After**
```java
        result.username = source.<<;
        result.active = source.<<;
        result.userIdentifier = source.<<;
```


##### Scenario 6

**Before**
```java
        var1.username = record.username;
```

**After**
```java
        var1.username = record.<<;
```


##### Scenario 7

**Before**
```java
        var2.active = source.active;
```

**After**
```java
        var2.active = source.<<;
```


##### Scenario 8

**Before**
```java
        result.username = changer(record.username);
        result.username = source.username;
        result.username = var1.child.username;
```

**After**
```java
        result.username = changer(record.<<);
        result.username = source.<<;
        result.username = var1.child.<<;
```


##### Scenario 9

**Before**
```java
        result.active = source.active;
        result.userIdentifier = source.userIdentifier;
```

**After**
```java
        result.active = source.<<;
        result.userIdentifier = source.<<;
```


##### Scenario 10

**Before**
```java
        setters2.child = var1.child;
```

**After**
```java
        setters2.child = var1.<<;
```


##### Scenario 11

**Before**
```java
        var1.username = source.username;
        setters2.active = fields.active;
        setters2.username = fields.username;
        setters2.userIdentifier = record.userIdentifier();
```

**After**
```java
        var1.username = source.<<;
        setters2.active = fields.<<;
        setters2.username = fields.<<;
        setters2.userIdentifier = record.<<;
```


##### Scenario 12

**Before**
```java
        childBuilder2.username = source.username;
        result.child = childBuilder2.child.child.child.child;
```

**After**
```java
        childBuilder2.username = source.<<;
        result.child = childBuilder2.child.child.child.<<;
```


##### Scenario 13

**Before**
```java
        var1.username = record.username();
```

**After**
```java
        var1.username = record.<<;
```


##### Scenario 14

**Before**
```java
        var2.active = source.isActive();
```

**After**
```java
        var2.active = source.<<;
```


##### Scenario 15

**Before**
```java
        result.username = changer(record.username());
        result.username = source.getUsername();
        result.username = var1.getChild().getUsername();
        result.username = source.getUsername() + "1";
        result.username = source.getUsername() + source.getUserIdentifier();
        result.active = source.isActive();
        result.userIdentifier = source.getUserIdentifier();
```

**After**
```java
        result.username = changer(record.<<);
        result.username = source.<<;
        result.username = var1.child.<<;
        result.username = source.username + "1";
        result.username = source.username + source.userIdentifier;
        result.active = source.<<;
        result.userIdentifier = source.<<;
```


##### Scenario 16

**Before**
```java
        setters2.child = var1.getChild();
        var1.userIdentifier = (source.getUsername());
        var1.username = source.getUsername();
        setters2.getChild();
        setters2.active = getters.isActive();
        setters2.username = getters.getUsername();
        setters2.userIdentifier = record.userIdentifier();
        result.child = setters2.getChild();
```

**After**
```java
        setters2.child = var1.<<;
        var1.userIdentifier = (source.username);
        var1.username = source.<<;
        setters2.child;
        setters2.active = getters.<<;
        setters2.username = getters.<<;
        setters2.userIdentifier = record.<<;
        result.child = setters2.<<;
```


##### Scenario 17

**Before**
```java
        childBuilder2.username = source.getUsername();
        result.child = childBuilder2.getChild().getChild().getChild().getChild();
```

**After**
```java
        childBuilder2.username = source.<<;
        result.child = childBuilder2.child.child.child.<<;
```


#### DestructuringAssignmentArrayTestData

##### Scenario 1

**Before**
```java
        Data ignored1 = array[0];
```

**After**
```java
        val ignored1 = array[0];
```


##### Scenario 2

**Before**
```java
        Data first = array[0];
```

**After**
```java
        val first, second, third, fourth) = array;
```


##### Scenario 3

**Before**
```java
        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];
```

**After**
```java
        val ignored21 = data.array[4];
        val ignored22 = data.array[5];
```


##### Scenario 4

**Before**
```java
        Data ignored21 = data.getArray()[4];
        Data ignored22 = data.getArray()[5];

        Data getter1 = data.getArray()[0];
        Data getter2 = data.getArray()[1];
        Data getter3 = data.getArray()[2];
```

**After**
```java
        var getter1, getter2, getter3) = data.array;
```


##### Scenario 5

**Before**
```java
        Data deepGetter1 = data.getData().getArray()[0];
        Data deepGetter2 = data.getData().getArray()[1];
```

**After**
```java
        val deepGetter1, deepGetter2) = data.data.array;
```


##### Scenario 6

**Before**
```java
        Data wrongParent1 = data.getArray()[0];
        Data wrongParent2 = data.getData().getArray()[1];
```

**After**
```java
        val wrongParent1 = data.array[0];
        val wrongParent2 = data.data.array[1];
```


#### DestructuringAssignmentArrayWithoutValTestData

##### Scenario 1

**Before**
```java
        Data first = array[0];
```

**After**
```java
        Data (first, second, third, fourth) = array;
```


##### Scenario 2

**Before**
```java
        Data second = array[1];
        Data third = array[2];
        Data fourth = array[3];
```

**After**
```java
        Data ignored21 = data.array[4];
        Data ignored22 = data.array[5];
```


##### Scenario 3

**Before**
```java
        Data ignored21 = data.getArray()[4];
        Data ignored22 = data.getArray()[5];

        Data getter1 = data.getArray()[0];
        Data getter2 = data.getArray()[1];
```

**After**
```java
        Data (getter1, getter2) = data.array;
```


##### Scenario 4

**Before**
```java
        Data deepGetter1 = data.getData().getArray()[0];
        Data deepGetter2 = data.getData().getArray()[1];
```

**After**
```java
        Data (deepGetter1, deepGetter2) = data.data.array;
```


##### Scenario 5

**Before**
```java
        Data wrongParent1 = data.getArray()[0];
        Data wrongParent2 = data.getData().getArray()[1];
```

**After**
```java
        Data wrongParent1 = data.array[0];
        Data wrongParent2 = data.data.array[1];
```


#### DestructuringAssignmentListTestData

##### Scenario 1

**Before**
```java
        Data ignored1 = list.get(0);
```

**After**
```java
        val ignored1 = list.get(0);
```


##### Scenario 2

**Before**
```java
        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);
```

**After**
```java
        val first, second, third, fourth) = list;
```


##### Scenario 3

**Before**
```java
        Data ignored21 = data.getList().get(4);
        Data ignored22 = data.getList().get(5);
```

**After**
```java
        val ignored21 = data.list.get(4);
        val ignored22 = data.list.get(5);
```


##### Scenario 4

**Before**
```java
        Data getter1 = data.getList().get(0);
        Data getter2 = data.getList().get(1);
        Data getter3 = data.getList().get(2);
```

**After**
```java
        var getter1, getter2, getter3) = data.list;
```


##### Scenario 5

**Before**
```java
        Data deepGetter1 = data.getData().getList().get(0);
        Data deepGetter2 = data.getData().getList().get(1);
```

**After**
```java
        val deepGetter1, deepGetter2) = data.data.list;
```


##### Scenario 6

**Before**
```java
        Data wrongParent1 = data.getList().get(0);
        Data wrongParent2 = data.getData().getList().get(1);
```

**After**
```java
        val wrongParent1 = data.list.get(0);
        val wrongParent2 = data.data.list.get(1);
```


#### DestructuringAssignmentListWithoutValTestData

##### Scenario 1

**Before**
```java
        Data first = list.get(0);
        Data second = list.get(1);
        Data third = list.get(2);
        Data fourth = list.get(3);
```

**After**
```java
        Data (first, second, third, fourth) = list;
```


##### Scenario 2

**Before**
```java
        Data ignored21 = data.getList().get(4);
        Data ignored22 = data.getList().get(5);
```

**After**
```java
        Data ignored21 = data.list.get(4);
        Data ignored22 = data.list.get(5);
```


##### Scenario 3

**Before**
```java
        Data getter1 = data.getList().get(0);
        Data getter2 = data.getList().get(1);
        Data getter3 = data.getList().get(2);
```

**After**
```java
        Data (getter1, getter2, getter3) = data.list;
```


##### Scenario 4

**Before**
```java
        Data deepGetter1 = data.getData().getList().get(0);
        Data deepGetter2 = data.getData().getList().get(1);
```

**After**
```java
        Data (deepGetter1, deepGetter2) = data.data.list;
```


##### Scenario 5

**Before**
```java
        Data wrongParent1 = data.getList().get(0);
        Data wrongParent2 = data.getData().getList().get(1);
```

**After**
```java
        Data wrongParent1 = data.list.get(0);
        Data wrongParent2 = data.data.list.get(1);
```


#### NullableAnnotationCheckNotNullTestData

##### Scenario 1

**Before**
```java
        public void main(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);
```

**After**
```java
        public void main(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;
```


##### Scenario 2

**Before**
```java
        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
        public void mainMsgs(String!!! args, Object o, Long!!! l, Preconditions z) {args!!;l!!;
            z.data!!;
            o!!;
```


##### Scenario 3

**Before**
```java
        public void mainConflictAnnotations(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            Preconditions.checkNotNull(args);
            Preconditions.checkNotNull(l);
            Preconditions.checkNotNull(z.getData());
            Preconditions.checkNotNull(o);
```

**After**
```java
        public void mainConflictAnnotations(String!!! args, Object? o, Long!!! l, Preconditions? z) {args!!;l!!;
            z.data!!;
            o!!;
```


##### Scenario 4

**Before**
```java
        public void mainConflictAnnotationsWithMsg(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            Preconditions.checkNotNull(args, "args are null");
            Preconditions.checkNotNull(l, "l is null");
            Preconditions.checkNotNull(z.getData(), "o is null");
            Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
        public void mainConflictAnnotationsWithMsg(String!!! args, Object? o, Long!!! l, Preconditions? z) {args!!;l!!;
            z.data!!;
            o!!;
```


##### Scenario 5

**Before**
```java
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
```

**After**
```java
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;
```


##### Scenario 6

**Before**
```java
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;
```


##### Scenario 7

**Before**
```java
        public void mainNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
```

**After**
```java
        public void mainNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;
```


##### Scenario 8

**Before**
```java
        public void mainMsgsNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
        public void mainMsgsNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = args!!;
            this.l = l!!;
            this.data = z.data!!;
            this.o = o!!;
```


#### NullableAnnotationCheckNotNullFieldShiftTestData

##### Scenario 1

**Before**
```java
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
```

**After**
```java
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
```


##### Scenario 2

**Before**
```java
            this.args = Preconditions.checkNotNull(args, "args are null");
            this.l = Preconditions.checkNotNull(l, "l is null");
            this.data = Preconditions.checkNotNull(z.getData(), "saaa is null");
            this.o = Preconditions.checkNotNull(o, "o is null");
```

**After**
```java
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
```


##### Scenario 3

**Before**
```java
        public void mainNullable(@Nullable String args, @Nullable Object o, @Nullable Long l, @Nullable Preconditions z) {
            this.args = Preconditions.checkNotNull(args);
            this.l = Preconditions.checkNotNull(l);
            this.data = Preconditions.checkNotNull(z.getData());
            this.o = Preconditions.checkNotNull(o);
```

**After**
```java
        public void mainNullable(String? args, Object? o, Long? l, Preconditions? z) {
            this.args = <<!!;
            this.l = <<!!;
            this.data = z.<<!!;
            this.o = <<!!;
```


#### DynamicTestData

##### Scenario 1

**Before**
```java
    public static void staticMethod(Data data) {
```

**After**
```java
    public static void changedStaticMethod(Data data) {
```


##### Scenario 2

**Before**
```java
                .normalMethod(
                        staticMethod(
```

**After**
```java
                .changedNormalMethod(
                        changedStaticMethod(
```


##### Scenario 3

**Before**
```java
                                        .normalMethod(
                                                new DynamicTestData().staticMethod(
                                                        data.getData()
```

**After**
```java
                                        .changedNormalMethod(
                                                new DynamicTestData().changedStaticMethod(
                                                        data.data
```


##### Scenario 4

**Before**
```java
        staticMethod(data.getData());
```

**After**
```java
        changedStaticMethod(data.data);
```


##### Scenario 5

**Before**
```java
    private String normalMethod(String args) {
        return normalMethod(args.substring(1));
```

**After**
```java
    private String changedNormalMethod(String args) {
        return changedNormalMethod(args.substring(1));
```


##### Scenario 6

**Before**
```java
    private static String staticMethod(String args) {
```

**After**
```java
    private static String changedStaticMethod(String args) {
```
