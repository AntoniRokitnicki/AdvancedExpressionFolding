# AGENTS.md

## Language and Style
- Use Java 17 or Kotlin.
- Use idiomatic Kotlin for all new code and utilities.
- Follow Google Java Style and Kotlin Coding Conventions.
- Unit tests must use JUnit 5.

## Project Structure
- Plugin is based on Gradle with Kotlin DSL.
- Main modules:
  - `src/main/java` – production code
  - `src/test/java` – tests
  - `resources/META-INF/plugin.xml` – plugin definition

### Test Data Structure
- `testData/` – Input Java files for testing
- `folded/` – Expected output showing folded results
- `examples/data/` – Example files demonstrating features
- Keep `examples/data/` updated with new folding features.

## Commits
- Follow Conventional Commits (`feat:`, `fix:`, `refactor:`, `test:`).
- Each commit should cover a single logical scope.

## Pull Requests
- Every PR must include a description and reason for change.
- PRs should be small and readable.

## Testing
- Always run `./gradlew test` **after** making code changes.
- Never run tests before applying the changes.
- If tests fail, fix the code until all tests pass.
- Do not run tests if only documentation files (e.g. `.md`) are modified.
- **Test Data Mapping**: Test methods use camelCase names mapped to PascalCase file names, e.g.
  `appendSetterInterpolatedStringTestData()` → `AppendSetterInterpolatedStringTestData.java`. Folding
  config comes from `doFoldingTest` parameters, so `doFoldingTest(state::concatenationExpressionsCollapse,
  state::getSetExpressionsCollapse)` yields `state: ["concatenationExpressionsCollapse", "getSetExpressionsCollapse"]`.

## Documentation Generation
- When documenting the project, produce `complete-project-documentation.yaml` reflecting the entire repository.
- When unsure whether documentation should be updated, ask the user (e.g., "update docs?") rather than forcing an update.
- Analyze every file including source, tests, resources, examples, configuration, scripts, and documentation.
- Organize the YAML to mirror the directory structure.
- For each file include: `purpose`, `key_classes`, `key_methods`.
- For `testData` files, map camelCase test methods to PascalCase file names and extract `state::` parameters from `doFoldingTest`; include them under `state` unless the file ends with `-all.java`.
- Separate main and test code sections.
- Descriptions must be specific and exhaustive; do not skip files or use vague language.
