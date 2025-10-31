# Scripts

## folding-suite-sweeper tool

`tools/folding-suite-sweeper` provides a JVM and GraalVM-native executable that exhaustively sweeps the folding test suite to isolate problematic `FoldingTest` cases. For every non-empty subset of the known flaky JUnit 5 classes, the tool:

1. Re-runs `./gradlew test` while excluding the subset via `-Djunit.jupiter.excludeClassNamePatterns` so the rest of the suite executes in order.
2. Immediately replays `FoldingTest` (`./gradlew test --tests com.intellij.advancedExpressionFolding.FoldingTest`).
3. Parses `build/test-results/test/TEST-com.intellij.advancedExpressionFolding.FoldingTest.xml` to report the first failing folding test method, if any, along with the Gradle exit codes.

Each sweep prints a summary line in the form:

```
candidate=<excluded classes joined with '+'> foldingTestFirstFailure=<method or 'none'> result=first=<exit code>,second=<exit code> stacktraceHeader="<first line of failure>"
```

### Usage

Run from the project root so the tool can find `./gradlew` and the `build/` directory. The JVM launcher is available via Gradle:

```bash
./gradlew :tools:folding-suite-sweeper:run
```

To build a standalone native executable with GraalVM, run:

```bash
./gradlew :tools:folding-suite-sweeper:nativeCompile
```

The resulting binary is written to `tools/folding-suite-sweeper/build/native/nativeCompile/folding-suite-sweeper`.

The tool enumerates 31 candidate subsets, so a complete sweep is lengthy. Use standard Gradle environment variables (e.g., `ORG_GRADLE_PROJECT_...`) if you need to tune JVM settings for stability.
