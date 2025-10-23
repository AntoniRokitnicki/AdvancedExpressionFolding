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

### @NotFullyImplemented

Fills in all missing interface methods with a body that throws `new NotImplementedException()`.

#### How it works:
1. **Completion trigger**: Type `@NotFullyImplemented` inside the modifier list of a class that implements interfaces
2. **Method generation**: Selecting the completion removes the pseudo-annotation and inserts implementations for every unimplemented interface method
3. **Method bodies**: Each generated method contains a single `throw new NotImplementedException();` statement so the class remains compilable
4. **Overrides**: Adds the `@Override` annotation when applicable to keep the class aligned with the Java style settings

#### Code example:
```java
public interface Service {
    void start();
    String status();
}

public class DemoService implements Service {

    @Override
    public void start() {
        throw new NotImplementedException();
    }

    @Override
    public String status() {
        throw new NotImplementedException();
    }
}
```

#### Notes:
- Works only when the `pseudoAnnotations` setting is enabled
- Leaves existing implementations untouched—only missing interface methods are generated
- Ideal for sketching class outlines before writing the real logic
