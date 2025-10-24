# Control Flow Multi Statement Code Block Collapse (State field: controlFlowMultiStatementCodeBlockCollapse)

### Control Flow Multi Statement Code Block Collapse
Folds multi-statement control-flow braces in read-only files.

#### Example: ControlFlowMultiStatementTestData

examples/data/ControlFlowMultiStatementTestData.java:
```java
        if (args.length > 0) {
// ...
        }
        if (args.length == 0) {
```

folded/ControlFlowMultiStatementTestData-folded.java:
```java
        if (args.length > 0) 
// ...
        if (args.length == 0) 
```

Highlights ControlFlowMultiStatementTestData with control flow multi statement code block collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `controlFlowMultiStatementCodeBlockCollapse`
Related features: (none)
---

#### Folding catalogue

##### ControlFlowMultiStatementTestData mappings
| Before | After |
| --- | --- |
| `if (args.length > 0) { System.out.println("..."); System.out.println("..."); }` | `if (args.length > 0) System.out.println("..."); System.out.println("...");` |
| `if (args.length == 0) { System.out.println("..."); System.out.println("..."); } else { System.out.println("Success"); }` | `if (args.length == 0) System.out.println("..."); System.out.println("..."); else { System.out.println("Success"); }` |
| `if (args.length > 0) { System.out.println("Terminating"); } else { System.out.println("Terminating"); System.out.println("..."); }` | `if (args.length > 0) { System.out.println("Terminating"); } else System.out.println("Terminating"); System.out.println("...");` |
| `for (String arg : args) { System.out.println(i++); System.out.println(arg); }` | `for (String arg : args) System.out.println(i++); System.out.println(arg);` |
| `while (true) { System.out.println("..."); break; }` | `while (true) System.out.println("..."); break;` |
| `try { System.out.println("..."); System.out.println("..."); } catch (Exception e) { System.out.println("..."); e.printStackTrace(); }` | `try System.out.println("..."); System.out.println("..."); catch (Exception e) System.out.println("..."); e.printStackTrace();` |
| `do { System.out.println("..."); break; } while (true);` | `do System.out.println("..."); break; while (true);` |
