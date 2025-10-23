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

### @Visitor

Generates visitor pattern boilerplate by wiring `accept` methods on the annotated elements and adding matching `visit` signatures to the designated visitor interface.

#### How it works:
1. **Interface placeholder**: Create an empty visitor interface up front (for example `interface ShapeVisitor { }`).
2. **Annotate elements**: Type `@Visitor(ShapeVisitor.class)` above every class that should participate in the visitor pattern.
3. **Auto-generation**: Selecting `@Visitor` from completion removes the pseudo-annotation, creates `accept(ShapeVisitor visitor)` calling `visitor.visit(this)`, and injects `void visit(ClassName element);` into the target interface.

#### Code example:
```java
interface ShapeVisitor {
}

@Visitor(ShapeVisitor.class)
class Circle {
}

@Visitor(ShapeVisitor.class)
class Rectangle {
}
```

After accepting the completion for each class:
```java
interface ShapeVisitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

class Circle {
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

class Rectangle {
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
```

#### Notes:
- The visitor interface must already exist so the generator can append the `visit` methods.
- Existing `accept` and `visit` methods are respected and never duplicated.
- Works when the `pseudoAnnotations` option is enabled in settings.
