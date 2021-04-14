plugins {
    io.spring.`dependency-management`
}

allprojects {
    group = "im.silen"
    version = "0.0.5"
}

buildscript {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public") }
    }
    dependencies {
        classpath(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
        classpath("io.spring.gradle:dependency-management-plugin")
    }
}

tasks.register<WriteProperties>("updateSpringBoot") {
    val springBootVersion = "2.4.4";
    dependencyManagement.imports { mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion") }
    outputFile = projectDir.resolve("buildSrc/${Project.GRADLE_PROPERTIES}")

    property("use.kotlin.version", dependencyManagement.importedProperties.get("kotlin.version") ?: embeddedKotlinVersion)
    property("use.spring.boot.version", springBootVersion)

}