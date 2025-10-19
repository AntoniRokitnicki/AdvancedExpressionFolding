package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.documentation.DocumentationLinks
import com.intellij.advancedExpressionFolding.processor.util.Consts.Emoji
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.editor.highlighter.EditorHighlighterFactory
import com.intellij.openapi.ui.ComponentValidator
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBLabel
import com.intellij.ui.dsl.builder.Panel
import org.intellij.lang.regexp.RegExpFileType
import java.awt.Dimension
import java.util.function.Supplier
import java.util.regex.PatternSyntaxException
import kotlin.reflect.KMutableProperty0

abstract class CheckboxesProvider {

    @Suppress("DEPRECATION")
    @CheckboxDsl
    fun Panel.initialize(state: State) {
        registerCheckbox(state::getSetExpressionsCollapse, "Getters and setters as properties") {
            example("GetterSetterTestData.java")
            DocumentationLinks.urlFor("getSetExpressionsCollapse")?.let(::link)
        }

        registerCheckbox(state::varExpressionsCollapse, "Variable declarations (var/val)") {
            example("VarTestData.java")
            DocumentationLinks.urlFor("varExpressionsCollapse")?.let(::link)
        }

        registerCheckbox(
            state::compactControlFlowSyntaxCollapse,
            "Compact control flow condition syntax (Golang ifs)"
        ) {
            example("CompactControlFlowTestData.java")
            DocumentationLinks.urlFor("compactControlFlowSyntaxCollapse")?.let(::link)
        }

        registerCheckbox(
            state::getExpressionsCollapse,
            "List.get, List.set, Map.get and Map.put expressions, array and list literals"
        ) {
            example("GetSetPutTestData.java")
            DocumentationLinks.urlFor("getExpressionsCollapse")?.let(::link)
        }

        registerCheckbox(
            state::concatenationExpressionsCollapse,
            "StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions"
        ) {
            example("StringBuilderTestData.java", "StringBuilder")
            example("InterpolatedStringTestData.java", "Interpolate")
            example("AppendSetterInterpolatedStringTestData.java", "Append")
            example("ConcatenationTestData.java", "Concatenation")
            DocumentationLinks.urlFor("concatenationExpressionsCollapse")?.let(::link)
        }

        registerCheckbox(state::slicingExpressionsCollapse, "List.subList and String.substring expressions") {
            example("SliceTestData.java")
            DocumentationLinks.urlFor("slicingExpressionsCollapse")?.let(::link)
        }

        registerCheckbox(state::comparingExpressionsCollapse, "Object.equals and Comparable.compareTo expressions") {
            example("EqualsCompareTestData.java")
            DocumentationLinks.urlFor("comparingExpressionsCollapse")?.let(::link)
        }

        registerCheckbox(state::comparingLocalDatesCollapse, "Java.time isBefore/isAfter expressions") {
            example("LocalDateTestData.java")
            DocumentationLinks.urlFor("comparingLocalDatesCollapse")?.let(::link)
        }

        registerCheckbox(state::localDateLiteralCollapse, "LocalDate.of literals (e.g. 2018-02-12)") {
            example("LocalDateLiteralTestData.java")
            DocumentationLinks.urlFor("localDateLiteralCollapse")?.let(::link)
        }

        registerCheckbox(state::localDateLiteralPostfixCollapse, "Postfix LocalDate literals (e.g. 2018Y-02M-12D)") {
            example("LocalDateLiteralPostfixTestData.java")
            DocumentationLinks.urlFor("localDateLiteralPostfixCollapse")?.let(::link)
        }

        registerCheckbox(state::castExpressionsCollapse, "Type cast expressions") {
            example("TypeCastTestData.java")
            DocumentationLinks.urlFor("castExpressionsCollapse")?.let(::link)
        }

        registerCheckbox(state::rangeExpressionsCollapse, "For loops, range expressions") {
            example("ForRangeTestData.java")
            DocumentationLinks.urlFor("rangeExpressionsCollapse")?.let(::link)
        }

        registerCheckbox(state::checkExpressionsCollapse, "Null-safe calls") {
            example("ElvisTestData.java")
            DocumentationLinks.urlFor("checkExpressionsCollapse")?.let(::link)
        }

        registerCheckbox(state::ifNullSafe, "Extended null-safe ifs") {
            example("IfNullSafeData.java")
            DocumentationLinks.urlFor("ifNullSafe")?.let(::link)
        }

        registerCheckbox(state::kotlinQuickReturn, "Kotlin quick return") {
            example("LetReturnIt.java")
            DocumentationLinks.urlFor("kotlinQuickReturn")?.let(::link)
        }

        registerCheckbox(state::assertsCollapse, "Asserts") {
            example("AssertTestData.java")
            DocumentationLinks.urlFor("assertsCollapse")?.let(::link)
        }

        registerCheckbox(state::optional, "Display optional as Kotlin null-safe") {
            example("OptionalTestData.java")
            DocumentationLinks.urlFor("optional")?.let(::link)
        }

        registerCheckbox(state::streamSpread, "Display stream operations as Groovy's spread operator") {
            example("SpreadTestData.java")
            DocumentationLinks.urlFor("streamSpread")?.let(::link)
        }

        registerCheckbox(state::logFolding, "Log folding") {
            example("LogBrackets.java")
            DocumentationLinks.urlFor("logFolding")?.let(::link)
        }
        registerCheckbox(state::logFoldingTextBlocks, "Log folding: collapse Text Blocks") {
            example("LogFoldingTextBlocksTestData.java")
        }

        registerCheckbox(
            state::fieldShift,
            "Display mapping of field with same name as << (for builders, setters and assignments)"
        ) {
            example("FieldShiftBuilder.java", "builders")
            example("FieldShiftSetters.java", "setters")
            example("FieldShiftFields.java", "fields")
            DocumentationLinks.urlFor("fieldShift")?.let(::link)
        }

        registerCheckbox(state::destructuring, "Destructuring assignment for array & list") {
            example("DestructuringAssignmentArrayTestData.java", "array")
            example("DestructuringAssignmentListTestData.java", "list")
            DocumentationLinks.urlFor("destructuring")?.let(::link)
        }

        registerCheckbox(state::println, "Simplify System.out.println to println") {
            example("PrintlnTestData.java")
            DocumentationLinks.urlFor("println")?.let(::link)
        }

        registerCheckbox(
            state::controlFlowSingleStatementCodeBlockCollapse,
            "Control flow single-statement code block braces (read-only files)"
        ) {
            example("ControlFlowSingleStatementTestData.java")
            DocumentationLinks.urlFor("controlFlowSingleStatementCodeBlockCollapse")?.let(::link)
        }

        registerCheckbox(
            state::controlFlowMultiStatementCodeBlockCollapse,
            "Control flow multi-statement code block braces (read-only files, deprecated)"
        ) {
            example("ControlFlowMultiStatementTestData.java")
            DocumentationLinks.urlFor("controlFlowMultiStatementCodeBlockCollapse")?.let(::link)
        }

        registerCheckbox(state::semicolonsCollapse, "Semicolons (read-only files)") {
            example("SemicolonTestData.java")
            DocumentationLinks.urlFor("semicolonsCollapse")?.let(::link)
        }

        registerCheckbox(state::const, "Simplify * static final to const") {
            example("ConstTestData.java")
            DocumentationLinks.urlFor("const")?.let(::link)
        }

        registerCheckbox(state::nullable, "Simplify @NotNull to Type!! and @Nullable to Type?") {
            example("NullableAnnotationTestData.java", "annotations")
            example("NullableAnnotationCheckNotNullTestData.java", "checkNotNull")
            DocumentationLinks.urlFor("nullable")?.let(::link)
        }

        registerCheckbox(state::finalRemoval, "Remove the 'final' modifier from all elements except fields") {
            example("FinalRemovalTestData.java")
            DocumentationLinks.urlFor("finalRemoval")?.let(::link)
        }

        registerCheckbox(state::finalEmoji, "Replace the 'final' modifier with ${Emoji.FINAL_LOCK}") {
            example("FinalEmojiTestData.java")
            DocumentationLinks.urlFor("finalEmoji")?.let(::link)
        }

        registerCheckbox(state::lombok, "Display Java bean as Lombok") {
            example("LombokTestData.java")
            DocumentationLinks.urlFor("lombok")?.let(::link)
        }

        registerCheckbox(state::lombokDirtyOff, "Don't fold Lombok dirty getters/setters") {
            example("LombokDirtyOffTestData.java")
            DocumentationLinks.urlFor("lombokDirtyOff")?.let(::link)
        }

        row {
            cell(JBLabel("Regex to disable Lombok folding (matched classes won’t be folded)"))
        }
        row {
            cell(createEditor(state::lombokPatternOff).component)
        }

        registerCheckbox(state::expressionFunc, "Single-Expression Function") {
            example("ExpressionFuncTestData.java")
            DocumentationLinks.urlFor("expressionFunc")?.let(::link)
        }

        registerCheckbox(state::dynamic, "Dynamic names for methods based on \$user.home/dynamic-ajf2.toml") {
            example("DynamicTestData.java")
            DocumentationLinks.urlFor("dynamic")?.let(::link)
        }

        registerCheckbox(state::arithmeticExpressions, "BigDecimal, BigInteger and Math") {
            example("ArithmeticExpressionsTestData.java")
            DocumentationLinks.urlFor("arithmeticExpressions")?.let(::link)
        }

        registerCheckbox(state::emojify, "Emojify code") {
            example("EmojifyTestData.java")
            DocumentationLinks.urlFor("emojify")?.let(::link)
        }

        registerCheckbox(
            state::interfaceExtensionProperties,
            "Converts traditional getter and setter methods in interfaces into extension properties"
        ) {
            example("InterfaceExtensionPropertiesTestData.java")
            DocumentationLinks.urlFor("interfaceExtensionProperties")?.let(::link)
        }

        registerCheckbox(state::patternMatchingInstanceof, "Pattern Matching for instanceof (JEP 394)") {
            example("PatternMatchingInstanceofTestData.java")
            DocumentationLinks.urlFor("patternMatchingInstanceof")?.let(::link)
        }

        registerCheckbox(
            state::summaryParentOverride,
            "Displays a folded summary of overridden methods from parent classes and interfaces."
        ) {
            example("SummaryParentOverrideTestData.java")
            DocumentationLinks.urlFor("summaryParentOverride")?.let(::link)
        }

        registerCheckbox(
            state::constructorReferenceNotation,
            "Constructor reference notation ::new and compact field initialization"
        ) {
            example("ConstructorReferenceNotationTestData.java")
            DocumentationLinks.urlFor("constructorReferenceNotation")?.let(::link)
        }

        registerCheckbox(state::methodDefaultParameters, "Default parameter values inline for overloaded method") {
            example("MethodDefaultParametersTestData.java")
            DocumentationLinks.urlFor("methodDefaultParameters")?.let(::link)
        }
        registerCheckbox(state::overrideHide, "Hide @Override annotation") {
            example("OverrideHideTestData.java")
            DocumentationLinks.urlFor("overrideHide")?.let(::link)
        }
        registerCheckbox(state::suppressWarningsHide, "Hide @SuppressWarnings annotation") {
            example("SuppressWarningsHideTestData.java")
            DocumentationLinks.urlFor("suppressWarningsHide")?.let(::link)
        }
        registerCheckbox(state::pseudoAnnotations, "Pseudo-annotations: @Main, @Loggable") {
            example("PseudoAnnotationsMainTestData.java")
            DocumentationLinks.urlFor("pseudoAnnotations")?.let(::link)
        }
        // NEW OPTION
        registerCheckbox(state::memoryImprovement, "Memory improvements")
        registerCheckbox(state::experimental, "Experimental features") {
            example("ExperimentalTestData.java")
            DocumentationLinks.urlFor("experimental")?.let(::link)
        }
    }

    @CheckboxDsl
    abstract fun Panel.registerCheckbox(
        property: KMutableProperty0<Boolean>,
        title: String,
        block: (CheckboxBuilder.() -> Unit)? = null
    )
}


private fun createEditor(property: KMutableProperty0<String?>): EditorEx {
    val factory = com.intellij.openapi.editor.EditorFactory.getInstance()
    val document = factory.createDocument(property.get() ?: "")

    val editor = factory.createEditor(document, null) as EditorEx
    val validator = ComponentValidator(ApplicationManager.getApplication())
        .withValidator(Supplier {
            val text = document.text.trim()
            if (text.isEmpty()) {
                property.set(null)
                return@Supplier null
            }

            try {
                text.toPattern()
                property.set(text)
                null
            } catch (exception: PatternSyntaxException) {
                property.set(null)
                ValidationInfo(
                    exception.description ?: exception.message ?: "Invalid regular expression",
                    editor.component
                )
            }
        })
        .installOn(editor.component)

    document.addDocumentListener(object : DocumentListener {
        override fun documentChanged(event: DocumentEvent) {
            validator.revalidate()
        }
    })
    validator.revalidate()
    return editor.apply {
        settings.apply {
            isUseSoftWraps = true
            isUseCustomSoftWrapIndent = true
            isLineNumbersShown = false
            isLineMarkerAreaShown = false
        }
        highlighter = EditorHighlighterFactory.getInstance().createEditorHighlighter(null, RegExpFileType.INSTANCE)
        component.preferredSize = Dimension(500, 50)
    }
}
