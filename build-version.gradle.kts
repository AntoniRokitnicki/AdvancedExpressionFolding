import java.io.File
import java.io.InputStream
import java.io.Writer
import java.time.Instant
import java.util.Properties

fun getPropertiesFile(): File = file("gradle.properties")

fun readProperties(file: File): Properties {
    val properties = Properties()
    file.inputStream().use { inputStream: InputStream ->
        properties.load(inputStream)
    }
    return properties
}

fun readVersion(properties: Properties): String =
    properties.getProperty("pluginVersion") ?: throw GradleException("pluginVersion not found in gradle.properties")

fun incrementMinorVersion(version: String): String {
    val versionParts = version.split('.').toMutableList()
    if (versionParts.size != 3) {
        throw GradleException("pluginVersion must have three parts separated by dots")
    }

    val minorPart = versionParts[1].toIntOrNull() ?: throw GradleException("Second part of pluginVersion is not a number")
    versionParts[1] = (minorPart + 1).toString()

    return versionParts.joinToString(".")
}

fun addTimestampSuffix(version: String): String {
    val timestamp = Instant.now().toEpochMilli()
    return "$version-$timestamp-canary"
}

fun saveProperties(file: File, properties: Properties) {
    file.writer().use { writer: Writer ->
        properties.store(writer, null)
    }
}

tasks.register("minorRelease") {
    doLast {
        val propertiesFile = getPropertiesFile()
        val properties = readProperties(propertiesFile)

        val version = readVersion(properties)
        val newVersion = incrementMinorVersion(version)

        properties.setProperty("pluginVersion", newVersion)
        saveProperties(propertiesFile, properties)

        println("Updated pluginVersion to $newVersion")
    }
}

tasks.register("canaryRelease") {
    doLast {
        val propertiesFile = getPropertiesFile()
        val properties = readProperties(propertiesFile)

        val version = readVersion(properties)
        val newVersion = addTimestampSuffix(version)

        properties.setProperty("pluginVersion", newVersion)
        saveProperties(propertiesFile, properties)

        println("Updated pluginVersion to $newVersion")
    }
}
