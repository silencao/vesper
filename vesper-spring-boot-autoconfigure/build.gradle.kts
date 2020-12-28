plugins {
    id("my.project.java-library-conventions")
}

dependencies {
    api(project(":vesper-spring-boot-library"))
    compileOnly("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("org.springframework.boot:spring-boot-autoconfigure")
}
