package com.intellij.advancedExpressionFolding

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorEx
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper
import com.intellij.advancedExpressionFolding.diff.Range
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
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

    fun saveFolding(file: File) {
        val text = document.text
        val mapper = ObjectMapper().registerKotlinModule().enable(SerializationFeature.INDENT_OUTPUT);
        val list = descriptors.mapIndexed { index, it ->
            FoldingDescriptorEx(
                index,
                it.range.substring(text),
                it.placeholderText,
                Range(it.range.startOffset, it.range.endOffset, it.range.length),
                it.group.toString()
            )
        }
        mapper.writeValue(file, FoldingDescriptorExWrapper(list.size, list))
    }
}

