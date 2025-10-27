package com.intellij.advancedExpressionFolding.settings

/**
 * New version of [AdvancedExpressionFoldingSettings.State] that will replace it.
 */
data class NewState(
    private val lombokState: ILombokState = LombokState(),
    private val logFoldingState: ILogFoldingState = LogFoldingState(),
    private val controlFlowState: IControlFlowState = ControlFlowState(),
    private val dateOperationsState: IDateOperationsState = DateOperationsState(),
    private val kotlinLanguageState: IKotlinLanguageState = KotlinLanguageState(),
    private val collectionsStreamsState: ICollectionsStreamsState = CollectionsStreamsState(),
    private val expressionCollapseState: IExpressionCollapseState = ExpressionCollapseState(),
    private val globalSettingsState: IGlobalSettingsState = GlobalSettingsState(),
    private val emojiVisibilityState: IEmojiVisibilityState = EmojiVisibilityState(),
    private val hidingSuppressionState: IHidingSuppressionState = HidingSuppressionState(),
    private val unclassifiedFeatureState: IUnclassifiedFeatureState = UnclassifiedFeatureState(),
) : IState,
    ILombokState by lombokState,
    ILogFoldingState by logFoldingState,
    IControlFlowState by controlFlowState,
    IDateOperationsState by dateOperationsState,
    IKotlinLanguageState by kotlinLanguageState,
    ICollectionsStreamsState by collectionsStreamsState,
    IExpressionCollapseState by expressionCollapseState,
    IEmojiVisibilityState by emojiVisibilityState,
    IHidingSuppressionState by hidingSuppressionState,
    
    IGlobalSettingsState by globalSettingsState,
    IUnclassifiedFeatureState by unclassifiedFeatureState
