package com.intellij.advancedExpressionFolding

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorEx
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper
import com.intellij.advancedExpressionFolding.diff.Range
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import java.io.File

class FoldingDataStorage : EmptyStorage() {
    private lateinit var descriptors: Array<FoldingDescriptor>
    private lateinit var document: Document

    override fun store(
        foldingDescriptors: Array<out FoldingDescriptor>?,
        document: Document
    ): Array<FoldingDescriptor> {
        this.descriptors = super.store(foldingDescriptors, document)
        this.document = document
        return descriptors
    }

    fun saveFolding(file: File): FoldingDescriptorExWrapper {
        val foldingGroupSet = mutableSetOf<FoldingGroup>()

        val text = document.text
        val mapper = ObjectMapper().registerKotlinModule().enable(SerializationFeature.INDENT_OUTPUT);
        val list = descriptors.mapIndexed { index, foldingDescriptor ->
            foldingDescriptor.group?.let {
                foldingGroupSet += it
            }

            FoldingDescriptorEx(
                index,
                foldingDescriptor.range.substring(text),
                foldingDescriptor.placeholderText,
                Range(
                    foldingDescriptor.range.startOffset,
                    foldingDescriptor.range.endOffset,
                    foldingDescriptor.range.length
                ),
                foldingDescriptor.group.asSimpleString(),
                foldingGroupSet.size - 1
            )
        }
        val foldingDescriptorExWrapper = FoldingDescriptorExWrapper(list.size, list)
        mapper.writeValue(file, foldingDescriptorExWrapper)
        return foldingDescriptorExWrapper
    }

    private fun FoldingGroup?.asSimpleString(): String? {
        return this?.toString()?.takeIf {
            it.contains('.')
        }?.substringAfterLast('.') ?: this?.toString()
    }

}


