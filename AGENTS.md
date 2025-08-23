# AGENTS.md

## Language and Style
- Use Java 17 or Kotlin. Prefer idiomatic Kotlin for all new code and utilities.
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
