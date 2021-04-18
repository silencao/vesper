plugins {
    id("my.project.kotlin-application-conventions")
    id("my.project.kotlin-plugin-serialization")
}

application {
    mainClass.set("demo.MainKt")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
}