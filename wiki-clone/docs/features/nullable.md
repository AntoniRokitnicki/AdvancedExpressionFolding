# Nullable annotations

## Overview
The **nullable** toggle rewrites common nullability annotations into Kotlin-style suffixes so intent is readable at a glance.
`@NotNull` members gain a `!!` marker, `@Nullable` members pick up a `?`, and chained `Preconditions.checkNotNull` calls collapse
to concise postfix expressions. The behaviour mirrors JetBrains' Kotlin editor without changing the underlying Java source.

## Usage
Enable this option when reviewing code that mixes Java APIs with nullability annotations from JetBrains, JSR-305, or similar
libraries. The folded syntax highlights null contracts in long property lists and setter chains, reducing the noise created by
repeated annotations. Leave it disabled if your team relies on custom annotations that must remain visible.

## Configuration
- **Toggle ID:** `nullable`
- **Default state:** Off
- **IDE path:** Settings/Preferences → Editor → General → Code Folding → Advanced Expression Folding → Null safety
- **Related toggles:** [Interface extension properties](interfaceExtensionProperties.md), [Check expressions collapse](checkExpressionsCollapse.md)

## Quick reference

| Sample | Source file | Folded output |
| --- | --- | --- |
| Annotated fields | `examples/data/NullableAnnotationTestData.java` | `folded/NullableAnnotationTestData-folded.java` |
| `checkNotNull` pipelines | `examples/data/NullableAnnotationCheckNotNullTestData.java` | `folded/NullableAnnotationCheckNotNullTestData-folded.java` |

## Examples

### Annotated fields become suffixed types
```java
@NotNull NullableAnnotationTestData data;
@Nullable String title;
```

⬇️

```java
NullableAnnotationTestData!! data;
String? title;
```

### `checkNotNull` pipelines show intent inline
```java
this.args = Preconditions.checkNotNull(args);
this.data = Preconditions.checkNotNull(z.getData());
```

⬇️

```java
this.args = args!!;
this.data = z.data!!;
```

### Interface accessors keep nullability markers
```java
interface Person {
    @Nullable String getNickname();
}
```

⬇️

```java
interface Person {
    String? nickname;
}
```

## Tips
- Combine **nullable** with [interface extension properties](interfaceExtensionProperties.md) to surface Kotlin-style contracts on interface methods.
- Pair it with [check expressions collapse](checkExpressionsCollapse.md) so folded null markers line up with Elvis expressions and safe calls in the same file.
