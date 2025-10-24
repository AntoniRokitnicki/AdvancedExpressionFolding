# for assignments

## Overview

[video](https://youtu.be/qANBuozPpvM)

## Configuration

- **Toggle ID:** `fieldShift`
- **Default state:** On
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding
- **Related toggles:** None

![Animated preview of field shift folding](https://github.com/user-attachments/assets/1e314518-72a3-445f-a966-e13608a3c678)



### Field Shift
Folds builder, setter, and assignment patterns into the << shorthand.

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

Highlights FieldShiftBuilder with field shift.
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

Highlights FieldShiftSetters with field shift.
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

Highlights FieldShiftFields with field shift.
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

Highlights NullableAnnotationCheckNotNullFieldShiftTestData with field shift.
Removes boilerplate while preserving behavior.


---
### Folding catalogue

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
