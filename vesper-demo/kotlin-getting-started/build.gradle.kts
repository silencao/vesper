plugins {
    id("my.project.kotlin-jvm-application")
    id("my.project.kotlin-plugin-serialization")
}

application {
    mainClass.set("demo.MainKt")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
}