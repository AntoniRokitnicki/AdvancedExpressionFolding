import java.io.File
import java.io.InputStream
import java.io.Writer
import java.time.Instant
import java.util.*

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
        val timestamp = Instant.now().toEpochMilli()
        val major = (timestamp / 1000000000) % 1000
        val minor = (timestamp / 1000000) % 1000
        val patch = (timestamp / 1000) % 1000
        val newVersion = "$major.$minor.$patch-canary"

        properties.setProperty("pluginVersion", newVersion)
        saveProperties(propertiesFile, properties)

        println("Updated pluginVersion to $newVersion")
    }
}

tasks.register("canaryEapRelease") {
    doLast {
        val propertiesFile = getPropertiesFile()
        val properties = readProperties(propertiesFile)

        val version = readVersion(properties)
        val newVersion = "$version".replace("-canary", "-eap-canary")

        properties.setProperty("pluginVersion", newVersion)
        properties.setProperty("pluginSinceBuild", "242.10180.25")
        properties.setProperty("pluginUntilBuild", "245.*")
        saveProperties(propertiesFile, properties)

        println("Updated pluginVersion to $newVersion")
    }
}
