package com.intellij.advancedExpressionFolding.processor.lombok

import java.util.*
import java.util.EnumSet.of

enum class LombokFoldingAnnotation(val annotation: String) {
    SERIAL("@Serial"),
    LOMBOK_LOG("@Log"),

    LOMBOK_GETTER("@Getter"),
    LOMBOK_SETTER("@Setter"),
    LOMBOK_EQUALS("@Equals"),
    LOMBOK_HASHCODE("@HashCode"),
    LOMBOK_TO_STRING("@ToString"),
    LOMBOK_BUILDER("@Builder"),

    LOMBOK_NO_ARGS_CONSTRUCTOR("@NoArgsConstructor"),
    LOMBOK_ALL_ARGS_CONSTRUCTOR("@AllArgsConstructor"),
    LOMBOK_REQUIRED_ARGS_CONSTRUCTOR("@RequiredArgsConstructor"),

    LOMBOK_FIELD_CONSTRUCTOR("@Constructor"),
    LOMBOK_POST_CONSTRUCTOR("@PostConstructor"),

    LOMBOK_DATA("@Data") {
        override fun children() = of(LOMBOK_GETTER, LOMBOK_SETTER, LOMBOK_EQUALS, LOMBOK_HASHCODE)!!
    },
    LOMBOK_VALUE("@Value") {
        override fun children() = of(LOMBOK_GETTER, LOMBOK_REQUIRED_ARGS_CONSTRUCTOR, LOMBOK_EQUALS, LOMBOK_HASHCODE)!!
    },
    LOMBOK_VALUE_SIMPLE("@LightValue") {
        override fun children() = of(LOMBOK_GETTER, LOMBOK_REQUIRED_ARGS_CONSTRUCTOR)!!
    },
    LOMBOK_EQUALS_AND_HASHCODE("@EqualsAndHashCode") {
        override fun children() = of(LOMBOK_EQUALS, LOMBOK_HASHCODE)!!
    },


    ;

    open fun children(): EnumSet<LombokFoldingAnnotation>? = null
}
