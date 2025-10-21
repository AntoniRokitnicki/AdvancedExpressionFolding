package com.intellij.advancedExpressionFolding.settings

interface IState :
    ILombokState,
    ILogFoldingState,
    IControlFlowState,
    IDateOperationsState,
    IKotlinLanguageState,
    ICollectionsStreamsState,
    IExpressionCollapseState,
    IGlobalSettingsState,
    IEmojiVisibilityState,
    IHidingSuppressionState,
    IUnclassifiedFeatureState
