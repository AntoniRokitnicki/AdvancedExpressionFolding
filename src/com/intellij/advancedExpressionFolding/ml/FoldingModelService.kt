package com.intellij.advancedExpressionFolding.ml

import com.intellij.openapi.application.PathManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.logger
import org.deeplearning4j.nn.api.OptimizationAlgorithm
import org.deeplearning4j.nn.conf.MultiLayerConfiguration
import org.deeplearning4j.nn.conf.NeuralNetConfiguration
import org.deeplearning4j.nn.conf.layers.DenseLayer
import org.deeplearning4j.nn.conf.layers.OutputLayer
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork
import org.deeplearning4j.nn.weights.WeightInit
import org.deeplearning4j.util.ModelSerializer
import org.nd4j.linalg.activations.Activation
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.nd4j.linalg.learning.config.Adam
import org.nd4j.linalg.lossfunctions.LossFunctions
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

@Service(Service.Level.APP)
class FoldingModelService {
    private val log = logger<FoldingModelService>()

    private val embeddingSize = 128
    private val modelDirectory: Path = Path.of(PathManager.getConfigPath(), "advancedExpressionFolding")
    private val modelFile: Path = modelDirectory.resolve("folding-model.zip")
    private val configuration: MultiLayerConfiguration by lazy { buildConfiguration() }

    @Volatile
    private var modelLoadedFromDisk = false

    @Volatile
    private var network: MultiLayerNetwork? = loadPersistedNetwork()

    fun warmup() {
        if (!modelLoadedFromDisk) {
            network = loadPersistedNetwork()
        }
    }

    fun model(): MultiLayerNetwork {
        var localNetwork = network
        if (localNetwork == null) {
            localNetwork = newInitializedNetwork()
            network = localNetwork
        }
        return localNetwork
    }

    fun shouldCollapse(text: String, threshold: Double = 0.5): Boolean? =
        predictCollapseProbability(text)?.let { probability -> probability >= threshold }

    fun predictCollapseProbability(text: String): Double? {
        if (!modelLoadedFromDisk) {
            return null
        }
        val tokens = tokenize(text)
        if (tokens.isEmpty()) {
            return null
        }
        val features = encode(tokens)
        return try {
            model().output(features, false).getDouble(0)
        } catch (t: Throwable) {
            log.warn("Failed to execute DL4J folding model inference", t)
            null
        }
    }

    private fun loadPersistedNetwork(): MultiLayerNetwork? {
        ensureDirectory()
        if (!Files.exists(modelFile)) {
            modelLoadedFromDisk = false
            return null
        }
        return try {
            ModelSerializer.restoreMultiLayerNetwork(modelFile.toFile(), true).also {
                modelLoadedFromDisk = true
            }
        } catch (t: Throwable) {
            log.warn("Unable to load DL4J folding model from $modelFile", t)
            modelLoadedFromDisk = false
            null
        }
    }

    private fun newInitializedNetwork(): MultiLayerNetwork = MultiLayerNetwork(configuration).also(MultiLayerNetwork::init)

    private fun ensureDirectory() {
        try {
            Files.createDirectories(modelDirectory)
        } catch (t: Throwable) {
            log.warn("Unable to create DL4J model directory $modelDirectory", t)
        }
    }

    private fun tokenize(text: String): List<String> =
        TOKEN_REGEX.findAll(text.lowercase()).map { it.value }.filter { it.isNotEmpty() }.toList()

    private fun encode(tokens: List<String>): INDArray {
        val counts = DoubleArray(embeddingSize)
        for (token in tokens) {
            val index = abs(token.hashCode()) % embeddingSize
            counts[index] += 1.0
        }
        val max = counts.maxOrNull() ?: 0.0
        if (max > 0.0) {
            for (i in counts.indices) {
                counts[i] /= max
            }
        }
        return Nd4j.create(counts).reshape(1L, embeddingSize.toLong())
    }

    private fun buildConfiguration(): MultiLayerConfiguration {
        return NeuralNetConfiguration.Builder()
            .seed(42)
            .weightInit(WeightInit.XAVIER)
            .updater(Adam(1e-3))
            .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
            .list()
            .layer(
                DenseLayer.Builder()
                    .nIn(embeddingSize)
                    .nOut(64)
                    .activation(Activation.RELU)
                    .build()
            )
            .layer(
                DenseLayer.Builder()
                    .nIn(64)
                    .nOut(32)
                    .activation(Activation.RELU)
                    .build()
            )
            .layer(
                OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                    .activation(Activation.SIGMOID)
                    .nIn(32)
                    .nOut(1)
                    .build()
            )
            .build()
    }

    companion object {
        private val TOKEN_REGEX = Regex("[a-z0-9]+")
    }
}
