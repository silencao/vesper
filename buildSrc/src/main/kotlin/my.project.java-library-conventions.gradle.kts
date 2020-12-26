plugins {
    id("my.project.java-common-conventions")

    `java-library`
}

tasks.withType<Jar> {
    archiveClassifier.set("lib")
}