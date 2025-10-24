# Advanced Expression Folding

## Overview
Advanced Expression Folding streamlines Java code in JetBrains IDEs by collapsing repetitive patterns into concise, Kotlin-inspired syntax. The plugin recognises getters, builders, log statements, null-checks, and dozens of other constructs so that reviewers focus on intent rather than boilerplate. Use this page to understand what the extension delivers and how to enable only the pieces that help your team read code faster.

## Install and enable
1. Open **Settings / Preferences → Plugins**, search for **“Advanced Expression Folding”**, and install the plugin from JetBrains Marketplace.
2. Restart the IDE if prompted.
3. Navigate to **Settings / Preferences → Editor → General → Code Folding → Advanced Expression Folding**.
4. Flip **Global On** to enable the plugin and then toggle the feature sections that matter for your project.
5. Apply the changes and reopen any editors you want to refresh; folds update instantly once the settings dialog closes.

## How to read the tables
Every section below groups related toggles. Each row links to the detailed article, lists the default state shipped with the plugin, and shows where the option lives inside the settings dialog. All entries share the same base path (**Settings / Preferences → Editor → General → Code Folding → Advanced Expression Folding**); the “Section” column calls out the group header you will see inside the panel.

### Global controls
| Toggle | Default | Section |
| --- | --- | --- |
| [Global On](docs/features/globalOn.md) | On | Top of panel |

### Null safety & Kotlin idioms
| Toggle | Default | Section |
| --- | --- | --- |
| [Check expressions collapse](docs/features/checkExpressionsCollapse.md) | On | Null safety |
| [If null-safe](docs/features/ifNullSafe.md) | On | Null safety |
| [Nullable annotations](docs/features/nullable.md) | Off | Null safety |
| [Optional to Kotlin style](docs/features/optional.md) | On | Null safety |
| [Kotlin quick return](docs/features/kotlinQuickReturn.md) | On | Kotlin idioms |
| [Method default parameters](docs/features/methodDefaultParameters.md) | On | Kotlin idioms |
| [Single-expression functions](docs/features/expressionFunc.md) | On | Kotlin idioms |

### Collections, streams & builders
| Toggle | Default | Section |
| --- | --- | --- |
| [Getters and setters as properties](docs/features/getSetExpressionsCollapse.md) | On | Collections & builders |
| [Var/val style declarations](docs/features/varExpressionsCollapse.md) | On | Collections & builders |
| [List / Map access simplification](docs/features/getExpressionsCollapse.md) | On | Collections & builders |
| [String & collection concatenation](docs/features/concatenationExpressionsCollapse.md) | On | Collections & builders |
| [List.subList / String.substring](docs/features/slicingExpressionsCollapse.md) | On | Collections & builders |
| [Range and loop folding](docs/features/rangeExpressionsCollapse.md) | On | Collections & builders |
| [Stream spread operator](docs/features/streamSpread.md) | On | Collections & builders |
| [Field shift (builders & setters)](docs/features/fieldShift.md) | On | Collections & builders |
| [Destructuring assignments](docs/features/destructuring.md) | Off | Collections & builders |

### Control flow & readability
| Toggle | Default | Section |
| --- | --- | --- |
| [Compact control-flow syntax](docs/features/compactControlFlowSyntaxCollapse.md) | Off | Control flow |
| [Single-statement brace folding](docs/features/controlFlowSingleStatementCodeBlockCollapse.md) | Off | Control flow |
| [Multi-statement brace folding (deprecated)](docs/features/controlFlowMultiStatementCodeBlockCollapse.md) | Off | Control flow |
| [Pattern matching for `instanceof`](docs/features/patternMatchingInstanceof.md) | On | Control flow |
| [Constructor reference notation](docs/features/constructorReferenceNotation.md) | On | Control flow |
| [Asserts folding](docs/features/assertsCollapse.md) | On | Control flow |

### Logging & diagnostics
| Toggle | Default | Section |
| --- | --- | --- |
| [Log folding](docs/features/logFolding.md) | On | Logging |
| [Log folding for text blocks](docs/features/logFoldingTextBlocks.md) | Off | Logging |
| [Summary parent override](docs/features/summaryParentOverride.md) | Off | Diagnostics |

### Dates, numbers & comparisons
| Toggle | Default | Section |
| --- | --- | --- |
| [Equals / compareTo folding](docs/features/comparingExpressionsCollapse.md) | On | Dates & numbers |
| [LocalDate comparisons](docs/features/comparingLocalDatesCollapse.md) | On | Dates & numbers |
| [LocalDate.of literals](docs/features/localDateLiteralCollapse.md) | Off | Dates & numbers |
| [LocalDate postfix literals](docs/features/localDateLiteralPostfixCollapse.md) | Off | Dates & numbers |
| [BigDecimal / BigInteger expressions](docs/features/arithmeticExpressions.md) | Off | Dates & numbers |
| [Constant fields](docs/features/const.md) | On | Dates & numbers |

### Lombok & annotations
| Toggle | Default | Section |
| --- | --- | --- |
| [Lombok style beans](docs/features/lombok.md) | On | Lombok & annotations |
| [Skip dirty Lombok accessors](docs/features/lombokDirtyOff.md) | Off | Lombok & annotations |
| [Regex opt-out for Lombok folding](docs/features/lombokPatternOff.md) | Off | Lombok & annotations |
| [Pseudo-annotations](docs/features/pseudoAnnotations.md) | On | Lombok & annotations |
| [Interface extension properties](docs/features/interfaceExtensionProperties.md) | On | Lombok & annotations |
| [Hide `@Override`](docs/features/overrideHide.md) | On | Lombok & annotations |
| [Hide `@SuppressWarnings`](docs/features/suppressWarningsHide.md) | On | Lombok & annotations |
| [Remove `final` modifiers](docs/features/finalRemoval.md) | Off | Lombok & annotations |
| [Replace `final` with emoji](docs/features/finalEmoji.md) | Off | Lombok & annotations |

### UI polish & convenience
| Toggle | Default | Section |
| --- | --- | --- |
| [Simplify `System.out.println`](docs/features/println.md) | On | UI polish |
| [Collapse stray semicolons](docs/features/semicolonsCollapse.md) | Off | UI polish |
| [Emojify code elements](docs/features/emojify.md) | Off | UI polish |
| [Dynamic method aliases](docs/features/dynamic.md) | On | UI polish |

### Performance & experimentation
| Toggle | Default | Section |
| --- | --- | --- |
| [Memory improvements](docs/features/memoryImprovement.md) | On | Performance |
| [Experimental features](docs/features/experimental.md) | Off | Performance |

## Next steps
* Visit the [feature settings overview](docs/features/feature-settings-overview.md) for a guided tour of the configuration panel.
* Review the linked articles for before-and-after screenshots, caveats, and sample code for each toggle.
