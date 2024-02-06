package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings;
import com.intellij.advancedExpressionFolding.expression.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.intellij.advancedExpressionFolding.extension.BuildExpressionExt.getAnyExpression;

public class ForStatementExpressionExt {
    @Nullable
    static Expression getForStatementExpression(@NotNull PsiForStatement element, @NotNull Document document) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        @Nullable PsiJavaToken lParenth = element.getLParenth();
        @Nullable PsiJavaToken rParenth = element.getRParenth();
        @Nullable PsiStatement initialization = element.getInitialization();
        @Nullable PsiStatement update = element.getUpdate();
        @Nullable PsiExpression condition = element.getCondition();
        if (settings.getState().getRangeExpressionsCollapse()
            && lParenth != null && rParenth != null
                && initialization instanceof PsiDeclarationStatement
                && ((PsiDeclarationStatement) initialization).getDeclaredElements().length == 1
                && ((PsiDeclarationStatement) initialization).getDeclaredElements()[0] instanceof PsiVariable
                && ((PsiVariable) ((PsiDeclarationStatement) initialization).getDeclaredElements()[0]).getInitializer() != null
                && update != null && update.getChildren().length == 1
                && update.getChildren()[0] instanceof PsiPostfixExpression
                && ((PsiPostfixExpression) update.getChildren()[0]).getOperand() instanceof PsiReferenceExpression
                && ((PsiPostfixExpression) update.getChildren()[0]).getOperationSign().getText().equals("++")
                && ((PsiPostfixExpression) update.getChildren()[0]).getOperand().getReference() != null
                && condition instanceof PsiBinaryExpression
                && ((PsiBinaryExpression) condition).getLOperand() instanceof PsiReferenceExpression
                && ((PsiBinaryExpression) condition).getLOperand().getReference() != null
                && ((PsiBinaryExpression) condition).getROperand() != null) {
            @SuppressWarnings("ConstantConditions")
            @Nullable PsiVariable updateVariable = (PsiVariable) ((PsiPostfixExpression) update.getChildren()[0]).getOperand().getReference().resolve();
            @SuppressWarnings("ConstantConditions")
            @Nullable PsiExpression conditionROperand = ((PsiBinaryExpression) condition).getROperand();
            @Nullable PsiReference reference = ((PsiBinaryExpression) condition).getLOperand().getReference();
            if (reference != null) {
                PsiVariable conditionVariable = (PsiVariable) reference.resolve();
                if (updateVariable != null && conditionROperand != null
                        && updateVariable == ((PsiDeclarationStatement) initialization).getDeclaredElements()[0]
                        && updateVariable == conditionVariable
                        && ("int".equals(updateVariable.getType().getCanonicalText())
                        || "long".equals(updateVariable.getType().getCanonicalText()))) {
                    Optional<PsiElement> identifier = Stream.of(((PsiDeclarationStatement) initialization).getDeclaredElements()[0].getChildren())
                            .filter(c -> c instanceof PsiIdentifier).findAny();
                    if (identifier.isPresent()) {
                        Variable variable = new Variable(identifier.get(), identifier.get().getTextRange(), null, identifier.get().getText(), false);
                        //noinspection ConstantConditions
                        @NotNull Expression start = getAnyExpression(
                                ((PsiVariable) ((PsiDeclarationStatement) initialization).getDeclaredElements()[0]).getInitializer(), document);
                        @NotNull Expression end = getAnyExpression(conditionROperand, document);
                        String sign = ((PsiBinaryExpression) condition).getOperationSign().getText();
                        if ("<".equals(sign) || "<=".equals(sign)) {
                            if (element.getBody() instanceof PsiBlockStatement
                                    && ((PsiBlockStatement) element.getBody()).getCodeBlock().getStatements().length > 0
                                    && ((PsiBlockStatement) element.getBody()).getCodeBlock().getStatements()[0] instanceof PsiDeclarationStatement
                                    && ((PsiDeclarationStatement) ((PsiBlockStatement) element.getBody()).getCodeBlock()
                                    .getStatements()[0]).getDeclaredElements().length == 1) {
                                if (start instanceof NumberLiteral && ((NumberLiteral) start).getNumber().equals(0)) {
                                    PsiVariable declaration = (PsiVariable) ((PsiDeclarationStatement) ((PsiBlockStatement) element.getBody())
                                            .getCodeBlock()
                                            .getStatements()[0]).getDeclaredElements()[0];
                                    @Nullable PsiIdentifier variableName = declaration.getNameIdentifier();
                                    @Nullable PsiExpression initializer = declaration.getInitializer();
                                    if (variableName != null
                                            && initializer instanceof PsiArrayAccessExpression
                                            && ((PsiArrayAccessExpression) initializer).getIndexExpression() instanceof PsiReferenceExpression
                                            && isReferenceTo(((PsiReferenceExpression) ((PsiArrayAccessExpression) initializer).getIndexExpression()), conditionVariable)
                                            && conditionROperand instanceof PsiReferenceExpression
                                            && ((PsiReferenceExpression) conditionROperand).getQualifierExpression() instanceof PsiReferenceExpression
                                            && ((PsiArrayAccessExpression) initializer).getArrayExpression() instanceof PsiReferenceExpression
                                            && isReferenceTo((PsiReferenceExpression) ((PsiReferenceExpression) conditionROperand).getQualifierExpression(),
                                            ((PsiReferenceExpression) ((PsiArrayAccessExpression) initializer).getArrayExpression()).resolve())) {
                                        // TODO: ((PsiArrayAccessExpression) initializer).getArrayExpression() can be a method call expression, e.g. getArgs()
                                        PsiExpression arrayExpression = ((PsiArrayAccessExpression) initializer)
                                                .getArrayExpression();
                                        List<PsiElement> references = SyntaxTraverser.psiTraverser(element.getBody()).filter(e -> e instanceof PsiReferenceExpression
                                                && ((PsiReferenceExpression) e).isReferenceTo(conditionVariable)).toList();
                                        //noinspection Duplicates
                                        if (references.size() == 1) {
                                            return new ForEachStatement(element, TextRange.create(
                                                    initialization.getTextRange().getStartOffset(),
                                                    declaration.getTextRange().getEndOffset()),
                                                    declaration.getTextRange(), variableName.getTextRange(),
                                                    arrayExpression.getTextRange()
                                            );
                                        } else {
                                            @Nullable PsiIdentifier indexName = conditionVariable.getNameIdentifier();
                                            boolean isFinal = Helper.calculateIfFinal(declaration) && Helper.calculateIfFinal(updateVariable);
                                            if (indexName != null) {
                                                return new ForEachIndexedStatement(element, TextRange.create(
                                                        initialization.getTextRange().getStartOffset() - 1,
                                                        declaration.getTextRange().getEndOffset()),
                                                        declaration.getTextRange(),
                                                        indexName.getTextRange(), variableName.getTextRange(),
                                                        arrayExpression.getTextRange(),
                                                        settings.getState().getVarExpressionsCollapse(),
                                                        isFinal);
                                            }
                                        }
                                    } else if (variableName != null
                                            && initializer instanceof PsiMethodCallExpression
                                            && ((PsiMethodCallExpression) initializer).getArgumentList().getExpressions().length == 1
                                            && ((PsiMethodCallExpression) initializer).getArgumentList().getExpressions()[0] instanceof PsiReferenceExpression
                                            && ((PsiReferenceExpression) ((PsiMethodCallExpression) initializer).getArgumentList().getExpressions()[0]).isReferenceTo(conditionVariable)
                                            && conditionROperand instanceof PsiMethodCallExpression
                                            && ((PsiMethodCallExpression) conditionROperand).getMethodExpression().getQualifierExpression() instanceof PsiReferenceExpression
                                            && ((PsiMethodCallExpression) initializer).getMethodExpression().getQualifierExpression() instanceof PsiReferenceExpression
                                            && Helper.isReferenceToReference((PsiReferenceExpression) ((PsiMethodCallExpression) conditionROperand).getMethodExpression().getQualifierExpression(), ((PsiReferenceExpression) ((PsiMethodCallExpression) initializer).getMethodExpression()
                                            .getQualifierExpression()))) {
                                        @Nullable PsiExpression arrayExpression = ((PsiMethodCallExpression) initializer).getMethodExpression().getQualifierExpression();
                                        if (arrayExpression != null) {
                                            List<PsiElement> references = SyntaxTraverser.psiTraverser(element.getBody()).filter(e -> e instanceof PsiReferenceExpression
                                                    && ((PsiReferenceExpression) e).isReferenceTo(conditionVariable)).toList();
                                            //noinspection Duplicates
                                            if (references.size() == 1) {
                                                return new ForEachStatement(element, TextRange.create(
                                                        initialization.getTextRange().getStartOffset(),
                                                        declaration.getTextRange().getEndOffset()),
                                                        declaration.getTextRange(), variableName.getTextRange(),
                                                        arrayExpression.getTextRange()
                                                );
                                            } else {
                                                @Nullable PsiIdentifier indexName = conditionVariable.getNameIdentifier();
                                                if (indexName != null) {
                                                    boolean isFinal = Helper.calculateIfFinal(declaration) && Helper.calculateIfFinal(updateVariable);
                                                    return new ForEachIndexedStatement(element, TextRange.create(
                                                            initialization.getTextRange().getStartOffset() - 1,
                                                            declaration.getTextRange().getEndOffset()),
                                                            declaration.getTextRange(),
                                                            indexName.getTextRange(), variableName.getTextRange(),
                                                            arrayExpression.getTextRange(),
                                                            settings.getState().getVarExpressionsCollapse(),
                                                            isFinal);
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                            int startOffset = lParenth.getTextRange().getStartOffset() + 1;
                            int endOffset = rParenth.getTextRange().getEndOffset() - 1;
                            return new ForStatement(element, TextRange.create(startOffset, endOffset), variable,
                                    start, true, end, "<=".equals(sign));
                        }
                    }
                }
            }
        }
        if (element.getCondition() != null
                && element.getLParenth() != null && element.getRParenth() != null
                && settings.getState().getCompactControlFlowSyntaxCollapse()) {
            return new CompactControlFlowExpression(element,
                    TextRange.create(element.getLParenth().getTextRange().getStartOffset(),
                            element.getRParenth().getTextRange().getEndOffset()));
        }
        return null;
    }

    static boolean isReferenceTo(@Nullable PsiReferenceExpression referenceExpression, @Nullable PsiElement element) {
        return referenceExpression != null && element != null && referenceExpression.isReferenceTo(element);
    }
}
