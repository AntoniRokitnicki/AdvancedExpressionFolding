package com.intellij.advancedExpressionFolding;

public class AdvancedExpressionFoldingOptionsProvider extends com.intellij.openapi.options.BeanConfigurable<AdvancedExpressionFoldingSettings.State> implements com.intellij.application.options.editor.CodeFoldingOptionsProvider {
    protected AdvancedExpressionFoldingOptionsProvider() {
        super(AdvancedExpressionFoldingSettings.getInstance().getState());
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        AdvancedExpressionFoldingSettings.State state = settings.getState();

        setTitle("AdvancedExpressionFolding");
        checkBox("Math, BigDecimal and BigInteger expressions (deprecated)", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions", state::isConcatenationExpressionsCollapse, state::setConcatenationExpressionsCollapse);
        checkBox("List.subList and String.substring expressions", state::isSlicingExpressionsCollapse, state::setSlicingExpressionsCollapse);
        checkBox("Object.equals and Comparable.compareTo expressions", state::isComparingExpressionsCollapse, state::setComparingExpressionsCollapse);
        checkBox("Java.time isBefore/isAfter expressions", state::isComparingLocalDatesCollapse, state::setComparingLocalDatesCollapse);
        checkBox("LocalDate.of literals (e.g. 2018-02-12)" , state::isLocalDateLiteralCollapse, state::setLocalDateLiteralCollapse);
        checkBox("Postfix LocalDate literals (e.g. 2018Y-02M-12D) " , state::isLocalDateLiteralPostfix, state::setLocalDateLiteralPostfix);
        checkBox("List.get, List.set, Map.get and Map.put expressions, array and list literals", state::isGetExpressionsCollapse, state::setGetExpressionsCollapse);
        checkBox("For loops, range expressions", state::isRangeExpressionsCollapse, state::setRangeExpressionsCollapse);
        checkBox("Null safe calls", state::isCheckExpressionsCollapse, state::setCheckExpressionsCollapse);
        checkBox("Type cast expressions", state::isCastExpressionsCollapse, state::setCastExpressionsCollapse);
        checkBox("Variable declarations", state::isVarExpressionsCollapse, state::setVarExpressionsCollapse);
        checkBox("Getters and setters", state::isGetSetExpressionsCollapse, state::setGetSetExpressionsCollapse);
        checkBox("Control flow single-statement code block braces (read-only files)", state::isControlFlowSingleStatementCodeBlockCollapse, state::setControlFlowSingleStatementCodeBlockCollapse);
        checkBox("Control flow multi-statement code block braces (read-only files, deprecated)", state::isControlFlowMultiStatementCodeBlockCollapse, state::setControlFlowMultiStatementCodeBlockCollapse);
        checkBox("Compact control flow condition syntax", state::isCompactControlFlowSyntaxCollapse, state::setCompactControlFlowSyntaxCollapse);
        checkBox("Semicolons (read-only files)", state::isSemicolonsCollapse, state::setSemicolonsCollapse);
        checkBox("Asserts", state::isAssertsCollapse, state::setAssertsCollapse);
        checkBox("Display optional as kotlin null-safe (experimental)", state::isOptional, state::setOptional);
        checkBox("Display stream operations as groovy's spread operator (experimental)", state::isStreamSpread, state::setStreamSpread);
        checkBox("Display java bean as lombok (experimental)", state::isLombok, state::setLombok);
    }
}
