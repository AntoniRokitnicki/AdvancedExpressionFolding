package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.testFramework.ExtensionTestUtil
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.codeInsight.completion.CompletionContributorEP
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertNotNull

class PropertyChangeListenerAnnotationCompletionContributorTest : BaseTest() {

    companion object {
        private var originalPseudoAnnotationsValue: Boolean = false
        private val completionContributorEp = ExtensionPointName.create<CompletionContributorEP>(
            "com.intellij.completion.contributor"
        )
    }

    @BeforeEach
    fun setUp() {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        originalPseudoAnnotationsValue = settings.state.pseudoAnnotations
        settings.state.pseudoAnnotations = true

        val completionContributors = completionContributorEp.point.extensionList
        val filteredContributors = completionContributors.filter {
            it.implementationClass?.startsWith("com.intellij.advancedExpressionFolding") == true
        }
        ExtensionTestUtil.maskExtensions(completionContributorEp, filteredContributors, fixture.testRootDisposable, false)
    }

    @AfterEach
    fun tearDown() {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        settings.state.pseudoAnnotations = originalPseudoAnnotationsValue
    }

    @Test
    fun `should offer @PropertyChangeListener for class`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                @<caret>
                public class Test {
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        assertNotNull(completions)
        assertTrue(completions.any { it.lookupString == "PropertyChangeListener" })
    }

    @Test
    fun `should generate infrastructure when selecting @PropertyChangeListener on class`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                @<caret>
                public class Test {
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        val propertyChange = completions?.find { it.lookupString == "PropertyChangeListener" }
        assertNotNull(propertyChange)
        val completion = propertyChange!!

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = completion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(
            @Language("JAVA") """
                import java.beans.PropertyChangeListener;
                import java.beans.PropertyChangeSupport;

                public class Test {
                    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

                    public void addPropertyChangeListener(PropertyChangeListener listener) {
                        propertyChangeSupport.addPropertyChangeListener(listener);
                    }

                    public void removePropertyChangeListener(PropertyChangeListener listener) {
                        propertyChangeSupport.removePropertyChangeListener(listener);
                    }

                    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
                        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
                    }
                }
            """.trimIndent()
        )
    }

    @Test
    fun `should generate setter when selecting @PropertyChangeListener on field`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                public class Test {
                    @<caret>
                    private String name;
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        val propertyChange = completions?.find { it.lookupString == "PropertyChangeListener" }
        assertNotNull(propertyChange)
        val completion = propertyChange!!

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = completion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(
            @Language("JAVA") """
                import java.beans.PropertyChangeListener;
                import java.beans.PropertyChangeSupport;

                public class Test {
                    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
                    private String name;

                    public void addPropertyChangeListener(PropertyChangeListener listener) {
                        propertyChangeSupport.addPropertyChangeListener(listener);
                    }

                    public void removePropertyChangeListener(PropertyChangeListener listener) {
                        propertyChangeSupport.removePropertyChangeListener(listener);
                    }

                    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
                        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
                    }

                    public void setName(String name) {
                        String oldName = this.name;
                        this.name = name;
                        firePropertyChange("name", oldName, name);
                    }
                }
            """.trimIndent()
        )
    }

    @Test
    fun `should update existing setter to fire property change`() {
        fixture.configureByText(
            "Test.java",
            @Language("JAVA") """
                public class Test {
                    @<caret>
                    private String name;

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            """.trimIndent()
        )

        val completions = fixture.complete(CompletionType.BASIC)
        val propertyChange = completions?.find { it.lookupString == "PropertyChangeListener" }
        assertNotNull(propertyChange)
        val completion = propertyChange!!

        ApplicationManager.getApplication().invokeAndWait {
            fixture.lookup.currentItem = completion
            fixture.finishLookup('\n')
        }

        fixture.checkResult(
            @Language("JAVA") """
                import java.beans.PropertyChangeListener;
                import java.beans.PropertyChangeSupport;

                public class Test {
                    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
                    private String name;

                    public void setName(String name) {
                        String oldName = this.name;
                        this.name = name;
                        firePropertyChange("name", oldName, name);
                    }

                    public void addPropertyChangeListener(PropertyChangeListener listener) {
                        propertyChangeSupport.addPropertyChangeListener(listener);
                    }

                    public void removePropertyChangeListener(PropertyChangeListener listener) {
                        propertyChangeSupport.removePropertyChangeListener(listener);
                    }

                    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
                        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
                    }
                }
            """.trimIndent()
        )
    }
}
