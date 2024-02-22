# Advanced Expression Folding 2 (Fork)​
[![Build](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/build.yml/badge.svg)](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/build.yml)
[![Release](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/release.yml/badge.svg)](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/release.yml)
[![Deploy static content to Pages](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/static.yml/badge.svg?branch=master)](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/actions/workflows/static.yml)

<a href="https://plugins.jetbrains.com/plugin/23659-advanced-java-folding-2-fork-">
    <img src="https://yiiguxing.github.io/TranslationPlugin/img/ext/installation_button.svg" height="52" alt="Get from Marketplace" title="Get from Marketplace">
</a>


<!-- Plugin description -->
<p>Modern JVM languages such as Kotlin, Groovy, Scala and some others offer many language features that let you
  write code in a more concise and expressive manner. These features include type inference, properties,
  interpolated string, range and tuple literals, enhanced operators, closures, implicits, smart casts and many more.</p>

<p>This plugin extends the IDE’s folding features to emulate some of these modern languages’ features helping
  fight verbosity.</p>

<p>Fork of abandoned <a href="https://plugins.jetbrains.com/plugin/9320-advanced-java-folding">Advanced Java Folding</a></p>

<p>For more information, read the <a href="https://medium.com/@andrey_cheptsov/making-java-code-easier-to-read-without-changing-it-adeebd5c36de" target="_blank">blog post</a>.</p>

<h4>New features:</h4>
<ul>
  <li><a href="https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/22">Display Optional as Kotlin Null-Safe</a></li>
  <li><a href="https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/23">Groovy spread operator in streams</a></li>
  <li><a href="https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/29">Lombok @Getter and @Setter on class level for POJO</a></li>
  <li><a href="https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/44">Display mapping of field with same name as << for builders</a></li>
  <li><a href="https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/51">Display mapping of field with same name as << for setters</a></li>
  <li><a href="https://github.com/cheptsov/AdvancedExpressionFolding/pull/132">java.time.* isBefore/isAfter folding and LocalDate literals</a></li>
  <li><a href="https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/25">Actions for folding and unfolding with key-shortcuts</a></li>
  <li><a href="https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/pull/62">Kotlin quick return - ?.let { return it } and ?: return null</a></li>
  <li><a href="https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/Extended-null%E2%80%90safe-ifs">Extended null-safe ifs</a></li>
</ul>
<br />
<p>For more clarity, you may try to adjust your color scheme: go to <strong>Settings</strong> | <strong>Editor</strong> |
<strong>Colors &amp; Fonts</strong> | <strong>General</strong> | <strong>Text</strong>, select <strong>Folded text</strong>
uncheck the <strong>Background</strong> color, and change the
<strong>Foreground</strong> color to #000091 for the default scheme and #7CA0BB for Darcula.</p>

To disable certain types of folding, go to <strong>Settings</strong> | <strong>Editor</strong> |
<strong>General</strong> | <strong>Code Folding</strong>.
<!-- Plugin description end -->


## Beta installation

- Using the Custom plugin repository:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Manager Plugin Repositories</kbd>

  - add
  - https://antonirokitnicki.github.io/AdvancedExpressionFolding/plugin_repository.xml
  - goto <kbd>Marketplace</kbd>
  - search for <kbd>Beta</kbd>

- Manually:

  Download the [latest release](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
