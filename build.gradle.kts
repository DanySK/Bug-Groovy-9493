import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.71"
    application
}

repositories {
    mavenCentral()
}

val kotest_runner_junit5: String = "io.kotest:kotest-runner-junit5-jvm:4.0.1"
val kotest_assertions: String = "io.kotest:kotest-assertions-core-jvm:4.0.1"
dependencies {
    testImplementation("org.codehaus.groovy:groovy-jsr223:3.0.2")
    testImplementation(kotest_assertions)
    testImplementation(kotest_runner_junit5)
    testImplementation(kotlin("stdlib-jdk8"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
