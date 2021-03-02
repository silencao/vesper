plugins {
    id("my.project.kotlin-common-conventions")
    application
}

dependencies {
    testImplementation(kotlin("test-junit"))
}

application {
    mainClass.set("MainKt")
}
