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

### @InheritConstructors

Generates constructors in the current class that mirror accessible constructors from the superclass. Ideal for quickly wiring up custom `Exception` types or other subclasses that simply delegate to their parent constructors.

#### How it works:
1. **Completion trigger**: Type `@InheritConstructors` above a class declaration to see the completion suggestion
2. **Constructor replication**: Selecting the annotation generates constructors matching each accessible superclass constructor
3. **Visibility preservation**: Keeps `public` and `protected` visibility modifiers when copying constructors
4. **Exception propagation**: Copies `throws` clauses so the generated constructors compile immediately
5. **Duplicate avoidance**: Skips constructors that already exist with the same parameter list in the subclass

#### Example:
```java
class BaseError extends Exception {
    public BaseError() {}
    public BaseError(String message, Throwable cause) throws java.io.IOException {}
    protected BaseError(int status) {}
}

class DerivedError extends BaseError {
    public DerivedError() {
        super();
    }

    public DerivedError(String message, Throwable cause) throws java.io.IOException {
        super(message, cause);
    }

    protected DerivedError(int status) {
        super(status);
    }
}
```

#### Notes:
- Only generates constructors that are accessible from the subclass (skips `private` and package-private constructors from other packages)
- Maintains parameter annotations and `final` modifiers when present
- Available when the `pseudoAnnotations` setting is enabled
