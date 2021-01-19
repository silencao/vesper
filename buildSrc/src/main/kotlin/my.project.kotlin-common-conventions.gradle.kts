plugins {
    kotlin("jvm")
    id("my.project.eclipse-build-conventions")
    id("my.project.spring-dependency-management")
    id("my.project.test-conventions")
}

dependencies {
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}