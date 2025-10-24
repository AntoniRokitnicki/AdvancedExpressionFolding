# Control Flow Single Statement Code Block Collapse (State field: controlFlowSingleStatementCodeBlockCollapse)

### Control Flow Single Statement Code Block Collapse
Folds single-statement control-flow braces in read-only files.

#### Example: ControlFlowSingleStatementTestData

examples/data/ControlFlowSingleStatementTestData.java:
```java
        if (args.length > 0) {
// ...
        }
        if (args.length == 0) {
```

folded/ControlFlowSingleStatementTestData-folded.java:
```java
        if (args.length > 0) 
// ...
        if (args.length == 0) 
```

Highlights ControlFlowSingleStatementTestData with control flow single statement code block collapse.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `controlFlowSingleStatementCodeBlockCollapse`
Related features: (none)
---

#### Folding catalogue

##### ControlFlowSingleStatementTestData mappings
| Before | After |
| --- | --- |
| `if (args.length > 0) { System.out.println(Arrays.asList(args)); }` | `if (args.length > 0) System.out.println(Arrays.asList(args));` |
| `if (args.length == 0) { System.out.println("..."); } else { System.out.println("..."); }` | `if (args.length == 0) System.out.println("..."); else System.out.println("...");` |
| `for (String arg : args) { System.out.println(arg); }` | `for (String arg : args) System.out.println(arg);` |
| `for (int i = 0; i < args.length; i++) { System.out.println(args[i]); }` | `for (int i = 0; i < args.length; i++) System.out.println(args[i]);` |
| `while (true) { break; }` | `while (true) break;` |
| `do { break; } while (true);` | `do break; while (true);` |
| `try { System.out.println("..."); } catch (Exception e) { System.out.println("..."); }` | `try System.out.println("..."); catch (Exception e) System.out.println("...");` |
