# Method-call Folding DSL Examples

You want a custom Kotlin DSL? Fine—here are the three spots starving for one, in descending order of pain:

1. **Method-call folding definitions**
   ```kotlin
   foldingDsl {
       methodCall("kotlin.text", "append", enableByDefault = true) {
           description = "Fold StringBuilder.append chains"

           qualifiers {
               receiver("java.lang.StringBuilder")
               receiver("kotlin.text.StringBuilder")
           }

           signature {
               parameter<String>("value")
           }

           fold {
               placeholder("…")
               display("append(\"$value\")")
               keepLineBreaks()
           }
       }
   }
   ```
   *Declares a folding block with qualifiers, signature, and folding output without re-implementing boilerplate `AbstractMethodCall` plumbing.*

2. **Chained qualifier handling**
   ```kotlin
   foldingDsl {
       chainedQualifier("kotlin.collections", "joinToString") {
           qualifierPolicy {
               collapseSingleQualifier()
               expandOnDifferentModule()
           }

           fold {
               placeholder("list.joinToString(…)")
           }
       }
   }
   ```
   *Expresses qualifier rules declaratively instead of juggling manual `onQualifier` overrides.*

3. **Result rendering**
   ```kotlin
   foldingDsl {
       renderer("appendInterpolation") {
           whenArgumentCount {
               exactly(1) {
                   placeholder("${'$'}{args[0]}")
               }
               atLeast(2) {
                   placeholder("${'$'}{args[0]}…")
               }
           }

           fallback {
               placeholder("append(…)")
           }
       }
   }
   ```
   *Replaces custom `onNthArgument` branches with a fluent declaration of output variants.*
