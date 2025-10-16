package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.ui.JBColor
import java.awt.Color
import kotlin.reflect.KMutableProperty0

@DslMarker
annotation class CheckboxDsl

typealias ExampleDescription = String
typealias UrlSuffix = String
typealias ExampleFile = String

data class CheckboxDefinition(
    val title: String,
    val property: KMutableProperty0<Boolean>,
    val exampleLinkMap: Map<ExampleFile, ExampleDescription?>? = null,
    val docLink: UrlSuffix? = null,
    val badge: FeatureBadge? = null,
    val longDescription: String? = null
)

data class FeatureBadge(
    val label: String,
    val style: FeatureBadgeStyle
)

enum class FeatureBadgeStyle(
    private val lightBackground: Int,
    private val darkBackground: Int,
    private val lightForeground: Int,
    private val darkForeground: Int,
    private val lightBorder: Int,
    private val darkBorder: Int
) {
    NEW(
        lightBackground = 0xE7F6EC,
        darkBackground = 0x204029,
        lightForeground = 0x1B5E20,
        darkForeground = 0xA5D6A7,
        lightBorder = 0xA5D6A7,
        darkBorder = 0x2E7D32
    ),
    EXPERIMENTAL(
        lightBackground = 0xFFF4E5,
        darkBackground = 0x3D2B1F,
        lightForeground = 0xBF360C,
        darkForeground = 0xFFCC80,
        lightBorder = 0xFFB74D,
        darkBorder = 0xE65100
    );

    val background: Color
        get() = Color(if (JBColor.isBright()) lightBackground else darkBackground)

    val foreground: Color
        get() = Color(if (JBColor.isBright()) lightForeground else darkForeground)

    val border: Color
        get() = Color(if (JBColor.isBright()) lightBorder else darkBorder)
}

@CheckboxDsl
class CheckboxBuilder {
    private val examples = mutableMapOf<ExampleFile, ExampleDescription?>()
    private var docLink: UrlSuffix? = null
    private var badge: FeatureBadge? = null
    private var longDescription: String? = null

    fun example(file: ExampleFile, description: ExampleDescription? = null) {
        examples[file] = description
    }

    fun link(documentationLink: UrlSuffix) {
        docLink = documentationLink
    }

    fun badge(label: String, style: FeatureBadgeStyle) {
        badge = FeatureBadge(label, style)
    }

    fun description(details: String) {
        longDescription = details
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
            badge = badge,
            longDescription = longDescription
        )
    }

}
