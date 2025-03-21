package com.intellij.advancedExpressionFolding.settings

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.extension.Consts.Emoji
import kotlin.reflect.KMutableProperty0

typealias Description = String
typealias UrlSuffix = String
typealias ExampleFile = String

data class CheckboxDefinition(
    val title: String,
    val property: KMutableProperty0<Boolean>,
    val exampleLinkMap: Map<ExampleFile, Description?>? = null,
    val docLink: UrlSuffix? = null
)

class CheckboxBuilder {
    private val examples = mutableMapOf<ExampleFile, Description?>()
    private var docLink: UrlSuffix? = null

    fun example(file: ExampleFile, description: Description? = null) {
        examples[file] = description
    }

    fun link(documentationLink: UrlSuffix) {
        docLink = documentationLink
    }

    internal fun build(
        property: KMutableProperty0<Boolean>,
        title: String
    ): CheckboxDefinition {
        return CheckboxDefinition(
            title = title,
            property = property,
            exampleLinkMap = if (examples.isEmpty()) null else examples.toMap(),
            docLink = docLink
        )
    }
}

class CheckboxDefinitionsProvider {
    private val checkboxDefinitions = mutableListOf<CheckboxDefinition>()

    fun getCheckboxDefinitions(): List<CheckboxDefinition> = checkboxDefinitions

    fun getAllExampleFiles(): List<ExampleFile> = checkboxDefinitions
        .mapNotNull { it.exampleLinkMap }
        .flatMap { it.keys }

    fun registerCheckbox(
        property: KMutableProperty0<Boolean>,
        title: String,
        block: (CheckboxBuilder.() -> Unit)? = null
    ) {
        val builder = CheckboxBuilder()
        block?.invoke(builder)

        checkboxDefinitions.add(builder.build(property, title))
    }

    fun initialize(state: AdvancedExpressionFoldingSettings.State): CheckboxDefinitionsProvider {
        registerCheckbox(state::getSetExpressionsCollapse, "Getters and setters as properties") {
            example("GetterSetterTestData.java")
        }

        registerCheckbox(state::varExpressionsCollapse, "Variable declarations (var/val)") {
            example("VarTestData.java")
        }

        registerCheckbox(state::compactControlFlowSyntaxCollapse, "Compact control flow condition syntax (Golang ifs)") {
            example("CompactControlFlowTestData.java")
        }

        registerCheckbox(state::getExpressionsCollapse, "List.get, List.set, Map.get and Map.put expressions, array and list literals") {
            example("GetSetPutTestData.java")
        }

        registerCheckbox(state::concatenationExpressionsCollapse,
            "StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions") {
            example("StringBuilderTestData.java", "StringBuilder")
            example("InterpolatedStringTestData.java", "Interpolate")
            example("AppendSetterInterpolatedStringTestData.java", "Append")
            example("ConcatenationTestData.java", "Concatenation")
        }

        registerCheckbox(state::slicingExpressionsCollapse, "List.subList and String.substring expressions") {
            example("SliceTestData.java")
        }

        registerCheckbox(state::comparingExpressionsCollapse, "Object.equals and Comparable.compareTo expressions") {
            example("EqualsCompareTestData.java")
        }

        registerCheckbox(state::comparingLocalDatesCollapse, "Java.time isBefore/isAfter expressions") {
            example("LocalDateTestData.java")
        }

        registerCheckbox(state::localDateLiteralCollapse, "LocalDate.of literals (e.g. 2018-02-12)") {
            example("LocalDateLiteralTestData.java")
        }

        registerCheckbox(state::localDateLiteralPostfixCollapse, "Postfix LocalDate literals (e.g. 2018Y-02M-12D)") {
            example("LocalDateLiteralPostfixTestData.java")
        }

        registerCheckbox(state::castExpressionsCollapse, "Type cast expressions") {
            example("TypeCastTestData.java")
        }

        registerCheckbox(state::rangeExpressionsCollapse, "For loops, range expressions") {
            example("ForRangeTestData.java")
        }

        registerCheckbox(state::checkExpressionsCollapse, "Null-safe calls") {
            example("ElvisTestData.java")
        }

        registerCheckbox(state::ifNullSafe, "Extended null-safe ifs") {
            example("IfNullSafeData.java")
            link("/Extended-null‐safe-ifs")
        }

        registerCheckbox(state::kotlinQuickReturn, "Kotlin quick return") {
            example("LetReturnIt.java")
        }

        registerCheckbox(state::assertsCollapse, "Asserts") {
            example("AssertTestData.java")
        }

        registerCheckbox(state::optional, "Display optional as Kotlin null-safe") {
            example("OptionalTestData.java")
        }

        registerCheckbox(state::streamSpread, "Display stream operations as Groovy's spread operator") {
            example("SpreadTestData.java")
        }

        registerCheckbox(state::lombok, "Display Java bean as Lombok") {
            example("LombokTestData.java")
        }

        registerCheckbox(state::logFolding, "Log folding") {
            example("LogBrackets.java")
            link("/Log-brackets-folding")
        }

        registerCheckbox(state::fieldShift, "Display mapping of field with same name as << (for builders, setters and assignments)") {
            example("FieldShiftBuilder.java", "builders")
            example("FieldShiftSetters.java", "setters")
            example("FieldShiftFields.java", "fields")
            link("/FieldShift")
        }

        registerCheckbox(state::destructuring, "Destructuring assignment for array & list") {
            example("DestructuringAssignmentArrayTestData.java", "array")
            example("DestructuringAssignmentListTestData.java", "list")
            link("/Destructuring-assignment")
        }

        registerCheckbox(state::println, "Simplify System.out.println to println") {
            example("PrintlnTestData.java")
            link("/Simplify-System.out.println-to-println")
        }

        registerCheckbox(state::controlFlowSingleStatementCodeBlockCollapse, "Control flow single-statement code block braces (read-only files)") {
            example("ControlFlowSingleStatementTestData.java")
        }

        registerCheckbox(state::controlFlowMultiStatementCodeBlockCollapse, "Control flow multi-statement code block braces (read-only files, deprecated)") {
            example("ControlFlowMultiStatementTestData.java")
        }

        registerCheckbox(state::semicolonsCollapse, "Semicolons (read-only files)") {
            example("SemicolonTestData.java")
        }

        registerCheckbox(state::testDataFoldingDiff, "Folding of testData in diff") {
            link("/Folding-of-testData-in-diff")
        }

        registerCheckbox(state::const, "Simplify * static final to const") {
            example("ConstTestData.java")
        }

        registerCheckbox(state::nullable, "Simplify @NotNull to Type!! and @Nullable to Type?") {
            example("NullableAnnotationTestData.java", "annotations")
            example("NullableAnnotationCheckNotNullTestData.java", "checkNotNull")
        }

        registerCheckbox(state::finalRemoval, "Remove the 'final' modifier from all elements except fields") {
            example("FinalRemovalTestData.java")
        }

        registerCheckbox(state::finalEmoji, "Replace the 'final' modifier with " + Emoji.FINAL_LOCK) {
            example("FinalEmojiTestData.java")
        }

        registerCheckbox(state::lombokDirtyOff, "Don't fold lombok dirty getters/setters") {
            example("LombokDirtyOffTestData.java")
        }

        registerCheckbox(state::expressionFunc, "Single-Expression Function") {
            example("ExpressionFuncTestData.java")
        }

        registerCheckbox(state::dynamic, "Dynamic names for methods based on \$user.home/dynamic-ajf2.toml") {
            example("DynamicTestData.java")
            link("/Dynamic-Folding")
        }

        if (false) {
            registerCheckbox(state::arithmeticExpressions, "BigDecimal, BigInteger and Math") {
                example("ArithmeticExpressionsTestData.java")
            }
        }

        registerCheckbox(state::emojify, "Emojify code") {
            example("EmojifyTestData.java")
        }

        registerCheckbox(state::interfaceExtensionProperties, "Converts traditional getter and setter methods in interfaces into extension properties") {
            example("InterfaceExtensionPropertiesTestData.java")
        }

        registerCheckbox(state::patternMatchingInstanceof, "Pattern Matching for instanceof (JEP 394)") {
            example("PatternMatchingInstanceofTestData.java")
            link("/PatternMatchingInstanceof")
        }

        registerCheckbox(state::summaryParentOverride, "Displays a folded summary of overridden methods from parent classes and interfaces.") {
            example("SummaryParentOverrideTestData.java")
        }

        registerCheckbox(state::constructorReferenceNotation, "Constructor reference notation ::new and compact field initialization") {
            example("ConstructorReferenceNotationTestData.java")
            link("/ConstructorReferenceNotation")
        }

        registerCheckbox(state::methodDefaultParameters, "Default parameter values inline for overloaded method") {
            example("MethodDefaultParametersTestData.java")
            link("/MethodDefaultParameters")
        }

        registerCheckbox(state::memoryImprovement, "Memory improvements(experimental)")

        registerCheckbox(state::experimental, "Experimental features")

        return this
    }
}