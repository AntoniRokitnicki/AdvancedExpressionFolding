package com.intellij.advancedExpressionFolding.weka;

import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.SerializationHelper;

import java.io.File;

public final class IntentModelTrainer {
    private IntentModelTrainer() {
        // Utility class
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java " + IntentModelTrainer.class.getName() + " <input-arff> <output-model>");
            System.exit(1);
        }

        File inputFile = new File(args[0]);
        if (!inputFile.isFile()) {
            throw new IllegalArgumentException("Input ARFF file not found: " + inputFile.getAbsolutePath());
        }

        File outputFile = new File(args[1]);
        trainAndSerializeModel(inputFile, outputFile);
    }

    private static void trainAndSerializeModel(File inputFile, File outputFile) throws Exception {
        DataSource dataSource = new DataSource(inputFile.getAbsolutePath());
        Instances data = dataSource.getDataSet();
        if (data.numAttributes() == 0) {
            throw new IllegalArgumentException("Dataset must contain at least one attribute.");
        }
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }

        Classifier classifier = new RandomForest();
        classifier.buildClassifier(data);

        SerializationHelper.write(outputFile.getAbsolutePath(), classifier);
        System.out.printf("Model trained and saved to %s%n", outputFile.getAbsolutePath());
    }
}
