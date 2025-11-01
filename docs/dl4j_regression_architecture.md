# DL4J Regression Architecture for Language/Framework Adaptation

Poniższy szkic konfiguracji MultiLayerNetwork w DL4J pokazuje minimalną architekturę dla zadania regresji (prawdopodobieństwo złożenia 0.0–1.0) z wektorami cech pochodzącymi z tokenów kodu.

```java
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

int embeddingSize = 128; // wymiar wektorów z Word2Vec/TextVectorizer

MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
        .seed(42)
        .weightInit(WeightInit.XAVIER)
        .updater(new Adam(1e-3))
        .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
        .list()
        .layer(new DenseLayer.Builder()
                .nIn(embeddingSize)
                .nOut(64)
                .activation(Activation.RELU)
                .build())
        .layer(new DenseLayer.Builder()
                .nIn(64)
                .nOut(32)
                .activation(Activation.RELU)
                .build())
        .layer(new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                .activation(Activation.SIGMOID)
                .nIn(32)
                .nOut(1)
                .build())
        .build();
```

## Wektoryzacja cech tekstowych
- Użyj `Word2Vec` z Deeplearning4j do trenowania wektorów na tokenach kodu (nazwy metod, identyfikatory, słowa kluczowe).
- Alternatywnie zastosuj `TfidfVectorizer` lub `BagOfWordsVectorizer` z DataVec, aby otrzymać stałe wektory wejściowe.
- Konwertuj ztokenizowane pliki źródłowe na sekwencje wektorów, a następnie agreguj (np. średnia, sumowanie) do wymiaru `embeddingSize` podanego w konfiguracji.

Ta konfiguracja spełnia wymagania: trzy warstwy (wejście, ukryta, wyjście), warstwy `DenseLayer` z funkcją aktywacji ReLU w ukrytych warstwach oraz `OutputLayer` z aktywacją `SIGMOID` i stratą `MSE` dla regresji.
