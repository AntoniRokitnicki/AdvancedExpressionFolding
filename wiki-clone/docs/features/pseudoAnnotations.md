### @Main

Generates a main method for quick testing of any method through pseudo-annotation code completion.

https://github.com/user-attachments/assets/53ad15ef-2c32-4fe4-a857-d36114d020aa

#### How it works:
1. **Completion trigger**: Type `@Main` above any method to see completion suggestion
2. **Method generation**: Selecting `@Main` generates a `public static void main(String[] args)` method in the top-level class
3. **Parameter handling**: Creates local variables with default values for all required parameters
4. **Call strategy**: 
   - **Constructor**: Creates new instance with generated parameters
   - **Static method**: Calls directly with parameters
   - **Instance method**: Creates instance first, then calls method
   - **Non-void return**: Wraps call in `System.out.println()`

#### Code example:
```java
public class Person {
    private String name;
    
    public Person(String name) {
        this.name = name;
    }
    
    public void greet(String greeting) {
        System.out.println(greeting + " " + name);
    }
}
```

After `@Main` on `greet()`:
```java
public class Person {
    public static void main(String[] args) {
        String name = null;
        
        String greeting = null;
        new Person(name).greet(greeting);
    }
    
    private String name;
    
    public Person(String name) {
        this.name = name;
    }
    
    public void greet(String greeting) {
        System.out.println(greeting + " " + name);
    }
}
```

#### Parameter defaults:
- `int`, `long`, `byte`, `short` → `0`
- `boolean` → `false`
- `char` → `'\0'`
- `float` → `0.0f`
- `double` → `0.0`
- `String` and objects → `null`
- `java.util.Date` → `new java.util.Date()`
- `java.time.LocalDate` → `LocalDate.now()`
- `java.time.LocalDateTime` → `LocalDateTime.now()`
- `java.time.ZonedDateTime` → `ZonedDateTime.now()`
- Varargs → empty arrays (`new Type[]{}`)

#### Smart behavior:
- **Instance methods**: If the containing class has a constructor with parameters, those are also generated as variables
- **Cleanup**: Removes any existing main method before generating new one
- **Annotation removal**: The `@Main` pseudo-annotation is removed after generation
- **Formatting**: Adds proper spacing between constructor and method parameters

#### Notes:
- This is a development-time convenience feature, not a real annotation
- The generated main method is fully functional and can be run immediately
- Only works when `pseudoAnnotations` setting is enabled
- Designed for rapid prototyping and testing

### @Optional

Creates `Optional`-returning wrapper methods for every non-void method in the annotated class.

https://github.com/user-attachments/assets/9dca58be-1d1f-40ed-8d8d-55f3b21339f5

#### How it works:
1. **Completion trigger**: Type `@Optional` above any class declaration to see the completion suggestion
2. **Wrapper generation**: Selecting `@Optional` removes the annotation and generates wrapper methods named `optionalXxx`
3. **Method coverage**: Every method with a non-void return type gets a wrapper that delegates to the original method and wraps the result in `Optional.ofNullable(...)`
4. **Signature parity**: Wrapper methods keep the original modifiers, type parameters, parameters, and `throws` clauses
5. **Idempotent**: Existing wrapper methods with matching signatures are left untouched to avoid duplicates

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
- Skips constructors, `void` methods, abstract/native methods, and pre-existing wrappers with the same signature

#### Notes:
- Works only when the `pseudoAnnotations` setting is enabled
- Generated wrappers follow project formatting and add the necessary `Optional` import automatically
