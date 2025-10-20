# AGENTS.md

## Language and Style
- Use idiomatic Kotlin.
- Unit tests must use JUnit 5.
- Plugin is based on Gradle with Kotlin DSL.

## Project Structure
- `src` – production code
- `test` – tests
- `resources/META-INF/plugin.xml` – plugin definition

### Test Data Structure

- `testData/` – Input Java files for testing
- `folded/` – Expected output showing folded results
- `examples/data/` – Example files demonstrating features
- Keep `examples/data/` updated with new folding features.

## Workflow
- Before starting any work, rebase the `main` branch onto the latest `origin/main` to ensure you are working with the newest code.

## Adding settings options
- Prefer using the Kotlin script `./option-code-generator.main.kts` to scaffold a new toggle. It injects the property, checkbox, docs, and tests.
- Execute the script with the desired option name and label, for example:
  ```bash
  ./option-code-generator.main.kts -DvarName=pseudoAnnotationsMain -DvarText="Pseudo-annotations: @Main"
  ```
  The helper shell script `option-code-generator.main.sh` shows the same invocation.
- When the generator finishes, review and adjust the produced files, including settings state, checkbox registration, examples, and the new `FoldingTest` entry.

## Pull Requests
- Every PR must include a description and reason for change.

## Testing
- Always run `./gradlew clean build test` **after** making code changes.
- If tests fail, fix the code until all tests pass.
- Do not run tests if only documentation files (e.g. `.md`) are modified.
- **Test Data Mapping**: Test methods use camelCase names mapped to PascalCase file names, e.g.
  `appendSetterInterpolatedStringTestData()` → `AppendSetterInterpolatedStringTestData.java`. Folding
  config comes from `doFoldingTest` parameters, so `doFoldingTest(state::concatenationExpressionsCollapse,
  state::getSetExpressionsCollapse)` yields `state: ["concatenationExpressionsCollapse", "getSetExpressionsCollapse"]`.
