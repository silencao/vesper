plugins {
    id("my.project.spring-boot-upgrade-plugin")
}

allprojects {
    group = "im.silen"
    version = "0.0.5"
}

tasks.withType<spring.boot.UpgradeSpringBootTask> {
    outputFile = projectDir.resolve("vesper-gradle-plugin/${Project.GRADLE_PROPERTIES}")
}