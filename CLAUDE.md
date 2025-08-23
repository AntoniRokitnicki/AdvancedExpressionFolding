# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Advanced Expression Folding 2 is an IntelliJ IDEA plugin that extends IDE folding features to emulate modern language constructs in Java code. The plugin transforms verbose Java patterns into more concise, readable forms through custom folding rules.

## Build Commands

```bash
# Build the plugin
./gradlew build

# Run tests  
./gradlew test

# Run IDE with plugin for testing
./gradlew runIde
```

## Architecture

### Core Components

**AdvancedExpressionFoldingBuilder.kt** - Main folding builder that processes PSI elements and creates folding descriptors. Implements caching and performance optimizations.

**AdvancedExpressionFoldingSettings.kt** - Persistent settings storage and configuration management using IntelliJ's state component system.

**Expression System** (`src/com/intellij/advancedExpressionFolding/expression/`) - Pluggable expression transformations:
- Abstract base classes for different expression types
- Concrete implementations for specific Java patterns (Optional, Stream, Math operations)
- Custom expressions in `expression/custom/` for advanced folding patterns

**Extension System** (`src/com/intellij/advancedExpressionFolding/extension/`) - Modular extensions for different code analysis:
- Method call analysis (`methodcall/`)
- Lombok emulation (`lombok/`)
- Builder pattern support (`builder/`)
- Logging framework integration (`logger/`)

### Plugin Extension Points

The plugin defines two key extension points:
- `methodCallFolding` - For registering method call transformations
- `expressionBuilder` - For registering PSI element processors

All extensions are dynamically registered in `plugin.xml` and loaded through the IntelliJ platform.

## Development Workflow

### Test Data Structure

- `testData/` - Input Java files for testing
- `folded/` - Expected output showing folded results
- `examples/data/` - Example files demonstrating features

### Test Classes

- `BaseTest.kt` - Base class for all tests with common setup
- `FoldingTest.kt` - Main folding functionality tests
- `IntegrationTest.kt` - End-to-end integration tests
- `PerformanceTest.kt` - Performance benchmarking

### Key Settings

Settings are managed through `AdvancedExpressionFoldingSettings` with categories:
- Global folding on/off
- Individual feature toggles (Lombok, Optional, Stream, etc.)
- Performance optimizations (memory improvement)

### Common File Patterns

- Java folding builders extend `BuildExpression` interface
- Method call folders extend `AbstractMethodCall`
- Custom expressions inherit from expression base classes
- Settings use Kotlin reflection for dynamic property access

## Testing
- Always run ./gradlew test after making code changes.
- Never run tests before applying the changes.
- If tests fail, fix the code until all tests pass.
- Do not run tests if only documentation files (e.g. .md) are modified.

## Code Quality
- Prefer readability over micro-optimizations.
- Keep examples/data/ up to date when adding new folding features.
- Use idiomatic Kotlin when writing new code.

## Platform Compatibility

- Supports IntelliJ IDEA 2023.3+ (build 233)
- Java 17 runtime required
- Kotlin 2.1+ for development
- Gradle 8.14.2 build system
