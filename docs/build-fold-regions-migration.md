# Expression Folding Migration

The `Expression.buildFoldRegions` API now exposes `buildFoldRegionsList` helpers that
receive a mutable list instead of returning an array. The following classes currently
override the deprecated array-based API and should migrate to the list-based variant:

- com.intellij.advancedExpressionFolding.expression.Function
- com.intellij.advancedExpressionFolding.expression.Operation
- com.intellij.advancedExpressionFolding.expression.SyntheticExpressionImpl
- com.intellij.advancedExpressionFolding.expression.VariableDeclarationImpl
- com.intellij.advancedExpressionFolding.expression.controlflow.AbstractControlFlowCodeBlock
- com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
- com.intellij.advancedExpressionFolding.expression.controlflow.ElvisExpression
- com.intellij.advancedExpressionFolding.expression.controlflow.ForEachIndexedStatement
- com.intellij.advancedExpressionFolding.expression.controlflow.ForEachStatement
- com.intellij.advancedExpressionFolding.expression.controlflow.ForStatement
- com.intellij.advancedExpressionFolding.expression.controlflow.IfExpression
- com.intellij.advancedExpressionFolding.expression.controlflow.SemicolonExpression
- com.intellij.advancedExpressionFolding.expression.controlflow.ShortElvisExpression
- com.intellij.advancedExpressionFolding.expression.literal.ArrayLiteral
- com.intellij.advancedExpressionFolding.expression.literal.InterpolatedString
- com.intellij.advancedExpressionFolding.expression.literal.ListLiteral
- com.intellij.advancedExpressionFolding.expression.literal.LocalDateLiteral
- com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
- com.intellij.advancedExpressionFolding.expression.literal.SetLiteral
- com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
- com.intellij.advancedExpressionFolding.expression.math.advanced.Cbrt
- com.intellij.advancedExpressionFolding.expression.math.advanced.Exp
- com.intellij.advancedExpressionFolding.expression.math.advanced.Pow
- com.intellij.advancedExpressionFolding.expression.math.basic.Abs
- com.intellij.advancedExpressionFolding.expression.operation.Get
- com.intellij.advancedExpressionFolding.expression.operation.basic.Append
- com.intellij.advancedExpressionFolding.expression.operation.basic.TypeCast
- com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
- com.intellij.advancedExpressionFolding.expression.operation.collection.ArrayGet
- com.intellij.advancedExpressionFolding.expression.operation.collection.ArrayStream
- com.intellij.advancedExpressionFolding.expression.operation.collection.Collect
- com.intellij.advancedExpressionFolding.expression.operation.collection.Put
- com.intellij.advancedExpressionFolding.expression.operation.collection.Range
- com.intellij.advancedExpressionFolding.expression.operation.collection.Slice
- com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalMapSafeCallParam
- com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalNotNullAssertionGet
- com.intellij.advancedExpressionFolding.expression.operation.stream.StreamExpression
- com.intellij.advancedExpressionFolding.expression.operation.stream.StreamMapCallParam
- com.intellij.advancedExpressionFolding.expression.operation.stream.StringExpression
- com.intellij.advancedExpressionFolding.expression.property.Getter
- com.intellij.advancedExpressionFolding.expression.property.GetterRecord
- com.intellij.advancedExpressionFolding.expression.property.Setter
- com.intellij.advancedExpressionFolding.expression.semantic.AbstractMultiExpression
- com.intellij.advancedExpressionFolding.expression.semantic.AbstractSingleChildExpression
- com.intellij.advancedExpressionFolding.expression.semantic.BuilderShiftExpression
- com.intellij.advancedExpressionFolding.expression.semantic.FastExpression
- com.intellij.advancedExpressionFolding.expression.semantic.kotlin.ElvisReturnNull
- com.intellij.advancedExpressionFolding.expression.semantic.kotlin.FieldConstExpression
- com.intellij.advancedExpressionFolding.expression.semantic.kotlin.IfNullSafeExpression
- com.intellij.advancedExpressionFolding.expression.semantic.kotlin.IfNullSafePrintlnExpression
- com.intellij.advancedExpressionFolding.expression.semantic.kotlin.LetReturnIt
- com.intellij.advancedExpressionFolding.expression.semantic.lombok.ClassAnnotationExpression
- com.intellij.advancedExpressionFolding.expression.semantic.lombok.NullAnnotationExpression

Classes and interfaces that delegate to these implementations (for example,
`com.intellij.advancedExpressionFolding.expression.property.IGetter`) should
also adopt the new API when their implementations migrate.
