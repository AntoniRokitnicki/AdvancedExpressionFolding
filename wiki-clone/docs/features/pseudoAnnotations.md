https://github.com/user-attachments/assets/53ad15ef-2c32-4fe4-a857-d36114d020aa

# Pseudo Annotations (State field: pseudoAnnotations)

### Pseudo Annotations
Folds entry points into an @Main pseudo-annotation.

#### Example: PseudoAnnotationsMainTestData

examples/data/PseudoAnnotationsMainTestData.java:
```java
package data;


// ...
    }

}
```

folded/PseudoAnnotationsMainTestData-folded.java:
```java
// No folded sample provided.
```

Highlights PseudoAnnotationsMainTestData with pseudo annotations.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `pseudoAnnotations`
Related features: (none)

### @Optional

Creates `Optional`-returning wrapper methods for every non-void method in the annotated class.

https://github.com/user-attachments/assets/9dca58be-1d1f-40ed-8d8d-55f3b21339f5

#### How it works:
1. **Completion trigger**: Type `@Optional` above any class declaration to see the completion suggestion
2. **Wrapper generation**: Selecting `@Optional` removes the annotation and generates wrapper methods named `optionalXxx`
3. **Method coverage**: Every method with a non-void return type gets a wrapper that delegates to the original method and wraps the result in `Optional.ofNullable(...)`
4. **Signature parity**: Wrapper methods keep the original modifiers, type parameters, parameters, and `throws` clauses
5. **Idempotent**: Existing wrapper methods with matching signatures are refreshed to ensure they use the `Optional` import while avoiding duplicates

#### Code example:
```java
@Optional
public class Repository {
    public String findName() {
        return "Ada";
    }

    public int findAge() {
        return 42;
    }
}
```

After selecting `@Optional`:
```java
import java.util.Optional;

public class Repository {
    public String findName() {
        return "Ada";
    }

    public Optional<String> optionalFindName() {
        return Optional.ofNullable(findName());
    }

    public int findAge() {
        return 42;
    }

    public Optional<Integer> optionalFindAge() {
        return Optional.ofNullable(findAge());
    }
}
```

#### Smart behavior:
- Maintains `static`, visibility, `final`, `synchronized`, and `strictfp` modifiers on generated wrappers
- Copies generic type parameters, parameter lists (including varargs), and declared exceptions
- Automatically boxes primitive return types before wrapping them in `Optional`
- Skips constructors, `void` methods, abstract/native methods, and methods that already return `Optional`

#### Notes:
- Works only when the `pseudoAnnotations` setting is enabled
- Generated wrappers follow project formatting and add the necessary `Optional` import automatically
