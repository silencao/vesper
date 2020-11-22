version = "0.0.1"

dependencies {
}

tasks.getByName<Jar>("jar") {
    enabled = true
    archiveClassifier.set("lib")
}
