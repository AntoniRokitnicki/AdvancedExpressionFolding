<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Advanced Java Folding IntelliJ Plugin Changelog

## [Unreleased]

## [5.0.0] - 2025-10-17

### Added

- [pseudo-annotations] Introduce toggleable **@Loggable** pseudo-annotation with control-flow analysis that logs method entry and all exits, parameters, return values, and exceptions for methods or entire classes. Logging statements are automatically inserted and removed without modifying user code permanently. See [#441](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/441), [#429](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/429), [#431](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/431), [#363](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/363).
- [pseudo-annotations] Introduce toggleable **@TracingLoggable** pseudo-annotation with control-flow analysis that uses XDebugger tracing breakpoints for expression logging, adds logging at method entry and all exits, supports grouped breakpoints by annotation and file, and provides full class-level toggling and cleanup logic. See [#447](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/447), [#448](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/448).
- [methodDefaultParameters] Skip folding of default parameters during debugging sessions to prevent interference with breakpoints. See [#424](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/424).
- [log folding] Support variable folding inside Java text blocks, add `logFoldingTextBlocks` setting, and enable text block folding by default. See [#338](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/338), [#371](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/371).
- [settings-ui] Add live validation for “Regex to disable Lombok folding” and warn instead of throwing when downloading examples without an open project. Invalid patterns now display inline errors and settings helpers are hardened with a dedicated notification group. See [#379](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/379), [#446](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/446).
- Add compatibility with IntelliJ IDEA 2025.3 EAP (253.20558.43)

### Changed

- Internal refactoring and performance optimizations including faster helper lookups and shared extension utilities. See [#416](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/416), [#402](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/402), [#401](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/401), [#398](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/398).
- Complete Java→Kotlin migration of the entire codebase. All remaining Java sources were converted to Kotlin, improving null-safety, readability, and modern language integration across the plugin. See [#394](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/394), [#397](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/397).

## [4.2.0] - 2025-09-20

- [Add setting to prevent collapsing Java text blocks in log folding](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/338)
- [Bugfix/log folding strings no params](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/341)

## [4.0.0] - 2025-07-15

- [[pseudo-annotations] @Main - method-level annotation that generates a main method invoking the annotated method.](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/PseudoAnnotations)
- [[fieldshift] Preconditions.checkNotNull now supported](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/FieldShift#preconditionschecknotnull)

## [3.8.0] - 2025-07-07

- make compatible with 252 EAP
- add button to apply folded text color based on current theme (blue/navy) in settings

## [3.7.0] - 2025-06-09

- [expressionFunc] methods returning literals are now more compact
- [summaryParentOverride] Improvement of summary parent override when no override exists
- [lombok] support @Setter without "this."
- [suppressWarningsHide] hides @SuppressWarnings annotation at method level
- [overrideHide] hides @Override annotation
- [lombok] turn off showing comment for private @NoArgsConstructor

## [3.6.0] - 2025-05-11

- [experimental] Add exception class to @SneakyThrows annotation
- fix [expressionFunc] issue when combining with [interfaceExtensionProperties]
- [experimental] multi-line @SneakyThrows

## [3.5.0] - 2025-04-27

- performance improvements
- [lombok] support more types of dirty getters: wrapper, lazy and dirtyNoReference
- [lombok] don't show index on @constructor for class with single constructor

## [3.4.1] - 2025-04-15

- remove internal code run before plugin updates to remove warnings

## [3.4.0] - 2025-04-14

- [lombokPatternOff] Prevents all Lombok folding for classes matching the regex pattern
- [experimental] @SneakyThrows for single statement
- [InterfaceExtensionPropertiesTestData] Group added per method

## [3.3.0] - 2025-03-31

- [fieldshift] defensive copy support

## [3.2.0] - 2025-03-17

- New settings with downloadable examples and documentation

## [3.1.0] - 2025-03-16

- [methodDefaultParameters] Method default parameter using an overloaded method
- [methodDefaultParameters] Menu option: Code -> Advanced Folding: Searches -> Find Methods with Default Parameters

## [3.0.2] - 2025-03-09

### Added

- [dynamic] support for static methods and methods without qualifier

## [3.0.1] - 2025-03-04

- make compatible with 251.* EAP
- [constructorReferenceNotation] Constructor reference notation ::new and compact field initialization
- [logFolding] - Support log folding for {} and %s-like placeholders in most formatting scenarios
- [patternMatchingInstanceof] - Pattern Matching for instanceof (JEP 394)

## [3.0.0] - 2025-03-03

### Added

- [constructorReferenceNotation] Constructor reference notation ::new and compact field initialization
- [logFolding] - Support log folding for {} and %s-like placeholders in most formatting scenarios
- [patternMatchingInstanceof] - Pattern Matching for instanceof (JEP 394)

## [2.0.0] - 2024-09-15

### Added

- [lombok] - @Builder - marker annotation when class has inner class with name *Builder
- [lombok] - @Value and @LightValue(without @EqualsAndHashCode)
- [lombok] - @NoArgsConstructor, @NoArgsConstructor(protected), etc.
- [lombok] - @AllArgsConstructor, @AllArgsConstructor(protected), etc.
- [lombok] - @RequiredArgsConstructor, @RequiredArgsConstructor(protected), etc.
- [lombok] - @Log and @Log(logger) - present for every Logger field
- [expressionFunc] - Kotlin's Single-expression functions for with single statement body with size < 145 characters. {} used instead of =, because of default method folding.
- [dynamic] - dynamic names for methods based on file $HOME/dynamic-ajf2.toml works only for methods with qualifier
- [const] - show modifiers for fields and hide types for enums(not statically imported) and factory methods
- [lombok] - @Constructor(nr) on field level for additional constructors

### Fixed

- [cache] - sometimes caching goes wrong when IDE throws ProcessCanceledException and friends

## [1.0.52] - 2024-05-18

@Setter(dirty) folded only on field level

## [1.0.51] - 2024-05-16

@Getter(dirty) for Lombok getters that do something other than getting the value of their field, and a new option to prevent folding those getters

## [1.0.50] - 2024-05-14

clear cache on editor close and make experimental ::new work for all fields

## [1.0.49] - 2024-05-13

Implemented memory improvement feature bypassing CachedValuesManager and more methods for get and put folding

## [1.0.48] - 2024-05-12

New options: Boolean for simplifying public static final to const, simplifying @NotNull to Type!! and @Nullable to Type?, removing the 'final' modifier from all elements except fields, replacing the 'final' modifier with 🔒, and allowing to opt in to experimental features

## [1.0.47] - 2024-05-09

Experimental memory improvements - disabled by default

## [1.0.46] - 2024-05-08

checkNotNull as !!, also folded to method argument as !!! and make before and after work on every type

## [1.0.45] - 2024-05-07

fold static final fields as const

## [1.0.44] - 2024-05-05

Support @Nullable and @NotNull annotations on getter and setter for field

## [1.0.43] - 2024-05-05

@Nullable, @NotNull, @Nonnull annotations for fields, method parameters, and method return type - edge cases fixed

## [1.0.42] - 2024-05-05

@Nullable, @NotNull, @Nonnull annotations for fields, method parameters, and method return type

## [1.0.41] - 2024-05-05

@Nullable and @NotNull annotations for fields, method parameters, and method return type

## [1.0.40] - 2024-05-02

reuse folding group for lombok

## [1.0.39] - 2024-05-01

fix for records

## [1.0.38] - 2024-05-01

more lombok annotations - @Data, @EqualsAndHashCode, @ToString

## [1.0.37] - 2024-04-28

improve memory consumption and extend support for java time folding

## [1.0.36] - 2024-04-14

Open example files specified in the settings within the current project

## [1.0.35] - 2024-04-08

Simplify System.out.println to println

## [1.0.34] - 2024-04-02

Enhance settings with example documentation links

## [1.0.33] - 2024-03-27

- Destructuring assignment for list

## [1.0.32] - 2024-03-24

- multi-line folding globally fixed
- destructuring arrays fully supported

## [1.0.31] - 2024-03-18

Destructuring assignment for array - preview

## [1.0.30] - 2024-03-14

Windows-compatible keyboard shortcuts for IntelliJ and Android Studio

## [1.0.29] - 2024-03-13

New version of fieldShift for better compatibility

## [1.0.28] - 2024-03-10

Enhanced logger interpolation and added support for extra parameters

## [1.0.27] - 2024-03-07

new action to turn off folding

## [1.0.26] - 2024-03-05

Folding of testData in diff

## [1.0.25] - 2024-03-04

FieldShift for assignments

## [1.0.24] - 2024-02-29

Resolved folding inconsistency in Collectors.joining

## [1.0.23] - 2024-02-28

Enable Marker support in log folding

## [1.0.22] - 2024-02-27

Improved handling of multi-line log messages

## [1.0.21] - 2024-02-25

- log folding
- improve null‐safe ifs to support negation

## [1.0.20] - 2024-02-22

- Extended null‐safe ifs

## [1.0.19] - 2024-02-12

- let return and elvis return support the returned variable

## [1.0.18] - 2024-02-11

- Kotlin quick return - ?.let { return it } and ?: return null

## [1.0.17] - 2024-02-07

- Field shift for setters

## [1.0.16] - 2024-02-06

- Bug in stream folding fixed

## [1.0.15] - 2024-02-04

### Added

- Display mapping of field with same name as << for builders

## [1.0.14] - 2024-02-01

### Added

- Different name for the beta plugin
- Support of EAP

## [1.0.13]

### Added

- Publishing by script to Marketplace

## [1.0.12]

### Added

- Publishing by script to GitHub

## [1.0.11]

### Added

- Published to IntelliJ Marketplace

## [1.0.6]

### Added

- Fold Optional.ofNullable, Optional.of, Optional.orElseThrow & Optional.flatMap
- Spread operator implementation for streams

## [1.0.5]

### Added

- Display Optional as Kotlin Null-Safe
- Display Optional as Kotlin Null-Safe - orElseGet
- Fix memory leak in AdvancedExpressionFoldingHighlightingComponent.highlighters

## [1.0.4]

### Added

- Fix NPEs when calling isReferenceTo with r.resolve() that might return null
- Better support for compareTo folding
- Improve getter folding

## [1.0.3]

### Fixed

- update site

## [1.0.2]

### Fixed

- update site

## [1.0.1]

### Added

- java.time.* isBefore/isAfter folding and LocalDate literals
- Optimizations

## [1.0.0]

### Fixed

- project migrated to IntelliJ template plugin.

## [0.0.1]

[Unreleased]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v5.0.0...HEAD
[5.0.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v4.2.0...v5.0.0
[4.2.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v4.0.0...v4.2.0
[4.1.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v4.0.0...v4.1.0
[4.0.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.8.0...v4.0.0
[3.8.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.7.0...v3.8.0
[3.7.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.6.0...v3.7.0
[3.6.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.5.0...v3.6.0
[3.5.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.4.1...v3.5.0
[3.4.1]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.4.0...v3.4.1
[3.4.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.3.0...v3.4.0
[3.3.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.2.0...v3.3.0
[3.2.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.1.0...v3.2.0
[3.1.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.0.2...v3.1.0
[3.0.2]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.0.1...v3.0.2
[3.0.1]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.0.0...v3.0.1
[3.0.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v2.0.0...v3.0.0
[2.0.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.52...v2.0.0
[1.0.52]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.51...v1.0.52
[1.0.51]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.50...v1.0.51
[1.0.50]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.49...v1.0.50
[1.0.49]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.48...v1.0.49
[1.0.48]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.47...v1.0.48
[1.0.47]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.46...v1.0.47
[1.0.46]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.45...v1.0.46
[1.0.45]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.44...v1.0.45
[1.0.44]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.43...v1.0.44
[1.0.43]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.42...v1.0.43
[1.0.42]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.41...v1.0.42
[1.0.41]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.40...v1.0.41
[1.0.40]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.39...v1.0.40
[1.0.39]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.38...v1.0.39
[1.0.38]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.37...v1.0.38
[1.0.37]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.36...v1.0.37
[1.0.36]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.35...v1.0.36
[1.0.35]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.34...v1.0.35
[1.0.34]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.33...v1.0.34
[1.0.33]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.32...v1.0.33
[1.0.32]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.31...v1.0.32
[1.0.31]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.30...v1.0.31
[1.0.30]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.29...v1.0.30
[1.0.29]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.28...v1.0.29
[1.0.28]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.27...v1.0.28
[1.0.27]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.26...v1.0.27
[1.0.26]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.25...v1.0.26
[1.0.25]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.24...v1.0.25
[1.0.24]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.23...v1.0.24
[1.0.23]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.22...v1.0.23
[1.0.22]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.21...v1.0.22
[1.0.21]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.20...v1.0.21
[1.0.20]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.19...v1.0.20
[1.0.19]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.18...v1.0.19
[1.0.18]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.17...v1.0.18
[1.0.17]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.16...v1.0.17
[1.0.16]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.15...v1.0.16
[1.0.15]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.14...v1.0.15
[1.0.14]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.13...v1.0.14
[1.0.13]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.12...v1.0.13
[1.0.12]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.11...v1.0.12
[1.0.11]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.6...v1.0.11
[1.0.6]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.5...v1.0.6
[1.0.5]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.4...v1.0.5
[1.0.4]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.3...v1.0.4
[1.0.3]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.2...v1.0.3
[1.0.2]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v1.0.0...v1.0.1
[1.0.0]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v0.0.1...v1.0.0
[0.0.1]: https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/commits/v0.0.1
