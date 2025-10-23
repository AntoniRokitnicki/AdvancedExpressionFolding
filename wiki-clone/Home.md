# Advanced Expression Folding Options

## getSetExpressionsCollapse
### Getters and setters as properties
Collapses getter and setter expressions into property-like syntax.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/GetterSetterTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/GetterSetterTestData-folded.java)

## varExpressionsCollapse
### Variable declarations (var/val)
Collapses variable declarations into more concise forms.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/VarTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/VarTestData-folded.java)

## compactControlFlowSyntaxCollapse
### Compact control flow condition syntax (Golang ifs)
Applies a more compact syntax for control flow conditions, similar to Golang's if statements.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/CompactControlFlowTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/CompactControlFlowTestData-folded.java)

## getExpressionsCollapse
### List.get, List.set, Map.get and Map.put expressions, array and list literals
Simplifies list and map access and modification expressions, as well as array and list literals.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/GetSetPutTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/GetSetPutTestData-folded.java)

## concatenationExpressionsCollapse
### StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions
Collapses various string and collection operations into more readable forms.
- [StringBuilder Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/StringBuilderTestData.java)
- [StringBuilder Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/StringBuilderTestData-folded.java)
- [Interpolated String Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/InterpolatedStringTestData.java)
- [Interpolated String Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/InterpolatedStringTestData-folded.java)
- [Append Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/AppendSetterInterpolatedStringTestData.java)
- [Append Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/AppendSetterInterpolatedStringTestData-folded.java)
- [Concatenation Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ConcatenationTestData.java)
- [Concatenation Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ConcatenationTestData-folded.java)

## slicingExpressionsCollapse
### List.subList and String.substring expressions
Simplifies list and string slicing operations.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/SliceTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/SliceTestData-folded.java)

## comparingExpressionsCollapse
### Object.equals and Comparable.compareTo expressions
Collapses equality and comparison operations into more readable forms.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/EqualsCompareTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/EqualsCompareTestData-folded.java)

## comparingLocalDatesCollapse
### Java.time isBefore/isAfter expressions
Simplifies date comparison operations.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/LocalDateTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/LocalDateTestData-folded.java)

## localDateLiteralCollapse
### LocalDate.of literals (e.g. 2018-02-12)
Collapses LocalDate creation into a more readable format.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/LocalDateLiteralTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/LocalDateLiteralTestData-folded.java)

## localDateLiteralPostfixCollapse
### Postfix LocalDate literals (e.g. 2018Y-02M-12D)
Allows for a postfix notation for LocalDate literals.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/LocalDateLiteralPostfixTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/LocalDateLiteralPostfixTestData-folded.java)

## castExpressionsCollapse
### Type cast expressions
Simplifies type casting operations.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/TypeCastTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/TypeCastTestData-folded.java)

## rangeExpressionsCollapse
### For loops, range expressions
Collapses for loops and range expressions into more concise forms.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ForRangeTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ForRangeTestData-folded.java)

## checkExpressionsCollapse
### Null-safe calls
Simplifies null-safe call operations.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ElvisTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ElvisTestData-folded.java)

## ifNullSafe
### Extended null-safe ifs
Provides extended null-safe if operations.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/IfNullSafeData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/IfNullSafeData-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/ifNullSafe)

## kotlinQuickReturn
### Kotlin quick return
Implements a quick return syntax similar to Kotlin.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/LetReturnIt.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/LetReturnIt-folded.java)

## assertsCollapse
### Asserts
Simplifies assert statements.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/AssertTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/AssertTestData-folded.java)

## optional
### Display optional as Kotlin null-safe
Represents Java Optional types using Kotlin-style null-safe syntax.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/OptionalTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/OptionalTestData-folded.java)

## streamSpread
### Display stream operations as Groovy's spread operator
Represents Java stream operations using Groovy-style spread operator syntax.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/SpreadTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/SpreadTestData-folded.java)

## lombok
### Display Java bean as Lombok
Represents Java beans using Lombok-style annotations.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/LombokTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/LombokTestData-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/lombok)

## logFolding
### Log folding
Applies folding to log statements.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/LogBrackets.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/LogBrackets-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/logFolding)

## logFoldingTextBlocks
### Log folding for Java text blocks
Extends log statement folding to Java text blocks so multi-line log messages collapse consistently with single-line strings.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/LogFoldingTextBlocksTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/LogFoldingTextBlocksTestData-folded.java)

## fieldShift
### Field initialization and builder patterns
Transforms field initialization and builder patterns into more concise syntax.
- [Builder Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/FieldShiftBuilder.java)
- [Builder Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/FieldShiftBuilder-folded.java)
- [Fields Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/FieldShiftFields.java)
- [Fields Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/FieldShiftFields-folded.java)
- [Setters Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/FieldShiftSetters.java)
- [Setters Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/FieldShiftSetters-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/fieldShift)

## destructuring
### Destructuring assignment for array & list
Implements destructuring assignments for arrays and lists.
- [Array Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/DestructuringAssignmentArrayTestData.java)
- [Array Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/DestructuringAssignmentArrayTestData-folded.java)
- [List Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/DestructuringAssignmentListTestData.java)
- [List Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/DestructuringAssignmentListTestData-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/destructuring)

## println
### Simplify System.out.println to println
Simplifies System.out.println calls to just println.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/PrintlnTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/PrintlnTestData-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/println)

## controlFlowSingleStatementCodeBlockCollapse
### Control flow single-statement code block braces (read-only files)
Simplifies single-statement code blocks in control flow statements for read-only files.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ControlFlowSingleStatementTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ControlFlowSingleStatementTestData-folded.java)

## controlFlowMultiStatementCodeBlockCollapse
### Control flow multi-statement code block braces (read-only files, deprecated)
Simplifies multi-statement code blocks in control flow statements for read-only files (deprecated).
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ControlFlowMultiStatementTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ControlFlowMultiStatementTestData-folded.java)

## semicolonsCollapse
### Semicolons (read-only files)
Removes semicolons in read-only files.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/SemicolonTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/SemicolonTestData-folded.java)

## const
### Simplify static final fields into const declarations
Converts eligible `static final` fields into concise Kotlin-style `const` (or `econst` for enum constants) declarations and hides redundant type information where possible.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ConstTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ConstTestData-folded.java)

## nullable
### Collapse nullability annotations to Kotlin suffixes
Replaces `@NotNull`/`@Nonnull` and `@Nullable` annotations with the Kotlin-style `!!` and `?` suffixes and folds matching `Preconditions.checkNotNull` patterns into readable guard clauses.
- [Annotations Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/NullableAnnotationTestData.java)
- [Annotations Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/NullableAnnotationTestData-folded.java)
- [checkNotNull Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/NullableAnnotationCheckNotNullTestData.java)
- [checkNotNull Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/NullableAnnotationCheckNotNullTestData-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/nullable)

## finalRemoval
### Remove the 'final' modifier from all elements except fields
Removes the 'final' modifier from all elements except fields.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/FinalRemovalTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/FinalRemovalTestData-folded.java)

## finalEmoji
### Replace the 'final' modifier with 🔒
Replaces the 'final' modifier with a lock emoji.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/FinalEmojiTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/FinalEmojiTestData-folded.java)

## lombokDirtyOff
### Don't fold lombok dirty getters/setters
Prevents folding of Lombok-generated getters and setters that have been modified.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/LombokDirtyOffTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/LombokDirtyOffTestData-folded.java)

## lombokPatternOff
### Pattern to turn off Lombok folding
Prevents all Lombok folding for classes matching the pattern
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/LombokPatternOffTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/LombokPatternOffTestData-folded.java)

## expressionFunc
### Single-Expression Function
Simplifies single-expression functions.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ExpressionFuncTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ExpressionFuncTestData-folded.java)

## dynamic
### Dynamic names for methods based on $user.home/dynamic-ajf2.toml
Applies dynamic naming to methods based on a configuration file.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/DynamicTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/DynamicTestData-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/dynamic)

## arithmeticExpressions
### BigDecimal, BigInteger and Math
Simplifies operations involving BigDecimal, BigInteger, and Math functions.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ArithmeticExpressionsTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ArithmeticExpressionsTestData-folded.java)

## emojify
### Emojify code
Replaces certain code elements with emojis.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/EmojifyTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/EmojifyTestData-folded.java)

## interfaceExtensionProperties
### Converts traditional getter and setter methods in interfaces into extension properties
Applies conversions to interface extension properties.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/InterfaceExtensionPropertiesTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/InterfaceExtensionPropertiesTestData-folded.java)

## patternMatchingInstanceof
### Pattern Matching for `instanceof` (JEP 394)
Applies pattern matching to `instanceof` checks for more concise and readable code.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/PatternMatchingInstanceofTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/PatternMatchingInstanceofTestData-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/patternMatchingInstanceof)

## summaryParentOverride
### Displays a folded summary of overridden methods from parent classes and interfaces
Provides a concise view of inherited method overrides, reducing redundant code duplication.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/SummaryParentOverrideTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/SummaryParentOverrideTestData-folded.java)

## constructorReferenceNotation
### Constructor reference notation `::new` and compact field initialization
Simplifies constructor references and inline field initialization.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ConstructorReferenceNotationTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ConstructorReferenceNotationTestData-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/constructorReferenceNotation)

## methodDefaultParameters
### Default parameter values inline for overloaded method
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/MethodDefaultParametersTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/MethodDefaultParametersTestData-folded.java)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/methodDefaultParameters)

## pseudoAnnotations
### Pseudo-annotations for main method generation
Provides pseudo-annotations like @Main and @NotFullyImplemented that automatically generate boilerplate for testing and prototyping.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/PseudoAnnotationsMainTestData.java)
- [Test](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/test/com/intellij/advancedExpressionFolding/MainAnnotationCompletionContributorTest.kt)
- [Documentation](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki/docs/features/pseudoAnnotations)

## overrideHide
### Hide @Override annotations
Hides @Override annotations to reduce visual clutter and focus on method implementation.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/OverrideHideTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/OverrideHideTestData-folded.java)

## suppressWarningsHide
### Hide @SuppressWarnings annotations on methods
Hides @SuppressWarnings annotations on methods to reduce visual clutter. Currently implemented only for methods.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/SuppressWarningsHideTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/SuppressWarningsHideTestData-folded.java)

## experimental
### Experimental features
Various experimental features for advanced code folding.
- [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/ExperimentalTestData.java)
- [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/ExperimentalTestData-folded.java)
