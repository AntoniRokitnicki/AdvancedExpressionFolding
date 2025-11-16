package com.intellij.advancedExpressionFolding.discovery

import org.apache.commons.math3.ml.distance.DistanceMeasure

internal class CosineDistance : DistanceMeasure {
    override fun compute(a: DoubleArray, b: DoubleArray): Double {
        return VectorMath.cosineDistance(a, b)
    }
}
