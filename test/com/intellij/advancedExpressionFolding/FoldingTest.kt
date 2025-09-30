package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import com.intellij.openapi.application.runInEdt
import com.intellij.platform.testFramework.core.FileComparisonFailedError
import org.junit.jupiter.api.Test
import org.junitpioneer.jupiter.Stopwatch
import org.opentest4j.TestAbortedException
import kotlin.reflect.KMutableProperty0

@Stopwatch
//TODO: maybe use @RetryingTest(maxAttempts = 3, suspendForMs = 100, onExceptions = <FindName>.class) when rarely IDE can't be start
open class FoldingTest : BaseTest() {

    class TooComplexException : TestAbortedException("TOO COMPLEX FOLDING")
    class FoldingChangedException : AssertionError()

    protected val state: State by lazy {
        getInstance().state
    }

    open fun assignState(vararg turnOnProperties: KMutableProperty0<Boolean>,) {
        getInstance().disableAll()
        turnOnProperties.forEach {
            it.set(true)
        }
    }

    open fun doFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider(),
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        try {
            super.doFoldingTest(null)
        } catch (_: FileComparisonFailedError) {
            throw FoldingChangedException()
        } catch (e: IllegalArgumentException) {
            if (e.message == "Comparison method violates its general contract!") {
                throw TooComplexException()
            }
        }
    }

    private fun doReadOnlyFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider()
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        runInEdt {
            super.doReadOnlyFoldingTest()
        }
    }

    /**
     * Folds verbose null checks into Kotlin Elvis syntax, e.g. `e == null ? "" : e` and
     * `e != null ? e.sayHello() : ""` collapse to `e ?: ""` and `e?.sayHello() ?: ""`.
     *
     * [data.ElvisTestData]
     */
    @Test
    open fun elvisTestData() {
        doFoldingTest(state::checkExpressionsCollapse)
    }

    /**
     * Replaces index-based iteration like `for (int i = 0; i < args.length; i++)` and
     * length comparisons with Kotlin-style range loops and membership checks such as
     * `for ((i, arg) : args)` and `if (args.length in (0, 2))`.
     *
     * [data.ForRangeTestData]
     */
    @Test
    open fun forRangeTestData() {
        doFoldingTest(state::rangeExpressionsCollapse)
    }

    /**
     * Collapses `StringBuilder` chains such as `sb.append(a).append(b)` and
     * character appends `sb.append(str.charAt(0))` into `+=` concatenations and
     * bracket character access.
     *
     * [data.StringBuilderTestData]
     */
    @Test
    open fun stringBuilderTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * Turns explicit concatenations like `"Hello, " + name` into string
     * interpolation `"Hello, $name"`, merging nested concatenations.
     *
     * [data.InterpolatedStringTestData]
     */
    @Test
    open fun interpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * Converts collection access like `list.set(i, v)`, `map.put(k, v)` and
     * `map.get(k)` into bracket syntax `list[i] = v`, `map[k] = v`, `map[k]`,
     * and rewrites factory methods into literal collections.
     *
     * [data.GetSetPutTestData]
     */
    @Test
    open fun getSetPutTestData() {
        doFoldingTest(state::getExpressionsCollapse)
    }

    /**
     * Rewrites `subList`, `substring` and array copy calls into slice notation
     * such as `list[1..3]` or `text[1:3]`.
     *
     * [data.SliceTestData]
     */
    @Test
    open fun sliceTestData() {
        doFoldingTest(state::slicingExpressionsCollapse)
    }

    /**
     * Folds chains like `obj.setName(obj.getName() + " " + suffix)` into
     * property assignments with interpolation `obj.name = "${obj.name} $suffix"`.
     *
     * [data.AppendSetterInterpolatedStringTestData]
     */
    @Test
    open fun appendSetterInterpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::getSetExpressionsCollapse)
    }

    /**
     * Replaces `equals`, `compareTo` and negated comparisons with symbolic
     * operators like `==`, `!=`, `>=`, `<=`, `>` and `<`.
     *
     * [data.EqualsCompareTestData]
     */
    @Test
    open fun equalsCompareTestData() {
        doFoldingTest(state::comparingExpressionsCollapse)
    }

    /**
     * Eliminates redundant casts after `instanceof` checks so that
     * `if (o instanceof String) ((String)o).trim()` becomes
     * `if (o is String) o.trim()`.
     *
     * [data.TypeCastTestData]
     */
    @Test
    open fun typeCastTestData() {
        doFoldingTest(state::castExpressionsCollapse)
    }

    /**
     * Replaces explicit types in local declarations and enhanced for-loops
     * with `val`/`var` type inference.
     *
     * [data.VarTestData]
     */
    @Test
    open fun varTestData() {
        doFoldingTest(state::varExpressionsCollapse)
    }

    /**
     * Collapses `obj.getName()`/`obj.setName(v)` method calls into
     * property-style access `obj.name` and assignment `obj.name = v`.
     *
     * [data.GetterSetterTestData]
     */
    @Test
    open fun getterSetterTestData() {
        doFoldingTest(state::getSetExpressionsCollapse)
    }

    /**
     * Removes braces and condenses `if`, `else`, `while`, and `for` blocks that
     * contain a single statement into one-line forms.
     *
     * [data.ControlFlowSingleStatementTestData]
     */
    @Test
    open fun controlFlowSingleStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowSingleStatementCodeBlockCollapse)
    }

    /**
     * Normalizes multi-statement control-flow structures into compact Kotlin
     * style while preserving braces and indentation.
     *
     * [data.ControlFlowMultiStatementTestData]
     */
    @Test
    open fun controlFlowMultiStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowMultiStatementCodeBlockCollapse)
    }

    /**
     * Transforms temporal comparisons like `d1.isBefore(d2)` and
     * `!d1.isAfter(d2)` into `<`, `>`, `<=`, and `>=` operators.
     *
     * [data.LocalDateTestData]
     */
    @Test
    open fun localDateTestData() {
        doReadOnlyFoldingTest(state::comparingLocalDatesCollapse)
    }

    /**
     * Verifies folding of `LocalDate` literal expressions.
     *
     * [data.LocalDateLiteralTestData]
     */
    @Test
    open fun localDateLiteralTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse)
    }

    /**
     * Ensures `LocalDate` literals with postfix notation fold properly.
     *
     * [data.LocalDateLiteralPostfixTestData]
     */
    @Test
    open fun localDateLiteralPostfixTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse, state::localDateLiteralPostfixCollapse)
    }

    /**
     * Tests compact control-flow syntax transformations.
     *
     * [data.CompactControlFlowTestData]
     */
    @Test
    open fun compactControlFlowTestData() {
        doFoldingTest(state::compactControlFlowSyntaxCollapse)
    }

    /**
     * Verifies removal of redundant semicolons.
     *
     * [data.SemicolonTestData]
     */
    @Test
    open fun semicolonTestData() {
        doReadOnlyFoldingTest(state::semicolonsCollapse)
    }

    /**
     * Converts defensive `if`/`throw` checks into concise `assert` statements,
     * optionally preserving custom messages.
     *
     * [data.AssertTestData]
     */
    @Test
    open fun assertTestData() {
        doReadOnlyFoldingTest(state::assertsCollapse)
    }

    /**
     * Simplifies complex string concatenations and collection mutations,
     * using `+=` for lists and spread operators for streams like `*.map`.
     *
     * [data.ConcatenationTestData]
     */
    @Test
    open fun concatenationTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * Replaces Java `Optional` chaining with Kotlin nullable operators
     * such as `?.`, `?:`, and non-null assertions `!!`.
     *
     * [data.OptionalTestData]
     */
    @Test
    open fun optionalTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * Collapses stream `map`/`flatMap` pipelines and array spreads into
     * concise spread syntax like `list*.prop` or `array**method`.
     *
     * [data.SpreadTestData]
     */
    @Test
    open fun spreadTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * Ensures every supported Lombok annotation folds to the generated
     * Kotlin-style construct. The test covers accessors (`@Getter`,
     * `@Setter`), builders (`@Builder`), string and equality helpers
     * (`@ToString`, `@EqualsAndHashCode`, `@Data`, `@Value`), logging
     * (`@Log`), and constructor shorthands (`@NoArgsConstructor`,
     * `@AllArgsConstructor`, `@RequiredArgsConstructor`).
     *
     * [data.LombokTestData]
     */
    @Test
    open fun lombokTestData() {
        doFoldingTest(state::lombok)
    }

    /**
     * Demonstrates that the Lombok annotations listed above collapse into
     * property access, builder invocations and compact constructors in a
     * typical class hierarchy.
     *
     * [data.LombokTestData]
     */
    @Test
    open fun lombokUsageTestData() {
        doFoldingTest(state::lombok)
    }

    /**
     * Uses the `<<` placeholder to copy builder fields directly into the
     * constructed object without repetitive setters.
     *
     * [data.FieldShiftBuilder]
     */
    @Test
    open fun fieldShiftBuilder() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * Converts sequences of `setX(value)` invocations into direct field
     * assignments using field shift semantics.
     *
     * [data.FieldShiftSetters]
     */
    @Test
    open fun fieldShiftSetters() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * Collapses verbose early-return idioms. For example:
     * `String v = getData(s); if (v != null) { return v; }` →
     * `val v = getData(s)?.let { return it }` and
     * `String v = getData(s); if (v == null) { return null; }` →
     * `val v = getData(s) ?: return null`.
     *
     * [data.LetReturnIt]
     */
    @Test
    open fun letReturnIt() {
        doFoldingTest(state::varExpressionsCollapse, state::kotlinQuickReturn)
    }

    /**
     * Turns nested null checks and boolean conditions into chained safe
     * navigation such as `a?.b?.c == true`.
     *
     * [data.IfNullSafeData]
     */
    @Test
    open fun ifNullSafeData() {
        doFoldingTest(state::checkExpressionsCollapse, state::getSetExpressionsCollapse, state::ifNullSafe)
    }

    /**
     * Rewrites logger placeholders like `logger.info("x={}", x)` into
     * interpolation `logger.info("x=$x")` and simplifies bracket handling.
     *
     * [data.LogBrackets]
     */
    @Test
    open fun logBrackets() {
        doFoldingTest(state::getSetExpressionsCollapse, state::logFolding)
    }
    /**
     * [data.LogFoldingTextBlocksTestData]
     */
    @Test
    fun logFoldingTextBlocksTestData() {
        doFoldingTest(state::getSetExpressionsCollapse, state::logFolding, state::logFoldingTextBlocks)
    }

    /**
     * Applies field shift when assigning directly to fields, e.g.
     * `other.name << name` moves values without intermediate setters.
     *
     * [data.FieldShiftFields]
     */
    @Test
    open fun fieldShiftFields() {
        doFoldingTest(state::getSetExpressionsCollapse, state::fieldShift)
    }

    /**
     * Converts manual array element extraction (`String first = arr[0];`) into
     * Kotlin-style destructuring `val (first, second) = arr`.
     *
     * [data.DestructuringAssignmentArrayTestData]
     */
    @Test
    open fun destructuringAssignmentArrayTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse, state::varExpressionsCollapse)
    }

    /**
     * Handles array destructuring when the declaration keyword is omitted,
     * relying on inference for the new variables.
     *
     * [data.DestructuringAssignmentArrayWithoutValTestData]
     */
    @Test
    open fun destructuringAssignmentArrayWithoutValTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse)
    }

    /**
     * Expands list destructuring such as `val first = list.get(0)` into
     * Kotlin's `val (first, second) = list` pattern.
     *
     * [data.DestructuringAssignmentListTestData]
     */
    @Test
    open fun destructuringAssignmentListTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse, state::varExpressionsCollapse)
    }

    /**
     * Handles list destructuring when the declaration keyword is omitted,
     * relying on type inference for the unpacked variables.
     *
     * [data.DestructuringAssignmentListWithoutValTestData]
     */
    @Test
    open fun destructuringAssignmentListWithoutValTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse)
    }

    /**
     * Shortens verbose `System.out.println` calls to simple `println` and
     * converts chained concatenations into interpolation.
     *
     * [data.PrintlnTestData]
     */
    @Test
    open fun printlnTestData() {
        doFoldingTest(state::println)
    }

    /**
     * Transforms `@Nullable`/`@NotNull` annotations into postfix markers
     * (`?` and `!!`) and leverages Lombok for generated accessors.
     *
     * [data.NullableAnnotationTestData]
     */
    @Test
    open fun nullableAnnotationTestData() {
        doFoldingTest(state::nullable, state::lombok)
    }

    /**
     * Rewrites `Preconditions.checkNotNull(arg)` into `arg!!` and marks the
     * variable as non-null in subsequent code.
     *
     * [data.NullableAnnotationCheckNotNullTestData]
     */
    @Test
    open fun nullableAnnotationCheckNotNullTestData() {
        doFoldingTest(state::nullable, state::getSetExpressionsCollapse)
    }
    /**
     * Same as above but also demonstrates field shift during assignment,
     * ensuring `target.name << checkNotNull(src.name)` collapses cleanly.
     *
     * [data.NullableAnnotationCheckNotNullFieldShiftTestData]
     */
    @Test
    open fun nullableAnnotationCheckNotNullFieldShiftTestData() {
        doFoldingTest(state::nullable, state::getSetExpressionsCollapse, state::fieldShift)
    }

    /**
     * Collapses `final static` fields initialized with constant expressions
     * into `const`/`econst` declarations with simplified modifiers.
     *
     * [data.ConstTestData]
     */
    @Test
    open fun constTestData() {
        doFoldingTest(state::const)
    }

    /**
     * Strips unnecessary `final` modifiers from variables and fields when
     * mutability is implied by context.
     *
     * [data.FinalRemovalTestData]
     */
    @Test
    open fun finalRemovalTestData() {
        doFoldingTest(state::finalRemoval)
    }

    /**
     * Replaces the `final` keyword with a 🔒 emoji to denote immutability
     * when the emojify option is enabled.
     *
     * [data.FinalEmojiTestData]
     */
    @Test
    open fun finalEmojiTestData() {
        @Suppress("DEPRECATION")
        doFoldingTest(state::finalEmoji)
    }

    /**
     * Confirms that all Lombok annotations (`@Getter`, `@Setter`, `@Builder`,
     * `@ToString`, `@EqualsAndHashCode`, `@Data`, `@Value`, `@Log`,
     * `@NoArgsConstructor`, `@AllArgsConstructor`, `@RequiredArgsConstructor`)
     * are folded even when dirty tracking is turned off via
     * `lombokDirtyOff`.
     *
     * [data.LombokDirtyOffTestData]
     */
    @Test
    open fun lombokDirtyOffTestData() {
        doFoldingTest(state::lombok, state::lombokDirtyOff)
    }

    /**
     * Converts single-expression methods from block bodies with `return` to
     * expression-bodied functions (`fun f() = expr`).
     *
     * [data.Expressionopen funcTestData]
     */
    @Test
    open fun expressionFuncTestData() {
        doFoldingTest(state::expressionFunc)
    }

    /**
     * Demonstrates dynamic method renaming through an external TOML
     * configuration (e.g., `staticMethod` → `changedStaticMethod`).
     *
     * [data.DynamicTestData]
     */
    @Test
    open fun dynamicTestData() {
        val dynamicProvider = object : IDynamicDataProvider {
            override fun parse(): List<DynamicMethodCall> {
                return parseToml(
                    """
normalMethod.method = 'normalMethod'
normalMethod.newName = 'changedNormalMethod'
staticMethod.method = 'staticMethod'
staticMethod.newName = 'changedStaticMethod'
                """.trimIndent()
                )
            }

        }
        doFoldingTest(state::dynamic, state::getSetExpressionsCollapse, dynamic = dynamicProvider)
    }

    /**
     * Simplifies `BigDecimal`/`BigInteger` constructions and operations into
     * plain numeric literals and operators such as `a + b` and `a.pow(2)` → `a²`.
     *
     * [data.ArithmeticExpressionsTestData]
     */
    @Test
    open fun arithmeticExpressionsTestData() {
        @Suppress("DEPRECATION")
        doFoldingTest(state::arithmeticExpressions)
    }

    /**
     * Substitutes keywords and primitive types with emoji equivalents
     * (e.g., `package` → 📦, `int` → 🔢).
     *
     * [data.EmojifyTestData]
     */
    @Test
    open fun emojifyTestData() {
        @Suppress("DEPRECATION")
        doFoldingTest(state::emojify)
    }

    /**
     * Collapses interface getter/setter pairs into annotated properties,
     * combining Lombok generation with nullable markers.
     *
     * [data.InterfaceExtensionPropertiesTestData]
     */
    @Test
    open fun interfaceExtensionPropertiesTestData() {
        doFoldingTest(state::interfaceExtensionProperties, state::lombok, state::nullable)
    }

    /**
     * Turns `instanceof` checks followed by casts into pattern matching
     * `if (o instanceof String s)` style expressions.
     *
     * [data.PatternMatchingInstanceofTestData]
     */
    @Test
    open fun patternMatchingInstanceofTestData() {
        doFoldingTest(state::patternMatchingInstanceof)
    }

    /**
     * Summarizes overridden parent and interface methods in the class
     * declaration header for quick reference.
     *
     * [data.SummaryParentOverrideTestData]
     */
    @Test
    open fun summaryParentOverrideTestData() {
        doFoldingTest(state::summaryParentOverride)
    }

    /**
     * Replaces `new` instantiations with constructor references such as
     * `ConstClass SELF = ::new;`.
     *
     * [data.ConstructorReferenceNotationTestData]
     */
    @Test
    open fun constructorReferenceNotationTestData() {
        doFoldingTest(state::constructorReferenceNotation)
    }

    /**
     * Same as above but confirms constructor references integrate with
     * `const` folding for static fields.
     *
     * [data.ConstructorReferenceNotationWithConstTestData]
     */
    @Test
    open fun constructorReferenceNotationWithConstTestData() {
        doFoldingTest(state::constructorReferenceNotation, state::const)
    }


    /**
     * Merges multiple overloads into a single method using default parameter
     * values (`String name = "DESC"`).
     *
     * [data.MethodDefaultParametersTestData]
     */
    @Test
    open fun methodDefaultParametersTestData() {
        doFoldingTest(state::methodDefaultParameters)
    }

    /**
     * Ensures a custom `lombokPatternOff` regex disables folding for
     * specific files even though the same annotations (`@Getter`,
     * `@Setter`, `@Builder`, etc.) are folded elsewhere.
     *
     * [data.LombokPatternOffTestData]
     */
    @Test
    open fun lombokPatternOffTestData() {
        state.lombokPatternOff = "LombokPa[t]{2}ernOffTestData"
        try {
            doFoldingTest(state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }
    /**
     * Verifies that excluding one file with `lombokPatternOff` does not
     * affect folding of other files using the same Lombok annotations.
     *
     * [data.LombokPatternOffNegativeTestData]
     */
    @Test
    open fun lombokPatternOffNegativeTestData() {
        state.lombokPatternOff = "LombokPatternOffTestData"
        try {
            doFoldingTest(state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }

    /**
     * Hides explicit `@Override` annotations when the override relationship
     * is obvious from the context.
     *
     * [data.OverrideHideTestData]
     */
    @Test
    fun overrideHideTestData() {
        doFoldingTest(state::overrideHide)
    }
    /**
     * Removes `@SuppressWarnings` annotations from generated code while
     * keeping their effect.
     *
     * [data.SuppressWarningsHideTestData]
     */
    @Test
    fun suppressWarningsHideTestData() {
        doFoldingTest(state::suppressWarningsHide)
    }

    // NEW OPTION
    /**
     * Stress-tests the plugin by enabling several experimental options at
     * once (nullable, const, Lombok, etc.).
     *
     * [data.ExperimentalTestData]
     */
    @Test
    open fun experimentalTestData() {
        doFoldingTest(state::experimental, state::nullable, state::const, state::lombok, state::getExpressionsCollapse)
    }

}
