package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.ui.JBColor
import java.awt.Color
import kotlin.reflect.KMutableProperty0

@DslMarker
annotation class CheckboxDsl

typealias Description = String
typealias UrlSuffix = String
typealias ExampleFile = String

data class CheckboxDefinition(
    val title: String,
    val property: KMutableProperty0<Boolean>,
    val exampleLinkMap: Map<ExampleFile, Description?>? = null,
    val docLink: UrlSuffix? = null,
    val status: CheckboxStatusDefinition? = null
)

data class CheckboxStatusDefinition(
    val status: CheckboxStatus,
    val descriptionOverride: Description?
) {
    val badgeText: String get() = status.badgeText
    val tooltip: String? get() = status.tooltip
    val background: JBColor get() = status.background
    val foreground: JBColor get() = status.foreground
    val description: Description get() = descriptionOverride ?: status.defaultDescription
}

enum class CheckboxStatus(
    val badgeText: String,
    val defaultDescription: Description,
    val tooltip: String?,
    val background: JBColor,
    val foreground: JBColor
) {
    NEW_FEATURE(
        badgeText = "NEW",
        defaultDescription = "Fresh functionality that is still stabilizing. Keep an eye on IDE performance when the option is enabled.",
        tooltip = "Recently introduced option",
        background = JBColor(Color(0xE6F4EA), Color(0x1F3328)),
        foreground = JBColor(Color(0x0F5132), Color(0xCFECDC))
    ),
    EXPERIMENTAL(
        badgeText = "EXPERIMENTAL",
        defaultDescription = "Work in progress functionality. Behaviors may change and the option could cause unexpected folding results.",
        tooltip = "Experimental – use with caution",
        background = JBColor(Color(0xFFF4E5), Color(0x352711)),
        foreground = JBColor(Color(0x663C00), Color(0xFFE8C2))
    )
}

@CheckboxDsl
class CheckboxBuilder {
    private val examples = mutableMapOf<ExampleFile, Description?>()
    private var docLink: UrlSuffix? = null
    private var status: CheckboxStatusDefinition? = null

    fun example(file: ExampleFile, description: Description? = null) {
        examples[file] = description
    }

    fun link(documentationLink: UrlSuffix) {
        docLink = documentationLink
    }

    fun status(status: CheckboxStatus, description: Description? = null) {
        this.status = CheckboxStatusDefinition(status, description)
    }

    internal fun build(
        property: KMutableProperty0<Boolean>,
        title: String
    ): CheckboxDefinition {
        return CheckboxDefinition(
            title = title,
            property = property,
            exampleLinkMap = if (examples.isEmpty()) null else examples.toMap(),
            docLink = docLink,
            status = status
        )
    }

}
