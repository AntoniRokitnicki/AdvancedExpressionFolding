#!/usr/bin/env -S kotlinc -script --
import java.io.File

val exampleFolder = "/Users/ant/h/AdvancedExpressionFolding/examples/data"
val foldedFolder = "/Users/ant/h/AdvancedExpressionFolding/folded"
val wikiFolder = "/Users/ant/h/AdvancedExpressionFolding/wiki/new-doc"

fun main() {
    File(exampleFolder).listFiles()?.forEach { file ->
        if (file.isFile) {
            val baseName = file.nameWithoutExtension.removeSuffix("TestData")
            val mdFileName = "$baseName.md"
            val mdFile = File(wikiFolder, mdFileName)

            mdFile.writeText("""
                @${file.nameWithoutExtension}
                #### example:
                [example file](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/master/examples/data/${file.name})
                [folded](https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/blob/master/folded/${file.nameWithoutExtension}-folded.java)
            """.trimIndent())

            println("Generated: $mdFileName")
        }
    }
}

main()