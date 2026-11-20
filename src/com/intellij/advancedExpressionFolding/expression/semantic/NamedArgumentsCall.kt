package com.intellij.advancedExpressionFolding.expression.semantic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.application.options.CodeStyle
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

/**
 * Shows a call chain as one call with named arguments, e.g. `person.copy(name = "Alice", age = 31)`.
 *
 * Only the text around each value folds. A single argument joins one line; several keep one argument per line,
 * re-indented the Kotlin way. The values are not folded here: the expression is nested, so their own foldings
 * come from the normal traversal.
 */
class NamedArgumentsCall(
    element: PsiElement,
    private val openRange: TextRange,
    private val openText: String,
    private val arguments: List<NamedArgument>,
    private val closeRange: TextRange,
) : Expression(element, element.textRange) {

    /**
     * @param prefixRange the text before the value, shown as `name = `
     * @param suffixRange the text after the value, shown as `,` (or nothing after the last argument)
     */
    class NamedArgument(val name: String, val prefixRange: TextRange, val suffixRange: TextRange)

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun isNested(): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(NamedArgumentsCall::class.java.name)
        fun fold(range: TextRange, text: String) = FoldingDescriptor(element.node, range, group, text)

        val single = arguments.singleOrNull()
        if (single != null &&
            document.isBlank(openRange.endOffset, single.prefixRange.startOffset) &&
            document.isBlank(single.suffixRange.endOffset, closeRange.startOffset)
        ) {
            return arrayOf(
                fold(openRange.union(single.prefixRange), "$openText${single.name} = "),
                fold(single.suffixRange.union(closeRange), ")"),
            )
        }

        // Kotlin layout: arguments one indent level deeper than the line the call starts on, `)` back on that level
        val baseIndent = document.indentOf(openRange.startOffset)
        val argumentIndent = baseIndent + indentUnit(element)
        val descriptors = mutableListOf(fold(openRange, openText))
        var previousEnd = openRange.endOffset
        arguments.forEachIndexed { index, argument ->
            val name = "${argument.name} = "
            descriptors += if (document.sameLine(previousEnd, argument.prefixRange.startOffset)) {
                fold(argument.prefixRange, if (index > 0) " $name" else name)
            } else {
                document.withIndent(argument.prefixRange)
                    ?.let { fold(it, argumentIndent + name) }
                    ?: fold(argument.prefixRange, name)
            }
            if (index < arguments.lastIndex) {
                descriptors += fold(argument.suffixRange, ",")
            }
            previousEnd = argument.suffixRange.endOffset
        }
        val lastSuffix = arguments.last().suffixRange
        if (lastSuffix.endOffset == closeRange.startOffset) {
            descriptors += fold(lastSuffix.union(closeRange), ")")
        } else {
            descriptors += fold(lastSuffix, "")
            val closeOnOwnLine = !document.sameLine(lastSuffix.endOffset, closeRange.startOffset)
            descriptors += document.withIndent(closeRange)?.takeIf { closeOnOwnLine }
                ?.let { fold(it, "$baseIndent)") }
                ?: fold(closeRange, ")")
        }
        return descriptors.toTypedArray()
    }

    private fun Document.sameLine(before: Int, after: Int) = getLineNumber(before) == getLineNumber(after)

    private fun Document.isBlank(start: Int, end: Int) = charsSequence.subSequence(start, end).isBlank()

    private fun Document.indentOf(offset: Int): String {
        val lineStart = getLineStartOffset(getLineNumber(offset))
        return charsSequence.subSequence(lineStart, offset).takeWhile { it == ' ' || it == '\t' }.toString()
    }

    /** `range` extended back over its line's indentation, or `null` when other text precedes it on the line. */
    private fun Document.withIndent(range: TextRange): TextRange? {
        val lineStart = getLineStartOffset(getLineNumber(range.startOffset))
        return TextRange(lineStart, range.endOffset).takeIf { isBlank(lineStart, range.startOffset) }
    }

    private fun indentUnit(element: PsiElement): String {
        val options = CodeStyle.getIndentOptions(element.containingFile)
        return if (options.USE_TAB_CHARACTER) "\t" else " ".repeat(options.INDENT_SIZE)
    }
}
