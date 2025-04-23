plugins {
    id("java")
    alias(libs.plugins.lombok)
}

dependencies {
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
    testCompileOnly(libs.commons.lang3)
    testCompileOnly(libs.annotations)
    testCompileOnly(libs.jsr305)
    testCompileOnly(libs.slf4j.api)
}

sourceSets {
    named("test") {
        java.srcDir(".")
    }
}

repositories {
    mavenCentral()
}