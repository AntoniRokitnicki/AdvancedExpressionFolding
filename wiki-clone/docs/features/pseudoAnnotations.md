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

### @Observator

Scaffolds property-change helpers for fields to make it easy to observe updates in plain Java classes.

#### How it works:
1. **Completion trigger**: Type `@Observator` above a field or at the class declaration to see completion suggestion.
2. **Support field**: Injects a `java.beans.PropertyChangeSupport` instance named `observatorSupport` if missing.
3. **Setter generation**: Produces a setter that fires a property-change event when the field value changes.
4. **Listener helpers**: Adds `add<Field>Listener` and `remove<Field>Listener` methods that delegate to the support field.
5. **Class annotation**: When used on the class, it applies the helpers to every non-static, non-final field.

#### Code example:

```java
public class CounterViewModel {
    private int count;
}
```

After `@Observator` on the field:

```java
public class CounterViewModel {
    private final java.beans.PropertyChangeSupport observatorSupport = new java.beans.PropertyChangeSupport(this);

    private int count;

    public void setCount(int count) {
        int oldValue = this.count;
        this.count = count;
        observatorSupport.firePropertyChange("count", oldValue, count);
    }

    public void addCountListener(java.beans.PropertyChangeListener listener) {
        observatorSupport.addPropertyChangeListener("count", listener);
    }

    public void removeCountListener(java.beans.PropertyChangeListener listener) {
        observatorSupport.removePropertyChangeListener("count", listener);
    }
}
```

#### Notes:
- The annotation is removed after generation just like a live template.
- Existing setters or listener helpers are preserved and not duplicated.
- Fields marked `static` or `final` are skipped when generating helpers.
