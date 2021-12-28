plugins {
    id("my.project.kotlin-jvm-common")
    id("my.project.spring-boot-conventions")
    application
}
/* Creating a Java uber or fat JAR
 --> https://docs.gradle.org/current/userguide/building_java_projects.html#sec:java_packaging */
tasks.register<Jar>("uberJar") {
    group = BasePlugin.BUILD_GROUP
    archiveClassifier.set("uber")

    manifest.attributes("Main-Class" to application.mainClass.get())

    dependsOn(configurations.runtimeClasspath)
    from(sourceSets.main.get().output)
    from(configurations.runtimeClasspath
        .get()
        .filter { it.name.endsWith(Jar.DEFAULT_EXTENSION) }
        .map { zipTree(it) })
}
