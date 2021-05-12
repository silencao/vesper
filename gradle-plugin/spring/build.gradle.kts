plugins {
    id("my.project.kotlin-dsl")
}

dependencies {
    val useSpringBootVersion = project.extra["use.spring.boot.version"] as String

    implementation(platform("org.springframework.boot:spring-boot-dependencies:$useSpringBootVersion"))
    implementation("org.springframework.boot:spring-boot-gradle-plugin:$useSpringBootVersion")
    implementation("io.spring.gradle:dependency-management-plugin")
}