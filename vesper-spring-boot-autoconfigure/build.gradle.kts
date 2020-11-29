plugins {
    id("java-library")
}

version = "0.0.1"

dependencies {
    api(project(":vesper-spring-boot-library"))

    implementation("org.springframework.boot:spring-boot-autoconfigure")
}

tasks.getByName<Jar>("jar") {
    enabled = true
    archiveClassifier.set("lib")
}