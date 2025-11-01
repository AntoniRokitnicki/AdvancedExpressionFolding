package com.intellij.advancedExpressionFolding.ranking

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import weka.classifiers.Classifier
import weka.core.Attribute
import weka.core.DenseInstance
import weka.core.Instance
import weka.core.Instances
import weka.core.SerializationHelper

object FoldingOptionRanker {
    private val logger = Logger.getInstance(FoldingOptionRanker::class.java)

    private val modelBundle: ModelBundle? by lazy { loadModel() }

    fun rank(descriptors: Array<FoldingDescriptor>, document: Document): Array<FoldingDescriptor> {
        val bundle = modelBundle ?: return descriptors
        if (descriptors.size <= 1) {
            return descriptors
        }

        val scoredDescriptors = descriptors.mapNotNull { descriptor ->
            val instance = buildInstance(bundle.header, descriptor, document) ?: return@mapNotNull null
            val probability = runCatching {
                val distribution = bundle.classifier.distributionForInstance(instance)
                val yesIndex = bundle.yesClassIndex
                when {
                    yesIndex in distribution.indices -> distribution[yesIndex]
                    distribution.isNotEmpty() -> distribution.maxOrNull() ?: 0.0
                    else -> 0.0
                }
            }.onFailure { error ->
                logger.debug("Failed to evaluate folding descriptor", error)
            }.getOrNull() ?: return@mapNotNull null

            descriptor to probability
        }

        if (scoredDescriptors.size != descriptors.size) {
            return descriptors
        }

        return scoredDescriptors
            .sortedByDescending { it.second }
            .map { it.first }
            .toTypedArray()
    }

    private fun buildInstance(header: Instances, descriptor: FoldingDescriptor, document: Document): Instance? {
        val dataset = Instances(header, 0).apply {
            if (classIndex() < 0 && numAttributes() > 0) {
                setClassIndex(numAttributes() - 1)
            }
        }
        val instance = DenseInstance(dataset.numAttributes())
        instance.setDataset(dataset)

        for (index in 0 until dataset.numAttributes()) {
            if (index == dataset.classIndex()) {
                instance.setMissing(index)
                continue
            }

            val attribute = dataset.attribute(index)
            when {
                attribute.isNumeric -> setNumeric(instance, attribute, descriptor, document)
                attribute.isNominal -> setNominal(instance, attribute, descriptor)
                else -> instance.setMissing(index)
            }
        }

        return instance
    }

    private fun setNumeric(instance: Instance, attribute: Attribute, descriptor: FoldingDescriptor, document: Document) {
        val value = when (attribute.name()) {
            "textLength" -> descriptor.element.psi?.textLength?.toDouble()
            "rangeLength" -> descriptor.range.length.toDouble()
            "lineSpan" -> descriptor.range.lineSpan(document).toDouble()
            "nestingDepth" -> descriptor.element.psi?.let { it.nestingDepth().toDouble() }
            else -> null
        }

        if (value != null) {
            instance.setValue(attribute, value)
        } else {
            instance.setMissing(attribute)
        }
    }

    private fun setNominal(instance: Instance, attribute: Attribute, descriptor: FoldingDescriptor) {
        val value = when (attribute.name()) {
            "psiElementType" -> descriptor.element.elementType.toString()
            "languageId" -> descriptor.element.psi?.language?.id
            else -> null
        }

        if (value != null) {
            val index = attribute.indexOfValue(value)
            if (index >= 0) {
                instance.setValue(attribute, value)
            } else {
                instance.setMissing(attribute)
            }
        } else {
            instance.setMissing(attribute)
        }
    }

    private fun PsiElement.nestingDepth(): Int {
        var depth = 0
        var current: PsiElement? = parent
        while (current != null) {
            depth++
            current = current.parent
        }
        return depth
    }

    private fun TextRange.lineSpan(document: Document): Int {
        if (isEmpty) {
            return 0
        }
        val startLine = document.getLineNumber(startOffset)
        val endOffsetAdjusted = (endOffset - 1).coerceAtLeast(startOffset)
        val endLine = document.getLineNumber(endOffsetAdjusted)
        return endLine - startLine + 1
    }

    private fun loadModel(): ModelBundle? {
        val resourceStream = FoldingOptionRanker::class.java.classLoader
            .getResourceAsStream(MODEL_RESOURCE_PATH) ?: return null

        return runCatching {
            resourceStream.use { stream ->
                val loaded = SerializationHelper.readAll(stream)
                val classifier = loaded.filterIsInstance<Classifier>().firstOrNull()
                    ?: loaded.firstOrNull { it is Classifier } as? Classifier

                val header = loaded.filterIsInstance<Instances>().firstOrNull()?.let { Instances(it) }
                if (classifier != null && header != null) {
                    val classAttribute = header.classAttribute() ?: header.lastAttributeAsClass()
                    val yesIndex = classAttribute?.indexOfValue("Yes")?.takeIf { it >= 0 } ?: 0
                    ModelBundle(classifier, header, yesIndex)
                } else {
                    null
                }
            }
        }.onFailure { error ->
            logger.warn("Failed to load folding ranking model", error)
        }.getOrNull()
    }

    private fun Instances.lastAttributeAsClass(): Attribute? {
        if (numAttributes() == 0) {
            return null
        }
        if (classIndex() < 0) {
            setClassIndex(numAttributes() - 1)
        }
        return classAttribute()
    }

    private data class ModelBundle(
        val classifier: Classifier,
        val header: Instances,
        val yesClassIndex: Int
    )

    private const val MODEL_RESOURCE_PATH = "model/folding-options.model"
}
