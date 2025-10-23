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

#### Folding catalogue

##### PrintlnTestData mappings
| Before | After |
| --- | --- |
| `System.out.println("Hello");` | `println("Hello");` |
| `<pre>System.out.println
                (123);</pre>` | `<pre>println
                (123);</pre>` |
| `<pre>System.
                out.println("Spacing");</pre>` | `println("Spacing");` |
| `<pre>System.out.
                println(3.14);</pre>` | `println(3.14);` |
| `System.out.println(string);` | `println(string);` |
| `System.out.println(true);` | `println(true);` |
| `System.out.println('A');` | `println('A');` |
| `System.out.println(CONST_VALUE);` | `println(CONST_VALUE);` |
| `System.out.println("Divided: " + "" +"into" +" multiple" + " " + "strings");` | `println("Divided: " + "" + "into" + " multiple" + " " + "strings");` |
| `System.out.println("Passed as parameter: " + string);` | `println("Passed as parameter: " + string);` |
| `<pre>System.out.println("Passed as parameter: " +
this.getClass());</pre>` | `println("Passed as parameter: " + this.getClass());` |
| `<pre>System.out.println("""
                text
                block
                """);</pre>` | `<pre>println("""
                text
                block
                """);</pre>` |
