package com.intellij.advancedExpressionFolding.settings

import com.intellij.advancedExpressionFolding.settings.state.CollectionsStreamsState
import com.intellij.advancedExpressionFolding.settings.state.ControlFlowState
import com.intellij.advancedExpressionFolding.settings.state.DateOperationsState
import com.intellij.advancedExpressionFolding.settings.state.EmojiVisibilityState
import com.intellij.advancedExpressionFolding.settings.state.ExpressionCollapseState
import com.intellij.advancedExpressionFolding.settings.state.GlobalSettingsState
import com.intellij.advancedExpressionFolding.settings.state.HidingSuppressionState
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
import com.intellij.advancedExpressionFolding.settings.state.KotlinLanguageState
import com.intellij.advancedExpressionFolding.settings.state.LogFoldingState
import com.intellij.advancedExpressionFolding.settings.state.LombokState
import com.intellij.advancedExpressionFolding.settings.state.UnclassifiedFeatureState
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Property delegate providing access to global settings state with interface delegation support.
 *
 * Usage patterns:
 *
 * 1. Object with interface delegation:
 * ```kotlin
 * object DelegatedObject : IGlobalSettingsState by State()() {
 *     fun process() = if (experimental) ... else ...
 * }
 * ```
 *
 * 2. Class with interface delegation:
 * ```kotlin
 * class DelegatedClass : IGlobalSettingsState by State()() {
 *     fun analyze() = experimental
 * }
 * ```
 */
data class State(
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
    IUnclassifiedFeatureState by unclassifiedFeatureState,

    ReadOnlyProperty<Any?, State> {
    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): State = AdvancedExpressionFoldingSettings.Companion.getInstance().state
    operator fun invoke(): State = AdvancedExpressionFoldingSettings.Companion.getInstance().state
}
