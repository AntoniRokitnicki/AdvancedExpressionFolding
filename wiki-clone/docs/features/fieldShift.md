[video](https://youtu.be/qANBuozPpvM)

![Animated preview of field shift folding](https://github.com/user-attachments/assets/1e314518-72a3-445f-a966-e13608a3c678)


## for assignments

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

Default: On
Controlled by: `fieldShift`
Related features: (none)
---

#### Folding catalogue

##### FieldShiftBuilder adjustments
| Before | After |
| --- | --- |
| `this.username = username;` | `this.username = <<;` |
| `.username(record.username())` | `.username(record<<)` |
| `.active(source.isActive())` | `.active(source<<)` |
| `.userIdentifier(source.getUserIdentifier())` | `.userIdentifier(source<<)` |
| `.child(builder1 ... .build().getChild())` | `.child(builder1 ... .build()<<)` |
| `.username(source.getUsername() + source.getUserIdentifier())` | `.username(source.username + source.userIdentifier)` |

##### FieldShiftSetters adjustments
| Before | After |
| --- | --- |
| `result.setUsername(source.getUsername());` | `result.username = source<<;` |
| `result.setActive(source.isActive());` | `result.active = source<<;` |
| `result.setUserIdentifier(source.getUserIdentifier());` | `result.userIdentifier = source<<;` |
| `setters2.setActive(setters.isActive());` | `setters2.active = setters<<;` |
| `childBuilder2.setUsername(source.getUsername());` | `childBuilder2.username = source<<;` |

##### FieldShiftFields adjustments
| Before | After |
| --- | --- |
| `this.userIdentifier = child.userIdentifier;` | `this.userIdentifier = child.<<;` |
| `result.username = source.child.username;` | `result.username = source.child.<<;` |
| `result.userIdentifier = source.userIdentifier;` | `result.userIdentifier = source.<<;` |
| `setters2.userIdentifier = record.userIdentifier();` | `setters2.userIdentifier = record.<<;` |
| `result.child = childBuilder2.child.child.child.child;` | `result.child = childBuilder2.child.child.child.<<;` |

##### NullableAnnotationCheckNotNullFieldShiftTestData adjustments
| Before | After |
| --- | --- |
| `this.args = Preconditions.checkNotNull(args);` | `this.args = <<!!;` |
| `this.l = Preconditions.checkNotNull(l);` | `this.l = <<!!;` |
| `this.data = Preconditions.checkNotNull(z.getData());` | `this.data = z.<<!!;` |
| `this.o = Preconditions.checkNotNull(o);` | `this.o = <<!!;` |
