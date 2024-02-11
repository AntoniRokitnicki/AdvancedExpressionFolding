package com.intellij.advancedExpressionFolding.extension;

import <fold text='...' expand='false'>com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings;
import com.intellij.advancedExpressionFolding.UnexpectedException;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.SemicolonExpression;
import com.intellij.advancedExpressionFolding.expression.SyntheticExpressionImpl;
import com.intellij.advancedExpressionFolding.expression.TypeCast;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.openapi.util.Key;
import com.intellij.psi.*;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiModificationTracker;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static com.intellij.advancedExpressionFolding.extension.OtherExt.*;</fold>

public class LetReturnIt {
    static final Map<String, Key<CachedValue<Expression>>> KEY_MAP = new WeakHashMap<>();
    static final FoldingDescriptor[] NO_DESCRIPTORS = new FoldingDescriptor[0];


    // 💩💩💩 Define the AdvancedExpressionFoldingProvider extension point
    @Contract("_, _, true -> !null")
    static Expression buildExpression(@NotNull PsiElement element, @NotNull Document document, boolean synthetic) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>AdvancedExpressionFoldingSettings</fold> settings = AdvancedExpressionFoldingSettings.<fold text='instance' expand='false'>getInstance()</fold>;
        if <fold text='' expand='false'>(</fold>element instanceof PsiForStatement<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>ForStatementExpressionExt.getForStatementExpression(<fold text='' expand='false'>(PsiForStatement) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiForeachStatement<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getForeachStatementExpression(<fold text='' expand='false'>(PsiForeachStatement) </fold>element)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiIfStatement<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getIfExpression(<fold text='' expand='false'>(PsiIfStatement) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiWhileStatement<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getWhileStatement(<fold text='' expand='false'>(PsiWhileStatement) </fold>element)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiJavaToken && <fold text='' expand='false'>((PsiJavaToken) </fold>element<fold text='.' expand='false'>).</fold><fold text='tokenType' expand='false'>getTokenType()</fold> == JavaTokenType.SEMICOLON
                && settings.<fold text='state' expand='false'>getState()</fold>.<fold text='semicolonsCollapse' expand='false'>getSemicolonsCollapse()</fold>
                && !element.<fold text='writable' expand='false'>isWritable()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            return new SemicolonExpression(element, element.<fold text='textRange' expand='false'>getTextRange()</fold>);
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiCatchSection<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getCatchStatement(<fold text='' expand='false'>(PsiCatchSection) </fold>element)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiDoWhileStatement<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getDoWhileStatement(<fold text='' expand='false'>(PsiDoWhileStatement) </fold>element)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiSwitchStatement<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getSwitchStatement(<fold text='' expand='false'>(PsiSwitchStatement) </fold>element)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiArrayAccessExpression && settings.<fold text='state' expand='false'>getState()</fold>.<fold text='getExpressionsCollapse' expand='false'>getGetExpressionsCollapse()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getArrayAccessExpression(<fold text='' expand='false'>(PsiArrayAccessExpression) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiMethodCallExpression<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>MethodCallExpressionExt.getMethodCallExpression(<fold text='' expand='false'>(PsiMethodCallExpression) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiReferenceExpression<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>Helper.getReferenceExpression(<fold text='' expand='false'>(PsiReferenceExpression) </fold>element)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiNewExpression<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>NewExpressionExt.getNewExpression(<fold text='' expand='false'>(PsiNewExpression) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiLiteralExpression<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>Helper.getLiteralExpression(<fold text='' expand='false'>(PsiLiteralExpression) </fold>element)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiAssignmentExpression<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>AssignmentExpressionExt.getAssignmentExpression(<fold text='' expand='false'>(PsiAssignmentExpression) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiPolyadicExpression<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getPolyadicExpression(<fold text='' expand='false'>(PsiPolyadicExpression) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiBinaryExpression<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>BinaryExpressionExt.getBinaryExpression(<fold text='' expand='false'>(PsiBinaryExpression) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiConditionalExpression<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getConditionalExpression(<fold text='' expand='false'>(PsiConditionalExpression) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiPrefixExpression<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>PrefixExpressionExt.getPrefixExpression(<fold text='' expand='false'>(PsiPrefixExpression) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiParenthesizedExpression
                && settings.<fold text='state' expand='false'>getState()</fold>.<fold text='castExpressionsCollapse' expand='false'>getCastExpressionsCollapse()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            if <fold text='' expand='false'>(</fold><fold text='' expand='false'>((PsiParenthesizedExpression) </fold>element<fold text='.' expand='false'>).</fold><fold text='expression' expand='false'>getExpression()</fold> instanceof PsiTypeCastExpression e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>TypeCast</fold> typeCast = getTypeCastExpression(e, document);
                if <fold text='' expand='false'>(</fold>typeCast != null<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    return new TypeCast(element, element.<fold text='textRange' expand='false'>getTextRange()</fold>, typeCast.<fold text='object' expand='false'>getObject()</fold>);
                }</fold>
            }</fold>
            <fold text='val' expand='false'>@Nullable PsiExpression</fold> e = <fold text='' expand='false'>((PsiParenthesizedExpression) </fold>element<fold text='.' expand='false'>).</fold><fold text='expression' expand='false'>getExpression()</fold>;
            if (e != null) <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'><fold text='' expand='true'>@Nullable Expression</fold> expression = </fold>getExpression(e, document, synthetic)<fold text='?.let { return it }' expand='true'>;
                if (expression != null) <fold text='{...}' expand='true'>{
                    return expression;
                }</fold></fold>
            }</fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiTypeCastExpression
                && settings.<fold text='state' expand='false'>getState()</fold>.<fold text='castExpressionsCollapse' expand='false'>getCastExpressionsCollapse()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>TypeCast</fold> expression = </fold>getTypeCastExpression(<fold text='' expand='false'>(PsiTypeCastExpression) </fold>element, document)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>settings.<fold text='state' expand='false'>getState()</fold>.<fold text='varExpressionsCollapse' expand='false'>getVarExpressionsCollapse()</fold>
                && element instanceof PsiVariable
                && (element.<fold text='parent' expand='false'>getParent()</fold> instanceof PsiDeclarationStatement
                || element.<fold text='parent' expand='false'>getParent()</fold> instanceof PsiForeachStatement)<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getVariableDeclaration(<fold text='' expand='false'>(PsiVariable) </fold>element)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiCodeBlock<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>getCodeBlockExpression(<fold text='' expand='false'>(PsiCodeBlock) </fold>element)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>
        if <fold text='' expand='false'>(</fold>element instanceof PsiClass psiClass<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'><fold text='' expand='true'>Expression</fold> expression = </fold>PsiClassExt.createExpression(psiClass)<fold text='?.let { return it }' expand='true'>;
            if (expression != null) <fold text='{...}' expand='true'>{
                return expression;
            }</fold></fold>
        }</fold>

        if <fold text='' expand='false'>(</fold>synthetic<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>ArrayList<Expression></fold> children = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
            Helper.findChildExpressions(element, children, document);
            return new SyntheticExpressionImpl(element, element.<fold text='textRange' expand='false'>getTextRange()</fold>, document.getText(element.<fold text='textRange' expand='false'>getTextRange()</fold>), children);
        }</fold>
        return null;
    }</fold>

    @Contract("_, _, true -> !null")
    static Expression getExpression(@NotNull PsiElement element, @NotNull Document document, boolean synthetic) <fold text='{...}' expand='true'>{
        return CachedValuesManager.getCachedValue(element, getKey(document, synthetic),
                () -> CachedValueProvider.Result.create(buildExpression(element, document, synthetic), PsiModificationTracker.MODIFICATION_COUNT)
        );
    }</fold>

    static Key<CachedValue<Expression>> getKey(Document document, boolean synthetic) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>var</fold> key =<fold text=' "${' expand='false'> </fold>document.hashCode()<fold text='}' expand='false'> + "</fold> <fold text='${' expand='false'>" + (</fold>synthetic ? 1 : 0<fold text='}")' expand='false'>)</fold>;
        return KEY_MAP.computeIfAbsent(key, Key::create);
    }</fold>

    <fold text='@{...}' expand='true'>@SuppressWarnings("WeakerAccess")
    @NotNull</fold>
    public static Expression getAnyExpression(@NotNull PsiElement element, @Nullable Document document) throws IndexNotReadyException <fold text='{...}' expand='true'>{
        //noinspection ConstantConditions
        return getExpression(element, document, true);
    }</fold>

    <fold text='/***/' expand='true'>/**

     */</fold>
    <fold text='@{...}' expand='true'>@SuppressWarnings("WeakerAccess")
    @Nullable</fold>
    public static Expression getNonSyntheticExpression(@NotNull PsiElement element, @Nullable Document document) throws IndexNotReadyException <fold text='{...}' expand='true'>{
        //noinspection ConstantConditions
        return getExpression(element, document, false);
    }</fold>

    @NotNull
    public static FoldingDescriptor @NotNull [] collectFoldRegionsRecursively(@NotNull PsiElement element, @NotNull Document document, boolean quick) <fold text='{...}' expand='true'>{
        <fold text='var' expand='false'>PsiElement</fold> lastElement = element;
        <fold text='var' expand='false'>List<FoldingDescriptor></fold> allDescriptors = null;
        try <fold text='{...}' expand='true'>{
            <fold text='val' expand='false'>@Nullable Expression</fold> expression = getNonSyntheticExpression(element, document);
            if <fold text='' expand='false'>(</fold>expression != null && expression.supportsFoldRegions(document, null)<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                <fold text='val' expand='false'>FoldingDescriptor[]</fold> descriptors = expression.buildFoldRegions(expression.<fold text='element' expand='false'>getElement()</fold>, document, null);
                allDescriptors = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
                Collections.addAll(allDescriptors, descriptors);
            }</fold>
            if <fold text='' expand='false'>(</fold>expression == null || expression.<fold text='nested' expand='false'>isNested()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                for <fold text='' expand='false'>(</fold><fold text='val' expand='false'>PsiElement</fold> child : element.<fold text='children' expand='false'>getChildren()</fold><fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                    lastElement = child;
                    <fold text='val' expand='false'>FoldingDescriptor[]</fold> descriptors = collectFoldRegionsRecursively(child, document, quick);
                    if <fold text='' expand='false'>(</fold>descriptors.length > 0<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                        if <fold text='' expand='false'>(</fold>allDescriptors == null<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                            allDescriptors = <fold text='[]' expand='false'>new ArrayList<>()</fold>;
                        }</fold>
                        Collections.addAll(allDescriptors, descriptors);
                    }</fold>
                }</fold>
            }</fold>
        }</fold> catch <fold text='' expand='false'>(</fold>IndexNotReadyException | ProcessCanceledException ignore<fold text='' expand='false'>)</fold> {
        } catch <fold text='' expand='false'>(</fold>Exception e<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
            <fold text='var' expand='false'>String</fold> fileName = null;
            <fold text='val' expand='false'>PsiFile</fold> containingFile = lastElement.<fold text='containingFile' expand='false'>getContainingFile()</fold>;
            if <fold text='' expand='false'>(</fold>containingFile != null<fold text='' expand='false'>)</fold> <fold text='{...}' expand='true'>{
                fileName = containingFile.<fold text='name' expand='false'>getName()</fold>;
            }</fold>
            throw new UnexpectedException(lastElement.<fold text='class' expand='false'>getClass()</fold>, lastElement.<fold text='text' expand='false'>getText()</fold>, lastElement.<fold text='textRange' expand='false'>getTextRange()</fold>, fileName, e);
        }</fold>
        return <fold text='' expand='false'>allDescriptors != null ? </fold>allDescriptors<fold text='?.' expand='false'>.</fold>toArray(NO_DESCRIPTORS)<fold text=' ?: ' expand='false'> : </fold>NO_DESCRIPTORS;
    }</fold>

}
