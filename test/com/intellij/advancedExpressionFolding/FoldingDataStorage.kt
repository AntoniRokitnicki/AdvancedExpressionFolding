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
import com.intellij.advancedExpressionFolding.isAdvancedExpressionFoldingGroup
import java.io.File

class FoldingDataStorage : Storage {
    private lateinit var descriptors: Array<FoldingDescriptor>
    private lateinit var document: Document

    private val showTextRangeIntersections = false

    class FoldingGroupNotFound(msg: String) : RuntimeException(msg)
    class InvalidFoldingGroup(msg: String) : RuntimeException(msg)

    override fun store(
        foldingDescriptors: Array<FoldingDescriptor>,
        document: Document
    ): Array<FoldingDescriptor> {
        validateCorrectFoldingGroup(foldingDescriptors)
        this.descriptors = foldingDescriptors
        this.document = document
        return foldingDescriptors
    }

    private fun validateCorrectFoldingGroup(descriptors: Array<FoldingDescriptor>) = descriptors.forEach {
        if (it.group == null) {
            throw FoldingGroupNotFound("${it.placeholderText} - ${it.element}")
        } else if (!it.isAdvancedExpressionFoldingGroup) {
            throw InvalidFoldingGroup("${it.placeholderText} - ${it.element} - ${it.group}")
        }
    }

    fun createOrderedFoldingWrapper(): FoldingDescriptorExWrapper {
        val uniqueElements = descriptors.distinctBy {
            it.element
        }.distinctBy {
            it.range
        }

        if (showTextRangeIntersections) {
            printoutTextRangeIntersects(uniqueElements)
        }

        printWarningIfThereIsFoldingDuplicate(uniqueElements)

        val foldingGroupSet = mutableSetOf<FoldingGroup>()
        val text = document.text

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
        return FoldingDescriptorExWrapper(list.size, list)
    }

    fun saveToJsonFile(
        file: File,
        foldingDescriptorExWrapper: FoldingDescriptorExWrapper
    ) {
        val mapper = ObjectMapper().registerKotlinModule().enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(file, foldingDescriptorExWrapper)
    }

    private fun printoutTextRangeIntersects(uniqueElements: List<FoldingDescriptor>) {
        val elementsWithSharedRange = createIntersectMap(uniqueElements)
        val elementsWithSingleValue = findSingleDuplicates(elementsWithSharedRange)
        removeSingleDuplicatesFromMultiDuplicateMap(elementsWithSingleValue, elementsWithSharedRange)

        printoutSingleDuplicates(elementsWithSingleValue)
        printoutMultiDuplicates(elementsWithSharedRange)
    }

    private fun printoutMultiDuplicates(elementsWithSharedRange: MutableMap<FoldingDescriptor, List<FoldingDescriptor>>) {
        elementsWithSharedRange.takeIf { it.isNotEmpty() }?.let {
            println("multi duplicates:")
            it.forEach { (key, value) ->
                println("[${key.element::class.simpleName}] ${key.element.text} (${key.group.asSimpleString()})")
                value.forEachIndexed { i, intersectingElement ->
                    val elementText = intersectingElement.element.text
                    val elementClassName = intersectingElement.element::class.simpleName
                    val suffix = if (key == intersectingElement) "<same>" else ""
                    println("\t$i)[${elementClassName}$suffix] $elementText (${intersectingElement.group.asSimpleString()})")
                }
                println("---")
            }
        }
    }

    private fun printoutSingleDuplicates(elementsWithSingleValue: List<Pair<FoldingDescriptor, FoldingDescriptor>>) {
        elementsWithSingleValue.takeIf { it.isNotEmpty() }?.let {
            println("single duplicates:")
            it.forEach { (key, intersectingElement) ->
                println("[${key.element::class.simpleName}] ${key.element.text} (${key.group.asSimpleString()})")
                val elementText = intersectingElement.element.text
                val elementClassName = intersectingElement.element::class.simpleName
                val suffix = if (key == intersectingElement) "<same>" else ""
                println("\t[${elementClassName}$suffix] $elementText (${intersectingElement.group.asSimpleString()})")
                println("---")
            }
        }
    }

    private fun removeSingleDuplicatesFromMultiDuplicateMap(
        elementsWithSingleValue: List<Pair<FoldingDescriptor, FoldingDescriptor>>,
        elementsWithSharedRange: MutableMap<FoldingDescriptor, List<FoldingDescriptor>>
    ) {
        elementsWithSingleValue.flatMap { (key, value) ->
            listOf(key, value)
        }.distinct().forEach {
            elementsWithSharedRange.remove(it)
        }
    }

    private fun findSingleDuplicates(elementsWithSharedRange: MutableMap<FoldingDescriptor, List<FoldingDescriptor>>): List<Pair<FoldingDescriptor, FoldingDescriptor>> {
        val processedPairs = mutableSetOf<Pair<FoldingDescriptor, FoldingDescriptor>>()
        val elementsWithSingleValue = elementsWithSharedRange.filter {
            it.value.size == 1
        }.mapNotNull { (key, value) ->
            val pair = if (key to value[0] !in processedPairs && value[0] to key !in processedPairs) {
                processedPairs.add(key to value[0])
                key to value[0]
            } else null
            pair
        }
        return elementsWithSingleValue
    }

    private fun createIntersectMap(uniqueElements: List<FoldingDescriptor>): MutableMap<FoldingDescriptor, List<FoldingDescriptor>> {
        val elementsWithSharedRange = mutableMapOf<FoldingDescriptor, List<FoldingDescriptor>>()

        uniqueElements.forEachIndexed { index1, element1 ->
            uniqueElements.drop(index1 + 1).forEach { element2 ->
                val intersects = element1.range.intersects(element2.range)
                val isAdjacent =
                    element1.range.endOffset == element2.range.startOffset || element2.range.endOffset == element1.range.startOffset
                if (intersects && !isAdjacent) {
                    elementsWithSharedRange[element1] = listOf(element2)
                    elementsWithSharedRange[element2] = listOf(element1)
                }
            }
        }
        return elementsWithSharedRange
    }

    private fun printWarningIfThereIsFoldingDuplicate(uniqueElements: List<FoldingDescriptor>) {
        if (uniqueElements.size != descriptors.size) {
            val duplicatedFoldings = descriptors.toSet() - uniqueElements.toSet()

            println(
                "WARNING! ${duplicatedFoldings.size} elements have many foldings for ${
                    duplicatedFoldings.mapNotNull {
                        it.group.asSimpleString()
                    }.joinToString(separator = ",")
                }"
            )
        }
    }

    private fun FoldingGroup?.asSimpleString(): String? {
        return this?.toString()?.takeIf {
            it.contains('.')
        }?.substringAfterLast('.') ?: this?.toString()
    }

}
