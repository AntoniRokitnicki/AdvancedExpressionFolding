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

### @Cache / @Memoize

Adds memoization to an existing method so that the body executes only once per input set. Typing `@Cache` or `@Memoize` above a
method offers completion that rewrites the method and introduces helper members.

#### How it works:
1. **Field generation**:
   - Parameterless methods get two fields: `<Type> methodNameCache` and `boolean methodNameCacheInitialized`
   - Methods with parameters get a `Map<List<Object>, Type>` cache that stores results per argument list
2. **Method wrapping**: The original method body is moved into a private helper `methodName$impl`
3. **Lookup logic**:
   - For parameterless methods, the rewritten method checks `methodNameCacheInitialized`
   - For methods with parameters, the rewritten method builds a `List<Object>` key using `Arrays.asList(...)`
4. **Result storage**: The computed result is stored in the cache field and returned immediately on subsequent calls
5. **Alias**: `@Memoize` is an alias of `@Cache`

#### Example (parameterless method):
```java
public class Calculator {
    public int randomOnce() {
        return compute();
    }
}
```

After `@Cache` on `randomOnce()`:
```java
public class Calculator {
    private int randomOnceCache;
    private boolean randomOnceCacheInitialized;

    public int randomOnce() {
        if (randomOnceCacheInitialized) {
            return randomOnceCache;
        }
        int __result = randomOnce$impl();
        randomOnceCache = __result;
        randomOnceCacheInitialized = true;
        return __result;
    }

    private int randomOnce$impl() {
        return compute();
    }
}
```

#### Example (method with parameters):
```java
public class Formatter {
    public static String render(String template, int value) {
        return template + value;
    }
}
```

After `@Memoize` on `render(...)`:
```java
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Formatter {
    private static final Map<List<Object>, String> renderCache = new HashMap<>();

    public static String render(String template, int value) {
        List<Object> __cacheKey = Arrays.asList(template, value);
        Map<List<Object>, String> __cache = renderCache;
        String __cachedValue = __cache.get(__cacheKey);
        if (__cachedValue != null || __cache.containsKey(__cacheKey)) {
            return __cachedValue;
        }
        String __result = render$impl(template, value);
        __cache.put(__cacheKey, __result);
        return __result;
    }

    private static String render$impl(String template, int value) {
        return template + value;
    }
}
```

#### Notes:
- Works only for non-void methods with concrete bodies
- Uses `List<Object>` cache keys and relies on `equals`/`hashCode` of parameters
- Automatically preserves `static` modifier and throws clause
- Requires the `pseudoAnnotations` setting to be enabled
