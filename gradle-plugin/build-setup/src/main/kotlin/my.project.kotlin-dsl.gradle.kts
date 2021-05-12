plugins {
    id("org.gradle.kotlin.kotlin-dsl")
}

dependencies {
    testImplementation(kotlin("test-testng"))
}

tasks.test {
    useTestNG()
}