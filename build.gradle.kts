plugins {
    id("my.project.custom-wrapper")
    id("my.project.spring-boot-upgrade-plugin")
}

allprojects {
    group = "im.silen"
    version = "0.0.5"
}

tasks.withType<spring.boot.UpgradeSpringBootTask> {
    outputFile = projectDir.resolve("gradle-plugin/${Project.GRADLE_PROPERTIES}")
}
