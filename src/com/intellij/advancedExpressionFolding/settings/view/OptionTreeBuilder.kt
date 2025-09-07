package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.processor.util.Consts.Emoji
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State

/**
 * Builds an [OptionNode] tree from the settings [State]. The structure mirrors the order in which
 * options were previously listed in the settings UI.
 */
fun buildOptionTree(state: State): OptionNode = OptionNode(
    id = "root",
    label = "Options",
    children = listOf(
        OptionNode("getSetExpressionsCollapse", "Getters and setters as properties", property = state::getSetExpressionsCollapse),
        OptionNode("varExpressionsCollapse", "Variable declarations (var/val)", property = state::varExpressionsCollapse),
        OptionNode("compactControlFlowSyntaxCollapse", "Compact control flow condition syntax (Golang ifs)", property = state::compactControlFlowSyntaxCollapse),
        OptionNode("getExpressionsCollapse", "List.get, List.set, Map.get and Map.put expressions, array and list literals", property = state::getExpressionsCollapse),
        OptionNode("concatenationExpressionsCollapse", "StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions", property = state::concatenationExpressionsCollapse),
        OptionNode("slicingExpressionsCollapse", "List.subList and String.substring expressions", property = state::slicingExpressionsCollapse),
        OptionNode("comparingExpressionsCollapse", "Object.equals and Comparable.compareTo expressions", property = state::comparingExpressionsCollapse),
        OptionNode("comparingLocalDatesCollapse", "Java.time isBefore/isAfter expressions", property = state::comparingLocalDatesCollapse),
        OptionNode("localDateLiteralCollapse", "LocalDate.of literals (e.g. 2018-02-12)", property = state::localDateLiteralCollapse),
        OptionNode("localDateLiteralPostfixCollapse", "Postfix LocalDate literals (e.g. 2018Y-02M-12D)", property = state::localDateLiteralPostfixCollapse),
        OptionNode("castExpressionsCollapse", "Type cast expressions", property = state::castExpressionsCollapse),
        OptionNode("rangeExpressionsCollapse", "For loops, range expressions", property = state::rangeExpressionsCollapse),
        OptionNode("checkExpressionsCollapse", "Null-safe calls", property = state::checkExpressionsCollapse),
        OptionNode("ifNullSafe", "Extended null-safe ifs", property = state::ifNullSafe),
        OptionNode("kotlinQuickReturn", "Kotlin quick return", property = state::kotlinQuickReturn),
        OptionNode("assertsCollapse", "Asserts", property = state::assertsCollapse),
        OptionNode("optional", "Display optional as Kotlin null-safe", property = state::optional),
        OptionNode("streamSpread", "Display stream operations as Groovy's spread operator", property = state::streamSpread),
        OptionNode("logFolding", "Log folding", property = state::logFolding),
        OptionNode("fieldShift", "Display mapping of field with same name as <<", property = state::fieldShift),
        OptionNode("destructuring", "Destructuring assignment for array & list", property = state::destructuring),
        OptionNode("println", "Simplify System.out.println to println", property = state::println),
        OptionNode("controlFlowSingleStatementCodeBlockCollapse", "Control flow single-statement code block braces (read-only files)", property = state::controlFlowSingleStatementCodeBlockCollapse),
        OptionNode("controlFlowMultiStatementCodeBlockCollapse", "Control flow multi-statement code block braces (read-only files, deprecated)", property = state::controlFlowMultiStatementCodeBlockCollapse),
        OptionNode("semicolonsCollapse", "Semicolons (read-only files)", property = state::semicolonsCollapse),
        OptionNode("const", "Simplify * static final to const", property = state::const),
        OptionNode("nullable", "Simplify @NotNull to Type!! and @Nullable to Type?", property = state::nullable),
        OptionNode("finalRemoval", "Remove the 'final' modifier from all elements except fields", property = state::finalRemoval),
        OptionNode("finalEmoji", "Replace the 'final' modifier with ${Emoji.FINAL_LOCK}", property = state::finalEmoji),
        OptionNode("lombok", "Display Java bean as Lombok", property = state::lombok),
        OptionNode("lombokDirtyOff", "Don't fold Lombok dirty getters/setters", property = state::lombokDirtyOff),
        OptionNode("expressionFunc", "Single-Expression Function", property = state::expressionFunc),
        OptionNode("dynamic", "Dynamic names for methods based on \$user.home/dynamic-ajf2.toml", property = state::dynamic),
        OptionNode("arithmeticExpressions", "BigDecimal, BigInteger and Math", property = state::arithmeticExpressions),
        OptionNode("emojify", "Emojify code", property = state::emojify),
        OptionNode("interfaceExtensionProperties", "Converts traditional getter and setter methods in interfaces into extension properties", property = state::interfaceExtensionProperties),
        OptionNode("patternMatchingInstanceof", "Pattern Matching for instanceof (JEP 394)", property = state::patternMatchingInstanceof),
        OptionNode("summaryParentOverride", "Displays a folded summary of overridden methods from parent classes and interfaces.", property = state::summaryParentOverride),
        OptionNode("constructorReferenceNotation", "Constructor reference notation ::new and compact field initialization", property = state::constructorReferenceNotation),
        OptionNode("methodDefaultParameters", "Default parameter values inline for overloaded method", property = state::methodDefaultParameters),
        OptionNode("overrideHide", "Hide @Override annotation", property = state::overrideHide),
        OptionNode("suppressWarningsHide", "Hide @SuppressWarnings annotation", property = state::suppressWarningsHide),
        OptionNode("pseudoAnnotations", "Pseudo-annotations: @Main", property = state::pseudoAnnotations),
        OptionNode("memoryImprovement", "Memory improvements", property = state::memoryImprovement),
        OptionNode("experimental", "Experimental features", property = state::experimental),
        OptionNode("globalOn", "Enable folding features", property = state::globalOn)
    )
)

