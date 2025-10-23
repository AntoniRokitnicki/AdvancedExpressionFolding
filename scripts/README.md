# Scripts

## folding-suite-sweeper.main.kts

`scripts/folding-suite-sweeper.main.kts` exhaustively sweeps the folding test suite to isolate problematic `FoldingTest` cases. For every non-empty subset of the known flaky JUnit 5 classes, the script:

1. Re-runs `./gradlew test` while excluding the subset via `-Djunit.jupiter.excludeClassNamePatterns` so the rest of the suite executes in order.
2. Immediately replays `FoldingTest` (`./gradlew test --tests com.intellij.advancedExpressionFolding.FoldingTest`).
3. Parses `build/test-results/test/TEST-com.intellij.advancedExpressionFolding.FoldingTest.xml` to report the first failing folding test method, if any, along with the Gradle exit codes.

Each sweep prints a summary line in the form:

```
candidate=<excluded classes joined with '+'> foldingTestFirstFailure=<method or 'none'> result=first=<exit code>,second=<exit code> stacktraceHeader="<first line of failure>"
```

### Usage

Run from the project root so the script can find `./gradlew` and the `build/` directory:

```bash
kotlinc -script scripts/folding-suite-sweeper.main.kts
```

The script enumerates 31 candidate subsets, so a complete sweep is lengthy. Use standard Gradle environment variables (e.g., `ORG_GRADLE_PROJECT_...`) if you need to tune JVM settings for stability.
