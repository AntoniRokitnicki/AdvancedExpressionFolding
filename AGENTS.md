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

## Build Environment
- The project targets **JDK 17** and relies on the **IntelliJ Platform Gradle plugin**; do not downgrade the toolchain or plugin version when configuring builds or CI.
- Kotlin compilation uses preview flags such as `-Xcontext-parameters` and `-Xnested-type-aliases`. Preserve these compiler options so that newer language features continue to compile.

## Adding settings options
- Prefer using the Kotlin script `./scripts/option-code-generator.main.kts` to scaffold a new toggle. It injects the property, checkbox, docs, and tests.
- Execute the script with the desired option name and label, for example:
  ```bash
  ./scripts/option-code-generator.main.kts -DvarName=pseudoAnnotationsMain -DvarText="Pseudo-annotations: @Main"
  ```
  The helper shell script `./scripts/option-code-generator.main.sh` shows the same invocation.
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

⸻

Verification hook

After implementing or modifying a folding rule:
    1. Always check that a corresponding example file exists or was updated in the examples/ directory.
    2. If any file under src/com/intellij/advancedExpressionFolding/folded/ was changed, you must verify:
        • a matching example file exists in examples/
        • the folded output still matches the example after running tests
    3. As a safety net, a post-implementation check should be run manually or as a pre-commit hook:

```
./gradlew test && ./scripts/check-examples.sh
```

This script validates that for every modified file in folded/, there is a corresponding file in examples/.
If missing, the commit should be blocked until an example is added.

⸻
Hook: scripts/check-examples.sh
#!/usr/bin/env bash
set -euo pipefail

FOLDED_DIR="folded"
EXAMPLES_DIR="examples"

# Diff against staged files for pre commit
changed_cached="$(git diff --name-only --cached || true)"
folded_changed="$(echo "$changed_cached" | grep "^${FOLDED_DIR}/" || true)"

if [ -z "$folded_changed" ]; then
  exit 0
fi

example_changed="$(echo "$changed_cached" | grep "^${EXAMPLES_DIR}/" || true)"
if [ -z "$example_changed" ]; then
  echo "Error: files changed in ${FOLDED_DIR} but nothing changed in ${EXAMPLES_DIR}"
  echo "Changed folded:"
  echo "$folded_changed"
  exit 1
fi

missing=""
while IFS= read -r f; do
  b="$(basename "$f")"
  n="${b%.*}"
  if ! git ls-files -- "${EXAMPLES_DIR}/${n}"* >/dev/null 2>&1 \
     && ! ls "${EXAMPLES_DIR}/${n}"* >/dev/null 2>&1; then
    missing="${missing}\n${EXAMPLES_DIR}/${n}*"
  fi
done <<< "$folded_changed"

if [ -n "$missing" ]; then
  printf "Error: missing example files for:%b\n" "$missing"
  exit 1
fi
