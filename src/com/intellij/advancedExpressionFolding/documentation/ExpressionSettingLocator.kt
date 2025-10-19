package com.intellij.advancedExpressionFolding.documentation

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.VariableDeclarationImpl
import com.intellij.advancedExpressionFolding.expression.controlflow.AbstractControlFlowCodeBlock
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowMultiStatementCodeBlockExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowSingleStatementCodeBlockExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ElvisExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ForEachIndexedStatement
import com.intellij.advancedExpressionFolding.expression.controlflow.ForEachStatement
import com.intellij.advancedExpressionFolding.expression.controlflow.ForStatement
import com.intellij.advancedExpressionFolding.expression.controlflow.SemicolonExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ShortElvisExpression
import com.intellij.advancedExpressionFolding.expression.literal.LocalDateLiteral
import com.intellij.advancedExpressionFolding.expression.operation.FieldShiftMethod
import com.intellij.advancedExpressionFolding.expression.operation.Get
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append
import com.intellij.advancedExpressionFolding.expression.operation.basic.TypeCast
import com.intellij.advancedExpressionFolding.expression.operation.collection.ArrayGet
import com.intellij.advancedExpressionFolding.expression.operation.collection.ArrayStream
import com.intellij.advancedExpressionFolding.expression.operation.collection.Collect
import com.intellij.advancedExpressionFolding.expression.operation.collection.Put
import com.intellij.advancedExpressionFolding.expression.operation.collection.Range
import com.intellij.advancedExpressionFolding.expression.operation.collection.Slice
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalMapCall
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalMapSafeCall
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalMapSafeCallParam
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalNotNullAssertionGet
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalOf
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalOfNullable
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalOrElseElvis
import com.intellij.advancedExpressionFolding.expression.operation.stream.StreamExpression
import com.intellij.advancedExpressionFolding.expression.operation.stream.StreamFilterNotNull
import com.intellij.advancedExpressionFolding.expression.operation.stream.StreamMapCallParam
import com.intellij.advancedExpressionFolding.expression.operation.stream.StringExpression
import com.intellij.advancedExpressionFolding.expression.operation.stream.StringOperation
import com.intellij.advancedExpressionFolding.expression.property.Getter
import com.intellij.advancedExpressionFolding.expression.property.GetterRecord
import com.intellij.advancedExpressionFolding.expression.property.Setter
import com.intellij.advancedExpressionFolding.expression.semantic.BuilderShiftExpression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.IfNullSafeExpression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.LetReturnIt
import kotlin.reflect.KClass

object ExpressionSettingLocator {
    private val keysByClass: Map<KClass<out Expression>, Set<String>> = mapOf(
        Getter::class to setOf("getSetExpressionsCollapse"),
        Setter::class to setOf("getSetExpressionsCollapse"),
        GetterRecord::class to setOf("getSetExpressionsCollapse"),
        OptionalNotNullAssertionGet::class to setOf("getSetExpressionsCollapse", "optional"),
        OptionalMapSafeCall::class to setOf("optional"),
        OptionalMapSafeCallParam::class to setOf("optional"),
        OptionalMapCall::class to setOf("optional"),
        OptionalOf::class to setOf("optional"),
        OptionalOfNullable::class to setOf("optional"),
        OptionalOrElseElvis::class to setOf("optional"),
        VariableDeclarationImpl::class to setOf("varExpressionsCollapse"),
        Append::class to setOf("concatenationExpressionsCollapse"),
        StringExpression::class to setOf("concatenationExpressionsCollapse"),
        StringOperation::class to setOf("concatenationExpressionsCollapse"),
        StreamExpression::class to setOf("concatenationExpressionsCollapse"),
        StreamMapCallParam::class to setOf("concatenationExpressionsCollapse"),
        StreamFilterNotNull::class to setOf("concatenationExpressionsCollapse"),
        Collect::class to setOf("concatenationExpressionsCollapse"),
        ArrayStream::class to setOf("concatenationExpressionsCollapse"),
        Slice::class to setOf("slicingExpressionsCollapse"),
        Range::class to setOf("rangeExpressionsCollapse"),
        ForEachStatement::class to setOf("rangeExpressionsCollapse"),
        ForEachIndexedStatement::class to setOf("rangeExpressionsCollapse"),
        ForStatement::class to setOf("rangeExpressionsCollapse", "compactControlFlowSyntaxCollapse"),
        CompactControlFlowExpression::class to setOf("compactControlFlowSyntaxCollapse"),
        AbstractControlFlowCodeBlock::class to setOf("controlFlowSingleStatementCodeBlockCollapse"),
        ControlFlowSingleStatementCodeBlockExpression::class to setOf("controlFlowSingleStatementCodeBlockCollapse"),
        ControlFlowMultiStatementCodeBlockExpression::class to setOf("controlFlowMultiStatementCodeBlockCollapse"),
        ElvisExpression::class to setOf("checkExpressionsCollapse"),
        ShortElvisExpression::class to setOf("checkExpressionsCollapse"),
        IfNullSafeExpression::class to setOf("ifNullSafe"),
        LetReturnIt::class to setOf("kotlinQuickReturn"),
        TypeCast::class to setOf("castExpressionsCollapse"),
        SemicolonExpression::class to setOf("semicolonsCollapse"),
        Get::class to setOf("getExpressionsCollapse"),
        Put::class to setOf("getExpressionsCollapse"),
        ArrayGet::class to setOf("getExpressionsCollapse"),
        BuilderShiftExpression::class to setOf("fieldShift"),
        FieldShiftMethod::class to setOf("fieldShift"),
        LocalDateLiteral::class to setOf("localDateLiteralCollapse", "localDateLiteralPostfixCollapse")
    )

    fun settingKeysFor(expression: Expression): Set<String> {
        val result = linkedSetOf<String>()
        for ((clazz, keys) in keysByClass) {
            if (clazz.java.isAssignableFrom(expression.javaClass)) {
                result += keys
            }
        }
        return result
    }
}
