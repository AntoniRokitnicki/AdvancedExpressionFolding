package data

class PseudoAnnotationsMainTestData(val required: Int) {
    constructor() : this(0)

    fun instanceMethod(name: String) {}

    fun returnsValue(): String = ""

    fun booleanParams(flag: Boolean, other: BooleanArray) {}

    fun numberParams(byteValue: Byte, shortValue: Short, intValue: Int, longValue: Long, floatValue: Float, doubleValue: Double) {}

    fun nullableParams(text: String?, numbers: List<Int?>) {}

    fun varargParams(vararg tokens: String) {}

    companion object {
        fun staticMethod(value: IntArray) {}
    }
}

object PseudoAnnotationsObject {
    fun objectMethod(data: Map<String, Int>) {}
}

fun topLevelFunction(message: String) {}
