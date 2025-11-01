package com.intellij.advancedExpressionFolding.learning

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.util.TextRange
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class FoldingLearningIntegrationTest : BasePlatformTestCase() {

    fun testPolicySkipsFoldWhenHighProbability() {
        val psiFile = myFixture.configureByText("Sample.java", "class Sample { void test() {} }")
        val descriptor = FoldingDescriptor(psiFile.node, TextRange(0, psiFile.textLength))
        descriptor.putUserData(FoldingLearningKeys.FOLD_TYPE, "imports")
        val predictor = FoldingRnnPredictor.get(project)
        val model = FoldingRnnPredictor.LearnedModel(
            bias = 2.0,
            weights = DoubleArray(8) { 0.0 },
            fileExtWeights = emptyMap(),
            foldTypeWeights = mapOf("imports" to 0.0),
            trainedAt = System.currentTimeMillis(),
        )
        predictor.updateModel(model, null)
        val filtered = predictor.prepareDescriptors(psiFile, arrayOf(descriptor))
        assertEquals(0, filtered.size)
    }

    fun testPolicyMarksDefaultExpanded() {
        val psiFile = myFixture.configureByText("Demo.java", "class Demo { void test() {} }")
        val descriptor = FoldingDescriptor(psiFile.node, TextRange(0, psiFile.textLength))
        descriptor.putUserData(FoldingLearningKeys.FOLD_TYPE, "imports")
        val predictor = FoldingRnnPredictor.get(project)
        val logit = kotlin.math.log(0.6 / (1 - 0.6))
        val model = FoldingRnnPredictor.LearnedModel(
            bias = logit,
            weights = DoubleArray(8) { 0.0 },
            fileExtWeights = emptyMap(),
            foldTypeWeights = mapOf("imports" to 0.0),
            trainedAt = System.currentTimeMillis(),
        )
        predictor.updateModel(model, null)
        val filtered = predictor.prepareDescriptors(psiFile, arrayOf(descriptor))
        assertEquals(1, filtered.size)
        assertTrue(filtered[0].getUserData(FoldingLearningKeys.DEFAULT_EXPANDED) == true)
    }
}
