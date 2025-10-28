![System.out.println call folded to println](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/75a5224f-7b52-4b71-9774-2814e8a867ba)

# Println (State field: println)

### Println
Folds System.out.println calls into println.

#### Example: PrintlnTestData

examples/data/PrintlnTestData.java:
```java
        System.out.println("Hello");
        System.out.println
// ...
        System.
                out.println("Spacing");
        System.out.
// ...
        System.out.println("Passed as parameter: " +
this.getClass());
        System.out.println("""
```

folded/PrintlnTestData-folded.java:
```java
        println("Hello");
        println
// ...
        println("Spacing");
        println(3.14);
        println(string);
// ...
        println("Passed as parameter: " + string);
        println("Passed as parameter: " + this.getClass());
        println("""
```

Highlights PrintlnTestData with println.
Removes boilerplate while preserving behavior.

Default: On
Controlled by: `println`
Related features: (none)

---
### Folding catalogue

#### PrintlnTestData

##### Scenario 1

**Before**
```java
        System.out.println("Hello");
        System.out.println
```

**After**
```java
        println("Hello");
        println
```


##### Scenario 2

**Before**
```java
        System.
                out.println("Spacing");
        System.out.
                println(3.14);
        System.out.println(string);
        System.out.println(true);
        System.out.println('A');
        System.out.println(CONST_VALUE);
        System.out.println("Divided: " + "" +
                "into" +
                " multiple" + " " + "strings");
        System.out.println("Passed as parameter: " + string);
        System.out.println("Passed as parameter: " +
this.getClass());
        System.out.println("""
```

**After**
```java
        println("Spacing");
        println(3.14);
        println(string);
        println(true);
        println('A');
        println(CONST_VALUE);
        println("Divided: " + "" + "into" + " multiple" + " " + "strings");
        println("Passed as parameter: " + string);
        println("Passed as parameter: " + this.getClass());
        println("""
```

