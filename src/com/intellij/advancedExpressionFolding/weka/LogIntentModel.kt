package com.intellij.advancedExpressionFolding.weka

import com.intellij.openapi.diagnostic.Logger
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiClassType
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiType
import com.intellij.psi.util.InheritanceUtil
import weka.classifiers.Classifier
import weka.core.Attribute
import weka.core.DenseInstance
import weka.core.Instances
import weka.core.SerializationHelper
import java.io.InputStream

private data class LogCallFeatures(
    val level: String,
    val argumentCount: Int,
    val messageLength: Int,
    val placeholderCount: Int,
    val hasThrowable: Boolean,
)

object LogIntentModel {
    private val logger: Logger = Logger.getInstance(LogIntentModel::class.java)

    private val levelValues = arrayListOf("debug", "info", "warn", "error", "trace", "other")
    private val throwableValues = arrayListOf("False", "True")
    private val classValues = arrayListOf("No", "Yes")

    private val datasetTemplate: Instances = Instances(
        "log_intent",
        arrayListOf(
            Attribute("level", levelValues),
            Attribute("argCount"),
            Attribute("messageLength"),
            Attribute("placeholderCount"),
            Attribute("hasThrowable", throwableValues),
            Attribute("Folded", classValues),
        ),
        1,
    ).apply {
        setClassIndex(numAttributes() - 1)
    }

    private val model: Classifier? by lazy { loadModel() }

    fun shouldFoldLogCall(element: PsiMethodCallExpression, methodName: String): Boolean {
        val classifier = model ?: return true
        val features = extractFeatures(element, methodName) ?: return true

        val dataset = Instances(datasetTemplate, 0)
        val instance = DenseInstance(datasetTemplate.numAttributes()).apply {
            setValue(datasetTemplate.attribute("level"), features.level)
            setValue(datasetTemplate.attribute("argCount"), features.argumentCount.toDouble())
            setValue(datasetTemplate.attribute("messageLength"), features.messageLength.toDouble())
            setValue(datasetTemplate.attribute("placeholderCount"), features.placeholderCount.toDouble())
            val throwableValue = if (features.hasThrowable) "True" else "False"
            setValue(datasetTemplate.attribute("hasThrowable"), throwableValue)
            setDataset(dataset)
        }
        dataset.add(instance)

        return runCatching {
            val predictionIndex = classifier.classifyInstance(instance).toInt()
            dataset.classAttribute().value(predictionIndex) == "Yes"
        }.onFailure { throwable ->
            logger.warn("Log intent prediction failed; falling back to folding", throwable)
        }.getOrDefault(true)
    }

    private fun loadModel(): Classifier? {
        val stream = openModelStream() ?: return null
        return stream.use {
            runCatching {
                SerializationHelper.read(it) as Classifier
            }.onFailure { throwable ->
                logger.warn("Unable to load log intent model", throwable)
            }.getOrNull()
        }
    }

    private fun openModelStream(): InputStream? {
        val classLoader = LogIntentModel::class.java.classLoader
        val resourcePath = "data/log-intent.model"
        return classLoader.getResourceAsStream(resourcePath).also { stream ->
            if (stream == null) {
                logger.warn("Missing log intent model resource at $resourcePath")
            }
        }
    }

    private fun extractFeatures(
        element: PsiMethodCallExpression,
        methodName: String,
    ): LogCallFeatures? {
        val arguments = element.argumentList.expressions
        if (arguments.isEmpty()) return null

        val literal = arguments.first() as? PsiLiteralExpression ?: return null
        val message = literal.value as? String ?: return null

        val level = methodName.lowercase().takeIf(levelValues::contains) ?: "other"
        val argumentCount = (arguments.size - 1).coerceAtLeast(0)
        val messageLength = message.length
        val placeholderCount = BRACE_PLACEHOLDER.countIn(message) + PRINTF_PLACEHOLDER.countIn(message)
        val hasThrowable = arguments.drop(1).lastOrNull()?.type.isThrowable()

        return LogCallFeatures(level, argumentCount, messageLength, placeholderCount, hasThrowable)
    }

    private fun Regex.countIn(text: String): Int = findAll(text).count()

    private fun PsiType?.isThrowable(): Boolean {
        val psiClass = resolvePsiClass() ?: return false
        return InheritanceUtil.isInheritor(psiClass, "java.lang.Throwable")
    }

    private fun PsiType?.resolvePsiClass(): PsiClass? = (this as? PsiClassType)?.resolve()
}

private val BRACE_PLACEHOLDER = Regex("\\{\\}")
private val PRINTF_PLACEHOLDER = Regex("%(?!n)\\w")
