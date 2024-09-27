package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.extension.Consts.Emoji
import com.intellij.advancedExpressionFolding.extension.methodcall.MethodCallFactory

class AdvancedExpressionFoldingOptionsProvider : AbstractExpressionFoldingOptionsProvider() {

    init {
        val state = AdvancedExpressionFoldingSettings.getInstance().state

        title = "Advanced Expression Folding 2"
        checkBox(
            "Getters and setters as properties",
            state::getSetExpressionsCollapse,
            mapOf("GetterSetterTestData.java" to null)
        )
        checkBox(
            "Variable declarations (var/val)",
            state::varExpressionsCollapse,
            mapOf("VarTestData.java" to null)
        )
        checkBox(
            "Compact control flow condition syntax (Golang ifs)",
            state::compactControlFlowSyntaxCollapse,
            mapOf("CompactControlFlowTestData.java" to null)
        )
        checkBox(
            "List.get, List.set, Map.get and Map.put expressions, array and list literals",
            state::getExpressionsCollapse,
            mapOf("GetSetPutTestData.java" to null)
        )

        checkBox(
            "StringBuilder.append and Collection.add/remove expressions, interpolated Strings and Stream expressions",
            state::concatenationExpressionsCollapse,
            mapOf(
                "StringBuilderTestData.java" to "StringBuilder",
                "InterpolatedStringTestData.java" to "Interpolate",
                "AppendSetterInterpolatedStringTestData.java" to "Append",
                "ConcatenationTestData.java" to "Concatenation"
            )
        )
        checkBox(
            "List.subList and String.substring expressions",
            state::slicingExpressionsCollapse,
            mapOf("SliceTestData.java" to null)
        )
        checkBox(
            "Object.equals and Comparable.compareTo expressions",
            state::comparingExpressionsCollapse,
            mapOf("EqualsCompareTestData.java" to null)
        )

        checkBox(
            "Java.time isBefore/isAfter expressions",
            state::comparingLocalDatesCollapse,
            mapOf("LocalDateTestData.java" to null)
        )
        checkBox(
            "LocalDate.of literals (e.g. 2018-02-12)",
            state::localDateLiteralCollapse,
            mapOf("LocalDateLiteralTestData.java" to null)
        )
        checkBox(
            "Postfix LocalDate literals (e.g. 2018Y-02M-12D)",
            state::localDateLiteralPostfixCollapse,
            mapOf("LocalDateLiteralPostfixTestData.java" to null)
        )

        checkBox(
            "Type cast expressions",
            state::castExpressionsCollapse,
            mapOf("TypeCastTestData.java" to null)
        )
        checkBox(
            "For loops, range expressions",
            state::rangeExpressionsCollapse,
            mapOf("ForRangeTestData.java" to null)
        )
        checkBox(
            "Null-safe calls",
            state::checkExpressionsCollapse,
            mapOf("ElvisTestData.java" to null)
        )
        checkBox(
            "Extended null-safe ifs",
            state::ifNullSafe,
            mapOf("IfNullSafeData.java" to null),
            "/Extended-null‐safe-ifs"
        )
        checkBox(
            "Kotlin quick return",
            state::kotlinQuickReturn,
            mapOf("LetReturnIt.java" to null)
        )

        checkBox(
            "Asserts",
            state::assertsCollapse,
            mapOf("AssertTestData.java" to null)
        )

        checkBox(
            "Display optional as Kotlin null-safe",
            state::optional,
            mapOf("OptionalTestData.java" to null)
        )
        checkBox(
            "Display stream operations as Groovy's spread operator",
            state::streamSpread,
            mapOf("SpreadTestData.java" to null)
        )

        checkBox(
            "Display Java bean as Lombok",
            state::lombok,
            mapOf("LombokTestData.java" to null)
        )
        checkBox(
            "Log folding",
            state::logFolding,
            mapOf("LogBrackets.java" to null),
            "/Log-brackets-folding"
        )
        checkBox(
            "Display mapping of field with same name as << (for builders, setters and assignments)",
            state::fieldShift,
            mapOf(
                "FieldShiftBuilder.java" to "builders",
                "FieldShiftSetters.java" to "setters",
                "FieldShiftFields.java" to "fields",
            ),
            "/FieldShift"
        )
        checkBox(
            "Destructuring assignment for array & list",
            state::destructuring,
            mapOf(
                "DestructuringAssignmentArrayTestData.java" to "array",
                "DestructuringAssignmentListTestData.java" to "list"
            ),
            "/Destructuring-assignment"
        )
        checkBox(
            "Simplify System.out.println to println",
            state::println,
            mapOf("PrintlnTestData.java" to null),
            "/Simplify-System.out.println-to-println"
        )

        checkBox(
            "Control flow single-statement code block braces (read-only files)",
            state::controlFlowSingleStatementCodeBlockCollapse,
            mapOf("ControlFlowSingleStatementTestData.java" to null)
        )
        checkBox(
            "Control flow multi-statement code block braces (read-only files, deprecated)",
            state::controlFlowMultiStatementCodeBlockCollapse,
            mapOf("ControlFlowMultiStatementTestData.java" to null)
        )
        checkBox(
            "Semicolons (read-only files)",
            state::semicolonsCollapse,
            mapOf("SemicolonTestData.java" to null)
        )
        checkBox(
            "Folding of testData in diff",
            state::testDataFoldingDiff,
            docLink = "/Folding-of-testData-in-diff"
        )
        checkBox(
            "Simplify * static final to const",
            state::const,
            mapOf("ConstTestData.java" to null),
        )
        checkBox(
            "Simplify @NotNull to Type!! and @Nullable to Type?",
            state::nullable,
            mapOf(
                "NullableAnnotationTestData.java" to "annotations",
                "NullableAnnotationCheckNotNullTestData.java" to "checkNotNull"
            )
        )
        checkBox("Remove the 'final' modifier from all elements except fields",
            state::finalRemoval,
            mapOf("FinalRemovalTestData.java" to null),
        )
        checkBox("Replace the 'final' modifier with "+ Emoji.FINAL_LOCK,
            state::finalEmoji,
            mapOf("FinalEmojiTestData.java" to null),
        )
        checkBox("Don't fold lombok dirty getters/setters",
            state::lombokDirtyOff,
            mapOf("LombokDirtyOffTestData.java" to null),
        )
        
        checkBox("Single-Expression Function",
            state::expressionFunc,
            mapOf("ExpressionFuncTestData.java" to null),
        )
        
        checkBox("Dynamic names for methods based on \$user.home/dynamic-ajf2.toml",
            state::dynamic,
            mapOf("DynamicTestData.java" to null),
        )
        
        if (false) {
            checkBox("BigDecimal, BigInteger and Math",
                state::arithmeticExpressions,
                mapOf("ArithmeticExpressionsTestData.java" to null),
            )
        }
        
        checkBox("Emojify code",
            state::emojify,
            mapOf("EmojifyTestData.java" to null),
        )
        
        checkBox("Converts",
        state::interfaceExtensionProperties,
        mapOf("InterfaceExtensionPropertiesTestData.java" to null),
    )
    
        checkBox("Pattern Matching for instanceof (JEP 394)",
        state::patternMatchingInstanceof,
        mapOf("PatternMatchingInstanceofTestData.java" to null),
    )
    
        // NEW OPTION

        checkBox(
            "Memory improvements(experimental)",
            state::memoryImprovement
        )
        checkBox(
            "Experimental features",
            state::experimental
        )
    }

    override fun apply() {
        super.apply()
        MethodCallFactory.refreshMethodCallMappings()
    }

}