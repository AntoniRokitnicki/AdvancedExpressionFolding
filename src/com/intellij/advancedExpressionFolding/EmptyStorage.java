package com.intellij.advancedExpressionFolding;

import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import org.jetbrains.annotations.NotNull;

public class EmptyStorage {
    public FoldingDescriptor[] store(FoldingDescriptor[] foldingDescriptors, @NotNull Document document) {
        return foldingDescriptors;
    }
}
