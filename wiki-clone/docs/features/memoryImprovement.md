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
---

#### Descriptor reuse on large fixtures

- When `memoryImprovement` is enabled, the plugin materialises folding descriptors for files under `testData/` once and keeps them cached while the editor stays open. Subsequent expansions or caret moves reuse the cached tree instead of walking the PSI again.
- The cache is scoped per file and invalidated if the document changes, so huge golden files stay responsive without sacrificing correctness.
- With the option disabled every caret move or highlighting refresh rebuilds the descriptor list, which increases allocations and slows diff previews on very large fixtures.

This switch is therefore recommended whenever you regularly inspect the bundled regression files or maintain your own large `testData` suites.
