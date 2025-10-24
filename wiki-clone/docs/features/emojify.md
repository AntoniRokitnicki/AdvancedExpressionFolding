# Emojify (State field: emojify)

### Emojify
Replaces select syntax elements with emoji equivalents.

#### Example: EmojifyTestData

examples/data/EmojifyTestData.java:
```java
package data;

import java.time.DayOfWeek;
// ...
public class EmojifyTestData {

    public final class FinalData {
// ...

        public void anotherMethod() {
            final int anotherFinalVariable;
```

folded/EmojifyTestData-folded.java:
```java
📦 data;

🚢 java.time.DayOfWeek;
// ...
public 🏛️ EmojifyTestData {

    public 🔒 🏛️ FinalData {
// ...

        public 💀 anotherMethod() {
            🔒 🔢 anotherFinalVariable;
```

Highlights EmojifyTestData with emojify.
Removes boilerplate while preserving behavior.

Default: Off
Controlled by: `emojify`
Related features: (none)

#### Keyword and literal replacements

The emojify processor scans every `PsiJavaToken` that survives other folds and swaps specific keyword tokens for fixed emoji glyphs. The mapping is hard-coded in `PsiJavaTokenExt` and covers null literals, modifiers, control-flow keywords, and primitive types, ensuring that the emoji replaces only the token text while leaving surrounding punctuation intact.【F:src/com/intellij/advancedExpressionFolding/processor/token/PsiJavaTokenExt.kt†L12-L58】

| Category | Java tokens | Emoji replacement |
| --- | --- | --- |
| Literals | `null` | `🕳️` |
| Modifiers | `final`, `static`, `abstract`, `native`, `transient`, `volatile`, `protected`, `private` | `🔒`, `⚡`, `🎨`, `🏕️`, `🚂`, `☢️`, `🛡️`, `🚫` |
| Type keywords | `void`, `boolean`, `byte`, `char`, `int`, `long`, `float`, `double` | `💀`, `🔘`, `💾`, `🅲`, `🔢`, `📏`, `🏊`, `⚖️` |
| Namespace keywords | `package`, `import`, `exports`, `requires`, `record`, `interface`, `enum`, `class` | `📦`, `🚢`, `🚢`, `🚧`, `📀`, `🖥️`, `📊`, `🏛️` |
| Flow-control keywords | `try`, `catch`, `throw`, `throws`, `return`, `break`, `case`, `do`, `else`, `for`, `while`, `switch`, `yield` | `🤞`, `🎣`, `🪃`, `🪃`, `🔙`, `✋`, `📦`, `▶️`, `🔄`, `🔁`, `♾️`, `🔀`, `🚸` |
| References | `this`, `super`, `instanceof` | `📍`, `💪`, `is` |

These substitutions explain why the folded example shows emoji versions of `package`, modifiers such as `final`, primitive declarations like `int`, and flow statements like the `try`/`catch` blocks inside the null-handling scenarios.

#### Singleton field aliasing

When the emojify toggle is active, reference expressions that resolve to a static singleton field named `INSTANCE` are rewrapped so that the identifier itself renders as the standing-person emoji. The check verifies that the field is static, refers to its declaring class (`singletonField` helper), and is accessed through the class literal, preventing unrelated identifiers from being touched.【F:src/com/intellij/advancedExpressionFolding/processor/language/ExperimentalExt.kt†L12-L29】【F:src/com/intellij/advancedExpressionFolding/processor/psiElementExtensions.kt†L33-L35】

examples/data/EmojifyTestData.java:
```java
    class SingletonUsage {
        void main() {
            var s = Singleton.INSTANCE;
        }
    }
```

folded/EmojifyTestData-folded.java:
```java
    🏛️ SingletonUsage {
        💀 main() {
            var s = Singleton.🧍;
        }
    }
```

Singleton references therefore collapse to `Singleton.🧍` throughout the example while preserving non-singleton members like `OTHER_NAME`.

---

#### Arithmetic Helper Folding

##### StaticImportUsage

**useStaticImport**
examples/data/EmojifyTestData.java:
```java
        public void useStaticImport() {
            int max = java.lang.Math.max(5, 10);
        }
```
folded/EmojifyTestData-folded.java:
```java
        public 💀 useStaticImport() {
            🔢 max = java.lang.Math.max(5, 10);
        }
```
Emojify replaces the `void` return type and `int` local with their emoji counterparts while leaving `Math.max` untouched.

#### Integration coverage

The `testData/EmojifyTestData-all.java` fixture runs the emoji substitutions across every context used in integration tests. The folded output shows:

* Namespace keywords (`package`, `import`), declaration modifiers (`final`, `static`, `abstract`, `transient`, `volatile`, `native`), and type keywords (`void`, `int`, `boolean`, etc.) all render with their emoji replacements inside class headers, fields, constructors, and method signatures.
* Control-flow tokens like `try`, `catch`, `return`, `for`, `switch`, and `default` pick up their glyphs even when nested inside anonymous classes, lambdas, or enhanced for-loops.
* The singleton access rewrite converts `Singleton.INSTANCE` into `Singleton.🧍`, matching the integration scenarios that assign or return the singleton reference.
* Because the processor operates after other folds, Lombok-generated getters/setters, builder patterns, and other feature rewrites coexist with the emoji tokens—ensuring the emoji version of each keyword appears alongside any additional folding.
