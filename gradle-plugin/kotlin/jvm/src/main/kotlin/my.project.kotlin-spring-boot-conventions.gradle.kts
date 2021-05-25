plugins {
    id("my.project.kotlin-jvm-common")
    id("my.project.spring-boot-conventions")
    kotlin("plugin.spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
}
