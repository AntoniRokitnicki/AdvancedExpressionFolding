# Contributing to Advanced Expression Folding

Thank you for investing time in improving the plugin. This guide covers the day-to-day workflows contributors need.

## Development environment
- Install **JDK 17**.
- Use the provided Gradle wrapper scripts to ensure a consistent toolchain (`./gradlew` on Unix-like systems, `gradlew.bat` on Windows).

## Build and verification workflow
- `./gradlew build` – compiles the codebase and runs the verification tasks bundled with the build.
- `./gradlew test` – executes the JUnit 5 test suite. Run this before opening a pull request.
- `./gradlew runIde` – launches an IntelliJ IDEA sandbox for manual testing of your changes.

## Project resources
- [Project wiki](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki) – feature guides, option explanations, and usage notes.
- [`docs/`](docs/) – generated reference material and design notes.
- [`CHANGELOG.md`](CHANGELOG.md) – release history for recent changes.
- [`examples/`](examples/) and [`folded/`](folded/) – sample inputs and expected folding outputs referenced by automated tests.

## Submitting changes
1. Fork the repository and create a feature branch.
2. Make your changes, keeping commits focused.
3. Ensure `./gradlew test` passes locally.
4. Open a pull request that describes the change and its motivation.
5. Respond to review feedback promptly and keep your branch up to date with `main`.

Happy folding!
