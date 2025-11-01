package com.intellij.advancedExpressionFolding.discovery

internal enum class CandidateFamily(val id: String) {
    CHAIN_CALL("chainCall"),
    GUARD_IF("guardIf"),
    LOGGER_LINE("loggerLine"),
    BUILDER_BLOCK("builderBlock"),
    LONG_PARAMETER_LIST("longParameterList")
}
