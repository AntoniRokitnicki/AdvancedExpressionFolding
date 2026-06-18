package com.intellij.advancedExpressionFolding.ml

import com.intellij.openapi.application.PathManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.logger
import com.intellij.psi.PsiElement
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
import java.nio.file.Paths
import kotlin.LazyThreadSafetyMode
import kotlin.io.path.createDirectories
import kotlin.math.absoluteValue

@Service(Service.Level.APP)
class FoldingModelService {
    private val log = logger<FoldingModelService>()
    private val modelFile: Path = Paths.get(PathManager.getConfigPath(), "advanced-expression-folding", MODEL_FILE_NAME)
    private val configuration: MultiLayerConfiguration by lazy(LazyThreadSafetyMode.PUBLICATION) { buildConfiguration() }

    @Volatile
    private var network: MultiLayerNetwork? = null
    @Volatile
    private var modelLoadedFromDisk: Boolean = false

    /**
     * Initializes the folding prediction model. Loads a persisted network if present,
     * otherwise builds a fresh instance using the DL4J regression configuration.
     */
    fun initialize() {
        ensureDirectoryExists()
        val restored = loadExistingModel()
        modelLoadedFromDisk = restored != null
        network = restored
    }

    fun predictCollapseProbability(element: PsiElement): Double? {
        val text = element.text.takeIf(String::isNotBlank) ?: return null
        val model = ensureNetworkInitialized() ?: return null
        val features = vectorize(text)
        return try {
            model.output(features, false).getDouble(0)
        } catch (throwable: Throwable) {
            log.warn("Failed to run folding probability prediction", throwable)
            null
        }
    }

    private fun ensureDirectoryExists() {
        modelFile.parent?.let { parent ->
            if (Files.notExists(parent)) {
                parent.createDirectories()
            }
        }
    }

    private fun ensureNetworkInitialized(): MultiLayerNetwork? {
        if (!modelLoadedFromDisk) {
            return null
        }
        var current = network
        if (current != null) {
            return current
        }
        synchronized(this) {
            current = network
            if (current == null && modelLoadedFromDisk) {
                current = loadExistingModel()?.also { network = it }
                if (current == null) {
                    modelLoadedFromDisk = false
                }
            }
        }
        return current
    }

    private fun loadExistingModel(): MultiLayerNetwork? {
        if (!Files.exists(modelFile)) {
            return null
        }
        return try {
            ModelSerializer.restoreMultiLayerNetwork(modelFile.toFile(), true)
        } catch (throwable: Throwable) {
            log.warn("Failed to load folding model from $modelFile", throwable)
            null
        }
    }

    /**
     * Minimal token hashing features until a Word2Vec/TextVectorizer pipeline is wired.
     */
    private fun vectorize(text: String): INDArray {
        val tokens = TOKEN_SPLIT_REGEX.split(text).filter(String::isNotBlank)
        val vector = Nd4j.zeros(1, EMBEDDING_SIZE)
        if (tokens.isEmpty()) {
            return vector
        }
        tokens.forEach { token ->
            val index = token.hashCode().absoluteValue % EMBEDDING_SIZE
            val currentValue = vector.getDouble(0, index)
            vector.putScalar(intArrayOf(0, index), currentValue + 1.0)
        }
        return vector.divi(tokens.size.toDouble())
    }

    private fun buildConfiguration(): MultiLayerConfiguration =
        NeuralNetConfiguration.Builder()
            .seed(42)
            .weightInit(WeightInit.XAVIER)
            .updater(Adam(1e-3))
            .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
            .list(
                DenseLayer.Builder()
                    .nIn(EMBEDDING_SIZE)
                    .nOut(64)
                    .activation(Activation.RELU)
                    .build(),
                DenseLayer.Builder()
                    .nIn(64)
                    .nOut(32)
                    .activation(Activation.RELU)
                    .build(),
                OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                    .activation(Activation.SIGMOID)
                    .nIn(32)
                    .nOut(1)
                    .build()
            )
            .build()

    companion object {
        private const val EMBEDDING_SIZE = 128
        private const val MODEL_FILE_NAME = "folding-model.zip"
        private val TOKEN_SPLIT_REGEX = Regex("[^A-Za-z0-9_]+")

        fun getInstance(): FoldingModelService = service()
    }
}
