plugins {
    id("my.project.java-library-conventions")
}

dependencies {
    api(project(":vesper-spring-boot-library"))

    implementation("org.springframework.boot:spring-boot-autoconfigure")
}
