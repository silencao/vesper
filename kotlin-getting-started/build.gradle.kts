plugins {
    id("my.project.kotlin-common-conventions")
    application
}

dependencies {
    testImplementation(kotlin("test-junit"))
}

application {
    mainClass.set("demo.MainKt")
}

/* Creating a Java uber or fat JAR
 --> https://docs.gradle.org/current/userguide/building_java_projects.html#sec:java_packaging */
tasks.jar {
    archiveClassifier.set("uber")

    manifest.attributes("Main-Class" to application.mainClass.get())

    dependsOn(configurations.runtimeClasspath)
    from({
        sourceSets.main.get().output
        configurations.runtimeClasspath
            .get()
            .filter { it.name.endsWith("jar") }
            .map    { zipTree(it) }
    })

}
