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

/*tasks.jar {
    manifest.attributes(
        "Main-Class" to application.mainClass.get()
    )
}*/
