plugins {
    id("my.project.java-common-conventions")

    `java-library`
}

tasks.getByName<Jar>("jar") {
    enabled = true
    archiveClassifier.set("lib")
}