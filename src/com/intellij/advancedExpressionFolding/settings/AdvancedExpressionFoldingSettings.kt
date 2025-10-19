package com.intellij.advancedExpressionFolding.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.diagnostic.Logger
import com.intellij.util.containers.ContainerUtil
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import org.jetbrains.annotations.NotNull
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

@State(name = "AdvancedExpressionFoldingSettings", storages = [Storage("editor.codeinsight.xml")])
class AdvancedExpressionFoldingSettings : PersistentStateComponent<AdvancedExpressionFoldingSettings.State> {
    private var myState = State()
    private val logger = Logger.getInstance(AdvancedExpressionFoldingSettings::class.java)

    init {
        ensurePersonaProfiles()
    }

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state.copy()
        ensurePersonaProfiles()
    }

    data class State(
        override var concatenationExpressionsCollapse: Boolean = true,
        override var slicingExpressionsCollapse: Boolean = true,
        override var comparingExpressionsCollapse: Boolean = true,
        override var comparingLocalDatesCollapse: Boolean = true,
        override var localDateLiteralCollapse: Boolean = false,
        override var localDateLiteralPostfixCollapse: Boolean = false,
        override var getExpressionsCollapse: Boolean = true,
        override var rangeExpressionsCollapse: Boolean = true,
        override var checkExpressionsCollapse: Boolean = true,
        override var castExpressionsCollapse: Boolean = true,
        override var varExpressionsCollapse: Boolean = true,
        override var getSetExpressionsCollapse: Boolean = true,
        override var controlFlowSingleStatementCodeBlockCollapse: Boolean = false,
        override var compactControlFlowSyntaxCollapse: Boolean = false,
        override var controlFlowMultiStatementCodeBlockCollapse: Boolean = false,
        override var semicolonsCollapse: Boolean = false,
        override var assertsCollapse: Boolean = true,
        override var optional: Boolean = true,
        override var streamSpread: Boolean = true,
        override var lombok: Boolean = true,
        override var fieldShift: Boolean = true,
        override var kotlinQuickReturn: Boolean = true,
        override var ifNullSafe: Boolean = true,

        override var logFolding: Boolean = true,
        override var logFoldingTextBlocks: Boolean = false,

        override var destructuring: Boolean = false,
        override var println: Boolean = true,
        override var const: Boolean = true,
        override var nullable: Boolean = false,
        override var finalRemoval: Boolean = false,
        @Deprecated("too specific")
        override var finalEmoji: Boolean = false,
        override var lombokDirtyOff: Boolean = false,
        override var expressionFunc: Boolean = true,
        override var dynamic: Boolean = true,
        @Deprecated("to be removed")
        override var arithmeticExpressions: Boolean = false,
        @Deprecated("it generates too many foldings")
        override var emojify: Boolean = false,
        override var interfaceExtensionProperties: Boolean = true,
        override var patternMatchingInstanceof: Boolean = true,
        override var summaryParentOverride: Boolean = false,
        override var constructorReferenceNotation: Boolean = true,
        override var methodDefaultParameters: Boolean = true,
        override var lombokPatternOff: String? = null,
        override var overrideHide: Boolean = true,
        override var suppressWarningsHide: Boolean = true,
        override var pseudoAnnotations: Boolean = true,
        // NEW OPTION VAR

        override var memoryImprovement: Boolean = true,
        override var experimental: Boolean = false,

        override var globalOn: Boolean = true,

        var activePersonaId: String = PersonaProfileState.DEFAULT_ID,
        var personaProfiles: MutableMap<String, PersonaProfileState> = linkedMapOf(),
        var personaAuditTrail: MutableList<PersonaAuditEntry> = mutableListOf(),

        ) : IState, IConfig

    data class PersonaProfileState(
        var id: String = DEFAULT_ID,
        var displayName: String = "",
        var propertySnapshot: MutableMap<String, Any?> = linkedMapOf(),
        var foldedTextColorHex: String? = null,
        var preferredShortcut: String? = null,
        var onboardingTip: String? = null,
        var exampleFile: String? = null,
        var lastModifiedBy: String? = null,
        var lastModifiedAtMillis: Long = 0L,
    ) {
        companion object {
            const val DEFAULT_ID = "core"
        }
    }

    data class PersonaAuditEntry(
        var personaId: String = PersonaProfileState.DEFAULT_ID,
        var message: String = "",
        var timestampMillis: Long = 0L,
    )

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

    fun personaDescriptors(): List<PersonaProfileState> {
        ensurePersonaProfiles()
        return myState.personaProfiles.values.toList()
    }

    fun onboardingTip(personaId: String): String? {
        ensurePersonaProfiles()
        return myState.personaProfiles[personaId]?.onboardingTip
    }

    fun personaExample(personaId: String): String? {
        ensurePersonaProfiles()
        return myState.personaProfiles[personaId]?.exampleFile
    }

    fun setActivePersona(personaId: String, user: String? = null, applyColorScheme: Boolean = true) {
        ensurePersonaProfiles()
        if (!myState.personaProfiles.containsKey(personaId)) {
            logger.warn("Attempted to activate unknown persona: $personaId")
            return
        }

        val targetPersona = myState.personaProfiles.getValue(personaId)
        if (personaId == myState.activePersonaId) {
            if (applyColorScheme) {
                applyPersonaColor(targetPersona)
            }
            return
        }

        captureActivePersonaSnapshot(user)
        applySnapshot(targetPersona.propertySnapshot)
        myState.activePersonaId = personaId
        logAudit(
            personaId,
            "Activated persona '${targetPersona.displayName}'${user?.let { " by $it" } ?: ""}"
        )

        if (applyColorScheme) {
            applyPersonaColor(targetPersona)
        }
    }

    private fun applyPersonaColor(targetPersona: PersonaProfileState) {
        targetPersona.foldedTextColorHex?.let { color ->
            try {
                com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction.changeFoldingColors(color)
            } catch (t: Throwable) {
                logger.warn("Unable to apply persona color $color", t)
            }
        }
    }

    fun captureActivePersonaSnapshot(user: String? = null) {
        ensurePersonaProfiles()
        val personaId = myState.activePersonaId
        val persona = myState.personaProfiles.getOrPut(personaId) { defaultPersona(personaId) }
        persona.propertySnapshot = snapshotState()
        persona.lastModifiedBy = user
        persona.lastModifiedAtMillis = System.currentTimeMillis()
    }

    fun commitPersonaChanges(personaId: String, changedProperties: Map<String, Boolean>, user: String?) {
        ensurePersonaProfiles()
        val persona = myState.personaProfiles.getOrPut(personaId) { defaultPersona(personaId) }
        if (changedProperties.isNotEmpty()) {
            val conflicts = detectConflicts(personaId, changedProperties)
            conflicts.forEach { conflict ->
                logAudit(personaId, conflict)
            }
            logAudit(
                personaId,
                "Updated ${changedProperties.size} setting${if (changedProperties.size == 1) "" else "s"}${user?.let { " by $it" } ?: ""}"
            )
        }
        persona.propertySnapshot = snapshotState()
        persona.lastModifiedBy = user
        persona.lastModifiedAtMillis = System.currentTimeMillis()
    }

    fun detectConflicts(personaId: String, changedProperties: Map<String, Boolean>): List<String> {
        ensurePersonaProfiles()
        if (changedProperties.isEmpty()) return emptyList()
        val persona = myState.personaProfiles[personaId] ?: return emptyList()
        val snapshot = persona.propertySnapshot
        val conflicts = mutableListOf<String>()
        changedProperties.forEach { (propertyName, newValue) ->
            val previousValue = snapshot[propertyName] as? Boolean
            if (previousValue != null && previousValue != newValue) {
                conflicts += "Conflict on $propertyName: was $previousValue now $newValue"
            }
            myState.personaProfiles
                .filterKeys { it != personaId }
                .forEach { (_, otherPersona) ->
                    val otherValue = otherPersona.propertySnapshot[propertyName] as? Boolean
                    if (otherValue != null && otherValue != newValue) {
                        conflicts += "Differs from ${otherPersona.displayName}: $propertyName ${otherValue} vs $newValue"
                    }
                }
        }
        return conflicts
    }

    fun auditTrail(): List<PersonaAuditEntry> {
        ensurePersonaProfiles()
        return myState.personaAuditTrail.toList()
    }

    fun diffAgainstPersona(personaId: String): Map<String, Pair<Boolean, Boolean>> {
        ensurePersonaProfiles()
        val persona = myState.personaProfiles[personaId] ?: return emptyMap()
        val diffs = LinkedHashMap<String, Pair<Boolean, Boolean>>()
        val currentState = snapshotState()
        allProperties().forEach { property ->
            val name = property.name
            val personaValue = persona.propertySnapshot[name] as? Boolean ?: return@forEach
            val currentValue = currentState[name] as? Boolean ?: return@forEach
            if (personaValue != currentValue) {
                diffs[name] = personaValue to currentValue
            }
        }
        return diffs
    }

    fun formatTimestamp(millis: Long): String = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        .withZone(ZoneId.systemDefault())
        .format(Instant.ofEpochMilli(millis))

    private fun logAudit(personaId: String, message: String) {
        val timestamp = System.currentTimeMillis()
        myState.personaAuditTrail.add(
            PersonaAuditEntry(personaId = personaId, message = message, timestampMillis = timestamp)
        )
        trimAuditTrail()
    }

    private fun trimAuditTrail(limit: Int = 100) {
        if (myState.personaAuditTrail.size > limit) {
            myState.personaAuditTrail = myState.personaAuditTrail.takeLast(limit).toMutableList()
        }
    }

    private fun snapshotState(): MutableMap<String, Any?> = ContainerUtil.newLinkedHashMap<String, Any?>().apply {
        allProperties().forEach { property ->
            this[property.name] = property.getter.call(myState)
        }
    }

    private fun applySnapshot(snapshot: Map<String, Any?>) {
        allProperties().forEach { property ->
            val rawValue = snapshot[property.name]
            val value = when (rawValue) {
                is Boolean -> rawValue
                is String -> rawValue.toBooleanStrictOrNull()
                else -> null
            }
            if (value != null) {
                property.setter.call(myState, value)
            }
        }
    }

    private fun ensurePersonaProfiles() {
        if (myState.personaProfiles.isEmpty()) {
            myState.personaProfiles = defaultPersonaProfiles()
        }
        if (!myState.personaProfiles.containsKey(myState.activePersonaId)) {
            myState.activePersonaId = PersonaProfileState.DEFAULT_ID
        }
    }

    private fun defaultPersonaProfiles(): MutableMap<String, PersonaProfileState> {
        val baseline = snapshotState()
        val now = System.currentTimeMillis()
        return linkedMapOf(
            PersonaProfileState.DEFAULT_ID to PersonaProfileState(
                id = PersonaProfileState.DEFAULT_ID,
                displayName = "Core Persona",
                propertySnapshot = baseline.toMutableMap(),
                onboardingTip = "Balanced defaults for general refactoring sessions.",
                exampleFile = "PersonaCoreExample.java",
                lastModifiedAtMillis = now
            ),
            "analyst" to PersonaProfileState(
                id = "analyst",
                displayName = "Analyst Persona",
                propertySnapshot = baseline.toMutableMap().apply {
                    this["logFolding"] = false
                    this["optional"] = true
                    this["nullable"] = true
                },
                foldedTextColorHex = "#1b5e20",
                preferredShortcut = "alt shift 1",
                onboardingTip = "Focus on optional/nullable flows when reasoning about data.",
                exampleFile = "PersonaAnalystExample.java",
                lastModifiedAtMillis = now
            ),
            "logger" to PersonaProfileState(
                id = "logger",
                displayName = "Logger Persona",
                propertySnapshot = baseline.toMutableMap().apply {
                    this["logFolding"] = true
                    this["logFoldingTextBlocks"] = true
                },
                foldedTextColorHex = "#0d47a1",
                preferredShortcut = "alt shift 2",
                onboardingTip = "Collapse noisy log statements to spotlight control flow.",
                exampleFile = "PersonaLoggerExample.java",
                lastModifiedAtMillis = now
            ),
            "reviewer" to PersonaProfileState(
                id = "reviewer",
                displayName = "Reviewer Persona",
                propertySnapshot = baseline.toMutableMap().apply {
                    this["lombok"] = false
                    this["fieldShift"] = false
                    this["destructuring"] = true
                },
                foldedTextColorHex = "#4a148c",
                preferredShortcut = "alt shift 3",
                onboardingTip = "Keep Lombok visible while folding advanced DSL helpers.",
                exampleFile = "PersonaReviewerExample.java",
                lastModifiedAtMillis = now
            )
        )
    }

    private fun defaultPersona(id: String): PersonaProfileState {
        val displayName = id.replaceFirstChar { ch ->
            if (ch.isLowerCase()) ch.titlecase() else ch.toString()
        }
        return PersonaProfileState(id = id, displayName = displayName)
    }

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
