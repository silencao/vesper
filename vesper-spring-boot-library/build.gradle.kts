plugins {
    id("my.project.java-library-conventions")
}

dependencies {
    compileOnly("org.springframework.boot:spring-boot-starter-json")
    compileOnly("org.springframework.boot:spring-boot-starter-data-redis")
}