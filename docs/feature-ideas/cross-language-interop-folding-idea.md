# Feature Idea: Cross-Language Interop Folding

**Hook:** Hide boilerplate in Kotlin↔Java bridges and Groovy DSL wrappers.

**Why now?** Mixed-language codebases are common; interoperability glue is noisy.

**Lift-Cost Score:** H

**Starter spec:**
- Detect trivial delegate methods generated for Kotlin top-level functions.
- Fold Groovy builder DSL wrappers to concise calls.
- Provide language-specific toggles in settings.

