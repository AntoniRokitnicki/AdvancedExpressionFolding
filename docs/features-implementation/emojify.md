---
title: Emojify (State field: emojify)
option: emojify
source: wiki-clone/docs/features/emojify.md
---
# Emojify (State field: emojify)

## Implementation Summary

This toggle gates the processors listed below; each entry shows the guard in the production code.

- `src/com/intellij/advancedExpressionFolding/processor/language/ExperimentalExt.kt:15` – `return emojify.takeIfTrue(element)?.singletonField()`
- `src/com/intellij/advancedExpressionFolding/processor/token/PsiJavaTokenExt.kt:14` – `emojify.takeIfTrue() ?: return null`

## Default Configuration

- Defined in `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt:86` with default `false`.
- Defined in `src/com/intellij/advancedExpressionFolding/settings/EmojiVisibilityState.kt:12` with default `false`.

### Settings UI Registration

- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:232` – `registerCheckbox(state::emojify, "Emojify code") {`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt:234` – `link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#emojify")`

## Tests

Automated folding tests cover this toggle at the locations below.

- `test/com/intellij/advancedExpressionFolding/performance/performanceResult.kt:28` – `::emojifyTestData to 13`
- `test/com/intellij/advancedExpressionFolding/folding/base/full/FullFoldingTest.kt:14` – `.enableAll(state::emojify, state::finalEmoji, state::arithmeticExpressions)`
- `test/com/intellij/advancedExpressionFolding/folding/base/folding/EmojiVisibilityFoldingTest.kt:16` – `fun emojifyTestData() = testCase.runFoldingTest(foldingState()::emojify)`

## Example Inputs

- No sample inputs were matched automatically.

## Legacy Reference

Content imported from the historical wiki entry for completeness.

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

#### Integration fixture coverage

The integration test `testData/EmojifyTestData-all.java` exercises the emoji overlay across a wide spectrum of declarations. Final classes, methods, and locals all carry the 🔒 modifier replacement, while local `final int` declarations collapse to the compact `val` form before the emoji substitution is applied.【F:testData/EmojifyTestData-all.java†L8-L25】 Static members and initializer blocks ensure the ⚡ glyph survives both declaration sites and implicit constructor calls.【F:testData/EmojifyTestData-all.java†L28-L40】 The fixture also walks through accessor-heavy classes, enums, synchronized blocks, and singleton access so that getter/setter boilerplate, enum override bodies, the `synchronized` keyword, and `Singleton.INSTANCE` references all render with the expected pictograms during folding.【F:testData/EmojifyTestData-all.java†L43-L125】【F:testData/EmojifyTestData-all.java†L537-L561】 This cross-section of constructs mirrors the behaviour asserted in the folding tests and documents precisely which Java tokens receive emoji substitutions.

### Integration fixture coverage

The full `EmojifyTestData-all.java` integration fixture enumerates every token that receives an emoji makeover, so the folding tests assert the behaviour without relying on the trimmed example file:

- Lines covering `FinalData` and `StaticData` prove that class, method, parameter, and local modifiers collapse to emoji glyphs while the surrounding braces and statements stay put.【F:testData/EmojifyTestData-all.java†L8-L40】
- The getter/setter, enum, synchronized, and transient/volatile sections ensure the emoji substitutions coexist with Lombok-style folds, method bodies, and synchronized blocks without corrupting the code structure.【F:testData/EmojifyTestData-all.java†L43-L155】
- The `NullUsage` scenarios walk through arrays, collections, streams, optionals, lambdas, and assertions to confirm that every `null`, primitive type, and control-flow keyword is swapped for its icon while the logic remains readable.【F:testData/EmojifyTestData-all.java†L439-L532】
- The `SingletonUsage` block validates the special handling of `INSTANCE` references so class-level singletons render as `🧍` yet other fields keep their original identifiers.【F:testData/EmojifyTestData-all.java†L537-L564】
