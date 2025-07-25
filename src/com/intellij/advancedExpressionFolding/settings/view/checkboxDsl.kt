package com.intellij.advancedExpressionFolding.settings.view

import kotlin.reflect.KMutableProperty0

typealias Description = String
typealias UrlSuffix = String
typealias ExampleFile = String

data class CheckboxDefinition(
    val title: String,
    val property: KMutableProperty0<Boolean>,
    val exampleLinkMap: Map<ExampleFile, Description?>? = null,
    val docLink: UrlSuffix? = null
)

class CheckboxBuilder {
    private val examples = mutableMapOf<ExampleFile, Description?>()
    private var docLink: UrlSuffix? = null

    fun example(file: ExampleFile, description: Description? = null) {
        examples[file] = description
    }

    fun link(documentationLink: UrlSuffix) {
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
