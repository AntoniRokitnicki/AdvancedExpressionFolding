#!/usr/bin/env -S kotlinc -script --
import java.io.File
import java.util.*

val basePath: String = System.getProperty("project.dir", "..")
val varName = System.getProperty("varName") ?: "patternMatchingInstanceof"
val varText = System.getProperty("varText") ?: "Pattern Matching for instanceof (JEP 394)"

val settingsFile = "$basePath/src/com/intellij/advancedExpressionFolding/settings/AdvancedExpressionFoldingSettings.kt"
settingsFile.doInFile {
    it.insertBeforeMarker("// NEW OPTION VAR", "        override var $varName: Boolean = true,")
}

val unclassifiedStateFile =
    "$basePath/src/com/intellij/advancedExpressionFolding/settings/UnclassifiedFeatureState.kt"
unclassifiedStateFile.doInFile { lines ->
    val result = mutableListOf<String>()
    var markerCount = 0

    lines.forEach { line ->
        if (line.trim().startsWith("// NEW OPTION VAR")) {
            markerCount += 1
            when (markerCount) {
                1 -> result.add("    val $varName: Boolean")
                2 -> result.add("    override val $varName: Boolean = true,")
            }
        }
        result.add(line)
    }

    if (markerCount < 2) {
        error("Expected two '// NEW OPTION VAR' markers in UnclassifiedFeatureState.kt")
    }

    result
}

val exampleFileName = "${varName.replaceFirstChar(Char::titlecase)}TestData"
val checkboxFile = "$basePath/src/com/intellij/advancedExpressionFolding/settings/view/CheckboxesProvider.kt"
checkboxFile.doInFile {
    it.insertBeforeMarker("// NEW OPTION", """        registerCheckbox(state::$varName, "$varText") {
            example("$exampleFileName.java")
            link("https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki#$varName")
        }""")
}

val exampleFile = "$basePath/examples/data/$exampleFileName.java"
exampleFile.doInFile {
    it + """package data;

public class $exampleFileName {

    public void main(String arg, int i, Object o, Data data) {
        System.out.println("String arg: " + arg);
        System.out.println("int i: " + i);
        System.out.println("Object o: " + o);
        System.out.println("Data data: " + data.getData());
        System.out.println("Data string: " + data.getString());
        System.out.println("Data ok: " + data.isOk());
    }

    static class Data {
        Data data;
        boolean ok;
        String string;
        public Data getData() {
            return data;
        }
        public void setData(Data data) {
            this.data = data;
        }
        public boolean isOk() {
            return ok;
        }
        public void setOk(boolean ok) {
            this.ok = ok;
        }
        public String getString() {
            return string;
        }
        public void setString(String string) {
            this.string = string;
        }
    }
}
"""
}

val testFile = "$basePath/test/com/intellij/advancedExpressionFolding/FoldingTest.kt"
testFile.doInFile { list ->
    list.insertBeforeMarker("// NEW OPTION", """
    /**
     * [data.$exampleFileName]
     */
    @Test
    fun ${exampleFileName.replaceFirstChar { it.lowercase(Locale.getDefault()) }}() {
        doFoldingTest(state::$varName)
    }""")
}


fun String.doInFile(convert: (List<String>) -> List<String>) {
    fun String.readFile(): List<String> {
        val file = File(this)
        if (!file.exists()) {
            return emptyList()
        }
        return file.readLines()
    }

    fun String.writeFile(lines: List<String>) {
        File(this).writeText(lines.joinToString("\n"))
    }
    this.writeFile(convert(readFile()))
}

fun List<String>.insertBeforeMarker(commentMarker: String, replacementCode: String): List<String> {
    val result = mutableListOf<String>()
    forEach { line ->
        if (line.trim().startsWith(commentMarker)) {
            result.add(replacementCode)
            result.add(line)
        } else {
            result.add(line)
        }
    }
    return result
}
