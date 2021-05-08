plugins {
    id("my.project.kotlin-common-conventions")
    id("my.project.spring-boot-conventions")
    kotlin("plugin.spring")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
}
