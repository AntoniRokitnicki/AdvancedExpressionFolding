package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.processor.util.Consts.Emoji
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import data.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.editor.impl.EditorImpl
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
            example(GetterSetterTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#getsetexpressionscollapse")
        }

        registerCheckbox(state::varExpressionsCollapse, "Variable declarations (var/val)") {
            example(VarTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#varexpressionscollapse")
        }

        registerCheckbox(
            state::compactControlFlowSyntaxCollapse,
            "Compact control flow condition syntax (Golang ifs)"
        ) {
            example(CompactControlFlowTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#compactcontrolflowsyntaxcollapse")
        }

        registerCheckbox(
            state::getExpressionsCollapse,
            "List.get, List.set, Map.get and Map.put expressions, array and list literals"
        ) {
            example(GetSetPutTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#getexpressionscollapse")
        }

        registerCheckbox(
            state::concatenationExpressionsCollapse,
            "StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions"
        ) {
            example(StringBuilderTestData::class, "StringBuilder")
            example(InterpolatedStringTestData::class, "Interpolate")
            example(AppendSetterInterpolatedStringTestData::class, "Append")
            example(ConcatenationTestData::class, "Concatenation")
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#concatenationexpressionscollapse")
        }

        registerCheckbox(state::slicingExpressionsCollapse, "List.subList and String.substring expressions") {
            example(SliceTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#slicingexpressionscollapse")
        }

        registerCheckbox(state::comparingExpressionsCollapse, "Object.equals and Comparable.compareTo expressions") {
            example(EqualsCompareTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#comparingexpressionscollapse")
        }

        registerCheckbox(state::comparingLocalDatesCollapse, "Java.time isBefore/isAfter expressions") {
            example(LocalDateTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#comparinglocaldatescollapse")
        }

        registerCheckbox(state::localDateLiteralCollapse, "LocalDate.of literals (e.g. 2018-02-12)") {
            example(LocalDateLiteralTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#localdateliteralcollapse")
        }

        registerCheckbox(state::localDateLiteralPostfixCollapse, "Postfix LocalDate literals (e.g. 2018Y-02M-12D)") {
            example(LocalDateLiteralPostfixTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#localdateliteralpostfixcollapse")
        }

        registerCheckbox(state::castExpressionsCollapse, "Type cast expressions") {
            example(TypeCastTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#castexpressionscollapse")
        }

        registerCheckbox(state::rangeExpressionsCollapse, "For loops, range expressions") {
            example(ForRangeTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#rangeexpressionscollapse")
        }

        registerCheckbox(state::checkExpressionsCollapse, "Null-safe calls") {
            example(ElvisTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#checkexpressionscollapse")
        }

        registerCheckbox(state::ifNullSafe, "Extended null-safe ifs") {
            example(IfNullSafeData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#ifnullsafe")
        }

        registerCheckbox(state::kotlinQuickReturn, "Kotlin quick return") {
            example(LetReturnIt::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#kotlinquickreturn")
        }

        registerCheckbox(state::assertsCollapse, "Asserts") {
            example(AssertTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#asserts")
        }

        registerCheckbox(state::optional, "Display optional as Kotlin null-safe") {
            example(OptionalTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#optional")
        }

        registerCheckbox(state::streamSpread, "Display stream operations as Groovy's spread operator") {
            example(SpreadTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#streamspread")
        }

        registerCheckbox(state::logFolding, "Log folding") {
            example(LogBrackets::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#logfolding")
        }
        registerCheckbox(state::logFoldingTextBlocks, "Log folding: collapse Text Blocks") {
            example(LogFoldingTextBlocksTestData::class)
        }

        registerCheckbox(
            state::fieldShift,
            "Display mapping of field with same name as << (for builders, setters and assignments)"
        ) {
            example(FieldShiftBuilder::class, "builders")
            example(FieldShiftSetters::class, "setters")
            example(FieldShiftFields::class, "fields")
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#fieldshift")
        }

        registerCheckbox(state::destructuring, "Destructuring assignment for array & list") {
            example(DestructuringAssignmentArrayTestData::class, "array")
            example(DestructuringAssignmentListTestData::class, "list")
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#destructuring")
        }

        registerCheckbox(state::println, "Simplify System.out.println to println") {
            example(PrintlnTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#println")
        }

        registerCheckbox(
            state::controlFlowSingleStatementCodeBlockCollapse,
            "Control flow single-statement code block braces (read-only files)"
        ) {
            example(ControlFlowSingleStatementTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#controlflowsinglestatementcodeblockcollapse")
        }

        registerCheckbox(
            state::controlFlowMultiStatementCodeBlockCollapse,
            "Control flow multi-statement code block braces (read-only files, deprecated)"
        ) {
            example(ControlFlowMultiStatementTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#controlflowmultistatementcodeblockcollapse")
        }

        registerCheckbox(
            state::stringEscapesVisualizeNewlines,
            "String escapes: visualize newlines"
        ) {
            example(StringEscapesTestData::class)
        }

        registerCheckbox(state::semicolonsCollapse, "Semicolons (read-only files)") {
            example(SemicolonTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#semicolonscollapse")
        }

        registerCheckbox(state::const, "Simplify * static final to const") {
            example(ConstTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#const")
        }

        registerCheckbox(state::nullable, "Simplify @NotNull to Type!! and @Nullable to Type?") {
            example(NullableAnnotationTestData::class, "annotations")
            example(NullableAnnotationCheckNotNullTestData::class, "checkNotNull")
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#nullable")
        }

        registerCheckbox(state::finalRemoval, "Remove the 'final' modifier from all elements except fields") {
            example(FinalRemovalTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#finalremoval")
        }

        registerCheckbox(state::finalEmoji, "Replace the 'final' modifier with ${Emoji.FINAL_LOCK}") {
            example(FinalEmojiTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#finalemoji")
        }

        registerCheckbox(state::lombok, "Display Java bean as Lombok") {
            example(LombokTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#lombok")
        }

        registerCheckbox(state::lombokDirtyOff, "Don't fold Lombok dirty getters/setters") {
            example(LombokDirtyOffTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#lombokdirtyoff")
        }

        row {
            cell(JBLabel("Regex to disable Lombok folding (matched classes won’t be folded)"))
        }
        row {
            cell(createEditor(state::lombokPatternOff).component)
        }

        registerCheckbox(state::expressionFunc, "Single-Expression Function") {
            example(ExpressionFuncTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#expressionfunc")
        }

        registerCheckbox(state::dynamic, "Dynamic names for methods based on \$user.home/dynamic-ajf2.toml") {
            example(DynamicTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#dynamic")
        }

        registerCheckbox(state::arithmeticExpressions, "BigDecimal, BigInteger and Math") {
            example(ArithmeticExpressionsTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#arithmeticexpressions")
        }

        registerCheckbox(state::emojify, "Emojify code") {
            example(EmojifyTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#emojify")
        }

        registerCheckbox(
            state::interfaceExtensionProperties,
            "Converts traditional getter and setter methods in interfaces into extension properties"
        ) {
            example(InterfaceExtensionPropertiesTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#interfaceextensionproperties")
        }

        registerCheckbox(state::patternMatchingInstanceof, "Pattern Matching for instanceof (JEP 394)") {
            example(PatternMatchingInstanceofTestData::class)
            example(PatternMatchingRecordPatternTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#patternmatchinginstanceof")
        }

        registerCheckbox(
            state::summaryParentOverride,
            "Displays a folded summary of overridden methods from parent classes and interfaces."
        ) {
            example(SummaryParentOverrideTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#summaryparentoverride")
        }

        registerCheckbox(
            state::constructorReferenceNotation,
            "Constructor reference notation ::new and compact field initialization"
        ) {
            example(ConstructorReferenceNotationTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#constructorReferenceNotation")
        }

        registerCheckbox(state::methodDefaultParameters, "Default parameter values inline for overloaded method") {
            example(MethodDefaultParametersTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#methodDefaultParameters")
        }
        registerCheckbox(state::overrideHide, "Hide @Override annotation") {
            example(OverrideHideTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#overrideHide")
        }
        registerCheckbox(state::suppressWarningsHide, "Hide @SuppressWarnings annotation") {
            example(SuppressWarningsHideTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#suppressWarningsHide")
        }
        registerCheckbox(state::pseudoAnnotations, "Pseudo-annotations: @Main, @Loggable") {
            example(PseudoAnnotationsMainTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#pseudoAnnotations")
        }
        // NEW OPTION
        registerCheckbox(state::memoryImprovement, "Memory improvements")
        registerCheckbox(state::experimental, "Experimental features") {
            example(ExperimentalTestData::class)
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#experimental")
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
    val application = ApplicationManager.getApplication()

    fun validateDocument(): ValidationInfo? {
        val text = document.text.trim()
        if (text.isEmpty()) {
            property.set(null)
            return null
        }

        return try {
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
    }

    val validator = application?.let {
        ComponentValidator(it)
            .withValidator(Supplier { validateDocument() })
            .installOn(editor.component)
    }

    val documentListener = object : DocumentListener {
        override fun documentChanged(event: DocumentEvent) {
            if (validator != null) {
                validator.revalidate()
            } else {
                validateDocument()
            }
        }
    }
    document.addDocumentListener(documentListener, (editor as EditorImpl).disposable)
    if (validator != null) {
        validator.revalidate()
    } else {
        validateDocument()
    }
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
