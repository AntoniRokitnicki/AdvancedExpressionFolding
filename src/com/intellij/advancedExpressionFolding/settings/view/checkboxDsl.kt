package com.intellij.advancedExpressionFolding.settings.view

import kotlin.jvm.JvmInline
import kotlin.reflect.KMutableProperty0

@DslMarker
annotation class CheckboxDsl
typealias UrlSuffix = String
typealias ExampleFile = String

data class CheckboxDefinition(
    val title: String,
    val property: KMutableProperty0<Boolean>,
    val exampleLinkMap: Map<ExampleFile, Description?>? = null,
    val docLink: DocumentationUrl? = null
)

@CheckboxDsl
class CheckboxBuilder {
    private val examples = mutableMapOf<ExampleFile, Description?>()
    private var docLink: DocumentationUrl? = null

    fun example(file: ExampleFile, description: String? = null) {
        examples[file] = description?.let(::Description)
    }

    fun link(documentationLink: DocumentationUrl) {
        docLink = documentationLink
    }

    internal fun build(
        property: KMutableProperty0<Boolean>,
        title: String
    ): CheckboxDefinition {
        return CheckboxDefinition(
            title = title,
            property = property,
            exampleLinkMap = if (examples.isEmpty()) null else examples.toMap(),
            docLink = docLink
        )
    }

}
