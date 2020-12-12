plugins {
    `kotlin-dsl`
}

repositories {
    // for kotlin-dsl plugin
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.4.0")

    implementation(
            group = "io.spring.dependency-management",
            name  = "io.spring.dependency-management.gradle.plugin",
            version = "1.0.10.RELEASE",
            ext = "pom"
    )
}