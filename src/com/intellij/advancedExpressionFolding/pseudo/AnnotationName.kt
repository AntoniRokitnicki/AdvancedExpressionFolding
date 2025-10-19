package com.intellij.advancedExpressionFolding.pseudo

@JvmInline
value class AnnotationName(val value: String) {
    init {
        require(value.isNotBlank()) { "Annotation name must not be blank." }
        val segments = value.split('.')
        require(segments.all(::isValidSegment)) { "Invalid annotation name: $value" }
    }

    override fun toString(): String = value

    companion object {
        private fun isValidSegment(segment: String): Boolean {
            if (segment.isEmpty()) return false
            val first = segment.first()
            if (!Character.isJavaIdentifierStart(first)) return false
            for (ch in segment.drop(1)) {
                if (!Character.isJavaIdentifierPart(ch)) return false
            }
            return true
        }
    }
}
