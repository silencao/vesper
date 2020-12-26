plugins {
    `kotlin-dsl`
}

repositories {
    // for kotlin-dsl plugin
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.4.1")
    implementation("io.spring.gradle:dependency-management-plugin:1.0.10.RELEASE")
}