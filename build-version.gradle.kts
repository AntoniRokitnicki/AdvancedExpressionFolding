import java.io.*
import java.time.Instant
import java.util.*

tasks.register("minorRelease") {
    doLast {
        val propertiesFile = file("gradle.properties")
        val properties1 = Properties()
        propertiesFile.inputStream().use<FileInputStream, Unit> { inputStream: InputStream ->
            properties1.load(inputStream)
        }
        val properties = properties1

        val version = properties.getProperty("pluginVersion")
            ?: throw GradleException("pluginVersion not found in gradle.properties")
        val versionParts = version.split('.').toMutableList()
        if (versionParts.size != 3) {
            throw GradleException("pluginVersion must have three parts separated by dots")
        }
        val minorPart =
            versionParts[1].toIntOrNull() ?: throw GradleException("Second part of pluginVersion is not a number")
        versionParts[1] = (minorPart + 1).toString()
        val newVersion = versionParts.joinToString(".")

        properties.setProperty("pluginVersion", newVersion)
        propertiesFile.writer().use<OutputStreamWriter, Unit> { writer: Writer ->
            properties.store(writer, null)
        }

        println("Updated pluginVersion to $newVersion")
    }
}

tasks.register("canaryRelease") {
    doLast {
        val propertiesFile = file("gradle.properties")
        val properties1 = Properties()
        propertiesFile.inputStream().use<FileInputStream, Unit> { inputStream: InputStream ->
            properties1.load(inputStream)
        }
        val properties = properties1

        val version = properties.getProperty("pluginVersion")
            ?: throw GradleException("pluginVersion not found in gradle.properties")
        val timestamp = Instant.now().toEpochMilli()
        val newVersion = "$version-$timestamp-canary"

        properties.setProperty("pluginVersion", newVersion)
        propertiesFile.writer().use<OutputStreamWriter, Unit> { writer: Writer ->
            properties.store(writer, null)
        }

        println("Updated pluginVersion to $newVersion")
    }
}
