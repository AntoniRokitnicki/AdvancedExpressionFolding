package com.intellij.advancedExpressionFolding


import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.application.options.editor.CodeFoldingOptionsProvider
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.options.BeanConfigurable
import com.intellij.ui.components.ActionLink
import java.awt.Component
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel
import kotlin.reflect.KMutableProperty0

abstract class AbstractExpressionFoldingOptionsProvider : BeanConfigurable<AdvancedExpressionFoldingSettings.State?>(
    AdvancedExpressionFoldingSettings.getInstance().state
), CodeFoldingOptionsProvider {

    private val log: Logger = Logger.getInstance(AdvancedExpressionFoldingOptionsProvider::class.java)
    private val checkboxAddonMap = HashMap<String, Component>()


    protected fun checkBox(
        title: String,
        property: KMutableProperty0<Boolean>,
        exampleLinkMap: Map<UrlSuffix, Description?>? = null,
        docLink: UrlSuffix? = null
    ) {
        super.checkBox(title, property)

        try {
            val panel = JPanel()
            val gridLayout = FlowLayout(FlowLayout.LEFT)
            panel.setLayout(gridLayout)
            docLink?.let {
                addLink(panel, "doc", "https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki$it")
            }
            exampleLinkMap?.let {
                it.forEach { (file, desc) ->
                    val description = "example" + if (desc != null) {
                        " $desc"
                    } else {
                        ""
                    }

                    addLink(
                        panel,
                        description,
                        "https://raw.githubusercontent.com/AntoniRokitnicki/AdvancedExpressionFolding/master/examples/data/$file"
                    )
                }
            }
            checkboxAddonMap[title] = panel
        } catch (e: Exception) {
            log.error("Unexpected issue while creating checkBox for $title", e);
        }
    }

    private fun addLink(panel: JPanel, title: String, url: String): Component? {
        val actionLink = ActionLink(title) {
            BrowserUtil.browse(url);
        }
        actionLink.setExternalLinkIcon()
        return panel.add(actionLink)
    }

    override fun createComponent(): JComponent? {
        val superComponent = super.createComponent() ?: return null
        try {
            val mainPanel = superComponent.components?.firstOrNull().asInstance<JPanel>() ?: return superComponent
            val withLinks = mainPanel.components?.filterIsInstance<JCheckBox>()?.mapIndexed { index, checkBox ->
                val text = checkBox.text
                val link = checkboxAddonMap[text] ?: return@mapIndexed null
                Pair(link, index + 1)
            }?.filterNotNull()?.reversed() ?: return superComponent

            withLinks.forEach { (link, index) ->
                mainPanel.add(link, index)
            }

            (mainPanel.layout as? GridLayout)?.let {
                it.rows += withLinks.size
            }
        } catch (e: Exception) {
            log.error("Unexpected issue while creating component", e);
        }
        return superComponent
    }
}

typealias Description = String
typealias UrlSuffix = String