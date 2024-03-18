package com.intellij.advancedExpressionFolding

import com.intellij.application.options.editor.CodeFoldingOptionsProvider
import com.intellij.openapi.options.BeanConfigurable

class AdvancedExpressionFoldingOptionsProvider protected constructor() :
    BeanConfigurable<AdvancedExpressionFoldingSettings.State?>(
        AdvancedExpressionFoldingSettings.getInstance().state
    ), CodeFoldingOptionsProvider {
    init {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val myState = settings.state

        title = "Advanced Expression Folding 2"
        checkBox("Getters and setters as properties", myState::getSetExpressionsCollapse)
        checkBox("Variable declarations (var/val)", myState::varExpressionsCollapse)
        checkBox("Compact control flow condition syntax (Golang ifs)", myState::compactControlFlowSyntaxCollapse)
        checkBox("List.get, List.set, Map.get and Map.put expressions, array and list literals", myState::getExpressionsCollapse)

        checkBox("StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions", myState::concatenationExpressionsCollapse)
        checkBox("List.subList and String.substring expressions", myState::slicingExpressionsCollapse)
        checkBox("Object.equals and Comparable.compareTo expressions", myState::comparingExpressionsCollapse)

        checkBox("Java.time isBefore/isAfter expressions", myState::comparingLocalDatesCollapse)
        checkBox("LocalDate.of literals (e.g. 2018-02-12)", myState::localDateLiteralCollapse)
        checkBox("Postfix LocalDate literals (e.g. 2018Y-02M-12D)", myState::localDateLiteralPostfixCollapse)

        checkBox("Type cast expressions", myState::castExpressionsCollapse)
        checkBox("For loops, range expressions", myState::rangeExpressionsCollapse)
        checkBox("Null-safe calls", myState::checkExpressionsCollapse)
        checkBox("Extended null-safe ifs", myState::ifNullSafe)
        checkBox("Kotlin quick return", myState::kotlinQuickReturn)

        checkBox("Asserts", myState::assertsCollapse)

        checkBox("Display optional as Kotlin null-safe", myState::optional)
        checkBox("Display stream operations as Groovy's spread operator", myState::streamSpread)

        checkBox("Display Java bean as Lombok", myState::lombok)
        checkBox("Log folding", myState::logFolding)
        checkBox("Display mapping of field with same name as << (for builders, setters and assignments)", myState::fieldShift)
        checkBox("Display mapping of field with same name as << (v1 deprecated)", myState::fieldShiftOld)
        checkBox("Destructuring assignment for array (preview)", myState::destructuring)

        checkBox("Control flow single-statement code block braces (read-only files)", myState::controlFlowSingleStatementCodeBlockCollapse)
        checkBox("Control flow multi-statement code block braces (read-only files, deprecated)", myState::controlFlowMultiStatementCodeBlockCollapse)
        checkBox("Semicolons (read-only files)", myState::semicolonsCollapse)
        checkBox("Folding of testData in diff", myState::testDataFoldingDiff)
    }

}
