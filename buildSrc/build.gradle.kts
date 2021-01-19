plugins {
    `kotlin-dsl`
}

repositories {
    // for kotlin-dsl plugin
    // gradlePluginPortal()
    maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.4.2")
    implementation("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
    // kotlin项目构建必须依赖此插件
    implementation(kotlin("gradle-plugin"))
}