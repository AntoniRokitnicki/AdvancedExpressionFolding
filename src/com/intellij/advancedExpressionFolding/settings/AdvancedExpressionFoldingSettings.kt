package com.intellij.advancedExpressionFolding.settings

import com.intellij.advancedExpressionFolding.FoldingRuleExecutionGuard
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.NotNull
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

@State(name = "AdvancedExpressionFoldingSettings", storages = [Storage("editor.codeinsight.xml")])
class AdvancedExpressionFoldingSettings : PersistentStateComponent<AdvancedExpressionFoldingSettings.State> {
    private var myState = State()
    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state.copy()
    }

    class State : IState, IConfig {
        companion object {
            const val DEFAULT_THROTTLE_THRESHOLD_MS: Long = 500
        }

        private val raw = mutableMapOf<String, Boolean>()
        private var lombokPatternOffValue: String? = null

        init {
            initializeDefaults()
        }

        override var concatenationExpressionsCollapse: Boolean
            get() = effective("concatenationExpressionsCollapse")
            set(value) = updateRule("concatenationExpressionsCollapse", value)

        override var slicingExpressionsCollapse: Boolean
            get() = effective("slicingExpressionsCollapse")
            set(value) = updateRule("slicingExpressionsCollapse", value)

        override var comparingExpressionsCollapse: Boolean
            get() = effective("comparingExpressionsCollapse")
            set(value) = updateRule("comparingExpressionsCollapse", value)

        override var comparingLocalDatesCollapse: Boolean
            get() = effective("comparingLocalDatesCollapse")
            set(value) = updateRule("comparingLocalDatesCollapse", value)

        override var localDateLiteralCollapse: Boolean
            get() = effective("localDateLiteralCollapse")
            set(value) = updateRule("localDateLiteralCollapse", value)

        override var localDateLiteralPostfixCollapse: Boolean
            get() = effective("localDateLiteralPostfixCollapse")
            set(value) = updateRule("localDateLiteralPostfixCollapse", value)

        override var getExpressionsCollapse: Boolean
            get() = effective("getExpressionsCollapse")
            set(value) = updateRule("getExpressionsCollapse", value)

        override var rangeExpressionsCollapse: Boolean
            get() = effective("rangeExpressionsCollapse")
            set(value) = updateRule("rangeExpressionsCollapse", value)

        override var checkExpressionsCollapse: Boolean
            get() = effective("checkExpressionsCollapse")
            set(value) = updateRule("checkExpressionsCollapse", value)

        override var castExpressionsCollapse: Boolean
            get() = effective("castExpressionsCollapse")
            set(value) = updateRule("castExpressionsCollapse", value)

        override var varExpressionsCollapse: Boolean
            get() = effective("varExpressionsCollapse")
            set(value) = updateRule("varExpressionsCollapse", value)

        override var getSetExpressionsCollapse: Boolean
            get() = effective("getSetExpressionsCollapse")
            set(value) = updateRule("getSetExpressionsCollapse", value)

        override var controlFlowSingleStatementCodeBlockCollapse: Boolean
            get() = effective("controlFlowSingleStatementCodeBlockCollapse")
            set(value) = updateRule("controlFlowSingleStatementCodeBlockCollapse", value)

        override var compactControlFlowSyntaxCollapse: Boolean
            get() = effective("compactControlFlowSyntaxCollapse")
            set(value) = updateRule("compactControlFlowSyntaxCollapse", value)

        override var controlFlowMultiStatementCodeBlockCollapse: Boolean
            get() = effective("controlFlowMultiStatementCodeBlockCollapse")
            set(value) = updateRule("controlFlowMultiStatementCodeBlockCollapse", value)

        override var semicolonsCollapse: Boolean
            get() = effective("semicolonsCollapse")
            set(value) = updateRule("semicolonsCollapse", value)

        override var assertsCollapse: Boolean
            get() = effective("assertsCollapse")
            set(value) = updateRule("assertsCollapse", value)

        override var optional: Boolean
            get() = effective("optional")
            set(value) = updateRule("optional", value)

        override var streamSpread: Boolean
            get() = effective("streamSpread")
            set(value) = updateRule("streamSpread", value)

        override var lombok: Boolean
            get() = effective("lombok")
            set(value) = updateRule("lombok", value)

        override var fieldShift: Boolean
            get() = effective("fieldShift")
            set(value) = updateRule("fieldShift", value)

        override var kotlinQuickReturn: Boolean
            get() = effective("kotlinQuickReturn")
            set(value) = updateRule("kotlinQuickReturn", value)

        override var ifNullSafe: Boolean
            get() = effective("ifNullSafe")
            set(value) = updateRule("ifNullSafe", value)

        override var logFolding: Boolean
            get() = effective("logFolding")
            set(value) = updateRule("logFolding", value)

        override var logFoldingTextBlocks: Boolean
            get() = effective("logFoldingTextBlocks")
            set(value) = updateRule("logFoldingTextBlocks", value)

        override var destructuring: Boolean
            get() = effective("destructuring")
            set(value) = updateRule("destructuring", value)

        override var println: Boolean
            get() = effective("println")
            set(value) = updateRule("println", value)

        override var const: Boolean
            get() = effective("const")
            set(value) = updateRule("const", value)

        override var nullable: Boolean
            get() = effective("nullable")
            set(value) = updateRule("nullable", value)

        override var finalRemoval: Boolean
            get() = effective("finalRemoval")
            set(value) = updateRule("finalRemoval", value)

        @Deprecated("too specific")
        override var finalEmoji: Boolean
            get() = effective("finalEmoji")
            set(value) = updateRule("finalEmoji", value)

        override var lombokDirtyOff: Boolean
            get() = effective("lombokDirtyOff")
            set(value) = updateRule("lombokDirtyOff", value)

        override var expressionFunc: Boolean
            get() = effective("expressionFunc")
            set(value) = updateRule("expressionFunc", value)

        override var dynamic: Boolean
            get() = effective("dynamic")
            set(value) = updateRule("dynamic", value)

        @Deprecated("to be removed")
        override var arithmeticExpressions: Boolean
            get() = effective("arithmeticExpressions")
            set(value) = updateRule("arithmeticExpressions", value)

        @Deprecated("it generates too many foldings")
        override var emojify: Boolean
            get() = effective("emojify")
            set(value) = updateRule("emojify", value)

        override var interfaceExtensionProperties: Boolean
            get() = effective("interfaceExtensionProperties")
            set(value) = updateRule("interfaceExtensionProperties", value)

        override var patternMatchingInstanceof: Boolean
            get() = effective("patternMatchingInstanceof")
            set(value) = updateRule("patternMatchingInstanceof", value)

        override var summaryParentOverride: Boolean
            get() = effective("summaryParentOverride")
            set(value) = updateRule("summaryParentOverride", value)

        override var constructorReferenceNotation: Boolean
            get() = effective("constructorReferenceNotation")
            set(value) = updateRule("constructorReferenceNotation", value)

        override var methodDefaultParameters: Boolean
            get() = effective("methodDefaultParameters")
            set(value) = updateRule("methodDefaultParameters", value)

        override var lombokPatternOff: String?
            get() = lombokPatternOffValue
            set(value) {
                lombokPatternOffValue = value
            }

        override var overrideHide: Boolean
            get() = effective("overrideHide")
            set(value) = updateRule("overrideHide", value)

        override var suppressWarningsHide: Boolean
            get() = effective("suppressWarningsHide")
            set(value) = updateRule("suppressWarningsHide", value)

        override var pseudoAnnotations: Boolean
            get() = effective("pseudoAnnotations")
            set(value) = updateRule("pseudoAnnotations", value)

        override var memoryImprovement: Boolean
            get() = rawValue("memoryImprovement")
            set(value) {
                raw["memoryImprovement"] = value
            }

        override var experimental: Boolean
            get() = rawValue("experimental")
            set(value) = updateRule("experimental", value)

        override var globalOn: Boolean
            get() = rawValue("globalOn")
            set(value) {
                raw["globalOn"] = value
            }

        var autoDisabledRules: MutableSet<String> = mutableSetOf()
        var throttleThresholdMillis: Long = DEFAULT_THROTTLE_THRESHOLD_MS

        private fun initializeDefaults() {
            raw["concatenationExpressionsCollapse"] = true
            raw["slicingExpressionsCollapse"] = true
            raw["comparingExpressionsCollapse"] = true
            raw["comparingLocalDatesCollapse"] = true
            raw["localDateLiteralCollapse"] = false
            raw["localDateLiteralPostfixCollapse"] = false
            raw["getExpressionsCollapse"] = true
            raw["rangeExpressionsCollapse"] = true
            raw["checkExpressionsCollapse"] = true
            raw["castExpressionsCollapse"] = true
            raw["varExpressionsCollapse"] = true
            raw["getSetExpressionsCollapse"] = true
            raw["controlFlowSingleStatementCodeBlockCollapse"] = false
            raw["compactControlFlowSyntaxCollapse"] = false
            raw["controlFlowMultiStatementCodeBlockCollapse"] = false
            raw["semicolonsCollapse"] = false
            raw["assertsCollapse"] = true
            raw["optional"] = true
            raw["streamSpread"] = true
            raw["lombok"] = true
            raw["fieldShift"] = true
            raw["kotlinQuickReturn"] = true
            raw["ifNullSafe"] = true
            raw["logFolding"] = true
            raw["logFoldingTextBlocks"] = false
            raw["destructuring"] = false
            raw["println"] = true
            raw["const"] = true
            raw["nullable"] = false
            raw["finalRemoval"] = false
            raw["finalEmoji"] = false
            raw["lombokDirtyOff"] = false
            raw["expressionFunc"] = true
            raw["dynamic"] = true
            raw["arithmeticExpressions"] = false
            raw["emojify"] = false
            raw["interfaceExtensionProperties"] = true
            raw["patternMatchingInstanceof"] = true
            raw["summaryParentOverride"] = false
            raw["constructorReferenceNotation"] = true
            raw["methodDefaultParameters"] = true
            raw["overrideHide"] = true
            raw["suppressWarningsHide"] = true
            raw["pseudoAnnotations"] = true
            raw["memoryImprovement"] = true
            raw["experimental"] = false
            raw["globalOn"] = true
        }

        private fun rawValue(flag: String): Boolean = raw[flag] ?: false

        private fun updateRule(flag: String, value: Boolean) {
            raw[flag] = value
            autoDisabledRules.remove(flag)
            FoldingRuleExecutionGuard.resetStats(flag)
        }

        private fun effective(flag: String): Boolean {
            FoldingRuleExecutionGuard.onFlagAccess(flag)
            return rawValue(flag) && !autoDisabledRules.contains(flag)
        }

        fun isAutoDisabled(flag: String): Boolean = autoDisabledRules.contains(flag)

        fun setAutoDisabled(flag: String, disabled: Boolean): Boolean {
            return if (disabled) {
                autoDisabledRules.add(flag)
            } else {
                autoDisabledRules.remove(flag)
            }
        }

        fun copy(): State {
            val copy = State()
            copy.raw.clear()
            copy.raw.putAll(raw)
            copy.autoDisabledRules = autoDisabledRules.toMutableSet()
            copy.lombokPatternOffValue = lombokPatternOffValue
            copy.throttleThresholdMillis = throttleThresholdMillis
            return copy
        }

        fun isAutoDisabled(property: KMutableProperty0<Boolean>): Boolean = isAutoDisabled(property.name)
    }

    private fun updateAllState(value: Boolean, vararg excludeProperties: KMutableProperty<Boolean>) {
        val excluded = excludeProperties.map { it.toString() }
        with(myState) {
            allProperties()
                .filter {
                    !excluded.contains(it.toString())
                }.forEach {
                    if (it.setter.parameters.getOrNull(1)?.type?.javaType?.typeName == "boolean") {
                        it.setter.call(this, value)
                    }
                }
        }
    }

    fun disableAll() = updateAllState(false)
    fun enableAll(vararg excludeProperties: KMutableProperty0<Boolean>) = updateAllState(true, *excludeProperties)

    // used in integrationStubs
    fun enableEverything() = updateAllState(true, state::emojify, state::finalEmoji)

    companion object {
        @JvmStatic
        @NotNull
        fun getInstance(): AdvancedExpressionFoldingSettings =
            ApplicationManager.getApplication().getService(AdvancedExpressionFoldingSettings::class.java)

        fun allProperties() = State::class.memberProperties
            .filterIsInstance<KMutableProperty<Boolean>>()
            .filter { property ->
                exclude(IConfig::class, property)
            }

        fun allMainProperties() = allProperties().filter { property ->
            exclude(ILessImportantState::class, property)
        }

        private fun exclude(
            kClass: KClass<*>,
            property: KMutableProperty<*>
        ) = !kClass.memberProperties.map { it.name }.contains(property.name)
    }
}
