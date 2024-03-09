package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.NumberLiteral;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class Helper {

    public static boolean isReferenceToReference(@Nullable PsiReferenceExpression referenceExpression, @Nullable PsiReference reference) {
        if (reference != null) {
            @Nullable PsiElement element = reference.resolve();
            return referenceExpression != null && element != null && referenceExpression.isReferenceTo(element);
        } else {
            return false;
        }
    }

    @Nullable
    public static PsiElement findSameQualifier(@NotNull PsiElement element, @NotNull PsiElement qualifier) {
        if (element instanceof PsiStatement && element.getFirstChild() != null) {
            return findSameQualifier(element.getFirstChild(), qualifier);
        }
        if (equal(qualifier, element)) {
            return element;
        }
        if (element instanceof PsiMethodCallExpression && ((PsiMethodCallExpression) element).getMethodExpression().getQualifierExpression() != null) {
            @Nullable PsiExpression q = ((PsiMethodCallExpression) element).getMethodExpression().getQualifierExpression();
            if (q != null) {
                return findSameQualifier(q, qualifier);
            }
        }
        if (element instanceof PsiReferenceExpression && ((PsiReferenceExpression) element).getQualifierExpression() != null) {
            PsiExpression q = ((PsiReferenceExpression) element).getQualifierExpression();
            if (q != null) {
                return findSameQualifier(q, qualifier);
            }
        }
        return null;
    }

    public static boolean startsWith(@Nullable String string, @NotNull String prefix) {
        return string != null && string.startsWith(prefix);
    }

    public static boolean equal(@Nullable PsiElement e1, @Nullable PsiElement e2) {
        // TODO: Use a cache for the resolved instance
        if (e2 instanceof PsiReferenceExpression && e1 instanceof PsiReferenceExpression) {
            return Objects.equals(((PsiReferenceExpression) e2).getReferenceName(), ((PsiReferenceExpression) e1).getReferenceName())
                    && isReferenceToReference((PsiReferenceExpression) e2, (PsiReferenceExpression) e1);
        } else if (e2 instanceof PsiMethodCallExpression && e1 instanceof PsiMethodCallExpression) {
            return equal(((PsiMethodCallExpression) e2).getMethodExpression(),
                    ((PsiMethodCallExpression) e1).getMethodExpression())
                    && equal(((PsiMethodCallExpression) e2).getMethodExpression().getQualifierExpression(),
                    ((PsiMethodCallExpression) e1).getMethodExpression().getQualifierExpression());
        }
        return false;
    }

    public static boolean calculateIfFinal(@NotNull PsiVariable element) {
        PsiModifierList modifiers = element.getModifierList();
        if (modifiers != null) {
            boolean isFinal = modifiers.hasExplicitModifier(PsiModifier.FINAL);
            if (!isFinal) {
                PsiElement body = element.getParent() instanceof PsiDeclarationStatement
                        ? element.getParent().getParent()
                        : element.getParent() instanceof PsiLoopStatement
                        ? ((PsiLoopStatement) element.getParent()).getBody()
                        : element.getParent();
                if (body instanceof PsiLoopStatement) {
                    body = ((PsiLoopStatement) body).getBody();
                }
                List<PsiElement> references = SyntaxTraverser.psiTraverser(body)
                        .filter(e ->
                                e instanceof PsiAssignmentExpression
                                        && ((PsiAssignmentExpression) e).getLExpression() instanceof PsiReferenceExpression
                                        && ((PsiReferenceExpression) ((PsiAssignmentExpression) e).getLExpression()).isReferenceTo(element)
                                        || e instanceof PsiPostfixExpression
                                        && (((PsiPostfixExpression) e).getOperationSign().getText().equals("++")
                                        || ((PsiPostfixExpression) e).getOperationSign().getText().equals("--"))
                                        && ((PsiPostfixExpression) e).getOperand() instanceof PsiReferenceExpression
                                        && ((PsiReferenceExpression) ((PsiPostfixExpression) e).getOperand()).isReferenceTo(element)
                        ).toList();
                if (references.isEmpty()) {
                    isFinal = true;
                }
            }
            return isFinal;
        }
        return false;
    }

    public static void findChildExpressions(@NotNull PsiElement element, @NotNull List<Expression> expressions, @NotNull Document document) {
        for (PsiElement child : element.getChildren()) {
            @Nullable Expression expression = BuildExpressionExt.getNonSyntheticExpression(child, document);
            if (expression != null) {
                expressions.add(expression);
            }
            if (expression == null || !expression.getTextRange().equals(child.getTextRange())) {
                findChildExpressions(child, expressions, document);
            }
        }
    }

    @NotNull
    public static String eraseGenerics(@NotNull String signature) {
        Matcher m = Consts.GENERICS_PATTERN.matcher(signature);
        while (m.find()) {
            signature = m.replaceAll("");
            m = Consts.GENERICS_PATTERN.matcher(signature);
        }
        return signature;
    }

    public static boolean isSupportedClass(@NotNull PsiElement element) {
        PsiReference reference = element.getReference();
        if (reference != null) {
            PsiElement e = reference.resolve();
            if (e instanceof PsiField field) {
                PsiClass psiClass = field.getContainingClass();
                if (psiClass != null && psiClass.getQualifiedName() != null) {
                    return Consts.SUPPORTED_CLASSES.contains(eraseGenerics(psiClass.getQualifiedName()));
                }
            }
        }
        return false;
    }

    public static int findDot(@NotNull Document document, int position, int i, boolean includeNewLines) {
        int offset = 0;
        while (Math.abs(offset) < 100 &&
                position > 0 &&
                position < document.getText().length()) {
            position += i;
            offset += i;
            if (charAt(document, position) == '.') {
                break;
            }
            if (!Character.isWhitespace(charAt(document, position))) {
                return Integer.MAX_VALUE;
            }
        }
        if (includeNewLines) {
            int offsetWithNewLine = offset;
            do {
                position += i;
                offsetWithNewLine += i;
                if (i < 0 && charAt(document, position) == '\n') {
                    offset = offsetWithNewLine;
                } else if (i > 0 && Character.isWhitespace(charAt(document, position))) {
                    offset = offsetWithNewLine;
                }
            } while (Math.abs(offsetWithNewLine) < 100 && position > 0 &&
                    position < document.getText().length() &&
                    Character.isWhitespace(charAt(document, position)));
        }
        if (Math.abs(offset) >= 100) {
            return Integer.MAX_VALUE;
        }
        return offset;
    }

    public static char charAt(@NotNull Document document, int position) {
        return document.getText(TextRange.create(position, position + 1)).charAt(0);
    }

    @Nullable
    public static NumberLiteral getSlicePosition(@NotNull PsiElement parent, @NotNull Expression qualifierExpression,
                                          @NotNull PsiBinaryExpression a2b, @NotNull Document document) {
        PsiExpression rOperand = a2b.getROperand();
        PsiExpression lOperand = a2b.getLOperand();
        if (a2b.getOperationSign().getText().equals("-")
                && rOperand != null
                && (lOperand instanceof PsiMethodCallExpression
                || lOperand instanceof PsiReferenceExpression)) {
            @NotNull Expression s = BuildExpressionExt.getAnyExpression(rOperand, document);
            if (s instanceof NumberLiteral) {
                @NotNull PsiReferenceExpression a2me = lOperand instanceof PsiMethodCallExpression
                        ? ((PsiMethodCallExpression) lOperand).getMethodExpression() : (PsiReferenceExpression) lOperand;
                @NotNull Optional<PsiElement> a2i = Stream.of(a2me.getChildren())
                        .filter(c -> c instanceof PsiIdentifier).findAny();
                if (a2i.isPresent() && (a2i.get().getText().equals("length")
                        || a2i.get().getText().equals("size")) && a2me.getQualifierExpression() != null) {
                    @NotNull Expression a2qe = BuildExpressionExt.getAnyExpression(a2me.getQualifierExpression(), document);
                    if (a2qe.equals(qualifierExpression)) {
                        return new NumberLiteral(parent,
                                TextRange.create(a2b.getOperationSign().getTextRange().getStartOffset(),
                                        a2b.getTextRange().getEndOffset()), null, -((NumberLiteral) s).getNumber().intValue(), false);
                    }
                }
            }
        }
        return null;
    }

    public static boolean isSetter(String text) {
        return text.startsWith("set")
                && text.length() > 3
                && Character.isUpperCase(text.charAt(3));
    }

    public static boolean isPureMethodReference(PsiMethodCallExpression element) {
        return findChildByTypeHierarchy(element, PsiMethodReferenceExpression.class, PsiExpressionList.class, PsiMethodReferenceExpression.class)
                .isPresent();
    }

    public static boolean hasOptionalChainOperations(PsiMethodCallExpression element) {
        return findAncestorsUntilClass(element, PsiExpressionStatement.class).findAny().isPresent();
    }

    public static boolean isGetter(@NotNull PsiElement element, @NotNull PsiMethodCallExpression expression) {
        return expression.getArgumentList().getExpressions().length == 0
                && isGetter(element.getText());
    }

    public static boolean isGetter(@NotNull String name) {
        return isGetterAux(name, "get") || isGetterAux(name, "is");
    }

    public static boolean isGetterAux(@Nullable String name, @NotNull String prefix) {
        return startsWith(name, prefix)
                && name.length() > prefix.length()
                && Character.isUpperCase(name.charAt(prefix.length()));
    }

    public static <T extends PsiElement> Stream<PsiElement> findAncestorsUntilClass(PsiElement element, Class<T> ancestorClass) {
        return findAncestorsUntil(element, (parent -> !ancestorClass.isInstance(parent)));
    }

    public static <T extends PsiElement> Stream<PsiElement> findAncestorsUntil(PsiElement element, Predicate<PsiElement> untilPredicate) {
        return Stream.iterate(element.getParent(), Objects::nonNull, PsiElement::getParent)
                .takeWhile(untilPredicate);
    }

    @SafeVarargs
    public static <T extends PsiElement> Optional<T> findChildByTypeHierarchy(PsiElement element, Class<T> childClass, Class<? extends PsiElement>... children) {
        LinkedList<Class<? extends PsiElement>> classQueue = new LinkedList<>(List.of(children));
        Class<? extends PsiElement> next = classQueue.poll();

        for (PsiElement child : element.getChildren()) {
            if (next != null && next.isInstance(child)) {
                if (classQueue.isEmpty()) {
                    //noinspection unchecked
                    return Optional.of((T) child);
                } else {
                    //noinspection unchecked
                    return findChildByTypeHierarchy(child, childClass, classQueue.toArray(new Class[0]));
                }
            }
        }
        return Optional.empty();
    }
}
