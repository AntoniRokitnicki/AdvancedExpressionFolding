package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings;
import com.intellij.advancedExpressionFolding.expression.Random;
import com.intellij.advancedExpressionFolding.expression.*;
import com.intellij.advancedExpressionFolding.expression.custom.GetterRecord;
import com.intellij.advancedExpressionFolding.expression.optional.*;
import com.intellij.advancedExpressionFolding.expression.stream.StreamExpression;
import com.intellij.advancedExpressionFolding.expression.stream.StreamFilterNotNull;
import com.intellij.advancedExpressionFolding.expression.stream.StreamMapCall;
import com.intellij.advancedExpressionFolding.expression.stream.StreamMapCallParam;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.intellij.advancedExpressionFolding.extension.BaseExtension.isInt;
import static com.intellij.advancedExpressionFolding.extension.Consts.*;
import static com.intellij.advancedExpressionFolding.extension.Helper.getReferenceExpression;
import static com.intellij.advancedExpressionFolding.extension.PropertyUtil.guessPropertyName;

public class MethodCallExpressionExt {

    @SuppressWarnings({"RedundantIfStatement", "SwitchStatementWithTooFewBranches"})
    @Nullable
    static Expression getMethodCallExpression(PsiMethodCallExpression element, @NotNull Document document) {
        @NotNull AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        PsiReferenceExpression referenceExpression = element.getMethodExpression();
        Optional<PsiElement> identifier = Stream.of(referenceExpression.getChildren())
                .filter(c -> c instanceof PsiIdentifier).findAny();
        @Nullable PsiExpression qualifier = element.getMethodExpression().getQualifierExpression();
        if (identifier.isPresent() && SUPPORTED_METHODS.contains(identifier.get().getText())) {
            PsiMethod method = (PsiMethod) referenceExpression.resolve();
            if (method != null) {
                PsiClass psiClass = method.getContainingClass();
                if (psiClass != null && psiClass.getQualifiedName() != null) {
                    String className = Helper.eraseGenerics(psiClass.getQualifiedName());
                    BuilderShiftExt.markIfBuilder(element, psiClass);
                    if ((SUPPORTED_CLASSES.contains(className) || UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS.contains(method.getName()))
                            && qualifier != null) {
                        @NotNull Expression qualifierExpression = BuildExpressionExt.getAnyExpression(qualifier, document);
                        String methodName = identifier.get().getText();
                        if (methodName.equals("asList") || methodName.equals("singletonList")) {
                            if (!methodName.equals("asList") ||
                                    element.getArgumentList().getExpressions().length != 1 ||
                                    !(element.getArgumentList().getExpressions()[0].getType() instanceof PsiArrayType)) {
                                if (settings.getState().getGetExpressionsCollapse()) {
                                    return new ListLiteral(element, element.getTextRange(),
                                            Stream.of(element.getArgumentList().getExpressions())
                                                    .map(e -> BuildExpressionExt.getAnyExpression(e, document)).collect(
                                                    Collectors.toList()));
                                }
                            }
                        } else if (element.getArgumentList().getExpressions().length == 1) {
                            @NotNull PsiExpression argument = element.getArgumentList().getExpressions()[0];
                            @NotNull Expression argumentExpression = BuildExpressionExt.getAnyExpression(argument, document);
                            switch (methodName) {
                                case "filter":
                                    switch (className) {
                                        case "java.util.stream.Stream":
                                            if (settings.getState().getStreamSpread()) {
                                                if (argumentExpression instanceof SyntheticExpressionImpl syn && syn.getText().equals("Objects::nonNull")) {
                                                    return new StreamFilterNotNull(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                                }
                                            }
                                    }
                                    return null;
                                case "ofNullable":
                                    switch (className) {
                                        case "java.util.Optional":
                                            if (settings.getState().getOptional() && Helper.hasOptionalChainOperations(element)) {
                                                return new OptionalOfNullable(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                            }
                                    }
                                    return null;
                                case "of":
                                    switch (className) {
                                        case "java.util.Optional":
                                            if (settings.getState().getOptional()) {
                                                return new OptionalOf(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                            }
                                    }
                                    return null;
                                case "map":
                                case "flatMap":
                                    switch (className) {
                                        case "java.util.Optional":
                                            if (settings.getState().getOptional() &&
                                                    (argumentExpression instanceof OptionalMapSafeCallParam || Helper.isPureMethodReference(element))) {
                                                boolean flatMap = methodName.equals("flatMap");
                                                if (qualifierExpression instanceof OptionalOf) {
                                                    return new OptionalMapCall(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression), flatMap);
                                                }
                                                return new OptionalMapSafeCall(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression), flatMap);
                                            }
                                        case "java.util.stream.Stream":
                                            if (settings.getState().getStreamSpread() &&
                                                    (argumentExpression instanceof StreamMapCallParam || Helper.isPureMethodReference(element))) {
                                                boolean flatMap = methodName.equals("flatMap");
                                                var textRange = new TextRange(identifier.get().getTextRange().getStartOffset(), element.getTextRange().getEndOffset());
                                                return new StreamMapCall(element, textRange, Arrays.asList(qualifierExpression, argumentExpression), flatMap);
                                            }
                                    }
                                    return null;
                                case "orElseGet":
                                case "orElse":
                                    switch (className) {
                                        case "java.util.Optional":
                                            if (settings.getState().getOptional() &&
                                                    Helper.findChildByTypeHierarchy(element, PsiExpressionList.class, PsiExpressionList.class)
                                                            .isPresent()) {
                                                return new OptionalOrElseElvis(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                            }
                                    }
                                    return null;
                                case "add":
                                    switch (className) {
                                        case "java.util.List":
                                        case "java.util.ArrayList":
                                        case "java.util.Set":
                                        case "java.util.HashSet":
                                        case "java.util.Map":
                                        case "java.util.HashMap":
                                        case "java.util.Collection":
                                            if (element.getParent() instanceof PsiStatement && settings.getState().getConcatenationExpressionsCollapse()) {
                                                return new AddAssignForCollection(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                            } else {
                                                return null;
                                            }
                                        default:
                                            return new Add(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));

                                    }
                                case "remove":
                                    if (method.getParameterList().getParameters().length == 1
                                            && !isInt(method.getParameterList().getParameters()[0].getType())) {
                                        if (element.getParent() instanceof PsiStatement && settings.getState().getConcatenationExpressionsCollapse()) {
                                            return new RemoveAssignForCollection(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                        }
                                    }
                                    break;
                                case "subtract":
                                    return new Subtract(element, element.getTextRange(),
                                            Arrays.asList(qualifierExpression, argumentExpression));
                                case "multiply":
                                    return new Multiply(element, element.getTextRange(),
                                            Arrays.asList(qualifierExpression, argumentExpression));
                                case "divide":
                                    return new Divide(element, element.getTextRange(),
                                            Arrays.asList(qualifierExpression, argumentExpression));
                                case "remainder":
                                case "mod":
                                    return new Remainder(element, element.getTextRange(),
                                            Arrays.asList(qualifierExpression, argumentExpression));
                                case "andNot":
                                    return new And(element, element.getTextRange(),
                                            Arrays.asList(qualifierExpression, new Not(element, argumentExpression.getTextRange(),
                                                    Collections.singletonList(argumentExpression))));
                                case "pow":
                                    return new Pow(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                case "min":
                                    if ("java.util.stream.Stream".equals(className)) {
                                       return null;
                                    }
                                    return new Min(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                case "max":
                                    if ("java.util.stream.Stream".equals(className)) {
                                        return null;
                                    }
                                    return new Max(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                case "gcd":
                                    return new Gcd(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                case "and":
                                    return new And(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                case "or":
                                    return new Or(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                case "xor":
                                    return new Xor(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                case "shiftLeft":
                                    return new ShiftLeft(element, element.getTextRange(),
                                            Arrays.asList(qualifierExpression, argumentExpression));
                                case "shiftRight":
                                    return new ShiftRight(element, element.getTextRange(),
                                            Arrays.asList(qualifierExpression, argumentExpression));
                                case "equals":
                                    if (settings.getState().getComparingExpressionsCollapse()) {
                                        return new Equal(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                    } else {
                                        break;
                                    }
                                case "append":
                                    if (settings.getState().getConcatenationExpressionsCollapse()) {
                                        if (qualifierExpression instanceof Append) {
                                            List<Expression> operands = new ArrayList<>(((Append) qualifierExpression).getOperands());
                                            operands.add(argumentExpression);
                                            return new Append(element, element.getTextRange(),
                                                    operands, element.getParent() instanceof PsiStatement);
                                        } else {
                                            if (qualifierExpression instanceof StringLiteral
                                                    && ((StringLiteral) qualifierExpression).getString().isEmpty()) {
                                                return new Append(element, element.getTextRange(),
                                                        Collections.singletonList(argumentExpression), element.getParent() instanceof PsiStatement);
                                            } else {
                                                return new Append(element, element.getTextRange(),
                                                        Arrays.asList(qualifierExpression, argumentExpression), element.getParent() instanceof PsiStatement);
                                            }
                                        }
                                    } else {
                                        break;
                                    }
                                case "charAt":
                                    if (settings.getState().getGetExpressionsCollapse()) {
                                        return new Get(element, element.getTextRange(), qualifierExpression,
                                                argumentExpression, Get.Style.NORMAL);
                                    } else {
                                        break;
                                    }
                                case "get":
                                    if (argumentExpression instanceof NumberLiteral && ((NumberLiteral) argumentExpression).getNumber().equals(0)) {
                                        /*return new Get(element, element.getTextRange(), qualifierExpression,
                                                argumentExpression, Get.Style.FIRST)*/
                                        return null;
                                    } else if (argument instanceof PsiBinaryExpression a2b) {
                                        NumberLiteral position = Helper.getSlicePosition(element, qualifierExpression, a2b, document);
                                        if (position != null && position.getNumber().equals(-1)) {
                                            if (settings.getState().getGetExpressionsCollapse()) {
                                                return new Get(element, element.getTextRange(), qualifierExpression,
                                                        argumentExpression, Get.Style.LAST);
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                    if (settings.getState().getGetExpressionsCollapse()) {
                                        return new Get(element, element.getTextRange(), qualifierExpression,
                                                argumentExpression, Get.Style.NORMAL);
                                    } else {
                                        break;
                                    }
                                case "subList":
                                case "substring":
                                    if (settings.getState().getSlicingExpressionsCollapse()) {
                                        if (argument instanceof PsiBinaryExpression) {
                                            NumberLiteral position = Helper.getSlicePosition(element,
                                                    qualifierExpression, (PsiBinaryExpression) argument, document);
                                            if (position != null) {
                                                return new Slice(element, element.getTextRange(),
                                                        Arrays.asList(qualifierExpression, position));
                                            }
                                        }
                                        return new Slice(element, element.getTextRange(),
                                                Arrays.asList(qualifierExpression, argumentExpression));
                                    } else {
                                        break;
                                    }
                                case "addAll":
                                    if (settings.getState().getConcatenationExpressionsCollapse()) {
                                        return new AddAssignForCollection(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                    } else {
                                        break;
                                    }
                                case "removeAll":
                                    if (settings.getState().getConcatenationExpressionsCollapse()) {
                                        return new RemoveAssignForCollection(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                    } else {
                                        break;
                                    }
                                case "collect":
                                    if (argument instanceof PsiMethodCallExpression
                                            && Helper.startsWith(((PsiMethodCallExpression) argument).getMethodExpression().getReferenceName(), "to")) {
                                        @Nullable PsiExpression q = ((PsiMethodCallExpression) argument).getMethodExpression().getQualifierExpression();
                                        if (q instanceof PsiReferenceExpression && Objects.equals(((PsiReferenceExpression) q).getReferenceName(), "Collectors")) {
                                            Optional<PsiElement> i = Arrays.stream(((PsiMethodCallExpression) argument).getMethodExpression().getChildren()).filter(c -> c instanceof PsiIdentifier && c.getText().startsWith("to")).findAny();
                                            if (i.isPresent()) {
                                                if (settings.getState().getConcatenationExpressionsCollapse()) {
                                                    return new Collect(element, TextRange.create(identifier.get().getTextRange().getStartOffset(),
                                                            element.getTextRange().getEndOffset()), qualifierExpression,
                                                            TextRange.create(i.get().getTextRange().getStartOffset(),
                                                                    argument.getTextRange().getEndOffset()));
                                                }
                                            }
                                        }
                                    }
                                    break;
                                case "stream":
                                    if (!className.equals("java.util.Optional") &&
                                            element.getParent() instanceof PsiReferenceExpression &&
                                            ((PsiReferenceExpression) element.getParent()).getQualifierExpression() == element) {
                                        if (settings.getState().getConcatenationExpressionsCollapse()) {
                                            return new ArrayStream(element, TextRange.create(
                                                    element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset()), argumentExpression);
                                        }
                                    }
                                    break;
                                // LocalDate handling
                                case "isBefore":
                                    if (settings.getState().getComparingLocalDatesCollapse()) {
                                        return new Less(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                    } else {
                                        break;
                                    }

                                case "isAfter":
                                    if (settings.getState().getComparingLocalDatesCollapse()) {
                                        return new Greater(element, element.getTextRange(), Arrays.asList(qualifierExpression, argumentExpression));
                                    } else {
                                        break;
                                    }
                            }
                        } else if (element.getArgumentList().getExpressions().length == 0) {
                            switch (methodName) {
                                case "get":
                                case "orElseThrow":
                                    switch (className) {
                                        case "java.util.Optional":
                                            if (settings.getState().getOptional()) {
                                                return new OptionalNotNullAssertionGet(element, identifier.get().getTextRange(), qualifierExpression);
                                            }
                                    }
                                    return null;
                                case "plus":
                                    return qualifierExpression;
                                case "negate":
                                    return new Negate(element, element.getTextRange(), Collections.singletonList(qualifierExpression));
                                case "not":
                                    return new Not(element, element.getTextRange(), Collections.singletonList(qualifierExpression));
                                case "abs":
                                    return new Abs(element, element.getTextRange(), Collections.singletonList(qualifierExpression));
                                case "signum":
                                    return new Signum(element, element.getTextRange(), Collections.singletonList(qualifierExpression));
                                case "stream":
                                    if (!className.equals("java.util.Optional") &&
                                            element.getParent() instanceof PsiReferenceExpression
                                            && ((PsiReferenceExpression) element.getParent()).getQualifierExpression() == element
                                            && settings.getState().getConcatenationExpressionsCollapse()) {
                                        return new StreamExpression(element, TextRange.create(identifier.get().getTextRange().getStartOffset(),
                                                element.getTextRange().getEndOffset()));
                                    } else {
                                        break;
                                    }
                                case "toString": // TODO: Generalize for literals and variables
                                    if (qualifierExpression instanceof Append append) {
                                        return new Append(element, element.getTextRange(), append.getOperands(), element.getParent() instanceof PsiStatement);
                                    } else if (qualifierExpression instanceof StringLiteral stringLiteral) {
                                        return new StringLiteral(element, element.getTextRange(), stringLiteral.getString());
                                    } else if (qualifierExpression instanceof NumberLiteral numberLiteral) {
                                        return new NumberLiteral(element, element.getTextRange(), numberLiteral.getNumberTextRange(), numberLiteral.getNumber(), true);
                                    } else if (qualifierExpression instanceof Variable variable) {
                                        return new Variable(element, element.getTextRange(), variable.getTextRange(), variable.getName(), true);
                                    } else {
                                        break;
                                    }
                            }
                        } else if (element.getArgumentList().getExpressions().length == 2) {
                            PsiExpression a1 = element.getArgumentList().getExpressions()[0];
                            PsiExpression a2 = element.getArgumentList().getExpressions()[1];
                            @NotNull Expression a1Expression = BuildExpressionExt.getAnyExpression(a1, document);
                            @NotNull Expression a2Expression = BuildExpressionExt.getAnyExpression(a2, document);
                            switch (methodName) {
                                case "put":
                                case "set":
                                    if (element.getParent() instanceof PsiStatement && settings.getState().getGetExpressionsCollapse()) {
                                        return new Put(element, element.getTextRange(), qualifierExpression, a1Expression, a2Expression);
                                    } else {
                                        break;
                                    }
                                case "atan2":
                                    return new Atan2(element, element.getTextRange(), Arrays.asList(qualifierExpression, a1Expression,
                                            a2Expression));
                                case "substring":
                                case "subList":
                                    if (settings.getState().getSlicingExpressionsCollapse()) {
                                        if (a1 instanceof PsiBinaryExpression) {
                                            NumberLiteral p1 = Helper.getSlicePosition(element, qualifierExpression, (PsiBinaryExpression) a1, document);
                                            if (p1 != null) {
                                                if (a2Expression instanceof NumberLiteral) {
                                                    return new Slice(element, element.getTextRange(), Arrays.asList(qualifierExpression,
                                                            p1, a2Expression));
                                                } else if (a2 instanceof PsiBinaryExpression) {
                                                    NumberLiteral p2 = Helper.getSlicePosition(element, qualifierExpression, (PsiBinaryExpression) a2, document);
                                                    if (p2 != null) {
                                                        return new Slice(element, element.getTextRange(),
                                                                Arrays.asList(qualifierExpression, p1, p2));
                                                    }
                                                } else //noinspection Duplicates
                                                    if (a2 instanceof PsiMethodCallExpression a2m) {
                                                        @NotNull PsiReferenceExpression a2me = a2m.getMethodExpression();
                                                        Optional<PsiElement> a2i = Stream.of(a2me.getChildren())
                                                                .filter(c -> c instanceof PsiIdentifier).findAny();
                                                        @Nullable PsiExpression q = a2me.getQualifierExpression();
                                                        if (a2i.isPresent() && q != null && (a2i.get().getText().equals("length") || a2i.get()
                                                                .getText().equals("size"))) {
                                                            @NotNull Expression a2qe = BuildExpressionExt.getAnyExpression(q, document);
                                                            if (a2qe.equals(qualifierExpression)) {
                                                                return new Slice(element, element.getTextRange(), Arrays.asList(qualifierExpression, p1));
                                                            }
                                                        }
                                                    }
                                            }
                                        }
                                        if (a2 instanceof @NotNull PsiBinaryExpression a2b) {
                                            @Nullable NumberLiteral position = Helper.getSlicePosition(element, qualifierExpression, a2b, document);
                                            if (position != null) {
                                                return new Slice(element, element.getTextRange(), Arrays.asList(qualifierExpression, a1Expression,
                                                        position));
                                            }
                                        } else //noinspection Duplicates
                                            if (a2 instanceof @NotNull PsiMethodCallExpression a2m) {
                                                @NotNull PsiReferenceExpression a2me = a2m.getMethodExpression();
                                                Optional<PsiElement> a2i = Stream.of(a2me.getChildren())
                                                        .filter(c -> c instanceof PsiIdentifier).findAny();
                                                @Nullable PsiExpression q = a2me.getQualifierExpression();
                                                if (a2i.isPresent() && q != null && (a2i.get().getText().equals("length") || a2i.get()
                                                        .getText().equals("size"))) {
                                                    @NotNull Expression a2qe = BuildExpressionExt.getAnyExpression(q, document);
                                                    if (a2qe.equals(qualifierExpression)) {
                                                        return new Slice(element, element.getTextRange(), Arrays.asList(qualifierExpression, a1Expression));
                                                    }
                                                }
                                            }
                                        return new Slice(element, element.getTextRange(), Arrays.asList(qualifierExpression, a1Expression, a2Expression));
                                    }
                                    break;
                            }
                        }
                        else if (element.getArgumentList().getExpressions().length == 3) {
                            PsiExpression a1 = element.getArgumentList().getExpressions()[0];
                            PsiExpression a2 = element.getArgumentList().getExpressions()[1];
                            PsiExpression a3 = element.getArgumentList().getExpressions()[2];
                            if (methodName.equals("of") && className.equals("java.time.LocalDate") && settings.getState().getLocalDateLiteralCollapse()) {
                                if (a1 instanceof PsiLiteralExpression year && a2 instanceof PsiLiteralExpression month && a3 instanceof PsiLiteralExpression day) {
                                    return new LocalDateLiteral(element, element.getTextRange(), year, month, day);
                                }
                            }
                        }
                        if (element.getArgumentList().getExpressions().length == 1) {
                            PsiExpression argument = element.getArgumentList().getExpressions()[0];
                            if (method.getName().equals("valueOf") && argument instanceof PsiLiteralExpression) {
                                return NewExpressionExt.getConstructorExpression(element, (PsiLiteralExpression) argument,
                                        className);
                            } else if (method.getName().equals("valueOf") && argument instanceof PsiReferenceExpression) {
                                Expression refExpr = getReferenceExpression((PsiReferenceExpression) argument);
                                if (refExpr instanceof Variable) {
                                    return new Variable(element, element.getTextRange(), refExpr.getTextRange(), ((Variable) refExpr).getName(), true);
                                } else {
                                    return null;
                                }
                            } else {
                                @NotNull Expression argumentExpression = BuildExpressionExt.getAnyExpression(argument, document);
                                switch (method.getName()) {
                                    case "abs":
                                        return new Abs(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "acos":
                                        return new Acos(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "asin":
                                        return new Asin(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "atan":
                                        return new Atan(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "cbrt":
                                        return new Cbrt(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "ceil":
                                        return new Ceil(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "cos":
                                        return new Cos(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "cosh":
                                        return new Cosh(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "floor":
                                        return new Floor(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "log":
                                        return new Log(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "log10":
                                        return new Log10(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "rint":
                                        return new Rint(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "round":
                                        return new Round(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "sin":
                                        return new Sin(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "sinh":
                                        return new Sinh(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "sqrt":
                                        return new Sqrt(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "tan":
                                        return new Tan(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "tanh":
                                        return new Tanh(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "toDegrees":
                                        return new ToDegrees(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "toRadians":
                                        return new ToRadians(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "ulp":
                                        return new Ulp(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "exp":
                                        return new Exp(element, element.getTextRange(), Collections.singletonList(argumentExpression));
                                    case "unmodifiableSet":
                                        if (argumentExpression instanceof SetLiteral setLiteral && settings.getState().getGetExpressionsCollapse()) {
                                            return new SetLiteral(element, element.getTextRange(),
                                                    setLiteral.getFirstBracesRange(), setLiteral.getSecondBracesRange(),
                                                    setLiteral.getOperands());
                                        } else {
                                            break;
                                        }
                                    case "unmodifiableList":
                                        if (argumentExpression instanceof ListLiteral setLiteral && settings.getState().getGetExpressionsCollapse()) {
                                            return new ListLiteral(element, element.getTextRange(),
                                                    setLiteral.getItems());
                                        } else {
                                            break;
                                        }
                                }
                            }
                        } else if (element.getArgumentList().getExpressions().length == 2) {
                            PsiExpression a1 = element.getArgumentList().getExpressions()[0];
                            @NotNull Expression a1Expression = BuildExpressionExt.getAnyExpression(a1, document);
                            PsiExpression a2 = element.getArgumentList().getExpressions()[1];
                            @NotNull Expression a2Expression = BuildExpressionExt.getAnyExpression(a2, document);
                            switch (methodName) {
                                case "min":
                                    return new Min(element, element.getTextRange(), Arrays.asList(a1Expression, a2Expression));
                                case "max":
                                    return new Max(element, element.getTextRange(), Arrays.asList(a1Expression, a2Expression));
                                case "pow":
                                    return new Pow(element, element.getTextRange(), Arrays.asList(a1Expression, a2Expression));
                                case "addAll":
                                    if (settings.getState().getConcatenationExpressionsCollapse()) {
                                        return new AddAssignForCollection(element, element.getTextRange(), Arrays.asList(a1Expression, a2Expression));
                                    } else {
                                        break;
                                    }
                                case "equals":
                                    if (settings.getState().getComparingExpressionsCollapse()) {
                                        return new Equal(element, element.getTextRange(), Arrays.asList(a1Expression, a2Expression));
                                    } else {
                                        break;
                                    }
                            }
                        } else if (element.getArgumentList().getExpressions().length == 0) {
                            switch (method.getName()) {
                                case "random":
                                    return new Random(element, element.getTextRange(), Collections.emptyList());
                            }
                        }
                    }
                }
            }

        }
        if (settings.getState().getGetSetExpressionsCollapse() && identifier.isPresent()) {
            PsiElement identi = identifier.get();

            if (Helper.isGetter(identi, element)) {
                if (BuilderShiftExt.isShifted(element)) {
                    return null;
                }
                Expression expression = qualifier != null
                        ? BuildExpressionExt.getAnyExpression(qualifier, document)
                        : null;
                return new Getter(element, element.getTextRange(), TextRange.create(identi.getTextRange().getStartOffset(),
                        element.getTextRange().getEndOffset()),
                        expression,
                        guessPropertyName(identi.getText()));
            } else {
                String text = identi.getText();
                if (Helper.isSetter(text)
                        && element.getArgumentList().getExpressions().length == 1
                        && element.getParent() instanceof PsiStatement
                        && (!(qualifier instanceof PsiMethodCallExpression)
                        || !(Helper.startsWith(((PsiMethodCallExpression) qualifier).getMethodExpression().getReferenceName(), "set")))) {
                    Expression qualifierExpression = qualifier != null ? BuildExpressionExt.getAnyExpression(qualifier, document) : null;
                    Expression paramExpression = BuildExpressionExt.getAnyExpression(element.getArgumentList().getExpressions()[0], document);
                    String propertyName = guessPropertyName(text);
                    if (settings.getState().getFieldShift()) {
                        if (paramExpression instanceof IGetter getter) {
                            if (getter.getName().equals(propertyName)) {
                                getter.makeFieldShift();
                            }
                        }
                    }
                    return new Setter(element, element.getTextRange(), TextRange.create(identi.getTextRange().getStartOffset(),
                            element.getTextRange().getEndOffset()),
                            qualifierExpression,
                            propertyName,
                            paramExpression);
                }
            }
        }
        if (identifier.isPresent()) {
            if (referenceExpression.resolve() instanceof PsiMethod psiMethod) {
                PsiClass psiClass = psiMethod.getContainingClass();
                if (psiClass != null && psiClass.isRecord()) {
                    if (settings.getState().getGetSetExpressionsCollapse()) {
                        Expression expression = qualifier != null
                                ? BuildExpressionExt.getAnyExpression(qualifier, document)
                                : null;
                        PsiElement identi = identifier.get();
                        return new GetterRecord(element, element.getTextRange(), TextRange.create(identi.getTextRange().getStartOffset(),
                                element.getTextRange().getEndOffset()),
                                expression,
                                guessPropertyName(identi.getText()));
                    }
                }
                Expression builder = BuilderShiftExt.createExpression(element, psiClass);
                if (builder != null) {
                    return builder;
                }
            }
            PsiElement identi = identifier.get();
            String text = identi.getText();
            Expression logger = LoggerBracketsExt.createExpression(element, text, document);
            if (logger != null) {
                return logger;
            }
        }
        return null;
    }



}
