package com.intellij.advancedExpressionFolding.application.port.input

interface FoldingApplicationPort :
    BuildFoldRegionsUseCase,
    PreviewFoldRegionsUseCase,
    CollapsedStateResolver
