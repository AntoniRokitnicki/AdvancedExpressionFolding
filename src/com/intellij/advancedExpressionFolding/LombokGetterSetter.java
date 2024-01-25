package com.intellij.advancedExpressionFolding;

import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;

import java.util.List;
import java.util.stream.Stream;

public class LombokGetterSetter {
    private final PsiField field;
    private final List<PsiMethod> methods;

    public LombokGetterSetter(PsiField field, List<PsiMethod> methods) {
        this.field = field;
        this.methods = methods;
    }

    public Stream<PsiMethod> getElements() {
        return methods.stream();
    }
}
