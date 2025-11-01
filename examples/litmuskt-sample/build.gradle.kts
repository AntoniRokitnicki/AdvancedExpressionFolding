plugins {
    kotlin("multiplatform") version "2.0.21"
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    linuxX64 {
        binaries {
            executable {
                entryPoint = "litmuskt.sample.LinuxMainKt"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.research:litmuskt:0.1.0-SNAPSHOT")
            }
        }
        val jvmMain by getting
        val linuxX64Main by getting
    }
}

val jvmMainCompilation = kotlin.targets.getByName("jvm").compilations.getByName("main")

tasks.register<JavaExec>("runJvm") {
    group = "application"
    description = "Run the message passing litmus test on the JVM."
    mainClass.set("litmuskt.sample.JvmMainKt")
    classpath(
        jvmMainCompilation.output.allOutputs,
        jvmMainCompilation.runtimeDependencyFiles
    )
    dependsOn(jvmMainCompilation.compileTaskProvider)
}
