plugins {
    id("my.project.kotlin-common-conventions")
    id("my.project.spring-boot-conventions")
    id("my.project.spring-dependency-management")
    kotlin("plugin.spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
}
