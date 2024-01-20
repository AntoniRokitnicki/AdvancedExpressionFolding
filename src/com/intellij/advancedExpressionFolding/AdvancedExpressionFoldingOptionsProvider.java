package com.intellij.advancedExpressionFolding;

public class AdvancedExpressionFoldingOptionsProvider extends com.intellij.openapi.options.BeanConfigurable<AdvancedExpressionFoldingSettings.State> implements com.intellij.application.options.editor.CodeFoldingOptionsProvider {
    protected AdvancedExpressionFoldingOptionsProvider() {
        super(AdvancedExpressionFoldingSettings.getInstance().getState());
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        AdvancedExpressionFoldingSettings.State state = settings.getState();

        checkBox("Math, BigDecimal and BigInteger expressions (deprecated)", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("List.subList and String.substring expressions", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Object.equals and Comparable.compareTo expressions", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Java.time isBefore/isAfter expressions", state::isComparingLocalDatesCollapse, state::setComparingLocalDatesCollapse);
        checkBox("LocalDate.of literals (e.g. 2018-02-12)" , state::isLocalDateLiteralCollapse, state::setLocalDateLiteralCollapse);
        checkBox("Postfix LocalDate literals (e.g. 2018Y-02M-12D) " , state::isLocalDateLiteralPostfix, state::setLocalDateLiteralPostfix);
        checkBox("List.get, List.set, Map.get and Map.put expressions, array and list literals", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("For loops, range expressions", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Null safe calls", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Type cast expressions", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Variable declarations", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Getters and setters", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Control flow single-statement code block braces (read-only files)", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Control flow multi-statement code block braces (read-only files, deprecated)", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Compact control flow condition syntax", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Semicolons (read-only files)", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Asserts", state::isArithmeticExpressionsCollapse, state::setArithmeticExpressionsCollapse);
        checkBox("Display optional as kotlin null-safe (experimental)", state::isOptional, state::setOptional);
    }
}
