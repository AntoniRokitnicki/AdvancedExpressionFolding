plugins {
    alias(libs.plugins.kotlin)
    application
    alias(libs.plugins.graalvmNative)
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.intellij.advancedExpressionFolding.tools.sweeper.FoldingSuiteSweeperKt")
    applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8")
}

dependencies {
    implementation(kotlin("stdlib"))
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("folding-suite-sweeper")
            mainClass.set(application.mainClass)
        }
    }
}
