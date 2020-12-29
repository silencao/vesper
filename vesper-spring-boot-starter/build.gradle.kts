plugins {
    id("my.project.java-library-conventions")
}

dependencies {
    api(project(":vesper-spring-boot-library"))
    api(project(":vesper-spring-boot-autoconfigure"))
}