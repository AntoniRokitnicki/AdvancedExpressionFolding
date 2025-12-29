#!/usr/bin/env -S kotlinc -script --
import java.io.File
import java.util.Locale

val srcDir = File("src")

fun camelToSentence(name: String): String {
    return name
        .replace(Regex("([a-z])([A-Z])"), "$1 $2")
        .toLowerCase(Locale.ROOT)
}

data class PackageInfo(
    val classes: MutableList<String> = mutableListOf(),
    var hasJava: Boolean = false
)

val packages = mutableMapOf<File, PackageInfo>()

srcDir.walkTopDown()
    .filter { it.isFile && (it.extension == "java" || it.extension == "kt") && it.name != "package-info.java" && it.name != "package-info.kt" }
    .forEach { file ->
        val dir = file.parentFile
        val info = packages.computeIfAbsent(dir) { PackageInfo() }
        info.classes.add(file.nameWithoutExtension)
        if (file.extension == "java") info.hasJava = true
    }

packages.forEach { (dir, info) ->
    val pkgName = dir.relativeTo(srcDir).path.replace(File.separatorChar, '.')
    val sb = StringBuilder()
    sb.append("/**\n")
    sb.append(" * Provides classes:\n")
    info.classes.sorted().forEach { cls ->
        sb.append(" * - ")
        sb.append(cls)
        sb.append(" - ")
        sb.append(camelToSentence(cls))
        sb.append("\n")
    }
    sb.append(" */\n")

    val ext = if (info.hasJava) "java" else "kt"
    if (ext == "kt") {
        sb.append("@file:JvmName(\"package-info\")\n")
        sb.append("package ")
        sb.append(pkgName)
        sb.append("\n")
        File(dir, "package-info.java").takeIf { it.exists() }?.delete()
    } else {
        sb.append("package ")
        sb.append(pkgName)
        sb.append(";\n")
        File(dir, "package-info.kt").takeIf { it.exists() }?.delete()
    }

    File(dir, "package-info.$ext").writeText(sb.toString())
    println("Generated package-info.$ext for $pkgName")
}
