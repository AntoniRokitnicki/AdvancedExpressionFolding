# Option Generator Script

This project ships with a Kotlin script that scaffolds a new folding toggle by updating the plugin's settings, UI, examples, and tests.

## Running the script

You can execute the generator either through Gradle or directly with the Kotlin scripting engine:

```bash
./gradlew -Pproject.dir="/absolute/path/to/AdvancedExpressionFolding" :option-code-generator
```

```bash
kotlinc -script option-code-generator.main.kts \
  -Dproject.dir="/absolute/path/to/AdvancedExpressionFolding" \
  -DvarName=myNewToggle \
  -DvarText="My new toggle label"
```

* `project.dir` is optional and defaults to the current working directory. Set it when you need to point the generator at a different checkout.

## Required properties

The following JVM properties must be provided for meaningful output:

- `varName` – Kotlin-friendly property name that becomes the new toggle field (for example `pseudoAnnotationsMain`).
- `varText` – Human-readable label that appears in the settings UI (for example `"Pseudo-annotations: @Main"`).

## Files that are created or updated

The script uses comment markers such as `// NEW OPTION` to inject code in several existing files. Expect edits in the following locations:

- `src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt`
- `src/com/intellij/advancedExpressionFolding/settings/UnclassifiedFeatureState.kt`
- `src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt`
- `examples/data/<VarName>TestData.java`
- `test/com/intellij/advancedExpressionFolding/FoldingTest.kt`

## Mandatory review checklist

> [!WARNING]
> After running the generator, carefully inspect the resulting diffs, with special attention to:
>
> - `settings/AdvancedExpressionFoldingSettings.kt`
> - `settings/view/CheckboxesProvider.kt`
> - the new or updated files under `examples/data`
> - the corresponding test entry in `test/.../FoldingTest.kt`
>
> The script provides a scaffold, but these areas usually need manual touch-ups (links, example content, test parameters, etc.).
