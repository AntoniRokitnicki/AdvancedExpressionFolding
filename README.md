# Advanced Expression Folding 2 (Fork)​

<p><a href="https://plugins.jetbrains.com/plugin/23659-advanced-java-folding-2-fork-">
    <img src="https://yiiguxing.github.io/TranslationPlugin/img/ext/installation_button.svg" height="52" alt="Get from Marketplace" title="Get from Marketplace">
</a></p>



[![Build](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/build.yml/badge.svg)](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/build.yml)
[![Release](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/release.yml/badge.svg)](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/release.yml)
[![Canary Release](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/release-canary.yml/badge.svg)](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/release-canary.yml)
[![Snyk](https://snyk.io/test/github/antonirokitnicki/AdvancedExpressionFolding/badge.svg)](https://app.snyk.io/org/antonirokitnicki/project/cb199e96-f9b6-471e-a7f2-befcd26d62ca)
![GitHub License](https://img.shields.io/github/license/AntoniRokitnicki/AdvancedExpressionFolding)

[![Java](https://img.shields.io/badge/java-17-blue.svg)](https://plugins.jetbrains.com/docs/intellij/setting-up-theme-environment.html#add-jdk-and-intellij-platform-plugin-sdk)
[![Kotlin](https://img.shields.io/badge/dynamic/toml?url=https%3A%2F%2Fraw.githubusercontent.com%2FAntoniRokitnicki%2FAdvancedExpressionFolding%2Fmain%2Fgradle%2Flibs.versions.toml&query=%24.versions.kotlin&label=kotlin)](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib)
[![Gradle](https://img.shields.io/badge/gradle-8.14.2-blue.svg)](https://gradle.org/releases/)

[![Junit](https://img.shields.io/badge/dynamic/toml?url=https%3A%2F%2Fraw.githubusercontent.com%2FAntoniRokitnicki%2FAdvancedExpressionFolding%2Fmain%2Fgradle%2Flibs.versions.toml&query=%24.versions.junit&label=junit)](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)
[![Jackson toml](https://img.shields.io/badge/dynamic/toml?url=https%3A%2F%2Fraw.githubusercontent.com%2FAntoniRokitnicki%2FAdvancedExpressionFolding%2Fmain%2Fgradle%2Flibs.versions.toml&query=%24.versions.jackson&label=jackson%20toml)](https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-toml)
[![Junit pioneer](https://img.shields.io/badge/dynamic/toml?url=https%3A%2F%2Fraw.githubusercontent.com%2FAntoniRokitnicki%2FAdvancedExpressionFolding%2Fmain%2Fgradle%2Flibs.versions.toml&query=%24.versions.pioneer&label=junit%20pioneer)](https://mvnrepository.com/artifact/org.junit-pioneer/junit-pioneer)
[![Kodein jxinject](https://img.shields.io/badge/dynamic/toml?url=https%3A%2F%2Fraw.githubusercontent.com%2FAntoniRokitnicki%2FAdvancedExpressionFolding%2Fmain%2Fgradle%2Flibs.versions.toml&query=%24.versions.kodein&label=kodein%20jxinject)](https://mvnrepository.com/artifact/org.kodein.di/kodein-di-conf-jvm)

[![Release Version](https://img.shields.io/github/v/release/AntoniRokitnicki/AdvancedExpressionFolding)](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/releases)
[![Marketplace Version](https://img.shields.io/jetbrains/plugin/v/com.github.advanced-java-folding2.svg)](https://plugins.jetbrains.com/plugin/23659-advanced-java-folding-2-fork-)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/com.github.advanced-java-folding2.svg)](https://plugins.jetbrains.com/plugin/23659-advanced-java-folding-2-fork-)
![GitHub Release Date](https://img.shields.io/github/release-date/AntoniRokitnicki/AdvancedExpressionFolding)
[![GitHub commits since latest release](https://img.shields.io/github/commits-since/AntoniRokitnicki/AdvancedExpressionFolding/latest)](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/compare/v3.8.0...main)
<!-- https://plugins.jetbrains.com/api/plugins/23659/updates?channel=&size=8 -->

![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/AntoniRokitnicki/AdvancedExpressionFolding)
![GitHub repo size](https://img.shields.io/github/repo-size/AntoniRokitnicki/AdvancedExpressionFolding)
![Lines of Code Badge](https://raw.githubusercontent.com/AntoniRokitnicki/AdvancedExpressionFolding/lines-of-code-badge/badge.svg)

<!-- Plugin description -->
<p>Modern JVM languages such as Kotlin, Groovy, Scala and some others offer many language features that let you
  write code in a more concise and expressive manner. These features include type inference, properties,
  interpolated strings, range and tuple literals, enhanced operators, closures, implicits, smart casts and many more.</p>

<p>This plugin extends the IDE’s folding features to emulate capabilities from these modern languages, helping
  reduce verbosity.</p>

<p>Fork of abandoned <a href="https://plugins.jetbrains.com/plugin/9320-advanced-java-folding">Advanced Java Folding</a></p>

<p>For more information, read the <a href="https://medium.com/@andrey_cheptsov/making-java-code-easier-to-read-without-changing-it-adeebd5c36de" target="_blank">blog post</a>.</p>

## Unreleased ##

## 4.2.0 ##
- [Add setting to prevent collapsing Java text blocks in log folding](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/338)
- [Bugfix/log folding strings no params](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/341)

## 4.0.0 ##

- [[pseudo-annotations] @Main - method-level annotation that generates a main method invoking the annotated method.](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/PseudoAnnotations)
- [[fieldshift] Preconditions.checkNotNull now supported](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/FieldShift#preconditionschecknotnull)

## 3.8.0 ##

- add button to apply folded text color based on current theme (blue/navy) in settings

## 3.7.0 ##

- [[expressionFunc] methods returning literals are now more compact](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ExpressionFuncTestData-folded.java)
- [[summaryParentOverride] Improvement of summary parent override when no override exists](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/ec60ae57361709b9e5ff3d05da29923e9aedfe44/folded/SummaryParentOverrideTestData-folded.java#L40)
- [[lombok] support @Setter without "this."](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/ec60ae57361709b9e5ff3d05da29923e9aedfe44/examples/data/LombokTestData.java#L598)
- [suppressWarningsHide] hides @SuppressWarnings annotation at method level
- [overrideHide] hides @Override annotation

## 3.6.0 ##

- [[experimental] Add exception class to @SneakyThrows and support multiline](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ExperimentalTestData-folded.java)

## 3.5.0 ##
- [[lombok] support more types of dirty getters: wrapper, lazy and dirtyNoReference](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/4ec74d4d7a40d22749e3e491baa604036f407bc4/folded/LombokTestData-folded.java#L168)

## 3.4.1 ##
- [Prevents all Lombok folding for classes matching the regex pattern](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#lombokpatternoff)
- [[experimental] - @SneakyThrows for single statement](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ExperimentalTestData-folded.java)

## 3.3.0 ##
- [FieldShift - defensive copy support](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/FieldShift#defensive-copy-support)

## 3.2.0 ##
- [New settings with downloadable examples and documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/New-settings)

## 3.1.0 ##
- [Method default parameters using an overloaded method](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/MethodDefaultParameters)

## 3.0.0 ##
- [Dynamic names for methods](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Dynamic-Folding)
- [Constructor reference notation ::new and compact field initialization](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/ConstructorReferenceNotation)
- [Improved log folding](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Log-brackets-folding)
- [Pattern Matching for `instanceof` (JEP 394)](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/PatternMatchingInstanceof)


## New features:
- **Lombok Emulator & Validator**: [`@Data`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#data), [`@Getter`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#getter), [`@Setter`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#setter), [`@ToString`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#tostring), [`@EqualsAndHashCode`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#equalsandhashcode), [`@NoArgsConstructor`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#noargsconstructor), [`@AllArgsConstructor`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#allargsconstructor), [`@RequiredArgsConstructor`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#requiredargsconstructor), [`@Builder`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#builder), [`@Value`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#value), [`@Log`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#log), [`@Equals`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#equals), [`@HashCode`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#hashcode), [`@Serial`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#serial), [`@Constructor`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#constructor), [`@LightValue`](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/lombok#lightvalue)
- [Extended java time folding](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Extended-java-time-folding)
- [**Logger Brackets Folding**: For Slf4j and other logging framework](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Log-brackets-folding)
- [Extended null-safe ifs](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Extended-null%E2%80%90safe-ifs)
- [Display Optional as Kotlin Null-Safe](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/22)
- [Groovy spread operator in streams](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/23)
- [Display mapping of field with same name as << for builders](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/44)
- [Display mapping of field with same name as << for setters](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/51)
- [FieldShift for assignments](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/FieldShift)
- [Actions for folding and unfolding with key-shortcuts](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Actions-for-folding-and-unfolding-with-key%E2%80%90shortcuts)
- [Kotlin quick return - ?.let { return it } and ?: return null](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/62)
- [Folding of testData in diff](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Folding-of-testData-in-diff)
- [Destructuring assignment for array & list](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Destructuring-assignment)
- [Simplify System.out.println to println](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Simplify-System.out.println-to-println)
- [@Nullable and @NotNull annotations](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/@Nullable-and-@NotNull-annotations)

<br />
<p>For more clarity, you may try to adjust your color scheme: go to <strong>Settings</strong> | <strong>Editor</strong> |
<strong>Colors &amp; Fonts</strong> | <strong>General</strong> | <strong>Text</strong>, select <strong>Folded text</strong>
uncheck the <strong>Background</strong> color, and change the
<strong>Foreground</strong> color to #000091 for the default scheme and #7CA0BB for Darcula.</p>

To disable certain types of folding, go to <strong>Settings</strong> | <strong>Editor</strong> |
<strong>General</strong> | <strong>Code Folding</strong>.
<!-- Plugin description end -->


> [!TIP]
> <a name="canary">To install the Canary version of the plugin:</a>
> 1. Open <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Manage Plugin Repositories</kbd>
> 2. Add the following URL: [https://plugins.jetbrains.com/plugins/list?channel=canary&pluginId=23659](https://plugins.jetbrains.com/plugins/list?channel=canary&pluginId=23659)
> 3. Go to the <kbd>Marketplace</kbd> tab
> 4. Search for <kbd>Advanced Expression Folding</kbd>



---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
