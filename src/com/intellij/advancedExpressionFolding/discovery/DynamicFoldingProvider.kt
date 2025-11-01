package com.intellij.advancedExpressionFolding.discovery

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ReadAction
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.SmartPointerManager
import com.intellij.util.concurrency.AppExecutorUtil
import com.intellij.openapi.util.TextRange
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference

@Service(Service.Level.PROJECT)
class DynamicFoldingProvider(private val project: Project) {

    private val extractor = PsiVectorExtractor(project)
    private val clusterer = IncrementalClusterer()
    private val miner = PatternMiner()
    private val storage = DiscoveredRuleStorage()
    private val rulesRef = AtomicReference(storage.getRules())
    private val analysedFiles = ConcurrentHashMap<String, Long>()

    fun contribute(element: PsiElement, @Suppress("UNUSED_PARAMETER") document: Document): List<FoldingDescriptor> {
        val application = ApplicationManager.getApplication()
        if (application != null && application.isUnitTestMode) {
            return emptyList()
        }
        val psiFile = element.containingFile ?: return emptyList()
        scheduleDiscovery(psiFile)
        val rules = rulesRef.get().filter(DiscoveredFoldingRule::enabled)
        if (rules.isEmpty()) {
            return emptyList()
        }
        val vectors = extractor.extractVectors(psiFile)
        if (vectors.isEmpty()) {
            return emptyList()
        }
        val descriptors = mutableListOf<FoldingDescriptor>()
        val usedRanges = mutableListOf<TextRange>()
        for (vector in vectors) {
            val elementPointer = vector.element ?: continue
            val best = rules
                .map { rule -> rule to VectorMath.cosineSimilarity(rule.centroidArray(), vector.vector) }
                .maxByOrNull { it.second }
                ?: continue
            val rule = best.first
            val similarity = best.second
            val threshold = (rule.confidence + rule.silhouette) / 2.0 - 0.1
            if (similarity < threshold.coerceAtLeast(0.5)) {
                continue
            }
            val range = elementPointer.textRange ?: continue
            if (usedRanges.any { it.intersects(range) }) {
                continue
            }
            descriptors += FoldingDescriptor(
                elementPointer.node,
                range,
                null,
                rule.placeholderText,
                true,
                emptySet()
            )
            usedRanges += range
        }
        return descriptors
    }

    private fun scheduleDiscovery(file: PsiFile) {
        val application = ApplicationManager.getApplication()
        if (application != null && application.isUnitTestMode) {
            return
        }
        if (project.isDisposed) {
            return
        }
        val vFile = file.virtualFile ?: return
        val key = vFile.path
        val now = System.currentTimeMillis()
        val last = analysedFiles[key]
        if (last != null && now - last < 10_000) {
            return
        }
        analysedFiles[key] = now
        val pointer = SmartPointerManager.getInstance(project).createSmartPsiElementPointer(file)
        AppExecutorUtil.getAppExecutorService().execute {
            if (project.isDisposed) {
                return@execute
            }
            val psiFile = pointer.element as? PsiFile ?: return@execute
            val vectors = ReadAction.compute<List<PsiFeatureVector>, RuntimeException> {
                extractor.extractVectors(psiFile)
            }
            if (vectors.isEmpty()) {
                return@execute
            }
            val candidates = clusterer.addVectors(vectors).ifEmpty { clusterer.forceCluster() }
            candidates.forEach { candidate ->
                val rule = miner.mine(candidate) ?: return@forEach
                if (isCoveredByStaticRules(rule)) {
                    return@forEach
                }
                val stored = storage.upsert(rule)
                rulesRef.updateAndGet { current ->
                    val index = current.indexOfFirst { it.pattern == stored.pattern }
                    if (index >= 0) {
                        current.toMutableList().apply { this[index] = stored }
                    } else {
                        current + stored
                    }
                }
            }
        }
    }

    private fun isCoveredByStaticRules(rule: DiscoveredFoldingRule): Boolean {
        val placeholder = rule.placeholderText.lowercase()
        if (placeholder in ALLOW_DYNAMIC_PREFIXES) {
            return false
        }
        return KNOWN_STATIC_PREFIXES.any { prefix -> placeholder.startsWith(prefix) }
    }

    fun reload() {
        rulesRef.set(storage.getRules())
    }

    companion object {
        private val KNOWN_STATIC_PREFIXES = setOf("logger", "stream", "builder", "guard", "null-safety")
        private val ALLOW_DYNAMIC_PREFIXES = setOf(
            "logger…",
            "logger...",
            "stream…",
            "stream...",
            "builder…",
            "builder...",
            "guard…",
            "guard...",
            "null-safety…",
            "null-safety..."
        )

        fun getInstance(project: Project): DynamicFoldingProvider = project.service()
    }
}
