package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.application.options.CodeStyle
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.Disposable
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.encoding.EncodingProjectManager
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBCheckBox
import com.intellij.util.ui.UIUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.net.URI
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.atomic.AtomicInteger
import kotlin.reflect.KMutableProperty0

class SettingsConfigurableUiTest {
    private lateinit var fixture: CodeInsightTestFixture
    private val uniqueId = AtomicInteger()

    @BeforeEach
    fun setUp() {
        val fixtureFactory = IdeaTestFixtureFactory.getFixtureFactory()
        val lightFixture = fixtureFactory.createLightFixtureBuilder(
            LightProjectDescriptor.EMPTY_PROJECT_DESCRIPTOR,
            "SettingsConfigurableUiTest"
        ).fixture
        fixture = fixtureFactory.createCodeInsightFixture(lightFixture)
        fixture.setTestDataPath("testData")
        fixture.setUp()
        AdvancedExpressionFoldingSettings.getInstance().loadState(AdvancedExpressionFoldingSettings.State())
    }

    @AfterEach
    fun tearDown() {
        EditorFactory.getInstance().allEditors.forEach { editor ->
            EditorFactory.getInstance().releaseEditor(editor)
        }
        fixture.tearDown()
    }

    @Test
    fun `scrolling opens every example and documentation link`() {
        val context = createConfigurable()
        val configurable = context.configurable
        val panel = context.panel

        val checkboxes = UIUtil.findComponentsOfType(panel, JBCheckBox::class.java)
        val propertySnapshot = configurable.propertyToCheckboxSnapshot()
        val registeredCheckboxes = propertySnapshot.size
        assertEquals(expectedProperties.size, registeredCheckboxes)
        assertEquals(registeredCheckboxes, checkboxes.size)
        val registeredProperties = propertySnapshot.keys.map { it.name }.toSet()
        assertEquals(expectedProperties, registeredProperties)

        runInEdt {
            checkboxes.forEach { checkbox ->
                panel.scrollRectToVisible(checkbox.bounds)
            }
        }

        val actionLinks = UIUtil.findComponentsOfType(panel, ActionLink::class.java)
        val exampleLinks = actionLinks.filter { it.text.startsWith("example") }
        val docLinks = actionLinks.filter { it.text == "doc" }

        exampleLinks.forEach { link -> runInEdt { triggerLink(link) } }

        val openedExampleFiles = configurable.openedFiles.map { it.name }.toSet()
        assertEquals(expectedExampleFiles, openedExampleFiles)
        val createdExampleFiles = configurable.createdFiles.map { it.name }.toSet()
        assertEquals(expectedExampleFiles, createdExampleFiles)

        docLinks.forEach { link -> runInEdt { triggerLink(link) } }

        assertEquals(expectedDocUris, configurable.browsedUris.toSet())

        runInEdt { (panel as? Disposable)?.let(Disposer::dispose) }
        releaseEditors(context.editors)
    }

    @Test
    fun `download examples respects project encoding and line separators`() {
        val lineSeparator = CodeStyle.getDefaultSettings().lineSeparator ?: "\n"
        val encodingManager = EncodingProjectManager.getInstance(fixture.project)
        val projectEncoding: Charset = encodingManager.defaultCharset

        val context = createConfigurable()
        val configurable = context.configurable
        val panel = context.panel
        val downloadLink = UIUtil.findComponentsOfType(panel, ActionLink::class.java)
            .first { it.text == "Checkout Examples to Current Project" }

        runInEdt { triggerLink(downloadLink) }

        val dataDirectory = configurable.sourceRoot.findChild("data")
            ?: error("data directory was not created")
        dataDirectory.refresh(false, true)

        expectedExampleFiles.forEach { fileName ->
            val file = dataDirectory.findChild(fileName)
                ?: error("Expected example $fileName to be created")
            val bytes = file.contentsToByteArray()
            val text = String(bytes, projectEncoding)
            val expected = resourceText(fileName).replace("\n", lineSeparator)
            assertEquals(expected, text, "Incorrect content for $fileName")
            val expectedBytes = expected.toByteArray(projectEncoding)
            assertTrue(bytes.contentEquals(expectedBytes), "Encoding mismatch for $fileName")
        }

        runInEdt { (panel as? Disposable)?.let(Disposer::dispose) }
        releaseEditors(context.editors)
    }

    @Test
    fun `cancel resets pending changes and metadata`() {
        val context = createConfigurable()
        val configurable = context.configurable
        val panel = context.panel
        val checkboxes = UIUtil.findComponentsOfType(panel, JBCheckBox::class.java)
        val initialSelections = checkboxes.associateWith { it.isSelected }

        val toggled = checkboxes.take(3)
        toggled.forEach { checkbox -> runInEdt { checkbox.doClick() } }

        assertEquals(toggled.size, configurable.pendingChangesSnapshot().size)

        runInEdt { configurable.reset() }

        assertTrue(configurable.pendingChangesSnapshot().isEmpty())
        toggled.forEach { checkbox ->
            assertEquals(initialSelections[checkbox], checkbox.isSelected, "Checkbox ${checkbox.text} did not reset")
        }

        val propertyMap = configurable.propertyToCheckboxSnapshot()
        assertEquals(checkboxes.size, propertyMap.size)
        assertEquals(checkboxes.toSet(), propertyMap.values.toSet())

        val reopenedContext = createConfigurable()
        val reopenedConfigurable = reopenedContext.configurable
        val reopenedPanel = reopenedContext.panel
        assertTrue(reopenedConfigurable.pendingChangesSnapshot().isEmpty())
        val reopenedCheckboxes = UIUtil.findComponentsOfType(reopenedPanel, JBCheckBox::class.java)
        assertEquals(checkboxes.size, reopenedCheckboxes.size)
        assertEquals(expectedExampleFiles, reopenedConfigurable.allExampleFilesSnapshot())

        runInEdt { (panel as? Disposable)?.let(Disposer::dispose) }
        runInEdt { (reopenedPanel as? Disposable)?.let(Disposer::dispose) }
        releaseEditors(context.editors + reopenedContext.editors)
    }

    private fun createConfigurable(): ConfigurableContext {
        val editorFactory = EditorFactory.getInstance()
        val beforeEditors = editorFactory.allEditors.toSet()
        val sourceRoot = fixture.tempDirFixture.findOrCreateDir("src-${uniqueId.incrementAndGet()}")
        val configurable = TestableSettingsConfigurable(fixture.project, sourceRoot)
        val panel = computeInEdt { configurable.createComponent() }
        val afterEditors = editorFactory.allEditors.toSet()
        val newEditors = afterEditors - beforeEditors
        return ConfigurableContext(configurable, panel, newEditors.toList())
    }

    private fun triggerLink(link: ActionLink) {
        val event = ActionEvent(link, ActionEvent.ACTION_PERFORMED, link.text)
        link.getListeners(ActionListener::class.java).forEach { listener ->
            listener.actionPerformed(event)
        }
    }

    private fun resourceText(fileName: String): String {
        val url = SettingsConfigurable::class.java.classLoader.getResource("data/$fileName")
            ?: error("Missing bundled example $fileName")
        return url.readText()
    }

    companion object {
        private val providerPath: Path = Path.of(
            "src",
            "com",
            "intellij",
            "advancedExpressionFolding",
            "settings",
            "view",
            "CheckboxesProvider.kt"
        )
        private val providerContent: String = Files.readString(providerPath)
        private val exampleRegex = Regex("example\\(\"([^\"]+)\"")
        private val docRegex = Regex("link\\(\"([^\"]+)\"")
        private val propertyRegex = Regex("registerCheckbox\\(\\s*state::(\\w+)")

        internal val expectedExampleFiles: Set<String> = exampleRegex.findAll(providerContent)
            .map { it.groupValues[1] }
            .toSet()
        internal val expectedDocUris: Set<URI> = docRegex.findAll(providerContent)
            .map { URI(it.groupValues[1]) }
            .toSet()
        internal val expectedProperties: Set<String> = propertyRegex.findAll(providerContent)
            .map { it.groupValues[1] }
            .toSet()
    }
}

private class TestableSettingsConfigurable(
    private val project: Project,
    val sourceRoot: VirtualFile
) : SettingsConfigurable() {
    val openedFiles = mutableListOf<VirtualFile>()
    val createdFiles = mutableListOf<VirtualFile>()
    val browsedUris = mutableListOf<URI>()

    override fun selectedProject(): Project = project

    override fun firstSourceRoot(project: Project): VirtualFile = sourceRoot

    override fun createFile(
        directory: VirtualFile,
        file: ExampleFile,
        project: Project
    ): VirtualFile? {
        return super.createFile(directory, file, project)?.also { createdFiles += it }
    }

    override fun openFile(file: VirtualFile, project: Project) {
        openedFiles += file
    }

    override fun browseDocumentation(uri: URI) {
        browsedUris += uri
    }
}

private data class ConfigurableContext(
    val configurable: TestableSettingsConfigurable,
    val panel: DialogPanel,
    val editors: List<Editor>
)

private fun SettingsConfigurable.pendingChangesSnapshot(): Map<KMutableProperty0<Boolean>, Boolean> {
    @Suppress("UNCHECKED_CAST")
    return getPrivateField("pendingChanges") as MutableMap<KMutableProperty0<Boolean>, Boolean>
}

private fun SettingsConfigurable.propertyToCheckboxSnapshot(): Map<KMutableProperty0<Boolean>, JBCheckBox> {
    @Suppress("UNCHECKED_CAST")
    return getPrivateField("propertyToCheckbox") as MutableMap<KMutableProperty0<Boolean>, JBCheckBox>
}

private fun SettingsConfigurable.allExampleFilesSnapshot(): Set<String> {
    @Suppress("UNCHECKED_CAST")
    return (getPrivateField("allExampleFiles") as MutableSet<String>).toSet()
}

private fun SettingsConfigurable.getPrivateField(name: String): Any {
    val field = SettingsConfigurable::class.java.getDeclaredField(name)
    field.isAccessible = true
    return field.get(this)
}

private fun releaseEditors(editors: List<Editor>) {
    if (editors.isEmpty()) return
    val factory = EditorFactory.getInstance()
    runInEdt {
        editors.forEach { factory.releaseEditor(it) }
    }
}

private fun runInEdt(action: () -> Unit) {
    ApplicationManager.getApplication().invokeAndWait(action)
}

private fun <T> computeInEdt(action: () -> T): T {
    var result: T? = null
    ApplicationManager.getApplication().invokeAndWait {
        result = action()
    }
    return result ?: error("EDT computation returned null")
}
