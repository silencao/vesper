plugins {
    my.project.`kotlin-application-conventions`
//    kotlin("plugin.serialization")
//    org.jetbrains.kotlin.plugin.serialization
    `kotlinx-serialization`
}

application {
    mainClass.set("demo.MainKt")
}

dependencies {
//    platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES) {
//        println(this)
//    }
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("io.spring.gradle:dependency-management-plugin")
}