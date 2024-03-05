package com.intellij.advancedExpressionFolding.diff

import org.jsoup.nodes.Element
import org.jsoup.parser.Parser


object FoldTagsParser {

    @OptIn(ExperimentalStdlibApi::class)
    fun asVisible(text: String): String {
        val parser = Parser.htmlParser()
        parser.setTrackPosition(true)
        val doc = parser.parseInput(text, "")
        val folds = doc.select("fold")

        fun Element.value(): String = this.attribute("text").value

        // intellij built-in folding
        fun Element.ignored(): Boolean {
            val value = this.attribute("text").value
            return value == "{...}" || value == " { " || value == " }" || value == "..."
        }

        // when two tags are folding the same text, use first (might not always be correct)
        val ignored = folds.zipWithNext { first, second ->
            if (first.sourceRange().endPos() == second.sourceRange().startPos()) {
                first
            } else {
                null
            }
        }.mapNotNull {
            it
        }

        val chain = folds.flatMap { fold ->
            if (fold.ignored()) {
                val rangeStart = (fold.sourceRange().start().pos()..<fold.sourceRange().end().pos())
                val rangeEnd = (fold.endSourceRange().start().pos()..<fold.endSourceRange().end().pos())
                listOf(FoldingDescriptorEx(rangeStart, ""), FoldingDescriptorEx(rangeEnd, ""))
            } else {
                val range = (fold.sourceRange().start().pos()..<fold.endSourceRange().end().pos())
                if (ignored.contains(fold)) {
                    listOf(FoldingDescriptorEx(range, ""))
                } else {
                    listOf(FoldingDescriptorEx(range, fold.value()))
                }
            }
        }.sortedByDescending {
            it.range.first
        }

        var changedText = text
        chain.forEach {
            if (changedText.length > it.range.first) {
                try {
                    changedText = changedText.replaceRange(it.range, it.content)
                } catch (e: IndexOutOfBoundsException) {
                    // ignored
                }
            }
        }
        return changedText
    }

    data class FoldingDescriptorEx(val range: IntRange, val content: String)

}
