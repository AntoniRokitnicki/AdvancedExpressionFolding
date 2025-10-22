package com.intellij.advancedExpressionFolding.view

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.util.Segment
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.application.ApplicationManager
import com.intellij.testFramework.PlatformTestUtil
import com.intellij.usages.UsageInfo2UsageAdapter
import com.intellij.usages.impl.UsageViewImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class FindUsageCustomViewTest : BaseTest() {

    @Test
    fun `addToUsage appends usage asynchronously`() {
        val psiFile = fixture.configureByText(
            "Sample.java",
            """
            class Sample {
                void target() {}
            }
            """.trimIndent()
        )
        val method = runReadAction { psiFile.children.first { it.text.contains("target") } }
        val range = runReadAction { TextRange(method.textRange.startOffset, method.textRange.endOffset) }

        val customView = createViewOnEdt()
        customView.addToUsage(method, range)

        val usageView = customView.extractUsageView()
        waitUntil("usage was not appended") { runReadAction { usageView.usagesCount } == 1 }

        runReadAction {
            val usage = usageView.usages.single()
            val adapter = assertInstanceOf(UsageInfo2UsageAdapter::class.java, usage)
            assertEquals(method.containingFile, adapter.usageInfo.file)
            assertEquals(range, adapter.usageInfo.extractRange())
        }
    }

    @Test
    fun `searchFinished is emitted after results are appended`() {
        val psiFile = fixture.configureByText(
            "Sample.java",
            """
            class Sample {
                void first() {}
                void second() {}
            }
            """.trimIndent()
        )
        val method = runReadAction { psiFile.children.first { it.text.contains("first") } }
        val range = runReadAction { TextRange(method.textRange.startOffset, method.textRange.endOffset) }

        val customView = createViewOnEdt()
        customView.addToUsage(method, range)

        val usageView = customView.extractUsageView()
        waitUntil("usage view did not receive appended result") { runReadAction { usageView.usagesCount } == 1 }
        waitUntil("search did not finish") { runReadAction { !usageView.isSearchInProgressCompat() } }
    }

    private fun createViewOnEdt(): FindUsageCustomView {
        var view: FindUsageCustomView? = null
        ApplicationManager.getApplication().invokeAndWait {
            view = FindUsageCustomView(fixture.project, "test view")
        }
        return view!!
    }

    private fun com.intellij.usageView.UsageInfo.extractRange(): TextRange {
        val method = javaClass.methods.firstOrNull { it.name == "getRange" && it.parameterCount == 0 }
            ?: javaClass.methods.firstOrNull { it.name == "getSegment" && it.parameterCount == 0 }
            ?: javaClass.methods.firstOrNull { it.name == "getRangeInElement" && it.parameterCount == 0 }
            ?: fail("Unable to locate range accessor on ${javaClass.name}")
        val value = method.invoke(this) ?: fail("Range accessor returned null for ${javaClass.name}")
        return when (value) {
            is TextRange -> value
            is Segment -> TextRange(value.startOffset, value.endOffset)
            else -> fail("Unsupported range accessor type ${value.javaClass}")
        }
    }

    private fun FindUsageCustomView.extractUsageView(): UsageViewImpl {
        val field = FindUsageCustomView::class.java.getDeclaredField("usageView")
        field.isAccessible = true
        return field.get(this) as UsageViewImpl
    }

    private fun UsageViewImpl.isSearchInProgressCompat(): Boolean {
        val method = javaClass.methods.firstOrNull { it.name == "isSearchInProgress" && it.parameterCount == 0 }
        if (method != null) {
            method.isAccessible = true
            return method.invoke(this) as Boolean
        }
        val field = javaClass.declaredFields.firstOrNull {
            it.name.contains("search", ignoreCase = true) &&
                (it.type == Boolean::class.javaPrimitiveType || it.type == java.lang.Boolean::class.java || it.type == AtomicBoolean::class.java)
        } ?: fail("Unable to locate search progress flag on ${javaClass.name}")
        field.isAccessible = true
        val value = field.get(this)
        return when (value) {
            is Boolean -> value
            is AtomicBoolean -> value.get()
            else -> fail("Unsupported search progress holder ${value?.javaClass}")
        }
    }

    private fun waitUntil(message: String, timeoutMillis: Long = TimeUnit.SECONDS.toMillis(5), condition: () -> Boolean) {
        val deadline = System.currentTimeMillis() + timeoutMillis
        while (System.currentTimeMillis() < deadline) {
            PlatformTestUtil.dispatchAllInvocationEventsInIdeEventQueue()
            if (condition()) {
                return
            }
            Thread.sleep(10)
        }
        PlatformTestUtil.dispatchAllInvocationEventsInIdeEventQueue()
        fail(message)
    }
}
