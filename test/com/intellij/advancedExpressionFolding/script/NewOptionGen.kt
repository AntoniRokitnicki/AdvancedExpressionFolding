package com.intellij.advancedExpressionFolding.script


import ai.grazie.utils.capitalize
import java.io.File

// -DvarName="const" -DvarText="Simplify public static final to const"
fun main() {
    val basePath = System.getProperty("project.dir", "/Users/ant/h/AdvancedExpressionFolding")
    val varName = System.getProperty("varName") ?: TODO("Property varName not found")
    val varText = System.getProperty("varText") ?: TODO("Property varText not found")

    val propertyFile = "$basePath/src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingSettings.kt"
    propertyFile.doInFile {
        it.insertBeforeMarker("// NEW OPTION VAR", "        override var $varName: Boolean = true,")
            .insertBeforeMarker("// NEW OPTION VAL", "        val $varName: Boolean")
    }

    val exampleFileName = "${varName.capitalize()}TestData"
    val checkboxFile = "$basePath/src/com/intellij/advancedExpressionFolding/AdvancedExpressionFoldingOptionsProvider.kt"
    checkboxFile.doInFile {
        it.insertBeforeMarker("// NEW OPTION", """        checkBox("$varText",
            state::$varName,
            mapOf("$exampleFileName.java" to null),
        )
        """)
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
    testFile.doInFile {
        it.insertBeforeMarker("// NEW OPTION", """
    /**
     * [data.$exampleFileName]
     */
    fun test${exampleFileName}() {
        doFoldingTest(state::$varName)
    }""")
    }
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
