package com.intellij.advancedExpressionFolding.settings

import com.intellij.advancedExpressionFolding.settings.state.ICollectionsStreamsState
import com.intellij.advancedExpressionFolding.settings.state.IControlFlowState
import com.intellij.advancedExpressionFolding.settings.state.IDateOperationsState
import com.intellij.advancedExpressionFolding.settings.state.IEmojiVisibilityState
import com.intellij.advancedExpressionFolding.settings.state.IExpressionCollapseState
import com.intellij.advancedExpressionFolding.settings.state.IGlobalSettingsState
import com.intellij.advancedExpressionFolding.settings.state.IHidingSuppressionState
import com.intellij.advancedExpressionFolding.settings.state.IKotlinLanguageState
import com.intellij.advancedExpressionFolding.settings.state.ILogFoldingState
import com.intellij.advancedExpressionFolding.settings.state.ILombokState
import com.intellij.advancedExpressionFolding.settings.state.IUnclassifiedFeatureState

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
