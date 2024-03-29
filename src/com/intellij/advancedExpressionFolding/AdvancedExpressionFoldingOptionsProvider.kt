package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.application.options.editor.CodeFoldingOptionsProvider
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.options.BeanConfigurable
import com.intellij.ui.components.ActionLink
import java.awt.Component
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel
import kotlin.reflect.KMutableProperty0

class AdvancedExpressionFoldingOptionsProvider protected constructor() :
    BeanConfigurable<AdvancedExpressionFoldingSettings.State?>(
        AdvancedExpressionFoldingSettings.getInstance().state
    ), CodeFoldingOptionsProvider {

    private val checkboxAddonMap = HashMap<String, Component>()

    init {
        val state = AdvancedExpressionFoldingSettings.getInstance().state

        title = "Advanced Expression Folding 2"
        checkBox(
            "Getters and setters as properties",
            state::getSetExpressionsCollapse,
            mapOf("GetterSetterTestData.java" to null)
        )
        checkBox(
            "Variable declarations (var/val)",
            state::varExpressionsCollapse,
            mapOf("VarTestData.java" to null)
        )
        checkBox(
            "Compact control flow condition syntax (Golang ifs)",
            state::compactControlFlowSyntaxCollapse,
            mapOf("CompactControlFlowTestData.java" to null)
        )
        checkBox(
            "List.get, List.set, Map.get and Map.put expressions, array and list literals",
            state::getExpressionsCollapse,
            mapOf("GetSetPutTestData.java" to null)
        )

        checkBox(
            "StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions",
            state::concatenationExpressionsCollapse,
            mapOf("StringBuilderTestData.java" to "StringBuilder", "InterpolatedStringTestData.java" to "Interpolate")
        )
        checkBox(
            "List.subList and String.substring expressions",
            state::slicingExpressionsCollapse,
            mapOf("SliceTestData.java" to null)
        )
        checkBox(
            "Object.equals and Comparable.compareTo expressions",
            state::comparingExpressionsCollapse,
            mapOf("EqualsCompareTestData.java" to null)
        )

        checkBox(
            "Java.time isBefore/isAfter expressions",
            state::comparingLocalDatesCollapse,
            mapOf("LocalDateTestData.java" to null)
        )
        checkBox(
            "LocalDate.of literals (e.g. 2018-02-12)",
            state::localDateLiteralCollapse,
            mapOf("LocalDateLiteralTestData.java" to null)
        )
        checkBox(
            "Postfix LocalDate literals (e.g. 2018Y-02M-12D)",
            state::localDateLiteralPostfixCollapse,
            mapOf("LocalDateLiteralPostfixTestData.java" to null)
        )

        checkBox(
            "Type cast expressions",
            state::castExpressionsCollapse,
            mapOf("TypeCastTestData.java" to null)
        )
        checkBox(
            "For loops, range expressions",
            state::rangeExpressionsCollapse,
            mapOf("ForRangeTestData.java" to null)
        )
        checkBox(
            "Null-safe calls",
            state::checkExpressionsCollapse,
            mapOf("ElvisTestData.java" to null)
        )
        checkBox(
            "Extended null-safe ifs",
            state::ifNullSafe,
            mapOf("IfNullSafeData.java" to null),
            "/Extended-null‐safe-ifs"
        )
        checkBox(
            "Kotlin quick return",
            state::kotlinQuickReturn,
            mapOf("LetReturnIt.java" to null)
        )

        checkBox(
            "Asserts",
            state::assertsCollapse,
            mapOf("AssertTestData.java" to null)
        )

        checkBox(
            "Display optional as Kotlin null-safe",
            state::optional,
            mapOf("OptionalTestData.java" to null)
        )
        checkBox(
            "Display stream operations as Groovy's spread operator",
            state::streamSpread,
            mapOf("SpreadTestData.java" to null)
        )

        checkBox(
            "Display Java bean as Lombok",
            state::lombok,
            mapOf("LombokTestData.java" to null)
        )
        checkBox(
            "Log folding",
            state::logFolding,
            mapOf("LogBrackets.java" to null),
            "/Log-brackets-folding"
        )
        checkBox(
            "Display mapping of field with same name as << (for builders, setters and assignments)",
            state::fieldShift,
            mapOf(
                "FieldShiftBuilder.java" to "builders",
                "FieldShiftSetters.java" to "setters",
                "FieldShiftFields.java" to "fields",
            ),
            "/FieldShift"
        )
        checkBox(
            "Destructuring assignment for array & list",
            state::destructuring,
            mapOf(
                "DestructuringAssignmentArrayTestData.java" to "array",
                "DestructuringAssignmentListTestData.java" to "list"
            ),
            "/Destructuring-assignment"
        )
        checkBox(
            "Control flow single-statement code block braces (read-only files)",
            state::controlFlowSingleStatementCodeBlockCollapse,
            mapOf("ControlFlowSingleStatementTestData.java" to null)
        )
        checkBox(
            "Control flow multi-statement code block braces (read-only files, deprecated)",
            state::controlFlowMultiStatementCodeBlockCollapse,
            mapOf("ControlFlowMultiStatementTestData.java" to null)
        )
        checkBox(
            "Semicolons (read-only files)",
            state::semicolonsCollapse,
            mapOf("SemicolonTestData.java" to null)
        )
        checkBox("Folding of testData in diff",
            state::testDataFoldingDiff,
            docLink = "/Folding-of-testData-in-diff")
    }

    private fun checkBox(
        title: String,
        prop: KMutableProperty0<Boolean>,
        exampleLinkMap: Map<UrlSuffix, Description?>? = null,
        docLink: UrlSuffix? = null
    ) {
        super.checkBox(title, prop)

        //val panel = UI.PanelFactory.grid().createPanel()
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
                    "https://raw.githubusercontent.com/AntoniRokitnicki/AdvancedExpressionFolding/master/test/data/$file"
                )
            }
        }
        checkboxAddonMap[title] = panel
    }

    private fun addLink(panel: JPanel, title: String, url: String): Component? {
        val link = ActionLink(title) {
            BrowserUtil.browse(url);
        }
        link.setExternalLinkIcon()
        return panel.add(link)
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
            // if implemenation changes
            TODO("Not yet implemented")
        }
        return superComponent
    }

}

typealias Description = String
typealias UrlSuffix = String
