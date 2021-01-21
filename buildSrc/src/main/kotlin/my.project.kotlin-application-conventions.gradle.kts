plugins {
    id("my.project.kotlin-common-conventions")
    id("my.project.spring-boot-conventions")
    kotlin("plugin.spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
}
