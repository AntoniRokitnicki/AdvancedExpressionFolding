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

### @Adapter

Generates a default adapter implementation for interfaces and abstract classes so you can override only the methods you care about.

#### How it works:
1. **Completion trigger**: Type `@Adapter` above an interface or abstract class to see the completion suggestion
2. **Class generation**: Selecting `@Adapter` creates a sibling `<TypeName>Adapter` class that implements or extends the annotated type
3. **Method stubs**: Every abstract method is overridden with a stub that either returns default values or throws
4. **Cleanup**: The pseudo-annotation is removed after generation and existing adapter classes with the same name are replaced

#### Configuration options:
- `@Adapter(throw = true)` – Generates bodies that throw `UnsupportedOperationException`
- `@Adapter(primitiveWrapperReturns = DEFAULT)` – Returns default primitive values for wrapper types (e.g., `Integer` → `0`)

#### Return value defaults:
- Primitives (`boolean`, `int`, `long`, etc.) → Same defaults as the language (`false`, `0`, `0L`, `0.0f`, `0.0d`)
- Primitive wrappers (`Boolean`, `Integer`, etc.) → `null` unless `primitiveWrapperReturns = DEFAULT` is set
- Other reference types → `null`
- `void` methods → Empty body

#### Example:
```java
@Adapter(primitiveWrapperReturns = DEFAULT)
public interface ChangeListener {
    void onCreated();
    Integer onUpdated();
}
```

After accepting the completion:

```java
public interface ChangeListener {
    void onCreated();
    Integer onUpdated();
}

public class ChangeListenerAdapter implements ChangeListener {

    @Override
    public void onCreated() {
    }

    @Override
    public Integer onUpdated() {
        return 0;
    }
}
```

You can also combine flags. The snippet below generates stubs that both throw and return wrapper defaults:

```java
@Adapter(throw = true, primitiveWrapperReturns = DEFAULT)
public abstract class SafeProcessor {
    public abstract Integer execute();
    protected abstract boolean enabled();
}
```

After completion the adapter looks like this:

```java
public abstract class SafeProcessor {
    public abstract Integer execute();
    protected abstract boolean enabled();
}

public class SafeProcessorAdapter extends SafeProcessor {

    @Override
    public Integer execute() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean enabled() {
        throw new UnsupportedOperationException();
    }
}
```

#### Notes:
- Works for both interfaces and abstract classes
- Generated classes inherit generic type parameters from the source type
- Only available when the `pseudoAnnotations` setting is enabled
