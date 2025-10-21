package com.intellij.advancedExpressionFolding.processor.methodcall

import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.ConfigurationParser
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.processor.takeIfTrue
import com.intellij.advancedExpressionFolding.processor.util.Consts

typealias MethodName = String
typealias ClassName = String

/**
 * Thread-safe singleton factory for method call folding definitions.
 *
 * **Threading Model:**
 * - Uses @Volatile fields for lock-free reads (frequent folding operations)
 * - Uses synchronized for atomic multi-field updates (rare configuration changes)
 * - This dual approach optimizes for the read-heavy/write-rare access pattern
 * - Direct @Volatile field access is faster than AtomicReference.get() for reads
 *
 * **Why both @Volatile AND synchronized:**
 * - @Volatile: Ensures immediate visibility of updates to all reader threads
 * - synchronized: Ensures atomicity when updating multiple related fields together
 * - Reader threads (folding builders) never block and always see consistent state
 * - Writer threads (configuration changes) update all fields atomically
 *
 * **Lifecycle:**
 * 1. Lazy initialization during first PsiMethodCallExpression folding
 * 2. Background reads during method call folding operations
 * 3. Runtime updates via AddDynamicMethodFoldingIntention
 *
 * **Performance characteristics:**
 * - Reads: O(1) HashMap lookup, no synchronization overhead
 * - Writes: O(n) reconstruction of all mappings, synchronized but infrequent
 */
object MethodCallFactory : BaseExtension(){

    @Volatile
    private var dynamicProvider: IDynamicDataProvider? = ConfigurationParser

    @Volatile
    private var methodCallMap: Map<MethodName?, List<AbstractMethodCall>> = emptyMap()
    @Volatile
    var supportedClasses: Collection<ClassName> = emptyList()
    @Volatile
    var supportedMethods: Collection<MethodName> = emptyList()
    @Volatile
    var classlessMethods: Collection<MethodName> = emptyList()

    fun refreshMethodCallMappings(dynamicProvider: IDynamicDataProvider? = null) {
        synchronized(this) {
            if (dynamicProvider != null) {
                // for tests
                this.dynamicProvider = dynamicProvider
            }

            methodCallMap = createMethodCalls()

            supportedClasses = createSupportedClasses()
            supportedMethods = createSupportedMethods()

            classlessMethods = createClasslessMethods()
        }
    }

    fun initialize(dynamicProvider: IDynamicDataProvider? = ConfigurationParser): MethodCallFactory {
        if (dynamicProvider != null) {
            this.dynamicProvider = dynamicProvider
        }
        refreshMethodCallMappings()
        return this
    }

    private fun createMethodCalls(): Map<MethodName?, List<AbstractMethodCall>> =
        getAllMethodCalls().filter {
            it.canExecute()
        }.flatMap { methodCall ->
            methodCall.methodNames.map { methodName ->
                methodName to methodCall
            }
        }.groupBy(
            { it.first },
            { it.second }
        ).mapValues { entry ->
            entry.value.sortedWith(compareBy {
                it.classNames.isEmpty()
            })
        }

    private fun getAllMethodCalls(): List<AbstractMethodCall> = MethodCallManager.methodCalls + loadDynamicMethods().orEmpty()

    private fun loadDynamicMethods(): List<DynamicMethodCall>? = dynamic.takeIfTrue()?.let { dynamicProvider?.parse() }

    private fun createSupportedClasses(): Collection<ClassName> =
        methodCallMap.values
            .asSequence()
            .flatten()
            .filter {
                it.classNames.isNotEmpty()
            }.map {
                it.classNames
            }.distinct().flatten()
            .toList()

    private fun createSupportedMethods(): List<MethodName> =
        methodCallMap.values
            .flatten()
            .map {
                it.methodNames
            }.flatten()

    private fun createClasslessMethods(): List<MethodName> =
        methodCallMap.values
            .flatten()
            .filter {
                it.classNames.isEmpty()
            }.map {
                it.methodNames
            }.flatten() + Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS

    fun findByMethodName(methodName: MethodName?): List<AbstractMethodCall>? =
        methodCallMap[methodName]
}
