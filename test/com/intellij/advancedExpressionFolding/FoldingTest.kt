package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import com.intellij.openapi.application.runInEdt
import com.intellij.platform.testFramework.core.FileComparisonFailedError
import io.kotest.core.spec.style.FunSpec
import org.opentest4j.TestAbortedException
import java.lang.reflect.InvocationTargetException
import kotlin.reflect.KMutableProperty0

class FoldingTest : FunSpec({
    registerStandardFoldingTests()
})

internal fun FunSpec.registerStandardFoldingTests(
    caseFactory: () -> FoldingTestCase = ::FoldingTestCase,
    excludedTests: Set<String> = emptySet(),
) {
    fun register(name: String, block: FoldingTestCase.() -> Unit) {
        if (name in excludedTests) return
        registerFoldingTest(name, caseFactory, block)
    }

    register("elvisTestData") {
        doFoldingTest(state::checkExpressionsCollapse)
    }

    register("forRangeTestData") {
        doFoldingTest(state::rangeExpressionsCollapse)
    }

    register("stringBuilderTestData") {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    register("interpolatedStringTestData") {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    register("getSetPutTestData") {
        doFoldingTest(state::getExpressionsCollapse)
    }

    register("sliceTestData") {
        doFoldingTest(state::slicingExpressionsCollapse)
    }

    register("appendSetterInterpolatedStringTestData") {
        doFoldingTest(state::concatenationExpressionsCollapse, state::getSetExpressionsCollapse)
    }

    register("equalsCompareTestData") {
        doFoldingTest(state::comparingExpressionsCollapse)
    }

    register("typeCastTestData") {
        doFoldingTest(state::castExpressionsCollapse)
    }

    register("varTestData") {
        doFoldingTest(state::varExpressionsCollapse)
    }

    register("getterSetterTestData") {
        doFoldingTest(state::getSetExpressionsCollapse)
    }

    register("controlFlowSingleStatementTestData") {
        doReadOnlyFoldingTest(state::controlFlowSingleStatementCodeBlockCollapse)
    }

    register("controlFlowMultiStatementTestData") {
        doReadOnlyFoldingTest(state::controlFlowMultiStatementCodeBlockCollapse)
    }

    register("localDateTestData") {
        doReadOnlyFoldingTest(state::comparingLocalDatesCollapse)
    }

    register("localDateLiteralTestData") {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse)
    }

    register("localDateLiteralPostfixTestData") {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse, state::localDateLiteralPostfixCollapse)
    }

    register("compactControlFlowTestData") {
        doFoldingTest(state::compactControlFlowSyntaxCollapse)
    }

    register("semicolonTestData") {
        doReadOnlyFoldingTest(state::semicolonsCollapse)
    }

    register("assertTestData") {
        doReadOnlyFoldingTest(state::assertsCollapse)
    }

    register("concatenationTestData") {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    register("optionalTestData") {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    register("spreadTestData") {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    register("lombokTestData") {
        doFoldingTest(state::lombok)
    }

    register("lombokUsageTestData") {
        doFoldingTest(state::lombok)
    }

    register("fieldShiftBuilder") {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    register("fieldShiftSetters") {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    register("letReturnIt") {
        doFoldingTest(state::varExpressionsCollapse, state::kotlinQuickReturn)
    }

    register("ifNullSafeData") {
        doFoldingTest(state::checkExpressionsCollapse, state::getSetExpressionsCollapse, state::ifNullSafe)
    }

    register("logBrackets") {
        doFoldingTest(state::getSetExpressionsCollapse, state::logFolding)
    }

    register("logFoldingTextBlocksTestData") {
        doFoldingTest(state::getSetExpressionsCollapse, state::logFolding, state::logFoldingTextBlocks)
    }

    register("fieldShiftFields") {
        doFoldingTest(state::getSetExpressionsCollapse, state::fieldShift)
    }

    register("destructuringAssignmentArrayTestData") {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse, state::varExpressionsCollapse)
    }

    register("destructuringAssignmentArrayWithoutValTestData") {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse)
    }

    register("destructuringAssignmentListTestData") {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse, state::varExpressionsCollapse)
    }

    register("destructuringAssignmentListWithoutValTestData") {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse)
    }

    register("printlnTestData") {
        doFoldingTest(state::println)
    }

    register("nullableAnnotationTestData") {
        doFoldingTest(state::nullable, state::lombok)
    }

    register("nullableAnnotationCheckNotNullTestData") {
        doFoldingTest(state::nullable, state::getSetExpressionsCollapse)
    }

    register("nullableAnnotationCheckNotNullFieldShiftTestData") {
        doFoldingTest(state::nullable, state::getSetExpressionsCollapse, state::fieldShift)
    }

    register("constTestData") {
        doFoldingTest(state::const)
    }

    register("finalRemovalTestData") {
        doFoldingTest(state::finalRemoval)
    }

    register("finalEmojiTestData") {
        @Suppress("DEPRECATION")
        doFoldingTest(state::finalEmoji)
    }

    register("lombokDirtyOffTestData") {
        doFoldingTest(state::lombok, state::lombokDirtyOff)
    }

    register("expressionFuncTestData") {
        doFoldingTest(state::expressionFunc)
    }

    register("dynamicTestData") {
        val dynamicProvider = object : IDynamicDataProvider {
            override fun parse(): List<DynamicMethodCall> {
                return parseToml(
                    """
normalMethod.method = 'normalMethod'
normalMethod.newName = 'changedNormalMethod'
staticMethod.method = 'staticMethod'
staticMethod.newName = 'changedStaticMethod'
                    """.trimIndent(),
                )
            }
        }
        doFoldingTest(state::dynamic, state::getSetExpressionsCollapse, dynamic = dynamicProvider)
    }

    register("arithmeticExpressionsTestData") {
        @Suppress("DEPRECATION")
        doFoldingTest(state::arithmeticExpressions)
    }

    register("emojifyTestData") {
        @Suppress("DEPRECATION")
        doFoldingTest(state::emojify)
    }

    register("interfaceExtensionPropertiesTestData") {
        doFoldingTest(state::interfaceExtensionProperties, state::lombok, state::nullable)
    }

    register("patternMatchingInstanceofTestData") {
        doFoldingTest(state::patternMatchingInstanceof)
    }

    register("summaryParentOverrideTestData") {
        doFoldingTest(state::summaryParentOverride)
    }

    register("constructorReferenceNotationTestData") {
        doFoldingTest(state::constructorReferenceNotation)
    }

    register("constructorReferenceNotationWithConstTestData") {
        doFoldingTest(state::constructorReferenceNotation, state::const)
    }

    register("methodDefaultParametersTestData") {
        doFoldingTest(state::methodDefaultParameters)
    }

    register("lombokPatternOffTestData") {
        state.lombokPatternOff = "LombokPa[t]{2}ernOffTestData"
        try {
            doFoldingTest(state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }

    register("lombokPatternOffNegativeTestData") {
        state.lombokPatternOff = "LombokPatternOffTestData"
        try {
            doFoldingTest(state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }

    register("overrideHideTestData") {
        doFoldingTest(state::overrideHide)
    }

    register("suppressWarningsHideTestData") {
        doFoldingTest(state::suppressWarningsHide)
    }

    register("experimentalTestData") {
        doFoldingTest(state::experimental, state::nullable, state::const, state::lombok, state::getExpressionsCollapse)
    }
}

internal fun FunSpec.registerFoldingTest(
    name: String,
    caseFactory: () -> FoldingTestCase = ::FoldingTestCase,
    testBody: FoldingTestCase.() -> Unit,
) {
    test(name) {
        withFoldingTestCase(name, caseFactory, testBody)
    }
}

internal suspend fun withFoldingTestCase(
    name: String,
    caseFactory: () -> FoldingTestCase = ::FoldingTestCase,
    testBody: FoldingTestCase.() -> Unit,
) {
    val testCase = caseFactory()
    testCase.initializeTestName(name)
    var beforeSucceeded = false
    try {
        testCase.beforeTest()
        beforeSucceeded = true
        testCase.testBody()
    } finally {
        if (beforeSucceeded) {
            testCase.afterTest()
        }
    }
}

open class FoldingTestCase : BaseTest() {

    class TooComplexException : TestAbortedException("TOO COMPLEX FOLDING")
    class FoldingChangedException : AssertionError()

    val state: State by lazy { getInstance().state }

    fun initializeTestName(name: String) {
        val testCase = getUnderlyingTestCase()
        val method = findLifecycleMethod(testCase.javaClass, "setName", String::class.java)
        invokeLifecycle(testCase, method, name)
        val testNameRuleField = findLifecycleFieldOrNull(javaClass, "testNameRule")
        val testNameRule = testNameRuleField?.get(this)
        if (testNameRule != null) {
            val setMethodName = runCatching {
                findLifecycleMethod(testNameRule.javaClass, "setMethodName", String::class.java)
            }.getOrNull()
            if (setMethodName != null) {
                invokeLifecycle(testNameRule, setMethodName, name)
            } else {
                val methodNameField = findLifecycleFieldOrNull(testNameRule.javaClass, "methodName")
                methodNameField?.set(testNameRule, name)
            }
        }
    }

    open fun beforeTest() {
        val testCase = getUnderlyingTestCase()
        val method = findLifecycleMethod(testCase.javaClass, "setUp")
        invokeLifecycle(testCase, method)
    }

    open fun afterTest() {
        val testCase = getUnderlyingTestCase()
        val method = findLifecycleMethod(testCase.javaClass, "tearDown")
        invokeLifecycle(testCase, method)
    }

    open fun assignState(vararg turnOnProperties: KMutableProperty0<Boolean>) {
        getInstance().disableAll()
        turnOnProperties.forEach {
            it.set(true)
        }
    }

    open fun doFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider(),
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        try {
            super.doFoldingTest(null)
        } catch (_: FileComparisonFailedError) {
            throw FoldingChangedException()
        } catch (e: IllegalArgumentException) {
            if (e.message == "Comparison method violates its general contract!") {
                throw TooComplexException()
            }
        }
    }

    open fun doReadOnlyFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider(),
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        runInEdt {
            super.doReadOnlyFoldingTest()
        }
    }

    private fun findLifecycleMethod(targetClass: Class<*>, name: String, vararg parameterTypes: Class<*>): java.lang.reflect.Method {
        var type: Class<*>? = targetClass
        while (type != null) {
            try {
                return type.getDeclaredMethod(name, *parameterTypes).apply {
                    isAccessible = true
                }
            } catch (_: NoSuchMethodException) {
                type = type.superclass
            }
        }
        error("Method $name not found on ${targetClass.name}")
    }

    private fun findLifecycleFieldOrNull(targetClass: Class<*>, name: String): java.lang.reflect.Field? {
        var type: Class<*>? = targetClass
        while (type != null) {
            try {
                return type.getDeclaredField(name).apply {
                    isAccessible = true
                }
            } catch (_: NoSuchFieldException) {
                type = type.superclass
            }
        }
        return null
    }

    private fun invokeLifecycle(target: Any, method: java.lang.reflect.Method, vararg args: Any?) {
        val targetMethod = if (method.declaringClass.isInstance(target)) {
            method
        } else {
            val compatibleDeclaringClass = Class.forName(method.declaringClass.name, false, target.javaClass.classLoader)
            compatibleDeclaringClass.getDeclaredMethod(method.name, *method.parameterTypes).apply {
                isAccessible = true
            }
        }
        try {
            targetMethod.invoke(target, *args)
        } catch (e: InvocationTargetException) {
            val cause = e.targetException ?: e
            when (cause) {
                is RuntimeException -> throw cause
                is Error -> throw cause
                else -> throw RuntimeException(cause)
            }
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
    }

    private fun getUnderlyingTestCase(): Any {
        val testCase = findLifecycleFieldOrNull(javaClass, "testCase")?.get(this)
        return testCase ?: error("JUnit testCase field is not initialized")
    }

}
