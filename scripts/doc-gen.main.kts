#!/usr/bin/env -S kotlinc -script --
import java.io.File

val exampleFolder = "../examples/data"
val foldedFolder = "../folded"
val wikiFolder = "../wiki/new-doc"
val excludedFeatures = listOf(
    "ConstructorReferenceNotationWithConst",
    "FieldShiftBuilder",
    "DestructuringAssignmentArrayWithoutVal",
    "DestructuringAssignmentListWithoutVal"
)

fun processTestFiles(processor: (file: File, baseName: String, mdFile: File) -> Unit) {
    File(exampleFolder).listFiles()?.forEach { file ->
        if (file.isFile) {
            val baseName = file.nameWithoutExtension.removeSuffix("TestData")
            val mdFileName = "$baseName.md"
            val mdFile = File(wikiFolder, mdFileName)
            processor(file, baseName, mdFile)
        }
    }
}

fun createFilesPerConfig() {
    processTestFiles { file, baseName, mdFile ->
        if (!mdFile.exists() && !excludedFeatures.contains(baseName)) {
            mdFile.writeText("""
                @${file.nameWithoutExtension}
                #### example:
                [example file](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/master/examples/data/${file.name})
                [folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/master/folded/${file.nameWithoutExtension}-folded.java)
            """.trimIndent())

            println("Generated: ${mdFile.name}")
        }
    }
}

fun updateWikiHomeWithFeatures() {
    val homeFile = File("../wiki/Home.md")
    if (homeFile.exists()) {
        val homeContent = homeFile.readText()
        val toAdd = StringBuilder()

        processTestFiles { file, baseName, _ ->
            if (!homeContent.contains(baseName) && !excludedFeatures.contains(baseName)) {
                // Create proper format for each feature
                val featureDescription = when (baseName) {
                    "methodDefaultParameters" -> "Default parameter values inline for overloaded methods"
                    "Experimental" -> "Experimental features"
                    else -> "Feature description"
                }

                val featureExplanation = when (baseName) {
                    "methodDefaultParameters" -> ""
                    "Experimental" -> "Various experimental features for advanced code folding."
                    else -> ""
                }

                toAdd.append("""
                ## ${baseName.lowercase()}
                ### $featureDescription
                $featureExplanation
                - [Example](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/examples/data/${file.nameWithoutExtension}.java)
                - [Folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/main/folded/${file.nameWithoutExtension}-folded.java)
                """.trimIndent())

                println("Found feature not in Home.md: $baseName")
            }
        }

        if (toAdd.isNotEmpty()) {
            val updatedContent = homeContent + toAdd.toString()
            homeFile.writeText(updatedContent)
            println("Updated Home.md with new features")
        }
    } else {
        println("Warning: wiki/Home.md not found")
    }
}

createFilesPerConfig()
updateWikiHomeWithFeatures()
