plugins {
    id("my.project.kotlin-dsl")
}

dependencies {
    val use_spring_boot_version: String by project

    implementation("org.springframework.boot:spring-boot-gradle-plugin:$use_spring_boot_version")
}