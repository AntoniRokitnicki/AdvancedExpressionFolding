# toBuilder() Copy (State field: toBuilderCopy)

### toBuilder() chain as copy(...)
Shows a Lombok `toBuilder()` chain that changes a few fields as a `copy` with named arguments, like a Kotlin data
class or a Scala case class. A single changed field folds to one line; several fields keep one argument per line,
indented one level deeper than the line the call starts on, with `)` back on that level.

#### Example: ToBuilderCopyTestData

examples/data/ToBuilderCopyTestData.java:
```java
        var renamed = person.toBuilder()
                .name("Alice")
                .age(31)
                .build();
        Person older = person.toBuilder().age(person.getAge() + 1).build();
```

folded/ToBuilderCopyTestData-folded.java:
```java
        var renamed = person.copy(
            name = "Alice",
            age = 31
        );
        Person older = person.copy(age = person.getAge() + 1);
```

Only a clean chain folds: `toBuilder()`, then one-argument builder calls that set a builder field of the same name,
then `build()`. A chain with any other call (`greet()`, `nameUpperCase(...)`), without `build()`, or a fresh
`Person.builder()` stays as it is. Argument values keep their own foldings, e.g. `name = source<<` with `fieldShift`.

Default: On
Controlled by: `toBuilderCopy`
Related features: `fieldShift`, `lombok`
