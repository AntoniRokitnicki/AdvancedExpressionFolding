package com.intellij.advancedExpressionFolding

import com.kotlinnlp.simplednn.core.functionalities.activations.Sigmoid
import com.kotlinnlp.simplednn.core.functionalities.activations.Tanh
import com.kotlinnlp.simplednn.core.functionalities.updatemethods.learningrate.LearningRateMethod
import com.kotlinnlp.simplednn.core.layers.LayerType
import com.kotlinnlp.simplednn.core.layers.StackedLayersParameters
import com.kotlinnlp.simplednn.core.functionalities.losses.LossCalculator
import com.kotlinnlp.simplednn.core.neuralnetwork.preset.SimpleRecurrentNeuralNetwork
import com.kotlinnlp.simplednn.core.neuralprocessor.recurrent.RecurrentNeuralProcessor
import com.kotlinnlp.simplednn.core.optimizer.ParamsOptimizer
import com.kotlinnlp.simplednn.simplemath.ndarray.dense.DenseNDArray
import com.kotlinnlp.simplednn.simplemath.ndarray.dense.DenseNDArrayFactory
import java.io.Serializable
import kotlin.math.ln

class FoldingRNNModel(private val featureSize: Int, private val hiddenUnits: Int = 64) {

    private var parameters: StackedLayersParameters = createParameters()
    private var processor: RecurrentNeuralProcessor<DenseNDArray> = createProcessor(parameters)
    @Volatile
    private var trained: Boolean = false

    fun isReady(): Boolean = trained

    fun load(snapshot: Snapshot) {
        if (snapshot.featureSize != featureSize || snapshot.hiddenUnits != hiddenUnits) {
            return
        }
        parameters = snapshot.parameters
        processor = createProcessor(parameters)
        trained = snapshot.trained
    }

    fun snapshot(): Snapshot = Snapshot(parameters, featureSize, hiddenUnits, trained)

    fun train(examples: List<FoldingTrainingExample>, epochs: Int = 3, batchSize: Int = 8) {
        if (examples.isEmpty()) {
            return
        }
        processor = createProcessor(parameters)
        val optimizer = ParamsOptimizer(LearningRateMethod(learningRate = 0.01))
        repeat(epochs) {
            optimizer.newEpoch()
            examples.shuffled().chunked(batchSize).forEach { batch ->
                optimizer.newBatch()
                batch.forEach { example ->
                    optimizer.newExample()
                    val inputSequence = example.sequence.map { DenseNDArrayFactory.arrayOf(it) }
                    val output = processor.forward(
                        input = inputSequence,
                        initHiddenArrays = null,
                        saveContributions = false
                    )
                    val gold = DenseNDArrayFactory.arrayOf(doubleArrayOf(example.label))
                    val errors = BinaryCrossEntropyCalculator.calculateErrors(output, gold)
                    processor.backward(errors)
                    val copy = batch.size > 1
                    optimizer.accumulate(processor.getParamsErrors(copy = copy), copy = copy)
                }
                optimizer.update()
            }
        }
        trained = true
    }

    fun predict(sequence: List<DoubleArray>): Double {
        if (!trained || sequence.isEmpty()) {
            return 0.5
        }
        val inputSequence = sequence.map { DenseNDArrayFactory.arrayOf(it) }
        val output = processor.forward(
            input = inputSequence,
            initHiddenArrays = null,
            saveContributions = false
        )
        return clampProbability(output[0])
    }

    private fun createParameters(): StackedLayersParameters = SimpleRecurrentNeuralNetwork(
        inputSize = featureSize,
        inputType = LayerType.Input.Dense,
        hiddenSize = hiddenUnits,
        hiddenActivation = Tanh,
        numOfHidden = 1,
        outputSize = 1,
        outputActivation = Sigmoid
    )

    private fun createProcessor(params: StackedLayersParameters): RecurrentNeuralProcessor<DenseNDArray> =
        RecurrentNeuralProcessor<DenseNDArray>(model = params, propagateToInput = false)

    data class Snapshot(
        val parameters: StackedLayersParameters,
        val featureSize: Int,
        val hiddenUnits: Int,
        val trained: Boolean
    ) : Serializable
}

data class FoldingTrainingExample(
    val sequence: List<DoubleArray>,
    val label: Double
)

private object BinaryCrossEntropyCalculator : LossCalculator {

    override fun calculateLoss(output: DenseNDArray, outputGold: DenseNDArray): DenseNDArray {
        val prediction = clampProbability(output[0])
        val target = outputGold[0].coerceIn(0.0, 1.0)
        val loss = -(target * ln(prediction) + (1.0 - target) * ln(1.0 - prediction))
        return DenseNDArrayFactory.arrayOf(doubleArrayOf(loss))
    }

    override fun calculateErrors(output: DenseNDArray, outputGold: DenseNDArray): DenseNDArray {
        val errors = output.copy()
        val prediction = clampProbability(output[0])
        val target = outputGold[0].coerceIn(0.0, 1.0)
        errors[0] = prediction - target
        return errors
    }
}

private const val PROBABILITY_EPS = 1e-6

private fun clampProbability(value: Double): Double = when {
    value < PROBABILITY_EPS -> PROBABILITY_EPS
    value > 1.0 - PROBABILITY_EPS -> 1.0 - PROBABILITY_EPS
    else -> value
}
