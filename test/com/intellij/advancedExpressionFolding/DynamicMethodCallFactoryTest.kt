package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.testFramework.junit5.TestApplication
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@TestApplication
class DynamicMethodCallFactoryTest {
    @AfterEach
    fun tearDown() {
        MethodCallFactory.initialize(TestDynamicDataProvider())
    }

    @Test
    fun `loads dynamic method mappings`() {
        val provider = object : IDynamicDataProvider {
            override fun parse(): List<DynamicMethodCall> = parseToml(
                """
                staticMethod.method = 'staticMethod'
                staticMethod.newName = 'changedStaticMethod'
                """.trimIndent(),
            )
        }

        MethodCallFactory.initialize(provider)
        val calls = MethodCallFactory.findByMethodName("staticMethod")
        assertNotNull(calls)
        val dynamic = calls!!.singleOrNull() as? DynamicMethodCall
        assertNotNull(dynamic)
        assertEquals("staticMethod", dynamic!!.data.method)
        assertEquals("changedStaticMethod", dynamic.data.newName)
    }
}
