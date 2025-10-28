## memoryImprovement

* only for files in **testData** folder

![Diff view before folding a testData file](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/89ccca5b-92ce-47ad-b051-7eabb39f94c2)

=>

![Diff view after folding a testData file](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/assets/3055326/440c30a8-1088-4e6a-bf25-bde6627aa7af)

# Memory Improvement (State field: memoryImprovement)

### Memory Improvement
Caches folding descriptors for large test data files.

_No bundled example for this setting._
This option affects editor behaviour without a dedicated sample file.

Default: On
Controlled by: `memoryImprovement`
Related features: (none)

#### Descriptor reuse behaviour

When `memoryImprovement` is enabled the plugin records the fold descriptors it generated for any file loaded from the `testData/` tree. Subsequent refreshes of the editor reuse those descriptors instead of rebuilding every region, which keeps the diff view responsive even for the multi-thousand line fixtures used in regression tests. The feature has no effect on ordinary project sources—the cache key is the absolute test data path—so day-to-day editing stays untouched while the heavy fixtures benefit from reduced allocations and a quicker toggle between folded and unfolded states.

When the toggle is enabled the folding builder first tries to reuse the array of `FoldingDescriptor`s cached on the `PsiJavaFile`. If no cached value exists—or the document stamp has changed—the builder recomputes the descriptors and stores them back under `Keys.FULL_CACHE`, so subsequent passes on the same test fixture can skip the expensive traversal.【F:src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingBuilder.kt†L31-L67】【F:src/com/intellij/advancedExpressionFolding/processor/cache/CacheExt.kt†L10-L23】【F:src/com/intellij/advancedExpressionFolding/processor/cache/Keys.kt†L29-L45】
